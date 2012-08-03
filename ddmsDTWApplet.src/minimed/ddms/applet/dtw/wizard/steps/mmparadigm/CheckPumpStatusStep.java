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
/*     */ public class CheckPumpStatusStep extends WizardStep
/*     */ {
/*     */   private final JLabel m_labelMainText;
/*     */ 
/*     */   public CheckPumpStatusStep(Wizard paramWizard)
/*     */   {
/*  46 */     super(paramWizard, IdentifyPumpStep.class);
/*     */ 
/*  49 */     Object localObject = new ImageIcon(getImage("wizard.read.icon"));
/*  50 */     getRightBannerLabel().setIcon((Icon)localObject);
/*     */ 
/*  53 */     localObject = getWarningIcon();
/*  54 */     getTopImageLabel().setIcon((Icon)localObject);
/*     */ 
/*  57 */     this.m_labelMainText = new JLabel();
/*  58 */     this.m_labelMainText.setHorizontalAlignment(0);
/*     */ 
/*  61 */     JPanel localJPanel = getContentArea();
/*  62 */     localJPanel.setLayout(new BorderLayout());
/*  63 */     localJPanel.add(this.m_labelMainText, "Center");
/*     */   }
/*     */ 
/*     */   protected void stepShown()
/*     */   {
/*  72 */     super.stepShown();
/*  73 */     updateContent();
/*     */   }
/*     */ 
/*     */   protected void updateContent()
/*     */   {
/*  82 */     getLeftBannerLabel().setText(getLeftBannerLabelText());
/*     */ 
/*  85 */     this.m_labelMainText.setText(getMainLabelText());
/*     */   }
/*     */ 
/*     */   protected void updateButtonStates()
/*     */   {
/*  92 */     rememberUserSelections();
/*  93 */     getFinishButton().setEnabled(getWizard().canFinish());
/*     */   }
/*     */ 
/*     */   private String getLeftBannerLabelText()
/*     */   {
/*     */     String str;
/* 103 */     if (getWizardSelections().isDeviceSelectionAPump()) {
/* 104 */       str = this.m_resources.getString("wizard.pump.checkstatus");
/*     */     }
/*     */     else {
/* 107 */       str = this.m_resources.getString("wizard.cgm.checkstatus");
/*     */     }
/* 109 */     return str;
/*     */   }
/*     */ 
/*     */   private String getMainLabelText()
/*     */   {
/*     */     String str2;
/*     */     String str1;
/* 119 */     if (getWizardSelections().isDeviceSelectionAPump()) {
/* 120 */       str2 = formatPumpDevice(getWizardSelections().getPumpDevice());
/* 121 */       str1 = MessageHelper.format(this.m_resources.getString("wizard.pump.checkstatus.expanded"), new Object[] { str2 });
/*     */     }
/*     */     else
/*     */     {
/* 126 */       str2 = this.m_resources.getString(getWizardSelections().getCGMDevice());
/* 127 */       str1 = MessageHelper.format(this.m_resources.getString("wizard.cgm.checkstatus.expanded"), new Object[] { str2 });
/*     */     }
/*     */ 
/* 131 */     return str1;
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.steps.mmparadigm.CheckPumpStatusStep
 * JD-Core Version:    0.6.0
 */