/*     */ package minimed.ddms.applet.dtw.wizard.steps;
/*     */ 
/*     */ import Serialio.SerialPortLocal;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.Insets;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.io.IOException;
/*     */ import java.util.Arrays;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.ButtonGroup;
/*     */ import javax.swing.DefaultComboBoxModel;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JRadioButton;
/*     */ import minimed.ddms.applet.dtw.wizard.Wizard;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardSelections;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardStep;
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ public abstract class SelectSerialPortStep extends WizardStep
/*     */   implements ActionListener
/*     */ {
/*     */   private static final int INSET_RADIOBUTTONS_LEFT = 62;
/*     */   private final JComboBox m_comboBoxSerialPorts;
/*     */   private final JRadioButton m_radioButtonAutoDetect;
/*     */   private final JRadioButton m_radioButtonSelectPort;
/*     */ 
/*     */   protected SelectSerialPortStep(Wizard paramWizard, String paramString, Class paramClass)
/*     */   {
/*  56 */     super(paramWizard, paramClass);
/*     */ 
/*  59 */     getLeftBannerLabel().setText(this.m_resources.getString("wizard.serial.portselect"));
/*  60 */     Object localObject = new ImageIcon(getImage(paramString));
/*  61 */     getRightBannerLabel().setIcon((Icon)localObject);
/*     */ 
/*  64 */     localObject = getQuestionIcon();
/*  65 */     getTopImageLabel().setIcon((Icon)localObject);
/*     */ 
/*  68 */     JPanel localJPanel1 = getContentArea();
/*  69 */     localJPanel1.setLayout(new GridBagLayout());
/*     */ 
/*  72 */     String str = this.m_resources.getString("wizard.serial.portselect2");
/*  73 */     localJPanel1.add(new JLabel(str));
/*     */ 
/*  78 */     this.m_radioButtonAutoDetect = new JRadioButton();
/*  79 */     setCustomColors(this.m_radioButtonAutoDetect);
/*  80 */     this.m_radioButtonAutoDetect.setText(getWizardSelections().m_selectionSerialPortAutoDetect);
/*  81 */     this.m_radioButtonAutoDetect.setName("wizard.selections.SELECTION_SERIAL_PORT_AUTO_DETECT");
/*     */ 
/*  84 */     ButtonGroup localButtonGroup = new ButtonGroup();
/*  85 */     localButtonGroup.add(this.m_radioButtonAutoDetect);
/*  86 */     GridBagConstraints localGridBagConstraints = new GridBagConstraints();
/*  87 */     localGridBagConstraints.gridx = 0;
/*  88 */     localGridBagConstraints.gridy = 2;
/*  89 */     localGridBagConstraints.insets = new Insets(0, 62, 0, 0);
/*  90 */     localGridBagConstraints.anchor = 17;
/*  91 */     localJPanel1.add(this.m_radioButtonAutoDetect, localGridBagConstraints);
/*     */ 
/*  95 */     JPanel localJPanel2 = new JPanel();
/*  96 */     setCustomColors(localJPanel2);
/*  97 */     localJPanel2.setLayout(new GridBagLayout());
/*     */ 
/*  99 */     this.m_radioButtonSelectPort = new JRadioButton();
/* 100 */     setCustomColors(this.m_radioButtonSelectPort);
/* 101 */     this.m_radioButtonSelectPort.setText(getWizardSelections().m_selectionSerialPortSelectPort);
/*     */ 
/* 103 */     this.m_radioButtonSelectPort.setName("wizard.selections.SELECTION_SERIAL_PORT_SELECT_PORT");
/*     */ 
/* 105 */     localButtonGroup.add(this.m_radioButtonSelectPort);
/* 106 */     localGridBagConstraints = new GridBagConstraints();
/* 107 */     localGridBagConstraints.gridx = 0;
/* 108 */     localGridBagConstraints.gridy = 3;
/* 109 */     localGridBagConstraints.anchor = 17;
/* 110 */     localJPanel2.add(this.m_radioButtonSelectPort, localGridBagConstraints);
/*     */ 
/* 112 */     this.m_comboBoxSerialPorts = new JComboBox();
/* 113 */     this.m_comboBoxSerialPorts.setEditable(false);
/* 114 */     this.m_comboBoxSerialPorts.setEnabled(false);
/* 115 */     localGridBagConstraints = new GridBagConstraints();
/* 116 */     localGridBagConstraints.gridx = 1;
/* 117 */     localGridBagConstraints.gridy = 3;
/* 118 */     localGridBagConstraints.insets = new Insets(0, 5, 0, 0);
/* 119 */     localJPanel2.add(this.m_comboBoxSerialPorts, localGridBagConstraints);
/*     */ 
/* 121 */     localGridBagConstraints = new GridBagConstraints();
/* 122 */     localGridBagConstraints.gridx = 0;
/* 123 */     localGridBagConstraints.gridy = 3;
/* 124 */     localGridBagConstraints.anchor = 17;
/* 125 */     localGridBagConstraints.insets = new Insets(0, 62, 0, 0);
/* 126 */     localJPanel1.add(localJPanel2, localGridBagConstraints);
/*     */ 
/* 130 */     this.m_radioButtonAutoDetect.addActionListener(this);
/* 131 */     this.m_radioButtonSelectPort.addActionListener(this);
/*     */   }
/*     */ 
/*     */   public final void actionPerformed(ActionEvent paramActionEvent)
/*     */   {
/* 146 */     this.m_comboBoxSerialPorts.setEnabled(paramActionEvent.getSource().equals(this.m_radioButtonSelectPort));
/*     */   }
/*     */ 
/*     */   protected final void stepShown()
/*     */   {
/* 154 */     super.stepShown();
/*     */     String[] arrayOfString;
/*     */     try {
/* 159 */       arrayOfString = SerialPortLocal.getPortList();
/*     */     } catch (IOException localIOException) {
/* 161 */       arrayOfString = new String[] { "COM1", "COM2", "COM3", "COM4" };
/* 162 */       logWarning("stepShown: SerialPortLocal.getPortList() failed; e=" + localIOException + "; using default port list: " + arrayOfString);
/*     */     }
/*     */ 
/* 166 */     Arrays.sort(arrayOfString);
/* 167 */     this.m_comboBoxSerialPorts.setModel(new DefaultComboBoxModel(arrayOfString));
/*     */ 
/* 170 */     String str1 = getWizardSelections().getSerialPort();
/*     */ 
/* 173 */     this.m_radioButtonAutoDetect.setSelected(str1.equals("wizard.selections.SELECTION_SERIAL_PORT_AUTO_DETECT"));
/*     */ 
/* 175 */     this.m_radioButtonSelectPort.setSelected(str1.equals("wizard.selections.SELECTION_SERIAL_PORT_SELECT_PORT"));
/*     */ 
/* 179 */     String str2 = getWizardSelections().getSerialPortName();
/*     */ 
/* 181 */     this.m_comboBoxSerialPorts.setSelectedItem(str2);
/*     */ 
/* 186 */     this.m_comboBoxSerialPorts.setEnabled(this.m_radioButtonSelectPort.isSelected());
/*     */   }
/*     */ 
/*     */   protected void rememberUserSelections()
/*     */   {
/* 193 */     String str = null;
/*     */ 
/* 196 */     if (this.m_radioButtonAutoDetect.isSelected()) {
/* 197 */       str = this.m_radioButtonAutoDetect.getName();
/*     */     }
/* 199 */     else if (this.m_radioButtonSelectPort.isSelected())
/* 200 */       str = this.m_radioButtonSelectPort.getName();
/*     */     else {
/* 202 */       Contract.unreachable();
/*     */     }
/*     */ 
/* 206 */     getWizardSelections().setSerialPort(str);
/*     */ 
/* 209 */     str = (String)this.m_comboBoxSerialPorts.getSelectedItem();
/* 210 */     getWizardSelections().setSerialPortName(str);
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.steps.SelectSerialPortStep
 * JD-Core Version:    0.6.0
 */