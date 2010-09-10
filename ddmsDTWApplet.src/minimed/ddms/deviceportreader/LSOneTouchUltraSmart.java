/*     */ package minimed.ddms.deviceportreader;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.Vector;
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ final class LSOneTouchUltraSmart extends LSMeter
/*     */ {
/*     */   static final int SNAPSHOT_FORMAT_ID = 156;
/*     */   private static final String CMD_GET_SERIAL_NUMBER = "DM@\r";
/*     */   private static final String CMD_READ_CLOCK = "DMF\r";
/*     */   private static final String CMD_GET_FIRMWARE_VERSION = "DM?\r";
/*     */   private static final int READ_TO_MS = 1500;
/*     */   private static final int IO_DELAY_MS = 5;
/*     */   private static final int IO_DELAY_LIMIT = 100;
/*     */   private static final int IO_DELAY_INCREMENT = 5;
/*  83 */   private int m_ioDelay = 5;
/*     */ 
/*     */   LSOneTouchUltraSmart()
/*     */   {
/*  91 */     this.m_description = "LifeScan One Touch UltraSmart Meter";
/*  92 */     logInfo(this, "creating interface to the '" + this.m_description + "', package version " + getPackageVersion());
/*     */ 
/*  94 */     this.m_deviceClassID = 21;
/*  95 */     this.m_snapshotFormatID = 156;
/*  96 */     this.m_snapshotCreator = new SnapshotCreator(null);
/*  97 */     this.m_baudRate = 9;
/*     */ 
/* 100 */     this.m_cmdReadRealTimeClock = new LSMeter.Command(this, "DMF\r", "Read Real Time Clock", 50);
/*     */ 
/* 102 */     this.m_cmdGetFirmwareVersion = new LSMeter.Command(this, "DM?\r", "Read Firmware Version", 50);
/*     */ 
/* 104 */     this.m_cmdGetSerialNumber = new LSMeter.Command(this, "DM@\r", "Read Serial Number", 50);
/*     */ 
/* 108 */     this.m_cmdGetDatalog = new HRProcedureCommand();
/*     */   }
/*     */ 
/*     */   void initSerialPort(int paramInt)
/*     */     throws IOException
/*     */   {
/* 121 */     super.initSerialPort(paramInt);
/*     */ 
/* 123 */     getRS232Port().setReadTimeOut(1500);
/* 124 */     getRS232Port().setIODelay(this.m_ioDelay);
/*     */   }
/*     */ 
/*     */   Vector createCommandList()
/*     */   {
/* 133 */     Vector localVector = new Vector();
/*     */ 
/* 137 */     localVector.addElement(this.m_cmdReadRealTimeClock);
/* 138 */     localVector.addElement(this.m_cmdGetFirmwareVersion);
/* 139 */     localVector.addElement(this.m_cmdGetSerialNumber);
/* 140 */     localVector.addElement(this.m_cmdGetDatalog);
/* 141 */     return localVector;
/*     */   }
/*     */ 
/*     */   void decodeCurrentSettings()
/*     */     throws BadDeviceValueException
/*     */   {
/*     */   }
/*     */ 
/*     */   int[] removeControlBytesTestAccess(int[] paramArrayOfInt)
/*     */   {
/* 162 */     return new HRCommand(1).removeControlBytes(paramArrayOfInt);
/*     */   }
/*     */ 
/*     */   private class SnapshotCreator extends LSMeter.SnapshotCreator
/*     */   {
/*     */     static final int SNAPCODE_HR_DATA = 1;
/*     */     static final int LAST_SNAPCODE = 1;
/*     */     static final int SNAPSHOT_BYTES = 169;
/*     */     private final LSOneTouchUltraSmart this$0;
/*     */ 
/*     */     private SnapshotCreator()
/*     */     {
/* 623 */       super(); this.this$0 = this$1;
/*     */     }
/*     */ 
/*     */     InputStream createSnapshot()
/*     */       throws BadDeviceValueException, IOException
/*     */     {
/* 649 */       this.this$0.m_snapshot = new Snapshot(this.this$0.m_snapshotFormatID, 1, pad(this.this$0.m_firmwareVersion), pad(this.this$0.m_serialNumber), pad(this.this$0.m_realTimeClock));
/*     */ 
/* 652 */       MedicalDevice.logInfo(this, "createSnapshot: creating snapshot");
/*     */ 
/* 658 */       this.this$0.m_snapshot.addElement(1, this.this$0.m_cmdGetDatalog.getRawData());
/*     */ 
/* 661 */       int i = this.this$0.m_snapshot.write();
/* 662 */       MedicalDevice.logInfo(this, "createSnapshot: wrote " + i + " bytes.");
/*     */ 
/* 664 */       if (i < 169) {
/* 665 */         throw new BadDeviceValueException("Resulting snapshot size is invalid: " + i + "; must be at least " + 169 + " bytes.");
/*     */       }
/*     */ 
/* 668 */       return this.this$0.m_snapshot.toInputStream();
/*     */     }
/*     */ 
/*     */     SnapshotCreator(LSOneTouchUltraSmart.1 arg2)
/*     */     {
/* 623 */       this();
/*     */     }
/*     */   }
/*     */ 
/*     */   private class HRCommand extends LSMeter.Command
/*     */   {
/*     */     private static final int RETRY_COUNT = 10;
/*     */     private static final int LEN_GET_ONE_HR_DATA = 17;
/*     */     private static final int LEN_GET_ONE_HR_DATA_LAST_REC = 7;
/*     */     private static final int LEN_CONTROL = 5;
/*     */     private static final String CMD_GET_HR_DATA = "HR";
/*     */     private int m_recNumber;
/*     */     private boolean m_lastRecord;
/*     */ 
/*     */     HRCommand(int arg2)
/*     */     {
/* 275 */       super("HR" + i, "Read HR Data-" + i, 17);
/* 276 */       Contract.pre(i > 0);
/* 277 */       this.m_recNumber = i;
/* 278 */       this.m_lastRecord = false;
/*     */ 
/* 280 */       this.m_maxRetryCount = 10;
/*     */     }
/*     */ 
/*     */     void sendAndRead()
/*     */       throws BadDeviceCommException, BadDeviceValueException
/*     */     {
/* 293 */       if (LSOneTouchUltraSmart.this.getState() != 7) {
/* 294 */         LSOneTouchUltraSmart.this.setState(4);
/*     */       }
/* 296 */       sendCommand();
/*     */ 
/* 299 */       if (!LSOneTouchUltraSmart.this.isHaltRequested())
/*     */       {
/* 301 */         LSOneTouchUltraSmart.this.setState(5);
/* 302 */         readDeviceData();
/*     */       }
/*     */ 
/* 306 */       LSOneTouchUltraSmart.this.notifyDeviceUpdateProgress();
/*     */     }
/*     */ 
/*     */     void sendCommand()
/*     */       throws BadDeviceCommException
/*     */     {
/*     */       try
/*     */       {
/* 316 */         sendCommandIO();
/*     */       } catch (BadDeviceCommException localBadDeviceCommException) {
/* 318 */         incIODelay("sendCommand");
/* 319 */         throw localBadDeviceCommException;
/*     */       }
/*     */     }
/*     */ 
/*     */     private void sendCommandIO()
/*     */       throws BadDeviceCommException
/*     */     {
/* 332 */       Contract.pre(LSOneTouchUltraSmart.this.getRS232Port() != null);
/* 333 */       Contract.pre(LSOneTouchUltraSmart.this.getRS232Port().isOpen());
/*     */ 
/* 335 */       MedicalDevice.logInfo(this, "sendCommandIO: sending cmd " + getCommand() + " (" + getDescription() + ")");
/*     */       try
/*     */       {
/* 339 */         LSOneTouchUltraSmart.this.getRS232Port().write(createFrame());
/*     */       } catch (IOException localIOException) {
/* 341 */         throw new BadDeviceCommException("sendCommandIO: ERROR - an IOException  has occurred processing cmd " + getCommand() + " (" + getDescription() + ")");
/*     */       }
/*     */     }
/*     */ 
/*     */     boolean isLastRecord()
/*     */     {
/* 353 */       return this.m_lastRecord;
/*     */     }
/*     */ 
/*     */     private void readDeviceData()
/*     */       throws BadDeviceCommException, BadDeviceValueException
/*     */     {
/*     */       try
/*     */       {
/* 364 */         readDeviceDataIO();
/*     */       } catch (BadDeviceCommException localBadDeviceCommException) {
/* 366 */         incIODelay("readDeviceData");
/* 367 */         throw localBadDeviceCommException;
/*     */       } catch (BadDeviceValueException localBadDeviceValueException) {
/* 369 */         incIODelay("readDeviceData");
/* 370 */         throw localBadDeviceValueException;
/*     */       }
/*     */     }
/*     */ 
/*     */     private void readDeviceDataIO() throws BadDeviceCommException, BadDeviceValueException
/*     */     {
/* 384 */       Contract.pre(LSOneTouchUltraSmart.this.getRS232Port() != null);
/* 385 */       Contract.pre(LSOneTouchUltraSmart.this.getRS232Port().isOpen());
/*     */ 
/* 387 */       MedicalDevice.logInfo(this, "readDeviceDataIO: reading reply to cmd " + getCommand() + " (" + getDescription() + ")");
/*     */       int[] arrayOfInt;
/*     */       try {
/* 393 */         arrayOfInt = LSOneTouchUltraSmart.this.getRS232Port().readAvailableBytes();
/*     */       } catch (IOException localIOException) {
/* 395 */         throw new BadDeviceCommException("readDeviceDataIO: ERROR - an IOException  has occurred processing cmd " + getCommand() + " (" + getDescription() + ")");
/*     */       }
/*     */ 
/* 401 */       setRawData(removeControlBytes(arrayOfInt));
/*     */ 
/* 404 */       verifyLength();
/* 405 */       verifyCRC();
/* 406 */       verifyFrame();
/*     */ 
/* 409 */       this.m_lastRecord = (getRawData().length == 7);
/*     */ 
/* 411 */       MedicalDevice.logInfo(this, "readDeviceDataIO: cmd " + getCommand() + " (" + getDescription() + ") returned " + getRawData().length + " data bytes: " + "<" + MedicalDevice.Util.getHex(getRawData()) + ">");
/*     */     }
/*     */ 
/*     */     private void incIODelay(String paramString)
/*     */     {
/* 422 */       LSOneTouchUltraSmart.access$202(LSOneTouchUltraSmart.this, Math.min(LSOneTouchUltraSmart.this.m_ioDelay + 5, 100));
/* 423 */       MedicalDevice.logInfo(this, paramString + ": increasing m_ioDelay to " + LSOneTouchUltraSmart.this.m_ioDelay);
/* 424 */       LSOneTouchUltraSmart.this.getRS232Port().setIODelay(LSOneTouchUltraSmart.this.m_ioDelay);
/*     */     }
/*     */ 
/*     */     private int[] removeControlBytes(int[] paramArrayOfInt)
/*     */     {
/* 436 */       Contract.preNonNull(paramArrayOfInt);
/* 437 */       int[] arrayOfInt1 = new int[paramArrayOfInt.length];
/*     */ 
/* 442 */       int i = 0;
/* 443 */       for (int j = 0; j < paramArrayOfInt.length; j++) {
/* 444 */         arrayOfInt1[(i++)] = paramArrayOfInt[j];
/* 445 */         if ((j <= 1) || (j >= paramArrayOfInt.length - 4))
/*     */           continue;
/* 447 */         if ((paramArrayOfInt[j] == 16) && (paramArrayOfInt[(j + 1)] == 16)) {
/* 448 */           j++;
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 454 */       int[] arrayOfInt2 = new int[i];
/* 455 */       System.arraycopy(arrayOfInt1, 0, arrayOfInt2, 0, arrayOfInt2.length);
/* 456 */       return arrayOfInt2;
/*     */     }
/*     */ 
/*     */     private int[] addControlBytes(int[] paramArrayOfInt)
/*     */     {
/* 468 */       Contract.preNonNull(paramArrayOfInt);
/* 469 */       int[] arrayOfInt1 = new int[paramArrayOfInt.length * 2];
/*     */ 
/* 472 */       int i = 0;
/* 473 */       for (int j = 0; j < paramArrayOfInt.length; j++)
/*     */       {
/* 475 */         arrayOfInt1[(i++)] = paramArrayOfInt[j];
/*     */ 
/* 477 */         if (paramArrayOfInt[j] == 16) {
/* 478 */           arrayOfInt1[(i++)] = paramArrayOfInt[j];
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 483 */       int[] arrayOfInt2 = new int[i];
/* 484 */       System.arraycopy(arrayOfInt1, 0, arrayOfInt2, 0, arrayOfInt2.length);
/* 485 */       return arrayOfInt2;
/*     */     }
/*     */ 
/*     */     private int[] createFrame()
/*     */     {
/* 495 */       int i = this.m_recNumber - 1;
/* 496 */       int[] arrayOfInt1 = { getCommand().charAt(0), getCommand().charAt(1), MedicalDevice.Util.getHighByte(i), MedicalDevice.Util.getLowByte(i) };
/*     */ 
/* 504 */       int j = calcCRC(arrayOfInt1);
/*     */ 
/* 507 */       int[] arrayOfInt2 = addControlBytes(arrayOfInt1);
/*     */ 
/* 510 */       int[] arrayOfInt3 = { 16, 2 };
/* 511 */       int[] arrayOfInt4 = { 16, 3, j };
/* 512 */       int[] arrayOfInt5 = new int[0];
/* 513 */       arrayOfInt5 = MedicalDevice.Util.concat(arrayOfInt5, arrayOfInt3);
/* 514 */       arrayOfInt5 = MedicalDevice.Util.concat(arrayOfInt5, arrayOfInt2);
/* 515 */       arrayOfInt5 = MedicalDevice.Util.concat(arrayOfInt5, arrayOfInt4);
/* 516 */       return arrayOfInt5;
/*     */     }
/*     */ 
/*     */     private void verifyLength()
/*     */       throws BadDeviceValueException
/*     */     {
/* 526 */       int[] arrayOfInt = getRawData();
/* 527 */       if ((arrayOfInt.length != 17) && (arrayOfInt.length != 7))
/*     */       {
/* 529 */         throw new BadDeviceValueException("verifyLength: ERROR - incorrect data length for HR command " + getCommand() + " (" + getDescription() + "); reply=<" + MedicalDevice.Util.getHex(arrayOfInt) + ">");
/*     */       }
/*     */     }
/*     */ 
/*     */     private void verifyFrame()
/*     */       throws BadDeviceCommException
/*     */     {
/* 544 */       int[] arrayOfInt = getRawData();
/*     */ 
/* 549 */       int i = (arrayOfInt[0] == 16) && (arrayOfInt[1] == 2) && (arrayOfInt[(arrayOfInt.length - 3)] == 16) && (arrayOfInt[(arrayOfInt.length - 2)] == 3) ? 1 : 0;
/*     */ 
/* 555 */       if (i == 0)
/* 556 */         throw new BadDeviceCommException("verifyFrame: ERROR - bad frame read for HR command " + getCommand() + " (" + getDescription() + "); reply=<" + MedicalDevice.Util.getHex(arrayOfInt) + ">" + (arrayOfInt[0] == 16 ? "" : "; reply[0] != DLE") + (arrayOfInt[1] == 2 ? "" : "; reply[1] != STX") + (arrayOfInt[(arrayOfInt.length - 3)] == 16 ? "" : "; reply[reply.length - 3] != DLE") + (arrayOfInt[(arrayOfInt.length - 2)] == 3 ? "" : "; reply[reply.length - 2] != EXT"));
/*     */     }
/*     */ 
/*     */     private int calcCRC(int[] paramArrayOfInt)
/*     */     {
/* 574 */       Contract.preNonNull(paramArrayOfInt);
/*     */ 
/* 576 */       int[] arrayOfInt = MedicalDevice.Util.concat(paramArrayOfInt, new int[1]);
/* 577 */       return MedicalDevice.Util.computeCRC7(arrayOfInt, 0, arrayOfInt.length);
/*     */     }
/*     */ 
/*     */     private int verifyCRC()
/*     */       throws BadDeviceValueException
/*     */     {
/* 588 */       int[] arrayOfInt1 = getRawData();
/*     */ 
/* 593 */       int i = arrayOfInt1.length - 1;
/* 594 */       int j = arrayOfInt1[i];
/*     */ 
/* 597 */       int[] arrayOfInt2 = new int[arrayOfInt1.length - 5 + 1];
/*     */ 
/* 600 */       System.arraycopy(arrayOfInt1, 2, arrayOfInt2, 0, arrayOfInt2.length - 1);
/*     */ 
/* 603 */       arrayOfInt2[(arrayOfInt2.length - 1)] = 0;
/*     */ 
/* 605 */       int k = MedicalDevice.Util.computeCRC7(arrayOfInt2, 0, arrayOfInt2.length) & 0xFF;
/*     */ 
/* 607 */       if (j != k) {
/* 608 */         throw new BadDeviceValueException("verifyCRC: cmd " + getCommand() + " (" + getDescription() + ")" + " resulted in bad CRC value of " + MedicalDevice.Util.getHex(j) + " (expected " + MedicalDevice.Util.getHex(k) + ") " + "(byte data = " + "<" + MedicalDevice.Util.getHex(arrayOfInt1) + ">)");
/*     */       }
/*     */ 
/* 614 */       return j;
/*     */     }
/*     */   }
/*     */ 
/*     */   private class HRProcedureCommand extends LSMeter.Command
/*     */   {
/*     */     private static final int MAX_RECORDS = 3064;
/*     */     private static final int LEN_HR_PROCEDURE = 52088;
/*     */ 
/*     */     HRProcedureCommand()
/*     */     {
/* 187 */       super("HRHR Procedure", "Read All HR Data", 52088);
/*     */     }
/*     */ 
/*     */     void execute()
/*     */       throws BadDeviceCommException, BadDeviceValueException
/*     */     {
/* 203 */       int i = 0;
/* 204 */       int[] arrayOfInt = new int[0];
/*     */ 
/* 206 */       for (int j = 1; (j <= 3064) && (i == 0); j++)
/*     */       {
/* 208 */         LSOneTouchUltraSmart.HRCommand localHRCommand = new LSOneTouchUltraSmart.HRCommand(LSOneTouchUltraSmart.this, j);
/* 209 */         localHRCommand.execute();
/*     */ 
/* 212 */         arrayOfInt = MedicalDevice.Util.concat(arrayOfInt, localHRCommand.getRawData());
/* 213 */         LSOneTouchUltraSmart.this.incBytesReadThusFar(localHRCommand.getRawData().length);
/*     */ 
/* 216 */         i = (localHRCommand.isLastRecord()) || (LSOneTouchUltraSmart.this.isHaltRequested()) ? 1 : 0;
/*     */       }
/*     */ 
/* 220 */       setRawData(arrayOfInt);
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.deviceportreader.LSOneTouchUltraSmart
 * JD-Core Version:    0.6.0
 */