/*     */ package minimed.ddms.deviceportreader;
/*     */ 
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ class MMX12 extends MM511
/*     */ {
/*     */   static final int SNAPSHOT_FORMAT_ID = 114;
/*     */   static final String MODEL_NUMBER1 = "512";
/*     */   static final String MODEL_NUMBER2 = "712";
/*     */   private static final int CMD_READ_SETTINGS = 145;
/*     */   private static final int CMD_READ_TEMP_BASAL = 152;
/*     */   private static final int CMD_READ_STD_PROFILES = 146;
/*     */   private static final int CMD_READ_A_PROFILES = 147;
/*     */   private static final int CMD_READ_B_PROFILES = 148;
/*     */   private static final int CMD_TEMP_BASAL_RATE = 76;
/*     */   private static final int CMD_READ_BG_ALARM_CLOCKS = 142;
/*     */   private static final int CMD_READ_BG_ALARM_ENABLE = 151;
/*     */   private static final int CMD_READ_BG_REMINDER_ENABLE = 144;
/*     */   private static final int CMD_READ_BG_TARGETS = 140;
/*     */   private static final int CMD_READ_BG_UNITS = 137;
/*     */   private static final int CMD_READ_BOLUS_WIZARD_SETUP_STATUS = 135;
/*     */   private static final int CMD_READ_CARB_RATIOS = 138;
/*     */   private static final int CMD_READ_CARB_UNITS = 136;
/*     */   private static final int CMD_READ_LOGIC_LINK_IDS = 149;
/*     */   private static final int CMD_READ_INSULIN_SENSITIVITIES = 139;
/*     */   private static final int CMD_READ_RESERVOIR_WARNING = 143;
/*     */   private static final int CMD_READ_PUMP_MODEL_NUMBER = 141;
/*     */   private static final int CMD_READ_LANGUAGE = 134;
/*     */   private static final int SETTINGINDEX_TEMP_BASAL_TYPE = 14;
/*     */   private static final int SETTINGINDEX_TEMP_BASAL_PERCENT = 15;
/*     */   private static final int SETTINGINDEX_PARADIGMLINK = 16;
/*     */   private static final int SETTINGINDEX_INSULIN_ACTION_TYPE = 17;
/*     */   private static final int REC_SIZE_MIN = 64;
/*     */   private static final int REC_SIZE_PROFILE = 192;
/*     */   private static final int REC_SIZE_ONE = 1;
/*     */   private static final int HISTORY_REC_COUNT = 36;
/*     */   private static final int MAX_TEMP_BASAL_PERCENT = 200;
/*     */   private static final int STROKES_PER_BOLUS_UNIT = 10;
/*     */   private static final int STROKES_PER_BASAL_UNIT = 40;
/*     */   private static final int PUMP_NORMAL_STATE = 3;
/*     */ 
/*     */   MMX12(int paramInt1, String paramString, int paramInt2, int paramInt3, int paramInt4)
/*     */   {
/* 147 */     super(paramInt1, paramString, paramInt2, paramInt3, paramInt4);
/*     */ 
/* 150 */     this.m_strokesPerBasalUnit = 40;
/* 151 */     this.m_strokesPerBolusUnit = 10;
/*     */ 
/* 158 */     this.m_cmdReadCurrentSettings1 = new MM511.Command(this, 145, "Read Current Settings");
/*     */ 
/* 160 */     this.m_cmdReadTempBasal = new MM511.Command(this, 152, "Read Temporary Basal");
/*     */ 
/* 163 */     this.m_cmdReadCurrBasalDataSTD = new MM511.Command(this, 146, "Read Standard Profiles Data", 192, 1, 8);
/*     */ 
/* 172 */     this.m_cmdReadCurrBasalDataA = new MM511.Command(this, 147, "Read Profiles A Data", 192, 1, 9);
/*     */ 
/* 181 */     this.m_cmdReadCurrBasalDataB = new MM511.Command(this, 148, "Read Profiles B Data", 192, 1, 10);
/*     */ 
/* 189 */     this.m_cmdReadHistoryData = new MM511.CommandHistoryData(this, 36);
/*     */ 
/* 194 */     this.m_cmdReadPumpModelNumber = new MM511.Command(this, 141, "Read Pump Model");
/*     */ 
/* 197 */     this.m_cmdReadBGAlarmClocks = new MM511.Command(this, 142, "Read BG Alarm Clocks");
/*     */ 
/* 200 */     this.m_cmdReadBGAlarmEnable = new MM511.Command(this, 151, "Read BG Alarm Enable");
/*     */ 
/* 203 */     this.m_cmdReadBGReminderEnable = new MM511.Command(this, 144, "Read BG Reminder Enable");
/*     */ 
/* 206 */     this.m_cmdReadBGTargets = new MM511.Command(this, 140, "Read BG Targets");
/*     */ 
/* 209 */     this.m_cmdReadBGUnits = new MM511.Command(this, 137, "Read BG Units");
/*     */ 
/* 211 */     this.m_cmdReadBolusWizardSetupStatus = new MM511.Command(this, 135, "Read Bolus Wizard Setup Status");
/*     */ 
/* 214 */     this.m_cmdReadCarbRatios = new MM511.Command(this, 138, "Read Carbohydrate Ratios");
/*     */ 
/* 217 */     this.m_cmdReadCarbUnits = new MM511.Command(this, 136, "Read Carbohydrate Units");
/*     */ 
/* 220 */     this.m_cmdReadParadigmLinkIds = new MM511.Command(this, 149, "Read ParadigmLink Ids");
/*     */ 
/* 223 */     this.m_cmdReadInsulinSensitivities = new MM511.Command(this, 139, "Read Insulin Sensitivities");
/*     */ 
/* 226 */     this.m_cmdReadReservoirWarning = new MM511.Command(this, 143, "Read Reservoir Warning");
/*     */ 
/* 229 */     this.m_cmdReadLanguage = new MM511.Command(this, 134, "Read Language");
/*     */ 
/* 233 */     this.m_cmdDetectBolus = new MM511.Command(this, 76, "Set Temp Basal Rate (bolus detection only)", 3);
/*     */ 
/* 235 */     this.m_cmdDetectBolus.m_commandParameters[0] = 0;
/* 236 */     this.m_cmdDetectBolus.m_commandParameters[1] = 0;
/* 237 */     this.m_cmdDetectBolus.m_commandParameters[2] = 0;
/* 238 */     this.m_cmdDetectBolus.m_maxRetries = 0;
/*     */ 
/* 240 */     this.m_snapshotCreator = new SnapshotCreator();
/*     */   }
/*     */ 
/*     */   MMX12(int paramInt)
/*     */   {
/* 252 */     this(paramInt, "MiniMed MMT-512/712 (Paradigm2) Insulin Pump", 13, 114, 3);
/*     */   }
/*     */ 
/*     */   static boolean isModelNumberSupported(String paramString)
/*     */   {
/* 263 */     return ("512".equals(paramString)) || ("712".equals(paramString));
/*     */   }
/*     */ 
/*     */   void decodeReply(MMPump.Command paramCommand)
/*     */     throws BadDeviceValueException
/*     */   {
/* 277 */     switch (paramCommand.m_commandCode)
/*     */     {
/*     */     case 145:
/* 282 */       decodeCurrentSettings(paramCommand.m_rawData);
/* 283 */       break;
/*     */     case 152:
/* 285 */       decodeTempBasal(paramCommand.m_rawData);
/* 286 */       break;
/*     */     case 141:
/* 291 */       decodeModelNumber(paramCommand.m_rawData);
/* 292 */       break;
/*     */     default:
/* 297 */       super.decodeReply(paramCommand);
/*     */     }
/*     */   }
/*     */ 
/*     */   void initDevice2()
/*     */     throws BadDeviceCommException, ConnectToPumpException
/*     */   {
/*     */   }
/*     */ 
/*     */   void shutDownPump2()
/*     */     throws BadDeviceCommException, SerialIOHaltedException
/*     */   {
/*     */   }
/*     */ 
/*     */   void initDeviceAfterModelNumberKnown()
/*     */     throws BadDeviceCommException, ConnectToPumpException
/*     */   {
/* 336 */     super.initDevice2();
/*     */   }
/*     */ 
/*     */   void decodeCurrentSettings(int[] paramArrayOfInt)
/*     */     throws BadDeviceValueException
/*     */   {
/* 355 */     super.decodeCurrentSettings(paramArrayOfInt);
/*     */ 
/* 362 */     this.m_tempBasalType = paramArrayOfInt[14];
/* 363 */     MedicalDevice.Util.verifyDeviceValue(this.m_tempBasalType, 0, 1, "Temp Basal Type");
/*     */ 
/* 366 */     this.m_tempBasalPercent = paramArrayOfInt[15];
/* 367 */     MedicalDevice.Util.verifyDeviceValue(this.m_tempBasalPercent, 0, 200, "Temp Basal Percent");
/*     */ 
/* 371 */     int i = paramArrayOfInt[16];
/* 372 */     MedicalDevice.Util.verifyDeviceValue(i, 0, 1, "ParadigmLink Enable");
/* 373 */     this.m_paradigmLinkEnable = (i == 1);
/*     */ 
/* 376 */     decodeInsulinActionSetting(paramArrayOfInt);
/*     */ 
/* 378 */     logInfo(this, "decodeCurrentSettings: Temp Basal Type = " + (this.m_tempBasalType == 0 ? "Units Per Hour" : "Percent"));
/*     */ 
/* 380 */     logInfo(this, "decodeCurrentSettings: Temp Basal Percent = " + this.m_tempBasalPercent);
/* 381 */     logInfo(this, "decodeCurrentSettings: ParadigmLink Enable = " + this.m_paradigmLinkEnable);
/*     */   }
/*     */ 
/*     */   void decodeInsulinActionSetting(int[] paramArrayOfInt)
/*     */     throws BadDeviceValueException
/*     */   {
/* 393 */     this.m_insulinActionType = paramArrayOfInt[17];
/* 394 */     MedicalDevice.Util.verifyDeviceValue(this.m_insulinActionType, 0, 1, "Insulin Action Type");
/* 395 */     logInfo(this, "decodeCurrentSettings: Insulin Action Type = " + (this.m_insulinActionType == 0 ? "Fast" : "Regular"));
/*     */   }
/*     */ 
/*     */   private void decodeModelNumber(int[] paramArrayOfInt)
/*     */     throws BadDeviceValueException
/*     */   {
/* 410 */     Contract.pre(paramArrayOfInt != null);
/* 411 */     Contract.pre(paramArrayOfInt.length == 64);
/*     */ 
/* 416 */     int i = paramArrayOfInt[0];
/* 417 */     int[] arrayOfInt = new int[i];
/* 418 */     System.arraycopy(paramArrayOfInt, 1, arrayOfInt, 0, arrayOfInt.length);
/* 419 */     this.m_modelNumber = MedicalDevice.Util.makeString(arrayOfInt);
/*     */ 
/* 421 */     logInfo(this, "decodeModelNumber: model = " + this.m_modelNumber);
/*     */   }
/*     */ 
/*     */   private void decodeTempBasal(int[] paramArrayOfInt)
/*     */     throws BadDeviceValueException
/*     */   {
/* 435 */     Contract.pre(paramArrayOfInt != null);
/* 436 */     Contract.pre(paramArrayOfInt.length == 64);
/*     */ 
/* 444 */     int i = MedicalDevice.Util.makeUnsignedShort(paramArrayOfInt[2], paramArrayOfInt[3]);
/* 445 */     MedicalDevice.Util.verifyDeviceValue(i, 0.0D, toBasalStrokes(35.0D), "Temporary Basal Rate");
/*     */ 
/* 450 */     this.m_settingTempBasalRate = toBasalInsulin(i);
/*     */ 
/* 453 */     this.m_settingTempBasalDurationMin = MedicalDevice.Util.makeUnsignedShort(paramArrayOfInt[4], paramArrayOfInt[5]);
/* 454 */     MedicalDevice.Util.verifyDeviceValue(this.m_settingTempBasalDurationMin, 0, 1440, "Temporary Basal Duration");
/*     */ 
/* 460 */     logInfo(this, "decodeTempBasal: Temp Basal Rate = " + this.m_settingTempBasalRate);
/* 461 */     logInfo(this, "decodeTempBasal: Temp Basal Remain Dur = " + this.m_settingTempBasalDurationMin + " minutes"); } 
/*     */   private final class SnapshotCreator extends MedicalDevice.SnapshotCreator { private static final int SNAPCODE_SETTINGS = 1;
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
/*     */     private static final int SNAPCODE_BG_TARGETS = 16;
/*     */     private static final int SNAPCODE_BG_ALARM_CLOCKS = 17;
/*     */     private static final int SNAPCODE_BG_REMINDER = 18;
/*     */     private static final int SNAPCODE_CARB_UNITS = 19;
/*     */     private static final int SNAPCODE_CARB_RATIOS = 20;
/*     */     private static final int SNAPCODE_INSULIN_SENS = 21;
/*     */     private static final int SNAPCODE_RESERVOIR_WARN = 22;
/*     */     private static final int SNAPCODE_PUMP_MODEL = 23;
/*     */     private static final int SNAPCODE_PARADIGM_LINKS = 24;
/*     */     private static final int SNAPCODE_BG_ALARM = 25;
/*     */     private static final int SNAPSHOT_BYTES = 2152;
/*     */ 
/* 512 */     SnapshotCreator() { super(2152);
/* 513 */       MMX12.this.m_snapshotFirmwareCount = 64;
/* 514 */       MMX12.this.m_snapshotSerialCount = 64;
/* 515 */       MMX12.this.m_snapshotTimeCount = 64;
/*     */     }
/*     */ 
/*     */     void createSnapshotBody()
/*     */     {
/* 525 */       MMX12.this.m_snapshot = new Snapshot(MMX12.this.m_snapshotFormatID, 1, MMX12.this.m_cmdReadFirmwareVersion.m_rawData, MMX12.this.m_cmdReadPumpId.m_rawData, MMX12.this.m_cmdReadRealTimeClock.m_rawData);
/*     */ 
/* 533 */       MedicalDevice.logInfo(this, "createSnapshot: creating snapshot");
/*     */ 
/* 536 */       MMX12.this.m_snapshot.addElement(1, MMX12.this.m_cmdReadCurrentSettings1.m_rawData);
/*     */ 
/* 540 */       MMX12.this.m_snapshot.addElement(2, MMX12.this.m_cmdReadTodaysTotals.m_rawData);
/*     */ 
/* 544 */       MMX12.this.m_snapshot.addElement(3, MMX12.this.m_cmdReadTempBasal.m_rawData);
/*     */ 
/* 548 */       MMX12.this.m_snapshot.addElement(4, MMX12.this.m_cmdReadHistoryData.m_rawData);
/*     */ 
/* 551 */       MMX12.this.m_snapshot.addElement(5, MMX12.this.m_cmdReadCurrBasalDataSTD.m_rawData);
/*     */ 
/* 555 */       MMX12.this.m_snapshot.addElement(6, MMX12.this.m_cmdReadCurrBasalDataA.m_rawData);
/*     */ 
/* 558 */       MMX12.this.m_snapshot.addElement(7, MMX12.this.m_cmdReadCurrBasalDataB.m_rawData);
/*     */ 
/* 561 */       MMX12.this.m_snapshot.addElement(8, MMX12.this.m_cmdReadBatteryStatus.m_rawData);
/*     */ 
/* 564 */       MMX12.this.m_snapshot.addElement(9, MMX12.this.m_cmdReadRemainingInsulin.m_rawData);
/*     */ 
/* 568 */       MMX12.this.m_snapshot.addElement(10, MMX12.this.m_cmdReadErrorStatus.m_rawData);
/*     */ 
/* 571 */       MMX12.this.m_snapshot.addElement(11, MMX12.this.m_cmdReadRemoteIDs.m_rawData);
/*     */ 
/* 574 */       MMX12.this.m_snapshot.addElement(12, MMX12.this.m_cmdReadState.m_rawData);
/*     */ 
/* 577 */       MMX12.this.m_snapshot.addElement(13, MMX12.this.m_cmdReadLanguage.m_rawData);
/*     */ 
/* 580 */       MMX12.this.m_snapshot.addElement(14, MMX12.this.m_cmdReadBolusWizardSetupStatus.m_rawData);
/*     */ 
/* 584 */       MMX12.this.m_snapshot.addElement(15, MMX12.this.m_cmdReadBGUnits.m_rawData);
/*     */ 
/* 587 */       MMX12.this.m_snapshot.addElement(16, MMX12.this.m_cmdReadBGTargets.m_rawData);
/*     */ 
/* 590 */       MMX12.this.m_snapshot.addElement(17, MMX12.this.m_cmdReadBGAlarmClocks.m_rawData);
/*     */ 
/* 593 */       MMX12.this.m_snapshot.addElement(18, MMX12.this.m_cmdReadBGReminderEnable.m_rawData);
/*     */ 
/* 596 */       MMX12.this.m_snapshot.addElement(19, MMX12.this.m_cmdReadCarbUnits.m_rawData);
/*     */ 
/* 599 */       MMX12.this.m_snapshot.addElement(20, MMX12.this.m_cmdReadCarbRatios.m_rawData);
/*     */ 
/* 602 */       MMX12.this.m_snapshot.addElement(21, MMX12.this.m_cmdReadInsulinSensitivities.m_rawData);
/*     */ 
/* 605 */       MMX12.this.m_snapshot.addElement(22, MMX12.this.m_cmdReadReservoirWarning.m_rawData);
/*     */ 
/* 608 */       MMX12.this.m_snapshot.addElement(23, MMX12.this.m_cmdReadPumpModelNumber.m_rawData);
/*     */ 
/* 611 */       MMX12.this.m_snapshot.addElement(24, MMX12.this.m_cmdReadParadigmLinkIds.m_rawData);
/*     */ 
/* 614 */       MMX12.this.m_snapshot.addElement(25, MMX12.this.m_cmdReadBGAlarmEnable.m_rawData);
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.deviceportreader.MMX12
 * JD-Core Version:    0.6.0
 */