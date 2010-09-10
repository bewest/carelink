/*     */ package minimed.ddms.deviceportreader;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.text.DateFormatSymbols;
/*     */ import java.text.ParsePosition;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.Vector;
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ final class TheraSenseFreeStyle extends MedicalDevice
/*     */ {
/*     */   public static final int SNAPSHOT_FORMAT_ID = 160;
/*     */   static final String CMD_GET_LOG = "mem";
/*     */   static final int METER_POWER_UP_DELAY_MS = 5000;
/*     */   static final int LEN_GET_LOG = 8250;
/*     */   private static final String CLOCK_FORMAT = "MMMM dd yyyy HH:mm:ss";
/*  51 */   private static final String[] DEVICE_MONTHS = { "Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sept", "Oct", "Nov", "Dec" };
/*     */ 
/*  58 */   private static final String[] DEVICE_MONTHS2 = { "Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sep", "Oct", "Nov", "Dec" };
/*     */   private static final int IO_DELAY_MS = 10;
/*     */   private static final int READ_TO_MS = 1000;
/*     */   private String m_logData;
/*     */   private Command m_cmdGetLog;
/*     */   private int m_totalBytesToRead;
/*     */   private int m_bytesReadThusFar;
/*     */   private String m_realTimeClock;
/*     */   private final SimpleDateFormat m_clockDateFormatter1;
/*     */   private final SimpleDateFormat m_clockDateFormatter2;
/*     */ 
/*     */   TheraSenseFreeStyle()
/*     */   {
/*  91 */     this.m_description = "TheraSense FreeStyle Meter";
/*  92 */     logInfo(this, "creating interface to the '" + this.m_description + "', package version " + getPackageVersion());
/*     */ 
/*  94 */     this.m_deviceClassID = 12;
/*  95 */     this.m_snapshotFormatID = 160;
/*  96 */     this.m_snapshotCreator = new SnapshotCreator();
/*     */ 
/*  98 */     this.m_logData = new String(new byte[8250]);
/*     */ 
/* 101 */     this.m_cmdGetLog = new Command("mem", "Read Log", this.m_logData.length(), null);
/*     */ 
/* 105 */     DateFormatSymbols localDateFormatSymbols = new DateFormatSymbols();
/*     */ 
/* 108 */     localDateFormatSymbols.setShortMonths(DEVICE_MONTHS);
/* 109 */     this.m_clockDateFormatter1 = new SimpleDateFormat("MMMM dd yyyy HH:mm:ss", localDateFormatSymbols);
/*     */ 
/* 112 */     localDateFormatSymbols = new DateFormatSymbols();
/*     */ 
/* 115 */     localDateFormatSymbols.setShortMonths(DEVICE_MONTHS2);
/* 116 */     this.m_clockDateFormatter2 = new SimpleDateFormat("MMMM dd yyyy HH:mm:ss", localDateFormatSymbols);
/*     */   }
/*     */ 
/*     */   public void readData(DeviceListener paramDeviceListener, int paramInt, String paramString)
/*     */     throws BadDeviceCommException, BadDeviceValueException, IOException
/*     */   {
/* 141 */     logInfo(this, "readData: starting reader...");
/* 142 */     setHaltRequested(false);
/*     */ 
/* 145 */     Vector localVector = createCommandList();
/*     */ 
/* 148 */     Reader localReader = new Reader(paramDeviceListener, paramInt, localVector, null);
/* 149 */     localReader.acquireDataFromDevice();
/*     */   }
/*     */ 
/*     */   void findDevice(DeviceListener paramDeviceListener)
/*     */     throws BadDeviceCommException, IOException
/*     */   {
/*     */     try
/*     */     {
/* 162 */       this.m_cmdGetLog.execute();
/*     */     }
/*     */     catch (BadDeviceValueException localBadDeviceValueException) {
/* 165 */       throw new BadDeviceCommException("Got reply, but format is bad.");
/*     */     }
/*     */   }
/*     */ 
/*     */   int getDeviceType()
/*     */   {
/* 175 */     return 3;
/*     */   }
/*     */ 
/*     */   void initSerialPort(int paramInt)
/*     */     throws IOException
/*     */   {
/* 186 */     beginSerialPort(paramInt);
/* 187 */     setRS232Port(new SerialPort(paramInt, 8));
/* 188 */     getRS232Port().readUntilEmpty();
/* 189 */     getRS232Port().setIODelay(10);
/* 190 */     getRS232Port().setReadTimeOut(1000);
/*     */   }
/*     */ 
/*     */   void shutDownSerialPort()
/*     */     throws IOException
/*     */   {
/* 199 */     if (getRS232Port() != null)
/*     */     {
/* 201 */       getRS232Port().close();
/* 202 */       setRS232Port(null);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void decodeCurrentTimeStamp()
/*     */     throws BadDeviceValueException
/*     */   {
/* 214 */     this.m_timeStamp = createTimestamp(this.m_realTimeClock);
/* 215 */     logInfo(this, "decodeCurrentTimeStamp: current time stamp for device is " + this.m_timeStamp);
/*     */   }
/*     */ 
/*     */   private final void decodeReply(Command paramCommand)
/*     */     throws BadDeviceValueException
/*     */   {
/* 230 */     String str = MedicalDevice.Util.makeString(paramCommand.m_rawData);
/*     */ 
/* 232 */     if (paramCommand.m_command.equals(this.m_cmdGetLog.m_command)) {
/* 233 */       this.m_logData = str;
/* 234 */       logInfo(this, "decodeReply: reply is Log reply: '" + this.m_logData + "'");
/*     */ 
/* 237 */       decodeCurrentTimeStamp();
/*     */     } else {
/* 239 */       throw new BadDeviceValueException("Command '" + paramCommand.m_command + " ' is unrecognized");
/*     */     }
/*     */   }
/*     */ 
/*     */   private final void initDevice()
/*     */   {
/* 250 */     Contract.pre(getRS232Port() != null);
/* 251 */     Contract.pre(getRS232Port().isOpen());
/*     */ 
/* 253 */     setPhase(4);
/*     */ 
/* 255 */     MedicalDevice.Util.sleepMS(5000);
/*     */   }
/*     */ 
/*     */   private final Vector createCommandList()
/*     */   {
/* 264 */     Vector localVector = new Vector();
/*     */ 
/* 267 */     localVector.addElement(this.m_cmdGetLog);
/* 268 */     return localVector;
/*     */   }
/*     */ 
/*     */   private Date createTimestamp(String paramString)
/*     */     throws BadDeviceValueException
/*     */   {
/* 282 */     Contract.pre(paramString != null);
/* 283 */     Contract.pre(paramString.length() == "MMMM dd yyyy HH:mm:ss".length());
/*     */ 
/* 286 */     Date localDate = this.m_clockDateFormatter1.parse(paramString, new ParsePosition(0));
/*     */ 
/* 288 */     if (localDate == null)
/*     */     {
/* 291 */       localDate = this.m_clockDateFormatter2.parse(paramString, new ParsePosition(0));
/* 292 */       if (localDate == null)
/*     */       {
/* 294 */         throw new BadDeviceValueException("Bad device time string (dataF is null) '" + paramString + "' received");
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 299 */     return localDate;
/*     */   }
/*     */ 
/*     */   private final class SnapshotCreator extends MedicalDevice.SnapshotCreator
/*     */   {
/*     */     static final int SNAPSHOT_BYTES = 70;
/*     */     static final int SNAPCODE_LOG = 1;
/*     */     static final int LAST_SNAPCODE = 1;
/*     */ 
/*     */     SnapshotCreator()
/*     */     {
/* 805 */       super(70);
/* 806 */       TheraSenseFreeStyle.this.m_snapshotFirmwareCount = 0;
/* 807 */       TheraSenseFreeStyle.this.m_snapshotSerialCount = 0;
/* 808 */       TheraSenseFreeStyle.this.m_snapshotTimeCount = 0;
/*     */     }
/*     */ 
/*     */     void createSnapshotBody()
/*     */     {
/* 817 */       String str = "";
/*     */ 
/* 820 */       TheraSenseFreeStyle.this.m_snapshot = new Snapshot(TheraSenseFreeStyle.this.m_snapshotFormatID, 1, str, str, str);
/*     */ 
/* 823 */       MedicalDevice.logInfo(this, "createSnapshot: creating snapshot");
/*     */ 
/* 827 */       TheraSenseFreeStyle.this.m_snapshot.addElement(1, TheraSenseFreeStyle.this.m_logData);
/*     */     }
/*     */   }
/*     */ 
/*     */   private final class Reader
/*     */   {
/*     */     private int m_serialPortNum;
/*     */     private Vector m_commandCollection;
/*     */     private final TheraSenseFreeStyle this$0;
/*     */ 
/*     */     private Reader(DeviceListener paramInt, int paramVector, Vector arg4)
/*     */     {
/* 652 */       this.this$0 = this$1;
/* 653 */       this$1.addDeviceListener(paramInt);
/* 654 */       this.m_serialPortNum = paramVector;
/*     */       Object localObject;
/* 655 */       this.m_commandCollection = localObject;
/*     */     }
/*     */ 
/*     */     private final void acquireDataFromDevice()
/*     */       throws BadDeviceCommException, BadDeviceValueException, IOException
/*     */     {
/* 671 */       int i = 0;
/*     */ 
/* 673 */       Vector localVector = new Vector();
/*     */ 
/* 677 */       this.this$0.m_firmwareVersion = "";
/* 678 */       this.this$0.m_serialNumber = "";
/*     */ 
/* 682 */       TheraSenseFreeStyle.access$602(this.this$0, 0);
/* 683 */       TheraSenseFreeStyle.access$502(this.this$0, 0);
/*     */       TheraSenseFreeStyle.Command localCommand;
/* 684 */       for (int j = 0; j < this.m_commandCollection.size(); j++) {
/* 685 */         localCommand = (TheraSenseFreeStyle.Command)this.m_commandCollection.elementAt(j);
/* 686 */         if (localCommand != null) {
/* 687 */           TheraSenseFreeStyle.access$612(this.this$0, localCommand.m_rawData.length);
/*     */         }
/*     */       }
/*     */ 
/* 691 */       this.this$0.notifyDeviceUpdateProgress(0);
/*     */       try
/*     */       {
/* 699 */         this.this$0.setState(2);
/*     */         try
/*     */         {
/* 702 */           initCommunications(this.m_serialPortNum);
/*     */         } catch (IOException localIOException) {
/* 704 */           if (!this.this$0.isHaltRequested()) {
/* 705 */             MedicalDevice.logWarning(this, "acquireDataFromDevice: initCommunications failed with IOException; retrying... (exception = " + localIOException + ")");
/*     */ 
/* 708 */             initCommunications(this.m_serialPortNum);
/*     */           }
/*     */         }
/*     */ 
/* 712 */         this.this$0.setPhase(5);
/*     */ 
/* 715 */         for (i = 0; (i < this.m_commandCollection.size()) && (!this.this$0.isHaltRequested()); )
/*     */         {
/* 717 */           localCommand = (TheraSenseFreeStyle.Command)this.m_commandCollection.elementAt(i);
/* 718 */           if (localCommand != null)
/*     */           {
/* 720 */             localCommand.execute();
/* 721 */             if (!this.this$0.isHaltRequested())
/*     */             {
/* 723 */               int[] arrayOfInt = TheraSenseFreeStyle.Command.access$800(localCommand);
/* 724 */               localVector.addElement(arrayOfInt);
/*     */             }
/*     */           }
/* 716 */           i++;
/*     */         }
/*     */ 
/*     */       }
/*     */       finally
/*     */       {
/*     */       }
/*     */ 
/* 748 */       ret;
/*     */     }
/*     */ 
/*     */     private final void initCommunications(int paramInt)
/*     */       throws IOException
/*     */     {
/* 762 */       this.this$0.initSerialPort(paramInt);
/*     */ 
/* 765 */       this.this$0.initDevice();
/*     */     }
/*     */ 
/*     */     private final void endCommunications()
/*     */       throws IOException
/*     */     {
/* 774 */       MedicalDevice.logInfo(this, "endCommunications: shutting down serial port.");
/* 775 */       if (this.this$0.getRS232Port() != null)
/*     */       {
/* 777 */         this.this$0.getRS232Port().close();
/* 778 */         this.this$0.setRS232Port(null);
/*     */       }
/*     */     }
/*     */ 
/*     */     Reader(DeviceListener paramInt, int paramVector, Vector param1, TheraSenseFreeStyle.1 arg5)
/*     */     {
/* 635 */       this(paramInt, paramVector, param1);
/*     */     }
/*     */   }
/*     */ 
/*     */   private final class Command extends DeviceCommand
/*     */   {
/*     */     static final byte REPLY_TIMEOUT = -1;
/*     */     static final int RETRY_COUNT = 2;
/*     */     static final int IO_DELAY_READ_RETRY_MS = 100;
/*     */     static final String LAST_RECORD = "END\r\n";
/*     */     static final String LOG_EMPTY = "LOG EMPTY";
/*     */     static final int LEN_CHECKSUM = 13;
/*     */     int[] m_rawData;
/*     */     private String m_command;
/*     */     private final TheraSenseFreeStyle this$0;
/*     */ 
/*     */     private Command(String paramString1, String paramInt, int arg4)
/*     */     {
/* 357 */       super();
/*     */ 
/* 356 */       this.this$0 = this$1;
/*     */ 
/* 358 */       this.m_command = paramString1;
/*     */       int i;
/* 359 */       this.m_rawData = new int[i];
/*     */     }
/*     */ 
/*     */     void execute()
/*     */       throws BadDeviceCommException, BadDeviceValueException
/*     */     {
/* 376 */       int i = 0;
/* 377 */       int j = 0;
/*     */ 
/* 379 */       Contract.pre(this.this$0.getRS232Port() != null);
/* 380 */       Contract.pre(this.this$0.getRS232Port().isOpen());
/*     */ 
/* 382 */       MedicalDevice.m_lastCommandDescription = this.m_description;
/*     */       do
/*     */       {
/*     */         try
/*     */         {
/* 388 */           sendAndRead();
/* 389 */           j = 1;
/*     */         } catch (BadDeviceCommException localBadDeviceCommException) {
/* 391 */           i++;
/* 392 */           this.this$0.getRS232Port().readUntilEmpty();
/* 393 */           if (i <= 2) {
/* 394 */             MedicalDevice.logWarning(this, "execute: cmd failed with exception: " + localBadDeviceCommException + "; retrying (attempts = " + (i + 1) + ")");
/*     */ 
/* 396 */             this.this$0.setState(7);
/*     */           } else {
/* 398 */             MedicalDevice.logError(this, "cmd " + this.m_command + " (" + this.m_description + ") failed after " + i + " attempts" + "; exception = " + localBadDeviceCommException);
/*     */ 
/* 405 */             throw new BadDeviceCommException("execute: cmd " + this.m_command + " (" + this.m_description + ") failed after " + i + " attempts");
/*     */           }
/*     */         }
/*     */         catch (BadDeviceValueException localBadDeviceValueException)
/*     */         {
/* 410 */           i++;
/* 411 */           this.this$0.getRS232Port().readUntilEmpty();
/* 412 */           if (i <= 2) {
/* 413 */             MedicalDevice.logWarning(this, "execute: cmd failed with exception: " + localBadDeviceValueException + "; retrying (attempts = " + (i + 1) + ")");
/*     */ 
/* 415 */             this.this$0.setState(7);
/*     */           } else {
/* 417 */             MedicalDevice.logError(this, "cmd " + this.m_command + " (" + this.m_description + ") failed after " + i + " attempts" + "; exception = " + localBadDeviceValueException);
/*     */ 
/* 424 */             throw new BadDeviceValueException("execute: cmd " + this.m_command + " (" + this.m_description + ") failed after " + i + " attempts");
/*     */           }
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 430 */       while ((j == 0) && (i <= 2));
/*     */     }
/*     */ 
/*     */     private final int[] getRawData()
/*     */     {
/* 439 */       return this.m_rawData;
/*     */     }
/*     */ 
/*     */     private final String readDeviceData()
/*     */       throws BadDeviceCommException, BadDeviceValueException
/*     */     {
/* 455 */       StringBuffer localStringBuffer = new StringBuffer();
/*     */ 
/* 457 */       int i = 0;
/*     */ 
/* 459 */       Contract.pre(this.this$0.getRS232Port() != null);
/* 460 */       Contract.pre(this.this$0.getRS232Port().isOpen());
/*     */ 
/* 462 */       MedicalDevice.logInfo(this, "readDeviceData: reading reply to cmd " + this.m_command + " (" + this.m_description + ")");
/*     */       String str1;
/*     */       try
/*     */       {
/* 472 */         str1 = this.this$0.getRS232Port().readLine();
/* 473 */         if (str1.length() == 2)
/*     */         {
/* 475 */           localStringBuffer.append(str1);
/* 476 */           str1 = this.this$0.getRS232Port().readLine();
/*     */         }
/*     */ 
/* 479 */         localStringBuffer.append(str1);
/* 480 */         this.this$0.m_serialNumber = str1.trim();
/*     */ 
/* 483 */         str1 = this.this$0.getRS232Port().readLine();
/* 484 */         localStringBuffer.append(str1);
/* 485 */         this.this$0.m_firmwareVersion = str1.trim();
/*     */ 
/* 488 */         str1 = this.this$0.getRS232Port().readLine();
/* 489 */         localStringBuffer.append(str1);
/* 490 */         TheraSenseFreeStyle.access$402(this.this$0, str1.trim());
/*     */       } catch (IOException localIOException1) {
/* 492 */         throw new BadDeviceCommException("readDeviceData: ERROR - an IOException  has occurred processing cmd " + this.m_command + " (" + this.m_description + ")");
/*     */       }
/*     */ 
/*     */       do
/*     */       {
/*     */         try
/*     */         {
/* 502 */           str1 = this.this$0.getRS232Port().readLine();
/* 503 */           localStringBuffer.append(str1);
/* 504 */           i = (str1.endsWith("END\r\n")) || (str1.length() == 0) ? 1 : 0;
/*     */         } catch (IOException localIOException2) {
/* 506 */           throw new BadDeviceCommException("readDeviceData: ERROR - an IOException  has occurred processing cmd " + this.m_command + " (" + this.m_description + ")");
/*     */         }
/*     */       }
/*     */ 
/* 510 */       while (i == 0);
/*     */ 
/* 512 */       String str2 = localStringBuffer.toString();
/* 513 */       String str3 = str1;
/*     */ 
/* 515 */       if (str2.length() > 0)
/*     */       {
/* 517 */         if (str2.toUpperCase().indexOf("LOG EMPTY") == -1)
/*     */         {
/* 519 */           int[] arrayOfInt = MedicalDevice.Util.makeIntArray(str2);
/* 520 */           int j = MedicalDevice.Util.computeCRC16sum(arrayOfInt, 0, arrayOfInt.length - 13);
/*     */ 
/* 525 */           if (str3.length() < 6) {
/* 526 */             throw new BadDeviceValueException("ERROR - CRC record too small: " + str3);
/*     */           }
/*     */ 
/* 530 */           String str4 = str3.substring(2, 6);
/* 531 */           int k = 0;
/*     */           try {
/* 533 */             k = Integer.parseInt(str4, 16);
/*     */           } catch (NumberFormatException localNumberFormatException) {
/* 535 */             throw new BadDeviceValueException("ERROR - '" + MedicalDevice.Util.convertControlChars(str2) + "' record has bad CRC value of " + str4 + " (expected " + MedicalDevice.Util.getHex(j) + ").");
/*     */           }
/*     */ 
/* 542 */           if (j != k) {
/* 543 */             throw new BadDeviceValueException("ERROR - " + MedicalDevice.Util.convertControlChars(str2) + " record has incorrect CRC value of " + MedicalDevice.Util.getHex(k) + " (expected " + MedicalDevice.Util.getHex(j) + ").");
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/* 549 */         MedicalDevice.logInfo(this, "readDeviceData: cmd " + this.m_command + " (" + this.m_description + ") returned " + str2.length() + " data bytes, with reply = <" + str2 + ">");
/*     */       }
/*     */       else
/*     */       {
/* 553 */         throw new BadDeviceCommException("readDeviceData: ERROR - no reply for cmd " + this.m_command + " (" + this.m_description + ")");
/*     */       }
/*     */ 
/* 556 */       return str2;
/*     */     }
/*     */ 
/*     */     private final void sendCommand()
/*     */       throws BadDeviceCommException
/*     */     {
/* 569 */       Contract.pre(this.this$0.getRS232Port() != null);
/* 570 */       Contract.pre(this.this$0.getRS232Port().isOpen());
/*     */ 
/* 572 */       MedicalDevice.logInfo(this, "sendCommand: sending cmd " + this.m_command + " (" + this.m_description + ")");
/*     */       try
/*     */       {
/* 576 */         this.this$0.getRS232Port().write(this.m_command);
/*     */       } catch (IOException localIOException) {
/* 578 */         throw new BadDeviceCommException("sendCommand: ERROR - an IOException  has occurred processing cmd " + this.m_command + " (" + this.m_description + ")");
/*     */       }
/*     */     }
/*     */ 
/*     */     private final void sendAndRead()
/*     */       throws BadDeviceCommException, BadDeviceValueException
/*     */     {
/* 598 */       if (this.this$0.getState() != 7) {
/* 599 */         this.this$0.setState(4);
/*     */       }
/* 601 */       sendCommand();
/*     */ 
/* 603 */       if (this.m_rawData.length > 0)
/*     */       {
/* 605 */         if (!this.this$0.isHaltRequested())
/*     */         {
/* 607 */           this.this$0.setState(5);
/* 608 */           this.m_rawData = MedicalDevice.Util.makeIntArray(readDeviceData());
/*     */         }
/*     */ 
/* 612 */         if ((this.m_rawData.length == 0) && (!this.this$0.isHaltRequested()))
/*     */         {
/* 614 */           MedicalDevice.Util.sleepMS(100);
/* 615 */           this.m_rawData = MedicalDevice.Util.makeIntArray(readDeviceData());
/*     */         }
/*     */ 
/* 618 */         TheraSenseFreeStyle.access$512(this.this$0, this.m_rawData.length);
/*     */ 
/* 621 */         this.this$0.notifyDeviceUpdateProgress(this.this$0.m_bytesReadThusFar, this.this$0.m_totalBytesToRead);
/*     */ 
/* 623 */         if (!this.this$0.isHaltRequested())
/* 624 */           this.this$0.decodeReply(this);
/*     */       }
/*     */     }
/*     */ 
/*     */     Command(String paramString1, String paramInt, int param1, TheraSenseFreeStyle.1 arg5)
/*     */     {
/* 312 */       this(paramString1, paramInt, param1);
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.deviceportreader.TheraSenseFreeStyle
 * JD-Core Version:    0.6.0
 */