/*     */ package minimed.ddms.applet.dtw;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ 
/*     */ public final class ExeHelper
/*     */ {
/*     */   private static final int STATUS_ERROR = 1;
/*     */ 
/*     */   public int execute(String[] paramArrayOfString, File paramFile)
/*     */     throws IOException, InterruptedException
/*     */   {
/*  57 */     int i = 1;
/*     */     Process localProcess;
/*  61 */     if (paramFile != null)
/*  62 */       localProcess = Runtime.getRuntime().exec(paramArrayOfString, null, paramFile);
/*     */     else {
/*  64 */       localProcess = Runtime.getRuntime().exec(paramArrayOfString);
/*     */     }
/*  66 */     StreamGobbler localStreamGobbler1 = new StreamGobbler(localProcess.getErrorStream(), null);
/*  67 */     StreamGobbler localStreamGobbler2 = new StreamGobbler(localProcess.getInputStream(), null);
/*     */ 
/*  70 */     new Thread(localStreamGobbler1).start();
/*  71 */     new Thread(localStreamGobbler2).start();
/*     */ 
/*  74 */     i = localProcess.waitFor();
/*     */ 
/*  77 */     return i;
/*     */   }
/*     */ 
/*     */   private static class StreamGobbler
/*     */     implements Runnable
/*     */   {
/*     */     private final InputStream m_is;
/*     */ 
/*     */     private StreamGobbler(InputStream paramInputStream)
/*     */     {
/*  97 */       this.m_is = paramInputStream;
/*     */     }
/*     */ 
/*     */     public void run()
/*     */     {
/*     */       try
/*     */       {
/* 107 */         InputStreamReader localInputStreamReader = new InputStreamReader(this.m_is);
/* 108 */         BufferedReader localBufferedReader = new BufferedReader(localInputStreamReader);
/* 109 */         while (localBufferedReader.readLine() != null);
/*     */       }
/*     */       catch (IOException localIOException)
/*     */       {
/*     */       }
/*     */     }
/*     */ 
/*     */     StreamGobbler(InputStream paramInputStream, ExeHelper.1 param1)
/*     */     {
/*  83 */       this(paramInputStream);
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.ExeHelper
 * JD-Core Version:    0.6.0
 */