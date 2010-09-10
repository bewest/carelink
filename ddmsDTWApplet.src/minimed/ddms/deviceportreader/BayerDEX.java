/*     */ package minimed.ddms.deviceportreader;
/*     */ 
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.Set;
/*     */ import java.util.StringTokenizer;
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ final class BayerDEX extends BayerMeter
/*     */ {
/*     */   static final int BEEPER_MASK = 1;
/*     */   static final int REF_METHOD_MASK = 16;
/*     */   static final int TEMP_UNITS_MASK = 8;
/*     */   static final int TIME_FORMAT_MASK = 2;
/*     */   static final int UNITS_MASK = 4;
/*     */   static final int METER_ID = 1;
/*     */   private static final int MAX_RECORD_COUNT = 100;
/*     */   private static final String PRODUCT_CODE = "Bayer3950";
/*     */ 
/*     */   BayerDEX()
/*     */   {
/*  77 */     super(100);
/*  78 */     this.m_description = "Bayer Glucometer DEX Meter";
/*  79 */     logInfo(this, "creating interface to the '" + this.m_description + "', package version " + getPackageVersion());
/*     */ 
/*  81 */     this.m_deviceClassID = 8;
/*  82 */     this.m_productCodes.add("Bayer3950");
/*     */   }
/*     */ 
/*     */   void decodeCurrentSettings() throws BadDeviceValueException
/*     */   {
/*  95 */     String str = null;
/*     */ 
/*  98 */     Contract.pre(this.m_currentSettings != null);
/*     */ 
/* 101 */     StringTokenizer localStringTokenizer = new StringTokenizer(this.m_currentSettings, "|\\^&\r\n");
/*     */     int i;
/*     */     try
/*     */     {
/* 106 */       str = localStringTokenizer.nextToken();
/* 107 */       str = localStringTokenizer.nextToken();
/* 108 */       i = new Integer(str).intValue();
/*     */     } catch (NoSuchElementException localNoSuchElementException) {
/* 110 */       throw new BadDeviceValueException("Bad current setting string '" + this.m_currentSettings + "' received (last token='" + str + "')");
/*     */     }
/*     */     catch (NumberFormatException localNumberFormatException) {
/* 113 */       throw new BadDeviceValueException("Bad current setting string format '" + this.m_currentSettings + "' received (last token='" + str + "')");
/*     */     }
/*     */ 
/* 118 */     this.m_settingBeeperEnable = ((i & 0x1) == 1 ? new Boolean(true) : new Boolean(false));
/*     */ 
/* 122 */     this.m_settingTimeFormatIsAMPM = ((i & 0x2) == 2 ? new Boolean(false) : new Boolean(true));
/*     */ 
/* 126 */     this.m_settingUnitsIsMGDL = ((i & 0x4) == 4 ? new Boolean(false) : new Boolean(true));
/*     */ 
/* 131 */     this.m_settingTempUnitsIsFahrenheit = ((i & 0x8) == 8 ? new Boolean(true) : new Boolean(false));
/*     */ 
/* 137 */     this.m_settingReferenceIsPlasma = ((i & 0x10) == 16 ? new Boolean(false) : new Boolean(true));
/*     */ 
/* 141 */     this.m_settingDateFormatIsMD = null;
/*     */ 
/* 143 */     logInfo(this, "decodeCurrentSettings: *** CURRENT SETTINGS *** (input is '" + this.m_currentSettings + "')");
/*     */ 
/* 145 */     logInfo(this, "decodeCurrentSettings: Beeper Enable = " + this.m_settingBeeperEnable);
/* 146 */     logInfo(this, "decodeCurrentSettings: Time Display is AM / PM = " + this.m_settingTimeFormatIsAMPM);
/*     */ 
/* 148 */     logInfo(this, "decodeCurrentSettings: Units is mm/dL = " + this.m_settingUnitsIsMGDL);
/* 149 */     logInfo(this, "decodeCurrentSettings: Temp Units is Fahrenheit = " + this.m_settingTempUnitsIsFahrenheit);
/*     */ 
/* 151 */     logInfo(this, "decodeCurrentSettings: Reference Method is Plasma = " + this.m_settingReferenceIsPlasma);
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.deviceportreader.BayerDEX
 * JD-Core Version:    0.6.0
 */