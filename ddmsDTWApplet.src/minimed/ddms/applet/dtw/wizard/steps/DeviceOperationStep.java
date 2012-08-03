/*      */ package minimed.ddms.applet.dtw.wizard.steps;
/*      */ 
/*      */ import java.awt.BorderLayout;
/*      */ import java.awt.Insets;
/*      */ import java.io.ByteArrayOutputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.util.ResourceBundle;
/*      */ import javax.swing.Icon;
/*      */ import javax.swing.ImageIcon;
/*      */ import javax.swing.JButton;
/*      */ import javax.swing.JLabel;
/*      */ import javax.swing.JPanel;
/*      */ import javax.swing.JProgressBar;
/*      */ import javax.swing.SwingUtilities;
/*      */ import javax.swing.border.EmptyBorder;
/*      */ import minimed.ddms.A.G;
/*      */ import minimed.ddms.A.I;
/*      */ import minimed.ddms.A.P;
/*      */ import minimed.ddms.A.W;
/*      */ import minimed.ddms.A.Z;
/*      */ import minimed.ddms.A.t;
/*      */ import minimed.ddms.A.v;
/*      */ import minimed.ddms.applet.dtw.CaptureResult;
/*      */ import minimed.ddms.applet.dtw.IOHelper;
/*      */ import minimed.ddms.applet.dtw.MessageHelper;
/*      */ import minimed.ddms.applet.dtw.NetHelper;
/*      */ import minimed.ddms.applet.dtw.PropertyWriter;
/*      */ import minimed.ddms.applet.dtw.wizard.Wizard;
/*      */ import minimed.ddms.applet.dtw.wizard.WizardConfig;
/*      */ import minimed.ddms.applet.dtw.wizard.WizardSelections;
/*      */ import minimed.ddms.applet.dtw.wizard.WizardStep;
/*      */ import minimed.util.Contract;
/*      */ import minimed.util.OSInfo;
/*      */ 
/*      */ public abstract class DeviceOperationStep extends WizardStep
/*      */   implements v
/*      */ {
/*      */   private static final int PERCENT_100 = 100;
/*      */   private static final int SECONDS_PER_MINUTE = 60;
/*      */   private static final int MILLISECONDS_PER_SECOND = 1000;
/*      */   private static final int RETRY_FLASH_DELAY_MS = 100;
/*      */   private static final int INSET_MAIN_LEFT = 5;
/*      */   private static final int INSET_MAIN_RIGHT = 5;
/*      */   private static final int MILLIS_TO_SLEEP = 25;
/*      */   private final JLabel m_phaseNotificationLabel;
/*      */   private final JLabel m_phaseElaborationLabel;
/*      */   private final JLabel m_progressBarLabel;
/*      */   private final JProgressBar m_progressBar;
/*      */   private final String m_progressBarText;
/*      */   private final Class m_failClass;
/*      */ 
/*      */   protected DeviceOperationStep(Wizard paramWizard, String paramString1, Class paramClass1, Class paramClass2, String paramString2)
/*      */   {
/*  102 */     super(paramWizard, paramClass1);
/*      */ 
/*  104 */     Contract.preNonNull(paramClass1);
/*  105 */     Contract.preNonNull(paramClass2);
/*  106 */     this.m_failClass = paramClass2;
/*      */ 
/*  109 */     this.m_phaseNotificationLabel = getLeftBannerLabel();
/*  110 */     this.m_phaseNotificationLabel.setText("");
/*  111 */     Object localObject = new ImageIcon(getImage(paramString1));
/*  112 */     getRightBannerLabel().setIcon((Icon)localObject);
/*      */ 
/*  115 */     localObject = getInfoIcon();
/*  116 */     getTopImageLabel().setIcon((Icon)localObject);
/*      */ 
/*  119 */     JPanel localJPanel1 = new JPanel();
/*  120 */     setCustomColors(localJPanel1);
/*  121 */     localJPanel1.setLayout(new BorderLayout());
/*      */ 
/*  124 */     JLabel localJLabel = new JLabel(this.m_resources.getString("wizard.warning.stayHere"));
/*  125 */     localJLabel.setHorizontalAlignment(0);
/*  126 */     localJPanel1.add(localJLabel, "North");
/*      */ 
/*  129 */     this.m_phaseElaborationLabel = new JLabel(" ");
/*  130 */     this.m_phaseElaborationLabel.setHorizontalAlignment(0);
/*  131 */     localJPanel1.add(this.m_phaseElaborationLabel, "Center");
/*      */ 
/*  134 */     JPanel localJPanel2 = new JPanel();
/*  135 */     setCustomColors(localJPanel2);
/*  136 */     localJPanel2.setLayout(new BorderLayout());
/*      */ 
/*  138 */     this.m_progressBarText = this.m_resources.getString(paramString2);
/*  139 */     this.m_progressBarLabel = new JLabel();
/*  140 */     this.m_progressBarLabel.setHorizontalAlignment(0);
/*  141 */     localJPanel2.add(this.m_progressBarLabel, "Center");
/*      */ 
/*  143 */     this.m_progressBar = new JProgressBar();
/*      */ 
/*  145 */     this.m_progressBar.setBackground(paramWizard.getConfig().getBackgroundColor());
/*  146 */     this.m_progressBar.setVisible(false);
/*  147 */     localJPanel2.add(this.m_progressBar, "South");
/*      */ 
/*  149 */     localJPanel1.add(localJPanel2, "South");
/*      */ 
/*  151 */     JPanel localJPanel3 = getContentArea();
/*  152 */     localJPanel3.setLayout(new BorderLayout());
/*  153 */     localJPanel3.add(localJPanel1);
/*  154 */     localJPanel3.setBorder(new EmptyBorder(new Insets(0, 5, 0, 5)));
/*      */   }
/*      */ 
/*      */   public final String toString()
/*      */   {
/*  166 */     PropertyWriter localPropertyWriter = new PropertyWriter(super.toString());
/*  167 */     localPropertyWriter.add("captureResult", getWizard().getCaptureResult());
/*  168 */     return localPropertyWriter.toString();
/*      */   }
/*      */ 
/*      */   public long getLastHistoryPageNumber(String paramString1, String paramString2)
/*      */   {
/*  202 */     Contract.pre(("515".equals(paramString1)) || ("715".equals(paramString1)) || ("522".equals(paramString1)) || ("522K".equals(paramString1)) || ("722".equals(paramString1)) || ("722K".equals(paramString1)) || ("523".equals(paramString1)) || ("523K".equals(paramString1)) || ("723".equals(paramString1)) || ("723K".equals(paramString1)) || ("553".equals(paramString1)) || ("753".equals(paramString1)) || ("554".equals(paramString1)) || ("754".equals(paramString1)) || ("7100".equals(paramString1)) || ("7100B".equals(paramString1)) || ("7100K".equals(paramString1)) || ("7200".equals(paramString1)));
/*      */ 
/*  220 */     Contract.preString(paramString2);
/*      */ 
/*  223 */     long l = 0L;
/*  224 */     Contract.post(l >= 0L);
/*  225 */     return l;
/*      */   }
/*      */ 
/*      */   public boolean isHaltRequested()
/*      */   {
/*  235 */     return getWizard().getCaptureResult().wasCancelled();
/*      */   }
/*      */ 
/*      */   public long getLastGlucoseHistoryPageNumber(String paramString1, String paramString2)
/*      */   {
/*  267 */     Contract.pre(("522".equals(paramString1)) || ("522K".equals(paramString1)) || ("722".equals(paramString1)) || ("722K".equals(paramString1)) || ("523".equals(paramString1)) || ("523K".equals(paramString1)) || ("723".equals(paramString1)) || ("723K".equals(paramString1)) || ("553".equals(paramString1)) || ("753".equals(paramString1)) || ("554".equals(paramString1)) || ("754".equals(paramString1)) || ("7100".equals(paramString1)) || ("7100B".equals(paramString1)) || ("7100K".equals(paramString1)) || ("7200".equals(paramString1)));
/*      */ 
/*  283 */     Contract.preString(paramString2);
/*      */ 
/*  286 */     long l = 0L;
/*  287 */     Contract.post(l >= 0L);
/*  288 */     return l;
/*      */   }
/*      */ 
/*      */   public final void allowDeviceOperation(G paramG)
/*      */     throws P
/*      */   {
/*  299 */     WizardConfig localWizardConfig = getWizard().getConfig();
/*  300 */     boolean bool1 = localWizardConfig.getWizardSelections().isDeviceSelectionAPump();
/*  301 */     boolean bool2 = localWizardConfig.getWizardSelections().isDeviceSelectionACGM();
/*      */ 
/*  303 */     Contract.pre((paramG.M()) || (paramG.A()));
/*      */ 
/*  305 */     if (isDeviceX12(paramG))
/*      */     {
/*  307 */       throw new P("Unsupported Pump Model", 1000, paramG.R());
/*      */     }
/*      */ 
/*  313 */     if ((isDeviceG3B(paramG)) && (!localWizardConfig.isUploadG3B()))
/*      */     {
/*  315 */       throw new P("Unsupported Pump Model", 1000, paramG.R());
/*      */     }
/*      */ 
/*  321 */     if (((!paramG.A()) || (!bool2)) && ((!paramG.M()) || (!bool1)))
/*      */     {
/*  324 */       throw new P("Wrong Device Selection", 2000, paramG.S());
/*      */     }
/*      */ 
/*  330 */     if (paramG.M())
/*      */     {
/*  332 */       getWizard().enableVerifySuspendOffMessage();
/*      */     }
/*      */   }
/*      */ 
/*      */   public final void deviceUpdateProgress(int paramInt)
/*      */   {
/*  342 */     CaptureResult localCaptureResult = getWizard().getCaptureResult();
/*  343 */     if (paramInt >= localCaptureResult.getPercentComplete()) {
/*  344 */       localCaptureResult.setPercentComplete(paramInt);
/*      */ 
/*  347 */       this.m_progressBar.setValue(paramInt);
/*  348 */       setProgressBarText(this.m_progressBarText);
/*      */     }
/*      */   }
/*      */ 
/*      */   public final void deviceUpdatePhase(int paramInt, String paramString)
/*      */   {
/*  361 */     WizardSelections localWizardSelections = getWizardSelections();
/*  362 */     CaptureResult localCaptureResult = getWizard().getCaptureResult();
/*  363 */     if (paramInt == 4)
/*      */     {
/*  365 */       if ((localWizardSelections.getDeviceSelection().equals("wizard.selections.SELECTION_DEVICE_ASCENSIA_CONTOUR_USB_METER")) || (localWizardSelections.getDeviceSelection().equals("wizard.selections.SELECTION_DEVICE_XTLINKMETER")))
/*      */       {
/*  369 */         getCancelButton().setEnabled(false);
/*      */       }
/*      */ 
/*  373 */       localCaptureResult.setDeviceInitialized();
/*      */ 
/*  376 */       deviceUpdateProgress(localCaptureResult.getPercentComplete() + 1);
/*      */     }
/*  380 */     else if (paramInt == 5)
/*      */     {
/*  383 */       if ((localWizardSelections.getDeviceSelection().equals("wizard.selections.SELECTION_DEVICE_ASCENSIA_CONTOUR_USB_METER")) || (localWizardSelections.getDeviceSelection().equals("wizard.selections.SELECTION_DEVICE_XTLINKMETER")))
/*      */       {
/*  387 */         getCancelButton().setEnabled(true);
/*      */       }
/*      */ 
/*  390 */       localCaptureResult.setAcquireDataStartPercentComplete(localCaptureResult.getPercentComplete());
/*      */ 
/*  393 */       deviceUpdateProgress(localCaptureResult.getPercentComplete() + 1);
/*      */     }
/*      */ 
/*  397 */     logInfo("deviceUpdatePhase: PHASE=" + paramInt + "-" + updateDynamicText(paramString) + "; percent complete=" + localCaptureResult.getPercentComplete() + "%");
/*      */ 
/*  401 */     localCaptureResult.setPhase(paramInt);
/*  402 */     String str1 = getPhaseElaboration(paramInt);
/*  403 */     String str2 = getPhaseNotification(paramInt);
/*      */ 
/*  406 */     String str3 = updateDynamicText(str2);
/*  407 */     String str4 = updateDynamicText(str1);
/*      */ 
/*  410 */     1 local1 = new Runnable(str4, str3)
/*      */     {
/*      */       public void run()
/*      */       {
/*  415 */         DeviceOperationStep.this.m_phaseElaborationLabel.setText("<html><center>" + this.val$phaseElaboration + "</center></html>");
/*      */ 
/*  419 */         DeviceOperationStep.this.m_phaseNotificationLabel.setText("<html>" + this.val$phaseNotification + "</html>");
/*      */ 
/*  425 */         if (DeviceOperationStep.this.getWizard().getCaptureResult().getPhase() == 6)
/*      */         {
/*  427 */           DeviceOperationStep.this.disable(DeviceOperationStep.access$300(DeviceOperationStep.this));
/*      */         }
/*      */       }
/*      */     };
/*  432 */     SwingUtilities.invokeLater(local1);
/*      */   }
/*      */ 
/*      */   public final void deviceUpdateState(int paramInt, String paramString)
/*      */   {
/*  445 */     CaptureResult localCaptureResult = getWizard().getCaptureResult();
/*  446 */     localCaptureResult.setState(paramInt);
/*      */ 
/*  449 */     if ((paramInt == 9) || (paramInt == 8))
/*      */     {
/*  451 */       localCaptureResult.setCancelled();
/*      */     }
/*      */ 
/*  454 */     logInfo("deviceUpdateState: state=" + paramInt + "-" + paramString + "; percent complete=" + localCaptureResult.getPercentComplete() + "%");
/*      */ 
/*  458 */     if (paramInt == 7) {
/*  459 */       localCaptureResult.incrementRetryCount();
/*      */ 
/*  462 */       clearProgressBarText();
/*  463 */       sleepMS(100);
/*  464 */       setProgressBarText(this.m_progressBarText);
/*      */     }
/*      */   }
/*      */ 
/*      */   protected final void appletStopped()
/*      */   {
/*  472 */     CaptureResult localCaptureResult = getWizard().getCaptureResult();
/*  473 */     if (localCaptureResult.getDevicePortReader() != null) {
/*  474 */       logInfo("Halt the deviceportreader");
/*  475 */       localCaptureResult.getDevicePortReader().P();
/*      */     }
/*      */   }
/*      */ 
/*      */   protected final void cancelRequest()
/*      */   {
/*  483 */     logInfo("cancelRequest: Cancel requested...");
/*      */ 
/*  486 */     disable(getCancelButton());
/*      */ 
/*  489 */     CaptureResult localCaptureResult = getWizard().getCaptureResult();
/*  490 */     if (localCaptureResult.getDevicePortReader() != null) {
/*  491 */       localCaptureResult.getDevicePortReader().P();
/*      */ 
/*  493 */       disableCursor();
/*  494 */       setProgressBarText(this.m_resources.getString("wizard.progressbar.cancel"));
/*      */     }
/*      */   }
/*      */ 
/*      */   protected final void stepShown()
/*      */   {
/*  510 */     getWizardSelections().flush();
/*      */ 
/*  513 */     getBackButton().setEnabled(false);
/*  514 */     getNextButton().setEnabled(false);
/*  515 */     getCancelButton().setEnabled(false);
/*  516 */     getFinishButton().setEnabled(false);
/*      */ 
/*  519 */     getWizard().getCaptureResult().clear();
/*      */ 
/*  521 */     startTimer();
/*      */ 
/*  523 */     enableCursor();
/*  524 */     this.m_progressBar.setVisible(true);
/*      */ 
/*  527 */     this.m_phaseNotificationLabel.setText("");
/*  528 */     this.m_phaseElaborationLabel.setText("");
/*      */ 
/*  531 */     getBottomImageLabel().setText("");
/*  532 */     getBottomImageLabel().setVisible(false);
/*      */ 
/*  534 */     deviceUpdateProgress(0);
/*      */ 
/*  537 */     setProgressBarText(this.m_progressBarText);
/*      */ 
/*  539 */     Thread localThread = createDeviceThread();
/*  540 */     localThread.start();
/*      */ 
/*  542 */     logInfo("********* BEGINNING READ OPERATION *********");
/*      */   }
/*      */ 
/*      */   protected abstract String getPhaseNotification(int paramInt);
/*      */ 
/*      */   protected abstract String getPhaseElaboration(int paramInt);
/*      */ 
/*      */   protected String updateDynamicText(String paramString)
/*      */   {
/*  570 */     WizardSelections localWizardSelections = getWizardSelections();
/*  571 */     CaptureResult localCaptureResult = getWizard().getCaptureResult();
/*      */ 
/*  575 */     if ((localCaptureResult.getDevicePortReader().L() != null) && (localCaptureResult.getDevicePortReader().L().equals(this.m_resources.getString("wizard.selections.NO_PORT_FOUND"))))
/*      */     {
/*  578 */       paramString = paramString.replaceAll("@commport", "");
/*      */     }
/*      */     else {
/*  581 */       if ((OSInfo.isMac()) && (localCaptureResult.getDevicePortReader().L() != null)) {
/*  582 */         for (int i = 0; i < WizardStep.PORTS_FILTERED_MAC_LIST.length; i++) {
/*  583 */           if (!localCaptureResult.getDevicePortReader().L().contains(WizardStep.PORTS_FILTERED_MAC_LIST[i]))
/*      */             continue;
/*  585 */           paramString = paramString.replaceAll("@commport", "");
/*      */         }
/*      */       }
/*      */ 
/*  589 */       paramString = paramString.replaceAll("@commport", localCaptureResult.getDevicePortReader().L());
/*      */     }
/*      */ 
/*  593 */     paramString = paramString.replaceAll("@linkdevice", this.m_resources.getString(localWizardSelections.getLinkDevice()));
/*      */ 
/*  596 */     if (localWizardSelections.getDeviceSerialNumber() != null) {
/*  597 */       paramString = paramString.replaceAll("@serialnumber", localWizardSelections.getDeviceSerialNumber());
/*      */     }
/*      */ 
/*  600 */     return paramString;
/*      */   }
/*      */ 
/*      */   final CaptureResult getCaptureResult()
/*      */   {
/*  609 */     return getWizard().getCaptureResult();
/*      */   }
/*      */ 
/*      */   private boolean isDeviceX12(G paramG)
/*      */   {
/*  619 */     return ("512".equals(paramG.S())) || ("712".equals(paramG.S()));
/*      */   }
/*      */ 
/*      */   private boolean isDeviceG3B(G paramG)
/*      */   {
/*  631 */     return "7100B".equals(paramG.S());
/*      */   }
/*      */ 
/*      */   private Thread createDeviceThread()
/*      */   {
/*  649 */     DeviceOperationStep localDeviceOperationStep = this;
/*      */ 
/*  658 */     WizardSelections localWizardSelections = getWizardSelections();
/*  659 */     String str1 = localWizardSelections.getLinkDevice();
/*  660 */     String str2 = localWizardSelections.getDeviceSelection();
/*      */     int i;
/*  662 */     if (str1.equals("wizard.selections.SELECTION_LINK_DEVICE_COMLINKUSB"))
/*      */     {
/*  664 */       i = 19;
/*  665 */     } else if (str1.equals("wizard.selections.SELECTION_LINK_DEVICE_XTLINKUSB"))
/*      */     {
/*  667 */       i = 20;
/*      */     }
/*  669 */     else if (str1.equals("wizard.selections.SELECTION_LINK_DEVICE_COMLINK"))
/*      */     {
/*  671 */       i = 14;
/*      */     }
/*  673 */     else if (str1.equals("wizard.selections.SELECTION_LINK_DEVICE_PARADIGMLINK"))
/*      */     {
/*  675 */       i = 15;
/*      */     } else {
/*  677 */       Contract.unreachable();
/*  678 */       i = 0;
/*      */     }
/*      */ 
/*  684 */     long l = System.currentTimeMillis();
/*      */ 
/*  687 */     int j = getWizard().getConfig().getDevicePortReaderMessageLevel();
/*      */ 
/*  690 */     logInfo(localWizardSelections.toString());
/*      */ 
/*  701 */     boolean bool1 = (localWizardSelections.isDeviceSelectionAMeter()) && (!"wizard.selections.SELECTION_DEVICE_MMLINKMETER".equals(str2)) ? getWizard().getConfig().isUploadParadigmLinkMeterOnly() : false;
/*      */ 
/*  714 */     boolean bool2 = !getWizard().getConfig().isUploadCGM();
/*      */ 
/*  717 */     G localG = I.A(Wizard.mapToDevicePortReaderFactoryID(str2), getWizard().getConfig().getLogWriter(), j, i);
/*      */ 
/*  720 */     getCaptureResult().setDevicePortReader(localG);
/*      */ 
/*  723 */     2 local2 = new Runnable(localWizardSelections, localDeviceOperationStep, bool1, str2, bool2, l)
/*      */     {
/*      */       public void run()
/*      */       {
/*      */         try
/*      */         {
/*  735 */           long l1 = System.currentTimeMillis();
/*  736 */           InitializationStep localInitializationStep = (InitializationStep)DeviceOperationStep.this.getWizard().getStep(InitializationStep.class);
/*      */ 
/*  738 */           String str2 = DeviceOperationStep.this.getWizard().getConfig().getTransferRemoteURL();
/*  739 */           NetHelper localNetHelper = new NetHelper();
/*  740 */           long l2 = localInitializationStep.getServerTime();
/*      */           try {
/*  742 */             l2 = localNetHelper.getServerTime(str2);
/*      */           }
/*      */           catch (IOException localIOException1)
/*      */           {
/*  747 */             DeviceOperationStep.this.logInfo("Fallback to init-time server time");
/*  748 */             localIOException1.printStackTrace(DeviceOperationStep.this.getWizard().getConfig().getLogWriter());
/*      */           }
/*  750 */           DeviceOperationStep.this.logInfo("Client-time@device-read is " + l1);
/*  751 */           DeviceOperationStep.this.logInfo("Server-time@device-read is " + l2);
/*      */ 
/*  754 */           SwingUtilities.invokeLater(new Runnable()
/*      */           {
/*      */             public void run()
/*      */             {
/*  760 */               if ((!DeviceOperationStep.2.this.val$ws.getDeviceSelection().equals("wizard.selections.SELECTION_DEVICE_ASCENSIA_CONTOUR_USB_METER")) && (!DeviceOperationStep.2.this.val$ws.getDeviceSelection().equals("wizard.selections.SELECTION_DEVICE_XTLINKMETER")))
/*      */               {
/*  766 */                 DeviceOperationStep.this.enableWithFocus(DeviceOperationStep.access$1100(DeviceOperationStep.this));
/*      */               }
/*      */             }
/*      */           });
/*      */           String str1;
/*  774 */           if (DeviceOperationStep.this.getWizard().isAutoDetect())
/*      */           {
/*  776 */             DeviceOperationStep.this.logInfo("Perform auto-detect of serial port device is connected to... ");
/*      */             try {
/*  778 */               str1 = DeviceOperationStep.this.getCaptureResult().getDevicePortReader().A(this.val$deviceListener);
/*      */ 
/*  780 */               if (DeviceOperationStep.this.getCaptureResult().wasCancelled()) {
/*  781 */                 DeviceOperationStep.this.deviceOperationCancelled();
/*  782 */                 return;
/*      */               }
/*      */             } catch (W localW1) {
/*  785 */               DeviceOperationStep.this.getCaptureResult().setDeviceException(localW1);
/*  786 */               DeviceOperationStep.this.deviceOperationFailed();
/*  787 */               return;
/*      */             } catch (t localt1) {
/*  789 */               DeviceOperationStep.this.getCaptureResult().setDeviceException(localt1);
/*  790 */               DeviceOperationStep.this.deviceOperationFailed();
/*  791 */               return;
/*      */             } catch (IOException localIOException2) {
/*  793 */               DeviceOperationStep.this.getCaptureResult().setDeviceException(localIOException2);
/*  794 */               DeviceOperationStep.this.deviceOperationFailed();
/*  795 */               return;
/*      */             }
/*      */           }
/*      */           else {
/*  799 */             str1 = this.val$ws.getSerialPortName();
/*      */           }
/*      */ 
/*  802 */           DeviceOperationStep.this.getCaptureResult().setCommPortUsed(str1);
/*      */           try
/*      */           {
/*  806 */             if (this.val$denyMeterUpload)
/*      */             {
/*  810 */               throw new IOException("Unsupported meter model: " + this.val$deviceSelection);
/*      */             }
/*      */ 
/*  815 */             if (this.val$denyCGMUpload)
/*      */             {
/*  819 */               throw new IOException("Unsupported CGM model: " + this.val$deviceSelection);
/*      */             }
/*      */ 
/*  824 */             DeviceOperationStep.this.getCaptureResult().getDevicePortReader().A(this.val$deviceListener, str1, this.val$ws.getDeviceSerialNumber(), DeviceOperationStep.this.getWizard().getConfig().getTraceUpload());
/*      */ 
/*  828 */             if (DeviceOperationStep.this.getWizard().isStopped())
/*      */             {
/*  830 */               DeviceOperationStep.this.logInfo("aborting data acquisition due to stop request");
/*  831 */               return;
/*      */             }
/*  833 */             if (DeviceOperationStep.this.getCaptureResult().wasCancelled()) {
/*  834 */               DeviceOperationStep.this.deviceOperationCancelled();
/*  835 */               return;
/*      */             }
/*      */ 
/*  839 */             long l3 = System.currentTimeMillis();
/*  840 */             long l4 = l3 - this.val$start;
/*  841 */             DeviceOperationStep.this.logInfo("run: finished reading data normally in " + l4 + " ms");
/*      */ 
/*  843 */             DeviceOperationStep.this.getCaptureResult().setReadTimeSeconds((int)(l4 / 1000L));
/*      */ 
/*  845 */             DeviceOperationStep.this.getCaptureResult().setClientTimeAtDeviceRead(l1);
/*  846 */             DeviceOperationStep.this.getCaptureResult().setServerTimeAtDeviceRead(l2);
/*      */ 
/*  849 */             DeviceOperationStep.this.logInfo("run: create snapshot image");
/*  850 */             ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
/*  851 */             InputStream localInputStream = DeviceOperationStep.this.getCaptureResult().getDevicePortReader().C();
/*  852 */             IOHelper localIOHelper = new IOHelper();
/*  853 */             localIOHelper.writeToStream(localInputStream, localByteArrayOutputStream);
/*  854 */             byte[] arrayOfByte = localByteArrayOutputStream.toByteArray();
/*  855 */             DeviceOperationStep.this.getCaptureResult().setSnapshotData(arrayOfByte);
/*      */ 
/*  858 */             DeviceOperationStep.this.deviceOperationSuccessful();
/*      */           } catch (W localW2) {
/*  860 */             DeviceOperationStep.this.getCaptureResult().setDeviceException(localW2);
/*  861 */             DeviceOperationStep.this.deviceOperationFailed();
/*      */           } catch (t localt2) {
/*  863 */             DeviceOperationStep.this.getCaptureResult().setDeviceException(localt2);
/*  864 */             DeviceOperationStep.this.deviceOperationFailed();
/*      */           } catch (Z localZ) {
/*  866 */             DeviceOperationStep.this.getCaptureResult().setDeviceException(localZ);
/*  867 */             DeviceOperationStep.this.deviceOperationFailed();
/*      */           } catch (P localP) {
/*  869 */             DeviceOperationStep.this.getCaptureResult().setDeviceException(localP);
/*  870 */             DeviceOperationStep.this.deviceOperationFailed();
/*      */           } catch (IOException localIOException3) {
/*  872 */             DeviceOperationStep.this.getCaptureResult().setDeviceException(localIOException3);
/*  873 */             DeviceOperationStep.this.deviceOperationFailed();
/*      */           }
/*      */         } catch (RuntimeException localRuntimeException) {
/*  876 */           DeviceOperationStep.this.deviceOperationFailedUnexpectedly(localRuntimeException);
/*      */         } catch (NoClassDefFoundError localNoClassDefFoundError) {
/*  878 */           DeviceOperationStep.this.deviceOperationFailedUnexpectedly(localNoClassDefFoundError);
/*      */         } catch (UnsatisfiedLinkError localUnsatisfiedLinkError) {
/*  880 */           DeviceOperationStep.this.deviceOperationFailedUnexpectedly(localUnsatisfiedLinkError);
/*      */         }
/*      */       }
/*      */     };
/*  885 */     Thread localThread = new Thread(local2);
/*  886 */     localThread.setName("Device");
/*  887 */     localThread.setDaemon(true);
/*  888 */     return localThread;
/*      */   }
/*      */ 
/*      */   private void deviceOperationSuccessful()
/*      */   {
/*  895 */     logInfo("********* END OF OPERATION (successful) *********: retryCount=" + getCaptureResult().getRetryCount() + ", et=" + getElapsedTime());
/*      */ 
/*  897 */     SwingUtilities.invokeLater(new Runnable()
/*      */     {
/*      */       public void run()
/*      */       {
/*  903 */         DeviceOperationStep.this.finishGracefully();
/*      */ 
/*  905 */         DeviceOperationStep.this.nextRequest();
/*      */       }
/*      */     });
/*      */   }
/*      */ 
/*      */   private void deviceOperationCancelled()
/*      */   {
/*  914 */     logInfo("********* END OF OPERATION (cancelled) *********: retryCount=" + getCaptureResult().getRetryCount() + ", et=" + getElapsedTime());
/*      */ 
/*  918 */     SwingUtilities.invokeLater(new Runnable()
/*      */     {
/*      */       public void run()
/*      */       {
/*  923 */         DeviceOperationStep.this.getWizard().showNextStep(DeviceOperationStep.this.m_failClass);
/*      */       }
/*      */     });
/*      */   }
/*      */ 
/*      */   private void deviceOperationFailed()
/*      */   {
/*  934 */     Exception localException = getCaptureResult().getDeviceException();
/*  935 */     Contract.preNonNull(localException);
/*      */ 
/*  938 */     String str1 = "********* END OF OPERATION (failed) *********: retryCount=" + getCaptureResult().getRetryCount() + ", et=" + getElapsedTime() + ", e=" + localException;
/*      */ 
/*  941 */     logInfo(str1);
/*      */ 
/*  943 */     localException.printStackTrace(getWizard().getConfig().getLogWriter());
/*      */ 
/*  949 */     String str2 = str1 + " CAPTURE RESULT--> " + getCaptureResult() + " WIZARD SELECTIONS--> " + getWizardSelections();
/*      */ 
/*  952 */     new NetHelper().uploadNotification(getWizard().getConfig().getTransferRemoteURL(), str2);
/*      */ 
/*  956 */     SwingUtilities.invokeLater(new Runnable()
/*      */     {
/*      */       public void run()
/*      */       {
/*  961 */         DeviceOperationStep.this.getWizard().showNextStep(DeviceOperationStep.this.m_failClass);
/*      */       }
/*      */     });
/*      */   }
/*      */ 
/*      */   private void deviceOperationFailedUnexpectedly(Throwable paramThrowable)
/*      */   {
/*  973 */     Contract.preNonNull(paramThrowable);
/*  974 */     paramThrowable.printStackTrace(getWizard().getConfig().getLogWriter());
/*  975 */     getWizard().setFailureReason(this.m_unexpectedErrorMsg);
/*  976 */     getWizard().showNextStep(UnrecoverableErrorStep.class);
/*      */   }
/*      */ 
/*      */   private void finishGracefully()
/*      */   {
/*  988 */     this.m_phaseNotificationLabel.setText(this.m_resources.getString("wizard.notification.end"));
/*  989 */     this.m_phaseElaborationLabel.setText(this.m_resources.getString("wizard.elaboration.end"));
/*      */ 
/*  992 */     int i = this.m_progressBar.getValue();
/*      */ 
/*  994 */     for (int j = i; j <= 100; j++)
/*      */     {
/*  996 */       this.m_progressBar.setValue(j);
/*  997 */       sleepMS(25);
/*      */     }
/*      */   }
/*      */ 
/*      */   private String getElapsedTime()
/*      */   {
/* 1007 */     long l = System.currentTimeMillis() - getCaptureResult().getStartTimeMS();
/* 1008 */     return getDurationString(l);
/*      */   }
/*      */ 
/*      */   private void startTimer()
/*      */   {
/* 1015 */     getCaptureResult().setStartTimeMS(System.currentTimeMillis());
/*      */   }
/*      */ 
/*      */   private String getDurationString(long paramLong)
/*      */   {
/* 1026 */     long l1 = paramLong / 1000L;
/* 1027 */     long l2 = l1 / 60L;
/* 1028 */     long l3 = l1 % 60L;
/* 1029 */     String str = l2 + ":";
/*      */ 
/* 1031 */     if (l3 < 10L) {
/* 1032 */       str = str + "0";
/*      */     }
/*      */ 
/* 1035 */     str = str + l3;
/* 1036 */     return str;
/*      */   }
/*      */ 
/*      */   private void setProgressBarText(String paramString)
/*      */   {
/* 1045 */     String str = MessageHelper.format(this.m_resources.getString("wizard.progressbar.message"), new Object[] { paramString, Integer.toString(this.m_progressBar.getValue()) });
/*      */ 
/* 1048 */     this.m_progressBarLabel.setText(str);
/*      */   }
/*      */ 
/*      */   private void clearProgressBarText()
/*      */   {
/* 1055 */     this.m_progressBarLabel.setText("<html><font size=4>&nbsp;</font></html>");
/*      */   }
/*      */ 
/*      */   private void sleepMS(int paramInt)
/*      */   {
/*      */     try
/*      */     {
/* 1065 */       Thread.sleep(paramInt);
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