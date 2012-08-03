/*     */ package minimed.ddms.applet.dtw.wizard;
/*     */ 
/*     */ import java.awt.CardLayout;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.Frame;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.HeadlessException;
/*     */ import java.awt.Insets;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.ItemEvent;
/*     */ import java.awt.event.ItemListener;
/*     */ import java.awt.event.MouseAdapter;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseMotionAdapter;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.Locale;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.AbstractAction;
/*     */ import javax.swing.Action;
/*     */ import javax.swing.ActionMap;
/*     */ import javax.swing.DefaultComboBoxModel;
/*     */ import javax.swing.InputMap;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JCheckBox;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JFileChooser;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.KeyStroke;
/*     */ import javax.swing.Timer;
/*     */ import javax.swing.filechooser.FileFilter;
/*     */ import minimed.ddms.A.P;
/*     */ import minimed.ddms.A.Z;
/*     */ import minimed.ddms.applet.dtw.CaptureResult;
/*     */ import minimed.ddms.applet.dtw.DTWApplet;
/*     */ import minimed.ddms.applet.dtw.DiskHelper;
/*     */ import minimed.ddms.applet.dtw.LoadFailedException;
/*     */ import minimed.ddms.applet.dtw.LogWriter;
/*     */ import minimed.ddms.applet.dtw.wizard.steps.MeterOperationFailStep;
/*     */ import minimed.ddms.applet.dtw.wizard.steps.TransferDataCheckStep;
/*     */ import minimed.ddms.applet.dtw.wizard.steps.TransferDataFailStep;
/*     */ import minimed.ddms.applet.dtw.wizard.steps.TransferDataStep;
/*     */ import minimed.ddms.applet.dtw.wizard.steps.mmparadigm.PumpOperationFailStep;
/*     */ 
/*     */ class ControlCodeHotCorner
/*     */ {
/*     */   private static final long TWENTY_MINUTES_MILLIS = 72000000L;
/*     */   private MouseEvent pressEvent;
/*     */   private MouseEvent moveEvent;
/*     */   private Timer timer;
/*     */   private WizardStep step;
/*  59 */   JTextField messageLevelText = new JTextField();
/*  60 */   JButton okButton = new JButton("OK");
/*  61 */   JCheckBox logToConsoleCB = new JCheckBox("Log to Console");
/*  62 */   final DefaultComboBoxModel nextScreenModel = new DefaultComboBoxModel(new String[] { "TransferDataStep", "PumpOperationFailStep", "MeterOperationFailStep", "TransferDataFailStep", "TransferDataCheckStep" });
/*     */ 
/*  66 */   Class[] nextScreenClass = { TransferDataStep.class, PumpOperationFailStep.class, MeterOperationFailStep.class, TransferDataFailStep.class, TransferDataCheckStep.class };
/*     */ 
/*  74 */   JPanel transferDataStepPanel = new JPanel();
/*  75 */   JPanel pumpOperationFailStepPanel = new JPanel();
/*  76 */   JPanel meterOperationFailStepPanel = new JPanel();
/*  77 */   JPanel transferDataFailStepPanel = new JPanel();
/*  78 */   JPanel transferDataCheckStepPanel = new JPanel();
/*  79 */   JComboBox nextScreen = new JComboBox(this.nextScreenModel);
/*  80 */   JComboBox pumpErrorCombo = new JComboBox();
/*     */   DefaultComboBoxModel pumpErrorComboModel;
/*  82 */   JComboBox meterErrorCombo = new JComboBox();
/*  83 */   JComboBox transferFailedCombo = new JComboBox();
/*     */   DefaultComboBoxModel meterErrorComboModel;
/*  85 */   JPanel cardPanel = new JPanel();
/*  86 */   CardLayout cardLayout = new CardLayout();
/*  87 */   JTextField snapshotFileText = new JTextField();
/*  88 */   JButton fileChooserButton = new JButton("...");
/*     */ 
/*     */   ControlCodeHotCorner(WizardStep paramWizardStep)
/*     */   {
/*  94 */     this.step = paramWizardStep;
/*  95 */     this.step.addMouseListener(new MouseAdapter()
/*     */     {
/*     */       public void mousePressed(MouseEvent paramMouseEvent)
/*     */       {
/*  99 */         ControlCodeHotCorner.access$002(ControlCodeHotCorner.this, paramMouseEvent);
/* 100 */         1 local1 = new ActionListener() {
/*     */           public void actionPerformed(ActionEvent paramActionEvent) {
/* 102 */             if ((ControlCodeHotCorner.this.pressEvent != null) && (ControlCodeHotCorner.this.moveEvent != null) && (ControlCodeHotCorner.this.pressEvent.getX() == ControlCodeHotCorner.this.moveEvent.getX()) && (ControlCodeHotCorner.this.pressEvent.getY() == ControlCodeHotCorner.this.moveEvent.getY()) && (ControlCodeHotCorner.this.inHotCorner(ControlCodeHotCorner.this.pressEvent)))
/*     */             {
/* 106 */               ControlCodeHotCorner.this.performHotAction(ControlCodeHotCorner.this.pressEvent);
/*     */             }
/*     */           }
/*     */         };
/* 110 */         if (ControlCodeHotCorner.this.timer == null) {
/* 111 */           ControlCodeHotCorner.access$402(ControlCodeHotCorner.this, new Timer(3000, local1));
/* 112 */           ControlCodeHotCorner.this.timer.setRepeats(false);
/*     */         }
/* 114 */         if (!ControlCodeHotCorner.this.timer.isRunning())
/* 115 */           ControlCodeHotCorner.this.timer.start();
/*     */       }
/*     */ 
/*     */       public void mouseReleased(MouseEvent paramMouseEvent)
/*     */       {
/* 121 */         ControlCodeHotCorner.access$002(ControlCodeHotCorner.this, null);
/* 122 */         if (ControlCodeHotCorner.this.timer != null)
/* 123 */           ControlCodeHotCorner.this.timer.stop();
/*     */       }
/*     */     });
/* 128 */     this.step.addMouseMotionListener(new MouseMotionAdapter()
/*     */     {
/*     */       public void mouseMoved(MouseEvent paramMouseEvent)
/*     */       {
/* 132 */         ControlCodeHotCorner.access$102(ControlCodeHotCorner.this, paramMouseEvent);
/* 133 */         if (ControlCodeHotCorner.this.timer != null)
/* 134 */           ControlCodeHotCorner.this.timer.stop();
/*     */       }
/*     */ 
/*     */       public void mouseDragged(MouseEvent paramMouseEvent)
/*     */       {
/* 140 */         ControlCodeHotCorner.access$102(ControlCodeHotCorner.this, paramMouseEvent);
/* 141 */         if (ControlCodeHotCorner.this.timer != null)
/* 142 */           ControlCodeHotCorner.this.timer.stop();
/*     */       }
/*     */     });
/* 148 */     this.step.getContentArea().getInputMap().put(KeyStroke.getKeyStroke("control M"), "hot");
/* 149 */     3 local3 = new AbstractAction("hot") {
/*     */       public void actionPerformed(ActionEvent paramActionEvent) {
/* 151 */         ControlCodeHotCorner.this.performHotAction(null);
/*     */       }
/*     */     };
/* 154 */     this.step.getContentArea().getActionMap().put(local3.getValue("Name"), local3);
/* 155 */     this.step.getContentArea().requestFocusInWindow();
/*     */   }
/*     */ 
/*     */   static Frame getFrameForComponent(Component paramComponent)
/*     */     throws HeadlessException
/*     */   {
/* 166 */     if (paramComponent == null)
/* 167 */       return null;
/* 168 */     if ((paramComponent instanceof Frame))
/* 169 */       return (Frame)paramComponent;
/* 170 */     return getFrameForComponent(paramComponent.getParent());
/*     */   }
/*     */ 
/*     */   protected void loadSnapshotFromFile()
/*     */     throws LoadFailedException
/*     */   {
/* 179 */     WizardConfig localWizardConfig = this.step.getWizard().getConfig();
/* 180 */     LogWriter localLogWriter1 = localWizardConfig.getLogWriter();
/* 181 */     File localFile = new File(this.snapshotFileText.getText());
/* 182 */     DiskHelper localDiskHelper = new DiskHelper();
/* 183 */     LogWriter localLogWriter2 = localWizardConfig.getLogWriter();
/*     */ 
/* 185 */     localLogWriter2.logInfo("Load from " + localFile.getParent() + File.separator + localFile.getName());
/* 186 */     byte[] arrayOfByte = localDiskHelper.loadFile(localFile.getParent(), localFile.getName(), localLogWriter1);
/* 187 */     localLogWriter1.logInfo("Snapshot file loaded of size " + arrayOfByte.length);
/* 188 */     CaptureResult localCaptureResult = this.step.getWizard().getCaptureResult();
/* 189 */     localCaptureResult.setSnapshotData(arrayOfByte);
/* 190 */     long l = System.currentTimeMillis();
/* 191 */     localCaptureResult.setClientTimeAtDeviceRead(l);
/* 192 */     localCaptureResult.setServerTimeAtDeviceRead(l);
/* 193 */     this.step.getWizard().showNextStep(TransferDataStep.class);
/*     */   }
/*     */ 
/*     */   private void performHotAction(MouseEvent paramMouseEvent)
/*     */   {
/* 202 */     this.cardPanel.setLayout(this.cardLayout);
/* 203 */     this.cardPanel.add(this.transferDataStepPanel, this.nextScreenModel.getElementAt(0));
/* 204 */     this.cardPanel.add(this.pumpOperationFailStepPanel, this.nextScreenModel.getElementAt(1));
/* 205 */     this.cardPanel.add(this.meterOperationFailStepPanel, this.nextScreenModel.getElementAt(2));
/* 206 */     this.cardPanel.add(this.transferDataFailStepPanel, this.nextScreenModel.getElementAt(3));
/* 207 */     this.cardPanel.add(this.transferDataCheckStepPanel, this.nextScreenModel.getElementAt(4));
/* 208 */     this.pumpErrorComboModel = new DefaultComboBoxModel(new String[] { "PROBLEM_WITH_PORT", "PROBLEM_WITH_LINK", "PROBLEM_FINDING_DEVICE", "LOST_DEVICE_COMMUNICATIONS", "EXCESSIVE_RETRIES", "HALT_REQUESTED", "UNKNOWN", "REASON_PUMP_ALARM_ERROR", "REASON_PUMP_ACTIVE_ERROR", "REASON_PUMP_MODEL_UNSUPPORTED", "REASON_WRONG_DEVICE_SELECTION" });
/*     */ 
/* 222 */     this.pumpErrorCombo.setModel(this.pumpErrorComboModel);
/* 223 */     this.pumpOperationFailStepPanel.add(new JLabel("Error:"));
/* 224 */     this.pumpOperationFailStepPanel.add(this.pumpErrorCombo);
/* 225 */     this.meterErrorComboModel = new DefaultComboBoxModel(new String[] { "PROBLEM_WITH_PORT", "PROBLEM_WITH_LINK", "PROBLEM_FINDING_DEVICE", "LOST_DEVICE_COMMUNICATIONS", "EXCESSIVE_RETRIES", "HALT_REQUESTED", "UNKNOWN" });
/*     */ 
/* 233 */     this.transferFailedCombo.setModel(new DefaultComboBoxModel(new String[] { "UPLOAD_IO_RESPONSE", "UPLOAD_IO_SEND", "RESPONSE_TRANSMIT", "RESPONSE_SERVER", "RESPONSE_CLIENT", "RESPONSE" }));
/*     */ 
/* 240 */     this.transferDataFailStepPanel.add(new JLabel("Reason:"));
/* 241 */     this.transferDataFailStepPanel.add(this.transferFailedCombo);
/*     */ 
/* 243 */     this.meterErrorCombo.setModel(this.meterErrorComboModel);
/* 244 */     this.meterOperationFailStepPanel.add(new JLabel("Error:"));
/* 245 */     this.meterOperationFailStepPanel.add(this.meterErrorCombo);
/* 246 */     this.transferDataStepPanel.setLayout(new GridBagLayout());
/* 247 */     this.transferDataStepPanel.add(new JLabel("Snapshot Filename:"), new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(10, 0, 0, 0), 50, 0));
/*     */ 
/* 250 */     this.transferDataStepPanel.add(this.snapshotFileText, new GridBagConstraints(0, 1, 1, 1, 1.0D, 0.0D, 17, 2, new Insets(0, 0, 0, 4), 0, 0));
/*     */ 
/* 253 */     this.transferDataStepPanel.add(this.fileChooserButton, new GridBagConstraints(1, 1, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 4), 0, 0));
/*     */ 
/* 256 */     this.fileChooserButton.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent paramActionEvent) {
/* 258 */         WizardConfig localWizardConfig = ControlCodeHotCorner.this.step.getWizard().getConfig();
/* 259 */         JFileChooser localJFileChooser = ControlCodeHotCorner.this.createFileChooser();
/* 260 */         int i = localJFileChooser.showOpenDialog(localWizardConfig.getApplet().getContentPane());
/* 261 */         if (i == 0)
/*     */           try {
/* 263 */             ControlCodeHotCorner.this.snapshotFileText.setText(localJFileChooser.getSelectedFile().getCanonicalPath());
/*     */           }
/*     */           catch (IOException localIOException)
/*     */           {
/*     */           }
/*     */       }
/*     */     });
/* 272 */     this.nextScreen.addItemListener(new ItemListener() {
/*     */       public void itemStateChanged(ItemEvent paramItemEvent) {
/* 274 */         String str = (String)ControlCodeHotCorner.this.nextScreen.getSelectedItem();
/* 275 */         ControlCodeHotCorner.this.cardLayout.show(ControlCodeHotCorner.this.cardPanel, str);
/*     */       }
/*     */     });
/* 279 */     WizardConfig localWizardConfig = this.step.getWizard().getConfig();
/* 280 */     Frame localFrame = getFrameForComponent(this.step);
/* 281 */     JDialog localJDialog = new JDialog(localFrame, "Overrides", true);
/* 282 */     GridBagLayout localGridBagLayout = new GridBagLayout();
/* 283 */     localJDialog.getContentPane().setLayout(localGridBagLayout);
/* 284 */     localJDialog.getContentPane().add(new JLabel("Parser Message Level"), new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 0), 0, 0));
/*     */ 
/* 286 */     localJDialog.getContentPane().add(this.messageLevelText, new GridBagConstraints(1, 0, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 4, 0, 0), 8, 0));
/*     */ 
/* 288 */     localJDialog.getContentPane().add(this.logToConsoleCB, new GridBagConstraints(0, 1, 2, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 0), 0, 0));
/*     */ 
/* 290 */     localJDialog.getContentPane().add(new JLabel("Next Screen:"), new GridBagConstraints(0, 3, 1, 1, 0.0D, 0.0D, 13, 0, new Insets(0, 0, 0, 0), 0, 0));
/*     */ 
/* 292 */     localJDialog.getContentPane().add(this.nextScreen, new GridBagConstraints(1, 3, 1, 1, 1.0D, 1.0D, 10, 0, new Insets(0, 0, 0, 0), 0, 0));
/*     */ 
/* 294 */     localJDialog.getContentPane().add(this.cardPanel, new GridBagConstraints(0, 4, 2, 1, 1.0D, 1.0D, 10, 0, new Insets(4, 4, 4, 4), 0, 0));
/*     */ 
/* 296 */     localJDialog.getContentPane().add(this.okButton, new GridBagConstraints(0, 5, 2, 1, 0.0D, 0.0D, 10, 0, new Insets(8, 0, 0, 0), 0, 0));
/*     */ 
/* 298 */     this.messageLevelText.setText(String.valueOf(localWizardConfig.getDevicePortReaderMessageLevel()));
/* 299 */     this.okButton.addActionListener(new ActionListener(localWizardConfig, localJDialog) {
/*     */       public void actionPerformed(ActionEvent paramActionEvent) {
/* 301 */         int i = ControlCodeHotCorner.this.nextScreen.getSelectedIndex();
/* 302 */         CaptureResult localCaptureResult = ControlCodeHotCorner.this.step.getWizard().getCaptureResult();
/* 303 */         switch (i)
/*     */         {
/*     */         case 0:
/*     */           try {
/* 307 */             this.val$config.setLoadSnapshotMode(true);
/* 308 */             ControlCodeHotCorner.this.loadSnapshotFromFile();
/*     */           }
/*     */           catch (Exception localException)
/*     */           {
/*     */           }
/*     */ 
/*     */         case 1:
/*     */         case 2:
/* 316 */           int j = ControlCodeHotCorner.this.pumpErrorCombo.getSelectedIndex();
/* 317 */           if (i == 2) {
/* 318 */             j = ControlCodeHotCorner.this.meterErrorCombo.getSelectedIndex();
/*     */           }
/* 320 */           localCaptureResult.setAcquireDataStartPercentComplete(0);
/* 321 */           localCaptureResult.setPercentComplete(50);
/* 322 */           int[] arrayOfInt = { 2, 7, 4, 5, 5, 5, 0, 2, 3, 1000, 2000 };
/*     */ 
/* 334 */           if (j < 7) {
/* 335 */             localCaptureResult.setDeviceException(new Z("BadDeviceValueException"));
/* 336 */             if (j == 5)
/* 337 */               localCaptureResult.setCancelled();
/* 338 */             else if (j == 4) {
/* 339 */               for (int m = 0; m < 5; m++) {
/* 340 */                 localCaptureResult.incrementRetryCount();
/*     */               }
/*     */             }
/* 343 */             localCaptureResult.setPhase(arrayOfInt[j]);
/*     */           }
/*     */           else {
/* 346 */             localCaptureResult.setDeviceException(new P("ConnectToPumpException", arrayOfInt[j], "pump value"));
/*     */           }
/* 348 */           if (i == 1) {
/* 349 */             ControlCodeHotCorner.this.step.getWizard().showNextStep(PumpOperationFailStep.class);
/*     */           }
/*     */           else {
/* 352 */             localCaptureResult.setPhase(arrayOfInt[j]);
/* 353 */             ControlCodeHotCorner.this.step.getWizard().showNextStep(MeterOperationFailStep.class);
/*     */           }
/* 355 */           break;
/*     */         case 3:
/* 360 */           int k = ControlCodeHotCorner.this.transferFailedCombo.getSelectedIndex();
/*     */           String str;
/* 361 */           switch (k) {
/*     */           case 0:
/* 363 */             str = "error.MSG_UPLOAD_IO_RESPONSE";
/* 364 */             break;
/*     */           case 1:
/* 366 */             str = "error.MSG_UPLOAD_IO_SEND";
/* 367 */             break;
/*     */           case 2:
/* 369 */             str = "error.MSG_RESPONSE_TRANSMIT";
/* 370 */             break;
/*     */           case 3:
/* 372 */             str = "error.MSG_RESPONSE_SERVER";
/* 373 */             break;
/*     */           case 4:
/* 375 */             str = "error.MSG_RESPONSE_CLIENT";
/* 376 */             break;
/*     */           default:
/* 378 */             str = "error.MSG_RESPONSE";
/*     */           }
/*     */ 
/* 381 */           ControlCodeHotCorner.this.step.getWizard().setFailureReason(DTWApplet.getResourceBundle().getString(str));
/* 382 */           ControlCodeHotCorner.this.step.getWizard().showNextStep(TransferDataFailStep.class);
/* 383 */           break;
/*     */         case 4:
/* 386 */           localCaptureResult.setClientTimeAtDeviceRead(System.currentTimeMillis() - 72000000L);
/* 387 */           ControlCodeHotCorner.this.step.getWizard().showNextStep(TransferDataCheckStep.class);
/* 388 */           break;
/*     */         }
/*     */ 
/* 392 */         this.val$dialog.dispose();
/*     */       }
/*     */     });
/* 395 */     localJDialog.pack();
/* 396 */     localJDialog.setVisible(true);
/*     */ 
/* 399 */     int i = Integer.parseInt(this.messageLevelText.getText());
/* 400 */     localWizardConfig.setDevicePortReaderMessageLevel(i);
/*     */ 
/* 403 */     LogWriter localLogWriter = localWizardConfig.getLogWriter();
/* 404 */     localLogWriter.setLogToConsole(this.logToConsoleCB.isSelected(), localLogWriter.getConsoleEncryptionKey());
/*     */ 
/* 406 */     localLogWriter.logInfo("Configuration updated to " + localWizardConfig + "...");
/*     */   }
/*     */ 
/*     */   private boolean inHotCorner(MouseEvent paramMouseEvent)
/*     */   {
/* 415 */     int i = 100;
/* 416 */     return (paramMouseEvent.getX() >= this.step.getWidth() - i) && (paramMouseEvent.getX() <= this.step.getWidth()) && (paramMouseEvent.getY() >= 0) && (paramMouseEvent.getY() <= i);
/*     */   }
/*     */ 
/*     */   private JFileChooser createFileChooser()
/*     */   {
/* 425 */     JFileChooser localJFileChooser = new JFileChooser();
/* 426 */     localJFileChooser.setDialogTitle("DTWApplet");
/* 427 */     7 local7 = new FileFilter()
/*     */     {
/*     */       public boolean accept(File paramFile)
/*     */       {
/* 438 */         if (paramFile != null) {
/* 439 */           if (paramFile.isDirectory())
/* 440 */             return true;
/* 441 */           if (paramFile.isFile()) {
/* 442 */             String str1 = paramFile.getName();
/* 443 */             int i = str1.lastIndexOf('.');
/* 444 */             if ((i > 0) && (i < str1.length() - 1)) {
/* 445 */               String str2 = str1.substring(i + 1).toLowerCase(Locale.ENGLISH);
/*     */ 
/* 447 */               if ("snp".equals(str2)) {
/* 448 */                 return true;
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/* 453 */         return false;
/*     */       }
/*     */       public String getDescription() {
/* 456 */         return "snapshot File";
/*     */       }
/*     */     };
/* 460 */     localJFileChooser.setFileFilter(local7);
/* 461 */     return localJFileChooser;
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.ControlCodeHotCorner
 * JD-Core Version:    0.6.0
 */