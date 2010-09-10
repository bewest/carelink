/*     */ package minimed.ddms.applet.dtw;
/*     */ 
/*     */ import java.util.StringTokenizer;
/*     */ 
/*     */ final class ControlCodeParser
/*     */ {
/*     */   private static final String CC_DELIM = "$";
/*     */   private static final String CC_VALUE_DELIM = ":";
/*     */   private static final String CC_DP_ML_NAME = "dpml";
/*     */   private static final String CC_L2C_NAME = "l2c";
/*     */   private static final String CC_L2S_NAME = "l2s";
/*     */   private static final String CC_KSS_NAME = "kss";
/*     */   private static final String CC_TUP_NAME = "tup";
/*     */   private static final String CC_LPHPRO_NAME = "lhpro";
/*     */   private static final String CC_LGHPRO_NAME = "lghpro";
/*     */   private static final String T = "t";
/*     */   private static final int DP_ML_MIN = 0;
/*     */   private static final int DP_ML_MAX = 3;
/*     */   private static final long LPHPRO_MIN = 0L;
/*     */   private static final long LPHPRO_MAX = 4294967295L;
/*     */   private static final long LGHPRO_MIN = 0L;
/*     */   private static final long LGHPRO_MAX = 4294967295L;
/*     */   private final int dpMl;
/*     */   private final boolean logToConsole;
/*     */   private final boolean logToServer;
/*     */   private final boolean keepSnapshotOnServer;
/*     */   private final boolean traceUpload;
/*     */   private final Long lastPumpHistoryPageReadOverride;
/*     */   private final Long lastGlucoseHistoryPageReadOverride;
/*     */ 
/*     */   ControlCodeParser(String paramString)
/*     */   {
/*  65 */     String str1 = paramString;
/*     */ 
/*  68 */     int i = 0;
/*  69 */     boolean bool1 = false;
/*  70 */     boolean bool2 = false;
/*  71 */     boolean bool3 = false;
/*  72 */     boolean bool4 = false;
/*  73 */     Long localLong1 = null;
/*  74 */     Long localLong2 = null;
/*     */ 
/*  76 */     if ((str1 != null) && (str1.length() > 0))
/*     */     {
/*  79 */       StringTokenizer localStringTokenizer = new StringTokenizer(str1, "$");
/*     */ 
/*  82 */       while (localStringTokenizer.hasMoreTokens()) {
/*  83 */         String str2 = localStringTokenizer.nextToken();
/*     */ 
/*  86 */         int j = str2.indexOf("dpml");
/*  87 */         if (j >= 0) {
/*  88 */           i = extractIntegerValue(str2, 0, 3);
/*     */         }
/*     */ 
/*  92 */         j = str2.indexOf("l2c");
/*  93 */         if (j >= 0) {
/*  94 */           bool1 = extractBooleanValue(str2);
/*     */         }
/*     */ 
/*  98 */         j = str2.indexOf("l2s");
/*  99 */         if (j >= 0) {
/* 100 */           bool2 = extractBooleanValue(str2);
/*     */         }
/*     */ 
/* 104 */         j = str2.indexOf("kss");
/* 105 */         if (j >= 0) {
/* 106 */           bool3 = extractBooleanValue(str2);
/*     */         }
/*     */ 
/* 110 */         j = str2.indexOf("tup");
/* 111 */         if (j >= 0) {
/* 112 */           bool4 = extractBooleanValue(str2);
/*     */         }
/*     */ 
/* 116 */         j = str2.indexOf("lhpro");
/* 117 */         if (j >= 0) {
/* 118 */           localLong1 = new Long(extractLongValue(str2, 0L, 4294967295L));
/*     */         }
/*     */ 
/* 123 */         j = str2.indexOf("lghpro");
/* 124 */         if (j >= 0) {
/* 125 */           localLong2 = new Long(extractLongValue(str2, 0L, 4294967295L));
/*     */         }
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 131 */     this.dpMl = i;
/* 132 */     this.logToConsole = bool1;
/* 133 */     this.logToServer = bool2;
/* 134 */     this.keepSnapshotOnServer = bool3;
/* 135 */     this.traceUpload = bool4;
/* 136 */     this.lastPumpHistoryPageReadOverride = localLong1;
/* 137 */     this.lastGlucoseHistoryPageReadOverride = localLong2;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 148 */     StringBuffer localStringBuffer = new StringBuffer();
/* 149 */     localStringBuffer.append("logToConsole=" + this.logToConsole).append(", ");
/* 150 */     localStringBuffer.append("logToServer=" + this.logToServer).append(", ");
/* 151 */     localStringBuffer.append("keepSnapshotOnServer=" + this.keepSnapshotOnServer).append(", ");
/* 152 */     localStringBuffer.append("traceUpload=" + this.traceUpload).append(", ");
/* 153 */     localStringBuffer.append("dpMl=" + this.dpMl);
/* 154 */     localStringBuffer.append("lastPumpHistoryPageReadOverride=" + this.lastPumpHistoryPageReadOverride);
/* 155 */     localStringBuffer.append("lastGlucoseHistoryPageReadOverride=" + this.lastGlucoseHistoryPageReadOverride);
/* 156 */     return localStringBuffer.toString();
/*     */   }
/*     */ 
/*     */   Long getLastPumpHistoryPageReadOverride()
/*     */   {
/* 165 */     return this.lastPumpHistoryPageReadOverride;
/*     */   }
/*     */ 
/*     */   Long getLastGlucoseHistoryPageReadOverride()
/*     */   {
/* 174 */     return this.lastGlucoseHistoryPageReadOverride;
/*     */   }
/*     */ 
/*     */   int getDevicePortReaderMessageLevel()
/*     */   {
/* 183 */     return this.dpMl;
/*     */   }
/*     */ 
/*     */   boolean getLogToConsole()
/*     */   {
/* 192 */     return this.logToConsole;
/*     */   }
/*     */ 
/*     */   boolean getLogToServer()
/*     */   {
/* 201 */     return this.logToServer;
/*     */   }
/*     */ 
/*     */   boolean getKeepSnapshotOnServer()
/*     */   {
/* 210 */     return this.keepSnapshotOnServer;
/*     */   }
/*     */ 
/*     */   boolean getTraceUpload()
/*     */   {
/* 219 */     return this.traceUpload;
/*     */   }
/*     */ 
/*     */   private boolean extractBooleanValue(String paramString)
/*     */   {
/* 230 */     int i = 0;
/* 231 */     StringTokenizer localStringTokenizer = new StringTokenizer(paramString, ":");
/* 232 */     if (localStringTokenizer.countTokens() != 2) {
/* 233 */       return i;
/*     */     }
/* 235 */     localStringTokenizer.nextToken();
/* 236 */     String str = localStringTokenizer.nextToken().trim();
/* 237 */     if (str.equals("t")) {
/* 238 */       i = 1;
/*     */     }
/* 240 */     return i;
/*     */   }
/*     */ 
/*     */   private int extractIntegerValue(String paramString, int paramInt1, int paramInt2)
/*     */   {
/* 254 */     StringTokenizer localStringTokenizer = new StringTokenizer(paramString, ":");
/* 255 */     if (localStringTokenizer.countTokens() != 2) {
/* 256 */       return 0;
/*     */     }
/* 258 */     localStringTokenizer.nextToken();
/* 259 */     String str = localStringTokenizer.nextToken().trim();
/*     */     try {
/* 261 */       int i = Integer.valueOf(str).intValue();
/* 262 */       if ((i < paramInt1) || (i > paramInt2)) {
/* 263 */         i = 0;
/*     */       }
/* 265 */       return i; } catch (NumberFormatException localNumberFormatException) {
/*     */     }
/* 267 */     return 0;
/*     */   }
/*     */ 
/*     */   private long extractLongValue(String paramString, long paramLong1, long paramLong2)
/*     */   {
/* 282 */     long l1 = paramLong1;
/* 283 */     StringTokenizer localStringTokenizer = new StringTokenizer(paramString, ":");
/* 284 */     if (localStringTokenizer.countTokens() != 2) {
/* 285 */       return l1;
/*     */     }
/* 287 */     localStringTokenizer.nextToken();
/* 288 */     String str = localStringTokenizer.nextToken().trim();
/*     */     try {
/* 290 */       long l2 = Long.valueOf(str).longValue();
/* 291 */       if (l2 < paramLong1)
/* 292 */         l2 = paramLong1;
/* 293 */       else if (l2 > paramLong2) {
/* 294 */         l2 = paramLong2;
/*     */       }
/* 296 */       return l2; } catch (NumberFormatException localNumberFormatException) {
/*     */     }
/* 298 */     return l1;
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.ControlCodeParser
 * JD-Core Version:    0.6.0
 */