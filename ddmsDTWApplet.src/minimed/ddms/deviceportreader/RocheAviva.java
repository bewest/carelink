/*     */ package minimed.ddms.A;
/*     */ 
/*     */ public class S extends N
/*     */ {
/*     */   private static final int ό = 172;
/*  35 */   private static final int[] ϓ = { 67, 9, 49, 13 };
/*  36 */   private static final int[] ϒ = { 67, 9, 51, 13 };
/*  37 */   private static final int[] ϕ = { 67, 9, 52, 13 };
/*  38 */   private static final int[] ύ = { 83, 9, 49, 13 };
/*  39 */   private static final int[] ϑ = { 83, 9, 50, 13 };
/*  40 */   private static final int[] ϖ = { 96, 13 };
/*     */ 
/*  46 */   private static final int[] ϔ = { 97, 9, 49, 9, 48, 48, 49, 13 };
/*     */   private static final int ώ = 31;
/*     */   private static final int ϐ = 15500;
/*     */ 
/*     */   public S()
/*     */   {
/*  60 */     super("Roche Aviva", 172, 22, ϓ, ϒ, ϑ, ύ, ϕ, ϖ, ϔ, 15500, 31);
/*     */   }
/*     */ 
/*     */   String U(int[] paramArrayOfInt)
/*     */     throws Z
/*     */   {
/*  85 */     int i = -1;
/*  86 */     for (int j = 0; (j < paramArrayOfInt.length) && (i == -1); j++) {
/*  87 */       if (paramArrayOfInt[j] == 9) {
/*  88 */         i = j;
/*     */       }
/*     */     }
/*     */ 
/*  92 */     if (i == -1) {
/*  93 */       throw new Z("extractData: can find TAB1 in response " + O._B.D(paramArrayOfInt));
/*     */     }
/*     */ 
/*  98 */     j = -1;
/*  99 */     for (int k = i + 1; (k < paramArrayOfInt.length) && (j == -1); k++) {
/* 100 */       if (paramArrayOfInt[k] == 9) {
/* 101 */         j = k;
/*     */       }
/*     */     }
/*     */ 
/* 105 */     if (j == -1) {
/* 106 */       throw new Z("extractData: can find TAB2 in response " + O._B.D(paramArrayOfInt));
/*     */     }
/*     */ 
/* 111 */     k = j - i - 1;
/* 112 */     if (k < 1) {
/* 113 */       throw new Z("extractData: response length (" + k + ") too short: " + O._B.D(paramArrayOfInt));
/*     */     }
/*     */ 
/* 118 */     int[] arrayOfInt = new int[k];
/*     */ 
/* 120 */     System.arraycopy(paramArrayOfInt, i + 1, arrayOfInt, 0, k);
/* 121 */     return O._B.E(arrayOfInt);
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.A.S
 * JD-Core Version:    0.6.0
 */