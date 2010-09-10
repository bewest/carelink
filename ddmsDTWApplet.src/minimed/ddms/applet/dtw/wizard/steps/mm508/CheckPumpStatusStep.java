/*    */ package minimed.ddms.applet.dtw.wizard.steps.mm508;
/*    */ 
/*    */ import java.awt.BorderLayout;
/*    */ import java.util.ResourceBundle;
/*    */ import javax.swing.Icon;
/*    */ import javax.swing.ImageIcon;
/*    */ import javax.swing.JLabel;
/*    */ import javax.swing.JPanel;
/*    */ import minimed.ddms.applet.dtw.MessageHelper;
/*    */ import minimed.ddms.applet.dtw.wizard.Wizard;
/*    */ import minimed.ddms.applet.dtw.wizard.WizardSelections;
/*    */ import minimed.ddms.applet.dtw.wizard.WizardStep;
/*    */ 
/*    */ public class CheckPumpStatusStep extends WizardStep
/*    */ {
/*    */   private final JLabel m_labelMainText;
/*    */ 
/*    */   public CheckPumpStatusStep(Wizard paramWizard)
/*    */   {
/* 45 */     super(paramWizard, PumpSelectSerialPortStep.class);
/*    */ 
/* 48 */     getLeftBannerLabel().setText(this.m_resources.getString("wizard.pump.checkstatus"));
/* 49 */     Object localObject = new ImageIcon(getImage("wizard.read.icon"));
/* 50 */     getRightBannerLabel().setIcon((Icon)localObject);
/*    */ 
/* 53 */     localObject = getWarningIcon();
/* 54 */     getTopImageLabel().setIcon((Icon)localObject);
/*    */ 
/* 57 */     this.m_labelMainText = new JLabel();
/* 58 */     this.m_labelMainText.setHorizontalAlignment(0);
/*    */ 
/* 61 */     JPanel localJPanel = getContentArea();
/* 62 */     localJPanel.setLayout(new BorderLayout());
/* 63 */     localJPanel.add(this.m_labelMainText, "Center");
/*    */   }
/*    */ 
/*    */   protected void stepShown()
/*    */   {
/* 72 */     super.stepShown();
/* 73 */     updateContent();
/*    */   }
/*    */ 
/*    */   protected void updateContent()
/*    */   {
/* 80 */     String str = formatPumpDevice(getWizardSelections().getPumpDevice());
/*    */ 
/* 83 */     this.m_labelMainText.setText(MessageHelper.format(this.m_resources.getString("wizard.pump.checkstatus.expanded"), new Object[] { str }));
/*    */   }
/*    */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.steps.mm508.CheckPumpStatusStep
 * JD-Core Version:    0.6.0
 */