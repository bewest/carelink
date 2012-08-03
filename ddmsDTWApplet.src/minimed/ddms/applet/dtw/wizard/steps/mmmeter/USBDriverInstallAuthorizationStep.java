/*    */ package minimed.ddms.applet.dtw.wizard.steps.mmmeter;
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
/* 43 */     super(paramWizard, USBDriverPostInstallStep.class);
/*    */   }
/*    */ 
/*    */   protected String getMainText()
/*    */   {
/* 56 */     return this.m_resources.getString("wizard.driver.install.meter");
/*    */   }
/*    */ 
/*    */   protected String getInstallText()
/*    */   {
/* 65 */     return this.m_resources.getString("wizard.driver.install.executingBD");
/*    */   }
/*    */ 
/*    */   protected WizardConfig.DriverConfig getDriverConfig()
/*    */   {
/* 74 */     return getWizard().getConfig().getBDDriverConfig();
/*    */   }
/*    */ 
/*    */   public String getDeviceName()
/*    */   {
/* 80 */     return "";
/*    */   }
/*    */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.steps.mmmeter.USBDriverInstallAuthorizationStep
 * JD-Core Version:    0.6.0
 */