/*     */ package minimed.ddms.applet.dtw;
/*     */ 
/*     */ import java.io.PrintWriter;
/*     */ import java.io.StringWriter;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ 
/*     */ public final class LogWriter extends PrintWriter
/*     */ {
/*     */   private ConsoleWriter m_sec;
/*     */   private final SimpleDateFormat m_dateFormatter;
/*     */ 
/*     */   public LogWriter(boolean paramBoolean, String paramString)
/*     */   {
/*  44 */     super(new StringWriter(), true);
/*  45 */     setLogToConsole(paramBoolean, paramString);
/*     */ 
/*  48 */     this.m_dateFormatter = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss:SSSS");
/*     */   }
/*     */ 
/*     */   public void setLogToConsole(boolean paramBoolean, String paramString)
/*     */   {
/*  61 */     if (this.m_sec != null) {
/*  62 */       this.m_sec.flush();
/*     */     }
/*  64 */     if (paramBoolean)
/*  65 */       this.m_sec = new ConsoleWriter(paramString);
/*     */     else
/*  67 */       this.m_sec = null;
/*     */   }
/*     */ 
/*     */   public String getConsoleEncryptionKey()
/*     */   {
/*  77 */     String str = null;
/*  78 */     if (this.m_sec != null) {
/*  79 */       str = this.m_sec.getEncryptionKey();
/*     */     }
/*  81 */     return str;
/*     */   }
/*     */ 
/*     */   public void logInfo(String paramString)
/*     */   {
/*  92 */     logMessage("INFO", paramString);
/*     */   }
/*     */ 
/*     */   public void logWarning(String paramString)
/*     */   {
/* 101 */     logMessage("WARNING", paramString);
/*     */   }
/*     */ 
/*     */   public void logError(String paramString)
/*     */   {
/* 110 */     logMessage("ERROR", paramString);
/*     */   }
/*     */ 
/*     */   private void logMessage(String paramString1, String paramString2)
/*     */   {
/* 121 */     Date localDate = new Date();
/* 122 */     String str = this.m_dateFormatter.format(localDate);
/* 123 */     println(str + " " + paramString1 + " " + paramString2);
/*     */   }
/*     */ 
/*     */   public void flush()
/*     */   {
/* 134 */     super.flush();
/* 135 */     if (this.m_sec != null)
/* 136 */       this.m_sec.flush();
/*     */   }
/*     */ 
/*     */   public void print(boolean paramBoolean)
/*     */   {
/* 147 */     super.print(paramBoolean);
/* 148 */     if (this.m_sec != null)
/* 149 */       this.m_sec.print(paramBoolean);
/*     */   }
/*     */ 
/*     */   public void print(char paramChar)
/*     */   {
/* 160 */     super.print(paramChar);
/* 161 */     if (this.m_sec != null)
/* 162 */       this.m_sec.print(paramChar);
/*     */   }
/*     */ 
/*     */   public void print(char[] paramArrayOfChar)
/*     */   {
/* 173 */     super.print(paramArrayOfChar);
/* 174 */     if (this.m_sec != null)
/* 175 */       this.m_sec.print(paramArrayOfChar);
/*     */   }
/*     */ 
/*     */   public void print(double paramDouble)
/*     */   {
/* 186 */     super.print(paramDouble);
/* 187 */     if (this.m_sec != null)
/* 188 */       this.m_sec.print(paramDouble);
/*     */   }
/*     */ 
/*     */   public void print(float paramFloat)
/*     */   {
/* 199 */     super.print(paramFloat);
/* 200 */     if (this.m_sec != null)
/* 201 */       this.m_sec.print(paramFloat);
/*     */   }
/*     */ 
/*     */   public void print(int paramInt)
/*     */   {
/* 212 */     super.print(paramInt);
/* 213 */     if (this.m_sec != null)
/* 214 */       this.m_sec.print(paramInt);
/*     */   }
/*     */ 
/*     */   public void print(long paramLong)
/*     */   {
/* 225 */     super.print(paramLong);
/* 226 */     if (this.m_sec != null)
/* 227 */       this.m_sec.print(paramLong);
/*     */   }
/*     */ 
/*     */   public void print(Object paramObject)
/*     */   {
/* 238 */     super.print(paramObject);
/* 239 */     if (this.m_sec != null)
/* 240 */       this.m_sec.print(paramObject);
/*     */   }
/*     */ 
/*     */   public void print(String paramString)
/*     */   {
/* 251 */     super.print(paramString);
/* 252 */     if (this.m_sec != null)
/* 253 */       this.m_sec.print(paramString);
/*     */   }
/*     */ 
/*     */   public void println()
/*     */   {
/* 262 */     super.println();
/* 263 */     if (this.m_sec != null)
/* 264 */       this.m_sec.println();
/*     */   }
/*     */ 
/*     */   public String getBackingStore()
/*     */   {
/* 277 */     flush();
/* 278 */     return this.out.toString();
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.LogWriter
 * JD-Core Version:    0.6.0
 */