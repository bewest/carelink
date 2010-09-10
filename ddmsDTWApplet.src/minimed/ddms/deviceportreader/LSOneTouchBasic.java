/*     */ package minimed.ddms.deviceportreader;
/*     */ 
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.StringTokenizer;
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ final class LSOneTouchBasic extends LSMeter
/*     */ {
/*     */   public static final int SNAPSHOT_FORMAT_ID = 152;
/*     */ 
/*     */   LSOneTouchBasic()
/*     */   {
/*  43 */     this.m_description = "LifeScan One Touch Basic Meter";
/*  44 */     logInfo(this, "creating interface to the '" + this.m_description + "', package version " + getPackageVersion());
/*     */ 
/*  46 */     this.m_deviceClassID = 4;
/*  47 */     this.m_snapshotFormatID = 152;
/*     */   }
/*     */ 
/*     */   void decodeCurrentSettings()
/*     */     throws BadDeviceValueException
/*     */   {
/*  60 */     Contract.pre(this.m_currentSettings != null);
/*     */ 
/*  62 */     String str1 = null;
/*     */ 
/*  66 */     String str2 = MedicalDevice.Util.remainderOf(this.m_currentSettings, "S?,");
/*  67 */     if (str2 == null) {
/*  68 */       throw new BadDeviceValueException("Bad current setting string '" + this.m_currentSettings + "' received");
/*     */     }
/*     */ 
/*  72 */     StringTokenizer localStringTokenizer = new StringTokenizer(str2, ",");
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
/*  86 */       if (str1.charAt(0) != 'L') {
/*  87 */         throw new BadDeviceValueException("Bad current setting string '" + this.m_currentSettings + "' received (last token='" + str1 + "')");
/*     */       }
/*     */ 
/*  90 */       this.m_settingLanguage = new Character(str1.charAt(1));
/*     */ 
/*  93 */       str1 = localStringTokenizer.nextToken();
/*  94 */       if (str1.charAt(0) != 'X') {
/*  95 */         throw new BadDeviceValueException("Bad current setting string '" + this.m_currentSettings + "' received (last token='" + str1 + "')");
/*     */       }
/*     */ 
/*  98 */       this.m_settingTranslationStatus = (str1.charAt(1) == '1' ? new Boolean(true) : new Boolean(false));
/*     */ 
/* 102 */       str1 = localStringTokenizer.nextToken();
/* 103 */       if (str1.charAt(0) != 'B') {
/* 104 */         throw new BadDeviceValueException("Bad current setting string '" + this.m_currentSettings + "' received (last token='" + str1 + "')");
/*     */       }
/*     */ 
/* 107 */       this.m_settingBeeperEnable = (str1.charAt(1) == '0' ? new Boolean(true) : new Boolean(false));
/*     */ 
/* 111 */       str1 = localStringTokenizer.nextToken();
/* 112 */       if (str1.charAt(0) != 'U') {
/* 113 */         throw new BadDeviceValueException("Bad current setting string '" + this.m_currentSettings + "' received (last token='" + str1 + "')");
/*     */       }
/*     */ 
/* 116 */       this.m_settingUnitsIsMGDL = (str1.charAt(1) == '0' ? new Boolean(true) : new Boolean(false));
/*     */ 
/* 120 */       str1 = localStringTokenizer.nextToken();
/* 121 */       if (str1.charAt(0) != 'P') {
/* 122 */         throw new BadDeviceValueException("Bad current setting string '" + this.m_currentSettings + "' received (last token='" + str1 + "')");
/*     */       }
/*     */ 
/* 125 */       this.m_settingPunctuationIsDecimal = (str1.charAt(1) == '0' ? new Boolean(true) : new Boolean(false));
/*     */ 
/* 129 */       str1 = localStringTokenizer.nextToken();
/* 130 */       if (str1.charAt(0) != 'W') {
/* 131 */         throw new BadDeviceValueException("Bad current setting string '" + this.m_currentSettings + "' received (last token='" + str1 + "')");
/*     */       }
/*     */ 
/* 134 */       this.m_settingDateTimeDisplayEnable = (str1.charAt(1) == '1' ? new Boolean(true) : new Boolean(false));
/*     */ 
/* 138 */       str1 = localStringTokenizer.nextToken();
/* 139 */       if (str1.charAt(0) != 'Y') {
/* 140 */         throw new BadDeviceValueException("Bad current setting string '" + this.m_currentSettings + "' received (last token='" + str1 + "')");
/*     */       }
/*     */ 
/* 143 */       this.m_settingDSTEnable = (str1.charAt(1) == '1' ? new Boolean(true) : new Boolean(false));
/*     */ 
/* 147 */       str1 = localStringTokenizer.nextToken();
/* 148 */       if (str1.charAt(0) != 'D') {
/* 149 */         throw new BadDeviceValueException("Bad current setting string '" + this.m_currentSettings + "' received (last token='" + str1 + "')");
/*     */       }
/*     */ 
/* 152 */       this.m_settingDateFormatIsMDY = (str1.charAt(1) == '0' ? new Boolean(true) : new Boolean(false));
/*     */ 
/* 156 */       str1 = localStringTokenizer.nextToken();
/* 157 */       if (str1.charAt(0) != 'T') {
/* 158 */         throw new BadDeviceValueException("Bad current setting string '" + this.m_currentSettings + "' received (last token='" + str1 + "')");
/*     */       }
/*     */ 
/* 161 */       this.m_settingTimeFormatIsAMPM = (str1.charAt(1) == '0' ? new Boolean(true) : new Boolean(false));
/*     */ 
/* 165 */       str1 = localStringTokenizer.nextToken();
/* 166 */       if (str1.charAt(0) != 'C') {
/* 167 */         throw new BadDeviceValueException("Bad current setting string '" + this.m_currentSettings + "' received (last token='" + str1 + "')");
/*     */       }
/*     */ 
/* 170 */       this.m_settingCommMode = new Integer(str1.charAt(1) - '0');
/*     */ 
/* 173 */       str1 = localStringTokenizer.nextToken();
/* 174 */       if (str1.charAt(0) != 'R') {
/* 175 */         throw new BadDeviceValueException("Bad current setting string '" + this.m_currentSettings + "' received (last token='" + str1 + "')");
/*     */       }
/*     */ 
/* 178 */       this.m_settingBaudRate = new Integer(str1.charAt(1) - '0');
/*     */     } catch (StringIndexOutOfBoundsException localStringIndexOutOfBoundsException) {
/* 180 */       logError(this, "Bad current setting string '" + this.m_currentSettings + "' received (last token='" + str1 + "'" + "; exception = " + localStringIndexOutOfBoundsException);
/*     */ 
/* 183 */       throw new BadDeviceValueException("Bad current setting string '" + this.m_currentSettings + "' received (last token='" + str1 + "'");
/*     */     }
/*     */     catch (NoSuchElementException localNoSuchElementException) {
/* 186 */       logError(this, "Bad current setting string '" + this.m_currentSettings + "' received (last token='" + str1 + "'" + "; exception = " + localNoSuchElementException);
/*     */ 
/* 189 */       throw new BadDeviceValueException("Bad current setting string '" + this.m_currentSettings + "' received (last token='" + str1 + "'");
/*     */     }
/*     */ 
/* 193 */     logInfo(this, "decodeCurrentSettings: *** CURRENT SETTINGS ***");
/* 194 */     logInfo(this, "decodeCurrentSettings: Strip Cal. Code = " + this.m_settingStripCalCode);
/*     */ 
/* 196 */     logInfo(this, "decodeCurrentSettings: Language = " + this.m_settingLanguage);
/* 197 */     logInfo(this, "decodeCurrentSettings: Translation Status = " + this.m_settingTranslationStatus);
/*     */ 
/* 199 */     logInfo(this, "decodeCurrentSettings: Beeper Enable = " + this.m_settingBeeperEnable);
/* 200 */     logInfo(this, "decodeCurrentSettings: Units Is mm/dL = " + this.m_settingUnitsIsMGDL);
/* 201 */     logInfo(this, "decodeCurrentSettings: Punctuation is Decimal Point = " + this.m_settingPunctuationIsDecimal);
/*     */ 
/* 203 */     logInfo(this, "decodeCurrentSettings: Date/Time Display = " + this.m_settingDateTimeDisplayEnable);
/*     */ 
/* 205 */     logInfo(this, "decodeCurrentSettings: DST = " + this.m_settingDSTEnable);
/* 206 */     logInfo(this, "decodeCurrentSettings: Date Format is M-D-Y = " + this.m_settingDateFormatIsMDY);
/*     */ 
/* 208 */     logInfo(this, "decodeCurrentSettings: Time Format is AM/PM = " + this.m_settingTimeFormatIsAMPM);
/*     */ 
/* 210 */     logInfo(this, "decodeCurrentSettings: Communications Mode = " + this.m_settingCommMode);
/*     */ 
/* 212 */     logInfo(this, "decodeCurrentSettings: Baud Rate = " + this.m_settingBaudRate);
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.deviceportreader.LSOneTouchBasic
 * JD-Core Version:    0.6.0
 */