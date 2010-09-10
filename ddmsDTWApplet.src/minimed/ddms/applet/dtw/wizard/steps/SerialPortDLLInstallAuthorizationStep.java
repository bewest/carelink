/*     */ package minimed.ddms.applet.dtw.wizard.steps;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.text.MessageFormat;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import minimed.ddms.applet.dtw.DiskHelper;
/*     */ import minimed.ddms.applet.dtw.IOHelper;
/*     */ import minimed.ddms.applet.dtw.InstallFileVersion;
/*     */ import minimed.ddms.applet.dtw.InstallTestResult;
/*     */ import minimed.ddms.applet.dtw.LogWriter;
/*     */ import minimed.ddms.applet.dtw.MessageHelper;
/*     */ import minimed.ddms.applet.dtw.OperationResult;
/*     */ import minimed.ddms.applet.dtw.SaveFailedException;
/*     */ import minimed.ddms.applet.dtw.wizard.Wizard;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardConfig;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardStep;
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ public class SerialPortDLLInstallAuthorizationStep extends WizardStep
/*     */ {
/*  39 */   private final String m_htmlTextPattern = this.m_resources.getString("wizard.serial.text");
/*     */ 
/*     */   public SerialPortDLLInstallAuthorizationStep(Wizard paramWizard)
/*     */   {
/*  50 */     super(paramWizard, null);
/*     */ 
/*  52 */     getLeftBannerLabel().setText(this.m_resources.getString("wizard.serial.needed"));
/*  53 */     Object localObject = new ImageIcon(getImage("wizard.read.icon"));
/*  54 */     getRightBannerLabel().setIcon((Icon)localObject);
/*     */ 
/*  57 */     localObject = getQuestionIcon();
/*  58 */     getTopImageLabel().setIcon((Icon)localObject);
/*     */ 
/*  61 */     JPanel localJPanel = getContentArea();
/*  62 */     localJPanel.setLayout(new BorderLayout());
/*     */ 
/*  64 */     String[] arrayOfString = { getWizard().getConfig().getInstallSerialPortDLL() };
/*  65 */     String str = centerLabelText(new MessageFormat(this.m_htmlTextPattern).format(arrayOfString));
/*  66 */     JLabel localJLabel = new JLabel(str);
/*  67 */     localJLabel.setHorizontalAlignment(0);
/*  68 */     localJPanel.add(localJLabel, "Center");
/*     */   }
/*     */ 
/*     */   protected void stepShown()
/*     */   {
/*  79 */     getNextButton().setEnabled(true);
/*  80 */     getBackButton().setEnabled(false);
/*  81 */     getFinishButton().setEnabled(false);
/*  82 */     getCancelButton().setEnabled(false);
/*     */ 
/*  84 */     enableCursor();
/*     */   }
/*     */ 
/*     */   protected void nextRequest()
/*     */   {
/*  93 */     LogWriter localLogWriter = getWizard().getConfig().getLogWriter();
/*     */ 
/*  95 */     OperationResult localOperationResult = performInstall(localLogWriter);
/*  96 */     if (localOperationResult.getResult() == 0) {
/*  97 */       localLogWriter.println("install succeeded");
/*  98 */       getWizard().showNextStep(SelectDeviceStep.class);
/*     */     }
/* 100 */     else if (localOperationResult.getResult() == 1) {
/* 101 */       String str = localOperationResult.getReason();
/* 102 */       localLogWriter.println("install failed because " + str);
/* 103 */       getWizard().setFailureReason(MessageHelper.format(this.m_resources.getString("wizard.serial.failed"), new Object[] { str }));
/*     */ 
/* 106 */       getWizard().showNextStep(UnrecoverableErrorStep.class);
/*     */     } else {
/* 108 */       Contract.unreachable();
/*     */     }
/*     */   }
/*     */ 
/*     */   private OperationResult performInstall(LogWriter paramLogWriter)
/*     */   {
/* 120 */     InitializationStep localInitializationStep = (InitializationStep)getWizard().getStep(InitializationStep.class);
/*     */ 
/* 122 */     InstallTestResult localInstallTestResult = localInitializationStep.getInstallSerialPortDLLTestResult();
/*     */ 
/* 125 */     if (localInstallTestResult.getResult() == 0)
/*     */     {
/* 129 */       Contract.unreachable();
/* 133 */     }
/*     */ InstallFileVersion localInstallFileVersion = localInstallTestResult.getFileToInstall();
/*     */     Object localObject;
/*     */     try { InputStream localInputStream = getWizard().getConfig().getSerialPortDLLInputStream();
/* 136 */       localObject = new ByteArrayOutputStream();
/* 137 */       new IOHelper().writeToStream(localInputStream, (OutputStream)localObject);
/* 138 */       byte[] arrayOfByte = ((ByteArrayOutputStream)localObject).toByteArray();
/* 139 */       localInstallFileVersion.setData(arrayOfByte);
/*     */     } catch (IOException localIOException) {
/* 141 */       localIOException.printStackTrace(paramLogWriter);
/* 142 */       return new OperationResult(1, localIOException.getMessage());
/*     */     }
/*     */ 
/*     */     try
/*     */     {
/* 147 */       paramLogWriter.println("save file " + localInstallFileVersion.getName());
/* 148 */       DiskHelper localDiskHelper = new DiskHelper();
/* 149 */       localObject = getWizard().getConfig().getInstallSerialPortDLLDir();
/* 150 */       localDiskHelper.saveFile((String)localObject, localInstallFileVersion.getName(), localInstallFileVersion.getData(), paramLogWriter);
/*     */     } catch (SaveFailedException localSaveFailedException) {
/* 152 */       localSaveFailedException.printStackTrace(paramLogWriter);
/* 153 */       return new OperationResult(1, localSaveFailedException.getMessage());
/*     */     }
/*     */ 
/* 156 */     return (OperationResult)new OperationResult(0, null);
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.steps.SerialPortDLLInstallAuthorizationStep
 * JD-Core Version:    0.6.0
 */