/*      */ package minimed.ddms.deviceportreader;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.text.ParsePosition;
/*      */ import java.text.SimpleDateFormat;
/*      */ import java.util.Date;
/*      */ import java.util.HashSet;
/*      */ import java.util.NoSuchElementException;
/*      */ import java.util.Set;
/*      */ import java.util.StringTokenizer;
/*      */ import java.util.Vector;
/*      */ import minimed.util.Contract;
/*      */ 
/*      */ abstract class BayerMeter extends MedicalDevice
/*      */ {
/*      */   public static final int SNAPSHOT_FORMAT_ID = 140;
/*   39 */   static final String CMD_GET_DATA = String.valueOf('\006');
/*      */   static final String CMD_GET_SETTINGS = "\rR|";
/*      */   static final String CMD_GET_SETTINGS_DATA = "C|";
/*   42 */   static final String CMD_END_SESSION = '\r' + String.valueOf('\004');
/*      */   static final int LEN_ONE = 1;
/*      */   static final int LEN_GET_SETTINGS = 7;
/*      */   static final int METER_POWER_UP_DELAY_MS = 5000;
/*      */   static final String DATE_PREFIX = "|P|1|";
/*      */   static final String FIELD_DELIMITERS = "|\\^&\r\n";
/*      */   private static final String CMD_INIT_COMMUNICATIONS = "X";
/*      */   private static final int IO_DELAY_MS = 10;
/*      */   private static final int READ_TO_MS = 2500;
/*      */   private static final String HEADER_RECORD = "H";
/*      */   CommandGetData m_cmdGetData;
/*      */   private Command m_cmdGetSettings;
/*      */   private Command m_cmdGetSettingsData;
/*      */   private Command m_cmdBeginRemoteCmds;
/*      */   private int m_totalBytesToRead;
/*      */   private int m_bytesReadThusFar;
/*      */   String m_currentSettings;
/*      */   Set m_productCodes;
/*      */   Boolean m_settingBeeperEnable;
/*      */   Boolean m_settingDateFormatIsMD;
/*      */   Boolean m_settingReferenceIsPlasma;
/*      */   Boolean m_settingTempUnitsIsFahrenheit;
/*      */   Boolean m_settingTimeFormatIsAMPM;
/*      */   Boolean m_settingUnitsIsMGDL;
/*      */   private int m_maxRecords;
/*      */   private String m_dataUpload;
/*      */   private String m_realTimeClock;
/*      */ 
/*      */   BayerMeter(int paramInt)
/*      */   {
/*  139 */     this.m_firmwareVersion = new String("x.xx");
/*  140 */     this.m_serialNumber = new String("xxxx-xxxxxxx");
/*  141 */     this.m_snapshotFormatID = 140;
/*  142 */     this.m_snapshotCreator = new SnapshotCreator();
/*  143 */     this.m_maxRecords = paramInt;
/*  144 */     this.m_productCodes = new HashSet();
/*      */ 
/*  147 */     createCommands();
/*      */   }
/*      */ 
/*      */   public void readData(DeviceListener paramDeviceListener, int paramInt, String paramString)
/*      */     throws BadDeviceCommException, BadDeviceValueException, IOException
/*      */   {
/*  169 */     logInfo(this, "readData: starting reader...");
/*  170 */     setHaltRequested(false);
/*      */ 
/*  173 */     Vector localVector = createCommandList();
/*      */ 
/*  176 */     Reader localReader = new Reader(paramDeviceListener, paramInt, localVector, null);
/*  177 */     localReader.acquireDataFromDevice();
/*      */   }
/*      */ 
/*      */   int getDeviceType()
/*      */   {
/*  186 */     return 3;
/*      */   }
/*      */ 
/*      */   void initSerialPort(int paramInt)
/*      */     throws IOException
/*      */   {
/*  197 */     beginSerialPort(paramInt);
/*  198 */     setRS232Port(new SerialPort(paramInt, 7));
/*  199 */     getRS232Port().readUntilEmpty();
/*  200 */     getRS232Port().setIODelay(10);
/*  201 */     getRS232Port().setReadTimeOut(2500);
/*      */   }
/*      */ 
/*      */   void shutDownSerialPort()
/*      */     throws IOException
/*      */   {
/*  210 */     if (getRS232Port() != null)
/*      */     {
/*  212 */       getRS232Port().close();
/*  213 */       setRS232Port(null);
/*      */     }
/*      */   }
/*      */ 
/*      */   void findDevice(DeviceListener paramDeviceListener)
/*      */     throws BadDeviceCommException, IOException
/*      */   {
/*      */     try
/*      */     {
/*  226 */       initCommunicationIO();
/*      */     }
/*      */     catch (BadDeviceValueException localBadDeviceValueException) {
/*  229 */       throw new BadDeviceCommException("Got reply, but format is bad.");
/*      */     }
/*      */   }
/*      */ 
/*      */   void createCommands()
/*      */   {
/*  237 */     this.m_cmdGetSettings = new Command("\rR|", "Read Current Settings", 1, null);
/*      */ 
/*  239 */     this.m_cmdGetSettingsData = new Command("C|", "Read Current Settings Data", 7, null);
/*      */ 
/*  241 */     this.m_cmdGetSettingsData.setDataHasCRC(false);
/*  242 */     this.m_cmdBeginRemoteCmds = new Command(String.valueOf('\005'), "Begin Remote Commands", 1, null);
/*  243 */     this.m_cmdGetData = new CommandGetData();
/*      */   }
/*      */ 
/*      */   Vector createCommandList()
/*      */   {
/*  252 */     Vector localVector = new Vector();
/*      */ 
/*  255 */     localVector.addElement(this.m_cmdGetData);
/*  256 */     localVector.addElement(this.m_cmdBeginRemoteCmds);
/*  257 */     localVector.addElement(this.m_cmdGetSettings);
/*  258 */     localVector.addElement(this.m_cmdGetSettingsData);
/*  259 */     return localVector;
/*      */   }
/*      */ 
/*      */   void addCurrSettingsElementToSnapshot(int paramInt)
/*      */   {
/*  267 */     this.m_snapshot.addElement(paramInt, this.m_currentSettings);
/*      */   }
/*      */ 
/*      */   void initDeviceAfterSerialNumberKnown()
/*      */     throws BadDeviceValueException
/*      */   {
/*      */   }
/*      */ 
/*      */   void updateMaxRecordCount(int paramInt)
/*      */   {
/*  286 */     this.m_maxRecords = paramInt;
/*      */ 
/*  288 */     this.m_totalBytesToRead = new CommandGetData().m_rawData.length;
/*      */   }
/*      */ 
/*      */   abstract void decodeCurrentSettings()
/*      */     throws BadDeviceValueException;
/*      */ 
/*      */   private boolean verifyProductCode(String paramString)
/*      */   {
/*  307 */     return this.m_productCodes.contains(paramString);
/*      */   }
/*      */ 
/*      */   private final void initDevice()
/*      */     throws BadDeviceCommException, BadDeviceValueException
/*      */   {
/*  319 */     Contract.pre(getRS232Port() != null);
/*  320 */     Contract.pre(getRS232Port().isOpen());
/*      */ 
/*  322 */     setPhase(4);
/*  323 */     initCommunicationIO();
/*      */   }
/*      */ 
/*      */   private void initCommunicationIO()
/*      */     throws BadDeviceCommException, BadDeviceValueException
/*      */   {
/*  334 */     MedicalDevice.Util.sleepMS(5000);
/*  335 */     Command localCommand = new Command("X", "Initialize Communications", 2, null);
/*      */ 
/*  337 */     localCommand.setDataHasCRC(false);
/*  338 */     localCommand.execute();
/*      */ 
/*  341 */     int i = localCommand.m_rawData.length;
/*  342 */     if (i < 2) {
/*  343 */       throw new BadDeviceValueException("initCommunicationIO: got bad reply");
/*      */     }
/*  345 */     if ((localCommand.m_rawData[(i - 2)] != 4) || (localCommand.m_rawData[(i - 1)] != 5))
/*      */     {
/*  347 */       throw new BadDeviceValueException("initCommunicationIO: got bad reply");
/*      */     }
/*      */   }
/*      */ 
/*      */   private final void shutDownMeter()
/*      */     throws BadDeviceCommException, BadDeviceValueException
/*      */   {
/*  360 */     if (getRS232Port() != null)
/*      */     {
/*  362 */       Command localCommand = new Command(CMD_END_SESSION, "End Session", 0, null);
/*  363 */       localCommand.setDataHasCRC(false);
/*  364 */       localCommand.execute();
/*      */     }
/*      */   }
/*      */ 
/*      */   private final void decodeReply(Command paramCommand)
/*      */     throws BadDeviceValueException
/*      */   {
/*  379 */     String str = MedicalDevice.Util.makeString(paramCommand.m_rawData);
/*      */ 
/*  381 */     if ((this.m_cmdGetSettingsData != null) && (paramCommand.m_command.equals(this.m_cmdGetSettingsData.m_command)))
/*      */     {
/*  384 */       this.m_currentSettings = str;
/*  385 */       logInfo(this, "decodeReply: current settings is '" + this.m_currentSettings + "'");
/*      */ 
/*  387 */       decodeCurrentSettings();
/*      */     }
/*  389 */     else if (paramCommand.m_command.equals("X")) {
/*  390 */       logInfo(this, "decodeReply: reply is initialization: '" + str + "'");
/*      */     }
/*      */   }
/*      */ 
/*      */   private void decodeDataUploadHeader(String paramString)
/*      */     throws BadDeviceValueException
/*      */   {
/*  408 */     StringTokenizer localStringTokenizer = null;
/*  409 */     String str1 = null;
/*      */ 
/*  411 */     String str3 = "1";
/*      */ 
/*  413 */     Contract.pre(paramString != null);
/*      */     try
/*      */     {
/*  420 */       logInfo(this, "decodeDataUploadHeader: parsing dataUpload header: '" + paramString + '\'');
/*      */ 
/*  423 */       localStringTokenizer = new StringTokenizer(paramString, "|\\^&\r\n");
/*      */ 
/*  427 */       str1 = localStringTokenizer.nextToken();
/*  428 */       if (!str1.startsWith(str3 + "H")) {
/*  429 */         throw new BadDeviceValueException("Bad header record type (last token='" + str1 + "')");
/*      */       }
/*      */ 
/*  434 */       str1 = localStringTokenizer.nextToken();
/*  435 */       String str2 = localStringTokenizer.nextToken();
/*      */ 
/*  437 */       if (!verifyProductCode(str2)) {
/*  438 */         throw new BadDeviceValueException("Product code '" + str2 + "' not found in list '" + this.m_productCodes + "' (last token='" + str1 + "')");
/*      */       }
/*      */ 
/*  444 */       this.m_firmwareVersion = localStringTokenizer.nextToken();
/*      */ 
/*  447 */       str1 = localStringTokenizer.nextToken();
/*  448 */       this.m_serialNumber = localStringTokenizer.nextToken();
/*      */ 
/*  450 */       logInfo(this, "decodeDataUploadHeader: current firmware version for device is " + this.m_firmwareVersion);
/*      */ 
/*  452 */       logInfo(this, "decodeDataUploadHeader: current serial number for device is " + this.m_serialNumber);
/*      */ 
/*  456 */       str1 = localStringTokenizer.nextToken();
/*  457 */       str1 = localStringTokenizer.nextToken();
/*  458 */       this.m_realTimeClock = localStringTokenizer.nextToken();
/*  459 */       decodeCurrentTimeStamp();
/*      */     } catch (NoSuchElementException localNoSuchElementException) {
/*  461 */       throw new BadDeviceValueException("Bad dataUpload header field '" + paramString + "' received (last token='" + str1 + "')");
/*      */     }
/*      */     catch (NumberFormatException localNumberFormatException)
/*      */     {
/*  465 */       throw new BadDeviceValueException("Bad dataUpload header field number '" + paramString + "' received (last token='" + str1 + "')");
/*      */     }
/*      */   }
/*      */ 
/*      */   private void decodeCurrentTimeStamp()
/*      */     throws BadDeviceValueException
/*      */   {
/*  481 */     this.m_timeStamp = createTimestamp(this.m_realTimeClock);
/*  482 */     logInfo(this, "decodeCurrentTimeStamp: current time stamp for device is " + this.m_timeStamp);
/*      */   }
/*      */ 
/*      */   private final Date createTimestamp(String paramString)
/*      */     throws BadDeviceValueException
/*      */   {
/*  500 */     String str = new String("yyyyMMddHHmm");
/*      */ 
/*  502 */     Contract.pre(paramString != null);
/*  503 */     Contract.pre(paramString.length() == str.length());
/*      */ 
/*  505 */     SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(str);
/*  506 */     Date localDate = localSimpleDateFormat.parse(paramString, new ParsePosition(0));
/*      */ 
/*  508 */     if (localDate == null)
/*      */     {
/*  510 */       throw new BadDeviceValueException("Bad device time string (null returned) '" + paramString + "' received");
/*      */     }
/*      */ 
/*  514 */     return localDate;
/*      */   }
/*      */ 
/*      */   private final class SnapshotCreator extends MedicalDevice.SnapshotCreator
/*      */   {
/*      */     static final int SNAPCODE_DATAUPLOAD = 1;
/*      */     static final int SNAPCODE_CURRENT_SETTINGS = 2;
/*      */     static final int SNAPSHOT_BYTES = 80;
/*      */     static final int LAST_SNAPCODE = 2;
/*      */ 
/*      */     SnapshotCreator()
/*      */     {
/* 1100 */       super(80);
/* 1101 */       BayerMeter.this.m_snapshotFirmwareCount = 0;
/* 1102 */       BayerMeter.this.m_snapshotSerialCount = 0;
/* 1103 */       BayerMeter.this.m_snapshotTimeCount = 0;
/*      */     }
/*      */ 
/*      */     void createSnapshotBody()
/*      */     {
/* 1113 */       String str1 = "";
/* 1114 */       String str2 = "";
/* 1115 */       String str3 = "";
/* 1116 */       BayerMeter.this.m_snapshot = new Snapshot(BayerMeter.this.m_snapshotFormatID, 1, str1, str2, str3);
/*      */ 
/* 1119 */       MedicalDevice.logInfo(this, "createSnapshot: creating snapshot");
/*      */ 
/* 1123 */       BayerMeter.this.m_snapshot.addElement(1, BayerMeter.this.m_dataUpload);
/*      */ 
/* 1127 */       BayerMeter.this.addCurrSettingsElementToSnapshot(2);
/*      */     }
/*      */   }
/*      */ 
/*      */   final class CommandGetData extends BayerMeter.Command
/*      */   {
/*      */     private static final int HEADER_SIZE = 300;
/*      */     private static final int AVG_REC_LEN = 55;
/*      */     private static final int HEADER_READ_TO_MS = 10000;
/*      */     private BayerMeter.Command m_cmdGetDataRecord;
/*      */ 
/*      */     CommandGetData()
/*      */     {
/*  989 */       super(BayerMeter.CMD_GET_DATA, "Read Data Records", 300 + BayerMeter.this.m_maxRecords * 55, null);
/*      */ 
/*  992 */       this.m_cmdGetDataRecord = new BayerMeter.Command(BayerMeter.this, BayerMeter.CMD_GET_DATA, "Read Data Record", 55, null);
/*      */     }
/*      */ 
/*      */     void execute()
/*      */       throws BadDeviceCommException, BadDeviceValueException
/*      */     {
/* 1010 */       int i = 0;
/* 1011 */       int j = 0;
/* 1012 */       this.m_rawData = new int[0];
/*      */ 
/* 1014 */       while (i == 0) {
/* 1015 */         MedicalDevice.logInfo(this, "execute: ****** reading record " + j + " ******");
/* 1016 */         int[] arrayOfInt = new int[0];
/* 1017 */         if (j == 0)
/*      */         {
/* 1020 */           int k = 0;
/* 1021 */           int m = 0;
/*      */           try {
/* 1023 */             k = BayerMeter.this.getRS232Port().getReadTimeOut();
/* 1024 */             m = 1;
/* 1025 */             BayerMeter.this.getRS232Port().setReadTimeOut(10000);
/*      */ 
/* 1028 */             arrayOfInt = readOneRecord();
/*      */ 
/* 1031 */             BayerMeter.this.decodeDataUploadHeader(MedicalDevice.Util.makeString(arrayOfInt).trim());
/*      */ 
/* 1034 */             BayerMeter.this.initDeviceAfterSerialNumberKnown();
/*      */           } catch (IOException localIOException1) {
/*      */           }
/*      */           finally {
/* 1038 */             if (m != 0)
/*      */               try
/*      */               {
/* 1041 */                 BayerMeter.this.getRS232Port().setReadTimeOut(k);
/*      */               }
/*      */               catch (IOException localIOException2) {
/*      */               }
/*      */           }
/*      */         }
/*      */         else {
/* 1048 */           arrayOfInt = readOneRecord();
/*      */         }
/*      */ 
/* 1052 */         String str = MedicalDevice.Util.makeString(arrayOfInt);
/* 1053 */         i = str.indexOf(4) >= 0 ? 1 : 0;
/*      */ 
/* 1055 */         j++;
/*      */       }
/* 1057 */       BayerMeter.access$1302(BayerMeter.this, MedicalDevice.Util.makeString(this.m_rawData).trim());
/*      */     }
/*      */ 
/*      */     private int[] readOneRecord()
/*      */       throws BadDeviceCommException, BadDeviceValueException
/*      */     {
/* 1069 */       this.m_cmdGetDataRecord.execute();
/* 1070 */       int[] arrayOfInt = this.m_cmdGetDataRecord.m_rawData;
/* 1071 */       this.m_rawData = MedicalDevice.Util.concat(this.m_rawData, arrayOfInt);
/* 1072 */       return arrayOfInt;
/*      */     }
/*      */   }
/*      */ 
/*      */   private final class Reader
/*      */   {
/*      */     private int m_serialPortNum;
/*      */     private Vector m_commandCollection;
/*      */     private final BayerMeter this$0;
/*      */ 
/*      */     private Reader(DeviceListener paramInt, int paramVector, Vector arg4)
/*      */     {
/*  821 */       this.this$0 = this$1;
/*  822 */       this$1.addDeviceListener(paramInt);
/*  823 */       this.m_serialPortNum = paramVector;
/*      */       Object localObject;
/*  824 */       this.m_commandCollection = localObject;
/*      */     }
/*      */ 
/*      */     private final void acquireDataFromDevice()
/*      */       throws BadDeviceCommException, BadDeviceValueException, IOException
/*      */     {
/*  840 */       int i = 0;
/*      */ 
/*  842 */       Vector localVector = new Vector();
/*      */ 
/*  846 */       this.this$0.m_firmwareVersion = "";
/*  847 */       this.this$0.m_serialNumber = "";
/*  848 */       BayerMeter.access$702(this.this$0, "");
/*      */ 
/*  852 */       BayerMeter.access$502(this.this$0, 0);
/*  853 */       BayerMeter.access$402(this.this$0, 0);
/*      */       BayerMeter.Command localCommand;
/*  854 */       for (int j = 0; j < this.m_commandCollection.size(); j++) {
/*  855 */         localCommand = (BayerMeter.Command)this.m_commandCollection.elementAt(j);
/*  856 */         if (localCommand != null) {
/*  857 */           BayerMeter.access$512(this.this$0, localCommand.m_rawData.length);
/*      */         }
/*      */       }
/*      */ 
/*  861 */       this.this$0.notifyDeviceUpdateProgress(0);
/*      */       try
/*      */       {
/*  869 */         this.this$0.setState(2);
/*      */         try
/*      */         {
/*  872 */           initCommunications(this.m_serialPortNum);
/*      */         } catch (IOException localIOException) {
/*  874 */           if (!this.this$0.isHaltRequested()) {
/*  875 */             MedicalDevice.logWarning(this, "acquireDataFromDevice: initCommunications failed with IOException; retrying... (exception = " + localIOException + ")");
/*      */ 
/*  878 */             initCommunications(this.m_serialPortNum);
/*      */           }
/*      */         }
/*      */ 
/*  882 */         this.this$0.setPhase(5);
/*      */ 
/*  885 */         for (i = 0; (i < this.m_commandCollection.size()) && (!this.this$0.isHaltRequested()); )
/*      */         {
/*  887 */           localCommand = (BayerMeter.Command)this.m_commandCollection.elementAt(i);
/*  888 */           if (localCommand != null)
/*      */           {
/*  890 */             localCommand.execute();
/*  891 */             if (!this.this$0.isHaltRequested())
/*      */             {
/*  893 */               int[] arrayOfInt = BayerMeter.Command.access$800(localCommand);
/*  894 */               localVector.addElement(arrayOfInt);
/*      */             }
/*      */           }
/*  886 */           i++;
/*      */         }
/*      */ 
/*      */       }
/*      */       finally
/*      */       {
/*      */       }
/*      */ 
/*  918 */       ret;
/*      */     }
/*      */ 
/*      */     private final void initCommunications(int paramInt)
/*      */       throws IOException, BadDeviceCommException, BadDeviceValueException
/*      */     {
/*  935 */       this.this$0.initSerialPort(paramInt);
/*      */ 
/*  938 */       this.this$0.initDevice();
/*      */     }
/*      */ 
/*      */     private final void endCommunications()
/*      */       throws BadDeviceValueException, BadDeviceCommException, IOException
/*      */     {
/*      */       try
/*      */       {
/*  952 */         MedicalDevice.logInfo(this, "endCommunications: shutting down meter communications.");
/*  953 */         this.this$0.shutDownMeter();
/*      */       } finally {
/*  955 */         MedicalDevice.logInfo(this, "endCommunications: shutting down serial port.");
/*  956 */         if (this.this$0.getRS232Port() != null)
/*      */         {
/*  958 */           this.this$0.getRS232Port().close();
/*  959 */           this.this$0.setRS232Port(null);
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/*      */     Reader(DeviceListener paramInt, int paramVector, Vector param1, BayerMeter.1 arg5)
/*      */     {
/*  804 */       this(paramInt, paramVector, param1);
/*      */     }
/*      */   }
/*      */ 
/*      */   private class Command extends DeviceCommand
/*      */   {
/*      */     static final byte REPLY_TIMEOUT = -1;
/*      */     static final int LEN_CHECKSUM = 2;
/*      */     static final int RETRY_COUNT = 2;
/*      */     static final int IO_DELAY_READ_RETRY_MS = 100;
/*      */     int[] m_rawData;
/*      */     String m_command;
/*      */     private boolean m_dataHasCRC;
/*      */     private final BayerMeter this$0;
/*      */ 
/*      */     private Command(String paramString1, String paramInt, int arg4)
/*      */     {
/*  553 */       super();
/*      */ 
/*  552 */       this.this$0 = this$1;
/*      */ 
/*  554 */       this.m_command = paramString1;
/*      */       int i;
/*  555 */       this.m_rawData = new int[i];
/*  556 */       this.m_dataHasCRC = true;
/*      */     }
/*      */ 
/*      */     void execute()
/*      */       throws BadDeviceCommException, BadDeviceValueException
/*      */     {
/*  572 */       int i = 0;
/*  573 */       int j = 0;
/*      */ 
/*  575 */       Contract.pre(this.this$0.getRS232Port() != null);
/*  576 */       Contract.pre(this.this$0.getRS232Port().isOpen());
/*      */ 
/*  578 */       MedicalDevice.m_lastCommandDescription = this.m_description;
/*      */       do
/*      */       {
/*      */         try
/*      */         {
/*  584 */           sendAndRead();
/*  585 */           j = 1;
/*      */         } catch (BadDeviceCommException localBadDeviceCommException) {
/*  587 */           i++;
/*  588 */           this.this$0.getRS232Port().readUntilEmpty();
/*  589 */           if (i <= 2) {
/*  590 */             MedicalDevice.logWarning(this, "execute: cmd failed with exception: " + localBadDeviceCommException + "; retrying (attempts = " + (i + 1) + ")");
/*      */ 
/*  592 */             this.this$0.setState(7);
/*      */           } else {
/*  594 */             MedicalDevice.logError(this, "cmd " + this.m_command + " (" + this.m_description + ") failed after " + i + " attempts" + "; exception = " + localBadDeviceCommException);
/*      */ 
/*  601 */             throw new BadDeviceCommException("execute: cmd " + this.m_command + " (" + this.m_description + ") failed after " + i + " attempts");
/*      */           }
/*      */         }
/*      */         catch (BadDeviceValueException localBadDeviceValueException)
/*      */         {
/*  606 */           i++;
/*  607 */           this.this$0.getRS232Port().readUntilEmpty();
/*  608 */           if (i <= 2) {
/*  609 */             MedicalDevice.logWarning(this, "execute: cmd failed with exception: " + localBadDeviceValueException + "; retrying (attempts = " + (i + 1) + ")");
/*      */ 
/*  611 */             this.this$0.setState(7);
/*      */           } else {
/*  613 */             MedicalDevice.logError(this, "cmd " + this.m_command + " (" + this.m_description + ") failed after " + i + " attempts" + "; exception = " + localBadDeviceValueException);
/*      */ 
/*  620 */             throw new BadDeviceValueException("execute: cmd " + this.m_command + " (" + this.m_description + ") failed after " + i + " attempts");
/*      */           }
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*  626 */       while ((j == 0) && (i <= 2));
/*      */     }
/*      */ 
/*      */     private final int[] getRawData()
/*      */     {
/*  635 */       return this.m_rawData;
/*      */     }
/*      */ 
/*      */     private final String readDeviceData() throws BadDeviceCommException, BadDeviceValueException {
/*  653 */       Contract.pre(this.this$0.getRS232Port() != null);
/*  654 */       Contract.pre(this.this$0.getRS232Port().isOpen());
/*      */ 
/*  656 */       MedicalDevice.logInfo(this, "readDeviceData: reading reply to cmd " + this.m_command + " (" + this.m_description + ")");
/*      */       String str;
/*      */       try {
/*  661 */         str = this.this$0.getRS232Port().readLine();
/*      */       } catch (IOException localIOException) {
/*  663 */         throw new BadDeviceCommException("readDeviceData: ERROR - an IOException  has occurred processing cmd " + this.m_command + " (" + this.m_description + ")");
/*      */       }
/*      */ 
/*  669 */       if (str.length() == 0)
/*      */       {
/*  671 */         throw new BadDeviceCommException("readDeviceData: ERROR - no reply for cmd " + this.m_command + " (" + this.m_description + ")");
/*      */       }
/*      */ 
/*  674 */       if (str.length() == 1)
/*      */       {
/*  676 */         MedicalDevice.logInfo(this, "readDeviceData: control code received: " + MedicalDevice.Util.convertControlChars(str));
/*      */       }
/*      */       else {
/*  679 */         if (isDataHasCRC())
/*      */         {
/*  681 */           boolean bool = false;
/*  682 */           if (str.length() > 3) {
/*  683 */             bool = MedicalDevice.Util.isCRC8E1381Valid(str);
/*      */           }
/*  685 */           if (!bool) {
/*  686 */             MedicalDevice.logError(this, "readDeviceData: ERROR - cmd " + this.m_command + " resulted in bad CRC reply of " + MedicalDevice.Util.convertControlChars(str));
/*      */ 
/*  689 */             throw new BadDeviceValueException("readDeviceData: ERROR - cmd " + this.m_command + " resulted in bad CRC reply of " + MedicalDevice.Util.convertControlChars(str));
/*      */           }
/*      */ 
/*      */         }
/*      */ 
/*  695 */         MedicalDevice.logInfo(this, "readDeviceData: cmd " + this.m_command + " (" + this.m_description + ") returned " + str.length() + " data bytes, with reply = <" + str + ">");
/*      */       }
/*      */ 
/*  700 */       return str;
/*      */     }
/*      */ 
/*      */     private final void sendCommand()
/*      */       throws BadDeviceCommException
/*      */     {
/*  713 */       Contract.pre(this.this$0.getRS232Port() != null);
/*  714 */       Contract.pre(this.this$0.getRS232Port().isOpen());
/*      */ 
/*  716 */       MedicalDevice.logInfo(this, "sendCommand: sending cmd " + this.m_command + " (" + this.m_description + ")");
/*      */       try
/*      */       {
/*  726 */         this.this$0.getRS232Port().write(this.m_command);
/*      */       }
/*      */       catch (IOException localIOException) {
/*  729 */         throw new BadDeviceCommException("sendCommand: ERROR - an IOException  has occurred processing cmd " + this.m_command + " (" + this.m_description + ")");
/*      */       }
/*      */     }
/*      */ 
/*      */     private final void sendAndRead()
/*      */       throws BadDeviceCommException, BadDeviceValueException
/*      */     {
/*  748 */       if (this.this$0.getState() != 7) {
/*  749 */         this.this$0.setState(4);
/*      */       }
/*  751 */       sendCommand();
/*      */ 
/*  753 */       if (this.m_rawData.length > 0)
/*      */       {
/*  755 */         if (!this.this$0.isHaltRequested())
/*      */         {
/*  757 */           this.this$0.setState(5);
/*  758 */           this.m_rawData = MedicalDevice.Util.makeIntArray(readDeviceData());
/*      */         }
/*      */ 
/*  762 */         if ((this.m_rawData.length == 0) && (!this.this$0.isHaltRequested()))
/*      */         {
/*  764 */           MedicalDevice.Util.sleepMS(100);
/*  765 */           this.m_rawData = MedicalDevice.Util.makeIntArray(readDeviceData());
/*      */         }
/*      */ 
/*  768 */         BayerMeter.access$412(this.this$0, this.m_rawData.length);
/*      */ 
/*  771 */         this.this$0.notifyDeviceUpdateProgress(this.this$0.m_bytesReadThusFar, this.this$0.m_totalBytesToRead);
/*      */ 
/*  773 */         if (!this.this$0.isHaltRequested())
/*  774 */           this.this$0.decodeReply(this);
/*      */       }
/*      */     }
/*      */ 
/*      */     private final boolean isDataHasCRC()
/*      */     {
/*  785 */       return this.m_dataHasCRC;
/*      */     }
/*      */ 
/*      */     private final void setDataHasCRC(boolean paramBoolean)
/*      */     {
/*  795 */       this.m_dataHasCRC = paramBoolean;
/*      */     }
/*      */ 
/*      */     Command(String paramString1, String paramInt, int param1, BayerMeter.1 arg5)
/*      */     {
/*  527 */       this(paramString1, paramInt, param1);
/*      */     }
/*      */   }
/*      */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.deviceportreader.BayerMeter
 * JD-Core Version:    0.6.0
 */