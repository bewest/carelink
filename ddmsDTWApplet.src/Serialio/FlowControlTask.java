/*      */ package Serialio;
/*      */ 
/*      */ import java.io.PrintStream;
/*      */ 
/*      */ class FlowControlTask extends Thread
/*      */ {
/*      */   private SerialPortLocal sp;
/*      */   private int portNum;
/* 1657 */   private boolean abortRequest = false;
/*      */ 
/*      */   FlowControlTask(SerialPortLocal paramSerialPortLocal) {
/* 1660 */     this.sp = paramSerialPortLocal;
/* 1661 */     this.portNum = this.sp.getPortNum();
/* 1662 */     System.out.println("FlowControlTask created");
/*      */   }
/*      */ 
/*      */   public void run()
/*      */   {
/* 1667 */     setPriority(getPriority() + 1);
/*      */     do
/* 1669 */       int i = this.sp.SerFlowTask(this.portNum);
/* 1670 */     while (!this.abortRequest);
/*      */   }
/*      */ 
/*      */   public void abort()
/*      */   {
/* 1678 */     this.abortRequest = true;
/*      */   }
/*      */ 
/*      */   public void setFlowPriority(int paramInt)
/*      */   {
/* 1685 */     setPriority(paramInt);
/*      */   }
/*      */ 
/*      */   public int getFlowPriority()
/*      */   {
/* 1694 */     return getPriority();
/*      */   }
/*      */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     Serialio.FlowControlTask
 * JD-Core Version:    0.6.0
 */