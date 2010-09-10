/*    */ package minimed.ddms.applet.dtw.wizard.steps.mmparadigm;
/*    */ 
/*    */ import java.util.ResourceBundle;
/*    */ import minimed.ddms.applet.dtw.wizard.Wizard;
/*    */ import minimed.ddms.applet.dtw.wizard.WizardConfig;
/*    */ import minimed.ddms.applet.dtw.wizard.WizardConfig.USBConfig;
/*    */ import minimed.ddms.applet.dtw.wizard.WizardSelections;
/*    */ 
/*    */ public class USBDriverInstallAuthorizationStep extends minimed.ddms.applet.dtw.wizard.steps.USBDriverInstallAuthorizationStep
/*    */ {
/*    */   public USBDriverInstallAuthorizationStep(Wizard paramWizard)
/*    */   {
/* 43 */     super(paramWizard, USBDriverPostInstallStep.class);
/*    */   }
/*    */ 
/*    */   protected String getMainText()
/*    */   {
/*    */     String str;
/* 57 */     if (getWizardSelections().isDeviceSelectionAPump())
/* 58 */       str = this.m_resources.getString("wizard.usb.install.pump");
/*    */     else {
/* 60 */       str = this.m_resources.getString("wizard.usb.install.cgm");
/*    */     }
/* 62 */     return str;
/*    */   }
/*    */ 
/*    */   protected String getInstallText()
/*    */   {
/* 71 */     return this.m_resources.getString("wizard.usb.install.executingBD");
/*    */   }
/*    */ 
/*    */   protected WizardConfig.USBConfig getUSBConfig()
/*    */   {
/* 80 */     return getWizard().getConfig().getBDUSBConfig();
/*    */   }
/*    */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.steps.mmparadigm.USBDriverInstallAuthorizationStep
 * JD-Core Version:    0.6.0
 */