/*     */ package minimed.ddms.A;
/*     */ 
/*     */ import java.util.Set;
/*     */ import java.util.Vector;
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ final class Y extends Q
/*     */ {
/*     */   private static final String Ќ = "Bayer6115";
/*     */   private static final String Ћ = "Bayer6116";
/*     */   private static final String Њ = "1.";
/*     */   private static final int Џ = 100;
/*     */   private static final int Ў = 420;
/*     */ 
/*     */   Y()
/*     */   {
/*  58 */     super(100);
/*  59 */     this.ć = "Bayer Glucometer Breeze Meter";
/*  60 */     A(this, "creating interface to the '" + this.ć + "', package version " + K());
/*     */ 
/*  62 */     this.ā = 19;
/*  63 */     this.ϰ.add("Bayer6115");
/*  64 */     this.ϰ.add("Bayer6116");
/*     */   }
/*     */ 
/*     */   void ¤()
/*     */     throws Z
/*     */   {
/*  77 */     Contract.preNonNull(this.Ă);
/*     */ 
/*  81 */     if (this.Ă.startsWith("1.")) {
/*  82 */       A(this, "initDeviceAfterSerialNumberKnown: meter model is Breeze");
/*     */     } else {
/*  84 */       A(this, "initDeviceAfterSerialNumberKnown: meter model is Breeze 2");
/*     */ 
/*  86 */       M(420);
/*     */     }
/*     */   }
/*     */ 
/*     */   void y()
/*     */   {
/*  95 */     this.ϭ = new Q._B(this);
/*     */   }
/*     */ 
/*     */   Vector w()
/*     */   {
/* 103 */     Vector localVector = new Vector();
/*     */ 
/* 106 */     localVector.addElement(this.ϭ);
/* 107 */     return localVector;
/*     */   }
/*     */ 
/*     */   void L(int paramInt)
/*     */   {
/*     */   }
/*     */ 
/*     */   void ¢()
/*     */     throws Z
/*     */   {
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.A.Y
 * JD-Core Version:    0.6.0
 */