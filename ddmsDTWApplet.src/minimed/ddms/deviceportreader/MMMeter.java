/*     */ package minimed.ddms.deviceportreader;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.text.ParsePosition;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.StringTokenizer;
/*     */ import java.util.Vector;
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ abstract class MMMeter extends MedicalDevice
/*     */ {
/*     */   public static final int SNAPSHOT_FORMAT_ID = 181;
/*     */   private static final int CMD_READ_CLOCK = 160;
/*     */   private static final int CMD_READ_SETTINGS = 162;
/*     */   private static final int CMD_READ_GLUCOSE_DATA = 128;
/*     */   private static final int LEN_READ_CLOCK = 28;
/*     */   private static final int LEN_READ_SETTINGS = 72;
/*     */   private static final int LEN_READ_GLUCOSE_DATA = 44;
/*     */   private static final int IO_DELAY_MS = 20;
/*     */   private static final int READ_TO_MS = 2500;
/*     */   private static final String FIELD_DELIMITERS = "[,\r\n";
/*     */   private String m_currentSettings;
/*     */   private Command m_commandReadClock;
/*     */   private Command m_commandReadSettings;
/*     */   private Command m_commandReadGlucoseData0;
/*     */   private Command m_commandReadGlucoseData1;
/*     */   private Command m_commandReadGlucoseData2;
/*     */   private Command m_commandReadGlucoseData3;
/*     */   private Command m_commandReadGlucoseData4;
/*     */   private Command m_commandReadGlucoseData5;
/*     */   private Command m_commandReadGlucoseData6;
/*     */   private Command m_commandReadGlucoseData7;
/*     */   private Command m_commandReadGlucoseData8;
/*     */   private Command m_commandReadGlucoseData9;
/*     */   private int m_totalBytesToRead;
/*     */   private int m_bytesReadThusFar;
/*     */   private int m_baudRate;
/*     */   private int m_parity;
/*     */   private String m_realTimeClock;
/*     */ 
/*     */   MMMeter(int paramInt1, int paramInt2, int paramInt3, String paramString)
/*     */   {
/*  97 */     this.m_description = paramString;
/*  98 */     this.m_deviceClassID = paramInt1;
/*  99 */     this.m_baudRate = paramInt2;
/* 100 */     this.m_parity = paramInt3;
/*     */ 
/* 102 */     logInfo(this, "creating interface to the '" + this.m_description + "', package version " + getPackageVersion());
/*     */ 
/* 105 */     this.m_firmwareVersion = "";
/* 106 */     this.m_serialNumber = "";
/* 107 */     this.m_snapshotFormatID = 181;
/* 108 */     this.m_snapshotCreator = new SnapshotCreator();
/*     */ 
/* 111 */     this.m_commandReadClock = new Command(160, "Read Clock", 28, null);
/* 112 */     this.m_commandReadSettings = new Command(162, "Read Current Settings", 72, null);
/*     */ 
/* 114 */     this.m_commandReadGlucoseData0 = new Command(128, "Read Glucose Data (block 0)", 44, null);
/*     */ 
/* 116 */     this.m_commandReadGlucoseData1 = new Command(129, "Read Glucose Data (block 1)", 44, null);
/*     */ 
/* 118 */     this.m_commandReadGlucoseData2 = new Command(130, "Read Glucose Data (block 2)", 44, null);
/*     */ 
/* 120 */     this.m_commandReadGlucoseData3 = new Command(131, "Read Glucose Data (block 3)", 44, null);
/*     */ 
/* 122 */     this.m_commandReadGlucoseData4 = new Command(132, "Read Glucose Data (block 4)", 44, null);
/*     */ 
/* 124 */     this.m_commandReadGlucoseData5 = new Command(133, "Read Glucose Data (block 5)", 44, null);
/*     */ 
/* 126 */     this.m_commandReadGlucoseData6 = new Command(134, "Read Glucose Data (block 6)", 44, null);
/*     */ 
/* 128 */     this.m_commandReadGlucoseData7 = new Command(135, "Read Glucose Data (block 7)", 44, null);
/*     */ 
/* 130 */     this.m_commandReadGlucoseData8 = new Command(136, "Read Glucose Data (block 8)", 44, null);
/*     */ 
/* 132 */     this.m_commandReadGlucoseData9 = new Command(137, "Read Glucose Data (block 9)", 44, null);
/*     */   }
/*     */ 
/*     */   public void readData(DeviceListener paramDeviceListener, int paramInt, String paramString)
/*     */     throws BadDeviceCommException, BadDeviceValueException, IOException
/*     */   {
/* 155 */     logInfo(this, "readData: starting reader...");
/* 156 */     setHaltRequested(false);
/*     */ 
/* 159 */     Vector localVector = createCommandList();
/*     */ 
/* 162 */     Reader localReader = new Reader(paramDeviceListener, paramInt, localVector, null);
/* 163 */     localReader.acquireDataFromDevice();
/*     */   }
/*     */ 
/*     */   public void decodeSerialNumber()
/*     */     throws BadDeviceValueException
/*     */   {
/* 172 */     Contract.preNonNull(this.m_currentSettings);
/*     */     try
/*     */     {
/* 182 */       StringTokenizer localStringTokenizer = new StringTokenizer(this.m_currentSettings, "[,\r\n");
/*     */ 
/* 185 */       localStringTokenizer.nextToken();
/*     */ 
/* 188 */       localStringTokenizer.nextToken();
/*     */ 
/* 191 */       localStringTokenizer.nextToken();
/*     */ 
/* 194 */       localStringTokenizer.nextToken();
/*     */ 
/* 197 */       localStringTokenizer.nextToken();
/*     */ 
/* 200 */       localStringTokenizer.nextToken();
/*     */ 
/* 203 */       localStringTokenizer.nextToken();
/*     */ 
/* 206 */       localStringTokenizer.nextToken();
/*     */ 
/* 209 */       localStringTokenizer.nextToken();
/*     */ 
/* 212 */       localStringTokenizer.nextToken();
/*     */ 
/* 215 */       localStringTokenizer.nextToken();
/*     */ 
/* 218 */       localStringTokenizer.nextToken();
/*     */ 
/* 221 */       this.m_serialNumber = localStringTokenizer.nextToken();
/*     */     } catch (NoSuchElementException localNoSuchElementException) {
/* 223 */       this.m_serialNumber = "";
/* 224 */       throw new BadDeviceValueException("Bad serial number (current setting) string '" + this.m_currentSettings + "' received");
/*     */     }
/*     */   }
/*     */ 
/*     */   void initSerialPort(int paramInt)
/*     */     throws IOException
/*     */   {
/* 237 */     beginSerialPort(paramInt);
/* 238 */     setRS232Port(new SerialPort(paramInt, this.m_baudRate, this.m_parity));
/* 239 */     getRS232Port().readUntilEmpty();
/* 240 */     getRS232Port().setIODelay(20);
/* 241 */     getRS232Port().setReadTimeOut(2500);
/*     */   }
/*     */ 
/*     */   void shutDownSerialPort()
/*     */     throws IOException
/*     */   {
/* 250 */     if (getRS232Port() != null)
/*     */     {
/* 252 */       getRS232Port().close();
/* 253 */       setRS232Port(null);
/*     */     }
/*     */   }
/*     */ 
/*     */   int getDeviceType()
/*     */   {
/* 263 */     return 3;
/*     */   }
/*     */ 
/*     */   void findDevice(DeviceListener paramDeviceListener)
/*     */     throws BadDeviceCommException, IOException
/*     */   {
/*     */     try
/*     */     {
/* 275 */       this.m_commandReadClock.execute();
/*     */     }
/*     */     catch (BadDeviceValueException localBadDeviceValueException) {
/* 278 */       throw new BadDeviceCommException("Got reply, but format is bad.");
/*     */     }
/*     */   }
/*     */ 
/*     */   private final void initDevice()
/*     */     throws BadDeviceCommException, BadDeviceValueException
/*     */   {
/* 291 */     Contract.pre(getRS232Port() != null);
/* 292 */     Contract.pre(getRS232Port().isOpen());
/*     */ 
/* 294 */     setPhase(4);
/*     */   }
/*     */ 
/*     */   private final Vector createCommandList()
/*     */   {
/* 303 */     Vector localVector = new Vector();
/*     */ 
/* 306 */     localVector.addElement(this.m_commandReadClock);
/* 307 */     localVector.addElement(this.m_commandReadSettings);
/* 308 */     localVector.addElement(this.m_commandReadGlucoseData0);
/* 309 */     localVector.addElement(this.m_commandReadGlucoseData1);
/* 310 */     localVector.addElement(this.m_commandReadGlucoseData2);
/* 311 */     localVector.addElement(this.m_commandReadGlucoseData3);
/* 312 */     localVector.addElement(this.m_commandReadGlucoseData4);
/* 313 */     localVector.addElement(this.m_commandReadGlucoseData5);
/* 314 */     localVector.addElement(this.m_commandReadGlucoseData6);
/* 315 */     localVector.addElement(this.m_commandReadGlucoseData7);
/* 316 */     localVector.addElement(this.m_commandReadGlucoseData8);
/* 317 */     localVector.addElement(this.m_commandReadGlucoseData9);
/*     */ 
/* 319 */     return localVector;
/*     */   }
/*     */ 
/*     */   private final void decodeReply(Command paramCommand)
/*     */     throws BadDeviceValueException
/*     */   {
/*     */     String str;
/* 334 */     if (paramCommand.m_commandCode == this.m_commandReadSettings.m_commandCode)
/*     */     {
/* 336 */       str = MedicalDevice.Util.makeString(paramCommand.m_rawData);
/* 337 */       decodeCurrentSettings(str);
/*     */     }
/* 339 */     else if (paramCommand.m_commandCode == this.m_commandReadClock.m_commandCode) {
/* 340 */       str = MedicalDevice.Util.makeString(paramCommand.m_rawData);
/* 341 */       decodeCurrentTimeStamp(str);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void decodeCurrentTimeStamp(String paramString)
/*     */     throws BadDeviceValueException
/*     */   {
/* 356 */     Contract.pre(paramString != null);
/* 357 */     Contract.pre(paramString.length() > 3);
/*     */ 
/* 370 */     this.m_realTimeClock = paramString;
/*     */ 
/* 372 */     String str1 = new String("yy,MM,dd,HH,mm");
/*     */ 
/* 375 */     String str2 = paramString.substring(5);
/*     */ 
/* 377 */     SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(str1);
/* 378 */     this.m_timeStamp = localSimpleDateFormat.parse(str2, new ParsePosition(0));
/*     */ 
/* 380 */     if (this.m_timeStamp == null)
/*     */     {
/* 382 */       throw new BadDeviceValueException("Bad device time string (null returned) '" + str2 + "' received");
/*     */     }
/*     */ 
/* 386 */     logInfo(this, "decodeCurrentTimeStamp: current time stamp for device is " + this.m_timeStamp);
/*     */   }
/*     */ 
/*     */   private final void decodeCurrentSettings(String paramString)
/*     */     throws BadDeviceValueException
/*     */   {
/* 399 */     Contract.pre(paramString != null);
/* 400 */     Contract.pre(paramString.length() > 1);
/*     */ 
/* 402 */     this.m_currentSettings = paramString;
/* 403 */     decodeSerialNumber();
/* 404 */     logInfo(this, "decodeCurrentSettings: *** CURRENT SETTINGS *** ");
/* 405 */     logInfo(this, "decodeCurrentSettings: input = " + this.m_currentSettings);
/* 406 */     logInfo(this, "decodeCurrentSettings: serial number = " + this.m_serialNumber);
/*     */   }
/*     */ 
/*     */   private final class SnapshotCreator extends MedicalDevice.SnapshotCreator
/*     */   {
/*     */     private static final int SNAPCODE_PRODUCT_ID = 1;
/*     */     private static final int SNAPCODE_SETTINGS = 2;
/*     */     private static final int SNAPCODE_GLUCOSE_DATA = 3;
/*     */     private static final int SNAPSHOT_BYTES = 221;
/*     */ 
/*     */     SnapshotCreator()
/*     */     {
/* 897 */       super(221);
/* 898 */       MMMeter.this.m_snapshotFirmwareCount = 0;
/* 899 */       MMMeter.this.m_snapshotSerialCount = 0;
/* 900 */       MMMeter.this.m_snapshotTimeCount = 28;
/*     */     }
/*     */ 
/*     */     void createSnapshotBody()
/*     */     {
/* 910 */       String str1 = "";
/* 911 */       String str2 = "";
/* 912 */       MMMeter.this.m_snapshot = new Snapshot(MMMeter.this.m_snapshotFormatID, 1, str1, str2, MMMeter.this.m_realTimeClock);
/*     */ 
/* 914 */       MedicalDevice.logInfo(this, "createSnapshot: creating snapshot");
/*     */ 
/* 918 */       MMMeter.this.m_snapshot.addElement(1, MMMeter.this.m_deviceClassID);
/*     */ 
/* 921 */       MMMeter.this.m_snapshot.addElement(2, MedicalDevice.Util.makeString(MMMeter.this.m_commandReadSettings.m_rawData));
/*     */ 
/* 925 */       int[] arrayOfInt = new int[0];
/* 926 */       arrayOfInt = MedicalDevice.Util.concat(arrayOfInt, MMMeter.this.m_commandReadGlucoseData0.m_rawData);
/* 927 */       arrayOfInt = MedicalDevice.Util.concat(arrayOfInt, MMMeter.this.m_commandReadGlucoseData1.m_rawData);
/* 928 */       arrayOfInt = MedicalDevice.Util.concat(arrayOfInt, MMMeter.this.m_commandReadGlucoseData2.m_rawData);
/* 929 */       arrayOfInt = MedicalDevice.Util.concat(arrayOfInt, MMMeter.this.m_commandReadGlucoseData3.m_rawData);
/* 930 */       arrayOfInt = MedicalDevice.Util.concat(arrayOfInt, MMMeter.this.m_commandReadGlucoseData4.m_rawData);
/* 931 */       arrayOfInt = MedicalDevice.Util.concat(arrayOfInt, MMMeter.this.m_commandReadGlucoseData5.m_rawData);
/* 932 */       arrayOfInt = MedicalDevice.Util.concat(arrayOfInt, MMMeter.this.m_commandReadGlucoseData6.m_rawData);
/* 933 */       arrayOfInt = MedicalDevice.Util.concat(arrayOfInt, MMMeter.this.m_commandReadGlucoseData7.m_rawData);
/* 934 */       arrayOfInt = MedicalDevice.Util.concat(arrayOfInt, MMMeter.this.m_commandReadGlucoseData8.m_rawData);
/* 935 */       arrayOfInt = MedicalDevice.Util.concat(arrayOfInt, MMMeter.this.m_commandReadGlucoseData9.m_rawData);
/* 936 */       MMMeter.this.m_snapshot.addElement(3, MedicalDevice.Util.makeString(arrayOfInt));
/*     */     }
/*     */   }
/*     */ 
/*     */   private final class Reader
/*     */   {
/*     */     private int m_serialPortNum;
/*     */     private Vector m_commandCollection;
/*     */     private final MMMeter this$0;
/*     */ 
/*     */     private Reader(DeviceListener paramInt, int paramVector, Vector arg4)
/*     */     {
/* 732 */       this.this$0 = this$1;
/* 733 */       this$1.addDeviceListener(paramInt);
/* 734 */       this.m_serialPortNum = paramVector;
/*     */       Object localObject;
/* 735 */       this.m_commandCollection = localObject;
/*     */     }
/*     */ 
/*     */     private final void acquireDataFromDevice()
/*     */       throws BadDeviceCommException, BadDeviceValueException, IOException
/*     */     {
/* 751 */       int i = 0;
/*     */ 
/* 753 */       Vector localVector = new Vector();
/*     */ 
/* 757 */       this.this$0.m_firmwareVersion = "";
/* 758 */       this.this$0.m_serialNumber = "";
/*     */ 
/* 762 */       MMMeter.access$502(this.this$0, 0);
/* 763 */       MMMeter.access$402(this.this$0, 0);
/*     */       MMMeter.Command localCommand;
/* 764 */       for (int j = 0; j < this.m_commandCollection.size(); j++) {
/* 765 */         localCommand = (MMMeter.Command)this.m_commandCollection.elementAt(j);
/* 766 */         if (localCommand != null) {
/* 767 */           MMMeter.access$512(this.this$0, localCommand.m_rawData.length);
/*     */         }
/*     */       }
/*     */ 
/* 771 */       this.this$0.notifyDeviceUpdateProgress(0);
/*     */       try
/*     */       {
/* 779 */         this.this$0.setState(2);
/*     */         try
/*     */         {
/* 782 */           initCommunications(this.m_serialPortNum);
/*     */         } catch (IOException localIOException) {
/* 784 */           if (!this.this$0.isHaltRequested()) {
/* 785 */             MedicalDevice.logWarning(this, "acquireDataFromDevice: initCommunications failed with IOException; retrying... (exception = " + localIOException + ")");
/*     */ 
/* 788 */             initCommunications(this.m_serialPortNum);
/*     */           }
/*     */         }
/*     */ 
/* 792 */         this.this$0.setPhase(5);
/*     */ 
/* 795 */         for (i = 0; (i < this.m_commandCollection.size()) && (!this.this$0.isHaltRequested()); )
/*     */         {
/* 797 */           localCommand = (MMMeter.Command)this.m_commandCollection.elementAt(i);
/* 798 */           if (localCommand != null)
/*     */           {
/* 800 */             localCommand.execute();
/* 801 */             if (!this.this$0.isHaltRequested())
/*     */             {
/* 803 */               int[] arrayOfInt = MMMeter.Command.access$700(localCommand);
/* 804 */               localVector.addElement(arrayOfInt);
/*     */             }
/*     */           }
/* 796 */           i++;
/*     */         }
/*     */ 
/*     */       }
/*     */       finally
/*     */       {
/*     */       }
/*     */ 
/* 828 */       ret;
/*     */     }
/*     */ 
/*     */     private final void initCommunications(int paramInt)
/*     */       throws IOException, BadDeviceCommException, BadDeviceValueException
/*     */     {
/* 845 */       this.this$0.initSerialPort(paramInt);
/*     */ 
/* 848 */       this.this$0.initDevice();
/*     */     }
/*     */ 
/*     */     private final void endCommunications()
/*     */       throws BadDeviceValueException, BadDeviceCommException, IOException
/*     */     {
/* 861 */       MedicalDevice.logInfo(this, "endCommunications: shutting down serial port.");
/* 862 */       if (this.this$0.getRS232Port() != null)
/*     */       {
/* 864 */         this.this$0.getRS232Port().close();
/* 865 */         this.this$0.setRS232Port(null);
/*     */       }
/*     */     }
/*     */ 
/*     */     Reader(DeviceListener paramInt, int paramVector, Vector param1, MMMeter.1 arg5)
/*     */     {
/* 715 */       this(paramInt, paramVector, param1);
/*     */     }
/*     */   }
/*     */ 
/*     */   private class Command extends DeviceCommand
/*     */   {
/*     */     private static final int RETRY_COUNT = 2;
/*     */     private static final String HEADER = "[\r\n";
/*     */     private static final String FOOTER = "]\r\n";
/*     */     int[] m_rawData;
/*     */     private int m_commandCode;
/*     */     private final MMMeter this$0;
/*     */ 
/*     */     private Command(int paramString, String paramInt1, int arg4)
/*     */     {
/* 451 */       super();
/*     */ 
/* 450 */       this.this$0 = this$1;
/*     */ 
/* 452 */       this.m_commandCode = paramString;
/*     */       int i;
/* 453 */       this.m_rawData = new int[i];
/*     */     }
/*     */ 
/*     */     final void execute()
/*     */       throws BadDeviceCommException, BadDeviceValueException
/*     */     {
/* 469 */       int i = 0;
/* 470 */       int j = 0;
/*     */ 
/* 472 */       Contract.pre(this.this$0.getRS232Port() != null);
/* 473 */       Contract.pre(this.this$0.getRS232Port().isOpen());
/*     */ 
/* 475 */       MedicalDevice.m_lastCommandDescription = this.m_description;
/*     */       do
/*     */       {
/*     */         try
/*     */         {
/* 481 */           sendAndRead();
/* 482 */           j = 1;
/*     */         } catch (BadDeviceCommException localBadDeviceCommException) {
/* 484 */           i++;
/* 485 */           this.this$0.getRS232Port().readUntilEmpty();
/* 486 */           if (i <= 2) {
/* 487 */             MedicalDevice.logWarning(this, "execute: cmd failed with exception: " + localBadDeviceCommException + "; retrying (attempts = " + (i + 1) + ")");
/*     */ 
/* 489 */             this.this$0.setState(7);
/*     */           } else {
/* 491 */             MedicalDevice.logError(this, "cmd " + MedicalDevice.Util.getHex(this.m_commandCode) + " (" + this.m_description + ") failed after " + i + " attempts" + "; exception = " + localBadDeviceCommException);
/*     */ 
/* 495 */             throw new BadDeviceCommException("execute: cmd " + MedicalDevice.Util.getHex(this.m_commandCode) + " (" + this.m_description + ") failed after " + i + " attempts");
/*     */           }
/*     */         }
/*     */         catch (BadDeviceValueException localBadDeviceValueException)
/*     */         {
/* 500 */           i++;
/* 501 */           this.this$0.getRS232Port().readUntilEmpty();
/* 502 */           if (i <= 2) {
/* 503 */             MedicalDevice.logWarning(this, "execute: cmd failed with exception: " + localBadDeviceValueException + "; retrying (attempts = " + (i + 1) + ")");
/*     */ 
/* 505 */             this.this$0.setState(7);
/*     */           } else {
/* 507 */             MedicalDevice.logError(this, "cmd " + MedicalDevice.Util.getHex(this.m_commandCode) + " (" + this.m_description + ") failed after " + i + " attempts" + "; exception = " + localBadDeviceValueException);
/*     */ 
/* 510 */             throw new BadDeviceValueException("execute: cmd " + MedicalDevice.Util.getHex(this.m_commandCode) + " (" + this.m_description + ") failed after " + i + " attempts");
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 517 */       while ((j == 0) && (i <= 2));
/*     */     }
/*     */ 
/*     */     private final int[] getRawData()
/*     */     {
/* 526 */       return this.m_rawData;
/*     */     }
/*     */ 
/*     */     private final void readDeviceData() throws BadDeviceCommException, BadDeviceValueException
/*     */     {
/* 540 */       Contract.pre(this.this$0.getRS232Port() != null);
/* 541 */       Contract.pre(this.this$0.getRS232Port().isOpen());
/*     */ 
/* 543 */       MedicalDevice.logInfo(this, "readDeviceData: reading reply to cmd " + MedicalDevice.Util.getHex(this.m_commandCode) + " (" + this.m_description + ")");
/*     */ 
/* 547 */       Object localObject = "";
/* 548 */       StringBuffer localStringBuffer = new StringBuffer();
/*     */       String str1;
/*     */       try
/*     */       {
/* 555 */         str1 = this.this$0.getRS232Port().readLine();
/* 556 */         if (!str1.equals("[\r\n")) {
/* 557 */           throw new BadDeviceCommException("readDeviceData: ERROR - no header reply for cmd " + MedicalDevice.Util.getHex(this.m_commandCode) + " (" + this.m_description + ")");
/*     */         }
/*     */ 
/* 563 */         int i = 0;
/*     */         do
/*     */         {
/* 566 */           String str3 = this.this$0.getRS232Port().readLine();
/* 567 */           if (str3.length() == 0) {
/* 568 */             throw new BadDeviceCommException("readDeviceData: ERROR - no data reply for cmd " + MedicalDevice.Util.getHex(this.m_commandCode) + " (" + this.m_description + ")");
/*     */           }
/*     */ 
/* 572 */           if (str3.length() == 4)
/*     */           {
/* 574 */             i = 1;
/* 575 */             localObject = str3;
/*     */           } else {
/* 577 */             localStringBuffer.append(str3);
/*     */           }
/*     */         }
/* 579 */         while (i == 0);
/*     */ 
/* 582 */         String str2 = this.this$0.getRS232Port().readLine();
/* 583 */         if (!str2.equals("]\r\n")) {
/* 584 */           throw new BadDeviceCommException("readDeviceData: ERROR - no footer reply for cmd " + MedicalDevice.Util.getHex(this.m_commandCode) + " (" + this.m_description + ")");
/*     */         }
/*     */ 
/* 590 */         this.m_rawData = MedicalDevice.Util.makeIntArray(str1 + localStringBuffer + (String)localObject + str2);
/*     */       } catch (IOException localIOException) {
/* 592 */         throw new BadDeviceCommException("readDeviceData: ERROR - an IOException  has occurred processing cmd " + MedicalDevice.Util.getHex(this.m_commandCode) + " (" + this.m_description + ")");
/*     */       }
/*     */ 
/* 598 */       verifyCRC(str1 + localStringBuffer, ((String)localObject).trim());
/*     */ 
/* 601 */       MedicalDevice.logInfo(this, "readDeviceData: cmd " + MedicalDevice.Util.getHex(this.m_commandCode) + " (" + this.m_description + ") returned " + this.m_rawData.length + " data bytes, with reply = <" + MedicalDevice.Util.makeString(this.m_rawData) + ">");
/*     */     }
/*     */ 
/*     */     final void verifyCRC(String paramString1, String paramString2) throws BadDeviceValueException {
/* 616 */       Contract.preNonNull(paramString1);
/*     */ 
/* 618 */       int[] arrayOfInt = MedicalDevice.Util.makeIntArray(paramString1);
/* 619 */       int i = MedicalDevice.Util.computeCRC8BD(arrayOfInt, 0, arrayOfInt.length);
/*     */       int j;
/*     */       try {
/* 624 */         j = Integer.parseInt(paramString2, 16);
/*     */       } catch (NumberFormatException localNumberFormatException) {
/* 626 */         throw new BadDeviceValueException("ERROR - '" + MedicalDevice.Util.convertControlChars(paramString1) + "' record has bad CRC value of " + paramString2 + " (expected " + MedicalDevice.Util.getHex(i) + ").");
/*     */       }
/*     */ 
/* 632 */       if (i != j) {
/* 633 */         MedicalDevice.logError(this, "verifyCRC: ERROR - cmd " + MedicalDevice.Util.getHex(this.m_commandCode) + " resulted in bad CRC reply of '" + MedicalDevice.Util.convertControlChars(paramString1) + "'; crcCalc=" + MedicalDevice.Util.getHex(i) + " crcDevice=" + MedicalDevice.Util.getHex(j));
/*     */ 
/* 637 */         throw new BadDeviceValueException("verifyCRC: ERROR - cmd " + MedicalDevice.Util.getHex(this.m_commandCode) + " resulted in bad CRC reply of '" + MedicalDevice.Util.convertControlChars(paramString1) + "'; crcCalc=" + MedicalDevice.Util.getHex(i) + " crcDevice=" + MedicalDevice.Util.getHex(j));
/*     */       }
/*     */     }
/*     */ 
/*     */     private final void sendCommand()
/*     */       throws BadDeviceCommException
/*     */     {
/* 655 */       Contract.pre(this.this$0.getRS232Port() != null);
/* 656 */       Contract.pre(this.this$0.getRS232Port().isOpen());
/*     */ 
/* 658 */       MedicalDevice.logInfo(this, "sendCommand: sending cmd " + MedicalDevice.Util.getHex(this.m_commandCode) + " (" + this.m_description + ")");
/*     */       try
/*     */       {
/* 663 */         this.this$0.getRS232Port().write(this.m_commandCode);
/*     */       } catch (IOException localIOException) {
/* 665 */         throw new BadDeviceCommException("sendCommand: ERROR - an IOException  has occurred processing cmd " + MedicalDevice.Util.getHex(this.m_commandCode) + " (" + this.m_description + ")");
/*     */       }
/*     */     }
/*     */ 
/*     */     private final void sendAndRead()
/*     */       throws BadDeviceCommException, BadDeviceValueException
/*     */     {
/* 684 */       if (this.this$0.getState() != 7) {
/* 685 */         this.this$0.setState(4);
/*     */       }
/* 687 */       sendCommand();
/*     */ 
/* 689 */       if (this.m_rawData.length > 0)
/*     */       {
/* 691 */         if (!this.this$0.isHaltRequested())
/*     */         {
/* 693 */           this.this$0.setState(5);
/* 694 */           readDeviceData();
/*     */         }
/*     */ 
/* 697 */         MMMeter.access$412(this.this$0, this.m_rawData.length);
/*     */ 
/* 700 */         this.this$0.notifyDeviceUpdateProgress(this.this$0.m_bytesReadThusFar, this.this$0.m_totalBytesToRead);
/*     */ 
/* 703 */         if (!this.this$0.isHaltRequested())
/* 704 */           this.this$0.decodeReply(this);
/*     */       }
/*     */     }
/*     */ 
/*     */     Command(int paramString, String paramInt1, int param1, MMMeter.1 arg5)
/*     */     {
/* 419 */       this(paramString, paramInt1, param1);
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.deviceportreader.MMMeter
 * JD-Core Version:    0.6.0
 */