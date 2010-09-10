/*     */ package minimed.ddms.deviceportreader;
/*     */ 
/*     */ import java.util.Vector;
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ final class LSFastTake extends LSMeter
/*     */ {
/*     */   public static final int SNAPSHOT_FORMAT_ID = 154;
/*     */   private static final int ONE_SECOND = 1000;
/*     */ 
/*     */   LSFastTake()
/*     */   {
/*  47 */     this.m_description = "LifeScan FastTake Meter";
/*  48 */     logInfo(this, "creating interface to the '" + this.m_description + "', package version " + getPackageVersion());
/*     */ 
/*  50 */     this.m_deviceClassID = 6;
/*  51 */     this.m_snapshotFormatID = 154;
/*  52 */     this.m_snapshotCreator = new SnapshotCreator(null);
/*     */   }
/*     */ 
/*     */   Vector createCommandList()
/*     */   {
/*  63 */     Vector localVector = new Vector();
/*     */ 
/*  67 */     localVector.addElement(this.m_cmdReadRealTimeClock);
/*  68 */     localVector.addElement(this.m_cmdGetFirmwareVersion);
/*  69 */     localVector.addElement(this.m_cmdGetSerialNumber);
/*  70 */     localVector.addElement(this.m_cmdGetDatalog);
/*  71 */     return localVector;
/*     */   }
/*     */ 
/*     */   void initDevice()
/*     */     throws BadDeviceCommException, BadDeviceValueException
/*     */   {
/*  84 */     Contract.preNonNull(getRS232Port());
/*  85 */     Contract.pre(getRS232Port().isOpen());
/*     */ 
/*  87 */     setPhase(4);
/*     */ 
/*  93 */     this.m_cmdGetSettings2 = new LSMeter.Command(this, "DMST?", "Read Current Time Settings (Ultra Detection only)", 50);
/*     */ 
/*  99 */     this.m_cmdGetSettings2.execute();
/* 100 */     MedicalDevice.Util.sleepMS(1000);
/* 101 */     this.m_cmdGetSettings2.execute();
/*     */ 
/* 103 */     String str = MedicalDevice.Util.makeString(this.m_cmdGetSettings2.getRawData());
/*     */ 
/* 106 */     if (str.indexOf("ST?") > -1)
/*     */     {
/* 109 */       throw new BadDeviceCommException("Not a FastTake meter.");
/*     */     }
/*     */ 
/* 113 */     this.m_cmdGetSettings2 = null;
/*     */   }
/*     */ 
/*     */   void decodeCurrentSettings()
/*     */     throws BadDeviceValueException
/*     */   {
/* 126 */     this.m_settingDateFormatIsMDY = null;
/* 127 */     this.m_settingTimeFormatIsAMPM = null;
/*     */ 
/* 129 */     logInfo(this, "decodeCurrentSettings: *** CURRENT SETTINGS *** (none)");
/*     */   }
/*     */ 
/*     */   private final class SnapshotCreator extends LSMeter.SnapshotCreator {
/*     */     private final LSFastTake this$0;
/*     */ 
/*     */     private SnapshotCreator() {
/* 137 */       super(); this.this$0 = this$1;
/*     */     }
/*     */ 
/*     */     void createSnapshotBody()
/*     */     {
/* 152 */       this.this$0.m_snapshot = new Snapshot(this.this$0.m_snapshotFormatID, 1, pad(this.this$0.m_firmwareVersion), pad(this.this$0.m_serialNumber), pad(this.this$0.m_realTimeClock));
/*     */ 
/* 155 */       MedicalDevice.logInfo(this, "createSnapshot: creating snapshot");
/*     */ 
/* 159 */       this.this$0.m_snapshot.addElement(2, this.this$0.m_datalog);
/*     */     }
/*     */ 
/*     */     SnapshotCreator(LSFastTake.1 arg2)
/*     */     {
/* 137 */       this();
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.deviceportreader.LSFastTake
 * JD-Core Version:    0.6.0
 */