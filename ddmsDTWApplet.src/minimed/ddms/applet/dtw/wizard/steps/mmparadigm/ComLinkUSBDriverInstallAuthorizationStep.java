/*    */ package minimed.ddms.applet.dtw.wizard.steps.mmparadigm;
/*    */ 
/*    */ import java.util.ResourceBundle;
/*    */ import minimed.ddms.applet.dtw.MessageHelper;
/*    */ import minimed.ddms.applet.dtw.wizard.Wizard;
/*    */ import minimed.ddms.applet.dtw.wizard.WizardConfig;
/*    */ import minimed.ddms.applet.dtw.wizard.WizardConfig.USBConfig;
/*    */ import minimed.ddms.applet.dtw.wizard.WizardSelections;
/*    */ import minimed.ddms.applet.dtw.wizard.steps.USBDriverInstallAuthorizationStep;
/*    */ 
/*    */ public class ComLinkUSBDriverInstallAuthorizationStep extends USBDriverInstallAuthorizationStep
/*    */ {
/*    */   public ComLinkUSBDriverInstallAuthorizationStep(Wizard paramWizard)
/*    */   {
/* 44 */     super(paramWizard, ComLinkUSBDriverPostInstallStep.class);
/*    */   }
/*    */ 
/*    */   protected String getMainText()
/*    */   {
/* 57 */     String str1 = this.m_resources.getString(getWizardSelections().getLinkDevice());
/*    */     String str2;
/* 59 */     if (getWizardSelections().isDeviceSelectionAPump())
/* 60 */       str2 = "wizard.comlinkusb.install.pump";
/*    */     else {
/* 62 */       str2 = "wizard.comlinkusb.install.cgm";
/*    */     }
/* 64 */     String str3 = MessageHelper.format(this.m_resources.getString(str2), new Object[] { str1 });
/*    */ 
/* 66 */     return str3;
/*    */   }
/*    */ 
/*    */   protected String getInstallText()
/*    */   {
/* 76 */     String str1 = this.m_resources.getString(getWizardSelections().getLinkDevice());
/* 77 */     String str2 = MessageHelper.format(this.m_resources.getString("wizard.comlinkusb.install.executing"), new Object[] { str1 });
/*    */ 
/* 80 */     return str2;
/*    */   }
/*    */ 
/*    */   protected WizardConfig.USBConfig getUSBConfig()
/*    */   {
/* 89 */     return getWizard().getConfig().getComLink2Config();
/*    */   }
/*    */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.steps.mmparadigm.ComLinkUSBDriverInstallAuthorizationStep
 * JD-Core Version:    0.6.0
 */