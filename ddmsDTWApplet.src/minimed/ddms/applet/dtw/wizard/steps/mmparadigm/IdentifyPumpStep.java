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
/*     */ import minimed.ddms.applet.dtw.components.NumericTextField;
/*     */ import minimed.ddms.applet.dtw.components.UserEditListener;
/*     */ import minimed.ddms.applet.dtw.wizard.Wizard;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardSelections;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardStep;
/*     */ 
/*     */ public class IdentifyPumpStep extends WizardStep
/*     */   implements UserEditListener
/*     */ {
/*     */   private final JLabel m_msgLabel;
/*     */   private final JLabel m_iconLabel;
/*     */   private final ImageIcon m_paradigmIcon;
/*     */   private final ImageIcon m_cgmIcon;
/*     */   private final NumericTextField m_serialNumber;
/*     */   private String m_prevSerialNumber;
/*     */ 
/*     */   public IdentifyPumpStep(Wizard paramWizard)
/*     */   {
/*  51 */     super(paramWizard, SelectLinkDeviceStep.class);
/*     */ 
/*  54 */     this.m_serialNumber = new NumericTextField();
/*     */ 
/*  57 */     Object localObject = new ImageIcon(getImage("wizard.read.icon"));
/*  58 */     getRightBannerLabel().setIcon((Icon)localObject);
/*     */ 
/*  61 */     localObject = getQuestionIcon();
/*  62 */     getTopImageLabel().setIcon((Icon)localObject);
/*     */ 
/*  65 */     getContentArea().setLayout(new GridBagLayout());
/*     */ 
/*  69 */     this.m_msgLabel = new JLabel();
/*     */ 
/*  72 */     GridBagConstraints localGridBagConstraints = new GridBagConstraints();
/*  73 */     localGridBagConstraints.insets = new Insets(0, 0, 10, 0);
/*  74 */     localGridBagConstraints.anchor = 17;
/*  75 */     localGridBagConstraints.gridx = 0;
/*  76 */     localGridBagConstraints.gridy = 0;
/*  77 */     getContentArea().add(this.m_msgLabel, localGridBagConstraints);
/*     */ 
/*  80 */     localGridBagConstraints = new GridBagConstraints();
/*  81 */     localGridBagConstraints.gridx = 0;
/*  82 */     localGridBagConstraints.gridy = 1;
/*  83 */     localGridBagConstraints.gridwidth = 2;
/*  84 */     localGridBagConstraints.anchor = 10;
/*     */ 
/*  86 */     this.m_iconLabel = new JLabel();
/*  87 */     getContentArea().add(this.m_iconLabel, localGridBagConstraints);
/*  88 */     this.m_paradigmIcon = new ImageIcon(getImage("wizard.paradigmserialnumber.pic"));
/*  89 */     this.m_cgmIcon = new ImageIcon(getImage("wizard.cgmserialnumber.pic"));
/*     */ 
/*  92 */     this.m_serialNumber.addUserEditListener(this);
/*     */ 
/*  95 */     localGridBagConstraints = new GridBagConstraints();
/*  96 */     localGridBagConstraints.gridx = 1;
/*  97 */     localGridBagConstraints.gridy = 0;
/*  98 */     localGridBagConstraints.insets = new Insets(0, 10, 10, 0);
/*  99 */     localGridBagConstraints.anchor = 13;
/* 100 */     getContentArea().add(this.m_serialNumber, localGridBagConstraints);
/*     */   }
/*     */ 
/*     */   public void userEdited()
/*     */   {
/* 109 */     updateButtonStates();
/*     */   }
/*     */ 
/*     */   protected void stepShown()
/*     */   {
/* 117 */     this.m_prevSerialNumber = null;
/* 118 */     super.stepShown();
/*     */ 
/* 121 */     getLeftBannerLabel().setText(getLeftBannerLabelDeviceType());
/*     */ 
/* 124 */     this.m_msgLabel.setText(getMessageLabelDeviceType());
/*     */ 
/* 127 */     this.m_iconLabel.setIcon(getIconLabelImage());
/*     */ 
/* 131 */     String str = getWizardSelectionsSerialNumber();
/* 132 */     if ((str == null) || (!NumericTextField.isValidEntry(str)))
/*     */     {
/* 134 */       this.m_serialNumber.setTextNoUserEditNotify("");
/*     */     }
/* 136 */     else this.m_serialNumber.setTextNoUserEditNotify(str);
/*     */ 
/* 140 */     updateButtonStates();
/*     */   }
/*     */ 
/*     */   protected void updateButtonStates()
/*     */   {
/* 147 */     rememberUserSelections();
/* 148 */     getFinishButton().setEnabled(getWizard().canFinish());
/*     */ 
/* 151 */     if (!this.m_serialNumber.getText().equals(this.m_prevSerialNumber))
/*     */     {
/* 153 */       if (this.m_serialNumber.isValidEntry()) {
/* 154 */         enableWithFocus(getNextButton());
/*     */       }
/*     */       else {
/* 157 */         getNextButton().setEnabled(false);
/* 158 */         enableWithFocus(this.m_serialNumber);
/*     */       }
/*     */     }
/* 161 */     this.m_prevSerialNumber = this.m_serialNumber.getText();
/*     */   }
/*     */ 
/*     */   protected void rememberUserSelections()
/*     */   {
/* 169 */     if (this.m_serialNumber.isValidEntry())
/* 170 */       setWizardSelectionsSerialNumber(this.m_serialNumber.getText());
/*     */     else
/* 172 */       clearWizardSelectionsSerialNumber();
/*     */   }
/*     */ 
/*     */   private String getLeftBannerLabelDeviceType()
/*     */   {
/*     */     String str;
/* 183 */     if (getWizardSelections().isDeviceSelectionAPump()) {
/* 184 */       str = this.m_resources.getString("wizard.paradigm.identify");
/*     */     }
/*     */     else {
/* 187 */       str = this.m_resources.getString("wizard.cgm.identify");
/*     */     }
/* 189 */     return str;
/*     */   }
/*     */ 
/*     */   private String getMessageLabelDeviceType()
/*     */   {
/*     */     String str;
/* 199 */     if (getWizardSelections().isDeviceSelectionAPump()) {
/* 200 */       str = this.m_resources.getString("wizard.paradigm.serial");
/*     */     }
/*     */     else {
/* 203 */       str = this.m_resources.getString("wizard.cgm.serial");
/*     */     }
/* 205 */     return str;
/*     */   }
/*     */ 
/*     */   private ImageIcon getIconLabelImage()
/*     */   {
/*     */     ImageIcon localImageIcon;
/* 215 */     if (getWizardSelections().isDeviceSelectionAPump()) {
/* 216 */       localImageIcon = this.m_paradigmIcon;
/*     */     }
/*     */     else {
/* 219 */       localImageIcon = this.m_cgmIcon;
/*     */     }
/* 221 */     return localImageIcon;
/*     */   }
/*     */ 
/*     */   private String getWizardSelectionsSerialNumber()
/*     */   {
/*     */     String str;
/* 231 */     if (getWizardSelections().isDeviceSelectionAPump()) {
/* 232 */       str = getWizardSelections().getPumpSerialNumber();
/*     */     }
/*     */     else {
/* 235 */       str = getWizardSelections().getCGMSerialNumber();
/*     */     }
/* 237 */     return str;
/*     */   }
/*     */ 
/*     */   private void setWizardSelectionsSerialNumber(String paramString)
/*     */   {
/* 246 */     if (getWizardSelections().isDeviceSelectionAPump()) {
/* 247 */       getWizardSelections().setPumpSerialNumber(paramString);
/*     */     }
/*     */     else
/* 250 */       getWizardSelections().setCGMSerialNumber(paramString);
/*     */   }
/*     */ 
/*     */   private void clearWizardSelectionsSerialNumber()
/*     */   {
/* 258 */     if (getWizardSelections().isDeviceSelectionAPump()) {
/* 259 */       getWizardSelections().clearPumpSerialNumber();
/*     */     }
/*     */     else
/* 262 */       getWizardSelections().clearCGMSerialNumber();
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.steps.mmparadigm.IdentifyPumpStep
 * JD-Core Version:    0.6.0
 */