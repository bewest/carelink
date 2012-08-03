/*     */ package minimed.ddms.applet.dtw.wizard.steps;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.SwingUtilities;
/*     */ import minimed.ddms.applet.dtw.DiskHelper;
/*     */ import minimed.ddms.applet.dtw.ExeHelper;
/*     */ import minimed.ddms.applet.dtw.LogWriter;
/*     */ import minimed.ddms.applet.dtw.MessageHelper;
/*     */ import minimed.ddms.applet.dtw.MessageListener;
/*     */ import minimed.ddms.applet.dtw.OperationResult;
/*     */ import minimed.ddms.applet.dtw.SaveFailedException;
/*     */ import minimed.ddms.applet.dtw.wizard.Wizard;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardConfig;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardConfig.DriverConfig;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardStep;
/*     */ import minimed.util.Contract;
/*     */ import minimed.util.OSInfo;
/*     */ 
/*     */ public abstract class DriverInstallAuthorizationStep extends WizardStep
/*     */   implements MessageListener
/*     */ {
/*     */   private static final String JAR_PATH_SEPARATOR = "/";
/*     */   private static final int STATUS_OK = 0;
/*     */   private static final int STATUS_ERROR = 1;
/*     */   private final JLabel m_label;
/*     */ 
/*     */   protected DriverInstallAuthorizationStep(Wizard paramWizard, Class<?> paramClass)
/*     */   {
/*  55 */     super(paramWizard, paramClass);
/*  56 */     Contract.preNonNull(paramClass);
/*     */ 
/*  59 */     getLeftBannerLabel().setText(this.m_resources.getString("wizard.driver.installneeded"));
/*     */ 
/*  62 */     this.m_label = new JLabel();
/*  63 */     this.m_label.setHorizontalAlignment(0);
/*     */ 
/*  66 */     getContentArea().setLayout(new BorderLayout());
/*  67 */     getContentArea().add(this.m_label, "Center");
/*     */   }
/*     */ 
/*     */   public final void messageReceived(String paramString)
/*     */   {
/*  81 */     SwingUtilities.invokeLater(new Runnable(paramString)
/*     */     {
/*     */       public void run()
/*     */       {
/*  86 */         DriverInstallAuthorizationStep.this.m_label.setText(this.val$message);
/*     */       }
/*     */     });
/*     */   }
/*     */ 
/*     */   public abstract String getDeviceName();
/*     */ 
/*     */   protected void stepShown()
/*     */   {
/* 100 */     super.stepShown();
/*     */ 
/* 103 */     if (OSInfo.isMac()) {
/* 104 */       nextRequest();
/*     */     }
/*     */ 
/* 107 */     setMainText();
/*     */ 
/* 110 */     getNextButton().setEnabled(true);
/* 111 */     getBackButton().setEnabled(true);
/* 112 */     getFinishButton().setEnabled(false);
/* 113 */     getCancelButton().setEnabled(false);
/*     */   }
/*     */ 
/*     */   protected void nextRequest()
/*     */   {
/* 120 */     if (!OSInfo.isMac()) {
/* 121 */       isUserRunningAsAdmin();
/*     */     }
/*     */ 
/* 125 */     getNextButton().setEnabled(false);
/* 126 */     getBackButton().setEnabled(false);
/*     */ 
/* 128 */     LogWriter localLogWriter = getWizard().getConfig().getLogWriter();
/* 129 */     DriverInstallAuthorizationStep localDriverInstallAuthorizationStep = this;
/* 130 */     2 local2 = new Runnable(localDriverInstallAuthorizationStep, localLogWriter)
/*     */     {
/*     */       public void run()
/*     */       {
/*     */         try
/*     */         {
/* 137 */           OperationResult localOperationResult = DriverInstallAuthorizationStep.this.performInstall(this.val$listener, this.val$log);
/* 138 */           if (localOperationResult.getResult() == 0) {
/* 139 */             this.val$log.println("install succeeded");
/* 140 */             DriverInstallAuthorizationStep.this.getWizard().showNextStep(DriverInstallAuthorizationStep.this.getNextClass());
/*     */           }
/* 142 */           else if (localOperationResult.getResult() == 1) {
/* 143 */             String str = localOperationResult.getReason();
/* 144 */             this.val$log.println("install failed because " + str);
/* 145 */             str = MessageHelper.format(DriverInstallAuthorizationStep.this.m_resources.getString("wizard.driver.failed"), new Object[] { str });
/*     */ 
/* 148 */             DriverInstallAuthorizationStep.this.getWizard().setFailureReason(str);
/* 149 */             DriverInstallAuthorizationStep.this.getWizard().showNextStep(UnrecoverableErrorStep.class);
/*     */           } else {
/* 151 */             Contract.unreachable();
/*     */           }
/*     */         }
/*     */         catch (RuntimeException localRuntimeException) {
/* 155 */           localRuntimeException.printStackTrace(this.val$log);
/* 156 */           DriverInstallAuthorizationStep.this.getWizard().setFailureReason(DriverInstallAuthorizationStep.this.m_unexpectedErrorMsg);
/* 157 */           DriverInstallAuthorizationStep.this.getWizard().showNextStep(UnrecoverableErrorStep.class);
/*     */         } catch (NoClassDefFoundError localNoClassDefFoundError) {
/* 159 */           localNoClassDefFoundError.printStackTrace(this.val$log);
/* 160 */           DriverInstallAuthorizationStep.this.getWizard().setFailureReason(DriverInstallAuthorizationStep.this.m_unexpectedErrorMsg);
/* 161 */           DriverInstallAuthorizationStep.this.getWizard().showNextStep(UnrecoverableErrorStep.class);
/*     */         }
/*     */       }
/*     */     };
/* 165 */     Thread localThread = new Thread(local2);
/* 166 */     localThread.setDaemon(true);
/* 167 */     localThread.setName("DriverInstall");
/* 168 */     localThread.start();
/*     */   }
/*     */ 
/*     */   protected abstract WizardConfig.DriverConfig getDriverConfig();
/*     */ 
/*     */   protected abstract String getMainText();
/*     */ 
/*     */   protected JLabel getMainTextLabel()
/*     */   {
/* 191 */     return this.m_label;
/*     */   }
/*     */ 
/*     */   protected String getInstallText()
/*     */   {
/* 200 */     String str = MessageHelper.format(this.m_resources.getString("wizard.driver.install.executing"), new Object[] { getDeviceName() });
/*     */ 
/* 203 */     return str;
/*     */   }
/*     */ 
/*     */   private void setMainText()
/*     */   {
/* 210 */     String str = "<html>" + getMainText() + "</html>";
/*     */ 
/* 213 */     if (OSInfo.isMac())
/* 214 */       this.m_label.setText("");
/*     */     else
/* 216 */       this.m_label.setText(str);
/*     */   }
/*     */ 
/*     */   private OperationResult performInstall(MessageListener paramMessageListener, LogWriter paramLogWriter)
/*     */   {
/* 229 */     disableCursor();
/*     */ 
/* 231 */     DiskHelper localDiskHelper = new DiskHelper();
/*     */ 
/* 235 */     String str1 = getDriverConfig().getInstallDriverDir();
/* 236 */     paramMessageListener.messageReceived(MessageHelper.format(this.m_resources.getString("wizard.driver.install.preparing"), new Object[] { localDiskHelper.getViewableName(str1) }));
/*     */ 
/* 240 */     File localFile = new File(str1);
/* 241 */     if ((!localFile.exists()) && (!localFile.mkdirs())) {
/* 242 */       return new OperationResult(1, MessageHelper.format(this.m_resources.getString("wizard.driver.install.createfailed"), new Object[] { localDiskHelper.getViewableName(str1) }));
/*     */     }
/*     */ 
/* 252 */     String[] arrayOfString1 = getDriverConfig().getInstallDriverFiles();
/*     */     Object localObject2;
/*     */     String str5;
/* 253 */     for (int i = 0; i < arrayOfString1.length; i++) {
/* 254 */       paramMessageListener.messageReceived(MessageHelper.format(this.m_resources.getString("wizard.driver.install.copying"), new Object[] { arrayOfString1[i] }));
/*     */ 
/* 257 */       str2 = getDriverConfig().getInstallDriverJarLoc() + arrayOfString1[i];
/* 258 */       paramLogWriter.logInfo("Extracting " + str2 + "...");
/* 259 */       InputStream localInputStream = getClass().getResourceAsStream(str2);
/* 260 */       localObject1 = new ByteArrayOutputStream();
/*     */       try {
/* 262 */         localDiskHelper.writeToStream(localInputStream, (OutputStream)localObject1);
/*     */       } catch (IOException localIOException1) {
/* 264 */         localIOException1.printStackTrace(paramLogWriter);
/* 265 */         return new OperationResult(1, localIOException1.getMessage());
/*     */       }
/* 267 */       localObject2 = ((ByteArrayOutputStream)localObject1).toByteArray();
/*     */       try
/*     */       {
/* 270 */         String str3 = arrayOfString1[i];
/* 271 */         str5 = "";
/* 272 */         int k = str3.indexOf("/");
/* 273 */         if (k > -1)
/*     */         {
/* 275 */           localObject3 = str3.split("/");
/* 276 */           str5 = File.separator + localObject3[0];
/* 277 */           str3 = localObject3[1];
/*     */         }
/* 279 */         Object localObject3 = localDiskHelper.saveFile(str1 + str5, str3, localObject2, paramLogWriter);
/* 280 */         paramLogWriter.println("created " + ((File)localObject3).getCanonicalPath());
/*     */       } catch (SaveFailedException localSaveFailedException) {
/* 282 */         localSaveFailedException.printStackTrace(paramLogWriter);
/* 283 */         return new OperationResult(1, localSaveFailedException.getMessage());
/*     */       }
/*     */       catch (IOException localIOException2) {
/* 286 */         localIOException2.printStackTrace(paramLogWriter);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 291 */     String[] arrayOfString2 = getDriverConfig().getInstallDriverCommand();
/* 292 */     paramMessageListener.messageReceived(getInstallText());
/* 293 */     String str2 = arrayOfString2[0];
/*     */ 
/* 295 */     str2 = str2.substring(str2.lastIndexOf(File.separator) + 1);
/* 296 */     for (int j = 1; j < arrayOfString2.length; j++) {
/* 297 */       str2 = str2 + " " + arrayOfString2[j];
/*     */     }
/* 299 */     paramLogWriter.println(str2);
/* 300 */     j = 1;
/* 301 */     Object localObject1 = new ExeHelper();
/*     */ 
/* 304 */     if (getWizard().getConfig().isWindowsVista())
/* 305 */       localObject2 = this.m_resources.getString("wizard.driver.install.vista") + "<br>";
/*     */     else {
/* 307 */       localObject2 = this.m_resources.getString("wizard.driver.install.administrator") + "<br>";
/*     */     }
/*     */     try
/*     */     {
/* 311 */       j = ((ExeHelper)localObject1).execute(arrayOfString2, localFile);
/*     */     }
/*     */     catch (IOException localIOException3) {
/* 314 */       return new OperationResult(1, (String)localObject2 + localIOException3.getMessage());
/*     */     } catch (InterruptedException localInterruptedException) {
/* 316 */       localInterruptedException.printStackTrace(paramLogWriter);
/* 317 */       return new OperationResult(1, localInterruptedException.getMessage());
/*     */     }
/*     */ 
/* 321 */     if (j != 0)
/*     */     {
/* 324 */       String str4 = MessageHelper.format(this.m_resources.getString("wizard.driver.install.failed"), new Object[] { (String)localObject2 + str2, String.valueOf(j) });
/*     */ 
/* 329 */       str5 = "";
/* 330 */       String str6 = getDeviceName();
/*     */ 
/* 332 */       if (str6.length() > 0) {
/* 333 */         str5 = "<br><br>" + MessageHelper.format(this.m_resources.getString("wizard.driver.install.disconnect"), new Object[] { str6 });
/*     */       }
/*     */ 
/* 337 */       return new OperationResult(1, str4 + str5);
/*     */     }
/*     */ 
/* 343 */     paramLogWriter.println("Driver install complete into " + localFile);
/* 344 */     return (OperationResult)(OperationResult)(OperationResult)new OperationResult(0, null);
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.steps.DriverInstallAuthorizationStep
 * JD-Core Version:    0.6.0
 */