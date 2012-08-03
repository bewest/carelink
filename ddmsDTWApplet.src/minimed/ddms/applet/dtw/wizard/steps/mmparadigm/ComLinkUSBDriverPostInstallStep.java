/*     */ package minimed.ddms.applet.dtw.wizard.steps.mmparadigm;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.JButton;
/*     */ import minimed.ddms.A.AA;
/*     */ import minimed.ddms.applet.dtw.MessageHelper;
/*     */ import minimed.ddms.applet.dtw.wizard.Wizard;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardSelections;
/*     */ import minimed.ddms.applet.dtw.wizard.steps.USBDriverPostInstallStep;
/*     */ import minimed.util.OSInfo;
/*     */ 
/*     */ public class ComLinkUSBDriverPostInstallStep extends USBDriverPostInstallStep
/*     */ {
/*     */   public ComLinkUSBDriverPostInstallStep(Wizard paramWizard)
/*     */   {
/*  49 */     super(paramWizard, null);
/*     */   }
/*     */ 
/*     */   protected final void stepShown()
/*     */   {
/*  59 */     startClock();
/*     */ 
/*  61 */     super.stepShown();
/*     */ 
/*  64 */     getNextButton().setEnabled(false);
/*     */   }
/*     */ 
/*     */   protected String getMainText()
/*     */   {
/*  74 */     String str1 = this.m_resources.getString(getWizardSelections().getLinkDevice());
/*     */ 
/*  77 */     String str2 = "wizard.comlinkusb.postinstall.vista";
/*     */ 
/*  79 */     String str3 = MessageHelper.format(this.m_resources.getString(str2), new Object[] { str1 });
/*     */ 
/*  83 */     if (OSInfo.isMac()) {
/*  84 */       return "";
/*     */     }
/*     */ 
/*  87 */     return str3;
/*     */   }
/*     */ 
/*     */   protected String getMainText98SE()
/*     */   {
/*  96 */     return "";
/*     */   }
/*     */ 
/*     */   protected void updateButtonStates()
/*     */   {
/* 104 */     if ((OSInfo.isMac()) || (AA.R()))
/*     */     {
/*     */       try
/*     */       {
/* 108 */         getWizard().writeComLink2EverBeenDetectedIndicator();
/*     */       } catch (IOException localIOException) {
/* 110 */         logWarning(this + " exception writing ComLink2 Detected Indicator: " + localIOException);
/*     */       }
/* 112 */       getWizard().showNextStep(VerifyComLinkUSBConnectionStep.class);
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.steps.mmparadigm.ComLinkUSBDriverPostInstallStep
 * JD-Core Version:    0.6.0
 */