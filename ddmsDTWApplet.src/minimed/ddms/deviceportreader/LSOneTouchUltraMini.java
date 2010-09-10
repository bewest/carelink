/*     */ package minimed.ddms.deviceportreader;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Date;
/*     */ import java.util.Vector;
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ final class LSOneTouchUltraMini extends LSMeter
/*     */ {
/*     */   static final int SNAPSHOT_FORMAT_ID = 159;
/*     */   private static final int IO_DELAY_MS = 10;
/*     */   private static final int READ_TIMEOUT_MS = 500;
/*     */   private int[] m_glucoseUnits;
/*     */   private int[] m_dateFormat;
/*     */   private int[] m_glucoseHistory;
/*     */   private Command m_cmdGetGlucoseUnits;
/*     */   private Command m_cmdReadDateFormat;
/*     */   private Command m_cmdReadNumOfRecords;
/*     */   private Command m_cmdDisconnect;
/*     */   private int m_ESnum;
/*     */ 
/*     */   LSOneTouchUltraMini()
/*     */   {
/*  76 */     this.m_description = "LifeScan One Touch UltraMini/UltraEasy Meter";
/*  77 */     logInfo(this, "creating interface to the '" + this.m_description + "', package version " + getPackageVersion());
/*     */ 
/*  79 */     this.m_deviceClassID = 26;
/*  80 */     this.m_snapshotFormatID = 159;
/*  81 */     this.m_snapshotCreator = new SnapshotCreator(null);
/*     */ 
/*  84 */     this.m_cmdGetFirmwareVersion = new Command(new int[] { 5, 13, 2 }, "Get Firmware Version", new ReplyDecoder()
/*     */     {
/*     */       public void decodeReply(LSMeter.AbstractCommand paramAbstractCommand) throws BadDeviceValueException
/*     */       {
/*  88 */         int[] arrayOfInt1 = paramAbstractCommand.getRawData();
/*  89 */         int[] arrayOfInt2 = new int[50];
/*  90 */         System.arraycopy(arrayOfInt1, 6, arrayOfInt2, 0, arrayOfInt1.length - 9);
/*  91 */         LSOneTouchUltraMini.this.m_firmwareVersion = MedicalDevice.Util.makeString(arrayOfInt2);
/*     */       }
/*     */     }
/*     */     , null);
/*     */ 
/*  94 */     this.m_cmdGetSerialNumber = new Command(new int[] { 5, 11, 2, 0, 0, 0, 0, 132, 106, 232, 115, 0 }, "Get Serial Number", new ReplyDecoder()
/*     */     {
/*     */       public void decodeReply(LSMeter.AbstractCommand paramAbstractCommand) throws BadDeviceValueException {
/*  97 */         int[] arrayOfInt1 = paramAbstractCommand.getRawData();
/*  98 */         int[] arrayOfInt2 = new int[50];
/*  99 */         System.arraycopy(arrayOfInt1, 5, arrayOfInt2, 0, arrayOfInt1.length - 8);
/* 100 */         LSOneTouchUltraMini.this.m_serialNumber = MedicalDevice.Util.makeString(arrayOfInt2);
/*     */       }
/*     */     }
/*     */     , null);
/*     */ 
/* 103 */     this.m_cmdReadRealTimeClock = new Command(new int[] { 5, 32, 2, 0, 0, 0, 0 }, "Get Current Time", new ReplyDecoder()
/*     */     {
/*     */       public void decodeReply(LSMeter.AbstractCommand paramAbstractCommand) throws BadDeviceValueException {
/* 106 */         int[] arrayOfInt1 = paramAbstractCommand.getRawData();
/* 107 */         int[] arrayOfInt2 = new int[50];
/* 108 */         System.arraycopy(arrayOfInt1, 5, arrayOfInt2, 0, 4);
/* 109 */         LSOneTouchUltraMini.this.m_realTimeClock = MedicalDevice.Util.makeString(arrayOfInt2);
/*     */ 
/* 112 */         long l = MedicalDevice.Util.makeLong(MedicalDevice.Util.getLowByte(arrayOfInt2[3]), MedicalDevice.Util.getLowByte(arrayOfInt2[2]), MedicalDevice.Util.getLowByte(arrayOfInt2[1]), MedicalDevice.Util.getLowByte(arrayOfInt2[0]));
/*     */ 
/* 117 */         LSOneTouchUltraMini.this.m_timeStamp = new Date(l * 1000L);
/*     */       }
/*     */     }
/*     */     , null);
/*     */ 
/* 120 */     this.m_cmdGetGlucoseUnits = new Command(new int[] { 5, 9, 2, 9, 0, 0, 0, 0 }, "Get Glucose Units", new ReplyDecoder()
/*     */     {
/*     */       public void decodeReply(LSMeter.AbstractCommand paramAbstractCommand) throws BadDeviceValueException {
/* 123 */         int[] arrayOfInt1 = paramAbstractCommand.getRawData();
/* 124 */         int[] arrayOfInt2 = new int[4];
/* 125 */         System.arraycopy(arrayOfInt1, 5, arrayOfInt2, 0, arrayOfInt2.length);
/* 126 */         LSOneTouchUltraMini.access$202(LSOneTouchUltraMini.this, arrayOfInt2);
/*     */       }
/*     */     }
/*     */     , null);
/*     */ 
/* 129 */     this.m_cmdReadDateFormat = new Command(new int[] { 5, 8, 2, 0, 0, 0, 0, 0 }, "Read Date Format", new ReplyDecoder()
/*     */     {
/*     */       public void decodeReply(LSMeter.AbstractCommand paramAbstractCommand) throws BadDeviceValueException {
/* 132 */         int[] arrayOfInt1 = paramAbstractCommand.getRawData();
/* 133 */         int[] arrayOfInt2 = new int[4];
/* 134 */         System.arraycopy(arrayOfInt1, 5, arrayOfInt2, 0, arrayOfInt2.length);
/* 135 */         LSOneTouchUltraMini.access$302(LSOneTouchUltraMini.this, arrayOfInt2);
/*     */       }
/*     */     }
/*     */     , null);
/*     */ 
/* 139 */     this.m_cmdReadNumOfRecords = new Command(new int[] { 5, 31, 245, 1 }, "Read Glucose Record 501", null, null);
/*     */ 
/* 142 */     this.m_cmdDisconnect = new DisconnectCommand(null);
/*     */ 
/* 144 */     this.m_cmdGetDatalog = new ReadGlucoseHistoryCommand(new ReplyDecoder() {
/*     */       public void decodeReply(LSMeter.AbstractCommand paramAbstractCommand) throws BadDeviceValueException {
/* 146 */         int[] arrayOfInt = paramAbstractCommand.getRawData();
/* 147 */         LSOneTouchUltraMini.access$502(LSOneTouchUltraMini.this, arrayOfInt);
/*     */       }
/*     */     }
/*     */     , null);
/*     */ 
/* 151 */     this.m_ioDelay = 10;
/*     */   }
/*     */ 
/*     */   Vector createCommandList()
/*     */   {
/* 162 */     Vector localVector = new Vector();
/*     */ 
/* 165 */     localVector.addElement(this.m_cmdGetFirmwareVersion);
/* 166 */     localVector.addElement(this.m_cmdGetSerialNumber);
/* 167 */     localVector.addElement(this.m_cmdReadRealTimeClock);
/* 168 */     localVector.addElement(this.m_cmdGetGlucoseUnits);
/* 169 */     localVector.addElement(this.m_cmdReadDateFormat);
/*     */ 
/* 171 */     localVector.addElement(this.m_cmdGetDatalog);
/*     */ 
/* 173 */     return localVector;
/*     */   }
/*     */ 
/*     */   void initSerialPort(int paramInt)
/*     */     throws IOException
/*     */   {
/* 185 */     super.initSerialPort(paramInt);
/* 186 */     getRS232Port().setReadTimeOut(500);
/*     */   }
/*     */ 
/*     */   void findDevice(DeviceListener paramDeviceListener)
/*     */     throws BadDeviceCommException, IOException
/*     */   {
/*     */     try
/*     */     {
/* 200 */       this.m_cmdDisconnect.execute();
/*     */     }
/*     */     catch (BadDeviceValueException localBadDeviceValueException) {
/* 203 */       throw new BadDeviceCommException("Got reply, but format is bad.");
/*     */     }
/*     */   }
/*     */ 
/*     */   void decodeCurrentSettings()
/*     */     throws BadDeviceValueException
/*     */   {
/* 220 */     logInfo(this, "decodeCurrentSettings: *** CURRENT SETTINGS *** (none)");
/*     */   }
/*     */ 
/*     */   private class SnapshotCreator extends MedicalDevice.SnapshotCreator
/*     */   {
/*     */     private static final int SNAPCODE_GLUCOSE_UNITS = 1;
/*     */     private static final int SNAPCODE_DATE_FORMAT = 2;
/*     */     private static final int SNAPCODE_GLUCOSE_HISTORY = 3;
/*     */     static final int LAST_SNAPCODE = 3;
/*     */     private static final int SNAPSHOT_BYTES = 250;
/*     */     private static final int SNAP_HEADER_LENGTH = 50;
/*     */     private static final char PAD_CHAR = ' ';
/*     */     private final LSOneTouchUltraMini this$0;
/*     */ 
/*     */     private SnapshotCreator()
/*     */     {
/* 707 */       super(250);
/*     */ 
/* 706 */       this.this$0 = this$1;
/*     */     }
/*     */ 
/*     */     void createSnapshotBody()
/*     */     {
/* 717 */       this.this$0.m_snapshot = new Snapshot(this.this$0.m_snapshotFormatID, 1, pad(this.this$0.m_firmwareVersion), pad(this.this$0.m_serialNumber), pad(this.this$0.m_realTimeClock));
/*     */ 
/* 720 */       MedicalDevice.logInfo(this, "createSnapshot: creating snapshot ");
/*     */ 
/* 724 */       this.this$0.m_snapshot.addElement(1, this.this$0.m_glucoseUnits);
/* 725 */       this.this$0.m_snapshot.addElement(2, this.this$0.m_dateFormat);
/*     */ 
/* 729 */       this.this$0.m_snapshot.addElement(3, this.this$0.m_glucoseHistory);
/*     */     }
/*     */ 
/*     */     String pad(String paramString)
/*     */     {
/* 739 */       Contract.preNonNull(paramString);
/* 740 */       StringBuffer localStringBuffer = new StringBuffer(paramString);
/*     */ 
/* 742 */       for (int i = paramString.length(); i < 50; i++) {
/* 743 */         localStringBuffer.append(' ');
/*     */       }
/*     */ 
/* 746 */       return localStringBuffer.toString();
/*     */     }
/*     */ 
/*     */     SnapshotCreator(LSOneTouchUltraMini.1 arg2)
/*     */     {
/* 686 */       this();
/*     */     }
/*     */   }
/*     */ 
/*     */   private class ReadGlucoseHistoryCommand extends LSOneTouchUltraMini.Command
/*     */   {
/*     */     private static final int MAX_HISTORY_RECORDS = 500;
/*     */     private static final int HR_LEN = 16;
/*     */     private final LSOneTouchUltraMini this$0;
/*     */ 
/*     */     private ReadGlucoseHistoryCommand(ReplyDecoder arg2)
/*     */     {
/* 612 */       super(new int[0], "Read Glucose History", localReplyDecoder, null);
/*     */ 
/* 611 */       this.this$0 = this$1;
/*     */ 
/* 615 */       this.m_cmdBytesToRead = (8000 + this$1.m_cmdReadNumOfRecords.m_cmdBytesToRead);
/*     */     }
/*     */ 
/*     */     protected void sendAndRead()
/*     */       throws BadDeviceCommException, BadDeviceValueException
/*     */     {
/* 631 */       this.this$0.m_cmdReadNumOfRecords.execute();
/* 632 */       int[] arrayOfInt1 = this.this$0.m_cmdReadNumOfRecords.getRawData();
/* 633 */       if ((arrayOfInt1[3] != 5) || (arrayOfInt1[4] != 15)) {
/* 634 */         throw new BadDeviceCommException("Expected RM1, RM2 to be 0x05, 0x0F -- got " + arrayOfInt1[3] + ", " + arrayOfInt1[4]);
/*     */       }
/*     */ 
/* 638 */       int i = (arrayOfInt1[6] << 8) + arrayOfInt1[5];
/*     */ 
/* 640 */       int j = i * 16 + this.this$0.m_cmdReadNumOfRecords.m_cmdBytesToRead;
/* 641 */       this.this$0.m_totalBytesToRead += j - this.m_cmdBytesToRead;
/*     */ 
/* 643 */       this.this$0.notifyDeviceUpdateProgress(this.this$0.m_bytesReadThusFar, this.this$0.m_totalBytesToRead);
/* 644 */       this.m_cmdBytesToRead = j;
/*     */ 
/* 646 */       int[] arrayOfInt2 = new int[16 * i];
/* 647 */       LSOneTouchUltraMini.Command localCommand = new LSOneTouchUltraMini.Command(this.this$0, new int[] { 5, 31, 0, 0 }, "Read Glucose Record", null, null);
/*     */ 
/* 649 */       for (int k = 0; k < i; k++) {
/* 650 */         int m = (k & 0xFF00) >> 8;
/* 651 */         int n = k & 0xFF;
/*     */ 
/* 653 */         localCommand.m_cmdBytes[2] = n;
/* 654 */         localCommand.m_cmdBytes[3] = m;
/*     */ 
/* 656 */         int[] arrayOfInt3 = null;
/*     */         try {
/* 658 */           localCommand.execute();
/* 659 */           arrayOfInt3 = localCommand.getRawData();
/*     */         } catch (Exception localException) {
/* 661 */           throw new BadDeviceCommException("Exception when executing " + localCommand.getDescription() + ". " + localException);
/*     */         }
/*     */ 
/* 664 */         if (arrayOfInt3[1] != 16) {
/* 665 */           throw new BadDeviceCommException("Invalid record length. Expected 16 bytes, got " + arrayOfInt3[1]);
/*     */         }
/*     */ 
/* 669 */         System.arraycopy(arrayOfInt3, 0, arrayOfInt2, k * 16, 16);
/*     */       }
/*     */ 
/* 672 */       setRawData(arrayOfInt2);
/*     */ 
/* 674 */       if ((!this.this$0.isHaltRequested()) && 
/* 675 */         (this.m_replyDecoder != null))
/* 676 */         this.m_replyDecoder.decodeReply(this);
/*     */     }
/*     */ 
/*     */     ReadGlucoseHistoryCommand(ReplyDecoder param1, LSOneTouchUltraMini.1 arg3)
/*     */     {
/* 601 */       this(param1);
/*     */     }
/*     */   }
/*     */ 
/*     */   private class DisconnectCommand extends LSOneTouchUltraMini.Command
/*     */   {
/*     */     private final LSOneTouchUltraMini this$0;
/*     */ 
/*     */     private DisconnectCommand()
/*     */     {
/* 529 */       super(new int[0], "Disconnect Command", null, null);
/*     */ 
/* 528 */       this.this$0 = this$1;
/*     */ 
/* 530 */       this.m_maxRetryCount = 0;
/*     */     }
/*     */ 
/*     */     protected void cleanupBeforeRetry()
/*     */     {
/* 540 */       this.this$0.getRS232Port().readUntilEmpty();
/*     */     }
/*     */ 
/*     */     void sendAndRead()
/*     */       throws BadDeviceCommException, BadDeviceValueException
/*     */     {
/* 554 */       super.sendAndRead();
/* 555 */       LSOneTouchUltraMini.access$802(this.this$0, 0);
/*     */     }
/*     */ 
/*     */     void sendCommand()
/*     */       throws BadDeviceCommException, IOException
/*     */     {
/* 567 */       Contract.pre(this.this$0.getRS232Port() != null);
/* 568 */       Contract.pre(this.this$0.getRS232Port().isOpen());
/*     */ 
/* 571 */       writePacket(8, this.m_cmdBytes);
/*     */     }
/*     */ 
/*     */     int[] readReply()
/*     */       throws BadDeviceCommException, IOException
/*     */     {
/* 584 */       return null;
/*     */     }
/*     */ 
/*     */     void sendACK()
/*     */       throws IOException
/*     */     {
/*     */     }
/*     */ 
/*     */     DisconnectCommand(LSOneTouchUltraMini.1 arg2)
/*     */     {
/* 523 */       this();
/*     */     }
/*     */   }
/*     */ 
/*     */   private class Command extends LSMeter.AbstractCommand
/*     */   {
/*     */     protected static final int TYPICAL_MESSAGE_LEN = 16;
/*     */     private static final int RETRY_COUNT = 3;
/*     */     protected static final int MSB_MASK = 65280;
/*     */     protected static final int LSB_MASK = 255;
/*     */     protected static final int MIN_PACKET_SIZE = 5;
/*     */     protected static final byte ACK_MASK = 4;
/*     */     protected int[] m_cmdBytes;
/*     */     protected int m_cmdBytesToRead;
/*     */     private final LSOneTouchUltraMini this$0;
/*     */ 
/*     */     private Command(int[] paramString, String paramReplyDecoder, ReplyDecoder arg4)
/*     */     {
/* 249 */       super(paramReplyDecoder);
/*     */ 
/* 248 */       this.this$0 = this$1;
/*     */ 
/* 238 */       this.m_cmdBytes = new int[0];
/*     */ 
/* 251 */       this.m_cmdBytes = paramString;
/* 252 */       this.m_cmdBytesToRead = 16;
/*     */       Object localObject;
/* 254 */       this.m_replyDecoder = localObject;
/*     */ 
/* 256 */       this.m_maxRetryCount = 3;
/*     */     }
/*     */ 
/*     */     public String toString()
/*     */     {
/* 264 */       return MedicalDevice.Util.getHex(this.m_cmdBytes) + " ( " + this.m_description + ")";
/*     */     }
/*     */ 
/*     */     protected void cleanupBeforeRetry()
/*     */     {
/* 272 */       this.this$0.getRS232Port().readUntilEmpty();
/*     */       try
/*     */       {
/* 276 */         this.this$0.m_cmdDisconnect.execute();
/*     */       }
/*     */       catch (Exception localException)
/*     */       {
/*     */       }
/*     */     }
/*     */ 
/*     */     final int getBytesToRead()
/*     */     {
/* 288 */       return this.m_cmdBytesToRead;
/*     */     }
/*     */ 
/*     */     void sendAndRead()
/*     */       throws BadDeviceCommException, BadDeviceValueException
/*     */     {
/*     */       try
/*     */       {
/* 301 */         if (this.this$0.getState() != 7) {
/* 302 */           this.this$0.setState(4);
/*     */         }
/* 304 */         sendCommand();
/* 305 */         readACK();
/*     */ 
/* 307 */         this.this$0.setState(5);
/* 308 */         int[] arrayOfInt = readReply();
/* 309 */         setRawData(arrayOfInt);
/*     */ 
/* 311 */         sendACK();
/*     */ 
/* 313 */         MedicalDevice.Util.sleepMS(20);
/*     */       } catch (IOException localIOException) {
/* 315 */         MedicalDevice.logError(this, "cmd " + this + " encountered io exception; exception = " + localIOException);
/* 316 */         throw new BadDeviceCommException("IO Exception occured while trying tocommunicate with meter");
/*     */       }
/*     */ 
/* 320 */       if ((!this.this$0.isHaltRequested()) && 
/* 321 */         (this.m_replyDecoder != null)) {
/* 322 */         this.m_replyDecoder.decodeReply(this);
/*     */       }
/*     */ 
/* 327 */       this.this$0.m_bytesReadThusFar += this.m_cmdBytesToRead;
/* 328 */       this.this$0.notifyDeviceUpdateProgress();
/*     */     }
/*     */ 
/*     */     void sendCommand()
/*     */       throws BadDeviceCommException, IOException
/*     */     {
/* 341 */       Contract.pre(this.this$0.getRS232Port() != null);
/* 342 */       Contract.pre(this.this$0.getRS232Port().isOpen());
/*     */ 
/* 344 */       MedicalDevice.logInfo(this, "sendCommand: sending cmd " + this);
/*     */ 
/* 346 */       writePacket(0, this.m_cmdBytes);
/*     */     }
/*     */ 
/*     */     int[] readPacket()
/*     */       throws BadDeviceCommException, IOException
/*     */     {
/* 358 */       int i = this.this$0.getRS232Port().read();
/*     */ 
/* 361 */       while (i != 2) {
/* 362 */         if (i == -1) {
/* 363 */           throw new BadDeviceCommException("Couldn't find STX -- nothing on the line");
/*     */         }
/*     */ 
/* 366 */         i = this.this$0.getRS232Port().read();
/*     */       }
/*     */ 
/* 369 */       int j = this.this$0.getRS232Port().read();
/* 370 */       if (j < 5) {
/* 371 */         throw new BadDeviceCommException("Packet length too short - " + j + ". Expected at least " + 5);
/*     */       }
/*     */ 
/* 375 */       int[] arrayOfInt = new int[j];
/* 376 */       arrayOfInt[0] = i;
/* 377 */       arrayOfInt[1] = j;
/* 378 */       this.this$0.getRS232Port().read(arrayOfInt, 2, arrayOfInt.length - 2);
/*     */ 
/* 380 */       verifyCRC(arrayOfInt);
/*     */ 
/* 382 */       return arrayOfInt;
/*     */     }
/*     */ 
/*     */     void writePacket(int paramInt, int[] paramArrayOfInt)
/*     */       throws IOException
/*     */     {
/* 395 */       int[] arrayOfInt = new int[paramArrayOfInt.length + 6];
/* 396 */       arrayOfInt[0] = 2;
/* 397 */       arrayOfInt[1] = arrayOfInt.length;
/*     */ 
/* 400 */       paramInt |= this.this$0.m_ESnum;
/*     */ 
/* 402 */       arrayOfInt[2] = paramInt;
/* 403 */       System.arraycopy(paramArrayOfInt, 0, arrayOfInt, 3, paramArrayOfInt.length);
/* 404 */       arrayOfInt[(arrayOfInt.length - 3)] = 3;
/*     */ 
/* 407 */       int i = MedicalDevice.Util.computeCRC16CCITT(arrayOfInt, 0, arrayOfInt.length - 2);
/* 408 */       arrayOfInt[(arrayOfInt.length - 2)] = (i & 0xFF);
/* 409 */       arrayOfInt[(arrayOfInt.length - 1)] = ((i & 0xFF00) >> 8);
/*     */ 
/* 411 */       this.this$0.getRS232Port().write(arrayOfInt);
/*     */     }
/*     */ 
/*     */     void verifyCRC(int[] paramArrayOfInt)
/*     */       throws BadDeviceCommException
/*     */     {
/* 422 */       int i = (paramArrayOfInt[(paramArrayOfInt.length - 1)] << 8) + paramArrayOfInt[(paramArrayOfInt.length - 2)];
/* 423 */       int j = MedicalDevice.Util.computeCRC16CCITT(paramArrayOfInt, 0, paramArrayOfInt.length - 2);
/*     */ 
/* 425 */       if (i != j)
/* 426 */         throw new BadDeviceCommException("Bad CRC: " + i + ", should be " + j);
/*     */     }
/*     */ 
/*     */     int[] readReply()
/*     */       throws BadDeviceCommException, IOException
/*     */     {
/* 443 */       Contract.pre(this.this$0.getRS232Port() != null);
/* 444 */       Contract.pre(this.this$0.getRS232Port().isOpen());
/*     */ 
/* 446 */       MedicalDevice.logInfo(this, "readReply: reading reply to cmd " + this);
/*     */ 
/* 448 */       int[] arrayOfInt = readPacket();
/* 449 */       if ((arrayOfInt == null) || (arrayOfInt.length < 5)) {
/* 450 */         throw new BadDeviceCommException("Invalid packet " + MedicalDevice.Util.getHex(arrayOfInt));
/*     */       }
/*     */ 
/* 459 */       int i = arrayOfInt[2];
/*     */ 
/* 461 */       int j = i & 0x1;
/* 462 */       int k = this.this$0.m_ESnum >> 1;
/*     */ 
/* 464 */       if (j != k) {
/* 465 */         throw new BadDeviceCommException("Invalid S number received: got " + j + ", expected " + k);
/*     */       }
/*     */ 
/* 470 */       LSOneTouchUltraMini.access$880(this.this$0, 2);
/* 471 */       return arrayOfInt;
/*     */     }
/*     */ 
/*     */     void readACK()
/*     */       throws BadDeviceCommException, IOException
/*     */     {
/* 484 */       Contract.pre(this.this$0.getRS232Port() != null);
/* 485 */       Contract.pre(this.this$0.getRS232Port().isOpen());
/*     */ 
/* 487 */       MedicalDevice.logInfo(this, "readACK: reading ack to cmd " + this);
/*     */ 
/* 489 */       int[] arrayOfInt = readPacket();
/* 490 */       int i = arrayOfInt[2];
/*     */ 
/* 492 */       if ((i & 0x4) != 4) {
/* 493 */         throw new BadDeviceCommException("NOT ACK");
/*     */       }
/*     */ 
/* 501 */       LSOneTouchUltraMini.access$880(this.this$0, 1);
/*     */     }
/*     */ 
/*     */     void sendACK()
/*     */       throws IOException
/*     */     {
/* 512 */       Contract.pre(this.this$0.getRS232Port() != null);
/* 513 */       Contract.pre(this.this$0.getRS232Port().isOpen());
/*     */ 
/* 515 */       MedicalDevice.logInfo(this, "sendACK: sending ack for cmd " + this);
/* 516 */       writePacket(4, new int[0]);
/*     */     }
/*     */ 
/*     */     Command(int[] paramString, String paramReplyDecoder, ReplyDecoder param1, LSOneTouchUltraMini.1 arg5)
/*     */     {
/* 226 */       this(paramString, paramReplyDecoder, param1);
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.deviceportreader.LSOneTouchUltraMini
 * JD-Core Version:    0.6.0
 */