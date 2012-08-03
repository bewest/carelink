/*     */ package minimed.ddms.A;
/*     */ 
/*     */ import java.io.IOException;
/*     */ 
/*     */ abstract class c
/*     */ {
/*  35 */   private boolean B = true;
/*     */   private int C;
/*     */   private int A;
/*     */ 
/*     */   final void G()
/*     */     throws W
/*     */   {
/*  52 */     if (!this.B) {
/*  53 */       O.B(this, "checkForSerialIOHalted: serial IO has been halted.");
/*  54 */       throw new W("Serial IO has been halted.");
/*     */     }
/*     */   }
/*     */ 
/*     */   final void A(boolean paramBoolean)
/*     */   {
/*  64 */     O.B(this, "setContinueIO: continueIO = " + paramBoolean);
/*  65 */     this.B = paramBoolean;
/*     */   }
/*     */ 
/*     */   void A()
/*     */   {
/*     */   }
/*     */ 
/*     */   final void A(int paramInt)
/*     */   {
/*  82 */     this.A = paramInt;
/*     */   }
/*     */ 
/*     */   final int F()
/*     */   {
/*  91 */     return this.A;
/*     */   }
/*     */ 
/*     */   void B(int paramInt)
/*     */     throws IOException
/*     */   {
/* 102 */     this.C = paramInt;
/*     */   }
/*     */ 
/*     */   final int D()
/*     */     throws IOException
/*     */   {
/* 113 */     return this.C;
/*     */   }
/*     */ 
/*     */   void B()
/*     */     throws IOException
/*     */   {
/*     */   }
/*     */ 
/*     */   void E()
/*     */     throws t
/*     */   {
/*     */   }
/*     */ 
/*     */   String C()
/*     */     throws IOException
/*     */   {
/* 145 */     return null;
/*     */   }
/*     */ 
/*     */   void A(String paramString)
/*     */     throws IOException
/*     */   {
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.A.c
 * JD-Core Version:    0.6.0
 */