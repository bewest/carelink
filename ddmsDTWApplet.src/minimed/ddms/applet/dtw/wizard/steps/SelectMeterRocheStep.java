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
/*     */ import minimed.ddms.applet.dtw.components.DefaultRadioButtonGroup;
/*     */ import minimed.ddms.applet.dtw.wizard.Wizard;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardSelections;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardStep;
/*     */ import minimed.ddms.applet.dtw.wizard.steps.rochemeter.MeterSelectSerialPortStep;
/*     */ 
/*     */ public class SelectMeterRocheStep extends WizardStep
/*     */ {
/*     */   private final DefaultRadioButtonGroup m_buttonGroup;
/*  41 */   private final ActionListener m_radioButtonListener = new ActionListener()
/*     */   {
/*     */     public void actionPerformed(ActionEvent paramActionEvent)
/*     */     {
/*  49 */       SelectMeterRocheStep.this.updateButtonStates();
/*     */     }
/*  41 */   };
/*     */ 
/*     */   public SelectMeterRocheStep(Wizard paramWizard)
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
/*  75 */     localJPanel.setLayout(new GridBagLayout());
/*     */ 
/*  78 */     String str = MessageHelper.format(this.m_resources.getString("wizard.meter.choose"), new Object[] { getWizardSelections().m_selectionBrandRoche });
/*     */ 
/*  81 */     GridBagConstraints localGridBagConstraints = new GridBagConstraints();
/*  82 */     localGridBagConstraints.gridx = 0;
/*  83 */     localGridBagConstraints.gridy = 0;
/*  84 */     localGridBagConstraints.gridheight = 1;
/*  85 */     localGridBagConstraints.gridwidth = 4;
/*  86 */     localGridBagConstraints.anchor = 17;
/*  87 */     localJPanel.add(new JLabel(str), localGridBagConstraints);
/*     */ 
/*  90 */     this.m_buttonGroup = new DefaultRadioButtonGroup(this.m_resources, localJPanel);
/*  91 */     JRadioButton localJRadioButton1 = this.m_buttonGroup.add("wizard.selections.SELECTION_DEVICE_ROCHE_ACTIVE_METER", 1, 1);
/*     */ 
/*  93 */     localJRadioButton1.addActionListener(this.m_radioButtonListener);
/*     */ 
/*  95 */     JRadioButton localJRadioButton2 = null;
/*  96 */     if (showMeter()) {
/*  97 */       localJRadioButton2 = this.m_buttonGroup.add("wizard.selections.SELECTION_DEVICE_ROCHE_AVIVA_METER", 1, 2);
/*     */ 
/*  99 */       localJRadioButton2.addActionListener(this.m_radioButtonListener);
/*     */     }
/* 101 */     JRadioButton localJRadioButton3 = this.m_buttonGroup.add("wizard.selections.SELECTION_DEVICE_ROCHE_COMPACT_METER", 3, 1);
/*     */ 
/* 103 */     localJRadioButton3.addActionListener(this.m_radioButtonListener);
/*     */ 
/* 105 */     JRadioButton localJRadioButton4 = null;
/* 106 */     if (showMeter()) {
/* 107 */       localJRadioButton4 = this.m_buttonGroup.add("wizard.selections.SELECTION_DEVICE_ROCHE_COMPACTPLUS_METER", 3, 2);
/*     */ 
/* 109 */       localJRadioButton4.addActionListener(this.m_radioButtonListener);
/*     */     }
/*     */ 
/* 115 */     localGridBagConstraints = new GridBagConstraints();
/* 116 */     localGridBagConstraints.gridx = 0;
/* 117 */     localGridBagConstraints.gridy = 1;
/* 118 */     localGridBagConstraints.anchor = 13;
/* 119 */     localJPanel.add(createImageButton("wizard.rocheactive.pic", localJRadioButton1), localGridBagConstraints);
/*     */ 
/* 122 */     if (showMeter()) {
/* 123 */       localGridBagConstraints = new GridBagConstraints();
/* 124 */       localGridBagConstraints.gridx = 0;
/* 125 */       localGridBagConstraints.gridy = 2;
/* 126 */       localGridBagConstraints.insets = new Insets(10, 0, 0, 0);
/* 127 */       localGridBagConstraints.anchor = 13;
/* 128 */       localJPanel.add(createImageButton("wizard.rocheaviva.pic", localJRadioButton2), localGridBagConstraints);
/*     */     }
/*     */ 
/* 133 */     localGridBagConstraints = new GridBagConstraints();
/* 134 */     localGridBagConstraints.gridx = 2;
/* 135 */     localGridBagConstraints.gridy = 1;
/* 136 */     localGridBagConstraints.insets = new Insets(0, 20, 0, 0);
/* 137 */     localGridBagConstraints.anchor = 13;
/* 138 */     localJPanel.add(createImageButton("wizard.rochecompact.pic", localJRadioButton3), localGridBagConstraints);
/*     */ 
/* 141 */     if (showMeter()) {
/* 142 */       localGridBagConstraints = new GridBagConstraints();
/* 143 */       localGridBagConstraints.gridx = 2;
/* 144 */       localGridBagConstraints.gridy = 2;
/* 145 */       localGridBagConstraints.insets = new Insets(10, 20, 0, 0);
/* 146 */       localGridBagConstraints.anchor = 13;
/* 147 */       localJPanel.add(createImageButton("wizard.rochecompactplus.pic", localJRadioButton4), localGridBagConstraints);
/*     */     }
/*     */ 
/* 152 */     this.m_buttonGroup.selectButton(getWizardSelections().getMeterDevice(), "wizard.selections.SELECTION_DEVICE_ROCHE_ACTIVE_METER");
/*     */   }
/*     */ 
/*     */   protected void stepShown()
/*     */   {
/* 162 */     super.stepShown();
/* 163 */     updateButtonStates();
/*     */   }
/*     */ 
/*     */   protected void rememberUserSelections()
/*     */   {
/* 171 */     getWizardSelections().setMeterDevice(this.m_buttonGroup.getSelectedButton());
/*     */   }
/*     */ 
/*     */   protected void updateButtonStates()
/*     */   {
/* 178 */     rememberUserSelections();
/* 179 */     getFinishButton().setEnabled(getWizard().canFinish());
/*     */   }
/*     */ 
/*     */   private boolean showMeter()
/*     */   {
/* 189 */     String str = getLocale().getCountry();
/* 190 */     int i = 0;
/* 191 */     if ((Locale.US.getCountry().equals(str)) || (Locale.CANADA.getCountry().equals(str)))
/*     */     {
/* 193 */       i = 1;
/*     */     }
/* 195 */     return i;
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.steps.SelectMeterRocheStep
 * JD-Core Version:    0.6.0
 */