/*     */ package minimed.ddms.applet.dtw.wizard.steps.mmparadigm;
/*     */ 
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JLabel;
/*     */ import minimed.ddms.applet.dtw.MessageHelper;
/*     */ import minimed.ddms.applet.dtw.wizard.Wizard;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardConfig;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardConfig.DriverConfig;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardSelections;
/*     */ import minimed.ddms.applet.dtw.wizard.steps.DriverInstallAuthorizationStep;
/*     */ 
/*     */ public class ComLinkUSBDriverInstallAuthorizationStep extends DriverInstallAuthorizationStep
/*     */ {
/*     */   public ComLinkUSBDriverInstallAuthorizationStep(Wizard paramWizard)
/*     */   {
/*  46 */     super(paramWizard, ComLinkUSBDriverPostInstallStep.class);
/*     */ 
/*  48 */     ImageIcon localImageIcon = new ImageIcon(getImage("wizard.clusbdisconnected.pic"));
/*  49 */     getMainTextLabel().setIcon(localImageIcon);
/*  50 */     getMainTextLabel().setIconTextGap(20);
/*     */   }
/*     */ 
/*     */   public String getDeviceName()
/*     */   {
/*  59 */     return this.m_resources.getString("wizard.selections.SELECTION_LINK_DEVICE_COMLINKUSB");
/*     */   }
/*     */ 
/*     */   protected void stepShown()
/*     */   {
/*  64 */     super.stepShown();
/*     */ 
/*  67 */     getBackButton().setEnabled(true);
/*  68 */     getNextButton().setEnabled(true);
/*  69 */     getCancelButton().setEnabled(false);
/*  70 */     getFinishButton().setEnabled(false);
/*     */   }
/*     */ 
/*     */   protected String getMainText()
/*     */   {
/*  75 */     String str1 = this.m_resources.getString(getWizardSelections().getLinkDevice());
/*     */     String str2;
/*  77 */     if (getWizardSelections().isDeviceSelectionAPump())
/*  78 */       str2 = "wizard.comlinkusb.install.pump";
/*     */     else {
/*  80 */       str2 = "wizard.comlinkusb.install.cgm";
/*     */     }
/*  82 */     String str3 = MessageHelper.format(this.m_resources.getString(str2), new Object[] { str1 });
/*     */ 
/*  84 */     return str3;
/*     */   }
/*     */ 
/*     */   protected void nextRequest()
/*     */   {
/*  90 */     getMainTextLabel().setIcon(null);
/*  91 */     super.nextRequest();
/*     */   }
/*     */ 
/*     */   protected WizardConfig.DriverConfig getDriverConfig()
/*     */   {
/*  96 */     return getWizard().getConfig().getComLink2Config();
/*     */   }
/*     */ 
/*     */   protected String getInstallText()
/*     */   {
/* 102 */     String str1 = this.m_resources.getString(getWizardSelections().getLinkDevice());
/* 103 */     String str2 = MessageHelper.format(this.m_resources.getString("wizard.comlinkusb.install.executing"), new Object[] { str1 });
/*     */ 
/* 106 */     return str2;
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.steps.mmparadigm.ComLinkUSBDriverInstallAuthorizationStep
 * JD-Core Version:    0.6.0
 */