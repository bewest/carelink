/*     */ package minimed.ddms.applet.dtw.wizard.steps;
/*     */ 
/*     */ import java.awt.Color;
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
/*     */ import minimed.ddms.applet.dtw.components.DefaultRadioButtonGroup;
/*     */ import minimed.ddms.applet.dtw.wizard.Wizard;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardConfig;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardSelections;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardStep;
/*     */ import minimed.ddms.applet.dtw.wizard.steps.mmmeter.SelectConnectionTypeStep;
/*     */ import minimed.ddms.applet.dtw.wizard.steps.mmparadigm.CheckPumpStatusStep;
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ public class SelectDeviceStep extends WizardStep
/*     */ {
/*  42 */   private final String m_tokenDevicePump = this.m_resources.getString("wizard.selectDeviceStep.TOKEN_DEVICE_PUMP");
/*     */ 
/*  45 */   private final String m_tokenDeviceMeter = this.m_resources.getString("wizard.selectDeviceStep.TOKEN_DEVICE_METER");
/*     */ 
/*  48 */   private final String m_tokenDeviceCGM = this.m_resources.getString("wizard.selectDeviceStep.TOKEN_DEVICE_CGM");
/*     */   private final DefaultRadioButtonGroup m_buttonGroup;
/*  56 */   private final ActionListener m_radioButtonListener = new ActionListener()
/*     */   {
/*     */     public void actionPerformed(ActionEvent paramActionEvent)
/*     */     {
/*  64 */       SelectDeviceStep.this.updateButtonStates();
/*     */     }
/*  56 */   };
/*     */ 
/*     */   public SelectDeviceStep(Wizard paramWizard)
/*     */   {
/*  77 */     super(paramWizard, null);
/*     */ 
/*  80 */     getLeftBannerLabel().setText(this.m_resources.getString("wizard.device.select"));
/*  81 */     Object localObject = new ImageIcon(getImage("wizard.read.icon"));
/*  82 */     getRightBannerLabel().setIcon((Icon)localObject);
/*     */ 
/*  85 */     localObject = getQuestionIcon();
/*  86 */     getTopImageLabel().setIcon((Icon)localObject);
/*  87 */     String str1 = this.m_resources.getString("wizard.device.disclaimer");
/*  88 */     JLabel localJLabel = new JLabel(str1);
/*     */ 
/*  90 */     JPanel localJPanel1 = new JPanel(new GridBagLayout());
/*  91 */     setCustomColors(localJPanel1);
/*  92 */     JPanel localJPanel2 = getContentArea();
/*  93 */     localJPanel2.setLayout(new GridBagLayout());
/*  94 */     String str2 = this.m_resources.getString("wizard.device.choose");
/*  95 */     localJPanel2.add(localJLabel, new GridBagConstraints(0, 1, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(2, 0, 0, 0), 0, 0));
/*     */ 
/*  97 */     localJPanel2.add(localJPanel1, new GridBagConstraints(0, 2, 1, 1, 0.0D, 1.0D, 10, 1, new Insets(0, 0, 0, 0), 0, 0));
/*     */ 
/*  99 */     localJPanel1.add(new JLabel(str2), new GridBagConstraints(0, 0, 2, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 0), 0, 0));
/*     */ 
/* 101 */     localJLabel.setForeground(Color.gray);
/*     */ 
/* 104 */     this.m_buttonGroup = new DefaultRadioButtonGroup(this.m_resources, localJPanel1);
/* 105 */     JRadioButton localJRadioButton = this.m_buttonGroup.add(formatDeviceType("wizard.selections.SELECTION_DEVICE_PUMP"), "wizard.selections.SELECTION_DEVICE_PUMP");
/*     */ 
/* 108 */     localJRadioButton.addActionListener(this.m_radioButtonListener);
/* 109 */     if (paramWizard.getConfig().isUploadCGM()) {
/* 110 */       localJRadioButton = this.m_buttonGroup.add(formatDeviceType("wizard.selections.SELECTION_DEVICE_CGM"), "wizard.selections.SELECTION_DEVICE_CGM");
/*     */ 
/* 113 */       localJRadioButton.addActionListener(this.m_radioButtonListener);
/*     */     }
/* 115 */     localJRadioButton = this.m_buttonGroup.add(formatDeviceType("wizard.selections.SELECTION_DEVICE_METER"), "wizard.selections.SELECTION_DEVICE_METER");
/*     */ 
/* 118 */     localJRadioButton.addActionListener(this.m_radioButtonListener);
/*     */ 
/* 124 */     if ((!paramWizard.getConfig().isUploadCGM()) && (getWizardSelections().getDeviceType().equals("wizard.selections.SELECTION_DEVICE_CGM")))
/*     */     {
/* 127 */       getWizardSelections().setDeviceType("wizard.selections.SELECTION_DEVICE_PUMP");
/*     */     }
/*     */ 
/* 130 */     this.m_buttonGroup.selectButton(getWizardSelections().getDeviceType(), "wizard.selections.SELECTION_DEVICE_PUMP");
/*     */   }
/*     */ 
/*     */   protected void stepShown()
/*     */   {
/* 140 */     super.stepShown();
/*     */ 
/* 143 */     getBackButton().setEnabled(false);
/*     */   }
/*     */ 
/*     */   protected void nextRequest()
/*     */   {
/* 150 */     rememberUserSelections();
/*     */ 
/* 152 */     Wizard localWizard = getWizard();
/*     */ 
/* 156 */     String str = getWizardSelections().getDeviceType();
/*     */     Class localClass;
/* 158 */     if (str.equals("wizard.selections.SELECTION_DEVICE_PUMP")) {
/* 159 */       if (!localWizard.getConfig().isUploadX15PumpOnly()) {
/* 160 */         localClass = SelectPumpStep.class;
/*     */       }
/*     */       else
/*     */       {
/* 164 */         getWizardSelections().setPumpDevice("wizard.selections.SELECTION_DEVICE_MMPARADIGM2");
/*     */ 
/* 166 */         localClass = CheckPumpStatusStep.class;
/*     */       }
/*     */     }
/* 169 */     else if (str.equals("wizard.selections.SELECTION_DEVICE_METER")) {
/* 170 */       if (!localWizard.getConfig().isUploadParadigmLinkMeterOnly()) {
/* 171 */         localClass = SelectMeterBrandStep.class;
/*     */       }
/*     */       else
/*     */       {
/* 175 */         getWizardSelections().setMeterBrand("wizard.selections.SELECTION_BRAND_MINIMED_BD");
/*     */ 
/* 177 */         getWizardSelections().setMeterDevice("wizard.selections.SELECTION_DEVICE_MMLINKMETER");
/*     */ 
/* 179 */         localClass = SelectConnectionTypeStep.class;
/*     */       }
/*     */     }
/* 182 */     else if (str.equals("wizard.selections.SELECTION_DEVICE_CGM"))
/*     */     {
/* 184 */       getWizardSelections().setCGMDevice("wizard.selections.SELECTION_DEVICE_MMGUARDIAN3");
/*     */ 
/* 186 */       localClass = CheckPumpStatusStep.class;
/*     */     } else {
/* 188 */       Contract.unreachable();
/* 189 */       localClass = null;
/*     */     }
/*     */ 
/* 192 */     localWizard.showNextStep(localClass);
/*     */   }
/*     */ 
/*     */   protected void rememberUserSelections()
/*     */   {
/* 200 */     getWizardSelections().setDeviceType(this.m_buttonGroup.getSelectedButton());
/*     */   }
/*     */ 
/*     */   String formatDeviceType(String paramString)
/*     */   {
/*     */     String str;
/* 212 */     if ("wizard.selections.SELECTION_DEVICE_PUMP".equals(paramString)) {
/* 213 */       str = this.m_tokenDevicePump;
/* 214 */     } else if ("wizard.selections.SELECTION_DEVICE_METER".equals(paramString)) {
/* 215 */       str = this.m_tokenDeviceMeter;
/* 216 */     } else if ("wizard.selections.SELECTION_DEVICE_CGM".equals(paramString)) {
/* 217 */       str = this.m_tokenDeviceCGM;
/*     */     } else {
/* 219 */       Contract.unreachable();
/* 220 */       str = null;
/*     */     }
/* 222 */     return str;
/*     */   }
/*     */ 
/*     */   protected void updateButtonStates()
/*     */   {
/* 229 */     rememberUserSelections();
/* 230 */     getFinishButton().setEnabled(getWizard().canFinish());
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.steps.SelectDeviceStep
 * JD-Core Version:    0.6.0
 */