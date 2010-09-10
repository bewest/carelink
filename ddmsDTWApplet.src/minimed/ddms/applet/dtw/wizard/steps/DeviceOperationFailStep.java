/*     */ package minimed.ddms.applet.dtw.wizard.steps;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Insets;
/*     */ import java.io.IOException;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JProgressBar;
/*     */ import javax.swing.border.EmptyBorder;
/*     */ import minimed.ddms.applet.dtw.CaptureResult;
/*     */ import minimed.ddms.applet.dtw.MessageHelper;
/*     */ import minimed.ddms.applet.dtw.wizard.Wizard;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardConfig;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardSelections;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardStep;
/*     */ import minimed.ddms.deviceportreader.BadDeviceCommException;
/*     */ import minimed.ddms.deviceportreader.BadDeviceValueException;
/*     */ import minimed.ddms.deviceportreader.ConnectToPumpException;
/*     */ import minimed.ddms.deviceportreader.DevicePortReader;
/*     */ import minimed.ddms.deviceportreader.SerialIOHaltedException;
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ public abstract class DeviceOperationFailStep extends WizardStep
/*     */ {
/*     */   private static final int INDEX_UNKNOWN = 0;
/*     */   private static final int INDEX_PROBLEM_WITH_PORT = 1;
/*     */   private static final int INDEX_PROBLEM_WITH_PARAMDIGMLINK = 2;
/*     */   private static final int INDEX_PROBLEM_FINDING_DEVICE = 3;
/*     */   private static final int INDEX_LOST_DEVICE_COMMUNICATIONS = 4;
/*     */   private static final int INDEX_EXCESSIVE_RETRIES = 5;
/*     */   private static final int INDEX_HALT_REQUESTED = 6;
/*     */   private static final int INDEX_PUMP_ALARM_ERROR = 7;
/*     */   private static final int INDEX_PUMP_ACTIVE_ERROR = 8;
/*     */   private static final int INDEX_PROBLEM_WITH_COMLINK = 9;
/*     */   private static final int INDEX_PUMP_MODEL_UNSUPPORTED = 10;
/*     */   private static final int INDEX_WRONG_DEVICE_SELECTION = 11;
/*     */   private static final int INSET_MAIN_LEFT = 5;
/*     */   private static final int INSET_MAIN_RIGHT = 5;
/*     */   private final JLabel m_suggestionMsgLabel;
/*     */   private final JLabel m_errorMsgLabel;
/*     */   private final JLabel m_progressBarLabel;
/*     */   private final JProgressBar m_progressBar;
/*     */   private final String m_progressBarText;
/*     */ 
/*     */   protected DeviceOperationFailStep(Wizard paramWizard, String paramString1, Class paramClass, String paramString2)
/*     */   {
/*  77 */     super(paramWizard, paramClass);
/*     */ 
/*  80 */     this.m_errorMsgLabel = getLeftBannerLabel();
/*  81 */     Object localObject = new ImageIcon(getImage(paramString1));
/*  82 */     getRightBannerLabel().setIcon((Icon)localObject);
/*     */ 
/*  85 */     localObject = getErrorIcon();
/*  86 */     getTopImageLabel().setIcon((Icon)localObject);
/*  87 */     getTopImageLabel().setHorizontalAlignment(0);
/*     */ 
/*  90 */     getBottomImageLabel().setVisible(false);
/*     */ 
/*  93 */     JPanel localJPanel1 = new JPanel();
/*  94 */     setCustomColors(localJPanel1);
/*  95 */     localJPanel1.setLayout(new BorderLayout());
/*     */ 
/*  97 */     this.m_suggestionMsgLabel = new JLabel();
/*  98 */     this.m_suggestionMsgLabel.setHorizontalAlignment(0);
/*  99 */     localJPanel1.add(this.m_suggestionMsgLabel, "Center");
/*     */ 
/* 102 */     JPanel localJPanel2 = new JPanel();
/* 103 */     setCustomColors(localJPanel2);
/* 104 */     localJPanel2.setLayout(new BorderLayout());
/*     */ 
/* 106 */     this.m_progressBarText = this.m_resources.getString(paramString2);
/* 107 */     this.m_progressBarLabel = new JLabel();
/* 108 */     this.m_progressBarLabel.setHorizontalAlignment(0);
/* 109 */     localJPanel2.add(this.m_progressBarLabel, "Center");
/*     */ 
/* 111 */     this.m_progressBar = new JProgressBar();
/*     */ 
/* 113 */     this.m_progressBar.setBackground(paramWizard.getConfig().getBackgroundColor());
/* 114 */     this.m_progressBar.setBorder(BorderFactory.createLineBorder(paramWizard.getConfig().getBorderColor()));
/*     */ 
/* 116 */     localJPanel2.add(this.m_progressBar, "South");
/*     */ 
/* 118 */     localJPanel1.add(localJPanel2, "South");
/*     */ 
/* 120 */     JPanel localJPanel3 = getContentArea();
/* 121 */     localJPanel3.setLayout(new BorderLayout());
/* 122 */     localJPanel3.add(localJPanel1);
/* 123 */     localJPanel3.setBorder(new EmptyBorder(new Insets(0, 5, 0, 5)));
/*     */   }
/*     */ 
/*     */   protected final void backRequest()
/*     */   {
/* 134 */     Wizard localWizard = getWizard();
/* 135 */     localWizard.showPreviousStep(2);
/*     */   }
/*     */ 
/*     */   protected final void finishRequest()
/*     */   {
/* 142 */     retryRequest();
/*     */   }
/*     */ 
/*     */   protected final void retryRequest()
/*     */   {
/* 150 */     getBottomImageLabel().setVisible(false);
/* 151 */     getBottomImageLabel().setText("");
/*     */ 
/* 154 */     Wizard localWizard = getWizard();
/* 155 */     localWizard.showPreviousStep();
/*     */   }
/*     */ 
/*     */   protected final void cancelRequest()
/*     */   {
/* 162 */     super.cancelRequest();
/* 163 */     getWizard().showNextStep(SelectDeviceStep.class);
/*     */   }
/*     */ 
/*     */   protected final void stepShown()
/*     */   {
/* 171 */     super.stepShown();
/*     */ 
/* 174 */     getCancelButton().setEnabled(true);
/* 175 */     enableWithFocus(getFinishButton());
/*     */ 
/* 177 */     getFinishButton().setText(this.m_resources.getString("wizard.finishButton.retryText"));
/* 178 */     getNextButton().setEnabled(false);
/* 179 */     DeviceOperationStep localDeviceOperationStep = (DeviceOperationStep)getWizard().getPreviousStep();
/* 180 */     this.m_progressBar.setValue(localDeviceOperationStep.getCaptureResult().getPercentComplete());
/*     */ 
/* 183 */     getBottomImageLabel().setVisible(false);
/* 184 */     getBottomImageLabel().setText("");
/*     */ 
/* 187 */     String[] arrayOfString = decodeMessages(localDeviceOperationStep);
/*     */ 
/* 190 */     this.m_errorMsgLabel.setText("<html>" + arrayOfString[0] + "</html>");
/*     */ 
/* 193 */     String str = MessageHelper.format(this.m_resources.getString("wizard.suggestion.message"), new Object[] { arrayOfString[1] });
/*     */ 
/* 195 */     this.m_suggestionMsgLabel.setText(str);
/*     */ 
/* 197 */     setProgressBarText(this.m_progressBarText);
/*     */   }
/*     */ 
/*     */   protected abstract String[] getErrorMessages();
/*     */ 
/*     */   protected abstract String[] getSuggestionMessages();
/*     */ 
/*     */   private void setProgressBarText(String paramString)
/*     */   {
/* 222 */     String str = MessageHelper.format(this.m_resources.getString("wizard.progressbar.message"), new Object[] { paramString, Integer.toString(this.m_progressBar.getValue()) });
/*     */ 
/* 225 */     this.m_progressBarLabel.setText(str);
/*     */   }
/*     */ 
/*     */   private String[] decodeMessages(DeviceOperationStep paramDeviceOperationStep)
/*     */   {
/* 236 */     CaptureResult localCaptureResult = paramDeviceOperationStep.getCaptureResult();
/* 237 */     Exception localException = localCaptureResult.getDeviceException();
/* 238 */     logInfo("decodeMessages: device exception = " + localException + " prev step cancelled = " + localCaptureResult.wasCancelled());
/*     */     String[] arrayOfString;
/* 242 */     if (localCaptureResult.wasCancelled()) {
/* 243 */       int i = 6;
/* 244 */       arrayOfString = new String[] { getErrorMessages()[i], getSuggestionMessages()[i] };
/*     */     }
/* 248 */     else if ((localException instanceof ConnectToPumpException)) {
/* 249 */       arrayOfString = mapConnectToPumpException(paramDeviceOperationStep);
/*     */     }
/* 253 */     else if (((localException instanceof BadDeviceCommException)) || ((localException instanceof IOException)) || ((localException instanceof BadDeviceValueException)) || ((localException instanceof SerialIOHaltedException)))
/*     */     {
/* 257 */       arrayOfString = mapGeneralDeviceException(paramDeviceOperationStep);
/*     */     }
/*     */     else
/*     */     {
/* 261 */       Contract.unreachable();
/* 262 */       arrayOfString = null;
/*     */     }
/*     */ 
/* 266 */     return arrayOfString;
/*     */   }
/*     */ 
/*     */   private final String[] mapConnectToPumpException(DeviceOperationStep paramDeviceOperationStep)
/*     */   {
/* 277 */     CaptureResult localCaptureResult = paramDeviceOperationStep.getCaptureResult();
/* 278 */     ConnectToPumpException localConnectToPumpException = (ConnectToPumpException)localCaptureResult.getDeviceException();
/*     */     int i;
/* 281 */     switch (localConnectToPumpException.getReasonCode()) {
/*     */     case 2:
/* 283 */       i = 7;
/* 284 */       break;
/*     */     case 3:
/* 286 */       i = 8;
/* 287 */       break;
/*     */     case 1000:
/* 289 */       i = 10;
/* 290 */       break;
/*     */     case 2000:
/* 292 */       i = 11;
/* 293 */       break;
/*     */     default:
/* 295 */       Contract.unreachable();
/* 296 */       i = 0;
/*     */     }
/*     */ 
/* 301 */     String str1 = paramDeviceOperationStep.updateDynamicText(getSuggestionMessages()[i]);
/* 302 */     String str2 = paramDeviceOperationStep.updateDynamicText(getErrorMessages()[i]);
/*     */ 
/* 304 */     return new String[] { str2, str1 };
/*     */   }
/*     */ 
/*     */   private final String[] mapGeneralDeviceException(DeviceOperationStep paramDeviceOperationStep)
/*     */   {
/* 316 */     CaptureResult localCaptureResult = paramDeviceOperationStep.getCaptureResult();
/* 317 */     int j = localCaptureResult.getLastOperationalPhase();
/*     */     int i;
/* 319 */     switch (j)
/*     */     {
/*     */     case 1:
/*     */     case 2:
/* 323 */       i = 1;
/* 324 */       break;
/*     */     case 3:
/*     */     case 7:
/* 331 */       str1 = getWizardSelections().getLinkDevice();
/* 332 */       if (str1.equals("wizard.selections.SELECTION_LINK_DEVICE_PARADIGMLINK"))
/*     */       {
/* 334 */         i = 2;
/*     */       }
/* 336 */       else if ((str1.equals("wizard.selections.SELECTION_LINK_DEVICE_COMLINK")) || (str1.equals("wizard.selections.SELECTION_LINK_DEVICE_COMLINKUSB")))
/*     */       {
/* 340 */         i = 9;
/*     */       } else {
/* 342 */         Contract.unreachable();
/* 343 */         i = 0;
/*     */       }
/*     */ 
/* 346 */       break;
/*     */     case 4:
/* 350 */       i = 3;
/* 351 */       break;
/*     */     case 5:
/* 356 */       if (localCaptureResult.wasAnyDeviceDataRead())
/*     */       {
/* 360 */         i = localCaptureResult.getRetryCount() <= localCaptureResult.getDevicePortReader().getMaxRetryCount() * 2 ? 4 : 5;
/*     */       }
/*     */       else
/*     */       {
/* 365 */         i = 3;
/*     */       }
/* 367 */       break;
/*     */     case 0:
/* 371 */       i = 0;
/* 372 */       break;
/*     */     case 6:
/*     */     default:
/* 376 */       Contract.unreachable();
/* 377 */       i = 0;
/*     */     }
/*     */ 
/* 381 */     String str1 = getErrorMessages()[i];
/* 382 */     String str2 = getSuggestionMessages()[i];
/*     */ 
/* 385 */     str2 = paramDeviceOperationStep.updateDynamicText(str2);
/* 386 */     str1 = paramDeviceOperationStep.updateDynamicText(str1);
/*     */ 
/* 388 */     return new String[] { str1, str2 };
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.steps.DeviceOperationFailStep
 * JD-Core Version:    0.6.0
 */