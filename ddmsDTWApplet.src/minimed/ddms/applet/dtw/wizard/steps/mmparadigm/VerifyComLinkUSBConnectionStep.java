/*     */ package minimed.ddms.applet.dtw.wizard.steps.mmparadigm;
/*     */ 
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.Insets;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import minimed.ddms.A.AA;
/*     */ import minimed.ddms.applet.dtw.MessageHelper;
/*     */ import minimed.ddms.applet.dtw.wizard.Wizard;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardSelections;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardStep;
/*     */ import minimed.ddms.applet.dtw.wizard.steps.USBDriverPostInstallStep;
/*     */ 
/*     */ public class VerifyComLinkUSBConnectionStep extends WizardStep
/*     */ {
/*     */   private static final String HTML_TM = "<FONT SIZE=-2><SUP>TM</SUP></FONT>";
/*     */   private static final String EMPTY_NOTE = "<html><br><br></html>";
/*     */   private static final int INSET_LEFT = 80;
/*     */   private static final int INSET_TOP = 50;
/*     */   private static final int INSET_BOTTOM = 25;
/*     */   private final JLabel m_labelMainText;
/*     */   private final JLabel m_labelNoteText;
/*     */ 
/*     */   public VerifyComLinkUSBConnectionStep(Wizard paramWizard)
/*     */   {
/*  55 */     super(paramWizard, null);
/*     */ 
/*  58 */     getLeftBannerLabel().setText(this.m_resources.getString("wizard.device.verify_connections"));
/*  59 */     Object localObject = new ImageIcon(getImage("wizard.read.icon"));
/*  60 */     getRightBannerLabel().setIcon((Icon)localObject);
/*     */ 
/*  63 */     localObject = getWarningIcon();
/*  64 */     getTopImageLabel().setIcon((Icon)localObject);
/*     */ 
/*  67 */     JPanel localJPanel = getContentArea();
/*  68 */     localJPanel.setLayout(new GridBagLayout());
/*     */ 
/*  71 */     this.m_labelMainText = new JLabel();
/*  72 */     GridBagConstraints localGridBagConstraints = new GridBagConstraints();
/*  73 */     localGridBagConstraints.gridx = 0;
/*  74 */     localGridBagConstraints.gridy = 0;
/*     */ 
/*  78 */     localGridBagConstraints.insets = new Insets(50, 80, 0, 0);
/*  79 */     localGridBagConstraints.anchor = 18;
/*  80 */     localGridBagConstraints.weightx = 1.0D;
/*  81 */     localGridBagConstraints.weighty = 1.0D;
/*  82 */     localGridBagConstraints.fill = 2;
/*  83 */     localJPanel.add(this.m_labelMainText, localGridBagConstraints);
/*     */ 
/*  85 */     this.m_labelNoteText = new JLabel();
/*  86 */     localGridBagConstraints = new GridBagConstraints();
/*  87 */     localGridBagConstraints.gridx = 0;
/*  88 */     localGridBagConstraints.gridy = 1;
/*  89 */     localGridBagConstraints.fill = 2;
/*  90 */     localGridBagConstraints.insets = new Insets(0, 80, 25, 0);
/*  91 */     localGridBagConstraints.anchor = 18;
/*  92 */     localJPanel.add(this.m_labelNoteText, localGridBagConstraints);
/*     */   }
/*     */ 
/*     */   protected void stepShown()
/*     */   {
/* 102 */     super.stepShown();
/*     */ 
/* 106 */     updateButtonStates();
/* 107 */     updateContent();
/*     */   }
/*     */ 
/*     */   protected void backRequest()
/*     */   {
/* 117 */     if ((getWizard().getPreviousStep() instanceof USBDriverPostInstallStep))
/*     */     {
/* 121 */       int i = 0;
/*     */ 
/* 123 */       i += (getWizard().getStep(ComLinkUSBDriverInstallAuthorizationStep.class) != null ? 1 : 0);
/*     */ 
/* 125 */       i += (getWizard().getStep(ComLinkUSBDriverPostInstallStep.class) != null ? 1 : 0);
/*     */ 
/* 127 */       getWizard().showPreviousStep(1 + i);
/*     */     } else {
/* 129 */       getWizard().showPreviousStep(1);
/*     */     }
/*     */   }
/*     */ 
/*     */   protected void finishRequest()
/*     */   {
/* 138 */     getWizard().setUserInputCompleted();
/* 139 */     super.finishRequest();
/*     */   }
/*     */ 
/*     */   protected void updateContent()
/*     */   {
/* 146 */     this.m_labelMainText.setText(getMainLabelText());
/* 147 */     this.m_labelMainText.setIcon(new ImageIcon(getImage(getMainLabelImageKey())));
/*     */ 
/* 149 */     this.m_labelNoteText.setText(getNoteLabelText());
/* 150 */     this.m_labelNoteText.setVisible(true);
/*     */   }
/*     */ 
/*     */   protected void updateButtonStates()
/*     */   {
/* 158 */     getNextButton().setEnabled(false);
/* 159 */     if (isLinkPresent()) {
/* 160 */       enableWithFocus(getFinishButton());
/*     */     }
/*     */     else {
/* 163 */       getFinishButton().setEnabled(false);
/* 164 */       enableWithFocus(getBackButton());
/*     */     }
/*     */   }
/*     */ 
/*     */   protected boolean isLinkPresent()
/*     */   {
/* 173 */     return AA.R();
/*     */   }
/*     */ 
/*     */   protected String getMainLabelText()
/*     */   {
/* 185 */     String str2 = this.m_resources.getString(getWizardSelections().getLinkDevice());
/*     */ 
/* 187 */     if (isLinkPresent())
/*     */     {
/*     */       String str3;
/* 189 */       if (getWizardSelections().isDeviceSelectionAPump())
/* 190 */         str3 = "pump";
/*     */       else {
/* 192 */         str3 = "cgm";
/*     */       }
/* 194 */       str1 = MessageHelper.format(this.m_resources.getString("wizard.comlinkusb.connected." + str3), new Object[] { str2 });
/*     */     }
/*     */     else
/*     */     {
/* 198 */       str1 = MessageHelper.format(this.m_resources.getString("wizard.comlinkusb.disconnected"), new Object[] { str2 });
/*     */     }
/*     */ 
/* 204 */     String str1 = str1.replaceAll("<FONT SIZE=-2><SUP>TM</SUP></FONT>", "");
/* 205 */     return str1;
/*     */   }
/*     */ 
/*     */   private String getNoteLabelText()
/*     */   {
/* 215 */     String str = "<html><br><br></html>";
/*     */ 
/* 217 */     if ((getWizardSelections().isDeviceSelectionAPump()) && (isLinkPresent()))
/*     */     {
/* 219 */       str = this.m_resources.getString("wizard.comlinkusb.pumpnote");
/*     */     }
/*     */ 
/* 222 */     return str;
/*     */   }
/*     */ 
/*     */   private String getMainLabelImageKey()
/*     */   {
/* 232 */     return isLinkPresent() ? "wizard.read.checkmark" : "wizard.read.rightarrow";
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.steps.mmparadigm.VerifyComLinkUSBConnectionStep
 * JD-Core Version:    0.6.0
 */