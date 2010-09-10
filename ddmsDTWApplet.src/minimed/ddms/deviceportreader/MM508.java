/*     */ package minimed.ddms.deviceportreader;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ final class MM508 extends MM508Command
/*     */ {
/*     */   private static final int SNAPSHOT_FORMAT_ID = -1;
/*     */   static final byte CMD_SUSPEND_RESUME = 16;
/*     */   static final byte CMD_READ_RTC = 32;
/*     */   static final byte CMD_READ_PUMP_ID = 33;
/*     */   static final byte CMD_READ_FIRMWARE_VER = 37;
/*     */   static final byte CMD_READ_ERROR_STATUS = 38;
/*     */   static final byte CMD_READ_REMOTE_CTRL_ID = 46;
/*     */   static final int REC_SIZE_READ_PUMP_ID = 10;
/*     */   static final int REC_SIZE_READ_FIRMWARE_VER = 8;
/*     */   static final int REC_SIZE_READ_RTC = 7;
/*     */   static final int REC_SIZE_ERROR_STATUS = 1;
/*     */   static final int REC_SIZE_NONE = 0;
/*     */   private static final int REC_SIZE_REMOTE_ID = 7;
/*     */   private static final int MIN_YEAR = 1998;
/*     */   private static final int MAX_YEAR = 2097;
/*     */   private static final int NO_REPLY = 0;
/*     */   private static final int MIN_ERROR_CODE = 0;
/*     */   private static final int MAX_ERROR_CODE = 58;
/*     */   private static final int REMOTE_ID1 = 0;
/*     */   private static final int REMOTE_ID2 = 1;
/*     */   private static final int REMOTE_ID3 = 2;
/*     */   private MMPump m_pump;
/*     */ 
/*     */   MM508()
/*     */   {
/* 101 */     this.m_description = "MiniMed MMT-508 Insulin Pump";
/* 102 */     logInfo(this, "creating interface to the '" + this.m_description + "', package version " + getPackageVersion());
/*     */ 
/* 104 */     this.m_deviceClassID = 0;
/* 105 */     this.m_snapshotFormatID = -1;
/* 106 */     this.m_minYear = 1998;
/* 107 */     this.m_maxYear = 2097;
/* 108 */     this.m_linkDevice = 18;
/*     */ 
/* 115 */     this.m_sequenceNumberInc = 0;
/*     */ 
/* 117 */     this.m_cmdSetAccessCode = new MM508Command.Command(this, 1, "Set Access Code", 0, 0, 0);
/*     */ 
/* 120 */     this.m_cmdSetSuspend = new MM508Command.Command(this, 16, "Set Suspend", 0, 0, 1, 1, 0);
/*     */ 
/* 123 */     this.m_cmdCancelSuspend = new MM508Command.Command(this, 16, "Cancel Suspend", 0, 0, 0, 1, 0);
/*     */ 
/* 126 */     this.m_cmdReadPumpId = new MM508Command.Command(this, 33, "Read Pump ID", 10, 1, 0);
/*     */ 
/* 129 */     this.m_cmdReadFirmwareVersion = new MM508Command.Command(this, 37, "Read Firmware Version", 8, 1, 0);
/*     */ 
/* 133 */     this.m_cmdReadRealTimeClock = new MM508Command.Command(this, 32, "Read Real Time Clock", 7, 1, 0);
/*     */ 
/* 137 */     this.m_cmdReadErrorStatus = new MM508Command.Command(this, 38, "Read Pump Error Status  (current alarm code)", 1, 1, 0);
/*     */ 
/* 141 */     this.m_cmdReadRemoteID1 = new MM508Command.Command(this, 46, "Read Remote Control Id 1", 7, 1, 0, 1, 0);
/*     */ 
/* 144 */     this.m_cmdReadRemoteID2 = new MM508Command.Command(this, 46, "Read Remote Control Id 2", 7, 1, 1, 1, 0);
/*     */ 
/* 147 */     this.m_cmdReadRemoteID3 = new MM508Command.Command(this, 46, "Read Remote Control Id 3", 7, 1, 2, 1, 0);
/*     */ 
/* 150 */     setState(1);
/*     */   }
/*     */ 
/*     */   public InputStream createSnapshot()
/*     */     throws BadDeviceValueException, IOException
/*     */   {
/* 169 */     InputStream localInputStream = this.m_pump.createSnapshot();
/* 170 */     this.m_snapshot = this.m_pump.getSnapshot();
/* 171 */     return localInputStream;
/*     */   }
/*     */ 
/*     */   void executeSecondaryCommands(DeviceListener paramDeviceListener, int paramInt, String paramString)
/*     */     throws BadDeviceCommException, BadDeviceValueException, IOException, ConnectToPumpException
/*     */   {
/* 194 */     if (MM508u.isFirmwareVersionSupported(this.m_firmwareVersion)) {
/* 195 */       this.m_pump = new MM508u(this.m_serialNumber, this.m_cmdReadPumpId.m_rawData, this.m_firmwareVersion, this.m_cmdReadFirmwareVersion.m_rawData, this.m_timeStamp, this.m_cmdReadRealTimeClock.m_rawData, this.m_cmdReadErrorStatus.m_rawData, this.m_cmdReadRemoteID1.m_rawData, this.m_cmdReadRemoteID2.m_rawData, this.m_cmdReadRemoteID3.m_rawData);
/*     */     }
/* 200 */     else if (MM508c.isFirmwareVersionSupported(this.m_firmwareVersion)) {
/* 201 */       this.m_pump = new MM508c(this.m_serialNumber, this.m_cmdReadPumpId.m_rawData, this.m_firmwareVersion, this.m_cmdReadFirmwareVersion.m_rawData, this.m_timeStamp, this.m_cmdReadRealTimeClock.m_rawData, this.m_cmdReadErrorStatus.m_rawData, this.m_cmdReadRemoteID1.m_rawData, this.m_cmdReadRemoteID2.m_rawData, this.m_cmdReadRemoteID3.m_rawData);
/*     */     }
/*     */     else
/*     */     {
/* 207 */       throw new BadDeviceValueException("ERROR - '" + this.m_firmwareVersion + "' is an unsupported 508 firmware version.");
/*     */     }
/*     */ 
/* 213 */     this.m_pump.setCommunicationsLink(getCommunicationsLink());
/* 214 */     this.m_pump.readData(paramDeviceListener, paramInt, paramString);
/*     */   }
/*     */ 
/*     */   void decodeReply(MMPump.Command paramCommand)
/*     */     throws BadDeviceValueException
/*     */   {
/* 228 */     switch (paramCommand.m_commandCode) {
/*     */     case 33:
/* 230 */       decodeSerialNumber(paramCommand.m_rawData);
/* 231 */       break;
/*     */     case 37:
/* 233 */       decodeFirmwareVersion(paramCommand.m_rawData);
/* 234 */       break;
/*     */     case 32:
/* 236 */       decodeCurrentTimeStamp(paramCommand.m_rawData);
/* 237 */       break;
/*     */     case 38:
/* 239 */       decodeErrorStatus(paramCommand.m_rawData);
/* 240 */       break;
/*     */     case 46:
/* 242 */       decodeRemoteId(paramCommand.m_rawData);
/* 243 */       break;
/*     */     case 34:
/*     */     case 35:
/*     */     case 36:
/*     */     case 39:
/*     */     case 40:
/*     */     case 41:
/*     */     case 42:
/*     */     case 43:
/*     */     case 44:
/*     */     case 45:
/*     */     }
/*     */   }
/*     */ 
/*     */   private void decodeRemoteId(int[] paramArrayOfInt)
/*     */     throws BadDeviceValueException
/*     */   {
/* 260 */     Contract.pre(paramArrayOfInt != null);
/*     */ 
/* 263 */     int i = paramArrayOfInt[0];
/* 264 */     MedicalDevice.Util.verifyDeviceValue(i, 0, 2, "Remote ID");
/*     */ 
/* 267 */     int[] arrayOfInt = new int[6];
/*     */ 
/* 269 */     System.arraycopy(paramArrayOfInt, 1, arrayOfInt, 0, arrayOfInt.length);
/*     */ 
/* 271 */     String str = MedicalDevice.Util.makeString(arrayOfInt);
/*     */ 
/* 273 */     switch (i) {
/*     */     case 0:
/* 275 */       this.m_settingRemoteID1 = str;
/* 276 */       break;
/*     */     case 1:
/* 278 */       this.m_settingRemoteID2 = str;
/* 279 */       break;
/*     */     case 2:
/* 281 */       this.m_settingRemoteID3 = str;
/* 282 */       break;
/*     */     default:
/* 285 */       Contract.unreachable();
/* 286 */       return;
/*     */     }
/*     */ 
/* 289 */     logInfo(this, "decodeRemoteId: id " + (i + 1) + " = " + str);
/*     */   }
/*     */ 
/*     */   private void decodeCurrentTimeStamp(int[] paramArrayOfInt)
/*     */     throws BadDeviceValueException
/*     */   {
/* 302 */     Contract.pre(paramArrayOfInt != null);
/*     */ 
/* 305 */     int i = paramArrayOfInt[0];
/* 306 */     int j = paramArrayOfInt[1];
/* 307 */     int k = paramArrayOfInt[2];
/* 308 */     int m = MedicalDevice.Util.makeUnsignedShort(paramArrayOfInt[3], paramArrayOfInt[4]);
/* 309 */     int n = paramArrayOfInt[5];
/* 310 */     int i1 = paramArrayOfInt[6];
/*     */ 
/* 312 */     this.m_timeStamp = createTimestamp(i1, n, m, i, j, k);
/*     */ 
/* 315 */     logInfo(this, "decodeCurrentTimeStamp: current time stamp for device is " + this.m_timeStamp);
/*     */   }
/*     */ 
/*     */   private void decodeErrorStatus(int[] paramArrayOfInt)
/*     */     throws BadDeviceValueException
/*     */   {
/* 329 */     Contract.pre(paramArrayOfInt != null);
/*     */ 
/* 333 */     this.m_settingErrorStatus = paramArrayOfInt[0];
/*     */ 
/* 335 */     MedicalDevice.Util.verifyDeviceValue(this.m_settingErrorStatus, 0, 58, "Error Status");
/*     */ 
/* 338 */     logInfo(this, "decodeErrorStatus: error code = " + this.m_settingErrorStatus);
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.deviceportreader.MM508
 * JD-Core Version:    0.6.0
 */