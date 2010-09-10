/*     */ package minimed.ddms.applet.dtw.wizard.steps;
/*     */ 
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JRadioButton;
/*     */ import minimed.ddms.applet.dtw.components.DefaultRadioButtonGroup;
/*     */ import minimed.ddms.applet.dtw.wizard.Wizard;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardSelections;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardStep;
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ public class SelectMeterBrandStep extends WizardStep
/*     */ {
/*     */   private final DefaultRadioButtonGroup m_buttonGroup;
/*  39 */   private final ActionListener m_radioButtonListener = new ActionListener()
/*     */   {
/*     */     public void actionPerformed(ActionEvent paramActionEvent)
/*     */     {
/*  47 */       SelectMeterBrandStep.this.updateButtonStates();
/*     */     }
/*  39 */   };
/*     */ 
/*     */   public SelectMeterBrandStep(Wizard paramWizard)
/*     */   {
/*  60 */     super(paramWizard, null);
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
/*  74 */     GridBagConstraints localGridBagConstraints = new GridBagConstraints();
/*  75 */     localGridBagConstraints.gridx = 0;
/*  76 */     localGridBagConstraints.gridy = 0;
/*  77 */     localGridBagConstraints.gridheight = 1;
/*  78 */     localGridBagConstraints.gridwidth = 2;
/*  79 */     localGridBagConstraints.anchor = 17;
/*     */ 
/*  81 */     String str = this.m_resources.getString("wizard.meter.choose.brand");
/*  82 */     localJPanel.add(new JLabel(str), localGridBagConstraints);
/*     */ 
/*  85 */     this.m_buttonGroup = new DefaultRadioButtonGroup(this.m_resources, localJPanel);
/*  86 */     JRadioButton localJRadioButton = this.m_buttonGroup.add("wizard.selections.SELECTION_BRAND_MINIMED_BD");
/*     */ 
/*  88 */     localJRadioButton.addActionListener(this.m_radioButtonListener);
/*  89 */     localJRadioButton = this.m_buttonGroup.add("wizard.selections.SELECTION_BRAND_LIFESCAN");
/*     */ 
/*  91 */     localJRadioButton.addActionListener(this.m_radioButtonListener);
/*  92 */     localJRadioButton = this.m_buttonGroup.add("wizard.selections.SELECTION_BRAND_ASCENSIA_BAYER");
/*     */ 
/*  94 */     localJRadioButton.addActionListener(this.m_radioButtonListener);
/*  95 */     localJRadioButton = this.m_buttonGroup.add("wizard.selections.SELECTION_BRAND_ROCHE");
/*     */ 
/*  97 */     localJRadioButton.addActionListener(this.m_radioButtonListener);
/*  98 */     localJRadioButton = this.m_buttonGroup.add("wizard.selections.SELECTION_GROUP_MEDISENSE_THERASENSE");
/*     */ 
/* 100 */     localJRadioButton.addActionListener(this.m_radioButtonListener);
/* 101 */     localJRadioButton = this.m_buttonGroup.add("wizard.selections.SELECTION_BRAND_BD");
/*     */ 
/* 103 */     localJRadioButton.addActionListener(this.m_radioButtonListener);
/*     */ 
/* 106 */     this.m_buttonGroup.selectButton(getWizardSelections().getMeterBrand(), "wizard.selections.SELECTION_BRAND_MINIMED_BD");
/*     */   }
/*     */ 
/*     */   protected void nextRequest()
/*     */   {
/* 116 */     rememberUserSelections();
/*     */ 
/* 118 */     Wizard localWizard = getWizard();
/*     */ 
/* 122 */     String str = getWizardSelections().getMeterBrand();
/*     */     Class localClass;
/* 124 */     if (str.equals("wizard.selections.SELECTION_BRAND_MINIMED_BD")) {
/* 125 */       localClass = SelectMeterMiniMedStep.class;
/* 126 */     } else if (str.equals("wizard.selections.SELECTION_BRAND_ASCENSIA_BAYER")) {
/* 127 */       localClass = SelectMeterBayerStep.class;
/* 128 */     } else if (str.equals("wizard.selections.SELECTION_BRAND_LIFESCAN")) {
/* 129 */       localClass = SelectMeterLifeScanStep.class;
/* 130 */     } else if (str.equals("wizard.selections.SELECTION_GROUP_MEDISENSE_THERASENSE"))
/*     */     {
/* 132 */       localClass = SelectMeterOtherStep.class;
/* 133 */     } else if (str.equals("wizard.selections.SELECTION_BRAND_ROCHE")) {
/* 134 */       localClass = SelectMeterRocheStep.class;
/* 135 */     } else if (str.equals("wizard.selections.SELECTION_BRAND_BD")) {
/* 136 */       localClass = SelectMeterBDStep.class;
/*     */     } else {
/* 138 */       Contract.unreachable();
/* 139 */       localClass = null;
/*     */     }
/*     */ 
/* 142 */     localWizard.showNextStep(localClass);
/*     */   }
/*     */ 
/*     */   protected void rememberUserSelections()
/*     */   {
/* 150 */     getWizardSelections().setMeterBrand(this.m_buttonGroup.getSelectedButton());
/*     */   }
/*     */ 
/*     */   protected void updateButtonStates()
/*     */   {
/* 157 */     rememberUserSelections();
/* 158 */     getFinishButton().setEnabled(getWizard().canFinish());
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.steps.SelectMeterBrandStep
 * JD-Core Version:    0.6.0
 */