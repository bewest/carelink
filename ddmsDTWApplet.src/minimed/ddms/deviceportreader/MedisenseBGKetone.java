/*     */ package minimed.ddms.A;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.text.ParsePosition;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.Set;
/*     */ import java.util.StringTokenizer;
/*     */ import java.util.Vector;
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ abstract class m extends O
/*     */ {
/*     */   static final int ц = 3000;
/*  49 */   private static final String у = String.valueOf('\t') + String.valueOf('\r') + String.valueOf('\002') + String.valueOf('\027');
/*     */   private static final String е = "ID";
/*     */   private static final String з = "GET_METER";
/*     */   private static final String л = "GET_EVENTS";
/*     */   private static final int о = 450;
/*     */   private static final int х = 35;
/*     */   private static final int м = 18;
/*     */   private static final int р = 45;
/*     */   private static final int д = 55;
/*     */   private static final int ж = 15769;
/*     */   private static final int ф = 50;
/*     */   private String с;
/*     */   private String й;
/*     */   private Set п;
/*     */   private String к;
/*     */   private _B г;
/*     */   private _B н;
/*     */   private _B т;
/*     */   private int и;
/*     */   private int в;
/*     */ 
/*     */   m(String paramString1, int paramInt1, int paramInt2, String paramString2)
/*     */   {
/* 139 */     this.ć = paramString1;
/* 140 */     this.Þ = paramInt1;
/* 141 */     this.ā = paramInt2;
/* 142 */     A(new HashSet());
/* 143 */     Â().add(paramString2);
/* 144 */     A(this, "creating interface to the '" + this.ć + "', package version " + K());
/*     */ 
/* 147 */     this.Ă = "";
/* 148 */     this.ă = "123456789-123456789-123456789-12";
/* 149 */     this.Ė = new _A();
/*     */ 
/* 155 */     this.г = new _B("ID", "Read Meter ID", 45);
/* 156 */     this.н = new _B("GET_METER", "Read Meter Info", 55);
/*     */ 
/* 158 */     this.т = new _B("GET_EVENTS", "Read Meter Data", 15769);
/*     */   }
/*     */ 
/*     */   public void A(v paramv, String paramString1, String paramString2)
/*     */     throws t, Z, IOException
/*     */   {
/* 182 */     A(this, "readData: starting reader...");
/* 183 */     B(false);
/*     */ 
/* 186 */     Vector localVector = Á();
/*     */ 
/* 189 */     _C local_C = new _C(paramv, paramString1, localVector, null);
/* 190 */     _C.A(local_C);
/*     */   }
/*     */ 
/*     */   int Z()
/*     */   {
/* 199 */     return 3;
/*     */   }
/*     */ 
/*     */   void C(v paramv)
/*     */     throws t, IOException
/*     */   {
/*     */     try
/*     */     {
/* 211 */       this.г.A();
/*     */     }
/*     */     catch (Z localZ) {
/* 214 */       throw new t("Got reply, but format is bad.");
/*     */     }
/*     */   }
/*     */ 
/*     */   void D(String paramString)
/*     */     throws IOException
/*     */   {
/* 226 */     C(paramString);
/* 227 */     A(new d(paramString, 7));
/* 228 */     Y().A();
/* 229 */     Y().A(50);
/*     */   }
/*     */ 
/*     */   void _()
/*     */     throws IOException
/*     */   {
/* 238 */     if (Y() != null)
/*     */     {
/* 240 */       Y().B();
/* 241 */       A(null);
/*     */     }
/*     */   }
/*     */ 
/*     */   void A(Set paramSet)
/*     */   {
/* 251 */     this.п = paramSet;
/*     */   }
/*     */ 
/*     */   Set Â()
/*     */   {
/* 260 */     return this.п;
/*     */   }
/*     */ 
/*     */   private void Ä()
/*     */     throws IOException
/*     */   {
/* 269 */     E(4);
/*     */ 
/* 272 */     O._B.G(3000);
/*     */ 
/* 279 */     Y().A('\006');
/* 280 */     Y().C();
/* 281 */     Y().A('\006');
/* 282 */     Y().O();
/*     */   }
/*     */ 
/*     */   private void A(_B param_B)
/*     */     throws Z
/*     */   {
/* 296 */     if (_B.B(param_B).equals(_B.B(this.н)))
/*     */     {
/* 298 */       this.к = O._B.E(_B.C(param_B));
/* 299 */       this.й = this.к;
/* 300 */       Ã();
/* 301 */       A(this, "decodeReply: device clock reads '" + N() + "'");
/* 302 */       A(this, "decodeReply: serial number is '" + this.ă + "'");
/*     */     }
/*     */     else
/*     */     {
/*     */       String str;
/* 303 */       if (_B.B(param_B).equals(_B.B(this.т))) {
/* 304 */         str = O._B.E(_B.C(param_B));
/* 305 */         this.с = str.trim();
/* 306 */         A(this, "decodeReply: reply is meter data records: '" + this.с + "'");
/* 307 */       } else if (_B.B(param_B).equals(_B.B(this.г))) {
/* 308 */         A(this, "decodeReply: reply is meter ID: '" + O._B.E(_B.C(param_B)) + "'");
/*     */ 
/* 310 */         str = O._B.E(_B.C(param_B));
/* 311 */         if (!O(str)) {
/* 312 */           throw new Z("Incorrect product code; expected '" + Â() + "' (" + this.ć + "), but found '" + str + "'.  " + "Make sure correct meter model has been specified.");
/*     */         }
/*     */       }
/*     */       else
/*     */       {
/* 317 */         throw new Z("Command '" + _B.B(param_B) + " ' is unrecognized");
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private Vector Á()
/*     */   {
/* 328 */     Vector localVector = new Vector();
/*     */ 
/* 330 */     localVector.addElement(this.г);
/* 331 */     localVector.addElement(this.н);
/* 332 */     localVector.addElement(this.т);
/* 333 */     return localVector;
/*     */   }
/*     */ 
/*     */   private Date N(String paramString)
/*     */     throws Z
/*     */   {
/* 346 */     String str = "yyyyMMddHH:mm";
/*     */ 
/* 348 */     Contract.pre(paramString != null);
/* 349 */     Contract.pre(paramString.length() == str.length());
/*     */ 
/* 351 */     SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(str);
/* 352 */     Date localDate = localSimpleDateFormat.parse(paramString, new ParsePosition(0));
/*     */ 
/* 354 */     if (localDate == null)
/*     */     {
/* 356 */       throw new Z("Bad device time string (dataF is null) '" + paramString + "' received");
/*     */     }
/*     */ 
/* 360 */     return localDate;
/*     */   }
/*     */ 
/*     */   private void Ã()
/*     */     throws Z
/*     */   {
/* 376 */     StringTokenizer localStringTokenizer = new StringTokenizer(this.к, у);
/*     */     try
/*     */     {
/* 380 */       String str1 = localStringTokenizer.nextToken();
/*     */ 
/* 382 */       if (str1.length() > "YYYYMMDD".length())
/*     */       {
/* 384 */         str1 = str1.substring(str1.length() - "YYYYMMDD".length());
/*     */       }
/*     */ 
/* 387 */       String str2 = localStringTokenizer.nextToken();
/*     */ 
/* 390 */       this.ĕ = N(str1 + str2);
/* 391 */       A(this, "decodeCurrentTimeStampAndSerialNumber: current time stamp for device is " + this.ĕ);
/*     */ 
/* 396 */       this.ă = localStringTokenizer.nextToken().trim();
/* 397 */       A(this, "decodeCurrentTimeStampAndSerialNumber: serial number is " + this.ă);
/*     */     }
/*     */     catch (NoSuchElementException localNoSuchElementException) {
/* 400 */       throw new Z("Bad device timestamp reply '" + this.к + '\'');
/*     */     }
/*     */   }
/*     */ 
/*     */   private boolean O(String paramString)
/*     */   {
/* 413 */     Contract.preNonNull(paramString);
/* 414 */     int i = 0;
/* 415 */     Iterator localIterator = Â().iterator();
/*     */ 
/* 417 */     while ((localIterator.hasNext()) && (i == 0)) {
/* 418 */       String str = (String)localIterator.next();
/* 419 */       if (paramString.indexOf(str) > 0) {
/* 420 */         i = 1;
/*     */       }
/*     */     }
/* 423 */     return i;
/*     */   }
/*     */ 
/*     */   private class _A extends O._A
/*     */   {
/*     */     static final int t = 80;
/*     */     static final int s = 1;
/*     */     static final int u = 2;
/*     */ 
/*     */     _A()
/*     */     {
/* 958 */       super(80);
/* 959 */       m.this.e = 0;
/* 960 */       m.this.ì = 0;
/* 961 */       m.this.£ = 0;
/*     */     }
/*     */ 
/*     */     void A()
/*     */     {
/* 971 */       String str1 = "";
/* 972 */       String str2 = "";
/* 973 */       String str3 = "";
/* 974 */       m.this.Î = new CA(m.this.Þ, 1, str1, str2, str3);
/*     */ 
/* 977 */       O.A(this, "createSnapshot: creating snapshot");
/*     */ 
/* 980 */       m.this.Î.A(1, m.A(m.this));
/*     */ 
/* 983 */       m.this.Î.A(2, m.E(m.this));
/*     */     }
/*     */   }
/*     */ 
/*     */   private class _C
/*     */   {
/*     */     private String B;
/*     */     private Vector C;
/*     */ 
/*     */     private _C(v paramString, String paramVector, Vector arg4)
/*     */     {
/* 800 */       m.this.B(paramString);
/* 801 */       this.B = paramVector;
/*     */       Object localObject;
/* 802 */       this.C = localObject;
/*     */     }
/*     */ 
/*     */     private void B()
/*     */       throws t, Z, IOException
/*     */     {
/* 818 */       int i = 0;
/*     */ 
/* 820 */       Vector localVector = new Vector();
/*     */ 
/* 824 */       m.this.Ă = "";
/* 825 */       m.this.ă = "";
/* 826 */       m.A(m.this, "");
/*     */ 
/* 830 */       m.C(m.this, 0);
/* 831 */       m.D(m.this, 0);
/*     */       m._B local_B;
/* 833 */       for (int j = 0; j < this.C.size(); j++) {
/* 834 */         local_B = (m._B)this.C.elementAt(j);
/* 835 */         if (local_B != null) {
/* 836 */           m.A(m.this, m._B.C(local_B).length);
/*     */         }
/*     */       }
/*     */ 
/* 840 */       m.this.C(0);
/*     */       try
/*     */       {
/* 849 */         m.this.B(2);
/*     */         try
/*     */         {
/* 853 */           A(this.B);
/*     */         } catch (IOException localIOException) {
/* 855 */           if (!m.this.isHaltRequested()) {
/* 856 */             O.D(this, "acquireDataFromDevice: initCommunications failed with IOException; retrying... (exception = " + localIOException + ")");
/*     */ 
/* 859 */             A(this.B);
/*     */           }
/*     */         }
/*     */ 
/* 863 */         m.this.E(5);
/*     */ 
/* 866 */         for (i = 0; (i < this.C.size()) && (!m.this.isHaltRequested()); )
/*     */         {
/* 868 */           local_B = (m._B)this.C.elementAt(i);
/* 869 */           if (local_B != null)
/*     */           {
/* 871 */             local_B.A();
/* 872 */             if (!m.this.isHaltRequested())
/*     */             {
/* 874 */               int[] arrayOfInt = m._B.A(local_B);
/* 875 */               localVector.addElement(arrayOfInt);
/*     */             }
/*     */           }
/* 867 */           i++;
/*     */         }
/*     */ 
/*     */       }
/*     */       finally
/*     */       {
/*     */         try
/*     */         {
/* 886 */           O.A(this, "run: shutting down communications...");
/* 887 */           m.this.E(6);
/* 888 */           A();
/*     */         } finally {
/* 890 */           O.A(this, "run: done!  Number of commands processed: " + i);
/*     */ 
/* 893 */           if (m.this.isHaltRequested())
/* 894 */             m.this.B(9);
/*     */           else {
/* 896 */             m.this.B(1);
/*     */           }
/*     */ 
/* 899 */           m.this.U();
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/*     */     private void A(String paramString)
/*     */       throws IOException
/*     */     {
/* 914 */       m.this.D(paramString);
/*     */ 
/* 917 */       m.C(m.this);
/*     */     }
/*     */ 
/*     */     private void A()
/*     */       throws IOException
/*     */     {
/* 926 */       O.A(this, "endCommunications: shutting down serial port.");
/* 927 */       if (m.this.Y() != null)
/*     */       {
/* 929 */         m.this.Y().B();
/* 930 */         m.this.A(null);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private class _B extends n
/*     */   {
/*     */     static final int µ = 2;
/*     */     static final int ¤ = 10000;
/*     */     private String ª;
/*     */     private int[] º;
/*     */     private int[] ¥;
/*     */ 
/*     */     _B(String paramString1, String paramInt, int arg4)
/*     */     {
/* 463 */       super();
/* 464 */       this.ª = paramString1;
/*     */       int i;
/* 465 */       this.º = new int[i];
/*     */     }
/*     */ 
/*     */     void A()
/*     */       throws t, Z
/*     */     {
/* 482 */       int i = 0;
/* 483 */       int j = 0;
/*     */ 
/* 485 */       m.this.Y().E();
/*     */ 
/* 487 */       O.Ã = this.A;
/*     */       do
/*     */       {
/*     */         try
/*     */         {
/* 493 */           T();
/* 494 */           j = 1;
/*     */         } catch (t localt) {
/* 496 */           i++;
/* 497 */           m.this.Y().A();
/* 498 */           if (i <= 2) {
/* 499 */             O.D(this, "execute: cmd failed with exception: " + localt + "; retrying (attempts = " + (i + 1) + ")");
/*     */ 
/* 501 */             m.this.B(7);
/*     */           } else {
/* 503 */             O.E(this, "cmd " + this.ª + " (" + this.A + ") failed after " + i + " attempts" + "; exception = " + localt);
/*     */ 
/* 510 */             throw new t("execute: cmd " + this.ª + " (" + this.A + ") failed after " + i + " attempts");
/*     */           }
/*     */ 
/*     */         }
/*     */         catch (Z localZ)
/*     */         {
/* 516 */           if (localZ.getMessage().indexOf("Incorrect product code") == 0) {
/* 517 */             O.E(this, localZ.getMessage());
/* 518 */             throw localZ;
/*     */           }
/*     */ 
/* 521 */           i++;
/* 522 */           m.this.Y().A();
/* 523 */           if (i <= 2) {
/* 524 */             O.D(this, "execute: cmd failed with exception: " + localZ + "; retrying (attempts = " + (i + 1) + ")");
/*     */ 
/* 526 */             m.this.B(7);
/*     */           } else {
/* 528 */             O.E(this, "cmd " + this.ª + " (" + this.A + ") failed after " + i + " attempts" + "; exception = " + localZ);
/*     */ 
/* 535 */             throw new Z("execute: cmd " + this.ª + " (" + this.A + ") failed after " + i + " attempts");
/*     */           }
/*     */         }
/*     */         catch (IOException localIOException)
/*     */         {
/* 540 */           i++;
/* 541 */           m.this.Y().A();
/* 542 */           if (i <= 2) {
/* 543 */             O.D(this, "execute: cmd failed with exception: " + localIOException + "; retrying (attempts = " + (i + 1) + ")");
/*     */ 
/* 545 */             m.this.B(7);
/*     */           } else {
/* 547 */             O.E(this, "cmd " + this.ª + " (" + this.A + ") failed after " + i + " attempts" + "; exception = " + localIOException);
/*     */ 
/* 554 */             throw new t("execute: cmd " + this.ª + " (" + this.A + ") failed after " + i + " attempts");
/*     */           }
/*     */         }
/*     */       }
/*     */ 
/* 559 */       while ((j == 0) && (i <= 2));
/*     */     }
/*     */ 
/*     */     private int[] V()
/*     */     {
/* 568 */       return this.º;
/*     */     }
/*     */ 
/*     */     private String S() throws t, Z, IOException
/*     */     {
/* 584 */       StringBuffer localStringBuffer = new StringBuffer();
/*     */ 
/* 586 */       m.this.Y().E();
/*     */ 
/* 588 */       O.A(this, "readDeviceData: reading reply to cmd " + this.ª + " (" + this.A + ")");
/*     */       String str;
/*     */       do {
/* 594 */         m.this.Y().A('\006');
/*     */ 
/* 597 */         str = m.this.Y().C();
/*     */ 
/* 599 */         if (str.length() <= 1)
/*     */           continue;
/* 601 */         if (!O._B.C(str)) {
/* 602 */           O.E(this, "readDeviceData: ERROR - cmd " + this.ª + " resulted in bad CRC reply of " + O._B.E(str));
/*     */ 
/* 604 */           throw new Z("readDeviceData: ERROR - cmd " + this.ª + " resulted in bad CRC reply of " + O._B.E(str));
/*     */         }
/*     */ 
/* 609 */         O.A(this, "readDeviceData: cmd " + this.ª + " (" + this.A + ") returned " + str.length() + " data bytes, with reply = <" + str + ">");
/*     */ 
/* 612 */         localStringBuffer.append(str);
/*     */ 
/* 615 */         m.B(m.this, str.length());
/* 616 */         m.this.A(m.D(m.this), m.B(m.this));
/*     */       }
/* 618 */       while ((str.length() > 0) && (str.charAt(0) != '\004'));
/*     */ 
/* 620 */       return localStringBuffer.toString();
/*     */     }
/*     */ 
/*     */     private void U()
/*     */       throws t, IOException
/*     */     {
/* 633 */       m.this.Y().E();
/*     */ 
/* 635 */       O.A(this, "sendCommand: sending cmd " + this.ª + " (" + this.A + ")");
/*     */ 
/* 639 */       m.this.Y().A('\005');
/*     */ 
/* 642 */       E(6);
/*     */ 
/* 645 */       this.¥ = W();
/*     */ 
/* 648 */       m.this.Y().A(this.¥);
/*     */ 
/* 651 */       E(6);
/*     */ 
/* 654 */       m.this.Y().A('\004');
/*     */ 
/* 657 */       E(5);
/*     */     }
/*     */ 
/*     */     private void T()
/*     */       throws t, Z, IOException
/*     */     {
/* 671 */       StringBuffer localStringBuffer = new StringBuffer();
/* 672 */       String str = "";
/*     */ 
/* 677 */       if (m.this.T() != 7) {
/* 678 */         m.this.B(4);
/*     */       }
/* 680 */       U();
/*     */ 
/* 682 */       if (this.º.length > 0)
/*     */       {
/* 684 */         if (!m.this.isHaltRequested())
/*     */         {
/* 686 */           m.this.B(5);
/* 687 */           str = S();
/* 688 */           localStringBuffer.append(str);
/*     */         }
/*     */ 
/* 692 */         if ((str.length() == 0) && (!m.this.isHaltRequested()))
/*     */         {
/* 694 */           O._B.G(10000);
/* 695 */           str = S();
/* 696 */           localStringBuffer.append(str);
/*     */         }
/*     */ 
/* 700 */         while ((str.length() > 0) && (!m.this.isHaltRequested())) {
/* 701 */           if (m.this.isHaltRequested())
/*     */             continue;
/* 703 */           str = S();
/* 704 */           localStringBuffer.append(str);
/*     */         }
/*     */ 
/* 708 */         this.º = O._B.D(localStringBuffer.toString());
/*     */ 
/* 710 */         if (!m.this.isHaltRequested())
/* 711 */           m.A(m.this, this);
/*     */       }
/*     */     }
/*     */ 
/*     */     private int[] W()
/*     */     {
/* 725 */       int i = 0;
/*     */ 
/* 727 */       Contract.pre(this.ª.length() > 0);
/*     */ 
/* 729 */       int[] arrayOfInt = new int[this.ª.length() + 7];
/*     */ 
/* 732 */       arrayOfInt[(i++)] = 2;
/*     */ 
/* 735 */       arrayOfInt[(i++)] = 49;
/*     */ 
/* 739 */       for (int j = i; j < 2 + this.ª.length(); j++) {
/* 740 */         arrayOfInt[(i++)] = this.ª.charAt(j - 2);
/*     */       }
/*     */ 
/* 744 */       arrayOfInt[(i++)] = 3;
/*     */ 
/* 747 */       j = O._B.H(arrayOfInt);
/* 748 */       arrayOfInt[(i++)] = O._B.L(O._B.M(j));
/* 749 */       arrayOfInt[(i++)] = O._B.L(O._B.D(j));
/*     */ 
/* 752 */       arrayOfInt[(i++)] = 13;
/* 753 */       arrayOfInt[(i++)] = 10;
/* 754 */       return arrayOfInt;
/*     */     }
/*     */ 
/*     */     private void E(int paramInt)
/*     */       throws t, IOException
/*     */     {
/* 767 */       int i = m.this.Y().O();
/*     */ 
/* 769 */       if (i != paramInt)
/* 770 */         throw new t("readAckByte: reply " + i + " does not match expected reply of " + paramInt);
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.A.m
 * JD-Core Version:    0.6.0
 */