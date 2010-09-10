/*     */ package minimed.ddms.deviceportreader;
/*     */ 
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ class MMX23 extends MMX22
/*     */ {
/*     */   public static final int SNAPSHOT_FORMAT_ID = 117;
/*     */   private static final int CMD_READ_VCNTR_HISTORY = 213;
/*     */   private static final int CMD_READ_OTHER_DEVICES_IDS = 240;
/*     */   private static final int VCNTR_HISTORY_REC_COUNT = 32;
/*     */   private static final int REC_SIZE_VCNTR_HISTORY = 1024;
/*     */   static final String MODEL_NUMBER1 = "523";
/*     */   static final String MODEL_NUMBER2 = "723";
/*     */   static final String MODEL_NUMBER3 = "523K";
/*     */   static final String MODEL_NUMBER4 = "723K";
/*     */   private static final int SETTINGINDEX_MAX_BOLUS = 5;
/*     */   private static final int SETTINGINDEX_MAX_BASAL = 7;
/*     */   private static final int SETTINGINDEX_TIME_FORMAT = 9;
/*     */   private static final int SETTINGINDEX_BOLUS_SCROLL_STEP_SIZE = 21;
/*     */   private static final int SETTINGINDEX_CAPTURE_EVENT_ENABLE = 22;
/*     */   private static final int SETTINGINDEX_OTHER_DEVICES_ENABLE = 23;
/*     */   private static final int SETTINGINDEX_OTHER_DEVICES_MARRIED = 24;
/*     */   private static final int MAX_BOLUS = 75;
/*     */ 
/*     */   public MMX23(int paramInt1, long paramLong1, long paramLong2, String paramString, int paramInt2)
/*     */   {
/* 104 */     super(paramInt1, paramLong1, paramLong2, paramString, paramInt2);
/*     */ 
/* 107 */     this.m_cmdReadSensorPredictiveAlerts = new MM511.Command(this, 209, "Read Sensor Predictive Alerts");
/*     */ 
/* 111 */     this.m_cmdReadSensorRateOfChangeAlerts = new MM511.Command(this, 212, "Read Sensor Rate Of Change Alerts");
/*     */ 
/* 115 */     this.m_cmdReadSensorDemoAndGraphTimeout = new MM511.Command(this, 210, "Read Sensor Demo and Graph Timeout");
/*     */ 
/* 119 */     this.m_cmdReadSensorAlarmSilence = new MM511.Command(this, 211, "Read Sensor Alarm Silence");
/*     */ 
/* 124 */     this.m_cmdReadSensorSettings = new MM511.Command(this, 207, "Read Sensor Settings");
/*     */ 
/* 128 */     this.m_cmdReadOtherDevicesIds = new MM511.Command(this, 240, "Read Other Devices ID");
/*     */ 
/* 131 */     this.m_cmdReadVcntrHistoryData = new MMX22.CommandReadSensorHistoryData(this, 213, "Read Vcntr History", 1024, 32);
/*     */ 
/* 134 */     this.m_snapshotCreator = new SnapshotCreator();
/*     */   }
/*     */ 
/*     */   MMX23(int paramInt, long paramLong1, long paramLong2)
/*     */   {
/* 151 */     this(paramInt, paramLong1, paramLong2, "MiniMed MMT-523/723 (Paradigm2) Insulin Pump", 117);
/*     */   }
/*     */ 
/*     */   static boolean isModelNumberSupported(String paramString)
/*     */   {
/* 166 */     return ("523".equals(paramString)) || ("723".equals(paramString)) || ("523K".equals(paramString)) || ("723K".equals(paramString));
/*     */   }
/*     */ 
/*     */   void decodeMaxBolus(int[] paramArrayOfInt)
/*     */     throws BadDeviceValueException
/*     */   {
/* 181 */     Contract.pre(paramArrayOfInt != null);
/* 182 */     Contract.pre(paramArrayOfInt.length == 64);
/*     */ 
/* 186 */     int i = MedicalDevice.Util.makeInt(paramArrayOfInt[5], paramArrayOfInt[6]);
/*     */ 
/* 188 */     MedicalDevice.Util.verifyDeviceValue(i, 0.0D, toBolusStrokes(75.0D), "Maximum Bolus Rate");
/* 189 */     this.m_settingMaxBolus = toBolusInsulin(i);
/*     */   }
/*     */ 
/*     */   void decodeCurrentSettings(int[] paramArrayOfInt)
/*     */     throws BadDeviceValueException
/*     */   {
/* 206 */     super.decodeCurrentSettings(paramArrayOfInt);
/*     */ 
/* 211 */     this.m_settingBolusScrollStepSize = paramArrayOfInt[21];
/*     */ 
/* 213 */     this.m_settingCaptureEventEnable = MedicalDevice.Util.parseEnable(paramArrayOfInt[22], "CaptureEvent Enable");
/*     */ 
/* 215 */     this.m_settingOtherDeviceEnable = MedicalDevice.Util.parseEnable(paramArrayOfInt[23], "OtherDevice Enable");
/*     */ 
/* 217 */     this.m_otherDeviceMarriedState = MedicalDevice.Util.parseEnable(paramArrayOfInt[24], "OtherDevice Married State");
/*     */ 
/* 220 */     logInfo(this, "decodeCurrentSettings: Bolus Scroll Step Size = " + this.m_settingBolusScrollStepSize);
/*     */ 
/* 222 */     logInfo(this, "decodeCurrentSettings: Capture Event Enable = " + this.m_settingCaptureEventEnable);
/*     */ 
/* 224 */     logInfo(this, "decodeCurrentSettings: Other Device Enable = " + this.m_settingOtherDeviceEnable);
/*     */ 
/* 226 */     logInfo(this, "decodeCurrentSettings: Other Device Married State = " + this.m_otherDeviceMarriedState);
/*     */   }
/*     */ 
/*     */   void decodeCurrentGlucoseHistoryPageNumber(int[] paramArrayOfInt)
/*     */   {
/* 239 */     super.decodeCurrentGlucoseHistoryPageNumber(paramArrayOfInt);
/*     */ 
/* 242 */     Contract.invariant(this.m_cmdReadGlucoseHistoryData.m_extraObject != null);
/* 243 */     int i = paramArrayOfInt[5];
/* 244 */     IntRange localIntRange = (IntRange)this.m_cmdReadGlucoseHistoryData.m_extraObject;
/* 245 */     this.m_cmdReadVcntrHistoryData.m_maxRecords = this.m_cmdReadGlucoseHistoryData.m_maxRecords;
/*     */ 
/* 248 */     this.m_cmdReadVcntrHistoryData.allocateRawData();
/* 249 */     calcTotalBytesToRead();
/*     */ 
/* 252 */     this.m_cmdReadVcntrHistoryData.m_extraObject = new IntRange(localIntRange.getMinimum(), localIntRange.getMaximum(), localIntRange.getDefault(), "Vcntr Page Range");
/*     */ 
/* 255 */     logInfo(this, "decodeCurrentGlucoseHistoryPageNumber: , available Vcntr pages = " + i + ", Vcntr pages to read = " + this.m_cmdReadVcntrHistoryData.m_maxRecords);
/*     */   }
/*     */ 
/*     */   int getSettingIndexMaxBasal()
/*     */   {
/* 266 */     return 7;
/*     */   }
/*     */ 
/*     */   int getSettingIndexTimeDisplayFormat()
/*     */   {
/* 275 */     return 9;
/*     */   }
/*     */ 
/*     */   private class SnapshotCreator extends MMX22.SnapshotCreator
/*     */   {
/*     */     private static final int SNAPSHOT_BYTES = 3162;
/*     */     private static final int SNAPCODE_SENSOR_PREDICTIVE_ALERTS = 37;
/*     */     private static final int SNAPCODE_SENSOR_GRAPH_DISPLAY = 38;
/*     */     private static final int SNAPCODE_SENSOR_ALARM_SILENCE = 39;
/*     */     private static final int SNAPCODE_SENSOR_ROC_ALERTS = 40;
/*     */     private static final int SNAPCODE_VCNTR_HISTORY_DATA = 34;
/*     */     private static final int SNAPCODE_OTHER_DEVICES = 42;
/*     */ 
/*     */     SnapshotCreator()
/*     */     {
/* 308 */       super(3162);
/*     */     }
/*     */ 
/*     */     void createSnapshotBody()
/*     */     {
/* 318 */       super.createSnapshotBody();
/*     */ 
/* 322 */       MMX23.this.m_snapshot.addElement(37, MMX23.this.m_cmdReadSensorPredictiveAlerts.m_rawData);
/*     */ 
/* 326 */       MMX23.this.m_snapshot.addElement(38, MMX23.this.m_cmdReadSensorDemoAndGraphTimeout.m_rawData);
/*     */ 
/* 330 */       MMX23.this.m_snapshot.addElement(39, MMX23.this.m_cmdReadSensorAlarmSilence.m_rawData);
/*     */ 
/* 334 */       MMX23.this.m_snapshot.addElement(40, MMX23.this.m_cmdReadSensorRateOfChangeAlerts.m_rawData);
/*     */ 
/* 339 */       MMX23.this.m_snapshot.addElement(34, MMX23.this.m_cmdReadVcntrHistoryData.m_rawData);
/*     */ 
/* 343 */       MMX23.this.m_snapshot.addElement(42, MMX23.this.m_cmdReadOtherDevicesIds.m_rawData);
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.deviceportreader.MMX23
 * JD-Core Version:    0.6.0
 */