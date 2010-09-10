/*     */ package minimed.ddms.deviceportreader;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.text.ParsePosition;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.Set;
/*     */ import java.util.StringTokenizer;
/*     */ import java.util.Vector;
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ abstract class MedisenseBGKetone extends MedicalDevice
/*     */ {
/*     */   static final int METER_POWER_UP_DELAY_MS = 3000;
/*  49 */   private static final String FIELD_DELIMITERS = String.valueOf('\t') + String.valueOf('\r') + String.valueOf('\002') + String.valueOf('\027');
/*     */   private static final String CMD_GET_METER_ID = "ID";
/*     */   private static final String CMD_GET_METER_INFO = "GET_METER";
/*     */   private static final String CMD_GET_METER_DATA = "GET_EVENTS";
/*     */   private static final int MAX_RESULT_RECORDS = 450;
/*     */   private static final int LEN_ONE_RESULT_RECORD = 35;
/*     */   private static final int LEN_END_OF_DATA_RESULT = 18;
/*     */   private static final int LEN_GET_METER_ID = 45;
/*     */   private static final int LEN_GET_METER_INFO = 55;
/*     */   private static final int LEN_GET_METER_DATA = 15769;
/*     */   private static final int IO_DELAY_MS = 50;
/*     */   private String m_meterData;
/*     */   private String m_meterInfo;
/*     */   private Set m_productCodes;
/*     */   private String m_realTimeClock;
/*     */   private Command m_cmdGetMeterID;
/*     */   private Command m_cmdGetMeterInfo;
/*     */   private Command m_cmdGetMeterData;
/*     */   private int m_totalBytesToRead;
/*     */   private int m_bytesReadThusFar;
/*     */ 
/*     */   MedisenseBGKetone(String paramString1, int paramInt1, int paramInt2, String paramString2)
/*     */   {
/* 139 */     this.m_description = paramString1;
/* 140 */     this.m_snapshotFormatID = paramInt1;
/* 141 */     this.m_deviceClassID = paramInt2;
/* 142 */     setProductCodes(new HashSet());
/* 143 */     getProductCodes().add(paramString2);
/* 144 */     logInfo(this, "creating interface to the '" + this.m_description + "', package version " + getPackageVersion());
/*     */ 
/* 147 */     this.m_firmwareVersion = "";
/* 148 */     this.m_serialNumber = "123456789-123456789-123456789-12";
/* 149 */     this.m_snapshotCreator = new SnapshotCreator();
/*     */ 
/* 155 */     this.m_cmdGetMeterID = new Command("ID", "Read Meter ID", 45);
/* 156 */     this.m_cmdGetMeterInfo = new Command("GET_METER", "Read Meter Info", 55);
/*     */ 
/* 158 */     this.m_cmdGetMeterData = new Command("GET_EVENTS", "Read Meter Data", 15769);
/*     */   }
/*     */ 
/*     */   public void readData(DeviceListener paramDeviceListener, int paramInt, String paramString)
/*     */     throws BadDeviceCommException, BadDeviceValueException, IOException
/*     */   {
/* 182 */     logInfo(this, "readData: starting reader...");
/* 183 */     setHaltRequested(false);
/*     */ 
/* 186 */     Vector localVector = createCommandList();
/*     */ 
/* 189 */     Reader localReader = new Reader(paramDeviceListener, paramInt, localVector, null);
/* 190 */     localReader.acquireDataFromDevice();
/*     */   }
/*     */ 
/*     */   int getDeviceType()
/*     */   {
/* 199 */     return 3;
/*     */   }
/*     */ 
/*     */   void findDevice(DeviceListener paramDeviceListener)
/*     */     throws BadDeviceCommException, IOException
/*     */   {
/*     */     try
/*     */     {
/* 211 */       this.m_cmdGetMeterID.execute();
/*     */     }
/*     */     catch (BadDeviceValueException localBadDeviceValueException) {
/* 214 */       throw new BadDeviceCommException("Got reply, but format is bad.");
/*     */     }
/*     */   }
/*     */ 
/*     */   void initSerialPort(int paramInt)
/*     */     throws IOException
/*     */   {
/* 226 */     beginSerialPort(paramInt);
/* 227 */     setRS232Port(new SerialPort(paramInt, 7));
/* 228 */     getRS232Port().readUntilEmpty();
/* 229 */     getRS232Port().setIODelay(50);
/*     */   }
/*     */ 
/*     */   void shutDownSerialPort()
/*     */     throws IOException
/*     */   {
/* 238 */     if (getRS232Port() != null)
/*     */     {
/* 240 */       getRS232Port().close();
/* 241 */       setRS232Port(null);
/*     */     }
/*     */   }
/*     */ 
/*     */   void setProductCodes(Set paramSet)
/*     */   {
/* 251 */     this.m_productCodes = paramSet;
/*     */   }
/*     */ 
/*     */   Set getProductCodes()
/*     */   {
/* 260 */     return this.m_productCodes;
/*     */   }
/*     */ 
/*     */   private void initDevice()
/*     */     throws IOException
/*     */   {
/* 269 */     setPhase(4);
/*     */ 
/* 272 */     MedicalDevice.Util.sleepMS(3000);
/*     */ 
/* 279 */     getRS232Port().write('\006');
/* 280 */     getRS232Port().readLine();
/* 281 */     getRS232Port().write('\006');
/* 282 */     getRS232Port().read();
/*     */   }
/*     */ 
/*     */   private void decodeReply(Command paramCommand)
/*     */     throws BadDeviceValueException
/*     */   {
/* 296 */     if (paramCommand.m_command.equals(this.m_cmdGetMeterInfo.m_command))
/*     */     {
/* 298 */       this.m_realTimeClock = MedicalDevice.Util.makeString(paramCommand.m_rawData);
/* 299 */       this.m_meterInfo = this.m_realTimeClock;
/* 300 */       decodeCurrentTimeStampAndSerialNumber();
/* 301 */       logInfo(this, "decodeReply: device clock reads '" + getClock() + "'");
/* 302 */       logInfo(this, "decodeReply: serial number is '" + this.m_serialNumber + "'");
/*     */     }
/*     */     else
/*     */     {
/*     */       String str;
/* 303 */       if (paramCommand.m_command.equals(this.m_cmdGetMeterData.m_command)) {
/* 304 */         str = MedicalDevice.Util.makeString(paramCommand.m_rawData);
/* 305 */         this.m_meterData = str.trim();
/* 306 */         logInfo(this, "decodeReply: reply is meter data records: '" + this.m_meterData + "'");
/* 307 */       } else if (paramCommand.m_command.equals(this.m_cmdGetMeterID.m_command)) {
/* 308 */         logInfo(this, "decodeReply: reply is meter ID: '" + MedicalDevice.Util.makeString(paramCommand.m_rawData) + "'");
/*     */ 
/* 310 */         str = MedicalDevice.Util.makeString(paramCommand.m_rawData);
/* 311 */         if (!verifyProductCode(str)) {
/* 312 */           throw new BadDeviceValueException("Incorrect product code; expected '" + getProductCodes() + "' (" + this.m_description + "), but found '" + str + "'.  " + "Make sure correct meter model has been specified.");
/*     */         }
/*     */       }
/*     */       else
/*     */       {
/* 317 */         throw new BadDeviceValueException("Command '" + paramCommand.m_command + " ' is unrecognized");
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private Vector createCommandList()
/*     */   {
/* 328 */     Vector localVector = new Vector();
/*     */ 
/* 330 */     localVector.addElement(this.m_cmdGetMeterID);
/* 331 */     localVector.addElement(this.m_cmdGetMeterInfo);
/* 332 */     localVector.addElement(this.m_cmdGetMeterData);
/* 333 */     return localVector;
/*     */   }
/*     */ 
/*     */   private Date createTimestamp(String paramString)
/*     */     throws BadDeviceValueException
/*     */   {
/* 346 */     String str = "yyyyMMddHH:mm";
/*     */ 
/* 348 */     Contract.pre(paramString != null);
/* 349 */     Contract.pre(paramString.length() == str.length());
/*     */ 
/* 351 */     SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(str);
/* 352 */     Date localDate = localSimpleDateFormat.parse(paramString, new ParsePosition(0));
/*     */ 
/* 354 */     if (localDate == null)
/*     */     {
/* 356 */       throw new BadDeviceValueException("Bad device time string (dataF is null) '" + paramString + "' received");
/*     */     }
/*     */ 
/* 360 */     return localDate;
/*     */   }
/*     */ 
/*     */   private void decodeCurrentTimeStampAndSerialNumber()
/*     */     throws BadDeviceValueException
/*     */   {
/* 376 */     StringTokenizer localStringTokenizer = new StringTokenizer(this.m_realTimeClock, FIELD_DELIMITERS);
/*     */     try
/*     */     {
/* 380 */       String str1 = localStringTokenizer.nextToken();
/*     */ 
/* 382 */       if (str1.length() > "YYYYMMDD".length())
/*     */       {
/* 384 */         str1 = str1.substring(str1.length() - "YYYYMMDD".length());
/*     */       }
/*     */ 
/* 387 */       String str2 = localStringTokenizer.nextToken();
/*     */ 
/* 390 */       this.m_timeStamp = createTimestamp(str1 + str2);
/* 391 */       logInfo(this, "decodeCurrentTimeStampAndSerialNumber: current time stamp for device is " + this.m_timeStamp);
/*     */ 
/* 396 */       this.m_serialNumber = localStringTokenizer.nextToken().trim();
/* 397 */       logInfo(this, "decodeCurrentTimeStampAndSerialNumber: serial number is " + this.m_serialNumber);
/*     */     }
/*     */     catch (NoSuchElementException localNoSuchElementException) {
/* 400 */       throw new BadDeviceValueException("Bad device timestamp reply '" + this.m_realTimeClock + '\'');
/*     */     }
/*     */   }
/*     */ 
/*     */   private boolean verifyProductCode(String paramString)
/*     */   {
/* 413 */     Contract.preNonNull(paramString);
/* 414 */     int i = 0;
/* 415 */     Iterator localIterator = getProductCodes().iterator();
/*     */ 
/* 417 */     while ((localIterator.hasNext()) && (i == 0)) {
/* 418 */       String str = (String)localIterator.next();
/* 419 */       if (paramString.indexOf(str) > 0) {
/* 420 */         i = 1;
/*     */       }
/*     */     }
/* 423 */     return i;
/*     */   }
/*     */ 
/*     */   private class SnapshotCreator extends MedicalDevice.SnapshotCreator
/*     */   {
/*     */     static final int SNAPSHOT_BYTES = 80;
/*     */     static final int SNAPCODE_METER_INFO = 1;
/*     */     static final int SNAPCODE_METER_DATA = 2;
/*     */ 
/*     */     SnapshotCreator()
/*     */     {
/* 967 */       super(80);
/* 968 */       MedisenseBGKetone.this.m_snapshotFirmwareCount = 0;
/* 969 */       MedisenseBGKetone.this.m_snapshotSerialCount = 0;
/* 970 */       MedisenseBGKetone.this.m_snapshotTimeCount = 0;
/*     */     }
/*     */ 
/*     */     void createSnapshotBody()
/*     */     {
/* 980 */       String str1 = "";
/* 981 */       String str2 = "";
/* 982 */       String str3 = "";
/* 983 */       MedisenseBGKetone.this.m_snapshot = new Snapshot(MedisenseBGKetone.this.m_snapshotFormatID, 1, str1, str2, str3);
/*     */ 
/* 986 */       MedicalDevice.logInfo(this, "createSnapshot: creating snapshot");
/*     */ 
/* 989 */       MedisenseBGKetone.this.m_snapshot.addElement(1, MedisenseBGKetone.this.m_meterInfo);
/*     */ 
/* 992 */       MedisenseBGKetone.this.m_snapshot.addElement(2, MedisenseBGKetone.this.m_meterData);
/*     */     }
/*     */   }
/*     */ 
/*     */   private class Reader
/*     */   {
/*     */     private int m_serialPortNum;
/*     */     private Vector m_commandCollection;
/*     */     private final MedisenseBGKetone this$0;
/*     */ 
/*     */     private Reader(DeviceListener paramInt, int paramVector, Vector arg4)
/*     */     {
/* 808 */       this.this$0 = this$1;
/* 809 */       this$1.addDeviceListener(paramInt);
/* 810 */       this.m_serialPortNum = paramVector;
/*     */       Object localObject;
/* 811 */       this.m_commandCollection = localObject;
/*     */     }
/*     */ 
/*     */     private void acquireDataFromDevice()
/*     */       throws BadDeviceCommException, BadDeviceValueException, IOException
/*     */     {
/* 827 */       int i = 0;
/*     */ 
/* 829 */       Vector localVector = new Vector();
/*     */ 
/* 833 */       this.this$0.m_firmwareVersion = "";
/* 834 */       this.this$0.m_serialNumber = "";
/* 835 */       MedisenseBGKetone.access$702(this.this$0, "");
/*     */ 
/* 839 */       MedisenseBGKetone.access$502(this.this$0, 0);
/* 840 */       MedisenseBGKetone.access$402(this.this$0, 0);
/*     */       MedisenseBGKetone.Command localCommand;
/* 842 */       for (int j = 0; j < this.m_commandCollection.size(); j++) {
/* 843 */         localCommand = (MedisenseBGKetone.Command)this.m_commandCollection.elementAt(j);
/* 844 */         if (localCommand != null) {
/* 845 */           MedisenseBGKetone.access$512(this.this$0, MedisenseBGKetone.Command.access$300(localCommand).length);
/*     */         }
/*     */       }
/*     */ 
/* 849 */       this.this$0.notifyDeviceUpdateProgress(0);
/*     */       try
/*     */       {
/* 858 */         this.this$0.setState(2);
/*     */         try
/*     */         {
/* 862 */           initCommunications(this.m_serialPortNum);
/*     */         } catch (IOException localIOException) {
/* 864 */           if (!this.this$0.isHaltRequested()) {
/* 865 */             MedicalDevice.logWarning(this, "acquireDataFromDevice: initCommunications failed with IOException; retrying... (exception = " + localIOException + ")");
/*     */ 
/* 868 */             initCommunications(this.m_serialPortNum);
/*     */           }
/*     */         }
/*     */ 
/* 872 */         this.this$0.setPhase(5);
/*     */ 
/* 875 */         for (i = 0; (i < this.m_commandCollection.size()) && (!this.this$0.isHaltRequested()); )
/*     */         {
/* 877 */           localCommand = (MedisenseBGKetone.Command)this.m_commandCollection.elementAt(i);
/* 878 */           if (localCommand != null)
/*     */           {
/* 880 */             localCommand.execute();
/* 881 */             if (!this.this$0.isHaltRequested())
/*     */             {
/* 883 */               int[] arrayOfInt = MedisenseBGKetone.Command.access$800(localCommand);
/* 884 */               localVector.addElement(arrayOfInt);
/*     */             }
/*     */           }
/* 876 */           i++;
/*     */         }
/*     */ 
/*     */       }
/*     */       finally
/*     */       {
/*     */       }
/*     */ 
/* 908 */       ret;
/*     */     }
/*     */ 
/*     */     private void initCommunications(int paramInt)
/*     */       throws IOException
/*     */     {
/* 923 */       this.this$0.initSerialPort(paramInt);
/*     */ 
/* 926 */       this.this$0.initDevice();
/*     */     }
/*     */ 
/*     */     private void endCommunications()
/*     */       throws IOException
/*     */     {
/* 935 */       MedicalDevice.logInfo(this, "endCommunications: shutting down serial port.");
/* 936 */       if (this.this$0.getRS232Port() != null)
/*     */       {
/* 938 */         this.this$0.getRS232Port().close();
/* 939 */         this.this$0.setRS232Port(null);
/*     */       }
/*     */     }
/*     */ 
/*     */     Reader(DeviceListener paramInt, int paramVector, Vector param1, MedisenseBGKetone.1 arg5)
/*     */     {
/* 791 */       this(paramInt, paramVector, param1);
/*     */     }
/*     */   }
/*     */ 
/*     */   private class Command extends DeviceCommand
/*     */   {
/*     */     static final int RETRY_COUNT = 2;
/*     */     static final int IO_DELAY_READ_RETRY_MS = 10000;
/*     */     private String m_command;
/*     */     private int[] m_rawData;
/*     */     private int[] m_packet;
/*     */ 
/*     */     Command(String paramString1, String paramInt, int arg4)
/*     */     {
/* 463 */       super();
/* 464 */       this.m_command = paramString1;
/*     */       int i;
/* 465 */       this.m_rawData = new int[i];
/*     */     }
/*     */ 
/*     */     void execute()
/*     */       throws BadDeviceCommException, BadDeviceValueException
/*     */     {
/* 484 */       int i = 0;
/* 485 */       int j = 0;
/*     */ 
/* 487 */       Contract.pre(MedisenseBGKetone.this.getRS232Port() != null);
/* 488 */       Contract.pre(MedisenseBGKetone.this.getRS232Port().isOpen());
/*     */ 
/* 490 */       MedicalDevice.m_lastCommandDescription = this.m_description;
/*     */       do
/*     */       {
/*     */         try
/*     */         {
/* 496 */           sendAndRead();
/* 497 */           j = 1;
/*     */         } catch (BadDeviceCommException localBadDeviceCommException) {
/* 499 */           i++;
/* 500 */           MedisenseBGKetone.this.getRS232Port().readUntilEmpty();
/* 501 */           if (i <= 2) {
/* 502 */             MedicalDevice.logWarning(this, "execute: cmd failed with exception: " + localBadDeviceCommException + "; retrying (attempts = " + (i + 1) + ")");
/*     */ 
/* 504 */             MedisenseBGKetone.this.setState(7);
/*     */           } else {
/* 506 */             MedicalDevice.logError(this, "cmd " + this.m_command + " (" + this.m_description + ") failed after " + i + " attempts" + "; exception = " + localBadDeviceCommException);
/*     */ 
/* 513 */             throw new BadDeviceCommException("execute: cmd " + this.m_command + " (" + this.m_description + ") failed after " + i + " attempts");
/*     */           }
/*     */ 
/*     */         }
/*     */         catch (BadDeviceValueException localBadDeviceValueException)
/*     */         {
/* 519 */           if (localBadDeviceValueException.getMessage().indexOf("Incorrect product code") == 0) {
/* 520 */             MedicalDevice.logError(this, localBadDeviceValueException.getMessage());
/* 521 */             throw localBadDeviceValueException;
/*     */           }
/*     */ 
/* 524 */           i++;
/* 525 */           MedisenseBGKetone.this.getRS232Port().readUntilEmpty();
/* 526 */           if (i <= 2) {
/* 527 */             MedicalDevice.logWarning(this, "execute: cmd failed with exception: " + localBadDeviceValueException + "; retrying (attempts = " + (i + 1) + ")");
/*     */ 
/* 529 */             MedisenseBGKetone.this.setState(7);
/*     */           } else {
/* 531 */             MedicalDevice.logError(this, "cmd " + this.m_command + " (" + this.m_description + ") failed after " + i + " attempts" + "; exception = " + localBadDeviceValueException);
/*     */ 
/* 538 */             throw new BadDeviceValueException("execute: cmd " + this.m_command + " (" + this.m_description + ") failed after " + i + " attempts");
/*     */           }
/*     */         }
/*     */         catch (IOException localIOException)
/*     */         {
/* 543 */           i++;
/* 544 */           MedisenseBGKetone.this.getRS232Port().readUntilEmpty();
/* 545 */           if (i <= 2) {
/* 546 */             MedicalDevice.logWarning(this, "execute: cmd failed with exception: " + localIOException + "; retrying (attempts = " + (i + 1) + ")");
/*     */ 
/* 548 */             MedisenseBGKetone.this.setState(7);
/*     */           } else {
/* 550 */             MedicalDevice.logError(this, "cmd " + this.m_command + " (" + this.m_description + ") failed after " + i + " attempts" + "; exception = " + localIOException);
/*     */ 
/* 557 */             throw new BadDeviceCommException("execute: cmd " + this.m_command + " (" + this.m_description + ") failed after " + i + " attempts");
/*     */           }
/*     */         }
/*     */       }
/*     */ 
/* 562 */       while ((j == 0) && (i <= 2));
/*     */     }
/*     */ 
/*     */     private int[] getRawData()
/*     */     {
/* 571 */       return this.m_rawData;
/*     */     }
/*     */ 
/*     */     private String readDeviceData() throws BadDeviceCommException, BadDeviceValueException, IOException {
/* 589 */       StringBuffer localStringBuffer = new StringBuffer();
/*     */ 
/* 591 */       Contract.pre(MedisenseBGKetone.this.getRS232Port() != null);
/* 592 */       Contract.pre(MedisenseBGKetone.this.getRS232Port().isOpen());
/*     */ 
/* 594 */       MedicalDevice.logInfo(this, "readDeviceData: reading reply to cmd " + this.m_command + " (" + this.m_description + ")");
/*     */       String str;
/*     */       do {
/* 600 */         MedisenseBGKetone.this.getRS232Port().write('\006');
/*     */ 
/* 603 */         str = MedisenseBGKetone.this.getRS232Port().readLine();
/*     */ 
/* 605 */         if (str.length() <= 1)
/*     */           continue;
/* 607 */         if (!MedicalDevice.Util.isCRC8E1381Valid(str)) {
/* 608 */           MedicalDevice.logError(this, "readDeviceData: ERROR - cmd " + this.m_command + " resulted in bad CRC reply of " + MedicalDevice.Util.convertControlChars(str));
/*     */ 
/* 610 */           throw new BadDeviceValueException("readDeviceData: ERROR - cmd " + this.m_command + " resulted in bad CRC reply of " + MedicalDevice.Util.convertControlChars(str));
/*     */         }
/*     */ 
/* 615 */         MedicalDevice.logInfo(this, "readDeviceData: cmd " + this.m_command + " (" + this.m_description + ") returned " + str.length() + " data bytes, with reply = <" + str + ">");
/*     */ 
/* 618 */         localStringBuffer.append(str);
/*     */ 
/* 621 */         MedisenseBGKetone.access$412(MedisenseBGKetone.this, str.length());
/* 622 */         MedisenseBGKetone.this.notifyDeviceUpdateProgress(MedisenseBGKetone.this.m_bytesReadThusFar, MedisenseBGKetone.this.m_totalBytesToRead);
/*     */       }
/* 624 */       while ((str.length() > 0) && (str.charAt(0) != '\004'));
/*     */ 
/* 626 */       return localStringBuffer.toString();
/*     */     }
/*     */ 
/*     */     private void sendCommand()
/*     */       throws BadDeviceCommException, IOException
/*     */     {
/* 641 */       Contract.pre(MedisenseBGKetone.this.getRS232Port() != null);
/* 642 */       Contract.pre(MedisenseBGKetone.this.getRS232Port().isOpen());
/*     */ 
/* 644 */       MedicalDevice.logInfo(this, "sendCommand: sending cmd " + this.m_command + " (" + this.m_description + ")");
/*     */ 
/* 648 */       MedisenseBGKetone.this.getRS232Port().write('\005');
/*     */ 
/* 651 */       readByte(6);
/*     */ 
/* 654 */       this.m_packet = buildPacket();
/*     */ 
/* 657 */       MedisenseBGKetone.this.getRS232Port().write(this.m_packet);
/*     */ 
/* 660 */       readByte(6);
/*     */ 
/* 663 */       MedisenseBGKetone.this.getRS232Port().write('\004');
/*     */ 
/* 666 */       readByte(5);
/*     */     }
/*     */ 
/*     */     private void sendAndRead()
/*     */       throws BadDeviceCommException, BadDeviceValueException, IOException
/*     */     {
/* 680 */       StringBuffer localStringBuffer = new StringBuffer();
/* 681 */       String str = "";
/*     */ 
/* 686 */       if (MedisenseBGKetone.this.getState() != 7) {
/* 687 */         MedisenseBGKetone.this.setState(4);
/*     */       }
/* 689 */       sendCommand();
/*     */ 
/* 691 */       if (this.m_rawData.length > 0)
/*     */       {
/* 693 */         if (!MedisenseBGKetone.this.isHaltRequested())
/*     */         {
/* 695 */           MedisenseBGKetone.this.setState(5);
/* 696 */           str = readDeviceData();
/* 697 */           localStringBuffer.append(str);
/*     */         }
/*     */ 
/* 701 */         if ((str.length() == 0) && (!MedisenseBGKetone.this.isHaltRequested()))
/*     */         {
/* 703 */           MedicalDevice.Util.sleepMS(10000);
/* 704 */           str = readDeviceData();
/* 705 */           localStringBuffer.append(str);
/*     */         }
/*     */ 
/* 709 */         while ((str.length() > 0) && (!MedisenseBGKetone.this.isHaltRequested())) {
/* 710 */           if (MedisenseBGKetone.this.isHaltRequested())
/*     */             continue;
/* 712 */           str = readDeviceData();
/* 713 */           localStringBuffer.append(str);
/*     */         }
/*     */ 
/* 717 */         this.m_rawData = MedicalDevice.Util.makeIntArray(localStringBuffer.toString());
/*     */ 
/* 719 */         if (!MedisenseBGKetone.this.isHaltRequested())
/* 720 */           MedisenseBGKetone.this.decodeReply(this);
/*     */       }
/*     */     }
/*     */ 
/*     */     private int[] buildPacket()
/*     */     {
/* 734 */       int i = 0;
/*     */ 
/* 736 */       Contract.pre(this.m_command.length() > 0);
/*     */ 
/* 738 */       int[] arrayOfInt = new int[this.m_command.length() + 7];
/*     */ 
/* 741 */       arrayOfInt[(i++)] = 2;
/*     */ 
/* 744 */       arrayOfInt[(i++)] = 49;
/*     */ 
/* 748 */       for (int j = i; j < 2 + this.m_command.length(); j++) {
/* 749 */         arrayOfInt[(i++)] = this.m_command.charAt(j - 2);
/*     */       }
/*     */ 
/* 753 */       arrayOfInt[(i++)] = 3;
/*     */ 
/* 756 */       j = MedicalDevice.Util.computeCRC8E1381(arrayOfInt);
/* 757 */       arrayOfInt[(i++)] = MedicalDevice.Util.convertHexToASCII(MedicalDevice.Util.getHighNibble(j));
/* 758 */       arrayOfInt[(i++)] = MedicalDevice.Util.convertHexToASCII(MedicalDevice.Util.getLowNibble(j));
/*     */ 
/* 761 */       arrayOfInt[(i++)] = 13;
/* 762 */       arrayOfInt[(i++)] = 10;
/* 763 */       return arrayOfInt;
/*     */     }
/*     */ 
/*     */     private void readByte(int paramInt)
/*     */       throws BadDeviceCommException, IOException
/*     */     {
/* 776 */       int i = MedisenseBGKetone.this.getRS232Port().read();
/*     */ 
/* 778 */       if (i != paramInt)
/* 779 */         throw new BadDeviceCommException("readAckByte: reply " + i + " does not match expected reply of " + paramInt);
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.deviceportreader.MedisenseBGKetone
 * JD-Core Version:    0.6.0
 */