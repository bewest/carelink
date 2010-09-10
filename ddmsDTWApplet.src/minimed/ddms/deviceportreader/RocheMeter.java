/*      */ package minimed.ddms.deviceportreader;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.text.ParsePosition;
/*      */ import java.text.SimpleDateFormat;
/*      */ import java.util.Arrays;
/*      */ import java.util.Date;
/*      */ import java.util.Vector;
/*      */ import minimed.util.Contract;
/*      */ 
/*      */ abstract class RocheMeter extends MedicalDevice
/*      */ {
/*   35 */   private static final int[] CLEAR_ERROR_STATUS = { 11, 13 };
/*      */   private static final int DEFAULT_REPLY_LENGTH = 50;
/*      */   private static final char PAD_CHAR = '$';
/*      */   private static final int EXTRACT_RESULTS_COUNT_INDEX = 4;
/*      */   private static final int IO_DELAY_MS = 5;
/*      */   private static final int IO_DELAY_LIMIT = 100;
/*      */   private static final int IO_DELAY_INCREMENT = 5;
/*   65 */   private int m_ioDelay = 5;
/*      */   private Vector m_commandCollection;
/*      */   private Command m_cmdReadFirmwareVersion;
/*      */   private Command m_cmdReadSerialNumber;
/*      */   private Command m_cmdReadTime;
/*      */   private Command m_cmdReadDate;
/*      */   private Command m_cmdReadModelNumber;
/*      */   private Command m_cmdGetNumResults;
/*      */   private Command m_cmdExtractResults;
/*      */   private Command m_cmdClearErrorStatus;
/*      */   private int m_totalBytesToRead;
/*      */   private int m_bytesReadThusFar;
/*   83 */   private String m_time = "";
/*      */ 
/*   88 */   private String m_date = "";
/*      */   private int[] m_meterResults;
/*      */   private int m_numResults;
/*      */   private int m_extractResultsRecLen;
/*      */ 
/*      */   RocheMeter(String paramString, int paramInt1, int paramInt2, int[] paramArrayOfInt1, int[] paramArrayOfInt2, int[] paramArrayOfInt3, int[] paramArrayOfInt4, int[] paramArrayOfInt5, int[] paramArrayOfInt6, int[] paramArrayOfInt7, int paramInt3, int paramInt4)
/*      */   {
/*  120 */     this.m_description = paramString;
/*  121 */     this.m_snapshotFormatID = paramInt1;
/*  122 */     this.m_deviceClassID = paramInt2;
/*  123 */     logInfo(this, "creating interface to the '" + this.m_description + "', package version " + getPackageVersion());
/*      */ 
/*  126 */     this.m_snapshotCreator = new SnapshotCreator();
/*  127 */     this.m_extractResultsRecLen = paramInt4;
/*      */ 
/*  130 */     this.m_cmdReadFirmwareVersion = new Command(paramArrayOfInt1, "Read Firmware Version", new ReplyDecoder()
/*      */     {
/*      */       public void decodeReply(RocheMeter.Command paramCommand) throws BadDeviceValueException {
/*  133 */         RocheMeter.this.m_firmwareVersion = RocheMeter.this.extractData(paramCommand.m_rawData);
/*  134 */         MedicalDevice.logInfo(this, "decodeReply: firmware version is '" + RocheMeter.this.m_firmwareVersion + "'");
/*      */       }
/*      */     });
/*  139 */     this.m_cmdReadSerialNumber = new Command(paramArrayOfInt2, "Read Serial Number", new ReplyDecoder()
/*      */     {
/*      */       public void decodeReply(RocheMeter.Command paramCommand) throws BadDeviceValueException {
/*  142 */         RocheMeter.this.m_serialNumber = RocheMeter.this.extractData(paramCommand.m_rawData);
/*  143 */         MedicalDevice.logInfo(this, "decodeReply: serial number is '" + RocheMeter.this.m_serialNumber + "'");
/*      */       }
/*      */     });
/*  148 */     this.m_cmdReadTime = new Command(paramArrayOfInt3, "Read Time", new ReplyDecoder()
/*      */     {
/*      */       public void decodeReply(RocheMeter.Command paramCommand) throws BadDeviceValueException {
/*  151 */         RocheMeter.access$102(RocheMeter.this, RocheMeter.this.extractData(paramCommand.m_rawData));
/*  152 */         MedicalDevice.logInfo(this, "decodeReply: current time is '" + RocheMeter.this.m_time + "'");
/*      */       }
/*      */     });
/*  157 */     this.m_cmdReadDate = new Command(paramArrayOfInt4, "Read Date", new ReplyDecoder()
/*      */     {
/*      */       public void decodeReply(RocheMeter.Command paramCommand) throws BadDeviceValueException {
/*  160 */         RocheMeter.access$202(RocheMeter.this, RocheMeter.this.extractData(paramCommand.m_rawData));
/*  161 */         MedicalDevice.logInfo(this, "decodeReply: current date is '" + RocheMeter.this.m_date + "'");
/*  162 */         RocheMeter.this.decodeCurrentTimeStamp();
/*      */       }
/*      */     });
/*  167 */     this.m_cmdReadModelNumber = new Command(paramArrayOfInt5, "Read Model Number", new ReplyDecoder()
/*      */     {
/*      */       public void decodeReply(RocheMeter.Command paramCommand) throws BadDeviceValueException {
/*  170 */         RocheMeter.this.m_modelNumber = RocheMeter.this.extractData(paramCommand.m_rawData);
/*  171 */         MedicalDevice.logInfo(this, "decodeReply: model number is '" + RocheMeter.this.m_modelNumber + "'");
/*      */       }
/*      */     });
/*  176 */     this.m_cmdGetNumResults = new Command(paramArrayOfInt6, "Get Number of Results Stored", new ReplyDecoder()
/*      */     {
/*      */       public void decodeReply(RocheMeter.Command paramCommand) throws BadDeviceValueException {
/*  179 */         String str = RocheMeter.this.extractData(paramCommand.m_rawData);
/*      */         try {
/*  181 */           RocheMeter.access$402(RocheMeter.this, Integer.parseInt(str));
/*      */         } catch (NumberFormatException localNumberFormatException) {
/*  183 */           throw new BadDeviceValueException("decodeReply: bad number of results: " + str);
/*      */         }
/*      */ 
/*  186 */         MedicalDevice.logInfo(this, "decodeReply: number of meter results is " + RocheMeter.this.m_numResults);
/*      */ 
/*  190 */         int i = RocheMeter.this.m_extractResultsRecLen * RocheMeter.this.m_numResults;
/*  191 */         int j = RocheMeter.access$600(RocheMeter.this).m_rawData.length - i;
/*  192 */         RocheMeter.access$720(RocheMeter.this, j);
/*      */ 
/*  195 */         RocheMeter.this.updateExtractResultsCmd();
/*      */       }
/*      */     });
/*  200 */     this.m_cmdExtractResults = new Command(paramArrayOfInt7, "Extract Results", paramInt3, new ReplyDecoder()
/*      */     {
/*      */       public void decodeReply(RocheMeter.Command paramCommand) throws BadDeviceValueException {
/*  203 */         RocheMeter.access$902(RocheMeter.this, paramCommand.m_rawData);
/*  204 */         MedicalDevice.logInfo(this, "decodeReply: meter results is " + RocheMeter.this.m_meterResults.length + " bytes.");
/*      */       }
/*      */     });
/*  210 */     this.m_cmdClearErrorStatus = new Command(CLEAR_ERROR_STATUS, "Clear Error Status", new ReplyDecoder()
/*      */     {
/*      */       public void decodeReply(RocheMeter.Command paramCommand) throws BadDeviceValueException {
/*  213 */         MedicalDevice.logInfo(this, "decodeReply: error status is " + RocheMeter.this.extractData(paramCommand.m_rawData));
/*      */       }
/*      */     });
/*      */   }
/*      */ 
/*      */   public void readData(DeviceListener paramDeviceListener, int paramInt, String paramString)
/*      */     throws BadDeviceCommException, BadDeviceValueException, IOException
/*      */   {
/*  239 */     logInfo(this, "readData: starting reader...");
/*  240 */     setHaltRequested(false);
/*      */ 
/*  243 */     Vector localVector = createCommandList();
/*      */ 
/*  246 */     Reader localReader = new Reader(paramDeviceListener, paramInt, localVector, null);
/*  247 */     localReader.acquireDataFromDevice();
/*      */   }
/*      */ 
/*      */   int getDeviceType()
/*      */   {
/*  256 */     return 3;
/*      */   }
/*      */ 
/*      */   void findDevice(DeviceListener paramDeviceListener)
/*      */     throws BadDeviceCommException, IOException
/*      */   {
/*      */     try
/*      */     {
/*  268 */       initDevice();
/*  269 */       this.m_cmdReadModelNumber.execute();
/*      */     }
/*      */     catch (BadDeviceValueException localBadDeviceValueException) {
/*  272 */       throw new BadDeviceCommException("Got reply, but format is bad.");
/*      */     }
/*      */   }
/*      */ 
/*      */   void initSerialPort(int paramInt)
/*      */     throws IOException
/*      */   {
/*  284 */     beginSerialPort(paramInt);
/*  285 */     setRS232Port(new SerialPort(paramInt, 7));
/*  286 */     getRS232Port().readUntilEmpty();
/*  287 */     getRS232Port().setIODelay(this.m_ioDelay);
/*      */   }
/*      */ 
/*      */   void shutDownSerialPort()
/*      */     throws IOException
/*      */   {
/*  296 */     if (getRS232Port() != null)
/*      */     {
/*  298 */       getRS232Port().close();
/*  299 */       setRS232Port(null);
/*      */     }
/*      */   }
/*      */ 
/*      */   Vector createCommandList()
/*      */   {
/*  309 */     Vector localVector = new Vector();
/*      */ 
/*  311 */     localVector.addElement(this.m_cmdReadFirmwareVersion);
/*  312 */     localVector.addElement(this.m_cmdReadSerialNumber);
/*      */ 
/*  315 */     localVector.addElement(this.m_cmdReadTime);
/*  316 */     localVector.addElement(this.m_cmdReadDate);
/*  317 */     localVector.addElement(this.m_cmdReadModelNumber);
/*  318 */     localVector.addElement(this.m_cmdGetNumResults);
/*  319 */     localVector.addElement(this.m_cmdExtractResults);
/*  320 */     return localVector;
/*      */   }
/*      */ 
/*      */   abstract String extractData(int[] paramArrayOfInt)
/*      */     throws BadDeviceValueException;
/*      */ 
/*      */   String pad(String paramString)
/*      */   {
/*  340 */     Contract.preNonNull(paramString);
/*  341 */     StringBuffer localStringBuffer = new StringBuffer(paramString);
/*      */ 
/*  343 */     for (int i = paramString.length(); i < 50; i++) {
/*  344 */       localStringBuffer.append('$');
/*      */     }
/*      */ 
/*  347 */     return localStringBuffer.toString();
/*      */   }
/*      */ 
/*      */   String pad(int paramInt)
/*      */   {
/*  357 */     return pad("" + paramInt);
/*      */   }
/*      */ 
/*      */   private void decodeCurrentTimeStamp()
/*      */     throws BadDeviceValueException
/*      */   {
/*  369 */     Contract.pre(this.m_date.length() > 0);
/*  370 */     Contract.pre(this.m_time.length() > 0);
/*      */ 
/*  374 */     this.m_timeStamp = createTimestamp(this.m_date + this.m_time);
/*  375 */     logInfo(this, "decodeCurrentTimeStamp: current time stamp for device is " + this.m_timeStamp);
/*      */   }
/*      */ 
/*      */   private final Date createTimestamp(String paramString)
/*      */     throws BadDeviceValueException
/*      */   {
/*  393 */     String str = "yyMMddHHmmss";
/*  394 */     Contract.preNonNull(paramString);
/*  395 */     Contract.pre(paramString.length() == str.length());
/*      */ 
/*  397 */     SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(str);
/*  398 */     Date localDate = localSimpleDateFormat.parse(paramString, new ParsePosition(0));
/*      */ 
/*  400 */     if (localDate == null)
/*      */     {
/*  402 */       throw new BadDeviceValueException("Bad device time string (null returned) '" + paramString + "' received");
/*      */     }
/*      */ 
/*  406 */     return localDate;
/*      */   }
/*      */ 
/*      */   private final void initDevice()
/*      */     throws BadDeviceCommException, BadDeviceValueException
/*      */   {
/*  417 */     setPhase(4);
/*      */     try
/*      */     {
/*  420 */       initIRPort();
/*      */     } catch (IOException localIOException) {
/*  422 */       throw new BadDeviceCommException("initDevice: ERROR - an IOException  has occurred: " + localIOException);
/*      */     }
/*      */ 
/*  426 */     getRS232Port().readUntilEmpty();
/*      */ 
/*  429 */     this.m_cmdClearErrorStatus.execute();
/*      */   }
/*      */ 
/*      */   private void initIRPort()
/*      */     throws IOException
/*      */   {
/*  439 */     getRS232Port().setDTR();
/*  440 */     getRS232Port().clearRTS();
/*  441 */     MedicalDevice.Util.sleepMS(1);
/*  442 */     getRS232Port().setBaudRate(7);
/*  443 */     getRS232Port().setRTS();
/*  444 */     getRS232Port().setDTR();
/*  445 */     MedicalDevice.Util.sleepMS(1);
/*      */   }
/*      */ 
/*      */   private void updateExtractResultsCmd()
/*      */   {
/*  452 */     if (this.m_numResults > 0)
/*      */     {
/*  454 */       String str = Integer.toString(this.m_numResults);
/*      */ 
/*  457 */       for (int i = str.length(); i < 3; i++) {
/*  458 */         str = '0' + str;
/*      */       }
/*      */ 
/*  461 */       i = 0;
/*      */ 
/*  463 */       for (int j = 0; j < str.length(); j++) {
/*  464 */         this.m_cmdExtractResults.m_command[(4 + j)] = str.charAt(j);
/*      */ 
/*  466 */         i = j;
/*      */       }
/*      */ 
/*  470 */       this.m_cmdExtractResults.m_command[(4 + i + 1)] = 13;
/*      */     }
/*      */     else {
/*  473 */       this.m_commandCollection.remove(this.m_cmdExtractResults);
/*      */     }
/*      */   }
/*      */ 
/*      */   static abstract interface ReplyDecoder
/*      */   {
/*      */     public abstract void decodeReply(RocheMeter.Command paramCommand)
/*      */       throws BadDeviceValueException;
/*      */   }
/*      */ 
/*      */   class SnapshotCreator extends MedicalDevice.SnapshotCreator
/*      */   {
/*      */     private static final int SNAPSHOT_BYTES = 270;
/*      */     private static final int SNAPCODE_METER_INFO = 1;
/*      */     private static final int SNAPCODE_NUM_RESULTS = 2;
/*      */     private static final int SNAPCODE_METER_DATA = 3;
/*      */ 
/*      */     SnapshotCreator()
/*      */     {
/* 1091 */       super(270);
/* 1092 */       RocheMeter.this.m_snapshotFirmwareCount = 0;
/* 1093 */       RocheMeter.this.m_snapshotSerialCount = 0;
/* 1094 */       RocheMeter.this.m_snapshotTimeCount = 0;
/*      */     }
/*      */ 
/*      */     void createSnapshotBody()
/*      */     {
/* 1104 */       RocheMeter.this.m_snapshot = new Snapshot(RocheMeter.this.m_snapshotFormatID, 1, RocheMeter.this.pad(RocheMeter.this.m_firmwareVersion), RocheMeter.this.pad(RocheMeter.this.m_serialNumber), RocheMeter.this.pad(RocheMeter.this.m_date + " " + RocheMeter.this.m_time));
/*      */ 
/* 1107 */       MedicalDevice.logInfo(this, "createSnapshot: creating snapshot");
/*      */ 
/* 1110 */       RocheMeter.this.m_snapshot.addElement(1, RocheMeter.this.pad(RocheMeter.this.m_modelNumber));
/* 1111 */       RocheMeter.this.m_snapshot.addElement(2, RocheMeter.this.pad(RocheMeter.this.m_numResults));
/* 1112 */       RocheMeter.this.m_snapshot.addElement(3, RocheMeter.this.m_meterResults);
/*      */     }
/*      */   }
/*      */ 
/*      */   private class Reader
/*      */   {
/*      */     private int m_serialPortNum;
/*      */     private final RocheMeter this$0;
/*      */ 
/*      */     private Reader(DeviceListener paramInt, int paramVector, Vector arg4)
/*      */     {
/*  927 */       this.this$0 = this$1;
/*  928 */       this$1.addDeviceListener(paramInt);
/*  929 */       this.m_serialPortNum = paramVector;
/*      */       Vector localVector;
/*  930 */       RocheMeter.access$1502(this$1, localVector);
/*      */     }
/*      */ 
/*      */     private final void acquireDataFromDevice()
/*      */       throws BadDeviceCommException, BadDeviceValueException, IOException
/*      */     {
/*  946 */       int i = 0;
/*      */ 
/*  948 */       Vector localVector = new Vector();
/*      */ 
/*  950 */       this.this$0.m_modelNumber = "";
/*  951 */       this.this$0.m_firmwareVersion = "";
/*  952 */       this.this$0.m_serialNumber = "";
/*  953 */       RocheMeter.access$102(this.this$0, "");
/*  954 */       RocheMeter.access$202(this.this$0, "");
/*  955 */       RocheMeter.access$902(this.this$0, new int[0]);
/*      */ 
/*  959 */       RocheMeter.access$702(this.this$0, 0);
/*  960 */       RocheMeter.access$1302(this.this$0, 0);
/*      */       RocheMeter.Command localCommand;
/*  962 */       for (int j = 0; j < this.this$0.m_commandCollection.size(); j++) {
/*  963 */         localCommand = (RocheMeter.Command)this.this$0.m_commandCollection.elementAt(j);
/*  964 */         if (localCommand != null) {
/*  965 */           RocheMeter.access$712(this.this$0, RocheMeter.Command.access$000(localCommand).length);
/*      */         }
/*      */       }
/*      */ 
/*  969 */       this.this$0.notifyDeviceUpdateProgress(0);
/*      */       try
/*      */       {
/*  978 */         this.this$0.setState(2);
/*      */         try
/*      */         {
/*  982 */           initCommunications(this.m_serialPortNum);
/*      */         } catch (IOException localIOException) {
/*  984 */           if (!this.this$0.isHaltRequested()) {
/*  985 */             MedicalDevice.logWarning(this, "acquireDataFromDevice: initCommunications failed with IOException; retrying... (exception = " + localIOException + ")");
/*      */ 
/*  988 */             initCommunications(this.m_serialPortNum);
/*      */           }
/*      */         }
/*      */ 
/*  992 */         this.this$0.setPhase(5);
/*      */ 
/*  995 */         for (i = 0; (i < this.this$0.m_commandCollection.size()) && (!this.this$0.isHaltRequested()); )
/*      */         {
/*  997 */           localCommand = (RocheMeter.Command)this.this$0.m_commandCollection.elementAt(i);
/*  998 */           if (localCommand != null)
/*      */           {
/* 1000 */             localCommand.execute();
/* 1001 */             if (!this.this$0.isHaltRequested())
/*      */             {
/* 1003 */               int[] arrayOfInt = localCommand.getRawData();
/* 1004 */               localVector.addElement(arrayOfInt);
/*      */             }
/*      */           }
/*  996 */           i++;
/*      */         }
/*      */ 
/*      */       }
/*      */       finally
/*      */       {
/*      */       }
/*      */ 
/* 1028 */       ret;
/*      */     }
/*      */ 
/*      */     private final void initCommunications(int paramInt)
/*      */       throws IOException, BadDeviceCommException, BadDeviceValueException
/*      */     {
/* 1046 */       this.this$0.initSerialPort(paramInt);
/*      */ 
/* 1049 */       this.this$0.initDevice();
/*      */     }
/*      */ 
/*      */     private final void endCommunications()
/*      */       throws IOException
/*      */     {
/* 1058 */       MedicalDevice.logInfo(this, "endCommunications: shutting down serial port.");
/* 1059 */       if (this.this$0.getRS232Port() != null)
/*      */       {
/* 1061 */         this.this$0.getRS232Port().close();
/* 1062 */         this.this$0.setRS232Port(null);
/*      */       }
/*      */     }
/*      */ 
/*      */     Reader(DeviceListener paramInt, int paramVector, Vector param1, RocheMeter.1 arg5)
/*      */     {
/*  910 */       this(paramInt, paramVector, param1);
/*      */     }
/*      */   }
/*      */ 
/*      */   class Command extends DeviceCommand
/*      */   {
/*      */     private static final int CRC_SEED = 110;
/*      */     private static final int WRITE_DELAY_MS = 3;
/*      */     private static final int RETRY_COUNT = 2;
/*      */     private int[] m_command;
/*      */     private int[] m_rawData;
/*      */     private RocheMeter.ReplyDecoder m_replyDecoder;
/*      */     private final RocheMeter this$0;
/*      */ 
/*      */     Command(int[] paramString, String paramInt, int paramReplyDecoder, RocheMeter.ReplyDecoder arg5)
/*      */     {
/*  525 */       super();
/*      */ 
/*  524 */       this.this$0 = this$1;
/*      */ 
/*  526 */       this.m_command = paramString;
/*  527 */       this.m_rawData = new int[paramReplyDecoder];
/*      */       Object localObject;
/*  528 */       this.m_replyDecoder = localObject;
/*      */     }
/*      */ 
/*      */     Command(int[] paramString, String paramReplyDecoder, RocheMeter.ReplyDecoder arg4)
/*      */     {
/*  540 */       this(paramString, paramReplyDecoder, 50, localReplyDecoder);
/*      */     }
/*      */ 
/*      */     public String toString()
/*      */     {
/*  551 */       return this.m_description + " (" + getCommand() + ")";
/*      */     }
/*      */ 
/*      */     public int hashCode()
/*      */     {
/*  561 */       return toString().hashCode();
/*      */     }
/*      */ 
/*      */     public boolean equals(Object paramObject)
/*      */     {
/*  574 */       if (this == paramObject) {
/*  575 */         return true;
/*      */       }
/*  577 */       if (paramObject == null) {
/*  578 */         return false;
/*      */       }
/*  580 */       if (getClass() != paramObject.getClass()) {
/*  581 */         return false;
/*      */       }
/*      */ 
/*  584 */       Command localCommand = (Command)paramObject;
/*      */ 
/*  586 */       return Arrays.equals(this.m_command, localCommand.m_command);
/*      */     }
/*      */ 
/*      */     void execute()
/*      */       throws BadDeviceCommException, BadDeviceValueException
/*      */     {
/*  605 */       int i = 0;
/*  606 */       int j = 0;
/*      */ 
/*  608 */       Contract.pre(this.this$0.getRS232Port() != null);
/*  609 */       Contract.pre(this.this$0.getRS232Port().isOpen());
/*      */ 
/*  611 */       MedicalDevice.m_lastCommandDescription = this.m_description;
/*      */       do
/*      */       {
/*      */         try
/*      */         {
/*  617 */           sendAndRead();
/*  618 */           j = 1;
/*      */         } catch (BadDeviceCommException localBadDeviceCommException) {
/*  620 */           i++;
/*  621 */           this.this$0.getRS232Port().readUntilEmpty();
/*  622 */           if (i <= 2) {
/*  623 */             MedicalDevice.logWarning(this, "execute: cmd failed with exception: " + localBadDeviceCommException + "; retrying (attempts = " + (i + 1) + ")");
/*      */ 
/*  625 */             this.this$0.setState(7);
/*      */           } else {
/*  627 */             MedicalDevice.logError(this, "cmd " + this + " failed after " + i + " attempts" + "; exception = " + localBadDeviceCommException);
/*      */ 
/*  633 */             throw new BadDeviceCommException("execute: cmd " + this + " failed after " + i + " attempts");
/*      */           }
/*      */ 
/*      */         }
/*      */         catch (BadDeviceValueException localBadDeviceValueException)
/*      */         {
/*  639 */           if (localBadDeviceValueException.getMessage().indexOf("Incorrect product code") == 0) {
/*  640 */             MedicalDevice.logError(this, localBadDeviceValueException.getMessage());
/*  641 */             throw localBadDeviceValueException;
/*      */           }
/*      */ 
/*  644 */           i++;
/*  645 */           this.this$0.getRS232Port().readUntilEmpty();
/*  646 */           if (i <= 2) {
/*  647 */             MedicalDevice.logWarning(this, "execute: cmd failed with exception: " + localBadDeviceValueException + "; retrying (attempts = " + (i + 1) + ")");
/*      */ 
/*  649 */             this.this$0.setState(7);
/*      */           } else {
/*  651 */             MedicalDevice.logError(this, "cmd " + this + " failed after " + i + " attempts" + "; exception = " + localBadDeviceValueException);
/*      */ 
/*  657 */             throw new BadDeviceValueException("execute: cmd " + this + " failed after " + i + " attempts");
/*      */           }
/*      */         }
/*      */       }
/*      */ 
/*  662 */       while ((j == 0) && (i <= 2));
/*      */     }
/*      */ 
/*      */     int[] getRawData()
/*      */     {
/*  671 */       return this.m_rawData;
/*      */     }
/*      */ 
/*      */     private String getCommand()
/*      */     {
/*  680 */       return "<" + MedicalDevice.Util.getHex(this.m_command) + ">";
/*      */     }
/*      */ 
/*      */     private void setRawData(int[] paramArrayOfInt)
/*      */     {
/*  689 */       this.m_rawData = paramArrayOfInt;
/*      */     }
/*      */ 
/*      */     private String readDeviceData()
/*      */       throws BadDeviceCommException, BadDeviceValueException
/*      */     {
/*  701 */       StringBuffer localStringBuffer = new StringBuffer();
/*  702 */       int i = 0;
/*      */       try
/*      */       {
/*  705 */         int j = 1;
/*      */         do
/*      */         {
/*  708 */           String str = readDeviceDataIO(j++);
/*  709 */           i = str.charAt(str.length() - 1) == '\006' ? 1 : 0;
/*      */ 
/*  711 */           RocheMeter.access$1312(this.this$0, str.length());
/*      */ 
/*  714 */           localStringBuffer.append(str.trim());
/*      */ 
/*  717 */           this.this$0.notifyDeviceUpdateProgress(this.this$0.m_bytesReadThusFar, this.this$0.m_totalBytesToRead);
/*      */ 
/*  719 */           if (i != 0)
/*      */             continue;
/*  721 */           this.this$0.getRS232Port().write('\006');
/*      */ 
/*  723 */           localStringBuffer.append('\r');
/*      */         }
/*  725 */         while (i == 0);
/*      */       } catch (BadDeviceCommException localBadDeviceCommException) {
/*  727 */         incIODelay("readDeviceData");
/*  728 */         throw localBadDeviceCommException;
/*      */       } catch (BadDeviceValueException localBadDeviceValueException) {
/*  730 */         incIODelay("readDeviceData");
/*  731 */         throw localBadDeviceValueException;
/*      */       } catch (IOException localIOException) {
/*  733 */         throw new BadDeviceCommException("readDeviceData: ERROR - an IOException  has occurred processing cmd " + this);
/*      */       }
/*      */ 
/*  736 */       return localStringBuffer.toString();
/*      */     }
/*      */ 
/*      */     private String readDeviceDataIO(int paramInt)
/*      */       throws BadDeviceCommException, BadDeviceValueException
/*      */     {
/*  752 */       Contract.pre(this.this$0.getRS232Port() != null);
/*  753 */       Contract.pre(this.this$0.getRS232Port().isOpen());
/*      */ 
/*  755 */       MedicalDevice.logInfo(this, "readDeviceDataIO: reading reply to cmd " + this);
/*      */ 
/*  757 */       String str = null;
/*      */       try
/*      */       {
/*  761 */         str = this.this$0.getRS232Port().readLine();
/*      */       } catch (IOException localIOException) {
/*  763 */         throw new BadDeviceCommException("readDeviceDataIO: ERROR - an IOException  has occurred processing cmd " + this);
/*      */       }
/*      */ 
/*  768 */       if (str.length() > 2) {
/*  769 */         verifyCRC(str);
/*      */       }
/*      */ 
/*  772 */       MedicalDevice.logInfo(this, "readDeviceDataIO: (" + paramInt + ") cmd " + this + " returned " + str.length() + " data bytes: " + "<" + str + ">");
/*      */ 
/*  775 */       return str;
/*      */     }
/*      */ 
/*      */     private int verifyCRC(String paramString)
/*      */       throws BadDeviceValueException
/*      */     {
/*  787 */       String str1 = paramString.trim();
/*  788 */       int i = MedicalDevice.Util.computeCRC8XOR(110, str1, 2, str1.length() - 4);
/*      */ 
/*  791 */       String str2 = str1.substring(str1.length() - 2);
/*  792 */       String str3 = MedicalDevice.Util.getHexCompact(i);
/*      */ 
/*  795 */       if (!str2.equalsIgnoreCase(str3)) {
/*  796 */         throw new BadDeviceValueException("verifyCRC: cmd " + this + " resulted in bad CRC value of " + str2 + " (expected " + str3 + ") " + "(byte data = " + "<" + MedicalDevice.Util.convertControlChars(paramString) + ">)");
/*      */       }
/*      */ 
/*  801 */       return i;
/*      */     }
/*      */ 
/*      */     private void incIODelay(String paramString)
/*      */     {
/*  810 */       RocheMeter.access$1402(this.this$0, Math.min(this.this$0.m_ioDelay + 5, 100));
/*  811 */       MedicalDevice.logInfo(this, paramString + ": increasing m_ioDelay to " + this.this$0.m_ioDelay);
/*  812 */       this.this$0.getRS232Port().setIODelay(this.this$0.m_ioDelay);
/*      */     }
/*      */ 
/*      */     private void sendCommand()
/*      */       throws BadDeviceCommException
/*      */     {
/*      */       try
/*      */       {
/*  822 */         sendCommandIO();
/*      */       } catch (BadDeviceCommException localBadDeviceCommException) {
/*  824 */         incIODelay("sendCommand");
/*  825 */         throw localBadDeviceCommException;
/*      */       }
/*      */     }
/*      */ 
/*      */     private void sendCommandIO()
/*      */       throws BadDeviceCommException
/*      */     {
/*  838 */       Contract.pre(this.this$0.getRS232Port() != null);
/*  839 */       Contract.pre(this.this$0.getRS232Port().isOpen());
/*      */ 
/*  841 */       MedicalDevice.logInfo(this, "sendCommandIO: sending cmd " + this);
/*      */       try
/*      */       {
/*  844 */         for (int i = 0; i < this.m_command.length; i++) {
/*  845 */           this.this$0.getRS232Port().write(this.m_command[i]);
/*      */ 
/*  847 */           MedicalDevice.Util.sleepMS(3);
/*      */ 
/*  849 */           int j = this.this$0.getRS232Port().read();
/*      */ 
/*  853 */           if ((i != this.m_command.length - 1) || 
/*  854 */             (this.m_command[i] != 13)) continue;
/*  855 */           if ((j != 6) && (j != 21)) {
/*  856 */             throw new BadDeviceCommException("sendCommandIO: ACK / NAK reply not found: " + MedicalDevice.Util.getHex(j) + " for cmd " + this);
/*      */           }
/*      */ 
/*  860 */           if (j == 21) {
/*  861 */             throw new BadDeviceCommException("sendCommandIO: NAK reply for cmd " + this);
/*      */           }
/*      */         }
/*      */ 
/*      */       }
/*      */       catch (IOException localIOException)
/*      */       {
/*  868 */         throw new BadDeviceCommException("sendCommandIO: ERROR - an IOException  has occurred processing cmd " + this);
/*      */       }
/*      */     }
/*      */ 
/*      */     private void sendAndRead()
/*      */       throws BadDeviceCommException, BadDeviceValueException
/*      */     {
/*  881 */       if (this.this$0.getState() != 7) {
/*  882 */         this.this$0.setState(4);
/*      */       }
/*  884 */       sendCommand();
/*      */ 
/*  887 */       if (!this.this$0.isHaltRequested())
/*      */       {
/*  889 */         this.this$0.setState(5);
/*      */ 
/*  893 */         setRawData(MedicalDevice.Util.makeIntArray(readDeviceData()));
/*      */       }
/*      */ 
/*  897 */       this.this$0.notifyDeviceUpdateProgress(this.this$0.m_bytesReadThusFar, this.this$0.m_totalBytesToRead);
/*      */ 
/*  899 */       if (!this.this$0.isHaltRequested())
/*  900 */         this.m_replyDecoder.decodeReply(this);
/*      */     }
/*      */   }
/*      */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.deviceportreader.RocheMeter
 * JD-Core Version:    0.6.0
 */