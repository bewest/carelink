/*     */ package minimed.ddms.applet.dtw.wizard.steps.mm508;
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
/*  49 */     super(paramWizard, null);
/*     */ 
/*  51 */     getLeftBannerLabel().setText(this.m_resources.getString("wizard.device.verify_connections"));
/*  52 */     Object localObject = new ImageIcon(getImage("wizard.read.icon"));
/*  53 */     getRightBannerLabel().setIcon((Icon)localObject);
/*     */ 
/*  56 */     localObject = getWarningIcon();
/*  57 */     getTopImageLabel().setIcon((Icon)localObject);
/*     */ 
/*  60 */     this.m_labelMainText = new JLabel();
/*  61 */     this.m_labelMainText.setHorizontalAlignment(0);
/*     */ 
/*  64 */     JPanel localJPanel = getContentArea();
/*  65 */     localJPanel.setLayout(new BorderLayout());
/*  66 */     localJPanel.add(this.m_labelMainText, "Center");
/*     */   }
/*     */ 
/*     */   protected void stepShown()
/*     */   {
/*  76 */     super.stepShown();
/*     */ 
/*  80 */     getNextButton().setEnabled(false);
/*  81 */     enableWithFocus(getFinishButton());
/*  82 */     updateContent();
/*     */   }
/*     */ 
/*     */   protected void finishRequest()
/*     */   {
/*  90 */     getWizard().setUserInputCompleted();
/*  91 */     super.finishRequest();
/*     */   }
/*     */ 
/*     */   protected void updateContent()
/*     */   {
/*  99 */     String str = formatPumpDevice(getWizardSelections().getPumpDevice());
/*     */ 
/* 101 */     this.m_labelMainText.setText(MessageHelper.format(this.m_resources.getString("wizard.pump508.uploadinstructions"), new Object[] { str }));
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.steps.mm508.VerifyConnectionsStep
 * JD-Core Version:    0.6.0
 */