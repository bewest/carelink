/*     */ package minimed.ddms.A;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.Vector;
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ final class R extends w
/*     */ {
/*     */   static final int ӕ = 156;
/*     */   private static final String ӛ = "DM@\r";
/*     */   private static final String ә = "DMF\r";
/*     */   private static final String Ӛ = "DM?\r";
/*     */   private static final int Ӗ = 1500;
/*     */   private static final int ӗ = 5;
/*     */   private static final int ӝ = 100;
/*     */   private static final int Ӝ = 5;
/*  83 */   private int Ә = 5;
/*     */ 
/*     */   R()
/*     */   {
/*  91 */     this.ć = "LifeScan One Touch UltraSmart Meter";
/*  92 */     A(this, "creating interface to the '" + this.ć + "', package version " + K());
/*     */ 
/*  94 */     this.ā = 21;
/*  95 */     this.Þ = 156;
/*  96 */     this.Ė = new _B(null);
/*  97 */     this.Ҧ = 9;
/*     */ 
/* 100 */     this.Ҷ = new w._C(this, "DMF\r", "Read Real Time Clock", 50);
/*     */ 
/* 102 */     this.ҭ = new w._C(this, "DM?\r", "Read Firmware Version", 50);
/*     */ 
/* 104 */     this.ѳ = new w._C(this, "DM@\r", "Read Serial Number", 50);
/*     */ 
/* 108 */     this.ѵ = new _C();
/*     */   }
/*     */ 
/*     */   void D(String paramString)
/*     */     throws IOException
/*     */   {
/* 121 */     super.D(paramString);
/*     */ 
/* 123 */     Y().B(1500);
/* 124 */     Y().A(this.Ә);
/*     */   }
/*     */ 
/*     */   Vector É()
/*     */   {
/* 133 */     Vector localVector = new Vector();
/*     */ 
/* 137 */     localVector.addElement(this.Ҷ);
/* 138 */     localVector.addElement(this.ҭ);
/* 139 */     localVector.addElement(this.ѳ);
/* 140 */     localVector.addElement(this.ѵ);
/* 141 */     return localVector;
/*     */   }
/*     */ 
/*     */   void Ë()
/*     */     throws Z
/*     */   {
/*     */   }
/*     */ 
/*     */   int[] V(int[] paramArrayOfInt)
/*     */   {
/* 162 */     return _A.A(new _A(1), paramArrayOfInt);
/*     */   }
/*     */ 
/*     */   private class _B extends w._A
/*     */   {
/*     */     static final int ï = 1;
/*     */     static final int ç = 1;
/*     */     static final int è = 169;
/*     */ 
/*     */     private _B()
/*     */     {
/* 617 */       super();
/*     */     }
/*     */ 
/*     */     InputStream B()
/*     */       throws Z, IOException
/*     */     {
/* 643 */       R.this.Î = new CA(R.this.Þ, 1, B(R.this.Ă), B(R.this.ă), B(R.this.ѻ));
/*     */ 
/* 646 */       O.A(this, "createSnapshot: creating snapshot");
/*     */ 
/* 652 */       R.this.Î.A(1, R.this.ѵ.d());
/*     */ 
/* 655 */       int i = R.this.Î.D();
/* 656 */       O.A(this, "createSnapshot: wrote " + i + " bytes.");
/*     */ 
/* 658 */       if (i < 169) {
/* 659 */         throw new Z("Resulting snapshot size is invalid: " + i + "; must be at least " + 169 + " bytes.");
/*     */       }
/*     */ 
/* 662 */       return R.this.Î.E();
/*     */     }
/*     */   }
/*     */ 
/*     */   private class _A extends w._C
/*     */   {
/*     */     private static final int à = 10;
/*     */     private static final int Ü = 17;
/*     */     private static final int Û = 7;
/*     */     private static final int ß = 5;
/*     */     private static final String Ú = "HR";
/*     */     private int Þ;
/*     */     private boolean Ý;
/*     */ 
/*     */     _A(int arg2)
/*     */     {
/* 275 */       super("HR" + i, "Read HR Data-" + i, 17);
/* 276 */       Contract.pre(i > 0);
/* 277 */       this.Þ = i;
/* 278 */       this.Ý = false;
/*     */ 
/* 280 */       this.Ç = 10;
/*     */     }
/*     */ 
/*     */     void c()
/*     */       throws t, Z
/*     */     {
/* 293 */       if (R.this.T() != 7) {
/* 294 */         R.this.B(4);
/*     */       }
/* 296 */       m();
/*     */ 
/* 299 */       if (!R.this.isHaltRequested())
/*     */       {
/* 301 */         R.this.B(5);
/* 302 */         r();
/*     */       }
/*     */ 
/* 306 */       R.this.Ì();
/*     */     }
/*     */ 
/*     */     void m()
/*     */       throws t
/*     */     {
/*     */       try
/*     */       {
/* 316 */         o();
/*     */       } catch (t localt) {
/* 318 */         E("sendCommand");
/* 319 */         throw localt;
/*     */       }
/*     */     }
/*     */ 
/*     */     private void o()
/*     */       throws t
/*     */     {
/* 330 */       R.this.Y().E();
/*     */ 
/* 332 */       O.A(this, "sendCommandIO: sending cmd " + k() + " (" + b() + ")");
/*     */       try
/*     */       {
/* 336 */         R.this.Y().A(u());
/*     */       } catch (IOException localIOException) {
/* 338 */         throw new t("sendCommandIO: ERROR - an IOException  has occurred processing cmd " + k() + " (" + b() + ")");
/*     */       }
/*     */     }
/*     */ 
/*     */     boolean n()
/*     */     {
/* 350 */       return this.Ý;
/*     */     }
/*     */ 
/*     */     private void r()
/*     */       throws t, Z
/*     */     {
/*     */       try
/*     */       {
/* 361 */         q();
/*     */       } catch (t localt) {
/* 363 */         E("readDeviceData");
/* 364 */         throw localt;
/*     */       } catch (Z localZ) {
/* 366 */         E("readDeviceData");
/* 367 */         throw localZ;
/*     */       }
/*     */     }
/*     */ 
/*     */     private void q() throws t, Z
/*     */     {
/* 379 */       R.this.Y().E();
/*     */ 
/* 381 */       O.A(this, "readDeviceDataIO: reading reply to cmd " + k() + " (" + b() + ")");
/*     */       int[] arrayOfInt;
/*     */       try
/*     */       {
/* 387 */         arrayOfInt = R.this.Y().I();
/*     */       } catch (IOException localIOException) {
/* 389 */         throw new t("readDeviceDataIO: ERROR - an IOException  has occurred processing cmd " + k() + " (" + b() + ")");
/*     */       }
/*     */ 
/* 395 */       B(E(arrayOfInt));
/*     */ 
/* 398 */       t();
/* 399 */       s();
/* 400 */       p();
/*     */ 
/* 403 */       this.Ý = (d().length == 7);
/*     */ 
/* 405 */       O.A(this, "readDeviceDataIO: cmd " + k() + " (" + b() + ") returned " + d().length + " data bytes: " + "<" + O._B.D(d()) + ">");
/*     */     }
/*     */ 
/*     */     private void E(String paramString)
/*     */     {
/* 416 */       R.A(R.this, Math.min(R.A(R.this) + 5, 100));
/* 417 */       O.A(this, paramString + ": increasing m_ioDelay to " + R.A(R.this));
/* 418 */       R.this.Y().A(R.A(R.this));
/*     */     }
/*     */ 
/*     */     private int[] E(int[] paramArrayOfInt)
/*     */     {
/* 430 */       Contract.preNonNull(paramArrayOfInt);
/* 431 */       int[] arrayOfInt1 = new int[paramArrayOfInt.length];
/*     */ 
/* 436 */       int i = 0;
/* 437 */       for (int j = 0; j < paramArrayOfInt.length; j++) {
/* 438 */         arrayOfInt1[(i++)] = paramArrayOfInt[j];
/* 439 */         if ((j <= 1) || (j >= paramArrayOfInt.length - 4))
/*     */           continue;
/* 441 */         if ((paramArrayOfInt[j] == 16) && (paramArrayOfInt[(j + 1)] == 16)) {
/* 442 */           j++;
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 448 */       int[] arrayOfInt2 = new int[i];
/* 449 */       System.arraycopy(arrayOfInt1, 0, arrayOfInt2, 0, arrayOfInt2.length);
/* 450 */       return arrayOfInt2;
/*     */     }
/*     */ 
/*     */     private int[] D(int[] paramArrayOfInt)
/*     */     {
/* 462 */       Contract.preNonNull(paramArrayOfInt);
/* 463 */       int[] arrayOfInt1 = new int[paramArrayOfInt.length * 2];
/*     */ 
/* 466 */       int i = 0;
/* 467 */       for (int j = 0; j < paramArrayOfInt.length; j++)
/*     */       {
/* 469 */         arrayOfInt1[(i++)] = paramArrayOfInt[j];
/*     */ 
/* 471 */         if (paramArrayOfInt[j] == 16) {
/* 472 */           arrayOfInt1[(i++)] = paramArrayOfInt[j];
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 477 */       int[] arrayOfInt2 = new int[i];
/* 478 */       System.arraycopy(arrayOfInt1, 0, arrayOfInt2, 0, arrayOfInt2.length);
/* 479 */       return arrayOfInt2;
/*     */     }
/*     */ 
/*     */     private int[] u()
/*     */     {
/* 489 */       int i = this.Þ - 1;
/* 490 */       int[] arrayOfInt1 = { k().charAt(0), k().charAt(1), O._B.J(i), O._B.K(i) };
/*     */ 
/* 498 */       int j = F(arrayOfInt1);
/*     */ 
/* 501 */       int[] arrayOfInt2 = D(arrayOfInt1);
/*     */ 
/* 504 */       int[] arrayOfInt3 = { 16, 2 };
/* 505 */       int[] arrayOfInt4 = { 16, 3, j };
/* 506 */       int[] arrayOfInt5 = new int[0];
/* 507 */       arrayOfInt5 = O._B.A(arrayOfInt5, arrayOfInt3);
/* 508 */       arrayOfInt5 = O._B.A(arrayOfInt5, arrayOfInt2);
/* 509 */       arrayOfInt5 = O._B.A(arrayOfInt5, arrayOfInt4);
/* 510 */       return arrayOfInt5;
/*     */     }
/*     */ 
/*     */     private void t()
/*     */       throws Z
/*     */     {
/* 520 */       int[] arrayOfInt = d();
/* 521 */       if ((arrayOfInt.length != 17) && (arrayOfInt.length != 7))
/*     */       {
/* 523 */         throw new Z("verifyLength: ERROR - incorrect data length for HR command " + k() + " (" + b() + "); reply=<" + O._B.D(arrayOfInt) + ">");
/*     */       }
/*     */     }
/*     */ 
/*     */     private void p()
/*     */       throws t
/*     */     {
/* 538 */       int[] arrayOfInt = d();
/*     */ 
/* 543 */       int i = (arrayOfInt[0] == 16) && (arrayOfInt[1] == 2) && (arrayOfInt[(arrayOfInt.length - 3)] == 16) && (arrayOfInt[(arrayOfInt.length - 2)] == 3) ? 1 : 0;
/*     */ 
/* 549 */       if (i == 0)
/* 550 */         throw new t("verifyFrame: ERROR - bad frame read for HR command " + k() + " (" + b() + "); reply=<" + O._B.D(arrayOfInt) + ">" + (arrayOfInt[0] == 16 ? "" : "; reply[0] != DLE") + (arrayOfInt[1] == 2 ? "" : "; reply[1] != STX") + (arrayOfInt[(arrayOfInt.length - 3)] == 16 ? "" : "; reply[reply.length - 3] != DLE") + (arrayOfInt[(arrayOfInt.length - 2)] == 3 ? "" : "; reply[reply.length - 2] != EXT"));
/*     */     }
/*     */ 
/*     */     private int F(int[] paramArrayOfInt)
/*     */     {
/* 568 */       Contract.preNonNull(paramArrayOfInt);
/*     */ 
/* 570 */       int[] arrayOfInt = O._B.A(paramArrayOfInt, new int[1]);
/* 571 */       return O._B.E(arrayOfInt, 0, arrayOfInt.length);
/*     */     }
/*     */ 
/*     */     private int s()
/*     */       throws Z
/*     */     {
/* 582 */       int[] arrayOfInt1 = d();
/*     */ 
/* 587 */       int i = arrayOfInt1.length - 1;
/* 588 */       int j = arrayOfInt1[i];
/*     */ 
/* 591 */       int[] arrayOfInt2 = new int[arrayOfInt1.length - 5 + 1];
/*     */ 
/* 594 */       System.arraycopy(arrayOfInt1, 2, arrayOfInt2, 0, arrayOfInt2.length - 1);
/*     */ 
/* 597 */       arrayOfInt2[(arrayOfInt2.length - 1)] = 0;
/*     */ 
/* 599 */       int k = O._B.E(arrayOfInt2, 0, arrayOfInt2.length) & 0xFF;
/*     */ 
/* 601 */       if (j != k) {
/* 602 */         throw new Z("verifyCRC: cmd " + k() + " (" + b() + ")" + " resulted in bad CRC value of " + O._B.H(j) + " (expected " + O._B.H(k) + ") " + "(byte data = " + "<" + O._B.D(arrayOfInt1) + ">)");
/*     */       }
/*     */ 
/* 608 */       return j;
/*     */     }
/*     */   }
/*     */ 
/*     */   private class _C extends w._C
/*     */   {
/*     */     private static final int â = 3064;
/*     */     private static final int á = 52088;
/*     */ 
/*     */     _C()
/*     */     {
/* 187 */       super("HRHR Procedure", "Read All HR Data", 52088);
/*     */     }
/*     */ 
/*     */     void A()
/*     */       throws t, Z
/*     */     {
/* 203 */       int i = 0;
/* 204 */       int[] arrayOfInt = new int[0];
/*     */ 
/* 206 */       for (int j = 1; (j <= 3064) && (i == 0); j++)
/*     */       {
/* 208 */         R._A local_A = new R._A(R.this, j);
/* 209 */         local_A.A();
/*     */ 
/* 212 */         arrayOfInt = O._B.A(arrayOfInt, local_A.d());
/* 213 */         R.this.N(local_A.d().length);
/*     */ 
/* 216 */         i = (local_A.n()) || (R.this.isHaltRequested()) ? 1 : 0;
/*     */       }
/*     */ 
/* 220 */       B(arrayOfInt);
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.A.R
 * JD-Core Version:    0.6.0
 */