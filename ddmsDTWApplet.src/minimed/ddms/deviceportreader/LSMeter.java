/*      */ package minimed.ddms.deviceportreader;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.text.ParsePosition;
/*      */ import java.text.SimpleDateFormat;
/*      */ import java.util.Date;
/*      */ import java.util.NoSuchElementException;
/*      */ import java.util.StringTokenizer;
/*      */ import java.util.Vector;
/*      */ import minimed.util.Contract;
/*      */ 
/*      */ abstract class LSMeter extends MedicalDevice
/*      */   implements ReplyDecoder
/*      */ {
/*      */   static final int SNAP_HEADER_LENGTH = 50;
/*      */   static final int SNAP_DATALOG_LENGTH = 15310;
/*      */   static final char PAD_CHAR = '$';
/*      */   static final String CURRENT_SETTINGS_REPLY = "S?";
/*      */   static final String SERIAL_NUMBER_REPLY = "@";
/*      */   static final String FIRMWARE_REPLY = "?";
/*      */   static final String TIMESTAMP_REPLY = "F";
/*      */   static final char SETTING_STRIPCALCODE = 'S';
/*      */   static final char SETTING_LANGUAGE = 'L';
/*      */   static final char SETTING_TRANSLATIONSTATUS = 'X';
/*      */   static final char SETTING_COMMMODE = 'C';
/*      */   static final char SETTING_BAUDRATE = 'R';
/*      */   static final char SETTING_BEEPER_ENABLE = 'B';
/*      */   static final char SETTING_UNITS = 'U';
/*      */   static final char SETTING_PUNCTUATION = 'P';
/*      */   static final char SETTING_DATEFORMAT = 'D';
/*      */   static final char SETTING_TIMEFORMAT = 'T';
/*      */   static final char SETTING_EVENTAVE_ENABLE = 'E';
/*      */   static final char SETTING_INSULINPROMPT_ENABLE = 'I';
/*      */   static final char SETTING_DATETIMEDISPLAY_ENABLE = 'W';
/*      */   static final char SETTING_DST_ENABLE = 'Y';
/*      */   static final char SETTING_MEMORYDISPLAY_ENABLE = 'M';
/*      */   static final char SETTING_AVERAGEDISPLAY_ENABLE = 'A';
/*      */   static final String CMD_GET_FIRMWARE_VERSION = "DM?";
/*      */   static final String CMD_GET_SERIAL_NUMBER = "DM@";
/*      */   static final String CMD_GET_DATALOG = "DMP";
/*      */   static final String CMD_GET_SETTINGS = "DMS?";
/*      */   static final String CMD_READ_CLOCK = "DMF";
/*      */   static final int LEN_GET_FIRMWARE_VERSION = 50;
/*      */   static final int LEN_GET_SERIAL_NUMBER = 50;
/*      */   static final int LEN_GET_DATALOG = 13900;
/*      */   static final int LEN_GET_SETTINGS = 50;
/*      */   static final int LEN_READ_CLOCK = 50;
/*      */   private static final int IO_DELAY_MS = 50;
/*   83 */   private static final String[] IGNORE_REPLY = { "INSERT", "STRIP", "CODE" };
/*      */   private static final String METER_ACK_CMD = "\021\r";
/*      */   String m_realTimeClock;
/*      */   String m_currentSettings;
/*      */   String m_currentSettings2;
/*      */   String m_datalog;
/*      */   Character m_settingStripCalCode;
/*      */   Character m_settingLanguage;
/*      */   Boolean m_settingTranslationStatus;
/*      */   Integer m_settingCommMode;
/*      */   Integer m_settingBaudRate;
/*      */   Boolean m_settingBeeperEnable;
/*      */   Boolean m_settingUnitsIsMGDL;
/*      */   Boolean m_settingPunctuationIsDecimal;
/*      */   Boolean m_settingDateFormatIsMDY;
/*      */   Boolean m_settingTimeFormatIsAMPM;
/*      */   Boolean m_settingEventAveEnable;
/*      */   Boolean m_settingInsulinPromptEnable;
/*      */   Boolean m_settingDateTimeDisplayEnable;
/*      */   Boolean m_settingDSTEnable;
/*      */   Boolean m_settingMemoryDisplayEnable;
/*      */   Boolean m_settingAverageDisplayEnable;
/*      */   AbstractCommand m_cmdGetFirmwareVersion;
/*      */   AbstractCommand m_cmdGetSerialNumber;
/*      */   AbstractCommand m_cmdGetDatalog;
/*      */   AbstractCommand m_cmdGetSettings;
/*      */   AbstractCommand m_cmdGetSettings2;
/*      */   AbstractCommand m_cmdReadRealTimeClock;
/*      */   int m_totalBytesToRead;
/*      */   int m_bytesReadThusFar;
/*      */   int m_baudRate;
/*      */   int m_ioDelay;
/*      */   private Reader m_reader;
/*      */ 
/*      */   LSMeter()
/*      */   {
/*  136 */     this.m_firmwareVersion = new String(new byte[50]);
/*  137 */     this.m_serialNumber = new String(new byte[50]);
/*  138 */     this.m_realTimeClock = new String(new byte[50]);
/*  139 */     this.m_currentSettings = new String(new byte[50]);
/*  140 */     this.m_datalog = new String(new byte[15310]);
/*  141 */     this.m_snapshotCreator = new SnapshotCreator();
/*      */ 
/*  145 */     this.m_settingStripCalCode = null;
/*  146 */     this.m_settingLanguage = null;
/*  147 */     this.m_settingTranslationStatus = null;
/*  148 */     this.m_settingCommMode = null;
/*  149 */     this.m_settingBaudRate = null;
/*  150 */     this.m_settingBeeperEnable = null;
/*  151 */     this.m_settingUnitsIsMGDL = null;
/*  152 */     this.m_settingPunctuationIsDecimal = null;
/*  153 */     this.m_settingDateFormatIsMDY = null;
/*  154 */     this.m_settingTimeFormatIsAMPM = null;
/*  155 */     this.m_settingEventAveEnable = null;
/*  156 */     this.m_settingInsulinPromptEnable = null;
/*  157 */     this.m_settingDateTimeDisplayEnable = null;
/*  158 */     this.m_settingDSTEnable = null;
/*  159 */     this.m_settingMemoryDisplayEnable = null;
/*  160 */     this.m_settingAverageDisplayEnable = null;
/*      */ 
/*  163 */     this.m_cmdGetFirmwareVersion = new Command("DM?", "Read Firmware Version", 50);
/*      */ 
/*  166 */     this.m_cmdGetSerialNumber = new Command("DM@", "Read Serial Number", 50);
/*      */ 
/*  168 */     this.m_cmdGetDatalog = new Command("DMP", "Read Datalog", 13900);
/*  169 */     this.m_cmdGetSettings = new Command("DMS?", "Read Current Settings", 50);
/*      */ 
/*  172 */     this.m_cmdGetSettings2 = null;
/*  173 */     this.m_cmdReadRealTimeClock = new Command("DMF", "Read Real Time Clock", 50);
/*      */ 
/*  176 */     this.m_ioDelay = 50;
/*  177 */     this.m_baudRate = 7;
/*      */   }
/*      */ 
/*      */   public void readData(DeviceListener paramDeviceListener, int paramInt, String paramString)
/*      */     throws BadDeviceCommException, BadDeviceValueException, IOException
/*      */   {
/*  200 */     logInfo(this, "readData: starting reader...");
/*  201 */     setHaltRequested(false);
/*      */ 
/*  204 */     Vector localVector = createCommandList();
/*      */ 
/*  206 */     this.m_reader = new Reader(paramDeviceListener, paramInt, localVector, null);
/*  207 */     this.m_reader.acquireDataFromDevice();
/*      */   }
/*      */ 
/*      */   public void readClock(DeviceListener paramDeviceListener, int paramInt, String paramString)
/*      */     throws BadDeviceCommException, BadDeviceValueException, IOException
/*      */   {
/*  226 */     Vector localVector = new Vector();
/*      */ 
/*  229 */     localVector.addElement(this.m_cmdReadRealTimeClock);
/*      */ 
/*  232 */     Reader localReader = new Reader(paramDeviceListener, paramInt, localVector, null);
/*  233 */     localReader.acquireDataFromDevice();
/*      */   }
/*      */ 
/*      */   public String getSerialNumber()
/*      */   {
/*      */     String str;
/*      */     try
/*      */     {
/*  246 */       str = MedicalDevice.Util.remainderOf(this.m_serialNumber, "@ ");
/*      */     } catch (BadDeviceValueException localBadDeviceValueException) {
/*  248 */       return this.m_serialNumber;
/*      */     }
/*      */ 
/*  252 */     if (str != null) {
/*  253 */       StringTokenizer localStringTokenizer = new StringTokenizer(str, "\"");
/*  254 */       if (localStringTokenizer.hasMoreTokens())
/*      */       {
/*  256 */         return localStringTokenizer.nextToken();
/*      */       }
/*      */     }
/*  259 */     return this.m_serialNumber;
/*      */   }
/*      */ 
/*      */   public String getFirmwareVersion()
/*      */   {
/*      */     String str1;
/*      */     try
/*      */     {
/*  273 */       str1 = MedicalDevice.Util.remainderOf(this.m_firmwareVersion, "?");
/*      */     } catch (BadDeviceValueException localBadDeviceValueException) {
/*  275 */       return this.m_firmwareVersion;
/*      */     }
/*      */ 
/*  279 */     if (str1 != null) {
/*  280 */       StringTokenizer localStringTokenizer = new StringTokenizer(str1, " ");
/*  281 */       if (localStringTokenizer.hasMoreTokens()) {
/*  282 */         String str2 = localStringTokenizer.nextToken();
/*      */ 
/*  284 */         return str2.substring(1);
/*      */       }
/*      */     }
/*  287 */     return this.m_firmwareVersion;
/*      */   }
/*      */ 
/*      */   public void decodeReply(AbstractCommand paramAbstractCommand)
/*      */     throws BadDeviceValueException
/*      */   {
/*  301 */     String str = MedicalDevice.Util.makeString(paramAbstractCommand.getRawData());
/*      */ 
/*  303 */     if (paramAbstractCommand.equals(this.m_cmdGetFirmwareVersion))
/*      */     {
/*  305 */       this.m_firmwareVersion = str;
/*  306 */       logInfo(this, "decodeReply: firmware version is '" + getFirmwareVersion() + "'");
/*      */     }
/*  309 */     else if (paramAbstractCommand.equals(this.m_cmdGetSerialNumber))
/*      */     {
/*  311 */       this.m_serialNumber = str;
/*  312 */       logInfo(this, "decodeReply: serial number is '" + getSerialNumber() + "'");
/*      */ 
/*  315 */       initDeviceAfterSerialNumberKnown();
/*      */ 
/*  318 */       this.m_reader.calcTotalBytesToRead();
/*      */     }
/*  320 */     else if (paramAbstractCommand.equals(this.m_cmdGetSettings))
/*      */     {
/*  322 */       this.m_currentSettings = str;
/*  323 */       logInfo(this, "decodeReply: current settings is '" + this.m_currentSettings + "'");
/*      */ 
/*  325 */       decodeCurrentSettings();
/*      */     }
/*  327 */     else if ((this.m_cmdGetSettings2 != null) && (paramAbstractCommand.equals(this.m_cmdGetSettings2)))
/*      */     {
/*  330 */       this.m_currentSettings2 = str;
/*  331 */       logInfo(this, "decodeReply: current settings2 is '" + this.m_currentSettings2 + "'");
/*      */ 
/*  333 */       decodeCurrentSettings2();
/*      */     }
/*  335 */     else if (paramAbstractCommand.equals(this.m_cmdReadRealTimeClock))
/*      */     {
/*  337 */       this.m_realTimeClock = str;
/*  338 */       decodeCurrentTimeStamp();
/*  339 */       logInfo(this, "decodeReply: device clock reads '" + getClock() + "'");
/*      */     }
/*  341 */     else if (paramAbstractCommand.equals(this.m_cmdGetDatalog)) {
/*  342 */       this.m_datalog = str.trim();
/*  343 */       logInfo(this, "decodeReply: reply is datalog records: '" + this.m_datalog + "'");
/*      */     }
/*      */     else {
/*  346 */       throw new BadDeviceValueException("Command '" + paramAbstractCommand + " ' is unrecognized");
/*      */     }
/*      */   }
/*      */ 
/*      */   void incBytesReadThusFar(int paramInt)
/*      */   {
/*  358 */     this.m_bytesReadThusFar += paramInt;
/*      */   }
/*      */ 
/*      */   abstract void decodeCurrentSettings()
/*      */     throws BadDeviceValueException;
/*      */ 
/*      */   void decodeCurrentSettings2()
/*      */     throws BadDeviceValueException
/*      */   {
/*      */   }
/*      */ 
/*      */   Vector createCommandList()
/*      */   {
/*  388 */     Vector localVector = new Vector();
/*      */ 
/*  391 */     localVector.addElement(this.m_cmdGetSettings);
/*  392 */     if (this.m_cmdGetSettings2 != null) {
/*  393 */       localVector.addElement(this.m_cmdGetSettings2);
/*      */     }
/*  395 */     localVector.addElement(this.m_cmdReadRealTimeClock);
/*  396 */     localVector.addElement(this.m_cmdGetFirmwareVersion);
/*  397 */     localVector.addElement(this.m_cmdGetSerialNumber);
/*  398 */     localVector.addElement(this.m_cmdGetDatalog);
/*  399 */     return localVector;
/*      */   }
/*      */ 
/*      */   void notifyDeviceUpdateProgress()
/*      */   {
/*  406 */     notifyDeviceUpdateProgress(this.m_bytesReadThusFar, this.m_totalBytesToRead);
/*      */   }
/*      */ 
/*      */   int getDeviceType()
/*      */   {
/*  415 */     return 3;
/*      */   }
/*      */ 
/*      */   Vector getCommandCollection()
/*      */   {
/*  424 */     return this.m_reader.m_commandCollection;
/*      */   }
/*      */ 
/*      */   void initDevice()
/*      */     throws BadDeviceCommException, BadDeviceValueException
/*      */   {
/*      */   }
/*      */ 
/*      */   void initDeviceAfterSerialNumberKnown()
/*      */     throws BadDeviceValueException
/*      */   {
/*      */   }
/*      */ 
/*      */   void findDevice(DeviceListener paramDeviceListener)
/*      */     throws BadDeviceCommException, IOException
/*      */   {
/*      */     try
/*      */     {
/*  456 */       this.m_cmdReadRealTimeClock.execute();
/*      */     }
/*      */     catch (BadDeviceValueException localBadDeviceValueException) {
/*  459 */       throw new BadDeviceCommException("Got reply, but format is bad.");
/*      */     }
/*      */   }
/*      */ 
/*      */   void initSerialPort(int paramInt)
/*      */     throws IOException
/*      */   {
/*  471 */     beginSerialPort(paramInt);
/*  472 */     setRS232Port(new SerialPort(paramInt, this.m_baudRate));
/*  473 */     getRS232Port().readUntilEmpty();
/*  474 */     getRS232Port().setIODelay(this.m_ioDelay);
/*      */   }
/*      */ 
/*      */   void shutDownSerialPort()
/*      */     throws IOException
/*      */   {
/*  483 */     if (getRS232Port() != null)
/*      */     {
/*  485 */       getRS232Port().close();
/*  486 */       setRS232Port(null);
/*      */     }
/*      */   }
/*      */   private void decodeCurrentTimeStamp() throws BadDeviceValueException {
/*  506 */     String str3 = MedicalDevice.Util.remainderOf(this.m_realTimeClock, "F ").trim();
/*      */ 
/*  510 */     StringTokenizer localStringTokenizer = new StringTokenizer(str3, "\",");
/*      */     String str1;
/*      */     String str2;
/*      */     try {
/*  512 */       localStringTokenizer.nextToken();
/*      */ 
/*  515 */       str1 = localStringTokenizer.nextToken();
/*  516 */       str2 = localStringTokenizer.nextToken();
/*      */     } catch (NoSuchElementException localNoSuchElementException) {
/*  518 */       throw new BadDeviceValueException("Bad device timestamp reply '" + this.m_realTimeClock + "'");
/*      */     }
/*      */ 
/*  522 */     if ((this.m_settingDateFormatIsMDY != null) && (this.m_settingTimeFormatIsAMPM != null)) {
/*  523 */       this.m_timeStamp = createTimestamp(str1, str2, this.m_settingDateFormatIsMDY.booleanValue());
/*      */     }
/*      */     else
/*      */     {
/*  527 */       this.m_timeStamp = createTimestamp(str1, str2, true);
/*      */     }
/*      */ 
/*  530 */     logInfo(this, "decodeCurrentTimeStamp: current time stamp for device is " + this.m_timeStamp);
/*      */   }
/*      */ 
/*      */   private final Date createTimestamp(String paramString1, String paramString2, boolean paramBoolean)
/*      */     throws BadDeviceValueException
/*      */   {
/*  553 */     Contract.pre(paramString1 != null);
/*  554 */     Contract.pre(paramString2 != null);
/*      */ 
/*  556 */     int i = (paramString2.indexOf("AM") >= 0) || (paramString2.indexOf("PM") >= 0) ? 1 : 0;
/*      */     String str1;
/*  559 */     if (paramBoolean) {
/*  560 */       if (i != 0) {
/*  561 */         str1 = new String("M/dd/yy h:mm:ss aa");
/*      */       }
/*      */       else {
/*  564 */         str1 = new String("M/dd/yy H:mm:ss");
/*      */       }
/*      */ 
/*      */     }
/*  568 */     else if (i != 0) {
/*  569 */       str1 = new String("dd/M/yy h:mm:ss aa");
/*      */     }
/*      */     else {
/*  572 */       str1 = new String("dd/M/yy H:mm:ss");
/*      */     }
/*      */ 
/*  577 */     SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(str1);
/*  578 */     String str2 = paramString1 + " " + paramString2;
/*  579 */     Date localDate = localSimpleDateFormat.parse(str2, new ParsePosition(0));
/*      */ 
/*  581 */     if (localDate == null)
/*      */     {
/*  583 */       throw new BadDeviceValueException("Bad device time string (null returned) '" + str2 + "' received");
/*      */     }
/*      */ 
/*  587 */     return localDate;
/*      */   }
/*      */ 
/*      */   class Command extends LSMeter.AbstractCommand
/*      */   {
/*      */     static final byte REPLY_TIMEOUT = -1;
/*      */     static final int RETRY_COUNT = 2;
/*      */     static final int IO_DELAY_READ_RETRY_MS = 10000;
/*      */     static final int LEN_CHECKSUM = 4;
/*      */     private String m_command;
/*      */     private final LSMeter this$0;
/*      */ 
/*      */     Command(String paramString1, String paramInt, int paramReplyDecoder, ReplyDecoder arg5)
/*      */     {
/* 1008 */       super(paramInt);
/*      */ 
/* 1007 */       this.this$0 = this$1;
/*      */ 
/* 1009 */       this.m_command = paramString1;
/* 1010 */       setRawData(new int[paramReplyDecoder]);
/* 1011 */       this.m_maxRetryCount = 2;
/*      */       Object localObject;
/* 1012 */       this.m_replyDecoder = localObject;
/*      */     }
/*      */ 
/*      */     Command(String paramString1, String paramInt, int arg4)
/*      */     {
/* 1024 */       this(paramString1, paramInt, i, this$1);
/*      */     }
/*      */ 
/*      */     public String toString()
/*      */     {
/* 1034 */       return this.m_command + " ( " + this.m_description + ")";
/*      */     }
/*      */ 
/*      */     final String getCommand()
/*      */     {
/* 1043 */       return this.m_command;
/*      */     }
/*      */ 
/*      */     void sendAndRead()
/*      */       throws BadDeviceCommException, BadDeviceValueException
/*      */     {
/* 1056 */       int j = 0;
/* 1057 */       String str = "";
/*      */ 
/* 1062 */       if (this.this$0.getState() != 7) {
/* 1063 */         this.this$0.setState(4);
/*      */       }
/* 1065 */       sendCommand();
/*      */ 
/* 1067 */       if (getBytesToRead() > 0)
/*      */       {
/* 1069 */         if (!this.this$0.isHaltRequested())
/*      */         {
/* 1071 */           this.this$0.setState(5);
/* 1072 */           str = readDeviceData();
/*      */         }
/*      */ 
/* 1076 */         if ((str.length() == 0) && (!this.this$0.isHaltRequested()))
/*      */         {
/* 1078 */           MedicalDevice.Util.sleepMS(10000);
/* 1079 */           str = readDeviceData();
/*      */         }
/*      */         int i;
/* 1084 */         while (((i = str.length()) > 0) && (j < getBytesToRead()) && (!this.this$0.isHaltRequested()))
/*      */         {
/* 1086 */           int[] arrayOfInt = getRawData();
/* 1087 */           int k = Math.min(i, arrayOfInt.length - j);
/* 1088 */           System.arraycopy(MedicalDevice.Util.makeIntArray(str), 0, arrayOfInt, j, k);
/*      */ 
/* 1090 */           setRawData(arrayOfInt);
/*      */ 
/* 1092 */           j += k;
/* 1093 */           this.this$0.m_bytesReadThusFar += k;
/*      */ 
/* 1096 */           this.this$0.notifyDeviceUpdateProgress();
/*      */ 
/* 1098 */           if ((j >= getBytesToRead()) || 
/* 1099 */             (this.this$0.isHaltRequested()))
/*      */             continue;
/* 1101 */           str = readDeviceData();
/*      */         }
/*      */ 
/* 1106 */         if (!this.this$0.isHaltRequested())
/* 1107 */           this.m_replyDecoder.decodeReply(this);
/*      */       }
/*      */     }
/*      */ 
/*      */     void sendCommand()
/*      */       throws BadDeviceCommException
/*      */     {
/* 1122 */       Contract.pre(this.this$0.getRS232Port() != null);
/* 1123 */       Contract.pre(this.this$0.getRS232Port().isOpen());
/*      */ 
/* 1125 */       MedicalDevice.logInfo(this, "sendCommand: sending cmd " + this.m_command + " (" + this.m_description + ")");
/*      */       try
/*      */       {
/* 1130 */         for (int i = 0; i < "\021\r".length(); i++) {
/* 1131 */           this.this$0.getRS232Port().write("\021\r".substring(i, i + 1));
/*      */         }
/*      */ 
/* 1135 */         for (i = 0; i < this.m_command.length(); i++)
/* 1136 */           this.this$0.getRS232Port().write(this.m_command.substring(i, i + 1));
/*      */       }
/*      */       catch (IOException localIOException) {
/* 1139 */         throw new BadDeviceCommException("sendCommand: ERROR - an IOException  has occurred processing cmd " + this.m_command + " (" + this.m_description + ")");
/*      */       }
/*      */     }
/*      */ 
/*      */     private final String readDeviceData() throws BadDeviceCommException, BadDeviceValueException {
/* 1161 */       Contract.pre(this.this$0.getRS232Port() != null);
/* 1162 */       Contract.pre(this.this$0.getRS232Port().isOpen());
/*      */ 
/* 1164 */       MedicalDevice.logInfo(this, "readDeviceData: reading reply to cmd " + this.m_command + " (" + this.m_description + ")");
/*      */       String str;
/*      */       try {
/* 1169 */         str = this.this$0.getRS232Port().readLine();
/*      */ 
/* 1173 */         for (int i = 0; i < LSMeter.IGNORE_REPLY.length; i++) {
/* 1174 */           if (str.indexOf(LSMeter.IGNORE_REPLY[i]) < 0)
/*      */             continue;
/* 1176 */           str = this.this$0.getRS232Port().readLine();
/*      */ 
/* 1179 */           for (int j = 0; j < LSMeter.IGNORE_REPLY.length; j++) {
/* 1180 */             if (str.indexOf(LSMeter.IGNORE_REPLY[j]) < 0)
/*      */               continue;
/* 1182 */             str = "";
/* 1183 */             break;
/*      */           }
/*      */         }
/*      */ 
/*      */       }
/*      */       catch (IOException localIOException)
/*      */       {
/* 1190 */         throw new BadDeviceCommException("readDeviceData: ERROR - an IOException  has occurred processing cmd " + this.m_command + " (" + this.m_description + ")");
/*      */       }
/*      */ 
/* 1195 */       if (str.length() > 0)
/*      */       {
/* 1197 */         if (str.length() > 5) {
/* 1198 */           verifyCRC(str);
/*      */         }
/* 1200 */         MedicalDevice.logInfo(this, "readDeviceData: cmd " + this.m_command + " (" + this.m_description + ") returned " + str.length() + " data bytes, with reply = <" + str + ">");
/*      */       }
/*      */ 
/* 1204 */       return str;
/*      */     }
/*      */ 
/*      */     final int verifyCRC(String paramString)
/*      */       throws BadDeviceValueException
/*      */     {
/* 1220 */       Contract.pre(paramString != null);
/* 1221 */       Contract.pre(paramString.length() > 5);
/*      */ 
/* 1227 */       int i = paramString.indexOf('\r');
/*      */       String str1;
/* 1229 */       if (i > 0) {
/* 1230 */         str1 = paramString.substring(0, paramString.indexOf('\r'));
/*      */       }
/*      */       else {
/* 1233 */         throw new BadDeviceValueException("ERROR - '" + MedicalDevice.Util.convertControlChars(paramString) + "' record has bad CRC format");
/*      */       }
/*      */ 
/* 1236 */       int[] arrayOfInt = MedicalDevice.Util.makeIntArray(str1);
/* 1237 */       int j = MedicalDevice.Util.computeCRC16sum(arrayOfInt, 0, arrayOfInt.length - 4 - 1);
/*      */ 
/* 1239 */       String str2 = str1.substring(arrayOfInt.length - 4 + 1).trim();
/*      */       int k;
/*      */       try {
/* 1246 */         k = Integer.parseInt(str2, 16);
/*      */       } catch (NumberFormatException localNumberFormatException) {
/* 1248 */         throw new BadDeviceValueException("ERROR - '" + MedicalDevice.Util.convertControlChars(paramString) + "' record has bad CRC value of " + str2 + " (expected " + MedicalDevice.Util.getHex(j) + ").");
/*      */       }
/*      */ 
/* 1255 */       if (j != k) {
/* 1256 */         throw new BadDeviceValueException("ERROR - '" + MedicalDevice.Util.convertControlChars(str1) + "' record has incorrect CRC value of " + MedicalDevice.Util.getHex(k) + " (expected " + MedicalDevice.Util.getHex(j) + ").");
/*      */       }
/*      */ 
/* 1262 */       return k;
/*      */     }
/*      */   }
/*      */ 
/*      */   abstract class AbstractCommand extends DeviceCommand
/*      */   {
/*      */     private int[] m_rawData;
/*  829 */     ReplyDecoder m_replyDecoder = null;
/*      */     int m_maxRetryCount;
/*      */ 
/*      */     public AbstractCommand(String arg2)
/*      */     {
/*  844 */       super();
/*      */     }
/*      */ 
/*      */     void execute()
/*      */       throws BadDeviceCommException, BadDeviceValueException
/*      */     {
/*  860 */       int i = 0;
/*  861 */       int j = 0;
/*      */ 
/*  863 */       Contract.pre(LSMeter.this.getRS232Port() != null);
/*  864 */       Contract.pre(LSMeter.this.getRS232Port().isOpen());
/*      */ 
/*  866 */       MedicalDevice.m_lastCommandDescription = this.m_description;
/*      */       do
/*      */       {
/*      */         try
/*      */         {
/*  872 */           sendAndRead();
/*  873 */           j = 1;
/*      */         } catch (BadDeviceCommException localBadDeviceCommException) {
/*  875 */           i++;
/*  876 */           cleanupBeforeRetry();
/*  877 */           if (i <= this.m_maxRetryCount) {
/*  878 */             MedicalDevice.logWarning(this, "execute: cmd failed with exception: " + localBadDeviceCommException + "; retrying (attempts = " + (i + 1) + ")");
/*      */ 
/*  880 */             LSMeter.this.setState(7);
/*      */           } else {
/*  882 */             MedicalDevice.logError(this, "cmd " + this + " failed after " + i + " attempts" + "; exception = " + localBadDeviceCommException);
/*      */ 
/*  888 */             throw new BadDeviceCommException("execute: cmd " + this + " failed after " + i + " attempts");
/*      */           }
/*      */         }
/*      */         catch (BadDeviceValueException localBadDeviceValueException)
/*      */         {
/*  893 */           i++;
/*  894 */           cleanupBeforeRetry();
/*  895 */           if (i <= this.m_maxRetryCount) {
/*  896 */             MedicalDevice.logWarning(this, "execute: cmd failed with exception: " + localBadDeviceValueException + "; retrying (attempts = " + (i + 1) + ")");
/*      */ 
/*  898 */             LSMeter.this.setState(7);
/*      */           } else {
/*  900 */             MedicalDevice.logError(this, "cmd " + this + " failed after " + i + " attempts" + "; exception = " + localBadDeviceValueException);
/*      */ 
/*  906 */             throw new BadDeviceValueException("execute: cmd " + this + " failed after " + i + " attempts");
/*      */           }
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*  912 */       while ((j == 0) && (i <= this.m_maxRetryCount));
/*      */     }
/*      */ 
/*      */     void cleanupBeforeRetry()
/*      */     {
/*  919 */       LSMeter.this.getRS232Port().readUntilEmpty();
/*      */     }
/*      */ 
/*      */     abstract void sendAndRead()
/*      */       throws BadDeviceCommException, BadDeviceValueException;
/*      */ 
/*      */     final int[] getRawData()
/*      */     {
/*  939 */       return this.m_rawData;
/*      */     }
/*      */ 
/*      */     final void setRawData(int[] paramArrayOfInt)
/*      */     {
/*  948 */       this.m_rawData = paramArrayOfInt;
/*      */     }
/*      */ 
/*      */     int getBytesToRead()
/*      */     {
/*  957 */       int[] arrayOfInt = getRawData();
/*  958 */       return arrayOfInt == null ? 0 : arrayOfInt.length;
/*      */     }
/*      */ 
/*      */     final String getDescription()
/*      */     {
/*  967 */       return this.m_description;
/*      */     }
/*      */   }
/*      */ 
/*      */   private final class Reader
/*      */   {
/*      */     private int m_serialPortNum;
/*      */     private Vector m_commandCollection;
/*      */     private final LSMeter this$0;
/*      */ 
/*      */     private Reader(DeviceListener paramInt, int paramVector, Vector arg4)
/*      */     {
/*  680 */       this.this$0 = this$1;
/*  681 */       this$1.addDeviceListener(paramInt);
/*  682 */       this.m_serialPortNum = paramVector;
/*      */       Object localObject;
/*  683 */       this.m_commandCollection = localObject;
/*      */     }
/*      */ 
/*      */     private final void calcTotalBytesToRead()
/*      */     {
/*  693 */       this.this$0.m_totalBytesToRead = 0;
/*      */ 
/*  695 */       for (int i = 0; i < this.m_commandCollection.size(); i++) {
/*  696 */         LSMeter.AbstractCommand localAbstractCommand = (LSMeter.AbstractCommand)this.m_commandCollection.elementAt(i);
/*  697 */         if (localAbstractCommand != null)
/*  698 */           this.this$0.m_totalBytesToRead += localAbstractCommand.getBytesToRead();
/*      */       }
/*      */     }
/*      */ 
/*      */     private final void acquireDataFromDevice()
/*      */       throws BadDeviceCommException, BadDeviceValueException, IOException
/*      */     {
/*  714 */       int i = 0;
/*      */ 
/*  716 */       Vector localVector = new Vector();
/*      */ 
/*  720 */       this.this$0.m_bytesReadThusFar = 0;
/*  721 */       calcTotalBytesToRead();
/*      */ 
/*  723 */       this.this$0.notifyDeviceUpdateProgress(0);
/*      */       try
/*      */       {
/*  731 */         this.this$0.setState(2);
/*      */         try
/*      */         {
/*  734 */           initCommunications(this.m_serialPortNum);
/*      */         } catch (IOException localIOException) {
/*  736 */           if (!this.this$0.isHaltRequested()) {
/*  737 */             MedicalDevice.logWarning(this, "acquireDataFromDevice: initCommunications failed with IOException; retrying... (exception = " + localIOException + ")");
/*      */ 
/*  740 */             initCommunications(this.m_serialPortNum);
/*      */           }
/*      */         }
/*      */ 
/*  744 */         this.this$0.setPhase(5);
/*      */ 
/*  747 */         for (i = 0; (i < this.m_commandCollection.size()) && (!this.this$0.isHaltRequested()); )
/*      */         {
/*  749 */           LSMeter.AbstractCommand localAbstractCommand = (LSMeter.AbstractCommand)this.m_commandCollection.elementAt(i);
/*  750 */           if (localAbstractCommand != null)
/*      */           {
/*  752 */             localAbstractCommand.execute();
/*  753 */             if (!this.this$0.isHaltRequested())
/*      */             {
/*  755 */               int[] arrayOfInt = localAbstractCommand.getRawData();
/*  756 */               localVector.addElement(arrayOfInt);
/*      */             }
/*      */           }
/*  748 */           i++;
/*      */         }
/*      */ 
/*      */       }
/*      */       finally
/*      */       {
/*      */       }
/*      */ 
/*  780 */       ret;
/*      */     }
/*      */ 
/*      */     private final void initCommunications(int paramInt)
/*      */       throws IOException, BadDeviceCommException, BadDeviceValueException
/*      */     {
/*  797 */       this.this$0.initSerialPort(paramInt);
/*      */ 
/*  800 */       this.this$0.initDevice();
/*      */     }
/*      */ 
/*      */     private final void endCommunications()
/*      */       throws IOException
/*      */     {
/*  809 */       MedicalDevice.logInfo(this, "endCommunications: shutting down serial port.");
/*  810 */       if (this.this$0.getRS232Port() != null)
/*      */       {
/*  812 */         this.this$0.getRS232Port().close();
/*  813 */         this.this$0.setRS232Port(null);
/*      */       }
/*      */     }
/*      */ 
/*      */     Reader(DeviceListener paramInt, int paramVector, Vector param1, LSMeter.1 arg5)
/*      */     {
/*  663 */       this(paramInt, paramVector, param1);
/*      */     }
/*      */   }
/*      */ 
/*      */   class SnapshotCreator extends MedicalDevice.SnapshotCreator
/*      */   {
/*      */     static final int SNAPSHOT_BYTES = 170;
/*      */     static final int SNAPCODE_CURRENT_SETTINGS = 1;
/*      */     static final int SNAPCODE_DATALOG = 2;
/*      */     static final int LAST_SNAPCODE = 2;
/*      */ 
/*      */     SnapshotCreator()
/*      */     {
/*  613 */       super(170);
/*  614 */       LSMeter.this.m_snapshotFirmwareCount = LSMeter.this.m_firmwareVersion.length();
/*  615 */       LSMeter.this.m_snapshotSerialCount = LSMeter.this.m_serialNumber.length();
/*  616 */       LSMeter.this.m_snapshotTimeCount = LSMeter.this.m_realTimeClock.length();
/*      */     }
/*      */ 
/*      */     void createSnapshotBody()
/*      */     {
/*  626 */       LSMeter.this.m_snapshot = new Snapshot(LSMeter.this.m_snapshotFormatID, 1, pad(LSMeter.this.m_firmwareVersion), pad(LSMeter.this.m_serialNumber), pad(LSMeter.this.m_realTimeClock));
/*      */ 
/*  629 */       MedicalDevice.logInfo(this, "createSnapshot: creating snapshot ");
/*      */ 
/*  633 */       LSMeter.this.m_snapshot.addElement(1, LSMeter.this.m_currentSettings);
/*      */ 
/*  637 */       LSMeter.this.m_snapshot.addElement(2, LSMeter.this.m_datalog);
/*      */     }
/*      */ 
/*      */     String pad(String paramString)
/*      */     {
/*  647 */       Contract.preNonNull(paramString);
/*  648 */       StringBuffer localStringBuffer = new StringBuffer(paramString);
/*      */ 
/*  650 */       for (int i = paramString.length(); i < 50; i++) {
/*  651 */         localStringBuffer.append('$');
/*      */       }
/*      */ 
/*  654 */       return localStringBuffer.toString();
/*      */     }
/*      */   }
/*      */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.deviceportreader.LSMeter
 * JD-Core Version:    0.6.0
 */