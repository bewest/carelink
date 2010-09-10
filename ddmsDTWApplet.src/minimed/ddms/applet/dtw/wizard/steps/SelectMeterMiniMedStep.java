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
/*     */ import minimed.ddms.applet.dtw.components.DefaultRadioButtonGroup;
/*     */ import minimed.ddms.applet.dtw.wizard.Wizard;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardConfig;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardSelections;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardStep;
/*     */ import minimed.ddms.applet.dtw.wizard.steps.mmmeter.SelectConnectionTypeStep;
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ public class SelectMeterMiniMedStep extends WizardStep
/*     */ {
/*     */   private final DefaultRadioButtonGroup m_buttonGroup;
/*  41 */   private final ActionListener m_radioButtonListener = new ActionListener()
/*     */   {
/*     */     public void actionPerformed(ActionEvent paramActionEvent)
/*     */     {
/*  49 */       SelectMeterMiniMedStep.this.updateButtonStates();
/*     */     }
/*  41 */   };
/*     */ 
/*     */   public SelectMeterMiniMedStep(Wizard paramWizard)
/*     */   {
/*  62 */     super(paramWizard, null);
/*     */ 
/*  65 */     getLeftBannerLabel().setText(this.m_resources.getString("wizard.device.select"));
/*  66 */     Object localObject = new ImageIcon(getImage("wizard.read.icon"));
/*  67 */     getRightBannerLabel().setIcon((Icon)localObject);
/*     */ 
/*  70 */     localObject = getQuestionIcon();
/*  71 */     getTopImageLabel().setIcon((Icon)localObject);
/*     */ 
/*  74 */     JPanel localJPanel = getContentArea();
/*  75 */     localJPanel.setLayout(new GridBagLayout());
/*     */ 
/*  77 */     String str = this.m_resources.getString("wizard.meter.choose.link");
/*  78 */     GridBagConstraints localGridBagConstraints = new GridBagConstraints();
/*  79 */     localGridBagConstraints.gridx = 0;
/*  80 */     localGridBagConstraints.gridy = 0;
/*  81 */     localGridBagConstraints.gridheight = 1;
/*  82 */     localGridBagConstraints.gridwidth = 6;
/*  83 */     localGridBagConstraints.anchor = 17;
/*  84 */     localJPanel.add(new JLabel(str), localGridBagConstraints);
/*     */ 
/*  87 */     this.m_buttonGroup = new DefaultRadioButtonGroup(this.m_resources, localJPanel);
/*  88 */     JRadioButton localJRadioButton1 = null;
/*  89 */     if (!Locale.US.equals(getLocale().getCountry())) {
/*  90 */       localJRadioButton1 = this.m_buttonGroup.add(getWizardSelections().m_selectionDeviceAscensiaContourLink, "wizard.selections.SELECTION_DEVICE_ASCENSIA_CONTOUR_METER", 1, 1);
/*     */ 
/*  93 */       localJRadioButton1.addActionListener(this.m_radioButtonListener);
/*     */     }
/*  95 */     JRadioButton localJRadioButton2 = this.m_buttonGroup.add(getWizardSelections().m_selectionDeviceLifeScanUltraLink, "wizard.selections.SELECTION_DEVICE_LIFESCAN_ULTRA_METER", 3, 1);
/*     */ 
/*  98 */     localJRadioButton2.addActionListener(this.m_radioButtonListener);
/*  99 */     JRadioButton localJRadioButton3 = this.m_buttonGroup.add("wizard.selections.SELECTION_DEVICE_MMLINKMETER", 5, 1);
/*     */ 
/* 101 */     localJRadioButton3.addActionListener(this.m_radioButtonListener);
/*     */ 
/* 106 */     if (localJRadioButton1 != null) {
/* 107 */       localGridBagConstraints = new GridBagConstraints();
/* 108 */       localGridBagConstraints.gridx = 0;
/* 109 */       localGridBagConstraints.gridy = 1;
/* 110 */       localGridBagConstraints.insets = new Insets(10, 0, 0, 0);
/* 111 */       localGridBagConstraints.anchor = 13;
/* 112 */       localJPanel.add(createImageButton("wizard.bayercontourmeter.pic", localJRadioButton1), localGridBagConstraints);
/*     */     }
/*     */ 
/* 116 */     localGridBagConstraints = new GridBagConstraints();
/* 117 */     localGridBagConstraints.gridx = 2;
/* 118 */     localGridBagConstraints.gridy = 1;
/* 119 */     localGridBagConstraints.insets = new Insets(10, 20, 0, 0);
/* 120 */     localGridBagConstraints.anchor = 13;
/* 121 */     localJPanel.add(createImageButton("wizard.lifescanultra.pic", localJRadioButton2), localGridBagConstraints);
/*     */ 
/* 123 */     localGridBagConstraints = new GridBagConstraints();
/* 124 */     localGridBagConstraints.gridx = 4;
/* 125 */     localGridBagConstraints.gridy = 1;
/* 126 */     localGridBagConstraints.insets = new Insets(10, 20, 0, 0);
/* 127 */     localGridBagConstraints.anchor = 13;
/* 128 */     localJPanel.add(createImageButton("wizard.mmlinkmeter.pic", localJRadioButton3), localGridBagConstraints);
/*     */ 
/* 132 */     this.m_buttonGroup.selectButton(getWizardSelections().getMeterDevice(), "wizard.selections.SELECTION_DEVICE_LIFESCAN_ULTRA_METER");
/*     */   }
/*     */ 
/*     */   protected void stepShown()
/*     */   {
/* 142 */     super.stepShown();
/* 143 */     updateButtonStates();
/*     */   }
/*     */ 
/*     */   protected void nextRequest()
/*     */   {
/* 150 */     rememberUserSelections();
/* 151 */     getWizard().showNextStep(getNextMiniMedClass());
/*     */   }
/*     */ 
/*     */   protected void rememberUserSelections()
/*     */   {
/* 159 */     getWizardSelections().setMeterDevice(this.m_buttonGroup.getSelectedButton());
/*     */   }
/*     */ 
/*     */   protected void updateButtonStates()
/*     */   {
/* 166 */     rememberUserSelections();
/* 167 */     getFinishButton().setEnabled(getWizard().canFinish());
/*     */   }
/*     */ 
/*     */   private Class getNextMiniMedClass()
/*     */   {
/*     */     Class localClass;
/* 177 */     if (getWizardSelections().getMeterDevice().equals("wizard.selections.SELECTION_DEVICE_MMLINKMETER"))
/*     */     {
/* 179 */       if (getWizard().getConfig().isWindowsNT())
/*     */       {
/* 181 */         getWizardSelections().setConnectionType("wizard.selections.SELECTION_CONN_TYPE_SERIAL");
/*     */ 
/* 183 */         localClass = minimed.ddms.applet.dtw.wizard.steps.mmmeter.MeterSelectSerialPortStep.class;
/*     */       } else {
/* 185 */         localClass = SelectConnectionTypeStep.class;
/*     */       }
/*     */ 
/*     */     }
/* 189 */     else if (getWizardSelections().getMeterDevice().equals("wizard.selections.SELECTION_DEVICE_ASCENSIA_CONTOUR_METER"))
/*     */     {
/* 191 */       localClass = minimed.ddms.applet.dtw.wizard.steps.bayermeter.MeterSelectSerialPortStep.class;
/*     */     }
/* 194 */     else if (getWizardSelections().getMeterDevice().equals("wizard.selections.SELECTION_DEVICE_LIFESCAN_ULTRA_METER"))
/*     */     {
/* 196 */       localClass = minimed.ddms.applet.dtw.wizard.steps.lifescanmeter.MeterSelectSerialPortStep.class;
/*     */     }
/*     */     else
/*     */     {
/* 200 */       localClass = null;
/* 201 */       Contract.unreachable();
/*     */     }
/*     */ 
/* 204 */     return localClass;
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.steps.SelectMeterMiniMedStep
 * JD-Core Version:    0.6.0
 */