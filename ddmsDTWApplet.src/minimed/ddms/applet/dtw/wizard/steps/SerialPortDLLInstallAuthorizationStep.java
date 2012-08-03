/*    */ package minimed.ddms.applet.dtw.wizard.steps;
/*    */ 
/*    */ import java.util.ResourceBundle;
/*    */ import javax.swing.JButton;
/*    */ import javax.swing.SwingUtilities;
/*    */ import minimed.ddms.applet.dtw.DTWApplet;
/*    */ import minimed.ddms.applet.dtw.wizard.Wizard;
/*    */ import minimed.ddms.applet.dtw.wizard.WizardConfig;
/*    */ import minimed.ddms.applet.dtw.wizard.WizardConfig.DriverConfig;
/*    */ import minimed.util.OSInfo;
/*    */ 
/*    */ public class SerialPortDLLInstallAuthorizationStep extends DriverInstallAuthorizationStep
/*    */ {
/*    */   public SerialPortDLLInstallAuthorizationStep(Wizard paramWizard)
/*    */   {
/* 48 */     super(paramWizard, InitializationStep.class);
/*    */   }
/*    */ 
/*    */   public String getDeviceName()
/*    */   {
/* 58 */     return "";
/*    */   }
/*    */ 
/*    */   protected void stepShown()
/*    */   {
/* 63 */     super.stepShown();
/* 64 */     if (OSInfo.isWindowsVista()) {
/*    */       try {
/* 66 */         DTWApplet localDTWApplet = (DTWApplet)SwingUtilities.getAncestorOfClass(DTWApplet.class, this);
/* 67 */         localDTWApplet.showHelp("helpAdmin");
/*    */       } catch (Exception localException) {
/* 69 */         localException.printStackTrace();
/*    */       }
/*    */ 
/*    */     }
/*    */ 
/* 74 */     getNextButton().setEnabled(true);
/* 75 */     getBackButton().setEnabled(false);
/* 76 */     getFinishButton().setEnabled(false);
/* 77 */     getCancelButton().setEnabled(false);
/*    */ 
/* 79 */     enableCursor();
/*    */   }
/*    */ 
/*    */   protected String getMainText()
/*    */   {
/* 84 */     return this.m_resources.getString("wizard.driver.install.serial");
/*    */   }
/*    */ 
/*    */   protected WizardConfig.DriverConfig getDriverConfig() {
/* 88 */     return getWizard().getConfig().getSerialConfig();
/*    */   }
/*    */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.steps.SerialPortDLLInstallAuthorizationStep
 * JD-Core Version:    0.6.0
 */