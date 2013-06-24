/*      */ package minimed.ddms.deviceportreader;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import minimed.util.Contract;
/*      */ 
/*      */ class MM511 extends MMPump
/*      */ {
/*      */   static final int SNAPSHOT_FORMAT_ID = 112;
/*      */   private static final int CMD_SUSPEND_RESUME = 77;
/*      */   private static final int CMD_KEYPAD_PUSH = 91;
/*      */   static final int CMD_POWER_CTRL = 93;
/*      */   private static final int CMD_READ_RTC = 112;
/*      */   private static final int CMD_READ_PUMP_ID = 113;
/*      */   private static final int CMD_READ_BATTERY_STATUS = 114;
/*      */   private static final int CMD_READ_REMAINING_INSULIN = 115;
/*      */   private static final int CMD_READ_FIRMWARE_VER = 116;
/*      */   private static final int CMD_READ_ERROR_STATUS = 117;
/*      */   private static final int CMD_READ_REMOTE_CTRL_IDS = 118;
/*      */   private static final int CMD_READ_TEMP_BASAL = 120;
/*      */   private static final int CMD_READ_TODAYS_TOTALS = 121;
/*      */   private static final int CMD_READ_STD_PROFILES = 122;
/*      */   private static final int CMD_READ_A_PROFILES = 123;
/*      */   private static final int CMD_READ_B_PROFILES = 124;
/*      */   private static final int CMD_READ_SETTINGS = 127;
/*      */   private static final int CMD_READ_HISTORY_DATA = 128;
/*      */   private static final int CMD_READ_PUMP_STATE = 131;
/*      */   private static final int CMD_TEMP_BASAL_RATE = 75;
/*      */   private static final int CMD_ENABLE_DISABLE_DETAIL_TRACE = 160;
/*      */   private static final int CMD_READ_PUMP_TRACE = 163;
/*      */   private static final int CMD_READ_DETAIL_TRACE = 164;
/*      */   private static final int CMD_READ_NEW_ALARM_TRACE = 166;
/*      */   private static final int CMD_READ_OLD_ALARM_TRACE = 167;
/*      */   private static final int SETTINGINDEX_AUTO_OFF = 0;
/*      */   private static final int SETTINGINDEX_ALERT_TYPE = 1;
/*      */   private static final int SETTINGINDEX_EASY_BOLUS_ENABLE = 2;
/*      */   private static final int SETTINGINDEX_EASY_BOLUS_STEP = 3;
/*      */   private static final int SETTINGINDEX_VAR_BOLUS_ENABLE = 4;
/*      */   private static final int SETTINGINDEX_MAX_BOLUS = 5;
/*      */   private static final int SETTINGINDEX_MAX_BASAL = 6;
/*      */   private static final int SETTINGINDEX_TIME_FORMAT = 8;
/*      */   private static final int SETTINGINDEX_INSULIN_TYPE = 9;
/*      */   private static final int SETTINGINDEX_PATTERNS_ENABLE = 10;
/*      */   private static final int SETTINGINDEX_CURRENT_PATTERN = 11;
/*      */   private static final int SETTINGINDEX_RF_ENABLE = 12;
/*      */   private static final int SETTINGINDEX_BLOCK_ENABLE = 13;
/*      */   private static final int REC_SIZE_MIN = 64;
/*      */   private static final int REC_SIZE_PROFILE = 128;
/*      */   private static final int REC_SIZE_HISTORY = 1024;
/*      */   private static final int REC_SIZE_TRACE_HISTORY = 1024;
/*      */   private static final int REC_SIZE_NONE = 0;
/*      */   private static final int REC_SIZE_ONE = 1;
/*      */   private static final int HISTORY_REC_COUNT = 32;
/*      */   private static final int LARGE_TRACE_REC_COUNT = 49;
/*      */   private static final int SMALL_TRACE_REC_COUNT = 11;
/*      */   private static final int MIN_EASY_BOLUS_STEP = 1;
/*      */   private static final int MAX_EASY_BOLUS_STEP = 20;
/*      */   private static final int MAX_REMAINING_INSULIN = 310;
/*      */   private static final int MIN_ERROR_CODE = 0;
/*      */   private static final int MAX_ERROR_CODE = 67;
/*      */   private static final int BASE_ALARM_CODE = 100;
/*      */   private static final int MAX_BATTERY_STATUS = 2;
/*      */   private static final int MAX_PUMP_STATE = 3;
/*      */   private static final int MAX_AUTO_OFF_DUR = 24;
/*      */   private static final int REC_COUNT_NONE = 0;
/*      */   private static final int BEEP_VOL511_LOW = 1;
/*      */   private static final int ALERT_TYPE_VIBRATE = 4;
/*      */   private static final int ALARM_MODE_AUDIO = 1;
/*      */   private static final int ALARM_MODE_VIBRATE = 2;
/*      */   private static final int PROFILE_PATTERN_STD = 0;
/*      */   private static final int PROFILE_PATTERN_B = 2;
/*      */   private static final int CONCENTRATION_U100_DEVICE = 0;
/*      */   private static final int CONCENTRATION_U50_DEVICE = 1;
/*      */   private static final int MIN_YEAR = 2000;
/*      */   private static final int MAX_YEAR = 2099;
/*      */   static final int RF_TO_OFF = 0;
/*      */   static final int RF_TO_ON = 1;
/*      */   private static final int RF_ON_MINUTES = 10;
/*      */   private static final int PUMP_NORMAL_STATE = 0;
/*      */   private static final int IO_DELAY_MS = 4;
/*  208 */   private static final String[] NAK_DESCRIPTIONS_TABLE = { "UNKNOWN NAK DESCRIPTION", "REQUEST PAUSE FOR 3 SECONDS", "REQUEST PAUSE UNTIL ACK RECEIVED", "CRC ERROR", "REFUSE PROGRAM UPLOAD", "TIMEOUT ERROR", "COUNTER SEQUENCE ERROR", "PUMP IN ERROR STATE", "INCONSISTENT COMMAND REQUEST", "DATA OUT OF RANGE", "DATA CONSISTENCY", "ATTEMPT TO ACTIVATE UNUSED PROFILES", "PUMP DELIVERING BOLUS", "REQUESTED HISTORY BLOCK HAS NO DATA", "HARDWARE FAILURE" };
/*      */   private static final int NAK_PUMP_DELIVERING_BOLUS = 12;
/*      */   static final int NAK_NO_HISTORY_DATA = 13;
/*      */   private static final int KEYPAD_DELAY_MS = 500;
/*      */   private static final int KEYPAD_ESC = 1;
/*      */   private static final int KEYPAD_ACK = 2;
/*      */   private static final String SUSPEND_OFF_BUG_FW1 = "VER 1.6";
/*      */   private static final String SUSPEND_OFF_BUG_FW2 = "VER 1.7";
/*      */   private static final int STROKES_PER_BOLUS_UNIT = 10;
/*      */   private static final int STROKES_PER_BASAL_UNIT = 10;
/*      */   private static final double BATTERY_VOLTAGE_FACTOR = 100.0D;
/*      */   private static final int RF_ON_DELAY = 17000;
/*      */   private int m_normalPumpStateCode;
/*      */ 
/*      */   MM511(int paramInt1, String paramString, int paramInt2, int paramInt3, int paramInt4)
/*      */   {
/*  298 */     Contract.pre(paramInt1, 14, 19);
/*      */ 
/*  301 */     this.m_strokesPerBasalUnit = 10;
/*  302 */     this.m_strokesPerBolusUnit = 10;
/*      */ 
/*  305 */     this.m_description = paramString;
/*  306 */     this.m_linkDevice = paramInt1;
/*  307 */     logInfo(this, "creating interface to the '" + this.m_description + "', link device = " + DevicePortReaderFactory.mapLinkDeviceID(this.m_linkDevice) + ", package version = " + getPackageVersion());
/*      */ 
/*  310 */     this.m_deviceClassID = paramInt2;
/*  311 */     this.m_snapshotFormatID = paramInt3;
/*  312 */     this.m_normalPumpStateCode = paramInt4;
/*  313 */     this.m_minYear = 2000;
/*  314 */     this.m_maxYear = 2099;
/*      */ 
/*  316 */     this.m_cmdReadFirmwareVersion = new Command(116, "Read Firmware Version");
/*      */ 
/*  319 */     this.m_cmdReadPumpId = new Command(113, "Read Pump ID");
/*      */ 
/*  321 */     this.m_cmdReadRealTimeClock = new Command(112, "Read Real Time Clock");
/*      */ 
/*  323 */     this.m_cmdReadCurrentSettings1 = new Command(127, "Read Current Settings");
/*      */ 
/*  325 */     this.m_cmdReadTodaysTotals = new Command(121, "Read Today's Total Insulin");
/*      */ 
/*  328 */     this.m_cmdReadTempBasal = new Command(120, "Read Temporary Basal");
/*      */ 
/*  331 */     this.m_cmdReadCurrBasalDataSTD = new Command(122, "Read Standard Profiles Data", 128, 1, 8);
/*      */ 
/*  340 */     this.m_cmdReadBasalProfilesData = null;
/*      */ 
/*  343 */     this.m_cmdReadCurrBasalDataA = new Command(123, "Read Profiles A Data", 128, 1, 9);
/*      */ 
/*  352 */     this.m_cmdReadCurrBasalDataB = new Command(124, "Read Profiles B Data", 128, 1, 10);
/*      */ 
/*  360 */     this.m_cmdReadBatteryStatus = new Command(114, "Read Battery Status");
/*      */ 
/*  362 */     this.m_cmdReadRemainingInsulin = new Command(115, "Read Remaining Insulin");
/*      */ 
/*  365 */     this.m_cmdReadErrorStatus = new Command(117, "Read Pump Error Status (current alarm code)");
/*      */ 
/*  368 */     this.m_cmdReadRemoteIDs = new Command(118, "Read Remote Control Ids");
/*      */ 
/*  371 */     this.m_cmdReadState = new Command(131, "Read Pump State");
/*      */ 
/*  373 */     this.m_cmdReadHistoryData = new CommandHistoryData();
/*      */ 
/*  377 */     this.m_cmdPowerControl = new Command(CMD_POWER_CTRL, "Set RF Power On", 2);
/*  378 */     this.m_cmdPowerControl.m_commandParameters[0] = RF_TO_ON;
/*  379 */     this.m_cmdPowerControl.m_commandParameters[1] = RF_ON_MINUTES;
/*  380 */     this.m_cmdPowerControl.m_maxRetries = 0;
/*  381 */     this.m_cmdPowerControl.setEffectTime(RF_ON_DELAY);
/*      */ 
/*  384 */     this.m_cmdSetSuspend = new Command(77, "Set Suspend", 1);
/*  385 */     this.m_cmdSetSuspend.m_commandParameters[0] = 1;
/*      */ 
/*  388 */     this.m_cmdCancelSuspend = new Command(77, "Cancel Suspend", 1);
/*  389 */     this.m_cmdCancelSuspend.m_commandParameters[0] = 0;
/*      */ 
/*  392 */     this.m_cmdDetectBolus = new Command(75, "Set Temp Basal Rate (bolus detection only)", 3);
/*      */ 
/*  394 */     this.m_cmdDetectBolus.m_commandParameters[0] = 0;
/*  395 */     this.m_cmdDetectBolus.m_commandParameters[1] = 0;
/*  396 */     this.m_cmdDetectBolus.m_commandParameters[2] = 0;
/*  397 */     this.m_cmdDetectBolus.m_maxRetries = 0;
/*      */ 
/*  400 */     this.m_cmdEnableDetailTrace = new Command(160, "Enable Detail Trace", 1);
/*      */ 
/*  402 */     this.m_cmdEnableDetailTrace.m_commandParameters[0] = 1;
/*      */ 
/*  404 */     this.m_cmdDisableDetailTrace = new Command(160, "Disable Detail Trace", 1);
/*      */ 
/*  406 */     this.m_cmdDisableDetailTrace.m_commandParameters[0] = 0;
/*      */ 
/*  408 */     this.m_cmdReadPumpTrace = new Command(163, "Read Pump Trace", 1024, 49, 0);
/*      */ 
/*  410 */     this.m_cmdReadDetailTrace = new Command(164, "Read Detail Trace", 1024, 11, 0);
/*      */ 
/*  412 */     this.m_cmdReadNewAlarmTrace = new Command(166, "Read New Alarm Trace", 1024, 11, 0);
/*      */ 
/*  414 */     this.m_cmdReadOldAlarmTrace = new Command(167, "Read Old Alarm Trace", 1024, 11, 0);
/*      */ 
/*  417 */     this.m_snapshotCreator = new SnapshotCreator();
/*  418 */     m_baudRate = 10;
/*  419 */     m_ioDelayMS = 4;
/*  420 */     setState(1);
/*      */   }
/*      */ 
/*      */   MM511(int paramInt)
/*      */   {
/*  431 */     this(paramInt, "MiniMed MMT-511 (Paradigm) Insulin Pump", 1, 112, 0);
/*      */   }
/*      */ 
/*      */   void initSerialPort(int paramInt)
/*      */     throws IOException
/*      */   {
/*  446 */     this.m_serialPortNumber = paramInt;
/*      */   }
/*      */ 
/*      */   void initCommunicationsLink(int paramInt)
/*      */     throws IOException, SerialIOHaltedException, BadDeviceCommException
/*      */   {
/*  459 */     setPhase(3);
/*      */ 
/*  461 */     switch (this.m_linkDevice) {
/*      */     case 14:
/*      */     case 15:
/*  464 */       setCommunicationsLink(new ComLink1(this, this.m_serialNumber, this.m_linkDevice, paramInt));
/*      */ 
/*  466 */       break;
/*      */     case 19:
/*  468 */       setCommunicationsLink(new ComLink2(this, this.m_serialNumber));
/*  469 */       break;
/*      */     default:
/*  471 */       Contract.unreachable();
/*      */     }
/*      */ 
/*  475 */     getCommunicationsLink().initCommunications();
/*  476 */     logInfo(this, "initCommunicationsLink: ************************ DONE **************************");
/*      */   }
/*      */ 
/*      */   void shutDownCommunicationsLink()
/*      */     throws IOException
/*      */   {
/*  487 */     if (getCommunicationsLink() != null) {
/*  488 */       getCommunicationsLink().endCommunications();
/*  489 */       setCommunicationsLink(null);
/*      */     }
/*      */   }
/*      */ 
/*      */   void shutDownSerialPort()
/*      */     throws IOException
/*      */   {
/*  500 */     if (getCommunicationsLink() != null)
/*      */     {
/*  502 */       getCommunicationsLink().endCommunications();
/*      */     }
/*      */   }
/*      */ 
/*      */   void initDevice()
/*      */     throws IOException, BadDeviceCommException, ConnectToPumpException
/*      */   {
/*  518 */     Contract.pre(getCommunicationsLink() != null);
/*      */ 
/*  521 */     setPhase(4);
/*  522 */     getCommunicationsLink().resetTotalReadByteCount();
/*  523 */     this.m_cmdPowerControl.execute();
/*      */ 
/*  528 */     this.m_cmdReadErrorStatus.execute();
/*      */     try {
/*  530 */       decodeReply(this.m_cmdReadErrorStatus);
/*      */     } catch (BadDeviceValueException localBadDeviceValueException1) {
/*  532 */       throw new ConnectToPumpException("Bad Error Status Reply", 2, MedicalDevice.Util.makeString(this.m_cmdReadErrorStatus.m_rawData));
/*      */     }
/*      */ 
/*  538 */     if (this.m_settingErrorStatus != 0) {
/*  539 */       throw new ConnectToPumpException("Pump Alarm (error)", 2, Integer.toString(this.m_settingErrorStatus));
/*      */     }
/*      */ 
/*  545 */     this.m_cmdReadState.execute();
/*      */     try {
/*  547 */       decodeReply(this.m_cmdReadState);
/*      */     } catch (BadDeviceValueException localBadDeviceValueException2) {
/*  549 */       throw new ConnectToPumpException("Bad Pump State Reply", 2, MedicalDevice.Util.makeString(this.m_cmdReadState.m_rawData));
/*      */     }
/*      */ 
/*  555 */     if (this.m_settingPumpState != this.m_normalPumpStateCode) {
/*  556 */       throw new ConnectToPumpException("Pump Alarm (state)", 2, Integer.toString(this.m_settingPumpState));
/*      */     }
/*      */ 
/*  562 */     this.m_cmdReadTempBasal.execute();
/*      */     try {
/*  564 */       decodeReply(this.m_cmdReadTempBasal);
/*      */     } catch (BadDeviceValueException localBadDeviceValueException3) {
/*  566 */       throw new ConnectToPumpException("Bad Temp Basal Reply", 2, MedicalDevice.Util.makeString(this.m_cmdReadTempBasal.m_rawData));
/*      */     }
/*      */ 
/*  572 */     if (this.m_settingTempBasalDurationMin != 0) {
/*  573 */       throw new ConnectToPumpException("Pump Active (temp basal)", 3, "PUMP DELIVERING TEMPORARY BASAL");
/*      */     }
/*      */ 
/*  580 */     initDevice2();
/*  581 */     getCommunicationsLink().resetTotalReadByteCount();
/*      */   }
/*      */ 
/*      */   void initDevice2()
/*      */     throws BadDeviceCommException, ConnectToPumpException
/*      */   {
/*      */     boolean bool;
/*      */     try
/*      */     {
/*  599 */       bool = detectActiveBolus();
/*      */     } catch (BadDeviceValueException localBadDeviceValueException) {
/*  601 */       throw new ConnectToPumpException("Bad Pump Active (bolus) Reply", 3, MedicalDevice.Util.makeString(this.m_cmdDetectBolus.m_rawData));
/*      */     }
/*      */ 
/*  606 */     if (bool) {
/*  607 */       throw new ConnectToPumpException("Pump Active (bolus)", 3, NAK_DESCRIPTIONS_TABLE[12]);
/*      */     }
/*      */ 
/*  612 */     logInfo(this, "initDevice2: ************************ DONE **************************");
/*      */   }
/*      */ 
/*      */   boolean detectActiveBolus()
/*      */     throws BadDeviceCommException, BadDeviceValueException
/*      */   {
/*  623 */     int i = 0;
/*      */     try
/*      */     {
/*  627 */       this.m_cmdDetectBolus.execute();
/*      */     }
/*      */     catch (BadDeviceCommException localBadDeviceCommException) {
/*  630 */       Integer localInteger = localBadDeviceCommException.getErrorCode();
/*      */ 
/*  632 */       if (localInteger != null) {
/*  633 */         if (localInteger.intValue() == 12) {
/*  634 */           i = 1;
/*      */         }
/*      */         else {
/*  637 */           throw localBadDeviceCommException;
/*      */         }
/*      */       }
/*      */       else {
/*  641 */         throw localBadDeviceCommException;
/*      */       }
/*      */     }
/*  644 */     return i;
/*      */   }
/*      */ 
/*      */   void shutDownPump()
/*      */     throws BadDeviceCommException, IOException
/*      */   {
/*  656 */     if (getCommunicationsLink() != null) {
/*  657 */       if (this.m_cmdCancelSuspend != null)
/*      */       {
/*  659 */         shutDownPump2();
/*  660 */         this.m_cmdCancelSuspend.execute();
/*      */       }
/*      */ 
/*  665 */       int[] arrayOfInt = new int[64];
/*  666 */       arrayOfInt[0] = 0;
/*  667 */       Command localCommand = new Command(93, "Set RF Power Off", arrayOfInt, 2);
/*      */ 
/*  669 */       localCommand.m_maxRetries = 0;
/*      */       try
/*      */       {
/*  676 */         localCommand.execute();
/*      */       } catch (BadDeviceCommException localBadDeviceCommException) {
/*  678 */         logInfo(this, "shutDownPump: ignoring BadDeviceCommException: " + localBadDeviceCommException);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   void shutDownPump2()
/*      */     throws BadDeviceCommException, SerialIOHaltedException
/*      */   {
/*  696 */     if ((this.m_firmwareVersion != null) && (
/*  697 */       (this.m_firmwareVersion.startsWith("VER 1.6")) || (this.m_firmwareVersion.startsWith("VER 1.7"))))
/*      */     {
/*  704 */       int[] arrayOfInt = new int[64];
/*  705 */       arrayOfInt[0] = 2;
/*  706 */       Command localCommand = new Command(91, "Keypad Push (ack)", arrayOfInt, 1);
/*      */ 
/*  708 */       localCommand.execute();
/*      */ 
/*  710 */       MedicalDevice.Util.sleepMS(500);
/*      */ 
/*  713 */       arrayOfInt[0] = 1;
/*  714 */       localCommand = new Command(91, "Keypad Push (esc)", arrayOfInt, 1);
/*      */ 
/*  716 */       localCommand.execute();
/*      */ 
/*  718 */       MedicalDevice.Util.sleepMS(500);
/*      */     }
/*      */   }
/*      */ 
/*      */   static String getNAKDescription(int paramInt)
/*      */   {
/*      */     String str;
/*  733 */     if (paramInt <= NAK_DESCRIPTIONS_TABLE.length - 1)
/*  734 */       str = NAK_DESCRIPTIONS_TABLE[paramInt];
/*      */     else {
/*  736 */       str = "UNKNOWN NAK DESCRIPTION";
/*      */     }
/*  738 */     return str;
/*      */   }
/*      */ 
/*      */   int getSettingIndexMaxBasal()
/*      */   {
/*  747 */     return 6;
/*      */   }
/*      */ 
/*      */   int getSettingIndexTimeDisplayFormat()
/*      */   {
/*  756 */     return 8;
/*      */   }
/*      */ 
/*      */   void decodeMaxBolus(int[] paramArrayOfInt)
/*      */     throws BadDeviceValueException
/*      */   {
/*  770 */     Contract.pre(paramArrayOfInt != null);
/*  771 */     Contract.pre(paramArrayOfInt.length == 64);
/*      */ 
/*  774 */     int i = paramArrayOfInt[5];
/*  775 */     MedicalDevice.Util.verifyDeviceValue(i, 0.0D, toBolusStrokes(25.0D), "Maximum Bolus Rate");
/*  776 */     this.m_settingMaxBolus = toBolusInsulin(i);
/*      */   }
/*      */ 
/*      */   void decodeReply(MMPump.Command paramCommand)
/*      */     throws BadDeviceValueException
/*      */   {
/*  790 */     switch (paramCommand.m_commandCode) {
/*      */     case 113:
/*  792 */       decodeSerialNumber(paramCommand.m_rawData);
/*  793 */       break;
/*      */     case 116:
/*  795 */       decodeFirmwareVersion(paramCommand.m_rawData);
/*  796 */       break;
/*      */     case 112:
/*  798 */       decodeCurrentTimeStamp(paramCommand.m_rawData);
/*  799 */       break;
/*      */     case 127:
/*  801 */       decodeCurrentSettings(paramCommand.m_rawData);
/*  802 */       break;
/*      */     case 120:
/*  804 */       decodeTempBasal(paramCommand.m_rawData);
/*  805 */       break;
/*      */     case 121:
/*  807 */       decodeTodaysDailyTotals(paramCommand.m_rawData);
/*  808 */       break;
/*      */     case 114:
/*  810 */       decodeBatteryStatus(paramCommand.m_rawData);
/*  811 */       break;
/*      */     case 115:
/*  813 */       decodeRemainingInsulin(paramCommand.m_rawData);
/*  814 */       break;
/*      */     case 117:
/*  816 */       decodeErrorStatus(paramCommand.m_rawData);
/*  817 */       break;
/*      */     case 131:
/*  819 */       decodePumpState(paramCommand.m_rawData);
/*  820 */       break;
/*      */     case 118:
/*  822 */       decodeRemoteIds(paramCommand.m_rawData);
/*  823 */       break;
/*      */     case 119:
/*      */     case 122:
/*      */     case 123:
/*      */     case 124:
/*      */     case 125:
/*      */     case 126:
/*      */     case 128:
/*      */     case 129:
/*      */     case 130:
/*      */     }
/*      */   }
/*      */ 
/*      */   static void verifyConcentration(int paramInt) throws BadDeviceValueException {
/*  837 */     MedicalDevice.Util.verifyDeviceValue((paramInt == 1) || (paramInt == 0), "The Insulin Concentration value of " + paramInt + " is invalid; must be " + 1 + " or " + 0);
/*      */   }
/*      */ 
/*      */   private void decodeCurrentTimeStamp(int[] paramArrayOfInt)
/*      */     throws BadDeviceValueException
/*      */   {
/*  859 */     Contract.pre(paramArrayOfInt != null);
/*  860 */     Contract.pre(paramArrayOfInt.length == 64);
/*      */ 
/*  864 */     int i = paramArrayOfInt[0];
/*  865 */     int j = paramArrayOfInt[1];
/*  866 */     int k = paramArrayOfInt[2];
/*  867 */     int m = MedicalDevice.Util.makeUnsignedShort(paramArrayOfInt[3], paramArrayOfInt[4]);
/*      */ 
/*  869 */     int n = paramArrayOfInt[5];
/*  870 */     int i1 = paramArrayOfInt[6];
/*      */ 
/*  872 */     this.m_timeStamp = createTimestamp(i1, n, m, i, j, k);
/*      */ 
/*  881 */     logInfo(this, "decodeCurrentTimeStamp: current time stamp for device is " + this.m_timeStamp);
/*      */   }
/*      */ 
/*      */   void decodeCurrentSettings(int[] paramArrayOfInt)
/*      */     throws BadDeviceValueException
/*      */   {
/*  899 */     Contract.pre(paramArrayOfInt != null);
/*  900 */     Contract.pre(paramArrayOfInt.length == 64);
/*      */ 
/*  905 */     this.m_settingAutoOffDurationHrs = paramArrayOfInt[0];
/*  906 */     MedicalDevice.Util.verifyDeviceValue(this.m_settingAutoOffDurationHrs, 0, 24, "Auto-off Duration");
/*      */ 
/*  913 */     int i = paramArrayOfInt[1];
/*  914 */     MedicalDevice.Util.verifyDeviceValue(i, 1, 4, "Alert type-Beep volume");
/*      */ 
/*  920 */     if (i == 4) {
/*  921 */       this.m_settingBeepVolume = -1;
/*  922 */       this.m_settingAlarmMode = 2;
/*      */     } else {
/*  924 */       this.m_settingBeepVolume = i;
/*  925 */       this.m_settingAlarmMode = 1;
/*      */     }
/*      */ 
/*  929 */     i = paramArrayOfInt[2];
/*  930 */     MedicalDevice.Util.verifyDeviceValue(i, 0, 1, "Easy Bolus Enable");
/*  931 */     this.m_settingAudioBolusEnable = (i == 1);
/*      */ 
/*  934 */     if (this.m_settingAudioBolusEnable) {
/*  935 */       j = paramArrayOfInt[3];
/*  936 */       MedicalDevice.Util.verifyDeviceValue(j, 1, 20, "Easy (Audio) Bolus Step");
/*      */ 
/*  941 */       this.m_settingAudioBolusSize = toBolusInsulin(j);
/*      */     } else {
/*  943 */       this.m_settingAudioBolusSize = 0.0D;
/*      */     }
/*      */ 
/*  947 */     i = paramArrayOfInt[4];
/*  948 */     MedicalDevice.Util.verifyDeviceValue(i, 0, 1, "Variable Bolus Enable");
/*  949 */     this.m_settingVarBolusEnable = (i == 1);
/*      */ 
/*  952 */     decodeMaxBolus(paramArrayOfInt);
/*      */ 
/*  955 */     int j = MedicalDevice.Util.makeUnsignedShort(paramArrayOfInt[getSettingIndexMaxBasal()], paramArrayOfInt[(getSettingIndexMaxBasal() + 1)]);
/*      */ 
/*  958 */     MedicalDevice.Util.verifyDeviceValue(j, 0.0D, toBasalStrokes(35.0D), "Maximum Basal Rate");
/*      */ 
/*  963 */     this.m_settingMaxBasalRate = toBasalInsulin(j);
/*      */ 
/*  966 */     this.m_settingTimeFormat = paramArrayOfInt[getSettingIndexTimeDisplayFormat()];
/*  967 */     MedicalDevice.Util.verifyDeviceValue(this.m_settingTimeFormat, 0, 1, "Time Display Format");
/*      */ 
/*  970 */     this.m_settingInsulinConcen = paramArrayOfInt[9];
/*  971 */     verifyConcentration(this.m_settingInsulinConcen);
/*  972 */     this.m_settingInsulinConcen = mapConcentration(this.m_settingInsulinConcen);
/*      */ 
/*  975 */     i = paramArrayOfInt[10];
/*  976 */     MedicalDevice.Util.verifyDeviceValue(i, 0, 1, "Basal Patterns Enable");
/*  977 */     this.m_settingBasalPatternEnable = (i == 1);
/*      */ 
/*  980 */     this.m_settingCurrentBasalPattern = paramArrayOfInt[11];
/*  981 */     MedicalDevice.Util.verifyDeviceValue(this.m_settingCurrentBasalPattern, 0, 2, "Selected Pattern");
/*      */ 
/*  988 */     i = paramArrayOfInt[12];
/*  989 */     MedicalDevice.Util.verifyDeviceValue(i, 0, 1, "RF Enable");
/*  990 */     this.m_settingRFEnable = (i == 1);
/*      */ 
/*  993 */     i = paramArrayOfInt[13];
/*  994 */     MedicalDevice.Util.verifyDeviceValue(i, 0, 1, "Block Enable");
/*  995 */     this.m_settingBlockEnable = (i == 1);
/*      */ 
/*  997 */     logInfo(this, "decodeCurrentSettings: *** CURRENT SETTINGS ***");
/*  998 */     logInfo(this, "decodeCurrentSettings: Auto Off Dur = " + this.m_settingAutoOffDurationHrs);
/*      */ 
/* 1001 */     if (this.m_settingBeepVolume != -1) {
/* 1002 */       logInfo(this, "decodeCurrentSettings: Beep Volume = " + this.m_settingBeepVolume);
/*      */     }
/* 1004 */     logInfo(this, "decodeCurrentSettings: Alarm Mode = " + this.m_settingAlarmMode);
/* 1005 */     logInfo(this, "decodeCurrentSettings: Easy (Audio) Bolus On = " + this.m_settingAudioBolusEnable);
/*      */ 
/* 1008 */     logInfo(this, "decodeCurrentSettings: Easy (Audio) Bolus Step Size = " + this.m_settingAudioBolusSize);
/*      */ 
/* 1011 */     logInfo(this, "decodeCurrentSettings: Variable Bolus On = " + this.m_settingVarBolusEnable);
/*      */ 
/* 1014 */     logInfo(this, "decodeCurrentSettings: Max Bolus = " + this.m_settingMaxBolus);
/* 1015 */     logInfo(this, "decodeCurrentSettings: Max Basal Rate = " + this.m_settingMaxBasalRate);
/* 1016 */     logInfo(this, "decodeCurrentSettings: Time Format = " + (this.m_settingTimeFormat == 1 ? "24h" : "12h"));
/*      */ 
/* 1020 */     logInfo(this, "decodeCurrentSettings: Insulin Concen = " + this.m_settingInsulinConcen);
/* 1021 */     logInfo(this, "decodeCurrentSettings: Pattern On = " + this.m_settingBasalPatternEnable);
/* 1022 */     logInfo(this, "decodeCurrentSettings: Current Basal Profile = " + getPatternString(new Integer(this.m_settingCurrentBasalPattern)));
/*      */ 
/* 1026 */     logInfo(this, "decodeCurrentSettings: RF On = " + this.m_settingRFEnable);
/* 1027 */     logInfo(this, "decodeCurrentSettings: Block On = " + this.m_settingBlockEnable);
/*      */   }
/*      */ 
/*      */   private void decodeTempBasal(int[] paramArrayOfInt)
/*      */     throws BadDeviceValueException
/*      */   {
/* 1041 */     Contract.pre(paramArrayOfInt != null);
/* 1042 */     Contract.pre(paramArrayOfInt.length == 64);
/*      */ 
/* 1048 */     int i = MedicalDevice.Util.makeUnsignedShort(paramArrayOfInt[0], paramArrayOfInt[1]);
/* 1049 */     MedicalDevice.Util.verifyDeviceValue(i, 0.0D, toBasalStrokes(35.0D), "Temporary Basal Rate");
/*      */ 
/* 1054 */     this.m_settingTempBasalRate = toBasalInsulin(i);
/*      */ 
/* 1057 */     this.m_settingTempBasalDurationMin = MedicalDevice.Util.makeUnsignedShort(paramArrayOfInt[2], paramArrayOfInt[3]);
/* 1058 */     MedicalDevice.Util.verifyDeviceValue(this.m_settingTempBasalDurationMin, 0, 1440, "Temporary Basal Duration");
/*      */ 
/* 1064 */     logInfo(this, "decodeTempBasal: Temp Basal Rate = " + this.m_settingTempBasalRate);
/* 1065 */     logInfo(this, "decodeTempBasal: Temp Basal Remain Dur = " + this.m_settingTempBasalDurationMin + " minutes");
/*      */   }
/*      */ 
/*      */   private void decodeErrorStatus(int[] paramArrayOfInt)
/*      */     throws BadDeviceValueException
/*      */   {
/* 1082 */     Contract.pre(paramArrayOfInt != null);
/* 1083 */     Contract.pre(paramArrayOfInt.length == 64);
/*      */ 
/* 1089 */     this.m_settingErrorStatus = paramArrayOfInt[0];
/* 1090 */     if (this.m_settingErrorStatus > 100) {
/* 1091 */       this.m_settingErrorStatus -= 100;
/*      */     }
/*      */ 
/* 1094 */     MedicalDevice.Util.verifyDeviceValue(this.m_settingErrorStatus, 0, 67, "Error Status");
/*      */ 
/* 1100 */     logInfo(this, "decodeErrorStatus: error code = " + this.m_settingErrorStatus);
/*      */   }
/*      */ 
/*      */   private void decodeRemoteIds(int[] paramArrayOfInt)
/*      */     throws BadDeviceValueException
/*      */   {
/* 1114 */     Contract.pre(paramArrayOfInt != null);
/* 1115 */     Contract.pre(paramArrayOfInt.length == 64);
/*      */ 
/* 1120 */     int i = 0;
/* 1121 */     int[] arrayOfInt = new int[6];
/*      */ 
/* 1123 */     System.arraycopy(paramArrayOfInt, i, arrayOfInt, 0, arrayOfInt.length);
/* 1124 */     i += arrayOfInt.length;
/* 1125 */     this.m_settingRemoteID1 = MedicalDevice.Util.makeString(arrayOfInt);
/*      */ 
/* 1128 */     System.arraycopy(paramArrayOfInt, i, arrayOfInt, 0, arrayOfInt.length);
/* 1129 */     i += arrayOfInt.length;
/* 1130 */     this.m_settingRemoteID2 = MedicalDevice.Util.makeString(arrayOfInt);
/*      */ 
/* 1133 */     System.arraycopy(paramArrayOfInt, i, arrayOfInt, 0, arrayOfInt.length);
/* 1134 */     i += arrayOfInt.length;
/* 1135 */     this.m_settingRemoteID3 = MedicalDevice.Util.makeString(arrayOfInt);
/*      */ 
/* 1137 */     logInfo(this, "decodeRemoteIds: id1 = " + this.m_settingRemoteID1 + " id2 = " + this.m_settingRemoteID2 + " id3 = " + this.m_settingRemoteID3);
/*      */   }
/*      */ 
/*      */   private void decodePumpState(int[] paramArrayOfInt)
/*      */     throws BadDeviceValueException
/*      */   {
/* 1158 */     Contract.pre(paramArrayOfInt != null);
/* 1159 */     Contract.pre(paramArrayOfInt.length == 64);
/*      */ 
/* 1164 */     this.m_settingPumpState = paramArrayOfInt[0];
/* 1165 */     MedicalDevice.Util.verifyDeviceValue(this.m_settingPumpState, 0, 3, "Pump State");
/*      */ 
/* 1167 */     logInfo(this, "decodePumpState: state = " + this.m_settingPumpState);
/*      */   }
/*      */ 
/*      */   private void decodeRemainingInsulin(int[] paramArrayOfInt)
/*      */     throws BadDeviceValueException
/*      */   {
/* 1181 */     Contract.pre(paramArrayOfInt != null);
/* 1182 */     Contract.pre(paramArrayOfInt.length == 64);
/*      */ 
/* 1187 */     this.m_settingRemainingInsulin = MedicalDevice.Util.makeUnsignedShort(paramArrayOfInt[0], paramArrayOfInt[1]);
/* 1188 */     this.m_settingRemainingInsulin /= 10;
/* 1189 */     MedicalDevice.Util.verifyDeviceValue(this.m_settingRemainingInsulin, 0, 310, "Remaining Insulin");
/*      */ 
/* 1195 */     logInfo(this, "decodeRemainingInsulin: insulin = " + this.m_settingRemainingInsulin);
/*      */   }
/*      */ 
/*      */   private void decodeBatteryStatus(int[] paramArrayOfInt)
/*      */     throws BadDeviceValueException
/*      */   {
/* 1209 */     Contract.pre(paramArrayOfInt != null);
/* 1210 */     Contract.pre(paramArrayOfInt.length == 64);
/*      */ 
/* 1215 */     this.m_settingBatteryStatus = paramArrayOfInt[0];
/* 1216 */     MedicalDevice.Util.verifyDeviceValue(this.m_settingBatteryStatus, 0, 2, "Battery Status");
/*      */ 
/* 1221 */     String str = this.m_settingBatteryStatus == 1 ? "Low" : this.m_settingBatteryStatus == 0 ? "Normal" : "Off";
/*      */ 
/* 1223 */     double d = MedicalDevice.Util.makeInt(paramArrayOfInt[1], paramArrayOfInt[2]) / 100.0D;
/*      */ 
/* 1225 */     logInfo(this, "decodeBatteryStatus: status = " + str + ", voltage = " + d + "V");
/*      */   }
/*      */ 
/*      */   private void decodeTodaysDailyTotals(int[] paramArrayOfInt)
/*      */   {
/* 1237 */     Contract.pre(paramArrayOfInt != null);
/* 1238 */     Contract.pre(paramArrayOfInt.length == 64);
/*      */ 
/* 1244 */     int i = MedicalDevice.Util.makeUnsignedShort(paramArrayOfInt[0], paramArrayOfInt[1]);
/* 1245 */     this.m_settingTodaysTotal = toBolusInsulin(i);
/*      */ 
/* 1248 */     i = MedicalDevice.Util.makeUnsignedShort(paramArrayOfInt[2], paramArrayOfInt[3]);
/* 1249 */     this.m_settingYesterdaysTotal = toBolusInsulin(i);
/*      */ 
/* 1251 */     logInfo(this, "decodeTodaysDailyTotals: Today's Total Insulin = " + this.m_settingTodaysTotal);
/* 1252 */     logInfo(this, "decodeTodaysDailyTotals: Yesterday's Total Insulin = " + this.m_settingYesterdaysTotal);
/*      */   }
/*      */ 
/*      */   private int mapConcentration(int paramInt)
/*      */   {
/* 1264 */     return paramInt == 0 ? 100 : 50;
/*      */   }
/*      */ 
/*      */   void initComStation()
/*      */     throws IOException, SerialIOHaltedException, BadDeviceCommException
/*      */   {
/* 1707 */     initCommunicationsLink(this.m_serialPortNumber);
/*      */   }
/*      */ 
/*      */   void shutDownComStation()
/*      */     throws IOException
/*      */   {
/* 1716 */     shutDownCommunicationsLink();
/*      */   }
/*      */ 
/*      */   private final class SnapshotCreator extends MedicalDevice.SnapshotCreator
/*      */   {
/*      */     private static final int SNAPSHOT_BYTES = 1144;
/*      */     private static final int SNAPCODE_SETTINGS = 1;
/*      */     private static final int SNAPCODE_TODAYS_TOTAL_INSULIN = 2;
/*      */     private static final int SNAPCODE_TEMP_BASAL = 3;
/*      */     private static final int SNAPCODE_HISTORY = 4;
/*      */     private static final int SNAPCODE_CURRENT_BASAL_STD = 5;
/*      */     private static final int SNAPCODE_CURRENT_BASAL_A = 6;
/*      */     private static final int SNAPCODE_CURRENT_BASAL_B = 7;
/*      */     private static final int SNAPCODE_BATTERY_STATUS = 8;
/*      */     private static final int SNAPCODE_REMAINING_INSULIN = 9;
/*      */     private static final int SNAPCODE_ERROR_STATUS = 10;
/*      */     private static final int SNAPCODE_REMOTE_IDS = 11;
/*      */     private static final int SNAPCODE_PUMP_STATE = 12;
/*      */ 
/*      */     SnapshotCreator()
/*      */     {
/* 1631 */       super(1144);
/* 1632 */       MM511.this.m_snapshotFirmwareCount = 64;
/* 1633 */       MM511.this.m_snapshotSerialCount = 64;
/* 1634 */       MM511.this.m_snapshotTimeCount = 64;
/*      */     }
/*      */ 
/*      */     void createSnapshotBody()
/*      */     {
/* 1644 */       MM511.this.m_snapshot = new Snapshot(MM511.this.m_snapshotFormatID, 1, MM511.this.m_cmdReadFirmwareVersion.m_rawData, MM511.this.m_cmdReadPumpId.m_rawData, MM511.this.m_cmdReadRealTimeClock.m_rawData);
/*      */ 
/* 1652 */       MedicalDevice.logInfo(this, "createSnapshot: creating snapshot");
/*      */ 
/* 1655 */       MM511.this.m_snapshot.addElement(1, MM511.this.m_cmdReadCurrentSettings1.m_rawData);
/*      */ 
/* 1659 */       MM511.this.m_snapshot.addElement(2, MM511.this.m_cmdReadTodaysTotals.m_rawData);
/*      */ 
/* 1663 */       MM511.this.m_snapshot.addElement(3, MM511.this.m_cmdReadTempBasal.m_rawData);
/*      */ 
/* 1667 */       MM511.this.m_snapshot.addElement(4, MM511.this.m_cmdReadHistoryData.m_rawData);
/*      */ 
/* 1670 */       MM511.this.m_snapshot.addElement(5, MM511.this.m_cmdReadCurrBasalDataSTD.m_rawData);
/*      */ 
/* 1674 */       MM511.this.m_snapshot.addElement(6, MM511.this.m_cmdReadCurrBasalDataA.m_rawData);
/*      */ 
/* 1677 */       MM511.this.m_snapshot.addElement(7, MM511.this.m_cmdReadCurrBasalDataB.m_rawData);
/*      */ 
/* 1680 */       MM511.this.m_snapshot.addElement(8, MM511.this.m_cmdReadBatteryStatus.m_rawData);
/*      */ 
/* 1683 */       MM511.this.m_snapshot.addElement(9, MM511.this.m_cmdReadRemainingInsulin.m_rawData);
/*      */ 
/* 1687 */       MM511.this.m_snapshot.addElement(10, MM511.this.m_cmdReadErrorStatus.m_rawData);
/*      */ 
/* 1690 */       MM511.this.m_snapshot.addElement(11, MM511.this.m_cmdReadRemoteIDs.m_rawData);
/*      */ 
/* 1693 */       MM511.this.m_snapshot.addElement(12, MM511.this.m_cmdReadState.m_rawData);
/*      */     }
/*      */   }
/*      */ 
/*      */   class CommandHistoryData extends MM511.Command
/*      */   {
/*      */     MM511.Command m_cmdReadHistoryDataPage;
/*      */ 
/*      */     CommandHistoryData()
/*      */     {
/* 1434 */       this(32);
/*      */     }
/*      */ 
/*      */     CommandHistoryData(int arg2)
/*      */     {
/* 1444 */       this(128, "Read History Data", 1024, i);
/*      */     }
/*      */ 
/*      */     CommandHistoryData(int paramString, String paramInt1, int paramInt2, int arg5)
/*      */     {
/* 1457 */       super(paramString, paramInt1, paramInt2, i, 0);
/*      */ 
/* 1460 */       int[] arrayOfInt = new int[64];
/* 1461 */       arrayOfInt[0] = 0;
/* 1462 */       this.m_cmdReadHistoryDataPage = new MM511.Command(MM511.this, paramString, paramInt1 + "(Page)", arrayOfInt, 1);
/*      */ 
/* 1465 */       this.m_cmdReadHistoryDataPage.m_bytesPerRecord = paramInt2;
/* 1466 */       this.m_cmdReadHistoryDataPage.m_maxRecords = 1;
/*      */     }
/*      */ 
/*      */     void execute(int paramInt1, int paramInt2)
/*      */       throws BadDeviceCommException, SerialIOHaltedException
/*      */     {
/* 1489 */       Contract.pre(paramInt1 >= 0);
/* 1490 */       Contract.pre(paramInt2 >= 0);
/* 1491 */       boolean bool = false;
/* 1492 */       this.m_rawData = new int[0];
/*      */ 
/* 1495 */       if (paramInt1 <= paramInt2)
/*      */       {
/* 1497 */         for (i = paramInt1; (i <= paramInt2) && (!bool); i++) {
/* 1498 */           MedicalDevice.logInfoLow(this, "execute: (" + this.m_description + ") reading page " + i + " (reading up to page " + paramInt2 + ")");
/*      */ 
/* 1500 */           bool = readPage(i);
/*      */         }
/*      */       }
/*      */ 
/* 1504 */       for (int i = paramInt1; (i >= paramInt2) && (!bool); i--) {
/* 1505 */         MedicalDevice.logInfoLow(this, "execute: (" + this.m_description + ") reading page " + i + " (reading down to page " + paramInt2 + ")");
/*      */ 
/* 1507 */         bool = readPage(i);
/*      */       }
/*      */ 
/* 1510 */       MedicalDevice.logInfoLow(this, "execute: " + this.m_description + " returned " + this.m_rawData.length + " bytes");
/*      */     }
/*      */ 
/*      */     private boolean readPage(int paramInt)
/*      */       throws SerialIOHaltedException, BadDeviceCommException
/*      */     {
/* 1528 */       int i = 0;
/* 1529 */       int j = 0;
/* 1530 */       setupCommandParameters(paramInt);
/*      */       do {
/*      */         try
/*      */         {
/* 1534 */           this.m_cmdReadHistoryDataPage.execute();
/* 1535 */           i = 1;
/*      */         } catch (BadDeviceCommException localBadDeviceCommException) {
/* 1537 */           j++;
/* 1538 */           if (j <= this.m_maxRetries) {
/* 1539 */             MedicalDevice.logWarning(this, "readPage: re-reading history data page " + paramInt + " (attempts = " + (j + 1) + ")");
/*      */             try
/*      */             {
/* 1542 */               MM511.this.getCommunicationsLink().initCommunications();
/*      */             } catch (IOException localIOException) {
/*      */             }
/*      */           }
/*      */           else {
/* 1547 */             MedicalDevice.logError(this, "page " + paramInt + " failed for " + this.m_description + " after " + j + " attempts" + "; exception = " + localBadDeviceCommException);
/*      */ 
/* 1558 */             throw new BadDeviceCommException("readPage: page " + paramInt + " failed for " + this.m_description + " after " + j + " attempts", localBadDeviceCommException.getErrorCode(), localBadDeviceCommException.getErrorCodeDescription());
/*      */           }
/*      */         }
/*      */       }
/*      */ 
/* 1563 */       while ((i == 0) && (j <= this.m_maxRetries) && (!MM511.this.isHaltRequested()));
/*      */ 
/* 1566 */       int[] arrayOfInt = this.m_cmdReadHistoryDataPage.m_rawData;
/* 1567 */       int k = (arrayOfInt.length == 0) || (MM511.this.isHaltRequested()) ? 1 : 0;
/*      */ 
/* 1569 */       if (arrayOfInt.length > 0) {
/* 1570 */         this.m_rawData = MedicalDevice.Util.concat(this.m_rawData, arrayOfInt);
/*      */       }
/* 1572 */       return k;
/*      */     }
/*      */ 
/*      */     void setupCommandParameters(int paramInt)
/*      */     {
/* 1581 */       this.m_cmdReadHistoryDataPage.m_commandParameters[0] = paramInt;
/*      */     }
/*      */ 
/*      */     void execute()
/*      */       throws BadDeviceCommException
/*      */     {
/* 1595 */       execute(0, this.m_maxRecords - 1);
/*      */     }
/*      */   }
/*      */ 
/*      */   class Command extends MMPump.Command
/*      */   {
/*      */     Command(int paramString, String arg3)
/*      */     {
/* 1295 */       super(paramString, arg3, 64, 1, 0);
/*      */     }
/*      */ 
/*      */     Command(int paramString, String paramArrayOfInt, int[] paramInt1, int arg5)
/*      */     {
/* 1313 */       super(paramString, paramArrayOfInt, 0, 1, 11);
/* 1314 */       this.m_commandParameters = paramInt1;
/* 1315 */       this.m_commandParameterCount = arg5;
/*      */     }
/*      */ 
/*      */     Command(int paramString, String paramInt1, int paramInt2, int paramInt3, int arg6)
/*      */     {
/* 1333 */       super(paramString, paramInt1, paramInt2, paramInt3, arg6);
/*      */     }
/*      */ 
/*      */     Command(int paramString, String paramInt1, int arg4)
/*      */     {
/* 1346 */       super(paramString, paramInt1, 0, 1, 11);
/* 1347 */       this.m_commandParameterCount = arg4;
/* 1348 */       int j = arg4 / 64 + 1;
/* 1349 */       this.m_commandParameters = new int[64 * j];
/*      */     }
/*      */ 
/*      */     private Command()
/*      */     {
/* 1356 */       super(0, "(empty command)", 64, 1, 0);
/*      */     }
/*      */ 
/*      */     void execute()
/*      */       throws BadDeviceCommException, SerialIOHaltedException
/*      */     {
/* 1370 */       int i = 0;
/* 1371 */       int j = 0;
/*      */       do
/*      */       {
/*      */         try
/*      */         {
/* 1376 */           MedicalDevice.logInfoLow(this, "execute: cmd=" + this.m_description + ", m_maxRetries=" + this.m_maxRetries + ", attempts=" + j);
/*      */ 
/* 1380 */           MM511.this.getCommunicationsLink().setTotalReadByteCountExpected(MM511.this.m_totalBytesToRead);
/*      */           try {
/* 1382 */             MM511.this.getCommunicationsLink().execute(this);
/*      */           }
/*      */           catch (BadDeviceValueException localBadDeviceValueException) {
/* 1385 */             localBadDeviceValueException.printStackTrace();
/*      */           }
/* 1387 */           i = 1;
/*      */         } catch (BadDeviceCommException localBadDeviceCommException) {
/* 1389 */           j++;
/* 1390 */           if (j <= this.m_maxRetries) {
/* 1391 */             MedicalDevice.logWarning(this, "execute: cmd failed with exception: " + localBadDeviceCommException + "; retrying (attempts = " + (j + 1) + ")");
/*      */ 
/* 1393 */             MM511.this.setState(7);
/*      */             try {
/* 1395 */               MM511.this.getCommunicationsLink().initCommunications();
/*      */             } catch (IOException localIOException) {
/*      */             }
/*      */           }
/*      */           else {
/* 1400 */             MedicalDevice.logError(this, "cmd " + MedicalDevice.Util.getHex(this.m_commandCode) + " (" + this.m_description + ") failed after " + j + " attempts" + "; exception = " + localBadDeviceCommException);
/*      */ 
/* 1404 */             throw new BadDeviceCommException("execute: cmd " + MedicalDevice.Util.getHex(this.m_commandCode) + " (" + this.m_description + ") failed after " + j + " attempts", localBadDeviceCommException.getErrorCode(), localBadDeviceCommException.getErrorCodeDescription());
/*      */           }
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/* 1410 */       while ((i == 0) && (j <= this.m_maxRetries) && (!MM511.this.isHaltRequested()));
/*      */     }
/*      */   }
/*      */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.deviceportreader.MM511
 * JD-Core Version:    0.6.0
 */