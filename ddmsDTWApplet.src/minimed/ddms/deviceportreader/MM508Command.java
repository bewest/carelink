/*     */ package minimed.ddms.deviceportreader;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ abstract class MM508Command extends MMPump
/*     */ {
/*     */   static final byte CMD_SET_ACCESS_CODE = 1;
/*     */   private static final int IO_DELAY_MS = 300;
/*     */   private static final boolean STOP_ON_BAD_CRC = true;
/*     */   private static final int MIN_INIT_PUMP = 4;
/*     */   private static final int MAX_INIT_PUMP = 8;
/*     */   private static final int REINIT_CMD_DELAY_MS = 2000;
/*     */   private ComStation1 m_comStation;
/*     */ 
/*     */   MM508Command()
/*     */   {
/*  83 */     m_baudRate = 7;
/*  84 */     m_ioDelayMS = 300;
/*     */   }
/*     */ 
/*     */   void initComStation()
/*     */     throws IOException
/*     */   {
/*  97 */     Contract.pre(getRS232Port() != null);
/*  98 */     Contract.pre(getRS232Port().isOpen());
/*     */ 
/* 100 */     setPhase(3);
/* 101 */     this.m_comStation = new ComStation1(getRS232Port());
/* 102 */     this.m_comStation.initCommunications(this.m_deviceClassID);
/*     */   }
/*     */ 
/*     */   void shutDownComStation()
/*     */     throws IOException
/*     */   {
/* 111 */     if (this.m_comStation != null) {
/* 112 */       this.m_comStation.endCommunications();
/* 113 */       this.m_comStation = null;
/*     */     }
/*     */   }
/*     */ 
/*     */   void shutDownSerialPort()
/*     */     throws IOException
/*     */   {
/* 123 */     if (getRS232Port() != null)
/*     */     {
/* 125 */       getRS232Port().close();
/* 126 */       setRS232Port(null);
/*     */     }
/*     */   }
/*     */ 
/*     */   void initDevice()
/*     */     throws IOException, BadDeviceCommException, ConnectToPumpException
/*     */   {
/* 142 */     Contract.pre(getRS232Port() != null);
/* 143 */     Contract.pre(getRS232Port().isOpen());
/*     */ 
/* 145 */     setPhase(4);
/*     */ 
/* 148 */     reinitDevice();
/*     */ 
/* 153 */     this.m_cmdReadErrorStatus.execute();
/*     */     try {
/* 155 */       decodeReply(this.m_cmdReadErrorStatus);
/*     */     } catch (BadDeviceValueException localBadDeviceValueException) {
/* 157 */       throw new ConnectToPumpException("Bad Error Status Reply", 2, MedicalDevice.Util.makeString(this.m_cmdReadErrorStatus.m_rawData));
/*     */     }
/*     */ 
/* 163 */     if (this.m_settingErrorStatus != 0)
/* 164 */       throw new ConnectToPumpException("Pump Alarm (error)", 2, Integer.toString(this.m_settingErrorStatus));
/*     */   }
/*     */ 
/*     */   final void shutDownPump()
/*     */     throws BadDeviceCommException, IOException
/*     */   {
/* 177 */     if ((getRS232Port() != null) && (this.m_comStation != null) && 
/* 178 */       (this.m_cmdCancelSuspend != null))
/* 179 */       this.m_cmdCancelSuspend.execute();
/*     */   }
/*     */ 
/*     */   final int verifyCRC(int[] paramArrayOfInt, int paramInt1, int paramInt2, String paramString)
/*     */     throws BadDeviceValueException
/*     */   {
/* 201 */     Contract.pre(paramArrayOfInt != null);
/* 202 */     Contract.pre(paramArrayOfInt.length >= paramInt1 + paramInt2);
/*     */ 
/* 204 */     int i = MedicalDevice.Util.makeUnsignedShort(paramArrayOfInt[(paramInt2 - 2)], paramArrayOfInt[(paramInt2 - 1)]);
/*     */ 
/* 208 */     int j = MedicalDevice.Util.computeCRC16CCITT(paramArrayOfInt, paramInt1, paramInt2 - 2);
/*     */ 
/* 210 */     if (i != j)
/*     */     {
/* 212 */       throw new BadDeviceValueException("ERROR - " + paramString + " record has bad CRC value of " + i + " (expected " + j + ").");
/*     */     }
/*     */ 
/* 222 */     return i;
/*     */   }
/*     */ 
/*     */   private final void reinitDevice()
/*     */     throws IOException, BadDeviceCommException
/*     */   {
/* 234 */     Contract.pre(getRS232Port() != null);
/* 235 */     Contract.pre(getRS232Port().isOpen());
/*     */ 
/* 238 */     int i = 0;
/*     */ 
/* 240 */     logInfo(this, "reinitDevice: reinitializing device...");
/*     */ 
/* 243 */     for (int j = 0; (j < 8) && (i < 4) && (!isHaltRequested()); )
/*     */     {
/*     */       try
/*     */       {
/* 247 */         this.m_cmdSetAccessCode.execute();
/* 248 */         i++;
/*     */       } catch (BadDeviceCommException localBadDeviceCommException) {
/* 250 */         if (j == 7)
/*     */         {
/* 252 */           logError(this, "reinitDevice: cmd failed with exception " + localBadDeviceCommException);
/* 253 */           throw localBadDeviceCommException;
/*     */         }
/*     */ 
/* 258 */         logInfo(this, "reinitDevice: cmd failed with exception " + localBadDeviceCommException);
/* 259 */         MedicalDevice.Util.sleepMS(2000);
/* 260 */         getRS232Port().readUntilEmpty();
/* 261 */         i = 0;
/*     */       }
/* 244 */       j++;
/*     */     }
/*     */   }
/*     */ 
/*     */   final class Command extends MMPump.Command
/*     */   {
/*     */     private static final byte EOT = 4;
/*     */     private static final byte ACK = 6;
/*     */     private static final byte NAK = 21;
/*     */     private static final int NUM_CMD_BYTES = 6;
/*     */     private static final int LENGTH_BYTE = 0;
/*     */     private static final int LENGTH_COMP_BYTE = 1;
/*     */     private static final int SEQ_NUM_BYTE = 2;
/*     */     private static final int CMD_BYTE = 3;
/*     */     private static final int HEADER_SIZE = 4;
/*     */     private static final int BAD_SEQ_NAK = 8;
/* 294 */     private int m_nakCode = 0;
/*     */ 
/*     */     Command(int paramString, String paramInt1, int paramInt2, int paramInt3, int arg6)
/*     */     {
/* 309 */       super(paramString, paramInt1, paramInt2, paramInt3, i);
/*     */     }
/*     */ 
/*     */     Command(int paramString, String paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int arg8)
/*     */     {
/* 326 */       super(paramString, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, i);
/*     */ 
/* 328 */       this.m_dataOffset = 0;
/*     */     }
/*     */ 
/*     */     void execute()
/*     */       throws BadDeviceCommException
/*     */     {
/* 343 */       int i = 0;
/* 344 */       int j = 0;
/*     */ 
/* 346 */       Contract.pre(MM508Command.this.getRS232Port() != null);
/* 347 */       Contract.pre(MM508Command.this.getRS232Port().isOpen());
/*     */ 
/* 349 */       MedicalDevice.m_lastCommandDescription = this.m_description;
/*     */       do
/*     */       {
/*     */         try
/*     */         {
/* 355 */           sendAndRead();
/* 356 */           i = 1;
/*     */         }
/*     */         catch (BadDeviceCommException localBadDeviceCommException) {
/* 359 */           if (this.m_commandCode == 1) {
/* 360 */             throw new BadDeviceCommException("execute: cmd " + this.m_commandCode + " (" + this.m_description + ") failed during pump initialization with exception " + localBadDeviceCommException);
/*     */           }
/*     */ 
/* 364 */           j++;
/* 365 */           MM508Command.this.getRS232Port().readUntilEmpty();
/* 366 */           if (j <= this.m_maxRetries) {
/* 367 */             MedicalDevice.logWarning(this, "execute: cmd failed with exception: " + localBadDeviceCommException + "; retrying (attempts = " + (j + 1) + ")");
/*     */ 
/* 369 */             MM508Command.this.setState(7);
/*     */ 
/* 371 */             if (this.m_nakCode == 8)
/*     */             {
/* 373 */               MMPump.m_sequenceNumber = (byte)(MMPump.m_sequenceNumber + 1 & 0x3);
/*     */             }
/*     */             else
/*     */               try {
/* 377 */                 MM508Command.this.reinitDevice();
/*     */               }
/*     */               catch (IOException localIOException) {
/*     */               }
/*     */           }
/*     */           else {
/* 383 */             MedicalDevice.logError(this, "cmd " + MedicalDevice.Util.getHex(this.m_commandCode) + " (" + this.m_description + ") failed after " + j + " attempts" + "; exception = " + localBadDeviceCommException);
/*     */ 
/* 389 */             throw new BadDeviceCommException("execute: cmd " + this.m_commandCode + " (" + this.m_description + ") failed after " + j + " attempts");
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 396 */       while ((i == 0) && (j <= this.m_maxRetries) && (!MM508Command.this.isHaltRequested()));
/*     */     }
/*     */ 
/*     */     private final void sendCommand()
/*     */       throws BadDeviceCommException
/*     */     {
/* 409 */       int[] arrayOfInt1 = new int[3];
/*     */ 
/* 411 */       Contract.pre(MM508Command.this.getRS232Port() != null);
/* 412 */       Contract.pre(MM508Command.this.getRS232Port().isOpen());
/*     */ 
/* 414 */       this.m_nakCode = 0;
/*     */ 
/* 417 */       if (this.m_commandCode == 1) {
/* 418 */         MMPump.m_sequenceNumber = 0;
/*     */       }
/* 423 */       else if (MM508Command.this.getState() != 7) {
/* 424 */         MMPump.m_sequenceNumber = (byte)(MMPump.m_sequenceNumber + 1 & 0x3);
/*     */       }
/*     */ 
/* 428 */       MedicalDevice.logInfo(this, "sendCommand: sending cmd " + MedicalDevice.Util.getHex(this.m_commandCode) + " (" + this.m_description + ")" + " m_sequenceNumber=" + MMPump.m_sequenceNumber);
/*     */       try
/*     */       {
/* 434 */         int[] arrayOfInt2 = buildPacket();
/* 435 */         int i = 0;
/*     */ 
/* 438 */         MM508Command.this.getRS232Port().write(arrayOfInt2);
/*     */         do
/*     */         {
/* 444 */           arrayOfInt1[0] = MM508Command.this.getRS232Port().read();
/* 445 */           i++;
/*     */         }
/* 447 */         while (((arrayOfInt1[0] == 90) || (arrayOfInt1[0] == -1)) && (i < this.m_maxRetries + 1));
/*     */ 
/* 450 */         if ((arrayOfInt1[0] == 90) || (arrayOfInt1[0] == -1)) {
/* 451 */           throw new BadDeviceCommException("sendCommand: TIMEOUT while reading reply to cmd " + MedicalDevice.Util.getHex(this.m_commandCode) + " (" + this.m_description + ")");
/*     */         }
/*     */ 
/* 457 */         arrayOfInt1[1] = MM508Command.this.getRS232Port().read();
/*     */ 
/* 460 */         arrayOfInt1[2] = MM508Command.this.getRS232Port().read();
/*     */ 
/* 463 */         int j = MedicalDevice.Util.computeCRC8(arrayOfInt1, 0, arrayOfInt1.length - 1) & 0xFF;
/* 464 */         if (arrayOfInt1[2] != j) {
/* 465 */           throw new BadDeviceCommException("sendCommand: cmd " + MedicalDevice.Util.getHex(this.m_commandCode) + " (" + this.m_description + ")" + " resulted in bad CRC value of " + arrayOfInt1[2] + " (expected " + j + ") " + "(byte data = " + "<" + MedicalDevice.Util.getHex(arrayOfInt1) + ">)");
/*     */         }
/*     */ 
/* 473 */         if ((arrayOfInt1[0] != 6) && (arrayOfInt1[0] != 21)) {
/* 474 */           throw new BadDeviceCommException("sendCommand: cmd " + MedicalDevice.Util.getHex(this.m_commandCode) + " (" + this.m_description + ")" + " resulted in bad ACK/NAK value of " + MedicalDevice.Util.getHex(arrayOfInt1[0]));
/*     */         }
/*     */ 
/* 480 */         if (arrayOfInt1[0] != 6)
/*     */         {
/* 482 */           this.m_nakCode = arrayOfInt1[1];
/* 483 */           throw new BadDeviceCommException("sendCommand: cmd " + MedicalDevice.Util.getHex(this.m_commandCode) + " (" + this.m_description + ")" + " resulted in NAK; error code = <" + this.m_nakCode + "> " + "(byte data = " + "<" + MedicalDevice.Util.getHex(arrayOfInt1) + ">)");
/*     */         }
/*     */ 
/*     */       }
/*     */       catch (IOException localIOException)
/*     */       {
/* 489 */         throw new BadDeviceCommException("sendCommand: ERROR - an IOException  has occurred processing cmd " + MedicalDevice.Util.getHex(this.m_commandCode) + " (" + this.m_description + ")");
/*     */       }
/*     */     }
/*     */ 
/*     */     private final void sendAck()
/*     */       throws IOException
/*     */     {
/* 503 */       int[] arrayOfInt = new int[3];
/* 504 */       int i = 0;
/*     */ 
/* 506 */       Contract.pre(MM508Command.this.getRS232Port() != null);
/* 507 */       Contract.pre(MM508Command.this.getRS232Port().isOpen());
/*     */ 
/* 509 */       MedicalDevice.logInfo(this, "sendAck: sending cmd " + MedicalDevice.Util.getHex(6));
/*     */ 
/* 513 */       arrayOfInt[(i++)] = 6;
/*     */ 
/* 516 */       arrayOfInt[(i++)] = 0;
/*     */ 
/* 519 */       arrayOfInt[(i++)] = MedicalDevice.Util.computeCRC8(arrayOfInt, 0, arrayOfInt.length - 1);
/*     */ 
/* 522 */       MM508Command.this.getRS232Port().write(arrayOfInt);
/*     */     }
/*     */ 
/*     */     private final int readDeviceData(int[] paramArrayOfInt)
/*     */       throws BadDeviceCommException
/*     */     {
/* 538 */       int[] arrayOfInt1 = new int[4 + this.m_dataOffset];
/*     */ 
/* 540 */       Contract.pre(MM508Command.this.getRS232Port() != null);
/* 541 */       Contract.pre(MM508Command.this.getRS232Port().isOpen());
/* 542 */       Contract.pre(paramArrayOfInt != null);
/*     */ 
/* 544 */       MedicalDevice.logInfo(this, "readDeviceData: processing cmd " + MedicalDevice.Util.getHex(this.m_commandCode) + " (" + this.m_description + ")");
/*     */       try
/*     */       {
/*     */         int i;
/*     */         do
/*     */         {
/* 552 */           i = MM508Command.this.getRS232Port().read();
/*     */         }
/* 554 */         while (i == 90);
/*     */ 
/* 556 */         if (i != 1) {
/* 557 */           throw new BadDeviceCommException("readDeviceData: ERROR - start of packet problem: " + i);
/*     */         }
/*     */ 
/* 569 */         MM508Command.this.getRS232Port().read(arrayOfInt1);
/*     */ 
/* 573 */         int j = arrayOfInt1[0];
/* 574 */         int k = arrayOfInt1[1];
/* 575 */         k = (k ^ 0xFFFFFFFF) & 0xFF;
/* 576 */         if (k != j) {
/* 577 */           throw new BadDeviceCommException("readDeviceData: ERROR - Length check problem: length=" + j + ", length complement=" + k);
/*     */         }
/*     */ 
/* 584 */         int m = arrayOfInt1[2] >> 6 & 0x3;
/*     */ 
/* 587 */         int n = MMPump.m_sequenceNumber + MM508Command.this.m_sequenceNumberInc & 0x3;
/*     */ 
/* 591 */         int i1 = arrayOfInt1[2] & 0x3F;
/* 592 */         if (m != n) {
/* 593 */           throw new BadDeviceCommException("readDeviceData: ERROR - bad sequence number; expected " + n + " got " + m);
/*     */         }
/*     */ 
/* 597 */         if (i1 != MMPump.m_accessCode) {
/* 598 */           throw new BadDeviceCommException("readDeviceData: ERROR - bad access code; expected " + MMPump.m_accessCode + " got " + i1);
/*     */         }
/*     */ 
/* 604 */         if (arrayOfInt1[3] != this.m_commandCode) {
/* 605 */           throw new BadDeviceCommException("readDeviceData: ERROR - bad command code; expected " + this.m_commandCode + " got " + arrayOfInt1[3]);
/*     */         }
/*     */ 
/* 613 */         int i2 = j - 2 - this.m_dataOffset;
/*     */ 
/* 615 */         if (i2 < 1) {
/* 616 */           throw new BadDeviceCommException("readDeviceData: ERROR - no data returned.");
/*     */         }
/*     */ 
/* 619 */         int[] arrayOfInt2 = new int[i2];
/*     */ 
/* 621 */         int i3 = MM508Command.this.getRS232Port().read(arrayOfInt2);
/*     */ 
/* 623 */         if (i3 != i2) {
/* 624 */           throw new BadDeviceCommException("readDeviceData: ERROR - length check failed: expected " + i2 + " bytes but got " + i3);
/*     */         }
/*     */ 
/* 632 */         int i4 = MM508Command.this.getRS232Port().read();
/*     */ 
/* 636 */         int[] arrayOfInt3 = new int[j];
/*     */ 
/* 638 */         System.arraycopy(arrayOfInt1, 2, arrayOfInt3, 0, 2 + this.m_dataOffset);
/* 639 */         System.arraycopy(arrayOfInt2, 0, arrayOfInt3, 2 + this.m_dataOffset, arrayOfInt2.length);
/*     */ 
/* 641 */         int i5 = MedicalDevice.Util.computeCRC8(arrayOfInt3, 0, j);
/* 642 */         if (i4 != i5) {
/* 643 */           throw new BadDeviceCommException("readDeviceData: ERROR - cmd " + this.m_commandCode + " resulted in bad CRC value of " + i4 + " (expected " + i5 + ").");
/*     */         }
/*     */ 
/* 650 */         if (paramArrayOfInt.length >= arrayOfInt2.length) {
/* 651 */           System.arraycopy(arrayOfInt2, 0, paramArrayOfInt, 0, arrayOfInt2.length);
/* 652 */           MedicalDevice.logInfo(this, "readDeviceData: cmd " + MedicalDevice.Util.getHex(this.m_commandCode) + " (" + this.m_description + ") returned " + arrayOfInt2.length + " data bytes.");
/*     */ 
/* 655 */           return arrayOfInt2.length;
/*     */         }
/*     */ 
/* 658 */         throw new BadDeviceCommException("readDeviceData: ERROR - cmd " + MedicalDevice.Util.getHex(this.m_commandCode) + " (" + this.m_description + ") returned " + arrayOfInt2.length + " data bytes, but deviceData parameter only holds " + paramArrayOfInt.length + " bytes; no data will be returned.");
/*     */       }
/*     */       catch (IOException localIOException)
/*     */       {
/*     */       }
/*     */ 
/* 665 */       throw new BadDeviceCommException("readDeviceData: ERROR - an IOException  has occurred processing cmd " + MedicalDevice.Util.getHex(this.m_commandCode) + " (" + this.m_description + ")");
/*     */     }
/*     */ 
/*     */     private final int readDeviceDataBlocks()
/*     */       throws BadDeviceCommException
/*     */     {
/* 684 */       int[] arrayOfInt = new int[256];
/* 685 */       int i = 0;
/* 686 */       int j = 0;
/* 687 */       int k = 0;
/*     */ 
/* 690 */       int i1 = 0;
/*     */ 
/* 692 */       MedicalDevice.logInfo(this, "readDeviceDataBlocks: processing cmd " + MedicalDevice.Util.getHex(this.m_commandCode) + " (" + this.m_description + ")");
/*     */       do
/*     */       {
/* 696 */         j = readDeviceData(arrayOfInt);
/*     */ 
/* 698 */         i1 = (arrayOfInt[0] == 4) && (j == 1) ? 1 : 0;
/* 699 */         if (i1 != 0)
/*     */         {
/*     */           continue;
/*     */         }
/* 703 */         int m = j / this.m_bytesPerRecord;
/*     */ 
/* 705 */         int n = m * this.m_bytesPerRecord;
/*     */ 
/* 707 */         n = Math.min(n, this.m_rawData.length - i);
/*     */ 
/* 711 */         if (i + n <= this.m_rawData.length) {
/* 712 */           System.arraycopy(arrayOfInt, 0, this.m_rawData, i, n);
/*     */ 
/* 714 */           i += n;
/* 715 */           MedicalDevice.logInfo(this, "readDeviceDataBlocks just read block " + k + ", bytes = " + n + ", total bytes = " + i);
/*     */ 
/* 718 */           MM508Command.this.m_bytesReadThusFar += n;
/* 719 */           k++;
/*     */ 
/* 722 */           MM508Command.this.notifyDeviceUpdateProgress(MM508Command.this.m_bytesReadThusFar, MM508Command.this.m_totalBytesToRead);
/*     */           try
/*     */           {
/* 726 */             if (!MM508Command.this.isHaltRequested())
/* 727 */               sendAck();
/*     */           }
/*     */           catch (IOException localIOException) {
/* 730 */             throw new BadDeviceCommException("readDeviceDataBlocks: ERROR - problem sending ACK.");
/*     */           }
/*     */         }
/*     */         else
/*     */         {
/* 735 */           int i2 = i + n - this.m_rawData.length;
/* 736 */           throw new BadDeviceCommException("readDeviceDataBlocks: ERROR - data overflow: last read returned " + n + " bytes which would " + "overflow the command data area by " + i2 + " bytes; last data block not copied.");
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 744 */       while ((i1 == 0) && (i <= this.m_rawData.length) && (!MM508Command.this.isHaltRequested()));
/*     */ 
/* 747 */       MedicalDevice.logInfo(this, "readDeviceDataBlocks: cmd " + MedicalDevice.Util.getHex(this.m_commandCode) + " (" + this.m_description + ") returned " + i + " bytes.");
/*     */ 
/* 750 */       return i;
/*     */     }
/*     */ 
/*     */     private final void sendAndRead()
/*     */       throws BadDeviceCommException
/*     */     {
/* 764 */       if (MM508Command.this.getState() != 7) {
/* 765 */         MM508Command.this.setState(4);
/*     */       }
/* 767 */       sendCommand();
/* 768 */       if ((this.m_rawData.length > 0) && (!MM508Command.this.isHaltRequested()))
/*     */       {
/* 771 */         if (this.m_rawData.length > 250) {
/* 772 */           MM508Command.this.setState(6);
/* 773 */           this.m_numBytesRead = readDeviceDataBlocks();
/*     */         }
/*     */         else {
/* 776 */           MM508Command.this.setState(5);
/* 777 */           this.m_numBytesRead = readDeviceData(this.m_rawData);
/* 778 */           MM508Command.this.m_bytesReadThusFar += this.m_numBytesRead;
/*     */ 
/* 781 */           MM508Command.this.notifyDeviceUpdateProgress(MM508Command.this.m_bytesReadThusFar, MM508Command.this.m_totalBytesToRead);
/*     */         }
/*     */ 
/*     */         try
/*     */         {
/* 786 */           if (!MM508Command.this.isHaltRequested())
/* 787 */             sendAck();
/*     */         }
/*     */         catch (IOException localIOException) {
/* 790 */           throw new BadDeviceCommException("sendAndRead: ERROR - problem sending ACK.");
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/*     */     private final int[] buildPacket()
/*     */     {
/* 804 */       int i = 0;
/*     */ 
/* 806 */       Contract.pre(this.m_cmdLength > 0);
/*     */ 
/* 808 */       int[] arrayOfInt = new int[this.m_cmdLength + 4];
/*     */ 
/* 811 */       arrayOfInt[(i++)] = 1;
/*     */ 
/* 814 */       arrayOfInt[(i++)] = (this.m_cmdLength & 0xFF);
/*     */ 
/* 817 */       arrayOfInt[(i++)] = ((this.m_cmdLength ^ 0xFFFFFFFF) & 0xFF);
/*     */ 
/* 821 */       arrayOfInt[(i++)] = ((MMPump.m_sequenceNumber << 6) + MMPump.m_accessCode);
/*     */ 
/* 824 */       arrayOfInt[(i++)] = this.m_commandCode;
/*     */ 
/* 830 */       if (this.m_addressLength > 0) {
/* 831 */         switch (this.m_addressLength) {
/*     */         case 1:
/* 833 */           arrayOfInt[(i++)] = this.m_address;
/* 834 */           break;
/*     */         case 2:
/* 836 */           arrayOfInt[(i++)] = (this.m_address >> 8);
/* 837 */           arrayOfInt[(i++)] = (this.m_address & 0xFF);
/* 838 */           arrayOfInt[(i++)] = this.m_rawData.length;
/* 839 */           break;
/*     */         case 3:
/* 841 */           arrayOfInt[(i++)] = (this.m_address >> 16);
/* 842 */           arrayOfInt[(i++)] = (this.m_address >> 8);
/* 843 */           arrayOfInt[(i++)] = (this.m_address & 0xFF);
/* 844 */           arrayOfInt[(i++)] = this.m_rawData.length;
/* 845 */           break;
/*     */         default:
/* 848 */           Contract.unreachable();
/* 849 */           return null;
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 854 */       arrayOfInt[(i++)] = MedicalDevice.Util.computeCRC8(arrayOfInt, 3, this.m_cmdLength);
/* 855 */       this.m_packet = arrayOfInt;
/* 856 */       return arrayOfInt;
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.deviceportreader.MM508Command
 * JD-Core Version:    0.6.0
 */