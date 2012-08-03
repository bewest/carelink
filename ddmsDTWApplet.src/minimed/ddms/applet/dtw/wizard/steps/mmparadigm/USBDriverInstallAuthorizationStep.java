/*    */ package minimed.ddms.applet.dtw.wizard.steps.mmparadigm;
/*    */ 
/*    */ import java.util.ResourceBundle;
/*    */ import javax.swing.SwingUtilities;
/*    */ import minimed.ddms.applet.dtw.DTWApplet;
/*    */ import minimed.ddms.applet.dtw.wizard.Wizard;
/*    */ import minimed.ddms.applet.dtw.wizard.WizardConfig;
/*    */ import minimed.ddms.applet.dtw.wizard.WizardConfig.DriverConfig;
/*    */ import minimed.ddms.applet.dtw.wizard.WizardSelections;
/*    */ import minimed.ddms.applet.dtw.wizard.steps.DriverInstallAuthorizationStep;
/*    */ import minimed.util.OSInfo;
/*    */ 
/*    */ public class USBDriverInstallAuthorizationStep extends DriverInstallAuthorizationStep
/*    */ {
/*    */   public USBDriverInstallAuthorizationStep(Wizard paramWizard)
/*    */   {
/* 47 */     super(paramWizard, USBDriverPostInstallStep.class);
/*    */   }
/*    */ 
/*    */   protected String getMainText()
/*    */   {
/*    */     String str;
/* 61 */     if (getWizardSelections().isDeviceSelectionAPump())
/* 62 */       str = this.m_resources.getString("wizard.driver.install.pump");
/*    */     else {
/* 64 */       str = this.m_resources.getString("wizard.driver.install.cgm");
/*    */     }
/* 66 */     return str;
/*    */   }
/*    */ 
/*    */   protected String getInstallText()
/*    */   {
/* 75 */     return this.m_resources.getString("wizard.driver.install.executingBD");
/*    */   }
/*    */ 
/*    */   protected WizardConfig.DriverConfig getDriverConfig()
/*    */   {
/* 84 */     return getWizard().getConfig().getBDDriverConfig();
/*    */   }
/*    */ 
/*    */   public String getDeviceName()
/*    */   {
/* 90 */     return "";
/*    */   }
/*    */ 
/*    */   protected void stepShown()
/*    */   {
/* 95 */     super.stepShown();
/* 96 */     if (OSInfo.isWindowsVista())
/*    */       try {
/* 98 */         DTWApplet localDTWApplet = (DTWApplet)SwingUtilities.getAncestorOfClass(DTWApplet.class, this);
/* 99 */         localDTWApplet.showHelp("helpAdmin");
/*    */       } catch (Exception localException) {
/* 101 */         localException.printStackTrace();
/*    */       }
/*    */   }
/*    */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.steps.mmparadigm.USBDriverInstallAuthorizationStep
 * JD-Core Version:    0.6.0
 */