/*     */ package minimed.ddms.applet.dtw.wizard.steps.mmparadigm;
/*     */ 
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.Insets;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
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
/*  40 */   private final ActionListener m_radioButtonListener = new ActionListener()
/*     */   {
/*     */     public void actionPerformed(ActionEvent paramActionEvent)
/*     */     {
/*  48 */       SelectLinkDeviceStep.this.updateButtonStates();
/*     */     }
/*  40 */   };
/*     */ 
/*     */   public SelectLinkDeviceStep(Wizard paramWizard)
/*     */   {
/*  61 */     super(paramWizard, null);
/*     */ 
/*  64 */     getLeftBannerLabel().setText(this.m_resources.getString("wizard.paradigm.selectlink"));
/*  65 */     Object localObject = new ImageIcon(getImage("wizard.read.icon"));
/*  66 */     getRightBannerLabel().setIcon((Icon)localObject);
/*     */ 
/*  69 */     localObject = getQuestionIcon();
/*  70 */     getTopImageLabel().setIcon((Icon)localObject);
/*     */ 
/*  73 */     JPanel localJPanel = getContentArea();
/*  74 */     localJPanel.setLayout(new GridBagLayout());
/*     */ 
/*  77 */     JRadioButton localJRadioButton1 = null;
/*  78 */     this.m_buttonGroup = new DefaultRadioButtonGroup(this.m_resources, localJPanel);
/*  79 */     int i = 1;
/*     */ 
/*  83 */     GridBagConstraints localGridBagConstraints = new GridBagConstraints();
/*  84 */     localGridBagConstraints.insets = new Insets(0, -4, 0, 0);
/*  85 */     localGridBagConstraints.gridx = (i++);
/*  86 */     localGridBagConstraints.gridy = 1;
/*  87 */     localGridBagConstraints.anchor = 17;
/*  88 */     localJRadioButton1 = this.m_buttonGroup.add("wizard.selections.SELECTION_LINK_DEVICE_COMLINKUSB", localGridBagConstraints);
/*     */ 
/*  90 */     localJRadioButton1.addActionListener(this.m_radioButtonListener);
/*     */ 
/*  95 */     localGridBagConstraints = new GridBagConstraints();
/*  96 */     localGridBagConstraints.insets = new Insets(0, 36, 0, 0);
/*  97 */     localGridBagConstraints.gridx = (i++);
/*  98 */     localGridBagConstraints.gridy = 1;
/*  99 */     localGridBagConstraints.anchor = 17;
/*     */ 
/* 101 */     JRadioButton localJRadioButton2 = this.m_buttonGroup.add("wizard.selections.SELECTION_LINK_DEVICE_PARADIGMLINK", localGridBagConstraints);
/*     */ 
/* 103 */     localJRadioButton2.addActionListener(this.m_radioButtonListener);
/*     */ 
/* 105 */     localGridBagConstraints = new GridBagConstraints();
/* 106 */     localGridBagConstraints.insets = new Insets(0, 36, 0, 0);
/* 107 */     localGridBagConstraints.gridx = (i++);
/* 108 */     localGridBagConstraints.gridy = 1;
/* 109 */     localGridBagConstraints.anchor = 17;
/*     */ 
/* 111 */     JRadioButton localJRadioButton3 = this.m_buttonGroup.add("wizard.selections.SELECTION_LINK_DEVICE_COMLINK", localGridBagConstraints);
/*     */ 
/* 113 */     localJRadioButton3.addActionListener(this.m_radioButtonListener);
/*     */ 
/* 115 */     i = 1;
/*     */ 
/* 118 */     localGridBagConstraints = new GridBagConstraints();
/* 119 */     localGridBagConstraints.gridx = (i++);
/* 120 */     localGridBagConstraints.gridy = 0;
/* 121 */     localGridBagConstraints.anchor = 17;
/* 122 */     localGridBagConstraints.insets = new Insets(10, 0, 0, 0);
/* 123 */     localJPanel.add(createImageButton("wizard.comlinkusb.pic", localJRadioButton1), localGridBagConstraints);
/*     */ 
/* 128 */     localGridBagConstraints = new GridBagConstraints();
/* 129 */     localGridBagConstraints.gridx = (i++);
/* 130 */     localGridBagConstraints.gridy = 0;
/* 131 */     localGridBagConstraints.anchor = 17;
/* 132 */     localGridBagConstraints.insets = new Insets(10, 40, 0, 0);
/* 133 */     localJPanel.add(createImageButton("wizard.paradigmlink.pic", localJRadioButton2), localGridBagConstraints);
/*     */ 
/* 137 */     localGridBagConstraints = new GridBagConstraints();
/* 138 */     localGridBagConstraints.gridx = (i++);
/* 139 */     localGridBagConstraints.gridy = 0;
/* 140 */     localGridBagConstraints.anchor = 17;
/* 141 */     localGridBagConstraints.insets = new Insets(10, 40, 0, 0);
/* 142 */     localJPanel.add(createImageButton("wizard.comlink.pic", localJRadioButton3), localGridBagConstraints);
/*     */ 
/* 147 */     this.m_buttonGroup.selectButton(getWizardSelections().getLinkDevice(), "wizard.selections.SELECTION_LINK_DEVICE_COMLINKUSB");
/*     */   }
/*     */ 
/*     */   protected void nextRequest()
/*     */   {
/* 162 */     rememberUserSelections();
/*     */ 
/* 164 */     Wizard localWizard = getWizard();
/*     */ 
/* 168 */     String str = getWizardSelections().getLinkDevice();
/*     */     Class localClass;
/* 170 */     if (str.equals("wizard.selections.SELECTION_LINK_DEVICE_PARADIGMLINK")) {
/* 171 */       localClass = TurnOffParadigmLinkStep.class;
/*     */     }
/* 173 */     else if (str.equals("wizard.selections.SELECTION_LINK_DEVICE_COMLINKUSB"))
/*     */     {
/* 177 */       if (localWizard.isComLink2USBDriverInstallNeeded())
/* 178 */         localClass = ComLinkUSBDriverInstallAuthorizationStep.class;
/* 179 */       else if (!localWizard.hasComLink2EverBeenDetected())
/* 180 */         localClass = ComLinkUSBDriverPostInstallStep.class;
/*     */       else {
/* 182 */         localClass = VerifyComLinkUSBConnectionStep.class;
/*     */       }
/*     */     }
/* 185 */     else if (str.equals("wizard.selections.SELECTION_LINK_DEVICE_COMLINK")) {
/* 186 */       localClass = PumpSelectSerialPortStep.class;
/*     */     } else {
/* 188 */       Contract.unreachable();
/* 189 */       localClass = null;
/*     */     }
/*     */ 
/* 194 */     localWizard.showNextStep(localClass);
/*     */   }
/*     */ 
/*     */   protected void rememberUserSelections()
/*     */   {
/* 202 */     getWizardSelections().setLinkDevice(this.m_buttonGroup.getSelectedButton());
/*     */   }
/*     */ 
/*     */   protected void updateButtonStates()
/*     */   {
/* 209 */     rememberUserSelections();
/* 210 */     getFinishButton().setEnabled(getWizard().canFinish());
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.steps.mmparadigm.SelectLinkDeviceStep
 * JD-Core Version:    0.6.0
 */