/*     */ package minimed.ddms.applet.dtw;
/*     */ 
/*     */ import java.io.PrintWriter;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.security.GeneralSecurityException;
/*     */ import javax.crypto.Cipher;
/*     */ import javax.crypto.SecretKey;
/*     */ import javax.crypto.SecretKeyFactory;
/*     */ import javax.crypto.spec.DESKeySpec;
/*     */ import minimed.util.BASE64;
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ class ConsoleWriter extends PrintWriter
/*     */ {
/*     */   private static final String CHARSET = "UTF8";
/*     */   private static final String CIPHER_TYPE = "DES";
/*     */   private final String m_keyPhrase;
/*     */   private final Cipher m_cipher;
/*     */ 
/*     */   ConsoleWriter(String paramString)
/*     */   {
/*  50 */     super(System.out, true);
/*  51 */     Cipher localCipher = null;
/*  52 */     if (paramString != null) {
/*     */       try {
/*  54 */         DESKeySpec localDESKeySpec = new DESKeySpec(paramString.getBytes("UTF8"));
/*  55 */         SecretKeyFactory localSecretKeyFactory = SecretKeyFactory.getInstance("DES");
/*  56 */         SecretKey localSecretKey = localSecretKeyFactory.generateSecret(localDESKeySpec);
/*  57 */         localCipher = Cipher.getInstance("DES");
/*  58 */         localCipher.init(1, localSecretKey);
/*     */       } catch (UnsupportedEncodingException localUnsupportedEncodingException) {
/*  60 */         localUnsupportedEncodingException.printStackTrace(System.err);
/*  61 */         Contract.unreachable();
/*     */       }
/*     */       catch (GeneralSecurityException localGeneralSecurityException) {
/*  64 */         localGeneralSecurityException.printStackTrace(System.err);
/*  65 */         Contract.unreachable();
/*     */       }
/*     */     }
/*     */ 
/*  69 */     this.m_cipher = localCipher;
/*  70 */     this.m_keyPhrase = paramString;
/*     */   }
/*     */ 
/*     */   public String getEncryptionKey()
/*     */   {
/*  81 */     return this.m_keyPhrase;
/*     */   }
/*     */ 
/*     */   public void print(String paramString)
/*     */   {
/*  86 */     if (this.m_cipher != null) {
/*     */       try {
/*  88 */         byte[] arrayOfByte = paramString.getBytes("UTF8");
/*  89 */         paramString = BASE64.encode(encrypt(arrayOfByte));
/*  90 */         paramString = "<" + paramString + ">";
/*     */       } catch (UnsupportedEncodingException localUnsupportedEncodingException) {
/*  92 */         localUnsupportedEncodingException.printStackTrace(System.err);
/*  93 */         Contract.unreachable();
/*     */       }
/*     */       catch (GeneralSecurityException localGeneralSecurityException) {
/*  96 */         localGeneralSecurityException.printStackTrace(System.err);
/*  97 */         Contract.unreachable();
/*     */       }
/*     */     }
/*     */ 
/* 101 */     super.print(paramString);
/*     */   }
/*     */ 
/*     */   private synchronized byte[] encrypt(byte[] paramArrayOfByte)
/*     */     throws GeneralSecurityException
/*     */   {
/* 112 */     return this.m_cipher.doFinal(paramArrayOfByte);
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.ConsoleWriter
 * JD-Core Version:    0.6.0
 */