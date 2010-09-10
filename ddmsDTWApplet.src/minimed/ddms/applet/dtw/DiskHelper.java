/*     */ package minimed.ddms.applet.dtw;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.io.PrintWriter;
/*     */ import java.io.StringWriter;
/*     */ import java.security.DigestInputStream;
/*     */ import java.security.MessageDigest;
/*     */ import java.security.NoSuchAlgorithmException;
/*     */ import java.util.ResourceBundle;
/*     */ 
/*     */ public final class DiskHelper extends IOHelper
/*     */ {
/*     */   private static final int DIGEST_BUF_SIZE = 4096;
/*     */ 
/*     */   public boolean doesFileExist(String paramString1, String paramString2)
/*     */   {
/*  44 */     int i = 0;
/*  45 */     File localFile = new File(paramString1 + File.separator + paramString2);
/*  46 */     if ((localFile.exists()) && (localFile.isFile())) {
/*  47 */       i = 1;
/*     */     }
/*  49 */     return i;
/*     */   }
/*     */ 
/*     */   public boolean doesFileDigestMatch(String paramString1, String paramString2, String paramString3, String paramString4, PrintWriter paramPrintWriter) throws IllegalStateException
/*     */   {
/*  69 */     paramPrintWriter.println("Compute an " + paramString4 + " digest for " + paramString1 + File.separator + paramString2);
/*     */     FileInputStream localFileInputStream;
/*     */     try {
/*  73 */       localFileInputStream = new FileInputStream(paramString1 + File.separator + paramString2);
/*     */     }
/*     */     catch (FileNotFoundException localFileNotFoundException)
/*     */     {
/*  77 */       localFileNotFoundException.printStackTrace(paramPrintWriter);
/*  78 */       return false;
/*     */     }
/*     */     MessageDigest localMessageDigest;
/*     */     try {
/*  83 */       localMessageDigest = MessageDigest.getInstance(paramString4);
/*     */     }
/*     */     catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
/*     */     {
/*  88 */       localNoSuchAlgorithmException.printStackTrace(paramPrintWriter);
/*  89 */       throw new IllegalStateException("getting " + paramString4);
/*     */     }
/*     */ 
/*  92 */     DigestInputStream localDigestInputStream = new DigestInputStream(localFileInputStream, localMessageDigest);
/*  93 */     byte[] arrayOfByte1 = new byte[4096];
/*     */     try {
/*  95 */       while (localDigestInputStream.read(arrayOfByte1, 0, 4096) == 4096);
/*  98 */       localDigestInputStream.close();
/*     */     }
/*     */     catch (IOException localIOException)
/*     */     {
/* 102 */       localIOException.printStackTrace(paramPrintWriter);
/* 103 */       throw new IllegalStateException("reading into digest");
/*     */     }
/*     */ 
/* 106 */     byte[] arrayOfByte2 = localDigestInputStream.getMessageDigest().digest();
/*     */ 
/* 110 */     StringWriter localStringWriter = new StringWriter();
/* 111 */     PrintWriter localPrintWriter = new PrintWriter(localStringWriter);
/* 112 */     for (int i = 0; i < arrayOfByte2.length; i++) {
/* 113 */       if (i > 0) {
/* 114 */         localPrintWriter.print(" ");
/*     */       }
/* 116 */       localPrintWriter.print(arrayOfByte2[i]);
/*     */     }
/* 118 */     localPrintWriter.close();
/*     */ 
/* 120 */     String str = localStringWriter.toString();
/*     */ 
/* 122 */     paramPrintWriter.println("Local " + paramString4 + " digest is '" + str + "'");
/* 123 */     paramPrintWriter.println("Remote " + paramString4 + " digest is '" + paramString3 + "'");
/*     */ 
/* 125 */     return paramString3.equals(str);
/*     */   }
/*     */ 
/*     */   public File saveFile(String paramString1, String paramString2, byte[] paramArrayOfByte, PrintWriter paramPrintWriter)
/*     */     throws SaveFailedException
/*     */   {
/* 142 */     ResourceBundle localResourceBundle = DTWApplet.getResourceBundle();
/*     */ 
/* 144 */     String str1 = paramString1 + File.separator + paramString2;
/* 145 */     File localFile1 = new File(paramString1);
/* 146 */     if (!localFile1.exists()) {
/* 147 */       localFile1.mkdirs();
/*     */     }
/*     */ 
/* 150 */     File localFile2 = new File(str1);
/*     */     Object localObject;
/* 151 */     if ((localFile2.exists()) && (!localFile2.canWrite())) {
/* 152 */       localObject = MessageHelper.format(localResourceBundle.getString("error.MSG_FILE_OVERWRITE_ERROR"), new Object[] { getViewableName(str1) });
/*     */ 
/* 154 */       throw new SaveFailedException((String)localObject);
/*     */     }
/*     */     String str2;
/*     */     try {
/* 159 */       localObject = new FileOutputStream(str1);
/*     */     } catch (FileNotFoundException localFileNotFoundException) {
/* 161 */       localFileNotFoundException.printStackTrace(paramPrintWriter);
/* 162 */       str2 = MessageHelper.format(localResourceBundle.getString("error.MSG_FILE_OPEN_ERROR"), new Object[] { getViewableName(str1) });
/*     */ 
/* 164 */       throw new SaveFailedException(str2);
/*     */     }
/*     */     try
/*     */     {
/* 168 */       writeToStream(new ByteArrayInputStream(paramArrayOfByte), (OutputStream)localObject);
/*     */     } catch (IOException localIOException) {
/* 170 */       localIOException.printStackTrace(paramPrintWriter);
/* 171 */       str2 = MessageHelper.format(localResourceBundle.getString("error.MSG_FILE_WRITE_ERROR"), new Object[] { getViewableName(str1) });
/*     */ 
/* 173 */       throw new SaveFailedException(str2);
/*     */     }
/* 175 */     return (File)localFile2;
/*     */   }
/*     */ 
/*     */   public String getViewableName(String paramString)
/*     */   {
/* 186 */     String str = paramString;
/*     */     try {
/* 188 */       str = new File(paramString).getCanonicalPath();
/*     */     }
/*     */     catch (IOException localIOException) {
/*     */     }
/* 192 */     return str;
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.DiskHelper
 * JD-Core Version:    0.6.0
 */