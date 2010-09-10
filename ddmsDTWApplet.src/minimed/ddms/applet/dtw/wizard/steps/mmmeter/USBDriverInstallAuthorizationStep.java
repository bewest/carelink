/*    */ package minimed.ddms.applet.dtw.wizard.steps.mmmeter;
/*    */ 
/*    */ import java.util.ResourceBundle;
/*    */ import minimed.ddms.applet.dtw.wizard.Wizard;
/*    */ import minimed.ddms.applet.dtw.wizard.WizardConfig;
/*    */ import minimed.ddms.applet.dtw.wizard.WizardConfig.USBConfig;
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
/* 56 */     return this.m_resources.getString("wizard.usb.install.meter");
/*    */   }
/*    */ 
/*    */   protected String getInstallText()
/*    */   {
/* 65 */     return this.m_resources.getString("wizard.usb.install.executingBD");
/*    */   }
/*    */ 
/*    */   protected WizardConfig.USBConfig getUSBConfig()
/*    */   {
/* 74 */     return getWizard().getConfig().getBDUSBConfig();
/*    */   }
/*    */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.steps.mmmeter.USBDriverInstallAuthorizationStep
 * JD-Core Version:    0.6.0
 */