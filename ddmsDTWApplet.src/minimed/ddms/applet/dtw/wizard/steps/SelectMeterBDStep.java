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
/*     */ import minimed.util.OSInfo;
/*     */ 
/*     */ public class SelectMeterBDStep extends WizardStep
/*     */ {
/*     */   private final DefaultRadioButtonGroup m_buttonGroup;
/*  41 */   private final ActionListener m_radioButtonListener = new ActionListener()
/*     */   {
/*     */     public void actionPerformed(ActionEvent paramActionEvent)
/*     */     {
/*  49 */       SelectMeterBDStep.this.updateButtonStates();
/*     */     }
/*  41 */   };
/*     */ 
/*     */   public SelectMeterBDStep(Wizard paramWizard)
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
/*  77 */     String str = MessageHelper.format(this.m_resources.getString("wizard.meter.choose"), new Object[] { getWizardSelections().m_selectionBrandBD });
/*     */ 
/*  80 */     GridBagConstraints localGridBagConstraints = new GridBagConstraints();
/*  81 */     localGridBagConstraints.gridx = 0;
/*  82 */     localGridBagConstraints.gridy = 0;
/*  83 */     localGridBagConstraints.gridheight = 1;
/*  84 */     localGridBagConstraints.gridwidth = 2;
/*  85 */     localGridBagConstraints.anchor = 17;
/*  86 */     localJPanel.add(new JLabel(str), localGridBagConstraints);
/*     */ 
/*  89 */     this.m_buttonGroup = new DefaultRadioButtonGroup(this.m_resources, localJPanel);
/*  90 */     JRadioButton localJRadioButton1 = this.m_buttonGroup.add("wizard.selections.SELECTION_DEVICE_MMLINKMETER");
/*     */ 
/*  92 */     localJRadioButton1.addActionListener(this.m_radioButtonListener);
/*  93 */     JRadioButton localJRadioButton2 = this.m_buttonGroup.add("wizard.selections.SELECTION_DEVICE_MMLOGICMETER");
/*     */ 
/*  95 */     localJRadioButton2.addActionListener(this.m_radioButtonListener);
/*     */ 
/*  99 */     localGridBagConstraints = new GridBagConstraints();
/* 100 */     localGridBagConstraints.gridx = 0;
/* 101 */     localGridBagConstraints.gridy = 1;
/* 102 */     localGridBagConstraints.insets = new Insets(10, 0, 0, 0);
/* 103 */     localGridBagConstraints.anchor = 13;
/* 104 */     localJPanel.add(createImageButton("wizard.mmlinkmeter.pic", localJRadioButton1), localGridBagConstraints);
/*     */ 
/* 107 */     localGridBagConstraints = new GridBagConstraints();
/* 108 */     localGridBagConstraints.gridx = 0;
/* 109 */     localGridBagConstraints.gridy = 2;
/* 110 */     localGridBagConstraints.insets = new Insets(10, 0, 0, 0);
/* 111 */     localGridBagConstraints.anchor = 13;
/* 112 */     localJPanel.add(createImageButton("wizard.mmlogicmeter.pic", localJRadioButton2), localGridBagConstraints);
/*     */ 
/* 115 */     this.m_buttonGroup.selectButton(getWizardSelections().getMeterDevice(), "wizard.selections.SELECTION_DEVICE_MMLINKMETER");
/*     */   }
/*     */ 
/*     */   protected void stepShown()
/*     */   {
/* 125 */     super.stepShown();
/* 126 */     updateButtonStates();
/*     */   }
/*     */ 
/*     */   protected void nextRequest()
/*     */   {
/* 133 */     rememberUserSelections();
/* 134 */     getWizard().showNextStep(getNextMiniMedClass());
/*     */   }
/*     */ 
/*     */   protected void rememberUserSelections()
/*     */   {
/* 142 */     getWizardSelections().setMeterDevice(this.m_buttonGroup.getSelectedButton());
/*     */   }
/*     */ 
/*     */   protected void updateButtonStates()
/*     */   {
/* 149 */     rememberUserSelections();
/* 150 */     getFinishButton().setEnabled(getWizard().canFinish());
/*     */   }
/*     */ 
/*     */   private Class getNextMiniMedClass()
/*     */   {
/*     */     Object localObject;
/* 161 */     if ((getWizard().getConfig().isWindowsNT()) || (OSInfo.isMac()))
/*     */     {
/* 163 */       getWizardSelections().setConnectionType("wizard.selections.SELECTION_CONN_TYPE_SERIAL");
/*     */ 
/* 165 */       localObject = MeterSelectSerialPortStep.class;
/*     */     } else {
/* 167 */       localObject = SelectConnectionTypeStep.class;
/*     */     }
/*     */ 
/* 171 */     return (Class)localObject;
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.steps.SelectMeterBDStep
 * JD-Core Version:    0.6.0
 */