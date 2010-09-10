/*     */ package minimed.ddms.deviceportreader;
/*     */ 
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ class MMX22 extends MMX15
/*     */ {
/*     */   public static final int SNAPSHOT_FORMAT_ID = 116;
/*     */   static final String MODEL_NUMBER1 = "522";
/*     */   static final String MODEL_NUMBER2 = "722";
/*     */   static final String MODEL_NUMBER3 = "522K";
/*     */   static final String MODEL_NUMBER4 = "722K";
/*     */   private static final int CMD_READ_CURRENT_GLUCOSE_HISTORY_PAGE_NUMBER = 205;
/*     */   private static final int CMD_READ_GLUCOSE_HISTORY = 154;
/*     */   private static final int CMD_READ_CALIBRATION_FACTOR = 156;
/*     */   private static final int CMD_READ_ISIG_HISTORY = 155;
/*     */   private static final int CMD_READ_SENSOR_SETTINGS = 153;
/*     */   private static final int CMD_WRITE_GLUCOSE_HISTORY_TIMESTAMP = 40;
/*     */   private static final int CMD_FILTER_BG = 168;
/*     */   private static final int CMD_FILTER_ISIG = 169;
/*     */   private static final int SETTINGINDEX_SENSOR_ENABLE = 0;
/*     */   private static final int SETTINGINDEX_HIGH_GLUCOSE_ENABLE = 1;
/*     */   private static final int SETTINGINDEX_HIGH_GLUCOSE_VALUE = 2;
/*     */   private static final int SETTINGINDEX_HIGH_GLUCOSE_SNOOZE = 4;
/*     */   private static final int SETTINGINDEX_LOW_GLUCOSE_ENABLE = 6;
/*     */   private static final int SETTINGINDEX_LOW_GLUCOSE_VALUE = 7;
/*     */   private static final int SETTINGINDEX_LOW_GLUCOSE_SNOOZE = 9;
/*     */   private static final int SETTINGINDEX_ALARM_SNOOZE_VALUE = 14;
/*     */   private static final int SETTINGINDEX_CALIB_REMINDER_ENABLE = 16;
/*     */   private static final int SETTINGINDEX_CALIB_REMINDER_VALUE = 17;
/*     */   private static final int SETTINGINDEX_SENSOR_TRANSMITTER_ID = 19;
/*     */   private static final int SETTINGINDEX_BG_UNITS = 22;
/*     */   private static final int SETTINGINDEX_MISSED_DATA_VALUE = 23;
/*     */   static final int REC_SIZE_MIN = 64;
/*     */   private static final int GLUCOSE_HISTORY_REC_COUNT = 32;
/*     */   private static final int REC_SIZE_GLUCOSE_HISTORY = 1024;
/*     */   private static final int ISIG_HISTORY_REC_COUNT = 32;
/*     */   private static final int REC_SIZE_ISIG_HISTORY = 2048;
/*     */   private static final int RAW_UNSET_BG_UNITS = 0;
/*     */   private static final int RAW_MMOL_L = 2;
/*     */   private static final double CALIBRATION_FACTOR_FACTOR = 1000.0D;
/* 125 */   private static final String[] BG_UNITS_DESCRIPTION = { "UNSET", "mg/dL", "mmol/L" };
/*     */ 
/*     */   MMX22(int paramInt1, long paramLong1, long paramLong2, String paramString, int paramInt2)
/*     */   {
/* 148 */     super(paramInt1, paramLong1, paramString, paramInt2);
/* 149 */     this.m_lastGlucoseHistoryPageNumber = paramLong2;
/*     */ 
/* 154 */     this.m_cmdWriteGlucoseHistoryTimestamp = new MM511.Command(this, 40, "Write Glucose History Timestamp", 0);
/*     */ 
/* 156 */     this.m_cmdReadGlucoseHistoryData = new CommandReadSensorHistoryData(154, "Read Glucose History", 1024, 32);
/*     */ 
/* 158 */     this.m_cmdReadIsigHistoryData = new CommandReadSensorHistoryData(155, "Read Isig History", 2048, 32);
/*     */ 
/* 161 */     this.m_cmdReadCurrentGlucoseHistoryPageNumber = new MM511.Command(this, 205, "Read Current Glucose History Page Number");
/*     */ 
/* 164 */     this.m_cmdReadCalibrationFactor = new MM511.Command(this, 156, "Read Calibration Factor");
/*     */ 
/* 166 */     this.m_cmdReadSensorSettings = new MM511.Command(this, 153, "Read Sensor Settings");
/*     */ 
/* 169 */     this.m_cmdFilterGlucoseHistoryData = new CommandHistoryDataFilter(168, "Filter Glucose History Data", (MM511.CommandHistoryData)this.m_cmdReadGlucoseHistoryData);
/*     */ 
/* 172 */     this.m_cmdFilterIsigHistoryData = new CommandHistoryDataFilter(169, "Filter Isig History Data", (MM511.CommandHistoryData)this.m_cmdReadIsigHistoryData);
/*     */ 
/* 176 */     this.m_snapshotCreator = new SnapshotCreator();
/*     */   }
/*     */ 
/*     */   MMX22(int paramInt, long paramLong1, long paramLong2)
/*     */   {
/* 193 */     this(paramInt, paramLong1, paramLong2, "MiniMed MMT-522/722 (Paradigm2) Insulin Pump", 116);
/*     */   }
/*     */ 
/*     */   static boolean isModelNumberSupported(String paramString)
/*     */   {
/* 208 */     return ("522".equals(paramString)) || ("722".equals(paramString)) || ("522K".equals(paramString)) || ("722K".equals(paramString));
/*     */   }
/*     */ 
/*     */   void decodeReply(MMPump.Command paramCommand)
/*     */     throws BadDeviceValueException
/*     */   {
/* 221 */     switch (paramCommand.m_commandCode)
/*     */     {
/*     */     case 205:
/* 227 */       decodeCurrentGlucoseHistoryPageNumber(paramCommand.m_rawData);
/* 228 */       break;
/*     */     case 153:
/* 231 */       decodeSensorSettings(paramCommand.m_rawData);
/* 232 */       break;
/*     */     case 156:
/* 235 */       decodeCalibrationFactor(paramCommand.m_rawData);
/* 236 */       break;
/*     */     default:
/* 242 */       super.decodeReply(paramCommand);
/*     */     }
/*     */   }
/*     */ 
/*     */   void decodeCurrentGlucoseHistoryPageNumber(int[] paramArrayOfInt)
/*     */   {
/* 256 */     Contract.pre(paramArrayOfInt != null);
/* 257 */     Contract.pre(paramArrayOfInt.length == 64);
/*     */ 
/* 266 */     this.m_currentGlucoseHistoryPageNumber = (int)MedicalDevice.Util.makeLong(paramArrayOfInt[0], paramArrayOfInt[1], paramArrayOfInt[2], paramArrayOfInt[3]);
/*     */ 
/* 268 */     Contract.invariant(this.m_currentGlucoseHistoryPageNumber >= 0);
/*     */ 
/* 270 */     int i = paramArrayOfInt[5];
/* 271 */     int j = paramArrayOfInt[7];
/*     */ 
/* 274 */     int k = this.m_currentGlucoseHistoryPageNumber - (int)this.m_lastGlucoseHistoryPageNumber + 1;
/*     */ 
/* 277 */     if ((k <= 0) || (k > i))
/*     */     {
/* 279 */       k = i;
/*     */     }
/*     */ 
/* 283 */     int m = this.m_currentGlucoseHistoryPageNumber - (int)this.m_lastGlucoseHistoryPageNumber + 1;
/*     */ 
/* 286 */     if ((m <= 0) || (m > j)) {
/* 287 */       m = j;
/*     */     }
/*     */ 
/* 300 */     int n = this.m_currentGlucoseHistoryPageNumber;
/*     */ 
/* 303 */     int i1 = this.m_currentGlucoseHistoryPageNumber - k + 1;
/*     */ 
/* 305 */     Contract.invariant(i1 >= 0);
/* 306 */     int i2 = this.m_currentGlucoseHistoryPageNumber - m + 1;
/*     */ 
/* 308 */     Contract.invariant(i2 >= 0);
/*     */ 
/* 311 */     this.m_cmdReadGlucoseHistoryData.m_extraObject = new IntRange(i1, n, n, "Glucose Page Range");
/*     */ 
/* 313 */     this.m_cmdReadIsigHistoryData.m_extraObject = new IntRange(i2, n, n, "Isig Page Range");
/*     */ 
/* 317 */     this.m_cmdReadGlucoseHistoryData.m_maxRecords = k;
/* 318 */     this.m_cmdReadIsigHistoryData.m_maxRecords = m;
/* 319 */     this.m_cmdReadGlucoseHistoryData.allocateRawData();
/* 320 */     this.m_cmdReadIsigHistoryData.allocateRawData();
/* 321 */     calcTotalBytesToRead();
/*     */ 
/* 323 */     logInfo(this, "decodeCurrentGlucoseHistoryPageNumber: current glucose page number (absolute) in pump = " + this.m_currentGlucoseHistoryPageNumber + ", available Glucose pages = " + i + ", available Isig pages = " + j + ", glucose pages to read = " + k + ", isig pages to read = " + m);
/*     */   }
/*     */ 
/*     */   private void decodeCalibrationFactor(int[] paramArrayOfInt)
/*     */   {
/* 341 */     Contract.pre(paramArrayOfInt != null);
/* 342 */     Contract.pre(paramArrayOfInt.length == 64);
/*     */ 
/* 347 */     int i = MedicalDevice.Util.makeInt(paramArrayOfInt[0], paramArrayOfInt[1]);
/* 348 */     this.m_calibrationFactor = (i / 1000.0D);
/*     */ 
/* 350 */     logInfo(this, "decodeCalibrationFactor: factor=" + this.m_calibrationFactor);
/*     */   }
/*     */ 
/*     */   private void decodeSensorSettings(int[] paramArrayOfInt)
/*     */     throws BadDeviceValueException
/*     */   {
/* 364 */     Contract.pre(paramArrayOfInt != null);
/* 365 */     Contract.pre(paramArrayOfInt.length == 64);
/*     */ 
/* 369 */     this.m_sensorEnable = MedicalDevice.Util.parseEnable(paramArrayOfInt[0], "Sensor Enable");
/*     */ 
/* 374 */     this.m_sensorBgUnits = paramArrayOfInt[22];
/* 375 */     MedicalDevice.Util.verifyDeviceValue(this.m_sensorBgUnits, 0, 2, "BG Units");
/*     */ 
/* 378 */     this.m_highGlucoseLimitEnable = MedicalDevice.Util.parseEnable(paramArrayOfInt[1], "High Glucose Limit Enable");
/*     */ 
/* 383 */     int i = MedicalDevice.Util.makeInt(paramArrayOfInt[2], paramArrayOfInt[3]);
/*     */ 
/* 386 */     if (this.m_sensorBgUnits == 2)
/*     */     {
/* 388 */       this.m_highGlucoseLimitValue = MedicalDevice.Util.toWholeUnits(i);
/*     */     }
/* 390 */     else this.m_highGlucoseLimitValue = i;
/*     */ 
/* 394 */     this.m_highGlucoseSnoozeTime = MedicalDevice.Util.makeInt(paramArrayOfInt[4], paramArrayOfInt[5]);
/*     */ 
/* 399 */     this.m_lowGlucoseLimitEnable = MedicalDevice.Util.parseEnable(paramArrayOfInt[6], "Low Glucose Limit Enable");
/*     */ 
/* 404 */     i = MedicalDevice.Util.makeInt(paramArrayOfInt[7], paramArrayOfInt[8]);
/*     */ 
/* 407 */     if (this.m_sensorBgUnits == 2)
/*     */     {
/* 409 */       this.m_lowGlucoseLimitValue = MedicalDevice.Util.toWholeUnits(i);
/*     */     }
/* 411 */     else this.m_lowGlucoseLimitValue = i;
/*     */ 
/* 415 */     this.m_lowGlucoseSnoozeTime = MedicalDevice.Util.makeInt(paramArrayOfInt[9], paramArrayOfInt[10]);
/*     */ 
/* 420 */     this.m_alarmSnoozeTime = MedicalDevice.Util.makeInt(paramArrayOfInt[14], paramArrayOfInt[15]);
/*     */ 
/* 425 */     this.m_calibrationReminderEnable = MedicalDevice.Util.parseEnable(paramArrayOfInt[16], "Calibration Reminder Enable");
/*     */ 
/* 430 */     this.m_calibrationReminderTime = MedicalDevice.Util.makeInt(paramArrayOfInt[17], paramArrayOfInt[18]);
/*     */ 
/* 435 */     this.m_sensorTransmitterId = MedicalDevice.Util.makeInt(paramArrayOfInt[19], paramArrayOfInt[20], paramArrayOfInt[21]);
/*     */ 
/* 441 */     this.m_missedDataTime = MedicalDevice.Util.makeInt(paramArrayOfInt[23], paramArrayOfInt[24]);
/*     */ 
/* 445 */     logInfo(this, "decodeSensorSettings: m_alarmSnoozeTime = " + this.m_alarmSnoozeTime);
/* 446 */     logInfo(this, "decodeSensorSettings: m_calibrationReminderEnable = " + this.m_calibrationReminderEnable);
/*     */ 
/* 448 */     logInfo(this, "decodeSensorSettings: m_calibrationReminderTime = " + this.m_calibrationReminderTime);
/*     */ 
/* 450 */     logInfo(this, "decodeSensorSettings: m_highGlucoseLimitEnable = " + this.m_highGlucoseLimitEnable);
/*     */ 
/* 452 */     logInfo(this, "decodeSensorSettings: m_highGlucoseLimitValue = " + this.m_highGlucoseLimitValue);
/* 453 */     logInfo(this, "decodeSensorSettings: m_highGlucoseSnoozeTime = " + this.m_highGlucoseSnoozeTime);
/* 454 */     logInfo(this, "decodeSensorSettings: m_lowGlucoseLimitEnable = " + this.m_lowGlucoseLimitEnable);
/* 455 */     logInfo(this, "decodeSensorSettings: m_lowGlucoseLimitValue = " + this.m_lowGlucoseLimitValue);
/* 456 */     logInfo(this, "decodeSensorSettings: m_lowGlucoseSnoozeTime = " + this.m_lowGlucoseSnoozeTime);
/* 457 */     logInfo(this, "decodeSensorSettings: m_missedDataTime = " + this.m_missedDataTime);
/* 458 */     logInfo(this, "decodeSensorSettings: m_sensorBgUnits = " + this.m_sensorBgUnits + " (" + BG_UNITS_DESCRIPTION[this.m_sensorBgUnits] + ")");
/*     */ 
/* 460 */     logInfo(this, "decodeSensorSettings: m_sensorEnable = " + this.m_sensorEnable);
/* 461 */     logInfo(this, "decodeSensorSettings: m_sensorTransmitterId = " + this.m_sensorTransmitterId);
/*     */   }
/*     */ 
/*     */   class SnapshotCreator extends MMX15.SnapshotCreator
/*     */   {
/*     */     private static final int SNAPSHOT_BYTES = 2810;
/*     */     private static final int SNAPCODE_GLUCOSE_HISTORY_PAGE_NUMBER = 32;
/*     */     private static final int SNAPCODE_GLUCOSE_HISTORY_DATA = 33;
/*     */     private static final int SNAPCODE_ISIG_HISTORY_DATA = 10000;
/*     */     private static final int SNAPCODE_CALIBRATION_FACTOR = 35;
/*     */     private static final int SNAPCODE_SENSOR_SETTINGS = 36;
/*     */     private final MMX22 this$0;
/*     */ 
/*     */     SnapshotCreator(int arg2)
/*     */     {
/* 714 */       super(i);
/*     */ 
/* 713 */       this.this$0 = this$1;
/*     */     }
/*     */ 
/*     */     SnapshotCreator()
/*     */     {
/* 722 */       this(2810);
/*     */     }
/*     */ 
/*     */     void createSnapshotBody()
/*     */     {
/* 732 */       super.createSnapshotBody();
/*     */ 
/* 737 */       this.this$0.m_snapshot.addElement(32, this.this$0.m_cmdReadCurrentGlucoseHistoryPageNumber.m_rawData);
/*     */ 
/* 741 */       this.this$0.m_snapshot.addElement(33, this.this$0.m_cmdReadGlucoseHistoryData.m_rawData);
/*     */ 
/* 745 */       this.this$0.m_snapshot.addElement(10000, this.this$0.m_cmdReadIsigHistoryData.m_rawData);
/*     */ 
/* 748 */       this.this$0.m_snapshot.addElement(35, this.this$0.m_cmdReadCalibrationFactor.m_rawData);
/*     */ 
/* 752 */       this.this$0.m_snapshot.addElement(36, this.this$0.m_cmdReadSensorSettings.m_rawData);
/*     */     }
/*     */   }
/*     */ 
/*     */   class CommandHistoryDataFilter extends MM511.Command
/*     */   {
/*     */     private static final int MAX_DAY = 31;
/*     */     private static final int MAX_MONTH = 12;
/*     */     private static final int NAK_DATA_CONSISTENCY = 10;
/*     */     private static final int FILTER_CMD_PARAMETER_COUNT = 8;
/*     */     MM511.CommandHistoryData m_commandHistoryData;
/*     */ 
/*     */     CommandHistoryDataFilter(int paramString, String paramCommandHistoryData, MM511.CommandHistoryData arg4)
/*     */     {
/* 573 */       super(paramString, paramCommandHistoryData, 8);
/* 574 */       this.m_bytesPerRecord = 64;
/* 575 */       this.m_maxRecords = 1;
/*     */       Object localObject;
/* 576 */       this.m_commandHistoryData = localObject;
/*     */     }
/*     */ 
/*     */     void execute()
/*     */       throws BadDeviceCommException
/*     */     {
/* 591 */       Contract.preNonNull(this.m_beginDate);
/* 592 */       Contract.preNonNull(this.m_endDate);
/* 593 */       Contract.pre(!this.m_beginDate.after(this.m_endDate));
/*     */ 
/* 595 */       int i = 0;
/* 596 */       int[] arrayOfInt = extractDateInfo(this.m_beginDate);
/*     */ 
/* 598 */       this.m_commandParameters[(i++)] = arrayOfInt[0];
/* 599 */       this.m_commandParameters[(i++)] = arrayOfInt[1];
/* 600 */       this.m_commandParameters[(i++)] = arrayOfInt[2];
/* 601 */       this.m_commandParameters[(i++)] = arrayOfInt[3];
/*     */ 
/* 604 */       arrayOfInt = extractDateInfo(this.m_endDate);
/* 605 */       this.m_commandParameters[(i++)] = arrayOfInt[0];
/* 606 */       this.m_commandParameters[(i++)] = arrayOfInt[1];
/* 607 */       this.m_commandParameters[(i++)] = arrayOfInt[2];
/* 608 */       this.m_commandParameters[(i++)] = arrayOfInt[3];
/*     */ 
/* 610 */       MedicalDevice.logInfo(this, "execute: filter data FROM " + this.m_commandParameters[2] + "/" + this.m_commandParameters[3] + "/" + MedicalDevice.Util.makeInt(this.m_commandParameters[0], this.m_commandParameters[1]) + " THROUGH " + this.m_commandParameters[6] + "/" + this.m_commandParameters[7] + "/" + MedicalDevice.Util.makeInt(this.m_commandParameters[4], this.m_commandParameters[5]));
/*     */       try
/*     */       {
/* 619 */         super.execute();
/*     */       }
/*     */       catch (BadDeviceCommException localBadDeviceCommException)
/*     */       {
/* 623 */         Integer localInteger = localBadDeviceCommException.getErrorCode();
/* 624 */         if ((localInteger != null) && (localInteger.intValue() == 10))
/*     */         {
/* 627 */           MedicalDevice.logInfo(this, "execute: NO DATA AVAILABLE.");
/*     */         }
/*     */         else {
/* 630 */           throw localBadDeviceCommException;
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 639 */       int j = MedicalDevice.Util.makeInt(this.m_rawData[0], this.m_rawData[1]);
/* 640 */       int k = MedicalDevice.Util.makeInt(this.m_rawData[2], this.m_rawData[3]);
/*     */ 
/* 643 */       this.m_commandHistoryData.m_maxRecords = Math.abs(j - k);
/* 644 */       this.m_commandHistoryData.allocateRawData();
/* 645 */       MMX22.this.calcTotalBytesToRead();
/*     */ 
/* 648 */       MedicalDevice.logInfo(this, "execute: reading pages " + j + " through " + k);
/* 649 */       this.m_commandHistoryData.execute(j, k);
/*     */     }
/*     */ 
/*     */     private int[] extractDateInfo(Date paramDate)
/*     */     {
/* 665 */       int[] arrayOfInt = new int[4];
/*     */ 
/* 667 */       Calendar localCalendar = Calendar.getInstance();
/* 668 */       localCalendar.setTime(paramDate);
/* 669 */       int i = localCalendar.get(5);
/* 670 */       int j = localCalendar.get(2) + 1;
/* 671 */       int k = localCalendar.get(1);
/*     */ 
/* 673 */       arrayOfInt[0] = MedicalDevice.Util.getHighByte(k);
/* 674 */       arrayOfInt[1] = MedicalDevice.Util.getLowByte(k);
/* 675 */       arrayOfInt[2] = j;
/* 676 */       arrayOfInt[3] = i;
/*     */ 
/* 679 */       Contract.post(j, 1, 12);
/* 680 */       Contract.post(i, 1, 31);
/* 681 */       return arrayOfInt;
/*     */     }
/*     */   }
/*     */ 
/*     */   class CommandReadSensorHistoryData extends MM511.CommandHistoryData
/*     */   {
/*     */     CommandReadSensorHistoryData(int paramString, String paramInt1, int paramInt2, int arg5)
/*     */     {
/* 488 */       super(paramString, paramInt1, paramInt2, i);
/*     */     }
/*     */ 
/*     */     CommandReadSensorHistoryData(int arg2)
/*     */     {
/* 497 */       super(i);
/*     */     }
/*     */ 
/*     */     void setupCommandParameters(int paramInt)
/*     */     {
/* 509 */       this.m_cmdReadHistoryDataPage.m_commandParameterCount = 4;
/*     */ 
/* 512 */       this.m_cmdReadHistoryDataPage.m_commandParameters[0] = MedicalDevice.Util.getLowByte(paramInt >> 24);
/*     */ 
/* 514 */       this.m_cmdReadHistoryDataPage.m_commandParameters[1] = MedicalDevice.Util.getLowByte(paramInt >> 16);
/*     */ 
/* 516 */       this.m_cmdReadHistoryDataPage.m_commandParameters[2] = MedicalDevice.Util.getLowByte(paramInt >> 8);
/*     */ 
/* 519 */       this.m_cmdReadHistoryDataPage.m_commandParameters[3] = MedicalDevice.Util.getLowByte(paramInt);
/*     */     }
/*     */ 
/*     */     void execute()
/*     */       throws BadDeviceCommException, SerialIOHaltedException
/*     */     {
/* 538 */       IntRange localIntRange = (IntRange)this.m_extraObject;
/* 539 */       execute(localIntRange.getMaximum(), localIntRange.getMinimum());
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.deviceportreader.MMX22
 * JD-Core Version:    0.6.0
 */