/*    */ package minimed.ddms.applet.dtw.wizard.steps;
/*    */ 
/*    */ import java.awt.BorderLayout;
/*    */ import java.util.ResourceBundle;
/*    */ import javax.swing.Icon;
/*    */ import javax.swing.ImageIcon;
/*    */ import javax.swing.JButton;
/*    */ import javax.swing.JLabel;
/*    */ import javax.swing.JPanel;
/*    */ import minimed.ddms.applet.dtw.DiskHelper;
/*    */ import minimed.ddms.applet.dtw.MessageHelper;
/*    */ import minimed.ddms.applet.dtw.wizard.Wizard;
/*    */ import minimed.ddms.applet.dtw.wizard.WizardConfig;
/*    */ import minimed.ddms.applet.dtw.wizard.WizardConfig.DriverConfig;
/*    */ import minimed.ddms.applet.dtw.wizard.WizardStep;
/*    */ 
/*    */ public abstract class USBDriverPostInstallStep extends WizardStep
/*    */ {
/*    */   private final JLabel m_label;
/*    */ 
/*    */   protected USBDriverPostInstallStep(Wizard paramWizard, Class paramClass)
/*    */   {
/* 50 */     super(paramWizard, paramClass);
/*    */ 
/* 52 */     getLeftBannerLabel().setText(this.m_resources.getString("wizard.driver.postinstall.leftbanner"));
/* 53 */     Object localObject = new ImageIcon(getImage("wizard.read.icon"));
/* 54 */     getRightBannerLabel().setIcon((Icon)localObject);
/*    */ 
/* 57 */     localObject = getQuestionIcon();
/* 58 */     getTopImageLabel().setIcon((Icon)localObject);
/*    */ 
/* 61 */     this.m_label = new JLabel();
/* 62 */     this.m_label.setHorizontalAlignment(0);
/* 63 */     String str1 = "<html>" + getMainText();
/* 64 */     if (paramWizard.getConfig().isWindows98()) {
/* 65 */       str2 = getMainText98SE();
/*    */ 
/* 67 */       if (str2.length() > 0)
/*    */       {
/* 69 */         String str3 = new DiskHelper().getViewableName(paramWizard.getConfig().getBDDriverConfig().getInstallDriverDir());
/*    */ 
/* 71 */         str1 = str1 + MessageHelper.format(str2, new Object[] { str3 });
/*    */       }
/*    */     }
/* 74 */     String str2 = str1 + "</html>";
/* 75 */     this.m_label.setText(str2);
/*    */ 
/* 77 */     getContentArea().setLayout(new BorderLayout());
/* 78 */     getContentArea().add(this.m_label, "Center");
/*    */   }
/*    */ 
/*    */   protected void stepShown()
/*    */   {
/* 89 */     super.stepShown();
/*    */ 
/* 91 */     getNextButton().setEnabled(true);
/* 92 */     getBackButton().setEnabled(false);
/* 93 */     getFinishButton().setEnabled(false);
/* 94 */     getCancelButton().setEnabled(false);
/*    */   }
/*    */ 
/*    */   protected abstract String getMainText();
/*    */ 
/*    */   protected abstract String getMainText98SE();
/*    */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.steps.USBDriverPostInstallStep
 * JD-Core Version:    0.6.0
 */