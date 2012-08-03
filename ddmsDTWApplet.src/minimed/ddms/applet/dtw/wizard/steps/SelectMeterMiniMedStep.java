/*     */ package minimed.ddms.applet.dtw.wizard.steps;
/*     */ 
/*     */ import java.awt.Font;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.Insets;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.Locale;
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
/*     */ import minimed.ddms.applet.dtw.wizard.steps.bayermeter.USBDriverInstallAuthorizationStep;
/*     */ import minimed.ddms.applet.dtw.wizard.steps.bayermeter.VerifyConnectionsStep;
/*     */ import minimed.ddms.applet.dtw.wizard.steps.mmmeter.SelectConnectionTypeStep;
/*     */ import minimed.util.Contract;
/*     */ import minimed.util.OSInfo;
/*     */ 
/*     */ public class SelectMeterMiniMedStep extends WizardStep
/*     */ {
/*     */   public static final int COL_INSET_SPACING = 12;
/*     */   private final DefaultRadioButtonGroup m_buttonGroup;
/*  46 */   private final ActionListener m_radioButtonListener = new ActionListener()
/*     */   {
/*     */     public void actionPerformed(ActionEvent paramActionEvent)
/*     */     {
/*  54 */       SelectMeterMiniMedStep.this.updateButtonStates();
/*     */     }
/*  46 */   };
/*     */ 
/*     */   public SelectMeterMiniMedStep(Wizard paramWizard)
/*     */   {
/*  67 */     super(paramWizard, null);
/*  68 */     Font localFont = null;
/*  69 */     if (getFont().getSize() > 12) {
/*  70 */       localFont = new Font(getFont().getFamily(), getFont().getStyle(), 12);
/*     */     }
/*     */ 
/*  74 */     getLeftBannerLabel().setText(this.m_resources.getString("wizard.device.select"));
/*  75 */     Object localObject = new ImageIcon(getImage("wizard.read.icon"));
/*  76 */     getRightBannerLabel().setIcon((Icon)localObject);
/*     */ 
/*  79 */     localObject = getQuestionIcon();
/*  80 */     getTopImageLabel().setIcon((Icon)localObject);
/*     */ 
/*  83 */     JPanel localJPanel = getContentArea();
/*  84 */     localJPanel.setLayout(new GridBagLayout());
/*     */ 
/*  86 */     String str1 = this.m_resources.getString("wizard.meter.choose.link");
/*  87 */     GridBagConstraints localGridBagConstraints = new GridBagConstraints();
/*  88 */     localGridBagConstraints.gridx = 0;
/*  89 */     localGridBagConstraints.gridy = 0;
/*  90 */     localGridBagConstraints.gridheight = 1;
/*  91 */     localGridBagConstraints.gridwidth = 6;
/*  92 */     localGridBagConstraints.anchor = 17;
/*  93 */     localJPanel.add(new JLabel(str1), localGridBagConstraints);
/*     */ 
/*  96 */     this.m_buttonGroup = new DefaultRadioButtonGroup(this.m_resources, localJPanel);
/*  97 */     JRadioButton localJRadioButton1 = null;
/*  98 */     if (getLocale().equals(Locale.US)) {
/*  99 */       localJRadioButton1 = this.m_buttonGroup.add(getWizardSelections().m_selectionDeviceAscensiaContourXTLink, "wizard.selections.SELECTION_DEVICE_XTLINKMETER", 1, 2);
/*     */ 
/* 102 */       localJRadioButton1.addActionListener(this.m_radioButtonListener);
/* 103 */       if (localFont != null) {
/* 104 */         localJRadioButton1.setFont(localFont);
/*     */       }
/*     */     }
/*     */ 
/* 108 */     JRadioButton localJRadioButton2 = this.m_buttonGroup.add(getWizardSelections().m_selectionDeviceAscensiaContourLink, "wizard.selections.SELECTION_DEVICE_ASCENSIA_CONTOUR_METER", 1, 1);
/*     */ 
/* 111 */     localJRadioButton2.addActionListener(this.m_radioButtonListener);
/* 112 */     if (localFont != null) {
/* 113 */       localJRadioButton2.setFont(localFont);
/*     */     }
/*     */ 
/* 116 */     JRadioButton localJRadioButton3 = this.m_buttonGroup.add(getWizardSelections().m_selectionDeviceLifeScanUltraLink, "wizard.selections.SELECTION_DEVICE_LIFESCAN_ULTRA_METER", 3, 1);
/*     */ 
/* 119 */     localJRadioButton3.addActionListener(this.m_radioButtonListener);
/* 120 */     if (localFont != null) {
/* 121 */       localJRadioButton3.setFont(localFont);
/*     */     }
/* 123 */     JRadioButton localJRadioButton4 = this.m_buttonGroup.add("wizard.selections.SELECTION_DEVICE_MMLINKMETER", 5, 1);
/*     */ 
/* 125 */     localJRadioButton4.addActionListener(this.m_radioButtonListener);
/* 126 */     if (localFont != null) {
/* 127 */       localJRadioButton4.setFont(localFont);
/*     */     }
/*     */ 
/* 133 */     if (localJRadioButton1 != null) {
/* 134 */       localGridBagConstraints = new GridBagConstraints();
/* 135 */       localGridBagConstraints.gridx = 0;
/* 136 */       localGridBagConstraints.gridy = 2;
/* 137 */       localGridBagConstraints.insets = new Insets(10, 0, 0, 0);
/* 138 */       localGridBagConstraints.anchor = 13;
/* 139 */       localJPanel.add(createImageButton("wizard.bayercontourusbmeter.pic", localJRadioButton1), localGridBagConstraints);
/*     */     }
/*     */ 
/* 143 */     localGridBagConstraints = new GridBagConstraints();
/* 144 */     localGridBagConstraints.gridx = 0;
/* 145 */     localGridBagConstraints.gridy = 1;
/* 146 */     localGridBagConstraints.insets = new Insets(10, 0, 0, 0);
/* 147 */     localGridBagConstraints.anchor = 13;
/* 148 */     localJPanel.add(createImageButton("wizard.bayercontourmeter.pic", localJRadioButton2), localGridBagConstraints);
/*     */ 
/* 151 */     localGridBagConstraints = new GridBagConstraints();
/* 152 */     localGridBagConstraints.gridx = 2;
/* 153 */     localGridBagConstraints.gridy = 1;
/* 154 */     localGridBagConstraints.insets = new Insets(10, 12, 0, 0);
/* 155 */     localGridBagConstraints.anchor = 13;
/* 156 */     localJPanel.add(createImageButton("wizard.lifescanultra.pic", localJRadioButton3), localGridBagConstraints);
/*     */ 
/* 158 */     localGridBagConstraints = new GridBagConstraints();
/* 159 */     localGridBagConstraints.gridx = 4;
/* 160 */     localGridBagConstraints.gridy = 1;
/* 161 */     localGridBagConstraints.insets = new Insets(10, 12, 0, 0);
/* 162 */     localGridBagConstraints.anchor = 13;
/* 163 */     localJPanel.add(createImageButton("wizard.mmlinkmeter.pic", localJRadioButton4), localGridBagConstraints);
/*     */ 
/* 166 */     String str2 = "wizard.selections.SELECTION_DEVICE_ASCENSIA_CONTOUR_METER";
/* 167 */     if (Locale.US.equals(getLocale()))
/*     */     {
/* 169 */       str2 = "wizard.selections.SELECTION_DEVICE_XTLINKMETER";
/*     */     }
/* 171 */     this.m_buttonGroup.selectButton(getWizardSelections().getMeterDevice(str2), str2);
/*     */   }
/*     */ 
/*     */   protected void stepShown()
/*     */   {
/* 180 */     super.stepShown();
/* 181 */     updateButtonStates();
/*     */   }
/*     */ 
/*     */   protected void nextRequest()
/*     */   {
/* 188 */     rememberUserSelections();
/* 189 */     getWizard().showNextStep(getNextMiniMedClass());
/*     */   }
/*     */ 
/*     */   protected void rememberUserSelections()
/*     */   {
/* 197 */     getWizardSelections().setMeterDevice(this.m_buttonGroup.getSelectedButton());
/*     */   }
/*     */ 
/*     */   protected void updateButtonStates()
/*     */   {
/* 204 */     rememberUserSelections();
/* 205 */     getFinishButton().setEnabled(getWizard().canFinish());
/*     */   }
/*     */ 
/*     */   private Class getNextMiniMedClass()
/*     */   {
/*     */     Object localObject;
/* 215 */     if (getWizardSelections().getMeterDevice().equals("wizard.selections.SELECTION_DEVICE_MMLINKMETER"))
/*     */     {
/* 217 */       if ((getWizard().getConfig().isWindowsNT()) || (OSInfo.isMac()))
/*     */       {
/* 219 */         getWizardSelections().setConnectionType("wizard.selections.SELECTION_CONN_TYPE_SERIAL");
/*     */ 
/* 221 */         localObject = minimed.ddms.applet.dtw.wizard.steps.mmmeter.MeterSelectSerialPortStep.class;
/*     */       } else {
/* 223 */         localObject = SelectConnectionTypeStep.class;
/*     */       }
/*     */ 
/*     */     }
/* 227 */     else if (getWizardSelections().getMeterDevice().equals("wizard.selections.SELECTION_DEVICE_ASCENSIA_CONTOUR_METER"))
/*     */     {
/* 229 */       localObject = minimed.ddms.applet.dtw.wizard.steps.bayermeter.MeterSelectSerialPortStep.class;
/*     */     }
/* 232 */     else if (getWizardSelections().getMeterDevice().equals("wizard.selections.SELECTION_DEVICE_LIFESCAN_ULTRA_METER"))
/*     */     {
/* 234 */       localObject = minimed.ddms.applet.dtw.wizard.steps.lifescanmeter.MeterSelectSerialPortStep.class;
/*     */     }
/* 237 */     else if (getWizardSelections().getMeterDevice().equals("wizard.selections.SELECTION_DEVICE_XTLINKMETER"))
/*     */     {
/* 239 */       if (getWizard().isBayerUSBDriverInstallNeeded()) {
/* 240 */         logInfo("Install Bayer USB Driver is neeeded...");
/* 241 */         localObject = USBDriverInstallAuthorizationStep.class;
/*     */       } else {
/* 243 */         localObject = VerifyConnectionsStep.class;
/*     */       }
/*     */     } else {
/* 246 */       localObject = null;
/* 247 */       Contract.unreachable();
/*     */     }
/*     */ 
/* 250 */     return (Class)localObject;
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.steps.SelectMeterMiniMedStep
 * JD-Core Version:    0.6.0
 */