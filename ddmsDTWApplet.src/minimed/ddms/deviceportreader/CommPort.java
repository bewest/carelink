/*     */ package minimed.ddms.deviceportreader;
/*     */ 
/*     */ import java.io.IOException;
/*     */ 
/*     */ abstract class CommPort // c
/*     */ {
/*  35 */   private boolean m_continueIO = true; // B
/*     */   private int m_readTimeOut; // C
/*     */   private int m_ioDelay; // A
/*     */   // final void G()
/*     */   final void checkForSerialIOHalted()
/*     */     throws SerialIOHaltedException
/*     */   {
/*  52 */     if (!this.m_continueIO) {
/*  53 */       MedicalDevice.logInfoHigh(this, "checkForSerialIOHalted: serial IO has been halted.");
/*  54 */       throw new SerialIOHaltedException("Serial IO has been halted.");
/*     */     }
/*     */   }
/*     */   // final void A(boolean paramBoolean)
/*     */   final void setContinueIO(boolean paramBoolean)
/*     */   {
/*  64 */     MedicalDevice.logInfoHigh(this, "setContinueIO: continueIO = " + paramBoolean);
/*  65 */     this.m_continueIO = paramBoolean;
/*     */   }
/*     */ 
/*     */   void A()
/*     */   {
/*     */   }
/*     */   // final void A(int paramInt)
/*     */   final void setIoDelay(int paramInt)
/*     */   {
/*  82 */     this.m_ioDelay = paramInt;
/*     */   }
/*     */   // final int F()
/*     */   final int getIoDelay()
/*     */   {
/*  91 */     return this.m_ioDelay;
/*     */   }
/*     */   // void B(int paramInt)
/*     */   final void setReadTimeOut(int paramInt)
/*     */     throws IOException
/*     */   {
/* 102 */     this.m_readTimeOut = paramInt;
/*     */   }
/*     */   // final int D()
/*     */   final int getReadTimeOut()
/*     */     throws IOException
/*     */   {
/* 113 */     return this.m_readTimeOut;
/*     */   }
/*     */ 
/*     */   void B()
/*     */     throws IOException
/*     */   {
/*     */   }
/*     */ 
/*     */   void E()
/*     */     throws t
/*     */   {
/*     */   }
/*     */ 
/*     */   String C()
/*     */     throws IOException
/*     */   {
/* 145 */     return null;
/*     */   }
/*     */ 
/*     */   void A(String paramString)
/*     */     throws IOException
/*     */   {
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.A.c
 * JD-Core Version:    0.6.0
 */