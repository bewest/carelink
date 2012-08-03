/*     */ package minimed.ddms.A;
/*     */ 
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.StringTokenizer;
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ final class i extends w
/*     */ {
/*     */   public static final int Ӫ = 153;
/*     */ 
/*     */   i()
/*     */   {
/*  43 */     this.ć = "LifeScan SureStep Meter";
/*  44 */     A(this, "creating interface to the '" + this.ć + "', package version " + K());
/*     */ 
/*  46 */     this.ā = 7;
/*  47 */     this.Þ = 153;
/*     */   }
/*     */ 
/*     */   void Ë()
/*     */     throws Z
/*     */   {
/*  60 */     Contract.pre(this.ѽ != null);
/*     */ 
/*  62 */     String str1 = null;
/*     */ 
/*  66 */     String str2 = O._B.A(this.ѽ, "S? ");
/*  67 */     if (str2 == null) {
/*  68 */       throw new Z("Bad current setting string '" + this.ѽ + "' received");
/*     */     }
/*     */ 
/*  72 */     StringTokenizer localStringTokenizer = new StringTokenizer(str2, " ");
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
/*  86 */       if (str1.charAt(0) != 'B') {
/*  87 */         throw new Z("Bad current setting string '" + this.ѽ + "' received (last token='" + str1 + "')");
/*     */       }
/*     */ 
/*  90 */       this.Ҹ = (str1.charAt(1) == '0' ? new Boolean(true) : new Boolean(false));
/*     */ 
/*  94 */       str1 = localStringTokenizer.nextToken();
/*  95 */       if (str1.charAt(0) != 'U') {
/*  96 */         throw new Z("Bad current setting string '" + this.ѽ + "' received (last token='" + str1 + "')");
/*     */       }
/*     */ 
/*  99 */       this.ҿ = (str1.charAt(1) == '0' ? new Boolean(true) : new Boolean(false));
/*     */ 
/* 103 */       str1 = localStringTokenizer.nextToken();
/* 104 */       if (str1.charAt(0) != 'M') {
/* 105 */         throw new Z("Bad current setting string '" + this.ѽ + "' received (last token='" + str1 + "')");
/*     */       }
/*     */ 
/* 108 */       this.Ӂ = (str1.charAt(1) == '0' ? new Boolean(true) : new Boolean(false));
/*     */ 
/* 112 */       str1 = localStringTokenizer.nextToken();
/* 113 */       if (str1.charAt(0) != 'A') {
/* 114 */         throw new Z("Bad current setting string '" + this.ѽ + "' received (last token='" + str1 + "')");
/*     */       }
/*     */ 
/* 117 */       this.ҩ = (str1.charAt(1) == '0' ? new Boolean(true) : new Boolean(false));
/*     */ 
/* 121 */       str1 = localStringTokenizer.nextToken();
/* 122 */       if (str1.charAt(0) != 'T') {
/* 123 */         throw new Z("Bad current setting string '" + this.ѽ + "' received (last token='" + str1 + "')");
/*     */       }
/*     */ 
/* 126 */       this.ҟ = (str1.charAt(1) == '0' ? new Boolean(true) : new Boolean(false));
/*     */ 
/* 130 */       str1 = localStringTokenizer.nextToken();
/* 131 */       if (str1.charAt(0) != 'D') {
/* 132 */         throw new Z("Bad current setting string '" + this.ѽ + "' received (last token='" + str1 + "')");
/*     */       }
/*     */ 
/* 135 */       this.Һ = (str1.charAt(1) == '0' ? new Boolean(true) : new Boolean(false));
/*     */     }
/*     */     catch (NoSuchElementException localNoSuchElementException) {
/* 138 */       E(this, "Bad current setting string '" + this.ѽ + "' received (last token='" + str1 + "'" + "; exception = " + localNoSuchElementException);
/*     */ 
/* 141 */       throw new Z("Bad current setting string '" + this.ѽ + "' received (last token='" + str1 + "'");
/*     */     }
/*     */     catch (StringIndexOutOfBoundsException localStringIndexOutOfBoundsException) {
/* 144 */       E(this, "Bad current setting string '" + this.ѽ + "' received (last token='" + str1 + "'" + "; exception = " + localStringIndexOutOfBoundsException);
/*     */ 
/* 147 */       throw new Z("Bad current setting string '" + this.ѽ + "' received (last token='" + str1 + "'");
/*     */     }
/*     */ 
/* 151 */     A(this, "decodeCurrentSettings: *** CURRENT SETTINGS ***");
/* 152 */     A(this, "decodeCurrentSettings: Strip Cal. Code = " + this.Ҵ);
/*     */ 
/* 154 */     A(this, "decodeCurrentSettings: Beeper Enable = " + this.Ҹ);
/* 155 */     A(this, "decodeCurrentSettings: Units Is mm/dL = " + this.ҿ);
/* 156 */     A(this, "decodeCurrentSettings: Memory Display Enable = " + this.Ӂ);
/*     */ 
/* 158 */     A(this, "decodeCurrentSettings: Average Display Enable = " + this.ҩ);
/*     */ 
/* 160 */     A(this, "decodeCurrentSettings: Date Format is M-D-Y = " + this.Һ);
/*     */ 
/* 162 */     A(this, "decodeCurrentSettings: Time Format is AM/PM = " + this.ҟ);
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.A.i
 * JD-Core Version:    0.6.0
 */