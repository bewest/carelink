/*     */ package minimed.ddms.A;
/*     */ 
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ abstract class x extends .A
/*     */ {
/*     */   static final int ɞ = 114;
/*     */   static final String ʈ = "512";
/*     */   static final String ʇ = "712";
/*     */   private static final int ʁ = 145;
/*     */   private static final int ɵ = 152;
/*     */   private static final int ɱ = 146;
/*     */   private static final int ɸ = 147;
/*     */   private static final int ʅ = 148;
/*     */   private static final int ʆ = 142;
/*     */   private static final int ɶ = 151;
/*     */   private static final int ʉ = 144;
/*     */   private static final int ʎ = 140;
/*     */   private static final int ɺ = 137;
/*     */   private static final int ɹ = 135;
/*     */   private static final int ɯ = 138;
/*     */   private static final int ɲ = 136;
/*     */   private static final int ʄ = 149;
/*     */   private static final int ʃ = 139;
/*     */   private static final int ʋ = 143;
/*     */   private static final int ʌ = 141;
/*     */   private static final int ɼ = 134;
/*     */   private static final int ʀ = 14;
/*     */   private static final int ɰ = 15;
/*     */   private static final int ɽ = 16;
/*     */   private static final int ɾ = 17;
/*     */   private static final int ɴ = 64;
/*     */   private static final int ɿ = 192;
/*     */   private static final int ɳ = 1;
/*     */   private static final int ɷ = 36;
/*     */   private static final int ɻ = 200;
/*     */   private static final int ʊ = 10;
/*     */   private static final int ʍ = 40;
/*     */   private static final int ʂ = 3;
/*     */ 
/*     */   x(int paramInt1, String paramString, int paramInt2, int paramInt3, int paramInt4)
/*     */   {
/* 146 */     super(paramInt1, paramString, paramInt2, paramInt3, paramInt4);
/*     */ 
/* 149 */     this.ū = 40;
/* 150 */     this.ƿ = 10;
/*     */ 
/* 157 */     this.ǜ = new .A._C(this, 145, "Read Current Settings");
/*     */ 
/* 159 */     this.ř = new .A._C(this, 152, "Read Temporary Basal");
/*     */ 
/* 162 */     this.Ƈ = new .A._C(this, 146, "Read Standard Profiles Data", 192, 1, 8);
/*     */ 
/* 171 */     this.Ų = new .A._C(this, 147, "Read Profiles A Data", 192, 1, 9);
/*     */ 
/* 180 */     this.ű = new .A._C(this, 148, "Read Profiles B Data", 192, 1, 10);
/*     */ 
/* 188 */     this.ƈ = new .A._B(this, 36);
/*     */ 
/* 193 */     this.ǈ = new .A._C(this, 141, "Read Pump Model");
/*     */ 
/* 196 */     this.Ɛ = new .A._C(this, 142, "Read BG Alarm Clocks");
/*     */ 
/* 199 */     this.ǘ = new .A._C(this, 151, "Read BG Alarm Enable");
/*     */ 
/* 202 */     this.Ʃ = new .A._C(this, 144, "Read BG Reminder Enable");
/*     */ 
/* 205 */     this.ż = new .A._C(this, 140, "Read BG Targets");
/*     */ 
/* 208 */     this.Ɣ = new .A._C(this, 137, "Read BG Units");
/*     */ 
/* 210 */     this.Ŗ = new .A._C(this, 135, "Read Bolus Wizard Setup Status");
/*     */ 
/* 213 */     this.ƪ = new .A._C(this, 138, "Read Carbohydrate Ratios");
/*     */ 
/* 216 */     this.Ʈ = new .A._C(this, 136, "Read Carbohydrate Units");
/*     */ 
/* 219 */     this.Ƅ = new .A._C(this, 149, "Read ParadigmLink Ids");
/*     */ 
/* 222 */     this.Ɯ = new .A._C(this, 139, "Read Insulin Sensitivities");
/*     */ 
/* 225 */     this.ŭ = new .A._C(this, 143, "Read Reservoir Warning");
/*     */ 
/* 228 */     this.Ƥ = new .A._C(this, 134, "Read Language");
/*     */ 
/* 231 */     this.Ė = new _A();
/*     */   }
/*     */ 
/*     */   static boolean G(String paramString)
/*     */   {
/* 241 */     return ("512".equals(paramString)) || ("712".equals(paramString));
/*     */   }
/*     */ 
/*     */   void A(M._A param_A)
/*     */     throws Z
/*     */   {
/* 255 */     switch (param_A.X)
/*     */     {
/*     */     case 145:
/* 260 */       I(param_A.a);
/* 261 */       break;
/*     */     case 152:
/* 263 */       N(param_A.a);
/* 264 */       break;
/*     */     case 141:
/* 269 */       M(param_A.a);
/* 270 */       break;
/*     */     default:
/* 275 */       super.A(param_A);
/*     */     }
/*     */   }
/*     */ 
/*     */   void k()
/*     */     throws t, P
/*     */   {
/*     */   }
/*     */ 
/*     */   void n()
/*     */     throws t, W
/*     */   {
/*     */   }
/*     */ 
/*     */   void p()
/*     */     throws t, P
/*     */   {
/* 314 */     super.k();
/*     */   }
/*     */ 
/*     */   void I(int[] paramArrayOfInt)
/*     */     throws Z
/*     */   {
/* 333 */     super.I(paramArrayOfInt);
/*     */ 
/* 340 */     this.Ÿ = paramArrayOfInt[14];
/* 341 */     O._B.A(this.Ÿ, 0, 1, "Temp Basal Type");
/*     */ 
/* 344 */     this.Ƌ = paramArrayOfInt[15];
/* 345 */     O._B.A(this.Ƌ, 0, 200, "Temp Basal Percent");
/*     */ 
/* 349 */     int i = paramArrayOfInt[16];
/* 350 */     O._B.A(i, 0, 1, "ParadigmLink Enable");
/* 351 */     this.į = (i == 1);
/*     */ 
/* 354 */     O(paramArrayOfInt);
/*     */ 
/* 356 */     A(this, "decodeCurrentSettings: Temp Basal Type = " + (this.Ÿ == 0 ? "Units Per Hour" : "Percent"));
/*     */ 
/* 358 */     A(this, "decodeCurrentSettings: Temp Basal Percent = " + this.Ƌ);
/* 359 */     A(this, "decodeCurrentSettings: ParadigmLink Enable = " + this.į);
/*     */   }
/*     */ 
/*     */   void O(int[] paramArrayOfInt)
/*     */     throws Z
/*     */   {
/* 371 */     this.Ɖ = paramArrayOfInt[17];
/* 372 */     O._B.A(this.Ɖ, 0, 1, "Insulin Action Type");
/* 373 */     A(this, "decodeCurrentSettings: Insulin Action Type = " + (this.Ɖ == 0 ? "Fast" : "Regular"));
/*     */   }
/*     */ 
/*     */   private void M(int[] paramArrayOfInt)
/*     */     throws Z
/*     */   {
/* 388 */     Contract.pre(paramArrayOfInt != null);
/* 389 */     Contract.pre(paramArrayOfInt.length == 64);
/*     */ 
/* 394 */     int i = paramArrayOfInt[0];
/* 395 */     int[] arrayOfInt = new int[i];
/* 396 */     System.arraycopy(paramArrayOfInt, 1, arrayOfInt, 0, arrayOfInt.length);
/* 397 */     this.À = O._B.E(arrayOfInt);
/*     */ 
/* 399 */     A(this, "decodeModelNumber: model = " + this.À);
/*     */   }
/*     */ 
/*     */   private void N(int[] paramArrayOfInt)
/*     */     throws Z
/*     */   {
/* 413 */     Contract.pre(paramArrayOfInt != null);
/* 414 */     Contract.pre(paramArrayOfInt.length == 64);
/*     */ 
/* 422 */     int i = O._B.B(paramArrayOfInt[2], paramArrayOfInt[3]);
/* 423 */     O._B.A(i, 0.0D, A(35.0D), "Temporary Basal Rate");
/*     */ 
/* 428 */     this.Ǉ = H(i);
/*     */ 
/* 431 */     this.Ɔ = O._B.B(paramArrayOfInt[4], paramArrayOfInt[5]);
/* 432 */     O._B.A(this.Ɔ, 0, 1440, "Temporary Basal Duration");
/*     */ 
/* 438 */     A(this, "decodeTempBasal: Temp Basal Rate = " + this.Ǉ);
/* 439 */     A(this, "decodeTempBasal: Temp Basal Remain Dur = " + this.Ɔ + " minutes"); } 
/*     */   private final class _A extends O._A { private static final int Ċ = 1;
/*     */     private static final int ā = 2;
/*     */     private static final int ñ = 3;
/*     */     private static final int Ą = 4;
/*     */     private static final int Ā = 5;
/*     */     private static final int Ć = 6;
/*     */     private static final int ą = 7;
/*     */     private static final int ĉ = 8;
/*     */     private static final int ó = 9;
/*     */     private static final int Ĉ = 10;
/*     */     private static final int ü = 11;
/*     */     private static final int ý = 12;
/*     */     private static final int ö = 13;
/*     */     private static final int þ = 14;
/*     */     private static final int Ă = 15;
/*     */     private static final int ć = 16;
/*     */     private static final int ô = 17;
/*     */     private static final int ú = 18;
/*     */     private static final int ù = 19;
/*     */     private static final int ð = 20;
/*     */     private static final int ă = 21;
/*     */     private static final int ø = 22;
/*     */     private static final int û = 23;
/*     */     private static final int ÿ = 24;
/*     */     private static final int ò = 25;
/*     */     private static final int õ = 2152;
/*     */ 
/* 490 */     _A() { super(2152);
/* 491 */       x.this.e = 64;
/* 492 */       x.this.ì = 64;
/* 493 */       x.this.£ = 64;
/*     */     }
/*     */ 
/*     */     void A()
/*     */     {
/* 503 */       x.this.Î = new CA(x.this.Þ, 1, x.this.Ű.a, x.this.ƛ.a, x.this.ń.a);
/*     */ 
/* 511 */       O.A(this, "createSnapshot: creating snapshot");
/*     */ 
/* 514 */       x.this.Î.A(1, x.this.ǜ.a);
/*     */ 
/* 518 */       x.this.Î.A(2, x.this.Ɗ.a);
/*     */ 
/* 522 */       x.this.Î.A(3, x.this.ř.a);
/*     */ 
/* 526 */       x.this.Î.A(4, x.this.ƈ.a);
/*     */ 
/* 529 */       x.this.Î.A(5, x.this.Ƈ.a);
/*     */ 
/* 533 */       x.this.Î.A(6, x.this.Ų.a);
/*     */ 
/* 536 */       x.this.Î.A(7, x.this.ű.a);
/*     */ 
/* 539 */       x.this.Î.A(8, x.this.ĳ.a);
/*     */ 
/* 542 */       x.this.Î.A(9, x.this.Ʒ.a);
/*     */ 
/* 546 */       x.this.Î.A(10, x.this.ş.a);
/*     */ 
/* 549 */       x.this.Î.A(11, x.this.ǅ.a);
/*     */ 
/* 552 */       x.this.Î.A(12, x.this.Ƴ.a);
/*     */ 
/* 555 */       x.this.Î.A(13, x.this.Ƥ.a);
/*     */ 
/* 558 */       x.this.Î.A(14, x.this.Ŗ.a);
/*     */ 
/* 562 */       x.this.Î.A(15, x.this.Ɣ.a);
/*     */ 
/* 565 */       x.this.Î.A(16, x.this.ż.a);
/*     */ 
/* 568 */       x.this.Î.A(17, x.this.Ɛ.a);
/*     */ 
/* 571 */       x.this.Î.A(18, x.this.Ʃ.a);
/*     */ 
/* 574 */       x.this.Î.A(19, x.this.Ʈ.a);
/*     */ 
/* 577 */       x.this.Î.A(20, x.this.ƪ.a);
/*     */ 
/* 580 */       x.this.Î.A(21, x.this.Ɯ.a);
/*     */ 
/* 583 */       x.this.Î.A(22, x.this.ŭ.a);
/*     */ 
/* 586 */       x.this.Î.A(23, x.this.ǈ.a);
/*     */ 
/* 589 */       x.this.Î.A(24, x.this.Ƅ.a);
/*     */ 
/* 592 */       x.this.Î.A(25, x.this.ǘ.a);
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.A.x
 * JD-Core Version:    0.6.0
 */