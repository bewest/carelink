/*     */ package minimed.ddms.deviceportreader;
/*     */ 
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.StringTokenizer;
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ final class LSSureStep extends LSMeter
/*     */ {
/*     */   public static final int SNAPSHOT_FORMAT_ID = 153;
/*     */ 
/*     */   LSSureStep()
/*     */   {
/*  43 */     this.m_description = "LifeScan SureStep Meter";
/*  44 */     logInfo(this, "creating interface to the '" + this.m_description + "', package version " + getPackageVersion());
/*     */ 
/*  46 */     this.m_deviceClassID = 7;
/*  47 */     this.m_snapshotFormatID = 153;
/*     */   }
/*     */ 
/*     */   void decodeCurrentSettings()
/*     */     throws BadDeviceValueException
/*     */   {
/*  60 */     Contract.pre(this.m_currentSettings != null);
/*     */ 
/*  62 */     String str1 = null;
/*     */ 
/*  66 */     String str2 = MedicalDevice.Util.remainderOf(this.m_currentSettings, "S? ");
/*  67 */     if (str2 == null) {
/*  68 */       throw new BadDeviceValueException("Bad current setting string '" + this.m_currentSettings + "' received");
/*     */     }
/*     */ 
/*  72 */     StringTokenizer localStringTokenizer = new StringTokenizer(str2, " ");
/*     */     try
/*     */     {
/*  77 */       str1 = localStringTokenizer.nextToken();
/*  78 */       if (str1.charAt(0) != 'S') {
/*  79 */         throw new BadDeviceValueException("Bad current setting string '" + this.m_currentSettings + "' received (last token='" + str1 + "')");
/*     */       }
/*     */ 
/*  82 */       this.m_settingStripCalCode = new Character(str1.charAt(1));
/*     */ 
/*  85 */       str1 = localStringTokenizer.nextToken();
/*  86 */       if (str1.charAt(0) != 'B') {
/*  87 */         throw new BadDeviceValueException("Bad current setting string '" + this.m_currentSettings + "' received (last token='" + str1 + "')");
/*     */       }
/*     */ 
/*  90 */       this.m_settingBeeperEnable = (str1.charAt(1) == '0' ? new Boolean(true) : new Boolean(false));
/*     */ 
/*  94 */       str1 = localStringTokenizer.nextToken();
/*  95 */       if (str1.charAt(0) != 'U') {
/*  96 */         throw new BadDeviceValueException("Bad current setting string '" + this.m_currentSettings + "' received (last token='" + str1 + "')");
/*     */       }
/*     */ 
/*  99 */       this.m_settingUnitsIsMGDL = (str1.charAt(1) == '0' ? new Boolean(true) : new Boolean(false));
/*     */ 
/* 103 */       str1 = localStringTokenizer.nextToken();
/* 104 */       if (str1.charAt(0) != 'M') {
/* 105 */         throw new BadDeviceValueException("Bad current setting string '" + this.m_currentSettings + "' received (last token='" + str1 + "')");
/*     */       }
/*     */ 
/* 108 */       this.m_settingMemoryDisplayEnable = (str1.charAt(1) == '0' ? new Boolean(true) : new Boolean(false));
/*     */ 
/* 112 */       str1 = localStringTokenizer.nextToken();
/* 113 */       if (str1.charAt(0) != 'A') {
/* 114 */         throw new BadDeviceValueException("Bad current setting string '" + this.m_currentSettings + "' received (last token='" + str1 + "')");
/*     */       }
/*     */ 
/* 117 */       this.m_settingAverageDisplayEnable = (str1.charAt(1) == '0' ? new Boolean(true) : new Boolean(false));
/*     */ 
/* 121 */       str1 = localStringTokenizer.nextToken();
/* 122 */       if (str1.charAt(0) != 'T') {
/* 123 */         throw new BadDeviceValueException("Bad current setting string '" + this.m_currentSettings + "' received (last token='" + str1 + "')");
/*     */       }
/*     */ 
/* 126 */       this.m_settingTimeFormatIsAMPM = (str1.charAt(1) == '0' ? new Boolean(true) : new Boolean(false));
/*     */ 
/* 130 */       str1 = localStringTokenizer.nextToken();
/* 131 */       if (str1.charAt(0) != 'D') {
/* 132 */         throw new BadDeviceValueException("Bad current setting string '" + this.m_currentSettings + "' received (last token='" + str1 + "')");
/*     */       }
/*     */ 
/* 135 */       this.m_settingDateFormatIsMDY = (str1.charAt(1) == '0' ? new Boolean(true) : new Boolean(false));
/*     */     }
/*     */     catch (NoSuchElementException localNoSuchElementException) {
/* 138 */       logError(this, "Bad current setting string '" + this.m_currentSettings + "' received (last token='" + str1 + "'" + "; exception = " + localNoSuchElementException);
/*     */ 
/* 141 */       throw new BadDeviceValueException("Bad current setting string '" + this.m_currentSettings + "' received (last token='" + str1 + "'");
/*     */     }
/*     */     catch (StringIndexOutOfBoundsException localStringIndexOutOfBoundsException) {
/* 144 */       logError(this, "Bad current setting string '" + this.m_currentSettings + "' received (last token='" + str1 + "'" + "; exception = " + localStringIndexOutOfBoundsException);
/*     */ 
/* 147 */       throw new BadDeviceValueException("Bad current setting string '" + this.m_currentSettings + "' received (last token='" + str1 + "'");
/*     */     }
/*     */ 
/* 151 */     logInfo(this, "decodeCurrentSettings: *** CURRENT SETTINGS ***");
/* 152 */     logInfo(this, "decodeCurrentSettings: Strip Cal. Code = " + this.m_settingStripCalCode);
/*     */ 
/* 154 */     logInfo(this, "decodeCurrentSettings: Beeper Enable = " + this.m_settingBeeperEnable);
/* 155 */     logInfo(this, "decodeCurrentSettings: Units Is mm/dL = " + this.m_settingUnitsIsMGDL);
/* 156 */     logInfo(this, "decodeCurrentSettings: Memory Display Enable = " + this.m_settingMemoryDisplayEnable);
/*     */ 
/* 158 */     logInfo(this, "decodeCurrentSettings: Average Display Enable = " + this.m_settingAverageDisplayEnable);
/*     */ 
/* 160 */     logInfo(this, "decodeCurrentSettings: Date Format is M-D-Y = " + this.m_settingDateFormatIsMDY);
/*     */ 
/* 162 */     logInfo(this, "decodeCurrentSettings: Time Format is AM/PM = " + this.m_settingTimeFormatIsAMPM);
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.deviceportreader.LSSureStep
 * JD-Core Version:    0.6.0
 */