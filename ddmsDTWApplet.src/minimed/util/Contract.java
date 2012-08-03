/*     */ package minimed.util;
/*     */ 
/*     */ public class Contract
/*     */ {
/*     */   public static void pre(boolean paramBoolean)
/*     */   {
/*  41 */     if (!paramBoolean)
/*  42 */       throw new ContractException("Precondition failed");
/*     */   }
/*     */ 
/*     */   public static void pre(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/*  55 */     pre((paramInt1 >= paramInt2) && (paramInt1 <= paramInt3));
/*     */   }
/*     */ 
/*     */   public static void pre(double paramDouble1, double paramDouble2, double paramDouble3)
/*     */   {
/*  67 */     pre((paramDouble1 >= paramDouble2) && (paramDouble1 <= paramDouble3));
/*     */   }
/*     */ 
/*     */   public static void preNonNull(Object paramObject)
/*     */   {
/*  76 */     pre(paramObject != null);
/*     */   }
/*     */ 
/*     */   public static void preString(String paramString)
/*     */   {
/*  85 */     preNonNull(paramString);
/*  86 */     pre(paramString.length() > 0);
/*     */   }
/*     */ 
/*     */   public static void post(boolean paramBoolean)
/*     */   {
/*  95 */     if (!paramBoolean)
/*  96 */       throw new ContractException("Postcondition failed");
/*     */   }
/*     */ 
/*     */   public static void postNonNull(Object paramObject)
/*     */   {
/* 106 */     post(paramObject != null);
/*     */   }
/*     */ 
/*     */   public static void postString(String paramString)
/*     */   {
/* 115 */     postNonNull(paramString);
/* 116 */     post(paramString.length() > 0);
/*     */   }
/*     */ 
/*     */   public static void post(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 128 */     post((paramInt1 >= paramInt2) && (paramInt1 <= paramInt3));
/*     */   }
/*     */ 
/*     */   public static void invariant(boolean paramBoolean)
/*     */   {
/* 137 */     if (!paramBoolean)
/* 138 */       throw new ContractException("Invariant failed");
/*     */   }
/*     */ 
/*     */   public static void unreachable()
/*     */   {
/* 147 */     throw new ContractException("Reached unreachable code");
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.util.Contract
 * JD-Core Version:    0.6.0
 */