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
/*     */ import javax.swing.SwingUtilities;
/*     */ import minimed.ddms.applet.dtw.DTWApplet;
/*     */ import minimed.ddms.applet.dtw.components.DefaultRadioButtonGroup;
/*     */ import minimed.ddms.applet.dtw.wizard.Wizard;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardSelections;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardStep;
/*     */ import minimed.util.Contract;
/*     */ import minimed.util.OSInfo;
/*     */ 
/*     */ public class SelectMeterBrandStep extends WizardStep
/*     */ {
/*     */   private final DefaultRadioButtonGroup m_buttonGroup;
/*  42 */   private final ActionListener m_radioButtonListener = new ActionListener()
/*     */   {
/*     */     public void actionPerformed(ActionEvent paramActionEvent)
/*     */     {
/*  50 */       SelectMeterBrandStep.this.updateButtonStates();
/*     */     }
/*  42 */   };
/*     */ 
/*     */   public SelectMeterBrandStep(Wizard paramWizard)
/*     */   {
/*  63 */     super(paramWizard, null);
/*     */ 
/*  66 */     getLeftBannerLabel().setText(this.m_resources.getString("wizard.device.select"));
/*  67 */     Object localObject = new ImageIcon(getImage("wizard.read.icon"));
/*  68 */     getRightBannerLabel().setIcon((Icon)localObject);
/*     */ 
/*  71 */     localObject = getQuestionIcon();
/*  72 */     getTopImageLabel().setIcon((Icon)localObject);
/*     */ 
/*  75 */     JPanel localJPanel = getContentArea();
/*  76 */     localJPanel.setLayout(new GridBagLayout());
/*  77 */     GridBagConstraints localGridBagConstraints = new GridBagConstraints();
/*  78 */     localGridBagConstraints.gridx = 0;
/*  79 */     localGridBagConstraints.gridy = 0;
/*  80 */     localGridBagConstraints.gridheight = 1;
/*  81 */     localGridBagConstraints.gridwidth = 2;
/*  82 */     localGridBagConstraints.anchor = 17;
/*     */ 
/*  84 */     String str = this.m_resources.getString("wizard.meter.choose.brand");
/*  85 */     localJPanel.add(new JLabel(str), localGridBagConstraints);
/*     */ 
/*  88 */     this.m_buttonGroup = new DefaultRadioButtonGroup(this.m_resources, localJPanel);
/*  89 */     JRadioButton localJRadioButton = this.m_buttonGroup.add("wizard.selections.SELECTION_BRAND_MINIMED_BD");
/*     */ 
/*  91 */     localJRadioButton.addActionListener(this.m_radioButtonListener);
/*  92 */     localJRadioButton = this.m_buttonGroup.add("wizard.selections.SELECTION_BRAND_LIFESCAN");
/*     */ 
/*  94 */     localJRadioButton.addActionListener(this.m_radioButtonListener);
/*  95 */     localJRadioButton = this.m_buttonGroup.add("wizard.selections.SELECTION_BRAND_ASCENSIA_BAYER");
/*     */ 
/*  97 */     localJRadioButton.addActionListener(this.m_radioButtonListener);
/*  98 */     localJRadioButton = this.m_buttonGroup.add("wizard.selections.SELECTION_BRAND_ROCHE");
/*     */ 
/* 100 */     localJRadioButton.addActionListener(this.m_radioButtonListener);
/* 101 */     localJRadioButton = this.m_buttonGroup.add("wizard.selections.SELECTION_GROUP_MEDISENSE_THERASENSE");
/*     */ 
/* 103 */     localJRadioButton.addActionListener(this.m_radioButtonListener);
/* 104 */     localJRadioButton = this.m_buttonGroup.add("wizard.selections.SELECTION_BRAND_BD");
/*     */ 
/* 106 */     localJRadioButton.addActionListener(this.m_radioButtonListener);
/*     */ 
/* 109 */     this.m_buttonGroup.selectButton(getWizardSelections().getMeterBrand(), "wizard.selections.SELECTION_BRAND_MINIMED_BD");
/*     */   }
/*     */ 
/*     */   protected void stepShown()
/*     */   {
/* 116 */     super.stepShown();
/* 117 */     if (OSInfo.isMac())
/*     */       try {
/* 119 */         DTWApplet localDTWApplet = (DTWApplet)SwingUtilities.getAncestorOfClass(DTWApplet.class, this);
/* 120 */         localDTWApplet.showHelp("helpMeter");
/*     */       } catch (Exception localException) {
/* 122 */         localException.printStackTrace();
/*     */       }
/*     */   }
/*     */ 
/*     */   protected void nextRequest()
/*     */   {
/* 131 */     rememberUserSelections();
/*     */ 
/* 133 */     Wizard localWizard = getWizard();
/*     */ 
/* 137 */     String str = getWizardSelections().getMeterBrand();
/*     */     Object localObject;
/* 139 */     if (str.equals("wizard.selections.SELECTION_BRAND_MINIMED_BD")) {
/* 140 */       localObject = SelectMeterMiniMedStep.class;
/* 141 */     } else if (str.equals("wizard.selections.SELECTION_BRAND_ASCENSIA_BAYER")) {
/* 142 */       localObject = SelectMeterBayerStep.class;
/* 143 */     } else if (str.equals("wizard.selections.SELECTION_BRAND_LIFESCAN")) {
/* 144 */       localObject = SelectMeterLifeScanStep.class;
/* 145 */     } else if (str.equals("wizard.selections.SELECTION_GROUP_MEDISENSE_THERASENSE"))
/*     */     {
/* 147 */       localObject = SelectMeterOtherStep.class;
/* 148 */     } else if (str.equals("wizard.selections.SELECTION_BRAND_ROCHE")) {
/* 149 */       localObject = SelectMeterRocheStep.class;
/* 150 */     } else if (str.equals("wizard.selections.SELECTION_BRAND_BD")) {
/* 151 */       localObject = SelectMeterBDStep.class;
/*     */     } else {
/* 153 */       Contract.unreachable();
/* 154 */       localObject = null;
/*     */     }
/*     */ 
/* 157 */     localWizard.showNextStep((Class)localObject);
/*     */   }
/*     */ 
/*     */   protected void rememberUserSelections()
/*     */   {
/* 165 */     getWizardSelections().setMeterBrand(this.m_buttonGroup.getSelectedButton());
/*     */   }
/*     */ 
/*     */   protected void updateButtonStates()
/*     */   {
/* 172 */     rememberUserSelections();
/* 173 */     getFinishButton().setEnabled(getWizard().canFinish());
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.steps.SelectMeterBrandStep
 * JD-Core Version:    0.6.0
 */