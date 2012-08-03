/*     */ package minimed.ddms.applet.dtw.wizard.steps;
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
/*     */ import minimed.ddms.applet.dtw.MessageHelper;
/*     */ import minimed.ddms.applet.dtw.components.ClickAdapter;
/*     */ import minimed.ddms.applet.dtw.components.DefaultRadioButtonGroup;
/*     */ import minimed.ddms.applet.dtw.wizard.Wizard;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardSelections;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardStep;
/*     */ import minimed.ddms.applet.dtw.wizard.steps.bayermeter.MeterSelectSerialPortStep;
/*     */ import minimed.ddms.applet.dtw.wizard.steps.bayermeter.USBDriverInstallAuthorizationStep;
/*     */ import minimed.ddms.applet.dtw.wizard.steps.bayermeter.VerifyConnectionsStep;
/*     */ 
/*     */ public class SelectMeterBayerStep extends WizardStep
/*     */ {
/*     */   private final DefaultRadioButtonGroup m_buttonGroup;
/*  41 */   private final ActionListener m_radioButtonListener = new ActionListener()
/*     */   {
/*     */     public void actionPerformed(ActionEvent paramActionEvent)
/*     */     {
/*  49 */       SelectMeterBayerStep.this.updateButtonStates();
/*     */     }
/*  41 */   };
/*     */ 
/*     */   public SelectMeterBayerStep(Wizard paramWizard)
/*     */   {
/*  62 */     super(paramWizard, MeterSelectSerialPortStep.class);
/*     */ 
/*  65 */     getLeftBannerLabel().setText(this.m_resources.getString("wizard.device.select"));
/*  66 */     Object localObject = new ImageIcon(getImage("wizard.read.icon"));
/*  67 */     getRightBannerLabel().setIcon((Icon)localObject);
/*     */ 
/*  70 */     localObject = getQuestionIcon();
/*  71 */     getTopImageLabel().setIcon((Icon)localObject);
/*     */ 
/*  74 */     JPanel localJPanel = getContentArea();
/*  75 */     GridBagLayout localGridBagLayout = new GridBagLayout();
/*  76 */     localJPanel.setLayout(localGridBagLayout);
/*     */ 
/*  78 */     String str1 = MessageHelper.format(this.m_resources.getString("wizard.meter.choose"), new Object[] { getWizardSelections().m_selectionBrandAscensiaBayer });
/*     */ 
/*  81 */     GridBagConstraints localGridBagConstraints1 = new GridBagConstraints(0, 0, 6, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 0), 0, 0);
/*     */ 
/*  83 */     localJPanel.add(new JLabel(str1), localGridBagConstraints1);
/*     */ 
/*  87 */     this.m_buttonGroup = new DefaultRadioButtonGroup(this.m_resources, localJPanel);
/*  88 */     JRadioButton localJRadioButton1 = this.m_buttonGroup.add("wizard.selections.SELECTION_DEVICE_ASCENSIA_CONTOUR_METER");
/*     */ 
/*  90 */     localJRadioButton1.addActionListener(this.m_radioButtonListener);
/*     */ 
/*  92 */     GridBagConstraints localGridBagConstraints2 = localGridBagLayout.getConstraints(localJRadioButton1);
/*  93 */     localGridBagConstraints2.gridheight = 2;
/*  94 */     localGridBagLayout.setConstraints(localJRadioButton1, localGridBagConstraints2);
/*     */ 
/*  97 */     JRadioButton localJRadioButton2 = this.m_buttonGroup.add("wizard.selections.SELECTION_DEVICE_ASCENSIA_BREEZE_METER", 3, 1);
/*     */ 
/* 100 */     localJRadioButton2.addActionListener(this.m_radioButtonListener);
/*     */ 
/* 102 */     localGridBagConstraints2 = localGridBagLayout.getConstraints(localJRadioButton2);
/* 103 */     localGridBagConstraints2.gridheight = 2;
/* 104 */     localGridBagLayout.setConstraints(localJRadioButton2, localGridBagConstraints2);
/*     */ 
/* 107 */     JRadioButton localJRadioButton3 = this.m_buttonGroup.add("wizard.selections.SELECTION_DEVICE_ASCENSIA_DEX_METER", 5, 1);
/*     */ 
/* 110 */     localJRadioButton3.addActionListener(this.m_radioButtonListener);
/*     */ 
/* 113 */     JRadioButton localJRadioButton4 = this.m_buttonGroup.add("wizard.selections.SELECTION_DEVICE_ASCENSIA_CONTOUR_USB_METER", 1, 3);
/*     */ 
/* 116 */     localJRadioButton4.addActionListener(this.m_radioButtonListener);
/* 117 */     JRadioButton localJRadioButton5 = null;
/* 118 */     if (getLocale().equals(Locale.US))
/*     */     {
/* 120 */       localGridBagConstraints2 = localGridBagLayout.getConstraints(localJRadioButton4);
/* 121 */       localGridBagConstraints2.anchor = 16;
/* 122 */       localGridBagConstraints2.insets = new Insets(20, 0, 0, 0);
/* 123 */       localGridBagLayout.setConstraints(localJRadioButton4, localGridBagConstraints2);
/* 124 */       localJRadioButton5 = this.m_buttonGroup.add("wizard.selections.SELECTION_DEVICE_XTLINKMETER", 1, 4);
/*     */ 
/* 127 */       localJRadioButton5.addActionListener(this.m_radioButtonListener);
/* 128 */       localGridBagConstraints2 = localGridBagLayout.getConstraints(localJRadioButton5);
/* 129 */       localGridBagConstraints2.anchor = 18;
/* 130 */       localGridBagConstraints2.insets = new Insets(0, 0, 10, 0);
/* 131 */       localGridBagLayout.setConstraints(localJRadioButton5, localGridBagConstraints2);
/*     */     }
/*     */ 
/* 135 */     JRadioButton localJRadioButton6 = this.m_buttonGroup.add("wizard.selections.SELECTION_DEVICE_ASCENSIA_ELITE_METER", 3, 3);
/*     */ 
/* 138 */     localJRadioButton6.addActionListener(this.m_radioButtonListener);
/* 139 */     localGridBagConstraints2 = localGridBagLayout.getConstraints(localJRadioButton6);
/* 140 */     localGridBagConstraints2.gridheight = 2;
/* 141 */     localGridBagLayout.setConstraints(localJRadioButton6, localGridBagConstraints2);
/*     */ 
/* 148 */     GridBagConstraints localGridBagConstraints3 = new GridBagConstraints();
/* 149 */     localGridBagConstraints3.gridx = 0;
/* 150 */     localGridBagConstraints3.gridy = 1;
/* 151 */     localGridBagConstraints3.gridheight = 1;
/* 152 */     localGridBagConstraints3.insets = new Insets(10, 0, 0, 0);
/* 153 */     localGridBagConstraints3.anchor = 13;
/* 154 */     localJPanel.add(createImageButton("wizard.bayercontourmeter.pic", localJRadioButton1), localGridBagConstraints3);
/*     */ 
/* 158 */     localGridBagConstraints3 = new GridBagConstraints();
/* 159 */     localGridBagConstraints3.gridx = 2;
/* 160 */     localGridBagConstraints3.gridy = 1;
/* 161 */     localGridBagConstraints3.gridheight = 1;
/* 162 */     localGridBagConstraints3.insets = new Insets(10, 20, 0, 0);
/* 163 */     localGridBagConstraints3.anchor = 13;
/* 164 */     localJPanel.add(createImageButton("wizard.bayerbreezemeter.pic", localJRadioButton2), localGridBagConstraints3);
/*     */ 
/* 167 */     localGridBagConstraints3 = new GridBagConstraints();
/* 168 */     localGridBagConstraints3.gridx = 4;
/* 169 */     localGridBagConstraints3.gridy = 1;
/* 170 */     localGridBagConstraints3.gridheight = 1;
/* 171 */     localGridBagConstraints3.anchor = 13;
/* 172 */     localGridBagConstraints3.insets = new Insets(10, 20, 0, 0);
/* 173 */     localJPanel.add(createImageButton("wizard.bayerdex2meter.pic", localJRadioButton3), localGridBagConstraints3);
/*     */ 
/* 178 */     localGridBagConstraints3 = new GridBagConstraints();
/* 179 */     localGridBagConstraints3.gridx = 0;
/* 180 */     localGridBagConstraints3.gridy = 3;
/* 181 */     localGridBagConstraints3.gridheight = 2;
/* 182 */     localGridBagConstraints3.insets = new Insets(10, 0, 0, 0);
/* 183 */     localGridBagConstraints3.anchor = 13;
/* 184 */     JLabel localJLabel = createImageButton("wizard.bayercontourusbmeter.pic", localJRadioButton4);
/* 185 */     localJPanel.add(localJLabel, localGridBagConstraints3);
/* 186 */     localJPanel.add(createImageButton("wizard.bayercontourusbmeter.pic", localJRadioButton4), localGridBagConstraints3);
/*     */ 
/* 188 */     if (localJRadioButton5 != null) {
/* 189 */       localJLabel.addMouseListener(new ClickAdapter(localJRadioButton5, localJLabel));
/*     */     }
/*     */ 
/* 193 */     localGridBagConstraints3 = new GridBagConstraints();
/* 194 */     localGridBagConstraints3.gridx = 2;
/* 195 */     localGridBagConstraints3.gridy = 3;
/* 196 */     localGridBagConstraints3.gridheight = 2;
/* 197 */     localGridBagConstraints3.insets = new Insets(10, 20, 0, 0);
/* 198 */     localGridBagConstraints3.anchor = 13;
/* 199 */     localJPanel.add(createImageButton("wizard.bayerelitexlmeter.pic", localJRadioButton6), localGridBagConstraints3);
/*     */ 
/* 205 */     String str2 = "wizard.selections.SELECTION_DEVICE_ASCENSIA_CONTOUR_METER";
/* 206 */     if (getLocale().equals(Locale.US))
/*     */     {
/* 208 */       str2 = "wizard.selections.SELECTION_DEVICE_XTLINKMETER";
/*     */     }
/* 210 */     this.m_buttonGroup.selectButton(getWizardSelections().getMeterDevice(), str2);
/*     */   }
/*     */ 
/*     */   protected void stepShown()
/*     */   {
/* 219 */     super.stepShown();
/* 220 */     updateButtonStates();
/*     */   }
/*     */ 
/*     */   protected void rememberUserSelections()
/*     */   {
/* 228 */     getWizardSelections().setMeterDevice(this.m_buttonGroup.getSelectedButton());
/*     */   }
/*     */ 
/*     */   protected void updateButtonStates()
/*     */   {
/* 235 */     rememberUserSelections();
/* 236 */     getFinishButton().setEnabled(getWizard().canFinish());
/*     */   }
/*     */ 
/*     */   protected void nextRequest()
/*     */   {
/* 243 */     rememberUserSelections();
/* 244 */     Wizard localWizard = getWizard();
/*     */     Object localObject;
/* 246 */     if ((getWizardSelections().getMeterDevice().equals("wizard.selections.SELECTION_DEVICE_ASCENSIA_CONTOUR_USB_METER")) || (getWizardSelections().getMeterDevice().equals("wizard.selections.SELECTION_DEVICE_XTLINKMETER")))
/*     */     {
/* 250 */       if (localWizard.isBayerUSBDriverInstallNeeded()) {
/* 251 */         logInfo("Install Bayer USB Driver is neeeded...");
/* 252 */         localObject = USBDriverInstallAuthorizationStep.class;
/*     */       } else {
/* 254 */         localObject = VerifyConnectionsStep.class;
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 259 */       localObject = MeterSelectSerialPortStep.class;
/*     */     }
/*     */ 
/* 262 */     localWizard.showNextStep((Class)localObject);
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.steps.SelectMeterBayerStep
 * JD-Core Version:    0.6.0
 */