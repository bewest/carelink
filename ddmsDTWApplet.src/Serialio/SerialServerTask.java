/*      */ package Serialio;
/*      */ 
/*      */ import java.io.IOException;
/*      */ 
/*      */ class SerialServerTask extends Thread
/*      */ {
/*      */   private SerialPortLocal sp;
/*      */   private int portNum;
/* 2112 */   private boolean abortRequest = false;
/*      */   public String eMsg;
/*      */ 
/*      */   SerialServerTask(SerialPortLocal paramSerialPortLocal)
/*      */   {
/* 2117 */     this.sp = paramSerialPortLocal;
/*      */   }
/*      */ 
/*      */   public void run()
/*      */   {
/* 2123 */     setPriority(getPriority() + 1);
/*      */     try
/*      */     {
/* 2126 */       int i = this.sp.SerServerTask();
/*      */     } catch (IOException localIOException) {
/* 2128 */       this.eMsg = localIOException.getMessage();
/* 2129 */       this.sp.getConfig().setPortNum(-1);
/*      */     }
/*      */   }
/*      */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     Serialio.SerialServerTask
 * JD-Core Version:    0.6.0
 */