/*     */ package minimed.ddms.A;
/*     */ 
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.StringTokenizer;
/*     */ import java.util.Vector;
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ final class B extends w
/*     */ {
/*     */   public static final int ӈ = 155;
/*     */   public static final int Ӑ = 157;
/*     */   public static final int ӑ = 158;
/*     */   static final String Ӌ = "DMST?";
/*     */   static final String Ӕ = "ST?";
/*     */   private static final String Ӓ = "DMSU?";
/*     */   private static final String ӓ = "DM?";
/*     */   private static final String ӂ = "DMID\r";
/*     */   private static final int Ӈ = 1000;
/*     */   private static final int Ӄ = 31000;
/*     */   private static final int ӌ = -1;
/*     */   private String ӄ;
/*     */ 
/*     */   B()
/*     */   {
/*  78 */     this.ć = "LifeScan One Touch Ultra / Ultra2 / UltraLink Meter";
/*  79 */     A(this, "creating interface to the '" + this.ć + "', package version " + K());
/*     */ 
/*  81 */     this.ā = 5;
/*  82 */     this.Þ = -1;
/*  83 */     this.Ė = new _A(null);
/*     */ 
/*  86 */     this.ѹ = new w._C(this, "DMSU?", "Read Current Units Settings", 50);
/*     */ 
/*  88 */     this.Ұ = new w._C(this, "DMST?", "Read Current Time Settings", 50);
/*     */   }
/*     */ 
/*     */   void Î()
/*     */     throws Z
/*     */   {
/* 100 */     boolean bool1 = Q().endsWith("Y");
/* 101 */     boolean bool2 = Q().startsWith("H");
/*     */ 
/* 104 */     if ((bool1) && (bool2)) {
/* 105 */       throw new Z("Meter is both Ultra2 and UltraLink: " + Q());
/*     */     }
/*     */ 
/* 109 */     int i = (!bool1) && (!bool2) ? 1 : 0;
/*     */ 
/* 112 */     Contract.invariant((i != 0) || (bool1) || (bool2));
/*     */ 
/* 114 */     if (i != 0)
/* 115 */       this.Þ = 155;
/* 116 */     else if (bool1)
/* 117 */       this.Þ = 157;
/*     */     else {
/* 119 */       this.Þ = 158;
/*     */     }
/* 121 */     A(this, "initDeviceAfterSerialNumberKnown: meter model is " + (bool2 ? "ULTRALINK" : bool1 ? "ULTRA2" : "ULTRA"));
/*     */ 
/* 125 */     if ((bool1) || (bool2)) {
/* 126 */       this.ѵ.B(new int[31000]);
/*     */     }
/*     */ 
/* 130 */     if (bool2) {
/* 131 */       w._C local_C = new w._C(this, "DMID\r", "Read RF ID", 50, new D()
/*     */       {
/*     */         public void A(w._B param_B)
/*     */           throws Z
/*     */         {
/* 142 */           B.A(B.this, O._B.E(param_B.d()));
/* 143 */           O.A(this, "decodeReply: RF ID is '" + B.B(B.this, B.A(B.this)) + "'");
/*     */         }
/*     */       });
/* 147 */       Ê().addElement(local_C);
/*     */     }
/*     */     else {
/* 150 */       this.ӄ = "";
/*     */     }
/*     */   }
/*     */ 
/*     */   void Ë()
/*     */     throws Z
/*     */   {
/* 165 */     Contract.pre(this.ѽ != null);
/*     */ 
/* 172 */     boolean bool = this.ѽ.indexOf("MG/DL") >= 0;
/* 173 */     int i = this.ѽ.indexOf("MMOL/L") >= 0 ? 1 : 0;
/*     */ 
/* 176 */     if (((!bool) && (i == 0)) || ((bool) && (i != 0))) {
/* 177 */       throw new Z("Bad current settings string received (units): '" + this.ѽ);
/*     */     }
/*     */ 
/* 181 */     this.ҿ = new Boolean(bool);
/*     */ 
/* 183 */     A(this, "decodeCurrentSettings: *** CURRENT SETTINGS ***");
/* 184 */     A(this, "decodeCurrentSettings: Units Is mm/dL = " + this.ҿ);
/*     */   }
/*     */ 
/*     */   void Ð()
/*     */     throws Z
/*     */   {
/* 198 */     Contract.pre(this.ҷ != null);
/* 199 */     Contract.pre(this.ҷ.length() > 1);
/*     */ 
/* 206 */     boolean bool = this.ҷ.indexOf("AM/PM") >= 0;
/* 207 */     int i = this.ҷ.indexOf("24:00") >= 0 ? 1 : 0;
/*     */ 
/* 210 */     if (((!bool) && (i == 0)) || ((bool) && (i != 0)))
/*     */     {
/* 212 */       throw new Z("Bad current settings string received (time format): '" + this.ҷ);
/*     */     }
/*     */ 
/* 217 */     this.ҟ = new Boolean(bool);
/*     */ 
/* 219 */     A(this, "decodeCurrentSettings: *** CURRENT SETTINGS2 ***");
/* 220 */     A(this, "decodeCurrentSettings: Time Format is AM/PM = " + this.ҟ);
/*     */   }
/*     */ 
/*     */   void Ñ()
/*     */     throws t, Z
/*     */   {
/* 232 */     Y().E();
/*     */ 
/* 234 */     E(4);
/* 235 */     w._C local_C = new w._C(this, "DM?", "Initialize Communications", 0);
/*     */ 
/* 237 */     local_C.A();
/* 238 */     O._B.G(1000);
/*     */   }
/*     */ 
/*     */   private String R(String paramString) throws Z
/*     */   {
/* 250 */     Contract.preNonNull(paramString);
/*     */     String str;
/*     */     try
/*     */     {
/* 255 */       StringTokenizer localStringTokenizer = new StringTokenizer(paramString, "\"");
/* 256 */       localStringTokenizer.nextToken();
/* 257 */       str = localStringTokenizer.nextToken();
/*     */     } catch (NoSuchElementException localNoSuchElementException) {
/* 259 */       throw new Z("RF ID reply '" + paramString + "'");
/*     */     }
/*     */ 
/* 262 */     return str; } 
/*     */   private final class _A extends w._A { static final int ë = 1;
/*     */     static final int î = 2;
/*     */     static final int é = 3;
/*     */     static final int ê = 4;
/*     */     static final int ç = 4;
/*     */     private static final String í = "Y";
/*     */     private static final String ì = "H";
/*     */ 
/* 270 */     private _A() { super();
/*     */     }
/*     */ 
/*     */     void A()
/*     */     {
/* 300 */       B.this.Î = new CA(B.this.Þ, 1, B(B.this.Ă), B(B.this.ă), B(B.this.ѻ));
/*     */ 
/* 303 */       O.A(this, "createSnapshot: creating snapshot");
/*     */ 
/* 307 */       B.this.Î.A(1, B.this.ѽ);
/* 308 */       B.this.Î.A(2, B.this.ҷ);
/*     */ 
/* 312 */       B.this.Î.A(3, B.this.Ҟ);
/*     */ 
/* 315 */       if (B.this.Þ == 158)
/* 316 */         B.this.Î.A(4, B('\r' + B.A(B.this).trim()));
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.A.B
 * JD-Core Version:    0.6.0
 */