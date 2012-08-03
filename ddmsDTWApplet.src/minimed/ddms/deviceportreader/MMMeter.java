/*     */ package minimed.ddms.A;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.text.ParsePosition;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.StringTokenizer;
/*     */ import java.util.Vector;
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ abstract class p extends O
/*     */ {
/*     */   public static final int Ѣ = 181;
/*     */   private static final int ѩ = 160;
/*     */   private static final int ќ = 162;
/*     */   private static final int ћ = 128;
/*     */   private static final int Ѧ = 28;
/*     */   private static final int ѣ = 72;
/*     */   private static final int ѡ = 44;
/*     */   private static final int ѧ = 20;
/*     */   private static final int џ = 2500;
/*     */   private static final String ѥ = "[,\r\n";
/*     */   private String ѓ;
/*     */   private _B Ѩ;
/*     */   private _B Ѡ;
/*     */   private _B ј;
/*     */   private _B ї;
/*     */   private _B і;
/*     */   private _B ѕ;
/*     */   private _B є;
/*     */   private _B ђ;
/*     */   private _B ё;
/*     */   private _B я;
/*     */   private _B э;
/*     */   private _B ь;
/*     */   private int њ;
/*     */   private int ю;
/*     */   private int Ѥ;
/*     */   private int љ;
/*     */   private String ў;
/*     */ 
/*     */   p(int paramInt1, int paramInt2, int paramInt3, String paramString)
/*     */   {
/*  97 */     this.ć = paramString;
/*  98 */     this.ā = paramInt1;
/*  99 */     this.Ѥ = paramInt2;
/* 100 */     this.љ = paramInt3;
/*     */ 
/* 102 */     A(this, "creating interface to the '" + this.ć + "', package version " + K());
/*     */ 
/* 105 */     this.Ă = "";
/* 106 */     this.ă = "";
/* 107 */     this.Þ = 181;
/* 108 */     this.Ė = new _A();
/*     */ 
/* 111 */     this.Ѩ = new _B(160, "Read Clock", 28, null);
/* 112 */     this.Ѡ = new _B(162, "Read Current Settings", 72, null);
/*     */ 
/* 114 */     this.ј = new _B(128, "Read Glucose Data (block 0)", 44, null);
/*     */ 
/* 116 */     this.ї = new _B(129, "Read Glucose Data (block 1)", 44, null);
/*     */ 
/* 118 */     this.і = new _B(130, "Read Glucose Data (block 2)", 44, null);
/*     */ 
/* 120 */     this.ѕ = new _B(131, "Read Glucose Data (block 3)", 44, null);
/*     */ 
/* 122 */     this.є = new _B(132, "Read Glucose Data (block 4)", 44, null);
/*     */ 
/* 124 */     this.ђ = new _B(133, "Read Glucose Data (block 5)", 44, null);
/*     */ 
/* 126 */     this.ё = new _B(134, "Read Glucose Data (block 6)", 44, null);
/*     */ 
/* 128 */     this.я = new _B(135, "Read Glucose Data (block 7)", 44, null);
/*     */ 
/* 130 */     this.э = new _B(136, "Read Glucose Data (block 8)", 44, null);
/*     */ 
/* 132 */     this.ь = new _B(137, "Read Glucose Data (block 9)", 44, null);
/*     */   }
/*     */ 
/*     */   public void A(v paramv, String paramString1, String paramString2)
/*     */     throws t, Z, IOException
/*     */   {
/* 155 */     A(this, "readData: starting reader...");
/* 156 */     B(false);
/*     */ 
/* 159 */     Vector localVector = Å();
/*     */ 
/* 162 */     _C local_C = new _C(paramv, paramString1, localVector, null);
/* 163 */     _C.A(local_C);
/*     */   }
/*     */ 
/*     */   public void Æ()
/*     */     throws Z
/*     */   {
/* 172 */     Contract.preNonNull(this.ѓ);
/*     */     try
/*     */     {
/* 182 */       StringTokenizer localStringTokenizer = new StringTokenizer(this.ѓ, "[,\r\n");
/*     */ 
/* 185 */       localStringTokenizer.nextToken();
/*     */ 
/* 188 */       localStringTokenizer.nextToken();
/*     */ 
/* 191 */       localStringTokenizer.nextToken();
/*     */ 
/* 194 */       localStringTokenizer.nextToken();
/*     */ 
/* 197 */       localStringTokenizer.nextToken();
/*     */ 
/* 200 */       localStringTokenizer.nextToken();
/*     */ 
/* 203 */       localStringTokenizer.nextToken();
/*     */ 
/* 206 */       localStringTokenizer.nextToken();
/*     */ 
/* 209 */       localStringTokenizer.nextToken();
/*     */ 
/* 212 */       localStringTokenizer.nextToken();
/*     */ 
/* 215 */       localStringTokenizer.nextToken();
/*     */ 
/* 218 */       localStringTokenizer.nextToken();
/*     */ 
/* 221 */       this.ă = localStringTokenizer.nextToken();
/*     */     } catch (NoSuchElementException localNoSuchElementException) {
/* 223 */       this.ă = "";
/* 224 */       throw new Z("Bad serial number (current setting) string '" + this.ѓ + "' received");
/*     */     }
/*     */   }
/*     */ 
/*     */   void D(String paramString)
/*     */     throws IOException
/*     */   {
/* 237 */     C(paramString);
/* 238 */     A(new d(paramString, this.Ѥ, this.љ));
/* 239 */     Y().A();
/* 240 */     Y().A(20);
/* 241 */     Y().B(2500);
/*     */   }
/*     */ 
/*     */   void _()
/*     */     throws IOException
/*     */   {
/* 250 */     if (Y() != null)
/*     */     {
/* 252 */       Y().B();
/* 253 */       A(null);
/*     */     }
/*     */   }
/*     */ 
/*     */   int Z()
/*     */   {
/* 263 */     return 3;
/*     */   }
/*     */ 
/*     */   void C(v paramv)
/*     */     throws t, IOException
/*     */   {
/*     */     try
/*     */     {
/* 275 */       this.Ѩ.A();
/*     */     }
/*     */     catch (Z localZ) {
/* 278 */       throw new t("Got reply, but format is bad.");
/*     */     }
/*     */   }
/*     */ 
/*     */   private final void Ç()
/*     */     throws t, Z
/*     */   {
/* 289 */     Y().E();
/*     */ 
/* 291 */     E(4);
/*     */   }
/*     */ 
/*     */   private final Vector Å()
/*     */   {
/* 300 */     Vector localVector = new Vector();
/*     */ 
/* 303 */     localVector.addElement(this.Ѩ);
/* 304 */     localVector.addElement(this.Ѡ);
/* 305 */     localVector.addElement(this.ј);
/* 306 */     localVector.addElement(this.ї);
/* 307 */     localVector.addElement(this.і);
/* 308 */     localVector.addElement(this.ѕ);
/* 309 */     localVector.addElement(this.є);
/* 310 */     localVector.addElement(this.ђ);
/* 311 */     localVector.addElement(this.ё);
/* 312 */     localVector.addElement(this.я);
/* 313 */     localVector.addElement(this.э);
/* 314 */     localVector.addElement(this.ь);
/*     */ 
/* 316 */     return localVector;
/*     */   }
/*     */ 
/*     */   private final void A(_B param_B)
/*     */     throws Z
/*     */   {
/*     */     String str;
/* 331 */     if (_B.A(param_B) == _B.A(this.Ѡ))
/*     */     {
/* 333 */       str = O._B.E(param_B.Ä);
/* 334 */       P(str);
/*     */     }
/* 336 */     else if (_B.A(param_B) == _B.A(this.Ѩ)) {
/* 337 */       str = O._B.E(param_B.Ä);
/* 338 */       Q(str);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void Q(String paramString)
/*     */     throws Z
/*     */   {
/* 353 */     Contract.pre(paramString != null);
/* 354 */     Contract.pre(paramString.length() > 3);
/*     */ 
/* 367 */     this.ў = paramString;
/*     */ 
/* 369 */     String str1 = new String("yy,MM,dd,HH,mm");
/*     */ 
/* 372 */     String str2 = paramString.substring(5);
/*     */ 
/* 374 */     SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(str1);
/* 375 */     this.ĕ = localSimpleDateFormat.parse(str2, new ParsePosition(0));
/*     */ 
/* 377 */     if (this.ĕ == null)
/*     */     {
/* 379 */       throw new Z("Bad device time string (null returned) '" + str2 + "' received");
/*     */     }
/*     */ 
/* 383 */     A(this, "decodeCurrentTimeStamp: current time stamp for device is " + this.ĕ);
/*     */   }
/*     */ 
/*     */   private final void P(String paramString)
/*     */     throws Z
/*     */   {
/* 396 */     Contract.pre(paramString != null);
/* 397 */     Contract.pre(paramString.length() > 1);
/*     */ 
/* 399 */     this.ѓ = paramString;
/* 400 */     Æ();
/* 401 */     A(this, "decodeCurrentSettings: *** CURRENT SETTINGS *** ");
/* 402 */     A(this, "decodeCurrentSettings: input = " + this.ѓ);
/* 403 */     A(this, "decodeCurrentSettings: serial number = " + this.ă);
/*     */   }
/*     */ 
/*     */   private final class _A extends O._A
/*     */   {
/*     */     private static final int w = 1;
/*     */     private static final int x = 2;
/*     */     private static final int v = 3;
/*     */     private static final int y = 221;
/*     */ 
/*     */     _A()
/*     */     {
/* 885 */       super(221);
/* 886 */       p.this.e = 0;
/* 887 */       p.this.ì = 0;
/* 888 */       p.this.£ = 28;
/*     */     }
/*     */ 
/*     */     void A()
/*     */     {
/* 898 */       String str1 = "";
/* 899 */       String str2 = "";
/* 900 */       p.this.Î = new CA(p.this.Þ, 1, str1, str2, p.O(p.this));
/*     */ 
/* 902 */       O.A(this, "createSnapshot: creating snapshot");
/*     */ 
/* 906 */       p.this.Î.A(1, p.this.ā);
/*     */ 
/* 909 */       p.this.Î.A(2, O._B.E(p.A(p.this).Ä));
/*     */ 
/* 913 */       int[] arrayOfInt = new int[0];
/* 914 */       arrayOfInt = O._B.A(arrayOfInt, p.F(p.this).Ä);
/* 915 */       arrayOfInt = O._B.A(arrayOfInt, p.I(p.this).Ä);
/* 916 */       arrayOfInt = O._B.A(arrayOfInt, p.B(p.this).Ä);
/* 917 */       arrayOfInt = O._B.A(arrayOfInt, p.C(p.this).Ä);
/* 918 */       arrayOfInt = O._B.A(arrayOfInt, p.K(p.this).Ä);
/* 919 */       arrayOfInt = O._B.A(arrayOfInt, p.L(p.this).Ä);
/* 920 */       arrayOfInt = O._B.A(arrayOfInt, p.D(p.this).Ä);
/* 921 */       arrayOfInt = O._B.A(arrayOfInt, p.E(p.this).Ä);
/* 922 */       arrayOfInt = O._B.A(arrayOfInt, p.N(p.this).Ä);
/* 923 */       arrayOfInt = O._B.A(arrayOfInt, p.H(p.this).Ä);
/* 924 */       p.this.Î.A(3, O._B.E(arrayOfInt));
/*     */     }
/*     */   }
/*     */ 
/*     */   private final class _C
/*     */   {
/*     */     private String B;
/*     */     private Vector C;
/*     */ 
/*     */     private _C(v paramString, String paramVector, Vector arg4)
/*     */     {
/* 721 */       p.this.B(paramString);
/* 722 */       this.B = paramVector;
/*     */       Object localObject;
/* 723 */       this.C = localObject;
/*     */     }
/*     */ 
/*     */     private final void B()
/*     */       throws t, Z, IOException
/*     */     {
/* 739 */       int i = 0;
/*     */ 
/* 741 */       Vector localVector = new Vector();
/*     */ 
/* 745 */       p.this.Ă = "";
/* 746 */       p.this.ă = "";
/*     */ 
/* 750 */       p.B(p.this, 0);
/* 751 */       p.C(p.this, 0);
/*     */       p._B local_B;
/* 752 */       for (int j = 0; j < this.C.size(); j++) {
/* 753 */         local_B = (p._B)this.C.elementAt(j);
/* 754 */         if (local_B != null) {
/* 755 */           p.A(p.this, local_B.Ä.length);
/*     */         }
/*     */       }
/*     */ 
/* 759 */       p.this.C(0);
/*     */       try
/*     */       {
/* 767 */         p.this.B(2);
/*     */         try
/*     */         {
/* 770 */           A(this.B);
/*     */         } catch (IOException localIOException) {
/* 772 */           if (!p.this.isHaltRequested()) {
/* 773 */             O.D(this, "acquireDataFromDevice: initCommunications failed with IOException; retrying... (exception = " + localIOException + ")");
/*     */ 
/* 776 */             A(this.B);
/*     */           }
/*     */         }
/*     */ 
/* 780 */         p.this.E(5);
/*     */ 
/* 783 */         for (i = 0; (i < this.C.size()) && (!p.this.isHaltRequested()); )
/*     */         {
/* 785 */           local_B = (p._B)this.C.elementAt(i);
/* 786 */           if (local_B != null)
/*     */           {
/* 788 */             local_B.A();
/* 789 */             if (!p.this.isHaltRequested())
/*     */             {
/* 791 */               int[] arrayOfInt = p._B.B(local_B);
/* 792 */               localVector.addElement(arrayOfInt);
/*     */             }
/*     */           }
/* 784 */           i++;
/*     */         }
/*     */ 
/*     */       }
/*     */       finally
/*     */       {
/*     */         try
/*     */         {
/* 803 */           O.A(this, "run: shutting down communications...");
/* 804 */           p.this.E(6);
/* 805 */           A();
/*     */         } finally {
/* 807 */           O.A(this, "run: done!  Number of commands processed: " + i);
/*     */ 
/* 810 */           if (p.this.isHaltRequested())
/* 811 */             p.this.B(9);
/*     */           else {
/* 813 */             p.this.B(1);
/*     */           }
/*     */ 
/* 816 */           p.this.U();
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/*     */     private final void A(String paramString)
/*     */       throws IOException, t, Z
/*     */     {
/* 833 */       p.this.D(paramString);
/*     */ 
/* 836 */       p.G(p.this);
/*     */     }
/*     */ 
/*     */     private final void A()
/*     */       throws Z, t, IOException
/*     */     {
/* 849 */       O.A(this, "endCommunications: shutting down serial port.");
/* 850 */       if (p.this.Y() != null)
/*     */       {
/* 852 */         p.this.Y().B();
/* 853 */         p.this.A(null);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private class _B extends n
/*     */   {
/*     */     private static final int Ã = 2;
/*     */     private static final String Â = "[\r\n";
/*     */     private static final String Á = "]\r\n";
/*     */     int[] Ä;
/*     */     private int Å;
/*     */ 
/*     */     private _B(int paramString, String paramInt1, int arg4)
/*     */     {
/* 448 */       super();
/* 449 */       this.Å = paramString;
/*     */       int i;
/* 450 */       this.Ä = new int[i];
/*     */     }
/*     */ 
/*     */     final void A()
/*     */       throws t, Z
/*     */     {
/* 464 */       int i = 0;
/* 465 */       int j = 0;
/*     */ 
/* 467 */       p.this.Y().E();
/*     */ 
/* 469 */       O.Ã = this.A;
/*     */       do
/*     */       {
/*     */         try
/*     */         {
/* 475 */           Y();
/* 476 */           j = 1;
/*     */         } catch (t localt) {
/* 478 */           i++;
/* 479 */           p.this.Y().A();
/* 480 */           if (i <= 2) {
/* 481 */             O.D(this, "execute: cmd failed with exception: " + localt + "; retrying (attempts = " + (i + 1) + ")");
/*     */ 
/* 483 */             p.this.B(7);
/*     */           } else {
/* 485 */             O.E(this, "cmd " + O._B.H(this.Å) + " (" + this.A + ") failed after " + i + " attempts" + "; exception = " + localt);
/*     */ 
/* 489 */             throw new t("execute: cmd " + O._B.H(this.Å) + " (" + this.A + ") failed after " + i + " attempts");
/*     */           }
/*     */         }
/*     */         catch (Z localZ)
/*     */         {
/* 494 */           i++;
/* 495 */           p.this.Y().A();
/* 496 */           if (i <= 2) {
/* 497 */             O.D(this, "execute: cmd failed with exception: " + localZ + "; retrying (attempts = " + (i + 1) + ")");
/*     */ 
/* 499 */             p.this.B(7);
/*     */           } else {
/* 501 */             O.E(this, "cmd " + O._B.H(this.Å) + " (" + this.A + ") failed after " + i + " attempts" + "; exception = " + localZ);
/*     */ 
/* 504 */             throw new Z("execute: cmd " + O._B.H(this.Å) + " (" + this.A + ") failed after " + i + " attempts");
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 511 */       while ((j == 0) && (i <= 2));
/*     */     }
/*     */ 
/*     */     private final int[] Z()
/*     */     {
/* 520 */       return this.Ä;
/*     */     }
/*     */ 
/*     */     private final void X()
/*     */       throws t, Z
/*     */     {
/* 532 */       p.this.Y().E();
/*     */ 
/* 534 */       O.A(this, "readDeviceData: reading reply to cmd " + O._B.H(this.Å) + " (" + this.A + ")");
/*     */ 
/* 538 */       Object localObject = "";
/* 539 */       StringBuffer localStringBuffer = new StringBuffer();
/*     */       String str1;
/*     */       try
/*     */       {
/* 546 */         str1 = p.this.Y().C();
/* 547 */         if (!str1.equals("[\r\n")) {
/* 548 */           throw new t("readDeviceData: ERROR - no header reply for cmd " + O._B.H(this.Å) + " (" + this.A + ")");
/*     */         }
/*     */ 
/* 554 */         int i = 0;
/*     */         do
/*     */         {
/* 557 */           String str3 = p.this.Y().C();
/* 558 */           if (str3.length() == 0) {
/* 559 */             throw new t("readDeviceData: ERROR - no data reply for cmd " + O._B.H(this.Å) + " (" + this.A + ")");
/*     */           }
/*     */ 
/* 563 */           if (str3.length() == 4)
/*     */           {
/* 565 */             i = 1;
/* 566 */             localObject = str3;
/*     */           } else {
/* 568 */             localStringBuffer.append(str3);
/*     */           }
/*     */         }
/* 570 */         while (i == 0);
/*     */ 
/* 573 */         String str2 = p.this.Y().C();
/* 574 */         if (!str2.equals("]\r\n")) {
/* 575 */           throw new t("readDeviceData: ERROR - no footer reply for cmd " + O._B.H(this.Å) + " (" + this.A + ")");
/*     */         }
/*     */ 
/* 581 */         this.Ä = O._B.D(str1 + localStringBuffer + (String)localObject + str2);
/*     */       } catch (IOException localIOException) {
/* 583 */         throw new t("readDeviceData: ERROR - an IOException  has occurred processing cmd " + O._B.H(this.Å) + " (" + this.A + ")");
/*     */       }
/*     */ 
/* 589 */       A(str1 + localStringBuffer, ((String)localObject).trim());
/*     */ 
/* 592 */       O.A(this, "readDeviceData: cmd " + O._B.H(this.Å) + " (" + this.A + ") returned " + this.Ä.length + " data bytes, with reply = <" + O._B.E(this.Ä) + ">");
/*     */     }
/*     */ 
/*     */     final void A(String paramString1, String paramString2) throws Z {
/* 607 */       Contract.preNonNull(paramString1);
/*     */ 
/* 609 */       int[] arrayOfInt = O._B.D(paramString1);
/* 610 */       int i = O._B.A(arrayOfInt, 0, arrayOfInt.length);
/*     */       int j;
/*     */       try {
/* 615 */         j = Integer.parseInt(paramString2, 16);
/*     */       } catch (NumberFormatException localNumberFormatException) {
/* 617 */         throw new Z("ERROR - '" + O._B.E(paramString1) + "' record has bad CRC value of " + paramString2 + " (expected " + O._B.H(i) + ").");
/*     */       }
/*     */ 
/* 623 */       if (i != j) {
/* 624 */         O.E(this, "verifyCRC: ERROR - cmd " + O._B.H(this.Å) + " resulted in bad CRC reply of '" + O._B.E(paramString1) + "'; crcCalc=" + O._B.H(i) + " crcDevice=" + O._B.H(j));
/*     */ 
/* 628 */         throw new Z("verifyCRC: ERROR - cmd " + O._B.H(this.Å) + " resulted in bad CRC reply of '" + O._B.E(paramString1) + "'; crcCalc=" + O._B.H(i) + " crcDevice=" + O._B.H(j));
/*     */       }
/*     */     }
/*     */ 
/*     */     private final void _()
/*     */       throws t
/*     */     {
/* 644 */       p.this.Y().E();
/*     */ 
/* 646 */       O.A(this, "sendCommand: sending cmd " + O._B.H(this.Å) + " (" + this.A + ")");
/*     */       try
/*     */       {
/* 651 */         p.this.Y().G(this.Å);
/*     */       } catch (IOException localIOException) {
/* 653 */         throw new t("sendCommand: ERROR - an IOException  has occurred processing cmd " + O._B.H(this.Å) + " (" + this.A + ")");
/*     */       }
/*     */     }
/*     */ 
/*     */     private final void Y()
/*     */       throws t, Z
/*     */     {
/* 672 */       if (p.this.T() != 7) {
/* 673 */         p.this.B(4);
/*     */       }
/* 675 */       _();
/*     */ 
/* 677 */       if (this.Ä.length > 0)
/*     */       {
/* 679 */         if (!p.this.isHaltRequested())
/*     */         {
/* 681 */           p.this.B(5);
/* 682 */           X();
/*     */         }
/*     */ 
/* 685 */         p.D(p.this, this.Ä.length);
/*     */ 
/* 688 */         p.this.A(p.J(p.this), p.M(p.this));
/*     */ 
/* 691 */         if (!p.this.isHaltRequested())
/* 692 */           p.A(p.this, this);
/*     */       }
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.A.p
 * JD-Core Version:    0.6.0
 */