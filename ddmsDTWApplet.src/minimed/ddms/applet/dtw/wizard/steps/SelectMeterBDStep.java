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
/*     */ import minimed.ddms.applet.dtw.wizard.WizardConfig;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardSelections;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardStep;
/*     */ import minimed.ddms.applet.dtw.wizard.steps.mmmeter.MeterSelectSerialPortStep;
/*     */ import minimed.ddms.applet.dtw.wizard.steps.mmmeter.SelectConnectionTypeStep;
/*     */ 
/*     */ public class SelectMeterBDStep extends WizardStep
/*     */ {
/*     */   private final DefaultRadioButtonGroup m_buttonGroup;
/*  40 */   private final ActionListener m_radioButtonListener = new ActionListener()
/*     */   {
/*     */     public void actionPerformed(ActionEvent paramActionEvent)
/*     */     {
/*  48 */       SelectMeterBDStep.this.updateButtonStates();
/*     */     }
/*  40 */   };
/*     */ 
/*     */   public SelectMeterBDStep(Wizard paramWizard)
/*     */   {
/*  61 */     super(paramWizard, null);
/*     */ 
/*  64 */     getLeftBannerLabel().setText(this.m_resources.getString("wizard.device.select"));
/*  65 */     Object localObject = new ImageIcon(getImage("wizard.read.icon"));
/*  66 */     getRightBannerLabel().setIcon((Icon)localObject);
/*     */ 
/*  69 */     localObject = getQuestionIcon();
/*  70 */     getTopImageLabel().setIcon((Icon)localObject);
/*     */ 
/*  73 */     JPanel localJPanel = getContentArea();
/*  74 */     localJPanel.setLayout(new GridBagLayout());
/*     */ 
/*  76 */     String str = MessageHelper.format(this.m_resources.getString("wizard.meter.choose"), new Object[] { getWizardSelections().m_selectionBrandBD });
/*     */ 
/*  79 */     GridBagConstraints localGridBagConstraints = new GridBagConstraints();
/*  80 */     localGridBagConstraints.gridx = 0;
/*  81 */     localGridBagConstraints.gridy = 0;
/*  82 */     localGridBagConstraints.gridheight = 1;
/*  83 */     localGridBagConstraints.gridwidth = 2;
/*  84 */     localGridBagConstraints.anchor = 17;
/*  85 */     localJPanel.add(new JLabel(str), localGridBagConstraints);
/*     */ 
/*  88 */     this.m_buttonGroup = new DefaultRadioButtonGroup(this.m_resources, localJPanel);
/*  89 */     JRadioButton localJRadioButton1 = this.m_buttonGroup.add("wizard.selections.SELECTION_DEVICE_MMLINKMETER");
/*     */ 
/*  91 */     localJRadioButton1.addActionListener(this.m_radioButtonListener);
/*  92 */     JRadioButton localJRadioButton2 = this.m_buttonGroup.add("wizard.selections.SELECTION_DEVICE_MMLOGICMETER");
/*     */ 
/*  94 */     localJRadioButton2.addActionListener(this.m_radioButtonListener);
/*     */ 
/*  98 */     localGridBagConstraints = new GridBagConstraints();
/*  99 */     localGridBagConstraints.gridx = 0;
/* 100 */     localGridBagConstraints.gridy = 1;
/* 101 */     localGridBagConstraints.insets = new Insets(10, 0, 0, 0);
/* 102 */     localGridBagConstraints.anchor = 13;
/* 103 */     localJPanel.add(createImageButton("wizard.mmlinkmeter.pic", localJRadioButton1), localGridBagConstraints);
/*     */ 
/* 106 */     localGridBagConstraints = new GridBagConstraints();
/* 107 */     localGridBagConstraints.gridx = 0;
/* 108 */     localGridBagConstraints.gridy = 2;
/* 109 */     localGridBagConstraints.insets = new Insets(10, 0, 0, 0);
/* 110 */     localGridBagConstraints.anchor = 13;
/* 111 */     localJPanel.add(createImageButton("wizard.mmlogicmeter.pic", localJRadioButton2), localGridBagConstraints);
/*     */ 
/* 114 */     this.m_buttonGroup.selectButton(getWizardSelections().getMeterDevice(), "wizard.selections.SELECTION_DEVICE_MMLINKMETER");
/*     */   }
/*     */ 
/*     */   protected void stepShown()
/*     */   {
/* 124 */     super.stepShown();
/* 125 */     updateButtonStates();
/*     */   }
/*     */ 
/*     */   protected void nextRequest()
/*     */   {
/* 132 */     rememberUserSelections();
/* 133 */     getWizard().showNextStep(getNextMiniMedClass());
/*     */   }
/*     */ 
/*     */   protected void rememberUserSelections()
/*     */   {
/* 141 */     getWizardSelections().setMeterDevice(this.m_buttonGroup.getSelectedButton());
/*     */   }
/*     */ 
/*     */   protected void updateButtonStates()
/*     */   {
/* 148 */     rememberUserSelections();
/* 149 */     getFinishButton().setEnabled(getWizard().canFinish());
/*     */   }
/*     */ 
/*     */   private Class getNextMiniMedClass()
/*     */   {
/*     */     Class localClass;
/* 160 */     if (getWizard().getConfig().isWindowsNT())
/*     */     {
/* 162 */       getWizardSelections().setConnectionType("wizard.selections.SELECTION_CONN_TYPE_SERIAL");
/*     */ 
/* 164 */       localClass = MeterSelectSerialPortStep.class;
/*     */     } else {
/* 166 */       localClass = SelectConnectionTypeStep.class;
/*     */     }
/*     */ 
/* 170 */     return localClass;
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.steps.SelectMeterBDStep
 * JD-Core Version:    0.6.0
 */