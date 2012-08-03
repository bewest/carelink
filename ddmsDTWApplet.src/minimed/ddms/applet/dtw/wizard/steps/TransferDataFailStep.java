/*     */ package minimed.ddms.applet.dtw.wizard.steps;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import minimed.ddms.applet.dtw.MessageHelper;
/*     */ import minimed.ddms.applet.dtw.wizard.Wizard;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardStep;
/*     */ 
/*     */ public class TransferDataFailStep extends WizardStep
/*     */ {
/*     */   private final JLabel m_stepMsg;
/*     */ 
/*     */   public TransferDataFailStep(Wizard paramWizard)
/*     */   {
/*  49 */     super(paramWizard, null);
/*     */ 
/*  51 */     getLeftBannerLabel().setText(this.m_resources.getString("wizard.init.error"));
/*  52 */     Object localObject = new ImageIcon(getImage("wizard.read.icon"));
/*  53 */     getRightBannerLabel().setIcon((Icon)localObject);
/*     */ 
/*  56 */     localObject = getQuestionIcon();
/*  57 */     getTopImageLabel().setIcon((Icon)localObject);
/*     */ 
/*  60 */     this.m_stepMsg = new JLabel();
/*  61 */     this.m_stepMsg.setHorizontalAlignment(0);
/*     */ 
/*  64 */     JPanel localJPanel = getContentArea();
/*  65 */     localJPanel.setLayout(new BorderLayout());
/*  66 */     localJPanel.add(this.m_stepMsg, "Center");
/*     */   }
/*     */ 
/*     */   protected void stepShown()
/*     */   {
/*  75 */     super.stepShown();
/*     */ 
/*  77 */     getNextButton().setEnabled(false);
/*  78 */     getBackButton().setEnabled(false);
/*  79 */     enableWithFocus(getFinishButton());
/*  80 */     getFinishButton().setText(this.m_resources.getString("wizard.finishButton.doneText"));
/*  81 */     getCancelButton().setEnabled(false);
/*     */ 
/*  84 */     String str1 = getWizard().getFailureReason();
/*  85 */     if (str1 == null) {
/*  86 */       str1 = this.m_resources.getString("wizard.transfer.failed.unknown");
/*     */     }
/*  88 */     String str2 = MessageHelper.format(this.m_resources.getString("wizard.transfer.failed"), new Object[] { str1 });
/*     */ 
/*  92 */     setMsgText(str2);
/*     */   }
/*     */ 
/*     */   protected void finishRequest()
/*     */   {
/* 100 */     getWizard().showNextStep(SelectDeviceStep.class);
/*     */   }
/*     */ 
/*     */   private void setMsgText(String paramString)
/*     */   {
/* 109 */     this.m_stepMsg.setText(centerLabelText(paramString));
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.steps.TransferDataFailStep
 * JD-Core Version:    0.6.0
 */