/*      */ package minimed.ddms.A;
/*      */ 
/*      */ import I;
/*      */ import java.io.IOException;
/*      */ import minimed.util.Contract;
/*      */ 
/*      */ public final class AA extends J
/*      */ {
/*      */   private static final int a = 100;
/*      */   private static final byte _ = 1;
/*      */   private static final byte d = 3;
/*      */   private static final byte P = 4;
/*      */   private static final byte K = 5;
/*      */   private static final byte c = 6;
/*      */   private static final byte Z = 12;
/*      */   private static final int S = 16;
/*      */   private static final int X = 19;
/*      */   private static final byte M = 85;
/*      */   private static final byte V = 102;
/*      */   private static final int O = 1;
/*      */   private static final int U = 2;
/*      */   private static final int W = 0;
/*      */   private static final int Y = 1;
/*      */   private static final int L = 64;
/*   68 */   private static final String[] R = { "NO ERROR", "CRC MISMATCH", "COMMAND DATA ERROR", "COMM BUSY AND/OR COMMAND CANNOT BE EXECUTED", "COMMAND NOT SUPPORTED" };
/*      */   private static l N;
/*      */   private int T;
/*   81 */   private boolean b = false;
/*      */   private long Q;
/*      */ 
/*      */   public AA(v paramv, String paramString)
/*      */   {
/*   93 */     super(paramv, paramString, "ComLink2");
/*   94 */     E(0);
/*      */   }
/*      */ 
/*      */   public void A(n paramn)
/*      */     throws t, W
/*      */   {
/*  109 */     Contract.pre(paramn instanceof .A._C);
/*  110 */     if (!this.b) {
/*  111 */       throw new t("ComLink2 communications not initialized: command=" + paramn);
/*      */     }
/*      */ 
/*  115 */     _A local_A = new _A((.A._C)paramn, null);
/*  116 */     _A.A(local_A);
/*      */   }
/*      */ 
/*      */   public static synchronized boolean R()
/*      */   {
/*  126 */     if (N == null) {
/*  127 */       N = new l("CareLinkUSB", false);
/*      */     }
/*  129 */     return N.A();
/*      */   }
/*      */ 
/*      */   public int V()
/*      */     throws t, IOException, W
/*      */   {
/*  142 */     int[] arrayOfInt = a();
/*      */ 
/*  144 */     int i = (byte)arrayOfInt[0];
/*  145 */     return i;
/*      */   }
/*      */ 
/*      */   public static void O()
/*      */   {
/*  152 */     N = null;
/*      */   }
/*      */ 
/*      */   void H()
/*      */     throws t, IOException, W
/*      */   {
/*  164 */     O.A(this, "initCommunicationsIO: BEGIN...");
/*  165 */     this.b = false;
/*  166 */     X();
/*  167 */     A(new DA());
/*      */     try
/*      */     {
/*  170 */       Z()._();
/*      */     } catch (IOException localIOException) {
/*  172 */       if (!R()) {
/*  173 */         throw new IOException("Link Device not present--reconnect & try again... (" + localIOException + ")");
/*      */       }
/*      */ 
/*  176 */       throw localIOException;
/*      */     }
/*      */ 
/*  180 */     M();
/*  181 */     a();
/*      */ 
/*  183 */     this.b = true;
/*  184 */     O.A(this, "initCommunicationsIO: END...");
/*      */   }
/*      */ 
/*      */   void F()
/*      */     throws IOException
/*      */   {
/*  193 */     if (this.b)
/*      */       try
/*      */       {
/*  196 */         a();
/*  197 */         T();
/*      */       } catch (IOException localIOException) {
/*      */       } catch (t localt) {
/*      */       } finally {
/*  201 */         X();
/*  202 */         this.b = false;
/*      */       }
/*      */   }
/*      */ 
/*      */   DA Z()
/*      */   {
/*  213 */     return (DA)C();
/*      */   }
/*      */ 
/*      */   private void X()
/*      */   {
/*  220 */     if (Z() != null) {
/*  221 */       Z().V();
/*  222 */       A(null);
/*      */     }
/*      */   }
/*      */ 
/*      */   private int[] M()
/*      */     throws t, IOException, W
/*      */   {
/*  658 */     O.B(this, "readProductInfo: obtaining product info...");
/*  659 */     int[] arrayOfInt = H(4);
/*  660 */     String str1 = arrayOfInt[5] == 1 ? "868.35Mhz" : (arrayOfInt[5] == 0) || (arrayOfInt[5] == 255) ? "916.5Mhz" : "UNKNOWN";
/*      */ 
/*  662 */     String str2 = O._B.B(arrayOfInt, 6, 10);
/*      */ 
/*  664 */     int i = arrayOfInt[18];
/*  665 */     StringBuffer localStringBuffer = new StringBuffer();
/*      */ 
/*  667 */     int j = 19;
/*  668 */     for (; j < 19 + i * 2; j += 2)
/*      */     {
/*  670 */       localStringBuffer.append("Interface" + arrayOfInt[j] + "=");
/*  671 */       int k = arrayOfInt[(j + 1)];
/*  672 */       localStringBuffer.append(k == 3 ? "USB" : k == 1 ? "Paradigm RF" : "UNKNOWN");
/*  673 */       localStringBuffer.append("; ");
/*      */     }
/*      */ 
/*  676 */     O.A(this, "readProductInfo: Serial #=" + arrayOfInt[0] + arrayOfInt[1] + arrayOfInt[2] + ", Product Version=" + arrayOfInt[3] + "." + arrayOfInt[4] + ", RF Freq=" + str1 + ", Product Description=" + str2 + ", Software Version=" + arrayOfInt[16] + "." + arrayOfInt[17] + ", #Interfaces=" + i + ": " + localStringBuffer);
/*      */ 
/*  685 */     return arrayOfInt;
/*      */   }
/*      */ 
/*      */   private int[] T()
/*      */     throws t, IOException, W
/*      */   {
/*  698 */     O.B(this, "readInterfaceStatistics: obtaining interface stats...");
/*  699 */     int[] arrayOfInt1 = A(5, 0);
/*      */ 
/*  701 */     O.B(this, "readInterfaceStatistics:  RF stats = " + A(arrayOfInt1));
/*      */ 
/*  704 */     int[] arrayOfInt2 = A(5, 1);
/*      */ 
/*  706 */     O.B(this, "readInterfaceStatistics: USB stats = " + A(arrayOfInt2));
/*      */ 
/*  709 */     return O._B.A(arrayOfInt1, arrayOfInt2);
/*      */   }
/*      */ 
/*      */   private String A(int[] paramArrayOfInt)
/*      */   {
/*  719 */     int i = paramArrayOfInt[0];
/*  720 */     int j = paramArrayOfInt[1];
/*  721 */     int k = paramArrayOfInt[2];
/*  722 */     int m = paramArrayOfInt[3];
/*  723 */     long l1 = O._B.A(paramArrayOfInt[4], paramArrayOfInt[5], paramArrayOfInt[6], paramArrayOfInt[7]);
/*      */ 
/*  725 */     long l2 = O._B.A(paramArrayOfInt[8], paramArrayOfInt[9], paramArrayOfInt[10], paramArrayOfInt[11]);
/*      */ 
/*  728 */     return "CRC Errors=" + i + ", Sequence Errors=" + j + ", NAKS=" + k + ", Timeouts=" + m + ", Packets Received=" + l1 + ", Packets Sent=" + l2;
/*      */   }
/*      */ 
/*      */   private int[] H(int paramInt)
/*      */     throws IOException, t
/*      */   {
/*  746 */     int[] arrayOfInt = new int[2];
/*  747 */     arrayOfInt[0] = paramInt;
/*  748 */     arrayOfInt[1] = 0;
/*  749 */     return B(arrayOfInt);
/*      */   }
/*      */ 
/*      */   private int[] A(int paramInt1, int paramInt2)
/*      */     throws IOException, t
/*      */   {
/*  764 */     int[] arrayOfInt = new int[3];
/*  765 */     arrayOfInt[0] = paramInt1;
/*  766 */     arrayOfInt[1] = paramInt2;
/*  767 */     arrayOfInt[2] = 0;
/*  768 */     return B(arrayOfInt);
/*      */   }
/*      */ 
/*      */   private int[] B(int[] paramArrayOfInt)
/*      */     throws IOException, t
/*      */   {
/*      */     try
/*      */     {
/*  781 */       Z().C(paramArrayOfInt);
/*  782 */       return Y();
/*      */     } catch (IOException localIOException) {
/*  784 */       O.D(this, "Caught exception " + localIOException + "; clearing buffers...");
/*      */       try
/*      */       {
/*  787 */         Z().T();
/*      */       } catch (Exception localException) {
/*      */       }
/*      */     }
/*  791 */     throw localIOException;
/*      */   }
/*      */ 
/*      */   private int[] a()
/*      */     throws t, IOException, W
/*      */   {
/*  805 */     O.B(this, "readSignalStrength: obtaining signal strength...");
/*  806 */     int[] arrayOfInt = A(6, 0);
/*      */ 
/*  809 */     int i = (byte)arrayOfInt[0];
/*  810 */     O.A(this, "readSignalStrength: Signal Strength= " + i + " dBm");
/*  811 */     return arrayOfInt;
/*      */   }
/*      */ 
/*      */   private int _()
/*      */     throws t, IOException, W
/*      */   {
/*  824 */     O.A(this, "readStatus: obtaining status...");
/*  825 */     int[] arrayOfInt = H(3);
/*      */ 
/*  828 */     int i = arrayOfInt[0];
/*      */ 
/*  834 */     this.T = arrayOfInt[2];
/*      */ 
/*  837 */     int j = O._B.A(arrayOfInt[3], arrayOfInt[4]);
/*      */ 
/*  841 */     O.B(this, "readStatus: ComLink2 status follows: commModuleStatus=" + O._B.H(i) + ", genericStatus=" + O._B.H(this.T) + ", rfBytesAvailable=" + j + ", receivedData=" + b() + ", RX in progress=" + W() + ", TX in progress=" + P() + ", Interface Error=" + L() + ", RX overflow=" + N() + ", TX overflow=" + S());
/*      */ 
/*  853 */     if (i != 0) {
/*  854 */       throw new t("readStatus: comm module error is set: " + O._B.H(i));
/*      */     }
/*      */ 
/*  858 */     if (L()) {
/*  859 */       throw new t("readStatus: ComLink1 has INTERFACE ERROR");
/*      */     }
/*      */ 
/*  862 */     if (N()) {
/*  863 */       throw new t("readStatus: ComLink1 has RX OVERFLOW ERROR");
/*      */     }
/*      */ 
/*  866 */     if (S()) {
/*  867 */       throw new t("readStatus: ComLink1 has TX OVERFLOW ERROR");
/*      */     }
/*      */ 
/*  870 */     return b() ? j : 0;
/*      */   }
/*      */ 
/*      */   private boolean b()
/*      */   {
/*  879 */     return (this.T & 0x1) > 0;
/*      */   }
/*      */ 
/*      */   private boolean W()
/*      */   {
/*  888 */     return (this.T & 0x2) > 0;
/*      */   }
/*      */ 
/*      */   private boolean P()
/*      */   {
/*  897 */     return (this.T & 0x4) > 0;
/*      */   }
/*      */ 
/*      */   private boolean L()
/*      */   {
/*  906 */     return (this.T & 0x8) > 0;
/*      */   }
/*      */ 
/*      */   private boolean N()
/*      */   {
/*  915 */     return (this.T & 0x10) > 0;
/*      */   }
/*      */ 
/*      */   private boolean S()
/*      */   {
/*  924 */     return (this.T & 0x20) > 0;
/*      */   }
/*      */ 
/*      */   private int[] Y()
/*      */     throws IOException, t
/*      */   {
/*  935 */     O.A(this, "checkAck: retrieving ComLink2 ACK packet...");
/*  936 */     O._B.G(100);
/*  937 */     int[] arrayOfInt1 = Z().R();
/*      */ 
/*  939 */     if (arrayOfInt1.length != 64) {
/*  940 */       throw new t("checkAck: incorrect comLinkReply length: " + arrayOfInt1.length);
/*      */     }
/*      */ 
/*  945 */     int i = 0;
/*  946 */     int j = arrayOfInt1[(i++)];
/*  947 */     if (j != 1) {
/*  948 */       throw new t("checkAck: bad response code: " + O._B.H(j));
/*      */     }
/*      */ 
/*  953 */     j = arrayOfInt1[(i++)];
/*  954 */     if (j == 102)
/*      */     {
/*  956 */       throw new t("checkAck: NAK received; reason code=" + O._B.H(j) + " - " + F(arrayOfInt1[(i++)]));
/*      */     }
/*  958 */     if (j != 85) {
/*  959 */       throw new t("checkAck: bad ACK/NAK value received: " + O._B.H(j));
/*      */     }
/*      */ 
/*  964 */     int[] arrayOfInt2 = new int[arrayOfInt1.length - 3];
/*  965 */     System.arraycopy(arrayOfInt1, 3, arrayOfInt2, 0, arrayOfInt2.length);
/*  966 */     return arrayOfInt2;
/*      */   }
/*      */ 
/*      */   private int G(int paramInt)
/*      */   {
/*  976 */     int i = paramInt / 64;
/*  977 */     int j = paramInt % 64;
/*  978 */     int k = i + (j > 0 ? 1 : 0);
/*  979 */     return k;
/*      */   }
/*      */ 
/*      */   private void U()
/*      */   {
/*  986 */     this.Q = System.currentTimeMillis();
/*      */   }
/*      */ 
/*      */   private long Q()
/*      */   {
/*  995 */     return System.currentTimeMillis() - this.Q;
/*      */   }
/*      */ 
/*      */   private String F(int paramInt)
/*      */   {
/*      */     String str;
/* 1007 */     if (paramInt <= R.length - 1)
/* 1008 */       str = R[paramInt];
/*      */     else {
/* 1010 */       str = "UNKNOWN NAK DESCRIPTION";
/*      */     }
/* 1012 */     return str;
/*      */   }
/*      */ 
/*      */   private class _A
/*      */   {
/*      */     private static final int A = 100;
/*      */     private static final int L = 2000;
/*      */     private static final int F = 14;
/*      */     private static final int C = 1;
/*      */     private static final int K = 2;
/*      */     private static final int O = 3;
/*      */     private static final int Q = 4;
/*      */     private static final int H = 5;
/*      */     private static final int D = 16;
/*      */     private static final int I = 0;
/*      */     private static final int J = 85;
/*      */     private static final int B = 128;
/*      */     private static final int G = 1024;
/*      */     private static final int M = 1;
/*      */     private static final int N = 167;
/*      */     .A._C R;
/*      */     private boolean E;
/*      */ 
/*      */     private _A(.A._C arg2)
/*      */     {
/*      */       Object localObject;
/*  264 */       this.R = localObject;
/*      */     }
/*      */ 
/*      */     public String toString()
/*      */     {
/*  275 */       return this.R.toString();
/*      */     }
/*      */ 
/*      */     private void B()
/*      */       throws t, W
/*      */     {
/*  288 */       O.Ã = this.R.A;
/*      */ 
/*  290 */       H();
/*      */ 
/*  293 */       I();
/*      */     }
/*      */ 
/*      */     private void I()
/*      */       throws t
/*      */     {
/*  307 */       if (AA.this.K() != 7) {
/*  308 */         AA.this.D(4);
/*      */       }
/*  310 */       E();
/*      */ 
/*  313 */       if ((this.R.a.length > 0) && (!AA.this.E().isHaltRequested())) {
/*  314 */         AA.this.D(5);
/*  315 */         this.R.a = C();
/*      */ 
/*  318 */         AA.this.I();
/*      */       }
/*      */ 
/*  322 */       if (AA.this.K() == 7)
/*  323 */         AA.this.D(4);
/*      */     }
/*      */ 
/*      */     private void E()
/*      */       throws t, W
/*      */     {
/*  336 */       O.A(this, "sendDeviceCommand: SENDING CMD " + this.R + "for device #" + AA.this.J());
/*      */       try
/*      */       {
/*  340 */         int[] arrayOfInt = G();
/*      */ 
/*  342 */         AA.this.Z().C(arrayOfInt);
/*      */ 
/*  345 */         O._B.G(this.R.D());
/*      */ 
/*  348 */         if ((this.R.X != 93) || (this.R.O[0] != 0))
/*      */         {
/*  350 */           AA.D(AA.this);
/*      */         }
/*      */       } catch (IOException localIOException) {
/*  353 */         throw new t("sendDeviceCommand: ERROR - an IOException  has occurred processing cmd " + O._B.H(this.R.X) + " (" + this.R.A + "); exception=" + localIOException);
/*      */       }
/*      */     }
/*      */ 
/*      */     private void H()
/*      */     {
/*  363 */       this.R.a = new int[this.R.E * this.R.Q];
/*      */     }
/*      */ 
/*      */     private int[] C()
/*      */       throws t, W
/*      */     {
/*  376 */       int[] arrayOfInt1 = new int[0];
/*      */       try
/*      */       {
/*      */         do
/*      */         {
/*  381 */           int[] arrayOfInt2 = D();
/*  382 */           arrayOfInt1 = O._B.A(arrayOfInt1, arrayOfInt2);
/*      */ 
/*  384 */           AA.this.B(arrayOfInt2.length);
/*      */ 
/*  386 */           AA.this.I();
/*  387 */           if (!this.E) {
/*  388 */             AA.this.D(6);
/*  389 */             O.A(this, "readDeviceData: just read " + arrayOfInt2.length + " bytes, total bytes=" + arrayOfInt1.length);
/*      */           }
/*      */         }
/*  392 */         while ((!this.E) && (!AA.this.E().isHaltRequested()));
/*      */ 
/*  394 */         O.A(this, "readDeviceData: read " + arrayOfInt1.length + " bytes: <" + O._B.G(arrayOfInt1) + ">");
/*      */       }
/*      */       catch (IOException localIOException) {
/*  397 */         throw new t("readDeviceData: ERROR - an IOException  has occurred processing cmd " + O._B.H(this.R.X) + " (" + this.R.A + "); exception=" + localIOException);
/*      */       }
/*      */ 
/*  401 */       return arrayOfInt1;
/*      */     }
/*      */ 
/*      */     private int[] D()
/*      */       throws IOException, t, W
/*      */     {
/*  420 */       int[] arrayOfInt = J();
/*      */ 
/*  423 */       this.E = ((arrayOfInt[5] & 0x80) > 0);
/*      */ 
/*  426 */       int i = O._B.A(0x7F & arrayOfInt[5], arrayOfInt[6]);
/*      */ 
/*  429 */       if (i < 64) {
/*  430 */         localObject = "readDeviceDataIO: ERROR - cmd " + this + " resulted in low " + "data byte count of " + i;
/*      */ 
/*  432 */         O.E(this, (String)localObject);
/*  433 */         throw new t((String)localObject);
/*      */       }
/*      */ 
/*  437 */       Object localObject = new int[i];
/*  438 */       System.arraycopy(arrayOfInt, 13, localObject, 0, i);
/*      */ 
/*  441 */       int j = arrayOfInt[(arrayOfInt.length - 1)];
/*  442 */       int k = O._B.F(localObject, 0, localObject.length);
/*  443 */       if (k != j) {
/*  444 */         String str = "readDeviceDataIO: ERROR - cmd " + this + " resulted in bad CRC " + "reply of '" + O._B.G(localObject) + "'; crcCalc=" + O._B.H(k) + " crcDevice=" + O._B.H(j);
/*      */ 
/*  448 */         O.E(this, str);
/*  449 */         throw new t(str);
/*      */       }
/*      */ 
/*  452 */       O.B(this, "readDeviceDataIO: done=" + this.E + ", device data[" + localObject.length + "]=<" + O._B.G(localObject) + ">");
/*      */ 
/*  456 */       return (I)localObject;
/*      */     }
/*      */ 
/*      */     private int[] J()
/*      */       throws t, IOException
/*      */     {
/*  470 */       int i = F();
/*      */ 
/*  473 */       int[] arrayOfInt1 = new int[5];
/*  474 */       int j = 0;
/*  475 */       arrayOfInt1[(j++)] = 12;
/*  476 */       arrayOfInt1[(j++)] = 0;
/*  477 */       arrayOfInt1[(j++)] = O._B.J(i);
/*  478 */       arrayOfInt1[(j++)] = O._B.K(i);
/*  479 */       arrayOfInt1[(j++)] = O._B.F(arrayOfInt1, 0, j - 1);
/*      */ 
/*  482 */       int[] arrayOfInt2 = AA.this.Z().A(arrayOfInt1, i);
/*  483 */       Contract.invariant(arrayOfInt2.length == i);
/*      */ 
/*  485 */       if (arrayOfInt2.length < 14) {
/*  486 */         throw new t("readData: insufficient data: <" + O._B.G(arrayOfInt2) + ">");
/*      */       }
/*      */ 
/*  490 */       O.B(this, "readData: retries=" + arrayOfInt2[3] + ", data[" + arrayOfInt2.length + "]=<" + O._B.G(arrayOfInt2) + ">");
/*      */ 
/*  495 */       j = 0;
/*  496 */       int k = arrayOfInt2[(j++)];
/*  497 */       if (k != 2) {
/*  498 */         throw new t("readData: bad response code: " + O._B.H(k));
/*      */       }
/*      */ 
/*  503 */       k = arrayOfInt2[(j++)];
/*  504 */       if (k != 0) {
/*  505 */         throw new t("readData: bad interface number: " + O._B.H(k));
/*      */       }
/*      */ 
/*  510 */       k = arrayOfInt2[(j++)];
/*  511 */       if (k == 5) {
/*  512 */         throw new t("readData: timeout occurred: " + O._B.H(k));
/*      */       }
/*      */ 
/*  515 */       if (k == 2) {
/*  516 */         throw new t("readData: device NAK received: " + O._B.H(k));
/*      */       }
/*      */ 
/*  519 */       if ((k != 1) && (k != 3) && (k != 4))
/*      */       {
/*  521 */         throw new t("readData: ERROR - unknown Response Type received: " + O._B.H(k));
/*      */       }
/*      */ 
/*  524 */       return arrayOfInt2;
/*      */     }
/*      */ 
/*      */     private int F()
/*      */       throws t, IOException
/*      */     {
/*  535 */       AA.C(AA.this);
/*      */ 
/*  540 */       int i = this.R.E / 4;
/*      */ 
/*  542 */       O._B.G(i);
/*  543 */       int j = AA.A(AA.this);
/*      */ 
/*  545 */       while ((j == 0) && (AA.B(AA.this) < 2000L)) {
/*  546 */         O._B.G(i);
/*  547 */         j = AA.A(AA.this);
/*      */ 
/*  549 */         i += i;
/*  550 */         i = Math.min(i, 1000);
/*      */       }
/*      */ 
/*  553 */       return j;
/*      */     }
/*      */ 
/*      */     private int[] G()
/*      */     {
/*  565 */       int i = this.R.I;
/*      */ 
/*  569 */       Contract.pre(i <= 1024);
/*  570 */       Contract.pre(this.R.C > 0);
/*      */ 
/*  572 */       int j = 0;
/*  573 */       int[] arrayOfInt1 = new int[i + 16];
/*      */ 
/*  576 */       arrayOfInt1[(j++)] = 1;
/*      */ 
/*  579 */       arrayOfInt1[(j++)] = 0;
/*      */ 
/*  582 */       arrayOfInt1[(j++)] = 167;
/*      */ 
/*  585 */       arrayOfInt1[(j++)] = 1;
/*      */ 
/*  588 */       int[] arrayOfInt2 = A();
/*  589 */       arrayOfInt1[(j++)] = arrayOfInt2[0];
/*  590 */       arrayOfInt1[(j++)] = arrayOfInt2[1];
/*  591 */       arrayOfInt1[(j++)] = arrayOfInt2[2];
/*      */ 
/*  596 */       arrayOfInt1[(j++)] = (0x80 | O._B.J(i));
/*  597 */       arrayOfInt1[(j++)] = O._B.K(i);
/*      */ 
/*  600 */       arrayOfInt1[(j++)] = (this.R.X == 93 ? 85 : 0);
/*      */ 
/*  604 */       arrayOfInt1[(j++)] = this.R.Y;
/*      */ 
/*  609 */       int k = AA.A(AA.this, this.R.a.length);
/*  610 */       arrayOfInt1[(j++)] = (k > 1 ? 2 : k);
/*      */ 
/*  613 */       arrayOfInt1[(j++)] = 0;
/*  614 */       arrayOfInt1[(j++)] = this.R.X;
/*      */ 
/*  617 */       arrayOfInt1[(j++)] = O._B.F(arrayOfInt1, 0, j - 1);
/*      */ 
/*  620 */       System.arraycopy(this.R.O, 0, arrayOfInt1, j, i);
/*      */ 
/*  622 */       j += i;
/*      */ 
/*  625 */       arrayOfInt1[(j++)] = O._B.F(this.R.O, 0, i);
/*      */ 
/*  628 */       this.R.H = arrayOfInt1;
/*  629 */       return arrayOfInt1;
/*      */     }
/*      */ 
/*      */     private int[] A()
/*      */     {
/*  641 */       Contract.pre(AA.this.J() != null);
/*  642 */       Contract.pre(AA.this.J().length() == 6);
/*      */ 
/*  644 */       return O._B.B(AA.this.J());
/*      */     }
/*      */   }
/*      */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.A.AA
 * JD-Core Version:    0.6.0
 */