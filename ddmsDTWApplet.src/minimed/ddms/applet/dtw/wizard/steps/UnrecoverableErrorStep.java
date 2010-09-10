/*     */ package minimed.ddms.applet.dtw.wizard.steps;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Container;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Properties;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import minimed.ddms.applet.dtw.LogWriter;
/*     */ import minimed.ddms.applet.dtw.MessageHelper;
/*     */ import minimed.ddms.applet.dtw.NetHelper;
/*     */ import minimed.ddms.applet.dtw.OperationResult;
/*     */ import minimed.ddms.applet.dtw.PropertyWriter;
/*     */ import minimed.ddms.applet.dtw.UploadCancelledException;
/*     */ import minimed.ddms.applet.dtw.UploadFailedException;
/*     */ import minimed.ddms.applet.dtw.wizard.Wizard;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardConfig;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardStep;
/*     */ 
/*     */ public class UnrecoverableErrorStep extends WizardStep
/*     */ {
/*     */   private final JLabel m_stepMsg;
/*     */   private boolean m_errorReportSent;
/*     */ 
/*     */   public UnrecoverableErrorStep(Wizard paramWizard)
/*     */   {
/*  53 */     super(paramWizard, null);
/*     */ 
/*  55 */     getLeftBannerLabel().setText(this.m_resources.getString("wizard.init.error"));
/*  56 */     Object localObject = new ImageIcon(getImage("wizard.read.icon"));
/*  57 */     getRightBannerLabel().setIcon((Icon)localObject);
/*     */ 
/*  60 */     localObject = getQuestionIcon();
/*  61 */     getTopImageLabel().setIcon((Icon)localObject);
/*     */ 
/*  64 */     this.m_stepMsg = new JLabel();
/*  65 */     this.m_stepMsg.setHorizontalAlignment(0);
/*     */ 
/*  68 */     JPanel localJPanel = getContentArea();
/*  69 */     localJPanel.setLayout(new BorderLayout());
/*  70 */     localJPanel.add(this.m_stepMsg, "Center");
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/*  83 */     PropertyWriter localPropertyWriter = new PropertyWriter(super.toString());
/*  84 */     localPropertyWriter.add("errorReportSent", this.m_errorReportSent);
/*  85 */     return localPropertyWriter.toString();
/*     */   }
/*     */ 
/*     */   protected void stepShown()
/*     */   {
/*  94 */     enableCursor();
/*     */ 
/*  97 */     getBackButton().setEnabled(false);
/*  98 */     getFinishButton().setEnabled(false);
/*  99 */     getCancelButton().setEnabled(false);
/*     */ 
/* 102 */     String str = getWizard().getFailureReason();
/*     */ 
/* 105 */     if (!isErrorReportSent())
/*     */     {
/* 107 */       getNextButton().setEnabled(true);
/* 108 */       str = str + this.m_resources.getString("wizard.unrecoverable.instructions");
/*     */     }
/*     */     else
/*     */     {
/* 112 */       getNextButton().setEnabled(false);
/*     */     }
/*     */ 
/* 115 */     setMsgText(str);
/*     */   }
/*     */ 
/*     */   protected void nextRequest()
/*     */   {
/* 124 */     1 local1 = new Runnable()
/*     */     {
/*     */       public void run()
/*     */       {
/* 130 */         LogWriter localLogWriter = UnrecoverableErrorStep.this.getWizard().getConfig().getLogWriter();
/* 131 */         UnrecoverableErrorStep.this.reportInProgressMsg();
/*     */ 
/* 133 */         UnrecoverableErrorStep.this.disableCursor();
/*     */         try
/*     */         {
/* 136 */           OperationResult localOperationResult = UnrecoverableErrorStep.this.performReport();
/*     */ 
/* 139 */           if (localOperationResult.getResult() == 0)
/*     */           {
/* 141 */             localLogWriter.println("Error report succeeded");
/* 142 */             UnrecoverableErrorStep.this.reportSucceededMsg();
/*     */           }
/* 144 */           else if (localOperationResult.getResult() == 2)
/*     */           {
/* 146 */             localLogWriter.println("Error report was cancelled by user");
/* 147 */             UnrecoverableErrorStep.this.reportCancelledMsg();
/*     */           }
/*     */           else {
/* 150 */             String str = localOperationResult.getReason();
/* 151 */             localLogWriter.println("Error report failed because " + str);
/* 152 */             UnrecoverableErrorStep.this.reportFailedMsg(str);
/*     */           }
/*     */         }
/*     */         catch (RuntimeException localRuntimeException) {
/* 156 */           localRuntimeException.printStackTrace(localLogWriter);
/*     */ 
/* 158 */           UnrecoverableErrorStep.this.getWizard().showNextStep(UnrecoverableErrorStep.class);
/*     */         } catch (NoClassDefFoundError localNoClassDefFoundError) {
/* 160 */           localNoClassDefFoundError.printStackTrace(localLogWriter);
/*     */ 
/* 162 */           UnrecoverableErrorStep.this.getWizard().showNextStep(UnrecoverableErrorStep.class);
/*     */         } finally {
/* 164 */           UnrecoverableErrorStep.this.enableCursor();
/*     */         }
/*     */       }
/*     */     };
/* 169 */     Thread localThread = new Thread(local1);
/* 170 */     localThread.setDaemon(true);
/* 171 */     localThread.start();
/*     */   }
/*     */ 
/*     */   private OperationResult performReport()
/*     */   {
/* 181 */     setErrorReportSent(true);
/* 182 */     getNextButton().setEnabled(false);
/*     */ 
/* 184 */     LogWriter localLogWriter = getWizard().getConfig().getLogWriter();
/* 185 */     Container localContainer = getWizard().getConfig().getContentPane();
/* 186 */     String str1 = getWizard().getConfig().getUnrecoverableErrorRemoteURL();
/*     */ 
/* 188 */     localLogWriter.println("Send the following to " + str1);
/* 189 */     localLogWriter.println("--- " + getWizard().getClass().getName() + " START ---");
/* 190 */     localLogWriter.println(getWizard());
/* 191 */     localLogWriter.println("--- " + getWizard().getClass().getName() + " END ---");
/*     */ 
/* 193 */     localLogWriter.println("--- System Properties START  ---");
/* 194 */     Properties localProperties = System.getProperties();
/* 195 */     Enumeration localEnumeration = localProperties.propertyNames();
/* 196 */     while (localEnumeration.hasMoreElements()) {
/* 197 */       str2 = (String)localEnumeration.nextElement();
/* 198 */       localLogWriter.println(str2 + " = " + localProperties.getProperty(str2));
/*     */     }
/* 200 */     localLogWriter.println("--- System Properties END ---");
/*     */ 
/* 202 */     String str2 = localLogWriter.getBackingStore();
/* 203 */     localLogWriter.println("Send approximately " + str2.getBytes().length + " bytes of log messages");
/* 204 */     str2 = localLogWriter.getBackingStore();
/*     */     try
/*     */     {
/* 207 */       NetHelper localNetHelper = new NetHelper();
/* 208 */       localNetHelper.uploadData(localContainer, str1, str2, localLogWriter);
/*     */     } catch (UploadFailedException localUploadFailedException) {
/* 210 */       localUploadFailedException.printStackTrace(localLogWriter);
/* 211 */       return new OperationResult(1, localUploadFailedException.getMessage());
/*     */     } catch (UploadCancelledException localUploadCancelledException) {
/* 213 */       return new OperationResult(2, "cancelled by user");
/*     */     }
/*     */ 
/* 216 */     return new OperationResult(0, null);
/*     */   }
/*     */ 
/*     */   private void reportInProgressMsg()
/*     */   {
/* 223 */     setMsgText(this.m_resources.getString("wizard.unrecoverable.sending"));
/*     */   }
/*     */ 
/*     */   private void reportSucceededMsg()
/*     */   {
/* 230 */     setMsgText(this.m_resources.getString("wizard.unrecoverable.received"));
/*     */   }
/*     */ 
/*     */   private void reportFailedMsg(String paramString)
/*     */   {
/* 239 */     if ((paramString == null) || (paramString.length() == 0)) {
/* 240 */       paramString = this.m_resources.getString("wizard.unrecoverable.failed.unknown");
/*     */     }
/* 242 */     setMsgText(MessageHelper.format(this.m_resources.getString("wizard.unrecoverable.failed"), new Object[] { paramString }));
/*     */   }
/*     */ 
/*     */   private void reportCancelledMsg()
/*     */   {
/* 251 */     setMsgText(this.m_resources.getString("wizard.unrecoverable.cancelled"));
/*     */   }
/*     */ 
/*     */   private void setMsgText(String paramString)
/*     */   {
/* 260 */     this.m_stepMsg.setText(centerLabelText(paramString));
/*     */   }
/*     */ 
/*     */   private synchronized boolean isErrorReportSent()
/*     */   {
/* 269 */     return this.m_errorReportSent;
/*     */   }
/*     */ 
/*     */   private synchronized void setErrorReportSent(boolean paramBoolean)
/*     */   {
/* 279 */     this.m_errorReportSent = paramBoolean;
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.steps.UnrecoverableErrorStep
 * JD-Core Version:    0.6.0
 */