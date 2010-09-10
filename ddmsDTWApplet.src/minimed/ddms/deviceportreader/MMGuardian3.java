/*     */ package minimed.ddms.deviceportreader;
/*     */ 
/*     */ class MMGuardian3 extends MMX22
/*     */ {
/*     */   public static final int SNAPSHOT_FORMAT_ID = 122;
/*     */   static final String MODEL_NUMBER1 = "7100";
/*     */   static final String MODEL_NUMBER2 = "7100B";
/*     */   static final String MODEL_NUMBER3 = "7200";
/*     */   static final String MODEL_NUMBER4 = "7100K";
/*     */   static final int CMD_READ_SENSOR_SETTINGS = 207;
/*     */   static final int CMD_READ_SENSOR_ALARM_SILENCE = 211;
/*     */   static final int CMD_READ_SENSOR_DEMO_AND_GRAPH_TIMEOUT = 210;
/*     */   static final int CMD_READ_SENSOR_PREDICTIVE_ALERTS = 209;
/*     */   static final int CMD_READ_SENSOR_RATE_OF_CHANGE_ALERTS = 212;
/*  81 */   private final MM511.Command m_cmdDummy = new MM511.Command(this, 0, "Dummy Command");
/*     */ 
/*     */   MMGuardian3(int paramInt, long paramLong1, long paramLong2)
/*     */   {
/*  99 */     super(paramInt, paramLong1, paramLong2, "MiniMed MMT-7100/7200 (Guardian3) CGM", 122);
/*     */ 
/* 106 */     this.m_cmdReadBGReminderEnable = null;
/* 107 */     this.m_cmdReadBGTargets = null;
/* 108 */     this.m_cmdReadBGUnits = null;
/* 109 */     this.m_cmdReadBolusReminderEnable = null;
/* 110 */     this.m_cmdReadBolusReminders = null;
/* 111 */     this.m_cmdReadBolusWizardSetupStatus = null;
/* 112 */     this.m_cmdReadCarbRatios = null;
/* 113 */     this.m_cmdReadCurrBasalDataA = null;
/* 114 */     this.m_cmdReadCurrBasalDataB = null;
/* 115 */     this.m_cmdReadCurrBasalDataSTD = null;
/* 116 */     this.m_cmdReadInsulinSensitivities = null;
/* 117 */     this.m_cmdReadRemainingInsulin = null;
/* 118 */     this.m_cmdReadRemoteIDs = null;
/* 119 */     this.m_cmdReadReservoirWarning = null;
/* 120 */     this.m_cmdReadTempBasal = null;
/* 121 */     this.m_cmdReadTodaysTotals = null;
/*     */ 
/* 124 */     this.m_cmdSetSuspend = null;
/* 125 */     this.m_cmdCancelSuspend = null;
/*     */ 
/* 127 */     this.m_snapshotCreator = new SnapshotCreator();
/*     */   }
/*     */ 
/*     */   int getDeviceType()
/*     */   {
/* 142 */     return 2;
/*     */   }
/*     */ 
/*     */   static boolean isModelNumberSupported(String paramString)
/*     */   {
/* 152 */     return ("7100".equals(paramString)) || ("7100B".equals(paramString)) || ("7200".equals(paramString)) || ("7100K".equals(paramString));
/*     */   }
/*     */ 
/*     */   final void initDeviceAfterModelNumberKnown()
/*     */     throws BadDeviceCommException, ConnectToPumpException
/*     */   {
/* 168 */     super.initDeviceAfterModelNumberKnown();
/*     */ 
/* 171 */     if (!"7200".equals(this.m_modelNumber))
/*     */     {
/* 173 */       this.m_cmdReadSensorSettings = new MM511.Command(this, 207, "Read Sensor Settings");
/*     */ 
/* 179 */       this.m_cmdReadSensorPredictiveAlerts = new MM511.Command(this, 209, "Read Sensor Predictive Alerts");
/*     */ 
/* 181 */       this.m_cmdReadSensorRateOfChangeAlerts = new MM511.Command(this, 212, "Read Sensor Rate Of Change Alerts");
/*     */ 
/* 183 */       this.m_cmdReadSensorDemoAndGraphTimeout = new MM511.Command(this, 210, "Read Sensor Demo and Graph Timeout");
/*     */ 
/* 185 */       this.m_cmdReadSensorAlarmSilence = new MM511.Command(this, 211, "Read Sensor Alarm Silence");
/*     */     }
/*     */   }
/*     */   class SnapshotCreator extends MedicalDevice.SnapshotCreator { private static final int MIN_SNAPSHOT_BYTES = 1657;
/*     */     private static final int REC_SIZE_MIN = 64;
/*     */     private static final int SNAPCODE_SETTINGS = 1;
/*     */     private static final int SNAPCODE_HISTORY = 4;
/*     */     private static final int SNAPCODE_BATTERY_STATUS = 8;
/*     */     private static final int SNAPCODE_ERROR_STATUS = 10;
/*     */     private static final int SNAPCODE_PUMP_STATE = 12;
/*     */     private static final int SNAPCODE_LANGUAGE = 13;
/*     */     private static final int SNAPCODE_BG_ALARM_CLOCKS = 17;
/*     */     private static final int SNAPCODE_CARB_UNITS = 19;
/*     */     private static final int SNAPCODE_PUMP_MODEL = 23;
/*     */     private static final int SNAPCODE_PARADIGM_LINKS = 24;
/*     */     private static final int SNAPCODE_BG_ALARM = 25;
/*     */     private static final int SNAPCODE_HISTORY_PAGE_NUMBER = 26;
/*     */     private static final int SNAPCODE_LCD_CONSTRAST = 27;
/*     */     private static final int SNAPCODE_FACTORY_PARAMS = 30;
/*     */     private static final int SNAPCODE_SAVED_SETTINGS_DATE = 31;
/*     */     private static final int SNAPCODE_GLUCOSE_HISTORY_PAGE_NUMBER = 32;
/*     */     private static final int SNAPCODE_GLUCOSE_HISTORY_DATA = 33;
/*     */     private static final int SNAPCODE_ISIG_HISTORY_DATA = 10000;
/*     */     private static final int SNAPCODE_CALIBRATION_FACTOR = 35;
/*     */     private static final int SNAPCODE_SENSOR_SETTINGS = 36;
/*     */     private static final int SNAPCODE_SENSOR_PREDICTIVE_ALERTS = 37;
/*     */     private static final int SNAPCODE_SENSOR_GRAPH_DISPLAY = 38;
/*     */     private static final int SNAPCODE_SENSOR_ALARM_SILENCE = 39;
/*     */     private static final int SNAPCODE_SENSOR_ROC_ALERTS = 40;
/*     */ 
/* 240 */     SnapshotCreator() { this(1657);
/*     */     }
/*     */ 
/*     */     SnapshotCreator(int arg2)
/*     */     {
/* 250 */       super(i);
/* 251 */       MMGuardian3.this.m_snapshotFirmwareCount = 64;
/* 252 */       MMGuardian3.this.m_snapshotSerialCount = 64;
/* 253 */       MMGuardian3.this.m_snapshotTimeCount = 64;
/*     */     }
/*     */ 
/*     */     void createSnapshotBody()
/*     */     {
/* 263 */       MMGuardian3.this.m_snapshot = new Snapshot(MMGuardian3.this.m_snapshotFormatID, 1, MMGuardian3.this.m_cmdReadFirmwareVersion.m_rawData, MMGuardian3.this.m_cmdReadPumpId.m_rawData, MMGuardian3.this.m_cmdReadRealTimeClock.m_rawData);
/*     */ 
/* 271 */       MedicalDevice.logInfo(this, "createSnapshot: creating snapshot");
/*     */ 
/* 274 */       MMGuardian3.this.m_snapshot.addElement(1, MMGuardian3.this.m_cmdReadCurrentSettings1.m_rawData);
/*     */ 
/* 279 */       MMGuardian3.this.m_snapshot.addElement(4, MMGuardian3.this.m_cmdReadHistoryData.m_rawData);
/*     */ 
/* 282 */       MMGuardian3.this.m_snapshot.addElement(8, MMGuardian3.this.m_cmdReadBatteryStatus.m_rawData);
/*     */ 
/* 285 */       MMGuardian3.this.m_snapshot.addElement(10, MMGuardian3.this.m_cmdReadErrorStatus.m_rawData);
/*     */ 
/* 288 */       MMGuardian3.this.m_snapshot.addElement(12, MMGuardian3.this.m_cmdReadState.m_rawData);
/*     */ 
/* 291 */       MMGuardian3.this.m_snapshot.addElement(13, MMGuardian3.this.m_cmdReadLanguage.m_rawData);
/*     */ 
/* 294 */       MMGuardian3.this.m_snapshot.addElement(17, MMGuardian3.this.m_cmdReadBGAlarmClocks.m_rawData);
/*     */ 
/* 297 */       MMGuardian3.this.m_snapshot.addElement(19, MMGuardian3.this.m_cmdReadCarbUnits.m_rawData);
/*     */ 
/* 300 */       MMGuardian3.this.m_snapshot.addElement(23, MMGuardian3.this.m_cmdReadPumpModelNumber.m_rawData);
/*     */ 
/* 303 */       MMGuardian3.this.m_snapshot.addElement(24, MMGuardian3.this.m_cmdReadParadigmLinkIds.m_rawData);
/*     */ 
/* 306 */       MMGuardian3.this.m_snapshot.addElement(25, MMGuardian3.this.m_cmdReadBGAlarmEnable.m_rawData);
/*     */ 
/* 309 */       MMGuardian3.this.m_snapshot.addElement(26, MMGuardian3.this.m_cmdReadCurrentHistoryPageNumber.m_rawData);
/*     */ 
/* 313 */       MMGuardian3.this.m_snapshot.addElement(27, MMGuardian3.this.m_cmdReadContrast.m_rawData);
/*     */ 
/* 316 */       MMGuardian3.this.m_snapshot.addElement(30, MMGuardian3.this.m_cmdReadFactoryParameters.m_rawData);
/*     */ 
/* 319 */       MMGuardian3.this.m_snapshot.addElement(31, MMGuardian3.this.m_cmdReadSavedSettingsDate.m_rawData);
/*     */ 
/* 323 */       MMGuardian3.this.m_snapshot.addElement(32, MMGuardian3.this.m_cmdReadCurrentGlucoseHistoryPageNumber.m_rawData);
/*     */ 
/* 327 */       MMGuardian3.this.m_snapshot.addElement(33, MMGuardian3.this.m_cmdReadGlucoseHistoryData.m_rawData);
/*     */ 
/* 331 */       MMGuardian3.this.m_snapshot.addElement(10000, MMGuardian3.this.m_cmdReadIsigHistoryData.m_rawData);
/*     */ 
/* 334 */       MMGuardian3.this.m_snapshot.addElement(35, MMGuardian3.this.m_cmdReadCalibrationFactor.m_rawData);
/*     */ 
/* 338 */       MMGuardian3.this.m_snapshot.addElement(36, MMGuardian3.this.m_cmdReadSensorSettings.m_rawData);
/*     */ 
/* 341 */       if ("7200".equals(MMGuardian3.this.m_modelNumber))
/*     */       {
/* 344 */         MMGuardian3.this.m_snapshot.addElement(37, MMGuardian3.this.m_cmdDummy.m_rawData);
/*     */ 
/* 348 */         MMGuardian3.this.m_snapshot.addElement(38, MMGuardian3.this.m_cmdDummy.m_rawData);
/*     */ 
/* 352 */         MMGuardian3.this.m_snapshot.addElement(39, MMGuardian3.this.m_cmdDummy.m_rawData);
/*     */ 
/* 356 */         MMGuardian3.this.m_snapshot.addElement(40, MMGuardian3.this.m_cmdDummy.m_rawData);
/*     */       }
/*     */       else
/*     */       {
/* 360 */         MMGuardian3.this.m_snapshot.addElement(37, MMGuardian3.this.m_cmdReadSensorPredictiveAlerts.m_rawData);
/*     */ 
/* 364 */         MMGuardian3.this.m_snapshot.addElement(38, MMGuardian3.this.m_cmdReadSensorDemoAndGraphTimeout.m_rawData);
/*     */ 
/* 368 */         MMGuardian3.this.m_snapshot.addElement(39, MMGuardian3.this.m_cmdReadSensorAlarmSilence.m_rawData);
/*     */ 
/* 372 */         MMGuardian3.this.m_snapshot.addElement(40, MMGuardian3.this.m_cmdReadSensorRateOfChangeAlerts.m_rawData);
/*     */       }
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.deviceportreader.MMGuardian3
 * JD-Core Version:    0.6.0
 */