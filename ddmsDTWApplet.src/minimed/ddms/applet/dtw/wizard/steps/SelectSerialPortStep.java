/*     */ package minimed.ddms.applet.dtw.wizard.steps;
/*     */ 
/*     */ import Serialio.SerialPortLocal;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.Insets;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Iterator;
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
/*     */ import minimed.util.OSInfo;
/*     */ 
/*     */ public abstract class SelectSerialPortStep extends WizardStep
/*     */   implements ActionListener
/*     */ {
/*     */   private static final int INSET_RADIOBUTTONS_LEFT = 62;
/*  42 */   private static final String[] FALLBACK_PORT_LIST = { "COM1", "COM2", "COM3", "COM4", "COM5", "COM6", "COM7", "COM8", "COM9", "usbserial", "usbserial1", "usbserial2", "usbserial3" };
/*     */   private final JComboBox m_comboBoxSerialPorts;
/*     */   private final JRadioButton m_radioButtonAutoDetect;
/*     */   private final JRadioButton m_radioButtonSelectPort;
/*     */ 
/*     */   protected SelectSerialPortStep(Wizard paramWizard, String paramString, Class paramClass)
/*     */   {
/*  63 */     super(paramWizard, paramClass);
/*     */ 
/*  66 */     getLeftBannerLabel().setText(this.m_resources.getString("wizard.serial.portselect"));
/*  67 */     Object localObject = new ImageIcon(getImage(paramString));
/*  68 */     getRightBannerLabel().setIcon((Icon)localObject);
/*     */ 
/*  71 */     localObject = getQuestionIcon();
/*  72 */     getTopImageLabel().setIcon((Icon)localObject);
/*     */ 
/*  75 */     JPanel localJPanel1 = getContentArea();
/*  76 */     localJPanel1.setLayout(new GridBagLayout());
/*     */ 
/*  79 */     String str = this.m_resources.getString("wizard.serial.portselect2");
/*  80 */     localJPanel1.add(new JLabel(str));
/*     */ 
/*  85 */     this.m_radioButtonAutoDetect = new JRadioButton();
/*  86 */     setCustomColors(this.m_radioButtonAutoDetect);
/*  87 */     this.m_radioButtonAutoDetect.setText(getWizardSelections().m_selectionSerialPortAutoDetect);
/*  88 */     this.m_radioButtonAutoDetect.setName("wizard.selections.SELECTION_SERIAL_PORT_AUTO_DETECT");
/*     */ 
/*  91 */     ButtonGroup localButtonGroup = new ButtonGroup();
/*  92 */     localButtonGroup.add(this.m_radioButtonAutoDetect);
/*  93 */     GridBagConstraints localGridBagConstraints = new GridBagConstraints();
/*  94 */     localGridBagConstraints.gridx = 0;
/*  95 */     localGridBagConstraints.gridy = 2;
/*  96 */     localGridBagConstraints.insets = new Insets(0, 62, 0, 0);
/*  97 */     localGridBagConstraints.anchor = 17;
/*  98 */     localJPanel1.add(this.m_radioButtonAutoDetect, localGridBagConstraints);
/*     */ 
/* 102 */     JPanel localJPanel2 = new JPanel();
/* 103 */     setCustomColors(localJPanel2);
/* 104 */     localJPanel2.setLayout(new GridBagLayout());
/*     */ 
/* 106 */     this.m_radioButtonSelectPort = new JRadioButton();
/* 107 */     setCustomColors(this.m_radioButtonSelectPort);
/* 108 */     this.m_radioButtonSelectPort.setText(getWizardSelections().m_selectionSerialPortSelectPort);
/*     */ 
/* 110 */     this.m_radioButtonSelectPort.setName("wizard.selections.SELECTION_SERIAL_PORT_SELECT_PORT");
/*     */ 
/* 112 */     localButtonGroup.add(this.m_radioButtonSelectPort);
/* 113 */     localGridBagConstraints = new GridBagConstraints();
/* 114 */     localGridBagConstraints.gridx = 0;
/* 115 */     localGridBagConstraints.gridy = 3;
/* 116 */     localGridBagConstraints.anchor = 17;
/* 117 */     localJPanel2.add(this.m_radioButtonSelectPort, localGridBagConstraints);
/*     */ 
/* 119 */     this.m_comboBoxSerialPorts = new JComboBox();
/* 120 */     this.m_comboBoxSerialPorts.setEditable(false);
/* 121 */     this.m_comboBoxSerialPorts.setEnabled(false);
/* 122 */     localGridBagConstraints = new GridBagConstraints();
/* 123 */     localGridBagConstraints.gridx = 1;
/* 124 */     localGridBagConstraints.gridy = 3;
/* 125 */     localGridBagConstraints.insets = new Insets(0, 5, 0, 0);
/* 126 */     localJPanel2.add(this.m_comboBoxSerialPorts, localGridBagConstraints);
/*     */ 
/* 128 */     localGridBagConstraints = new GridBagConstraints();
/* 129 */     localGridBagConstraints.gridx = 0;
/* 130 */     localGridBagConstraints.gridy = 3;
/* 131 */     localGridBagConstraints.anchor = 17;
/* 132 */     localGridBagConstraints.insets = new Insets(0, 62, 0, 0);
/* 133 */     localJPanel1.add(localJPanel2, localGridBagConstraints);
/*     */ 
/* 137 */     this.m_radioButtonAutoDetect.addActionListener(this);
/* 138 */     this.m_radioButtonSelectPort.addActionListener(this);
/*     */   }
/*     */ 
/*     */   public final void actionPerformed(ActionEvent paramActionEvent)
/*     */   {
/* 153 */     this.m_comboBoxSerialPorts.setEnabled(paramActionEvent.getSource().equals(this.m_radioButtonSelectPort));
/*     */   }
/*     */ 
/*     */   protected final void stepShown()
/*     */   {
/* 161 */     super.stepShown();
/*     */ 
/* 164 */     String[] arrayOfString = new String[0];
/* 165 */     int i = 0;
/* 166 */     ArrayList localArrayList = null;
/*     */     try {
/* 168 */       localArrayList = new ArrayList(Arrays.asList(SerialPortLocal.getPortList()));
/*     */     }
/*     */     catch (IOException localIOException)
/*     */     {
/* 173 */       if (localIOException.getMessage().startsWith("Driver Installation Error"))
/*     */       {
/* 175 */         str2 = this.m_resources.getString("error.MSG_NATIVE_LIBRARY_SANITY_CHECK_FAILED");
/*     */ 
/* 178 */         getWizard().setFailureReason(str2);
/*     */ 
/* 180 */         getWizard().showNextStep(InitializationFailStep.class);
/*     */       } else {
/* 182 */         localArrayList = new ArrayList(Arrays.asList(FALLBACK_PORT_LIST));
/* 183 */         logWarning("stepShown: SerialPortLocal.getPortList() failed; e=" + localIOException + "; using default port list: " + localArrayList);
/*     */       }
/*     */     }
/*     */ 
/* 187 */     if (localArrayList != null) {
/* 188 */       filterPorts(localArrayList);
/*     */     }
/*     */ 
/* 192 */     if ((localArrayList == null) || (localArrayList.size() == 0)) {
/* 193 */       i = 1;
/* 194 */       localArrayList = new ArrayList(Arrays.asList(new String[] { this.m_resources.getString("wizard.selections.NO_PORT_FOUND") }));
/*     */     }
/*     */ 
/* 198 */     arrayOfString = (String[])localArrayList.toArray(new String[0]);
/* 199 */     Arrays.sort(arrayOfString);
/* 200 */     this.m_comboBoxSerialPorts.setModel(new DefaultComboBoxModel(arrayOfString));
/*     */ 
/* 203 */     String str1 = getWizardSelections().getSerialPort();
/*     */ 
/* 206 */     this.m_radioButtonAutoDetect.setSelected(str1.equals("wizard.selections.SELECTION_SERIAL_PORT_AUTO_DETECT"));
/*     */ 
/* 208 */     this.m_radioButtonSelectPort.setSelected(str1.equals("wizard.selections.SELECTION_SERIAL_PORT_SELECT_PORT"));
/*     */ 
/* 212 */     String str2 = getWizardSelections().getSerialPortName();
/*     */ 
/* 214 */     this.m_comboBoxSerialPorts.setSelectedItem(str2);
/*     */ 
/* 219 */     this.m_comboBoxSerialPorts.setEnabled(this.m_radioButtonSelectPort.isSelected());
/*     */ 
/* 222 */     if (i != 0) {
/* 223 */       this.m_radioButtonSelectPort.setEnabled(false);
/* 224 */       this.m_comboBoxSerialPorts.setEnabled(false);
/* 225 */       this.m_radioButtonAutoDetect.setSelected(true);
/*     */     } else {
/* 227 */       this.m_radioButtonSelectPort.setEnabled(true);
/*     */     }
/*     */   }
/*     */ 
/*     */   protected void rememberUserSelections()
/*     */   {
/* 236 */     String str = null;
/*     */ 
/* 239 */     if (this.m_radioButtonAutoDetect.isSelected()) {
/* 240 */       str = this.m_radioButtonAutoDetect.getName();
/*     */     }
/* 242 */     else if (this.m_radioButtonSelectPort.isSelected())
/* 243 */       str = this.m_radioButtonSelectPort.getName();
/*     */     else {
/* 245 */       Contract.unreachable();
/*     */     }
/*     */ 
/* 249 */     getWizardSelections().setSerialPort(str);
/*     */ 
/* 252 */     str = (String)this.m_comboBoxSerialPorts.getSelectedItem();
/* 253 */     getWizardSelections().setSerialPortName(str);
/*     */   }
/*     */ 
/*     */   private void filterPorts(ArrayList<String> paramArrayList)
/*     */   {
/* 261 */     if (OSInfo.isMac())
/* 262 */       for (int i = 0; i < WizardStep.PORTS_FILTERED_MAC_LIST.length; i++) {
/* 263 */         Iterator localIterator = paramArrayList.iterator();
/* 264 */         while (localIterator.hasNext()) {
/* 265 */           String str = (String)localIterator.next();
/* 266 */           if (str.contains(WizardStep.PORTS_FILTERED_MAC_LIST[i]))
/* 267 */             localIterator.remove();
/*     */         }
/*     */       }
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.steps.SelectSerialPortStep
 * JD-Core Version:    0.6.0
 */