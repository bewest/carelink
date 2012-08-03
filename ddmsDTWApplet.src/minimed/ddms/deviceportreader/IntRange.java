/*     */ package minimed.ddms.A;
/*     */ 
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ public class y
/*     */ {
/*     */   private final int C;
/*     */   private final int D;
/*     */   private final int B;
/*     */   private final String E;
/*     */   private final int A;
/*     */ 
/*     */   public y(int paramInt1, int paramInt2, int paramInt3, String paramString, int paramInt4)
/*     */   {
/*  54 */     Contract.pre(paramInt4 >= 1);
/*  55 */     Contract.pre(paramInt1 <= paramInt2);
/*  56 */     this.C = paramInt1;
/*  57 */     this.D = paramInt2;
/*  58 */     this.A = paramInt4;
/*     */ 
/*  60 */     A(paramInt3);
/*  61 */     this.B = paramInt3;
/*  62 */     this.E = paramString;
/*     */   }
/*     */ 
/*     */   public y(int paramInt1, int paramInt2, int paramInt3, String paramString)
/*     */   {
/*  75 */     this(paramInt1, paramInt2, paramInt3, paramString, 1);
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/*  88 */     return "Ranges for " + this.E + ": minimum=" + this.C + ", maximum=" + this.D + ", precision=" + this.A;
/*     */   }
/*     */ 
/*     */   public void B(int paramInt)
/*     */     throws Z
/*     */   {
/*  99 */     O._B.A(paramInt, this.C, this.D, this.E);
/* 100 */     O._B.A(paramInt % this.A == 0, "bad precision: " + paramInt);
/*     */   }
/*     */ 
/*     */   public void A(int paramInt)
/*     */   {
/* 110 */     Contract.pre(paramInt, this.C, this.D);
/* 111 */     Contract.pre(paramInt % this.A == 0);
/*     */   }
/*     */ 
/*     */   public int D()
/*     */   {
/* 120 */     return this.C;
/*     */   }
/*     */ 
/*     */   public int B()
/*     */   {
/* 129 */     return this.D;
/*     */   }
/*     */ 
/*     */   public int C()
/*     */   {
/* 138 */     return this.B;
/*     */   }
/*     */ 
/*     */   public int A()
/*     */   {
/* 147 */     return this.A;
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.A.y
 * JD-Core Version:    0.6.0
 */