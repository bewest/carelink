/*     */ package minimed.ddms.applet.dtw;
/*     */ 
/*     */ public class OperationResult
/*     */ {
/*     */   public static final int OP_SUCCEEDED = 0;
/*     */   public static final int OP_FAILED = 1;
/*     */   public static final int OP_CANCELLED = 2;
/*     */   private int m_result;
/*     */   private String m_reason;
/*     */ 
/*     */   public OperationResult(int paramInt, String paramString)
/*     */   {
/*  58 */     this.m_result = paramInt;
/*  59 */     this.m_reason = paramString;
/*     */   }
/*     */ 
/*     */   public OperationResult()
/*     */   {
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/*  77 */     PropertyWriter localPropertyWriter = new PropertyWriter();
/*  78 */     localPropertyWriter.add("result", this.m_result);
/*  79 */     localPropertyWriter.add("reason", this.m_reason);
/*  80 */     return localPropertyWriter.toString();
/*     */   }
/*     */ 
/*     */   public final int getResult()
/*     */   {
/*  89 */     return this.m_result;
/*     */   }
/*     */ 
/*     */   public final void setResult(int paramInt)
/*     */   {
/*  98 */     this.m_result = paramInt;
/*     */   }
/*     */ 
/*     */   public final String getReason()
/*     */   {
/* 107 */     return this.m_reason;
/*     */   }
/*     */ 
/*     */   public final void setReason(String paramString)
/*     */   {
/* 116 */     this.m_reason = paramString;
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.OperationResult
 * JD-Core Version:    0.6.0
 */