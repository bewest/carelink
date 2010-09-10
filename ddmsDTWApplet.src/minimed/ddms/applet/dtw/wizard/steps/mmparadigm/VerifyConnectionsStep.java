/*     */ package minimed.ddms.applet.dtw.wizard.steps.mmparadigm;
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
/*  52 */     getLeftBannerLabel().setText(this.m_resources.getString("wizard.device.verify_connections"));
/*  53 */     Object localObject = new ImageIcon(getImage("wizard.read.icon"));
/*  54 */     getRightBannerLabel().setIcon((Icon)localObject);
/*     */ 
/*  57 */     localObject = getWarningIcon();
/*  58 */     getTopImageLabel().setIcon((Icon)localObject);
/*     */ 
/*  61 */     this.m_labelMainText = new JLabel();
/*  62 */     this.m_labelMainText.setHorizontalAlignment(0);
/*     */ 
/*  65 */     JPanel localJPanel = getContentArea();
/*  66 */     localJPanel.setLayout(new BorderLayout());
/*  67 */     localJPanel.add(this.m_labelMainText, "Center");
/*     */   }
/*     */ 
/*     */   protected void stepShown()
/*     */   {
/*  77 */     super.stepShown();
/*     */ 
/*  81 */     getNextButton().setEnabled(false);
/*  82 */     enableWithFocus(getFinishButton());
/*  83 */     updateContent();
/*     */   }
/*     */ 
/*     */   protected void backRequest()
/*     */   {
/*  93 */     if ((getWizard().getPreviousStep() instanceof USBDriverPostInstallStep))
/*  94 */       getWizard().showPreviousStep(3);
/*     */     else
/*  96 */       getWizard().showPreviousStep(1);
/*     */   }
/*     */ 
/*     */   protected void finishRequest()
/*     */   {
/* 105 */     getWizard().setUserInputCompleted();
/* 106 */     super.finishRequest();
/*     */   }
/*     */ 
/*     */   protected void updateContent()
/*     */   {
/* 113 */     this.m_labelMainText.setText(getMainLabelText());
/*     */   }
/*     */ 
/*     */   private String getMainLabelText()
/*     */   {
/* 124 */     String str2 = this.m_resources.getString(getWizardSelections().getLinkDevice());
/*     */     String str3;
/*     */     String str1;
/* 125 */     if (getWizardSelections().isDeviceSelectionAPump()) {
/* 126 */       str3 = formatPumpDevice(getWizardSelections().getPumpDevice());
/* 127 */       str1 = MessageHelper.format(this.m_resources.getString("wizard.pumpparadigm.uploadinstructions"), new Object[] { str3, str2 });
/*     */     }
/*     */     else
/*     */     {
/* 132 */       str3 = this.m_resources.getString(getWizardSelections().getCGMDevice());
/* 133 */       str1 = MessageHelper.format(this.m_resources.getString("wizard.cgm.uploadinstructions"), new Object[] { str3, str2 });
/*     */     }
/*     */ 
/* 137 */     return str1;
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.steps.mmparadigm.VerifyConnectionsStep
 * JD-Core Version:    0.6.0
 */