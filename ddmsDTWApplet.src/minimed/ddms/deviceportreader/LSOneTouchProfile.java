/*     */ package minimed.ddms.deviceportreader;
/*     */ 
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.StringTokenizer;
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ final class LSOneTouchProfile extends LSMeter
/*     */ {
/*     */   public static final int SNAPSHOT_FORMAT_ID = 150;
/*     */   static final String CMD_GET_DATALOG = "DMI";
/*     */ 
/*     */   LSOneTouchProfile()
/*     */   {
/*  44 */     this.m_description = "LifeScan One Touch Profile Meter";
/*  45 */     logInfo(this, "creating interface to the '" + this.m_description + "', package version " + getPackageVersion());
/*     */ 
/*  47 */     this.m_deviceClassID = 2;
/*  48 */     this.m_snapshotFormatID = 150;
/*     */ 
/*  51 */     this.m_cmdGetDatalog = new LSMeter.Command(this, "DMI", "Read Datalog", 13900);
/*     */   }
/*     */ 
/*     */   void decodeCurrentSettings()
/*     */     throws BadDeviceValueException
/*     */   {
/*  65 */     Contract.pre(this.m_currentSettings != null);
/*     */ 
/*  67 */     String str1 = null;
/*     */ 
/*  71 */     String str2 = MedicalDevice.Util.remainderOf(this.m_currentSettings, "S?,");
/*  72 */     if (str2 == null) {
/*  73 */       throw new BadDeviceValueException("Bad current setting string '" + this.m_currentSettings + "' received");
/*     */     }
/*     */ 
/*  77 */     StringTokenizer localStringTokenizer = new StringTokenizer(str2, ",");
/*     */     try
/*     */     {
/*  82 */       str1 = localStringTokenizer.nextToken();
/*  83 */       if (str1.charAt(0) != 'S') {
/*  84 */         throw new BadDeviceValueException("Bad current setting string '" + this.m_currentSettings + "' received (last token='" + str1 + "')");
/*     */       }
/*     */ 
/*  87 */       this.m_settingStripCalCode = new Character(str1.charAt(1));
/*     */ 
/*  90 */       str1 = localStringTokenizer.nextToken();
/*  91 */       if (str1.charAt(0) != 'L') {
/*  92 */         throw new BadDeviceValueException("Bad current setting string '" + this.m_currentSettings + "' received (last token='" + str1 + "')");
/*     */       }
/*     */ 
/*  95 */       this.m_settingLanguage = new Character(str1.charAt(1));
/*     */ 
/*  98 */       str1 = localStringTokenizer.nextToken();
/*  99 */       if (str1.charAt(0) != 'X') {
/* 100 */         throw new BadDeviceValueException("Bad current setting string '" + this.m_currentSettings + "' received (last token='" + str1 + "')");
/*     */       }
/*     */ 
/* 103 */       this.m_settingTranslationStatus = (str1.charAt(1) == '1' ? new Boolean(true) : new Boolean(false));
/*     */ 
/* 107 */       str1 = localStringTokenizer.nextToken();
/* 108 */       if (str1.charAt(0) != 'B') {
/* 109 */         throw new BadDeviceValueException("Bad current setting string '" + this.m_currentSettings + "' received (last token='" + str1 + "')");
/*     */       }
/*     */ 
/* 112 */       this.m_settingBeeperEnable = (str1.charAt(1) == '0' ? new Boolean(true) : new Boolean(false));
/*     */ 
/* 116 */       str1 = localStringTokenizer.nextToken();
/* 117 */       if (str1.charAt(0) != 'U') {
/* 118 */         throw new BadDeviceValueException("Bad current setting string '" + this.m_currentSettings + "' received (last token='" + str1 + "')");
/*     */       }
/*     */ 
/* 121 */       this.m_settingUnitsIsMGDL = (str1.charAt(1) == '0' ? new Boolean(true) : new Boolean(false));
/*     */ 
/* 125 */       str1 = localStringTokenizer.nextToken();
/* 126 */       if (str1.charAt(0) != 'P') {
/* 127 */         throw new BadDeviceValueException("Bad current setting string '" + this.m_currentSettings + "' received (last token='" + str1 + "')");
/*     */       }
/*     */ 
/* 130 */       this.m_settingPunctuationIsDecimal = (str1.charAt(1) == '0' ? new Boolean(true) : new Boolean(false));
/*     */ 
/* 134 */       str1 = localStringTokenizer.nextToken();
/* 135 */       if (str1.charAt(0) != 'D') {
/* 136 */         throw new BadDeviceValueException("Bad current setting string '" + this.m_currentSettings + "' received (last token='" + str1 + "')");
/*     */       }
/*     */ 
/* 139 */       this.m_settingDateFormatIsMDY = (str1.charAt(1) == '0' ? new Boolean(true) : new Boolean(false));
/*     */ 
/* 143 */       str1 = localStringTokenizer.nextToken();
/* 144 */       if (str1.charAt(0) != 'T') {
/* 145 */         throw new BadDeviceValueException("Bad current setting string '" + this.m_currentSettings + "' received (last token='" + str1 + "')");
/*     */       }
/*     */ 
/* 148 */       this.m_settingTimeFormatIsAMPM = (str1.charAt(1) == '0' ? new Boolean(true) : new Boolean(false));
/*     */ 
/* 152 */       str1 = localStringTokenizer.nextToken();
/* 153 */       if (str1.charAt(0) != 'C') {
/* 154 */         throw new BadDeviceValueException("Bad current setting string '" + this.m_currentSettings + "' received (last token='" + str1 + "')");
/*     */       }
/*     */ 
/* 157 */       this.m_settingCommMode = new Integer(str1.charAt(1) - '0');
/*     */ 
/* 160 */       str1 = localStringTokenizer.nextToken();
/* 161 */       if (str1.charAt(0) != 'R') {
/* 162 */         throw new BadDeviceValueException("Bad current setting string '" + this.m_currentSettings + "' received (last token='" + str1 + "')");
/*     */       }
/*     */ 
/* 165 */       this.m_settingBaudRate = new Integer(str1.charAt(1) - '0');
/*     */ 
/* 168 */       str1 = localStringTokenizer.nextToken();
/* 169 */       if (str1.charAt(0) != 'E') {
/* 170 */         throw new BadDeviceValueException("Bad current setting string '" + this.m_currentSettings + "' received (last token='" + str1 + "')");
/*     */       }
/*     */ 
/* 173 */       this.m_settingEventAveEnable = (str1.charAt(1) == '1' ? new Boolean(true) : new Boolean(false));
/*     */ 
/* 177 */       str1 = localStringTokenizer.nextToken();
/* 178 */       if (str1.charAt(0) != 'I') {
/* 179 */         throw new BadDeviceValueException("Bad current setting string '" + this.m_currentSettings + "' received (last token='" + str1 + "')");
/*     */       }
/*     */ 
/* 182 */       this.m_settingInsulinPromptEnable = (str1.charAt(1) == '1' ? new Boolean(true) : new Boolean(false));
/*     */     }
/*     */     catch (NoSuchElementException localNoSuchElementException) {
/* 185 */       logError(this, "Bad current setting string '" + this.m_currentSettings + "' received (last token='" + str1 + "'" + "; exception = " + localNoSuchElementException);
/*     */ 
/* 188 */       throw new BadDeviceValueException("Bad current setting string '" + this.m_currentSettings + "' received (last token='" + str1 + "'");
/*     */     }
/*     */     catch (StringIndexOutOfBoundsException localStringIndexOutOfBoundsException) {
/* 191 */       logError(this, "Bad current setting string '" + this.m_currentSettings + "' received (last token='" + str1 + "'" + "; exception = " + localStringIndexOutOfBoundsException);
/*     */ 
/* 194 */       throw new BadDeviceValueException("Bad current setting string '" + this.m_currentSettings + "' received (last token='" + str1 + "'");
/*     */     }
/*     */ 
/* 198 */     logInfo(this, "decodeCurrentSettings: *** CURRENT SETTINGS ***");
/* 199 */     logInfo(this, "decodeCurrentSettings: Strip Cal. Code = " + this.m_settingStripCalCode);
/*     */ 
/* 201 */     logInfo(this, "decodeCurrentSettings: Language = " + this.m_settingLanguage);
/* 202 */     logInfo(this, "decodeCurrentSettings: Translation Status = " + this.m_settingTranslationStatus);
/*     */ 
/* 204 */     logInfo(this, "decodeCurrentSettings: Beeper Enable = " + this.m_settingBeeperEnable);
/* 205 */     logInfo(this, "decodeCurrentSettings: Units Is mm/dL = " + this.m_settingUnitsIsMGDL);
/* 206 */     logInfo(this, "decodeCurrentSettings: Punctuation is Decimal Point = " + this.m_settingPunctuationIsDecimal);
/*     */ 
/* 208 */     logInfo(this, "decodeCurrentSettings: Date Format is M-D-Y = " + this.m_settingDateFormatIsMDY);
/*     */ 
/* 210 */     logInfo(this, "decodeCurrentSettings: Time Format is AM/PM = " + this.m_settingTimeFormatIsAMPM);
/*     */ 
/* 212 */     logInfo(this, "decodeCurrentSettings: Communications Mode = " + this.m_settingCommMode);
/*     */ 
/* 214 */     logInfo(this, "decodeCurrentSettings: Baud Rate = " + this.m_settingBaudRate);
/* 215 */     logInfo(this, "decodeCurrentSettings: Event Average Enable = " + this.m_settingEventAveEnable);
/*     */ 
/* 217 */     logInfo(this, "decodeCurrentSettings: Insulin Prompt Enable = " + this.m_settingInsulinPromptEnable);
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.deviceportreader.LSOneTouchProfile
 * JD-Core Version:    0.6.0
 */