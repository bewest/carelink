/*    */ package minimed.ddms.applet.dtw.wizard.steps.mmparadigm;
/*    */ 
/*    */ import java.util.ResourceBundle;
/*    */ import javax.swing.JButton;
/*    */ import minimed.ddms.applet.dtw.MessageHelper;
/*    */ import minimed.ddms.applet.dtw.wizard.Wizard;
/*    */ import minimed.ddms.applet.dtw.wizard.WizardSelections;
/*    */ import minimed.ddms.applet.dtw.wizard.steps.bayermeter.USBDriverPostInstallStep;
/*    */ import minimed.util.OSInfo;
/*    */ 
/*    */ public class XTLinkUSBDriverPostInstallStep extends USBDriverPostInstallStep
/*    */ {
/*    */   public XTLinkUSBDriverPostInstallStep(Wizard paramWizard)
/*    */   {
/* 39 */     super(paramWizard);
/*    */   }
/*    */ 
/*    */   protected final void stepShown()
/*    */   {
/* 46 */     if (OSInfo.isMac()) {
/* 47 */       nextRequest();
/*    */     } else {
/* 49 */       enableCursor();
/* 50 */       getNextButton().setEnabled(true);
/* 51 */       getBackButton().setEnabled(false);
/* 52 */       getFinishButton().setEnabled(false);
/* 53 */       getCancelButton().setEnabled(false);
/*    */     }
/*    */   }
/*    */ 
/*    */   protected void nextRequest()
/*    */   {
/* 59 */     getWizard().showNextStep(VerifyXTLinkUSBConnectionStep.class);
/*    */   }
/*    */ 
/*    */   protected String getMainText()
/*    */   {
/* 69 */     String str1 = this.m_resources.getString(getWizardSelections().getLinkDevice());
/*    */ 
/* 72 */     String str2 = "wizard.xtlinkusb.postinstall.vista";
/*    */ 
/* 74 */     String str3 = MessageHelper.format(this.m_resources.getString(str2), new Object[] { str1 });
/*    */ 
/* 78 */     if (OSInfo.isMac()) {
/* 79 */       return "";
/*    */     }
/*    */ 
/* 82 */     return str3;
/*    */   }
/*    */ 
/*    */   protected String getMainText98SE()
/*    */   {
/* 91 */     return "";
/*    */   }
/*    */ 
/*    */   protected void updateButtonStates()
/*    */   {
/* 99 */     getWizard().showNextStep(VerifyXTLinkUSBConnectionStep.class);
/*    */   }
/*    */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.steps.mmparadigm.XTLinkUSBDriverPostInstallStep
 * JD-Core Version:    0.6.0
 */