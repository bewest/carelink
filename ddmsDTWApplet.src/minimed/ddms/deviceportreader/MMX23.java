/*     */ package minimed.ddms.A;
/*     */ 
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ class _ extends b
/*     */ {
/*     */   public static final int ɞ = 117;
/*     */   private static final int Ι = 213;
/*     */   private static final int Μ = 240;
/*     */   private static final int Δ = 32;
/*     */   private static final int Λ = 1024;
/*     */   static final String ʈ = "523";
/*     */   static final String ʇ = "723";
/*     */   static final String ʸ = "523K";
/*     */   static final String ʷ = "723K";
/*     */   private static final int Ζ = 5;
/*     */   private static final int Κ = 7;
/*     */   private static final int Γ = 9;
/*     */   private static final int Β = 21;
/*     */   private static final int Θ = 22;
/*     */   private static final int Α = 23;
/*     */   private static final int Η = 24;
/*     */   private static final int Ε = 75;
/*     */ 
/*     */   public _(int paramInt1, long paramLong1, long paramLong2, String paramString, int paramInt2)
/*     */   {
/* 104 */     super(paramInt1, paramLong1, paramLong2, paramString, paramInt2);
/*     */ 
/* 107 */     this.Ũ = new .A._C(this, 209, "Read Sensor Predictive Alerts");
/*     */ 
/* 111 */     this.Ƨ = new .A._C(this, 212, "Read Sensor Rate Of Change Alerts");
/*     */ 
/* 115 */     this.ŏ = new .A._C(this, 210, "Read Sensor Demo and Graph Timeout");
/*     */ 
/* 119 */     this.Ǐ = new .A._C(this, 211, "Read Sensor Alarm Silence");
/*     */ 
/* 124 */     this.ƽ = new .A._C(this, 207, "Read Sensor Settings");
/*     */ 
/* 128 */     this.ŷ = new .A._C(this, 240, "Read Other Devices ID");
/*     */ 
/* 131 */     this.Ư = new b._A(this, 213, "Read Vcntr History", 1024, 32);
/*     */ 
/* 134 */     this.Ė = new _A();
/*     */   }
/*     */ 
/*     */   _(int paramInt, long paramLong1, long paramLong2)
/*     */   {
/* 151 */     this(paramInt, paramLong1, paramLong2, "MiniMed MMT-523/723 (Paradigm2) Insulin Pump", 117);
/*     */   }
/*     */ 
/*     */   static boolean G(String paramString)
/*     */   {
/* 166 */     return ("523".equals(paramString)) || ("723".equals(paramString)) || ("523K".equals(paramString)) || ("723K".equals(paramString));
/*     */   }
/*     */ 
/*     */   void J(int[] paramArrayOfInt)
/*     */     throws Z
/*     */   {
/* 181 */     Contract.pre(paramArrayOfInt != null);
/* 182 */     Contract.pre(paramArrayOfInt.length == 64);
/*     */ 
/* 186 */     int i = O._B.A(paramArrayOfInt[5], paramArrayOfInt[6]);
/*     */ 
/* 188 */     O._B.A(i, 0.0D, B(75.0D), "Maximum Bolus Rate");
/* 189 */     this.ƺ = F(i);
/*     */   }
/*     */ 
/*     */   void I(int[] paramArrayOfInt)
/*     */     throws Z
/*     */   {
/* 206 */     super.I(paramArrayOfInt);
/*     */ 
/* 211 */     this.Ň = paramArrayOfInt[21];
/*     */ 
/* 213 */     this.Ƙ = O._B.A(paramArrayOfInt[22], "CaptureEvent Enable");
/*     */ 
/* 215 */     this.ǖ = O._B.A(paramArrayOfInt[23], "OtherDevice Enable");
/*     */ 
/* 217 */     this.Ő = O._B.A(paramArrayOfInt[24], "OtherDevice Married State");
/*     */ 
/* 220 */     A(this, "decodeCurrentSettings: Bolus Scroll Step Size = " + this.Ň);
/*     */ 
/* 222 */     A(this, "decodeCurrentSettings: Capture Event Enable = " + this.Ƙ);
/*     */ 
/* 224 */     A(this, "decodeCurrentSettings: Other Device Enable = " + this.ǖ);
/*     */ 
/* 226 */     A(this, "decodeCurrentSettings: Other Device Married State = " + this.Ő);
/*     */   }
/*     */ 
/*     */   void R(int[] paramArrayOfInt)
/*     */   {
/* 239 */     super.R(paramArrayOfInt);
/*     */ 
/* 242 */     Contract.invariant(this.Ŕ.U != null);
/* 243 */     int i = paramArrayOfInt[5];
/* 244 */     y localy = (y)this.Ŕ.U;
/* 245 */     this.Ư.Q = this.Ŕ.Q;
/*     */ 
/* 248 */     this.Ư.C();
/* 249 */     h();
/*     */ 
/* 252 */     this.Ư.U = new y(localy.D(), localy.B(), localy.C(), "Vcntr Page Range");
/*     */ 
/* 255 */     A(this, "decodeCurrentGlucoseHistoryPageNumber: , available Vcntr pages = " + i + ", Vcntr pages to read = " + this.Ư.Q);
/*     */   }
/*     */ 
/*     */   int o()
/*     */   {
/* 266 */     return 7;
/*     */   }
/*     */ 
/*     */   int m()
/*     */   {
/* 275 */     return 9;
/*     */   }
/*     */ 
/*     */   private class _A extends b._B
/*     */   {
/*     */     private static final int ä = 3162;
/*     */     private static final int ã = 37;
/*     */     private static final int á = 38;
/*     */     private static final int å = 39;
/*     */     private static final int ß = 40;
/*     */     private static final int â = 34;
/*     */     private static final int à = 42;
/*     */ 
/*     */     _A()
/*     */     {
/* 308 */       super(3162);
/*     */     }
/*     */ 
/*     */     void A()
/*     */     {
/* 318 */       super.A();
/*     */ 
/* 322 */       _.this.Î.A(37, _.this.Ũ.a);
/*     */ 
/* 326 */       _.this.Î.A(38, _.this.ŏ.a);
/*     */ 
/* 330 */       _.this.Î.A(39, _.this.Ǐ.a);
/*     */ 
/* 334 */       _.this.Î.A(40, _.this.Ƨ.a);
/*     */ 
/* 339 */       _.this.Î.A(34, _.this.Ư.a);
/*     */ 
/* 343 */       _.this.Î.A(42, _.this.ŷ.a);
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.A._
 * JD-Core Version:    0.6.0
 */