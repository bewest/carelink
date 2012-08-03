/*    */ package minimed.ddms.applet.dtw.wizard.steps.bayermeter;
/*    */ 
/*    */ import java.util.ResourceBundle;
/*    */ import minimed.ddms.applet.dtw.wizard.Wizard;
/*    */ import minimed.util.OSInfo;
/*    */ 
/*    */ public class USBDriverPostInstallStep extends minimed.ddms.applet.dtw.wizard.steps.USBDriverPostInstallStep
/*    */ {
/*    */   public USBDriverPostInstallStep(Wizard paramWizard)
/*    */   {
/* 41 */     super(paramWizard, VerifyConnectionsStep.class);
/*    */   }
/*    */ 
/*    */   protected String getMainText()
/*    */   {
/* 52 */     return this.m_resources.getString("wizard.driver.postinstall.bayer");
/*    */   }
/*    */ 
/*    */   protected String getMainText98SE()
/*    */   {
/* 61 */     return this.m_resources.getString("wizard.driver.postinstall.98se");
/*    */   }
/*    */ 
/*    */   protected void stepShown()
/*    */   {
/* 68 */     super.stepShown();
/*    */ 
/* 70 */     if (OSInfo.isMac())
/* 71 */       getWizard().showNextStep(VerifyConnectionsStep.class);
/*    */   }
/*    */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.steps.bayermeter.USBDriverPostInstallStep
 * JD-Core Version:    0.6.0
 */