/*     */ package minimed.ddms.applet.dtw.wizard.steps;
/*     */ 
/*     */ import Serialio.SerialPortLocal;
/*     */ import java.awt.BorderLayout;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.text.MessageFormat;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import minimed.ddms.applet.dtw.DTWApplet;
/*     */ import minimed.ddms.applet.dtw.DiskHelper;
/*     */ import minimed.ddms.applet.dtw.InstallFileVersion;
/*     */ import minimed.ddms.applet.dtw.InstallTestResult;
/*     */ import minimed.ddms.applet.dtw.LogWriter;
/*     */ import minimed.ddms.applet.dtw.NetHelper;
/*     */ import minimed.ddms.applet.dtw.PropertyWriter;
/*     */ import minimed.ddms.applet.dtw.wizard.Wizard;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardConfig;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardStep;
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ public class InitializationStep extends WizardStep
/*     */ {
/*  43 */   private final String m_msgIncompatibleWithProtocol = this.m_resources.getString("error.MSG_INCOMPATIBLE_WITH_PROTOCOL");
/*     */ 
/*  49 */   private final String m_msgRecordDdmsTimeFailed = this.m_resources.getString("error.MSG_RECORD_DDMS_TIME_FAILED");
/*     */ 
/*  55 */   private final String m_msgNativeLibrarySanityCheckFailed = this.m_resources.getString("error.MSG_NATIVE_LIBRARY_SANITY_CHECK_FAILED");
/*     */ 
/*  61 */   private final String m_msgPatternIncompatibleWithDdms = this.m_resources.getString("error.MSG_PATTERN_INCOMPATIBLE_WITH_DDMS");
/*     */ 
/*  67 */   private final String m_msgBadJavaToJavascriptComm = this.m_resources.getString("error.MSG_BAD_JAVA_TO_JAVASCRIPT_COMM");
/*     */   private final JLabel m_stepMsg;
/*     */   private InstallTestResult m_installSerialPortDLLTestResult;
/*     */   private Long m_serverTime;
/*     */ 
/*     */   public InitializationStep(Wizard paramWizard)
/*     */   {
/*  91 */     super(paramWizard, null);
/*     */ 
/*  93 */     getLeftBannerLabel().setText(this.m_resources.getString("wizard.init.message"));
/*  94 */     Object localObject = new ImageIcon(getImage("wizard.read.icon"));
/*  95 */     getRightBannerLabel().setIcon((Icon)localObject);
/*     */ 
/*  98 */     localObject = getInfoIcon();
/*  99 */     getTopImageLabel().setIcon((Icon)localObject);
/*     */ 
/* 102 */     this.m_stepMsg = new JLabel();
/* 103 */     this.m_stepMsg.setHorizontalAlignment(0);
/* 104 */     setMsgText(this.m_resources.getString("wizard.init.message2"));
/*     */ 
/* 107 */     JPanel localJPanel = getContentArea();
/* 108 */     localJPanel.setLayout(new BorderLayout());
/* 109 */     localJPanel.add(this.m_stepMsg, "Center");
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 120 */     PropertyWriter localPropertyWriter = new PropertyWriter(super.toString());
/* 121 */     localPropertyWriter.add("serverTime", this.m_serverTime);
/* 122 */     localPropertyWriter.add("installSerialPortDLLTestResult", this.m_installSerialPortDLLTestResult);
/* 123 */     return localPropertyWriter.toString();
/*     */   }
/*     */ 
/*     */   protected void stepShown()
/*     */   {
/* 130 */     this.m_installSerialPortDLLTestResult = null;
/* 131 */     LogWriter localLogWriter = getWizard().getConfig().getLogWriter();
/*     */ 
/* 134 */     getNextButton().setEnabled(false);
/* 135 */     getBackButton().setEnabled(false);
/* 136 */     getFinishButton().setEnabled(false);
/* 137 */     getCancelButton().setEnabled(false);
/*     */ 
/* 139 */     disableCursor();
/*     */ 
/* 142 */     Runnable local1 = new Runnable(localLogWriter)
/*     */     {
/*     */       private final LogWriter log;
/*     */ 
/*     */       public void run()
/*     */       {
/*     */         try {
/* 150 */           if ((InitializationStep.this.compatibleWithProtocolCheck()) && (InitializationStep.this.compatibleWithDDMSCheck()) && (InitializationStep.this.recordDDMSTime()) && (InitializationStep.this.javaToJavascriptCheck()))
/*     */           {
/* 155 */             if (InitializationStep.this.serialPortDLLInstallCheck())
/*     */             {
/* 157 */               InitializationStep.this.getWizard().showNextStep(SerialPortDLLInstallAuthorizationStep.class);
/*     */             }
/* 160 */             else if (InitializationStep.this.nativeLibrarySanityCheck())
/*     */             {
/* 162 */               InitializationStep.this.getWizard().showNextStep(SelectDeviceStep.class);
/*     */             }
/*     */             else {
/* 165 */               InitializationStep.this.getWizard().showNextStep(InitializationFailStep.class);
/*     */             }
/*     */           }
/*     */           else
/*     */           {
/* 170 */             InitializationStep.this.getWizard().showNextStep(InitializationFailStep.class);
/*     */           }
/*     */         } catch (RuntimeException localRuntimeException) {
/* 173 */           localRuntimeException.printStackTrace(this.log);
/* 174 */           InitializationStep.this.getWizard().setFailureReason(InitializationStep.this.m_unexpectedErrorMsg);
/* 175 */           InitializationStep.this.getWizard().showNextStep(UnrecoverableErrorStep.class);
/*     */         }
/*     */       }
/*     */     };
/* 180 */     Thread localThread = new Thread(local1);
/* 181 */     localThread.setDaemon(true);
/* 182 */     localThread.setName("InitTests");
/* 183 */     localThread.start();
/*     */   }
/*     */ 
/*     */   public long getServerTime()
/*     */   {
/* 193 */     Contract.preNonNull(this.m_serverTime);
/* 194 */     return this.m_serverTime.longValue();
/*     */   }
/*     */ 
/*     */   public InstallTestResult getInstallSerialPortDLLTestResult()
/*     */   {
/* 204 */     Contract.preNonNull(this.m_installSerialPortDLLTestResult);
/* 205 */     return this.m_installSerialPortDLLTestResult;
/*     */   }
/*     */ 
/*     */   private void setInstallSerialPortDLLTestResult(InstallTestResult paramInstallTestResult)
/*     */   {
/* 216 */     Contract.pre(this.m_installSerialPortDLLTestResult == null);
/* 217 */     Contract.preNonNull(paramInstallTestResult);
/* 218 */     this.m_installSerialPortDLLTestResult = paramInstallTestResult;
/*     */   }
/*     */ 
/*     */   private boolean javaToJavascriptCheck()
/*     */   {
/* 231 */     DTWApplet localDTWApplet = getWizard().getConfig().getApplet();
/* 232 */     LogWriter localLogWriter = getWizard().getConfig().getLogWriter();
/*     */ 
/* 235 */     int i = 0;
/*     */     try
/*     */     {
/* 241 */       localDTWApplet.enableUnloadMessage(true);
/* 242 */       localDTWApplet.enableUnloadMessage(false);
/* 243 */       i = 1;
/*     */     }
/*     */     catch (RuntimeException localRuntimeException) {
/* 246 */       localRuntimeException.printStackTrace(localLogWriter);
/* 247 */       getWizard().setFailureReason(this.m_msgBadJavaToJavascriptComm);
/*     */     }
/* 249 */     return i == 1;
/*     */   }
/*     */ 
/*     */   private boolean nativeLibrarySanityCheck()
/*     */   {
/* 263 */     int i = 0;
/*     */     try {
/* 265 */       SerialPortLocal.getPortList();
/* 266 */       i = 1;
/*     */     }
/*     */     catch (IOException localIOException) {
/* 269 */       i = 1;
/*     */     }
/*     */     catch (UnsatisfiedLinkError localUnsatisfiedLinkError) {
/* 272 */       getWizard().setFailureReason(this.m_msgNativeLibrarySanityCheckFailed);
/*     */     }
/* 274 */     return i == 1;
/*     */   }
/*     */ 
/*     */   private boolean compatibleWithProtocolCheck()
/*     */   {
/* 283 */     LogWriter localLogWriter = getWizard().getConfig().getLogWriter();
/* 284 */     String str = getWizard().getConfig().getProtocol();
/* 285 */     localLogWriter.logInfo("Am I compatible with protocol " + str + "?...");
/*     */ 
/* 288 */     boolean bool = str.startsWith("http");
/* 289 */     if (bool) {
/* 290 */       localLogWriter.logInfo("yes!");
/*     */     } else {
/* 292 */       localLogWriter.logWarning("no!");
/* 293 */       getWizard().setFailureReason(this.m_msgIncompatibleWithProtocol);
/*     */     }
/*     */ 
/* 296 */     return bool;
/*     */   }
/*     */ 
/*     */   private boolean compatibleWithDDMSCheck()
/*     */   {
/* 305 */     LogWriter localLogWriter = getWizard().getConfig().getLogWriter();
/* 306 */     String str1 = getWizard().getConfig().getClientSysVer();
/* 307 */     String str2 = getWizard().getConfig().getServerSysVer();
/*     */ 
/* 309 */     localLogWriter.logInfo("Is my version " + str1 + " compatible with server version " + str2 + "?...");
/*     */ 
/* 311 */     boolean bool = str1.equals(str2);
/*     */ 
/* 313 */     if (bool) {
/* 314 */       localLogWriter.logInfo("yes!");
/*     */     } else {
/* 316 */       localLogWriter.logWarning("no!");
/* 317 */       String[] arrayOfString = { str1, str2 };
/* 318 */       MessageFormat localMessageFormat = new MessageFormat(this.m_msgPatternIncompatibleWithDdms);
/* 319 */       getWizard().setFailureReason(localMessageFormat.format(arrayOfString));
/*     */     }
/* 321 */     return bool;
/*     */   }
/*     */ 
/*     */   private boolean recordDDMSTime()
/*     */   {
/* 330 */     int i = 0;
/* 331 */     String str = getWizard().getConfig().getTransferRemoteURL();
/* 332 */     LogWriter localLogWriter = getWizard().getConfig().getLogWriter();
/*     */ 
/* 334 */     localLogWriter.logInfo("Recording server time...");
/* 335 */     NetHelper localNetHelper = new NetHelper();
/*     */     try
/*     */     {
/* 339 */       this.m_serverTime = new Long(localNetHelper.getServerTime(str));
/* 340 */       localLogWriter.logInfo("Server time is " + this.m_serverTime);
/* 341 */       i = 1;
/*     */     }
/*     */     catch (IOException localIOException)
/*     */     {
/* 352 */       localIOException.printStackTrace(localLogWriter);
/* 353 */       getWizard().setFailureReason(this.m_msgRecordDdmsTimeFailed);
/*     */     }
/*     */ 
/* 356 */     return i;
/*     */   }
/*     */ 
/*     */   private boolean serialPortDLLInstallCheck()
/*     */   {
/* 365 */     int i = 0;
/*     */ 
/* 367 */     InstallTestResult localInstallTestResult = performSerialPortDLLInstallTest();
/* 368 */     setInstallSerialPortDLLTestResult(localInstallTestResult);
/* 369 */     if (localInstallTestResult.getResult() == 1) {
/* 370 */       i = 1;
/* 371 */       getWizard().getConfig().getLogWriter().logWarning("install is required");
/*     */     } else {
/* 373 */       getWizard().getConfig().getLogWriter().logInfo("install not required");
/*     */     }
/*     */ 
/* 376 */     return i;
/*     */   }
/*     */ 
/*     */   private InstallTestResult performSerialPortDLLInstallTest()
/*     */   {
/* 386 */     String str1 = getWizard().getConfig().getInstallSerialPortDLLDir();
/* 387 */     String str2 = getWizard().getConfig().getInstallSerialPortDLL();
/* 388 */     String str3 = getWizard().getConfig().getInstallSerialPortDLLDigest();
/* 389 */     String str4 = getWizard().getConfig().getInstallMessageDigestAlgorithm();
/* 390 */     String str5 = "serial port driver";
/*     */ 
/* 392 */     DiskHelper localDiskHelper = new DiskHelper();
/*     */ 
/* 394 */     int i = !localDiskHelper.doesFileExist(str1, str2) ? 1 : 0;
/* 395 */     if (i != 0) {
/* 396 */       str1 = System.getProperty("java.home") + File.separator + "bin";
/* 397 */       i = !localDiskHelper.doesFileExist(str1, str2) ? 1 : 0;
/*     */     }
/*     */ 
/* 400 */     int j = (i == 0) && (localDiskHelper.doesFileDigestMatch(str1, str2, str3, str4, getWizard().getConfig().getLogWriter())) ? 1 : 0;
/*     */ 
/* 404 */     int k = 0;
/* 405 */     if (i != 0) {
/* 406 */       getWizard().getConfig().getLogWriter().logWarning(str2 + " is missing");
/* 407 */       k = 1;
/*     */     }
/*     */ 
/* 410 */     if ((i == 0) && (j == 0)) {
/* 411 */       getWizard().getConfig().getLogWriter().logWarning(str2 + " is not the latest version");
/* 412 */       k = 1;
/*     */     }
/*     */ 
/* 415 */     if (k != 0) {
/* 416 */       InstallFileVersion localInstallFileVersion = new InstallFileVersion(str2, str5);
/* 417 */       InstallTestResult localInstallTestResult = new InstallTestResult(1, localInstallFileVersion);
/*     */ 
/* 419 */       return localInstallTestResult;
/*     */     }
/*     */ 
/* 422 */     return new InstallTestResult(0);
/*     */   }
/*     */ 
/*     */   private void setMsgText(String paramString)
/*     */   {
/* 432 */     this.m_stepMsg.setText(centerLabelText(paramString));
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.steps.InitializationStep
 * JD-Core Version:    0.6.0
 */