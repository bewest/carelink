/*     */ package minimed.ddms.applet.dtw.wizard.steps.mmmeter;
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
/*     */ import minimed.ddms.applet.dtw.wizard.WizardSelections;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardStep;
/*     */ 
/*     */ public class VerifyConnectionsStep extends WizardStep
/*     */ {
/*     */   private final JLabel m_labelMainText;
/*     */ 
/*     */   public VerifyConnectionsStep(Wizard paramWizard)
/*     */   {
/*  48 */     super(paramWizard, null);
/*     */ 
/*  50 */     getLeftBannerLabel().setText(this.m_resources.getString("wizard.device.verify_connections"));
/*  51 */     Object localObject = new ImageIcon(getImage("wizard.read.icon"));
/*  52 */     getRightBannerLabel().setIcon((Icon)localObject);
/*     */ 
/*  55 */     localObject = getWarningIcon();
/*  56 */     getTopImageLabel().setIcon((Icon)localObject);
/*     */ 
/*  59 */     this.m_labelMainText = new JLabel();
/*  60 */     this.m_labelMainText.setHorizontalAlignment(0);
/*     */ 
/*  63 */     JPanel localJPanel = getContentArea();
/*  64 */     localJPanel.setLayout(new BorderLayout());
/*  65 */     localJPanel.add(this.m_labelMainText, "Center");
/*     */   }
/*     */ 
/*     */   protected void stepShown()
/*     */   {
/*  75 */     super.stepShown();
/*     */ 
/*  79 */     getNextButton().setEnabled(false);
/*  80 */     enableWithFocus(getFinishButton());
/*  81 */     updateContent();
/*     */   }
/*     */ 
/*     */   protected void backRequest()
/*     */   {
/*  91 */     if ((getWizard().getPreviousStep() instanceof USBDriverPostInstallStep))
/*  92 */       getWizard().showPreviousStep(3);
/*     */     else
/*  94 */       getWizard().showPreviousStep(1);
/*     */   }
/*     */ 
/*     */   protected void finishRequest()
/*     */   {
/* 103 */     getWizard().setUserInputCompleted();
/* 104 */     super.finishRequest();
/*     */   }
/*     */ 
/*     */   protected void updateContent()
/*     */   {
/* 112 */     String str2 = this.m_resources.getString(getWizardSelections().getMeterDevice());
/* 113 */     String str1 = MessageHelper.format(this.m_resources.getString("wizard.minimed.meters.ul_instructions.single_meter"), new Object[] { str2 });
/*     */ 
/* 116 */     this.m_labelMainText.setText(MessageHelper.format(this.m_resources.getString("wizard.minimed.meters.ul_instructions"), new Object[] { str1 }));
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.steps.mmmeter.VerifyConnectionsStep
 * JD-Core Version:    0.6.0
 */