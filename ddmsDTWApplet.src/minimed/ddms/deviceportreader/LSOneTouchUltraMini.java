/*     */ package minimed.ddms.A;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Date;
/*     */ import java.util.Vector;
/*     */ import minimed.util.Contract;
/*     */ import minimed.util.OSInfo;
/*     */ 
/*     */ final class h extends w
/*     */ {
/*     */   static final int ӟ = 159;
/*     */   private static final int Ӥ = 10;
/*     */   private static final int ӡ = 500;
/*     */   private int[] ӥ;
/*     */   private int[] Ө;
/*     */   private int[] ӧ;
/*     */   private _A ө;
/*     */   private _A Ӧ;
/*     */   private _A ӣ;
/*     */   private _A Ӣ;
/*     */   private int Ӡ;
/*     */ 
/*     */   h()
/*     */   {
/*  76 */     this.ć = "LifeScan One Touch UltraMini/UltraEasy Meter";
/*  77 */     A(this, "creating interface to the '" + this.ć + "', package version " + K());
/*     */ 
/*  79 */     this.ā = 26;
/*  80 */     this.Þ = 159;
/*  81 */     this.Ė = new _D(null);
/*     */ 
/*  84 */     this.ҭ = new _A(new int[] { 5, 13, 2 }, "Get Firmware Version", new D()
/*     */     {
/*     */       public void A(w._B param_B) throws Z
/*     */       {
/*  88 */         int[] arrayOfInt1 = param_B.d();
/*  89 */         int[] arrayOfInt2 = new int[50];
/*  90 */         System.arraycopy(arrayOfInt1, 6, arrayOfInt2, 0, arrayOfInt1.length - 9);
/*  91 */         h.this.Ă = O._B.E(arrayOfInt2);
/*     */       }
/*     */     }
/*     */     , null);
/*     */ 
/*  94 */     this.ѳ = new _A(new int[] { 5, 11, 2, 0, 0, 0, 0, 132, 106, 232, 115, 0 }, "Get Serial Number", new D()
/*     */     {
/*     */       public void A(w._B param_B) throws Z {
/*  97 */         int[] arrayOfInt1 = param_B.d();
/*  98 */         int[] arrayOfInt2 = new int[50];
/*  99 */         System.arraycopy(arrayOfInt1, 5, arrayOfInt2, 0, arrayOfInt1.length - 8);
/* 100 */         h.this.ă = O._B.E(arrayOfInt2);
/*     */       }
/*     */     }
/*     */     , null);
/*     */ 
/* 103 */     this.Ҷ = new _A(new int[] { 5, 32, 2, 0, 0, 0, 0 }, "Get Current Time", new D()
/*     */     {
/*     */       public void A(w._B param_B) throws Z {
/* 106 */         int[] arrayOfInt1 = param_B.d();
/* 107 */         int[] arrayOfInt2 = new int[50];
/* 108 */         System.arraycopy(arrayOfInt1, 5, arrayOfInt2, 0, 4);
/* 109 */         h.this.ѻ = O._B.E(arrayOfInt2);
/*     */ 
/* 112 */         long l = O._B.A(O._B.K(arrayOfInt2[3]), O._B.K(arrayOfInt2[2]), O._B.K(arrayOfInt2[1]), O._B.K(arrayOfInt2[0]));
/*     */ 
/* 117 */         h.this.ĕ = new Date(l * 1000L);
/*     */       }
/*     */     }
/*     */     , null);
/*     */ 
/* 120 */     this.ө = new _A(new int[] { 5, 9, 2, 9, 0, 0, 0, 0 }, "Get Glucose Units", new D()
/*     */     {
/*     */       public void A(w._B param_B) throws Z {
/* 123 */         int[] arrayOfInt1 = param_B.d();
/* 124 */         int[] arrayOfInt2 = new int[4];
/* 125 */         System.arraycopy(arrayOfInt1, 5, arrayOfInt2, 0, arrayOfInt2.length);
/* 126 */         h.B(h.this, arrayOfInt2);
/*     */       }
/*     */     }
/*     */     , null);
/*     */ 
/* 129 */     this.Ӧ = new _A(new int[] { 5, 8, 2, 0, 0, 0, 0, 0 }, "Read Date Format", new D()
/*     */     {
/*     */       public void A(w._B param_B) throws Z {
/* 132 */         int[] arrayOfInt1 = param_B.d();
/* 133 */         int[] arrayOfInt2 = new int[4];
/* 134 */         System.arraycopy(arrayOfInt1, 5, arrayOfInt2, 0, arrayOfInt2.length);
/* 135 */         h.C(h.this, arrayOfInt2);
/*     */       }
/*     */     }
/*     */     , null);
/*     */ 
/* 139 */     this.ӣ = new _A(new int[] { 5, 31, 245, 1 }, "Read Glucose Record 501", null, null);
/*     */ 
/* 142 */     this.Ӣ = new _B(null);
/*     */ 
/* 144 */     this.ѵ = new _C(new D() {
/*     */       public void A(w._B param_B) throws Z {
/* 146 */         int[] arrayOfInt = param_B.d();
/* 147 */         h.A(h.this, arrayOfInt);
/*     */       }
/*     */     }
/*     */     , null);
/*     */ 
/* 151 */     this.ҹ = 10;
/*     */   }
/*     */ 
/*     */   Vector É()
/*     */   {
/* 162 */     Vector localVector = new Vector();
/*     */ 
/* 165 */     localVector.addElement(this.ҭ);
/* 166 */     localVector.addElement(this.ѳ);
/* 167 */     localVector.addElement(this.Ҷ);
/* 168 */     localVector.addElement(this.ө);
/* 169 */     localVector.addElement(this.Ӧ);
/*     */ 
/* 171 */     localVector.addElement(this.ѵ);
/*     */ 
/* 173 */     return localVector;
/*     */   }
/*     */ 
/*     */   void D(String paramString)
/*     */     throws IOException
/*     */   {
/* 185 */     super.D(paramString);
/* 186 */     Y().B(500);
/*     */   }
/*     */ 
/*     */   void C(v paramv)
/*     */     throws t, IOException
/*     */   {
/*     */     try
/*     */     {
/* 200 */       if (OSInfo.isMac())
/*     */       {
/* 203 */         this.ѳ.A();
/*     */       }
/* 205 */       else this.Ӣ.A();
/*     */     }
/*     */     catch (Z localZ)
/*     */     {
/* 209 */       throw new t("Got reply, but format is bad.");
/*     */     }
/*     */   }
/*     */ 
/*     */   void Ë()
/*     */     throws Z
/*     */   {
/* 226 */     A(this, "decodeCurrentSettings: *** CURRENT SETTINGS *** (none)");
/*     */   }
/*     */ 
/*     */   private class _D extends O._A
/*     */   {
/*     */     private static final int q = 1;
/*     */     private static final int l = 2;
/*     */     private static final int o = 3;
/*     */     static final int n = 3;
/*     */     private static final int r = 250;
/*     */     private static final int m = 50;
/*     */     private static final char p = ' ';
/*     */ 
/*     */     private _D()
/*     */     {
/* 699 */       super(250);
/*     */     }
/*     */ 
/*     */     void A()
/*     */     {
/* 709 */       h.this.Î = new CA(h.this.Þ, 1, A(h.this.Ă), A(h.this.ă), A(h.this.ѻ));
/*     */ 
/* 712 */       O.A(this, "createSnapshot: creating snapshot ");
/*     */ 
/* 716 */       h.this.Î.A(1, h.E(h.this));
/* 717 */       h.this.Î.A(2, h.C(h.this));
/*     */ 
/* 721 */       h.this.Î.A(3, h.B(h.this));
/*     */     }
/*     */ 
/*     */     String A(String paramString)
/*     */     {
/* 731 */       Contract.preNonNull(paramString);
/* 732 */       StringBuffer localStringBuffer = new StringBuffer(paramString);
/*     */ 
/* 734 */       for (int i = paramString.length(); i < 50; i++) {
/* 735 */         localStringBuffer.append(' ');
/*     */       }
/*     */ 
/* 738 */       return localStringBuffer.toString();
/*     */     }
/*     */   }
/*     */ 
/*     */   private class _C extends h._A
/*     */   {
/*     */     private static final int Ó = 500;
/*     */     private static final int Ò = 16;
/*     */ 
/*     */     private _C(D arg2)
/*     */     {
/* 604 */       super(new int[0], "Read Glucose History", localD, null);
/*     */ 
/* 607 */       this.Ë = (8000 + h.A(h.this).Ë);
/*     */     }
/*     */ 
/*     */     protected void c()
/*     */       throws t, Z
/*     */     {
/* 623 */       h.A(h.this).A();
/* 624 */       int[] arrayOfInt1 = h.A(h.this).d();
/* 625 */       if ((arrayOfInt1[3] != 5) || (arrayOfInt1[4] != 15)) {
/* 626 */         throw new t("Expected RM1, RM2 to be 0x05, 0x0F -- got " + arrayOfInt1[3] + ", " + arrayOfInt1[4]);
/*     */       }
/*     */ 
/* 630 */       int i = (arrayOfInt1[6] << 8) + arrayOfInt1[5];
/*     */ 
/* 632 */       int j = i * 16 + h.A(h.this).Ë;
/* 633 */       h.this.ҧ += j - this.Ë;
/*     */ 
/* 635 */       h.this.A(h.this.ѿ, h.this.ҧ);
/* 636 */       this.Ë = j;
/*     */ 
/* 638 */       int[] arrayOfInt2 = new int[16 * i];
/* 639 */       h._A local_A = new h._A(h.this, new int[] { 5, 31, 0, 0 }, "Read Glucose Record", null, null);
/*     */ 
/* 641 */       for (int k = 0; k < i; k++) {
/* 642 */         int m = (k & 0xFF00) >> 8;
/* 643 */         int n = k & 0xFF;
/*     */ 
/* 645 */         local_A.Ï[2] = n;
/* 646 */         local_A.Ï[3] = m;
/*     */ 
/* 648 */         int[] arrayOfInt3 = null;
/*     */         try {
/* 650 */           local_A.A();
/* 651 */           arrayOfInt3 = local_A.d();
/*     */         } catch (Exception localException) {
/* 653 */           throw new t("Exception when executing " + local_A.b() + ". " + localException);
/*     */         }
/*     */ 
/* 656 */         if (arrayOfInt3[1] != 16) {
/* 657 */           throw new t("Invalid record length. Expected 16 bytes, got " + arrayOfInt3[1]);
/*     */         }
/*     */ 
/* 661 */         System.arraycopy(arrayOfInt3, 0, arrayOfInt2, k * 16, 16);
/*     */       }
/*     */ 
/* 664 */       B(arrayOfInt2);
/*     */ 
/* 666 */       if ((!h.this.isHaltRequested()) && 
/* 667 */         (this.É != null))
/* 668 */         this.É.A(this);
/*     */     }
/*     */   }
/*     */ 
/*     */   private class _B extends h._A
/*     */   {
/*     */     private _B()
/*     */     {
/* 524 */       super(new int[0], "Disconnect Command", null, null);
/* 525 */       this.Ç = 0;
/*     */     }
/*     */ 
/*     */     protected void a()
/*     */     {
/* 535 */       h.this.Y().A();
/*     */     }
/*     */ 
/*     */     void c()
/*     */       throws t, Z
/*     */     {
/* 549 */       super.c();
/* 550 */       h.B(h.this, 0);
/*     */     }
/*     */ 
/*     */     void i()
/*     */       throws t, IOException
/*     */     {
/* 560 */       h.this.Y().E();
/*     */ 
/* 563 */       A(8, this.Ï);
/*     */     }
/*     */ 
/*     */     int[] j()
/*     */       throws t, IOException
/*     */     {
/* 576 */       return null;
/*     */     }
/*     */ 
/*     */     void g()
/*     */       throws IOException
/*     */     {
/*     */     }
/*     */   }
/*     */ 
/*     */   private class _A extends w._B
/*     */   {
/*     */     protected static final int Î = 16;
/*     */     private static final int Ê = 3;
/*     */     protected static final int Ñ = 65280;
/*     */     protected static final int Ì = 255;
/*     */     protected static final int Ð = 5;
/*     */     protected static final byte Í = 4;
/* 244 */     protected int[] Ï = new int[0];
/*     */     protected int Ë;
/*     */ 
/*     */     private _A(int[] paramString, String paramD, D arg4)
/*     */     {
/* 255 */       super(paramD);
/*     */ 
/* 257 */       this.Ï = paramString;
/* 258 */       this.Ë = 16;
/*     */       Object localObject;
/* 260 */       this.É = localObject;
/*     */ 
/* 262 */       this.Ç = 3;
/*     */     }
/*     */ 
/*     */     public String toString()
/*     */     {
/* 270 */       return O._B.D(this.Ï) + " ( " + this.A + ")";
/*     */     }
/*     */ 
/*     */     protected void a()
/*     */     {
/* 278 */       h.this.Y().A();
/*     */       try
/*     */       {
/* 282 */         h.D(h.this).A();
/*     */       }
/*     */       catch (Exception localException)
/*     */       {
/*     */       }
/*     */     }
/*     */ 
/*     */     final int e()
/*     */     {
/* 294 */       return this.Ë;
/*     */     }
/*     */ 
/*     */     void c()
/*     */       throws t, Z
/*     */     {
/*     */       try
/*     */       {
/* 307 */         if (h.this.T() != 7) {
/* 308 */           h.this.B(4);
/*     */         }
/* 310 */         i();
/* 311 */         h();
/*     */ 
/* 313 */         h.this.B(5);
/* 314 */         int[] arrayOfInt = j();
/* 315 */         B(arrayOfInt);
/*     */ 
/* 317 */         g();
/*     */ 
/* 319 */         O._B.G(20);
/*     */       } catch (IOException localIOException) {
/* 321 */         O.E(this, "cmd " + this + " encountered io exception; exception = " + localIOException);
/* 322 */         throw new t("IO Exception occured while trying tocommunicate with meter");
/*     */       }
/*     */ 
/* 326 */       if ((!h.this.isHaltRequested()) && 
/* 327 */         (this.É != null)) {
/* 328 */         this.É.A(this);
/*     */       }
/*     */ 
/* 333 */       h.this.ѿ += this.Ë;
/* 334 */       h.this.Ì();
/*     */     }
/*     */ 
/*     */     void i()
/*     */       throws t, IOException
/*     */     {
/* 345 */       h.this.Y().E();
/*     */ 
/* 347 */       O.A(this, "sendCommand: sending cmd " + this);
/*     */ 
/* 349 */       A(0, this.Ï);
/*     */     }
/*     */ 
/*     */     int[] f()
/*     */       throws t, IOException
/*     */     {
/* 361 */       int i = h.this.Y().O();
/*     */ 
/* 364 */       while (i != 2) {
/* 365 */         if (i == -1) {
/* 366 */           throw new t("Couldn't find STX -- nothing on the line");
/*     */         }
/*     */ 
/* 369 */         i = h.this.Y().O();
/*     */       }
/*     */ 
/* 372 */       int j = h.this.Y().O();
/* 373 */       if (j < 5) {
/* 374 */         throw new t("Packet length too short - " + j + ". Expected at least " + 5);
/*     */       }
/*     */ 
/* 378 */       int[] arrayOfInt = new int[j];
/* 379 */       arrayOfInt[0] = i;
/* 380 */       arrayOfInt[1] = j;
/* 381 */       h.this.Y().A(arrayOfInt, 2, arrayOfInt.length - 2);
/*     */ 
/* 383 */       C(arrayOfInt);
/*     */ 
/* 385 */       return arrayOfInt;
/*     */     }
/*     */ 
/*     */     void A(int paramInt, int[] paramArrayOfInt)
/*     */       throws IOException
/*     */     {
/* 398 */       int[] arrayOfInt = new int[paramArrayOfInt.length + 6];
/* 399 */       arrayOfInt[0] = 2;
/* 400 */       arrayOfInt[1] = arrayOfInt.length;
/*     */ 
/* 403 */       paramInt |= h.F(h.this);
/*     */ 
/* 405 */       arrayOfInt[2] = paramInt;
/* 406 */       System.arraycopy(paramArrayOfInt, 0, arrayOfInt, 3, paramArrayOfInt.length);
/* 407 */       arrayOfInt[(arrayOfInt.length - 3)] = 3;
/*     */ 
/* 410 */       int i = O._B.C(arrayOfInt, 0, arrayOfInt.length - 2);
/* 411 */       arrayOfInt[(arrayOfInt.length - 2)] = (i & 0xFF);
/* 412 */       arrayOfInt[(arrayOfInt.length - 1)] = ((i & 0xFF00) >> 8);
/*     */ 
/* 414 */       h.this.Y().A(arrayOfInt);
/*     */     }
/*     */ 
/*     */     void C(int[] paramArrayOfInt)
/*     */       throws t
/*     */     {
/* 425 */       int i = (paramArrayOfInt[(paramArrayOfInt.length - 1)] << 8) + paramArrayOfInt[(paramArrayOfInt.length - 2)];
/* 426 */       int j = O._B.C(paramArrayOfInt, 0, paramArrayOfInt.length - 2);
/*     */ 
/* 428 */       if (i != j)
/* 429 */         throw new t("Bad CRC: " + i + ", should be " + j);
/*     */     }
/*     */ 
/*     */     int[] j()
/*     */       throws t, IOException
/*     */     {
/* 444 */       h.this.Y().E();
/*     */ 
/* 446 */       O.A(this, "readReply: reading reply to cmd " + this);
/*     */ 
/* 448 */       int[] arrayOfInt = f();
/* 449 */       if ((arrayOfInt == null) || (arrayOfInt.length < 5)) {
/* 450 */         throw new t("Invalid packet " + O._B.D(arrayOfInt));
/*     */       }
/*     */ 
/* 459 */       int i = arrayOfInt[2];
/*     */ 
/* 461 */       int j = i & 0x1;
/* 462 */       int k = h.F(h.this) >> 1;
/*     */ 
/* 464 */       if (j != k) {
/* 465 */         throw new t("Invalid S number received: got " + j + ", expected " + k);
/*     */       }
/*     */ 
/* 470 */       h.A(h.this, 2);
/* 471 */       return arrayOfInt;
/*     */     }
/*     */ 
/*     */     void h()
/*     */       throws t, IOException
/*     */     {
/* 482 */       h.this.Y().E();
/*     */ 
/* 484 */       O.A(this, "readACK: reading ack to cmd " + this);
/*     */ 
/* 486 */       int[] arrayOfInt = f();
/* 487 */       int i = arrayOfInt[2];
/*     */ 
/* 489 */       if ((i & 0x4) != 4) {
/* 490 */         throw new t("NOT ACK");
/*     */       }
/*     */ 
/* 498 */       h.A(h.this, 1);
/*     */     }
/*     */ 
/*     */     void g()
/*     */       throws IOException, t
/*     */     {
/* 508 */       h.this.Y().E();
/*     */ 
/* 510 */       O.A(this, "sendACK: sending ack for cmd " + this);
/* 511 */       A(4, new int[0]);
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.A.h
 * JD-Core Version:    0.6.0
 */