/*     */ package minimed.ddms.applet.dtw;
/*     */ 
/*     */ import java.util.StringTokenizer;
/*     */ 
/*     */ public final class ControlCodeParser
/*     */ {
/*     */   private static final String CC_DELIM = "$";
/*     */   private static final String CC_VALUE_DELIM = ":";
/*     */   private static final String CC_DP_ML_NAME = "dpml";
/*     */   private static final String CC_L2C_NAME = "l2c";
/*     */   private static final String CC_CEK_NAME = "cek";
/*     */   private static final String CC_L2S_NAME = "l2s";
/*     */   private static final String CC_KSS_NAME = "kss";
/*     */   private static final String CC_TUP_NAME = "tup";
/*     */   private static final String CC_LPHPRO_NAME = "lhpro";
/*     */   private static final String CC_LGHPRO_NAME = "lghpro";
/*     */   private static final String CC_HCM_NAME = "hcm";
/*     */   private static final String T = "t";
/*     */   private static final int DP_ML_MIN = 0;
/*     */   private static final int DP_ML_MAX = 3;
/*     */   private static final long LPHPRO_MIN = 0L;
/*     */   private static final long LPHPRO_MAX = 4294967295L;
/*     */   private static final long LGHPRO_MIN = 0L;
/*     */   private static final long LGHPRO_MAX = 4294967295L;
/*     */   private final int dpMl;
/*     */   private final boolean logToConsole;
/*     */   private final String consoleEncryptionKey;
/*     */   private final boolean logToServer;
/*     */   private final boolean keepSnapshotOnServer;
/*     */   private final boolean traceUpload;
/*     */   private final Long lastPumpHistoryPageReadOverride;
/*     */   private final Long lastGlucoseHistoryPageReadOverride;
/*     */   private final boolean hotCornerMode;
/*     */ 
/*     */   ControlCodeParser(String paramString)
/*     */   {
/*  69 */     String str1 = paramString;
/*     */ 
/*  72 */     int i = 0;
/*  73 */     boolean bool1 = false;
/*  74 */     String str2 = null;
/*  75 */     boolean bool2 = false;
/*  76 */     boolean bool3 = false;
/*  77 */     boolean bool4 = false;
/*  78 */     boolean bool5 = false;
/*  79 */     Long localLong1 = null;
/*  80 */     Long localLong2 = null;
/*     */ 
/*  82 */     if ((str1 != null) && (str1.length() > 0))
/*     */     {
/*  85 */       StringTokenizer localStringTokenizer = new StringTokenizer(str1, "$");
/*     */ 
/*  88 */       while (localStringTokenizer.hasMoreTokens()) {
/*  89 */         String str3 = localStringTokenizer.nextToken();
/*     */ 
/*  92 */         int j = str3.indexOf("dpml");
/*  93 */         if (j >= 0) {
/*  94 */           i = extractIntegerValue(str3, 0, 3);
/*     */         }
/*     */ 
/*  98 */         j = str3.indexOf("l2c");
/*  99 */         if (j >= 0) {
/* 100 */           bool1 = extractBooleanValue(str3);
/*     */         }
/*     */ 
/* 104 */         j = str3.indexOf("cek");
/* 105 */         if (j >= 0) {
/* 106 */           str2 = extractStringValue(str3);
/*     */         }
/*     */ 
/* 110 */         j = str3.indexOf("l2s");
/* 111 */         if (j >= 0) {
/* 112 */           bool2 = extractBooleanValue(str3);
/*     */         }
/*     */ 
/* 116 */         j = str3.indexOf("kss");
/* 117 */         if (j >= 0) {
/* 118 */           bool3 = extractBooleanValue(str3);
/*     */         }
/*     */ 
/* 122 */         j = str3.indexOf("tup");
/* 123 */         if (j >= 0) {
/* 124 */           bool4 = extractBooleanValue(str3);
/*     */         }
/*     */ 
/* 128 */         j = str3.indexOf("lhpro");
/* 129 */         if (j >= 0) {
/* 130 */           localLong1 = new Long(extractLongValue(str3, 0L, 4294967295L));
/*     */         }
/*     */ 
/* 135 */         j = str3.indexOf("lghpro");
/* 136 */         if (j >= 0) {
/* 137 */           localLong2 = new Long(extractLongValue(str3, 0L, 4294967295L));
/*     */         }
/*     */ 
/* 142 */         j = str3.indexOf("hcm");
/* 143 */         if (j >= 0) {
/* 144 */           bool5 = extractBooleanValue(str3);
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 149 */     this.dpMl = i;
/* 150 */     this.logToConsole = bool1;
/* 151 */     this.consoleEncryptionKey = str2;
/* 152 */     this.logToServer = bool2;
/* 153 */     this.keepSnapshotOnServer = bool3;
/* 154 */     this.traceUpload = bool4;
/* 155 */     this.lastPumpHistoryPageReadOverride = localLong1;
/* 156 */     this.lastGlucoseHistoryPageReadOverride = localLong2;
/* 157 */     this.hotCornerMode = bool5;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 168 */     StringBuffer localStringBuffer = new StringBuffer();
/* 169 */     localStringBuffer.append("logToConsole=" + this.logToConsole).append(", ");
/* 170 */     localStringBuffer.append("consoleEncryptionKey=" + this.consoleEncryptionKey).append(", ");
/* 171 */     localStringBuffer.append("logToServer=" + this.logToServer).append(", ");
/* 172 */     localStringBuffer.append("keepSnapshotOnServer=" + this.keepSnapshotOnServer).append(", ");
/* 173 */     localStringBuffer.append("traceUpload=" + this.traceUpload).append(", ");
/* 174 */     localStringBuffer.append("dpMl=" + this.dpMl).append(", ");
/* 175 */     localStringBuffer.append("lastPumpHistoryPageReadOverride=" + this.lastPumpHistoryPageReadOverride).append(", ");
/* 176 */     localStringBuffer.append("lastGlucoseHistoryPageReadOverride=" + this.lastGlucoseHistoryPageReadOverride).append(", ");
/* 177 */     localStringBuffer.append("hotCornerMode=" + this.hotCornerMode);
/* 178 */     return localStringBuffer.toString();
/*     */   }
/*     */ 
/*     */   Long getLastPumpHistoryPageReadOverride()
/*     */   {
/* 187 */     return this.lastPumpHistoryPageReadOverride;
/*     */   }
/*     */ 
/*     */   Long getLastGlucoseHistoryPageReadOverride()
/*     */   {
/* 196 */     return this.lastGlucoseHistoryPageReadOverride;
/*     */   }
/*     */ 
/*     */   int getDevicePortReaderMessageLevel()
/*     */   {
/* 205 */     return this.dpMl;
/*     */   }
/*     */ 
/*     */   boolean getLogToConsole()
/*     */   {
/* 214 */     return this.logToConsole;
/*     */   }
/*     */ 
/*     */   String getConsoleEncryptionKey()
/*     */   {
/* 223 */     return this.consoleEncryptionKey;
/*     */   }
/*     */ 
/*     */   boolean getLogToServer()
/*     */   {
/* 232 */     return this.logToServer;
/*     */   }
/*     */ 
/*     */   boolean getKeepSnapshotOnServer()
/*     */   {
/* 241 */     return this.keepSnapshotOnServer;
/*     */   }
/*     */ 
/*     */   boolean getTraceUpload()
/*     */   {
/* 250 */     return this.traceUpload;
/*     */   }
/*     */ 
/*     */   boolean isHotCornerMode()
/*     */   {
/* 257 */     return this.hotCornerMode;
/*     */   }
/*     */ 
/*     */   private String extractStringValue(String paramString)
/*     */   {
/* 268 */     String str = null;
/* 269 */     StringTokenizer localStringTokenizer = new StringTokenizer(paramString, ":");
/* 270 */     if (localStringTokenizer.countTokens() != 2) {
/* 271 */       return str;
/*     */     }
/* 273 */     localStringTokenizer.nextToken();
/* 274 */     str = localStringTokenizer.nextToken().trim();
/* 275 */     return str;
/*     */   }
/*     */ 
/*     */   private boolean extractBooleanValue(String paramString)
/*     */   {
/* 286 */     int i = 0;
/* 287 */     StringTokenizer localStringTokenizer = new StringTokenizer(paramString, ":");
/* 288 */     if (localStringTokenizer.countTokens() != 2) {
/* 289 */       return i;
/*     */     }
/* 291 */     localStringTokenizer.nextToken();
/* 292 */     String str = localStringTokenizer.nextToken().trim();
/* 293 */     if (str.equals("t")) {
/* 294 */       i = 1;
/*     */     }
/* 296 */     return i;
/*     */   }
/*     */ 
/*     */   private int extractIntegerValue(String paramString, int paramInt1, int paramInt2)
/*     */   {
/* 310 */     StringTokenizer localStringTokenizer = new StringTokenizer(paramString, ":");
/* 311 */     if (localStringTokenizer.countTokens() != 2) {
/* 312 */       return 0;
/*     */     }
/* 314 */     localStringTokenizer.nextToken();
/* 315 */     String str = localStringTokenizer.nextToken().trim();
/*     */     try {
/* 317 */       int i = Integer.valueOf(str).intValue();
/* 318 */       if ((i < paramInt1) || (i > paramInt2)) {
/* 319 */         i = 0;
/*     */       }
/* 321 */       return i; } catch (NumberFormatException localNumberFormatException) {
/*     */     }
/* 323 */     return 0;
/*     */   }
/*     */ 
/*     */   private long extractLongValue(String paramString, long paramLong1, long paramLong2)
/*     */   {
/* 338 */     long l1 = paramLong1;
/* 339 */     StringTokenizer localStringTokenizer = new StringTokenizer(paramString, ":");
/* 340 */     if (localStringTokenizer.countTokens() != 2) {
/* 341 */       return l1;
/*     */     }
/* 343 */     localStringTokenizer.nextToken();
/* 344 */     String str = localStringTokenizer.nextToken().trim();
/*     */     try {
/* 346 */       long l2 = Long.valueOf(str).longValue();
/* 347 */       if (l2 < paramLong1)
/* 348 */         l2 = paramLong1;
/* 349 */       else if (l2 > paramLong2) {
/* 350 */         l2 = paramLong2;
/*     */       }
/* 352 */       return l2; } catch (NumberFormatException localNumberFormatException) {
/*     */     }
/* 354 */     return l1;
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.ControlCodeParser
 * JD-Core Version:    0.6.0
 */