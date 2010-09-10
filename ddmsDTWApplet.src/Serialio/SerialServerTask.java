/*      */ package Serialio;
/*      */ 
/*      */ import java.io.IOException;
/*      */ 
/*      */ class SerialServerTask extends Thread
/*      */ {
/*      */   private SerialPortLocal sp;
/*      */   private int portNum;
/* 1722 */   private boolean abortRequest = false;
/*      */   public String eMsg;
/*      */ 
/*      */   SerialServerTask(SerialPortLocal paramSerialPortLocal)
/*      */   {
/* 1727 */     this.sp = paramSerialPortLocal;
/*      */   }
/*      */ 
/*      */   public void run()
/*      */   {
/* 1733 */     setPriority(getPriority() + 1);
/*      */     try
/*      */     {
/* 1736 */       int i = this.sp.SerServerTask();
/*      */     } catch (IOException localIOException) {
/* 1738 */       this.eMsg = localIOException.getMessage();
/* 1739 */       this.sp.getConfig().setPortNum(-1);
/*      */     }
/*      */   }
/*      */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     Serialio.SerialServerTask
 * JD-Core Version:    0.6.0
 */