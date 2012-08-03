/*     */ package minimed.ddms.applet.dtw.wizard.steps.othermeter;
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
/*     */ import minimed.util.Contract;
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
/*     */   protected void finishRequest()
/*     */   {
/*  89 */     getWizard().setUserInputCompleted();
/*  90 */     super.finishRequest();
/*     */   }
/*     */ 
/*     */   protected void updateContent()
/*     */   {
/*  97 */     String str1 = getWizardSelections().getMeterDevice();
/*  98 */     String str2 = formatMeterDevice(str1);
/*  99 */     str2 = str2.replaceAll("<br>", " / ");
/* 100 */     if (str1.equals("wizard.selections.SELECTION_DEVICE_MEDISENSE_XTRA_METER")) {
/* 101 */       this.m_labelMainText.setText(MessageHelper.format(this.m_resources.getString("wizard.medisense.xtra.ul_instructions"), new Object[] { str2 }));
/*     */     }
/* 104 */     else if (str1.equals("wizard.selections.SELECTION_DEVICE_THERASENSE_FREESTYLE_METER"))
/*     */     {
/* 106 */       this.m_labelMainText.setText(MessageHelper.format(this.m_resources.getString("wizard.therasense.freestyle.ul_instructions"), new Object[] { str2 }));
/*     */     }
/*     */     else
/*     */     {
/* 110 */       Contract.unreachable();
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.steps.othermeter.VerifyConnectionsStep
 * JD-Core Version:    0.6.0
 */