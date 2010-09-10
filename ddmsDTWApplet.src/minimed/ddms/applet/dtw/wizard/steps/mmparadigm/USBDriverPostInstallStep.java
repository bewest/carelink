/*    */ package minimed.ddms.applet.dtw.wizard.steps.mmparadigm;
/*    */ 
/*    */ import java.util.ResourceBundle;
/*    */ import minimed.ddms.applet.dtw.wizard.Wizard;
/*    */ 
/*    */ public class USBDriverPostInstallStep extends minimed.ddms.applet.dtw.wizard.steps.USBDriverPostInstallStep
/*    */ {
/*    */   public USBDriverPostInstallStep(Wizard paramWizard)
/*    */   {
/* 39 */     super(paramWizard, VerifyConnectionsStep.class);
/*    */   }
/*    */ 
/*    */   protected String getMainText()
/*    */   {
/* 50 */     return this.m_resources.getString("wizard.usb.postinstall");
/*    */   }
/*    */ 
/*    */   protected String getMainText98SE()
/*    */   {
/* 59 */     return this.m_resources.getString("wizard.usb.postinstall.98se");
/*    */   }
/*    */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.steps.mmparadigm.USBDriverPostInstallStep
 * JD-Core Version:    0.6.0
 */