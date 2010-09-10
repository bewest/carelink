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
/*     */ import minimed.ddms.applet.dtw.MessageHelper;
/*     */ import minimed.ddms.applet.dtw.wizard.Wizard;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardSelections;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardStep;
/*     */ import minimed.ddms.applet.dtw.wizard.steps.USBDriverPostInstallStep;
/*     */ import minimed.ddms.deviceportreader.ComLink2;
/*     */ 
/*     */ public class VerifyComLinkUSBConnectionStep extends WizardStep
/*     */ {
/*     */   private static final String HTML_TM = "<FONT SIZE=-2><SUP>TM</SUP></FONT>";
/*     */   private static final String EMPTY_NOTE = "<html><br><br></html>";
/*     */   private static final int INSET_LEFT = 100;
/*     */   private static final int INSET_TOP = 25;
/*     */   private static final int INSET_BOTTOM = 90;
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
/*  78 */     localGridBagConstraints.insets = new Insets(0, 100, 90, 0);
/*  79 */     localGridBagConstraints.anchor = 18;
/*  80 */     localGridBagConstraints.weightx = 1.0D;
/*  81 */     localJPanel.add(this.m_labelMainText, localGridBagConstraints);
/*     */ 
/*  83 */     this.m_labelNoteText = new JLabel();
/*  84 */     localGridBagConstraints = new GridBagConstraints();
/*  85 */     localGridBagConstraints.gridx = 0;
/*  86 */     localGridBagConstraints.gridy = 1;
/*  87 */     localGridBagConstraints.insets = new Insets(25, 100, 0, 0);
/*  88 */     localGridBagConstraints.anchor = 18;
/*  89 */     localJPanel.add(this.m_labelNoteText, localGridBagConstraints);
/*     */   }
/*     */ 
/*     */   protected void stepShown()
/*     */   {
/*  99 */     super.stepShown();
/*     */ 
/* 103 */     updateButtonStates();
/* 104 */     updateContent();
/*     */   }
/*     */ 
/*     */   protected void backRequest()
/*     */   {
/* 114 */     if ((getWizard().getPreviousStep() instanceof USBDriverPostInstallStep))
/*     */     {
/* 118 */       int i = 0;
/*     */ 
/* 120 */       i += (getWizard().getStep(ComLinkUSBDriverInstallAuthorizationStep.class) != null ? 1 : 0);
/*     */ 
/* 122 */       i += (getWizard().getStep(ComLinkUSBDriverPostInstallStep.class) != null ? 1 : 0);
/*     */ 
/* 124 */       getWizard().showPreviousStep(1 + i);
/*     */     } else {
/* 126 */       getWizard().showPreviousStep(1);
/*     */     }
/*     */   }
/*     */ 
/*     */   protected void finishRequest()
/*     */   {
/* 135 */     getWizard().setUserInputCompleted();
/* 136 */     super.finishRequest();
/*     */   }
/*     */ 
/*     */   protected void updateContent()
/*     */   {
/* 143 */     this.m_labelMainText.setText(getMainLabelText());
/* 144 */     this.m_labelMainText.setIcon(new ImageIcon(getImage(getMainLabelImageKey())));
/*     */ 
/* 146 */     this.m_labelNoteText.setText(getNoteLabelText());
/* 147 */     this.m_labelNoteText.setVisible(true);
/*     */   }
/*     */ 
/*     */   protected void updateButtonStates()
/*     */   {
/* 155 */     getNextButton().setEnabled(false);
/* 156 */     if (ComLink2.isLinkPresent()) {
/* 157 */       enableWithFocus(getFinishButton());
/*     */     }
/*     */     else {
/* 160 */       getFinishButton().setEnabled(false);
/* 161 */       enableWithFocus(getBackButton());
/*     */     }
/*     */   }
/*     */ 
/*     */   private String getMainLabelText()
/*     */   {
/* 174 */     String str2 = this.m_resources.getString(getWizardSelections().getLinkDevice());
/*     */ 
/* 176 */     if (ComLink2.isLinkPresent())
/*     */     {
/*     */       String str3;
/* 178 */       if (getWizardSelections().isDeviceSelectionAPump())
/* 179 */         str3 = "pump";
/*     */       else {
/* 181 */         str3 = "cgm";
/*     */       }
/* 183 */       str1 = MessageHelper.format(this.m_resources.getString("wizard.comlinkusb.connected." + str3), new Object[] { str2 });
/*     */     }
/*     */     else
/*     */     {
/* 187 */       str1 = MessageHelper.format(this.m_resources.getString("wizard.comlinkusb.disconnected"), new Object[] { str2 });
/*     */     }
/*     */ 
/* 193 */     String str1 = str1.replaceAll("<FONT SIZE=-2><SUP>TM</SUP></FONT>", "");
/* 194 */     return str1;
/*     */   }
/*     */ 
/*     */   private String getNoteLabelText()
/*     */   {
/* 204 */     String str = "<html><br><br></html>";
/*     */ 
/* 206 */     if ((getWizardSelections().isDeviceSelectionAPump()) && (ComLink2.isLinkPresent()))
/*     */     {
/* 208 */       str = this.m_resources.getString("wizard.comlinkusb.pumpnote");
/*     */     }
/*     */ 
/* 211 */     return str;
/*     */   }
/*     */ 
/*     */   private String getMainLabelImageKey()
/*     */   {
/* 221 */     return ComLink2.isLinkPresent() ? "wizard.read.checkmark" : "wizard.read.rightarrow";
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.steps.mmparadigm.VerifyComLinkUSBConnectionStep
 * JD-Core Version:    0.6.0
 */