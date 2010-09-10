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
/*     */ 
/*  77 */     String str = MessageHelper.format(this.m_resources.getString("wizard.meter.choose"), new Object[] { getWizardSelections().m_selectionGroupMedisenseTherasense });
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
/*  90 */     JRadioButton localJRadioButton1 = this.m_buttonGroup.add("wizard.selections.SELECTION_DEVICE_MEDISENSE_XTRA_METER");
/*     */ 
/*  92 */     localJRadioButton1.addActionListener(this.m_radioButtonListener);
/*  93 */     JRadioButton localJRadioButton2 = this.m_buttonGroup.add(formatMeterDevice("wizard.selections.SELECTION_DEVICE_THERASENSE_FREESTYLE_METER"), "wizard.selections.SELECTION_DEVICE_THERASENSE_FREESTYLE_METER");
/*     */ 
/*  96 */     localJRadioButton2.addActionListener(this.m_radioButtonListener);
/*     */ 
/*  99 */     localGridBagConstraints = new GridBagConstraints();
/* 100 */     localGridBagConstraints.gridx = 0;
/* 101 */     localGridBagConstraints.gridy = 1;
/* 102 */     localGridBagConstraints.anchor = 13;
/* 103 */     localJPanel.add(createImageButton("wizard.medisensextra.pic", localJRadioButton1), localGridBagConstraints);
/*     */ 
/* 106 */     localGridBagConstraints = new GridBagConstraints();
/* 107 */     localGridBagConstraints.gridx = 0;
/* 108 */     localGridBagConstraints.gridy = 2;
/* 109 */     localGridBagConstraints.insets = new Insets(10, 0, 0, 0);
/* 110 */     localGridBagConstraints.anchor = 13;
/* 111 */     localJPanel.add(createImageButton("wizard.therasensefreestyle.pic", localJRadioButton2), localGridBagConstraints);
/*     */ 
/* 115 */     this.m_buttonGroup.selectButton(getWizardSelections().getMeterDevice(), "wizard.selections.SELECTION_DEVICE_MEDISENSE_XTRA_METER");
/*     */   }
/*     */ 
/*     */   protected void stepShown()
/*     */   {
/* 126 */     super.stepShown();
/* 127 */     updateButtonStates();
/*     */   }
/*     */ 
/*     */   protected void rememberUserSelections()
/*     */   {
/* 135 */     getWizardSelections().setMeterDevice(parseMeterDevice(this.m_buttonGroup.getSelectedButton()));
/*     */   }
/*     */ 
/*     */   protected void updateButtonStates()
/*     */   {
/* 143 */     rememberUserSelections();
/* 144 */     getFinishButton().setEnabled(getWizard().canFinish());
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.steps.SelectMeterOtherStep
 * JD-Core Version:    0.6.0
 */