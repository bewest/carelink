/*     */ package minimed.ddms.applet.dtw;
/*     */ 
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ public final class PropertyWriter
/*     */ {
/*     */   private static final String INTRA_SEP = " = ";
/*     */   private static final String INTER_SEP = ", ";
/*     */   private final StringBuffer m_buffer;
/*     */   private boolean m_notFirst;
/*     */ 
/*     */   public PropertyWriter()
/*     */   {
/*  59 */     this.m_buffer = new StringBuffer();
/*     */   }
/*     */ 
/*     */   public PropertyWriter(String paramString)
/*     */   {
/*  69 */     Contract.preNonNull(paramString);
/*  70 */     this.m_buffer = new StringBuffer(paramString);
/*  71 */     this.m_notFirst = true;
/*     */   }
/*     */ 
/*     */   public void add(String paramString, boolean paramBoolean)
/*     */   {
/*  84 */     Contract.preString(paramString);
/*  85 */     add(paramString, Boolean.valueOf(paramBoolean));
/*     */   }
/*     */ 
/*     */   public void add(String paramString, long paramLong)
/*     */   {
/*  96 */     Contract.preString(paramString);
/*  97 */     add(paramString, new Long(paramLong));
/*     */   }
/*     */ 
/*     */   public void add(String paramString, Object paramObject)
/*     */   {
/* 108 */     Contract.preString(paramString);
/* 109 */     if (this.m_notFirst)
/* 110 */       this.m_buffer.append(", ");
/*     */     else {
/* 112 */       this.m_notFirst = true;
/*     */     }
/* 114 */     this.m_buffer.append(paramString + " = " + paramObject);
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 123 */     return this.m_buffer.toString();
/*     */   }
/*     */ 
/*     */   public boolean equals(Object paramObject)
/*     */   {
/* 134 */     Contract.unreachable();
/* 135 */     return false;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 145 */     Contract.unreachable();
/* 146 */     return 0;
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.PropertyWriter
 * JD-Core Version:    0.6.0
 */