/*     */ package minimed.ddms.applet.dtw;
/*     */ 
/*     */ import java.text.MessageFormat;
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ public final class MessageHelper
/*     */ {
/*     */   public static final String TAG_HTML_BEGIN = "<html>";
/*     */   public static final String TAG_HTML_END = "</html>";
/*     */ 
/*     */   public static String format(String paramString, Object[] paramArrayOfObject)
/*     */   {
/*  60 */     return MessageFormat.format(escape(paramString), paramArrayOfObject);
/*     */   }
/*     */ 
/*     */   public static String addHtmlDocumentTags(String paramString)
/*     */   {
/*  70 */     return "<html>" + paramString + "</html>";
/*     */   }
/*     */ 
/*     */   public static String removeHtmlDocumentTags(String paramString)
/*     */   {
/*  82 */     Contract.preNonNull(paramString);
/*  83 */     String str = new String(paramString);
/*  84 */     str = str.replaceAll("<html>", "");
/*  85 */     str = str.replaceAll("</html>", "");
/*  86 */     return str;
/*     */   }
/*     */ 
/*     */   private static String escape(String paramString)
/*     */   {
/*  96 */     if ((paramString == null) || (paramString.indexOf('\'') < 0)) {
/*  97 */       return paramString;
/*     */     }
/*     */ 
/* 100 */     int i = paramString.length();
/* 101 */     StringBuffer localStringBuffer = new StringBuffer(i);
/* 102 */     for (int j = 0; j < i; j++) {
/* 103 */       char c = paramString.charAt(j);
/* 104 */       if (c == '\'') {
/* 105 */         localStringBuffer.append('\'');
/*     */       }
/* 107 */       localStringBuffer.append(c);
/*     */     }
/* 109 */     return localStringBuffer.toString();
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.MessageHelper
 * JD-Core Version:    0.6.0
 */