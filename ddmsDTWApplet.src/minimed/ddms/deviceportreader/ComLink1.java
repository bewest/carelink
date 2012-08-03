/*      */ package minimed.ddms.A;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import minimed.util.Contract;
/*      */ 
/*      */ final class BA extends J
/*      */ {
/*      */   private static final byte z = 2;
/*      */   private static final byte w = 4;
/*      */   private static final byte g = 5;
/*      */   private static final byte m = 6;
/*      */   private static final byte k = 7;
/*      */   private static final byte j = 8;
/*      */   private static final byte r = 10;
/*      */   private static final byte f = 51;
/*      */   private static final byte i = 85;
/*      */   private static final byte t = 102;
/*      */   private static final byte o = 6;
/*      */   private static final byte y = 21;
/*      */   private static final int u = 1;
/*      */   private static final int n = 150;
/*      */   private static final int s = 70;
/*      */   private static final int q = 5;
/*   81 */   private static final int[] h = { 21, 49, 50, 35, 52, 37, 38, 22, 26, 25, 42, 11, 44, 13, 14, 28 };
/*      */ 
/*  104 */   private int l = 0;
/*      */   private int p;
/*      */   private int v;
/*      */   private final int e;
/*      */   String x;
/*      */ 
/*      */   BA(v paramv, String paramString1, int paramInt, String paramString2)
/*      */   {
/*  124 */     super(paramv, paramString1, "ComLink1");
/*  125 */     this.x = paramString2;
/*  126 */     this.e = paramInt;
/*      */ 
/*  130 */     if (this.e == 15)
/*  131 */       this.l = 70;
/*      */     else
/*  133 */       this.l = 0;
/*      */   }
/*      */ 
/*      */   public void A(n paramn)
/*      */     throws Z, t, W
/*      */   {
/*  149 */     _A local_A = new _A(paramn, null);
/*  150 */     _A.A(local_A);
/*      */   }
/*      */ 
/*      */   void H()
/*      */     throws IOException, W, t
/*      */   {
/*  163 */     O.A(this, "initCommunicationsIO: waking up ComLink1.");
/*  164 */     B(this.x);
/*  165 */     c().A();
/*  166 */     A(6, 51);
/*      */     try
/*      */     {
/*  170 */       int i1 = j();
/*  171 */       if (i1 > 0) {
/*  172 */         O.D(this, "initCommunicationsIO: dumping " + i1 + " bytes.");
/*      */ 
/*  175 */         g();
/*      */ 
/*  178 */         int[] arrayOfInt = new int[i1];
/*  179 */         c().B(arrayOfInt);
/*  180 */         e();
/*      */       }
/*      */     }
/*      */     catch (t localt)
/*      */     {
/*      */     }
/*      */     catch (IOException localIOException)
/*      */     {
/*      */     }
/*      */   }
/*      */ 
/*      */   void F()
/*      */     throws IOException
/*      */   {
/*  195 */     if (c() != null)
/*      */     {
/*  197 */       c().B();
/*  198 */       A(null);
/*      */     }
/*      */   }
/*      */ 
/*      */   void B(String paramString)
/*      */     throws IOException
/*      */   {
/*  210 */     A(paramString);
/*      */ 
/*  212 */     int i1 = 0;
/*  213 */     int i2 = 0;
/*      */     do
/*      */     {
/*      */       try
/*      */       {
/*  218 */         A(new d(paramString, M.ƥ));
/*  219 */         c().B(500);
/*  220 */         i2 = 1;
/*      */       }
/*      */       catch (IOException localIOException) {
/*  223 */         i1++;
/*      */ 
/*  225 */         if (i1 >= 0) {
/*  226 */           throw localIOException;
/*      */         }
/*      */ 
/*  229 */         O.A(this, "initSerialPort: waiting for port to become available...e=" + localIOException);
/*      */ 
/*  231 */         O._B.G(5000);
/*      */       }
/*      */     }
/*  233 */     while (i2 == 0);
/*      */ 
/*  235 */     c().A();
/*  236 */     c().A(M.ſ);
/*      */   }
/*      */ 
/*      */   void A(String paramString)
/*      */     throws IOException
/*      */   {
/*  247 */     this.x = paramString;
/*  248 */     C(2);
/*      */ 
/*  250 */     if (c() != null)
/*  251 */       c().B();
/*      */   }
/*      */ 
/*      */   private d c()
/*      */   {
/*  261 */     return (d)C();
/*      */   }
/*      */ 
/*      */   private void g()
/*      */     throws IOException, W, t
/*      */   {
/*  273 */     c().E();
/*      */ 
/*  275 */     O.B(this, "sendTransferDataCommand: sending cmd.");
/*  276 */     A(8);
/*      */   }
/*      */ 
/*      */   private void B(boolean paramBoolean)
/*      */     throws IOException, W, t
/*      */   {
/*  289 */     int i1 = 0;
/*      */ 
/*  291 */     for (int i2 = 0; (i2 <= 1) && (i1 == 0); i2++)
/*      */       try {
/*  293 */         A(paramBoolean);
/*  294 */         i1 = 1;
/*      */       }
/*      */       catch (IOException localIOException)
/*      */       {
/*  298 */         if (this.e == 15) {
/*  299 */           this.l = Math.min(this.l + 5, 150);
/*      */ 
/*  302 */           O.A(this, "readReadyByte: increasing m_paradigmLinkDelay to " + this.l);
/*      */         }
/*      */ 
/*  306 */         if (i2 != 1)
/*      */           continue;
/*  308 */         throw localIOException;
/*      */       }
/*      */   }
/*      */ 
/*      */   private void e()
/*      */     throws IOException, W
/*      */   {
/*  321 */     int i1 = 0;
/*  322 */     int i2 = 0;
/*  323 */     int i3 = 0;
/*      */ 
/*  326 */     for (int i4 = 0; (i4 <= 1) && (i2 == 0); i4++) {
/*  327 */       i1 = c().O();
/*  328 */       i2 = i1 == 85 ? 1 : 0;
/*  329 */       if (i1 == 102) {
/*  330 */         i3 = 1;
/*      */       }
/*      */     }
/*      */ 
/*  334 */     if (i2 == 0) {
/*  335 */       c().K();
/*  336 */       if (i3 != 0) {
/*  337 */         throw new IOException("readAckByte: reply " + O._B.B(102) + " (NAK) does not match expected ACK reply of " + O._B.B(85));
/*      */       }
/*      */ 
/*  341 */       throw new IOException("readAckByte: reply " + O._B.H(i1) + " does not match expected ACK reply of " + O._B.B(85));
/*      */     }
/*      */   }
/*      */ 
/*      */   private int j()
/*      */     throws t, IOException, W
/*      */   {
/*  359 */     this.p = B(2);
/*      */ 
/*  361 */     this.v = c().O();
/*  362 */     e();
/*  363 */     O.B(this, "readStatus: CS status follows: NumberReceivedDataBytes=" + this.v + ", ReceivedData=" + m() + ", RS232Mode=" + d() + ", FilterRepeat=" + f() + ", AutoSleep=" + l() + ", StatusError=" + i() + ", SelfTestError=" + k());
/*      */ 
/*  371 */     if (i()) {
/*  372 */       throw new t("readStatus: ComLink1 has STATUS ERROR");
/*      */     }
/*      */ 
/*  375 */     if (k()) {
/*  376 */       throw new t("readStatus: ComLink1 has SELFTEST ERROR");
/*      */     }
/*      */ 
/*  379 */     return m() ? this.v : 0;
/*      */   }
/*      */ 
/*      */   private void A(boolean paramBoolean)
/*      */     throws IOException, W, t
/*      */   {
/*  392 */     int i1 = 0;
/*  393 */     int i2 = 0;
/*      */ 
/*  395 */     if (paramBoolean)
/*      */     {
/*  399 */       O._B.G(this.l);
/*  400 */       A(7);
/*      */ 
/*  402 */       O._B.G(this.l);
/*  403 */       e();
/*      */     } else {
/*  405 */       O._B.G(this.l);
/*      */     }
/*      */ 
/*  409 */     i1 = c().O();
/*  410 */     i2 = i1 == 51 ? 1 : 0;
/*      */ 
/*  412 */     if (i2 == 0)
/*  413 */       throw new IOException("readReadyByteIO: reply " + O._B.H(i1) + " does not match expected READY reply of " + O._B.B(51));
/*      */   }
/*      */ 
/*      */   private int B(byte paramByte)
/*      */     throws IOException, W, t
/*      */   {
/*  430 */     c().E();
/*      */ 
/*  432 */     A(paramByte);
/*  433 */     return c().O();
/*      */   }
/*      */ 
/*      */   private void A(byte paramByte1, byte paramByte2)
/*      */     throws IOException, W, t
/*      */   {
/*  449 */     c().E();
/*      */ 
/*  451 */     byte b = 0;
/*  452 */     int i1 = 0;
/*      */ 
/*  455 */     for (int i2 = 0; (i2 <= 1) && (i1 == 0); i2++) {
/*  456 */       b = B(paramByte1);
/*  457 */       i1 = b == paramByte2 ? 1 : 0;
/*      */     }
/*      */ 
/*  460 */     if (i1 == 0) {
/*  461 */       c().K();
/*  462 */       throw new IOException("SendCommand: command " + O._B.B(paramByte1) + " reply " + O._B.H(b) + " does not match expected command " + O._B.B(paramByte2));
/*      */     }
/*      */   }
/*      */ 
/*      */   private void A(byte paramByte)
/*      */     throws IOException, W, t
/*      */   {
/*  479 */     c().E();
/*      */ 
/*  481 */     c().A(paramByte);
/*      */   }
/*      */ 
/*      */   private boolean f()
/*      */   {
/*  491 */     return (this.p & 0x40) > 0;
/*      */   }
/*      */ 
/*      */   private boolean l()
/*      */   {
/*  500 */     return (this.p & 0x20) > 0;
/*      */   }
/*      */ 
/*      */   private boolean i()
/*      */   {
/*  509 */     return (this.p & 0x10) > 0;
/*      */   }
/*      */ 
/*      */   private boolean k()
/*      */   {
/*  518 */     return (this.p & 0x8) > 0;
/*      */   }
/*      */ 
/*      */   private boolean d()
/*      */   {
/*  527 */     return (this.p & 0x4) > 0;
/*      */   }
/*      */ 
/*      */   private boolean m()
/*      */   {
/*  536 */     return (this.p & 0x1) > 0;
/*      */   }
/*      */ 
/*      */   private class _A
/*      */   {
/*      */     private static final int K = 167;
/*      */     private static final int L = 7;
/*      */     private static final int D = 6;
/*      */     private static final int I = 4;
/*      */     private static final int J = 6;
/*      */     private static final int E = 127;
/*      */     private static final int H = 128;
/*      */     private static final int A = 0;
/*      */     private static final int G = 64;
/*      */     private static final int N = 0;
/*      */     private static final int M = 17000;
/*      */     .A._C C;
/*      */     private Integer B;
/*      */ 
/*      */     private _A(n arg2)
/*      */     {
/*      */       Object localObject;
/*  591 */       this.C = ((.A._C)localObject);
/*      */     }
/*      */ 
/*      */     public String toString()
/*      */     {
/*  600 */       return this.C.toString();
/*      */     }
/*      */ 
/*      */     private void B()
/*      */       throws t, Z, W
/*      */     {
/*  614 */       if (this.C.I > 0)
/*      */       {
/*  618 */         n localn = J();
/*      */ 
/*  622 */         localn.A();
/*      */       }
/*      */ 
/*  629 */       O.Ã = this.C.A;
/*      */ 
/*  631 */       E();
/*      */ 
/*  634 */       F();
/*      */     }
/*      */ 
/*      */     private void F()
/*      */       throws t
/*      */     {
/*  647 */       if (BA.this.K() != 7) {
/*  648 */         BA.this.D(4);
/*      */       }
/*  650 */       G();
/*      */ 
/*  653 */       if ((this.C.a.length > 0) && (!BA.this.E().isHaltRequested()))
/*      */       {
/*  656 */         if (this.C.a.length > 64)
/*      */         {
/*  658 */           this.C.a = A(this.C.a.length);
/*      */         }
/*      */         else {
/*  661 */           BA.this.D(5);
/*      */ 
/*  663 */           this.C.a = C();
/*  664 */           BA.this.B(this.C.a.length);
/*      */ 
/*  667 */           BA.this.I();
/*      */         }
/*      */       }
/*      */       else {
/*      */         try {
/*  672 */           I();
/*      */         } catch (IOException localIOException) {
/*  674 */           throw new t("sendAndRead: ERROR - problem checking ACK; exception = " + localIOException);
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*  680 */       if (BA.this.K() == 7)
/*  681 */         BA.this.D(4);
/*      */     }
/*      */ 
/*      */     private void G()
/*      */       throws t, W
/*      */     {
/*  694 */       BA.C(BA.this).E();
/*      */ 
/*  696 */       O.A(this, "sendCommand: SENDING CMD " + this.C + "for pump #" + BA.this.J());
/*      */       try
/*      */       {
/*  701 */         int[] arrayOfInt1 = H();
/*  702 */         int[] arrayOfInt2 = new int[2];
/*      */ 
/*  707 */         if (this.C.B()) {
/*  708 */           arrayOfInt2[0] = 10;
/*      */         }
/*  710 */         else if (this.C.I == 0)
/*      */         {
/*  712 */           arrayOfInt2[0] = 5;
/*      */         }
/*      */         else {
/*  715 */           arrayOfInt2[0] = 4;
/*      */         }
/*      */ 
/*  720 */         arrayOfInt2[1] = arrayOfInt1.length;
/*  721 */         arrayOfInt1 = O._B.A(arrayOfInt2, arrayOfInt1);
/*      */ 
/*  724 */         BA.C(BA.this).A(arrayOfInt1);
/*      */ 
/*  726 */         O.A(this, "sendCommand: reading link device ACK & (optional) RDY byte.");
/*      */ 
/*  728 */         BA.A(BA.this);
/*      */ 
/*  732 */         if ((this.C.X == 93) && (this.C.I == 0) && (this.C.O[0] == 1))
/*      */         {
/*  736 */           int i = BA.C(BA.this).D();
/*  737 */           BA.C(BA.this).B(17000);
/*      */ 
/*  740 */           BA.A(BA.this, arrayOfInt2[0] == 4);
/*      */ 
/*  743 */           BA.C(BA.this).B(i);
/*      */         } else {
/*  745 */           BA.A(BA.this, arrayOfInt2[0] == 4);
/*      */         }
/*      */       } catch (IOException localIOException) {
/*  748 */         throw new t("sendCommand: ERROR - an IOException  has occurred processing cmd " + O._B.H(this.C.X) + " (" + this.C.A + "); exception = " + localIOException);
/*      */       }
/*      */     }
/*      */ 
/*      */     private void A(int[] paramArrayOfInt)
/*      */       throws t
/*      */     {
/*  767 */       int i = paramArrayOfInt.length - 1;
/*  768 */       int j = paramArrayOfInt[i];
/*  769 */       int k = O._B.F(paramArrayOfInt, 0, i) & 0xFF;
/*      */ 
/*  771 */       if (j != k) {
/*  772 */         throw new t("checkHeaderAndCRC: cmd " + O._B.H(this.C.X) + " (" + this.C.A + ")" + " resulted in bad CRC value of " + j + " (expected " + k + ") " + "(byte data = " + "<" + O._B.D(paramArrayOfInt) + ">)");
/*      */       }
/*      */ 
/*  780 */       if (paramArrayOfInt[0] != 167) {
/*  781 */         throw new t("checkHeaderAndCRC: cmd " + O._B.H(this.C.X) + " (" + this.C.A + ")" + " resulted in bad Type Code value of " + O._B.H(paramArrayOfInt[0]));
/*      */       }
/*      */ 
/*  790 */       int[] arrayOfInt = A();
/*      */ 
/*  792 */       if ((arrayOfInt[0] != paramArrayOfInt[1]) || (arrayOfInt[1] != paramArrayOfInt[2]) || (arrayOfInt[2] != paramArrayOfInt[3]))
/*      */       {
/*  795 */         throw new t("checkHeaderAndCRC: cmd " + O._B.H(this.C.X) + " (" + this.C.A + ")" + " resulted in bad serial number value of <" + O._B.H(paramArrayOfInt[1]) + " " + O._B.H(paramArrayOfInt[2]) + " " + O._B.H(paramArrayOfInt[3]) + ">");
/*      */       }
/*      */     }
/*      */ 
/*      */     private void E()
/*      */     {
/*  809 */       this.C.a = new int[this.C.E * this.C.Q];
/*      */     }
/*      */ 
/*      */     private void I()
/*      */       throws t, IOException, W
/*      */     {
/*  823 */       BA.C(BA.this).E();
/*      */ 
/*  825 */       O.A(this, "checkAck: retrieving pump ACK packet...");
/*      */ 
/*  827 */       int i = BA.D(BA.this);
/*      */ 
/*  830 */       BA.B(BA.this);
/*      */ 
/*  833 */       int[] arrayOfInt1 = new int[i];
/*  834 */       BA.C(BA.this).B(arrayOfInt1);
/*  835 */       int[] arrayOfInt2 = C(arrayOfInt1);
/*      */ 
/*  838 */       BA.A(BA.this);
/*      */ 
/*  841 */       A(arrayOfInt2);
/*      */ 
/*  844 */       if (arrayOfInt2[4] != 6)
/*      */       {
/*  846 */         int j = arrayOfInt2[5];
/*  847 */         throw new t("checkAck: cmd " + O._B.H(this.C.X) + " (" + this.C.A + ")" + " failed; error code = <" + O._B.H(j) + ">" + "(" + .A.J(j) + ") " + "(byte data = " + "<" + O._B.D(arrayOfInt2) + ">)", new Integer(j), .A.J(j));
/*      */       }
/*      */ 
/*  856 */       O.A(this, "checkAck: GOOD pump ACK reply received.");
/*      */     }
/*      */ 
/*      */     private void D()
/*      */       throws IOException, W, t
/*      */     {
/*  870 */       int i = 0;
/*      */ 
/*  872 */       BA.C(BA.this).E();
/*      */ 
/*  874 */       O.A(this, "sendAck: sending cmd " + O._B.B(6));
/*      */ 
/*  877 */       int[] arrayOfInt1 = new int[7];
/*      */ 
/*  880 */       arrayOfInt1[(i++)] = 167;
/*      */ 
/*  883 */       int[] arrayOfInt2 = A();
/*  884 */       arrayOfInt1[(i++)] = arrayOfInt2[0];
/*  885 */       arrayOfInt1[(i++)] = arrayOfInt2[1];
/*  886 */       arrayOfInt1[(i++)] = arrayOfInt2[2];
/*      */ 
/*  889 */       arrayOfInt1[(i++)] = 6;
/*      */ 
/*  892 */       arrayOfInt1[(i++)] = 0;
/*      */ 
/*  895 */       arrayOfInt1[(i++)] = O._B.F(arrayOfInt1, 0, i - 1);
/*      */ 
/*  898 */       arrayOfInt1 = B(arrayOfInt1);
/*      */ 
/*  901 */       int[] arrayOfInt3 = new int[2];
/*  902 */       arrayOfInt3[0] = 5;
/*  903 */       arrayOfInt3[1] = arrayOfInt1.length;
/*  904 */       arrayOfInt1 = O._B.A(arrayOfInt3, arrayOfInt1);
/*      */ 
/*  907 */       BA.C(BA.this).A(arrayOfInt1);
/*  908 */       BA.A(BA.this);
/*  909 */       BA.A(BA.this, arrayOfInt3[0] == 4);
/*      */     }
/*      */ 
/*      */     private int[] A(int paramInt)
/*      */       throws t, W
/*      */     {
/*  925 */       int[] arrayOfInt1 = new int[70];
/*  926 */       int[] arrayOfInt2 = new int[0];
/*  927 */       int i = 0;
/*  928 */       int j = 1;
/*  929 */       int k = 0;
/*      */ 
/*  931 */       O.A(this, "readDeviceDataPage: processing cmd " + O._B.H(this.C.X) + " (" + this.C.A + ")");
/*      */       do
/*      */       {
/*  937 */         BA.this.D(6);
/*  938 */         arrayOfInt1 = C();
/*  939 */         if (arrayOfInt1.length == 0)
/*      */         {
/*  941 */           k = 1;
/*      */         } else {
/*  943 */           arrayOfInt2 = O._B.A(arrayOfInt2, arrayOfInt1);
/*  944 */           O.A(this, "readDeviceDataPage: just read packet " + i + ", bytes = " + arrayOfInt1.length + ", total bytes = " + arrayOfInt2.length);
/*      */ 
/*  947 */           BA.this.B(arrayOfInt1.length);
/*  948 */           i++;
/*      */ 
/*  951 */           BA.this.I();
/*      */ 
/*  956 */           int m = (this.C.M & 0x80) == 128 ? 1 : 0;
/*  957 */           int n = this.C.M & 0x7F;
/*  958 */           if (n != j) {
/*  959 */             throw new t("readDeviceDataPage: ERROR - sequence number mismatch); expected " + j + ", read " + n);
/*      */           }
/*      */ 
/*  965 */           j++; if (j > 127) {
/*  966 */             j = 1;
/*      */           }
/*      */ 
/*  969 */           k = (arrayOfInt2.length >= paramInt) || (BA.this.E().isHaltRequested()) || (m != 0) ? 1 : 0;
/*      */           try
/*      */           {
/*  973 */             if ((k == 0) && (!BA.this.E().isHaltRequested())) {
/*  974 */               BA.this.D(4);
/*  975 */               D();
/*      */             }
/*      */           } catch (IOException localIOException) {
/*  978 */             throw new t("readDeviceDataPage: ERROR - problem sending ACK; exception = " + localIOException);
/*      */           }
/*      */         }
/*      */       }
/*  982 */       while (k == 0);
/*      */ 
/*  985 */       O.C(this, "readDeviceDataPage: cmd " + O._B.H(this.C.X) + " (" + this.C.A + ") returned " + arrayOfInt2.length + " bytes.");
/*      */ 
/*  988 */       return arrayOfInt2;
/*      */     }
/*      */ 
/*      */     private int[] C() throws t, W
/*      */     {
/* 1000 */       BA.C(BA.this).E();
/*      */ 
/* 1003 */       int[] arrayOfInt2 = new int[0];
/*      */       int[] arrayOfInt1;
/*      */       try {
/* 1007 */         int i = BA.D(BA.this);
/*      */ 
/* 1010 */         BA.B(BA.this);
/*      */ 
/* 1013 */         arrayOfInt1 = new int[i];
/* 1014 */         BA.C(BA.this).B(arrayOfInt1);
/*      */ 
/* 1016 */         BA.A(BA.this);
/*      */       } catch (IOException localIOException) {
/* 1018 */         throw new t("readDeviceData: ERROR - an IOException  has occurred processing cmd " + O._B.H(this.C.X) + " (" + this.C.A + "); exception = " + localIOException);
/*      */       }
/*      */ 
/* 1023 */       int[] arrayOfInt3 = C(arrayOfInt1);
/*      */ 
/* 1026 */       A(arrayOfInt3);
/* 1027 */       int j = 0;
/*      */ 
/* 1030 */       if (arrayOfInt3[4] == 21) {
/* 1031 */         int k = arrayOfInt3[5];
/* 1032 */         if (k == 13)
/* 1033 */           O.A(this, "readDeviceData: NAK received = no more data.");
/*      */         else {
/* 1035 */           throw new t("readDeviceData: cmd " + O._B.H(this.C.X) + " (" + this.C.A + ")" + " failed; error code = <" + O._B.H(k) + ">" + "(" + .A.J(k) + ") " + "(byte data = " + "<" + O._B.D(arrayOfInt3) + ">)", new Integer(k), .A.J(k));
/*      */         }
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/* 1045 */         if (arrayOfInt3[4] != this.C.X) {
/* 1046 */           throw new t("readDeviceData: cmd " + O._B.H(this.C.X) + " (" + this.C.A + ")" + " has bad command code value of " + O._B.H(arrayOfInt3[4]) + " (expected " + O._B.H(this.C.X) + ") " + "(byte data = " + "<" + O._B.D(arrayOfInt3) + ">)");
/*      */         }
/*      */ 
/* 1056 */         this.C.M = arrayOfInt3[5];
/*      */ 
/* 1059 */         j = arrayOfInt3.length - 6 - 1;
/* 1060 */         arrayOfInt2 = new int[j];
/* 1061 */         System.arraycopy(arrayOfInt3, 6, arrayOfInt2, 0, j);
/* 1062 */         O.B(this, "readDeviceData: decoded packet = <" + O._B.D(arrayOfInt2) + ">");
/*      */       }
/*      */ 
/* 1067 */       return arrayOfInt2;
/*      */     }
/*      */ 
/*      */     private int[] H()
/*      */     {
/* 1079 */       int i = 0;
/*      */ 
/* 1081 */       Contract.pre(this.C.C > 0);
/*      */ 
/* 1083 */       int[] arrayOfInt1 = new int[7];
/*      */ 
/* 1086 */       arrayOfInt1[(i++)] = 167;
/*      */ 
/* 1089 */       int[] arrayOfInt2 = A();
/* 1090 */       arrayOfInt1[(i++)] = arrayOfInt2[0];
/* 1091 */       arrayOfInt1[(i++)] = arrayOfInt2[1];
/* 1092 */       arrayOfInt1[(i++)] = arrayOfInt2[2];
/*      */ 
/* 1095 */       arrayOfInt1[(i++)] = this.C.X;
/*      */ 
/* 1099 */       if (this.C.I == 0) {
/* 1100 */         arrayOfInt1[(i++)] = 0;
/*      */       }
/*      */       else {
/* 1103 */         int[] arrayOfInt3 = new int[i + 1];
/* 1104 */         System.arraycopy(arrayOfInt1, 0, arrayOfInt3, 0, i);
/*      */ 
/* 1109 */         if (this.B != null)
/*      */         {
/* 1111 */           arrayOfInt3[i] = this.B.intValue();
/*      */         }
/*      */         else {
/* 1114 */           arrayOfInt3[i] = this.C.I;
/*      */         }
/*      */ 
/* 1118 */         arrayOfInt1 = O._B.A(arrayOfInt3, this.C.O);
/*      */ 
/* 1121 */         arrayOfInt1 = O._B.A(arrayOfInt1, new int[1]);
/* 1122 */         i = arrayOfInt1.length - 1;
/*      */       }
/*      */ 
/* 1126 */       arrayOfInt1[(i++)] = O._B.F(arrayOfInt1, 0, i - 1);
/* 1127 */       this.C.H = arrayOfInt1;
/*      */ 
/* 1129 */       return B(arrayOfInt1);
/*      */     }
/*      */ 
/*      */     private n J()
/*      */     {
/* 1138 */       .A._C local_C = (.A._C)this.C.clone();
/* 1139 */       local_C.A = (this.C.A + "-command packet");
/* 1140 */       local_C.E = 0;
/* 1141 */       local_C.Q = 0;
/* 1142 */       local_C._ = 0;
/* 1143 */       local_C.I = 0;
/*      */ 
/* 1146 */       if ((this.C.X == 93) && (this.C.O[0] == 1))
/*      */       {
/* 1148 */         local_C.A(true);
/*      */       }
/* 1150 */       return local_C;
/*      */     }
/*      */ 
/*      */     private int[] A()
/*      */     {
/* 1162 */       Contract.pre(BA.this.J() != null);
/* 1163 */       Contract.pre(BA.this.J().length() == 6);
/*      */ 
/* 1165 */       return O._B.B(BA.this.J());
/*      */     }
/*      */ 
/*      */     private int[] B(int[] paramArrayOfInt)
/*      */     {
/* 1181 */       int[] arrayOfInt1 = new int[paramArrayOfInt.length * 3];
/* 1182 */       int i = 0;
/*      */ 
/* 1185 */       O.A(this, "encodeDC: about to encode bytes = <" + O._B.G(paramArrayOfInt) + ">");
/*      */       int n;
/* 1189 */       for (int j = 0; j < paramArrayOfInt.length; j++) {
/* 1190 */         k = paramArrayOfInt[j];
/*      */ 
/* 1192 */         Contract.pre((k >= 0) && (k <= 255));
/*      */ 
/* 1196 */         int m = k >> 4 & 0xF;
/*      */ 
/* 1198 */         n = k & 0xF;
/*      */ 
/* 1201 */         i1 = BA.h()[m];
/* 1202 */         int i2 = BA.h()[n];
/*      */ 
/* 1205 */         arrayOfInt1[(i++)] = (i1 >> 2);
/*      */ 
/* 1209 */         int i3 = i1 & 0x3;
/*      */ 
/* 1212 */         int i4 = i2 >> 4 & 0x3;
/*      */ 
/* 1214 */         arrayOfInt1[(i++)] = (i3 << 2 | i4);
/*      */ 
/* 1217 */         arrayOfInt1[(i++)] = (i2 & 0xF);
/*      */       }
/*      */ 
/* 1221 */       j = 0;
/* 1222 */       int k = (int)Math.ceil(paramArrayOfInt.length * 6.0D / 4.0D);
/*      */ 
/* 1226 */       int[] arrayOfInt2 = new int[k];
/*      */ 
/* 1229 */       for (int i1 = 0; i1 < arrayOfInt1.length; i1 += 2)
/*      */       {
/* 1231 */         if (i1 < arrayOfInt1.length - 1) {
/* 1232 */           n = O._B.C(arrayOfInt1[i1], arrayOfInt1[(i1 + 1)]);
/*      */         }
/*      */         else {
/* 1235 */           n = O._B.C(arrayOfInt1[i1], 5);
/*      */         }
/*      */ 
/* 1238 */         Contract.post((n >= 0) && (n <= 255));
/* 1239 */         arrayOfInt2[(j++)] = n;
/*      */       }
/*      */ 
/* 1242 */       return arrayOfInt2;
/*      */     }
/*      */ 
/*      */     private int[] C(int[] paramArrayOfInt)
/*      */       throws t
/*      */     {
/* 1259 */       int i = 0;
/* 1260 */       int j = 0;
/* 1261 */       int k = 0;
/* 1262 */       int m = 0;
/*      */ 
/* 1265 */       int i1 = 0;
/* 1266 */       int i2 = (int)Math.floor(paramArrayOfInt.length * 4.0D / 6.0D);
/*      */ 
/* 1270 */       int[] arrayOfInt = new int[i2];
/*      */       int i4;
/* 1273 */       for (int i3 = 0; i3 < paramArrayOfInt.length; i3++)
/*      */       {
/* 1275 */         for (i4 = 7; i4 >= 0; )
/*      */         {
/* 1277 */           int i5 = paramArrayOfInt[i3] >> i4 & 0x1;
/*      */ 
/* 1279 */           k = k << 1 | i5;
/* 1280 */           i++;
/*      */ 
/* 1282 */           if (i == 6)
/*      */           {
/* 1284 */             j++;
/* 1285 */             if (j == 1) {
/* 1286 */               m = B(k);
/*      */             }
/*      */             else {
/* 1289 */               int n = B(k);
/* 1290 */               int i6 = O._B.C(m, n);
/* 1291 */               arrayOfInt[(i1++)] = i6;
/* 1292 */               j = 0;
/*      */             }
/*      */ 
/* 1296 */             k = 0;
/* 1297 */             i = 0;
/*      */           }
/* 1276 */           i4--;
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/* 1302 */       O.A(this, "decodeDC: decoded bytes = <" + O._B.G(arrayOfInt) + ">");
/*      */ 
/* 1305 */       return arrayOfInt;
/*      */     }
/*      */ 
/*      */     private int B(int paramInt)
/*      */       throws t
/*      */     {
/* 1320 */       if ((paramInt < 0) || (paramInt > 63)) {
/* 1321 */         throw new t("decodeDC: value of " + paramInt + " is out of expected range 0.." + 63);
/*      */       }
/*      */ 
/* 1326 */       for (int i = 0; i < BA.h().length; i++) {
/* 1327 */         if (BA.h()[i] == paramInt) {
/* 1328 */           return i;
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/* 1333 */       throw new t("decodeDC: Can't find value of " + O._B.H(paramInt) + " in table.");
/*      */     }
/*      */   }
/*      */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.A.BA
 * JD-Core Version:    0.6.0
 */