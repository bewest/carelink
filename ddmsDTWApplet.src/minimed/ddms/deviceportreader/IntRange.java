/*     */ package minimed.ddms.deviceportreader;
/*     */ 
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ public class IntRange
/*     */ {
/*     */   private final int m_minimum;
/*     */   private final int m_maximum;
/*     */   private final int m_default;
/*     */   private final String m_description;
/*     */   private final int m_precision;
/*     */ 
/*     */   public IntRange(int paramInt1, int paramInt2, int paramInt3, String paramString, int paramInt4)
/*     */   {
/*  54 */     Contract.pre(paramInt4 >= 1);
/*  55 */     Contract.pre(paramInt1 <= paramInt2);
/*  56 */     this.m_minimum = paramInt1;
/*  57 */     this.m_maximum = paramInt2;
/*  58 */     this.m_precision = paramInt4;
/*     */ 
/*  60 */     verifyInRangePre(paramInt3);
/*  61 */     this.m_default = paramInt3;
/*  62 */     this.m_description = paramString;
/*     */   }
/*     */ 
/*     */   public IntRange(int paramInt1, int paramInt2, int paramInt3, String paramString)
/*     */   {
/*  75 */     this(paramInt1, paramInt2, paramInt3, paramString, 1);
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/*  88 */     return "Ranges for " + this.m_description + ": minimum=" + this.m_minimum + ", maximum=" + this.m_maximum + ", precision=" + this.m_precision;
/*     */   }
/*     */ 
/*     */   public void verifyInRange(int paramInt)
/*     */     throws BadDeviceValueException
/*     */   {
/*  99 */     MedicalDevice.Util.verifyDeviceValue(paramInt, this.m_minimum, this.m_maximum, this.m_description);
/* 100 */     MedicalDevice.Util.verifyDeviceValue(paramInt % this.m_precision == 0, "bad precision: " + paramInt);
/*     */   }
/*     */ 
/*     */   public void verifyInRangePre(int paramInt)
/*     */   {
/* 110 */     Contract.pre(paramInt, this.m_minimum, this.m_maximum);
/* 111 */     Contract.pre(paramInt % this.m_precision == 0);
/*     */   }
/*     */ 
/*     */   public int getMinimum()
/*     */   {
/* 120 */     return this.m_minimum;
/*     */   }
/*     */ 
/*     */   public int getMaximum()
/*     */   {
/* 129 */     return this.m_maximum;
/*     */   }
/*     */ 
/*     */   public int getDefault()
/*     */   {
/* 138 */     return this.m_default;
/*     */   }
/*     */ 
/*     */   public int getPrecision()
/*     */   {
/* 147 */     return this.m_precision;
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.deviceportreader.IntRange
 * JD-Core Version:    0.6.0
 */