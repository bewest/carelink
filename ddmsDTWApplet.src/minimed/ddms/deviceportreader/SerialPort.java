/*     */ package minimed.ddms.A;
/*     */ 
/*     */ import Serialio.SerialConfig;
/*     */ import Serialio.SerialPortLocal;
/*     */ import java.io.IOException;
/*     */ import java.util.Arrays;
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ final class d extends c
/*     */ {
/*     */   static final int X = 10;
/*     */   static final int R = 7;
/*     */   static final int Q = 8;
/*     */   static final int T = 9;
/*     */   static final int F = 0;
/*     */   static final int E = 1;
/*     */   static final int M = 2;
/*     */   static final int N = 3;
/*     */   static final int G = 4;
/*     */   private static final byte W = -1;
/*     */   private static final char L = '\n';
/*     */   private static final char D = '\r';
/*     */   private static final int S = 250;
/*     */   private static final int I = 250;
/*     */   private static final int J = 16384;
/*     */   private static final int Y = 2048;
/*     */   private static final int P = 500;
/*     */   private static final int U = 250;
/*     */   private SerialPortLocal O;
/*     */   private final SerialConfig H;
/*     */   private final String V;
/*     */   private long K;
/*     */ 
/*     */   d(String paramString, int paramInt1, int paramInt2)
/*     */     throws IOException
/*     */   {
/*  79 */     Contract.pre(paramInt2, 0, 4);
/*  80 */     this.V = paramString;
/*     */ 
/*  83 */     this.H = new SerialConfig(this.V);
/*  84 */     C(paramInt1);
/*  85 */     this.H.setParity(paramInt2);
/*     */ 
/*  87 */     O.B(this, "creating port '" + this.V + "' with parameters (rate/parity/data/stop/handshake): " + this.H.getSettingsString());
/*     */ 
/*  92 */     this.O = new SerialPortLocal(this.H);
/*  93 */     this.O.setTimeoutRx(250);
/*  94 */     this.O.setTimeoutTx(250);
/*  95 */     this.O.setDTR(true);
/*  96 */     this.O.setRTS(true);
/*  97 */     this.K = System.currentTimeMillis();
/*  98 */     this.O.setPortName(this.V);
/*  99 */     O._B.G(500);
/* 100 */     K();
/* 101 */     A(250);
/*     */   }
/*     */ 
/*     */   d(String paramString, int paramInt)
/*     */     throws IOException
/*     */   {
/* 115 */     this(paramString, paramInt, 0);
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 127 */     return "RS232 @" + this.H.getSettingsString();
/*     */   }
/*     */ 
/*     */   final void B(int paramInt)
/*     */     throws IOException
/*     */   {
/* 138 */     super.B(paramInt);
/* 139 */     this.O.setTimeoutRx(paramInt);
/*     */   }
/*     */ 
/*     */   final boolean N()
/*     */   {
/* 149 */     Contract.pre(this.O != null);
/*     */ 
/* 151 */     return this.O.isOpen();
/*     */   }
/*     */ 
/*     */   final void E() throws t
/*     */   {
/* 156 */     Contract.pre(this.O != null);
/*     */ 
/* 158 */     if (!this.O.isOpen())
/* 159 */       throw new t("port not open: " + this.O);
/*     */   }
/*     */ 
/*     */   final void B()
/*     */     throws IOException
/*     */   {
/* 165 */     Contract.pre(this.O != null);
/*     */ 
/* 167 */     if (this.O.isOpen())
/* 168 */       this.O.close();  } 
/* 184 */   final String C() throws IOException, W { int i = 65535;
/*     */ 
/* 187 */     int m = 0;
/* 188 */     StringBuffer localStringBuffer = new StringBuffer("");
/*     */ 
/* 190 */     Contract.pre(this.O != null);
/* 191 */     Contract.pre(this.O.isOpen());
/*     */ 
/* 193 */     G();
/*     */ 
/* 195 */     O._B.G(F());
/*     */     int k;
/*     */     do { int j = i;
/* 198 */       k = this.O.getByte();
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
/* 213 */     long l = System.currentTimeMillis() - this.K;
/* 214 */     O.B(this, "readLine(" + l + "MS): read <" + localStringBuffer + ">");
/* 215 */     this.K = System.currentTimeMillis();
/*     */ 
/* 217 */     return new String(localStringBuffer);
/*     */   }
/*     */ 
/*     */   final int O()
/*     */     throws IOException, W
/*     */   {
/* 232 */     int i = -1;
/*     */ 
/* 234 */     Contract.pre(this.O != null);
/* 235 */     Contract.pre(this.O.isOpen());
/*     */ 
/* 237 */     G();
/*     */ 
/* 239 */     O._B.G(F());
/* 240 */     i = this.O.getByte();
/*     */ 
/* 243 */     long l = System.currentTimeMillis() - this.K;
/* 244 */     O.B(this, "read()(" + l + "MS): read <" + O._B.H(i) + ">");
/*     */ 
/* 246 */     this.K = System.currentTimeMillis();
/* 247 */     return i;
/*     */   }
/*     */ 
/*     */   private final int B(byte[] paramArrayOfByte)
/*     */     throws IOException, W
/*     */   {
/* 263 */     Contract.pre(this.O != null);
/* 264 */     Contract.pre(this.O.isOpen());
/* 265 */     Contract.pre(paramArrayOfByte != null);
/*     */ 
/* 267 */     G();
/* 268 */     O._B.G(F());
/* 269 */     int i = 0;
/*     */ 
/* 274 */     for (int j = 0; j < paramArrayOfByte.length; j++) {
/* 275 */       int k = this.O.getByte();
/*     */ 
/* 277 */       if (k == -1) {
/*     */         break;
/*     */       }
/* 280 */       paramArrayOfByte[j] = (byte)k;
/* 281 */       i++;
/*     */     }
/*     */ 
/* 285 */     long l = System.currentTimeMillis() - this.K;
/* 286 */     O.B(this, "read(byte[])(" + l + "MS): read <" + O._B.B(paramArrayOfByte, i) + ">");
/*     */ 
/* 288 */     this.K = System.currentTimeMillis();
/*     */ 
/* 290 */     return i;
/*     */   }
/*     */ 
/*     */   final int A(int[] paramArrayOfInt, int paramInt1, int paramInt2)
/*     */     throws IOException, W
/*     */   {
/* 307 */     Contract.pre(paramInt1, 0, paramArrayOfInt.length);
/* 308 */     Contract.pre((paramInt2 >= 0) && (paramInt1 + paramInt2 <= paramArrayOfInt.length));
/*     */ 
/* 310 */     byte[] arrayOfByte = new byte[paramInt2];
/*     */ 
/* 312 */     G();
/*     */ 
/* 315 */     int i = B(arrayOfByte);
/*     */ 
/* 318 */     for (int j = 0; j < i; j++) {
/* 319 */       paramArrayOfInt[(paramInt1 + j)] = (arrayOfByte[j] & 0xFF);
/*     */     }
/*     */ 
/* 322 */     return i;
/*     */   }
/*     */ 
/*     */   public final int B(int[] paramArrayOfInt)
/*     */     throws IOException
/*     */   {
/* 339 */     return A(paramArrayOfInt, 0, paramArrayOfInt.length);
/*     */   }
/*     */ 
/*     */   final int[] I()
/*     */     throws IOException, W
/*     */   {
/* 353 */     int i = this.O.rxReadyCount();
/* 354 */     long l1 = System.currentTimeMillis();
/* 355 */     int j = 0;
/*     */ 
/* 358 */     while ((i == 0) && (j == 0)) {
/* 359 */       O._B.G(F());
/* 360 */       i = this.O.rxReadyCount();
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
/*     */ 
/*     */   final void A()
/*     */   {
/* 380 */     int i = 0;
/*     */ 
/* 383 */     Contract.pre(this.O != null);
/*     */     try
/*     */     {
/*     */       int j;
/* 386 */       while ((j = this.O.rxReadyCount()) > 0) {
/* 387 */         byte[] arrayOfByte = new byte[j];
/*     */ 
/* 390 */         O._B.G(F());
/* 391 */         i += this.O.getData(arrayOfByte);
/*     */       }
/*     */     }
/*     */     catch (IOException localIOException)
/*     */     {
/*     */     }
/*     */ 
/* 398 */     if (i > 0)
/* 399 */       O.B(this, "readUntilEmpty: dumped " + i + (i > 1 ? " bytes." : " byte."));
/*     */   }
/*     */ 
/*     */   final void A(byte paramByte)
/*     */     throws IOException, W
/*     */   {
/* 415 */     Contract.pre(this.O != null);
/* 416 */     Contract.pre(this.O.isOpen());
/*     */ 
/* 418 */     G();
/*     */ 
/* 420 */     O._B.G(F());
/*     */ 
/* 423 */     long l = System.currentTimeMillis() - this.K;
/* 424 */     O.B(this, "write(byte)(" + l + "MS): writing <" + O._B.B(paramByte) + ">");
/*     */ 
/* 426 */     this.K = System.currentTimeMillis();
/*     */ 
/* 428 */     this.O.putByte(paramByte);
/*     */   }
/*     */ 
/*     */   final void A(byte[] paramArrayOfByte)
/*     */     throws IOException, W
/*     */   {
/* 442 */     Contract.pre(this.O != null);
/* 443 */     Contract.pre(this.O.isOpen());
/* 444 */     Contract.pre(paramArrayOfByte != null);
/*     */ 
/* 446 */     G();
/*     */ 
/* 448 */     O._B.G(F());
/*     */ 
/* 450 */     long l = System.currentTimeMillis() - this.K;
/* 451 */     O.B(this, "write(byte buffer[])(" + l + "MS): writing <" + O._B.A(paramArrayOfByte) + ">");
/*     */ 
/* 453 */     this.K = System.currentTimeMillis();
/*     */ 
/* 455 */     this.O.putData(paramArrayOfByte);
/*     */   }
/*     */ 
/*     */   final void A(int[] paramArrayOfInt)
/*     */     throws IOException, W
/*     */   {
/* 471 */     Contract.pre(this.O != null);
/* 472 */     Contract.pre(this.O.isOpen());
/* 473 */     Contract.pre(paramArrayOfInt != null);
/*     */ 
/* 475 */     G();
/*     */ 
/* 477 */     O._B.G(F());
/*     */ 
/* 480 */     long l = System.currentTimeMillis() - this.K;
/* 481 */     O.B(this, "write(int buffer[])(" + l + "MS): writing <" + O._B.D(paramArrayOfInt) + ">");
/*     */ 
/* 483 */     this.K = System.currentTimeMillis();
/*     */ 
/* 485 */     byte[] arrayOfByte = new byte[paramArrayOfInt.length];
/*     */ 
/* 489 */     for (int i = 0; i < paramArrayOfInt.length; i++) {
/* 490 */       int j = paramArrayOfInt[i];
/* 491 */       if ((j < 0) || (j > 255)) {
/* 492 */         O.D(this, "write: warning - value at index " + i + " (" + j + ") may be out of range.");
/*     */       }
/*     */ 
/* 496 */       arrayOfByte[i] = (byte)j;
/*     */     }
/*     */ 
/* 499 */     this.O.putData(arrayOfByte);
/*     */   }
/*     */ 
/*     */   final void G(int paramInt)
/*     */     throws IOException, W
/*     */   {
/* 514 */     int[] arrayOfInt = new int[1];
/* 515 */     arrayOfInt[0] = paramInt;
/* 516 */     A(arrayOfInt);
/*     */   }
/*     */ 
/*     */   final void A(char paramChar)
/*     */     throws IOException, W
/*     */   {
/* 531 */     Contract.pre(this.O != null);
/* 532 */     Contract.pre(this.O.isOpen());
/*     */ 
/* 534 */     G();
/*     */ 
/* 536 */     O._B.G(F());
/*     */ 
/* 539 */     long l = System.currentTimeMillis() - this.K;
/* 540 */     O.B(this, "write(char)(" + l + "MS): writing <" + O._B.H(paramChar) + ">");
/*     */ 
/* 542 */     this.K = System.currentTimeMillis();
/*     */ 
/* 544 */     if (paramChar > 'ÿ') {
/* 545 */       O.D(this, "write: warning - value of " + paramChar + " may be out of range.");
/*     */     }
/*     */ 
/* 550 */     this.O.putByte((byte)paramChar);
/*     */   }
/*     */ 
/*     */   final void A(String paramString)
/*     */     throws IOException, W
/*     */   {
/* 566 */     byte[] arrayOfByte = paramString.getBytes();
/*     */ 
/* 568 */     Contract.pre(this.O != null);
/* 569 */     Contract.pre(this.O.isOpen());
/* 570 */     Contract.pre(paramString != null);
/*     */ 
/* 572 */     G();
/*     */ 
/* 574 */     O._B.G(F());
/*     */ 
/* 576 */     long l = System.currentTimeMillis() - this.K;
/* 577 */     O.B(this, "write(String)(" + l + "MS): writing <" + paramString + ">");
/* 578 */     this.K = System.currentTimeMillis();
/*     */ 
/* 580 */     this.O.putData(arrayOfByte);
/*     */   }
/*     */ 
/*     */   final void K()
/*     */     throws IOException
/*     */   {
/* 590 */     Contract.pre(this.O != null);
/*     */ 
/* 592 */     long l = System.currentTimeMillis() - this.K;
/* 593 */     O.B(this, "dumpSerialStatus(" + l + "MS): Rx ready count: " + this.O.rxReadyCount() + "   Tx buffer count: " + this.O.txBufCount() + "   CD:  " + this.O.sigCD() + "   CTS: " + this.O.sigCTS() + "   DSR: " + this.O.sigDSR());
/*     */   }
/*     */ 
/*     */   static final String[] M()
/*     */     throws IOException
/*     */   {
/* 607 */     String[] arrayOfString = SerialPortLocal.getPortList();
/*     */ 
/* 609 */     Arrays.sort(arrayOfString);
/* 610 */     return arrayOfString;
/*     */   }
/*     */ 
/*     */   final void E(int paramInt)
/*     */   {
/* 620 */     Contract.pre((paramInt == 7) || (paramInt == 8) || (paramInt == 9) || (paramInt == 10));
/*     */ 
/* 622 */     this.H.setBitRate(paramInt);
/*     */   }
/*     */ 
/*     */   final void L()
/*     */     throws IOException
/*     */   {
/* 632 */     Contract.pre(this.O != null);
/*     */ 
/* 634 */     this.O.setDTR(true);
/*     */   }
/*     */ 
/*     */   final void Q()
/*     */     throws IOException
/*     */   {
/* 644 */     Contract.pre(this.O != null);
/*     */ 
/* 646 */     this.O.setDTR(false);
/*     */   }
/*     */ 
/*     */   final void J()
/*     */     throws IOException
/*     */   {
/* 656 */     Contract.pre(this.O != null);
/*     */ 
/* 658 */     this.O.setRTS(true);
/*     */   }
/*     */ 
/*     */   final void P()
/*     */     throws IOException
/*     */   {
/* 668 */     Contract.pre(this.O != null);
/*     */ 
/* 670 */     this.O.setRTS(false);
/*     */   }
/*     */ 
/*     */   final String H()
/*     */   {
/* 679 */     return this.V;
/*     */   }
/*     */ 
/*     */   private void C(int paramInt)
/*     */   {
/* 689 */     F(16384);
/* 690 */     D(2048);
/* 691 */     E(paramInt);
/* 692 */     this.H.setDataBits(3);
/* 693 */     this.H.setStopBits(0);
/* 694 */     this.H.setParity(0);
/*     */ 
/* 696 */     this.H.setHandshake(0);
/*     */   }
/*     */ 
/*     */   private final void F(int paramInt)
/*     */   {
/* 705 */     this.H.setRxLen(paramInt);
/*     */   }
/*     */ 
/*     */   private final void D(int paramInt)
/*     */   {
/* 714 */     this.H.setTxLen(paramInt);
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.A.d
 * JD-Core Version:    0.6.0
 */