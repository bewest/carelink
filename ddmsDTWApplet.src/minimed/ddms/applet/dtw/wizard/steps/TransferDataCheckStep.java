/*     */ package minimed.ddms.applet.dtw.wizard.steps;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.text.DateFormat;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.Locale;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import minimed.ddms.applet.dtw.CaptureResult;
/*     */ import minimed.ddms.applet.dtw.DeviceID;
/*     */ import minimed.ddms.applet.dtw.MessageHelper;
/*     */ import minimed.ddms.applet.dtw.PropertyWriter;
/*     */ import minimed.ddms.applet.dtw.wizard.Wizard;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardConfig;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardSelections;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardStep;
/*     */ import minimed.ddms.text.Formats;
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ public class TransferDataCheckStep extends WizardStep
/*     */ {
/*     */   private static final int TIME_DIFFERENCE_WINDOW_MINUTES = 10;
/*     */   private static final int SECONDS_IN_MINUTE = 60;
/*     */   private static final int MS_IN_SECOND = 1000;
/*     */   private static final int MS_IN_MINUTE = 60000;
/*  56 */   private final String m_intro1 = this.m_resources.getString("wizard.transferstep.check.intro1");
/*     */ 
/*  61 */   private final String m_intro2 = this.m_resources.getString("wizard.transferstep.check.intro2");
/*     */   private Date m_deviceTime;
/*     */   private JPanel m_panel;
/*     */   private JLabel m_label;
/*     */   private DeviceID m_lastPumpUniqueID;
/*     */   private DeviceID m_currentPumpUniqueID;
/*     */   private DeviceID m_lastMeterUniqueID;
/*     */   private DeviceID m_currentMeterUniqueID;
/*     */   private DeviceID m_lastCGMUniqueID;
/*     */   private DeviceID m_currentCGMUniqueID;
/*     */ 
/*     */   public TransferDataCheckStep(Wizard paramWizard)
/*     */   {
/* 106 */     super(paramWizard, TransferDataStep.class);
/* 107 */     this.m_panel = getContentArea();
/* 108 */     this.m_panel.setLayout(new BorderLayout());
/* 109 */     this.m_label = new JLabel();
/* 110 */     this.m_panel.add(this.m_label);
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 123 */     PropertyWriter localPropertyWriter = new PropertyWriter(super.toString());
/* 124 */     localPropertyWriter.add("deviceTime", this.m_deviceTime);
/* 125 */     localPropertyWriter.add("currentPumpUniqueID", this.m_currentPumpUniqueID);
/* 126 */     localPropertyWriter.add("lastPumpUniqueID", this.m_lastPumpUniqueID);
/* 127 */     localPropertyWriter.add("currentMeterUniqueID", this.m_currentMeterUniqueID);
/* 128 */     localPropertyWriter.add("lastMeterUniqueID", this.m_lastMeterUniqueID);
/* 129 */     localPropertyWriter.add("currentCGMUniqueID", this.m_currentCGMUniqueID);
/* 130 */     localPropertyWriter.add("lastCGMUniqueID", this.m_lastCGMUniqueID);
/* 131 */     return localPropertyWriter.toString();
/*     */   }
/*     */ 
/*     */   protected void stepShown()
/*     */   {
/* 138 */     StringBuffer localStringBuffer = new StringBuffer();
/* 139 */     int i = 0;
/* 140 */     String str1 = this.m_intro1;
/*     */ 
/* 142 */     WizardSelections localWizardSelections = getWizard().getConfig().getWizardSelections();
/* 143 */     String str2 = localWizardSelections.getDeviceType();
/*     */ 
/* 146 */     boolean bool1 = str2.equals("wizard.selections.SELECTION_DEVICE_PUMP");
/*     */ 
/* 148 */     boolean bool2 = str2.equals("wizard.selections.SELECTION_DEVICE_METER");
/*     */ 
/* 150 */     boolean bool3 = str2.equals("wizard.selections.SELECTION_DEVICE_CGM");
/*     */ 
/* 154 */     if ((bool1) && (bool2) && (bool3))
/*     */     {
/* 156 */       Contract.unreachable();
/*     */     }
/*     */     Object localObject4;
/*     */     String str4;
/* 159 */     if (isTimeMismatch()) {
/* 160 */       String str3 = "wizard.transferstep.check.timediff.meter";
/* 161 */       if (bool3)
/* 162 */         str3 = "wizard.transferstep.check.timediff.cgm";
/* 163 */       else if (bool1) {
/* 164 */         str3 = "wizard.transferstep.check.timediff.pump";
/*     */       }
/*     */ 
/* 168 */       localObject1 = getLocale();
/* 169 */       localObject2 = (SimpleDateFormat)Formats.getDateInstance(1, (Locale)localObject1);
/*     */ 
/* 171 */       localObject3 = (SimpleDateFormat)Formats.getTimeInstance(3, (Locale)localObject1);
/*     */ 
/* 173 */       localObject4 = new SimpleDateFormat(((SimpleDateFormat)localObject2).toPattern() + " " + ((SimpleDateFormat)localObject3).toPattern(), (Locale)localObject1);
/*     */ 
/* 176 */       str4 = ((DateFormat)localObject4).format(this.m_deviceTime);
/* 177 */       localStringBuffer.append(MessageHelper.format(this.m_resources.getString(str3), new Object[] { str4 }));
/*     */ 
/* 180 */       i = 1;
/*     */     }
/*     */ 
/* 184 */     int j = 0;
/* 185 */     Object localObject1 = "unknown";
/* 186 */     Object localObject2 = "unknown";
/* 187 */     Object localObject3 = "unknown";
/*     */ 
/* 189 */     if (bool1)
/*     */     {
/* 191 */       this.m_currentPumpUniqueID = createUniqueID();
/*     */ 
/* 193 */       if (isDifferentPump()) {
/* 194 */         localObject1 = this.m_currentPumpUniqueID.getSerialNumber();
/* 195 */         localObject2 = this.m_lastPumpUniqueID.getSerialNumber();
/* 196 */         localObject3 = this.m_lastPumpUniqueID.getClassName();
/* 197 */         j = 1;
/*     */       }
/*     */     }
/*     */ 
/* 201 */     if (bool2)
/*     */     {
/* 203 */       this.m_currentMeterUniqueID = createUniqueID();
/*     */ 
/* 205 */       if (isDifferentMeter()) {
/* 206 */         localObject1 = this.m_currentMeterUniqueID.getSerialNumber();
/* 207 */         localObject2 = this.m_lastMeterUniqueID.getSerialNumber();
/* 208 */         localObject3 = this.m_lastMeterUniqueID.getClassName();
/* 209 */         j = 1;
/*     */       }
/*     */     }
/*     */ 
/* 213 */     if (bool3)
/*     */     {
/* 215 */       this.m_currentCGMUniqueID = createUniqueID();
/*     */ 
/* 217 */       if (isDifferentCGM()) {
/* 218 */         localObject1 = this.m_currentCGMUniqueID.getSerialNumber();
/* 219 */         localObject2 = this.m_lastCGMUniqueID.getSerialNumber();
/* 220 */         localObject3 = this.m_lastCGMUniqueID.getClassName();
/* 221 */         j = 1;
/*     */       }
/*     */     }
/*     */ 
/* 225 */     if (j != 0) {
/* 226 */       if (i == 0)
/*     */       {
/* 228 */         localStringBuffer.append("1. ");
/* 229 */         i = 1;
/*     */       }
/*     */       else {
/* 232 */         str1 = this.m_intro2;
/* 233 */         localStringBuffer.append("2. ");
/*     */       }
/*     */ 
/* 236 */       localObject4 = getWizardSelections().getDeviceSelection();
/* 237 */       if (getWizard().isDeviceSelectionAPump())
/* 238 */         localObject4 = formatPumpDevice((String)localObject4);
/* 239 */       else if (getWizard().isDeviceSelectionAMeter()) {
/* 240 */         localObject4 = formatMeterDevice((String)localObject4);
/*     */       }
/*     */       else {
/* 243 */         localObject4 = this.m_resources.getString((String)localObject4);
/*     */       }
/* 245 */       localObject4 = fixForDisplay((String)localObject4);
/*     */ 
/* 247 */       str4 = Wizard.mapToWizardDeviceSelection((String)localObject3);
/* 248 */       if (getWizard().isDeviceSelectionAPump())
/* 249 */         str4 = formatPumpDevice(str4);
/* 250 */       else if (getWizard().isDeviceSelectionAMeter()) {
/* 251 */         str4 = formatMeterDevice(str4);
/*     */       }
/*     */       else {
/* 254 */         str4 = this.m_resources.getString(str4);
/*     */       }
/* 256 */       str4 = fixForDisplay(str4);
/* 257 */       String str5 = "wizard.transferstep.check.devicediff.meter";
/* 258 */       if (bool3)
/* 259 */         str5 = "wizard.transferstep.check.devicediff.cgm";
/* 260 */       else if (bool1) {
/* 261 */         str5 = "wizard.transferstep.check.devicediff.pump";
/*     */       }
/*     */ 
/* 264 */       localStringBuffer.append(MessageHelper.format(this.m_resources.getString(str5), new Object[] { localObject4, localObject1, str4, localObject2 }));
/*     */     }
/*     */ 
/* 271 */     if (i != 0) {
/* 272 */       localStringBuffer.append(this.m_resources.getString("wizard.transferstep.check.failed"));
/*     */ 
/* 274 */       displayStep(str1 + localStringBuffer.toString());
/*     */     }
/*     */     else {
/* 277 */       nextRequest();
/*     */     }
/*     */   }
/*     */ 
/*     */   protected final void backRequest()
/*     */   {
/* 286 */     getWizard().showPreviousStep(2);
/*     */   }
/*     */ 
/*     */   private String fixForDisplay(String paramString)
/*     */   {
/* 296 */     String str = "/";
/* 297 */     WizardSelections localWizardSelections = getWizard().getConfig().getWizardSelections();
/*     */ 
/* 299 */     if (localWizardSelections.isRocheMeter()) {
/* 300 */       str = " ";
/*     */     }
/* 302 */     return paramString.replaceAll("<br>", str);
/*     */   }
/*     */ 
/*     */   private void displayStep(String paramString)
/*     */   {
/* 312 */     getLeftBannerLabel().setText(this.m_resources.getString("wizard.transferstep.check.conditions"));
/* 313 */     Object localObject = new ImageIcon(getImage("wizard.read.icon"));
/* 314 */     getRightBannerLabel().setIcon((Icon)localObject);
/*     */ 
/* 317 */     localObject = getQuestionIcon();
/* 318 */     getTopImageLabel().setIcon((Icon)localObject);
/*     */ 
/* 321 */     super.stepShown();
/* 322 */     getFinishButton().setEnabled(false);
/*     */ 
/* 324 */     this.m_label.setText("<html>" + paramString + "</html>");
/*     */   }
/*     */ 
/*     */   private boolean isTimeMismatch()
/*     */   {
/* 335 */     CaptureResult localCaptureResult = getWizard().getCaptureResult();
/* 336 */     Date localDate = new Date(localCaptureResult.getClientTimeAtDeviceRead());
/* 337 */     this.m_deviceTime = localCaptureResult.getClock();
/*     */ 
/* 340 */     long l = Math.abs(localDate.getTime() - this.m_deviceTime.getTime());
/*     */ 
/* 343 */     boolean bool = l > 600000L;
/*     */ 
/* 345 */     logInfo("isTimeMismatch: client time = " + localDate + ", device time = " + this.m_deviceTime + "; outsideWindow = " + bool);
/*     */ 
/* 347 */     return bool;
/*     */   }
/*     */ 
/*     */   private boolean isDifferentPump()
/*     */   {
/* 358 */     Contract.preNonNull(this.m_currentPumpUniqueID);
/*     */ 
/* 362 */     if (this.m_lastPumpUniqueID == null)
/*     */     {
/* 364 */       String str1 = getWizard().getConfig().getLastPumpUniqueID();
/* 365 */       if (str1 != null)
/*     */       {
/* 371 */         DeviceID localDeviceID = new DeviceID(str1);
/*     */ 
/* 373 */         String str2 = Wizard.resolveServerClassName(localDeviceID.getClassName());
/*     */ 
/* 376 */         this.m_lastPumpUniqueID = new DeviceID(str2, localDeviceID.getSerialNumber());
/*     */       }
/*     */     }
/*     */     boolean bool;
/* 381 */     if (this.m_lastPumpUniqueID != null)
/*     */     {
/* 383 */       bool = !this.m_currentPumpUniqueID.equals(this.m_lastPumpUniqueID);
/*     */     }
/*     */     else {
/* 386 */       bool = false;
/*     */     }
/*     */ 
/* 389 */     logInfo("isDifferentPump: last pump ID = " + this.m_lastPumpUniqueID + ", current pump ID = " + this.m_currentPumpUniqueID + "; different = " + bool);
/*     */ 
/* 392 */     return bool;
/*     */   }
/*     */ 
/*     */   private boolean isDifferentMeter()
/*     */   {
/* 403 */     Contract.preNonNull(this.m_currentMeterUniqueID);
/*     */ 
/* 407 */     if (this.m_lastMeterUniqueID == null)
/*     */     {
/* 409 */       String str1 = getWizard().getConfig().getLastMeterUniqueID();
/* 410 */       if (str1 != null) {
/* 411 */         DeviceID localDeviceID = new DeviceID(str1);
/*     */ 
/* 413 */         String str2 = Wizard.resolveServerClassName(localDeviceID.getClassName());
/*     */ 
/* 415 */         this.m_lastMeterUniqueID = new DeviceID(str2, localDeviceID.getSerialNumber());
/*     */       }
/*     */     }
/*     */     boolean bool;
/* 420 */     if (this.m_lastMeterUniqueID != null)
/*     */     {
/* 422 */       bool = !this.m_currentMeterUniqueID.equals(this.m_lastMeterUniqueID);
/*     */     }
/*     */     else {
/* 425 */       bool = false;
/*     */     }
/*     */ 
/* 428 */     logInfo("isDifferentMeter: last meter ID = " + this.m_lastMeterUniqueID + ", current meter ID = " + this.m_currentMeterUniqueID + "; different = " + bool);
/*     */ 
/* 431 */     return bool;
/*     */   }
/*     */ 
/*     */   private boolean isDifferentCGM()
/*     */   {
/* 442 */     Contract.preNonNull(this.m_currentCGMUniqueID);
/*     */ 
/* 446 */     if (this.m_lastCGMUniqueID == null)
/*     */     {
/* 448 */       String str1 = getWizard().getConfig().getLastCGMUniqueID();
/* 449 */       if (str1 != null)
/*     */       {
/* 455 */         DeviceID localDeviceID = new DeviceID(str1);
/*     */ 
/* 457 */         String str2 = Wizard.resolveServerClassName(localDeviceID.getClassName());
/*     */ 
/* 460 */         this.m_lastCGMUniqueID = new DeviceID(str2, localDeviceID.getSerialNumber());
/*     */       }
/*     */     }
/*     */     boolean bool;
/* 465 */     if (this.m_lastCGMUniqueID != null)
/*     */     {
/* 467 */       bool = !this.m_currentCGMUniqueID.equals(this.m_lastCGMUniqueID);
/*     */     }
/*     */     else {
/* 470 */       bool = false;
/*     */     }
/*     */ 
/* 473 */     logInfo("isDifferentCGM: last CGM ID = " + this.m_lastCGMUniqueID + ", current CGM ID = " + this.m_currentCGMUniqueID + "; different = " + bool);
/*     */ 
/* 476 */     return bool;
/*     */   }
/*     */ 
/*     */   private DeviceID createUniqueID()
/*     */   {
/* 487 */     String str1 = Wizard.mapToServerClassName(getWizardSelections().getDeviceSelection());
/*     */ 
/* 489 */     CaptureResult localCaptureResult = getWizard().getCaptureResult();
/* 490 */     String str2 = localCaptureResult.getSerialNumber();
/* 491 */     return new DeviceID(str1, str2);
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.steps.TransferDataCheckStep
 * JD-Core Version:    0.6.0
 */