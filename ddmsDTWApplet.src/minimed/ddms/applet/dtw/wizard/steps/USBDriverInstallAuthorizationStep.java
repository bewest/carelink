/*     */ package minimed.ddms.applet.dtw.wizard.steps;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.ImageIcon;
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
/*     */ import minimed.ddms.applet.dtw.wizard.WizardConfig.USBConfig;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardStep;
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ public abstract class USBDriverInstallAuthorizationStep extends WizardStep
/*     */   implements MessageListener
/*     */ {
/*     */   private static final int STATUS_OK = 0;
/*     */   private static final int STATUS_ERROR = 1;
/*     */   private final JLabel m_label;
/*     */ 
/*     */   protected USBDriverInstallAuthorizationStep(Wizard paramWizard, Class paramClass)
/*     */   {
/*  57 */     super(paramWizard, paramClass);
/*     */ 
/*  60 */     getLeftBannerLabel().setText(this.m_resources.getString("wizard.usb.installneeded"));
/*  61 */     Object localObject = new ImageIcon(getImage("wizard.read.icon"));
/*  62 */     getRightBannerLabel().setIcon((Icon)localObject);
/*     */ 
/*  65 */     localObject = getQuestionIcon();
/*  66 */     getTopImageLabel().setIcon((Icon)localObject);
/*     */ 
/*  69 */     this.m_label = new JLabel();
/*  70 */     this.m_label.setHorizontalAlignment(0);
/*     */ 
/*  72 */     getContentArea().setLayout(new BorderLayout());
/*  73 */     getContentArea().add(this.m_label, "Center");
/*     */   }
/*     */ 
/*     */   public final void messageReceived(String paramString)
/*     */   {
/*  86 */     SwingUtilities.invokeLater(new Runnable(paramString) {
/*     */       private final String val$message;
/*     */ 
/*     */       public void run() {
/*  91 */         USBDriverInstallAuthorizationStep.this.m_label.setText(this.val$message);
/*     */       }
/*     */     });
/*     */   }
/*     */ 
/*     */   protected final void stepShown()
/*     */   {
/* 100 */     super.stepShown();
/* 101 */     setMainText();
/*     */ 
/* 104 */     getNextButton().setEnabled(true);
/* 105 */     getBackButton().setEnabled(true);
/* 106 */     getFinishButton().setEnabled(false);
/* 107 */     getCancelButton().setEnabled(false);
/*     */   }
/*     */ 
/*     */   protected final void nextRequest()
/*     */   {
/* 118 */     getNextButton().setEnabled(false);
/* 119 */     getBackButton().setEnabled(false);
/*     */ 
/* 121 */     LogWriter localLogWriter = getWizard().getConfig().getLogWriter();
/* 122 */     USBDriverInstallAuthorizationStep localUSBDriverInstallAuthorizationStep = this;
/* 123 */     2 local2 = new Runnable(localUSBDriverInstallAuthorizationStep, localLogWriter) {
/*     */       private final MessageListener val$listener;
/*     */       private final LogWriter val$log;
/*     */ 
/*     */       public void run() {
/*     */         try { OperationResult localOperationResult = USBDriverInstallAuthorizationStep.this.performInstall(this.val$listener, this.val$log);
/* 131 */           if (localOperationResult.getResult() == 0) {
/* 132 */             this.val$log.println("install succeeded");
/* 133 */             USBDriverInstallAuthorizationStep.this.getWizard().showNextStep(USBDriverInstallAuthorizationStep.this.getNextClass());
/*     */           }
/* 135 */           else if (localOperationResult.getResult() == 1) {
/* 136 */             String str = localOperationResult.getReason();
/* 137 */             this.val$log.println("install failed because " + str);
/* 138 */             str = MessageHelper.format(USBDriverInstallAuthorizationStep.this.m_resources.getString("wizard.serial.failed"), new Object[] { str });
/*     */ 
/* 141 */             USBDriverInstallAuthorizationStep.this.getWizard().setFailureReason(str);
/* 142 */             USBDriverInstallAuthorizationStep.this.getWizard().showNextStep(UnrecoverableErrorStep.class);
/*     */           } else {
/* 144 */             Contract.unreachable();
/*     */           }
/*     */         } catch (RuntimeException localRuntimeException)
/*     */         {
/* 148 */           localRuntimeException.printStackTrace(this.val$log);
/* 149 */           USBDriverInstallAuthorizationStep.this.getWizard().setFailureReason(USBDriverInstallAuthorizationStep.this.m_unexpectedErrorMsg);
/* 150 */           USBDriverInstallAuthorizationStep.this.getWizard().showNextStep(UnrecoverableErrorStep.class);
/*     */         } catch (NoClassDefFoundError localNoClassDefFoundError) {
/* 152 */           localNoClassDefFoundError.printStackTrace(this.val$log);
/* 153 */           USBDriverInstallAuthorizationStep.this.getWizard().setFailureReason(USBDriverInstallAuthorizationStep.this.m_unexpectedErrorMsg);
/* 154 */           USBDriverInstallAuthorizationStep.this.getWizard().showNextStep(UnrecoverableErrorStep.class);
/*     */         }
/*     */       }
/*     */     };
/* 158 */     Thread localThread = new Thread(local2);
/* 159 */     localThread.setDaemon(true);
/* 160 */     localThread.setName("USBInstall");
/* 161 */     localThread.start();
/*     */   }
/*     */ 
/*     */   protected abstract WizardConfig.USBConfig getUSBConfig();
/*     */ 
/*     */   protected abstract String getMainText();
/*     */ 
/*     */   protected abstract String getInstallText();
/*     */ 
/*     */   private void setMainText()
/*     */   {
/* 189 */     String str = "<html>" + getMainText() + "</html>";
/* 190 */     this.m_label.setText(str);
/*     */   }
/*     */ 
/*     */   private OperationResult performInstall(MessageListener paramMessageListener, LogWriter paramLogWriter)
/*     */   {
/* 202 */     disableCursor();
/*     */ 
/* 204 */     DiskHelper localDiskHelper = new DiskHelper();
/*     */ 
/* 208 */     String str1 = getUSBConfig().getInstallUSBDriverDir();
/* 209 */     paramMessageListener.messageReceived(MessageHelper.format(this.m_resources.getString("wizard.usb.install.preparing"), new Object[] { localDiskHelper.getViewableName(str1) }));
/*     */ 
/* 213 */     File localFile1 = new File(str1);
/* 214 */     if ((!localFile1.exists()) && (!localFile1.mkdirs())) {
/* 215 */       return new OperationResult(1, MessageHelper.format(this.m_resources.getString("wizard.usb.install.createfailed"), new Object[] { localDiskHelper.getViewableName(str1) }));
/*     */     }
/*     */ 
/* 225 */     String[] arrayOfString1 = getUSBConfig().getInstallUSBDriverFiles();
/* 226 */     for (int i = 0; i < arrayOfString1.length; i++) {
/* 227 */       paramMessageListener.messageReceived(MessageHelper.format(this.m_resources.getString("wizard.usb.install.copying"), new Object[] { arrayOfString1[i] }));
/*     */ 
/* 230 */       localObject1 = getClass().getResourceAsStream(getUSBConfig().getInstallUSBDriverJarLoc() + arrayOfString1[i]);
/*     */ 
/* 232 */       ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
/*     */       try {
/* 234 */         localDiskHelper.writeToStream((InputStream)localObject1, localByteArrayOutputStream);
/*     */       } catch (IOException localIOException1) {
/* 236 */         localIOException1.printStackTrace(paramLogWriter);
/* 237 */         return new OperationResult(1, localIOException1.getMessage());
/*     */       }
/* 239 */       localObject2 = localByteArrayOutputStream.toByteArray();
/*     */       try {
/* 241 */         File localFile2 = localDiskHelper.saveFile(str1, arrayOfString1[i], localObject2, paramLogWriter);
/* 242 */         paramLogWriter.println("created " + localFile2.getCanonicalPath());
/*     */       } catch (SaveFailedException localSaveFailedException) {
/* 244 */         localSaveFailedException.printStackTrace(paramLogWriter);
/* 245 */         return new OperationResult(1, localSaveFailedException.getMessage());
/*     */       }
/*     */       catch (IOException localIOException2) {
/* 248 */         localIOException2.printStackTrace(paramLogWriter);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 253 */     String[] arrayOfString2 = getUSBConfig().getInstallUSBDriverCommand();
/* 254 */     paramMessageListener.messageReceived(getInstallText());
/* 255 */     Object localObject1 = arrayOfString2[0];
/*     */ 
/* 257 */     localObject1 = ((String)localObject1).substring(((String)localObject1).lastIndexOf(File.separator) + 1);
/* 258 */     for (int j = 1; j < arrayOfString2.length; j++) {
/* 259 */       localObject1 = (String)localObject1 + " " + arrayOfString2[j];
/*     */     }
/* 261 */     paramLogWriter.println((String)localObject1);
/* 262 */     j = 1;
/* 263 */     Object localObject2 = new ExeHelper();
/*     */     String str2;
/* 265 */     if (getWizard().getConfig().isWindowsVista())
/* 266 */       str2 = this.m_resources.getString("wizard.usb.install.vista") + "<br>";
/*     */     else
/* 268 */       str2 = this.m_resources.getString("wizard.usb.install.administrator") + "<br>";
/*     */     try
/*     */     {
/* 271 */       j = ((ExeHelper)localObject2).execute(arrayOfString2, localFile1);
/*     */     }
/*     */     catch (IOException localIOException3) {
/* 274 */       return new OperationResult(1, str2 + localIOException3.getMessage());
/*     */     } catch (InterruptedException localInterruptedException) {
/* 276 */       localInterruptedException.printStackTrace(paramLogWriter);
/* 277 */       return new OperationResult(1, localInterruptedException.getMessage());
/*     */     }
/*     */ 
/* 281 */     if (j != 0)
/*     */     {
/* 284 */       return new OperationResult(1, MessageHelper.format(this.m_resources.getString("wizard.usb.install.failed"), new Object[] { str2 + (String)localObject1, String.valueOf(j) }));
/*     */     }
/*     */ 
/* 294 */     paramMessageListener.messageReceived(this.m_resources.getString("wizard.usb.install.success"));
/* 295 */     File localFile3 = new File(localFile1 + File.separator + getUSBConfig().getInstallUSBDriverSuccessIndicatorFile());
/*     */     try
/*     */     {
/* 298 */       localFile3.createNewFile();
/*     */     }
/*     */     catch (IOException localIOException4)
/*     */     {
/* 303 */       localIOException4.printStackTrace(paramLogWriter);
/* 304 */       return new OperationResult(1, this.m_resources.getString("wizard.usb.install.indicatorfailed"));
/*     */     }
/*     */ 
/* 309 */     paramLogWriter.println("USB Driver install complete into " + localFile1);
/* 310 */     return (OperationResult)(OperationResult)new OperationResult(0, null);
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.steps.USBDriverInstallAuthorizationStep
 * JD-Core Version:    0.6.0
 */