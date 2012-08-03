/*     */ package minimed.ddms.A;
/*     */ 
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ class b extends u
/*     */ {
/*     */   public static final int ɞ = 116;
/*     */   static final String ʈ = "522";
/*     */   static final String ʇ = "722";
/*     */   static final String ʸ = "522K";
/*     */   static final String ʷ = "722K";
/*     */   private static final int ˑ = 205;
/*     */   private static final int ʨ = 154;
/*     */   private static final int ʳ = 156;
/*     */   private static final int ˀ = 155;
/*     */   private static final int Έ = 153;
/*     */   private static final int ʼ = 40;
/*     */   private static final int ː = 168;
/*     */   private static final int ʱ = 169;
/*     */   private static final int ʴ = 0;
/*     */   private static final int ˢ = 1;
/*     */   private static final int ˤ = 2;
/*     */   private static final int ʿ = 4;
/*     */   private static final int ʤ = 6;
/*     */   private static final int ʰ = 7;
/*     */   private static final int ʧ = 9;
/*     */   private static final int ʾ = 14;
/*     */   private static final int ʶ = 16;
/*     */   private static final int ʲ = 17;
/*     */   private static final int ʵ = 19;
/*     */   private static final int ʦ = 22;
/*     */   private static final int Ά = 23;
/*     */   static final int ǁ = 64;
/*     */   private static final int ˁ = 32;
/*     */   private static final int ʻ = 1024;
/*     */   private static final int ˡ = 32;
/*     */   private static final int ʥ = 2048;
/*     */   private static final int ˣ = 0;
/*     */   private static final int ͺ = 2;
/*     */   private static final double ˠ = 1000.0D;
/* 125 */   private static final String[] ʽ = { "UNSET", "mg/dL", "mmol/L" };
/*     */ 
/*     */   b(int paramInt1, long paramLong1, long paramLong2, String paramString, int paramInt2)
/*     */   {
/* 148 */     super(paramInt1, paramLong1, paramString, paramInt2);
/* 149 */     this.Ñ = paramLong2;
/*     */ 
/* 154 */     this.ǒ = new .A._C(this, 40, "Write Glucose History Timestamp", 0);
/*     */ 
/* 156 */     this.Ŕ = new _A(154, "Read Glucose History", 1024, 32);
/*     */ 
/* 158 */     this.ǃ = new _A(155, "Read Isig History", 2048, 32);
/*     */ 
/* 161 */     this.ƅ = new .A._C(this, 205, "Read Current Glucose History Page Number");
/*     */ 
/* 164 */     this.Ĳ = new .A._C(this, 156, "Read Calibration Factor");
/*     */ 
/* 166 */     this.ƽ = new .A._C(this, 153, "Read Sensor Settings");
/*     */ 
/* 169 */     this.ƀ = new _C(168, "Filter Glucose History Data", (.A._B)this.Ŕ);
/*     */ 
/* 172 */     this.Ɓ = new _C(169, "Filter Isig History Data", (.A._B)this.ǃ);
/*     */ 
/* 176 */     this.Ė = new _B();
/*     */   }
/*     */ 
/*     */   b(int paramInt, long paramLong1, long paramLong2)
/*     */   {
/* 193 */     this(paramInt, paramLong1, paramLong2, "MiniMed MMT-522/722 (Paradigm2) Insulin Pump", 116);
/*     */   }
/*     */ 
/*     */   static boolean G(String paramString)
/*     */   {
/* 208 */     return ("522".equals(paramString)) || ("722".equals(paramString)) || ("522K".equals(paramString)) || ("722K".equals(paramString));
/*     */   }
/*     */ 
/*     */   void A(M._A param_A)
/*     */     throws Z
/*     */   {
/* 221 */     switch (param_A.X)
/*     */     {
/*     */     case 205:
/* 227 */       R(param_A.a);
/* 228 */       break;
/*     */     case 153:
/* 231 */       S(param_A.a);
/* 232 */       break;
/*     */     case 156:
/* 235 */       T(param_A.a);
/* 236 */       break;
/*     */     default:
/* 242 */       super.A(param_A);
/*     */     }
/*     */   }
/*     */ 
/*     */   void R(int[] paramArrayOfInt)
/*     */   {
/* 256 */     Contract.pre(paramArrayOfInt != null);
/* 257 */     Contract.pre(paramArrayOfInt.length == 64);
/*     */ 
/* 266 */     this.ł = (int)O._B.A(paramArrayOfInt[0], paramArrayOfInt[1], paramArrayOfInt[2], paramArrayOfInt[3]);
/*     */ 
/* 268 */     Contract.invariant(this.ł >= 0);
/*     */ 
/* 270 */     int i = paramArrayOfInt[5];
/* 271 */     int j = paramArrayOfInt[7];
/*     */ 
/* 274 */     int k = this.ł - (int)this.Ñ + 1;
/*     */ 
/* 277 */     if ((k <= 0) || (k > i))
/*     */     {
/* 279 */       k = i;
/*     */     }
/*     */ 
/* 283 */     int m = this.ł - (int)this.Ñ + 1;
/*     */ 
/* 286 */     if ((m <= 0) || (m > j)) {
/* 287 */       m = j;
/*     */     }
/*     */ 
/* 300 */     int n = this.ł;
/*     */ 
/* 303 */     int i1 = this.ł - k + 1;
/*     */ 
/* 305 */     Contract.invariant(i1 >= 0);
/* 306 */     int i2 = this.ł - m + 1;
/*     */ 
/* 308 */     Contract.invariant(i2 >= 0);
/*     */ 
/* 311 */     this.Ŕ.U = new y(i1, n, n, "Glucose Page Range");
/*     */ 
/* 313 */     this.ǃ.U = new y(i2, n, n, "Isig Page Range");
/*     */ 
/* 317 */     this.Ŕ.Q = k;
/* 318 */     this.ǃ.Q = m;
/* 319 */     this.Ŕ.C();
/* 320 */     this.ǃ.C();
/* 321 */     h();
/*     */ 
/* 323 */     A(this, "decodeCurrentGlucoseHistoryPageNumber: current glucose page number (absolute) in pump = " + this.ł + ", available Glucose pages = " + i + ", available Isig pages = " + j + ", glucose pages to read = " + k + ", isig pages to read = " + m);
/*     */   }
/*     */ 
/*     */   private void T(int[] paramArrayOfInt)
/*     */   {
/* 341 */     Contract.pre(paramArrayOfInt != null);
/* 342 */     Contract.pre(paramArrayOfInt.length == 64);
/*     */ 
/* 347 */     int i = O._B.A(paramArrayOfInt[0], paramArrayOfInt[1]);
/* 348 */     this.ƨ = (i / 1000.0D);
/*     */ 
/* 350 */     A(this, "decodeCalibrationFactor: factor=" + this.ƨ);
/*     */   }
/*     */ 
/*     */   private void S(int[] paramArrayOfInt)
/*     */     throws Z
/*     */   {
/* 364 */     Contract.pre(paramArrayOfInt != null);
/* 365 */     Contract.pre(paramArrayOfInt.length == 64);
/*     */ 
/* 369 */     this.ŧ = O._B.A(paramArrayOfInt[0], "Sensor Enable");
/*     */ 
/* 374 */     this.ư = paramArrayOfInt[22];
/* 375 */     O._B.A(this.ư, 0, 2, "BG Units");
/*     */ 
/* 378 */     this.Ƃ = O._B.A(paramArrayOfInt[1], "High Glucose Limit Enable");
/*     */ 
/* 383 */     int i = O._B.A(paramArrayOfInt[2], paramArrayOfInt[3]);
/*     */ 
/* 386 */     if (this.ư == 2)
/*     */     {
/* 388 */       this.Ǆ = O._B.B(i);
/*     */     }
/* 390 */     else this.Ǆ = i;
/*     */ 
/* 394 */     this.ţ = O._B.A(paramArrayOfInt[4], paramArrayOfInt[5]);
/*     */ 
/* 399 */     this.Ǖ = O._B.A(paramArrayOfInt[6], "Low Glucose Limit Enable");
/*     */ 
/* 404 */     i = O._B.A(paramArrayOfInt[7], paramArrayOfInt[8]);
/*     */ 
/* 407 */     if (this.ư == 2)
/*     */     {
/* 409 */       this.Ĵ = O._B.B(i);
/*     */     }
/* 411 */     else this.Ĵ = i;
/*     */ 
/* 415 */     this.ž = O._B.A(paramArrayOfInt[9], paramArrayOfInt[10]);
/*     */ 
/* 420 */     this.İ = O._B.A(paramArrayOfInt[14], paramArrayOfInt[15]);
/*     */ 
/* 425 */     this.ƚ = O._B.A(paramArrayOfInt[16], "Calibration Reminder Enable");
/*     */ 
/* 430 */     this.Ņ = O._B.A(paramArrayOfInt[17], paramArrayOfInt[18]);
/*     */ 
/* 435 */     this.ņ = O._B.A(paramArrayOfInt[19], paramArrayOfInt[20], paramArrayOfInt[21]);
/*     */ 
/* 441 */     this.Ĺ = O._B.A(paramArrayOfInt[23], paramArrayOfInt[24]);
/*     */ 
/* 445 */     A(this, "decodeSensorSettings: m_alarmSnoozeTime = " + this.İ);
/* 446 */     A(this, "decodeSensorSettings: m_calibrationReminderEnable = " + this.ƚ);
/*     */ 
/* 448 */     A(this, "decodeSensorSettings: m_calibrationReminderTime = " + this.Ņ);
/*     */ 
/* 450 */     A(this, "decodeSensorSettings: m_highGlucoseLimitEnable = " + this.Ƃ);
/*     */ 
/* 452 */     A(this, "decodeSensorSettings: m_highGlucoseLimitValue = " + this.Ǆ);
/* 453 */     A(this, "decodeSensorSettings: m_highGlucoseSnoozeTime = " + this.ţ);
/* 454 */     A(this, "decodeSensorSettings: m_lowGlucoseLimitEnable = " + this.Ǖ);
/* 455 */     A(this, "decodeSensorSettings: m_lowGlucoseLimitValue = " + this.Ĵ);
/* 456 */     A(this, "decodeSensorSettings: m_lowGlucoseSnoozeTime = " + this.ž);
/* 457 */     A(this, "decodeSensorSettings: m_missedDataTime = " + this.Ĺ);
/* 458 */     A(this, "decodeSensorSettings: m_sensorBgUnits = " + this.ư + " (" + ʽ[this.ư] + ")");
/*     */ 
/* 460 */     A(this, "decodeSensorSettings: m_sensorEnable = " + this.ŧ);
/* 461 */     A(this, "decodeSensorSettings: m_sensorTransmitterId = " + this.ņ);
/*     */   }
/*     */ 
/*     */   class _B extends u._A
/*     */   {
/*     */     private static final int Ý = 2810;
/*     */     private static final int Ù = 32;
/*     */     private static final int Ú = 33;
/*     */     private static final int Ü = 10000;
/*     */     private static final int Û = 35;
/*     */     private static final int Þ = 36;
/*     */ 
/*     */     _B(int arg2)
/*     */     {
/* 714 */       super(i);
/*     */     }
/*     */ 
/*     */     _B()
/*     */     {
/* 722 */       this(2810);
/*     */     }
/*     */ 
/*     */     void A()
/*     */     {
/* 732 */       super.A();
/*     */ 
/* 737 */       this.A.Î.A(32, this.A.ƅ.a);
/*     */ 
/* 741 */       this.A.Î.A(33, this.A.Ŕ.a);
/*     */ 
/* 745 */       this.A.Î.A(10000, this.A.ǃ.a);
/*     */ 
/* 748 */       this.A.Î.A(35, this.A.Ĳ.a);
/*     */ 
/* 752 */       this.A.Î.A(36, this.A.ƽ.a);
/*     */     }
/*     */   }
/*     */ 
/*     */   class _C extends .A._C
/*     */   {
/*     */     private static final int f = 31;
/*     */     private static final int e = 12;
/*     */     private static final int c = 10;
/*     */     private static final int d = 8;
/*     */     .A._B b;
/*     */ 
/*     */     _C(int paramString, String param_B, .A._B arg4)
/*     */     {
/* 573 */       super(paramString, param_B, 8);
/* 574 */       this.E = 64;
/* 575 */       this.Q = 1;
/*     */       Object localObject;
/* 576 */       this.b = localObject;
/*     */     }
/*     */ 
/*     */     void A()
/*     */       throws t
/*     */     {
/* 591 */       Contract.preNonNull(this.R);
/* 592 */       Contract.preNonNull(this.G);
/* 593 */       Contract.pre(!this.R.after(this.G));
/*     */ 
/* 595 */       int i = 0;
/* 596 */       int[] arrayOfInt = C(this.R);
/*     */ 
/* 598 */       this.O[(i++)] = arrayOfInt[0];
/* 599 */       this.O[(i++)] = arrayOfInt[1];
/* 600 */       this.O[(i++)] = arrayOfInt[2];
/* 601 */       this.O[(i++)] = arrayOfInt[3];
/*     */ 
/* 604 */       arrayOfInt = C(this.G);
/* 605 */       this.O[(i++)] = arrayOfInt[0];
/* 606 */       this.O[(i++)] = arrayOfInt[1];
/* 607 */       this.O[(i++)] = arrayOfInt[2];
/* 608 */       this.O[(i++)] = arrayOfInt[3];
/*     */ 
/* 610 */       O.A(this, "execute: filter data FROM " + this.O[2] + "/" + this.O[3] + "/" + O._B.A(this.O[0], this.O[1]) + " THROUGH " + this.O[6] + "/" + this.O[7] + "/" + O._B.A(this.O[4], this.O[5]));
/*     */       try
/*     */       {
/* 619 */         super.A();
/*     */       }
/*     */       catch (t localt)
/*     */       {
/* 623 */         Integer localInteger = localt.A();
/* 624 */         if ((localInteger != null) && (localInteger.intValue() == 10))
/*     */         {
/* 627 */           O.A(this, "execute: NO DATA AVAILABLE.");
/*     */         }
/*     */         else {
/* 630 */           throw localt;
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 639 */       int j = O._B.A(this.a[0], this.a[1]);
/* 640 */       int k = O._B.A(this.a[2], this.a[3]);
/*     */ 
/* 643 */       this.b.Q = Math.abs(j - k);
/* 644 */       this.b.C();
/* 645 */       b.this.h();
/*     */ 
/* 648 */       O.A(this, "execute: reading pages " + j + " through " + k);
/* 649 */       this.b.A(j, k);
/*     */     }
/*     */ 
/*     */     private int[] C(Date paramDate)
/*     */     {
/* 665 */       int[] arrayOfInt = new int[4];
/*     */ 
/* 667 */       Calendar localCalendar = Calendar.getInstance();
/* 668 */       localCalendar.setTime(paramDate);
/* 669 */       int i = localCalendar.get(5);
/* 670 */       int j = localCalendar.get(2) + 1;
/* 671 */       int k = localCalendar.get(1);
/*     */ 
/* 673 */       arrayOfInt[0] = O._B.J(k);
/* 674 */       arrayOfInt[1] = O._B.K(k);
/* 675 */       arrayOfInt[2] = j;
/* 676 */       arrayOfInt[3] = i;
/*     */ 
/* 679 */       Contract.post(j, 1, 12);
/* 680 */       Contract.post(i, 1, 31);
/* 681 */       return arrayOfInt;
/*     */     }
/*     */   }
/*     */ 
/*     */   class _A extends .A._B
/*     */   {
/*     */     _A(int paramString, String paramInt1, int paramInt2, int arg5)
/*     */     {
/* 488 */       super(paramString, paramInt1, paramInt2, i);
/*     */     }
/*     */ 
/*     */     _A(int arg2)
/*     */     {
/* 497 */       super(i);
/*     */     }
/*     */ 
/*     */     void B(int paramInt)
/*     */     {
/* 509 */       this.g.I = 4;
/*     */ 
/* 512 */       this.g.O[0] = O._B.K(paramInt >> 24);
/*     */ 
/* 514 */       this.g.O[1] = O._B.K(paramInt >> 16);
/*     */ 
/* 516 */       this.g.O[2] = O._B.K(paramInt >> 8);
/*     */ 
/* 519 */       this.g.O[3] = O._B.K(paramInt);
/*     */     }
/*     */ 
/*     */     void A()
/*     */       throws t, W
/*     */     {
/* 538 */       y localy = (y)this.U;
/* 539 */       A(localy.B(), localy.D());
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.A.b
 * JD-Core Version:    0.6.0
 */