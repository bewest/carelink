/*     */ package minimed.ddms.deviceportreader;
/*     */ 
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ class MMX15 extends MMX12
/*     */ {
/*     */   static final int SNAPSHOT_FORMAT_ID = 115;
/*     */   static final String MODEL_NUMBER1 = "515";
/*     */   static final String MODEL_NUMBER2 = "715";
/*     */   private static final int CMD_READ_SETTINGS = 192;
/*     */   private static final int CMD_READ_BG_TARGETS = 159;
/*     */   private static final int CMD_READ_CURRENT_HISTORY_PAGE_NUMBER = 157;
/*     */   private static final int CMD_READ_SAVED_SETTINGS_DATE = 193;
/*     */   private static final int CMD_READ_CONSTRAST = 195;
/*     */   private static final int CMD_READ_BOLUS_REMINDER_ENABLE = 197;
/*     */   private static final int CMD_READ_BOLUS_REMINDERS = 198;
/*     */   private static final int CMD_READ_FACTORY_PARAMETERS = 199;
/*     */   private static final int CMD_READ_CURRENT_PUMP_STATUS = 206;
/*     */   private static final int SETTINGINDEX_INSULIN_ACTION_CURVE = 17;
/*     */   private static final int SETTINGINDEX_LOW_RESERVOIR_WARN_TYPE = 18;
/*     */   private static final int SETTINGINDEX_LOW_RESERVOIR_WARN_POINT = 19;
/*     */   private static final int SETTINGINDEX_KEYPAD_LOCK_STATUS = 20;
/*     */   private static final int REC_SIZE_MIN = 64;
/*     */   private static final int MAX_HISTORY_REC_COUNT = 36;
/*     */   private static final int INSULIN_ACTION_CURVE_MIN = 2;
/*     */   private static final int INSULIN_ACTION_CURVE_MAX = 8;
/*     */   private static final int INSULIN_ACTION_CURVE_UNSET = 15;
/*     */   private static final int PUMP_NORMAL_STATE = 3;
/*     */   private boolean m_pumpStatusBolusing;
/*     */   private boolean m_pumpStatusSuspended;
/*     */ 
/*     */   MMX15(int paramInt1, long paramLong, String paramString, int paramInt2)
/*     */   {
/* 122 */     super(paramInt1, paramString, 13, paramInt2, 3);
/*     */ 
/* 130 */     this.m_cmdReadCurrentSettings1 = new MM511.Command(this, 192, "Read Current Settings");
/* 131 */     this.m_cmdReadBGTargets = new MM511.Command(this, 159, "Read BG Target Ranges");
/*     */ 
/* 137 */     this.m_cmdReadCurrentHistoryPageNumber = new MM511.Command(this, 157, "Read Current History Download Page Number");
/*     */ 
/* 141 */     this.m_cmdReadSavedSettingsDate = new MM511.Command(this, 193, "Read Saved Settings Date");
/*     */ 
/* 144 */     this.m_cmdReadContrast = new MM511.Command(this, 195, "Read Contrast");
/*     */ 
/* 147 */     this.m_cmdReadBolusReminderEnable = new MM511.Command(this, 197, "Read Bolus Reminder On/Off");
/*     */ 
/* 150 */     this.m_cmdReadBolusReminders = new MM511.Command(this, 198, "Read Bolus Reminders");
/*     */ 
/* 153 */     this.m_cmdReadFactoryParameters = new MM511.Command(this, 199, "Read Factory Parameters");
/*     */ 
/* 157 */     this.m_cmdDetectBolus = new MM511.Command(this, 206, "Read Current Pump Status");
/*     */ 
/* 159 */     this.m_cmdDetectBolus.m_maxRetries = 0;
/*     */ 
/* 161 */     this.m_snapshotCreator = new SnapshotCreator();
/* 162 */     this.m_lastHistoryPageNumber = paramLong;
/*     */   }
/*     */ 
/*     */   MMX15(int paramInt, long paramLong)
/*     */   {
/* 176 */     this(paramInt, paramLong, "MiniMed MMT-515/715 (Paradigm2) Insulin Pump", 115);
/*     */   }
/*     */ 
/*     */   static boolean isModelNumberSupported(String paramString)
/*     */   {
/* 187 */     return ("515".equals(paramString)) || ("715".equals(paramString));
/*     */   }
/*     */ 
/*     */   boolean detectActiveBolus()
/*     */     throws BadDeviceCommException, BadDeviceValueException
/*     */   {
/* 199 */     this.m_cmdDetectBolus.execute();
/* 200 */     decodeReply(this.m_cmdDetectBolus);
/* 201 */     return this.m_pumpStatusBolusing;
/*     */   }
/*     */ 
/*     */   void decodeReply(MMPump.Command paramCommand)
/*     */     throws BadDeviceValueException
/*     */   {
/* 213 */     switch (paramCommand.m_commandCode)
/*     */     {
/*     */     case 192:
/* 218 */       decodeCurrentSettings(paramCommand.m_rawData);
/* 219 */       break;
/*     */     case 157:
/* 225 */       this.m_cmdReadHistoryData.m_maxRecords = decodeCurrentHistoryPageNumber(paramCommand.m_rawData);
/*     */ 
/* 228 */       this.m_cmdReadHistoryData.allocateRawData();
/* 229 */       calcTotalBytesToRead();
/* 230 */       break;
/*     */     case 206:
/* 233 */       decodePumpStatus(paramCommand.m_rawData);
/* 234 */       break;
/*     */     default:
/* 240 */       super.decodeReply(paramCommand);
/*     */     }
/*     */   }
/*     */ 
/*     */   void decodeCurrentSettings(int[] paramArrayOfInt)
/*     */     throws BadDeviceValueException
/*     */   {
/* 259 */     super.decodeCurrentSettings(paramArrayOfInt);
/*     */ 
/* 266 */     decodeInsulinActionSetting(paramArrayOfInt);
/*     */ 
/* 273 */     this.m_lowReservoirWarnType = paramArrayOfInt[18];
/*     */ 
/* 276 */     this.m_lowReservoirWarnPoint = paramArrayOfInt[19];
/*     */ 
/* 279 */     this.m_keypadLockStatus = paramArrayOfInt[20];
/*     */ 
/* 281 */     logInfo(this, "decodeCurrentSettings: Temp Basal Type = " + (this.m_tempBasalType == 0 ? "Units Per Hour" : "Percent"));
/*     */ 
/* 283 */     logInfo(this, "decodeCurrentSettings: Temp Basal Percent = " + this.m_tempBasalPercent);
/* 284 */     logInfo(this, "decodeCurrentSettings: ParadigmLink Enable = " + this.m_paradigmLinkEnable);
/* 285 */     logInfo(this, "decodeCurrentSettings: Reservoir Warning Type = " + (this.m_lowReservoirWarnType == 0 ? "units" : "time"));
/*     */ 
/* 287 */     logInfo(this, "decodeCurrentSettings: Reservoir Warning Point = " + this.m_lowReservoirWarnPoint);
/*     */ 
/* 289 */     logInfo(this, "decodeCurrentSettings: Keypad Locked = " + this.m_keypadLockStatus);
/*     */   }
/*     */ 
/*     */   void decodeInsulinActionSetting(int[] paramArrayOfInt)
/*     */     throws BadDeviceValueException
/*     */   {
/* 301 */     this.m_insulinActionType = paramArrayOfInt[17];
/* 302 */     if (this.m_insulinActionType != 15) {
/* 303 */       MedicalDevice.Util.verifyDeviceValue(this.m_insulinActionType, 2, 8, "Insulin Action Curve");
/*     */     }
/*     */ 
/* 306 */     logInfo(this, "decodeCurrentSettings: Insulin Action Curve = " + (this.m_insulinActionType == 15 ? "unset" : Integer.toString(this.m_insulinActionType)));
/*     */   }
/*     */ 
/*     */   private void decodePumpStatus(int[] paramArrayOfInt)
/*     */     throws BadDeviceValueException
/*     */   {
/* 321 */     Contract.pre(paramArrayOfInt != null);
/* 322 */     Contract.pre(paramArrayOfInt.length == 64);
/*     */ 
/* 328 */     this.m_pumpStatusBolusing = MedicalDevice.Util.parseEnable(paramArrayOfInt[1], "bolusing flag");
/*     */ 
/* 331 */     this.m_pumpStatusSuspended = MedicalDevice.Util.parseEnable(paramArrayOfInt[2], "suspended flag");
/*     */ 
/* 333 */     logInfo(this, "decodePumpStatus: bolusing=" + this.m_pumpStatusBolusing + ", suspended=" + this.m_pumpStatusSuspended);
/*     */   }
/*     */ 
/*     */   private int decodeCurrentHistoryPageNumber(int[] paramArrayOfInt)
/*     */     throws BadDeviceValueException
/*     */   {
/* 347 */     Contract.pre(paramArrayOfInt != null);
/* 348 */     Contract.pre(paramArrayOfInt.length == 64);
/*     */ 
/* 356 */     this.m_currentHistoryPageNumber = (int)MedicalDevice.Util.makeLong(paramArrayOfInt[0], paramArrayOfInt[1], paramArrayOfInt[2], paramArrayOfInt[3]);
/*     */ 
/* 360 */     int i = this.m_currentHistoryPageNumber - (int)this.m_lastHistoryPageNumber + 1;
/*     */ 
/* 363 */     if ((i <= 0) || (i > 36)) {
/* 364 */       i = 36;
/*     */     }
/*     */ 
/* 367 */     logInfo(this, "decodeCurrentHistoryPageNumber: current page number (pump) = " + this.m_currentHistoryPageNumber + ", last page number (system) = " + this.m_lastHistoryPageNumber + ", pages to read = " + i);
/*     */ 
/* 370 */     return i; } 
/*     */   class SnapshotCreator extends MedicalDevice.SnapshotCreator { private static final int SNAPSHOT_BYTES = 2598;
/*     */     private static final int SNAPCODE_SETTINGS = 1;
/*     */     private static final int SNAPCODE_TODAYS_TOTAL_INSULIN = 2;
/*     */     private static final int SNAPCODE_TEMP_BASAL = 3;
/*     */     private static final int SNAPCODE_HISTORY = 4;
/*     */     private static final int SNAPCODE_CURRENT_BASAL_STD = 5;
/*     */     private static final int SNAPCODE_CURRENT_BASAL_A = 6;
/*     */     private static final int SNAPCODE_CURRENT_BASAL_B = 7;
/*     */     private static final int SNAPCODE_BATTERY_STATUS = 8;
/*     */     private static final int SNAPCODE_REMAINING_INSULIN = 9;
/*     */     private static final int SNAPCODE_ERROR_STATUS = 10;
/*     */     private static final int SNAPCODE_REMOTE_IDS = 11;
/*     */     private static final int SNAPCODE_PUMP_STATE = 12;
/*     */     private static final int SNAPCODE_LANGUAGE = 13;
/*     */     private static final int SNAPCODE_BOLUS_WIZARD_SETUP = 14;
/*     */     private static final int SNAPCODE_BG_UNITS = 15;
/*     */     private static final int SNAPCODE_BG_TARGET_RANGES = 16;
/*     */     private static final int SNAPCODE_ALARM_CLOCKS = 17;
/*     */     private static final int SNAPCODE_BG_REMINDER_ENABLE = 18;
/*     */     private static final int SNAPCODE_CARB_UNITS = 19;
/*     */     private static final int SNAPCODE_CARB_RATIOS = 20;
/*     */     private static final int SNAPCODE_INSULIN_SENS = 21;
/*     */     private static final int SNAPCODE_RESERVOIR_WARN = 22;
/*     */     private static final int SNAPCODE_PUMP_MODEL = 23;
/*     */     private static final int SNAPCODE_PARADIGM_LINKS = 24;
/*     */     private static final int SNAPCODE_ALARM_ENABLE = 25;
/*     */     private static final int SNAPCODE_HISTORY_PAGE_NUMBER = 26;
/*     */     private static final int SNAPCODE_LCD_CONSTRAST = 27;
/*     */     private static final int SNAPCODE_BOLUS_REMINDER_ENABLE = 28;
/*     */     private static final int SNAPCODE_BOLUS_REMINDERS = 29;
/*     */     private static final int SNAPCODE_FACTORY_PARAMS = 30;
/*     */     private static final int SNAPCODE_SAVED_SETTINGS_DATE = 31;
/*     */ 
/* 426 */     SnapshotCreator() { this(2598);
/*     */     }
/*     */ 
/*     */     SnapshotCreator(int arg2)
/*     */     {
/* 436 */       super(i);
/* 437 */       MMX15.this.m_snapshotFirmwareCount = 64;
/* 438 */       MMX15.this.m_snapshotSerialCount = 64;
/* 439 */       MMX15.this.m_snapshotTimeCount = 64;
/*     */     }
/*     */ 
/*     */     void createSnapshotBody()
/*     */     {
/* 449 */       MMX15.this.m_snapshot = new Snapshot(MMX15.this.m_snapshotFormatID, 1, MMX15.this.m_cmdReadFirmwareVersion.m_rawData, MMX15.this.m_cmdReadPumpId.m_rawData, MMX15.this.m_cmdReadRealTimeClock.m_rawData);
/*     */ 
/* 457 */       MedicalDevice.logInfo(this, "createSnapshot: creating snapshot");
/*     */ 
/* 460 */       MMX15.this.m_snapshot.addElement(1, MMX15.this.m_cmdReadCurrentSettings1.m_rawData);
/*     */ 
/* 464 */       MMX15.this.m_snapshot.addElement(2, MMX15.this.m_cmdReadTodaysTotals.m_rawData);
/*     */ 
/* 468 */       MMX15.this.m_snapshot.addElement(3, MMX15.this.m_cmdReadTempBasal.m_rawData);
/*     */ 
/* 472 */       MMX15.this.m_snapshot.addElement(4, MMX15.this.m_cmdReadHistoryData.m_rawData);
/*     */ 
/* 475 */       MMX15.this.m_snapshot.addElement(5, MMX15.this.m_cmdReadCurrBasalDataSTD.m_rawData);
/*     */ 
/* 479 */       MMX15.this.m_snapshot.addElement(6, MMX15.this.m_cmdReadCurrBasalDataA.m_rawData);
/*     */ 
/* 482 */       MMX15.this.m_snapshot.addElement(7, MMX15.this.m_cmdReadCurrBasalDataB.m_rawData);
/*     */ 
/* 485 */       MMX15.this.m_snapshot.addElement(8, MMX15.this.m_cmdReadBatteryStatus.m_rawData);
/*     */ 
/* 488 */       MMX15.this.m_snapshot.addElement(9, MMX15.this.m_cmdReadRemainingInsulin.m_rawData);
/*     */ 
/* 492 */       MMX15.this.m_snapshot.addElement(10, MMX15.this.m_cmdReadErrorStatus.m_rawData);
/*     */ 
/* 495 */       MMX15.this.m_snapshot.addElement(11, MMX15.this.m_cmdReadRemoteIDs.m_rawData);
/*     */ 
/* 498 */       MMX15.this.m_snapshot.addElement(12, MMX15.this.m_cmdReadState.m_rawData);
/*     */ 
/* 501 */       MMX15.this.m_snapshot.addElement(13, MMX15.this.m_cmdReadLanguage.m_rawData);
/*     */ 
/* 504 */       MMX15.this.m_snapshot.addElement(14, MMX15.this.m_cmdReadBolusWizardSetupStatus.m_rawData);
/*     */ 
/* 508 */       MMX15.this.m_snapshot.addElement(15, MMX15.this.m_cmdReadBGUnits.m_rawData);
/*     */ 
/* 511 */       MMX15.this.m_snapshot.addElement(16, MMX15.this.m_cmdReadBGTargets.m_rawData);
/*     */ 
/* 514 */       MMX15.this.m_snapshot.addElement(17, MMX15.this.m_cmdReadBGAlarmClocks.m_rawData);
/*     */ 
/* 517 */       MMX15.this.m_snapshot.addElement(18, MMX15.this.m_cmdReadBGReminderEnable.m_rawData);
/*     */ 
/* 520 */       MMX15.this.m_snapshot.addElement(19, MMX15.this.m_cmdReadCarbUnits.m_rawData);
/*     */ 
/* 523 */       MMX15.this.m_snapshot.addElement(20, MMX15.this.m_cmdReadCarbRatios.m_rawData);
/*     */ 
/* 526 */       MMX15.this.m_snapshot.addElement(21, MMX15.this.m_cmdReadInsulinSensitivities.m_rawData);
/*     */ 
/* 529 */       MMX15.this.m_snapshot.addElement(22, MMX15.this.m_cmdReadReservoirWarning.m_rawData);
/*     */ 
/* 532 */       MMX15.this.m_snapshot.addElement(23, MMX15.this.m_cmdReadPumpModelNumber.m_rawData);
/*     */ 
/* 535 */       MMX15.this.m_snapshot.addElement(24, MMX15.this.m_cmdReadParadigmLinkIds.m_rawData);
/*     */ 
/* 538 */       MMX15.this.m_snapshot.addElement(25, MMX15.this.m_cmdReadBGAlarmEnable.m_rawData);
/*     */ 
/* 543 */       MMX15.this.m_snapshot.addElement(26, MMX15.this.m_cmdReadCurrentHistoryPageNumber.m_rawData);
/*     */ 
/* 547 */       MMX15.this.m_snapshot.addElement(27, MMX15.this.m_cmdReadContrast.m_rawData);
/*     */ 
/* 550 */       MMX15.this.m_snapshot.addElement(28, MMX15.this.m_cmdReadBolusReminderEnable.m_rawData);
/*     */ 
/* 554 */       MMX15.this.m_snapshot.addElement(29, MMX15.this.m_cmdReadBolusReminders.m_rawData);
/*     */ 
/* 557 */       MMX15.this.m_snapshot.addElement(30, MMX15.this.m_cmdReadFactoryParameters.m_rawData);
/*     */ 
/* 560 */       MMX15.this.m_snapshot.addElement(31, MMX15.this.m_cmdReadSavedSettingsDate.m_rawData);
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.deviceportreader.MMX15
 * JD-Core Version:    0.6.0
 */