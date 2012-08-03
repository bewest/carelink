/*     */ package minimed.ddms.A;
/*     */ 
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.StringTokenizer;
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ final class q extends w
/*     */ {
/*     */   public static final int ӫ = 152;
/*     */ 
/*     */   q()
/*     */   {
/*  43 */     this.ć = "LifeScan One Touch Basic Meter";
/*  44 */     A(this, "creating interface to the '" + this.ć + "', package version " + K());
/*     */ 
/*  46 */     this.ā = 4;
/*  47 */     this.Þ = 152;
/*     */   }
/*     */ 
/*     */   void Ë()
/*     */     throws Z
/*     */   {
/*  60 */     Contract.pre(this.ѽ != null);
/*     */ 
/*  62 */     String str1 = null;
/*     */ 
/*  66 */     String str2 = O._B.A(this.ѽ, "S?,");
/*  67 */     if (str2 == null) {
/*  68 */       throw new Z("Bad current setting string '" + this.ѽ + "' received");
/*     */     }
/*     */ 
/*  72 */     StringTokenizer localStringTokenizer = new StringTokenizer(str2, ",");
/*     */     try
/*     */     {
/*  77 */       str1 = localStringTokenizer.nextToken();
/*  78 */       if (str1.charAt(0) != 'S') {
/*  79 */         throw new Z("Bad current setting string '" + this.ѽ + "' received (last token='" + str1 + "')");
/*     */       }
/*     */ 
/*  82 */       this.Ҵ = new Character(str1.charAt(1));
/*     */ 
/*  85 */       str1 = localStringTokenizer.nextToken();
/*  86 */       if (str1.charAt(0) != 'L') {
/*  87 */         throw new Z("Bad current setting string '" + this.ѽ + "' received (last token='" + str1 + "')");
/*     */       }
/*     */ 
/*  90 */       this.Ҡ = new Character(str1.charAt(1));
/*     */ 
/*  93 */       str1 = localStringTokenizer.nextToken();
/*  94 */       if (str1.charAt(0) != 'X') {
/*  95 */         throw new Z("Bad current setting string '" + this.ѽ + "' received (last token='" + str1 + "')");
/*     */       }
/*     */ 
/*  98 */       this.Җ = (str1.charAt(1) == '1' ? new Boolean(true) : new Boolean(false));
/*     */ 
/* 102 */       str1 = localStringTokenizer.nextToken();
/* 103 */       if (str1.charAt(0) != 'B') {
/* 104 */         throw new Z("Bad current setting string '" + this.ѽ + "' received (last token='" + str1 + "')");
/*     */       }
/*     */ 
/* 107 */       this.Ҹ = (str1.charAt(1) == '0' ? new Boolean(true) : new Boolean(false));
/*     */ 
/* 111 */       str1 = localStringTokenizer.nextToken();
/* 112 */       if (str1.charAt(0) != 'U') {
/* 113 */         throw new Z("Bad current setting string '" + this.ѽ + "' received (last token='" + str1 + "')");
/*     */       }
/*     */ 
/* 116 */       this.ҿ = (str1.charAt(1) == '0' ? new Boolean(true) : new Boolean(false));
/*     */ 
/* 120 */       str1 = localStringTokenizer.nextToken();
/* 121 */       if (str1.charAt(0) != 'P') {
/* 122 */         throw new Z("Bad current setting string '" + this.ѽ + "' received (last token='" + str1 + "')");
/*     */       }
/*     */ 
/* 125 */       this.Ґ = (str1.charAt(1) == '0' ? new Boolean(true) : new Boolean(false));
/*     */ 
/* 129 */       str1 = localStringTokenizer.nextToken();
/* 130 */       if (str1.charAt(0) != 'W') {
/* 131 */         throw new Z("Bad current setting string '" + this.ѽ + "' received (last token='" + str1 + "')");
/*     */       }
/*     */ 
/* 134 */       this.Ѽ = (str1.charAt(1) == '1' ? new Boolean(true) : new Boolean(false));
/*     */ 
/* 138 */       str1 = localStringTokenizer.nextToken();
/* 139 */       if (str1.charAt(0) != 'Y') {
/* 140 */         throw new Z("Bad current setting string '" + this.ѽ + "' received (last token='" + str1 + "')");
/*     */       }
/*     */ 
/* 143 */       this.Ѻ = (str1.charAt(1) == '1' ? new Boolean(true) : new Boolean(false));
/*     */ 
/* 147 */       str1 = localStringTokenizer.nextToken();
/* 148 */       if (str1.charAt(0) != 'D') {
/* 149 */         throw new Z("Bad current setting string '" + this.ѽ + "' received (last token='" + str1 + "')");
/*     */       }
/*     */ 
/* 152 */       this.Һ = (str1.charAt(1) == '0' ? new Boolean(true) : new Boolean(false));
/*     */ 
/* 156 */       str1 = localStringTokenizer.nextToken();
/* 157 */       if (str1.charAt(0) != 'T') {
/* 158 */         throw new Z("Bad current setting string '" + this.ѽ + "' received (last token='" + str1 + "')");
/*     */       }
/*     */ 
/* 161 */       this.ҟ = (str1.charAt(1) == '0' ? new Boolean(true) : new Boolean(false));
/*     */ 
/* 165 */       str1 = localStringTokenizer.nextToken();
/* 166 */       if (str1.charAt(0) != 'C') {
/* 167 */         throw new Z("Bad current setting string '" + this.ѽ + "' received (last token='" + str1 + "')");
/*     */       }
/*     */ 
/* 170 */       this.ҫ = new Integer(str1.charAt(1) - '0');
/*     */ 
/* 173 */       str1 = localStringTokenizer.nextToken();
/* 174 */       if (str1.charAt(0) != 'R') {
/* 175 */         throw new Z("Bad current setting string '" + this.ѽ + "' received (last token='" + str1 + "')");
/*     */       }
/*     */ 
/* 178 */       this.Ҥ = new Integer(str1.charAt(1) - '0');
/*     */     } catch (StringIndexOutOfBoundsException localStringIndexOutOfBoundsException) {
/* 180 */       E(this, "Bad current setting string '" + this.ѽ + "' received (last token='" + str1 + "'" + "; exception = " + localStringIndexOutOfBoundsException);
/*     */ 
/* 183 */       throw new Z("Bad current setting string '" + this.ѽ + "' received (last token='" + str1 + "'");
/*     */     }
/*     */     catch (NoSuchElementException localNoSuchElementException) {
/* 186 */       E(this, "Bad current setting string '" + this.ѽ + "' received (last token='" + str1 + "'" + "; exception = " + localNoSuchElementException);
/*     */ 
/* 189 */       throw new Z("Bad current setting string '" + this.ѽ + "' received (last token='" + str1 + "'");
/*     */     }
/*     */ 
/* 193 */     A(this, "decodeCurrentSettings: *** CURRENT SETTINGS ***");
/* 194 */     A(this, "decodeCurrentSettings: Strip Cal. Code = " + this.Ҵ);
/*     */ 
/* 196 */     A(this, "decodeCurrentSettings: Language = " + this.Ҡ);
/* 197 */     A(this, "decodeCurrentSettings: Translation Status = " + this.Җ);
/*     */ 
/* 199 */     A(this, "decodeCurrentSettings: Beeper Enable = " + this.Ҹ);
/* 200 */     A(this, "decodeCurrentSettings: Units Is mm/dL = " + this.ҿ);
/* 201 */     A(this, "decodeCurrentSettings: Punctuation is Decimal Point = " + this.Ґ);
/*     */ 
/* 203 */     A(this, "decodeCurrentSettings: Date/Time Display = " + this.Ѽ);
/*     */ 
/* 205 */     A(this, "decodeCurrentSettings: DST = " + this.Ѻ);
/* 206 */     A(this, "decodeCurrentSettings: Date Format is M-D-Y = " + this.Һ);
/*     */ 
/* 208 */     A(this, "decodeCurrentSettings: Time Format is AM/PM = " + this.ҟ);
/*     */ 
/* 210 */     A(this, "decodeCurrentSettings: Communications Mode = " + this.ҫ);
/*     */ 
/* 212 */     A(this, "decodeCurrentSettings: Baud Rate = " + this.Ҥ);
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.A.q
 * JD-Core Version:    0.6.0
 */