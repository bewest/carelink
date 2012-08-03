/*    */ package minimed.util;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import sun.misc.BASE64Decoder;
/*    */ import sun.misc.BASE64Encoder;
/*    */ 
/*    */ public class BASE64
/*    */ {
/*    */   public static String encode(byte[] paramArrayOfByte)
/*    */   {
/* 46 */     return new BASE64Encoder().encode(paramArrayOfByte);
/*    */   }
/*    */ 
/*    */   public static byte[] decode(String paramString)
/*    */   {
/*    */     try
/*    */     {
/* 58 */       return new BASE64Decoder().decodeBuffer(paramString);
/*    */     } catch (IOException localIOException) {
/*    */     }
/* 61 */     throw new IllegalStateException(localIOException.getMessage());
/*    */   }
/*    */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.util.BASE64
 * JD-Core Version:    0.6.0
 */