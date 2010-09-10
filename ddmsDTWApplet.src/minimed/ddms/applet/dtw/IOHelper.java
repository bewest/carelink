/*    */ package minimed.ddms.applet.dtw;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.io.OutputStream;
/*    */ 
/*    */ public class IOHelper
/*    */ {
/*    */   private static final int F_BUFSIZE = 256;
/*    */   private static final int F_EOF = -1;
/*    */ 
/*    */   public final void writeToStream(InputStream paramInputStream, OutputStream paramOutputStream)
/*    */     throws IOException
/*    */   {
/*    */     try
/*    */     {
/* 52 */       byte[] arrayOfByte = new byte[256];
/* 53 */       int i = 0;
/* 54 */       while ((i = paramInputStream.read(arrayOfByte)) != -1)
/* 55 */         paramOutputStream.write(arrayOfByte, 0, i);
/*    */     }
/*    */     finally {
/* 58 */       paramInputStream.close();
/* 59 */       paramOutputStream.flush();
/* 60 */       paramOutputStream.close();
/*    */     }
/*    */   }
/*    */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.IOHelper
 * JD-Core Version:    0.6.0
 */