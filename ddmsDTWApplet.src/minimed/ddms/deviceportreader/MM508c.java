/*     */ package minimed.ddms.deviceportreader;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Date;
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ final class MM508c extends MM508Command
/*     */ {
/*     */   public static final int SNAPSHOT_FORMAT_ID = 113;
/*     */   private static final String SUPPORTED_FIRMWARE_VERSION = "VER 61B";
/*     */   private static final byte CMD_SET_ACCESS_CODE = 1;
/*     */   private static final byte CMD_READ_BATTERY_STATUS = 34;
/*     */   private static final byte CMD_READ_REMAINING_INSULIN = 35;
/*     */   private static final byte CMD_READ_BOLUS_HISTORY = 39;
/*     */   private static final byte CMD_READ_DAILY_TOTALS = 40;
/*     */   private static final byte CMD_READ_PRIME_BOLUSES = 41;
/*     */   private static final byte CMD_READ_ALARMS = 42;
/*     */   private static final byte CMD_READ_PROFILE_SETS = 43;
/*     */   private static final byte CMD_READ_USER_EVENTS = 44;
/*     */   private static final byte CMD_READ_256K_MEM = 56;
/*     */   private static final byte CMD_READ_TEMP_BASAL = 64;
/*     */   private static final byte CMD_READ_TODAYS_TOTALS = 65;
/*     */   private static final byte CMD_READ_STD_PROFILES = 66;
/*     */   private static final byte CMD_READ_A_PROFILES = 67;
/*     */   private static final byte CMD_READ_B_PROFILES = 68;
/*     */   private static final byte CMD_READ_SETTINGS = 70;
/*     */   private static final int MEM_POINTERS = 1;
/*     */   private static final int SETTING_AUTO_OFF_OFFSET = 0;
/*     */   private static final int SETTING_ALARM_MODE_OFFSET = 1;
/*     */   private static final int SETTING_BEEP_VOL_OFFSET = 2;
/*     */   private static final int SETTING_AUDIO_BOLUS_ENABLE_OFFSET = 3;
/*     */   private static final int SETTING_AUDIO_BOLUS_STEP_OFFSET = 4;
/*     */   private static final int SETTING_VAR_BOLUS_ENABLE_OFFSET = 5;
/*     */   private static final int SETTING_MAX_BOLUS_OFFSET = 6;
/*     */   private static final int SETTING_MAX_BASAL_OFFSET = 7;
/*     */   private static final int SETTING_TIME_FORMAT_OFFSET = 9;
/*     */   private static final int SETTING_INSULIN_CONCEN_OFFSET = 10;
/*     */   private static final int SETTING_PATTERN_ENABLE_OFFSET = 11;
/*     */   private static final int SETTING_CUR_BASAL_PROFILE_OFFSET = 12;
/*     */   private static final int SETTING_RF_ENABLE_OFFSET = 13;
/*     */   private static final int SETTING_BLOCK_ENABLE_OFFSET = 14;
/*     */   private static final int MAX_REMAINING_INSULIN = 310;
/*     */   private static final int MAX_BATTERY_STATUS = 2;
/*     */   private static final int REC_SIZE_READ_PUMP_ID = 10;
/*     */   private static final int REC_SIZE_READ_FIRMWARE_VER = 8;
/*     */   private static final int REC_SIZE_READ_RTC = 7;
/*     */   private static final int REC_SIZE_READ_TODAYS_TOTAL = 4;
/*     */   private static final int REC_SIZE_READ_TEMP_BASAL = 4;
/*     */   private static final int REC_SIZE_NONE = 0;
/*     */   private static final int REC_SIZE_ALARM = 10;
/*     */   private static final int REC_SIZE_BASAL_RATE = 2;
/*     */   private static final int REC_SIZE_BOLUS = 11;
/*     */   private static final int REC_SIZE_CURR_BASAL = 2;
/*     */   private static final int REC_SIZE_EVENT = 13;
/*     */   private static final int REC_SIZE_PRIME = 9;
/*     */   private static final int REC_SIZE_TOTAL = 9;
/*     */   private static final int REC_SIZE_PROFILE_SETS = 103;
/*     */   private static final int REC_SIZE_READ_CURR_SETTINGS1 = 15;
/*     */   private static final int REC_SIZE_POINTERS = 120;
/*     */   private static final int REC_SIZE_BATTERY_STATUS = 3;
/*     */   private static final int REC_SIZE_REMAINING_INSULIN = 2;
/*     */   private static final int REC_SIZE_256K_MEM = 250;
/*     */   private static final int ALARM_RECORD_COUNT = 50;
/*     */   private static final int BOLUS_RECORD_COUNT = 450;
/*     */   private static final int CURR_BASAL_RECORD_COUNT = 48;
/*     */   private static final int EVENT_RECORD_COUNT = 200;
/*     */   private static final int PRIME_RECORD_COUNT = 50;
/*     */   private static final int TOTALS_RECORD_COUNT = 90;
/*     */   private static final int PROFILE_SETS_RECORD_COUNT = 15;
/*     */   private static final int CATEGORY_SIZE = 16;
/*     */   private static final int CATEGORY_OFFSET = 2;
/*     */   private static final int ALARM_MODE_AUDIO = 1;
/*     */   private static final int ALARM_MODE_VIBRATE = 2;
/*     */   private static final int PROFILE_PATTERN_STD = 0;
/*     */   private static final int PROFILE_PATTERN_B = 2;
/*     */   private static final int MIN_YEAR = 1998;
/*     */   private static final int MAX_YEAR = 2097;
/*     */   private static final int NO_REPLY = 0;
/*     */   private int[] m_serialNumberRawData;
/*     */   private int[] m_firmwareVersionRawData;
/*     */   private int[] m_timeStampRawData;
/*     */   private int[] m_errorStatusRawData;
/*     */   private int[] m_remoteID1RawData;
/*     */   private int[] m_remoteID2RawData;
/*     */   private int[] m_remoteID3RawData;
/*     */ 
/*     */   MM508c(String paramString1, int[] paramArrayOfInt1, String paramString2, int[] paramArrayOfInt2, Date paramDate, int[] paramArrayOfInt3, int[] paramArrayOfInt4, int[] paramArrayOfInt5, int[] paramArrayOfInt6, int[] paramArrayOfInt7)
/*     */   {
/* 207 */     Contract.pre(paramString2 != null);
/* 208 */     Contract.pre(paramArrayOfInt3 != null);
/* 209 */     Contract.pre(paramArrayOfInt1 != null);
/* 210 */     Contract.pre(paramArrayOfInt2 != null);
/* 211 */     Contract.pre(paramArrayOfInt3 != null);
/* 212 */     Contract.pre(paramArrayOfInt4 != null);
/* 213 */     Contract.pre(paramArrayOfInt5 != null);
/* 214 */     Contract.pre(paramArrayOfInt6 != null);
/* 215 */     Contract.pre(paramArrayOfInt7 != null);
/*     */ 
/* 218 */     this.m_description = "MiniMed MMT-508 Insulin Pump (compressed)";
/* 219 */     logInfo(this, "creating interface to the '" + this.m_description + "', package version " + getPackageVersion());
/*     */ 
/* 221 */     this.m_serialNumber = paramString1;
/* 222 */     this.m_firmwareVersion = paramString2;
/* 223 */     this.m_timeStamp = paramDate;
/* 224 */     this.m_serialNumberRawData = paramArrayOfInt1;
/* 225 */     this.m_firmwareVersionRawData = paramArrayOfInt2;
/* 226 */     this.m_timeStampRawData = paramArrayOfInt3;
/* 227 */     this.m_errorStatusRawData = paramArrayOfInt4;
/* 228 */     this.m_remoteID1RawData = paramArrayOfInt5;
/* 229 */     this.m_remoteID2RawData = paramArrayOfInt6;
/* 230 */     this.m_remoteID3RawData = paramArrayOfInt7;
/*     */ 
/* 232 */     this.m_deviceClassID = 0;
/* 233 */     this.m_snapshotFormatID = 113;
/* 234 */     this.m_minYear = 1998;
/* 235 */     this.m_maxYear = 2097;
/*     */ 
/* 242 */     this.m_sequenceNumberInc = 0;
/*     */ 
/* 244 */     this.m_cmdSetAccessCode = new MM508Command.Command(this, 1, "Set Access Code", 0, 0, 0);
/*     */ 
/* 247 */     this.m_cmdReadCurrentSettings1 = new MM508Command.Command(this, 70, "Read Current Settings", 15, 1, 7);
/*     */ 
/* 250 */     this.m_cmdReadTodaysTotals = new MM508Command.Command(this, 65, "Read Today's Total Insulin", 4, 1, 7);
/*     */ 
/* 254 */     this.m_cmdReadTempBasal = new MM508Command.Command(this, 64, "Read Temporary Basal", 4, 1, 7);
/*     */ 
/* 257 */     this.m_cmdReadBolusData = new MM508Command.Command(this, 39, "Read Bolus History Data", 11, 450, 1);
/*     */ 
/* 260 */     this.m_cmdReadTotalsData = new MM508Command.Command(this, 40, "Read Totals Data", 9, 90, 5);
/*     */ 
/* 263 */     this.m_cmdReadEventData = new MM508Command.Command(this, 44, "Read Event Data", 13, 200, 6);
/*     */ 
/* 266 */     this.m_cmdReadAlarmData = new MM508Command.Command(this, 42, "Read Alarm Data", 10, 50, 3);
/*     */ 
/* 269 */     this.m_cmdReadPrimesData = new MM508Command.Command(this, 41, "Read Primes Data", 9, 50, 2);
/*     */ 
/* 273 */     this.m_cmdReadCurrBasalDataSTD = new MM508Command.Command(this, 66, "Read Standard Profiles Data", 2, 48, 8);
/*     */ 
/* 278 */     this.m_cmdReadCurrBasalDataA = new MM508Command.Command(this, 67, "Read Profiles A Data", 2, 48, 9);
/*     */ 
/* 283 */     this.m_cmdReadCurrBasalDataB = new MM508Command.Command(this, 68, "Read Profiles B Data", 2, 48, 10);
/*     */ 
/* 288 */     this.m_cmdReadBasalProfilesData = new MM508Command.Command(this, 43, "Read Profile Sets Data", 103, 15, 4);
/*     */ 
/* 292 */     this.m_cmdReadDataPointers = new MM508Command.Command(this, 56, "Read Memory Pointers (header page)", 250, 1, 1, 2, 0);
/*     */ 
/* 296 */     this.m_cmdReadBatteryStatus = new MM508Command.Command(this, 34, "Read Battery Status", 3, 1, 0);
/*     */ 
/* 299 */     this.m_cmdReadRemainingInsulin = new MM508Command.Command(this, 35, "Read Remaining Insulin", 2, 1, 0);
/*     */ 
/* 303 */     this.m_snapshotCreator = new SnapshotCreator();
/*     */   }
/*     */ 
/*     */   void initComStation()
/*     */     throws IOException
/*     */   {
/* 316 */     Contract.pre(getRS232Port() != null);
/* 317 */     Contract.pre(getRS232Port().isOpen());
/*     */   }
/*     */ 
/*     */   void shutDownComStation()
/*     */     throws IOException
/*     */   {
/*     */   }
/*     */ 
/*     */   void shutDownSerialPort()
/*     */     throws IOException
/*     */   {
/*     */   }
/*     */ 
/*     */   void initDevice()
/*     */     throws IOException, BadDeviceCommException
/*     */   {
/* 349 */     Contract.pre(getRS232Port() != null);
/* 350 */     Contract.pre(getRS232Port().isOpen());
/* 351 */     Contract.pre(isFirmwareVersionSupported(this.m_firmwareVersion));
/*     */   }
/*     */ 
/*     */   static final boolean isFirmwareVersionSupported(String paramString)
/*     */   {
/* 363 */     return "VER 61B".equals(paramString);
/*     */   }
/*     */ 
/*     */   void decodeReply(MMPump.Command paramCommand)
/*     */     throws BadDeviceValueException
/*     */   {
/* 377 */     switch (paramCommand.m_commandCode) {
/*     */     case 70:
/* 379 */       decodeCurrentSettings(paramCommand.m_rawData);
/* 380 */       break;
/*     */     case 56:
/* 384 */       switch (paramCommand.m_address) {
/*     */       case 1:
/* 386 */         decodeDataPointers(paramCommand.m_rawData);
/*     */       }
/*     */ 
/* 390 */       break;
/*     */     case 64:
/* 394 */       decodeTempBasal(paramCommand.m_rawData);
/* 395 */       break;
/*     */     case 65:
/* 397 */       decodeTodaysDailyTotals(paramCommand.m_rawData);
/* 398 */       break;
/*     */     case 34:
/* 400 */       decodeBatteryStatus(paramCommand.m_rawData);
/* 401 */       break;
/*     */     case 35:
/* 403 */       decodeRemainingInsulin(paramCommand.m_rawData);
/* 404 */       break;
/*     */     }
/*     */   }
/*     */ 
/*     */   private void decodeCurrentSettings(int[] paramArrayOfInt)
/*     */     throws BadDeviceValueException
/*     */   {
/* 423 */     Contract.pre(paramArrayOfInt != null);
/*     */ 
/* 428 */     this.m_settingTimeFormat = paramArrayOfInt[9];
/* 429 */     MedicalDevice.Util.verifyDeviceValue(this.m_settingTimeFormat, 0, 1, "Time Display Format");
/*     */ 
/* 432 */     int i = paramArrayOfInt[5];
/* 433 */     MedicalDevice.Util.verifyDeviceValue(i, 0, 1, "Variable Bolus Enable");
/* 434 */     this.m_settingVarBolusEnable = (i == 1);
/*     */ 
/* 437 */     i = paramArrayOfInt[3];
/* 438 */     MedicalDevice.Util.verifyDeviceValue(i, 0, 1, "Audio Bolus Enable");
/* 439 */     this.m_settingAudioBolusEnable = (i == 1);
/*     */ 
/* 442 */     this.m_settingBeepVolume = paramArrayOfInt[2];
/* 443 */     MedicalDevice.Util.verifyDeviceValue(this.m_settingBeepVolume, 1, 3, "Beep Volume");
/*     */ 
/* 446 */     i = paramArrayOfInt[11];
/* 447 */     MedicalDevice.Util.verifyDeviceValue(i, 0, 1, "Basal Pattern Enable");
/* 448 */     this.m_settingBasalPatternEnable = (i == 1);
/*     */ 
/* 451 */     i = paramArrayOfInt[14];
/* 452 */     MedicalDevice.Util.verifyDeviceValue(i, 0, 1, "Block Enable");
/* 453 */     this.m_settingBlockEnable = (i == 1);
/*     */ 
/* 456 */     this.m_settingAlarmMode = paramArrayOfInt[1];
/* 457 */     MedicalDevice.Util.verifyDeviceValue(this.m_settingAlarmMode, 1, 2, "Alarm Mode");
/*     */ 
/* 461 */     this.m_settingCurrentBasalPattern = paramArrayOfInt[12];
/* 462 */     MedicalDevice.Util.verifyDeviceValue(this.m_settingCurrentBasalPattern, 0, 2, "Current Basal Profile");
/*     */ 
/* 466 */     this.m_settingInsulinConcen = paramArrayOfInt[10];
/* 467 */     verifyConcentration(this.m_settingInsulinConcen);
/*     */ 
/* 470 */     this.m_strokesPerBasalUnit = (int)(1000.0D / this.m_settingInsulinConcen);
/*     */ 
/* 472 */     this.m_strokesPerBolusUnit = this.m_strokesPerBasalUnit;
/*     */ 
/* 475 */     if (this.m_settingAudioBolusEnable) {
/* 476 */       j = paramArrayOfInt[4];
/* 477 */       if ((j != 10) && (j != 5)) {
/* 478 */         throw new BadDeviceValueException("The value of " + j + " for Audio Bolus Step Size is invalid; " + "must be 5 or 10");
/*     */       }
/*     */ 
/* 482 */       this.m_settingAudioBolusSize = toBolusInsulin(j);
/*     */     }
/*     */     else {
/* 485 */       this.m_settingAudioBolusSize = 0.0D;
/*     */     }
/*     */ 
/* 489 */     this.m_settingAutoOffDurationHrs = paramArrayOfInt[0];
/* 490 */     MedicalDevice.Util.verifyDeviceValue(this.m_settingAutoOffDurationHrs, 0, 16, "Auto-off Duration");
/*     */ 
/* 494 */     int j = MedicalDevice.Util.makeUnsignedShort(paramArrayOfInt[7], paramArrayOfInt[8]);
/*     */ 
/* 496 */     MedicalDevice.Util.verifyDeviceValue(j, 0.0D, toBasalStrokes(35.0D), "Maximum Basal Rate");
/*     */ 
/* 498 */     this.m_settingMaxBasalRate = toBasalInsulin(j);
/*     */ 
/* 501 */     int k = paramArrayOfInt[6];
/* 502 */     MedicalDevice.Util.verifyDeviceValue(k, 0.0D, toBolusStrokes(25.0D), "Maximum Bolus Rate");
/*     */ 
/* 504 */     this.m_settingMaxBolus = toBolusInsulin(k);
/*     */ 
/* 507 */     i = paramArrayOfInt[13];
/* 508 */     MedicalDevice.Util.verifyDeviceValue(i, 0, 1, "RF Enable");
/* 509 */     this.m_settingRFEnable = (i == 1);
/*     */ 
/* 511 */     logInfo(this, "decodeCurrentSettings: *** CURRENT SETTINGS ***");
/* 512 */     logInfo(this, "decodeCurrentSettings: RF On = " + this.m_settingRFEnable);
/* 513 */     logInfo(this, "decodeCurrentSettings: Max Basal Rate = " + this.m_settingMaxBasalRate);
/*     */ 
/* 515 */     logInfo(this, "decodeCurrentSettings: Max Bolus = " + this.m_settingMaxBolus);
/* 516 */     logInfo(this, "decodeCurrentSettings: Insulin Concen = " + this.m_settingInsulinConcen);
/*     */ 
/* 518 */     logInfo(this, "decodeCurrentSettings: Audio Bolus Step Size = " + this.m_settingAudioBolusSize);
/*     */ 
/* 520 */     logInfo(this, "decodeCurrentSettings: Auto Off Dur = " + this.m_settingAutoOffDurationHrs);
/*     */ 
/* 522 */     logInfo(this, "decodeCurrentSettings: Current Basal Profile = " + getPatternString(new Integer(this.m_settingCurrentBasalPattern)));
/*     */ 
/* 524 */     logInfo(this, "decodeCurrentSettings: Time Format = " + (this.m_settingTimeFormat == 1 ? "24h" : "12h"));
/*     */ 
/* 526 */     logInfo(this, "decodeCurrentSettings: Variable Bolus On = " + this.m_settingVarBolusEnable);
/*     */ 
/* 528 */     logInfo(this, "decodeCurrentSettings: Audio Bolus On = " + this.m_settingAudioBolusEnable);
/*     */ 
/* 530 */     logInfo(this, "decodeCurrentSettings: Beep Volume = " + this.m_settingBeepVolume);
/* 531 */     logInfo(this, "decodeCurrentSettings: Pattern On = " + this.m_settingBasalPatternEnable);
/*     */ 
/* 533 */     logInfo(this, "decodeCurrentSettings: Block On = " + this.m_settingBlockEnable);
/* 534 */     logInfo(this, "decodeCurrentSettings: Alarm Mode = " + (this.m_settingAlarmMode == 1 ? "audio" : "vibrate"));
/*     */   }
/*     */ 
/*     */   private void decodeBatteryStatus(int[] paramArrayOfInt)
/*     */     throws BadDeviceValueException
/*     */   {
/* 548 */     Contract.pre(paramArrayOfInt != null);
/*     */ 
/* 552 */     this.m_settingBatteryStatus = paramArrayOfInt[0];
/* 553 */     MedicalDevice.Util.verifyDeviceValue(this.m_settingBatteryStatus, 0, 2, "Battery Status");
/* 554 */     String str = this.m_settingBatteryStatus == 1 ? "Low" : this.m_settingBatteryStatus == 0 ? "Normal" : "Off";
/*     */ 
/* 557 */     logInfo(this, "decodeBatteryStatus: status = " + str);
/*     */   }
/*     */ 
/*     */   private void decodeRemainingInsulin(int[] paramArrayOfInt)
/*     */     throws BadDeviceValueException
/*     */   {
/* 570 */     Contract.pre(paramArrayOfInt != null);
/*     */ 
/* 574 */     int i = MedicalDevice.Util.makeUnsignedShort(paramArrayOfInt[0], paramArrayOfInt[1]);
/* 575 */     this.m_settingRemainingInsulin = (int)toBolusInsulin(i);
/*     */ 
/* 577 */     MedicalDevice.Util.verifyDeviceValue(this.m_settingRemainingInsulin, 0, 310, "Remaining Insulin");
/*     */ 
/* 580 */     logInfo(this, "decodeRemainingInsulin: insulin = " + this.m_settingRemainingInsulin);
/*     */   }
/*     */ 
/*     */   private final void decodeTempBasal(int[] paramArrayOfInt)
/*     */     throws BadDeviceValueException
/*     */   {
/* 593 */     Contract.pre(paramArrayOfInt != null);
/*     */ 
/* 600 */     int i = MedicalDevice.Util.makeUnsignedShort(paramArrayOfInt[0], paramArrayOfInt[1]);
/* 601 */     MedicalDevice.Util.verifyDeviceValue(i, 0.0D, toBasalStrokes(35.0D), "Temporary Basal Rate");
/*     */ 
/* 603 */     this.m_settingTempBasalRate = toBasalInsulin(i);
/*     */ 
/* 606 */     this.m_settingTempBasalDurationMin = MedicalDevice.Util.makeUnsignedShort(paramArrayOfInt[2], paramArrayOfInt[3]);
/* 607 */     MedicalDevice.Util.verifyDeviceValue(this.m_settingTempBasalDurationMin, 0, 1440, "Temporary Basal Duration");
/*     */ 
/* 610 */     logInfo(this, "decodeTempBasal: Temp Basal Rate = " + this.m_settingTempBasalRate);
/*     */ 
/* 612 */     logInfo(this, "decodeTempBasal: Temp Basal Remain Dur = " + this.m_settingTempBasalDurationMin + " minutes");
/*     */   }
/*     */ 
/*     */   private final void decodeTodaysDailyTotals(int[] paramArrayOfInt)
/*     */   {
/* 624 */     Contract.pre(paramArrayOfInt != null);
/*     */ 
/* 631 */     int i = MedicalDevice.Util.makeUnsignedShort(paramArrayOfInt[0], paramArrayOfInt[1]);
/* 632 */     this.m_settingTodaysTotal = toBolusInsulin(i);
/*     */ 
/* 635 */     i = MedicalDevice.Util.makeUnsignedShort(paramArrayOfInt[2], paramArrayOfInt[3]);
/* 636 */     this.m_settingYesterdaysTotal = toBolusInsulin(i);
/*     */ 
/* 638 */     logInfo(this, "decodeTodaysDailyTotals: Today's Total Insulin = " + this.m_settingTodaysTotal);
/*     */ 
/* 640 */     logInfo(this, "decodeTodaysDailyTotals: Yesterday's Total Insulin = " + this.m_settingYesterdaysTotal);
/*     */   }
/*     */ 
/*     */   private final void decodeDataPointers(int[] paramArrayOfInt)
/*     */     throws BadDeviceValueException
/*     */   {
/* 654 */     int[] arrayOfInt = new int[16];
/*     */ 
/* 656 */     Contract.pre(paramArrayOfInt != null);
/*     */ 
/* 658 */     MedicalDevice.Util.verifyDeviceValue(!MedicalDevice.Util.isZeros(paramArrayOfInt), "rawData array is all zeroes");
/* 659 */     verifyCRC(paramArrayOfInt, 0, 120, "Data Pointers");
/*     */ 
/* 662 */     for (int i = 0; i < 7; i++)
/*     */     {
/* 664 */       System.arraycopy(paramArrayOfInt, 2 + 16 * i, arrayOfInt, 0, 16);
/*     */ 
/* 668 */       switch (i) {
/*     */       case 0:
/* 670 */         decodeDataPointer(arrayOfInt, this.m_cmdReadBolusData);
/* 671 */         break;
/*     */       case 1:
/* 674 */         decodeDataPointer(arrayOfInt, this.m_cmdReadTotalsData);
/* 675 */         break;
/*     */       case 2:
/* 678 */         decodeDataPointer(arrayOfInt, this.m_cmdReadPrimesData);
/* 679 */         break;
/*     */       case 3:
/* 682 */         decodeDataPointer(arrayOfInt, this.m_cmdReadEventData);
/* 683 */         break;
/*     */       case 4:
/* 686 */         decodeDataPointer(arrayOfInt, this.m_cmdReadAlarmData);
/* 687 */         break;
/*     */       case 5:
/* 690 */         break;
/*     */       case 6:
/* 693 */         decodeDataPointer(arrayOfInt, this.m_cmdReadBasalProfilesData);
/* 694 */         break;
/*     */       default:
/* 697 */         Contract.unreachable();
/* 698 */         return;
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private final void decodeDataPointer(int[] paramArrayOfInt, MMPump.Command paramCommand)
/*     */     throws BadDeviceValueException
/*     */   {
/* 718 */     Contract.pre(paramArrayOfInt != null);
/* 719 */     Contract.pre(paramArrayOfInt.length >= 16);
/* 720 */     Contract.pre(paramCommand != null);
/*     */ 
/* 723 */     paramCommand.m_dataPointer = MedicalDevice.Util.makeUnsignedShort(paramArrayOfInt[10], paramArrayOfInt[11]);
/*     */ 
/* 725 */     MedicalDevice.Util.verifyDeviceValue(paramCommand.m_dataPointer, 0, paramCommand.m_maxRecords - 1, paramCommand.m_description + ", Next Record Pointer (m_dataPointer)");
/*     */ 
/* 728 */     logInfo(this, "decodeDataPointer: " + paramCommand.m_description + " Pointer = " + paramCommand.m_dataPointer);
/*     */ 
/* 732 */     int i = MedicalDevice.Util.makeUnsignedShort(paramArrayOfInt[8], paramArrayOfInt[9]);
/*     */ 
/* 735 */     MedicalDevice.Util.verifyDeviceValue(i, 0, paramCommand.m_maxRecords, paramCommand.m_description + ", Current Record Pointer (dataPointerCurRecs)");
/*     */ 
/* 739 */     logInfo(this, "decodeDataPointer: " + paramCommand.m_description + " Current Records Pointer = " + i);
/*     */   }
/*     */   private final class SnapshotCreator extends MedicalDevice.SnapshotCreator { private static final int SNAPSHOT_BYTES = 567;
/*     */     private static final int SNAPCODE_POINTERS_BYTES = 120;
/*     */     private static final int SNAPCODE_SETTINGS = 1;
/*     */     private static final int SNAPCODE_TODAYS_TOTAL_INSULIN = 2;
/*     */     private static final int SNAPCODE_TEMP_BASAL = 3;
/*     */     private static final int SNAPCODE_BOLUS_HISTORY = 4;
/*     */     private static final int SNAPCODE_TOTALS = 5;
/*     */     private static final int SNAPCODE_EVENTS = 6;
/*     */     private static final int SNAPCODE_ALARMS = 7;
/*     */     private static final int SNAPCODE_PRIMES = 8;
/*     */     private static final int SNAPCODE_CURRENT_BASAL_STD = 9;
/*     */     private static final int SNAPCODE_CURRENT_BASAL_A = 10;
/*     */     private static final int SNAPCODE_CURRENT_BASAL_B = 11;
/*     */     private static final int SNAPCODE_BASAL_CHANGES = 12;
/*     */     private static final int SNAPCODE_POINTERS = 13;
/*     */     private static final int SNAPCODE_BATTERY_STATUS = 14;
/*     */     private static final int SNAPCODE_REMAINING_INSULIN = 15;
/*     */     private static final int SNAPCODE_ERROR_STATUS = 16;
/*     */     private static final int SNAPCODE_REMOTE_ID1 = 17;
/*     */     private static final int SNAPCODE_REMOTE_ID2 = 18;
/*     */     private static final int SNAPCODE_REMOTE_ID3 = 19;
/*     */ 
/* 784 */     SnapshotCreator() { super(567);
/* 785 */       MM508c.this.m_snapshotFirmwareCount = 8;
/* 786 */       MM508c.this.m_snapshotSerialCount = 10;
/* 787 */       MM508c.this.m_snapshotTimeCount = 7;
/*     */     }
/*     */ 
/*     */     void createSnapshotBody()
/*     */     {
/* 795 */       MM508c.this.m_snapshot = new Snapshot(MM508c.this.m_snapshotFormatID, 1, MM508c.this.m_firmwareVersionRawData, MM508c.this.m_serialNumberRawData, MM508c.this.m_timeStampRawData);
/*     */ 
/* 798 */       MedicalDevice.logInfo(this, "createSnapshot: creating snapshot");
/*     */ 
/* 802 */       MM508c.this.m_snapshot.addElement(1, MM508c.this.m_cmdReadCurrentSettings1.m_rawData);
/*     */ 
/* 806 */       MM508c.this.m_snapshot.addElement(2, MM508c.this.m_cmdReadTodaysTotals.m_rawData);
/*     */ 
/* 809 */       MM508c.this.m_snapshot.addElement(3, MM508c.this.m_cmdReadTempBasal.m_rawData);
/*     */ 
/* 813 */       MM508c.this.m_snapshot.addElement(4, MM508c.this.m_cmdReadBolusData.m_rawData, MM508c.this.m_cmdReadBolusData.m_numBytesRead);
/*     */ 
/* 817 */       MM508c.this.m_snapshot.addElement(5, MM508c.this.m_cmdReadTotalsData.m_rawData, MM508c.this.m_cmdReadTotalsData.m_numBytesRead);
/*     */ 
/* 821 */       MM508c.this.m_snapshot.addElement(6, MM508c.this.m_cmdReadEventData.m_rawData, MM508c.this.m_cmdReadEventData.m_numBytesRead);
/*     */ 
/* 825 */       MM508c.this.m_snapshot.addElement(7, MM508c.this.m_cmdReadAlarmData.m_rawData, MM508c.this.m_cmdReadAlarmData.m_numBytesRead);
/*     */ 
/* 829 */       MM508c.this.m_snapshot.addElement(8, MM508c.this.m_cmdReadPrimesData.m_rawData, MM508c.this.m_cmdReadPrimesData.m_numBytesRead);
/*     */ 
/* 833 */       MM508c.this.m_snapshot.addElement(9, MM508c.this.m_cmdReadCurrBasalDataSTD.m_rawData);
/*     */ 
/* 836 */       MM508c.this.m_snapshot.addElement(10, MM508c.this.m_cmdReadCurrBasalDataA.m_rawData);
/*     */ 
/* 839 */       MM508c.this.m_snapshot.addElement(11, MM508c.this.m_cmdReadCurrBasalDataB.m_rawData);
/*     */ 
/* 842 */       MM508c.this.m_snapshot.addElement(12, MM508c.this.m_cmdReadBasalProfilesData.m_rawData, MM508c.this.m_cmdReadBasalProfilesData.m_numBytesRead);
/*     */ 
/* 846 */       MM508c.this.m_snapshot.addElement(13, MM508c.this.m_cmdReadDataPointers.m_rawData, 120);
/*     */ 
/* 851 */       MM508c.this.m_snapshot.addElement(14, MM508c.this.m_cmdReadBatteryStatus.m_rawData);
/*     */ 
/* 854 */       MM508c.this.m_snapshot.addElement(15, MM508c.this.m_cmdReadRemainingInsulin.m_rawData);
/*     */ 
/* 857 */       MM508c.this.m_snapshot.addElement(16, MM508c.this.m_errorStatusRawData);
/*     */ 
/* 860 */       MM508c.this.m_snapshot.addElement(17, MM508c.this.m_remoteID1RawData);
/* 861 */       MM508c.this.m_snapshot.addElement(18, MM508c.this.m_remoteID2RawData);
/* 862 */       MM508c.this.m_snapshot.addElement(19, MM508c.this.m_remoteID3RawData);
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.deviceportreader.MM508c
 * JD-Core Version:    0.6.0
 */