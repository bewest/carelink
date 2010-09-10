/*     */ package minimed.ddms.applet.dtw.wizard.steps;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Container;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import minimed.ddms.applet.dtw.CaptureResult;
/*     */ import minimed.ddms.applet.dtw.LogWriter;
/*     */ import minimed.ddms.applet.dtw.NetHelper;
/*     */ import minimed.ddms.applet.dtw.OperationResult;
/*     */ import minimed.ddms.applet.dtw.UploadCancelledException;
/*     */ import minimed.ddms.applet.dtw.UploadFailedException;
/*     */ import minimed.ddms.applet.dtw.wizard.Wizard;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardConfig;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardSelections;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardStep;
/*     */ import minimed.ddms.deviceportreader.DevicePortReader;
/*     */ import minimed.ddms.deviceportreader.TraceHistorySet;
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ public class TransferDataStep extends WizardStep
/*     */ {
/*  38 */   private final String m_htmlText = this.m_resources.getString("wizard.transferstep.text");
/*     */   private final JLabel m_label;
/*     */ 
/*     */   public TransferDataStep(Wizard paramWizard)
/*     */   {
/*  50 */     super(paramWizard, null);
/*     */ 
/*  52 */     getLeftBannerLabel().setText(this.m_resources.getString("wizard.transferstep.leftbanner"));
/*  53 */     Object localObject = new ImageIcon(getImage("wizard.read.icon"));
/*  54 */     getRightBannerLabel().setIcon((Icon)localObject);
/*     */ 
/*  57 */     localObject = getQuestionIcon();
/*  58 */     getTopImageLabel().setIcon((Icon)localObject);
/*     */ 
/*  61 */     JPanel localJPanel = getContentArea();
/*     */ 
/*  64 */     JLabel localJLabel = new JLabel(this.m_resources.getString("wizard.warning.stayHere"));
/*  65 */     localJLabel.setHorizontalAlignment(0);
/*     */ 
/*  67 */     this.m_label = new JLabel();
/*  68 */     this.m_label.setHorizontalAlignment(0);
/*     */ 
/*  71 */     localJPanel.setLayout(new BorderLayout());
/*  72 */     localJPanel.add(localJLabel, "North");
/*  73 */     localJPanel.add(this.m_label, "Center");
/*     */   }
/*     */ 
/*     */   protected void stepShown()
/*     */   {
/*  84 */     disableCursor();
/*  85 */     getNextButton().setEnabled(false);
/*  86 */     getBackButton().setEnabled(false);
/*  87 */     getFinishButton().setEnabled(false);
/*  88 */     getCancelButton().setEnabled(false);
/*     */ 
/*  91 */     this.m_label.setText(centerLabelText(this.m_htmlText));
/*     */ 
/*  94 */     Thread localThread = createTransferThread();
/*  95 */     localThread.start();
/*     */   }
/*     */ 
/*     */   protected void nextRequest()
/*     */   {
/* 105 */     String str = getWizardSelections().getDeviceType();
/*     */     Class localClass;
/* 107 */     if ((str.equals("wizard.selections.SELECTION_DEVICE_PUMP")) || (str.equals("wizard.selections.SELECTION_DEVICE_CGM")))
/*     */     {
/* 109 */       localClass = TransferPumpDataCompleteStep.class;
/* 110 */     } else if (str.equals("wizard.selections.SELECTION_DEVICE_METER")) {
/* 111 */       localClass = TransferMeterDataCompleteStep.class;
/*     */     } else {
/* 113 */       Contract.unreachable();
/* 114 */       localClass = null;
/*     */     }
/*     */ 
/* 117 */     getWizard().showNextStep(localClass);
/*     */   }
/*     */ 
/*     */   private Thread createTransferThread()
/*     */   {
/* 127 */     1 local1 = new Runnable()
/*     */     {
/*     */       public void run()
/*     */       {
/*     */         try
/*     */         {
/* 134 */           long l1 = System.currentTimeMillis();
/*     */ 
/* 137 */           TransferDataStep.TransferResult localTransferResult = TransferDataStep.this.performTransfer();
/*     */ 
/* 140 */           if (localTransferResult.getResult() == 0)
/*     */           {
/* 142 */             long l2 = System.currentTimeMillis();
/* 143 */             TransferDataStep.this.logInfo("transfer succeeded in " + (l2 - l1) + " millis");
/*     */ 
/* 145 */             TransferDataStep.this.nextRequest();
/*     */           }
/* 148 */           else if (localTransferResult.getResult() == 2) {
/* 149 */             TransferDataStep.this.logInfo("transfer was cancelled by user");
/* 150 */             TransferDataStep.this.getWizard().setFailureReason("the data transfer was cancelled");
/* 151 */             TransferDataStep.this.getWizard().showNextStep(TransferDataFailStep.class);
/*     */           }
/* 153 */           else if (localTransferResult.getResult() == 1) {
/* 154 */             TransferDataStep.this.logInfo("transfer failed");
/* 155 */             TransferDataStep.this.getWizard().setFailureReason(localTransferResult.getReason());
/* 156 */             TransferDataStep.this.getWizard().showNextStep(TransferDataFailStep.class);
/*     */           } else {
/* 158 */             Contract.unreachable();
/*     */           }
/*     */         }
/*     */         catch (RuntimeException localRuntimeException)
/*     */         {
/* 163 */           localRuntimeException.printStackTrace(TransferDataStep.this.getWizard().getConfig().getLogWriter());
/* 164 */           TransferDataStep.this.getWizard().setFailureReason(TransferDataStep.this.m_unexpectedErrorMsg);
/* 165 */           TransferDataStep.this.getWizard().showNextStep(UnrecoverableErrorStep.class);
/*     */         } catch (NoClassDefFoundError localNoClassDefFoundError) {
/* 167 */           localNoClassDefFoundError.printStackTrace(TransferDataStep.this.getWizard().getConfig().getLogWriter());
/* 168 */           TransferDataStep.this.getWizard().setFailureReason(TransferDataStep.this.m_unexpectedErrorMsg);
/* 169 */           TransferDataStep.this.getWizard().showNextStep(UnrecoverableErrorStep.class);
/*     */         }
/*     */       }
/*     */     };
/* 174 */     Thread localThread = new Thread(local1);
/* 175 */     localThread.setName("Transfer");
/* 176 */     localThread.setDaemon(true);
/* 177 */     return localThread;
/*     */   }
/*     */ 
/*     */   private TransferResult performTransfer()
/*     */   {
/* 186 */     LogWriter localLogWriter = getWizard().getConfig().getLogWriter();
/* 187 */     Container localContainer = getWizard().getConfig().getContentPane();
/* 188 */     String str1 = getWizard().getConfig().getTransferRemoteURL();
/*     */ 
/* 190 */     logInfo("Send the following to " + str1);
/*     */ 
/* 192 */     DeviceOperationStep localDeviceOperationStep = (DeviceOperationStep)getWizard().getStep(DeviceOperationStep.class);
/*     */ 
/* 194 */     CaptureResult localCaptureResult = localDeviceOperationStep.getCaptureResult();
/* 195 */     long l1 = localCaptureResult.getServerTimeAtDeviceRead();
/* 196 */     long l2 = localCaptureResult.getClientTimeAtDeviceRead();
/* 197 */     String str2 = getWizard().getConfig().getClientTimeZoneID();
/* 198 */     byte[] arrayOfByte = localCaptureResult.getSnapshotData();
/* 199 */     boolean bool = getWizard().getConfig().getKeepSnapshotOnServer();
/*     */ 
/* 201 */     logInfo("Send server time " + l1);
/* 202 */     logInfo("Send client time " + l2);
/* 203 */     logInfo("Send client time zone id " + str2);
/* 204 */     logInfo("Send " + arrayOfByte.length + " bytes of snapshot data");
/* 205 */     if (bool) {
/* 206 */       logInfo("Request that the server keep the snapshot");
/*     */     }
/*     */ 
/* 210 */     TraceHistorySet localTraceHistorySet = localCaptureResult.getDevicePortReader().getTraceHistorySet();
/* 211 */     int i = localTraceHistorySet == null ? 0 : localTraceHistorySet.getTotalBytes();
/* 212 */     logInfo("Send " + i + " bytes of trace history data");
/*     */     String str3;
/* 215 */     if (getWizard().getConfig().getLogToServer())
/* 216 */       str3 = getWizard().getConfig().getLogWriter().getBackingStore();
/*     */     else {
/* 218 */       str3 = getWizard().getConfig().getNoLogMessage();
/*     */     }
/*     */ 
/* 221 */     logInfo("Send approximately " + str3.getBytes().length + " bytes of log messages");
/* 222 */     if (getWizard().getConfig().getLogToServer()) {
/* 223 */       str3 = localLogWriter.getBackingStore();
/*     */     }
/*     */ 
/*     */     try
/*     */     {
/* 230 */       NetHelper localNetHelper = new NetHelper();
/* 231 */       localNetHelper.uploadData(localContainer, str1, str3, l1, l2, str2, arrayOfByte, bool, localLogWriter, localTraceHistorySet);
/*     */     }
/*     */     catch (UploadFailedException localUploadFailedException) {
/* 234 */       localUploadFailedException.printStackTrace(localLogWriter);
/* 235 */       return new TransferResult(1, localUploadFailedException.getMessage());
/*     */     } catch (UploadCancelledException localUploadCancelledException) {
/* 237 */       return new TransferResult(2, "cancelled by user");
/*     */     }
/*     */ 
/* 240 */     TransferResult localTransferResult = new TransferResult(0, null);
/* 241 */     return localTransferResult;
/*     */   }
/*     */ 
/*     */   private static final class TransferResult extends OperationResult
/*     */   {
/*     */     private final Exception m_warning;
/*     */ 
/*     */     TransferResult(int paramInt, String paramString)
/*     */     {
/* 264 */       this(paramInt, paramString, null);
/*     */     }
/*     */ 
/*     */     TransferResult(int paramInt, String paramString, Exception paramException)
/*     */     {
/* 275 */       super(paramString);
/* 276 */       this.m_warning = paramException;
/*     */     }
/*     */ 
/*     */     Exception getWarning()
/*     */     {
/* 287 */       return this.m_warning;
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.steps.TransferDataStep
 * JD-Core Version:    0.6.0
 */