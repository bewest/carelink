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
/*     */ import minimed.ddms.A.P;
/*     */ import minimed.ddms.A.W;
/*     */ import minimed.ddms.A.Z;
/*     */ import minimed.ddms.A.t;
/*     */ import minimed.ddms.applet.dtw.CaptureResult;
/*     */ import minimed.ddms.applet.dtw.MessageHelper;
/*     */ import minimed.ddms.applet.dtw.wizard.Wizard;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardConfig;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardSelections;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardStep;
/*     */ import minimed.util.Contract;
/*     */ import minimed.util.OSInfo;
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
/*     */   private static final int INDEX_PROBLEM_WITH_PORT_ON_MAC = 12;
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
/*  80 */     super(paramWizard, paramClass);
/*     */ 
/*  83 */     this.m_errorMsgLabel = getLeftBannerLabel();
/*  84 */     Object localObject = new ImageIcon(getImage(paramString1));
/*  85 */     getRightBannerLabel().setIcon((Icon)localObject);
/*     */ 
/*  88 */     localObject = getErrorIcon();
/*  89 */     getTopImageLabel().setIcon((Icon)localObject);
/*  90 */     getTopImageLabel().setHorizontalAlignment(0);
/*     */ 
/*  93 */     getBottomImageLabel().setVisible(false);
/*     */ 
/*  96 */     JPanel localJPanel1 = new JPanel();
/*  97 */     setCustomColors(localJPanel1);
/*  98 */     localJPanel1.setLayout(new BorderLayout());
/*     */ 
/* 100 */     this.m_suggestionMsgLabel = new JLabel();
/* 101 */     this.m_suggestionMsgLabel.setHorizontalAlignment(0);
/* 102 */     localJPanel1.add(this.m_suggestionMsgLabel, "Center");
/*     */ 
/* 105 */     JPanel localJPanel2 = new JPanel();
/* 106 */     setCustomColors(localJPanel2);
/* 107 */     localJPanel2.setLayout(new BorderLayout());
/*     */ 
/* 109 */     this.m_progressBarText = this.m_resources.getString(paramString2);
/* 110 */     this.m_progressBarLabel = new JLabel();
/* 111 */     this.m_progressBarLabel.setHorizontalAlignment(0);
/* 112 */     localJPanel2.add(this.m_progressBarLabel, "Center");
/*     */ 
/* 114 */     this.m_progressBar = new JProgressBar();
/*     */ 
/* 116 */     this.m_progressBar.setBackground(paramWizard.getConfig().getBackgroundColor());
/* 117 */     this.m_progressBar.setBorder(BorderFactory.createLineBorder(paramWizard.getConfig().getBorderColor()));
/*     */ 
/* 119 */     localJPanel2.add(this.m_progressBar, "South");
/*     */ 
/* 121 */     localJPanel1.add(localJPanel2, "South");
/*     */ 
/* 123 */     JPanel localJPanel3 = getContentArea();
/* 124 */     localJPanel3.setLayout(new BorderLayout());
/* 125 */     localJPanel3.add(localJPanel1);
/* 126 */     localJPanel3.setBorder(new EmptyBorder(new Insets(0, 5, 0, 5)));
/*     */   }
/*     */ 
/*     */   protected final void backRequest()
/*     */   {
/* 137 */     Wizard localWizard = getWizard();
/* 138 */     localWizard.showPreviousStep(2);
/*     */   }
/*     */ 
/*     */   protected final void finishRequest()
/*     */   {
/* 145 */     retryRequest();
/*     */   }
/*     */ 
/*     */   protected final void retryRequest()
/*     */   {
/* 153 */     getBottomImageLabel().setVisible(false);
/* 154 */     getBottomImageLabel().setText("");
/*     */ 
/* 157 */     Wizard localWizard = getWizard();
/* 158 */     localWizard.showPreviousStep();
/*     */   }
/*     */ 
/*     */   protected final void cancelRequest()
/*     */   {
/* 165 */     super.cancelRequest();
/* 166 */     getWizard().showNextStep(SelectDeviceStep.class);
/*     */   }
/*     */ 
/*     */   protected final void stepShown()
/*     */   {
/* 174 */     super.stepShown();
/*     */ 
/* 177 */     getCancelButton().setEnabled(true);
/* 178 */     enableWithFocus(getFinishButton());
/*     */ 
/* 180 */     getFinishButton().setText(this.m_resources.getString("wizard.finishButton.retryText"));
/* 181 */     getNextButton().setEnabled(false);
/* 182 */     logInfo("stepshown " + this);
/*     */ 
/* 184 */     WizardStep localWizardStep = getWizard().getPreviousStep();
/* 185 */     this.m_progressBar.setValue(getWizard().getCaptureResult().getPercentComplete());
/*     */ 
/* 188 */     getBottomImageLabel().setVisible(false);
/* 189 */     getBottomImageLabel().setText("");
/*     */ 
/* 192 */     String[] arrayOfString = decodeMessages(localWizardStep);
/*     */ 
/* 195 */     this.m_errorMsgLabel.setText("<html>" + arrayOfString[0] + "</html>");
/*     */ 
/* 198 */     String str = MessageHelper.format(this.m_resources.getString("wizard.suggestion.message"), new Object[] { arrayOfString[1] });
/*     */ 
/* 200 */     this.m_suggestionMsgLabel.setText(str);
/*     */ 
/* 202 */     setProgressBarText(this.m_progressBarText);
/* 203 */     logInfo("stepshown complete" + this);
/*     */   }
/*     */ 
/*     */   public abstract String[] getErrorMessages();
/*     */ 
/*     */   public abstract String[] getSuggestionMessages();
/*     */ 
/*     */   private void setProgressBarText(String paramString)
/*     */   {
/* 229 */     String str = MessageHelper.format(this.m_resources.getString("wizard.progressbar.message"), new Object[] { paramString, Integer.toString(this.m_progressBar.getValue()) });
/*     */ 
/* 232 */     this.m_progressBarLabel.setText(str);
/*     */   }
/*     */ 
/*     */   private String[] decodeMessages(WizardStep paramWizardStep)
/*     */   {
/* 243 */     CaptureResult localCaptureResult = getWizard().getCaptureResult();
/* 244 */     Exception localException = localCaptureResult.getDeviceException();
/* 245 */     logInfo("decodeMessages: device exception = " + localException + " prev step cancelled = " + localCaptureResult.wasCancelled());
/*     */     String[] arrayOfString;
/* 249 */     if (localCaptureResult.wasCancelled()) {
/* 250 */       int i = 6;
/* 251 */       arrayOfString = new String[] { getErrorMessages()[i], getSuggestionMessages()[i] };
/*     */     }
/* 255 */     else if ((localException instanceof P)) {
/* 256 */       arrayOfString = mapConnectToPumpException(paramWizardStep);
/*     */     }
/* 260 */     else if (((localException instanceof t)) || ((localException instanceof IOException)) || ((localException instanceof Z)) || ((localException instanceof W)))
/*     */     {
/* 264 */       arrayOfString = mapGeneralDeviceException(paramWizardStep);
/*     */     }
/*     */     else
/*     */     {
/* 268 */       Contract.unreachable();
/* 269 */       arrayOfString = null;
/*     */     }
/*     */ 
/* 273 */     return arrayOfString;
/*     */   }
/*     */ 
/*     */   private final String[] mapConnectToPumpException(WizardStep paramWizardStep)
/*     */   {
/* 284 */     CaptureResult localCaptureResult = getWizard().getCaptureResult();
/* 285 */     P localP = (P)localCaptureResult.getDeviceException();
/*     */     int i;
/* 288 */     switch (localP.B()) {
/*     */     case 2:
/* 290 */       i = 7;
/* 291 */       break;
/*     */     case 3:
/* 293 */       i = 8;
/* 294 */       break;
/*     */     case 1000:
/* 296 */       i = 10;
/* 297 */       break;
/*     */     case 2000:
/* 299 */       i = 11;
/* 300 */       break;
/*     */     default:
/* 302 */       Contract.unreachable();
/* 303 */       i = 0;
/*     */     }
/*     */ 
/* 307 */     String str1 = getSuggestionMessages()[i];
/* 308 */     String str2 = getErrorMessages()[i];
/*     */ 
/* 310 */     if ((paramWizardStep instanceof DeviceOperationStep)) {
/* 311 */       str1 = ((DeviceOperationStep)paramWizardStep).updateDynamicText(str1);
/* 312 */       str2 = ((DeviceOperationStep)paramWizardStep).updateDynamicText(str2);
/*     */     }
/*     */ 
/* 315 */     return new String[] { str2, str1 };
/*     */   }
/*     */ 
/*     */   private final String[] mapGeneralDeviceException(WizardStep paramWizardStep)
/*     */   {
/* 327 */     CaptureResult localCaptureResult = getWizard().getCaptureResult();
/* 328 */     int j = localCaptureResult.getLastOperationalPhase();
/*     */     int i;
/* 330 */     switch (j)
/*     */     {
/*     */     case 1:
/*     */     case 2:
/* 334 */       if (OSInfo.isMac())
/* 335 */         i = 12;
/*     */       else {
/* 337 */         i = 1;
/*     */       }
/* 339 */       break;
/*     */     case 3:
/*     */     case 7:
/* 346 */       str1 = getWizardSelections().getLinkDevice();
/* 347 */       if (str1.equals("wizard.selections.SELECTION_LINK_DEVICE_PARADIGMLINK"))
/*     */       {
/* 349 */         i = 2;
/*     */       }
/* 351 */       else if ((str1.equals("wizard.selections.SELECTION_LINK_DEVICE_COMLINK")) || (str1.equals("wizard.selections.SELECTION_LINK_DEVICE_COMLINKUSB")) || (str1.equals("wizard.selections.SELECTION_LINK_DEVICE_XTLINKUSB")))
/*     */       {
/* 357 */         i = 9;
/*     */       } else {
/* 359 */         Contract.unreachable();
/* 360 */         i = 0;
/*     */       }
/*     */ 
/* 363 */       break;
/*     */     case 4:
/* 367 */       i = 3;
/* 368 */       break;
/*     */     case 5:
/* 373 */       if (localCaptureResult.wasAnyDeviceDataRead())
/*     */       {
/* 377 */         i = localCaptureResult.getRetryCount() <= localCaptureResult.getMaxRetryCount() * 2 ? 4 : 5;
/*     */       }
/*     */       else
/*     */       {
/* 382 */         i = 3;
/*     */       }
/* 384 */       break;
/*     */     case 0:
/* 388 */       i = 0;
/* 389 */       break;
/*     */     case 6:
/*     */     default:
/* 393 */       Contract.unreachable();
/* 394 */       i = 0;
/*     */     }
/*     */ 
/* 398 */     String str1 = getErrorMessages()[i];
/* 399 */     String str2 = getSuggestionMessages()[i];
/*     */ 
/* 402 */     if ((paramWizardStep instanceof DeviceOperationStep)) {
/* 403 */       str2 = ((DeviceOperationStep)paramWizardStep).updateDynamicText(str2);
/* 404 */       str1 = ((DeviceOperationStep)paramWizardStep).updateDynamicText(str1);
/*     */     }
/*     */ 
/* 407 */     return new String[] { str1, str2 };
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.steps.DeviceOperationFailStep
 * JD-Core Version:    0.6.0
 */