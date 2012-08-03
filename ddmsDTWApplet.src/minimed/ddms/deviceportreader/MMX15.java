/*     */ package minimed.ddms.A;
/*     */ 
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ class u extends x
/*     */ {
/*     */   static final int ɞ = 115;
/*     */   static final String ʈ = "515";
/*     */   static final String ʇ = "715";
/*     */   private static final int ʖ = 192;
/*     */   private static final int ʢ = 159;
/*     */   private static final int ʔ = 157;
/*     */   private static final int ʒ = 193;
/*     */   private static final int ʠ = 195;
/*     */   private static final int ʕ = 197;
/*     */   private static final int ʓ = 198;
/*     */   private static final int ʞ = 199;
/*     */   private static final int ʏ = 206;
/*     */   private static final int ʜ = 17;
/*     */   private static final int ʐ = 18;
/*     */   private static final int ʚ = 19;
/*     */   private static final int ʘ = 20;
/*     */   private static final int ʑ = 64;
/*     */   private static final int ʣ = 36;
/*     */   private static final int ʛ = 2;
/*     */   private static final int ʝ = 8;
/*     */   private static final int ʡ = 15;
/*     */   private static final int ʗ = 3;
/*     */   private boolean ʙ;
/*     */   private boolean ʟ;
/*     */ 
/*     */   u(int paramInt1, long paramLong, String paramString, int paramInt2)
/*     */   {
/* 122 */     super(paramInt1, paramString, 13, paramInt2, 3);
/*     */ 
/* 130 */     this.ǜ = new .A._C(this, 192, "Read Current Settings");
/* 131 */     this.ż = new .A._C(this, 159, "Read BG Target Ranges");
/*     */ 
/* 137 */     this.Ǟ = new .A._C(this, 157, "Read Current History Download Page Number");
/*     */ 
/* 141 */     this.Ř = new .A._C(this, 193, "Read Saved Settings Date");
/*     */ 
/* 144 */     this.ũ = new .A._C(this, 195, "Read Contrast");
/*     */ 
/* 147 */     this.ť = new .A._C(this, 197, "Read Bolus Reminder On/Off");
/*     */ 
/* 150 */     this.ƒ = new .A._C(this, 198, "Read Bolus Reminders");
/*     */ 
/* 153 */     this.ŵ = new .A._C(this, 199, "Read Factory Parameters");
/*     */ 
/* 157 */     this.ƴ = new .A._C(this, 206, "Read Current Pump Status");
/*     */ 
/* 159 */     this.ƴ.Y = 0;
/*     */ 
/* 161 */     this.Ė = new _A();
/* 162 */     this.¤ = paramLong;
/*     */   }
/*     */ 
/*     */   u(int paramInt, long paramLong)
/*     */   {
/* 176 */     this(paramInt, paramLong, "MiniMed MMT-515/715 (Paradigm2) Insulin Pump", 115);
/*     */   }
/*     */ 
/*     */   static boolean G(String paramString)
/*     */   {
/* 187 */     return ("515".equals(paramString)) || ("715".equals(paramString));
/*     */   }
/*     */ 
/*     */   boolean l()
/*     */     throws t, Z
/*     */   {
/* 199 */     this.ƴ.A();
/* 200 */     A(this.ƴ);
/* 201 */     return this.ʙ;
/*     */   }
/*     */ 
/*     */   void A(M._A param_A)
/*     */     throws Z
/*     */   {
/* 213 */     switch (param_A.X)
/*     */     {
/*     */     case 192:
/* 218 */       I(param_A.a);
/* 219 */       break;
/*     */     case 157:
/* 225 */       this.ƈ.Q = Q(param_A.a);
/*     */ 
/* 228 */       this.ƈ.C();
/* 229 */       h();
/* 230 */       break;
/*     */     case 206:
/* 233 */       P(param_A.a);
/* 234 */       break;
/*     */     default:
/* 240 */       super.A(param_A);
/*     */     }
/*     */   }
/*     */ 
/*     */   void I(int[] paramArrayOfInt)
/*     */     throws Z
/*     */   {
/* 259 */     super.I(paramArrayOfInt);
/*     */ 
/* 266 */     O(paramArrayOfInt);
/*     */ 
/* 273 */     this.ı = paramArrayOfInt[18];
/*     */ 
/* 276 */     this.ő = paramArrayOfInt[19];
/*     */ 
/* 279 */     this.Ǔ = paramArrayOfInt[20];
/*     */ 
/* 281 */     A(this, "decodeCurrentSettings: Temp Basal Type = " + (this.Ÿ == 0 ? "Units Per Hour" : "Percent"));
/*     */ 
/* 283 */     A(this, "decodeCurrentSettings: Temp Basal Percent = " + this.Ƌ);
/* 284 */     A(this, "decodeCurrentSettings: ParadigmLink Enable = " + this.į);
/* 285 */     A(this, "decodeCurrentSettings: Reservoir Warning Type = " + (this.ı == 0 ? "units" : "time"));
/*     */ 
/* 287 */     A(this, "decodeCurrentSettings: Reservoir Warning Point = " + this.ő);
/*     */ 
/* 289 */     A(this, "decodeCurrentSettings: Keypad Locked = " + this.Ǔ);
/*     */   }
/*     */ 
/*     */   void O(int[] paramArrayOfInt)
/*     */     throws Z
/*     */   {
/* 301 */     this.Ɖ = paramArrayOfInt[17];
/* 302 */     if (this.Ɖ != 15) {
/* 303 */       O._B.A(this.Ɖ, 2, 8, "Insulin Action Curve");
/*     */     }
/*     */ 
/* 306 */     A(this, "decodeCurrentSettings: Insulin Action Curve = " + (this.Ɖ == 15 ? "unset" : Integer.toString(this.Ɖ)));
/*     */   }
/*     */ 
/*     */   private void P(int[] paramArrayOfInt)
/*     */     throws Z
/*     */   {
/* 321 */     Contract.pre(paramArrayOfInt != null);
/* 322 */     Contract.pre(paramArrayOfInt.length == 64);
/*     */ 
/* 328 */     this.ʙ = O._B.A(paramArrayOfInt[1], "bolusing flag");
/*     */ 
/* 331 */     this.ʟ = O._B.A(paramArrayOfInt[2], "suspended flag");
/*     */ 
/* 333 */     A(this, "decodePumpStatus: bolusing=" + this.ʙ + ", suspended=" + this.ʟ);
/*     */   }
/*     */ 
/*     */   private int Q(int[] paramArrayOfInt)
/*     */     throws Z
/*     */   {
/* 347 */     Contract.pre(paramArrayOfInt != null);
/* 348 */     Contract.pre(paramArrayOfInt.length == 64);
/*     */ 
/* 356 */     this.ų = (int)O._B.A(paramArrayOfInt[0], paramArrayOfInt[1], paramArrayOfInt[2], paramArrayOfInt[3]);
/*     */ 
/* 360 */     int i = this.ų - (int)this.¤ + 1;
/*     */ 
/* 363 */     if ((i <= 0) || (i > 36)) {
/* 364 */       i = 36;
/*     */     }
/*     */ 
/* 367 */     A(this, "decodeCurrentHistoryPageNumber: current page number (pump) = " + this.ų + ", last page number (system) = " + this.¤ + ", pages to read = " + i);
/*     */ 
/* 370 */     return i; } 
/*     */   class _A extends O._A { private static final int À = 2598;
/*     */     private static final int Ø = 1;
/*     */     private static final int Î = 2;
/*     */     private static final int £ = 3;
/*     */     private static final int Ñ = 4;
/*     */     private static final int Í = 5;
/*     */     private static final int Ó = 6;
/*     */     private static final int Ò = 7;
/*     */     private static final int Ö = 8;
/*     */     private static final int µ = 9;
/*     */     private static final int Õ = 10;
/*     */     private static final int È = 11;
/*     */     private static final int É = 12;
/*     */     private static final int Á = 13;
/*     */     private static final int Ê = 14;
/*     */     private static final int Ï = 15;
/*     */     private static final int ¥ = 16;
/*     */     private static final int z = 17;
/*     */     private static final int ª = 18;
/*     */     private static final int Ä = 19;
/*     */     private static final int ¢ = 20;
/*     */     private static final int Ð = 21;
/*     */     private static final int Ã = 22;
/*     */     private static final int Ç = 23;
/*     */     private static final int Ì = 24;
/*     */     private static final int Å = 25;
/*     */     private static final int ¤ = 26;
/*     */     private static final int Æ = 27;
/*     */     private static final int Ë = 28;
/*     */     private static final int º = 29;
/*     */     private static final int Ô = 30;
/*     */     private static final int Â = 31;
/*     */ 
/* 426 */     _A() { this(2598);
/*     */     }
/*     */ 
/*     */     _A(int arg2)
/*     */     {
/* 436 */       super(i);
/* 437 */       u.this.e = 64;
/* 438 */       u.this.ì = 64;
/* 439 */       u.this.£ = 64;
/*     */     }
/*     */ 
/*     */     void A()
/*     */     {
/* 449 */       u.this.Î = new CA(u.this.Þ, 1, u.this.Ű.a, u.this.ƛ.a, u.this.ń.a);
/*     */ 
/* 457 */       O.A(this, "createSnapshot: creating snapshot");
/*     */ 
/* 460 */       u.this.Î.A(1, u.this.ǜ.a);
/*     */ 
/* 464 */       u.this.Î.A(2, u.this.Ɗ.a);
/*     */ 
/* 468 */       u.this.Î.A(3, u.this.ř.a);
/*     */ 
/* 472 */       u.this.Î.A(4, u.this.ƈ.a);
/*     */ 
/* 475 */       u.this.Î.A(5, u.this.Ƈ.a);
/*     */ 
/* 479 */       u.this.Î.A(6, u.this.Ų.a);
/*     */ 
/* 482 */       u.this.Î.A(7, u.this.ű.a);
/*     */ 
/* 485 */       u.this.Î.A(8, u.this.ĳ.a);
/*     */ 
/* 488 */       u.this.Î.A(9, u.this.Ʒ.a);
/*     */ 
/* 492 */       u.this.Î.A(10, u.this.ş.a);
/*     */ 
/* 495 */       u.this.Î.A(11, u.this.ǅ.a);
/*     */ 
/* 498 */       u.this.Î.A(12, u.this.Ƴ.a);
/*     */ 
/* 501 */       u.this.Î.A(13, u.this.Ƥ.a);
/*     */ 
/* 504 */       u.this.Î.A(14, u.this.Ŗ.a);
/*     */ 
/* 508 */       u.this.Î.A(15, u.this.Ɣ.a);
/*     */ 
/* 511 */       u.this.Î.A(16, u.this.ż.a);
/*     */ 
/* 514 */       u.this.Î.A(17, u.this.Ɛ.a);
/*     */ 
/* 517 */       u.this.Î.A(18, u.this.Ʃ.a);
/*     */ 
/* 520 */       u.this.Î.A(19, u.this.Ʈ.a);
/*     */ 
/* 523 */       u.this.Î.A(20, u.this.ƪ.a);
/*     */ 
/* 526 */       u.this.Î.A(21, u.this.Ɯ.a);
/*     */ 
/* 529 */       u.this.Î.A(22, u.this.ŭ.a);
/*     */ 
/* 532 */       u.this.Î.A(23, u.this.ǈ.a);
/*     */ 
/* 535 */       u.this.Î.A(24, u.this.Ƅ.a);
/*     */ 
/* 538 */       u.this.Î.A(25, u.this.ǘ.a);
/*     */ 
/* 543 */       u.this.Î.A(26, u.this.Ǟ.a);
/*     */ 
/* 547 */       u.this.Î.A(27, u.this.ũ.a);
/*     */ 
/* 550 */       u.this.Î.A(28, u.this.ť.a);
/*     */ 
/* 554 */       u.this.Î.A(29, u.this.ƒ.a);
/*     */ 
/* 557 */       u.this.Î.A(30, u.this.ŵ.a);
/*     */ 
/* 560 */       u.this.Î.A(31, u.this.Ř.a);
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.A.u
 * JD-Core Version:    0.6.0
 */