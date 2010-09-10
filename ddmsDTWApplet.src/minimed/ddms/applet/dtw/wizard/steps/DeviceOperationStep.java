/*      */ package minimed.ddms.applet.dtw.wizard.steps;
/*      */ 
/*      */ import java.awt.BorderLayout;
/*      */ import java.awt.Insets;
/*      */ import java.io.ByteArrayOutputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.util.ResourceBundle;
/*      */ import javax.swing.BorderFactory;
/*      */ import javax.swing.Icon;
/*      */ import javax.swing.ImageIcon;
/*      */ import javax.swing.JButton;
/*      */ import javax.swing.JLabel;
/*      */ import javax.swing.JPanel;
/*      */ import javax.swing.JProgressBar;
/*      */ import javax.swing.SwingUtilities;
/*      */ import javax.swing.border.EmptyBorder;
/*      */ import minimed.ddms.applet.dtw.CaptureResult;
/*      */ import minimed.ddms.applet.dtw.IOHelper;
/*      */ import minimed.ddms.applet.dtw.MessageHelper;
/*      */ import minimed.ddms.applet.dtw.NetHelper;
/*      */ import minimed.ddms.applet.dtw.PropertyWriter;
/*      */ import minimed.ddms.applet.dtw.wizard.Wizard;
/*      */ import minimed.ddms.applet.dtw.wizard.WizardConfig;
/*      */ import minimed.ddms.applet.dtw.wizard.WizardSelections;
/*      */ import minimed.ddms.applet.dtw.wizard.WizardStep;
/*      */ import minimed.ddms.deviceportreader.BadDeviceCommException;
/*      */ import minimed.ddms.deviceportreader.BadDeviceValueException;
/*      */ import minimed.ddms.deviceportreader.ConnectToPumpException;
/*      */ import minimed.ddms.deviceportreader.DeviceListener;
/*      */ import minimed.ddms.deviceportreader.DevicePortReader;
/*      */ import minimed.ddms.deviceportreader.DevicePortReaderFactory;
/*      */ import minimed.ddms.deviceportreader.SerialIOHaltedException;
/*      */ import minimed.util.Contract;
/*      */ 
/*      */ public abstract class DeviceOperationStep extends WizardStep
/*      */   implements DeviceListener
/*      */ {
/*      */   private static final int PERCENT_100 = 100;
/*      */   private static final int SECONDS_PER_MINUTE = 60;
/*      */   private static final int MILLISECONDS_PER_SECOND = 1000;
/*      */   private static final int RETRY_FLASH_DELAY_MS = 100;
/*      */   private static final int INSET_MAIN_LEFT = 5;
/*      */   private static final int INSET_MAIN_RIGHT = 5;
/*      */   private static final int MILLIS_TO_SLEEP = 25;
/*      */   private static final String COMM_PORT_PREFIX = "COM";
/*      */   private final JLabel m_phaseNotificationLabel;
/*      */   private final JLabel m_phaseElaborationLabel;
/*      */   private final JLabel m_progressBarLabel;
/*      */   private final JProgressBar m_progressBar;
/*      */   private final String m_progressBarText;
/*      */   private final Class m_failClass;
/*   91 */   private final CaptureResult m_captureResult = new CaptureResult();
/*      */ 
/*      */   protected DeviceOperationStep(Wizard paramWizard, String paramString1, Class paramClass1, Class paramClass2, String paramString2)
/*      */   {
/*  109 */     super(paramWizard, paramClass1);
/*      */ 
/*  111 */     Contract.preNonNull(paramClass1);
/*  112 */     Contract.preNonNull(paramClass2);
/*  113 */     this.m_failClass = paramClass2;
/*      */ 
/*  116 */     this.m_phaseNotificationLabel = getLeftBannerLabel();
/*  117 */     this.m_phaseNotificationLabel.setText("");
/*  118 */     Object localObject = new ImageIcon(getImage(paramString1));
/*  119 */     getRightBannerLabel().setIcon((Icon)localObject);
/*      */ 
/*  122 */     localObject = getInfoIcon();
/*  123 */     getTopImageLabel().setIcon((Icon)localObject);
/*      */ 
/*  126 */     JPanel localJPanel1 = new JPanel();
/*  127 */     setCustomColors(localJPanel1);
/*  128 */     localJPanel1.setLayout(new BorderLayout());
/*      */ 
/*  131 */     JLabel localJLabel = new JLabel(this.m_resources.getString("wizard.warning.stayHere"));
/*  132 */     localJLabel.setHorizontalAlignment(0);
/*  133 */     localJPanel1.add(localJLabel, "North");
/*      */ 
/*  136 */     this.m_phaseElaborationLabel = new JLabel(" ");
/*  137 */     this.m_phaseElaborationLabel.setHorizontalAlignment(0);
/*  138 */     localJPanel1.add(this.m_phaseElaborationLabel, "Center");
/*      */ 
/*  141 */     JPanel localJPanel2 = new JPanel();
/*  142 */     setCustomColors(localJPanel2);
/*  143 */     localJPanel2.setLayout(new BorderLayout());
/*      */ 
/*  145 */     this.m_progressBarText = this.m_resources.getString(paramString2);
/*  146 */     this.m_progressBarLabel = new JLabel();
/*  147 */     this.m_progressBarLabel.setHorizontalAlignment(0);
/*  148 */     localJPanel2.add(this.m_progressBarLabel, "Center");
/*      */ 
/*  150 */     this.m_progressBar = new JProgressBar();
/*      */ 
/*  152 */     this.m_progressBar.setBackground(paramWizard.getConfig().getBackgroundColor());
/*  153 */     this.m_progressBar.setBorder(BorderFactory.createLineBorder(paramWizard.getConfig().getBorderColor()));
/*      */ 
/*  155 */     this.m_progressBar.setVisible(false);
/*  156 */     localJPanel2.add(this.m_progressBar, "South");
/*      */ 
/*  158 */     localJPanel1.add(localJPanel2, "South");
/*      */ 
/*  160 */     JPanel localJPanel3 = getContentArea();
/*  161 */     localJPanel3.setLayout(new BorderLayout());
/*  162 */     localJPanel3.add(localJPanel1);
/*  163 */     localJPanel3.setBorder(new EmptyBorder(new Insets(0, 5, 0, 5)));
/*      */   }
/*      */ 
/*      */   public final String toString()
/*      */   {
/*  175 */     PropertyWriter localPropertyWriter = new PropertyWriter(super.toString());
/*  176 */     localPropertyWriter.add("captureResult", this.m_captureResult);
/*  177 */     return localPropertyWriter.toString();
/*      */   }
/*      */ 
/*      */   public long getLastHistoryPageNumber(String paramString1, String paramString2)
/*      */   {
/*  211 */     Contract.pre(("515".equals(paramString1)) || ("715".equals(paramString1)) || ("522".equals(paramString1)) || ("522K".equals(paramString1)) || ("722".equals(paramString1)) || ("722K".equals(paramString1)) || ("523".equals(paramString1)) || ("523K".equals(paramString1)) || ("723".equals(paramString1)) || ("723K".equals(paramString1)) || ("553".equals(paramString1)) || ("753".equals(paramString1)) || ("554".equals(paramString1)) || ("754".equals(paramString1)) || ("7100".equals(paramString1)) || ("7100B".equals(paramString1)) || ("7100K".equals(paramString1)) || ("7200".equals(paramString1)));
/*      */ 
/*  229 */     Contract.preString(paramString2);
/*      */ 
/*  232 */     long l = 0L;
/*  233 */     Contract.post(l >= 0L);
/*  234 */     return l;
/*      */   }
/*      */ 
/*      */   public boolean isHaltRequested()
/*      */   {
/*  244 */     return this.m_captureResult.wasCancelled();
/*      */   }
/*      */ 
/*      */   public long getLastGlucoseHistoryPageNumber(String paramString1, String paramString2)
/*      */   {
/*  276 */     Contract.pre(("522".equals(paramString1)) || ("522K".equals(paramString1)) || ("722".equals(paramString1)) || ("722K".equals(paramString1)) || ("523".equals(paramString1)) || ("523K".equals(paramString1)) || ("723".equals(paramString1)) || ("723K".equals(paramString1)) || ("553".equals(paramString1)) || ("753".equals(paramString1)) || ("554".equals(paramString1)) || ("754".equals(paramString1)) || ("7100".equals(paramString1)) || ("7100B".equals(paramString1)) || ("7100K".equals(paramString1)) || ("7200".equals(paramString1)));
/*      */ 
/*  292 */     Contract.preString(paramString2);
/*      */ 
/*  295 */     long l = 0L;
/*  296 */     Contract.post(l >= 0L);
/*  297 */     return l;
/*      */   }
/*      */ 
/*      */   public final void allowDeviceOperation(DevicePortReader paramDevicePortReader)
/*      */     throws ConnectToPumpException
/*      */   {
/*  308 */     WizardConfig localWizardConfig = getWizard().getConfig();
/*  309 */     boolean bool1 = localWizardConfig.getWizardSelections().isDeviceSelectionAPump();
/*  310 */     boolean bool2 = localWizardConfig.getWizardSelections().isDeviceSelectionACGM();
/*      */ 
/*  312 */     Contract.pre((paramDevicePortReader.isDevicePump()) || (paramDevicePortReader.isDeviceMonitor()));
/*      */ 
/*  314 */     if ((isDeviceX12(paramDevicePortReader)) && (localWizardConfig.isUploadX15PumpOnly()))
/*      */     {
/*  316 */       throw new ConnectToPumpException("Unsupported Pump Model", 1000, paramDevicePortReader.getDescription());
/*      */     }
/*      */ 
/*  322 */     if ((isDeviceG3B(paramDevicePortReader)) && (!localWizardConfig.isUploadG3B()))
/*      */     {
/*  324 */       throw new ConnectToPumpException("Unsupported Pump Model", 1000, paramDevicePortReader.getDescription());
/*      */     }
/*      */ 
/*  330 */     if (((!paramDevicePortReader.isDeviceMonitor()) || (!bool2)) && ((!paramDevicePortReader.isDevicePump()) || (!bool1)))
/*      */     {
/*  333 */       throw new ConnectToPumpException("Wrong Device Selection", 2000, paramDevicePortReader.getModelNumber());
/*      */     }
/*      */ 
/*  339 */     if (paramDevicePortReader.isDevicePump())
/*      */     {
/*  341 */       getWizard().enableVerifySuspendOffMessage();
/*      */     }
/*      */   }
/*      */ 
/*      */   public final void deviceUpdateProgress(int paramInt)
/*      */   {
/*  351 */     if (paramInt >= this.m_captureResult.getPercentComplete()) {
/*  352 */       this.m_captureResult.setPercentComplete(paramInt);
/*      */ 
/*  355 */       this.m_progressBar.setValue(paramInt);
/*  356 */       setProgressBarText(this.m_progressBarText);
/*      */     }
/*      */   }
/*      */ 
/*      */   public final void deviceUpdatePhase(int paramInt, String paramString)
/*      */   {
/*  369 */     if (paramInt == 4)
/*      */     {
/*  371 */       this.m_captureResult.setDeviceInitialized();
/*      */ 
/*  374 */       deviceUpdateProgress(this.m_captureResult.getPercentComplete() + 1);
/*      */     }
/*  378 */     else if (paramInt == 5) {
/*  379 */       this.m_captureResult.setAcquireDataStartPercentComplete(this.m_captureResult.getPercentComplete());
/*      */ 
/*  382 */       deviceUpdateProgress(this.m_captureResult.getPercentComplete() + 1);
/*      */     }
/*      */ 
/*  386 */     logInfo("deviceUpdatePhase: PHASE=" + paramInt + "-" + updateDynamicText(paramString) + "; percent complete=" + this.m_captureResult.getPercentComplete() + "%");
/*      */ 
/*  390 */     this.m_captureResult.setPhase(paramInt);
/*  391 */     String str1 = getPhaseElaboration(paramInt);
/*  392 */     String str2 = getPhaseNotification(paramInt);
/*      */ 
/*  395 */     String str3 = updateDynamicText(str2);
/*  396 */     String str4 = updateDynamicText(str1);
/*      */ 
/*  399 */     1 local1 = new Runnable(str4, str3) { private final String val$phaseElaboration;
/*      */       private final String val$phaseNotification;
/*      */ 
/*  404 */       public void run() { DeviceOperationStep.this.m_phaseElaborationLabel.setText("<html><center>" + this.val$phaseElaboration + "</center></html>");
/*      */ 
/*  408 */         DeviceOperationStep.this.m_phaseNotificationLabel.setText("<html>" + this.val$phaseNotification + "</html>");
/*      */ 
/*  414 */         if (DeviceOperationStep.this.m_captureResult.getPhase() == 6)
/*      */         {
/*  416 */           DeviceOperationStep.this.disable(DeviceOperationStep.access$300(DeviceOperationStep.this));
/*      */         }
/*      */       }
/*      */     };
/*  421 */     SwingUtilities.invokeLater(local1);
/*      */   }
/*      */ 
/*      */   public final void deviceUpdateState(int paramInt, String paramString)
/*      */   {
/*  434 */     this.m_captureResult.setState(paramInt);
/*      */ 
/*  437 */     if ((paramInt == 9) || (paramInt == 8))
/*      */     {
/*  439 */       this.m_captureResult.setCancelled();
/*      */     }
/*      */ 
/*  442 */     logInfo("deviceUpdateState: state=" + paramInt + "-" + paramString + "; percent complete=" + this.m_captureResult.getPercentComplete() + "%");
/*      */ 
/*  446 */     if (paramInt == 7) {
/*  447 */       this.m_captureResult.incrementRetryCount();
/*      */ 
/*  450 */       clearProgressBarText();
/*  451 */       sleepMS(100);
/*  452 */       setProgressBarText(this.m_progressBarText);
/*      */     }
/*      */   }
/*      */ 
/*      */   protected final void appletStopped()
/*      */   {
/*  460 */     if (this.m_captureResult.getDevicePortReader() != null) {
/*  461 */       logInfo("Halt the deviceportreader");
/*  462 */       this.m_captureResult.getDevicePortReader().halt();
/*      */     }
/*      */   }
/*      */ 
/*      */   protected final void cancelRequest()
/*      */   {
/*  470 */     logInfo("cancelRequest: Cancel requested...");
/*      */ 
/*  473 */     disable(getCancelButton());
/*      */ 
/*  476 */     if (this.m_captureResult.getDevicePortReader() != null) {
/*  477 */       this.m_captureResult.getDevicePortReader().halt();
/*      */ 
/*  479 */       disableCursor();
/*  480 */       setProgressBarText(this.m_resources.getString("wizard.progressbar.cancel"));
/*      */     }
/*      */   }
/*      */ 
/*      */   protected final void stepShown()
/*      */   {
/*  496 */     getWizardSelections().flush();
/*      */ 
/*  499 */     getBackButton().setEnabled(false);
/*  500 */     getNextButton().setEnabled(false);
/*  501 */     getCancelButton().setEnabled(false);
/*  502 */     getFinishButton().setEnabled(false);
/*      */ 
/*  505 */     this.m_captureResult.clear();
/*      */ 
/*  507 */     startTimer();
/*      */ 
/*  509 */     enableCursor();
/*  510 */     this.m_progressBar.setVisible(true);
/*      */ 
/*  513 */     this.m_phaseNotificationLabel.setText("");
/*  514 */     this.m_phaseElaborationLabel.setText("");
/*      */ 
/*  517 */     getBottomImageLabel().setText("");
/*  518 */     getBottomImageLabel().setVisible(false);
/*      */ 
/*  520 */     deviceUpdateProgress(0);
/*      */ 
/*  523 */     setProgressBarText(this.m_progressBarText);
/*      */ 
/*  525 */     Thread localThread = createDeviceThread();
/*  526 */     localThread.start();
/*      */ 
/*  528 */     logInfo("********* BEGINNING READ OPERATION *********");
/*      */   }
/*      */ 
/*      */   protected abstract String getPhaseNotification(int paramInt);
/*      */ 
/*      */   protected abstract String getPhaseElaboration(int paramInt);
/*      */ 
/*      */   protected String updateDynamicText(String paramString)
/*      */   {
/*  556 */     WizardSelections localWizardSelections = getWizardSelections();
/*      */ 
/*  559 */     paramString = paramString.replaceAll("@commport", "COM" + this.m_captureResult.getDevicePortReader().getSerialPortNumber());
/*      */ 
/*  562 */     paramString = paramString.replaceAll("@linkdevice", this.m_resources.getString(localWizardSelections.getLinkDevice()));
/*      */ 
/*  565 */     if (localWizardSelections.getDeviceSerialNumber() != null) {
/*  566 */       paramString = paramString.replaceAll("@serialnumber", localWizardSelections.getDeviceSerialNumber());
/*      */     }
/*      */ 
/*  569 */     return paramString;
/*      */   }
/*      */ 
/*      */   final CaptureResult getCaptureResult()
/*      */   {
/*  579 */     CaptureResult localCaptureResult = this.m_captureResult;
/*  580 */     Contract.postNonNull(localCaptureResult);
/*  581 */     return localCaptureResult;
/*      */   }
/*      */ 
/*      */   private boolean isDeviceX12(DevicePortReader paramDevicePortReader)
/*      */   {
/*  591 */     return ("512".equals(paramDevicePortReader.getModelNumber())) || ("712".equals(paramDevicePortReader.getModelNumber()));
/*      */   }
/*      */ 
/*      */   private boolean isDeviceG3B(DevicePortReader paramDevicePortReader)
/*      */   {
/*  603 */     return "7100B".equals(paramDevicePortReader.getModelNumber());
/*      */   }
/*      */ 
/*      */   private Thread createDeviceThread()
/*      */   {
/*  621 */     DeviceOperationStep localDeviceOperationStep = this;
/*      */ 
/*  630 */     WizardSelections localWizardSelections = getWizardSelections();
/*  631 */     String str1 = localWizardSelections.getLinkDevice();
/*  632 */     String str2 = localWizardSelections.getDeviceSelection();
/*      */     int i;
/*  634 */     if (str1.equals("wizard.selections.SELECTION_LINK_DEVICE_COMLINKUSB"))
/*      */     {
/*  636 */       i = 19;
/*      */     }
/*  638 */     else if (str1.equals("wizard.selections.SELECTION_LINK_DEVICE_COMLINK"))
/*      */     {
/*  640 */       i = 14;
/*      */     }
/*  642 */     else if (str1.equals("wizard.selections.SELECTION_LINK_DEVICE_PARADIGMLINK"))
/*      */     {
/*  644 */       i = 15;
/*      */     } else {
/*  646 */       Contract.unreachable();
/*  647 */       i = 0;
/*      */     }
/*      */ 
/*  653 */     long l = System.currentTimeMillis();
/*      */ 
/*  656 */     int j = getWizard().getConfig().getDevicePortReaderMessageLevel();
/*      */ 
/*  659 */     logInfo(localWizardSelections.toString());
/*      */ 
/*  672 */     boolean bool1 = ("wizard.selections.SELECTION_DEVICE_MM508".equals(str2)) || ("wizard.selections.SELECTION_DEVICE_MM511".equals(str2)) ? getWizard().getConfig().isUploadX15PumpOnly() : false;
/*      */ 
/*  686 */     boolean bool2 = (localWizardSelections.isDeviceSelectionAMeter()) && (!"wizard.selections.SELECTION_DEVICE_MMLINKMETER".equals(str2)) ? getWizard().getConfig().isUploadParadigmLinkMeterOnly() : false;
/*      */ 
/*  699 */     boolean bool3 = !getWizard().getConfig().isUploadCGM();
/*      */ 
/*  702 */     DevicePortReader localDevicePortReader = DevicePortReaderFactory.makeDevice(Wizard.mapToDevicePortReaderFactoryID(str2), getWizard().getConfig().getLogWriter(), j, i);
/*      */ 
/*  705 */     this.m_captureResult.setDevicePortReader(localDevicePortReader);
/*      */ 
/*  708 */     2 local2 = new Runnable(localWizardSelections, localDeviceOperationStep, bool1, str2, bool2, bool3, l) { private final WizardSelections val$ws;
/*      */       private final DeviceOperationStep val$deviceListener;
/*      */       private final boolean val$denyPumpUpload;
/*      */       private final String val$deviceSelection;
/*      */       private final boolean val$denyMeterUpload;
/*      */       private final boolean val$denyCGMUpload;
/*      */       private final long val$start;
/*      */ 
/*      */       public void run() { try { long l1 = System.currentTimeMillis();
/*  721 */           InitializationStep localInitializationStep = (InitializationStep)DeviceOperationStep.this.getWizard().getStep(InitializationStep.class);
/*      */ 
/*  723 */           String str2 = DeviceOperationStep.this.getWizard().getConfig().getTransferRemoteURL();
/*  724 */           NetHelper localNetHelper = new NetHelper();
/*  725 */           long l2 = localInitializationStep.getServerTime();
/*      */           try {
/*  727 */             l2 = localNetHelper.getServerTime(str2);
/*      */           }
/*      */           catch (IOException localIOException1)
/*      */           {
/*  732 */             DeviceOperationStep.this.logInfo("Fallback to init-time server time");
/*  733 */             localIOException1.printStackTrace(DeviceOperationStep.this.getWizard().getConfig().getLogWriter());
/*      */           }
/*  735 */           DeviceOperationStep.this.logInfo("Client-time@device-read is " + l1);
/*  736 */           DeviceOperationStep.this.logInfo("Server-time@device-read is " + l2);
/*      */ 
/*  739 */           SwingUtilities.invokeLater(new DeviceOperationStep.3(this));
/*      */ 
/*  751 */           boolean bool = this.val$ws.getSerialPort().equals("wizard.selections.SELECTION_SERIAL_PORT_AUTO_DETECT");
/*      */ 
/*  753 */           int i = (this.val$ws.getConnectionType().equals("wizard.selections.SELECTION_CONN_TYPE_USB")) && (DeviceOperationStep.this.getWizard().canDeviceUseUSB()) && (this.val$ws.getLinkDevice().equals("wizard.selections.SELECTION_LINK_DEVICE_PARADIGMLINK")) ? 1 : 0;
/*      */           String str1;
/*  758 */           if ((bool) || (i != 0)) {
/*      */             try
/*      */             {
/*  761 */               str1 = DeviceOperationStep.this.m_captureResult.getDevicePortReader().autoDetectDevice(this.val$deviceListener);
/*      */ 
/*  763 */               if (DeviceOperationStep.this.m_captureResult.wasCancelled()) {
/*  764 */                 DeviceOperationStep.this.deviceOperationCancelled();
/*  765 */                 return;
/*      */               }
/*      */             } catch (SerialIOHaltedException localSerialIOHaltedException1) {
/*  768 */               DeviceOperationStep.this.m_captureResult.setDeviceException(localSerialIOHaltedException1);
/*  769 */               DeviceOperationStep.this.deviceOperationFailed();
/*  770 */               return;
/*      */             } catch (BadDeviceCommException localBadDeviceCommException1) {
/*  772 */               DeviceOperationStep.this.m_captureResult.setDeviceException(localBadDeviceCommException1);
/*  773 */               DeviceOperationStep.this.deviceOperationFailed();
/*  774 */               return;
/*      */             } catch (IOException localIOException2) {
/*  776 */               DeviceOperationStep.this.m_captureResult.setDeviceException(localIOException2);
/*  777 */               DeviceOperationStep.this.deviceOperationFailed();
/*  778 */               return;
/*      */             }
/*      */           }
/*      */           else {
/*  782 */             str1 = this.val$ws.getSerialPortName();
/*      */           }
/*      */ 
/*  785 */           DeviceOperationStep.this.m_captureResult.setCommPortUsed(str1);
/*      */           try
/*      */           {
/*  789 */             if (this.val$denyPumpUpload) {
/*  790 */               throw new ConnectToPumpException("Unsupported Pump Model", 1000, this.val$deviceSelection);
/*      */             }
/*      */ 
/*  797 */             if (this.val$denyMeterUpload)
/*      */             {
/*  801 */               throw new IOException("Unsupported meter model: " + this.val$deviceSelection);
/*      */             }
/*      */ 
/*  806 */             if (this.val$denyCGMUpload)
/*      */             {
/*  810 */               throw new IOException("Unsupported CGM model: " + this.val$deviceSelection);
/*      */             }
/*      */ 
/*  815 */             DeviceOperationStep.this.m_captureResult.getDevicePortReader().readData(this.val$deviceListener, DeviceOperationStep.this.fixCommPort(str1), this.val$ws.getDeviceSerialNumber(), DeviceOperationStep.this.getWizard().getConfig().getTraceUpload());
/*      */ 
/*  819 */             if (DeviceOperationStep.this.getWizard().isStopped())
/*      */             {
/*  821 */               DeviceOperationStep.this.logInfo("aborting data acquisition due to stop request");
/*  822 */               return;
/*      */             }
/*  824 */             if (DeviceOperationStep.this.m_captureResult.wasCancelled()) {
/*  825 */               DeviceOperationStep.this.deviceOperationCancelled();
/*  826 */               return;
/*      */             }
/*      */ 
/*  830 */             long l3 = System.currentTimeMillis();
/*  831 */             long l4 = l3 - this.val$start;
/*  832 */             DeviceOperationStep.this.logInfo("run: finished reading data normally in " + l4 + " ms");
/*      */ 
/*  834 */             DeviceOperationStep.this.m_captureResult.setClientTimeAtDeviceRead(l1);
/*  835 */             DeviceOperationStep.this.m_captureResult.setServerTimeAtDeviceRead(l2);
/*      */ 
/*  838 */             DeviceOperationStep.this.logInfo("run: create snapshot image");
/*  839 */             ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
/*  840 */             InputStream localInputStream = DeviceOperationStep.this.m_captureResult.getDevicePortReader().createSnapshot();
/*  841 */             IOHelper localIOHelper = new IOHelper();
/*  842 */             localIOHelper.writeToStream(localInputStream, localByteArrayOutputStream);
/*  843 */             byte[] arrayOfByte = localByteArrayOutputStream.toByteArray();
/*  844 */             DeviceOperationStep.this.m_captureResult.setSnapshotData(arrayOfByte);
/*      */ 
/*  847 */             DeviceOperationStep.this.deviceOperationSuccessful();
/*      */           } catch (SerialIOHaltedException localSerialIOHaltedException2) {
/*  849 */             DeviceOperationStep.this.m_captureResult.setDeviceException(localSerialIOHaltedException2);
/*  850 */             DeviceOperationStep.this.deviceOperationFailed();
/*      */           } catch (BadDeviceCommException localBadDeviceCommException2) {
/*  852 */             DeviceOperationStep.this.m_captureResult.setDeviceException(localBadDeviceCommException2);
/*  853 */             DeviceOperationStep.this.deviceOperationFailed();
/*      */           } catch (BadDeviceValueException localBadDeviceValueException) {
/*  855 */             DeviceOperationStep.this.m_captureResult.setDeviceException(localBadDeviceValueException);
/*  856 */             DeviceOperationStep.this.deviceOperationFailed();
/*      */           } catch (ConnectToPumpException localConnectToPumpException) {
/*  858 */             DeviceOperationStep.this.m_captureResult.setDeviceException(localConnectToPumpException);
/*  859 */             DeviceOperationStep.this.deviceOperationFailed();
/*      */           } catch (IOException localIOException3) {
/*  861 */             DeviceOperationStep.this.m_captureResult.setDeviceException(localIOException3);
/*  862 */             DeviceOperationStep.this.deviceOperationFailed();
/*      */           }
/*      */         } catch (RuntimeException localRuntimeException) {
/*  865 */           DeviceOperationStep.this.deviceOperationFailedUnexpectedly(localRuntimeException);
/*      */         } catch (NoClassDefFoundError localNoClassDefFoundError) {
/*  867 */           DeviceOperationStep.this.deviceOperationFailedUnexpectedly(localNoClassDefFoundError);
/*      */         } catch (UnsatisfiedLinkError localUnsatisfiedLinkError) {
/*  869 */           DeviceOperationStep.this.deviceOperationFailedUnexpectedly(localUnsatisfiedLinkError);
/*      */         }
/*      */       }
/*      */     };
/*  874 */     Thread localThread = new Thread(local2);
/*  875 */     localThread.setName("Device");
/*  876 */     localThread.setDaemon(true);
/*  877 */     return localThread;
/*      */   }
/*      */ 
/*      */   private void deviceOperationSuccessful()
/*      */   {
/*  884 */     logInfo("********* END OF OPERATION (successful) *********: retryCount=" + this.m_captureResult.getRetryCount() + ", et=" + getElapsedTime());
/*      */ 
/*  886 */     SwingUtilities.invokeLater(new Runnable()
/*      */     {
/*      */       public void run()
/*      */       {
/*  892 */         DeviceOperationStep.this.finishGracefully();
/*      */ 
/*  894 */         DeviceOperationStep.this.nextRequest();
/*      */       }
/*      */     });
/*      */   }
/*      */ 
/*      */   private void deviceOperationCancelled()
/*      */   {
/*  903 */     logInfo("********* END OF OPERATION (cancelled) *********: retryCount=" + this.m_captureResult.getRetryCount() + ", et=" + getElapsedTime());
/*      */ 
/*  907 */     SwingUtilities.invokeLater(new Runnable()
/*      */     {
/*      */       public void run()
/*      */       {
/*  912 */         DeviceOperationStep.this.getWizard().showNextStep(DeviceOperationStep.this.m_failClass);
/*      */       }
/*      */     });
/*      */   }
/*      */ 
/*      */   private void deviceOperationFailed()
/*      */   {
/*  923 */     Exception localException = this.m_captureResult.getDeviceException();
/*  924 */     Contract.preNonNull(localException);
/*      */ 
/*  927 */     String str1 = "********* END OF OPERATION (failed) *********: retryCount=" + this.m_captureResult.getRetryCount() + ", et=" + getElapsedTime() + ", e=" + localException;
/*      */ 
/*  930 */     logInfo(str1);
/*      */ 
/*  932 */     localException.printStackTrace(getWizard().getConfig().getLogWriter());
/*      */ 
/*  938 */     String str2 = str1 + " CAPTURE RESULT--> " + this.m_captureResult + " WIZARD SELECTIONS--> " + getWizardSelections();
/*      */ 
/*  941 */     new NetHelper().uploadNotification(getWizard().getConfig().getTransferRemoteURL(), str2);
/*      */ 
/*  945 */     SwingUtilities.invokeLater(new Runnable()
/*      */     {
/*      */       public void run()
/*      */       {
/*  950 */         DeviceOperationStep.this.getWizard().showNextStep(DeviceOperationStep.this.m_failClass);
/*      */       }
/*      */     });
/*      */   }
/*      */ 
/*      */   private void deviceOperationFailedUnexpectedly(Throwable paramThrowable)
/*      */   {
/*  962 */     Contract.preNonNull(paramThrowable);
/*  963 */     paramThrowable.printStackTrace(getWizard().getConfig().getLogWriter());
/*  964 */     getWizard().setFailureReason(this.m_unexpectedErrorMsg);
/*  965 */     getWizard().showNextStep(UnrecoverableErrorStep.class);
/*      */   }
/*      */ 
/*      */   private void finishGracefully()
/*      */   {
/*  977 */     this.m_phaseNotificationLabel.setText(this.m_resources.getString("wizard.notification.end"));
/*  978 */     this.m_phaseElaborationLabel.setText(this.m_resources.getString("wizard.elaboration.end"));
/*      */ 
/*  981 */     int i = this.m_progressBar.getValue();
/*      */ 
/*  983 */     for (int j = i; j <= 100; j++)
/*      */     {
/*  985 */       this.m_progressBar.setValue(j);
/*  986 */       sleepMS(25);
/*      */     }
/*      */   }
/*      */ 
/*      */   private int fixCommPort(String paramString)
/*      */   {
/* 1000 */     String str = paramString.substring("COM".length(), paramString.length());
/* 1001 */     return new Integer(str).intValue();
/*      */   }
/*      */ 
/*      */   private String getElapsedTime()
/*      */   {
/* 1010 */     long l = System.currentTimeMillis() - this.m_captureResult.getStartTimeMS();
/* 1011 */     return getDurationString(l);
/*      */   }
/*      */ 
/*      */   private void startTimer()
/*      */   {
/* 1018 */     this.m_captureResult.setStartTimeMS(System.currentTimeMillis());
/*      */   }
/*      */ 
/*      */   private String getDurationString(long paramLong)
/*      */   {
/* 1029 */     long l1 = paramLong / 1000L;
/* 1030 */     long l2 = l1 / 60L;
/* 1031 */     long l3 = l1 % 60L;
/* 1032 */     String str = l2 + ":";
/*      */ 
/* 1034 */     if (l3 < 10L) {
/* 1035 */       str = str + "0";
/*      */     }
/*      */ 
/* 1038 */     str = str + l3;
/* 1039 */     return str;
/*      */   }
/*      */ 
/*      */   private void setProgressBarText(String paramString)
/*      */   {
/* 1048 */     String str = MessageHelper.format(this.m_resources.getString("wizard.progressbar.message"), new Object[] { paramString, Integer.toString(this.m_progressBar.getValue()) });
/*      */ 
/* 1051 */     this.m_progressBarLabel.setText(str);
/*      */   }
/*      */ 
/*      */   private void clearProgressBarText()
/*      */   {
/* 1058 */     this.m_progressBarLabel.setText("<html><font size=4>&nbsp;</font></html>");
/*      */   }
/*      */ 
/*      */   private void sleepMS(int paramInt)
/*      */   {
/*      */     try
/*      */     {
/* 1068 */       Thread.sleep(paramInt);
/*      */     }
/*      */     catch (InterruptedException localInterruptedException)
/*      */     {
/*      */     }
/*      */   }
/*      */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.steps.DeviceOperationStep
 * JD-Core Version:    0.6.0
 */