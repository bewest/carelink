/*     */ package minimed.ddms.A;
/*     */ 
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.Set;
/*     */ import java.util.StringTokenizer;
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ final class o extends Q
/*     */ {
/*     */   static final int Ы = 1;
/*     */   static final int Ц = 16;
/*     */   static final int Ь = 8;
/*     */   static final int Ш = 2;
/*     */   static final int Ъ = 4;
/*     */   static final int Щ = 1;
/*     */   private static final int Ч = 100;
/*     */   private static final String Х = "Bayer3950";
/*     */ 
/*     */   o()
/*     */   {
/*  77 */     super(100);
/*  78 */     this.ć = "Bayer Glucometer DEX Meter";
/*  79 */     A(this, "creating interface to the '" + this.ć + "', package version " + K());
/*     */ 
/*  81 */     this.ā = 8;
/*  82 */     this.ϰ.add("Bayer3950");
/*     */   }
/*     */ 
/*     */   void ¢() throws Z
/*     */   {
/*  95 */     String str = null;
/*     */ 
/*  98 */     Contract.pre(this.Ϥ != null);
/*     */ 
/* 101 */     StringTokenizer localStringTokenizer = new StringTokenizer(this.Ϥ, "|\\^&\r\n");
/*     */     int i;
/*     */     try
/*     */     {
/* 106 */       str = localStringTokenizer.nextToken();
/* 107 */       str = localStringTokenizer.nextToken();
/* 108 */       i = new Integer(str).intValue();
/*     */     } catch (NoSuchElementException localNoSuchElementException) {
/* 110 */       throw new Z("Bad current setting string '" + this.Ϥ + "' received (last token='" + str + "')");
/*     */     }
/*     */     catch (NumberFormatException localNumberFormatException) {
/* 113 */       throw new Z("Bad current setting string format '" + this.Ϥ + "' received (last token='" + str + "')");
/*     */     }
/*     */ 
/* 118 */     this.Ђ = ((i & 0x1) == 1 ? new Boolean(true) : new Boolean(false));
/*     */ 
/* 122 */     this.Ϝ = ((i & 0x2) == 2 ? new Boolean(false) : new Boolean(true));
/*     */ 
/* 126 */     this.Ϟ = ((i & 0x4) == 4 ? new Boolean(false) : new Boolean(true));
/*     */ 
/* 131 */     this.Ϭ = ((i & 0x8) == 8 ? new Boolean(true) : new Boolean(false));
/*     */ 
/* 137 */     this.ϲ = ((i & 0x10) == 16 ? new Boolean(false) : new Boolean(true));
/*     */ 
/* 141 */     this.Ϩ = null;
/*     */ 
/* 143 */     A(this, "decodeCurrentSettings: *** CURRENT SETTINGS *** (input is '" + this.Ϥ + "')");
/*     */ 
/* 145 */     A(this, "decodeCurrentSettings: Beeper Enable = " + this.Ђ);
/* 146 */     A(this, "decodeCurrentSettings: Time Display is AM / PM = " + this.Ϝ);
/*     */ 
/* 148 */     A(this, "decodeCurrentSettings: Units is mm/dL = " + this.Ϟ);
/* 149 */     A(this, "decodeCurrentSettings: Temp Units is Fahrenheit = " + this.Ϭ);
/*     */ 
/* 151 */     A(this, "decodeCurrentSettings: Reference Method is Plasma = " + this.ϲ);
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.A.o
 * JD-Core Version:    0.6.0
 */