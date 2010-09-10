/*     */ package minimed.ddms.deviceportreader;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ public class Enums
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private final String m_name;
/*     */ 
/*     */   private Enums(String paramString)
/*     */   {
/*  54 */     this.m_name = paramString;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/*  64 */     return toString().hashCode();
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/*  75 */     return this.m_name;
/*     */   }
/*     */ 
/*     */   Enums(String paramString, 1 param1)
/*     */   {
/*  31 */     this(paramString);
/*     */   }
/*     */ 
/*     */   public static final class ReservoirWarningUnits extends Enums
/*     */   {
/* 389 */     public static final ReservoirWarningUnits TIME = new ReservoirWarningUnits("TIME");
/*     */ 
/* 394 */     public static final ReservoirWarningUnits INSULIN = new ReservoirWarningUnits("INSULIN");
/*     */ 
/*     */     private ReservoirWarningUnits(String paramString)
/*     */     {
/* 406 */       super(null);
/*     */     }
/*     */ 
/*     */     public boolean equals(Object paramObject)
/*     */     {
/* 417 */       boolean bool = false;
/*     */ 
/* 419 */       if ((paramObject != null) && ((paramObject instanceof ReservoirWarningUnits))) {
/* 420 */         ReservoirWarningUnits localReservoirWarningUnits = (ReservoirWarningUnits)paramObject;
/* 421 */         bool = toString().equals(localReservoirWarningUnits.toString());
/*     */       }
/*     */ 
/* 424 */       return bool;
/*     */     }
/*     */ 
/*     */     public int intValue()
/*     */     {
/*     */       int i;
/* 435 */       if (equals(INSULIN)) {
/* 436 */         i = 0;
/*     */       }
/* 438 */       else if (equals(TIME)) {
/* 439 */         i = 1;
/*     */       }
/*     */       else {
/* 442 */         i = 0;
/* 443 */         Contract.unreachable();
/*     */       }
/*     */ 
/* 447 */       return i;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static final class TempBasalType extends Enums
/*     */   {
/* 318 */     public static final TempBasalType INSULIN_RATE = new TempBasalType("INSULIN_RATE");
/*     */ 
/* 323 */     public static final TempBasalType PERCENTAGE = new TempBasalType("PERCENTAGE");
/*     */ 
/*     */     private TempBasalType(String paramString)
/*     */     {
/* 335 */       super(null);
/*     */     }
/*     */ 
/*     */     public boolean equals(Object paramObject)
/*     */     {
/* 346 */       boolean bool = false;
/*     */ 
/* 348 */       if ((paramObject != null) && ((paramObject instanceof TempBasalType))) {
/* 349 */         TempBasalType localTempBasalType = (TempBasalType)paramObject;
/* 350 */         bool = toString().equals(localTempBasalType.toString());
/*     */       }
/*     */ 
/* 353 */       return bool;
/*     */     }
/*     */ 
/*     */     public int intValue()
/*     */     {
/*     */       int i;
/* 364 */       if (equals(INSULIN_RATE)) {
/* 365 */         i = 0;
/*     */       }
/* 367 */       else if (equals(PERCENTAGE)) {
/* 368 */         i = 1;
/*     */       }
/*     */       else {
/* 371 */         i = 0;
/* 372 */         Contract.unreachable();
/*     */       }
/*     */ 
/* 376 */       return i;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static final class InsulinActionType extends Enums
/*     */   {
/* 247 */     public static final InsulinActionType FAST_ACTING = new InsulinActionType("FAST_ACTING");
/*     */ 
/* 252 */     public static final InsulinActionType REGULAR = new InsulinActionType("REGULAR");
/*     */ 
/*     */     private InsulinActionType(String paramString)
/*     */     {
/* 264 */       super(null);
/*     */     }
/*     */ 
/*     */     public boolean equals(Object paramObject)
/*     */     {
/* 275 */       boolean bool = false;
/*     */ 
/* 277 */       if ((paramObject != null) && ((paramObject instanceof InsulinActionType))) {
/* 278 */         InsulinActionType localInsulinActionType = (InsulinActionType)paramObject;
/* 279 */         bool = toString().equals(localInsulinActionType.toString());
/*     */       }
/*     */ 
/* 282 */       return bool;
/*     */     }
/*     */ 
/*     */     public int intValue()
/*     */     {
/*     */       int i;
/* 293 */       if (equals(FAST_ACTING)) {
/* 294 */         i = 0;
/*     */       }
/* 296 */       else if (equals(REGULAR)) {
/* 297 */         i = 1;
/*     */       }
/*     */       else {
/* 300 */         i = 0;
/* 301 */         Contract.unreachable();
/*     */       }
/*     */ 
/* 305 */       return i;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static final class CarbUnits extends Enums
/*     */   {
/* 167 */     public static final CarbUnits UNSET = new CarbUnits("UNSET");
/*     */ 
/* 172 */     public static final CarbUnits GRAMS = new CarbUnits("GRAMS");
/*     */ 
/* 177 */     public static final CarbUnits EXCHANGES = new CarbUnits("EXCHANGES");
/*     */ 
/*     */     private CarbUnits(String paramString)
/*     */     {
/* 189 */       super(null);
/*     */     }
/*     */ 
/*     */     public boolean equals(Object paramObject)
/*     */     {
/* 200 */       boolean bool = false;
/*     */ 
/* 202 */       if ((paramObject != null) && ((paramObject instanceof CarbUnits))) {
/* 203 */         CarbUnits localCarbUnits = (CarbUnits)paramObject;
/* 204 */         bool = toString().equals(localCarbUnits.toString());
/*     */       }
/*     */ 
/* 207 */       return bool;
/*     */     }
/*     */ 
/*     */     public int intValue()
/*     */     {
/*     */       int i;
/* 218 */       if (equals(UNSET)) {
/* 219 */         i = 0;
/*     */       }
/* 221 */       else if (equals(GRAMS)) {
/* 222 */         i = 1;
/*     */       }
/* 224 */       else if (equals(EXCHANGES)) {
/* 225 */         i = 2;
/*     */       }
/*     */       else {
/* 228 */         i = 0;
/* 229 */         Contract.unreachable();
/*     */       }
/*     */ 
/* 234 */       return i;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static final class BGUnits extends Enums
/*     */   {
/*  87 */     public static final BGUnits UNSET = new BGUnits("UNSET");
/*     */ 
/*  92 */     public static final BGUnits MG_DL = new BGUnits("MG_DL");
/*     */ 
/*  97 */     public static final BGUnits MMOL_L = new BGUnits("MMOL_L");
/*     */ 
/*     */     private BGUnits(String paramString)
/*     */     {
/* 109 */       super(null);
/*     */     }
/*     */ 
/*     */     public boolean equals(Object paramObject)
/*     */     {
/* 120 */       boolean bool = false;
/*     */ 
/* 122 */       if ((paramObject != null) && ((paramObject instanceof BGUnits))) {
/* 123 */         BGUnits localBGUnits = (BGUnits)paramObject;
/* 124 */         bool = toString().equals(localBGUnits.toString());
/*     */       }
/*     */ 
/* 127 */       return bool;
/*     */     }
/*     */ 
/*     */     public int intValue()
/*     */     {
/*     */       int i;
/* 138 */       if (equals(UNSET)) {
/* 139 */         i = 0;
/*     */       }
/* 141 */       else if (equals(MG_DL)) {
/* 142 */         i = 1;
/*     */       }
/* 144 */       else if (equals(MMOL_L)) {
/* 145 */         i = 2;
/*     */       }
/*     */       else {
/* 148 */         i = 0;
/* 149 */         Contract.unreachable();
/*     */       }
/*     */ 
/* 154 */       return i;
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.deviceportreader.Enums
 * JD-Core Version:    0.6.0
 */