/*      */ package Serialio;
/*      */ 
/*      */ class FlowControlTask extends Thread
/*      */ {
/*      */   private SerialPortLocal sp;
/*      */   private int portNum;
/* 2047 */   private boolean abortRequest = false;
/*      */ 
/*      */   FlowControlTask(SerialPortLocal paramSerialPortLocal) {
/* 2050 */     this.sp = paramSerialPortLocal;
/* 2051 */     this.portNum = this.sp.getPortNum();
/* 2052 */     SerialPortLocal.displayMsg("FlowControlTask created");
/*      */   }
/*      */ 
/*      */   public void run()
/*      */   {
/* 2057 */     setPriority(getPriority() + 1);
/*      */     do
/* 2059 */       int i = this.sp.SerFlowTask(this.portNum);
/* 2060 */     while (!this.abortRequest);
/*      */   }
/*      */ 
/*      */   public void abort()
/*      */   {
/* 2068 */     this.abortRequest = true;
/*      */   }
/*      */ 
/*      */   public void setFlowPriority(int paramInt)
/*      */   {
/* 2075 */     setPriority(paramInt);
/*      */   }
/*      */ 
/*      */   public int getFlowPriority()
/*      */   {
/* 2084 */     return getPriority();
/*      */   }
/*      */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     Serialio.FlowControlTask
 * JD-Core Version:    0.6.0
 */