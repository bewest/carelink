/*     */ package minimed.ddms.applet.dtw.wizard.steps;
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
/*     */ import minimed.ddms.applet.dtw.MessageHelper;
/*     */ import minimed.ddms.applet.dtw.components.DefaultRadioButtonGroup;
/*     */ import minimed.ddms.applet.dtw.wizard.Wizard;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardSelections;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardStep;
/*     */ import minimed.ddms.applet.dtw.wizard.steps.lifescanmeter.MeterSelectSerialPortStep;
/*     */ 
/*     */ public class SelectMeterLifeScanStep extends WizardStep
/*     */ {
/*     */   private final DefaultRadioButtonGroup m_buttonGroup;
/*  40 */   private final ActionListener m_radioButtonListener = new ActionListener()
/*     */   {
/*     */     public void actionPerformed(ActionEvent paramActionEvent)
/*     */     {
/*  48 */       SelectMeterLifeScanStep.this.updateButtonStates();
/*     */     }
/*  40 */   };
/*     */ 
/*     */   public SelectMeterLifeScanStep(Wizard paramWizard)
/*     */   {
/*  61 */     super(paramWizard, MeterSelectSerialPortStep.class);
/*     */ 
/*  64 */     getLeftBannerLabel().setText(this.m_resources.getString("wizard.device.select"));
/*  65 */     Object localObject = new ImageIcon(getImage("wizard.read.icon"));
/*  66 */     getRightBannerLabel().setIcon((Icon)localObject);
/*     */ 
/*  69 */     localObject = getQuestionIcon();
/*  70 */     getTopImageLabel().setIcon((Icon)localObject);
/*     */ 
/*  73 */     JPanel localJPanel = getContentArea();
/*  74 */     GridBagLayout localGridBagLayout = new GridBagLayout();
/*  75 */     localJPanel.setLayout(localGridBagLayout);
/*     */ 
/*  77 */     String str = MessageHelper.format(this.m_resources.getString("wizard.meter.choose"), new Object[] { getWizardSelections().m_selectionBrandLifeScan });
/*     */ 
/*  80 */     GridBagConstraints localGridBagConstraints1 = new GridBagConstraints();
/*  81 */     localGridBagConstraints1.gridx = 0;
/*  82 */     localGridBagConstraints1.gridy = 0;
/*  83 */     localGridBagConstraints1.gridheight = 1;
/*  84 */     localGridBagConstraints1.gridwidth = 4;
/*  85 */     localGridBagConstraints1.anchor = 17;
/*  86 */     localJPanel.add(new JLabel(str), localGridBagConstraints1);
/*     */ 
/*  91 */     this.m_buttonGroup = new DefaultRadioButtonGroup(this.m_resources, localJPanel);
/*  92 */     JRadioButton localJRadioButton1 = this.m_buttonGroup.add("wizard.selections.SELECTION_DEVICE_LIFESCAN_ULTRA_METER", 1, 1);
/*     */ 
/*  94 */     localJRadioButton1.addActionListener(this.m_radioButtonListener);
/*  95 */     JRadioButton localJRadioButton2 = this.m_buttonGroup.add("wizard.selections.SELECTION_DEVICE_LIFESCAN_ULTRAMINI_METER", 3, 1);
/*     */ 
/*  97 */     JRadioButton localJRadioButton3 = this.m_buttonGroup.add("wizard.selections.SELECTION_DEVICE_LIFESCAN_ULTRASMART_METER", 5, 1);
/*     */ 
/*  99 */     localJRadioButton3.addActionListener(this.m_radioButtonListener);
/* 100 */     JRadioButton localJRadioButton4 = this.m_buttonGroup.add("wizard.selections.SELECTION_DEVICE_LIFESCAN_FASTTAKE_METER", 1, 2);
/*     */ 
/* 102 */     localJRadioButton4.addActionListener(this.m_radioButtonListener);
/*     */ 
/* 104 */     GridBagConstraints localGridBagConstraints2 = localGridBagLayout.getConstraints(localJRadioButton4);
/* 105 */     localGridBagConstraints2.gridheight = 2;
/* 106 */     localGridBagLayout.setConstraints(localJRadioButton4, localGridBagConstraints2);
/*     */ 
/* 108 */     JRadioButton localJRadioButton5 = this.m_buttonGroup.add("wizard.selections.SELECTION_DEVICE_LIFESCAN_PROFILE_METER", 3, 2);
/*     */ 
/* 110 */     localJRadioButton5.addActionListener(this.m_radioButtonListener);
/*     */ 
/* 112 */     GridBagConstraints localGridBagConstraints3 = localGridBagLayout.getConstraints(localJRadioButton5);
/* 113 */     localGridBagConstraints3.gridheight = 2;
/* 114 */     localGridBagLayout.setConstraints(localJRadioButton5, localGridBagConstraints3);
/* 115 */     JRadioButton localJRadioButton6 = this.m_buttonGroup.add("wizard.selections.SELECTION_DEVICE_LIFESCAN_BASIC_METER", 5, 2);
/*     */ 
/* 117 */     localJRadioButton6.addActionListener(this.m_radioButtonListener);
/* 118 */     JRadioButton localJRadioButton7 = this.m_buttonGroup.add("wizard.selections.SELECTION_DEVICE_LIFESCAN_SURESTEP_METER", 5, 3);
/*     */ 
/* 120 */     localJRadioButton7.addActionListener(this.m_radioButtonListener);
/*     */ 
/* 124 */     localGridBagConstraints1 = new GridBagConstraints();
/* 125 */     localGridBagConstraints1.gridx = 0;
/* 126 */     localGridBagConstraints1.gridy = 1;
/* 127 */     localGridBagConstraints1.insets = new Insets(10, 0, 0, 0);
/* 128 */     localGridBagConstraints1.anchor = 13;
/* 129 */     localJPanel.add(createImageButton("wizard.lifescanultra.pic", localJRadioButton1), localGridBagConstraints1);
/*     */ 
/* 132 */     localGridBagConstraints1 = new GridBagConstraints();
/* 133 */     localGridBagConstraints1.gridx = 0;
/* 134 */     localGridBagConstraints1.gridy = 2;
/* 135 */     localGridBagConstraints1.gridheight = 2;
/* 136 */     localGridBagConstraints1.insets = new Insets(10, 0, 0, 0);
/* 137 */     localGridBagConstraints1.anchor = 13;
/* 138 */     localJPanel.add(createImageButton("wizard.lifescanfasttake.pic", localJRadioButton4), localGridBagConstraints1);
/*     */ 
/* 142 */     localGridBagConstraints1 = new GridBagConstraints();
/* 143 */     localGridBagConstraints1.gridx = 2;
/* 144 */     localGridBagConstraints1.gridy = 1;
/* 145 */     localGridBagConstraints1.insets = new Insets(10, 0, 0, 0);
/* 146 */     localGridBagConstraints1.anchor = 13;
/* 147 */     localJPanel.add(createImageButton("wizard.lifescanultramini.pic", localJRadioButton2), localGridBagConstraints1);
/*     */ 
/* 150 */     localGridBagConstraints1 = new GridBagConstraints();
/* 151 */     localGridBagConstraints1.gridx = 2;
/* 152 */     localGridBagConstraints1.gridy = 2;
/* 153 */     localGridBagConstraints1.gridheight = 2;
/* 154 */     localGridBagConstraints1.insets = new Insets(10, 20, 0, 0);
/* 155 */     localGridBagConstraints1.anchor = 13;
/* 156 */     localJPanel.add(createImageButton("wizard.lifescanprofile.pic", localJRadioButton5), localGridBagConstraints1);
/*     */ 
/* 160 */     localGridBagConstraints1 = new GridBagConstraints();
/* 161 */     localGridBagConstraints1.gridx = 4;
/* 162 */     localGridBagConstraints1.gridy = 1;
/* 163 */     localGridBagConstraints1.insets = new Insets(10, 20, 0, 0);
/* 164 */     localGridBagConstraints1.anchor = 13;
/* 165 */     localJPanel.add(createImageButton("wizard.lifescanultrasmart.pic", localJRadioButton3), localGridBagConstraints1);
/*     */ 
/* 168 */     localGridBagConstraints1 = new GridBagConstraints();
/* 169 */     localGridBagConstraints1.gridx = 4;
/* 170 */     localGridBagConstraints1.gridy = 2;
/* 171 */     localGridBagConstraints1.gridheight = 2;
/* 172 */     localGridBagConstraints1.insets = new Insets(10, 20, 0, 0);
/* 173 */     localGridBagConstraints1.anchor = 13;
/* 174 */     localJPanel.add(createImageButton("wizard.lifescanbasic.pic", localJRadioButton6), localGridBagConstraints1);
/*     */ 
/* 178 */     this.m_buttonGroup.selectButton(getWizardSelections().getMeterDevice(), "wizard.selections.SELECTION_DEVICE_LIFESCAN_ULTRA_METER");
/*     */   }
/*     */ 
/*     */   protected void stepShown()
/*     */   {
/* 188 */     super.stepShown();
/* 189 */     updateButtonStates();
/*     */   }
/*     */ 
/*     */   protected void rememberUserSelections()
/*     */   {
/* 197 */     getWizardSelections().setMeterDevice(this.m_buttonGroup.getSelectedButton());
/*     */   }
/*     */ 
/*     */   protected void updateButtonStates()
/*     */   {
/* 204 */     rememberUserSelections();
/* 205 */     getFinishButton().setEnabled(getWizard().canFinish());
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.steps.SelectMeterLifeScanStep
 * JD-Core Version:    0.6.0
 */