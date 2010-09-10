/*     */ package minimed.ddms.deviceportreader;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import mdt.common.device.driver.minimed.IDevicePnPListener;
/*     */ 
/*     */ final class USBPnPNotifier
/*     */   implements IDevicePnPListener
/*     */ {
/*  33 */   private boolean m_isPresent = false;
/*  34 */   private boolean m_firstTime = true;
/*     */   private String m_deviceDescription;
/*     */   private boolean m_debugMessages;
/*     */ 
/*     */   USBPnPNotifier(String paramString, boolean paramBoolean)
/*     */   {
/*  47 */     this.m_deviceDescription = paramString;
/*  48 */     this.m_debugMessages = paramBoolean;
/*     */   }
/*     */ 
/*     */   public void deviceAttached(int paramInt)
/*     */   {
/*  61 */     String str = this.m_deviceDescription + " ATTACHED (handle=" + paramInt + ")";
/*  62 */     if (this.m_debugMessages) {
/*  63 */       System.out.println(str);
/*     */     }
/*  65 */     MedicalDevice.logInfoLow(this, str);
/*  66 */     this.m_isPresent = true;
/*     */   }
/*     */ 
/*     */   public void deviceDetached(int paramInt)
/*     */   {
/*  75 */     String str = this.m_deviceDescription + " DETACHED: (handle=" + paramInt + ")";
/*  76 */     if (this.m_debugMessages) {
/*  77 */       System.out.println(str);
/*     */     }
/*  79 */     MedicalDevice.logInfoLow(this, str);
/*  80 */     this.m_isPresent = false;
/*     */   }
/*     */ 
/*     */   boolean isDevicePresent()
/*     */   {
/*  89 */     if (this.m_firstTime)
/*     */     {
/*  92 */       this.m_firstTime = false;
/*  93 */       USBComm localUSBComm = new USBComm();
/*  94 */       USBComm.addPnPListener(this);
/*     */       try {
/*  96 */         localUSBComm.initCommunications();
/*  97 */         localUSBComm.endCommunications();
/*     */       }
/*     */       catch (IOException localIOException) {
/*     */       }
/*     */     }
/* 102 */     return this.m_isPresent;
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.deviceportreader.USBPnPNotifier
 * JD-Core Version:    0.6.0
 */