/*    */ package minimed.ddms.applet.dtw.wizard.steps.mmparadigm;
/*    */ 
/*    */ import java.util.ResourceBundle;
/*    */ import javax.swing.JButton;
/*    */ import javax.swing.JLabel;
/*    */ import minimed.ddms.applet.dtw.MessageHelper;
/*    */ import minimed.ddms.applet.dtw.wizard.Wizard;
/*    */ import minimed.ddms.applet.dtw.wizard.WizardSelections;
/*    */ import minimed.ddms.applet.dtw.wizard.steps.bayermeter.USBDriverInstallAuthorizationStep;
/*    */ 
/*    */ public class XTLinkUSBDriverInstallAuthorizationStep extends USBDriverInstallAuthorizationStep
/*    */ {
/*    */   public XTLinkUSBDriverInstallAuthorizationStep(Wizard paramWizard)
/*    */   {
/* 39 */     super(paramWizard);
/*    */   }
/*    */ 
/*    */   public String getDeviceName()
/*    */   {
/* 48 */     return this.m_resources.getString("wizard.selections.SELECTION_LINK_DEVICE_XTLINKUSB");
/*    */   }
/*    */ 
/*    */   protected Class getNextClass()
/*    */   {
/* 53 */     return XTLinkUSBDriverPostInstallStep.class;
/*    */   }
/*    */ 
/*    */   protected void stepShown()
/*    */   {
/* 58 */     super.stepShown();
/*    */ 
/* 61 */     getBackButton().setEnabled(true);
/* 62 */     getNextButton().setEnabled(true);
/* 63 */     getCancelButton().setEnabled(false);
/* 64 */     getFinishButton().setEnabled(false);
/*    */   }
/*    */ 
/*    */   protected String getMainText()
/*    */   {
/* 69 */     String str1 = this.m_resources.getString(getWizardSelections().getLinkDevice());
/*    */     String str2;
/* 71 */     if (getWizardSelections().isDeviceSelectionAPump())
/* 72 */       str2 = "wizard.xtlinkusb.install.pump";
/*    */     else {
/* 74 */       str2 = "wizard.xtlinkusb.install.cgm";
/*    */     }
/* 76 */     String str3 = this.m_resources.getString(str2);
/*    */ 
/* 78 */     return str3;
/*    */   }
/*    */ 
/*    */   protected void nextRequest()
/*    */   {
/* 84 */     getMainTextLabel().setIcon(null);
/* 85 */     super.nextRequest();
/*    */   }
/*    */ 
/*    */   protected String getInstallText()
/*    */   {
/* 91 */     String str1 = this.m_resources.getString(getWizardSelections().getLinkDevice());
/* 92 */     String str2 = MessageHelper.format(this.m_resources.getString("wizard.comlinkusb.install.executing"), new Object[] { str1 });
/*    */ 
/* 95 */     return str2;
/*    */   }
/*    */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.steps.mmparadigm.XTLinkUSBDriverInstallAuthorizationStep
 * JD-Core Version:    0.6.0
 */