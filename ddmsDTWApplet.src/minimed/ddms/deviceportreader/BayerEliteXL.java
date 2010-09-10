/*     */ package minimed.ddms.deviceportreader;
/*     */ 
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.Set;
/*     */ import java.util.StringTokenizer;
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ final class BayerEliteXL extends BayerMeter
/*     */ {
/*     */   static final int BEEPER_MASK = 1;
/*     */   static final int DATE_FORMAT_MASK = 32;
/*     */   static final int TIME_FORMAT_MASK = 2;
/*     */   static final int UNITS_MASK = 4;
/*     */   static final int METER_ID = 2;
/*     */   private static final String PRODUCT_CODE = "Bayer3883";
/*     */   private static final int MAX_RECORD_COUNT = 120;
/*     */ 
/*     */   BayerEliteXL()
/*     */   {
/*  72 */     super(120);
/*  73 */     this.m_description = "Bayer Glucometer Elite XL Meter";
/*  74 */     logInfo(this, "creating interface to the '" + this.m_description + "', package version " + getPackageVersion());
/*     */ 
/*  76 */     this.m_deviceClassID = 9;
/*  77 */     this.m_productCodes.add("Bayer3883");
/*     */   }
/*     */ 
/*     */   void decodeCurrentSettings() throws BadDeviceValueException
/*     */   {
/*  90 */     String str = null;
/*     */ 
/*  93 */     Contract.pre(this.m_currentSettings != null);
/*     */ 
/*  96 */     StringTokenizer localStringTokenizer = new StringTokenizer(this.m_currentSettings, "|\\^&\r\n");
/*     */     int i;
/*     */     try
/*     */     {
/* 101 */       str = localStringTokenizer.nextToken();
/* 102 */       str = localStringTokenizer.nextToken();
/* 103 */       i = MedicalDevice.Util.convertHexToDec(str);
/*     */     } catch (NoSuchElementException localNoSuchElementException) {
/* 105 */       throw new BadDeviceValueException("Bad current setting string '" + this.m_currentSettings + "' received (last token='" + str + "')");
/*     */     }
/*     */     catch (NumberFormatException localNumberFormatException) {
/* 108 */       throw new BadDeviceValueException("Bad current setting string format '" + this.m_currentSettings + "' received (last token='" + str + "')");
/*     */     }
/*     */ 
/* 113 */     this.m_settingBeeperEnable = ((i & 0x1) == 1 ? new Boolean(true) : new Boolean(false));
/*     */ 
/* 117 */     this.m_settingTimeFormatIsAMPM = ((i & 0x2) == 2 ? new Boolean(false) : new Boolean(true));
/*     */ 
/* 121 */     this.m_settingUnitsIsMGDL = ((i & 0x4) == 4 ? new Boolean(false) : new Boolean(true));
/*     */ 
/* 126 */     this.m_settingDateFormatIsMD = ((i & 0x20) == 32 ? new Boolean(false) : new Boolean(true));
/*     */ 
/* 129 */     this.m_settingTempUnitsIsFahrenheit = null;
/* 130 */     this.m_settingReferenceIsPlasma = null;
/*     */ 
/* 132 */     logInfo(this, "decodeCurrentSettings: *** CURRENT SETTINGS *** (input is '" + this.m_currentSettings + "')");
/*     */ 
/* 134 */     logInfo(this, "decodeCurrentSettings: Beeper Enable = " + this.m_settingBeeperEnable);
/* 135 */     logInfo(this, "decodeCurrentSettings: Time Display is AM / PM = " + this.m_settingTimeFormatIsAMPM);
/*     */ 
/* 137 */     logInfo(this, "decodeCurrentSettings: Units is mm/dL = " + this.m_settingUnitsIsMGDL);
/* 138 */     logInfo(this, "decodeCurrentSettings: Time Display is Month.Day = " + this.m_settingDateFormatIsMD);
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.deviceportreader.BayerEliteXL
 * JD-Core Version:    0.6.0
 */