/*     */ package minimed.ddms.deviceportreader;
/*     */ 
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.StringTokenizer;
/*     */ import java.util.Vector;
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ final class LSOneTouchUltra extends LSMeter
/*     */ {
/*     */   public static final int SNAPSHOT_FORMAT_ID_ULTRA = 155;
/*     */   public static final int SNAPSHOT_FORMAT_ID_ULTRA2 = 157;
/*     */   public static final int SNAPSHOT_FORMAT_ID_ULTRALINK = 158;
/*     */   static final String CMD_GET_TIME_SETTING = "DMST?";
/*     */   static final String TIME_SETTING_REPLY = "ST?";
/*     */   private static final String CMD_GET_UNITS_SETTING = "DMSU?";
/*     */   private static final String CMD_INIT_COMMUNICATIONS = "DM?";
/*     */   private static final String CMD_GET_RF_ID = "DMID\r";
/*     */   private static final int ONE_SECOND = 1000;
/*     */   private static final int LEN_GET_DATALOG_ULTRA2 = 31000;
/*     */   private static final int SNAPSHOT_FORMAT_ID_UNKNOWN = -1;
/*     */   private String m_rfID;
/*     */ 
/*     */   LSOneTouchUltra()
/*     */   {
/*  78 */     this.m_description = "LifeScan One Touch Ultra / Ultra2 / UltraLink Meter";
/*  79 */     logInfo(this, "creating interface to the '" + this.m_description + "', package version " + getPackageVersion());
/*     */ 
/*  81 */     this.m_deviceClassID = 5;
/*  82 */     this.m_snapshotFormatID = -1;
/*  83 */     this.m_snapshotCreator = new SnapshotCreator(null);
/*     */ 
/*  86 */     this.m_cmdGetSettings = new LSMeter.Command(this, "DMSU?", "Read Current Units Settings", 50);
/*     */ 
/*  88 */     this.m_cmdGetSettings2 = new LSMeter.Command(this, "DMST?", "Read Current Time Settings", 50);
/*     */   }
/*     */ 
/*     */   void initDeviceAfterSerialNumberKnown()
/*     */     throws BadDeviceValueException
/*     */   {
/* 100 */     boolean bool1 = getSerialNumber().endsWith("Y");
/* 101 */     boolean bool2 = getSerialNumber().startsWith("H");
/*     */ 
/* 104 */     if ((bool1) && (bool2)) {
/* 105 */       throw new BadDeviceValueException("Meter is both Ultra2 and UltraLink: " + getSerialNumber());
/*     */     }
/*     */ 
/* 109 */     int i = (!bool1) && (!bool2) ? 1 : 0;
/*     */ 
/* 112 */     Contract.invariant((i != 0) || (bool1) || (bool2));
/*     */ 
/* 114 */     if (i != 0)
/* 115 */       this.m_snapshotFormatID = 155;
/* 116 */     else if (bool1)
/* 117 */       this.m_snapshotFormatID = 157;
/*     */     else {
/* 119 */       this.m_snapshotFormatID = 158;
/*     */     }
/* 121 */     logInfo(this, "initDeviceAfterSerialNumberKnown: meter model is " + (bool2 ? "ULTRALINK" : bool1 ? "ULTRA2" : "ULTRA"));
/*     */ 
/* 125 */     if ((bool1) || (bool2)) {
/* 126 */       this.m_cmdGetDatalog.setRawData(new int[31000]);
/*     */     }
/*     */ 
/* 130 */     if (bool2) {
/* 131 */       LSMeter.Command localCommand = new LSMeter.Command(this, "DMID\r", "Read RF ID", 50, new ReplyDecoder()
/*     */       {
/*     */         public void decodeReply(LSMeter.AbstractCommand paramAbstractCommand)
/*     */           throws BadDeviceValueException
/*     */         {
/* 142 */           LSOneTouchUltra.access$102(LSOneTouchUltra.this, MedicalDevice.Util.makeString(paramAbstractCommand.getRawData()));
/* 143 */           MedicalDevice.logInfo(this, "decodeReply: RF ID is '" + LSOneTouchUltra.this.getRFID(LSOneTouchUltra.this.m_rfID) + "'");
/*     */         }
/*     */       });
/* 147 */       getCommandCollection().addElement(localCommand);
/*     */     }
/*     */     else {
/* 150 */       this.m_rfID = "";
/*     */     }
/*     */   }
/*     */ 
/*     */   void decodeCurrentSettings()
/*     */     throws BadDeviceValueException
/*     */   {
/* 165 */     Contract.pre(this.m_currentSettings != null);
/*     */ 
/* 172 */     boolean bool = this.m_currentSettings.indexOf("MG/DL") >= 0;
/* 173 */     int i = this.m_currentSettings.indexOf("MMOL/L") >= 0 ? 1 : 0;
/*     */ 
/* 176 */     if (((!bool) && (i == 0)) || ((bool) && (i != 0))) {
/* 177 */       throw new BadDeviceValueException("Bad current settings string received (units): '" + this.m_currentSettings);
/*     */     }
/*     */ 
/* 181 */     this.m_settingUnitsIsMGDL = new Boolean(bool);
/*     */ 
/* 183 */     logInfo(this, "decodeCurrentSettings: *** CURRENT SETTINGS ***");
/* 184 */     logInfo(this, "decodeCurrentSettings: Units Is mm/dL = " + this.m_settingUnitsIsMGDL);
/*     */   }
/*     */ 
/*     */   void decodeCurrentSettings2()
/*     */     throws BadDeviceValueException
/*     */   {
/* 198 */     Contract.pre(this.m_currentSettings2 != null);
/* 199 */     Contract.pre(this.m_currentSettings2.length() > 1);
/*     */ 
/* 206 */     boolean bool = this.m_currentSettings2.indexOf("AM/PM") >= 0;
/* 207 */     int i = this.m_currentSettings2.indexOf("24:00") >= 0 ? 1 : 0;
/*     */ 
/* 210 */     if (((!bool) && (i == 0)) || ((bool) && (i != 0)))
/*     */     {
/* 212 */       throw new BadDeviceValueException("Bad current settings string received (time format): '" + this.m_currentSettings2);
/*     */     }
/*     */ 
/* 217 */     this.m_settingTimeFormatIsAMPM = new Boolean(bool);
/*     */ 
/* 219 */     logInfo(this, "decodeCurrentSettings: *** CURRENT SETTINGS2 ***");
/* 220 */     logInfo(this, "decodeCurrentSettings: Time Format is AM/PM = " + this.m_settingTimeFormatIsAMPM);
/*     */   }
/*     */ 
/*     */   void initDevice()
/*     */     throws BadDeviceCommException, BadDeviceValueException
/*     */   {
/* 234 */     Contract.pre(getRS232Port() != null);
/* 235 */     Contract.pre(getRS232Port().isOpen());
/*     */ 
/* 237 */     setPhase(4);
/* 238 */     LSMeter.Command localCommand = new LSMeter.Command(this, "DM?", "Initialize Communications", 0);
/*     */ 
/* 240 */     localCommand.execute();
/* 241 */     MedicalDevice.Util.sleepMS(1000);
/*     */   }
/*     */ 
/*     */   private String getRFID(String paramString) throws BadDeviceValueException
/*     */   {
/* 253 */     Contract.preNonNull(paramString);
/*     */     String str;
/*     */     try
/*     */     {
/* 258 */       StringTokenizer localStringTokenizer = new StringTokenizer(paramString, "\"");
/* 259 */       localStringTokenizer.nextToken();
/* 260 */       str = localStringTokenizer.nextToken();
/*     */     } catch (NoSuchElementException localNoSuchElementException) {
/* 262 */       throw new BadDeviceValueException("RF ID reply '" + paramString + "'");
/*     */     }
/*     */ 
/* 265 */     return str; } 
/*     */   private final class SnapshotCreator extends LSMeter.SnapshotCreator { static final int SNAPCODE_CURRENT_UNITS_SETTING = 1;
/*     */     static final int SNAPCODE_CURRENT_TIMEFORMAT_SETTING = 2;
/*     */     static final int SNAPCODE_DATALOG = 3;
/*     */     static final int SNAPCODE_RFID = 4;
/*     */     static final int LAST_SNAPCODE = 4;
/*     */     private static final String ULTRA2_ID = "Y";
/*     */     private static final String ULTRALINK_ID = "H";
/*     */     private final LSOneTouchUltra this$0;
/*     */ 
/* 273 */     private SnapshotCreator() { super(); this.this$0 = this$1;
/*     */     }
/*     */ 
/*     */     void createSnapshotBody()
/*     */     {
/* 303 */       this.this$0.m_snapshot = new Snapshot(this.this$0.m_snapshotFormatID, 1, pad(this.this$0.m_firmwareVersion), pad(this.this$0.m_serialNumber), pad(this.this$0.m_realTimeClock));
/*     */ 
/* 306 */       MedicalDevice.logInfo(this, "createSnapshot: creating snapshot");
/*     */ 
/* 310 */       this.this$0.m_snapshot.addElement(1, this.this$0.m_currentSettings);
/* 311 */       this.this$0.m_snapshot.addElement(2, this.this$0.m_currentSettings2);
/*     */ 
/* 315 */       this.this$0.m_snapshot.addElement(3, this.this$0.m_datalog);
/*     */ 
/* 318 */       if (this.this$0.m_snapshotFormatID == 158)
/* 319 */         this.this$0.m_snapshot.addElement(4, pad('\r' + this.this$0.m_rfID.trim()));
/*     */     }
/*     */ 
/*     */     SnapshotCreator(LSOneTouchUltra.1 arg2)
/*     */     {
/* 273 */       this();
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.deviceportreader.LSOneTouchUltra
 * JD-Core Version:    0.6.0
 */