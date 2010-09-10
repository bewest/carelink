/*     */ package minimed.ddms.deviceportreader;
/*     */ 
/*     */ import java.util.Set;
/*     */ import java.util.Vector;
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ final class BayerBreeze extends BayerMeter
/*     */ {
/*     */   private static final String PRODUCT_CODE1 = "Bayer6115";
/*     */   private static final String PRODUCT_CODE2 = "Bayer6116";
/*     */   private static final String BREEZE1_FIRMWARE_VERSION = "1.";
/*     */   private static final int MAX_RECORD_COUNT_BREEZE1 = 100;
/*     */   private static final int MAX_RECORD_COUNT_BREEZE2 = 420;
/*     */ 
/*     */   BayerBreeze()
/*     */   {
/*  58 */     super(100);
/*  59 */     this.m_description = "Bayer Glucometer Breeze Meter";
/*  60 */     logInfo(this, "creating interface to the '" + this.m_description + "', package version " + getPackageVersion());
/*     */ 
/*  62 */     this.m_deviceClassID = 19;
/*  63 */     this.m_productCodes.add("Bayer6115");
/*  64 */     this.m_productCodes.add("Bayer6116");
/*     */   }
/*     */ 
/*     */   void initDeviceAfterSerialNumberKnown()
/*     */     throws BadDeviceValueException
/*     */   {
/*  77 */     Contract.preNonNull(this.m_firmwareVersion);
/*     */ 
/*  81 */     if (this.m_firmwareVersion.startsWith("1.")) {
/*  82 */       logInfo(this, "initDeviceAfterSerialNumberKnown: meter model is Breeze");
/*     */     } else {
/*  84 */       logInfo(this, "initDeviceAfterSerialNumberKnown: meter model is Breeze 2");
/*     */ 
/*  86 */       updateMaxRecordCount(420);
/*     */     }
/*     */   }
/*     */ 
/*     */   void createCommands()
/*     */   {
/*  95 */     this.m_cmdGetData = new BayerMeter.CommandGetData(this);
/*     */   }
/*     */ 
/*     */   Vector createCommandList()
/*     */   {
/* 103 */     Vector localVector = new Vector();
/*     */ 
/* 106 */     localVector.addElement(this.m_cmdGetData);
/* 107 */     return localVector;
/*     */   }
/*     */ 
/*     */   void addCurrSettingsElementToSnapshot(int paramInt)
/*     */   {
/*     */   }
/*     */ 
/*     */   void decodeCurrentSettings()
/*     */     throws BadDeviceValueException
/*     */   {
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.deviceportreader.BayerBreeze
 * JD-Core Version:    0.6.0
 */