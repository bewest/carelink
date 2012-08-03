/*    */ package BayerComm;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ 
/*    */ public class BCIBuf
/*    */ {
/* 91 */   private byte[] buf = null;
/*    */ 
/*    */   public BCIBuf(int paramInt)
/*    */   {
/*  7 */     this.buf = new byte[paramInt];
/*    */   }
/*    */ 
/*    */   public byte[] getBuf() {
/* 11 */     return this.buf;
/*    */   }
/*    */ 
/*    */   public String asString()
/*    */   {
/* 16 */     String str = new String("");
/*    */ 
/* 18 */     for (int i = 0; i < this.buf.length; i++) {
/* 19 */       char c = (char)this.buf[i];
/* 20 */       if (c == 0)
/*    */         break;
/* 22 */       str = str + c;
/*    */     }
/*    */ 
/* 25 */     return str;
/*    */   }
/*    */ 
/*    */   public boolean setBuf(byte paramByte, int paramInt)
/*    */   {
/* 31 */     int i = 0;
/*    */ 
/* 33 */     if (paramInt < this.buf.length) {
/* 34 */       this.buf[paramInt] = paramByte;
/* 35 */       i = 1;
/*    */     }
/* 37 */     return i;
/*    */   }
/*    */ 
/*    */   public boolean setBuf(String paramString) {
/* 41 */     byte[] arrayOfByte = paramString.getBytes();
/*    */ 
/* 43 */     return setBuf(arrayOfByte, arrayOfByte.length);
/*    */   }
/*    */ 
/*    */   public boolean setBuf(byte[] paramArrayOfByte, int paramInt) {
/* 47 */     int i = 0;
/*    */ 
/* 49 */     if (paramInt <= this.buf.length) {
/*    */       while (true) { paramInt--; if (paramInt < 0) break;
/* 51 */         this.buf[paramInt] = paramArrayOfByte[paramInt]; }
/* 52 */       i = 1;
/*    */     }
/*    */ 
/* 55 */     return i;
/*    */   }
/*    */   public int getLength() {
/* 58 */     return this.buf.length;
/*    */   }
/*    */ 
/*    */   public void trace() {
/* 62 */     trace(this.buf);
/*    */   }
/*    */ 
/*    */   public static void trace(byte[] paramArrayOfByte) {
/* 66 */     System.out.println("[BUF START]");
/*    */ 
/* 68 */     for (int i = 0; i < paramArrayOfByte.length; )
/*    */     {
/* 70 */       String str1 = "";
/* 71 */       String str2 = "";
/*    */ 
/* 73 */       for (int j = 0; ((j < 16 ? 1 : 0) & (i < paramArrayOfByte.length ? 1 : 0)) != 0; i++)
/*    */       {
/* 75 */         int k = paramArrayOfByte[i];
/* 76 */         str1 = str1 + String.format("%02X ", new Object[] { Integer.valueOf(k) });
/*    */ 
/* 78 */         if ((k < 32) || (k > 126))
/* 79 */           k = 46;
/* 80 */         str2 = str2 + String.format("%c", new Object[] { Integer.valueOf(k) });
/*    */ 
/* 73 */         j++;
/*    */       }
/*    */ 
/* 82 */       for (; j < 16; j++)
/*    */       {
/* 84 */         str1 = str1 + "   ";
/* 85 */         str2 = str2 + " ";
/*    */       }
/* 87 */       System.out.println(str1 + " [" + str2 + "]");
/*    */     }
/* 89 */     System.out.println("[BUF END]");
/*    */   }
/*    */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     BayerComm.BCIBuf
 * JD-Core Version:    0.6.0
 */