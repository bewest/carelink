/*     */ package minimed.ddms.deviceportreader;
/*     */ 
/*     */ import Serialio.SerialConfig;
/*     */ import Serialio.SerialPortLocal;
/*     */ import java.io.IOException;
/*     */ import java.util.Arrays;
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ final class SerialPort extends CommPort
/*     */ {
/*     */   static final int BAUD_57600 = 10;
/*     */   static final int BAUD_9600 = 7;
/*     */   static final int BAUD_19200 = 8;
/*     */   static final int BAUD_38400 = 9;
/*     */   static final int PY_NONE = 0;
/*     */   static final int PY_ODD = 1;
/*     */   static final int PY_EVEN = 2;
/*     */   static final int PY_MARK = 3;
/*     */   static final int PY_SPACE = 4;
/*     */   private static final byte BYTE_NOT_AVAILABLE = -1;
/*     */   private static final char LF = '\n';
/*     */   private static final char CR = '\r';
/*     */   private static final int RECEIVE_TIMEOUT_MS = 250;
/*     */   private static final int TRANSMIT_TIMEOUT_MS = 250;
/*     */   private static final int RECEIVE_BUFFER_SIZE = 16384;
/*     */   private static final int TRANSMIT_BUFFER_SIZE = 2048;
/*     */   private static final int HALF_SECOND = 500;
/*     */   private static final int IO_DELAY_MS = 250;
/*     */   private SerialPortLocal m_serialPortLocal;
/*     */   private final SerialConfig m_serialConfig;
/*     */   private final String m_commPortName;
/*     */   private long m_startTimeMS;
/*  63 */   private int m_ioDelay = 250;
/*     */ 
/*     */   SerialPort(int paramInt1, int paramInt2, int paramInt3)
/*     */     throws IOException
/*     */   {
/*  80 */     Contract.pre(paramInt3, 0, 4);
/*  81 */     this.m_commPortName = ("COM" + paramInt1);
/*     */ 
/*  84 */     this.m_serialConfig = new SerialConfig(this.m_commPortName);
/*  85 */     setDefaultCommParameters(paramInt2);
/*  86 */     this.m_serialConfig.setParity(paramInt3);
/*     */ 
/*  88 */     MedicalDevice.logInfoHigh(this, "creating port '" + this.m_commPortName + "' with parameters (rate/parity/data/stop/handshake): " + this.m_serialConfig.getSettingsString());
/*     */ 
/*  93 */     this.m_serialPortLocal = new SerialPortLocal(this.m_serialConfig);
/*  94 */     this.m_serialPortLocal.setTimeoutRx(250);
/*  95 */     this.m_serialPortLocal.setTimeoutTx(250);
/*  96 */     this.m_serialPortLocal.setDTR(true);
/*  97 */     this.m_serialPortLocal.setRTS(true);
/*  98 */     this.m_startTimeMS = System.currentTimeMillis();
/*  99 */     this.m_serialPortLocal.setPortName(this.m_commPortName);
/* 100 */     MedicalDevice.Util.sleepMS(500);
/* 101 */     dumpSerialStatus();
/*     */   }
/*     */ 
/*     */   SerialPort(int paramInt1, int paramInt2)
/*     */     throws IOException
/*     */   {
/* 115 */     this(paramInt1, paramInt2, 0);
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 126 */     return "RS232 @" + this.m_serialConfig.getSettingsString();
/*     */   }
/*     */ 
/*     */   final void setReadTimeOut(int paramInt)
/*     */     throws IOException
/*     */   {
/* 136 */     this.m_serialPortLocal.setTimeoutRx(paramInt);
/*     */   }
/*     */ 
/*     */   final int getReadTimeOut()
/*     */     throws IOException
/*     */   {
/* 146 */     return this.m_serialPortLocal.getTimeoutRx();
/*     */   }
/*     */ 
/*     */   final boolean isOpen()
/*     */   {
/* 156 */     Contract.pre(this.m_serialPortLocal != null);
/*     */ 
/* 158 */     return this.m_serialPortLocal.isOpen();
/*     */   }
/*     */ 
/*     */   final void close()
/*     */     throws IOException
/*     */   {
/* 169 */     Contract.pre(this.m_serialPortLocal != null);
/*     */ 
/* 171 */     if (this.m_serialPortLocal.isOpen())
/* 172 */       this.m_serialPortLocal.close();
/*     */   }
/*     */ 
/*     */   final void setIODelay(int paramInt)
/*     */   {
/* 182 */     this.m_ioDelay = paramInt; } 
/* 196 */   final String readLine() throws IOException, SerialIOHaltedException { int i = 65535;
/*     */ 
/* 199 */     int m = 0;
/* 200 */     StringBuffer localStringBuffer = new StringBuffer("");
/*     */ 
/* 202 */     Contract.pre(this.m_serialPortLocal != null);
/* 203 */     Contract.pre(this.m_serialPortLocal.isOpen());
/*     */ 
/* 205 */     checkForSerialIOHalted();
/*     */ 
/* 207 */     MedicalDevice.Util.sleepMS(this.m_ioDelay);
/*     */     int k;
/*     */     do { int j = i;
/* 210 */       k = this.m_serialPortLocal.getByte();
/* 211 */       i = (char)k;
/*     */ 
/* 213 */       if (k != -1) {
/* 214 */         localStringBuffer.append(i);
/*     */       }
/*     */ 
/* 218 */       if ((j == 13) && (i == 10)) {
/* 219 */         m = 1;
/*     */       }
/*     */     }
/* 222 */     while ((k != -1) && (m == 0));
/*     */ 
/* 225 */     long l = System.currentTimeMillis() - this.m_startTimeMS;
/* 226 */     MedicalDevice.logInfoHigh(this, "readLine(" + l + "MS): read <" + localStringBuffer + ">");
/* 227 */     this.m_startTimeMS = System.currentTimeMillis();
/*     */ 
/* 229 */     return new String(localStringBuffer);
/*     */   }
/*     */ 
/*     */   final int read()
/*     */     throws IOException, SerialIOHaltedException
/*     */   {
/* 244 */     int i = -1;
/*     */ 
/* 246 */     Contract.pre(this.m_serialPortLocal != null);
/* 247 */     Contract.pre(this.m_serialPortLocal.isOpen());
/*     */ 
/* 249 */     checkForSerialIOHalted();
/*     */ 
/* 251 */     MedicalDevice.Util.sleepMS(this.m_ioDelay);
/* 252 */     i = this.m_serialPortLocal.getByte();
/*     */ 
/* 255 */     long l = System.currentTimeMillis() - this.m_startTimeMS;
/* 256 */     MedicalDevice.logInfoHigh(this, "read()(" + l + "MS): read <" + MedicalDevice.Util.getHex(i) + ">");
/*     */ 
/* 258 */     this.m_startTimeMS = System.currentTimeMillis();
/* 259 */     return i;
/*     */   }
/*     */ 
/*     */   private final int read(byte[] paramArrayOfByte)
/*     */     throws IOException, SerialIOHaltedException
/*     */   {
/* 275 */     Contract.pre(this.m_serialPortLocal != null);
/* 276 */     Contract.pre(this.m_serialPortLocal.isOpen());
/* 277 */     Contract.pre(paramArrayOfByte != null);
/*     */ 
/* 279 */     checkForSerialIOHalted();
/* 280 */     MedicalDevice.Util.sleepMS(this.m_ioDelay);
/* 281 */     int i = 0;
/*     */ 
/* 286 */     for (int j = 0; j < paramArrayOfByte.length; j++) {
/* 287 */       int k = this.m_serialPortLocal.getByte();
/*     */ 
/* 289 */       if (k == -1) {
/*     */         break;
/*     */       }
/* 292 */       paramArrayOfByte[j] = (byte)k;
/* 293 */       i++;
/*     */     }
/*     */ 
/* 297 */     long l = System.currentTimeMillis() - this.m_startTimeMS;
/* 298 */     MedicalDevice.logInfoHigh(this, "read(byte[])(" + l + "MS): read <" + MedicalDevice.Util.getHex(paramArrayOfByte, i) + ">");
/*     */ 
/* 300 */     this.m_startTimeMS = System.currentTimeMillis();
/*     */ 
/* 302 */     return i;
/*     */   }
/*     */ 
/*     */   final int read(int[] paramArrayOfInt, int paramInt1, int paramInt2)
/*     */     throws IOException, SerialIOHaltedException
/*     */   {
/* 319 */     Contract.pre(paramInt1, 0, paramArrayOfInt.length);
/* 320 */     Contract.pre((paramInt2 >= 0) && (paramInt1 + paramInt2 <= paramArrayOfInt.length));
/*     */ 
/* 322 */     byte[] arrayOfByte = new byte[paramInt2];
/*     */ 
/* 324 */     checkForSerialIOHalted();
/*     */ 
/* 327 */     int i = read(arrayOfByte);
/*     */ 
/* 330 */     for (int j = 0; j < i; j++) {
/* 331 */       paramArrayOfInt[(paramInt1 + j)] = (arrayOfByte[j] & 0xFF);
/*     */     }
/*     */ 
/* 334 */     return i;
/*     */   }
/*     */ 
/*     */   public final int read(int[] paramArrayOfInt)
/*     */     throws IOException
/*     */   {
/* 351 */     return read(paramArrayOfInt, 0, paramArrayOfInt.length);
/*     */   }
/*     */ 
/*     */   final int[] readAvailableBytes()
/*     */     throws IOException, SerialIOHaltedException
/*     */   {
/* 365 */     int i = this.m_serialPortLocal.rxReadyCount();
/* 366 */     long l1 = System.currentTimeMillis();
/* 367 */     int j = 0;
/*     */ 
/* 370 */     while ((i == 0) && (j == 0)) {
/* 371 */       MedicalDevice.Util.sleepMS(this.m_ioDelay);
/* 372 */       i = this.m_serialPortLocal.rxReadyCount();
/* 373 */       long l2 = System.currentTimeMillis() - l1;
/* 374 */       j = l2 > getReadTimeOut() ? 1 : 0;
/*     */     }
/*     */ 
/* 377 */     int[] arrayOfInt = new int[i];
/* 378 */     if (i > 0) {
/* 379 */       read(arrayOfInt);
/*     */     }
/* 381 */     return arrayOfInt;
/*     */   }
/*     */ 
/*     */   final int readUntilEmpty()
/*     */   {
/* 393 */     int i = 0;
/*     */ 
/* 396 */     Contract.pre(this.m_serialPortLocal != null);
/*     */     try
/*     */     {
/*     */       int j;
/* 399 */       while ((j = this.m_serialPortLocal.rxReadyCount()) > 0) {
/* 400 */         byte[] arrayOfByte = new byte[j];
/*     */ 
/* 403 */         MedicalDevice.Util.sleepMS(this.m_ioDelay);
/* 404 */         i += this.m_serialPortLocal.getData(arrayOfByte);
/*     */       }
/*     */     }
/*     */     catch (IOException localIOException)
/*     */     {
/*     */     }
/*     */ 
/* 411 */     if (i > 0) {
/* 412 */       MedicalDevice.logInfoHigh(this, "readUntilEmpty: dumped " + i + (i > 1 ? " bytes." : " byte."));
/*     */     }
/*     */ 
/* 416 */     return i;
/*     */   }
/*     */ 
/*     */   final void write(byte paramByte)
/*     */     throws IOException, SerialIOHaltedException
/*     */   {
/* 429 */     Contract.pre(this.m_serialPortLocal != null);
/* 430 */     Contract.pre(this.m_serialPortLocal.isOpen());
/*     */ 
/* 432 */     checkForSerialIOHalted();
/*     */ 
/* 434 */     MedicalDevice.Util.sleepMS(this.m_ioDelay);
/*     */ 
/* 437 */     long l = System.currentTimeMillis() - this.m_startTimeMS;
/* 438 */     MedicalDevice.logInfoHigh(this, "write(byte)(" + l + "MS): writing <" + MedicalDevice.Util.getHex(paramByte) + ">");
/*     */ 
/* 440 */     this.m_startTimeMS = System.currentTimeMillis();
/*     */ 
/* 442 */     this.m_serialPortLocal.putByte(paramByte);
/*     */   }
/*     */ 
/*     */   final void write(byte[] paramArrayOfByte)
/*     */     throws IOException, SerialIOHaltedException
/*     */   {
/* 456 */     Contract.pre(this.m_serialPortLocal != null);
/* 457 */     Contract.pre(this.m_serialPortLocal.isOpen());
/* 458 */     Contract.pre(paramArrayOfByte != null);
/*     */ 
/* 460 */     checkForSerialIOHalted();
/*     */ 
/* 462 */     MedicalDevice.Util.sleepMS(this.m_ioDelay);
/*     */ 
/* 464 */     long l = System.currentTimeMillis() - this.m_startTimeMS;
/* 465 */     MedicalDevice.logInfoHigh(this, "write(byte buffer[])(" + l + "MS): writing <" + MedicalDevice.Util.getHex(paramArrayOfByte) + ">");
/*     */ 
/* 467 */     this.m_startTimeMS = System.currentTimeMillis();
/*     */ 
/* 469 */     this.m_serialPortLocal.putData(paramArrayOfByte);
/*     */   }
/*     */ 
/*     */   final void write(int[] paramArrayOfInt)
/*     */     throws IOException, SerialIOHaltedException
/*     */   {
/* 485 */     Contract.pre(this.m_serialPortLocal != null);
/* 486 */     Contract.pre(this.m_serialPortLocal.isOpen());
/* 487 */     Contract.pre(paramArrayOfInt != null);
/*     */ 
/* 489 */     checkForSerialIOHalted();
/*     */ 
/* 491 */     MedicalDevice.Util.sleepMS(this.m_ioDelay);
/*     */ 
/* 494 */     long l = System.currentTimeMillis() - this.m_startTimeMS;
/* 495 */     MedicalDevice.logInfoHigh(this, "write(int buffer[])(" + l + "MS): writing <" + MedicalDevice.Util.getHex(paramArrayOfInt) + ">");
/*     */ 
/* 497 */     this.m_startTimeMS = System.currentTimeMillis();
/*     */ 
/* 499 */     byte[] arrayOfByte = new byte[paramArrayOfInt.length];
/*     */ 
/* 503 */     for (int i = 0; i < paramArrayOfInt.length; i++) {
/* 504 */       int j = paramArrayOfInt[i];
/* 505 */       if ((j < 0) || (j > 255)) {
/* 506 */         MedicalDevice.logWarning(this, "write: warning - value at index " + i + " (" + j + ") may be out of range.");
/*     */       }
/*     */ 
/* 510 */       arrayOfByte[i] = (byte)j;
/*     */     }
/*     */ 
/* 513 */     this.m_serialPortLocal.putData(arrayOfByte);
/*     */   }
/*     */ 
/*     */   final void write(int paramInt)
/*     */     throws IOException, SerialIOHaltedException
/*     */   {
/* 528 */     int[] arrayOfInt = new int[1];
/* 529 */     arrayOfInt[0] = paramInt;
/* 530 */     write(arrayOfInt);
/*     */   }
/*     */ 
/*     */   final void write(char paramChar)
/*     */     throws IOException, SerialIOHaltedException
/*     */   {
/* 545 */     Contract.pre(this.m_serialPortLocal != null);
/* 546 */     Contract.pre(this.m_serialPortLocal.isOpen());
/*     */ 
/* 548 */     checkForSerialIOHalted();
/*     */ 
/* 550 */     MedicalDevice.Util.sleepMS(this.m_ioDelay);
/*     */ 
/* 553 */     long l = System.currentTimeMillis() - this.m_startTimeMS;
/* 554 */     MedicalDevice.logInfoHigh(this, "write(char)(" + l + "MS): writing <" + MedicalDevice.Util.getHex(paramChar) + ">");
/*     */ 
/* 556 */     this.m_startTimeMS = System.currentTimeMillis();
/*     */ 
/* 558 */     if (paramChar > 'ÿ') {
/* 559 */       MedicalDevice.logWarning(this, "write: warning - value of " + paramChar + " may be out of range.");
/*     */     }
/*     */ 
/* 564 */     this.m_serialPortLocal.putByte((byte)paramChar);
/*     */   }
/*     */ 
/*     */   final void write(String paramString)
/*     */     throws IOException, SerialIOHaltedException
/*     */   {
/* 579 */     byte[] arrayOfByte = paramString.getBytes();
/*     */ 
/* 581 */     Contract.pre(this.m_serialPortLocal != null);
/* 582 */     Contract.pre(this.m_serialPortLocal.isOpen());
/* 583 */     Contract.pre(paramString != null);
/*     */ 
/* 585 */     checkForSerialIOHalted();
/*     */ 
/* 587 */     MedicalDevice.Util.sleepMS(this.m_ioDelay);
/*     */ 
/* 589 */     long l = System.currentTimeMillis() - this.m_startTimeMS;
/* 590 */     MedicalDevice.logInfoHigh(this, "write(String)(" + l + "MS): writing <" + paramString + ">");
/* 591 */     this.m_startTimeMS = System.currentTimeMillis();
/*     */ 
/* 593 */     this.m_serialPortLocal.putData(arrayOfByte);
/*     */   }
/*     */ 
/*     */   final void dumpSerialStatus()
/*     */     throws IOException
/*     */   {
/* 603 */     Contract.pre(this.m_serialPortLocal != null);
/*     */ 
/* 605 */     long l = System.currentTimeMillis() - this.m_startTimeMS;
/* 606 */     MedicalDevice.logInfoHigh(this, "dumpSerialStatus(" + l + "MS): Rx ready count: " + this.m_serialPortLocal.rxReadyCount() + "   Tx buffer count: " + this.m_serialPortLocal.txBufCount() + "   CD:  " + this.m_serialPortLocal.sigCD() + "   CTS: " + this.m_serialPortLocal.sigCTS() + "   DSR: " + this.m_serialPortLocal.sigDSR());
/*     */   }
/*     */ 
/*     */   static final String[] getPortList()
/*     */     throws IOException
/*     */   {
/* 620 */     String[] arrayOfString = SerialPortLocal.getPortList();
/*     */ 
/* 622 */     Arrays.sort(arrayOfString);
/* 623 */     return arrayOfString;
/*     */   }
/*     */ 
/*     */   final void setBaudRate(int paramInt)
/*     */   {
/* 633 */     Contract.pre((paramInt == 7) || (paramInt == 8) || (paramInt == 9) || (paramInt == 10));
/*     */ 
/* 635 */     this.m_serialConfig.setBitRate(paramInt);
/*     */   }
/*     */ 
/*     */   final void setDTR()
/*     */     throws IOException
/*     */   {
/* 645 */     Contract.pre(this.m_serialPortLocal != null);
/*     */ 
/* 647 */     this.m_serialPortLocal.setDTR(true);
/*     */   }
/*     */ 
/*     */   final void clearDTR()
/*     */     throws IOException
/*     */   {
/* 657 */     Contract.pre(this.m_serialPortLocal != null);
/*     */ 
/* 659 */     this.m_serialPortLocal.setDTR(false);
/*     */   }
/*     */ 
/*     */   final void setRTS()
/*     */     throws IOException
/*     */   {
/* 669 */     Contract.pre(this.m_serialPortLocal != null);
/*     */ 
/* 671 */     this.m_serialPortLocal.setRTS(true);
/*     */   }
/*     */ 
/*     */   final void clearRTS()
/*     */     throws IOException
/*     */   {
/* 681 */     Contract.pre(this.m_serialPortLocal != null);
/*     */ 
/* 683 */     this.m_serialPortLocal.setRTS(false);
/*     */   }
/*     */ 
/*     */   final String getCommPortName()
/*     */   {
/* 692 */     return this.m_commPortName;
/*     */   }
/*     */ 
/*     */   private void setDefaultCommParameters(int paramInt)
/*     */   {
/* 702 */     setReceiveBufferSize(16384);
/* 703 */     setTransmitBufferSize(2048);
/* 704 */     setBaudRate(paramInt);
/* 705 */     this.m_serialConfig.setDataBits(3);
/* 706 */     this.m_serialConfig.setStopBits(0);
/* 707 */     this.m_serialConfig.setParity(0);
/*     */ 
/* 709 */     this.m_serialConfig.setHandshake(0);
/*     */   }
/*     */ 
/*     */   private final void setReceiveBufferSize(int paramInt)
/*     */   {
/* 718 */     this.m_serialConfig.setRxLen(paramInt);
/*     */   }
/*     */ 
/*     */   private final void setTransmitBufferSize(int paramInt)
/*     */   {
/* 727 */     this.m_serialConfig.setTxLen(paramInt);
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.deviceportreader.SerialPort
 * JD-Core Version:    0.6.0
 */