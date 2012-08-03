/*     */ package minimed.ddms.A;
/*     */ 
/*     */ import java.util.Vector;
/*     */ 
/*     */ final class s extends w
/*     */ {
/*     */   public static final int ӯ = 154;
/*     */   private static final int Ӯ = 1000;
/*     */ 
/*     */   s()
/*     */   {
/*  47 */     this.ć = "LifeScan FastTake Meter";
/*  48 */     A(this, "creating interface to the '" + this.ć + "', package version " + K());
/*     */ 
/*  50 */     this.ā = 6;
/*  51 */     this.Þ = 154;
/*  52 */     this.Ė = new _A(null);
/*     */   }
/*     */ 
/*     */   Vector É()
/*     */   {
/*  63 */     Vector localVector = new Vector();
/*     */ 
/*  67 */     localVector.addElement(this.Ҷ);
/*  68 */     localVector.addElement(this.ҭ);
/*  69 */     localVector.addElement(this.ѳ);
/*  70 */     localVector.addElement(this.ѵ);
/*  71 */     return localVector;
/*     */   }
/*     */ 
/*     */   void Ñ()
/*     */     throws t, Z
/*     */   {
/*  82 */     Y().E();
/*     */ 
/*  84 */     E(4);
/*     */ 
/*  90 */     this.Ұ = new w._C(this, "DMST?", "Read Current Time Settings (Ultra Detection only)", 50);
/*     */ 
/*  96 */     this.Ұ.A();
/*  97 */     O._B.G(1000);
/*  98 */     this.Ұ.A();
/*     */ 
/* 100 */     String str = O._B.E(this.Ұ.d());
/*     */ 
/* 103 */     if (str.indexOf("ST?") > -1)
/*     */     {
/* 106 */       throw new t("Not a FastTake meter.");
/*     */     }
/*     */ 
/* 110 */     this.Ұ = null;
/*     */   }
/*     */ 
/*     */   void Ë()
/*     */     throws Z
/*     */   {
/* 123 */     this.Һ = null;
/* 124 */     this.ҟ = null;
/*     */ 
/* 126 */     A(this, "decodeCurrentSettings: *** CURRENT SETTINGS *** (none)");
/*     */   }
/*     */ 
/*     */   private final class _A extends w._A
/*     */   {
/*     */     private _A()
/*     */     {
/* 134 */       super();
/*     */     }
/*     */ 
/*     */     void A()
/*     */     {
/* 149 */       s.this.Î = new CA(s.this.Þ, 1, B(s.this.Ă), B(s.this.ă), B(s.this.ѻ));
/*     */ 
/* 152 */       O.A(this, "createSnapshot: creating snapshot");
/*     */ 
/* 156 */       s.this.Î.A(2, s.this.Ҟ);
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.A.s
 * JD-Core Version:    0.6.0
 */