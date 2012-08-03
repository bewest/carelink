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
/*     */ import minimed.ddms.applet.dtw.wizard.steps.othermeter.MeterSelectSerialPortStep;
/*     */ 
/*     */ public class SelectMeterOtherStep extends WizardStep
/*     */ {
/*     */   private final DefaultRadioButtonGroup m_buttonGroup;
/*  41 */   private final ActionListener m_radioButtonListener = new ActionListener()
/*     */   {
/*     */     public void actionPerformed(ActionEvent paramActionEvent)
/*     */     {
/*  49 */       SelectMeterOtherStep.this.updateButtonStates();
/*     */     }
/*  41 */   };
/*     */ 
/*     */   public SelectMeterOtherStep(Wizard paramWizard)
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
/*  76 */     Insets localInsets = new Insets(0, 60, 0, 0);
/*  77 */     String str = MessageHelper.format(this.m_resources.getString("wizard.meter.choose"), new Object[] { getWizardSelections().m_selectionGroupMedisenseTherasense });
/*     */ 
/*  80 */     GridBagConstraints localGridBagConstraints1 = new GridBagConstraints();
/*  81 */     localGridBagConstraints1.gridx = 0;
/*  82 */     localGridBagConstraints1.gridy = 0;
/*  83 */     localGridBagConstraints1.gridheight = 1;
/*  84 */     localGridBagConstraints1.gridwidth = 2;
/*  85 */     localGridBagConstraints1.anchor = 17;
/*  86 */     localGridBagConstraints1.insets = localInsets;
/*  87 */     localGridBagConstraints1.fill = 2;
/*  88 */     localJPanel.add(new JLabel(str), localGridBagConstraints1);
/*     */ 
/*  91 */     this.m_buttonGroup = new DefaultRadioButtonGroup(this.m_resources, localJPanel);
/*  92 */     JRadioButton localJRadioButton1 = this.m_buttonGroup.add("wizard.selections.SELECTION_DEVICE_MEDISENSE_XTRA_METER");
/*     */ 
/*  94 */     localJRadioButton1.addActionListener(this.m_radioButtonListener);
/*  95 */     JRadioButton localJRadioButton2 = this.m_buttonGroup.add(formatMeterDevice("wizard.selections.SELECTION_DEVICE_THERASENSE_FREESTYLE_METER"), "wizard.selections.SELECTION_DEVICE_THERASENSE_FREESTYLE_METER");
/*     */ 
/*  98 */     localJRadioButton2.addActionListener(this.m_radioButtonListener);
/*     */ 
/* 101 */     GridBagConstraints localGridBagConstraints2 = ((GridBagLayout)localJPanel.getLayout()).getConstraints(localJRadioButton1);
/* 102 */     localGridBagConstraints2.weightx = 1.0D;
/* 103 */     localGridBagConstraints2.fill = 2;
/* 104 */     ((GridBagLayout)localJPanel.getLayout()).setConstraints(localJRadioButton1, localGridBagConstraints2);
/* 105 */     localGridBagConstraints2 = ((GridBagLayout)localJPanel.getLayout()).getConstraints(localJRadioButton2);
/* 106 */     localGridBagConstraints2.weightx = 1.0D;
/* 107 */     localGridBagConstraints2.fill = 2;
/* 108 */     ((GridBagLayout)localJPanel.getLayout()).setConstraints(localJRadioButton2, localGridBagConstraints2);
/*     */ 
/* 111 */     localGridBagConstraints1 = new GridBagConstraints();
/* 112 */     localGridBagConstraints1.gridx = 0;
/* 113 */     localGridBagConstraints1.gridy = 1;
/* 114 */     localGridBagConstraints1.anchor = 13;
/* 115 */     localGridBagConstraints1.insets = localInsets;
/* 116 */     localJPanel.add(createImageButton("wizard.medisensextra.pic", localJRadioButton1), localGridBagConstraints1);
/*     */ 
/* 119 */     localGridBagConstraints1 = new GridBagConstraints();
/* 120 */     localGridBagConstraints1.gridx = 0;
/* 121 */     localGridBagConstraints1.gridy = 2;
/* 122 */     localGridBagConstraints1.insets = new Insets(10, 0, 0, 0);
/* 123 */     localGridBagConstraints1.anchor = 13;
/* 124 */     localGridBagConstraints1.insets = localInsets;
/* 125 */     localJPanel.add(createImageButton("wizard.therasensefreestyle.pic", localJRadioButton2), localGridBagConstraints1);
/*     */ 
/* 129 */     this.m_buttonGroup.selectButton(getWizardSelections().getMeterDevice(), "wizard.selections.SELECTION_DEVICE_MEDISENSE_XTRA_METER");
/*     */   }
/*     */ 
/*     */   protected void stepShown()
/*     */   {
/* 140 */     super.stepShown();
/* 141 */     updateButtonStates();
/*     */   }
/*     */ 
/*     */   protected void rememberUserSelections()
/*     */   {
/* 149 */     getWizardSelections().setMeterDevice(parseMeterDevice(this.m_buttonGroup.getSelectedButton()));
/*     */   }
/*     */ 
/*     */   protected void updateButtonStates()
/*     */   {
/* 157 */     rememberUserSelections();
/* 158 */     getFinishButton().setEnabled(getWizard().canFinish());
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.steps.SelectMeterOtherStep
 * JD-Core Version:    0.6.0
 */