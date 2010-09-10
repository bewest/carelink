/*     */ package minimed.ddms.text;
/*     */ 
/*     */ import java.text.DateFormat;
/*     */ import java.text.DecimalFormatSymbols;
/*     */ import java.text.NumberFormat;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.HashMap;
/*     */ import java.util.Locale;
/*     */ 
/*     */ public class Formats
/*     */ {
/*  34 */   private static final HashMap NUMBER_MAP = new HashMap();
/*  35 */   private static final HashMap DATE_PATTERN_MAP = new HashMap();
/*  36 */   private static final HashMap TIME_PATTERN_MAP = new HashMap();
/*     */ 
/*     */   public static NumberFormat getIntegerInstance(Locale paramLocale)
/*     */   {
/* 320 */     return NumberFormat.getIntegerInstance(getNumberLocale(paramLocale));
/*     */   }
/*     */ 
/*     */   public static NumberFormat getNumberInstance(Locale paramLocale)
/*     */   {
/* 329 */     return NumberFormat.getNumberInstance(getNumberLocale(paramLocale));
/*     */   }
/*     */ 
/*     */   public static DecimalFormatSymbols getDecimalFormatSymbols(Locale paramLocale)
/*     */   {
/* 341 */     return new DecimalFormatSymbols(getNumberLocale(paramLocale));
/*     */   }
/*     */ 
/*     */   public static DateFormat getDateInstance(int paramInt, Locale paramLocale)
/*     */   {
/* 351 */     if (paramLocale != null) {
/* 352 */       String str = (String)DATE_PATTERN_MAP.get(new DatePatternKey(paramInt, paramLocale));
/* 353 */       if (str != null) {
/* 354 */         return new SimpleDateFormat(str, paramLocale);
/*     */       }
/*     */     }
/* 357 */     return DateFormat.getDateInstance(paramInt, paramLocale);
/*     */   }
/*     */ 
/*     */   public static DateFormat getMonthDayFormat(int paramInt, Locale paramLocale)
/*     */   {
/* 367 */     DateFormat localDateFormat = getDateInstance(paramInt, paramLocale);
/*     */     String str;
/* 369 */     if ((localDateFormat instanceof SimpleDateFormat)) {
/* 370 */       int i = ((SimpleDateFormat)localDateFormat).toPattern().indexOf("M");
/* 371 */       int j = ((SimpleDateFormat)localDateFormat).toPattern().indexOf("d");
/* 372 */       int k = Math.min(i, j);
/* 373 */       int m = ((SimpleDateFormat)localDateFormat).toPattern().lastIndexOf("M");
/* 374 */       int n = ((SimpleDateFormat)localDateFormat).toPattern().lastIndexOf("d");
/* 375 */       int i1 = Math.max(m, n);
/* 376 */       if ((k >= 0) && (i1 >= 0)) {
/* 377 */         str = ((SimpleDateFormat)localDateFormat).toPattern().substring(k, i1 + 1);
/*     */       }
/*     */       else
/* 380 */         str = "MMM d";
/*     */     }
/*     */     else {
/* 383 */       str = "MMM d";
/*     */     }
/* 385 */     return new SimpleDateFormat(str, paramLocale);
/*     */   }
/*     */ 
/*     */   public static DateFormat getTimeInstance(int paramInt, Locale paramLocale)
/*     */   {
/* 396 */     if (paramLocale != null) {
/* 397 */       String str = (String)TIME_PATTERN_MAP.get(new DatePatternKey(paramInt, paramLocale));
/* 398 */       if (str != null) {
/* 399 */         return new SimpleDateFormat(str, paramLocale);
/*     */       }
/*     */     }
/* 402 */     return DateFormat.getTimeInstance(paramInt, paramLocale);
/*     */   }
/*     */ 
/*     */   private static Locale getNumberLocale(Locale paramLocale)
/*     */   {
/* 414 */     Locale localLocale = (Locale)NUMBER_MAP.get(paramLocale);
/* 415 */     if (localLocale == null) {
/* 416 */       localLocale = paramLocale;
/*     */     }
/* 418 */     return localLocale;
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/*  39 */     Locale localLocale1 = new Locale("fr", "CA");
/*  40 */     Locale localLocale2 = Locale.ITALIAN;
/*  41 */     Locale localLocale3 = new Locale("fr", "CH");
/*  42 */     Locale localLocale4 = new Locale("en", "CA");
/*  43 */     Locale localLocale5 = new Locale("es", "MX");
/*  44 */     Locale localLocale6 = new Locale("en", "PL");
/*  45 */     TIME_PATTERN_MAP.put(new DatePatternKey(3, Locale.UK), "hh:mm a");
/*  46 */     NUMBER_MAP.put(localLocale6, localLocale1);
/*  47 */     TIME_PATTERN_MAP.put(new DatePatternKey(3, localLocale6), "HH:mm");
/*  48 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale6), "yy-MM-dd");
/*  49 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale6), "yyyy-MM-dd");
/*  50 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale6), "d MMMM yyyy");
/*  51 */     Locale localLocale7 = new Locale("en", "AT");
/*  52 */     NUMBER_MAP.put(localLocale7, localLocale2);
/*  53 */     TIME_PATTERN_MAP.put(new DatePatternKey(3, localLocale7), "HH:mm");
/*  54 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale7), "dd.MM.yy");
/*  55 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale7), "dd.MM.yy");
/*  56 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale7), "dd. MMMM yyyy");
/*  57 */     Locale localLocale8 = new Locale("de", "BE");
/*     */ 
/*  59 */     NUMBER_MAP.put(localLocale8, localLocale2);
/*  60 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale8), "d/MM/yy");
/*  61 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale8), "dd-MMM-yyyy");
/*  62 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale8), "d MMMM yyyy");
/*  63 */     Locale localLocale9 = new Locale("en", "BE");
/*  64 */     NUMBER_MAP.put(localLocale9, localLocale2);
/*  65 */     TIME_PATTERN_MAP.put(new DatePatternKey(3, localLocale9), "H:mm");
/*  66 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale9), "d/MM/yy");
/*  67 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale9), "dd-MMM-yyyy");
/*  68 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale9), "d MMMM yyyy");
/*  69 */     Locale localLocale10 = new Locale("en", "BR");
/*  70 */     NUMBER_MAP.put(localLocale10, localLocale2);
/*  71 */     TIME_PATTERN_MAP.put(new DatePatternKey(3, localLocale10), "HH:mm");
/*  72 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale10), "dd/MM/yy");
/*  73 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale10), "dd/MMM/yyyy");
/*  74 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale10), "d MMMM yyyy");
/*  75 */     Locale localLocale11 = new Locale("en", "CL");
/*  76 */     NUMBER_MAP.put(localLocale11, localLocale2);
/*     */ 
/*  78 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale11), "dd-MM-yy");
/*  79 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale11), "dd-MMM-yyyy");
/*  80 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale11), "d MMMM yyyy");
/*  81 */     Locale localLocale12 = new Locale("en", "CO");
/*  82 */     NUMBER_MAP.put(localLocale12, localLocale4);
/*     */ 
/*  84 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale12), "d/MM/yy");
/*  85 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale12), "d/MMM/yyyy");
/*  86 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale12), "d MMMM yyyy");
/*  87 */     Locale localLocale13 = new Locale("en", "HR");
/*  88 */     NUMBER_MAP.put(localLocale13, localLocale2);
/*  89 */     TIME_PATTERN_MAP.put(new DatePatternKey(3, localLocale13), "HH:mm");
/*  90 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale13), "yyyy.MM.dd");
/*  91 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale13), "yyyy.MM.dd");
/*  92 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale13), "yyyy. MMMM dd");
/*  93 */     Locale localLocale14 = new Locale("en", "CZ");
/*  94 */     NUMBER_MAP.put(localLocale14, localLocale1);
/*  95 */     TIME_PATTERN_MAP.put(new DatePatternKey(3, localLocale14), "H:mm");
/*  96 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale14), "d.M.yy");
/*  97 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale14), "d.M.yyyy");
/*  98 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale14), "d. MMMM yyyy");
/*  99 */     Locale localLocale15 = new Locale("en", "EG");
/* 100 */     NUMBER_MAP.put(localLocale15, localLocale5);
/*     */ 
/* 102 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale15), "dd/MM/yy");
/* 103 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale15), "dd/MM/yyyy");
/* 104 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale15), "dd MMMM, yyyy");
/* 105 */     Locale localLocale16 = new Locale("en", "EE");
/* 106 */     NUMBER_MAP.put(localLocale16, localLocale1);
/* 107 */     TIME_PATTERN_MAP.put(new DatePatternKey(3, localLocale16), "H:mm");
/* 108 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale16), "d.MM.yy");
/* 109 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale16), "d.MM.yyyy");
/* 110 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale16), "d. MMMM yyyy");
/* 111 */     Locale localLocale17 = new Locale("en", "DK");
/* 112 */     NUMBER_MAP.put(localLocale17, localLocale2);
/* 113 */     TIME_PATTERN_MAP.put(new DatePatternKey(3, localLocale17), "HH:mm");
/* 114 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale17), "dd-MM-yy");
/* 115 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale17), "dd-MM-yyyy");
/* 116 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale17), "d. MMMM yyyy");
/* 117 */     Locale localLocale18 = new Locale("en", "FI");
/* 118 */     NUMBER_MAP.put(localLocale18, localLocale1);
/* 119 */     TIME_PATTERN_MAP.put(new DatePatternKey(3, localLocale18), "H:mm");
/* 120 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale18), "d.M.yy");
/* 121 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale18), "d.M.yyyy");
/* 122 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale18), "d. MMMM yyyy");
/* 123 */     Locale localLocale19 = new Locale("en", "FR");
/* 124 */     NUMBER_MAP.put(localLocale19, localLocale1);
/* 125 */     TIME_PATTERN_MAP.put(new DatePatternKey(3, localLocale19), "HH:mm");
/* 126 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale19), "dd/MM/yy");
/* 127 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale19), "d MMM yyyy");
/* 128 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale19), "d MMMM yyyy");
/* 129 */     Locale localLocale20 = new Locale("en", "DE");
/* 130 */     NUMBER_MAP.put(localLocale20, localLocale2);
/* 131 */     TIME_PATTERN_MAP.put(new DatePatternKey(3, localLocale20), "HH:mm");
/* 132 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale20), "dd.MM.yy");
/* 133 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale20), "dd.MM.yyyy");
/* 134 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale20), "d. MMMM yyyy");
/* 135 */     Locale localLocale21 = new Locale("en", "GR");
/* 136 */     NUMBER_MAP.put(localLocale21, localLocale2);
/* 137 */     TIME_PATTERN_MAP.put(new DatePatternKey(3, localLocale21), "H:mm");
/* 138 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale21), "d/M/yyyy");
/* 139 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale21), "d MMM yyyy");
/* 140 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale21), "d MMM yyyy");
/* 141 */     Locale localLocale22 = new Locale("el", "GR");
/*     */ 
/* 143 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale22), "d/M/yyyy");
/* 144 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale22), "d/M/yyyy");
/* 145 */     Locale localLocale23 = new Locale("en", "HU");
/* 146 */     NUMBER_MAP.put(localLocale23, localLocale1);
/* 147 */     TIME_PATTERN_MAP.put(new DatePatternKey(3, localLocale23), "H:mm");
/* 148 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale23), "yyyy.MM.dd");
/* 149 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale23), "yyyy.MM.dd");
/* 150 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale23), "yyyy. MMMM d.");
/* 151 */     Locale localLocale24 = new Locale("en", "IL");
/* 152 */     NUMBER_MAP.put(localLocale24, localLocale5);
/* 153 */     TIME_PATTERN_MAP.put(new DatePatternKey(3, localLocale24), "HH:mm");
/* 154 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale24), "dd/MM/yy");
/* 155 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale24), "dd/MM/yyyy");
/* 156 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale24), "d MMMM yyyy");
/* 157 */     Locale localLocale25 = new Locale("it", "IT");
/* 158 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale25), "d MMM yyyy");
/* 159 */     TIME_PATTERN_MAP.put(new DatePatternKey(3, localLocale25), "H:mm");
/* 160 */     Locale localLocale26 = new Locale("en", "IT");
/* 161 */     NUMBER_MAP.put(localLocale26, localLocale2);
/* 162 */     TIME_PATTERN_MAP.put(new DatePatternKey(3, localLocale26), "H:mm");
/* 163 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale26), "dd/MM/yy");
/* 164 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale26), "d MMM yyyy");
/* 165 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale26), "d MMMM yyyy");
/* 166 */     Locale localLocale27 = new Locale("en", "KW");
/* 167 */     NUMBER_MAP.put(localLocale27, localLocale5);
/*     */ 
/* 169 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale27), "dd/MM/yy");
/* 170 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale27), "dd/MM/yyyy");
/* 171 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale27), "dd MMMM, yyyy");
/* 172 */     Locale localLocale28 = new Locale("en", "LV");
/* 173 */     NUMBER_MAP.put(localLocale28, localLocale1);
/* 174 */     TIME_PATTERN_MAP.put(new DatePatternKey(3, localLocale28), "HH:mm");
/* 175 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale28), "yy.d.M");
/* 176 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale28), "yyyy.d.M");
/* 177 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale28), "yyyy, d MMMM");
/* 178 */     Locale localLocale29 = new Locale("en", "LB");
/* 179 */     NUMBER_MAP.put(localLocale29, localLocale5);
/*     */ 
/* 181 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale29), "dd/MM/yy");
/* 182 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale29), "dd/MM/yyyy");
/* 183 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale29), "dd MMMM, yyyy");
/* 184 */     Locale localLocale30 = new Locale("en", "LT");
/* 185 */     NUMBER_MAP.put(localLocale30, localLocale2);
/* 186 */     TIME_PATTERN_MAP.put(new DatePatternKey(3, localLocale30), "HH:mm");
/* 187 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale30), "yy.M.d");
/* 188 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale30), "yyyy.M.d");
/* 189 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale30), "yyyy, MMMM d");
/* 190 */     Locale localLocale31 = new Locale("en", "LU");
/* 191 */     NUMBER_MAP.put(localLocale31, localLocale1);
/* 192 */     TIME_PATTERN_MAP.put(new DatePatternKey(3, localLocale31), "HH:mm");
/* 193 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale31), "dd/MM/yy");
/* 194 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale31), "d MMM yyyy");
/* 195 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale31), "d MMMM yyyy");
/* 196 */     Locale localLocale32 = new Locale("en", "MX");
/* 197 */     NUMBER_MAP.put(localLocale32, localLocale5);
/*     */ 
/* 199 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale32), "d/MM/yy");
/* 200 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale32), "d/MMM/yyyy");
/* 201 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale32), "d MMMM yyyy");
/* 202 */     Locale localLocale33 = new Locale("en", "NO");
/* 203 */     NUMBER_MAP.put(localLocale33, localLocale1);
/* 204 */     TIME_PATTERN_MAP.put(new DatePatternKey(3, localLocale33), "HH:mm");
/* 205 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale33), "dd.MM.yy");
/* 206 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale33), "dd.MMM.yyyy");
/* 207 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale33), "d. MMMM yyyy");
/* 208 */     Locale localLocale34 = new Locale("en", "NL");
/* 209 */     NUMBER_MAP.put(localLocale34, localLocale2);
/* 210 */     TIME_PATTERN_MAP.put(new DatePatternKey(3, localLocale34), "H:mm");
/* 211 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale34), "d-MM-yy");
/* 212 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale34), "d-MMM-yyyy");
/* 213 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale34), "d MMMM yyyy");
/* 214 */     Locale localLocale35 = new Locale("en", "PR");
/* 215 */     NUMBER_MAP.put(localLocale35, localLocale5);
/*     */ 
/* 217 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale35), "MM-dd-yy");
/* 218 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale35), "MM-dd-yyyy");
/* 219 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale35), "d MMMM yyyy");
/* 220 */     Locale localLocale36 = new Locale("en", "QA");
/* 221 */     NUMBER_MAP.put(localLocale36, localLocale5);
/*     */ 
/* 223 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale36), "dd/MM/yy");
/* 224 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale36), "dd/MM/yyyy");
/* 225 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale36), "dd MMMM, yyyy");
/* 226 */     Locale localLocale37 = new Locale("en", "RO");
/* 227 */     NUMBER_MAP.put(localLocale37, localLocale2);
/* 228 */     TIME_PATTERN_MAP.put(new DatePatternKey(3, localLocale37), "HH:mm");
/* 229 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale37), "dd.MM.yyyy");
/* 230 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale37), "dd.MM.yyyy");
/* 231 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale37), "dd MMMM yyyy");
/* 232 */     Locale localLocale38 = new Locale("en", "RU");
/* 233 */     NUMBER_MAP.put(localLocale38, localLocale1);
/* 234 */     TIME_PATTERN_MAP.put(new DatePatternKey(3, localLocale38), "H:mm");
/* 235 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale38), "dd.MM.yy");
/* 236 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale38), "dd.MM.yyyy");
/* 237 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale38), "d MMMM yyyy");
/* 238 */     Locale localLocale39 = new Locale("en", "SA");
/* 239 */     NUMBER_MAP.put(localLocale39, localLocale5);
/*     */ 
/* 241 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale39), "dd/MM/yy");
/* 242 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale39), "dd/MM/yyyy");
/* 243 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale39), "dd MMMM, yyyy");
/* 244 */     Locale localLocale40 = new Locale("en", "RS");
/* 245 */     NUMBER_MAP.put(localLocale40, localLocale1);
/* 246 */     TIME_PATTERN_MAP.put(new DatePatternKey(3, localLocale40), "H:mm");
/* 247 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale40), "yy.M.d");
/* 248 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale40), "yyyy.MM.d");
/* 249 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale40), "yyyy, MMMM d");
/* 250 */     Locale localLocale41 = new Locale("en", "SK");
/* 251 */     NUMBER_MAP.put(localLocale41, localLocale1);
/* 252 */     TIME_PATTERN_MAP.put(new DatePatternKey(3, localLocale41), "H:mm");
/* 253 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale41), "d.M.yyyy");
/* 254 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale41), "d.MM.yyyy");
/* 255 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale41), "yyyy, MMMM d");
/* 256 */     Locale localLocale42 = new Locale("en", "SI");
/* 257 */     NUMBER_MAP.put(localLocale42, localLocale2);
/* 258 */     TIME_PATTERN_MAP.put(new DatePatternKey(3, localLocale42), "H:mm");
/* 259 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale42), "y.M.d");
/* 260 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale42), "yyyy.M.d");
/* 261 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale42), "yyyy, MMMM d");
/* 262 */     Locale localLocale43 = new Locale("en", "ES");
/* 263 */     NUMBER_MAP.put(localLocale43, localLocale2);
/* 264 */     TIME_PATTERN_MAP.put(new DatePatternKey(3, localLocale43), "H:mm");
/* 265 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale43), "d/MM/yy");
/* 266 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale43), "MMM d,yyyy");
/* 267 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale43), "MMMM d, yyyy");
/* 268 */     Locale localLocale44 = new Locale("en", "SE");
/* 269 */     NUMBER_MAP.put(localLocale44, localLocale1);
/* 270 */     TIME_PATTERN_MAP.put(new DatePatternKey(3, localLocale44), "HH:mm");
/* 271 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale44), "yyyy-MM-dd");
/* 272 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale44), "dd MMM yyyy");
/* 273 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale44), "d MMMM yyyy");
/* 274 */     Locale localLocale45 = new Locale("sv", "SE");
/* 275 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale45), "dd MMM yyyy");
/* 276 */     Locale localLocale46 = new Locale("en", "CH");
/* 277 */     NUMBER_MAP.put(localLocale46, localLocale3);
/* 278 */     TIME_PATTERN_MAP.put(new DatePatternKey(3, localLocale46), "HH:mm");
/* 279 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale46), "dd.MM.yy");
/* 280 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale46), "d-MMM-yyyy");
/* 281 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale46), "d. MMMM yyyy");
/* 282 */     Locale localLocale47 = new Locale("en", "TR");
/* 283 */     NUMBER_MAP.put(localLocale47, localLocale2);
/* 284 */     TIME_PATTERN_MAP.put(new DatePatternKey(3, localLocale47), "HH:mm");
/* 285 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale47), "dd.MM.yyyy");
/* 286 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale47), "dd.MMM.yyyy");
/* 287 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale47), "dd MMMM yyyy");
/* 288 */     Locale localLocale48 = new Locale("en", "UA");
/* 289 */     NUMBER_MAP.put(localLocale48, localLocale2);
/* 290 */     TIME_PATTERN_MAP.put(new DatePatternKey(3, localLocale48), "H:mm");
/* 291 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale48), "d/M/yy");
/* 292 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale48), "d/M/yyyy");
/* 293 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale48), "d, MMMM yyyy");
/* 294 */     Locale localLocale49 = new Locale("en", "AE");
/* 295 */     NUMBER_MAP.put(localLocale49, localLocale5);
/*     */ 
/* 297 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale49), "dd/MM/yy");
/* 298 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale49), "dd/MM/yyyy");
/* 299 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale49), "dd MMMM, yyyy");
/* 300 */     Locale localLocale50 = new Locale("en", "VE");
/* 301 */     NUMBER_MAP.put(localLocale50, localLocale2);
/*     */ 
/* 303 */     DATE_PATTERN_MAP.put(new DatePatternKey(3, localLocale50), "dd/MM/yy");
/* 304 */     DATE_PATTERN_MAP.put(new DatePatternKey(2, localLocale50), "dd/MM/yyyy");
/* 305 */     DATE_PATTERN_MAP.put(new DatePatternKey(1, localLocale50), "d MMMM yyyy");
/*     */   }
/*     */ 
/*     */   private static class DatePatternKey
/*     */   {
/*     */     private int type;
/*     */     private Locale locale;
/*     */ 
/*     */     DatePatternKey(int paramInt, Locale paramLocale)
/*     */     {
/* 433 */       this.type = paramInt;
/* 434 */       this.locale = paramLocale;
/*     */     }
/*     */ 
/*     */     public boolean equals(Object paramObject)
/*     */     {
/* 443 */       if (paramObject == null) {
/* 444 */         return false;
/*     */       }
/* 446 */       if ((paramObject instanceof DatePatternKey)) {
/* 447 */         return (((DatePatternKey)paramObject).type == this.type) && (((DatePatternKey)paramObject).locale.equals(this.locale));
/*     */       }
/*     */ 
/* 450 */       return false;
/*     */     }
/*     */ 
/*     */     public int hashCode()
/*     */     {
/* 459 */       return this.locale.hashCode() + this.type * 17;
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.text.Formats
 * JD-Core Version:    0.6.0
 */