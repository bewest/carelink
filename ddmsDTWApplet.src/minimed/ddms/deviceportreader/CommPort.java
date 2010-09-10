/*    */ package minimed.ddms.deviceportreader;
/*    */ 
/*    */ abstract class CommPort
/*    */ {
/* 33 */   private boolean m_continueIO = true;
/*    */ 
/*    */   final void checkForSerialIOHalted()
/*    */     throws SerialIOHaltedException
/*    */   {
/* 47 */     if (!this.m_continueIO) {
/* 48 */       MedicalDevice.logInfoHigh(this, "checkForSerialIOHalted: serial IO has been halted.");
/* 49 */       throw new SerialIOHaltedException("Serial IO has been halted.");
/*    */     }
/*    */   }
/*    */ 
/*    */   final void setContinueIO(boolean paramBoolean)
/*    */   {
/* 59 */     MedicalDevice.logInfoHigh(this, "setContinueIO: continueIO = " + paramBoolean);
/* 60 */     this.m_continueIO = paramBoolean;
/*    */   }
/*    */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.deviceportreader.CommPort
 * JD-Core Version:    0.6.0
 */