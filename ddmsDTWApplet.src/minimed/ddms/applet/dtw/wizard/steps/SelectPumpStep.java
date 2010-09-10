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
/*     */ import minimed.ddms.applet.dtw.components.DefaultRadioButtonGroup;
/*     */ import minimed.ddms.applet.dtw.wizard.Wizard;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardSelections;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardStep;
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ public class SelectPumpStep extends WizardStep
/*     */ {
/*     */   private final DefaultRadioButtonGroup m_buttonGroup;
/*     */   private final JRadioButton m_buttonMMX12;
/*     */   private final JRadioButton m_buttonMM511;
/*     */   private final JRadioButton m_buttonMM508;
/*  43 */   private final ActionListener m_radiolButtonListener = new ActionListener()
/*     */   {
/*     */     public void actionPerformed(ActionEvent paramActionEvent)
/*     */     {
/*  51 */       SelectPumpStep.this.updateButtonStates();
/*     */     }
/*  43 */   };
/*     */ 
/*     */   public SelectPumpStep(Wizard paramWizard)
/*     */   {
/*  64 */     super(paramWizard, null);
/*     */ 
/*  67 */     getLeftBannerLabel().setText(this.m_resources.getString("wizard.device.select"));
/*  68 */     Object localObject = new ImageIcon(getImage("wizard.read.icon"));
/*  69 */     getRightBannerLabel().setIcon((Icon)localObject);
/*     */ 
/*  72 */     localObject = getQuestionIcon();
/*  73 */     getTopImageLabel().setIcon((Icon)localObject);
/*     */ 
/*  76 */     JPanel localJPanel = getContentArea();
/*  77 */     localJPanel.setLayout(new GridBagLayout());
/*     */ 
/*  79 */     String str = this.m_resources.getString("wizard.pump.choose");
/*  80 */     localJPanel.add(new JLabel(str));
/*     */ 
/*  83 */     this.m_buttonGroup = new DefaultRadioButtonGroup(this.m_resources, localJPanel);
/*  84 */     this.m_buttonGroup.add(formatPumpDevice("wizard.selections.SELECTION_DEVICE_MMPARADIGM2"), "wizard.selections.SELECTION_DEVICE_MMPARADIGM2");
/*     */ 
/*  87 */     this.m_buttonGroup.add("wizard.selections.SELECTION_DEVICE_MM511");
/*     */ 
/*  89 */     this.m_buttonGroup.add("wizard.selections.SELECTION_DEVICE_MM508");
/*     */ 
/*  93 */     this.m_buttonMMX12 = this.m_buttonGroup.getButton("wizard.selections.SELECTION_DEVICE_MMPARADIGM2");
/*     */ 
/*  95 */     this.m_buttonMM511 = this.m_buttonGroup.getButton("wizard.selections.SELECTION_DEVICE_MM511");
/*     */ 
/*  97 */     this.m_buttonMM508 = this.m_buttonGroup.getButton("wizard.selections.SELECTION_DEVICE_MM508");
/*     */ 
/*  99 */     this.m_buttonMMX12.addActionListener(this.m_radiolButtonListener);
/* 100 */     this.m_buttonMM511.addActionListener(this.m_radiolButtonListener);
/* 101 */     this.m_buttonMM508.addActionListener(this.m_radiolButtonListener);
/*     */ 
/* 104 */     this.m_buttonGroup.selectButton(getWizardSelections().getPumpDevice(), "wizard.selections.SELECTION_DEVICE_MMPARADIGM2");
/*     */ 
/* 109 */     GridBagConstraints localGridBagConstraints = new GridBagConstraints();
/* 110 */     localGridBagConstraints.gridx = 0;
/* 111 */     localGridBagConstraints.gridy = 1;
/* 112 */     localGridBagConstraints.anchor = 10;
/* 113 */     localJPanel.add(new JLabel(new ImageIcon(getImage("wizard.mm512.pic"))), localGridBagConstraints);
/*     */ 
/* 116 */     localGridBagConstraints = new GridBagConstraints();
/* 117 */     localGridBagConstraints.gridx = 0;
/* 118 */     localGridBagConstraints.gridy = 2;
/* 119 */     localGridBagConstraints.insets = new Insets(10, 0, 0, 0);
/* 120 */     localGridBagConstraints.anchor = 10;
/* 121 */     localJPanel.add(new JLabel(new ImageIcon(getImage("wizard.mm511.pic"))), localGridBagConstraints);
/*     */ 
/* 124 */     localGridBagConstraints = new GridBagConstraints();
/* 125 */     localGridBagConstraints.gridx = 0;
/* 126 */     localGridBagConstraints.gridy = 3;
/* 127 */     localGridBagConstraints.insets = new Insets(10, 0, 0, 0);
/* 128 */     localGridBagConstraints.anchor = 10;
/* 129 */     localJPanel.add(new JLabel(new ImageIcon(getImage("wizard.mm508.pic"))), localGridBagConstraints);
/*     */   }
/*     */ 
/*     */   protected void nextRequest()
/*     */   {
/* 139 */     rememberUserSelections();
/*     */ 
/* 141 */     Wizard localWizard = getWizard();
/*     */ 
/* 145 */     String str = getWizardSelections().getPumpDevice();
/*     */     Class localClass;
/* 147 */     if ((str.equals("wizard.selections.SELECTION_DEVICE_MMPARADIGM2")) || (str.equals("wizard.selections.SELECTION_DEVICE_MM511")))
/*     */     {
/* 149 */       localClass = minimed.ddms.applet.dtw.wizard.steps.mmparadigm.CheckPumpStatusStep.class;
/*     */     }
/* 151 */     else if (str.equals("wizard.selections.SELECTION_DEVICE_MM508")) {
/* 152 */       localClass = minimed.ddms.applet.dtw.wizard.steps.mm508.CheckPumpStatusStep.class;
/*     */     } else {
/* 154 */       Contract.unreachable();
/* 155 */       localClass = null;
/*     */     }
/*     */ 
/* 159 */     localWizard.showNextStep(localClass);
/*     */   }
/*     */ 
/*     */   protected final void rememberUserSelections()
/*     */   {
/* 167 */     getWizardSelections().setPumpDevice(parsePumpDevice(this.m_buttonGroup.getSelectedButton()));
/*     */   }
/*     */ 
/*     */   protected void updateButtonStates()
/*     */   {
/* 175 */     rememberUserSelections();
/* 176 */     getFinishButton().setEnabled(getWizard().canFinish());
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.steps.SelectPumpStep
 * JD-Core Version:    0.6.0
 */