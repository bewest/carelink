/*      */ package minimed.ddms.deviceportreader;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.util.Date;
/*      */ import minimed.util.Contract;
/*      */ 
/*      */ final class MM508u extends MM508Command
/*      */ {
/*      */   public static final int SNAPSHOT_FORMAT_ID = 111;
/*      */   private static final String SUPPORTED_FIRMWARE_VERSION1 = "VER 60G";
/*      */   private static final String SUPPORTED_FIRMWARE_VERSION2 = "VER 60I";
/*      */   private static final byte CMD_SET_ACCESS_CODE = 1;
/*      */   private static final byte CMD_READ_BATTERY_STATUS = 34;
/*      */   private static final byte CMD_READ_REMAINING_INSULIN = 35;
/*      */   private static final byte CMD_READ_BOLUS_HISTORY = 39;
/*      */   private static final byte CMD_READ_DAILY_TOTALS = 40;
/*      */   private static final byte CMD_READ_PRIME_BOLUSES = 41;
/*      */   private static final byte CMD_READ_ALARMS = 42;
/*      */   private static final byte CMD_READ_PROFILE_SETS = 43;
/*      */   private static final byte CMD_READ_USER_EVENTS = 44;
/*      */   private static final byte CMD_READ_128K_MEM = 55;
/*      */   private static final byte CMD_READ_256K_MEM = 56;
/*      */   private static final byte CMD_READ_TEMP_BASAL = 64;
/*      */   private static final byte CMD_READ_TODAYS_TOTALS = 65;
/*      */   private static final byte CMD_READ_STD_PROFILES = 66;
/*      */   private static final byte CMD_READ_A_PROFILES = 67;
/*      */   private static final byte CMD_READ_B_PROFILES = 68;
/*      */   private static final int MEM_CURR_SETTINGS1 = 254;
/*      */   private static final int MEM_CURR_SETTINGS2 = 7296;
/*      */   private static final int MEM_CURR_SETTINGS3 = 7926;
/*      */   private static final int MEM_CURR_SETTINGS4 = 1420;
/*      */   private static final int MEM_POINTERS = 1;
/*      */   private static final int SETTING_TIME_FORMAT_OFFSET = 0;
/*      */   private static final int SETTING_PATTERN_ENABLE_OFFSET = 2;
/*      */   private static final int SETTING_VAR_BOLUS_ENABLE_OFFSET = 4;
/*      */   private static final int SETTING_AUDIO_BOLUS_ENABLE_OFFSET = 6;
/*      */   private static final int SETTING_BLOCK_ENABLE_OFFSET = 12;
/*      */   private static final int SETTING_BEEP_VOL_OFFSET = 24;
/*      */   private static final int SETTING_ALARM_MODE_OFFSET = 26;
/*      */   private static final int SETTING_CUR_BASAL_PROFILE_OFFSET = 0;
/*      */   private static final int SETTING_AUDIO_BOLUS_STEP_OFFSET = 14;
/*      */   private static final int SETTING_AUTO_OFF_OFFSET = 16;
/*      */   private static final int SETTING_INSULIN_CONCEN_OFFSET = 24;
/*      */   private static final int SETTING_MAX_BOLUS_OFFSET = 0;
/*      */   private static final int SETTING_MAX_BASAL_OFFSET = 2;
/*      */   private static final int SETTING_RF_ENABLE_OFFSET = 0;
/*      */   private static final int MAX_REMAINING_INSULIN = 310;
/*      */   private static final int MAX_BATTERY_STATUS = 2;
/*      */   private static final int REC_SIZE_READ_PUMP_ID = 10;
/*      */   private static final int REC_SIZE_READ_FIRMWARE_VER = 8;
/*      */   private static final int REC_SIZE_READ_RTC = 7;
/*      */   private static final int REC_SIZE_READ_TODAYS_TOTAL = 4;
/*      */   private static final int REC_SIZE_READ_TEMP_BASAL = 4;
/*      */   private static final int REC_SIZE_NONE = 0;
/*      */   private static final int REC_SIZE_ALARM = 16;
/*      */   private static final int REC_SIZE_BASAL_RATE = 2;
/*      */   private static final int REC_SIZE_BOLUS = 18;
/*      */   private static final int REC_SIZE_CURR_BASAL = 2;
/*      */   private static final int REC_SIZE_EVENT = 20;
/*      */   private static final int REC_SIZE_PRIME = 14;
/*      */   private static final int REC_SIZE_TOTAL = 14;
/*      */   private static final int REC_SIZE_PROFILE_SETS = 204;
/*      */   private static final int REC_SIZE_READ_CURR_SETTINGS1 = 28;
/*      */   private static final int REC_SIZE_READ_CURR_SETTINGS2 = 26;
/*      */   private static final int REC_SIZE_READ_CURR_SETTINGS3 = 4;
/*      */   private static final int REC_SIZE_READ_CURR_SETTINGS4 = 2;
/*      */   private static final int REC_SIZE_POINTERS = 120;
/*      */   private static final int REC_SIZE_BATTERY_STATUS = 3;
/*      */   private static final int REC_SIZE_REMAINING_INSULIN = 2;
/*      */   private static final int REC_SIZE_256K_MEM = 250;
/*      */   private static final int ALARM_RECORD_COUNT = 50;
/*      */   private static final int BOLUS_RECORD_COUNT = 450;
/*      */   private static final int CURR_BASAL_RECORD_COUNT = 48;
/*      */   private static final int EVENT_RECORD_COUNT = 200;
/*      */   private static final int PRIME_RECORD_COUNT = 50;
/*      */   private static final int TOTALS_RECORD_COUNT = 90;
/*      */   private static final int PROFILE_SETS_RECORD_COUNT = 15;
/*      */   private static final int BEEP_VOL508_LOW = 30;
/*      */   private static final int BEEP_VOL508_MED = 100;
/*      */   private static final int BEEP_VOL508_HIGH = 280;
/*      */   private static final int CATEGORY_SIZE = 16;
/*      */   private static final int CATEGORY_OFFSET = 2;
/*      */   private static final int ALARM_MODE_AUDIO = 1;
/*      */   private static final int ALARM_MODE_VIBRATE = 2;
/*      */   private static final int PROFILE_PATTERN_STD = 0;
/*      */   private static final int PROFILE_PATTERN_B = 2;
/*      */   private static final int MIN_YEAR = 1998;
/*      */   private static final int MAX_YEAR = 2097;
/*      */   private static final int NO_REPLY = 0;
/*      */   private int[] m_serialNumberRawData;
/*      */   private int[] m_firmwareVersionRawData;
/*      */   private int[] m_timeStampRawData;
/*      */   private int[] m_errorStatusRawData;
/*      */   private int[] m_remoteID1RawData;
/*      */   private int[] m_remoteID2RawData;
/*      */   private int[] m_remoteID3RawData;
/*      */ 
/*      */   MM508u(String paramString1, int[] paramArrayOfInt1, String paramString2, int[] paramArrayOfInt2, Date paramDate, int[] paramArrayOfInt3, int[] paramArrayOfInt4, int[] paramArrayOfInt5, int[] paramArrayOfInt6, int[] paramArrayOfInt7)
/*      */   {
/*  229 */     Contract.pre(paramString2 != null);
/*  230 */     Contract.pre(paramArrayOfInt3 != null);
/*  231 */     Contract.pre(paramArrayOfInt1 != null);
/*  232 */     Contract.pre(paramArrayOfInt2 != null);
/*  233 */     Contract.pre(paramArrayOfInt3 != null);
/*  234 */     Contract.pre(paramArrayOfInt4 != null);
/*  235 */     Contract.pre(paramArrayOfInt5 != null);
/*  236 */     Contract.pre(paramArrayOfInt6 != null);
/*  237 */     Contract.pre(paramArrayOfInt7 != null);
/*      */ 
/*  240 */     this.m_description = "MiniMed MMT-508 Insulin Pump (uncompressed)";
/*  241 */     logInfo(this, "creating interface to the '" + this.m_description + "', package version " + getPackageVersion());
/*      */ 
/*  243 */     this.m_serialNumber = paramString1;
/*  244 */     this.m_firmwareVersion = paramString2;
/*  245 */     this.m_timeStamp = paramDate;
/*  246 */     this.m_serialNumberRawData = paramArrayOfInt1;
/*  247 */     this.m_firmwareVersionRawData = paramArrayOfInt2;
/*  248 */     this.m_timeStampRawData = paramArrayOfInt3;
/*  249 */     this.m_errorStatusRawData = paramArrayOfInt4;
/*  250 */     this.m_remoteID1RawData = paramArrayOfInt5;
/*  251 */     this.m_remoteID2RawData = paramArrayOfInt6;
/*  252 */     this.m_remoteID3RawData = paramArrayOfInt7;
/*      */ 
/*  254 */     this.m_deviceClassID = 0;
/*  255 */     this.m_snapshotFormatID = 111;
/*  256 */     this.m_minYear = 1998;
/*  257 */     this.m_maxYear = 2097;
/*      */ 
/*  264 */     this.m_sequenceNumberInc = 0;
/*      */ 
/*  266 */     this.m_cmdSetAccessCode = new MM508Command.Command(this, 1, "Set Access Code", 0, 0, 0);
/*      */ 
/*  269 */     this.m_cmdReadBolusData = new MM508Command.Command(this, 39, "Read Bolus History Data", 18, 450, 1);
/*      */ 
/*  272 */     this.m_cmdReadTotalsData = new MM508Command.Command(this, 40, "Read Totals Data", 14, 90, 5);
/*      */ 
/*  275 */     this.m_cmdReadEventData = new MM508Command.Command(this, 44, "Read Event Data", 20, 200, 6);
/*      */ 
/*  278 */     this.m_cmdReadAlarmData = new MM508Command.Command(this, 42, "Read Alarm Data", 16, 50, 3);
/*      */ 
/*  281 */     this.m_cmdReadPrimesData = new MM508Command.Command(this, 41, "Read Primes Data", 14, 50, 2);
/*      */ 
/*  285 */     this.m_cmdReadBasalProfilesData = new MM508Command.Command(this, 43, "Read Profile Sets Data", 204, 15, 4);
/*      */ 
/*  290 */     this.m_cmdReadCurrBasalDataSTD = new MM508Command.Command(this, 66, "Read Standard Profiles Data", 2, 48, 8);
/*      */ 
/*  295 */     this.m_cmdReadCurrBasalDataA = new MM508Command.Command(this, 67, "Read Profiles A Data", 2, 48, 9);
/*      */ 
/*  300 */     this.m_cmdReadCurrBasalDataB = new MM508Command.Command(this, 68, "Read Profiles B Data", 2, 48, 10);
/*      */ 
/*  304 */     this.m_cmdReadCurrentSettings1 = new MM508Command.Command(this, 55, "Read Current Settings1", 28, 1, 254, 3, 7);
/*      */ 
/*  309 */     this.m_cmdReadCurrentSettings2 = new MM508Command.Command(this, 55, "Read Current Settings2", 26, 1, 7296, 3, 7);
/*      */ 
/*  314 */     this.m_cmdReadCurrentSettings3 = new MM508Command.Command(this, 55, "Read Current Settings3", 4, 1, 7926, 3, 7);
/*      */ 
/*  319 */     this.m_cmdReadCurrentSettings4 = new MM508Command.Command(this, 55, "Read Current Settings4", 2, 1, 1420, 3, 7);
/*      */ 
/*  324 */     this.m_cmdReadCurrentSettings5 = new MM508Command.Command(this, 64, "Read Current Settings5", 4, 1, 7);
/*      */ 
/*  328 */     this.m_cmdReadTodaysTotals = new MM508Command.Command(this, 65, "Read Today's Total Insulin", 4, 1, 7);
/*      */ 
/*  332 */     this.m_cmdReadDataPointers = new MM508Command.Command(this, 56, "Read Memory Pointers (header page)", 250, 1, 1, 2, 0);
/*      */ 
/*  336 */     this.m_cmdReadBatteryStatus = new MM508Command.Command(this, 34, "Read Battery Status", 3, 1, 0);
/*      */ 
/*  339 */     this.m_cmdReadRemainingInsulin = new MM508Command.Command(this, 35, "Read Remaining Insulin", 2, 1, 0);
/*      */ 
/*  343 */     this.m_snapshotCreator = new SnapshotCreator();
/*      */   }
/*      */ 
/*      */   void initComStation()
/*      */     throws IOException
/*      */   {
/*  356 */     Contract.pre(getRS232Port() != null);
/*  357 */     Contract.pre(getRS232Port().isOpen());
/*      */   }
/*      */ 
/*      */   void shutDownComStation()
/*      */     throws IOException
/*      */   {
/*      */   }
/*      */ 
/*      */   void shutDownSerialPort()
/*      */     throws IOException
/*      */   {
/*      */   }
/*      */ 
/*      */   static final boolean isFirmwareVersionSupported(String paramString)
/*      */   {
/*  387 */     boolean bool1 = "VER 60G".equals(paramString);
/*  388 */     boolean bool2 = "VER 60I".equals(paramString);
/*  389 */     return (bool1) || (bool2);
/*      */   }
/*      */ 
/*      */   void initDevice()
/*      */     throws IOException, BadDeviceCommException
/*      */   {
/*  402 */     Contract.pre(getRS232Port() != null);
/*  403 */     Contract.pre(getRS232Port().isOpen());
/*  404 */     Contract.pre(isFirmwareVersionSupported(this.m_firmwareVersion));
/*      */   }
/*      */ 
/*      */   void decodeReply(MMPump.Command paramCommand)
/*      */     throws BadDeviceValueException
/*      */   {
/*  419 */     switch (paramCommand.m_commandCode)
/*      */     {
/*      */     case 55:
/*  423 */       switch (paramCommand.m_address) {
/*      */       case 254:
/*  425 */         decodeCurrentSettings1(paramCommand.m_rawData);
/*  426 */         break;
/*      */       case 7296:
/*  428 */         decodeCurrentSettings2(paramCommand.m_rawData);
/*  429 */         break;
/*      */       case 7926:
/*  431 */         decodeCurrentSettings3(paramCommand.m_rawData);
/*  432 */         break;
/*      */       case 1420:
/*  434 */         decodeCurrentSettings4(paramCommand.m_rawData);
/*      */       }
/*      */ 
/*  438 */       break;
/*      */     case 56:
/*  444 */       switch (paramCommand.m_address) {
/*      */       case 1:
/*  446 */         decodeDataPointers(paramCommand.m_rawData);
/*      */       }
/*      */ 
/*  450 */       break;
/*      */     case 64:
/*  454 */       decodeTempBasal(paramCommand.m_rawData);
/*  455 */       break;
/*      */     case 65:
/*  457 */       decodeTodaysDailyTotals(paramCommand.m_rawData);
/*  458 */       break;
/*      */     case 34:
/*  460 */       decodeBatteryStatus(paramCommand.m_rawData);
/*  461 */       break;
/*      */     case 35:
/*  463 */       decodeRemainingInsulin(paramCommand.m_rawData);
/*  464 */       break;
/*      */     }
/*      */   }
/*      */ 
/*      */   private void decodeCurrentSettings1(int[] paramArrayOfInt)
/*      */     throws BadDeviceValueException
/*      */   {
/*  483 */     Contract.pre(paramArrayOfInt != null);
/*      */ 
/*  488 */     this.m_settingTimeFormat = MedicalDevice.Util.makeUnsignedShort(paramArrayOfInt[0], paramArrayOfInt[1]);
/*      */ 
/*  491 */     MedicalDevice.Util.verifyDeviceValue(this.m_settingTimeFormat, 0, 1, "Time Display Format");
/*      */ 
/*  494 */     int i = MedicalDevice.Util.makeUnsignedShort(paramArrayOfInt[4], paramArrayOfInt[5]);
/*      */ 
/*  496 */     MedicalDevice.Util.verifyDeviceValue(i, 0, 1, "Variable Bolus Enable");
/*  497 */     this.m_settingVarBolusEnable = (i == 1);
/*      */ 
/*  500 */     i = MedicalDevice.Util.makeUnsignedShort(paramArrayOfInt[6], paramArrayOfInt[7]);
/*      */ 
/*  502 */     MedicalDevice.Util.verifyDeviceValue(i, 0, 1, "Audio Bolus Enable");
/*  503 */     this.m_settingAudioBolusEnable = (i == 1);
/*      */ 
/*  506 */     i = MedicalDevice.Util.makeUnsignedShort(paramArrayOfInt[24], paramArrayOfInt[25]);
/*      */ 
/*  508 */     switch (i) {
/*      */     case 30:
/*  510 */       this.m_settingBeepVolume = 1;
/*  511 */       break;
/*      */     case 100:
/*  513 */       this.m_settingBeepVolume = 2;
/*  514 */       break;
/*      */     case 280:
/*  516 */       this.m_settingBeepVolume = 3;
/*  517 */       break;
/*      */     default:
/*  520 */       throw new BadDeviceValueException("The value of " + i + " for Beep Volume is invalid; " + "must be " + 30 + ", " + 100 + ", or " + 280);
/*      */     }
/*      */ 
/*  526 */     i = MedicalDevice.Util.makeUnsignedShort(paramArrayOfInt[2], paramArrayOfInt[3]);
/*      */ 
/*  528 */     MedicalDevice.Util.verifyDeviceValue(i, 0, 1, "Basal Pattern Enable");
/*  529 */     this.m_settingBasalPatternEnable = (i == 1);
/*      */ 
/*  532 */     i = MedicalDevice.Util.makeUnsignedShort(paramArrayOfInt[12], paramArrayOfInt[13]);
/*      */ 
/*  534 */     MedicalDevice.Util.verifyDeviceValue(i, 0, 1, "Block Enable");
/*  535 */     this.m_settingBlockEnable = (i == 1);
/*      */ 
/*  538 */     this.m_settingAlarmMode = MedicalDevice.Util.makeUnsignedShort(paramArrayOfInt[26], paramArrayOfInt[27]);
/*      */ 
/*  542 */     MedicalDevice.Util.verifyDeviceValue(this.m_settingAlarmMode, 1, 2, "Alarm Mode");
/*      */ 
/*  545 */     logInfo(this, "decodeCurrentSettings1: *** CURRENT SETTINGS1 ***");
/*  546 */     logInfo(this, "decodeCurrentSettings1: Time Format = " + (this.m_settingTimeFormat == 1 ? "24h" : "12h"));
/*      */ 
/*  548 */     logInfo(this, "decodeCurrentSettings1: Variable Bolus On = " + this.m_settingVarBolusEnable);
/*      */ 
/*  550 */     logInfo(this, "decodeCurrentSettings1: Audio Bolus On = " + this.m_settingAudioBolusEnable);
/*      */ 
/*  552 */     logInfo(this, "decodeCurrentSettings1: Beep Volume = " + this.m_settingBeepVolume);
/*  553 */     logInfo(this, "decodeCurrentSettings1: Pattern On = " + this.m_settingBasalPatternEnable);
/*      */ 
/*  555 */     logInfo(this, "decodeCurrentSettings1: Block On = " + this.m_settingBlockEnable);
/*  556 */     logInfo(this, "decodeCurrentSettings1: Alarm Mode = " + (this.m_settingAlarmMode == 1 ? "audio" : "vibrate"));
/*      */   }
/*      */ 
/*      */   private void decodeCurrentSettings2(int[] paramArrayOfInt)
/*      */     throws BadDeviceValueException
/*      */   {
/*  570 */     Contract.pre(paramArrayOfInt != null);
/*      */ 
/*  574 */     this.m_settingCurrentBasalPattern = MedicalDevice.Util.makeUnsignedShort(paramArrayOfInt[0], paramArrayOfInt[1]);
/*      */ 
/*  578 */     MedicalDevice.Util.verifyDeviceValue(this.m_settingCurrentBasalPattern, 0, 2, "Current Basal Profile");
/*      */ 
/*  582 */     this.m_settingInsulinConcen = MedicalDevice.Util.makeUnsignedShort(paramArrayOfInt[24], paramArrayOfInt[25]);
/*      */ 
/*  585 */     verifyConcentration(this.m_settingInsulinConcen);
/*      */ 
/*  588 */     this.m_strokesPerBasalUnit = (int)(1000.0D / this.m_settingInsulinConcen);
/*      */ 
/*  590 */     this.m_strokesPerBolusUnit = this.m_strokesPerBasalUnit;
/*      */ 
/*  593 */     if (this.m_settingAudioBolusEnable) {
/*  594 */       int i = MedicalDevice.Util.makeUnsignedShort(paramArrayOfInt[14], paramArrayOfInt[15]);
/*      */ 
/*  597 */       if ((i != 10) && (i != 5)) {
/*  598 */         throw new BadDeviceValueException("The value of " + i + " for Audio Bolus Step Size is invalid; " + "must be 5 or 10");
/*      */       }
/*      */ 
/*  602 */       this.m_settingAudioBolusSize = toBolusInsulin(i);
/*      */     }
/*      */     else {
/*  605 */       this.m_settingAudioBolusSize = 0.0D;
/*      */     }
/*      */ 
/*  609 */     this.m_settingAutoOffDurationHrs = paramArrayOfInt[16];
/*  610 */     MedicalDevice.Util.verifyDeviceValue(this.m_settingAutoOffDurationHrs, 0, 16, "Auto-off Duration");
/*      */ 
/*  613 */     logInfo(this, "decodeCurrentSettings2: *** CURRENT SETTINGS2 ***");
/*  614 */     logInfo(this, "decodeCurrentSettings2: Insulin Concen = " + this.m_settingInsulinConcen);
/*      */ 
/*  616 */     logInfo(this, "decodeCurrentSettings2: Audio Bolus Step Size = " + this.m_settingAudioBolusSize);
/*      */ 
/*  618 */     logInfo(this, "decodeCurrentSettings2: Auto Off Dur = " + this.m_settingAutoOffDurationHrs);
/*      */ 
/*  620 */     logInfo(this, "decodeCurrentSettings2: Current Basal Profile = " + getPatternString(new Integer(this.m_settingCurrentBasalPattern)));
/*      */   }
/*      */ 
/*      */   private final void decodeCurrentSettings3(int[] paramArrayOfInt)
/*      */     throws BadDeviceValueException
/*      */   {
/*  634 */     Contract.pre(paramArrayOfInt != null);
/*      */ 
/*  641 */     int i = MedicalDevice.Util.makeUnsignedShort(paramArrayOfInt[2], paramArrayOfInt[3]);
/*      */ 
/*  643 */     MedicalDevice.Util.verifyDeviceValue(i, 0.0D, toBasalStrokes(35.0D), "Maximum Basal Rate");
/*      */ 
/*  645 */     this.m_settingMaxBasalRate = toBasalInsulin(i);
/*      */ 
/*  648 */     int j = MedicalDevice.Util.makeUnsignedShort(paramArrayOfInt[0], paramArrayOfInt[1]);
/*      */ 
/*  650 */     MedicalDevice.Util.verifyDeviceValue(j, 0.0D, toBolusStrokes(25.0D), "Maximum Bolus Rate");
/*      */ 
/*  652 */     this.m_settingMaxBolus = toBolusInsulin(j);
/*      */ 
/*  654 */     logInfo(this, "decodeCurrentSettings3: *** CURRENT SETTINGS3 ***");
/*  655 */     logInfo(this, "decodeCurrentSettings3: Max Basal Rate = " + this.m_settingMaxBasalRate);
/*      */ 
/*  657 */     logInfo(this, "decodeCurrentSettings3: Max Bolus = " + this.m_settingMaxBolus);
/*      */   }
/*      */ 
/*      */   private final void decodeCurrentSettings4(int[] paramArrayOfInt)
/*      */     throws BadDeviceValueException
/*      */   {
/*  670 */     Contract.pre(paramArrayOfInt != null);
/*      */ 
/*  674 */     int i = MedicalDevice.Util.makeUnsignedShort(paramArrayOfInt[0], paramArrayOfInt[1]);
/*      */ 
/*  676 */     MedicalDevice.Util.verifyDeviceValue(i, 0, 1, "RF Enable");
/*  677 */     this.m_settingRFEnable = (i == 1);
/*      */ 
/*  679 */     logInfo(this, "decodeCurrentSettings4: *** CURRENT SETTINGS4 ***");
/*  680 */     logInfo(this, "decodeCurrentSettings4: RF On = " + this.m_settingRFEnable);
/*      */   }
/*      */ 
/*      */   private void decodeBatteryStatus(int[] paramArrayOfInt)
/*      */     throws BadDeviceValueException
/*      */   {
/*  693 */     Contract.pre(paramArrayOfInt != null);
/*      */ 
/*  697 */     this.m_settingBatteryStatus = paramArrayOfInt[0];
/*  698 */     MedicalDevice.Util.verifyDeviceValue(this.m_settingBatteryStatus, 0, 2, "Battery Status");
/*  699 */     String str = this.m_settingBatteryStatus == 1 ? "Low" : this.m_settingBatteryStatus == 0 ? "Normal" : "Off";
/*      */ 
/*  702 */     logInfo(this, "decodeBatteryStatus: status = " + str);
/*      */   }
/*      */ 
/*      */   private void decodeRemainingInsulin(int[] paramArrayOfInt)
/*      */     throws BadDeviceValueException
/*      */   {
/*  715 */     Contract.pre(paramArrayOfInt != null);
/*      */ 
/*  719 */     int i = MedicalDevice.Util.makeUnsignedShort(paramArrayOfInt[0], paramArrayOfInt[1]);
/*  720 */     this.m_settingRemainingInsulin = (int)toBolusInsulin(i);
/*  721 */     MedicalDevice.Util.verifyDeviceValue(this.m_settingRemainingInsulin, 0, 310, "Remaining Insulin");
/*      */ 
/*  724 */     logInfo(this, "decodeRemainingInsulin: insulin = " + this.m_settingRemainingInsulin);
/*      */   }
/*      */ 
/*      */   private final void decodeTempBasal(int[] paramArrayOfInt)
/*      */     throws BadDeviceValueException
/*      */   {
/*  737 */     Contract.pre(paramArrayOfInt != null);
/*      */ 
/*  744 */     int i = MedicalDevice.Util.makeUnsignedShort(paramArrayOfInt[0], paramArrayOfInt[1]);
/*  745 */     MedicalDevice.Util.verifyDeviceValue(i, 0.0D, toBasalStrokes(35.0D), "Temporary Basal Rate");
/*      */ 
/*  747 */     this.m_settingTempBasalRate = toBasalInsulin(i);
/*      */ 
/*  750 */     this.m_settingTempBasalDurationMin = MedicalDevice.Util.makeUnsignedShort(paramArrayOfInt[2], paramArrayOfInt[3]);
/*  751 */     MedicalDevice.Util.verifyDeviceValue(this.m_settingTempBasalDurationMin, 0, 1440, "Temporary Basal Duration");
/*      */ 
/*  754 */     logInfo(this, "decodeTempBasal: Temp Basal Rate = " + this.m_settingTempBasalRate);
/*      */ 
/*  756 */     logInfo(this, "decodeTempBasal: Temp Basal Remain Dur = " + this.m_settingTempBasalDurationMin + " minutes");
/*      */   }
/*      */ 
/*      */   private final void decodeTodaysDailyTotals(int[] paramArrayOfInt)
/*      */   {
/*  768 */     Contract.pre(paramArrayOfInt != null);
/*      */ 
/*  775 */     int i = MedicalDevice.Util.makeUnsignedShort(paramArrayOfInt[0], paramArrayOfInt[1]);
/*  776 */     this.m_settingTodaysTotal = toBolusInsulin(i);
/*      */ 
/*  779 */     i = MedicalDevice.Util.makeUnsignedShort(paramArrayOfInt[2], paramArrayOfInt[3]);
/*  780 */     this.m_settingYesterdaysTotal = toBolusInsulin(i);
/*      */ 
/*  782 */     logInfo(this, "decodeTodaysDailyTotals: Today's Total Insulin = " + this.m_settingTodaysTotal);
/*      */ 
/*  784 */     logInfo(this, "decodeTodaysDailyTotals: Yesterday's Total Insulin = " + this.m_settingYesterdaysTotal);
/*      */   }
/*      */ 
/*      */   private final void decodeDataPointers(int[] paramArrayOfInt)
/*      */     throws BadDeviceValueException
/*      */   {
/*  798 */     int[] arrayOfInt = new int[16];
/*      */ 
/*  800 */     Contract.pre(paramArrayOfInt != null);
/*      */ 
/*  802 */     MedicalDevice.Util.verifyDeviceValue(!MedicalDevice.Util.isZeros(paramArrayOfInt), "rawData array is all zeroes");
/*  803 */     verifyCRC(paramArrayOfInt, 0, 120, "Data Pointers");
/*      */ 
/*  806 */     for (int i = 0; i < 7; i++)
/*      */     {
/*  808 */       System.arraycopy(paramArrayOfInt, 2 + 16 * i, arrayOfInt, 0, 16);
/*      */ 
/*  812 */       switch (i) {
/*      */       case 0:
/*  814 */         decodeDataPointer(arrayOfInt, this.m_cmdReadBolusData);
/*  815 */         break;
/*      */       case 1:
/*  818 */         decodeDataPointer(arrayOfInt, this.m_cmdReadTotalsData);
/*  819 */         break;
/*      */       case 2:
/*  822 */         decodeDataPointer(arrayOfInt, this.m_cmdReadPrimesData);
/*  823 */         break;
/*      */       case 3:
/*  826 */         decodeDataPointer(arrayOfInt, this.m_cmdReadEventData);
/*  827 */         break;
/*      */       case 4:
/*  830 */         decodeDataPointer(arrayOfInt, this.m_cmdReadAlarmData);
/*  831 */         break;
/*      */       case 5:
/*  834 */         break;
/*      */       case 6:
/*  837 */         decodeDataPointer(arrayOfInt, this.m_cmdReadBasalProfilesData);
/*  838 */         break;
/*      */       default:
/*  841 */         Contract.unreachable();
/*  842 */         return;
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private final void decodeDataPointer(int[] paramArrayOfInt, MMPump.Command paramCommand)
/*      */     throws BadDeviceValueException
/*      */   {
/*  862 */     Contract.pre(paramArrayOfInt != null);
/*  863 */     Contract.pre(paramArrayOfInt.length >= 16);
/*  864 */     Contract.pre(paramCommand != null);
/*      */ 
/*  867 */     paramCommand.m_dataPointer = MedicalDevice.Util.makeUnsignedShort(paramArrayOfInt[10], paramArrayOfInt[11]);
/*      */ 
/*  869 */     MedicalDevice.Util.verifyDeviceValue(paramCommand.m_dataPointer, 0, paramCommand.m_maxRecords - 1, paramCommand.m_description + ", Next Record Pointer (m_dataPointer)");
/*      */ 
/*  872 */     logInfo(this, "decodeDataPointer: " + paramCommand.m_description + " Pointer = " + paramCommand.m_dataPointer);
/*      */ 
/*  876 */     int i = MedicalDevice.Util.makeUnsignedShort(paramArrayOfInt[8], paramArrayOfInt[9]);
/*      */ 
/*  879 */     MedicalDevice.Util.verifyDeviceValue(i, 0, paramCommand.m_maxRecords, paramCommand.m_description + ", Current Record Pointer (dataPointerCurRecs)");
/*      */ 
/*  883 */     logInfo(this, "decodeDataPointer: " + paramCommand.m_description + " Current Records Pointer = " + i); } 
/*      */   private final class SnapshotCreator extends MedicalDevice.SnapshotCreator { private static final int SNAPSHOT_BYTES = 20631;
/*      */     private static final int SNAPCODE_POINTERS_BYTES = 120;
/*      */     private static final int SNAPCODE_TIME_DISPLAY = 1;
/*      */     private static final int SNAPCODE_AUDIO_BOLUS = 2;
/*      */     private static final int SNAPCODE_AUDIO_BOLUS_STEP = 3;
/*      */     private static final int SNAPCODE_VAR_BOLUS = 4;
/*      */     private static final int SNAPCODE_BEEP_VOL = 5;
/*      */     private static final int SNAPCODE_AUTO_OFF = 6;
/*      */     private static final int SNAPCODE_TODAYS_TOTAL_INSULIN = 7;
/*      */     private static final int SNAPCODE_TEMP_BASAL = 8;
/*      */     private static final int SNAPCODE_CONCENTRATION = 9;
/*      */     private static final int SNAPCODE_MAX_BASAL = 10;
/*      */     private static final int SNAPCODE_MAX_BOLUS = 11;
/*      */     private static final int SNAPCODE_CURR_PROFILE = 12;
/*      */     private static final int SNAPCODE_PATTERN = 13;
/*      */     private static final int SNAPCODE_BLOCK = 14;
/*      */     private static final int SNAPCODE_ALARM = 15;
/*      */     private static final int SNAPCODE_RF = 16;
/*      */     private static final int SNAPCODE_BOLUS_HISTORY = 20;
/*      */     private static final int SNAPCODE_TOTALS = 21;
/*      */     private static final int SNAPCODE_EVENTS = 22;
/*      */     private static final int SNAPCODE_ALARMS = 23;
/*      */     private static final int SNAPCODE_PRIMES = 24;
/*      */     private static final int SNAPCODE_CURRENT_BASAL_STD = 25;
/*      */     private static final int SNAPCODE_CURRENT_BASAL_A = 26;
/*      */     private static final int SNAPCODE_CURRENT_BASAL_B = 27;
/*      */     private static final int SNAPCODE_BASAL_CHANGES = 28;
/*      */     private static final int SNAPCODE_POINTERS = 30;
/*      */     private static final int SNAPCODE_BATTERY_STATUS = 31;
/*      */     private static final int SNAPCODE_REMAINING_INSULIN = 32;
/*      */     private static final int SNAPCODE_ERROR_STATUS = 33;
/*      */     private static final int SNAPCODE_REMOTE_ID1 = 34;
/*      */     private static final int SNAPCODE_REMOTE_ID2 = 35;
/*      */     private static final int SNAPCODE_REMOTE_ID3 = 36;
/*      */ 
/*  941 */     SnapshotCreator() { super(20631);
/*  942 */       MM508u.this.m_snapshotFirmwareCount = 8;
/*  943 */       MM508u.this.m_snapshotSerialCount = 10;
/*  944 */       MM508u.this.m_snapshotTimeCount = 7;
/*      */     }
/*      */ 
/*      */     private int[] createRawDataWithPad(MMPump.Command paramCommand)
/*      */     {
/*  963 */       int i = 0;
/*  964 */       int[] arrayOfInt1 = new int[0];
/*      */ 
/*  966 */       Contract.pre(paramCommand != null);
/*      */ 
/*  968 */       if (paramCommand.m_rawData.length <= 250)
/*      */       {
/*  970 */         return paramCommand.m_rawData;
/*      */       }
/*      */ 
/*  975 */       int j = 250 / paramCommand.m_bytesPerRecord;
/*      */ 
/*  977 */       int k = paramCommand.m_maxRecords / j + (paramCommand.m_bytesPerRecord < 125 ? 1 : 0);
/*      */ 
/*  979 */       int m = j * paramCommand.m_bytesPerRecord;
/*      */ 
/*  981 */       int n = 250 - m;
/*  982 */       int[] arrayOfInt2 = new int[n];
/*  983 */       int[] arrayOfInt3 = new int[m];
/*  984 */       int[] arrayOfInt4 = new int[0];
/*      */ 
/*  987 */       for (int i1 = 0; i1 < k - 1; i1++)
/*      */       {
/*  989 */         System.arraycopy(paramCommand.m_rawData, i1 * m, arrayOfInt3, 0, m);
/*      */ 
/*  992 */         arrayOfInt4 = MedicalDevice.Util.concat(arrayOfInt3, arrayOfInt2);
/*      */ 
/*  994 */         arrayOfInt1 = MedicalDevice.Util.concat(arrayOfInt1, arrayOfInt4);
/*  995 */         i += arrayOfInt3.length;
/*      */       }
/*      */ 
/* 1000 */       m = paramCommand.m_rawData.length - i;
/* 1001 */       if (m > 0) {
/* 1002 */         n = 250 - m;
/* 1003 */         arrayOfInt2 = new int[n];
/* 1004 */         arrayOfInt3 = new int[m];
/* 1005 */         System.arraycopy(paramCommand.m_rawData, i, arrayOfInt3, 0, m);
/*      */ 
/* 1008 */         arrayOfInt4 = MedicalDevice.Util.concat(arrayOfInt3, arrayOfInt2);
/*      */ 
/* 1010 */         arrayOfInt1 = MedicalDevice.Util.concat(arrayOfInt1, arrayOfInt4);
/* 1011 */         i += arrayOfInt3.length;
/*      */       }
/*      */ 
/* 1015 */       Contract.post(i == paramCommand.m_rawData.length);
/* 1016 */       return arrayOfInt1;
/*      */     }
/*      */ 
/*      */     void createSnapshotBody()
/*      */     {
/* 1026 */       MM508u.this.m_snapshot = new Snapshot(MM508u.this.m_snapshotFormatID, 2, MM508u.this.m_firmwareVersionRawData, MM508u.this.m_serialNumberRawData, MM508u.this.m_timeStampRawData);
/*      */ 
/* 1029 */       MedicalDevice.logInfo(this, "createSnapshot: creating snapshot");
/*      */ 
/* 1033 */       MM508u.this.m_snapshot.addElement(1, MM508u.this.m_cmdReadCurrentSettings1.m_rawData[0], MM508u.this.m_cmdReadCurrentSettings1.m_rawData[1]);
/*      */ 
/* 1037 */       MM508u.this.m_snapshot.addElement(2, MM508u.this.m_cmdReadCurrentSettings1.m_rawData[6], MM508u.this.m_cmdReadCurrentSettings1.m_rawData[7]);
/*      */ 
/* 1042 */       MM508u.this.m_snapshot.addElement(3, MM508u.this.m_cmdReadCurrentSettings2.m_rawData[14], MM508u.this.m_cmdReadCurrentSettings2.m_rawData[15]);
/*      */ 
/* 1047 */       MM508u.this.m_snapshot.addElement(4, MM508u.this.m_cmdReadCurrentSettings1.m_rawData[4], MM508u.this.m_cmdReadCurrentSettings1.m_rawData[5]);
/*      */ 
/* 1052 */       MM508u.this.m_snapshot.addElement(5, MM508u.this.m_cmdReadCurrentSettings1.m_rawData[24], MM508u.this.m_cmdReadCurrentSettings1.m_rawData[25]);
/*      */ 
/* 1056 */       MM508u.this.m_snapshot.addElement(6, MM508u.this.m_cmdReadCurrentSettings2.m_rawData[16]);
/*      */ 
/* 1059 */       MM508u.this.m_snapshot.addElement(7, MM508u.this.m_cmdReadTodaysTotals.m_rawData);
/*      */ 
/* 1062 */       MM508u.this.m_snapshot.addElement(8, MM508u.this.m_cmdReadCurrentSettings5.m_rawData);
/*      */ 
/* 1065 */       MM508u.this.m_snapshot.addElement(9, MM508u.this.m_cmdReadCurrentSettings2.m_rawData[24], MM508u.this.m_cmdReadCurrentSettings2.m_rawData[25]);
/*      */ 
/* 1069 */       MM508u.this.m_snapshot.addElement(10, MM508u.this.m_cmdReadCurrentSettings3.m_rawData[2], MM508u.this.m_cmdReadCurrentSettings3.m_rawData[3]);
/*      */ 
/* 1073 */       MM508u.this.m_snapshot.addElement(11, MM508u.this.m_cmdReadCurrentSettings3.m_rawData[0], MM508u.this.m_cmdReadCurrentSettings3.m_rawData[1]);
/*      */ 
/* 1077 */       MM508u.this.m_snapshot.addElement(12, MM508u.this.m_cmdReadCurrentSettings2.m_rawData[0], MM508u.this.m_cmdReadCurrentSettings2.m_rawData[1]);
/*      */ 
/* 1082 */       MM508u.this.m_snapshot.addElement(13, MM508u.this.m_cmdReadCurrentSettings1.m_rawData[2], MM508u.this.m_cmdReadCurrentSettings1.m_rawData[3]);
/*      */ 
/* 1086 */       MM508u.this.m_snapshot.addElement(14, MM508u.this.m_cmdReadCurrentSettings1.m_rawData[12], MM508u.this.m_cmdReadCurrentSettings1.m_rawData[13]);
/*      */ 
/* 1090 */       MM508u.this.m_snapshot.addElement(15, MM508u.this.m_cmdReadCurrentSettings1.m_rawData[26], MM508u.this.m_cmdReadCurrentSettings1.m_rawData[27]);
/*      */ 
/* 1094 */       MM508u.this.m_snapshot.addElement(16, MM508u.this.m_cmdReadCurrentSettings4.m_rawData[0], MM508u.this.m_cmdReadCurrentSettings4.m_rawData[1]);
/*      */ 
/* 1100 */       int[] arrayOfInt = createRawDataWithPad(MM508u.this.m_cmdReadBolusData);
/* 1101 */       MM508u.this.m_snapshot.addElement(20, arrayOfInt);
/*      */ 
/* 1103 */       arrayOfInt = createRawDataWithPad(MM508u.this.m_cmdReadTotalsData);
/* 1104 */       MM508u.this.m_snapshot.addElement(21, arrayOfInt);
/*      */ 
/* 1106 */       arrayOfInt = createRawDataWithPad(MM508u.this.m_cmdReadEventData);
/* 1107 */       MM508u.this.m_snapshot.addElement(22, arrayOfInt);
/*      */ 
/* 1109 */       arrayOfInt = createRawDataWithPad(MM508u.this.m_cmdReadAlarmData);
/* 1110 */       MM508u.this.m_snapshot.addElement(23, arrayOfInt);
/*      */ 
/* 1112 */       arrayOfInt = createRawDataWithPad(MM508u.this.m_cmdReadPrimesData);
/* 1113 */       MM508u.this.m_snapshot.addElement(24, arrayOfInt);
/*      */ 
/* 1115 */       arrayOfInt = createRawDataWithPad(MM508u.this.m_cmdReadCurrBasalDataSTD);
/* 1116 */       MM508u.this.m_snapshot.addElement(25, arrayOfInt);
/*      */ 
/* 1118 */       arrayOfInt = createRawDataWithPad(MM508u.this.m_cmdReadCurrBasalDataA);
/* 1119 */       MM508u.this.m_snapshot.addElement(26, arrayOfInt);
/*      */ 
/* 1121 */       arrayOfInt = createRawDataWithPad(MM508u.this.m_cmdReadCurrBasalDataB);
/* 1122 */       MM508u.this.m_snapshot.addElement(27, arrayOfInt);
/*      */ 
/* 1124 */       arrayOfInt = createRawDataWithPad(MM508u.this.m_cmdReadBasalProfilesData);
/* 1125 */       MM508u.this.m_snapshot.addElement(28, arrayOfInt);
/*      */ 
/* 1127 */       MM508u.this.m_snapshot.addElement(30, MM508u.this.m_cmdReadDataPointers.m_rawData, 120);
/*      */ 
/* 1131 */       MM508u.this.m_snapshot.addElement(31, MM508u.this.m_cmdReadBatteryStatus.m_rawData);
/*      */ 
/* 1134 */       MM508u.this.m_snapshot.addElement(32, MM508u.this.m_cmdReadRemainingInsulin.m_rawData);
/*      */ 
/* 1137 */       MM508u.this.m_snapshot.addElement(33, MM508u.this.m_errorStatusRawData);
/*      */ 
/* 1140 */       MM508u.this.m_snapshot.addElement(34, MM508u.this.m_remoteID1RawData);
/* 1141 */       MM508u.this.m_snapshot.addElement(35, MM508u.this.m_remoteID2RawData);
/* 1142 */       MM508u.this.m_snapshot.addElement(36, MM508u.this.m_remoteID3RawData);
/*      */     }
/*      */   }
/*      */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.deviceportreader.MM508u
 * JD-Core Version:    0.6.0
 */