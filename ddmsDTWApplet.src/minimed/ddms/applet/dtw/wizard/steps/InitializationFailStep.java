/*    */ package minimed.ddms.applet.dtw.wizard.steps;
/*    */ 
/*    */ import java.awt.BorderLayout;
/*    */ import java.util.ResourceBundle;
/*    */ import javax.swing.Icon;
/*    */ import javax.swing.ImageIcon;
/*    */ import javax.swing.JButton;
/*    */ import javax.swing.JLabel;
/*    */ import javax.swing.JPanel;
/*    */ import minimed.ddms.applet.dtw.wizard.Wizard;
/*    */ import minimed.ddms.applet.dtw.wizard.WizardStep;
/*    */ 
/*    */ public class InitializationFailStep extends WizardStep
/*    */ {
/*    */   private final JLabel m_stepMsg;
/*    */ 
/*    */   public InitializationFailStep(Wizard paramWizard)
/*    */   {
/* 46 */     super(paramWizard, null);
/*    */ 
/* 48 */     getLeftBannerLabel().setText(this.m_resources.getString("wizard.init.error"));
/* 49 */     Object localObject = new ImageIcon(getImage("wizard.read.icon"));
/* 50 */     getRightBannerLabel().setIcon((Icon)localObject);
/*    */ 
/* 53 */     localObject = getQuestionIcon();
/* 54 */     getTopImageLabel().setIcon((Icon)localObject);
/*    */ 
/* 57 */     this.m_stepMsg = new JLabel();
/* 58 */     this.m_stepMsg.setHorizontalAlignment(0);
/*    */ 
/* 61 */     JPanel localJPanel = getContentArea();
/* 62 */     localJPanel.setLayout(new BorderLayout());
/* 63 */     localJPanel.add(this.m_stepMsg, "Center");
/*    */   }
/*    */ 
/*    */   protected void stepShown()
/*    */   {
/* 73 */     getNextButton().setEnabled(false);
/* 74 */     getBackButton().setEnabled(false);
/* 75 */     getFinishButton().setEnabled(false);
/* 76 */     getCancelButton().setEnabled(false);
/*    */ 
/* 78 */     enableCursor();
/*    */ 
/* 81 */     String str = getWizard().getFailureReason();
/*    */ 
/* 83 */     setMsgText(str);
/*    */   }
/*    */ 
/*    */   private void setMsgText(String paramString)
/*    */   {
/* 92 */     this.m_stepMsg.setText(centerLabelText(paramString));
/*    */   }
/*    */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.steps.InitializationFailStep
 * JD-Core Version:    0.6.0
 */