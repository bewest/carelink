/*     */ package minimed.ddms.applet.dtw.wizard.steps.mmparadigm;
/*     */ 
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.Insets;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.Locale;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JRadioButton;
/*     */ import minimed.ddms.applet.dtw.components.DefaultRadioButtonGroup;
/*     */ import minimed.ddms.applet.dtw.wizard.Wizard;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardSelections;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardStep;
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ public class SelectLinkDeviceStep extends WizardStep
/*     */ {
/*     */   private final DefaultRadioButtonGroup m_buttonGroup;
/*  41 */   private final ActionListener m_radioButtonListener = new ActionListener()
/*     */   {
/*     */     public void actionPerformed(ActionEvent paramActionEvent)
/*     */     {
/*  49 */       SelectLinkDeviceStep.this.updateButtonStates();
/*     */     }
/*  41 */   };
/*     */ 
/*     */   public SelectLinkDeviceStep(Wizard paramWizard)
/*     */   {
/*  62 */     super(paramWizard, null);
/*     */ 
/*  65 */     getLeftBannerLabel().setText(this.m_resources.getString("wizard.paradigm.selectlink"));
/*  66 */     Object localObject = new ImageIcon(getImage("wizard.read.icon"));
/*  67 */     getRightBannerLabel().setIcon((Icon)localObject);
/*     */ 
/*  70 */     localObject = getQuestionIcon();
/*  71 */     getTopImageLabel().setIcon((Icon)localObject);
/*     */ 
/*  74 */     JPanel localJPanel = getContentArea();
/*  75 */     localJPanel.setLayout(new GridBagLayout());
/*     */ 
/*  78 */     JRadioButton localJRadioButton1 = null;
/*  79 */     JRadioButton localJRadioButton2 = null;
/*  80 */     this.m_buttonGroup = new DefaultRadioButtonGroup(this.m_resources, localJPanel);
/*  81 */     int i = 1;
/*     */ 
/*  83 */     if (getLocale().equals(Locale.US)) {
/*  84 */       localGridBagConstraints = new GridBagConstraints();
/*  85 */       localGridBagConstraints.insets = new Insets(0, -4, 0, 0);
/*  86 */       localGridBagConstraints.gridx = (i++);
/*  87 */       localGridBagConstraints.gridy = 1;
/*  88 */       localGridBagConstraints.anchor = 17;
/*  89 */       localJRadioButton2 = this.m_buttonGroup.add("wizard.selections.SELECTION_LINK_DEVICE_XTLINKUSB", localGridBagConstraints);
/*     */ 
/*  91 */       localJRadioButton2.addActionListener(this.m_radioButtonListener);
/*     */     }
/*     */ 
/*  96 */     GridBagConstraints localGridBagConstraints = new GridBagConstraints();
/*  97 */     localGridBagConstraints.insets = new Insets(0, 36, 0, 0);
/*  98 */     localGridBagConstraints.gridx = (i++);
/*  99 */     localGridBagConstraints.gridy = 1;
/* 100 */     localGridBagConstraints.anchor = 17;
/* 101 */     localJRadioButton1 = this.m_buttonGroup.add("wizard.selections.SELECTION_LINK_DEVICE_COMLINKUSB", localGridBagConstraints);
/*     */ 
/* 103 */     localJRadioButton1.addActionListener(this.m_radioButtonListener);
/*     */ 
/* 108 */     localGridBagConstraints = new GridBagConstraints();
/* 109 */     localGridBagConstraints.insets = new Insets(0, 36, 0, 0);
/* 110 */     localGridBagConstraints.gridx = (i++);
/* 111 */     localGridBagConstraints.gridy = 1;
/* 112 */     localGridBagConstraints.anchor = 17;
/*     */ 
/* 114 */     JRadioButton localJRadioButton3 = this.m_buttonGroup.add("wizard.selections.SELECTION_LINK_DEVICE_PARADIGMLINK", localGridBagConstraints);
/*     */ 
/* 116 */     localJRadioButton3.addActionListener(this.m_radioButtonListener);
/*     */ 
/* 118 */     localGridBagConstraints = new GridBagConstraints();
/* 119 */     localGridBagConstraints.insets = new Insets(0, 36, 0, 0);
/* 120 */     localGridBagConstraints.gridx = (i++);
/* 121 */     localGridBagConstraints.gridy = 1;
/* 122 */     localGridBagConstraints.anchor = 17;
/*     */ 
/* 124 */     JRadioButton localJRadioButton4 = this.m_buttonGroup.add("wizard.selections.SELECTION_LINK_DEVICE_COMLINK", localGridBagConstraints);
/*     */ 
/* 126 */     localJRadioButton4.addActionListener(this.m_radioButtonListener);
/*     */ 
/* 128 */     i = 1;
/*     */ 
/* 130 */     if (getLocale().equals(Locale.US)) {
/* 131 */       localGridBagConstraints = new GridBagConstraints();
/* 132 */       localGridBagConstraints.gridx = (i++);
/* 133 */       localGridBagConstraints.gridy = 0;
/* 134 */       localGridBagConstraints.anchor = 17;
/* 135 */       localGridBagConstraints.insets = new Insets(10, 0, 0, 0);
/* 136 */       localJPanel.add(createImageButton("wizard.xtlinkusb.pic", localJRadioButton2), localGridBagConstraints);
/*     */     }
/*     */ 
/* 141 */     localGridBagConstraints = new GridBagConstraints();
/* 142 */     localGridBagConstraints.gridx = (i++);
/* 143 */     localGridBagConstraints.gridy = 0;
/* 144 */     localGridBagConstraints.anchor = 17;
/* 145 */     localGridBagConstraints.insets = new Insets(10, 40, 0, 0);
/* 146 */     localJPanel.add(createImageButton("wizard.comlinkusb.pic", localJRadioButton1), localGridBagConstraints);
/*     */ 
/* 151 */     localGridBagConstraints = new GridBagConstraints();
/* 152 */     localGridBagConstraints.gridx = (i++);
/* 153 */     localGridBagConstraints.gridy = 0;
/* 154 */     localGridBagConstraints.anchor = 17;
/* 155 */     localGridBagConstraints.insets = new Insets(10, 40, 0, 0);
/* 156 */     localJPanel.add(createImageButton("wizard.paradigmlink.pic", localJRadioButton3), localGridBagConstraints);
/*     */ 
/* 160 */     localGridBagConstraints = new GridBagConstraints();
/* 161 */     localGridBagConstraints.gridx = (i++);
/* 162 */     localGridBagConstraints.gridy = 0;
/* 163 */     localGridBagConstraints.anchor = 17;
/* 164 */     localGridBagConstraints.insets = new Insets(10, 40, 0, 0);
/* 165 */     localJPanel.add(createImageButton("wizard.comlink.pic", localJRadioButton4), localGridBagConstraints);
/*     */ 
/* 170 */     String str = getLocale().equals(Locale.US) ? "wizard.selections.SELECTION_LINK_DEVICE_XTLINKUSB" : "wizard.selections.SELECTION_LINK_DEVICE_COMLINKUSB";
/*     */ 
/* 173 */     this.m_buttonGroup.selectButton(getWizardSelections().getLinkDevice(str), str);
/*     */   }
/*     */ 
/*     */   protected void nextRequest()
/*     */   {
/* 188 */     rememberUserSelections();
/*     */ 
/* 190 */     Wizard localWizard = getWizard();
/*     */ 
/* 194 */     String str = getWizardSelections().getLinkDevice();
/*     */     Object localObject;
/* 196 */     if (str.equals("wizard.selections.SELECTION_LINK_DEVICE_PARADIGMLINK")) {
/* 197 */       localObject = TurnOffParadigmLinkStep.class;
/*     */     }
/* 199 */     else if (str.equals("wizard.selections.SELECTION_LINK_DEVICE_XTLINKUSB"))
/*     */     {
/* 203 */       if (localWizard.isBayerUSBDriverInstallNeeded())
/* 204 */         localObject = XTLinkUSBDriverInstallAuthorizationStep.class;
/*     */       else
/* 206 */         localObject = VerifyXTLinkUSBConnectionStep.class;
/*     */     }
/* 208 */     else if (str.equals("wizard.selections.SELECTION_LINK_DEVICE_COMLINKUSB"))
/*     */     {
/* 212 */       if (localWizard.isComLink2USBDriverInstallNeeded())
/* 213 */         localObject = ComLinkUSBDriverInstallAuthorizationStep.class;
/* 214 */       else if (!localWizard.hasComLink2EverBeenDetected())
/* 215 */         localObject = ComLinkUSBDriverPostInstallStep.class;
/*     */       else {
/* 217 */         localObject = VerifyComLinkUSBConnectionStep.class;
/*     */       }
/*     */     }
/* 220 */     else if (str.equals("wizard.selections.SELECTION_LINK_DEVICE_COMLINK")) {
/* 221 */       localObject = PumpSelectSerialPortStep.class;
/*     */     } else {
/* 223 */       Contract.unreachable();
/* 224 */       localObject = null;
/*     */     }
/*     */ 
/* 229 */     localWizard.showNextStep((Class)localObject);
/*     */   }
/*     */ 
/*     */   protected void rememberUserSelections()
/*     */   {
/* 237 */     getWizardSelections().setLinkDevice(this.m_buttonGroup.getSelectedButton());
/*     */   }
/*     */ 
/*     */   protected void updateButtonStates()
/*     */   {
/* 244 */     rememberUserSelections();
/* 245 */     getFinishButton().setEnabled(getWizard().canFinish());
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.steps.mmparadigm.SelectLinkDeviceStep
 * JD-Core Version:    0.6.0
 */