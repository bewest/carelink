/*     */ package minimed.ddms.text;
/*     */ 
/*     */ import java.text.DateFormat;
/*     */ import java.text.DecimalFormatSymbols;
/*     */ import java.text.NumberFormat;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.GregorianCalendar;
/*     */ import java.util.HashMap;
/*     */ import java.util.Locale;
/*     */ 
/*     */ public class Formats
/*     */ {
/*  37 */   private static final HashMap NUMBER_MAP = new HashMap();
/*  38 */   private static final HashMap DATE_PATTERN_MAP = new HashMap();
/*  39 */   private static final HashMap TIME_PATTERN_MAP = new HashMap();
/*     */ 
/*     */   public static NumberFormat getIntegerInstance(Locale paramLocale)
/*     */   {
/* 349 */     return NumberFormat.getIntegerInstance(getNumberLocale(paramLocale));
/*     */   }
/*     */ 
/*     */   public static NumberFormat getNumberInstance(Locale paramLocale)
/*     */   {
/* 358 */     return NumberFormat.getNumberInstance(getNumberLocale(paramLocale));
/*     */   }
/*     */ 
/*     */   public static DecimalFormatSymbols getDecimalFormatSymbols(Locale paramLocale)
/*     */   {
/* 370 */     return new DecimalFormatSymbols(getNumberLocale(paramLocale));
/*     */   }
/*     */ 
/*     */   public static DateFormat getDateInstance(int paramInt, Locale paramLocale)
/*     */   {
/* 380 */     if (paramLocale != null) {
/* 381 */       String str = (String)DATE_PATTERN_MAP.get(new DatePatternKey(paramInt, paramLocale));
/* 382 */       if (str != null) {
/* 383 */         return new SimpleDateFormat(str, paramLocale);
/*     */       }
/*     */     }
/* 386 */     return DateFormat.getDateInstance(paramInt, paramLocale);
/*     */   }
/*     */ 
/*     */   public static DateFormat getMonthDayFormat(int paramInt, Locale paramLocale)
/*     */   {
/* 396 */     DateFormat localDateFormat = getDateInstance(paramInt, paramLocale);
/*     */     String str1;
/* 398 */     if ((localDateFormat instanceof SimpleDateFormat)) {
/* 399 */       String str2 = paramLocale.getCountry();
/* 400 */       int i = str2.equals("CZ") ? 1 : 0;
/* 401 */       int j = i != 0 ? 2 : 1;
/* 402 */       int k = ((SimpleDateFormat)localDateFormat).toPattern().indexOf("M");
/* 403 */       int m = ((SimpleDateFormat)localDateFormat).toPattern().indexOf("d");
/* 404 */       int n = Math.min(k, m);
/* 405 */       int i1 = ((SimpleDateFormat)localDateFormat).toPattern().lastIndexOf("M");
/* 406 */       int i2 = ((SimpleDateFormat)localDateFormat).toPattern().lastIndexOf("d");
/* 407 */       int i3 = Math.max(i1, i2);
/* 408 */       if ((n >= 0) && (i3 >= 0)) {
/* 409 */         str1 = ((SimpleDateFormat)localDateFormat).toPattern().substring(n, i3 + j);
/*     */       }
/*     */       else
/*     */       {
/* 413 */         str1 = "MMM d";
/*     */       }
/*     */     } else {
/* 416 */       str1 = "MMM d";
/*     */     }
/* 418 */     return new SimpleDateFormat(str1, paramLocale);
/*     */   }
/*     */ 
/*     */   public static String formatReportRange(Date paramDate1, Date paramDate2, Locale paramLocale) {
/* 422 */     DateFormat localDateFormat = getDateInstance(2, paramLocale);
/* 423 */     String str1 = localDateFormat.format(paramDate1);
/* 424 */     String str2 = localDateFormat.format(paramDate2);
/* 425 */     GregorianCalendar localGregorianCalendar = new GregorianCalendar();
/* 426 */     localGregorianCalendar.setTime(paramDate2);
/* 427 */     String str3 = String.valueOf(localGregorianCalendar.get(1));
/*     */ 
/* 429 */     if (str1.endsWith(str3)) {
/* 430 */       str1 = getMonthDayFormat(2, paramLocale).format(paramDate1);
/*     */     }
/* 432 */     return str1 + " – " + str2;
/*     */   }
/*     */ 
/*     */   public static DateFormat getTimeInstance(int paramInt, Locale paramLocale)
/*     */   {
/* 443 */     if (paramLocale != null) {
/* 444 */       String str = (String)TIME_PATTERN_MAP.get(new DatePatternKey(paramInt, paramLocale));
/* 445 */       if (str != null) {
/* 446 */         return new SimpleDateFormat(str, paramLocale);
/*     */       }
/*     */     }
/* 449 */     return DateFormat.getTimeInstance(paramInt, paramLocale);
/*     */   }
/*     */ 
/*     */   public static Locale getNumberLocale(Locale paramLocale)
/*     */   {
/* 461 */     Locale localLocale = (Locale)NUMBER_MAP.get(paramLocale);
/* 462 */     if (localLocale == null) {
/* 463 */       localLocale = paramLocale;
/*     */     }
/* 465 */     return localLocale;
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/*  42 */     Locale localLocale1 = new Locale("fr", "CA");
/*  43 */     Locale localLocale2 = Locale.ITALIAN;
/*  44 */     Locale localLocale3 = new Locale("fr", "CH");
/*  45 */     Locale localLocale4 = new Locale("en", "CA");
/*  46 */     Locale localLocale5 = new Locale("es", "MX");
/*  47 */     Locale localLocale6 = new Locale("en", "PL");
/*  48 */     Locale localLocale7 = Locale.US;
/*  49 */     TIME_PATTERN_MAP.put(new DatePatternKey(3, Locale.UK), "hh:mm a");
/*  50 */     NUMBER_MAP.put(localLocale6, localLocale1);
/*  51 */     TIME_PATTERN_MAP.put(new DatePatternKey(3, localLocale6), "HH:mm");
/*  52 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale6), "dd/MM/yy");
/*  53 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale6), "dd/MM/yyyy");
/*  54 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale6), "d MMMM yyyy");
/*  55 */     Locale localLocale8 = new Locale("pl", "PL");
/*  56 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale8), "dd/MM/yy");
/*  57 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale8), "dd/MM/yyyy");
/*  58 */     Locale localLocale9 = new Locale("en", "AT");
/*  59 */     NUMBER_MAP.put(localLocale9, localLocale2);
/*  60 */     TIME_PATTERN_MAP.put(new DatePatternKey(3, localLocale9), "HH:mm");
/*  61 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale9), "dd.MM.yy");
/*  62 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale9), "dd.MM.yy");
/*  63 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale9), "dd. MMMM yyyy");
/*  64 */     Locale localLocale10 = new Locale("de", "BE");
/*     */ 
/*  66 */     NUMBER_MAP.put(localLocale10, localLocale2);
/*  67 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale10), "d/MM/yy");
/*  68 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale10), "dd-MMM-yyyy");
/*  69 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale10), "d MMMM yyyy");
/*  70 */     Locale localLocale11 = new Locale("en", "BE");
/*  71 */     NUMBER_MAP.put(localLocale11, localLocale2);
/*  72 */     TIME_PATTERN_MAP.put(new DatePatternKey(3, localLocale11), "H:mm");
/*  73 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale11), "d/MM/yy");
/*  74 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale11), "dd-MMM-yyyy");
/*  75 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale11), "d MMMM yyyy");
/*  76 */     Locale localLocale12 = new Locale("en", "BR");
/*  77 */     NUMBER_MAP.put(localLocale12, localLocale2);
/*  78 */     TIME_PATTERN_MAP.put(new DatePatternKey(3, localLocale12), "HH:mm");
/*  79 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale12), "dd/MM/yy");
/*  80 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale12), "dd/MMM/yyyy");
/*  81 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale12), "d MMMM yyyy");
/*  82 */     Locale localLocale13 = new Locale("en", "CL");
/*  83 */     NUMBER_MAP.put(localLocale13, localLocale2);
/*     */ 
/*  85 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale13), "dd-MM-yy");
/*  86 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale13), "dd-MMM-yyyy");
/*  87 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale13), "d MMMM yyyy");
/*  88 */     Locale localLocale14 = new Locale("es", "CO");
/*  89 */     NUMBER_MAP.put(localLocale14, localLocale7);
/*  90 */     Locale localLocale15 = new Locale("en", "CO");
/*  91 */     NUMBER_MAP.put(localLocale15, localLocale4);
/*     */ 
/*  93 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale15), "d/MM/yy");
/*  94 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale15), "d/MMM/yyyy");
/*  95 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale15), "d MMMM yyyy");
/*  96 */     Locale localLocale16 = new Locale("en", "HR");
/*  97 */     NUMBER_MAP.put(localLocale16, localLocale2);
/*  98 */     TIME_PATTERN_MAP.put(new DatePatternKey(3, localLocale16), "HH:mm");
/*  99 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale16), "dd/MM/yyyy");
/* 100 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale16), "dd/MM/yyyy");
/* 101 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale16), "dd/MM/yyyy");
/* 102 */     Locale localLocale17 = new Locale("en", "CN");
/* 103 */     NUMBER_MAP.put(localLocale17, localLocale2);
/* 104 */     TIME_PATTERN_MAP.put(new DatePatternKey(3, localLocale17), "H:mm");
/* 105 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale17), "yyyy/M/d");
/* 106 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale17), "yyyy/MM/dd");
/* 107 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale17), "yyyy/MM/dd");
/* 108 */     Locale localLocale18 = new Locale("en", "CZ");
/* 109 */     NUMBER_MAP.put(localLocale18, localLocale1);
/* 110 */     TIME_PATTERN_MAP.put(new DatePatternKey(3, localLocale18), "H:mm");
/* 111 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale18), "d.M.yy");
/* 112 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale18), "d.M.yyyy");
/* 113 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale18), "d. MMMM yyyy");
/* 114 */     Locale localLocale19 = new Locale("en", "EG");
/* 115 */     NUMBER_MAP.put(localLocale19, localLocale5);
/*     */ 
/* 117 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale19), "dd/MM/yy");
/* 118 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale19), "dd/MM/yyyy");
/* 119 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale19), "dd MMMM, yyyy");
/* 120 */     Locale localLocale20 = new Locale("en", "EE");
/* 121 */     NUMBER_MAP.put(localLocale20, localLocale1);
/* 122 */     TIME_PATTERN_MAP.put(new DatePatternKey(3, localLocale20), "H:mm");
/* 123 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale20), "d.MM.yy");
/* 124 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale20), "d.MM.yyyy");
/* 125 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale20), "d. MMMM yyyy");
/* 126 */     Locale localLocale21 = new Locale("en", "DK");
/* 127 */     NUMBER_MAP.put(localLocale21, localLocale2);
/* 128 */     TIME_PATTERN_MAP.put(new DatePatternKey(3, localLocale21), "HH:mm");
/* 129 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale21), "dd-MM-yy");
/* 130 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale21), "dd-MM-yyyy");
/* 131 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale21), "d. MMMM yyyy");
/* 132 */     Locale localLocale22 = new Locale("da", "DK");
/* 133 */     NUMBER_MAP.put(localLocale22, localLocale2);
/* 134 */     TIME_PATTERN_MAP.put(new DatePatternKey(3, localLocale22), "HH:mm");
/* 135 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale22), "dd-MM-yy");
/* 136 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale22), "dd-MM-yyyy");
/* 137 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale22), "d. MMMM yyyy");
/* 138 */     Locale localLocale23 = new Locale("en", "FI");
/* 139 */     NUMBER_MAP.put(localLocale23, localLocale1);
/* 140 */     TIME_PATTERN_MAP.put(new DatePatternKey(3, localLocale23), "H:mm");
/* 141 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale23), "d.M.yy");
/* 142 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale23), "d.M.yyyy");
/* 143 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale23), "d. MMMM yyyy");
/* 144 */     Locale localLocale24 = new Locale("en", "FR");
/* 145 */     NUMBER_MAP.put(localLocale24, localLocale1);
/* 146 */     TIME_PATTERN_MAP.put(new DatePatternKey(3, localLocale24), "HH:mm");
/* 147 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale24), "dd/MM/yy");
/* 148 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale24), "d MMM yyyy");
/* 149 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale24), "d MMMM yyyy");
/* 150 */     Locale localLocale25 = new Locale("en", "DE");
/* 151 */     NUMBER_MAP.put(localLocale25, localLocale2);
/* 152 */     TIME_PATTERN_MAP.put(new DatePatternKey(3, localLocale25), "HH:mm");
/* 153 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale25), "dd.MM.yy");
/* 154 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale25), "dd.MM.yyyy");
/* 155 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale25), "d. MMMM yyyy");
/* 156 */     Locale localLocale26 = new Locale("en", "GR");
/* 157 */     NUMBER_MAP.put(localLocale26, localLocale2);
/* 158 */     TIME_PATTERN_MAP.put(new DatePatternKey(3, localLocale26), "H:mm");
/* 159 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale26), "d/M/yyyy");
/* 160 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale26), "d MMM yyyy");
/* 161 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale26), "d MMM yyyy");
/* 162 */     Locale localLocale27 = new Locale("el", "GR");
/*     */ 
/* 164 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale27), "d/M/yyyy");
/* 165 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale27), "d/M/yyyy");
/* 166 */     TIME_PATTERN_MAP.put(new DatePatternKey(3, localLocale27), "H:mm");
/* 167 */     Locale localLocale28 = new Locale("en", "HU");
/* 168 */     NUMBER_MAP.put(localLocale28, localLocale1);
/* 169 */     TIME_PATTERN_MAP.put(new DatePatternKey(3, localLocale28), "H:mm");
/* 170 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale28), "yyyy.MM.dd");
/* 171 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale28), "yyyy.MM.dd");
/* 172 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale28), "yyyy. MMMM d.");
/* 173 */     Locale localLocale29 = new Locale("en", "IL");
/* 174 */     NUMBER_MAP.put(localLocale29, localLocale5);
/* 175 */     TIME_PATTERN_MAP.put(new DatePatternKey(3, localLocale29), "HH:mm");
/* 176 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale29), "dd/MM/yy");
/* 177 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale29), "dd/MM/yyyy");
/* 178 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale29), "d MMMM yyyy");
/* 179 */     Locale localLocale30 = new Locale("it", "IT");
/* 180 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale30), "d MMM yyyy");
/* 181 */     TIME_PATTERN_MAP.put(new DatePatternKey(3, localLocale30), "H:mm");
/* 182 */     Locale localLocale31 = new Locale("en", "IT");
/* 183 */     NUMBER_MAP.put(localLocale31, localLocale2);
/* 184 */     TIME_PATTERN_MAP.put(new DatePatternKey(3, localLocale31), "H:mm");
/* 185 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale31), "dd/MM/yy");
/* 186 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale31), "d MMM yyyy");
/* 187 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale31), "d MMMM yyyy");
/* 188 */     Locale localLocale32 = new Locale("en", "KW");
/* 189 */     NUMBER_MAP.put(localLocale32, localLocale5);
/*     */ 
/* 191 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale32), "dd/MM/yy");
/* 192 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale32), "dd/MM/yyyy");
/* 193 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale32), "dd MMMM, yyyy");
/* 194 */     Locale localLocale33 = new Locale("en", "LV");
/* 195 */     NUMBER_MAP.put(localLocale33, localLocale1);
/* 196 */     TIME_PATTERN_MAP.put(new DatePatternKey(3, localLocale33), "HH:mm");
/* 197 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale33), "yy.d.M");
/* 198 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale33), "yyyy.d.M");
/* 199 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale33), "yyyy, d MMMM");
/* 200 */     Locale localLocale34 = new Locale("en", "LB");
/* 201 */     NUMBER_MAP.put(localLocale34, localLocale5);
/*     */ 
/* 203 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale34), "dd/MM/yy");
/* 204 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale34), "dd/MM/yyyy");
/* 205 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale34), "dd MMMM, yyyy");
/* 206 */     Locale localLocale35 = new Locale("en", "LT");
/* 207 */     NUMBER_MAP.put(localLocale35, localLocale2);
/* 208 */     TIME_PATTERN_MAP.put(new DatePatternKey(3, localLocale35), "HH:mm");
/* 209 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale35), "yy-MM-dd");
/* 210 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale35), "yyyy-MM-dd");
/* 211 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale35), "yyyy, MMMM d");
/* 212 */     Locale localLocale36 = new Locale("en", "LU");
/* 213 */     NUMBER_MAP.put(localLocale36, localLocale1);
/* 214 */     TIME_PATTERN_MAP.put(new DatePatternKey(3, localLocale36), "HH:mm");
/* 215 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale36), "dd/MM/yy");
/* 216 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale36), "d MMM yyyy");
/* 217 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale36), "d MMMM yyyy");
/* 218 */     Locale localLocale37 = new Locale("en", "MX");
/* 219 */     NUMBER_MAP.put(localLocale37, localLocale5);
/*     */ 
/* 221 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale37), "d/MM/yy");
/* 222 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale37), "d/MMM/yyyy");
/* 223 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale37), "d MMMM yyyy");
/* 224 */     Locale localLocale38 = new Locale("en", "NO");
/* 225 */     NUMBER_MAP.put(localLocale38, localLocale1);
/* 226 */     TIME_PATTERN_MAP.put(new DatePatternKey(3, localLocale38), "HH:mm");
/* 227 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale38), "dd.MM.yy");
/* 228 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale38), "dd.MMM.yyyy");
/* 229 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale38), "d. MMMM yyyy");
/* 230 */     Locale localLocale39 = new Locale("en", "NL");
/* 231 */     NUMBER_MAP.put(localLocale39, localLocale2);
/* 232 */     TIME_PATTERN_MAP.put(new DatePatternKey(3, localLocale39), "H:mm");
/* 233 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale39), "d-MM-yy");
/* 234 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale39), "d-MMM-yyyy");
/* 235 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale39), "d MMMM yyyy");
/* 236 */     Locale localLocale40 = new Locale("en", "PR");
/* 237 */     NUMBER_MAP.put(localLocale40, localLocale5);
/*     */ 
/* 239 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale40), "MM-dd-yy");
/* 240 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale40), "MM-dd-yyyy");
/* 241 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale40), "d MMMM yyyy");
/* 242 */     Locale localLocale41 = new Locale("en", "QA");
/* 243 */     NUMBER_MAP.put(localLocale41, localLocale5);
/*     */ 
/* 245 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale41), "dd/MM/yy");
/* 246 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale41), "dd/MM/yyyy");
/* 247 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale41), "dd MMMM, yyyy");
/* 248 */     Locale localLocale42 = new Locale("en", "RO");
/* 249 */     NUMBER_MAP.put(localLocale42, localLocale2);
/* 250 */     TIME_PATTERN_MAP.put(new DatePatternKey(3, localLocale42), "HH:mm");
/* 251 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale42), "dd.MM.yyyy");
/* 252 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale42), "dd.MM.yyyy");
/* 253 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale42), "dd MMMM yyyy");
/* 254 */     Locale localLocale43 = new Locale("en", "RU");
/* 255 */     NUMBER_MAP.put(localLocale43, localLocale1);
/* 256 */     TIME_PATTERN_MAP.put(new DatePatternKey(3, localLocale43), "H:mm");
/* 257 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale43), "dd.MM.yy");
/* 258 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale43), "dd.MM.yyyy");
/* 259 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale43), "d MMMM yyyy");
/* 260 */     Locale localLocale44 = new Locale("en", "SA");
/* 261 */     NUMBER_MAP.put(localLocale44, localLocale5);
/*     */ 
/* 263 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale44), "dd/MM/yy");
/* 264 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale44), "dd/MM/yyyy");
/* 265 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale44), "dd MMMM, yyyy");
/* 266 */     Locale localLocale45 = new Locale("en", "RS");
/* 267 */     NUMBER_MAP.put(localLocale45, localLocale1);
/* 268 */     TIME_PATTERN_MAP.put(new DatePatternKey(3, localLocale45), "H:mm");
/* 269 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale45), "yy.M.d");
/* 270 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale45), "yyyy.MM.d");
/* 271 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale45), "yyyy, MMMM d");
/* 272 */     Locale localLocale46 = new Locale("en", "SK");
/* 273 */     NUMBER_MAP.put(localLocale46, localLocale1);
/* 274 */     TIME_PATTERN_MAP.put(new DatePatternKey(3, localLocale46), "H:mm");
/* 275 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale46), "d.M.yyyy");
/* 276 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale46), "d.MM.yyyy");
/* 277 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale46), "d. MMMM yyyy");
/* 278 */     Locale localLocale47 = new Locale("sk", "SK");
/* 279 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale47), "d. MMMM yyyy");
/* 280 */     Locale localLocale48 = new Locale("en", "SI");
/* 281 */     NUMBER_MAP.put(localLocale48, localLocale2);
/* 282 */     TIME_PATTERN_MAP.put(new DatePatternKey(3, localLocale48), "H:mm");
/* 283 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale48), "d-M-yy");
/* 284 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale48), "dd-MM-yyyy");
/* 285 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale48), "yyyy, MMMM d");
/* 286 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale48), "EEEE, d. MMMM yyyy");
/* 287 */     Locale localLocale49 = new Locale("sl", "SI");
/* 288 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale49), "d-M-yy");
/* 289 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale49), "dd-MM-yyyy");
/* 290 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale49), "EEEE, d. MMMM yyyy");
/* 291 */     Locale localLocale50 = new Locale("en", "ES");
/* 292 */     NUMBER_MAP.put(localLocale50, localLocale2);
/* 293 */     TIME_PATTERN_MAP.put(new DatePatternKey(3, localLocale50), "H:mm");
/* 294 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale50), "d/MM/yy");
/* 295 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale50), "MMM d,yyyy");
/* 296 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale50), "MMMM d, yyyy");
/* 297 */     Locale localLocale51 = new Locale("en", "SE");
/* 298 */     NUMBER_MAP.put(localLocale51, localLocale1);
/* 299 */     TIME_PATTERN_MAP.put(new DatePatternKey(3, localLocale51), "HH:mm");
/* 300 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale51), "yyyy-MM-dd");
/* 301 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale51), "yyyy-MM-dd");
/* 302 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale51), "d MMMM yyyy");
/* 303 */     Locale localLocale52 = new Locale("sv", "SE");
/* 304 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale52), "yyyy-MM-dd");
/* 305 */     Locale localLocale53 = new Locale("en", "CH");
/* 306 */     NUMBER_MAP.put(localLocale53, localLocale3);
/* 307 */     TIME_PATTERN_MAP.put(new DatePatternKey(3, localLocale53), "HH:mm");
/* 308 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale53), "dd.MM.yy");
/* 309 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale53), "d-MMM-yyyy");
/* 310 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale53), "d. MMMM yyyy");
/* 311 */     Locale localLocale54 = new Locale("en", "TR");
/* 312 */     NUMBER_MAP.put(localLocale54, localLocale2);
/* 313 */     TIME_PATTERN_MAP.put(new DatePatternKey(3, localLocale54), "HH:mm");
/* 314 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale54), "dd.MM.yyyy");
/* 315 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale54), "dd.MMM.yyyy");
/* 316 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale54), "dd MMMM yyyy");
/* 317 */     Locale localLocale55 = new Locale("en", "UA");
/* 318 */     NUMBER_MAP.put(localLocale55, localLocale2);
/* 319 */     TIME_PATTERN_MAP.put(new DatePatternKey(3, localLocale55), "H:mm");
/* 320 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale55), "d/M/yy");
/* 321 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale55), "d/M/yyyy");
/* 322 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale55), "d, MMMM yyyy");
/* 323 */     Locale localLocale56 = new Locale("en", "AE");
/* 324 */     NUMBER_MAP.put(localLocale56, localLocale5);
/*     */ 
/* 326 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale56), "dd/MM/yy");
/* 327 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale56), "dd/MM/yyyy");
/* 328 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale56), "dd MMMM, yyyy");
/* 329 */     Locale localLocale57 = new Locale("en", "VE");
/* 330 */     NUMBER_MAP.put(localLocale57, localLocale2);
/*     */ 
/* 332 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale57), "dd/MM/yy");
/* 333 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale57), "dd/MM/yyyy");
/* 334 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale57), "d MMMM yyyy");
/*     */   }
/*     */ 
/*     */   private static class DatePatternKey
/*     */   {
/*     */     private int type;
/*     */     private Locale locale;
/*     */ 
/*     */     DatePatternKey(int paramInt, Locale paramLocale)
/*     */     {
/* 480 */       this.type = paramInt;
/* 481 */       this.locale = paramLocale;
/*     */     }
/*     */ 
/*     */     public boolean equals(Object paramObject)
/*     */     {
/* 490 */       if (paramObject == null) {
/* 491 */         return false;
/*     */       }
/* 493 */       if ((paramObject instanceof DatePatternKey)) {
/* 494 */         return (((DatePatternKey)paramObject).type == this.type) && (((DatePatternKey)paramObject).locale.equals(this.locale));
/*     */       }
/*     */ 
/* 497 */       return false;
/*     */     }
/*     */ 
/*     */     public int hashCode()
/*     */     {
/* 506 */       return this.locale.hashCode() + this.type * 17;
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.text.Formats
 * JD-Core Version:    0.6.0
 */