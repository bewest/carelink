/*    */ package minimed.ddms.applet.dtw.wizard.steps.mmparadigm;
/*    */ 
/*    */ import java.util.ResourceBundle;
/*    */ import javax.swing.JButton;
/*    */ import minimed.ddms.applet.dtw.MessageHelper;
/*    */ import minimed.ddms.applet.dtw.wizard.Wizard;
/*    */ import minimed.ddms.applet.dtw.wizard.WizardSelections;
/*    */ import minimed.ddms.applet.dtw.wizard.steps.USBDriverPostInstallStep;
/*    */ 
/*    */ public class VerifyXTLinkUSBConnectionStep extends VerifyComLinkUSBConnectionStep
/*    */ {
/*    */   public VerifyXTLinkUSBConnectionStep(Wizard paramWizard)
/*    */   {
/* 41 */     super(paramWizard);
/*    */   }
/*    */ 
/*    */   protected void stepShown()
/*    */   {
/* 48 */     enableCursor();
/* 49 */     getNextButton().setEnabled(false);
/* 50 */     getBackButton().setEnabled(true);
/* 51 */     getFinishButton().setEnabled(true);
/* 52 */     getCancelButton().setEnabled(false);
/* 53 */     updateContent();
/*    */   }
/*    */ 
/*    */   protected void backRequest()
/*    */   {
/* 63 */     if ((getWizard().getPreviousStep() instanceof USBDriverPostInstallStep))
/*    */     {
/* 67 */       int i = 0;
/*    */ 
/* 69 */       i += (getWizard().getStep(XTLinkUSBDriverInstallAuthorizationStep.class) != null ? 1 : 0);
/*    */ 
/* 71 */       i += (getWizard().getStep(XTLinkUSBDriverPostInstallStep.class) != null ? 1 : 0);
/*    */ 
/* 73 */       getWizard().showPreviousStep(1 + i);
/*    */     } else {
/* 75 */       getWizard().showPreviousStep(1);
/*    */     }
/*    */   }
/*    */ 
/*    */   protected String getMainLabelText()
/*    */   {
/* 83 */     String str = this.m_resources.getString(getWizardSelections().getLinkDevice());
/*    */ 
/* 85 */     if (getWizardSelections().isDeviceSelectionAPump()) {
/* 86 */       return MessageHelper.format(this.m_resources.getString("wizard.xtlinkusb.unkownstate.pump"), new Object[] { str });
/*    */     }
/*    */ 
/* 90 */     return MessageHelper.format(this.m_resources.getString("wizard.xtlinkusb.unkownstate.cgm"), new Object[] { str });
/*    */   }
/*    */ 
/*    */   protected boolean isLinkPresent()
/*    */   {
/* 99 */     return false;
/*    */   }
/*    */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.steps.mmparadigm.VerifyXTLinkUSBConnectionStep
 * JD-Core Version:    0.6.0
 */