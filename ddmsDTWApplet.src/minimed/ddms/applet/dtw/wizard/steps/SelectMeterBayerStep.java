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
/*     */ import minimed.ddms.applet.dtw.wizard.steps.bayermeter.MeterSelectSerialPortStep;
/*     */ 
/*     */ public class SelectMeterBayerStep extends WizardStep
/*     */ {
/*     */   private final DefaultRadioButtonGroup m_buttonGroup;
/*  39 */   private final ActionListener m_radioButtonListener = new ActionListener()
/*     */   {
/*     */     public void actionPerformed(ActionEvent paramActionEvent)
/*     */     {
/*  47 */       SelectMeterBayerStep.this.updateButtonStates();
/*     */     }
/*  39 */   };
/*     */ 
/*     */   public SelectMeterBayerStep(Wizard paramWizard)
/*     */   {
/*  60 */     super(paramWizard, MeterSelectSerialPortStep.class);
/*     */ 
/*  63 */     getLeftBannerLabel().setText(this.m_resources.getString("wizard.device.select"));
/*  64 */     Object localObject = new ImageIcon(getImage("wizard.read.icon"));
/*  65 */     getRightBannerLabel().setIcon((Icon)localObject);
/*     */ 
/*  68 */     localObject = getQuestionIcon();
/*  69 */     getTopImageLabel().setIcon((Icon)localObject);
/*     */ 
/*  72 */     JPanel localJPanel = getContentArea();
/*  73 */     localJPanel.setLayout(new GridBagLayout());
/*     */ 
/*  75 */     String str = MessageHelper.format(this.m_resources.getString("wizard.meter.choose"), new Object[] { getWizardSelections().m_selectionBrandAscensiaBayer });
/*     */ 
/*  78 */     GridBagConstraints localGridBagConstraints1 = new GridBagConstraints(0, 0, 4, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 0), 0, 0);
/*     */ 
/*  80 */     localJPanel.add(new JLabel(str), localGridBagConstraints1);
/*     */ 
/*  84 */     this.m_buttonGroup = new DefaultRadioButtonGroup(this.m_resources, localJPanel);
/*  85 */     JRadioButton localJRadioButton1 = this.m_buttonGroup.add("wizard.selections.SELECTION_DEVICE_ASCENSIA_CONTOUR_METER");
/*     */ 
/*  87 */     localJRadioButton1.addActionListener(this.m_radioButtonListener);
/*     */ 
/*  90 */     JRadioButton localJRadioButton2 = this.m_buttonGroup.add("wizard.selections.SELECTION_DEVICE_ASCENSIA_DEX_METER");
/*     */ 
/*  93 */     localJRadioButton2.addActionListener(this.m_radioButtonListener);
/*     */ 
/*  96 */     JRadioButton localJRadioButton3 = this.m_buttonGroup.add("wizard.selections.SELECTION_DEVICE_ASCENSIA_BREEZE_METER", 3, 1);
/*     */ 
/*  99 */     localJRadioButton3.addActionListener(this.m_radioButtonListener);
/*     */ 
/* 102 */     JRadioButton localJRadioButton4 = this.m_buttonGroup.add("wizard.selections.SELECTION_DEVICE_ASCENSIA_ELITE_METER", 3, 2);
/*     */ 
/* 105 */     localJRadioButton4.addActionListener(this.m_radioButtonListener);
/*     */ 
/* 110 */     GridBagConstraints localGridBagConstraints2 = new GridBagConstraints();
/* 111 */     localGridBagConstraints2.gridx = 0;
/* 112 */     localGridBagConstraints2.gridy = 1;
/* 113 */     localGridBagConstraints2.insets = new Insets(10, 0, 0, 0);
/* 114 */     localGridBagConstraints2.anchor = 13;
/* 115 */     localJPanel.add(createImageButton("wizard.bayercontourmeter.pic", localJRadioButton1), localGridBagConstraints2);
/*     */ 
/* 119 */     localGridBagConstraints2 = new GridBagConstraints();
/* 120 */     localGridBagConstraints2.gridx = 0;
/* 121 */     localGridBagConstraints2.gridy = 2;
/* 122 */     localGridBagConstraints2.anchor = 13;
/* 123 */     localGridBagConstraints2.insets = new Insets(10, 0, 0, 0);
/* 124 */     localJPanel.add(createImageButton("wizard.bayerdex2meter.pic", localJRadioButton2), localGridBagConstraints2);
/*     */ 
/* 128 */     localGridBagConstraints2 = new GridBagConstraints();
/* 129 */     localGridBagConstraints2.gridx = 2;
/* 130 */     localGridBagConstraints2.gridy = 1;
/* 131 */     localGridBagConstraints2.insets = new Insets(10, 20, 0, 0);
/* 132 */     localGridBagConstraints2.anchor = 13;
/* 133 */     localJPanel.add(createImageButton("wizard.bayerbreezemeter.pic", localJRadioButton3), localGridBagConstraints2);
/*     */ 
/* 136 */     localGridBagConstraints2 = new GridBagConstraints();
/* 137 */     localGridBagConstraints2.gridx = 2;
/* 138 */     localGridBagConstraints2.gridy = 2;
/* 139 */     localGridBagConstraints2.insets = new Insets(10, 20, 0, 0);
/* 140 */     localGridBagConstraints2.anchor = 13;
/* 141 */     localJPanel.add(createImageButton("wizard.bayerelitexlmeter.pic", localJRadioButton4), localGridBagConstraints2);
/*     */ 
/* 145 */     this.m_buttonGroup.selectButton(getWizardSelections().getMeterDevice(), "wizard.selections.SELECTION_DEVICE_ASCENSIA_CONTOUR_METER");
/*     */   }
/*     */ 
/*     */   protected void stepShown()
/*     */   {
/* 155 */     super.stepShown();
/* 156 */     updateButtonStates();
/*     */   }
/*     */ 
/*     */   protected void rememberUserSelections()
/*     */   {
/* 164 */     getWizardSelections().setMeterDevice(this.m_buttonGroup.getSelectedButton());
/*     */   }
/*     */ 
/*     */   protected void updateButtonStates()
/*     */   {
/* 171 */     rememberUserSelections();
/* 172 */     getFinishButton().setEnabled(getWizard().canFinish());
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.steps.SelectMeterBayerStep
 * JD-Core Version:    0.6.0
 */