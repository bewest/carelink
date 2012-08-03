/*     */ package minimed.ddms.A;
/*     */ 
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.Set;
/*     */ import java.util.StringTokenizer;
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ final class g extends Q
/*     */ {
/*     */   static final int Ж = 1;
/*     */   static final int Е = 32;
/*     */   static final int В = 2;
/*     */   static final int Д = 4;
/*     */   static final int Г = 2;
/*     */   private static final String А = "Bayer3883";
/*     */   private static final int Б = 120;
/*     */ 
/*     */   g()
/*     */   {
/*  72 */     super(120);
/*  73 */     this.ć = "Bayer Glucometer Elite XL Meter";
/*  74 */     A(this, "creating interface to the '" + this.ć + "', package version " + K());
/*     */ 
/*  76 */     this.ā = 9;
/*  77 */     this.ϰ.add("Bayer3883");
/*     */   }
/*     */ 
/*     */   void ¢() throws Z
/*     */   {
/*  90 */     String str = null;
/*     */ 
/*  93 */     Contract.pre(this.Ϥ != null);
/*     */ 
/*  96 */     StringTokenizer localStringTokenizer = new StringTokenizer(this.Ϥ, "|\\^&\r\n");
/*     */     int i;
/*     */     try
/*     */     {
/* 101 */       str = localStringTokenizer.nextToken();
/* 102 */       str = localStringTokenizer.nextToken();
/* 103 */       i = O._B.A(str);
/*     */     } catch (NoSuchElementException localNoSuchElementException) {
/* 105 */       throw new Z("Bad current setting string '" + this.Ϥ + "' received (last token='" + str + "')");
/*     */     }
/*     */     catch (NumberFormatException localNumberFormatException) {
/* 108 */       throw new Z("Bad current setting string format '" + this.Ϥ + "' received (last token='" + str + "')");
/*     */     }
/*     */ 
/* 113 */     this.Ђ = ((i & 0x1) == 1 ? new Boolean(true) : new Boolean(false));
/*     */ 
/* 117 */     this.Ϝ = ((i & 0x2) == 2 ? new Boolean(false) : new Boolean(true));
/*     */ 
/* 121 */     this.Ϟ = ((i & 0x4) == 4 ? new Boolean(false) : new Boolean(true));
/*     */ 
/* 126 */     this.Ϩ = ((i & 0x20) == 32 ? new Boolean(false) : new Boolean(true));
/*     */ 
/* 129 */     this.Ϭ = null;
/* 130 */     this.ϲ = null;
/*     */ 
/* 132 */     A(this, "decodeCurrentSettings: *** CURRENT SETTINGS *** (input is '" + this.Ϥ + "')");
/*     */ 
/* 134 */     A(this, "decodeCurrentSettings: Beeper Enable = " + this.Ђ);
/* 135 */     A(this, "decodeCurrentSettings: Time Display is AM / PM = " + this.Ϝ);
/*     */ 
/* 137 */     A(this, "decodeCurrentSettings: Units is mm/dL = " + this.Ϟ);
/* 138 */     A(this, "decodeCurrentSettings: Time Display is Month.Day = " + this.Ϩ);
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.A.g
 * JD-Core Version:    0.6.0
 */