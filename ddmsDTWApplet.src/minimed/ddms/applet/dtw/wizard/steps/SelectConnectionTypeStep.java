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
/*     */ public abstract class SelectConnectionTypeStep extends WizardStep
/*     */ {
/*     */   private final DefaultRadioButtonGroup m_buttonGroup;
/*     */   private final Class m_nextSerialClass;
/*     */   private final Class m_nextUSBNeedsInstallClass;
/*     */   private final Class m_nextUSBNoInstallNeededClass;
/*  47 */   private final ActionListener m_radioButtonListener = new ActionListener()
/*     */   {
/*     */     public void actionPerformed(ActionEvent paramActionEvent)
/*     */     {
/*  55 */       SelectConnectionTypeStep.this.updateButtonStates();
/*     */     }
/*  47 */   };
/*     */ 
/*     */   protected SelectConnectionTypeStep(Wizard paramWizard, Class paramClass1, Class paramClass2, Class paramClass3)
/*     */   {
/*  75 */     super(paramWizard, null);
/*     */ 
/*  77 */     this.m_nextSerialClass = paramClass1;
/*  78 */     this.m_nextUSBNeedsInstallClass = paramClass2;
/*  79 */     this.m_nextUSBNoInstallNeededClass = paramClass3;
/*     */ 
/*  82 */     getLeftBannerLabel().setText(this.m_resources.getString("wizard.connselect.leftbanner"));
/*  83 */     Object localObject = new ImageIcon(getImage("wizard.read.icon"));
/*  84 */     getRightBannerLabel().setIcon((Icon)localObject);
/*     */ 
/*  87 */     localObject = getQuestionIcon();
/*  88 */     getTopImageLabel().setIcon((Icon)localObject);
/*     */ 
/*  91 */     JPanel localJPanel = getContentArea();
/*  92 */     localJPanel.setLayout(new GridBagLayout());
/*  93 */     String str = this.m_resources.getString("wizard.connselect.label");
/*  94 */     localJPanel.add(new JLabel(str), new GridBagConstraints(0, 0, 2, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 0), 0, 0));
/*     */ 
/*  98 */     this.m_buttonGroup = new DefaultRadioButtonGroup(this.m_resources, localJPanel);
/*  99 */     JRadioButton localJRadioButton1 = this.m_buttonGroup.add("wizard.selections.SELECTION_CONN_TYPE_USB");
/*     */ 
/* 101 */     localJRadioButton1.addActionListener(this.m_radioButtonListener);
/* 102 */     JRadioButton localJRadioButton2 = this.m_buttonGroup.add("wizard.selections.SELECTION_CONN_TYPE_SERIAL");
/*     */ 
/* 104 */     localJRadioButton2.addActionListener(this.m_radioButtonListener);
/*     */ 
/* 107 */     GridBagConstraints localGridBagConstraints = new GridBagConstraints();
/* 108 */     localGridBagConstraints.gridx = 0;
/* 109 */     localGridBagConstraints.gridy = 1;
/* 110 */     localGridBagConstraints.anchor = 10;
/* 111 */     localJPanel.add(createImageButton("wizard.usbcable.pic", localJRadioButton1), localGridBagConstraints);
/*     */ 
/* 115 */     localGridBagConstraints = new GridBagConstraints();
/* 116 */     localGridBagConstraints.gridx = 0;
/* 117 */     localGridBagConstraints.gridy = 2;
/* 118 */     localGridBagConstraints.insets = new Insets(10, 0, 0, 0);
/* 119 */     localGridBagConstraints.anchor = 10;
/* 120 */     localJPanel.add(createImageButton("wizard.serialcable.pic", localJRadioButton2), localGridBagConstraints);
/*     */ 
/* 124 */     this.m_buttonGroup.selectButton(getWizardSelections().getConnectionType(), "wizard.selections.SELECTION_CONN_TYPE_USB");
/*     */   }
/*     */ 
/*     */   protected final void nextRequest()
/*     */   {
/* 134 */     rememberUserSelections();
/*     */ 
/* 136 */     Wizard localWizard = getWizard();
/*     */ 
/* 140 */     String str = getWizardSelections().getConnectionType();
/*     */     Class localClass;
/* 142 */     if (str.equals("wizard.selections.SELECTION_CONN_TYPE_SERIAL")) {
/* 143 */       localClass = this.m_nextSerialClass;
/*     */     }
/* 145 */     else if (str.equals("wizard.selections.SELECTION_CONN_TYPE_USB")) {
/* 146 */       if (localWizard.isBDUSBDriverInstallNeeded()) {
/* 147 */         logInfo("Install BD USB Driver is neeeded...");
/* 148 */         localClass = this.m_nextUSBNeedsInstallClass;
/*     */       } else {
/* 150 */         localClass = this.m_nextUSBNoInstallNeededClass;
/*     */       }
/*     */     } else {
/* 153 */       Contract.unreachable();
/* 154 */       localClass = null;
/*     */     }
/*     */ 
/* 158 */     localWizard.showNextStep(localClass);
/*     */   }
/*     */ 
/*     */   protected void rememberUserSelections()
/*     */   {
/* 166 */     getWizardSelections().setConnectionType(this.m_buttonGroup.getSelectedButton());
/*     */   }
/*     */ 
/*     */   protected void updateButtonStates()
/*     */   {
/* 173 */     rememberUserSelections();
/* 174 */     getFinishButton().setEnabled(getWizard().canFinish());
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.steps.SelectConnectionTypeStep
 * JD-Core Version:    0.6.0
 */