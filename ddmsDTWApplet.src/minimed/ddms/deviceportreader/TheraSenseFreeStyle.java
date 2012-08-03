/*     */ package minimed.ddms.A;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.text.DateFormatSymbols;
/*     */ import java.text.ParsePosition;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.Vector;
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ class A extends O
/*     */ {
/*     */   public static final int Ĝ = 160;
/*     */   static final String ě = "mem";
/*     */   static final int ģ = 5000;
/*     */   static final int Ħ = 14520;
/*     */   private static final String Ĥ = "MMMM dd yyyy HH:mm:ss";
/*  51 */   private static final String[] Ĩ = { "Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sept", "Oct", "Nov", "Dec" };
/*     */ 
/*  58 */   private static final String[] ħ = { "Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sep", "Oct", "Nov", "Dec" };
/*     */   private static final int Ģ = 10;
/*     */   private static final int ğ = 1000;
/*     */   private String ĥ;
/*     */   private _B ĝ;
/*     */   private int ġ;
/*     */   private int Ī;
/*     */   private String ĩ;
/*     */   private final SimpleDateFormat Ġ;
/*     */   private final SimpleDateFormat Ğ;
/*     */ 
/*     */   A(String paramString1, int paramInt, String paramString2)
/*     */   {
/*  95 */     this.ć = paramString2;
/*  96 */     A(this, "creating interface to the '" + this.ć + "', package version " + K());
/*     */ 
/*  98 */     this.ā = 12;
/*  99 */     this.Þ = paramInt;
/* 100 */     this.Ė = new _A();
/*     */ 
/* 102 */     this.ĥ = new String(new byte[14520]);
/*     */ 
/* 105 */     this.ĝ = new _B(paramString1, "Read Log", this.ĥ.length(), null);
/*     */ 
/* 109 */     DateFormatSymbols localDateFormatSymbols = new DateFormatSymbols();
/*     */ 
/* 112 */     localDateFormatSymbols.setShortMonths(Ĩ);
/* 113 */     this.Ġ = new SimpleDateFormat("MMMM dd yyyy HH:mm:ss", localDateFormatSymbols);
/*     */ 
/* 116 */     localDateFormatSymbols = new DateFormatSymbols();
/*     */ 
/* 119 */     localDateFormatSymbols.setShortMonths(ħ);
/* 120 */     this.Ğ = new SimpleDateFormat("MMMM dd yyyy HH:mm:ss", localDateFormatSymbols);
/*     */   }
/*     */ 
/*     */   A()
/*     */   {
/* 127 */     this("mem", 160, "TheraSense FreeStyle Meter");
/*     */   }
/*     */ 
/*     */   public void A(v paramv, String paramString1, String paramString2)
/*     */     throws t, Z, IOException
/*     */   {
/* 152 */     A(this, "readData: starting reader...");
/* 153 */     B(false);
/*     */ 
/* 156 */     Vector localVector = a();
/*     */ 
/* 159 */     _C local_C = new _C(paramv, paramString1, localVector, null);
/* 160 */     _C.A(local_C);
/*     */   }
/*     */ 
/*     */   void C(v paramv)
/*     */     throws t, IOException
/*     */   {
/*     */     try
/*     */     {
/* 173 */       this.ĝ.A();
/*     */     }
/*     */     catch (Z localZ) {
/* 176 */       throw new t("Got reply, but format is bad.");
/*     */     }
/*     */   }
/*     */ 
/*     */   int Z()
/*     */   {
/* 186 */     return 3;
/*     */   }
/*     */ 
/*     */   void D(String paramString)
/*     */     throws IOException
/*     */   {
/* 197 */     C(paramString);
/* 198 */     A(new d(paramString, 8));
/* 199 */     Y().A();
/* 200 */     Y().A(10);
/* 201 */     Y().B(1000);
/*     */   }
/*     */ 
/*     */   void _()
/*     */     throws IOException
/*     */   {
/* 210 */     if (Y() != null)
/*     */     {
/* 212 */       Y().B();
/* 213 */       A(null);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void b()
/*     */     throws Z
/*     */   {
/* 225 */     this.ĕ = E(this.ĩ);
/* 226 */     A(this, "decodeCurrentTimeStamp: current time stamp for device is " + this.ĕ);
/*     */   }
/*     */ 
/*     */   private final void A(_B param_B)
/*     */     throws Z
/*     */   {
/* 241 */     String str = O._B.E(param_B.ã);
/*     */ 
/* 243 */     if (_B.A(param_B).equals(_B.A(this.ĝ))) {
/* 244 */       this.ĥ = str;
/* 245 */       A(this, "decodeReply: reply is Log reply: '" + this.ĥ + "'");
/*     */ 
/* 248 */       b();
/*     */     } else {
/* 250 */       throw new Z("Command '" + _B.A(param_B) + " ' is unrecognized");
/*     */     }
/*     */   }
/*     */ 
/*     */   private final void c()
/*     */     throws t
/*     */   {
/* 262 */     Y().E();
/*     */ 
/* 264 */     E(4);
/*     */ 
/* 266 */     O._B.G(5000);
/*     */   }
/*     */ 
/*     */   private final Vector a()
/*     */   {
/* 275 */     Vector localVector = new Vector();
/*     */ 
/* 278 */     localVector.addElement(this.ĝ);
/* 279 */     return localVector;
/*     */   }
/*     */ 
/*     */   private Date E(String paramString)
/*     */     throws Z
/*     */   {
/* 293 */     Contract.pre(paramString != null);
/* 294 */     Contract.pre(paramString.length() == "MMMM dd yyyy HH:mm:ss".length());
/*     */ 
/* 297 */     Date localDate = this.Ġ.parse(paramString, new ParsePosition(0));
/*     */ 
/* 299 */     if (localDate == null)
/*     */     {
/* 302 */       localDate = this.Ğ.parse(paramString, new ParsePosition(0));
/* 303 */       if (localDate == null)
/*     */       {
/* 305 */         throw new Z("Bad device time string (dataF is null) '" + paramString + "' received");
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 310 */     return localDate;
/*     */   }
/*     */ 
/*     */   private final class _A extends O._A
/*     */   {
/*     */     static final int Ě = 70;
/*     */     static final int ę = 1;
/*     */     static final int Ę = 1;
/*     */ 
/*     */     _A()
/*     */     {
/* 809 */       super(70);
/* 810 */       A.this.e = 0;
/* 811 */       A.this.ì = 0;
/* 812 */       A.this.£ = 0;
/*     */     }
/*     */ 
/*     */     void A()
/*     */     {
/* 821 */       String str = "";
/*     */ 
/* 824 */       A.this.Î = new CA(A.this.Þ, 1, str, str, str);
/*     */ 
/* 827 */       O.A(this, "createSnapshot: creating snapshot");
/*     */ 
/* 831 */       A.this.Î.A(1, A.D(A.this));
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
/* 655 */       A.this.B(paramString);
/* 656 */       this.B = paramVector;
/*     */       Object localObject;
/* 657 */       this.C = localObject;
/*     */     }
/*     */ 
/*     */     private final void B()
/*     */       throws t, Z, IOException
/*     */     {
/* 673 */       int i = 0;
/*     */ 
/* 675 */       Vector localVector = new Vector();
/*     */ 
/* 679 */       A.this.Ă = "";
/* 680 */       A.this.ă = "";
/*     */ 
/* 684 */       A.A(A.this, 0);
/* 685 */       A.C(A.this, 0);
/*     */       A._B local_B;
/* 686 */       for (int j = 0; j < this.C.size(); j++) {
/* 687 */         local_B = (A._B)this.C.elementAt(j);
/* 688 */         if (local_B != null) {
/* 689 */           A.B(A.this, local_B.ã.length);
/*     */         }
/*     */       }
/*     */ 
/* 693 */       A.this.C(0);
/*     */       try
/*     */       {
/* 701 */         A.this.B(2);
/*     */         try
/*     */         {
/* 704 */           A(this.B);
/*     */         } catch (IOException localIOException) {
/* 706 */           if (!A.this.isHaltRequested()) {
/* 707 */             O.D(this, "acquireDataFromDevice: initCommunications failed with IOException; retrying... (exception = " + localIOException + ")");
/*     */ 
/* 710 */             A(this.B);
/*     */           }
/*     */         }
/*     */ 
/* 714 */         A.this.E(5);
/*     */ 
/* 717 */         for (i = 0; (i < this.C.size()) && (!A.this.isHaltRequested()); )
/*     */         {
/* 719 */           local_B = (A._B)this.C.elementAt(i);
/* 720 */           if (local_B != null)
/*     */           {
/* 722 */             local_B.A();
/* 723 */             if (!A.this.isHaltRequested())
/*     */             {
/* 725 */               int[] arrayOfInt = A._B.B(local_B);
/* 726 */               localVector.addElement(arrayOfInt);
/*     */             }
/*     */           }
/* 718 */           i++;
/*     */         }
/*     */ 
/*     */       }
/*     */       finally
/*     */       {
/*     */         try
/*     */         {
/* 737 */           O.A(this, "run: shutting down communications...");
/* 738 */           A.this.E(6);
/* 739 */           A();
/*     */         } finally {
/* 741 */           O.A(this, "run: done!  Number of commands processed: " + i);
/*     */ 
/* 744 */           if (A.this.isHaltRequested())
/* 745 */             A.this.B(9);
/*     */           else {
/* 747 */             A.this.B(1);
/*     */           }
/*     */ 
/* 750 */           A.this.U();
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/*     */     private final void A(String paramString)
/*     */       throws IOException, t
/*     */     {
/* 766 */       A.this.D(paramString);
/*     */ 
/* 769 */       A.B(A.this);
/*     */     }
/*     */ 
/*     */     private final void A()
/*     */       throws IOException
/*     */     {
/* 778 */       O.A(this, "endCommunications: shutting down serial port.");
/* 779 */       if (A.this.Y() != null)
/*     */       {
/* 781 */         A.this.Y().B();
/* 782 */         A.this.A(null);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private final class _B extends n
/*     */   {
/*     */     static final byte æ = -1;
/*     */     static final int ä = 2;
/*     */     static final int ë = 100;
/*     */     static final String ê = "END\r\n";
/*     */     static final String è = "LOG EMPTY";
/*     */     static final int å = 13;
/*     */     int[] ã;
/*     */     private String ç;
/*     */ 
/*     */     private _B(String paramString1, String paramInt, int arg4)
/*     */     {
/* 368 */       super();
/* 369 */       this.ç = paramString1;
/*     */       int i;
/* 370 */       this.ã = new int[i];
/*     */     }
/*     */ 
/*     */     void A()
/*     */       throws t, Z
/*     */     {
/* 385 */       int i = 0;
/* 386 */       int j = 0;
/*     */ 
/* 388 */       A.this.Y().E();
/*     */ 
/* 390 */       O.Ã = this.A;
/*     */       do
/*     */       {
/*     */         try
/*     */         {
/* 396 */           v();
/* 397 */           j = 1;
/*     */         } catch (t localt) {
/* 399 */           i++;
/* 400 */           A.this.Y().A();
/* 401 */           if (i <= 2) {
/* 402 */             O.D(this, "execute: cmd failed with exception: " + localt + "; retrying (attempts = " + (i + 1) + ")");
/*     */ 
/* 404 */             A.this.B(7);
/*     */           } else {
/* 406 */             O.E(this, "cmd " + this.ç + " (" + this.A + ") failed after " + i + " attempts" + "; exception = " + localt);
/*     */ 
/* 413 */             throw new t("execute: cmd " + this.ç + " (" + this.A + ") failed after " + i + " attempts");
/*     */           }
/*     */         }
/*     */         catch (Z localZ)
/*     */         {
/* 418 */           i++;
/* 419 */           A.this.Y().A();
/* 420 */           if (i <= 2) {
/* 421 */             O.D(this, "execute: cmd failed with exception: " + localZ + "; retrying (attempts = " + (i + 1) + ")");
/*     */ 
/* 423 */             A.this.B(7);
/*     */           } else {
/* 425 */             O.E(this, "cmd " + this.ç + " (" + this.A + ") failed after " + i + " attempts" + "; exception = " + localZ);
/*     */ 
/* 432 */             throw new Z("execute: cmd " + this.ç + " (" + this.A + ") failed after " + i + " attempts");
/*     */           }
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 438 */       while ((j == 0) && (i <= 2));
/*     */     }
/*     */ 
/*     */     private final int[] x()
/*     */     {
/* 447 */       return this.ã;
/*     */     }
/*     */ 
/*     */     private final String w()
/*     */       throws t, Z
/*     */     {
/* 461 */       StringBuffer localStringBuffer = new StringBuffer();
/*     */ 
/* 463 */       int i = 0;
/*     */ 
/* 465 */       A.this.Y().E();
/*     */ 
/* 467 */       O.A(this, "readDeviceData: reading reply to cmd " + this.ç + " (" + this.A + ")");
/*     */       String str1;
/*     */       try
/*     */       {
/* 477 */         str1 = A.this.Y().C();
/* 478 */         if (str1.length() == 2)
/*     */         {
/* 480 */           localStringBuffer.append(str1);
/* 481 */           str1 = A.this.Y().C();
/*     */         }
/*     */ 
/* 484 */         localStringBuffer.append(str1);
/* 485 */         A.this.ă = str1.trim();
/*     */ 
/* 488 */         str1 = A.this.Y().C();
/* 489 */         localStringBuffer.append(str1);
/* 490 */         A.this.Ă = str1.trim();
/*     */ 
/* 493 */         str1 = A.this.Y().C();
/* 494 */         localStringBuffer.append(str1);
/* 495 */         A.A(A.this, str1.trim());
/*     */       } catch (IOException localIOException1) {
/* 497 */         throw new t("readDeviceData: ERROR - an IOException  has occurred processing cmd " + this.ç + " (" + this.A + ")");
/*     */       }
/*     */ 
/*     */       do
/*     */       {
/*     */         try
/*     */         {
/* 507 */           str1 = A.this.Y().C();
/* 508 */           localStringBuffer.append(str1);
/* 509 */           i = (str1.endsWith("END\r\n")) || (str1.length() == 0) ? 1 : 0;
/*     */         } catch (IOException localIOException2) {
/* 511 */           throw new t("readDeviceData: ERROR - an IOException  has occurred processing cmd " + this.ç + " (" + this.A + ")");
/*     */         }
/*     */       }
/*     */ 
/* 515 */       while (i == 0);
/*     */ 
/* 517 */       String str2 = localStringBuffer.toString();
/* 518 */       String str3 = str1;
/*     */ 
/* 520 */       if (str2.length() > 0)
/*     */       {
/* 522 */         if (str2.toUpperCase().indexOf("LOG EMPTY") == -1)
/*     */         {
/* 524 */           int[] arrayOfInt = O._B.D(str2);
/* 525 */           int j = O._B.D(arrayOfInt, 0, arrayOfInt.length - 13);
/*     */ 
/* 530 */           if (str3.length() < 6) {
/* 531 */             throw new Z("ERROR - CRC record too small: " + str3);
/*     */           }
/*     */ 
/* 535 */           String str4 = str3.substring(2, 6);
/* 536 */           int k = 0;
/*     */           try {
/* 538 */             k = Integer.parseInt(str4, 16);
/*     */           } catch (NumberFormatException localNumberFormatException) {
/* 540 */             throw new Z("ERROR - '" + O._B.E(str2) + "' record has bad CRC value of " + str4 + " (expected " + O._B.H(j) + ").");
/*     */           }
/*     */ 
/* 547 */           if (j != k) {
/* 548 */             throw new Z("ERROR - " + O._B.E(str2) + " record has incorrect CRC value of " + O._B.H(k) + " (expected " + O._B.H(j) + ").");
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/* 554 */         O.A(this, "readDeviceData: cmd " + this.ç + " (" + this.A + ") returned " + str2.length() + " data bytes, with reply = <" + str2 + ">");
/*     */       }
/*     */       else
/*     */       {
/* 558 */         throw new t("readDeviceData: ERROR - no reply for cmd " + this.ç + " (" + this.A + ")");
/*     */       }
/*     */ 
/* 561 */       return str2;
/*     */     }
/*     */ 
/*     */     private final void y()
/*     */       throws t
/*     */     {
/* 572 */       A.this.Y().E();
/*     */ 
/* 574 */       O.A(this, "sendCommand: sending cmd " + this.ç + " (" + this.A + ")");
/*     */       try
/*     */       {
/* 578 */         A.this.Y().A(this.ç);
/*     */       } catch (IOException localIOException) {
/* 580 */         throw new t("sendCommand: ERROR - an IOException  has occurred processing cmd " + this.ç + " (" + this.A + ")");
/*     */       }
/*     */     }
/*     */ 
/*     */     private final void v()
/*     */       throws t, Z
/*     */     {
/* 600 */       if (A.this.T() != 7) {
/* 601 */         A.this.B(4);
/*     */       }
/* 603 */       y();
/*     */ 
/* 605 */       if (this.ã.length > 0)
/*     */       {
/* 607 */         if (!A.this.isHaltRequested())
/*     */         {
/* 609 */           A.this.B(5);
/* 610 */           this.ã = O._B.D(w());
/*     */         }
/*     */ 
/* 614 */         if ((this.ã.length == 0) && (!A.this.isHaltRequested()))
/*     */         {
/* 616 */           O._B.G(100);
/* 617 */           this.ã = O._B.D(w());
/*     */         }
/*     */ 
/* 620 */         A.D(A.this, this.ã.length);
/*     */ 
/* 623 */         A.this.A(A.C(A.this), A.A(A.this));
/*     */ 
/* 625 */         if (!A.this.isHaltRequested())
/* 626 */           A.A(A.this, this);
/*     */       }
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.A.A
 * JD-Core Version:    0.6.0
 */