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
/*     */ import minimed.ddms.A.EA;
/*     */ import minimed.ddms.A.G;
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
/*     */     Object localObject;
/* 107 */     if ((str.equals("wizard.selections.SELECTION_DEVICE_PUMP")) || (str.equals("wizard.selections.SELECTION_DEVICE_CGM")))
/*     */     {
/* 109 */       localObject = TransferPumpDataCompleteStep.class;
/* 110 */     } else if (str.equals("wizard.selections.SELECTION_DEVICE_METER")) {
/* 111 */       localObject = TransferMeterDataCompleteStep.class;
/*     */     } else {
/* 113 */       Contract.unreachable();
/* 114 */       localObject = null;
/*     */     }
/*     */ 
/* 117 */     getWizard().showNextStep((Class)localObject);
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
/* 192 */     CaptureResult localCaptureResult = getWizard().getCaptureResult();
/* 193 */     long l1 = localCaptureResult.getServerTimeAtDeviceRead();
/* 194 */     long l2 = localCaptureResult.getClientTimeAtDeviceRead();
/* 195 */     String str2 = getWizard().getConfig().getClientTimeZoneID();
/* 196 */     byte[] arrayOfByte = localCaptureResult.getSnapshotData();
/* 197 */     boolean bool = getWizard().getConfig().getKeepSnapshotOnServer();
/*     */ 
/* 199 */     logInfo("Send server time " + l1);
/* 200 */     logInfo("Send client time " + l2);
/* 201 */     logInfo("Send client time zone id " + str2);
/* 202 */     logInfo("Send " + arrayOfByte.length + " bytes of snapshot data");
/* 203 */     if (bool) {
/* 204 */       logInfo("Request that the server keep the snapshot");
/*     */     }
/*     */ 
/* 208 */     EA localEA = null;
/* 209 */     if (localCaptureResult.getDevicePortReader() != null) {
/* 210 */       localEA = localCaptureResult.getDevicePortReader().J();
/*     */     }
/* 212 */     int i = localEA == null ? 0 : localEA.A();
/* 213 */     logInfo("Send " + i + " bytes of trace history data");
/*     */ 
/* 216 */     if (getWizard().getConfig().getLogToServer())
/* 217 */       str3 = getWizard().getConfig().getLogWriter().getBackingStore();
/*     */     else {
/* 219 */       str3 = getWizard().getConfig().getNoLogMessage();
/*     */     }
/*     */ 
/* 222 */     logInfo("Send approximately " + str3.getBytes().length + " bytes of log messages");
/* 223 */     if (getWizard().getConfig().getLogToServer()) {
/* 224 */       str3 = localLogWriter.getBackingStore();
/*     */     }
/* 226 */     String str4 = "snapshot file";
/* 227 */     String str5 = "";
/* 228 */     if (localCaptureResult.getDevicePortReader() != null) {
/* 229 */       str4 = localCaptureResult.getDevicePortReader().R();
/* 230 */       str5 = localCaptureResult.getDevicePortReader().S();
/*     */     }
/* 232 */     String str3 = str3 + ". DEVICE INFO: description=" + str4 + "; modelNumber=" + str5 + "; readTimeSeconds=" + localCaptureResult.getReadTimeSeconds() + "; retryCount=" + localCaptureResult.getRetryCount() + "; snapshotByteCount=" + arrayOfByte.length + ";";
/*     */     try
/*     */     {
/* 243 */       NetHelper localNetHelper = new NetHelper();
/* 244 */       localNetHelper.uploadData(localContainer, str1, str3, l1, l2, str2, arrayOfByte, bool, localLogWriter, localEA);
/*     */     }
/*     */     catch (UploadFailedException localUploadFailedException) {
/* 247 */       localUploadFailedException.printStackTrace(localLogWriter);
/* 248 */       return new TransferResult(1, localUploadFailedException.getMessage());
/*     */     } catch (UploadCancelledException localUploadCancelledException) {
/* 250 */       return new TransferResult(2, "cancelled by user");
/*     */     }
/*     */ 
/* 253 */     TransferResult localTransferResult = new TransferResult(0, null);
/* 254 */     return localTransferResult;
/*     */   }
/*     */ 
/*     */   private static final class TransferResult extends OperationResult
/*     */   {
/*     */     private final Exception m_warning;
/*     */ 
/*     */     TransferResult(int paramInt, String paramString)
/*     */     {
/* 277 */       this(paramInt, paramString, null);
/*     */     }
/*     */ 
/*     */     TransferResult(int paramInt, String paramString, Exception paramException)
/*     */     {
/* 288 */       super(paramString);
/* 289 */       this.m_warning = paramException;
/*     */     }
/*     */ 
/*     */     Exception getWarning()
/*     */     {
/* 300 */       return this.m_warning;
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.steps.TransferDataStep
 * JD-Core Version:    0.6.0
 */