/*     */ package minimed.ddms.applet.dtw;
/*     */ 
/*     */ import java.io.PrintWriter;
/*     */ import java.io.StringWriter;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ 
/*     */ public final class LogWriter extends PrintWriter
/*     */ {
/*     */   private final PrintWriter m_sec;
/*     */   private final SimpleDateFormat m_dateFormatter;
/*     */ 
/*     */   public LogWriter(boolean paramBoolean)
/*     */   {
/*  44 */     super(new StringWriter(), true);
/*  45 */     if (paramBoolean)
/*  46 */       this.m_sec = new PrintWriter(System.out, true);
/*     */     else {
/*  48 */       this.m_sec = null;
/*     */     }
/*     */ 
/*  52 */     this.m_dateFormatter = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss:SSSS");
/*     */   }
/*     */ 
/*     */   public final void logInfo(String paramString)
/*     */   {
/*  65 */     logMessage("INFO", paramString);
/*     */   }
/*     */ 
/*     */   public final void logWarning(String paramString)
/*     */   {
/*  74 */     logMessage("WARNING", paramString);
/*     */   }
/*     */ 
/*     */   public final void logError(String paramString)
/*     */   {
/*  83 */     logMessage("ERROR", paramString);
/*     */   }
/*     */ 
/*     */   private void logMessage(String paramString1, String paramString2)
/*     */   {
/*  94 */     Date localDate = new Date();
/*  95 */     String str = this.m_dateFormatter.format(localDate);
/*  96 */     println(str + " " + paramString1 + " " + paramString2);
/*     */   }
/*     */ 
/*     */   public void flush()
/*     */   {
/* 106 */     super.flush();
/* 107 */     if (this.m_sec != null)
/* 108 */       this.m_sec.flush();
/*     */   }
/*     */ 
/*     */   public void print(boolean paramBoolean)
/*     */   {
/* 118 */     super.print(paramBoolean);
/* 119 */     if (this.m_sec != null)
/* 120 */       this.m_sec.print(paramBoolean);
/*     */   }
/*     */ 
/*     */   public void print(char paramChar)
/*     */   {
/* 130 */     super.print(paramChar);
/* 131 */     if (this.m_sec != null)
/* 132 */       this.m_sec.print(paramChar);
/*     */   }
/*     */ 
/*     */   public void print(char[] paramArrayOfChar)
/*     */   {
/* 142 */     super.print(paramArrayOfChar);
/* 143 */     if (this.m_sec != null)
/* 144 */       this.m_sec.print(paramArrayOfChar);
/*     */   }
/*     */ 
/*     */   public void print(double paramDouble)
/*     */   {
/* 154 */     super.print(paramDouble);
/* 155 */     if (this.m_sec != null)
/* 156 */       this.m_sec.print(paramDouble);
/*     */   }
/*     */ 
/*     */   public void print(float paramFloat)
/*     */   {
/* 166 */     super.print(paramFloat);
/* 167 */     if (this.m_sec != null)
/* 168 */       this.m_sec.print(paramFloat);
/*     */   }
/*     */ 
/*     */   public void print(int paramInt)
/*     */   {
/* 178 */     super.print(paramInt);
/* 179 */     if (this.m_sec != null)
/* 180 */       this.m_sec.print(paramInt);
/*     */   }
/*     */ 
/*     */   public void print(long paramLong)
/*     */   {
/* 190 */     super.print(paramLong);
/* 191 */     if (this.m_sec != null)
/* 192 */       this.m_sec.print(paramLong);
/*     */   }
/*     */ 
/*     */   public void print(Object paramObject)
/*     */   {
/* 202 */     super.print(paramObject);
/* 203 */     if (this.m_sec != null)
/* 204 */       this.m_sec.print(paramObject);
/*     */   }
/*     */ 
/*     */   public void print(String paramString)
/*     */   {
/* 214 */     super.print(paramString);
/* 215 */     if (this.m_sec != null)
/* 216 */       this.m_sec.print(paramString);
/*     */   }
/*     */ 
/*     */   public void println()
/*     */   {
/* 224 */     super.println();
/* 225 */     if (this.m_sec != null)
/* 226 */       this.m_sec.println();
/*     */   }
/*     */ 
/*     */   public String getBackingStore()
/*     */   {
/* 239 */     flush();
/* 240 */     return this.out.toString();
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.LogWriter
 * JD-Core Version:    0.6.0
 */