/*      */ package minimed.ddms.A;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.text.ParsePosition;
/*      */ import java.text.SimpleDateFormat;
/*      */ import java.util.Arrays;
/*      */ import java.util.Date;
/*      */ import java.util.Vector;
/*      */ import minimed.util.Contract;
/*      */ 
/*      */ abstract class N extends O
/*      */ {
/*   35 */   private static final int[] α = { 11, 13 };
/*      */   private static final int Ω = 50;
/*      */   private static final char Ρ = '$';
/*      */   private static final int Σ = 4;
/*      */   private static final int δ = 5;
/*      */   private static final int ε = 100;
/*      */   private static final int Ϊ = 5;
/*   65 */   private int Φ = 5;
/*      */   private Vector ί;
/*      */   private _A Υ;
/*      */   private _A Ο;
/*      */   private _A Ν;
/*      */   private _A ά;
/*      */   private _A β;
/*      */   private _A ΰ;
/*      */   private _A Ξ;
/*      */   private _A ή;
/*      */   private int Χ;
/*      */   private int Π;
/*   83 */   private String Τ = "";
/*      */ 
/*   88 */   private String γ = "";
/*      */   private int[] Ψ;
/*      */   private int Ϋ;
/*      */   private int έ;
/*      */ 
/*      */   N(String paramString, int paramInt1, int paramInt2, int[] paramArrayOfInt1, int[] paramArrayOfInt2, int[] paramArrayOfInt3, int[] paramArrayOfInt4, int[] paramArrayOfInt5, int[] paramArrayOfInt6, int[] paramArrayOfInt7, int paramInt3, int paramInt4)
/*      */   {
/*  120 */     this.ć = paramString;
/*  121 */     this.Þ = paramInt1;
/*  122 */     this.ā = paramInt2;
/*  123 */     A(this, "creating interface to the '" + this.ć + "', package version " + K());
/*      */ 
/*  126 */     this.Ė = new _C();
/*  127 */     this.έ = paramInt4;
/*      */ 
/*  130 */     this.Υ = new _A(paramArrayOfInt1, "Read Firmware Version", new _D()
/*      */     {
/*      */       public void A(N._A param_A) throws Z {
/*  133 */         N.this.Ă = N.this.U(N._A.A(param_A));
/*  134 */         O.A(this, "decodeReply: firmware version is '" + N.this.Ă + "'");
/*      */       }
/*      */     });
/*  139 */     this.Ο = new _A(paramArrayOfInt2, "Read Serial Number", new _D()
/*      */     {
/*      */       public void A(N._A param_A) throws Z {
/*  142 */         N.this.ă = N.this.U(N._A.A(param_A));
/*  143 */         O.A(this, "decodeReply: serial number is '" + N.this.ă + "'");
/*      */       }
/*      */     });
/*  148 */     this.Ν = new _A(paramArrayOfInt3, "Read Time", new _D()
/*      */     {
/*      */       public void A(N._A param_A) throws Z {
/*  151 */         N.A(N.this, N.this.U(N._A.A(param_A)));
/*  152 */         O.A(this, "decodeReply: current time is '" + N.A(N.this) + "'");
/*      */       }
/*      */     });
/*  157 */     this.ά = new _A(paramArrayOfInt4, "Read Date", new _D()
/*      */     {
/*      */       public void A(N._A param_A) throws Z {
/*  160 */         N.B(N.this, N.this.U(N._A.A(param_A)));
/*  161 */         O.A(this, "decodeReply: current date is '" + N.M(N.this) + "'");
/*  162 */         N.I(N.this);
/*      */       }
/*      */     });
/*  167 */     this.β = new _A(paramArrayOfInt5, "Read Model Number", new _D()
/*      */     {
/*      */       public void A(N._A param_A) throws Z {
/*  170 */         N.this.À = N.this.U(N._A.A(param_A));
/*  171 */         O.A(this, "decodeReply: model number is '" + N.this.À + "'");
/*      */       }
/*      */     });
/*  176 */     this.ΰ = new _A(paramArrayOfInt6, "Get Number of Results Stored", new _D()
/*      */     {
/*      */       public void A(N._A param_A) throws Z {
/*  179 */         String str = N.this.U(N._A.A(param_A));
/*      */         try {
/*  181 */           N.G(N.this, Integer.parseInt(str));
/*      */         } catch (NumberFormatException localNumberFormatException) {
/*  183 */           throw new Z("decodeReply: bad number of results: " + str);
/*      */         }
/*      */ 
/*  186 */         O.A(this, "decodeReply: number of meter results is " + N.L(N.this));
/*      */ 
/*  190 */         int i = N.K(N.this) * N.L(N.this);
/*  191 */         int j = N._A.A(N.C(N.this)).length - i;
/*  192 */         N.E(N.this, j);
/*      */ 
/*  195 */         N.G(N.this);
/*      */       }
/*      */     });
/*  200 */     this.Ξ = new _A(paramArrayOfInt7, "Extract Results", paramInt3, new _D()
/*      */     {
/*      */       public void A(N._A param_A) throws Z {
/*  203 */         N.A(N.this, N._A.A(param_A));
/*  204 */         O.A(this, "decodeReply: meter results is " + N.H(N.this).length + " bytes.");
/*      */       }
/*      */     });
/*  210 */     this.ή = new _A(α, "Clear Error Status", new _D()
/*      */     {
/*      */       public void A(N._A param_A) throws Z {
/*  213 */         O.A(this, "decodeReply: error status is " + N.this.U(N._A.A(param_A)));
/*      */       }
/*      */     });
/*      */   }
/*      */ 
/*      */   public void A(v paramv, String paramString1, String paramString2)
/*      */     throws t, Z, IOException
/*      */   {
/*  239 */     A(this, "readData: starting reader...");
/*  240 */     B(false);
/*      */ 
/*  243 */     Vector localVector = r();
/*      */ 
/*  246 */     _B local_B = new _B(paramv, paramString1, localVector, null);
/*  247 */     _B.A(local_B);
/*      */   }
/*      */ 
/*      */   int Z()
/*      */   {
/*  256 */     return 3;
/*      */   }
/*      */ 
/*      */   void C(v paramv)
/*      */     throws t, IOException
/*      */   {
/*      */     try
/*      */     {
/*  268 */       u();
/*  269 */       this.β.A();
/*      */     }
/*      */     catch (Z localZ) {
/*  272 */       throw new t("Got reply, but format is bad.");
/*      */     }
/*      */   }
/*      */ 
/*      */   void D(String paramString)
/*      */     throws IOException
/*      */   {
/*  284 */     C(paramString);
/*  285 */     A(new d(paramString, 7));
/*  286 */     Y().A();
/*  287 */     Y().A(this.Φ);
/*      */   }
/*      */ 
/*      */   void _()
/*      */     throws IOException
/*      */   {
/*  296 */     if (Y() != null)
/*      */     {
/*  298 */       Y().B();
/*  299 */       A(null);
/*      */     }
/*      */   }
/*      */ 
/*      */   Vector r()
/*      */   {
/*  309 */     Vector localVector = new Vector();
/*      */ 
/*  311 */     localVector.addElement(this.Υ);
/*  312 */     localVector.addElement(this.Ο);
/*      */ 
/*  315 */     localVector.addElement(this.Ν);
/*  316 */     localVector.addElement(this.ά);
/*  317 */     localVector.addElement(this.β);
/*  318 */     localVector.addElement(this.ΰ);
/*  319 */     localVector.addElement(this.Ξ);
/*  320 */     return localVector;
/*      */   }
/*      */ 
/*      */   abstract String U(int[] paramArrayOfInt)
/*      */     throws Z;
/*      */ 
/*      */   String I(String paramString)
/*      */   {
/*  340 */     Contract.preNonNull(paramString);
/*  341 */     StringBuffer localStringBuffer = new StringBuffer(paramString);
/*      */ 
/*  343 */     for (int i = paramString.length(); i < 50; i++) {
/*  344 */       localStringBuffer.append('$');
/*      */     }
/*      */ 
/*  347 */     return localStringBuffer.toString();
/*      */   }
/*      */ 
/*      */   String K(int paramInt)
/*      */   {
/*  357 */     return I("" + paramInt);
/*      */   }
/*      */ 
/*      */   private void q()
/*      */     throws Z
/*      */   {
/*  369 */     Contract.pre(this.γ.length() > 0);
/*  370 */     Contract.pre(this.Τ.length() > 0);
/*      */ 
/*  374 */     this.ĕ = H(this.γ + this.Τ);
/*  375 */     A(this, "decodeCurrentTimeStamp: current time stamp for device is " + this.ĕ);
/*      */   }
/*      */ 
/*      */   private final Date H(String paramString)
/*      */     throws Z
/*      */   {
/*  393 */     String str = "yyMMddHHmmss";
/*  394 */     Contract.preNonNull(paramString);
/*  395 */     Contract.pre(paramString.length() == str.length());
/*      */ 
/*  397 */     SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(str);
/*  398 */     Date localDate = localSimpleDateFormat.parse(paramString, new ParsePosition(0));
/*      */ 
/*  400 */     if (localDate == null)
/*      */     {
/*  402 */       throw new Z("Bad device time string (null returned) '" + paramString + "' received");
/*      */     }
/*      */ 
/*  406 */     return localDate;
/*      */   }
/*      */ 
/*      */   private final void u()
/*      */     throws t, Z
/*      */   {
/*  417 */     E(4);
/*      */     try
/*      */     {
/*  420 */       t();
/*      */     } catch (IOException localIOException) {
/*  422 */       throw new t("initDevice: ERROR - an IOException  has occurred: " + localIOException);
/*      */     }
/*      */ 
/*  426 */     Y().A();
/*      */ 
/*  429 */     this.ή.A();
/*      */   }
/*      */ 
/*      */   private void t()
/*      */     throws IOException
/*      */   {
/*  439 */     Y().L();
/*  440 */     Y().P();
/*  441 */     O._B.G(1);
/*  442 */     Y().E(7);
/*  443 */     Y().J();
/*  444 */     Y().L();
/*  445 */     O._B.G(1);
/*      */   }
/*      */ 
/*      */   private void s()
/*      */   {
/*  452 */     if (this.Ϋ > 0)
/*      */     {
/*  454 */       String str = Integer.toString(this.Ϋ);
/*      */ 
/*  457 */       for (int i = str.length(); i < 3; i++) {
/*  458 */         str = '0' + str;
/*      */       }
/*      */ 
/*  461 */       i = 0;
/*      */ 
/*  463 */       for (int j = 0; j < str.length(); j++) {
/*  464 */         _A.B(this.Ξ)[(4 + j)] = str.charAt(j);
/*      */ 
/*  466 */         i = j;
/*      */       }
/*      */ 
/*  470 */       _A.B(this.Ξ)[(4 + i + 1)] = 13;
/*      */     }
/*      */     else {
/*  473 */       this.ί.remove(this.Ξ);
/*      */     }
/*      */   }
/*      */ 
/*      */   static abstract interface _D
/*      */   {
/*      */     public abstract void A(N._A param_A)
/*      */       throws Z;
/*      */   }
/*      */ 
/*      */   class _C extends O._A
/*      */   {
/*      */     private static final int c = 270;
/*      */     private static final int b = 1;
/*      */     private static final int a = 2;
/*      */     private static final int d = 3;
/*      */ 
/*      */     _C()
/*      */     {
/* 1083 */       super(270);
/* 1084 */       N.this.e = 0;
/* 1085 */       N.this.ì = 0;
/* 1086 */       N.this.£ = 0;
/*      */     }
/*      */ 
/*      */     void A()
/*      */     {
/* 1096 */       N.this.Î = new CA(N.this.Þ, 1, N.this.I(N.this.Ă), N.this.I(N.this.ă), N.this.I(N.M(N.this) + " " + N.A(N.this)));
/*      */ 
/* 1099 */       O.A(this, "createSnapshot: creating snapshot");
/*      */ 
/* 1102 */       N.this.Î.A(1, N.this.I(N.this.À));
/* 1103 */       N.this.Î.A(2, N.this.K(N.L(N.this)));
/* 1104 */       N.this.Î.A(3, N.H(N.this));
/*      */     }
/*      */   }
/*      */ 
/*      */   private class _B
/*      */   {
/*      */     private String B;
/*      */ 
/*      */     private _B(v paramString, String paramVector, Vector arg4)
/*      */     {
/*  920 */       N.this.B(paramString);
/*  921 */       this.B = paramVector;
/*      */       Vector localVector;
/*  922 */       N.A(N.this, localVector);
/*      */     }
/*      */ 
/*      */     private final void B()
/*      */       throws t, Z, IOException
/*      */     {
/*  938 */       int i = 0;
/*      */ 
/*  940 */       Vector localVector = new Vector();
/*      */ 
/*  942 */       N.this.À = "";
/*  943 */       N.this.Ă = "";
/*  944 */       N.this.ă = "";
/*  945 */       N.A(N.this, "");
/*  946 */       N.B(N.this, "");
/*  947 */       N.A(N.this, new int[0]);
/*      */ 
/*  951 */       N.B(N.this, 0);
/*  952 */       N.C(N.this, 0);
/*      */       N._A local_A;
/*  954 */       for (int j = 0; j < N.F(N.this).size(); j++) {
/*  955 */         local_A = (N._A)N.F(N.this).elementAt(j);
/*  956 */         if (local_A != null) {
/*  957 */           N.A(N.this, N._A.A(local_A).length);
/*      */         }
/*      */       }
/*      */ 
/*  961 */       N.this.C(0);
/*      */       try
/*      */       {
/*  970 */         N.this.B(2);
/*      */         try
/*      */         {
/*  974 */           A(this.B);
/*      */         } catch (IOException localIOException) {
/*  976 */           if (!N.this.isHaltRequested()) {
/*  977 */             O.D(this, "acquireDataFromDevice: initCommunications failed with IOException; retrying... (exception = " + localIOException + ")");
/*      */ 
/*  980 */             A(this.B);
/*      */           }
/*      */         }
/*      */ 
/*  984 */         N.this.E(5);
/*      */ 
/*  987 */         for (i = 0; (i < N.F(N.this).size()) && (!N.this.isHaltRequested()); )
/*      */         {
/*  989 */           local_A = (N._A)N.F(N.this).elementAt(i);
/*  990 */           if (local_A != null)
/*      */           {
/*  992 */             local_A.A();
/*  993 */             if (!N.this.isHaltRequested())
/*      */             {
/*  995 */               int[] arrayOfInt = local_A.I();
/*  996 */               localVector.addElement(arrayOfInt);
/*      */             }
/*      */           }
/*  988 */           i++;
/*      */         }
/*      */ 
/*      */       }
/*      */       finally
/*      */       {
/*      */         try
/*      */         {
/* 1007 */           O.A(this, "run: shutting down communications...");
/* 1008 */           N.this.E(6);
/* 1009 */           A();
/*      */         } finally {
/* 1011 */           O.A(this, "run: done!  Number of commands processed: " + i);
/*      */ 
/* 1014 */           if (N.this.isHaltRequested())
/* 1015 */             N.this.B(9);
/*      */           else {
/* 1017 */             N.this.B(1);
/*      */           }
/*      */ 
/* 1020 */           N.this.U();
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/*      */     private final void A(String paramString)
/*      */       throws IOException, t, Z
/*      */     {
/* 1038 */       N.this.D(paramString);
/*      */ 
/* 1041 */       N.B(N.this);
/*      */     }
/*      */ 
/*      */     private final void A()
/*      */       throws IOException
/*      */     {
/* 1050 */       O.A(this, "endCommunications: shutting down serial port.");
/* 1051 */       if (N.this.Y() != null)
/*      */       {
/* 1053 */         N.this.Y().B();
/* 1054 */         N.this.A(null);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   class _A extends n
/*      */   {
/*      */     private static final int i = 110;
/*      */     private static final int n = 3;
/*      */     private static final int k = 2;
/*      */     private int[] j;
/*      */     private int[] l;
/*      */     private N._D m;
/*      */ 
/*      */     _A(int[] paramString, String paramInt, int param_D, N._D arg5)
/*      */     {
/*  525 */       super();
/*  526 */       this.j = paramString;
/*  527 */       this.l = new int[param_D];
/*      */       Object localObject;
/*  528 */       this.m = localObject;
/*      */     }
/*      */ 
/*      */     _A(int[] paramString, String param_D, N._D arg4)
/*      */     {
/*  540 */       this(paramString, param_D, 50, local_D);
/*      */     }
/*      */ 
/*      */     public String toString()
/*      */     {
/*  551 */       return this.A + " (" + J() + ")";
/*      */     }
/*      */ 
/*      */     public int hashCode()
/*      */     {
/*  561 */       return toString().hashCode();
/*      */     }
/*      */ 
/*      */     public boolean equals(Object paramObject)
/*      */     {
/*  574 */       if (this == paramObject) {
/*  575 */         return true;
/*      */       }
/*  577 */       if (paramObject == null) {
/*  578 */         return false;
/*      */       }
/*  580 */       if (getClass() != paramObject.getClass()) {
/*  581 */         return false;
/*      */       }
/*      */ 
/*  584 */       _A local_A = (_A)paramObject;
/*      */ 
/*  586 */       return Arrays.equals(this.j, local_A.j);
/*      */     }
/*      */ 
/*      */     void A()
/*      */       throws t, Z
/*      */     {
/*  603 */       int i1 = 0;
/*  604 */       int i2 = 0;
/*      */ 
/*  606 */       this.h.Y().E();
/*      */ 
/*  608 */       O.Ã = this.A;
/*      */       do
/*      */       {
/*      */         try
/*      */         {
/*  614 */           G();
/*  615 */           i2 = 1;
/*      */         } catch (t localt) {
/*  617 */           i1++;
/*  618 */           this.h.Y().A();
/*  619 */           if (i1 <= 2) {
/*  620 */             O.D(this, "execute: cmd failed with exception: " + localt + "; retrying (attempts = " + (i1 + 1) + ")");
/*      */ 
/*  622 */             this.h.B(7);
/*      */           } else {
/*  624 */             O.E(this, "cmd " + this + " failed after " + i1 + " attempts" + "; exception = " + localt);
/*      */ 
/*  630 */             throw new t("execute: cmd " + this + " failed after " + i1 + " attempts");
/*      */           }
/*      */ 
/*      */         }
/*      */         catch (Z localZ)
/*      */         {
/*  636 */           if (localZ.getMessage().indexOf("Incorrect product code") == 0) {
/*  637 */             O.E(this, localZ.getMessage());
/*  638 */             throw localZ;
/*      */           }
/*      */ 
/*  641 */           i1++;
/*  642 */           this.h.Y().A();
/*  643 */           if (i1 <= 2) {
/*  644 */             O.D(this, "execute: cmd failed with exception: " + localZ + "; retrying (attempts = " + (i1 + 1) + ")");
/*      */ 
/*  646 */             this.h.B(7);
/*      */           } else {
/*  648 */             O.E(this, "cmd " + this + " failed after " + i1 + " attempts" + "; exception = " + localZ);
/*      */ 
/*  654 */             throw new Z("execute: cmd " + this + " failed after " + i1 + " attempts");
/*      */           }
/*      */         }
/*      */       }
/*      */ 
/*  659 */       while ((i2 == 0) && (i1 <= 2));
/*      */     }
/*      */ 
/*      */     int[] I()
/*      */     {
/*  668 */       return this.l;
/*      */     }
/*      */ 
/*      */     private String J()
/*      */     {
/*  677 */       return "<" + O._B.D(this.j) + ">";
/*      */     }
/*      */ 
/*      */     private void A(int[] paramArrayOfInt)
/*      */     {
/*  686 */       this.l = paramArrayOfInt;
/*      */     }
/*      */ 
/*      */     private String F()
/*      */       throws t, Z
/*      */     {
/*  698 */       StringBuffer localStringBuffer = new StringBuffer();
/*  699 */       int i1 = 0;
/*      */       try
/*      */       {
/*  702 */         int i2 = 1;
/*      */         do
/*      */         {
/*  705 */           String str = D(i2++);
/*  706 */           int i3 = str.length();
/*  707 */           i1 = (i3 > 0) && (str.charAt(i3 - 1) == '\006') ? 1 : 0;
/*      */ 
/*  709 */           N.D(this.h, str.length());
/*      */ 
/*  712 */           localStringBuffer.append(str.trim());
/*      */ 
/*  715 */           this.h.A(N.E(this.h), N.J(this.h));
/*      */ 
/*  717 */           if (i1 != 0)
/*      */             continue;
/*  719 */           this.h.Y().A('\006');
/*      */ 
/*  721 */           localStringBuffer.append('\r');
/*      */         }
/*  723 */         while (i1 == 0);
/*      */       } catch (t localt) {
/*  725 */         B("readDeviceData");
/*  726 */         throw localt;
/*      */       } catch (Z localZ) {
/*  728 */         B("readDeviceData");
/*  729 */         throw localZ;
/*      */       } catch (IOException localIOException) {
/*  731 */         throw new t("readDeviceData: ERROR - an IOException  has occurred processing cmd " + this);
/*      */       }
/*      */ 
/*  734 */       return localStringBuffer.toString();
/*      */     }
/*      */ 
/*      */     private String D(int paramInt)
/*      */       throws t, Z
/*      */     {
/*  748 */       this.h.Y().E();
/*      */ 
/*  750 */       O.A(this, "readDeviceDataIO: reading reply to cmd " + this);
/*      */ 
/*  752 */       String str = null;
/*      */       try
/*      */       {
/*  756 */         str = this.h.Y().C();
/*      */       } catch (IOException localIOException) {
/*  758 */         throw new t("readDeviceDataIO: ERROR - an IOException  has occurred processing cmd " + this);
/*      */       }
/*      */ 
/*  763 */       if (str.length() > 2) {
/*  764 */         A(str);
/*      */       }
/*      */ 
/*  767 */       O.A(this, "readDeviceDataIO: (" + paramInt + ") cmd " + this + " returned " + str.length() + " data bytes: " + "<" + str + ">");
/*      */ 
/*  770 */       return str;
/*      */     }
/*      */ 
/*      */     private int A(String paramString)
/*      */       throws Z
/*      */     {
/*  782 */       String str1 = paramString.trim();
/*  783 */       int i1 = O._B.A(110, str1, 2, str1.length() - 4);
/*      */ 
/*  786 */       String str2 = str1.substring(str1.length() - 2);
/*  787 */       String str3 = O._B.F(i1);
/*      */ 
/*  790 */       if (!str2.equalsIgnoreCase(str3)) {
/*  791 */         throw new Z("verifyCRC: cmd " + this + " resulted in bad CRC value of " + str2 + " (expected " + str3 + ") " + "(byte data = " + "<" + O._B.E(paramString) + ">)");
/*      */       }
/*      */ 
/*  796 */       return i1;
/*      */     }
/*      */ 
/*      */     private void B(String paramString)
/*      */     {
/*  805 */       N.F(this.h, Math.min(N.D(this.h) + 5, 100));
/*  806 */       O.A(this, paramString + ": increasing m_ioDelay to " + N.D(this.h));
/*  807 */       this.h.Y().A(N.D(this.h));
/*      */     }
/*      */ 
/*      */     private void H()
/*      */       throws t
/*      */     {
/*      */       try
/*      */       {
/*  817 */         E();
/*      */       } catch (t localt) {
/*  819 */         B("sendCommand");
/*  820 */         throw localt;
/*      */       }
/*      */     }
/*      */ 
/*      */     private void E()
/*      */       throws t
/*      */     {
/*  831 */       this.h.Y().E();
/*      */ 
/*  833 */       O.A(this, "sendCommandIO: sending cmd " + this);
/*      */       try
/*      */       {
/*  836 */         for (int i1 = 0; i1 < this.j.length; i1++) {
/*  837 */           this.h.Y().G(this.j[i1]);
/*      */ 
/*  839 */           O._B.G(3);
/*      */ 
/*  841 */           int i2 = this.h.Y().O();
/*      */ 
/*  845 */           if ((i1 != this.j.length - 1) || 
/*  846 */             (this.j[i1] != 13)) continue;
/*  847 */           if ((i2 != 6) && (i2 != 21)) {
/*  848 */             throw new t("sendCommandIO: ACK / NAK reply not found: " + O._B.H(i2) + " for cmd " + this);
/*      */           }
/*      */ 
/*  852 */           if (i2 == 21) {
/*  853 */             throw new t("sendCommandIO: NAK reply for cmd " + this);
/*      */           }
/*      */         }
/*      */ 
/*      */       }
/*      */       catch (IOException localIOException)
/*      */       {
/*  860 */         throw new t("sendCommandIO: ERROR - an IOException  has occurred processing cmd " + this);
/*      */       }
/*      */     }
/*      */ 
/*      */     private void G()
/*      */       throws t, Z
/*      */     {
/*  873 */       if (this.h.T() != 7) {
/*  874 */         this.h.B(4);
/*      */       }
/*  876 */       H();
/*      */ 
/*  879 */       if (!this.h.isHaltRequested())
/*      */       {
/*  881 */         this.h.B(5);
/*      */ 
/*  885 */         A(O._B.D(F()));
/*      */       }
/*      */ 
/*  889 */       this.h.A(N.E(this.h), N.J(this.h));
/*      */ 
/*  891 */       if (!this.h.isHaltRequested())
/*  892 */         this.m.A(this);
/*      */     }
/*      */   }
/*      */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.A.N
 * JD-Core Version:    0.6.0
 */