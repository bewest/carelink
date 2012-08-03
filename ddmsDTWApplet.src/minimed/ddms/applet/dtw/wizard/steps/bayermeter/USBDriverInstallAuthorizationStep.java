/*    */ package minimed.ddms.applet.dtw.wizard.steps.bayermeter;
/*    */ 
/*    */ import java.util.ResourceBundle;
/*    */ import minimed.ddms.applet.dtw.wizard.Wizard;
/*    */ import minimed.ddms.applet.dtw.wizard.WizardConfig;
/*    */ import minimed.ddms.applet.dtw.wizard.WizardConfig.DriverConfig;
/*    */ import minimed.ddms.applet.dtw.wizard.steps.DriverInstallAuthorizationStep;
/*    */ 
/*    */ public class USBDriverInstallAuthorizationStep extends DriverInstallAuthorizationStep
/*    */ {
/*    */   public USBDriverInstallAuthorizationStep(Wizard paramWizard)
/*    */   {
/* 44 */     super(paramWizard, USBDriverPostInstallStep.class);
/*    */   }
/*    */ 
/*    */   protected String getMainText()
/*    */   {
/* 57 */     return this.m_resources.getString("wizard.driver.install.bayermeter");
/*    */   }
/*    */ 
/*    */   protected String getInstallText()
/*    */   {
/* 66 */     return this.m_resources.getString("wizard.driver.install.executingBayer");
/*    */   }
/*    */ 
/*    */   protected WizardConfig.DriverConfig getDriverConfig()
/*    */   {
/* 75 */     return getWizard().getConfig().getBayerUSBConfig();
/*    */   }
/*    */ 
/*    */   public String getDeviceName()
/*    */   {
/* 81 */     return "";
/*    */   }
/*    */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.steps.bayermeter.USBDriverInstallAuthorizationStep
 * JD-Core Version:    0.6.0
 */