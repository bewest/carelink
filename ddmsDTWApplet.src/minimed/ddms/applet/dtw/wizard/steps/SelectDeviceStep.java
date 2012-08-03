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
/*     */ import javax.swing.SwingUtilities;
/*     */ import minimed.ddms.applet.dtw.DTWApplet;
/*     */ import minimed.ddms.applet.dtw.components.DefaultRadioButtonGroup;
/*     */ import minimed.ddms.applet.dtw.wizard.Wizard;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardConfig;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardSelections;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardStep;
/*     */ import minimed.ddms.applet.dtw.wizard.steps.mmmeter.SelectConnectionTypeStep;
/*     */ import minimed.ddms.applet.dtw.wizard.steps.mmparadigm.CheckPumpStatusStep;
/*     */ import minimed.util.Contract;
/*     */ import minimed.util.OSInfo;
/*     */ 
/*     */ public class SelectDeviceStep extends WizardStep
/*     */ {
/*  44 */   private final String m_tokenDevicePump = this.m_resources.getString("wizard.selectDeviceStep.TOKEN_DEVICE_PUMP");
/*     */ 
/*  47 */   private final String m_tokenDeviceMeter = this.m_resources.getString("wizard.selectDeviceStep.TOKEN_DEVICE_METER");
/*     */ 
/*  50 */   private final String m_tokenDeviceCGM = this.m_resources.getString("wizard.selectDeviceStep.TOKEN_DEVICE_CGM");
/*     */   private final DefaultRadioButtonGroup m_buttonGroup;
/*  58 */   private final ActionListener m_radioButtonListener = new ActionListener()
/*     */   {
/*     */     public void actionPerformed(ActionEvent paramActionEvent)
/*     */     {
/*  66 */       SelectDeviceStep.this.updateButtonStates();
/*     */     }
/*  58 */   };
/*     */ 
/*     */   public SelectDeviceStep(Wizard paramWizard)
/*     */   {
/*  79 */     super(paramWizard, null);
/*     */ 
/*  82 */     getLeftBannerLabel().setText(this.m_resources.getString("wizard.device.select"));
/*  83 */     Object localObject = new ImageIcon(getImage("wizard.read.icon"));
/*  84 */     getRightBannerLabel().setIcon((Icon)localObject);
/*     */ 
/*  87 */     localObject = getQuestionIcon();
/*  88 */     getTopImageLabel().setIcon((Icon)localObject);
/*  89 */     String str1 = this.m_resources.getString("wizard.device.disclaimer");
/*  90 */     JLabel localJLabel = new JLabel(str1);
/*     */ 
/*  92 */     JPanel localJPanel1 = new JPanel(new GridBagLayout());
/*  93 */     setCustomColors(localJPanel1);
/*  94 */     JPanel localJPanel2 = getContentArea();
/*  95 */     localJPanel2.setLayout(new GridBagLayout());
/*  96 */     String str2 = this.m_resources.getString("wizard.device.choose");
/*  97 */     localJPanel2.add(localJLabel, new GridBagConstraints(0, 1, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(2, 0, 0, 0), 0, 0));
/*     */ 
/*  99 */     localJPanel2.add(localJPanel1, new GridBagConstraints(0, 2, 1, 1, 0.0D, 1.0D, 10, 1, new Insets(0, 0, 0, 0), 0, 0));
/*     */ 
/* 101 */     localJPanel1.add(new JLabel(str2), new GridBagConstraints(0, 0, 2, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 0), 0, 0));
/*     */ 
/* 103 */     localJLabel.setForeground(Color.gray);
/*     */ 
/* 106 */     this.m_buttonGroup = new DefaultRadioButtonGroup(this.m_resources, localJPanel1);
/* 107 */     JRadioButton localJRadioButton = this.m_buttonGroup.add(formatDeviceType("wizard.selections.SELECTION_DEVICE_PUMP"), "wizard.selections.SELECTION_DEVICE_PUMP");
/*     */ 
/* 110 */     localJRadioButton.addActionListener(this.m_radioButtonListener);
/* 111 */     if (paramWizard.getConfig().isUploadCGM()) {
/* 112 */       localJRadioButton = this.m_buttonGroup.add(formatDeviceType("wizard.selections.SELECTION_DEVICE_CGM"), "wizard.selections.SELECTION_DEVICE_CGM");
/*     */ 
/* 115 */       localJRadioButton.addActionListener(this.m_radioButtonListener);
/*     */     }
/* 117 */     localJRadioButton = this.m_buttonGroup.add(formatDeviceType("wizard.selections.SELECTION_DEVICE_METER"), "wizard.selections.SELECTION_DEVICE_METER");
/*     */ 
/* 120 */     localJRadioButton.addActionListener(this.m_radioButtonListener);
/*     */ 
/* 126 */     if ((!paramWizard.getConfig().isUploadCGM()) && (getWizardSelections().getDeviceType().equals("wizard.selections.SELECTION_DEVICE_CGM")))
/*     */     {
/* 129 */       getWizardSelections().setDeviceType("wizard.selections.SELECTION_DEVICE_PUMP");
/*     */     }
/*     */ 
/* 132 */     this.m_buttonGroup.selectButton(getWizardSelections().getDeviceType(), "wizard.selections.SELECTION_DEVICE_PUMP");
/*     */   }
/*     */ 
/*     */   protected void stepShown()
/*     */   {
/* 143 */     super.stepShown();
/* 144 */     if (OSInfo.isMac()) {
/*     */       try {
/* 146 */         DTWApplet localDTWApplet = (DTWApplet)SwingUtilities.getAncestorOfClass(DTWApplet.class, this);
/* 147 */         localDTWApplet.showHelp("helpMeter");
/*     */       } catch (Exception localException) {
/* 149 */         localException.printStackTrace();
/*     */       }
/*     */     }
/*     */ 
/* 153 */     getBackButton().setEnabled(false);
/* 154 */     enableControlCodeHotCorner();
/*     */   }
/*     */ 
/*     */   protected void nextRequest()
/*     */   {
/* 161 */     rememberUserSelections();
/*     */ 
/* 163 */     Wizard localWizard = getWizard();
/*     */ 
/* 167 */     String str = getWizardSelections().getDeviceType();
/*     */     Object localObject;
/* 169 */     if (str.equals("wizard.selections.SELECTION_DEVICE_PUMP"))
/*     */     {
/* 171 */       getWizardSelections().setPumpDevice("wizard.selections.SELECTION_DEVICE_MMPARADIGM2");
/*     */ 
/* 173 */       localObject = CheckPumpStatusStep.class;
/*     */     }
/* 176 */     else if (str.equals("wizard.selections.SELECTION_DEVICE_METER")) {
/* 177 */       if (!localWizard.getConfig().isUploadParadigmLinkMeterOnly()) {
/* 178 */         localObject = SelectMeterBrandStep.class;
/*     */       }
/*     */       else
/*     */       {
/* 182 */         getWizardSelections().setMeterBrand("wizard.selections.SELECTION_BRAND_MINIMED_BD");
/*     */ 
/* 184 */         getWizardSelections().setMeterDevice("wizard.selections.SELECTION_DEVICE_MMLINKMETER");
/*     */ 
/* 186 */         localObject = SelectConnectionTypeStep.class;
/*     */       }
/*     */     }
/* 189 */     else if (str.equals("wizard.selections.SELECTION_DEVICE_CGM"))
/*     */     {
/* 191 */       getWizardSelections().setCGMDevice("wizard.selections.SELECTION_DEVICE_MMGUARDIAN3");
/*     */ 
/* 193 */       localObject = CheckPumpStatusStep.class;
/*     */     } else {
/* 195 */       Contract.unreachable();
/* 196 */       localObject = null;
/*     */     }
/*     */ 
/* 199 */     localWizard.showNextStep((Class)localObject);
/*     */   }
/*     */ 
/*     */   protected void rememberUserSelections()
/*     */   {
/* 207 */     getWizardSelections().setDeviceType(this.m_buttonGroup.getSelectedButton());
/*     */   }
/*     */ 
/*     */   String formatDeviceType(String paramString)
/*     */   {
/*     */     String str;
/* 219 */     if ("wizard.selections.SELECTION_DEVICE_PUMP".equals(paramString)) {
/* 220 */       str = this.m_tokenDevicePump;
/* 221 */     } else if ("wizard.selections.SELECTION_DEVICE_METER".equals(paramString)) {
/* 222 */       str = this.m_tokenDeviceMeter;
/* 223 */     } else if ("wizard.selections.SELECTION_DEVICE_CGM".equals(paramString)) {
/* 224 */       str = this.m_tokenDeviceCGM;
/*     */     } else {
/* 226 */       Contract.unreachable();
/* 227 */       str = null;
/*     */     }
/* 229 */     return str;
/*     */   }
/*     */ 
/*     */   protected void updateButtonStates()
/*     */   {
/* 236 */     rememberUserSelections();
/* 237 */     getFinishButton().setEnabled(getWizard().canFinish());
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.steps.SelectDeviceStep
 * JD-Core Version:    0.6.0
 */