/*      */ package minimed.ddms.deviceportreader;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.util.Date;
/*      */ import java.util.Vector;
/*      */ import minimed.util.Contract;
/*      */ 
/*      */ abstract class MMPump extends MedicalDevice
/*      */ {
/*      */   static final int TYPE_OTHER = 0;
/*      */   static final int TYPE_BOLUSES = 1;
/*      */   static final int TYPE_PRIMES = 2;
/*      */   static final int TYPE_ALARMS = 3;
/*      */   static final int TYPE_BASAL_CHANGES = 4;
/*      */   static final int TYPE_TOTALS = 5;
/*      */   static final int TYPE_EVENTS = 6;
/*      */   static final int TYPE_SETTING = 7;
/*      */   static final int TYPE_PROFILES_STD = 8;
/*      */   static final int TYPE_PROFILES_A = 9;
/*      */   static final int TYPE_PROFILES_B = 10;
/*      */   static final int TYPE_CMD_PARAMS = 11;
/*      */   static final int READ_DATA_OFFSET = 0;
/*      */   static final int READ_MEM_DATA_OFFSET = 2;
/*      */   static final int WRITE_DATA_OFFSET = 4;
/*      */   static final int ONE_BYTE_ADDRESS = 1;
/*      */   static final int TWO_BYTE_ADDRESS = 2;
/*      */   static final int THREE_BYTE_ADDRESS = 3;
/*   72 */   static final String[] PATTERN_TEXT_MAP = { "STD", "A", "B", "UNKNOWN" };
/*      */   static final int MAX_READ_BYTES = 250;
/*      */   static final int CONCENTRATION_U100 = 100;
/*      */   static final int CONCENTRATION_U50 = 50;
/*      */   static final int CONCENTRATION_U40 = 40;
/*      */   static final double CLICKS_PER_U100 = 10.0D;
/*      */   static final double BASE_CONCENTRATION_FACTOR = 1000.0D;
/*      */   static final int PATTERN_STD = 0;
/*      */   static final int PATTERN_A = 1;
/*      */   static final int PATTERN_B = 2;
/*      */   static final int MAX_BOLUS = 25;
/*      */   static final int MAX_BASAL = 35;
/*      */   static final int MAX_PROFILE_SEGMENTS = 48;
/*      */   static final int BEEP_VOL_LOW = 1;
/*      */   static final int BEEP_VOL_MED = 2;
/*      */   static final int BEEP_VOL_HIGH = 3;
/*      */   static final int FIELD_UNUSED = -1;
/*  143 */   static byte m_sequenceNumber = 0;
/*      */ 
/*  148 */   static byte m_accessCode = 8;
/*      */   private static final byte COMMAND_LENGTH2 = 2;
/*      */   static final int PORT_OPEN_DELAY_MS = 5000;
/*      */   static final int PORT_OPEN_RETRY_COUNT = 0;
/*      */   private static final int SUSPEND_DELAY_MS = 4000;
/*      */   private static final int RESUME_DELAY_MS = 1000;
/*  175 */   static int m_baudRate = -1;
/*  176 */   static int m_ioDelayMS = 0;
/*      */   static final int REC_SIZE_MIN = 64;
/*      */   static final int RECEIVE_TIMEOUT_MS = 500;
/*      */   Command m_cmdSetAccessCode;
/*      */   Command m_cmdSetSuspend;
/*      */   Command m_cmdCancelSuspend;
/*      */   Command m_cmdReadPumpId;
/*      */   Command m_cmdReadBolusData;
/*      */   Command m_cmdReadTotalsData;
/*      */   Command m_cmdReadEventData;
/*      */   Command m_cmdReadAlarmData;
/*      */   Command m_cmdReadPrimesData;
/*      */   Command m_cmdReadCurrBasalDataSTD;
/*      */   Command m_cmdReadCurrBasalDataA;
/*      */   Command m_cmdReadCurrBasalDataB;
/*      */   Command m_cmdReadBasalProfilesData;
/*      */   Command m_cmdReadRealTimeClock;
/*      */   Command m_cmdReadCurrentSettings1;
/*      */   Command m_cmdReadCurrentSettings2;
/*      */   Command m_cmdReadCurrentSettings3;
/*      */   Command m_cmdReadCurrentSettings4;
/*      */   Command m_cmdReadCurrentSettings5;
/*      */   Command m_cmdReadTodaysTotals;
/*      */   Command m_cmdReadFirmwareVersion;
/*      */   Command m_cmdReadDataPointers;
/*      */   Command m_cmdReadTempBasal;
/*      */   Command m_cmdReadBatteryStatus;
/*      */   Command m_cmdReadRemainingInsulin;
/*      */   Command m_cmdReadErrorStatus;
/*      */   Command m_cmdReadRemoteIDs;
/*      */   Command m_cmdReadRemoteID1;
/*      */   Command m_cmdReadRemoteID2;
/*      */   Command m_cmdReadRemoteID3;
/*      */   Command m_cmdReadState;
/*      */   Command m_cmdReadHistoryData;
/*      */   Command m_cmdPowerControl;
/*      */   Command m_cmdReadBGAlarmClocks;
/*      */   Command m_cmdReadBGAlarmEnable;
/*      */   Command m_cmdReadBGReminderEnable;
/*      */   Command m_cmdReadBGTargets;
/*      */   Command m_cmdReadBGUnits;
/*      */   Command m_cmdReadBolusWizardSetupStatus;
/*      */   Command m_cmdReadCarbRatios;
/*      */   Command m_cmdReadCarbUnits;
/*      */   Command m_cmdReadParadigmLinkIds;
/*      */   Command m_cmdReadInsulinSensitivities;
/*      */   Command m_cmdReadReservoirWarning;
/*      */   Command m_cmdReadPumpModelNumber;
/*      */   Command m_cmdReadLanguage;
/*      */   Command m_cmdDetectBolus;
/*      */   Command m_cmdReadCurrentHistoryPageNumber;
/*      */   Command m_cmdReadSavedSettingsDate;
/*      */   Command m_cmdReadContrast;
/*      */   Command m_cmdReadBolusReminderEnable;
/*      */   Command m_cmdReadBolusReminders;
/*      */   Command m_cmdReadFactoryParameters;
/*      */   Command m_cmdEnableDetailTrace;
/*      */   Command m_cmdDisableDetailTrace;
/*      */   Command m_cmdReadPumpTrace;
/*      */   Command m_cmdReadDetailTrace;
/*      */   Command m_cmdReadNewAlarmTrace;
/*      */   Command m_cmdReadOldAlarmTrace;
/*      */   Command m_cmdWriteGlucoseHistoryTimestamp;
/*      */   Command m_cmdReadCurrentGlucoseHistoryPageNumber;
/*      */   Command m_cmdReadGlucoseHistoryData;
/*      */   Command m_cmdReadIsigHistoryData;
/*      */   Command m_cmdReadCalibrationFactor;
/*      */   Command m_cmdReadSensorSettings;
/*      */   Command m_cmdFilterGlucoseHistoryData;
/*      */   Command m_cmdFilterIsigHistoryData;
/*      */   Command m_cmdReadSensorPredictiveAlerts;
/*      */   Command m_cmdReadSensorRateOfChangeAlerts;
/*      */   Command m_cmdReadSensorDemoAndGraphTimeout;
/*      */   Command m_cmdReadSensorAlarmSilence;
/*      */   Command m_cmdReadVcntrHistoryData;
/*      */   Command m_cmdReadOtherDevicesIds;
/*      */   int m_settingBeepVolume;
/*      */   int m_settingAutoOffDurationHrs;
/*      */   double m_settingTempBasalRate;
/*      */   int m_settingTempBasalDurationMin;
/*      */   int m_settingInsulinConcen;
/*      */   double m_settingMaxBasalRate;
/*      */   double m_settingMaxBolus;
/*      */   boolean m_settingVarBolusEnable;
/*      */   int m_settingTimeFormat;
/*      */   boolean m_settingAudioBolusEnable;
/*      */   double m_settingAudioBolusSize;
/*      */   double m_settingTodaysTotal;
/*      */   double m_settingYesterdaysTotal;
/*      */   int m_settingCurrentBasalPattern;
/*      */   int m_settingAlarmMode;
/*      */   boolean m_settingBasalPatternEnable;
/*      */   boolean m_settingRFEnable;
/*      */   boolean m_settingBlockEnable;
/*      */   int m_settingBatteryStatus;
/*      */   int m_settingRemainingInsulin;
/*      */   int m_settingErrorStatus;
/*      */   int m_settingPumpState;
/*      */   String m_settingRemoteID1;
/*      */   String m_settingRemoteID2;
/*      */   String m_settingRemoteID3;
/*      */   int m_tempBasalType;
/*      */   int m_tempBasalPercent;
/*      */   boolean m_paradigmLinkEnable;
/*      */   int m_insulinActionType;
/*      */   int m_lowReservoirWarnType;
/*      */   int m_lowReservoirWarnPoint;
/*      */   int m_keypadLockStatus;
/*      */   int m_settingBolusScrollStepSize;
/*      */   boolean m_settingCaptureEventEnable;
/*      */   boolean m_settingOtherDeviceEnable;
/*      */   boolean m_otherDeviceMarriedState;
/*      */   int m_alarmSnoozeTime;
/*      */   boolean m_calibrationReminderEnable;
/*      */   int m_calibrationReminderTime;
/*      */   boolean m_highGlucoseLimitEnable;
/*      */   double m_highGlucoseLimitValue;
/*      */   int m_highGlucoseSnoozeTime;
/*      */   boolean m_lowGlucoseLimitEnable;
/*      */   double m_lowGlucoseLimitValue;
/*      */   int m_lowGlucoseSnoozeTime;
/*      */   int m_missedDataTime;
/*      */   boolean m_sensorEnable;
/*      */   int m_sensorTransmitterId;
/*      */   int m_sensorBgUnits;
/*      */   double m_calibrationFactor;
/*      */   int m_strokesPerBasalUnit;
/*      */   int m_strokesPerBolusUnit;
/*      */   int m_sequenceNumberInc;
/*      */   int m_totalBytesToRead;
/*      */   int m_bytesReadThusFar;
/*      */   boolean m_serialPortInitialized;
/*      */   boolean m_comStationInitialized;
/*      */   boolean m_pumpInitialized;
/*      */   int m_currentHistoryPageNumber;
/*      */   int m_currentGlucoseHistoryPageNumber;
/*      */   private Vector m_commandCollection;
/*      */ 
/*      */   public void readData(DeviceListener paramDeviceListener, int paramInt, String paramString, boolean paramBoolean)
/*      */     throws BadDeviceCommException, BadDeviceValueException, IOException, ConnectToPumpException
/*      */   {
/*  395 */     readData(paramDeviceListener, paramInt, paramString, true, paramBoolean);
/*      */   }
/*      */ 
/*      */   public void readData(DeviceListener paramDeviceListener, int paramInt, String paramString)
/*      */     throws BadDeviceCommException, BadDeviceValueException, IOException, ConnectToPumpException
/*      */   {
/*  420 */     readData(paramDeviceListener, paramInt, paramString, true, false);
/*      */   }
/*      */ 
/*      */   void readData(DeviceListener paramDeviceListener, int paramInt, String paramString, boolean paramBoolean1, boolean paramBoolean2)
/*      */     throws BadDeviceCommException, BadDeviceValueException, IOException, ConnectToPumpException
/*      */   {
/*  447 */     logInfo(this, "readData: starting reader...");
/*  448 */     this.m_serialNumber = paramString;
/*  449 */     setHaltRequested(false);
/*      */ 
/*  452 */     this.m_commandCollection = createCommandList(paramBoolean2);
/*      */ 
/*  455 */     Reader localReader = new Reader(paramDeviceListener, paramInt, paramString, null);
/*  456 */     localReader.acquireDataFromDevice(paramBoolean1, true);
/*      */ 
/*  459 */     this.m_lastHistoryPageNumber = this.m_currentHistoryPageNumber;
/*  460 */     this.m_lastGlucoseHistoryPageNumber = this.m_currentGlucoseHistoryPageNumber;
/*      */ 
/*  463 */     if ((!isHaltRequested()) && (paramBoolean2))
/*  464 */       this.m_traceHistorySet = new TraceHistorySet(this.m_serialNumber, this.m_firmwareVersion, new Date(), this.m_cmdReadPumpTrace.m_rawData, this.m_cmdReadDetailTrace.m_rawData, this.m_cmdReadNewAlarmTrace.m_rawData, this.m_cmdReadOldAlarmTrace.m_rawData);
/*      */   }
/*      */ 
/*      */   public void readClock(DeviceListener paramDeviceListener, int paramInt, String paramString)
/*      */     throws BadDeviceCommException, BadDeviceValueException, IOException, ConnectToPumpException
/*      */   {
/*  492 */     this.m_serialNumber = paramString;
/*  493 */     this.m_commandCollection = new Vector(1);
/*  494 */     setHaltRequested(false);
/*      */ 
/*  497 */     this.m_commandCollection.addElement(this.m_cmdReadRealTimeClock);
/*      */ 
/*  500 */     Reader localReader = new Reader(paramDeviceListener, paramInt, paramString, null);
/*  501 */     localReader.acquireDataFromDevice();
/*      */   }
/*      */ 
/*      */   String readModelNumber(DeviceListener paramDeviceListener, int paramInt, String paramString)
/*      */     throws BadDeviceCommException, BadDeviceValueException, IOException, ConnectToPumpException
/*      */   {
/*  523 */     this.m_serialNumber = paramString;
/*  524 */     this.m_commandCollection = new Vector(1);
/*  525 */     setHaltRequested(false);
/*      */ 
/*  528 */     this.m_commandCollection.addElement(this.m_cmdReadPumpModelNumber);
/*      */ 
/*  531 */     Reader localReader = new Reader(paramDeviceListener, paramInt, paramString, null);
/*  532 */     localReader.acquireDataFromDevice(true, false);
/*  533 */     return this.m_modelNumber;
/*      */   }
/*      */ 
/*      */   public void readGlucoseDataRange(DeviceListener paramDeviceListener, int paramInt, String paramString, Date paramDate1, Date paramDate2)
/*      */     throws BadDeviceCommException, BadDeviceValueException, IOException, ConnectToPumpException
/*      */   {
/*  567 */     Contract.preNonNull(paramDate1);
/*  568 */     Contract.preNonNull(paramDate2);
/*  569 */     Contract.pre(!paramDate1.after(paramDate2));
/*      */ 
/*  571 */     this.m_serialNumber = paramString;
/*  572 */     this.m_commandCollection = new Vector(1);
/*  573 */     setHaltRequested(false);
/*      */ 
/*  576 */     this.m_cmdFilterGlucoseHistoryData.setBeginDate(paramDate1);
/*  577 */     this.m_cmdFilterGlucoseHistoryData.setEndDate(paramDate2);
/*      */ 
/*  580 */     this.m_cmdReadHistoryData.m_rawData = new int[64];
/*  581 */     this.m_cmdReadIsigHistoryData.m_rawData = new int[64];
/*      */ 
/*  584 */     this.m_commandCollection.addElement(this.m_cmdFilterGlucoseHistoryData);
/*      */ 
/*  587 */     Reader localReader = new Reader(paramDeviceListener, paramInt, paramString, null);
/*  588 */     localReader.acquireDataFromDevice(true, false);
/*      */ 
/*  591 */     this.m_cmdReadGlucoseHistoryData.m_rawData = this.m_cmdFilterGlucoseHistoryData.m_rawData;
/*      */   }
/*      */ 
/*      */   public void readIsigDataRange(DeviceListener paramDeviceListener, int paramInt, String paramString, Date paramDate1, Date paramDate2)
/*      */     throws BadDeviceCommException, BadDeviceValueException, IOException, ConnectToPumpException
/*      */   {
/*  625 */     Contract.preNonNull(paramDate1);
/*  626 */     Contract.preNonNull(paramDate2);
/*  627 */     Contract.pre(!paramDate1.after(paramDate2));
/*      */ 
/*  629 */     this.m_serialNumber = paramString;
/*  630 */     this.m_commandCollection = new Vector(1);
/*  631 */     setHaltRequested(false);
/*      */ 
/*  634 */     this.m_cmdFilterIsigHistoryData.setBeginDate(paramDate1);
/*  635 */     this.m_cmdFilterIsigHistoryData.setEndDate(paramDate2);
/*      */ 
/*  638 */     this.m_cmdReadHistoryData.m_rawData = new int[64];
/*  639 */     this.m_cmdReadGlucoseHistoryData.m_rawData = new int[64];
/*      */ 
/*  642 */     this.m_commandCollection.addElement(this.m_cmdFilterIsigHistoryData);
/*      */ 
/*  645 */     Reader localReader = new Reader(paramDeviceListener, paramInt, paramString, null);
/*  646 */     localReader.acquireDataFromDevice(true, false);
/*      */ 
/*  649 */     this.m_cmdReadIsigHistoryData.m_rawData = this.m_cmdFilterIsigHistoryData.m_rawData;
/*      */   }
/*      */ 
/*      */   abstract void decodeReply(Command paramCommand)
/*      */     throws BadDeviceValueException;
/*      */ 
/*      */   void calcTotalBytesToRead()
/*      */   {
/*  666 */     this.m_totalBytesToRead = 0;
/*      */ 
/*  669 */     for (int i = 0; i < this.m_commandCollection.size(); i++) {
/*  670 */       Command localCommand = (Command)this.m_commandCollection.elementAt(i);
/*  671 */       if (localCommand != null)
/*  672 */         this.m_totalBytesToRead += localCommand.m_rawData.length;
/*      */     }
/*      */   }
/*      */ 
/*      */   void executeSecondaryCommands(DeviceListener paramDeviceListener, int paramInt, String paramString)
/*      */     throws BadDeviceCommException, BadDeviceValueException, IOException, ConnectToPumpException
/*      */   {
/*      */   }
/*      */ 
/*      */   static final String getPatternString(Integer paramInteger)
/*      */   {
/*  707 */     if (paramInteger == null) {
/*  708 */       return "NOT USED";
/*      */     }
/*  710 */     return PATTERN_TEXT_MAP[paramInteger.intValue()];
/*      */   }
/*      */ 
/*      */   static void verifyConcentration(int paramInt)
/*      */     throws BadDeviceValueException
/*      */   {
/*  721 */     MedicalDevice.Util.verifyDeviceValue((paramInt == 40) || (paramInt == 50) || (paramInt == 100), "The Insulin Concentration value of " + paramInt + " is invalid; must be " + 40 + " or " + 50 + " or " + 100);
/*      */   }
/*      */ 
/*      */   static void verifyValue(int paramInt1, int paramInt2, int paramInt3, String paramString)
/*      */   {
/*  746 */     Contract.pre((paramInt1 >= paramInt2) && (paramInt1 <= paramInt3));
/*      */   }
/*      */ 
/*      */   long toBasalStrokes(double paramDouble)
/*      */   {
/*  757 */     return ()(paramDouble * this.m_strokesPerBasalUnit);
/*      */   }
/*      */ 
/*      */   long toBolusStrokes(double paramDouble)
/*      */   {
/*  767 */     return ()(paramDouble * this.m_strokesPerBolusUnit);
/*      */   }
/*      */ 
/*      */   double toBasalInsulin(int paramInt)
/*      */   {
/*  777 */     return paramInt / this.m_strokesPerBasalUnit;
/*      */   }
/*      */ 
/*      */   double toBolusInsulin(int paramInt)
/*      */   {
/*  787 */     return paramInt / this.m_strokesPerBolusUnit;
/*      */   }
/*      */ 
/*      */   void initSerialPort(int paramInt)
/*      */     throws IOException
/*      */   {
/*  798 */     beginSerialPort(paramInt);
/*      */ 
/*  800 */     int i = 0;
/*  801 */     int j = 0;
/*      */     do
/*      */     {
/*      */       try
/*      */       {
/*  806 */         setRS232Port(new SerialPort(paramInt, m_baudRate));
/*  807 */         getRS232Port().setReadTimeOut(500);
/*  808 */         j = 1;
/*      */       }
/*      */       catch (IOException localIOException) {
/*  811 */         i++;
/*      */ 
/*  813 */         if (i >= 0) {
/*  814 */           throw localIOException;
/*      */         }
/*      */ 
/*  817 */         logInfo(this, "initSerialPort: waiting for port to become available...e=" + localIOException);
/*      */ 
/*  819 */         MedicalDevice.Util.sleepMS(5000);
/*      */       }
/*      */     }
/*  821 */     while (j == 0);
/*      */ 
/*  823 */     getRS232Port().readUntilEmpty();
/*  824 */     getRS232Port().setIODelay(m_ioDelayMS);
/*      */   }
/*      */ 
/*      */   abstract void initComStation()
/*      */     throws IOException, SerialIOHaltedException, BadDeviceCommException;
/*      */ 
/*      */   abstract void initDevice()
/*      */     throws IOException, BadDeviceCommException, ConnectToPumpException;
/*      */ 
/*      */   abstract void shutDownPump()
/*      */     throws BadDeviceCommException, IOException;
/*      */ 
/*      */   abstract void shutDownComStation()
/*      */     throws IOException;
/*      */ 
/*      */   abstract void shutDownSerialPort()
/*      */     throws IOException;
/*      */ 
/*      */   void findDevice(DeviceListener paramDeviceListener)
/*      */     throws BadDeviceCommException, IOException
/*      */   {
/*  880 */     initComStation();
/*      */   }
/*      */ 
/*      */   int getDeviceType()
/*      */   {
/*  889 */     return 1;
/*      */   }
/*      */ 
/*      */   private Vector createCommandList(boolean paramBoolean)
/*      */   {
/*  899 */     Vector localVector = new Vector();
/*      */ 
/*  903 */     localVector.addElement(this.m_cmdCancelSuspend);
/*  904 */     localVector.addElement(this.m_cmdReadErrorStatus);
/*  905 */     localVector.addElement(this.m_cmdReadRealTimeClock);
/*  906 */     localVector.addElement(this.m_cmdReadRemoteID1);
/*  907 */     localVector.addElement(this.m_cmdReadRemoteID2);
/*  908 */     localVector.addElement(this.m_cmdReadRemoteID3);
/*      */ 
/*  910 */     localVector.addElement(this.m_cmdSetSuspend);
/*  911 */     localVector.addElement(this.m_cmdReadPumpId);
/*  912 */     localVector.addElement(this.m_cmdReadFirmwareVersion);
/*      */ 
/*  915 */     localVector.addElement(this.m_cmdReadCurrentHistoryPageNumber);
/*  916 */     localVector.addElement(this.m_cmdReadCurrentGlucoseHistoryPageNumber);
/*      */ 
/*  918 */     localVector.addElement(this.m_cmdReadCurrentSettings1);
/*  919 */     localVector.addElement(this.m_cmdReadCurrentSettings2);
/*  920 */     localVector.addElement(this.m_cmdReadCurrentSettings3);
/*  921 */     localVector.addElement(this.m_cmdReadCurrentSettings4);
/*  922 */     localVector.addElement(this.m_cmdReadCurrentSettings5);
/*  923 */     localVector.addElement(this.m_cmdReadTodaysTotals);
/*  924 */     localVector.addElement(this.m_cmdReadDataPointers);
/*  925 */     localVector.addElement(this.m_cmdReadBolusData);
/*  926 */     localVector.addElement(this.m_cmdReadTotalsData);
/*  927 */     localVector.addElement(this.m_cmdReadAlarmData);
/*  928 */     localVector.addElement(this.m_cmdReadPrimesData);
/*  929 */     localVector.addElement(this.m_cmdReadCurrBasalDataSTD);
/*  930 */     localVector.addElement(this.m_cmdReadCurrBasalDataA);
/*  931 */     localVector.addElement(this.m_cmdReadCurrBasalDataB);
/*  932 */     localVector.addElement(this.m_cmdReadBasalProfilesData);
/*  933 */     localVector.addElement(this.m_cmdReadEventData);
/*  934 */     localVector.addElement(this.m_cmdReadTempBasal);
/*  935 */     localVector.addElement(this.m_cmdReadBatteryStatus);
/*  936 */     localVector.addElement(this.m_cmdReadRemainingInsulin);
/*  937 */     localVector.addElement(this.m_cmdReadRemoteIDs);
/*  938 */     localVector.addElement(this.m_cmdReadState);
/*      */ 
/*  940 */     localVector.addElement(this.m_cmdReadLanguage);
/*  941 */     localVector.addElement(this.m_cmdReadPumpModelNumber);
/*  942 */     localVector.addElement(this.m_cmdReadBGAlarmClocks);
/*  943 */     localVector.addElement(this.m_cmdReadBGAlarmEnable);
/*  944 */     localVector.addElement(this.m_cmdReadBGReminderEnable);
/*  945 */     localVector.addElement(this.m_cmdReadBGTargets);
/*  946 */     localVector.addElement(this.m_cmdReadBGUnits);
/*  947 */     localVector.addElement(this.m_cmdReadBolusWizardSetupStatus);
/*  948 */     localVector.addElement(this.m_cmdReadCarbRatios);
/*  949 */     localVector.addElement(this.m_cmdReadCarbUnits);
/*  950 */     localVector.addElement(this.m_cmdReadParadigmLinkIds);
/*  951 */     localVector.addElement(this.m_cmdReadInsulinSensitivities);
/*  952 */     localVector.addElement(this.m_cmdReadReservoirWarning);
/*      */ 
/*  954 */     localVector.addElement(this.m_cmdReadSavedSettingsDate);
/*  955 */     localVector.addElement(this.m_cmdReadContrast);
/*  956 */     localVector.addElement(this.m_cmdReadBolusReminderEnable);
/*  957 */     localVector.addElement(this.m_cmdReadBolusReminders);
/*  958 */     localVector.addElement(this.m_cmdReadFactoryParameters);
/*      */ 
/*  960 */     localVector.addElement(this.m_cmdReadSensorSettings);
/*  961 */     localVector.addElement(this.m_cmdReadCalibrationFactor);
/*  962 */     localVector.addElement(this.m_cmdReadSensorPredictiveAlerts);
/*  963 */     localVector.addElement(this.m_cmdReadSensorRateOfChangeAlerts);
/*  964 */     localVector.addElement(this.m_cmdReadSensorDemoAndGraphTimeout);
/*  965 */     localVector.addElement(this.m_cmdReadSensorAlarmSilence);
/*  966 */     localVector.addElement(this.m_cmdReadOtherDevicesIds);
/*      */ 
/*  968 */     localVector.addElement(this.m_cmdReadHistoryData);
/*      */ 
/*  971 */     localVector.addElement(this.m_cmdWriteGlucoseHistoryTimestamp);
/*      */ 
/*  975 */     localVector.addElement(this.m_cmdReadCurrentGlucoseHistoryPageNumber);
/*      */ 
/*  977 */     localVector.addElement(this.m_cmdReadGlucoseHistoryData);
/*  978 */     localVector.addElement(this.m_cmdReadIsigHistoryData);
/*  979 */     localVector.addElement(this.m_cmdReadVcntrHistoryData);
/*      */ 
/*  981 */     if (paramBoolean)
/*      */     {
/*  983 */       localVector.addElement(this.m_cmdReadPumpTrace);
/*      */ 
/*  986 */       localVector.addElement(this.m_cmdDisableDetailTrace);
/*  987 */       localVector.addElement(this.m_cmdReadDetailTrace);
/*  988 */       localVector.addElement(this.m_cmdEnableDetailTrace);
/*      */ 
/*  990 */       localVector.addElement(this.m_cmdReadNewAlarmTrace);
/*  991 */       localVector.addElement(this.m_cmdReadOldAlarmTrace);
/*      */     }
/*      */ 
/*  994 */     return localVector;
/*      */   }
/*      */ 
/*      */   private final class Reader
/*      */   {
/*      */     private int m_serialPortNum;
/*      */     private String m_deviceSerialNum;
/*      */     private DeviceListener m_listener;
/*      */     private final MMPump this$0;
/*      */ 
/*      */     private Reader(DeviceListener paramInt, int paramString, String arg4)
/*      */     {
/* 1283 */       this.this$0 = this$1;
/* 1284 */       this.m_listener = paramInt;
/* 1285 */       this$1.addDeviceListener(this.m_listener);
/* 1286 */       this.m_serialPortNum = paramString;
/*      */       Object localObject;
/* 1287 */       this.m_deviceSerialNum = localObject;
/*      */     }
/*      */ 
/*      */     private void acquireDataFromDevice(boolean paramBoolean1, boolean paramBoolean2)
/*      */       throws BadDeviceCommException, BadDeviceValueException, IOException, ConnectToPumpException
/*      */     {
/* 1307 */       int i = 0;
/*      */ 
/* 1309 */       Vector localVector = new Vector(this.this$0.m_commandCollection.size());
/* 1310 */       int j = 0;
/* 1311 */       int k = 0;
/*      */ 
/* 1313 */       if (paramBoolean1) {
/* 1314 */         this.this$0.m_bytesReadThusFar = 0;
/* 1315 */         this.this$0.m_serialPortInitialized = false;
/* 1316 */         this.this$0.m_comStationInitialized = false;
/* 1317 */         this.this$0.m_pumpInitialized = false;
/*      */       }
/*      */       else {
/* 1320 */         this.this$0.m_serialPortInitialized = true;
/* 1321 */         this.this$0.m_comStationInitialized = true;
/* 1322 */         this.this$0.m_pumpInitialized = true;
/*      */       }
/*      */ 
/* 1325 */       this.this$0.calcTotalBytesToRead();
/* 1326 */       this.this$0.notifyDeviceUpdateProgress(0);
/*      */       try
/*      */       {
/* 1334 */         if (paramBoolean1)
/*      */         {
/* 1336 */           this.this$0.setState(2);
/* 1337 */           initCommunications(this.m_serialPortNum);
/*      */         }
/*      */ 
/* 1340 */         if (paramBoolean2) {
/* 1341 */           this.this$0.setPhase(5);
/*      */         }
/*      */ 
/* 1344 */         if (!this.this$0.isHaltRequested())
/*      */         {
/* 1347 */           i = 0;
/* 1348 */           while ((i < this.this$0.m_commandCollection.size()) && (!this.this$0.isHaltRequested()))
/*      */           {
/* 1350 */             MMPump.Command localCommand = (MMPump.Command)this.this$0.m_commandCollection.elementAt(i);
/* 1351 */             if (localCommand != null)
/*      */             {
/* 1353 */               localCommand.execute();
/*      */ 
/* 1356 */               if (localCommand.equals(this.this$0.m_cmdSetSuspend))
/*      */               {
/* 1358 */                 MedicalDevice.Util.sleepMS(4000);
/*      */               }
/*      */ 
/* 1361 */               if (localCommand.equals(this.this$0.m_cmdCancelSuspend))
/*      */               {
/* 1363 */                 MedicalDevice.Util.sleepMS(1000);
/*      */               }
/*      */ 
/* 1366 */               if (!this.this$0.isHaltRequested())
/*      */               {
/* 1368 */                 int[] arrayOfInt = localCommand.m_rawData;
/* 1369 */                 localVector.addElement(arrayOfInt);
/* 1370 */                 this.this$0.decodeReply(localCommand);
/*      */               }
/*      */             }
/* 1349 */             i++;
/*      */           }
/*      */ 
/* 1376 */           this.this$0.executeSecondaryCommands(this.m_listener, this.m_serialPortNum, this.m_deviceSerialNum);
/*      */ 
/* 1379 */           if (this.this$0.isHaltRequested())
/* 1380 */             j = 2;
/*      */           else
/* 1382 */             j = 1;
/*      */         }
/*      */       }
/*      */       catch (IOException localIOException) {
/* 1386 */         k = 1;
/* 1387 */         throw localIOException;
/*      */       } catch (BadDeviceCommException localBadDeviceCommException) {
/* 1389 */         k = 1;
/* 1390 */         throw localBadDeviceCommException;
/*      */       } catch (BadDeviceValueException localBadDeviceValueException) {
/* 1392 */         k = 1;
/* 1393 */         throw localBadDeviceValueException;
/*      */       } catch (ConnectToPumpException localConnectToPumpException) {
/* 1395 */         k = 1;
/* 1396 */         throw localConnectToPumpException;
/*      */       }
/*      */       finally
/*      */       {
/*      */       }
/*      */ 
/* 1435 */       ret;
/*      */     }
/*      */ 
/*      */     private void acquireDataFromDevice()
/*      */       throws BadDeviceCommException, BadDeviceValueException, IOException, ConnectToPumpException
/*      */     {
/* 1456 */       acquireDataFromDevice(true, true);
/*      */     }
/*      */ 
/*      */     private void initCommunications(int paramInt)
/*      */       throws IOException, BadDeviceCommException, ConnectToPumpException
/*      */     {
/* 1471 */       this.this$0.initSerialPort(paramInt);
/* 1472 */       this.this$0.m_serialPortInitialized = true;
/*      */       try
/*      */       {
/* 1476 */         this.this$0.initComStation();
/*      */       }
/*      */       catch (IOException localIOException) {
/* 1479 */         this.this$0.initComStation();
/*      */       }
/* 1481 */       this.this$0.m_comStationInitialized = true;
/*      */ 
/* 1484 */       this.this$0.initDevice();
/* 1485 */       this.this$0.m_pumpInitialized = true;
/*      */     }
/*      */ 
/*      */     private void endCommunications()
/*      */       throws BadDeviceCommException, IOException
/*      */     {
/* 1495 */       MedicalDevice.logInfo(this, "endCommunications: shutting down...");
/*      */       try {
/* 1497 */         if (this.this$0.m_pumpInitialized) {
/* 1498 */           MedicalDevice.logInfo(this, "endCommunications: shutting down pump communications.");
/* 1499 */           this.this$0.shutDownPump();
/*      */         }
/*      */ 
/*      */       }
/*      */       finally
/*      */       {
/*      */       }
/*      */ 
/* 1512 */       ret;
/*      */     }
/*      */ 
/*      */     Reader(DeviceListener paramInt, int paramString, String param1, MMPump.1 arg5)
/*      */     {
/* 1261 */       this(paramInt, paramString, param1);
/*      */     }
/*      */   }
/*      */ 
/*      */   abstract class Command extends DeviceCommand
/*      */     implements Cloneable
/*      */   {
/*      */     static final byte SOH = 1;
/*      */     static final byte BITS_IN_ACCESS_CODE = 6;
/*      */     static final byte REPLY_HELLO = 90;
/*      */     static final byte REPLY_TIMEOUT = -1;
/*      */     static final int SIO_DELAY_MS = 50;
/*      */     int[] m_commandParameters;
/*      */     int m_commandParameterCount;
/*      */     int m_dataCount;
/*      */     int m_commandCode;
/*      */     int[] m_rawData;
/*      */     int m_dataOffset;
/*      */     int m_bytesPerRecord;
/*      */     int m_maxRecords;
/*      */     int m_address;
/*      */     int m_dataPointer;
/*      */     int m_cmdLength;
/*      */     int m_addressLength;
/*      */     int m_commandType;
/*      */     int[] m_packet;
/* 1050 */     int m_numBytesRead = 0;
/*      */     int m_maxRetries;
/*      */     boolean m_useMultiXmitMode;
/*      */     Date m_beginDate;
/*      */     Date m_endDate;
/* 1075 */     Object m_extraObject = null;
/*      */ 
/* 1080 */     int m_effectTime = 0;
/*      */ 
/*      */     Command(int paramString, String paramInt1, int paramInt2, int paramInt3, int arg6)
/*      */     {
/* 1095 */       this(paramString, paramInt1, paramInt2, paramInt3, 0, 0, i);
/* 1096 */       this.m_dataOffset = 0;
/* 1097 */       this.m_cmdLength = 2;
/* 1098 */       setUseMultiXmitMode(false);
/*      */     }
/*      */ 
/*      */     Command(int paramString, String paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int arg8)
/*      */     {
/* 1115 */       super();
/* 1116 */       this.m_commandCode = paramString;
/* 1117 */       this.m_bytesPerRecord = paramInt2;
/* 1118 */       this.m_maxRecords = paramInt3;
/* 1119 */       allocateRawData();
/* 1120 */       this.m_address = paramInt4;
/* 1121 */       this.m_addressLength = paramInt5;
/* 1122 */       this.m_dataOffset = 2;
/*      */ 
/* 1124 */       if (paramInt5 == 1)
/* 1125 */         this.m_cmdLength = (2 + paramInt5);
/*      */       else {
/* 1127 */         this.m_cmdLength = (2 + paramInt5 + 1);
/*      */       }
/*      */ 
/* 1130 */       this.m_packet = new int[0];
/*      */       int i;
/* 1131 */       this.m_commandType = i;
/* 1132 */       this.m_commandParameterCount = 0;
/* 1133 */       this.m_commandParameters = new int[64];
/* 1134 */       setUseMultiXmitMode(false);
/* 1135 */       this.m_maxRetries = 2;
/*      */     }
/*      */ 
/*      */     void allocateRawData()
/*      */     {
/* 1144 */       this.m_rawData = new int[this.m_bytesPerRecord * this.m_maxRecords];
/*      */     }
/*      */ 
/*      */     public Object clone()
/*      */     {
/* 1153 */       Command localCommand = null;
/*      */       try {
/* 1155 */         localCommand = (Command)super.clone();
/* 1156 */         localCommand.m_packet = MedicalDevice.Util.clone(this.m_packet);
/* 1157 */         localCommand.m_commandParameters = MedicalDevice.Util.clone(this.m_commandParameters);
/* 1158 */         localCommand.m_rawData = MedicalDevice.Util.clone(this.m_rawData);
/* 1159 */         if (this.m_beginDate != null) {
/* 1160 */           localCommand.m_beginDate = ((Date)this.m_beginDate.clone());
/*      */         }
/* 1162 */         if (this.m_endDate != null)
/* 1163 */           localCommand.m_endDate = ((Date)this.m_endDate.clone());
/*      */       }
/*      */       catch (CloneNotSupportedException localCloneNotSupportedException) {
/* 1166 */         Contract.unreachable();
/*      */       }
/* 1168 */       return localCommand;
/*      */     }
/*      */ 
/*      */     public String toString()
/*      */     {
/* 1177 */       StringBuffer localStringBuffer = new StringBuffer();
/* 1178 */       localStringBuffer.append(MedicalDevice.Util.getHex(this.m_commandCode));
/* 1179 */       localStringBuffer.append(" (" + this.m_description);
/*      */ 
/* 1182 */       for (int i = 0; i < this.m_commandParameterCount; i++) {
/* 1183 */         localStringBuffer.append(" p" + i + "=" + this.m_commandParameters[i]);
/*      */       }
/*      */ 
/* 1186 */       localStringBuffer.append(") ");
/* 1187 */       return localStringBuffer.toString();
/*      */     }
/*      */ 
/*      */     abstract void execute()
/*      */       throws BadDeviceCommException, SerialIOHaltedException;
/*      */ 
/*      */     void setBeginDate(Date paramDate)
/*      */     {
/* 1206 */       this.m_beginDate = paramDate;
/*      */     }
/*      */ 
/*      */     void setEndDate(Date paramDate)
/*      */     {
/* 1214 */       this.m_endDate = paramDate;
/*      */     }
/*      */ 
/*      */     void setUseMultiXmitMode(boolean paramBoolean)
/*      */     {
/* 1224 */       this.m_useMultiXmitMode = paramBoolean;
/*      */     }
/*      */ 
/*      */     boolean isUseMultiXmitMode()
/*      */     {
/* 1234 */       return this.m_useMultiXmitMode;
/*      */     }
/*      */ 
/*      */     int getEffectTime()
/*      */     {
/* 1243 */       return this.m_effectTime;
/*      */     }
/*      */ 
/*      */     void setEffectTime(int paramInt)
/*      */     {
/* 1252 */       this.m_effectTime = paramInt;
/*      */     }
/*      */   }
/*      */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.deviceportreader.MMPump
 * JD-Core Version:    0.6.0
 */