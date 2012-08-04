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
/*     */   private SerialPortLocal m_serialPortLocal;  // O
/*     */   private final SerialConfig m_serialConfig;  // H
/*     */   private final String m_commPortName;        // V
/*     */   private long m_startTimeMS;                 // K
/*     */   // d(String paramString, int paramInt1, int paramInt2)
/*     */   SerialPort(String paramString, int paramInt1, int paramInt2)
/*     */     throws IOException
/*     */   {
/*  79 */     Contract.pre(paramInt2, 0, 4);
/*  80 */     this.m_commPortName = paramString;
/*     */ 
/*  83 */     this.m_serialConfig = new SerialConfig(this.m_commPortName);
/*  84 */     C(paramInt1);
/*  85 */     this.m_serialConfig.setParity(paramInt2);
/*     */ 
/*  87 */     MedicalDevice.logInfoHigh(this, "creating port '" + this.m_commPortName + "' with parameters (rate/parity/data/stop/handshake): " + this.m_serialConfig.getSettingsString());
/*     */ 
/*  92 */     this.m_serialPortLocal = new SerialPortLocal(this.m_serialConfig);
/*  93 */     this.m_serialPortLocal.setTimeoutRx(250);
/*  94 */     this.m_serialPortLocal.setTimeoutTx(250);
/*  95 */     this.m_serialPortLocal.setDTR(true);
/*  96 */     this.m_serialPortLocal.setRTS(true);
/*  97 */     this.m_startTimeMS = System.currentTimeMillis();
/*  98 */     this.m_serialPortLocal.setPortName(this.m_commPortName);
/*  99 */     MedicalDevice.Util.sleepMS(500);
/* 100 */     dumpSerialStatus();
/* 101 */     A(250);
/*     */   }
/*     */   // d(String paramString, int paramInt)
/*     */   SerialPort(String paramString, int paramInt)
/*     */     throws IOException
/*     */   {
/* 115 */     this(paramString, paramInt, 0);
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 127 */     return "RS232 @" + this.m_serialConfig.getSettingsString();
/*     */   }
/*     */   // final void B(int paramInt)
/*     */   final void setReadTimeOut(int paramInt)
/*     */     throws IOException
/*     */   {
/* 138 */     super.setReadTimeOut(paramInt);
/* 139 */     this.m_serialPortLocal.setTimeoutRx(paramInt);
/*     */   }
/*     */   // final boolean N()
/*     */   final boolean isOpen()
/*     */   {
/* 149 */     Contract.pre(this.m_serialPortLocal != null);
/*     */ 
/* 151 */     return this.m_serialPortLocal.isOpen();
/*     */   }
/*     */   // final void E() throws t
/*     */   final void close()
/*     */     throws IOException
/*     */   {
/* 156 */     Contract.pre(this.m_serialPortLocal != null);
/*     */ 
/* 158 */     if (!this.m_serialPortLocal.isOpen())
/* 159 */       throw new IOException("port not open: " + this.m_serialPortLocal);
/*     */   }
/*     */   // final void B()
/*     */   final void setIODelay()
/*     */     throws IOException
/*     */   {
/* 165 */     Contract.pre(this.m_serialPortLocal != null);
/*     */ 
/* 167 */     if (this.m_serialPortLocal.isOpen())
/* 168 */       this.m_serialPortLocal.close();  } 
/* 184 */   final String C() throws IOException, SerialIOHaltedException { int i = 65535;
/*     */ 
/* 187 */     int m = 0;
/* 188 */     StringBuffer localStringBuffer = new StringBuffer("");
/*     */ 
/* 190 */     Contract.pre(this.m_serialPortLocal != null);
/* 191 */     Contract.pre(this.m_serialPortLocal.isOpen());
/*     */ 
/* 193 */     checkForSerialIOHalted();
/*     */ 
/* 195 */     MedicalDevice.Util.sleepMS(getIoDelay());
/*     */     int k;
/*     */     do { int j = i;
/* 198 */       k = this.m_serialPortLocal.getByte();
/* 199 */       i = (char)k;
/*     */ 
/* 201 */       if (k != -1) {
/* 202 */         localStringBuffer.append(i);
/*     */       }
/*     */ 
/* 206 */       if ((j == 13) && (i == 10)) {
/* 207 */         m = 1;
/*     */       }
/*     */     }
/* 210 */     while ((k != -1) && (m == 0));
/*     */ 
/* 213 */     long l = System.currentTimeMillis() - this.m_startTimeMS;
/* 214 */     MedicalDevice.logInfoHigh(this, "readLine(" + l + "MS): read <" + localStringBuffer + ">");
/* 215 */     this.m_startTimeMS = System.currentTimeMillis();
/*     */ 
/* 217 */     return new String(localStringBuffer);
/*     */   }
/*     */   // final int O()
/*     */   final int read()
/*     */     throws IOException, SerialIOHaltedException
/*     */   {
/* 232 */     int i = -1;
/*     */ 
/* 234 */     Contract.pre(this.m_serialPortLocal != null);
/* 235 */     Contract.pre(this.m_serialPortLocal.isOpen());
/*     */ 
/* 237 */     checkForSerialIOHalted();
/*     */ 
/* 239 */     MedicalDevice.Util.sleepMS(getIoDelay());
/* 240 */     i = this.m_serialPortLocal.getByte();
/*     */ 
/* 243 */     long l = System.currentTimeMillis() - this.m_startTimeMS;
/* 244 */     MedicalDevice.logInfoHigh(this, "read()(" + l + "MS): read <" + MedicalDevice.Util.getHex(i) + ">");
/*     */ 
/* 246 */     this.m_startTimeMS = System.currentTimeMillis();
/* 247 */     return i;
/*     */   }
/*     */   // private final int B(byte[] paramArrayOfByte)
/*     */   private final int read(byte[] paramArrayOfByte)
/*     */     throws IOException, SerialIOHaltedException
/*     */   {
/* 263 */     Contract.pre(this.m_serialPortLocal != null);
/* 264 */     Contract.pre(this.m_serialPortLocal.isOpen());
/* 265 */     Contract.pre(paramArrayOfByte != null);
/*     */ 
/* 267 */     checkForSerialIOHalted();
/* 268 */     MedicalDevice.Util.sleepMS(getIoDelay());
/* 269 */     int i = 0;
/*     */ 
/* 274 */     for (int j = 0; j < paramArrayOfByte.length; j++) {
/* 275 */       int k = this.m_serialPortLocal.getByte();
/*     */ 
/* 277 */       if (k == -1) {
/*     */         break;
/*     */       }
/* 280 */       paramArrayOfByte[j] = (byte)k;
/* 281 */       i++;
/*     */     }
/*     */ 
/* 285 */     long l = System.currentTimeMillis() - this.m_startTimeMS;
/* 286 */     MedicalDevice.logInfoHigh(this, "read(byte[])(" + l + "MS): read <" + MedicalDevice.Util.getHex(paramArrayOfByte, i) + ">");
/*     */ 
/* 288 */     this.m_startTimeMS = System.currentTimeMillis();
/*     */ 
/* 290 */     return i;
/*     */   }
/*     */   // final int A(int[] paramArrayOfInt, int paramInt1, int paramInt2)
/*     */   final int read(int[] paramArrayOfInt, int paramInt1, int paramInt2)
/*     */     throws IOException, SerialIOHaltedException
/*     */   {
/* 307 */     Contract.pre(paramInt1, 0, paramArrayOfInt.length);
/* 308 */     Contract.pre((paramInt2 >= 0) && (paramInt1 + paramInt2 <= paramArrayOfInt.length));
/*     */ 
/* 310 */     byte[] arrayOfByte = new byte[paramInt2];
/*     */ 
/* 312 */     checkForSerialIOHalted();
/*     */ 
/* 315 */     int i = B(arrayOfByte);
/*     */ 
/* 318 */     for (int j = 0; j < i; j++) {
/* 319 */       paramArrayOfInt[(paramInt1 + j)] = (arrayOfByte[j] & 0xFF);
/*     */     }
/*     */ 
/* 322 */     return i;
/*     */   }
/*     */   // public final int B(int[] paramArrayOfInt)
/*     */   public final int read(int[] paramArrayOfInt)
/*     */     throws IOException
/*     */   {
/* 339 */     return A(paramArrayOfInt, 0, paramArrayOfInt.length);
/*     */   }
/*     */   // final int[] I()
/*     */   final int[] readAvailableBytes()
/*     */     throws IOException, SerialIOHaltedException
/*     */   {
/* 353 */     int i = this.m_serialPortLocal.rxReadyCount();
/* 354 */     long l1 = System.currentTimeMillis();
/* 355 */     int j = 0;
/*     */ 
/* 358 */     while ((i == 0) && (j == 0)) {
/* 359 */       MedicalDevice.Util.sleepMS(getIoDelay());
/* 360 */       i = this.m_serialPortLocal.rxReadyCount();
/* 361 */       long l2 = System.currentTimeMillis() - l1;
/* 362 */       j = l2 > D() ? 1 : 0;
/*     */     }
/*     */ 
/* 365 */     int[] arrayOfInt = new int[i];
/* 366 */     if (i > 0) {
/* 367 */       B(arrayOfInt);
/*     */     }
/* 369 */     return arrayOfInt;
/*     */   }
/*     */   // final void A()
/*     */   final int readUntilEmpty()
/*     */   {
/* 380 */     int i = 0;
/*     */ 
/* 383 */     Contract.pre(this.m_serialPortLocal != null);
/*     */     try
/*     */     {
/*     */       int j;
/* 386 */       while ((j = this.m_serialPortLocal.rxReadyCount()) > 0) {
/* 387 */         byte[] arrayOfByte = new byte[j];
/*     */ 
/* 390 */         MedicalDevice.Util.sleepMS(getIoDelay());
/* 391 */         i += this.m_serialPortLocal.getData(arrayOfByte);
/*     */       }
/*     */     }
/*     */     catch (IOException localIOException)
/*     */     {
/*     */     }
/*     */ 
/* 398 */     if (i > 0)
/* 399 */       MedicalDevice.logInfoHigh(this, "readUntilEmpty: dumped " + i + (i > 1 ? " bytes." : " byte."));
/*     */   }
/*     */   // final void A(byte paramByte)
/*     */   final void write(byte paramByte)
/*     */     throws IOException, SerialIOHaltedException
/*     */   {
/* 415 */     Contract.pre(this.m_serialPortLocal != null);
/* 416 */     Contract.pre(this.m_serialPortLocal.isOpen());
/*     */ 
/* 418 */     checkForSerialIOHalted();
/*     */ 
/* 420 */     MedicalDevice.Util.sleepMS(getIoDelay());
/*     */ 
/* 423 */     long l = System.currentTimeMillis() - this.m_startTimeMS;
/* 424 */     MedicalDevice.logInfoHigh(this, "write(byte)(" + l + "MS): writing <" + MedicalDevice.Util.getHex(paramByte) + ">");
/*     */ 
/* 426 */     this.m_startTimeMS = System.currentTimeMillis();
/*     */ 
/* 428 */     this.m_serialPortLocal.putByte(paramByte);
/*     */   }
/*     */   // final void A(byte[] paramArrayOfByte)
/*     */   final void write(byte[] paramArrayOfByte)
/*     */     throws IOException, SerialIOHaltedException
/*     */   {
/* 442 */     Contract.pre(this.m_serialPortLocal != null);
/* 443 */     Contract.pre(this.m_serialPortLocal.isOpen());
/* 444 */     Contract.pre(paramArrayOfByte != null);
/*     */ 
/* 446 */     checkForSerialIOHalted();
/*     */ 
/* 448 */     MedicalDevice.Util.sleepMS(getIoDelay());
/*     */ 
/* 450 */     long l = System.currentTimeMillis() - this.m_startTimeMS;
/* 451 */     MedicalDevice.logInfoHigh(this, "write(byte buffer[])(" + l + "MS): writing <" + MedicalDevice.Util.getHex(paramArrayOfByte) + ">");
/*     */ 
/* 453 */     this.m_startTimeMS = System.currentTimeMillis();
/*     */ 
/* 455 */     this.m_serialPortLocal.putData(paramArrayOfByte);
/*     */   }
/*     */   // final void A(int[] paramArrayOfInt)
/*     */   final void write(int[] paramArrayOfInt)
/*     */     throws IOException, SerialIOHaltedException
/*     */   {
/* 471 */     Contract.pre(this.m_serialPortLocal != null);
/* 472 */     Contract.pre(this.m_serialPortLocal.isOpen());
/* 473 */     Contract.pre(paramArrayOfInt != null);
/*     */ 
/* 475 */     checkForSerialIOHalted();
/*     */ 
/* 477 */     MedicalDevice.Util.sleepMS(getIoDelay());
/*     */ 
/* 480 */     long l = System.currentTimeMillis() - this.m_startTimeMS;
/* 481 */     MedicalDevice.logInfoHigh(this, "write(int buffer[])(" + l + "MS): writing <" + MedicalDevice.Util.getHex(paramArrayOfInt) + ">");
/*     */ 
/* 483 */     this.m_startTimeMS = System.currentTimeMillis();
/*     */ 
/* 485 */     byte[] arrayOfByte = new byte[paramArrayOfInt.length];
/*     */ 
/* 489 */     for (int i = 0; i < paramArrayOfInt.length; i++) {
/* 490 */       int j = paramArrayOfInt[i];
/* 491 */       if ((j < 0) || (j > 255)) {
/* 492 */         MedicalDevice.logWarning(this, "write: warning - value at index " + i + " (" + j + ") may be out of range.");
/*     */       }
/*     */ 
/* 496 */       arrayOfByte[i] = (byte)j;
/*     */     }
/*     */ 
/* 499 */     this.m_serialPortLocal.putData(arrayOfByte);
/*     */   }
/*     */   // final void G(int paramInt)
/*     */   final void write(int paramInt)
/*     */     throws IOException, SerialIOHaltedException
/*     */   {
/* 514 */     int[] arrayOfInt = new int[1];
/* 515 */     arrayOfInt[0] = paramInt;
/* 516 */     A(arrayOfInt);
/*     */   }
/*     */   // final void A(char paramChar)
/*     */   final void write(char paramChar)
/*     */     throws IOException, SerialIOHaltedException
/*     */   {
/* 531 */     Contract.pre(this.m_serialPortLocal != null);
/* 532 */     Contract.pre(this.m_serialPortLocal.isOpen());
/*     */ 
/* 534 */     checkForSerialIOHalted();
/*     */ 
/* 536 */     MedicalDevice.Util.sleepMS(getIoDelay());
/*     */ 
/* 539 */     long l = System.currentTimeMillis() - this.m_startTimeMS;
/* 540 */     MedicalDevice.logInfoHigh(this, "write(char)(" + l + "MS): writing <" + MedicalDevice.Util.getHex(paramChar) + ">");
/*     */ 
/* 542 */     this.m_startTimeMS = System.currentTimeMillis();
/*     */ 
/* 544 */     if (paramChar > 'ÿ') {
/* 545 */       MedicalDevice.logWarning(this, "write: warning - value of " + paramChar + " may be out of range.");
/*     */     }
/*     */ 
/* 550 */     this.m_serialPortLocal.putByte((byte)paramChar);
/*     */   }
/*     */   // final void A(String paramString)
/*     */   final void write(String paramString)
/*     */     throws IOException, SerialIOHaltedException
/*     */   {
/* 566 */     byte[] arrayOfByte = paramString.getBytes();
/*     */ 
/* 568 */     Contract.pre(this.m_serialPortLocal != null);
/* 569 */     Contract.pre(this.m_serialPortLocal.isOpen());
/* 570 */     Contract.pre(paramString != null);
/*     */ 
/* 572 */     checkForSerialIOHalted();
/*     */ 
/* 574 */     MedicalDevice.Util.sleepMS(getIoDelay());
/*     */ 
/* 576 */     long l = System.currentTimeMillis() - this.m_startTimeMS;
/* 577 */     MedicalDevice.logInfoHigh(this, "write(String)(" + l + "MS): writing <" + paramString + ">");
/* 578 */     this.m_startTimeMS = System.currentTimeMillis();
/*     */ 
/* 580 */     this.m_serialPortLocal.putData(arrayOfByte);
/*     */   }
/*     */   // final void K()
/*     */   final void dumpSerialStatus()
/*     */     throws IOException
/*     */   {
/* 590 */     Contract.pre(this.m_serialPortLocal != null);
/*     */ 
/* 592 */     long l = System.currentTimeMillis() - this.m_startTimeMS;
/* 593 */     MedicalDevice.logInfoHigh(this, "dumpSerialStatus(" + l + "MS): Rx ready count: " + this.m_serialPortLocal.rxReadyCount() + "   Tx buffer count: " + this.m_serialPortLocal.txBufCount() + "   CD:  " + this.m_serialPortLocal.sigCD() + "   CTS: " + this.m_serialPortLocal.sigCTS() + "   DSR: " + this.m_serialPortLocal.sigDSR());
/*     */   }
/*     */   // static final String[] M()
/*     */   static final String[] getPortList()
/*     */     throws IOException
/*     */   {
/* 607 */     String[] arrayOfString = SerialPortLocal.getPortList();
/*     */ 
/* 609 */     Arrays.sort(arrayOfString);
/* 610 */     return arrayOfString;
/*     */   }
/*     */   // final void E(int paramInt)
/*     */   final void setBaudRate(int paramInt)
/*     */   {
/* 620 */     Contract.pre((paramInt == 7) || (paramInt == 8) || (paramInt == 9) || (paramInt == 10));
/*     */ 
/* 622 */     this.m_serialConfig.setBitRate(paramInt);
/*     */   }
/*     */   // final void L()
/*     */   final void setDTR()
/*     */     throws IOException
/*     */   {
/* 632 */     Contract.pre(this.m_serialPortLocal != null);
/*     */ 
/* 634 */     this.m_serialPortLocal.setDTR(true);
/*     */   }
/*     */   // final void Q()
/*     */   final void clearDTR()
/*     */     throws IOException
/*     */   {
/* 644 */     Contract.pre(this.m_serialPortLocal != null);
/*     */ 
/* 646 */     this.m_serialPortLocal.setDTR(false);
/*     */   }
/*     */   // final void J()
/*     */   final void setRTS()
/*     */     throws IOException
/*     */   {
/* 656 */     Contract.pre(this.m_serialPortLocal != null);
/*     */ 
/* 658 */     this.m_serialPortLocal.setRTS(true);
/*     */   }
/*     */   // final void P()
/*     */   final void clearRTS()
/*     */     throws IOException
/*     */   {
/* 668 */     Contract.pre(this.m_serialPortLocal != null);
/*     */ 
/* 670 */     this.m_serialPortLocal.setRTS(false);
/*     */   }
/*     */   // final String H()
/*     */   final String getCommPortName()
/*     */   {
/* 679 */     return this.m_commPortName;
/*     */   }
/*     */   // private void C(int paramInt)
/*     */   private void setDefaultCommParameters(int paramInt)
/*     */   {
/* 689 */     setReceiveBufferSize(16384);
/* 690 */     setTransmitBufferSize(2048);
/* 691 */     setBaudRate(paramInt);
/* 692 */     this.m_serialConfig.setDataBits(3);
/* 693 */     this.m_serialConfig.setStopBits(0);
/* 694 */     this.m_serialConfig.setParity(0);
/*     */ 
/* 696 */     this.m_serialConfig.setHandshake(0);
/*     */   }
/*     */   // private final void F(int paramInt)
/*     */   private final void setReceiveBufferSize(int paramInt)
/*     */   {
/* 705 */     this.m_serialConfig.setRxLen(paramInt);
/*     */   }
/*     */   // private final void D(int paramInt)
/*     */   private final void setTransmitBufferSize(int paramInt)
/*     */   {
/* 714 */     this.m_serialConfig.setTxLen(paramInt);
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.A.d
 * JD-Core Version:    0.6.0
 */