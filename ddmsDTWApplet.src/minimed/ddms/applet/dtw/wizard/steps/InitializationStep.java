/*     */ package minimed.ddms.applet.dtw.wizard.steps;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.io.IOException;
/*     */ import java.text.MessageFormat;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.text.html.parser.ParserDelegator;
/*     */ import minimed.ddms.applet.dtw.DTWApplet;
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
/*  55 */   private final String m_msgPatternIncompatibleWithDdms = this.m_resources.getString("error.MSG_PATTERN_INCOMPATIBLE_WITH_DDMS");
/*     */ 
/*  61 */   private final String m_msgBadJavaToJavascriptComm = this.m_resources.getString("error.MSG_BAD_JAVA_TO_JAVASCRIPT_COMM");
/*     */   private final JLabel m_stepMsg;
/*     */   private Long m_serverTime;
/*     */ 
/*     */   public InitializationStep(Wizard paramWizard)
/*     */   {
/*  80 */     super(paramWizard, null);
/*     */ 
/*  83 */     new ParserDelegator();
/*     */ 
/*  86 */     getLeftBannerLabel().setText(this.m_resources.getString("wizard.init.message"));
/*  87 */     Object localObject = new ImageIcon(getImage("wizard.read.icon"));
/*  88 */     getRightBannerLabel().setIcon((Icon)localObject);
/*     */ 
/*  91 */     localObject = getInfoIcon();
/*  92 */     getTopImageLabel().setIcon((Icon)localObject);
/*     */ 
/*  95 */     this.m_stepMsg = new JLabel();
/*  96 */     this.m_stepMsg.setHorizontalAlignment(0);
/*  97 */     setMsgText(this.m_resources.getString("wizard.init.message2"));
/*     */ 
/* 100 */     JPanel localJPanel = getContentArea();
/* 101 */     localJPanel.setLayout(new BorderLayout());
/* 102 */     localJPanel.add(this.m_stepMsg, "Center");
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 113 */     PropertyWriter localPropertyWriter = new PropertyWriter(super.toString());
/* 114 */     localPropertyWriter.add("serverTime", this.m_serverTime);
/* 115 */     return localPropertyWriter.toString();
/*     */   }
/*     */ 
/*     */   protected void stepShown()
/*     */   {
/* 122 */     LogWriter localLogWriter = getWizard().getConfig().getLogWriter();
/*     */ 
/* 125 */     getNextButton().setEnabled(false);
/* 126 */     getBackButton().setEnabled(false);
/* 127 */     getFinishButton().setEnabled(false);
/* 128 */     getCancelButton().setEnabled(false);
/*     */ 
/* 130 */     disableCursor();
/*     */ 
/* 133 */     1 local1 = new Runnable(localLogWriter)
/*     */     {
/*     */       public void run()
/*     */       {
/*     */         try
/*     */         {
/* 141 */           if ((InitializationStep.this.compatibleWithProtocolCheck()) && (InitializationStep.this.compatibleWithDDMSCheck()) && (InitializationStep.this.recordDDMSTime()) && (InitializationStep.this.javaToJavascriptCheck()))
/*     */           {
/* 146 */             if (InitializationStep.this.isSerialDriverInstallNeeded())
/*     */             {
/* 148 */               InitializationStep.this.getWizard().showNextStep(SerialPortDLLInstallAuthorizationStep.class);
/*     */             }
/*     */             else {
/* 151 */               InitializationStep.this.getWizard().showNextStep(SelectDeviceStep.class);
/*     */             }
/*     */           }
/*     */           else
/* 155 */             InitializationStep.this.getWizard().showNextStep(InitializationFailStep.class);
/*     */         }
/*     */         catch (RuntimeException localRuntimeException) {
/* 158 */           localRuntimeException.printStackTrace(this.val$log);
/* 159 */           InitializationStep.this.getWizard().setFailureReason(InitializationStep.this.m_unexpectedErrorMsg);
/* 160 */           InitializationStep.this.getWizard().showNextStep(UnrecoverableErrorStep.class);
/*     */         }
/*     */       }
/*     */     };
/* 165 */     Thread localThread = new Thread(local1);
/* 166 */     localThread.setDaemon(true);
/* 167 */     localThread.setName("InitTests");
/* 168 */     localThread.start();
/*     */   }
/*     */ 
/*     */   public long getServerTime()
/*     */   {
/* 178 */     Contract.preNonNull(this.m_serverTime);
/* 179 */     return this.m_serverTime.longValue();
/*     */   }
/*     */ 
/*     */   private boolean javaToJavascriptCheck()
/*     */   {
/* 192 */     DTWApplet localDTWApplet = getWizard().getConfig().getApplet();
/* 193 */     LogWriter localLogWriter = getWizard().getConfig().getLogWriter();
/*     */ 
/* 196 */     int i = 0;
/*     */     try
/*     */     {
/* 202 */       localDTWApplet.enableUnloadMessage(true);
/* 203 */       localDTWApplet.enableUnloadMessage(false);
/* 204 */       i = 1;
/*     */     }
/*     */     catch (RuntimeException localRuntimeException) {
/* 207 */       localRuntimeException.printStackTrace(localLogWriter);
/* 208 */       getWizard().setFailureReason(this.m_msgBadJavaToJavascriptComm);
/*     */     }
/* 210 */     return i;
/*     */   }
/*     */ 
/*     */   private boolean isSerialDriverInstallNeeded()
/*     */   {
/* 219 */     return getWizard().isSerialDriverInstallNeeded();
/*     */   }
/*     */ 
/*     */   private boolean compatibleWithProtocolCheck()
/*     */   {
/* 228 */     LogWriter localLogWriter = getWizard().getConfig().getLogWriter();
/* 229 */     String str = getWizard().getConfig().getProtocol();
/* 230 */     localLogWriter.logInfo("Am I compatible with protocol " + str + "?...");
/*     */ 
/* 233 */     boolean bool = str.startsWith("http");
/* 234 */     if (bool) {
/* 235 */       localLogWriter.logInfo("yes!");
/*     */     } else {
/* 237 */       localLogWriter.logWarning("no!");
/* 238 */       getWizard().setFailureReason(this.m_msgIncompatibleWithProtocol);
/*     */     }
/*     */ 
/* 241 */     return bool;
/*     */   }
/*     */ 
/*     */   private boolean compatibleWithDDMSCheck()
/*     */   {
/* 250 */     LogWriter localLogWriter = getWizard().getConfig().getLogWriter();
/* 251 */     String str1 = getWizard().getConfig().getClientSysVer();
/* 252 */     String str2 = getWizard().getConfig().getServerSysVer();
/*     */ 
/* 254 */     localLogWriter.logInfo("Is my version " + str1 + " compatible with server version " + str2 + "?...");
/*     */ 
/* 256 */     boolean bool = str1.equals(str2);
/*     */ 
/* 258 */     if (bool) {
/* 259 */       localLogWriter.logInfo("yes!");
/*     */     } else {
/* 261 */       localLogWriter.logWarning("no!");
/* 262 */       String[] arrayOfString = { str1, str2 };
/* 263 */       MessageFormat localMessageFormat = new MessageFormat(this.m_msgPatternIncompatibleWithDdms);
/* 264 */       getWizard().setFailureReason(localMessageFormat.format(arrayOfString));
/*     */     }
/* 266 */     return bool;
/*     */   }
/*     */ 
/*     */   private boolean recordDDMSTime()
/*     */   {
/* 275 */     int i = 0;
/* 276 */     String str = getWizard().getConfig().getTransferRemoteURL();
/* 277 */     LogWriter localLogWriter = getWizard().getConfig().getLogWriter();
/*     */ 
/* 279 */     localLogWriter.logInfo("Recording server time...");
/* 280 */     NetHelper localNetHelper = new NetHelper();
/*     */     try
/*     */     {
/* 284 */       this.m_serverTime = new Long(localNetHelper.getServerTime(str));
/* 285 */       localLogWriter.logInfo("Server time is " + this.m_serverTime);
/* 286 */       i = 1;
/*     */     }
/*     */     catch (IOException localIOException)
/*     */     {
/* 297 */       localIOException.printStackTrace(localLogWriter);
/* 298 */       getWizard().setFailureReason(this.m_msgRecordDdmsTimeFailed);
/*     */     }
/*     */ 
/* 301 */     return i;
/*     */   }
/*     */ 
/*     */   private void setMsgText(String paramString)
/*     */   {
/* 310 */     this.m_stepMsg.setText(centerLabelText(paramString));
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.steps.InitializationStep
 * JD-Core Version:    0.6.0
 */