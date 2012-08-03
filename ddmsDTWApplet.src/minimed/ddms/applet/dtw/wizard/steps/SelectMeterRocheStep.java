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
/*  96 */     localJRadioButton2 = this.m_buttonGroup.add("wizard.selections.SELECTION_DEVICE_ROCHE_AVIVA_METER", 1, 2);
/*     */ 
/*  98 */     localJRadioButton2.addActionListener(this.m_radioButtonListener);
/*  99 */     JRadioButton localJRadioButton3 = this.m_buttonGroup.add("wizard.selections.SELECTION_DEVICE_ROCHE_COMPACT_METER", 3, 1);
/*     */ 
/* 101 */     localJRadioButton3.addActionListener(this.m_radioButtonListener);
/*     */ 
/* 103 */     JRadioButton localJRadioButton4 = null;
/* 104 */     localJRadioButton4 = this.m_buttonGroup.add("wizard.selections.SELECTION_DEVICE_ROCHE_COMPACTPLUS_METER", 3, 2);
/*     */ 
/* 106 */     localJRadioButton4.addActionListener(this.m_radioButtonListener);
/*     */ 
/* 111 */     localGridBagConstraints = new GridBagConstraints();
/* 112 */     localGridBagConstraints.gridx = 0;
/* 113 */     localGridBagConstraints.gridy = 1;
/* 114 */     localGridBagConstraints.anchor = 13;
/* 115 */     localJPanel.add(createImageButton("wizard.rocheactive.pic", localJRadioButton1), localGridBagConstraints);
/*     */ 
/* 118 */     localGridBagConstraints = new GridBagConstraints();
/* 119 */     localGridBagConstraints.gridx = 0;
/* 120 */     localGridBagConstraints.gridy = 2;
/* 121 */     localGridBagConstraints.insets = new Insets(10, 0, 0, 0);
/* 122 */     localGridBagConstraints.anchor = 13;
/* 123 */     localJPanel.add(createImageButton("wizard.rocheaviva.pic", localJRadioButton2), localGridBagConstraints);
/*     */ 
/* 127 */     localGridBagConstraints = new GridBagConstraints();
/* 128 */     localGridBagConstraints.gridx = 2;
/* 129 */     localGridBagConstraints.gridy = 1;
/* 130 */     localGridBagConstraints.insets = new Insets(0, 20, 0, 0);
/* 131 */     localGridBagConstraints.anchor = 13;
/* 132 */     localJPanel.add(createImageButton("wizard.rochecompact.pic", localJRadioButton3), localGridBagConstraints);
/*     */ 
/* 135 */     localGridBagConstraints = new GridBagConstraints();
/* 136 */     localGridBagConstraints.gridx = 2;
/* 137 */     localGridBagConstraints.gridy = 2;
/* 138 */     localGridBagConstraints.insets = new Insets(10, 20, 0, 0);
/* 139 */     localGridBagConstraints.anchor = 13;
/* 140 */     localJPanel.add(createImageButton("wizard.rochecompactplus.pic", localJRadioButton4), localGridBagConstraints);
/*     */ 
/* 144 */     this.m_buttonGroup.selectButton(getWizardSelections().getMeterDevice(), "wizard.selections.SELECTION_DEVICE_ROCHE_ACTIVE_METER");
/*     */   }
/*     */ 
/*     */   protected void stepShown()
/*     */   {
/* 154 */     super.stepShown();
/* 155 */     updateButtonStates();
/*     */   }
/*     */ 
/*     */   protected void rememberUserSelections()
/*     */   {
/* 163 */     getWizardSelections().setMeterDevice(this.m_buttonGroup.getSelectedButton());
/*     */   }
/*     */ 
/*     */   protected void updateButtonStates()
/*     */   {
/* 170 */     rememberUserSelections();
/* 171 */     getFinishButton().setEnabled(getWizard().canFinish());
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.steps.SelectMeterRocheStep
 * JD-Core Version:    0.6.0
 */