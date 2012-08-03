/*     */ package minimed.ddms.A;
/*     */ 
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.StringTokenizer;
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ final class V extends w
/*     */ {
/*     */   public static final int Ӟ = 150;
/*     */   static final String Ҭ = "DMI";
/*     */ 
/*     */   V()
/*     */   {
/*  44 */     this.ć = "LifeScan One Touch Profile Meter";
/*  45 */     A(this, "creating interface to the '" + this.ć + "', package version " + K());
/*     */ 
/*  47 */     this.ā = 2;
/*  48 */     this.Þ = 150;
/*     */ 
/*  51 */     this.ѵ = new w._C(this, "DMI", "Read Datalog", 13900);
/*     */   }
/*     */ 
/*     */   void Ë()
/*     */     throws Z
/*     */   {
/*  65 */     Contract.pre(this.ѽ != null);
/*     */ 
/*  67 */     String str1 = null;
/*     */ 
/*  71 */     String str2 = O._B.A(this.ѽ, "S?,");
/*  72 */     if (str2 == null) {
/*  73 */       throw new Z("Bad current setting string '" + this.ѽ + "' received");
/*     */     }
/*     */ 
/*  77 */     StringTokenizer localStringTokenizer = new StringTokenizer(str2, ",");
/*     */     try
/*     */     {
/*  82 */       str1 = localStringTokenizer.nextToken();
/*  83 */       if (str1.charAt(0) != 'S') {
/*  84 */         throw new Z("Bad current setting string '" + this.ѽ + "' received (last token='" + str1 + "')");
/*     */       }
/*     */ 
/*  87 */       this.Ҵ = new Character(str1.charAt(1));
/*     */ 
/*  90 */       str1 = localStringTokenizer.nextToken();
/*  91 */       if (str1.charAt(0) != 'L') {
/*  92 */         throw new Z("Bad current setting string '" + this.ѽ + "' received (last token='" + str1 + "')");
/*     */       }
/*     */ 
/*  95 */       this.Ҡ = new Character(str1.charAt(1));
/*     */ 
/*  98 */       str1 = localStringTokenizer.nextToken();
/*  99 */       if (str1.charAt(0) != 'X') {
/* 100 */         throw new Z("Bad current setting string '" + this.ѽ + "' received (last token='" + str1 + "')");
/*     */       }
/*     */ 
/* 103 */       this.Җ = (str1.charAt(1) == '1' ? new Boolean(true) : new Boolean(false));
/*     */ 
/* 107 */       str1 = localStringTokenizer.nextToken();
/* 108 */       if (str1.charAt(0) != 'B') {
/* 109 */         throw new Z("Bad current setting string '" + this.ѽ + "' received (last token='" + str1 + "')");
/*     */       }
/*     */ 
/* 112 */       this.Ҹ = (str1.charAt(1) == '0' ? new Boolean(true) : new Boolean(false));
/*     */ 
/* 116 */       str1 = localStringTokenizer.nextToken();
/* 117 */       if (str1.charAt(0) != 'U') {
/* 118 */         throw new Z("Bad current setting string '" + this.ѽ + "' received (last token='" + str1 + "')");
/*     */       }
/*     */ 
/* 121 */       this.ҿ = (str1.charAt(1) == '0' ? new Boolean(true) : new Boolean(false));
/*     */ 
/* 125 */       str1 = localStringTokenizer.nextToken();
/* 126 */       if (str1.charAt(0) != 'P') {
/* 127 */         throw new Z("Bad current setting string '" + this.ѽ + "' received (last token='" + str1 + "')");
/*     */       }
/*     */ 
/* 130 */       this.Ґ = (str1.charAt(1) == '0' ? new Boolean(true) : new Boolean(false));
/*     */ 
/* 134 */       str1 = localStringTokenizer.nextToken();
/* 135 */       if (str1.charAt(0) != 'D') {
/* 136 */         throw new Z("Bad current setting string '" + this.ѽ + "' received (last token='" + str1 + "')");
/*     */       }
/*     */ 
/* 139 */       this.Һ = (str1.charAt(1) == '0' ? new Boolean(true) : new Boolean(false));
/*     */ 
/* 143 */       str1 = localStringTokenizer.nextToken();
/* 144 */       if (str1.charAt(0) != 'T') {
/* 145 */         throw new Z("Bad current setting string '" + this.ѽ + "' received (last token='" + str1 + "')");
/*     */       }
/*     */ 
/* 148 */       this.ҟ = (str1.charAt(1) == '0' ? new Boolean(true) : new Boolean(false));
/*     */ 
/* 152 */       str1 = localStringTokenizer.nextToken();
/* 153 */       if (str1.charAt(0) != 'C') {
/* 154 */         throw new Z("Bad current setting string '" + this.ѽ + "' received (last token='" + str1 + "')");
/*     */       }
/*     */ 
/* 157 */       this.ҫ = new Integer(str1.charAt(1) - '0');
/*     */ 
/* 160 */       str1 = localStringTokenizer.nextToken();
/* 161 */       if (str1.charAt(0) != 'R') {
/* 162 */         throw new Z("Bad current setting string '" + this.ѽ + "' received (last token='" + str1 + "')");
/*     */       }
/*     */ 
/* 165 */       this.Ҥ = new Integer(str1.charAt(1) - '0');
/*     */ 
/* 168 */       str1 = localStringTokenizer.nextToken();
/* 169 */       if (str1.charAt(0) != 'E') {
/* 170 */         throw new Z("Bad current setting string '" + this.ѽ + "' received (last token='" + str1 + "')");
/*     */       }
/*     */ 
/* 173 */       this.Ҳ = (str1.charAt(1) == '1' ? new Boolean(true) : new Boolean(false));
/*     */ 
/* 177 */       str1 = localStringTokenizer.nextToken();
/* 178 */       if (str1.charAt(0) != 'I') {
/* 179 */         throw new Z("Bad current setting string '" + this.ѽ + "' received (last token='" + str1 + "')");
/*     */       }
/*     */ 
/* 182 */       this.Ү = (str1.charAt(1) == '1' ? new Boolean(true) : new Boolean(false));
/*     */     }
/*     */     catch (NoSuchElementException localNoSuchElementException) {
/* 185 */       E(this, "Bad current setting string '" + this.ѽ + "' received (last token='" + str1 + "'" + "; exception = " + localNoSuchElementException);
/*     */ 
/* 188 */       throw new Z("Bad current setting string '" + this.ѽ + "' received (last token='" + str1 + "'");
/*     */     }
/*     */     catch (StringIndexOutOfBoundsException localStringIndexOutOfBoundsException) {
/* 191 */       E(this, "Bad current setting string '" + this.ѽ + "' received (last token='" + str1 + "'" + "; exception = " + localStringIndexOutOfBoundsException);
/*     */ 
/* 194 */       throw new Z("Bad current setting string '" + this.ѽ + "' received (last token='" + str1 + "'");
/*     */     }
/*     */ 
/* 198 */     A(this, "decodeCurrentSettings: *** CURRENT SETTINGS ***");
/* 199 */     A(this, "decodeCurrentSettings: Strip Cal. Code = " + this.Ҵ);
/*     */ 
/* 201 */     A(this, "decodeCurrentSettings: Language = " + this.Ҡ);
/* 202 */     A(this, "decodeCurrentSettings: Translation Status = " + this.Җ);
/*     */ 
/* 204 */     A(this, "decodeCurrentSettings: Beeper Enable = " + this.Ҹ);
/* 205 */     A(this, "decodeCurrentSettings: Units Is mm/dL = " + this.ҿ);
/* 206 */     A(this, "decodeCurrentSettings: Punctuation is Decimal Point = " + this.Ґ);
/*     */ 
/* 208 */     A(this, "decodeCurrentSettings: Date Format is M-D-Y = " + this.Һ);
/*     */ 
/* 210 */     A(this, "decodeCurrentSettings: Time Format is AM/PM = " + this.ҟ);
/*     */ 
/* 212 */     A(this, "decodeCurrentSettings: Communications Mode = " + this.ҫ);
/*     */ 
/* 214 */     A(this, "decodeCurrentSettings: Baud Rate = " + this.Ҥ);
/* 215 */     A(this, "decodeCurrentSettings: Event Average Enable = " + this.Ҳ);
/*     */ 
/* 217 */     A(this, "decodeCurrentSettings: Insulin Prompt Enable = " + this.Ү);
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.A.V
 * JD-Core Version:    0.6.0
 */