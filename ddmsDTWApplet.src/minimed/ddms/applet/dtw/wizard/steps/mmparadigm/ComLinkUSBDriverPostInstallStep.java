/*     */ package minimed.ddms.applet.dtw.wizard.steps.mmparadigm;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.JButton;
/*     */ import minimed.ddms.applet.dtw.ExeHelper;
/*     */ import minimed.ddms.applet.dtw.MessageHelper;
/*     */ import minimed.ddms.applet.dtw.wizard.Wizard;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardConfig;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardConfig.USBConfig;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardSelections;
/*     */ import minimed.ddms.applet.dtw.wizard.steps.USBDriverPostInstallStep;
/*     */ import minimed.ddms.deviceportreader.ComLink2;
/*     */ 
/*     */ public class ComLinkUSBDriverPostInstallStep extends USBDriverPostInstallStep
/*     */ {
/*     */   private static final int DRIVER_REINSTALL_DELAY = 20;
/*     */ 
/*     */   public ComLinkUSBDriverPostInstallStep(Wizard paramWizard)
/*     */   {
/*  52 */     super(paramWizard, null);
/*     */   }
/*     */ 
/*     */   protected final void stepShown()
/*     */   {
/*  62 */     startClock();
/*     */ 
/*  64 */     super.stepShown();
/*     */ 
/*  66 */     getNextButton().setEnabled(false);
/*     */   }
/*     */ 
/*     */   protected String getMainText()
/*     */   {
/*  76 */     String str1 = this.m_resources.getString(getWizardSelections().getLinkDevice());
/*     */ 
/*  79 */     String str2 = "wizard.comlinkusb.postinstall.vista";
/*     */ 
/*  81 */     String str3 = MessageHelper.format(this.m_resources.getString(str2), new Object[] { str1 });
/*     */ 
/*  84 */     return str3;
/*     */   }
/*     */ 
/*     */   protected String getMainText98SE()
/*     */   {
/*  93 */     return "";
/*     */   }
/*     */ 
/*     */   protected void updateButtonStates()
/*     */   {
/* 101 */     if (ComLink2.isLinkPresent())
/*     */     {
/*     */       try
/*     */       {
/* 105 */         getWizard().writeComLink2EverBeenDetectedIndicator();
/*     */       } catch (IOException localIOException) {
/* 107 */         logWarning(this + " exception writing ComLink2 Detected Indicator: " + localIOException);
/*     */       }
/* 109 */       getWizard().showNextStep(VerifyComLinkUSBConnectionStep.class);
/*     */     }
/* 117 */     else if (getClockET() >= 20) {
/* 118 */       reinstallComLinkUSBDriver();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void reinstallComLinkUSBDriver()
/*     */   {
/* 128 */     String str1 = getWizard().getConfig().getComLink2Config().getInstallUSBDriverDir();
/*     */ 
/* 131 */     String str2 = getWizard().getConfig().getComLink2Config().getInstallUSBDriverFiles()[5];
/*     */ 
/* 133 */     String str3 = str1 + File.separator + str2;
/* 134 */     logInfo(this + " re-installing driver: " + str3);
/* 135 */     ExeHelper localExeHelper = new ExeHelper();
/*     */     try {
/* 137 */       int i = localExeHelper.execute(new String[] { str3 }, new File(str1));
/* 138 */       if (i != 0)
/* 139 */         logWarning(this + " problem re-installing driver: " + i);
/*     */     }
/*     */     catch (IOException localIOException) {
/* 142 */       logWarning(this + " exception re-installing driver: " + localIOException);
/*     */     } catch (InterruptedException localInterruptedException) {
/* 144 */       logWarning(this + " exception re-installing driver: " + localInterruptedException);
/*     */     }
/*     */ 
/* 148 */     startClock();
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.steps.mmparadigm.ComLinkUSBDriverPostInstallStep
 * JD-Core Version:    0.6.0
 */