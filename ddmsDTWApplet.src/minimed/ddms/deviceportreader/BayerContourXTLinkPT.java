/*      */ package minimed.ddms.A;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.util.HashMap;
/*      */ import java.util.Map;
/*      */ import mdt.common.device.driver.minimed.BayerUSB;
/*      */ import minimed.util.Contract;
/*      */ 
/*      */ public final class E extends J
/*      */ {
/*      */   private static final int É = 15;
/*      */   private static final int À = 30;
/*      */   private static final int º = 64;
/*      */   private static final int Î = 64;
/*      */   static final int ¥ = 256;
/*      */   private static final int Ä = 1024;
/*      */   private static final int Ó = 500;
/*      */   private static final int Æ = 256;
/*      */   private static final String Ð = "X";
/*      */   private static final int Ã = 6;
/*      */   private static final boolean ¢ = false;
/*      */   private static final boolean Í = true;
/*      */   private static final int Ì = 1;
/*      */   private static final int Ñ = 0;
/*      */   private static final String Ò = "vid_1a79";
/*      */   private static final String Å = "pid_6200";
/*      */   private static final int Á = 2;
/*      */   private static final int µ = 5;
/*      */   private BayerUSB Â;
/*      */   private DA ¤;
/*  195 */   private boolean Ë = false;
/*      */   private _C Ï;
/*      */   private _C Ê;
/*      */   private _C Ç;
/*      */   private _C ª;
/*      */   private _C È;
/*      */   private boolean £;
/*      */ 
/*      */   E(v paramv, String paramString)
/*      */   {
/*  213 */     super(paramv, paramString, "BayerContourXTLinkPT");
/*  214 */     Contract.pre(J().length() <= 6);
/*  215 */     E(0);
/*      */ 
/*  218 */     this.Ç = new _C(_B.B, 2, 5, 1, false, null);
/*      */ 
/*  223 */     this.Ï = new _C(_B.D, 0, 30, 1, false, null);
/*      */ 
/*  226 */     this.Ê = new _C(_B.E, 0, 15, 1, false, null);
/*      */ 
/*  229 */     this.ª = new _C(_B.C, 2, 5, 1, false, null);
/*      */ 
/*  232 */     this.È = new _C(_B.C, 2, 5, 64, true, null);
/*      */   }
/*      */ 
/*      */   public static synchronized boolean q()
/*      */   {
/*  249 */     return true;
/*      */   }
/*      */ 
/*      */   public static void n()
/*      */   {
/*      */   }
/*      */ 
/*      */   public void A(n paramn)
/*      */     throws t, W, Z
/*      */   {
/*  273 */     Contract.pre(paramn instanceof .A._C);
/*  274 */     if (!this.Ë) {
/*  275 */       throw new t("BayerContourXTLinkPT communications not initialized: command=" + paramn);
/*      */     }
/*      */ 
/*  279 */     .A._C local_C = (.A._C)paramn;
/*      */     _C local_C1;
/*  284 */     if ((local_C.X == 93) && (local_C.O[0] == 1))
/*      */     {
/*  286 */       local_C1 = this.Ï;
/*  287 */     } else if ((local_C.X == 93) && (local_C.O[0] == 0))
/*      */     {
/*  289 */       local_C1 = this.Ê;
/*      */     }
/*      */     else {
/*  292 */       local_C1 = local_C.E > 64 ? this.È : this.ª;
/*      */ 
/*  295 */       if (_C.A(local_C1))
/*      */       {
/*  297 */         int i = local_C.E >= 1024 ? 256 : 64;
/*      */ 
/*  301 */         if (i > local_C.E) {
/*  302 */           i = local_C.E;
/*      */         }
/*  304 */         _C.A(local_C1, i);
/*      */       }
/*      */     }
/*      */     try
/*      */     {
/*  309 */       _C.A(local_C1, local_C);
/*      */     } catch (IOException localIOException) {
/*  311 */       throw new t(localIOException.getMessage());
/*      */     }
/*      */   }
/*      */ 
/*      */   void H()
/*      */     throws IOException, W, t
/*      */   {
/*  318 */     if (!this.Ë) {
/*  319 */       O.A(this, "initCommunicationsIO: BEGIN  maxWait=256, VID/PID=vid_1a79/pid_6200");
/*      */ 
/*  321 */       this.£ = false;
/*      */ 
/*  323 */       this.Â = new BayerUSB(256L, "vid_1a79", "pid_6200");
/*  324 */       this.¤ = new DA(this.Â);
/*  325 */       A(this.¤);
/*      */       try
/*      */       {
/*  328 */         this.¤._();
/*      */       } catch (IOException localIOException1) {
/*  330 */         throw new IOException("Link Device not present--reconnect & try again... (" + localIOException1 + ")");
/*      */       }
/*      */ 
/*  334 */       for (int i = 0; (i <= 2) && (!this.Ë); i++) {
/*      */         try {
/*  336 */           o();
/*  337 */           this.Ë = true;
/*      */         }
/*      */         catch (IOException localIOException2) {
/*  340 */           O.D(this, "initCommunicationsIO: initLinkCommunications failed with " + localIOException2);
/*      */         }
/*      */         catch (t localt)
/*      */         {
/*  344 */           O.D(this, "initCommunicationsIO: initLinkCommunications failed with " + localt);
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*  349 */       if (!this.Ë) {
/*  350 */         throw new t("could not establish communications with Link Device");
/*      */       }
/*      */ 
/*  354 */       O.A(this, "initCommunicationsIO: END...");
/*      */     }
/*      */   }
/*      */ 
/*      */   void F() throws IOException
/*      */   {
/*  360 */     if (this.Ë)
/*      */     {
/*      */       try
/*      */       {
/*  364 */         boolean bool = E().isHaltRequested();
/*  365 */         w().A(true);
/*  366 */         p();
/*  367 */         w().A(!bool);
/*      */       } catch (t localt) {
/*      */       }
/*      */       finally {
/*  371 */         u();
/*  372 */         this.Ë = false;
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   DA w()
/*      */   {
/*  383 */     return (DA)C();
/*      */   }
/*      */ 
/*      */   private void A(.A._C param_C, _C param_C1)
/*      */   {
/*  393 */     if (_C.A(param_C1)) {
/*  394 */       O.A(this, "abortMultipacketCommand: aborting operation...");
/*  395 */       _C.B(param_C1, param_C);
/*      */     }
/*      */   }
/*      */ 
/*      */   private void o()
/*      */     throws t, IOException
/*      */   {
/*      */     try
/*      */     {
/*  408 */       s();
/*      */     }
/*      */     catch (t localt)
/*      */     {
/*      */     }
/*      */ 
/*  415 */     r();
/*      */   }
/*      */ 
/*      */   private void s()
/*      */     throws t, IOException
/*      */   {
/*  424 */     t();
/*  425 */     C("X");
/*  426 */     String str = w().C();
/*  427 */     if (!str.endsWith(String.valueOf('\005'))) {
/*  428 */       throw new t("did not find ENQ in X command reply of: " + str);
/*      */     }
/*  430 */     w().K(21);
/*  431 */     A(w().I(1), 4);
/*  432 */     w().K(5);
/*  433 */     A(w().I(1), 6);
/*      */   }
/*      */ 
/*      */   private void r()
/*      */     throws t, IOException
/*      */   {
/*  443 */     v();
/*  444 */     C("1|");
/*      */     try {
/*  446 */       A(w().I(1), 6);
/*      */     }
/*      */     catch (t localt) {
/*  449 */       C("0|");
/*  450 */       A(w().I(1), 6);
/*  451 */       C("1|");
/*  452 */       A(w().I(1), 6);
/*      */     }
/*  454 */     O.A(this, "enablePassthroughMode: passthrough mode enabled");
/*      */   }
/*      */ 
/*      */   private void p()
/*      */     throws t, IOException
/*      */   {
/*  464 */     v();
/*  465 */     C("0|");
/*  466 */     A(w().I(1), 6);
/*  467 */     O.A(this, "disablePassthroughMode: passthrough mode disabled");
/*      */   }
/*      */ 
/*      */   private void v()
/*      */     throws t, IOException
/*      */   {
/*  477 */     C("W|");
/*  478 */     A(w().I(1), 6);
/*  479 */     C("Q|");
/*  480 */     A(w().I(1), 6);
/*      */   }
/*      */ 
/*      */   private void A(int[] paramArrayOfInt, int paramInt)
/*      */     throws t
/*      */   {
/*  492 */     if (paramArrayOfInt[0] != paramInt)
/*  493 */       throw new t("checkValue: value of " + paramArrayOfInt[0] + " does not " + "equal " + paramInt);
/*      */   }
/*      */ 
/*      */   private void u()
/*      */   {
/*  502 */     this.¤.V();
/*  503 */     A(null);
/*      */   }
/*      */ 
/*      */   private void C(String paramString)
/*      */     throws IOException
/*      */   {
/* 1140 */     t();
/* 1141 */     w().A(paramString);
/* 1142 */     O._B.G(500);
/*      */   }
/*      */ 
/*      */   private void t()
/*      */     throws IOException
/*      */   {
/* 1153 */     if ((this.Ë) && (!this.Â.hasHandle())) {
/* 1154 */       this.Ë = false;
/* 1155 */       throw new IOException("device " + this.Â + "not attached");
/*      */     }
/*      */   }
/*      */ 
/*      */   class _C
/*      */   {
/*      */     private static final char J = 'Q';
/*      */     private static final int H = 27;
/*      */     private static final int G = 167;
/*      */     private static final int B = 7;
/*      */     private static final int E = 1;
/*      */     private static final int M = 1;
/*      */     private static final int O = 33;
/*      */     private static final int F = 15;
/*      */     private static final int L = 255;
/*      */     private static final int N = 4095;
/*      */     private final E._B I;
/*      */     private final int D;
/*      */     private final int K;
/*      */     private int A;
/*      */     private boolean C;
/*      */     private int[] R;
/*      */     private int[] P;
/*      */ 
/*      */     private _C(E._B paramInt1, int paramInt2, int paramInt3, int paramBoolean, boolean arg6)
/*      */     {
/*  557 */       Contract.pre(paramInt2, 0, 15);
/*  558 */       Contract.pre(paramInt3, 0, 255);
/*  559 */       Contract.pre(paramBoolean, 1, 4095);
/*      */ 
/*  561 */       this.I = paramInt1;
/*  562 */       this.D = paramInt2;
/*  563 */       this.K = paramInt3;
/*  564 */       this.A = paramBoolean;
/*      */       boolean bool;
/*  565 */       this.C = bool;
/*      */     }
/*      */ 
/*      */     public String toString()
/*      */     {
/*  572 */       return "PassthroughCommand [linkDeviceOperation=" + this.I + ", retryCount=" + this.D + ", timeoutSeconds=" + this.K + ", chunkSize=" + this.A + ", multiPacketResponse=" + this.C + "]";
/*      */     }
/*      */ 
/*      */     private void G(.A._C param_C)
/*      */       throws W, IOException, t
/*      */     {
/*  589 */       if (this.I != E._B.B) {
/*  590 */         A(param_C);
/*      */       }
/*      */ 
/*  594 */       param_C.Y = 0;
/*      */ 
/*  596 */       if ((param_C.I > 0) && (this.I == E._B.C))
/*      */       {
/*  601 */         .A._C local_C = D(param_C);
/*  602 */         this.C = false;
/*      */ 
/*  606 */         G(local_C);
/*      */ 
/*  610 */         if (param_C.E > 0) {
/*  611 */           this.C = true;
/*      */         }
/*      */       }
/*      */ 
/*  615 */       O.A(this, "execute: ********** processing cmd " + param_C + "**********");
/*      */ 
/*  617 */       B(param_C);
/*      */ 
/*  621 */       if (this.I == E._B.E)
/*      */       {
/*  623 */         E.A(E.this, false);
/*  624 */         I(param_C);
/*      */       } else {
/*      */         try {
/*  627 */           this.P = B();
/*      */         }
/*      */         catch (t localt)
/*      */         {
/*  632 */           if (this.I == E._B.B) {
/*  633 */             O.A(this, "execute: re-reading execution ACK...");
/*  634 */             this.P = B();
/*      */           } else {
/*  636 */             throw localt;
/*      */           }
/*      */         }
/*      */       }
/*      */ 
/*  641 */       if (this.I != E._B.B)
/*  642 */         if (this.C)
/*  643 */           C(param_C);
/*      */         else
/*  645 */           J(param_C);
/*      */     }
/*      */ 
/*      */     private boolean A(.A._C param_C)
/*      */     {
/*  658 */       boolean bool = E.this.E().isHaltRequested();
/*  659 */       if (bool) {
/*  660 */         I(param_C);
/*      */       }
/*  662 */       return bool;
/*      */     }
/*      */ 
/*      */     private void I(.A._C param_C)
/*      */     {
/*  671 */       .A._C local_C = (.A._C)param_C.clone();
/*      */ 
/*  673 */       local_C.A = ("Aborting " + local_C.A);
/*      */       try
/*      */       {
/*  676 */         if (!E.B(E.this)) {
/*  677 */           E.A(E.this, true);
/*  678 */           O.A(this, "sendAbortOperationCommand: sending Abort Operation passthrough cmd...");
/*      */ 
/*  680 */           E.C(E.this).G(local_C);
/*      */         }
/*      */       }
/*      */       catch (IOException localIOException3) {
/*  684 */         O.D(this, "sendAbortOperationCommand: Abort Operation passthrough cmd resulted in exception " + localIOException2);
/*      */       }
/*      */       catch (t localIOException4)
/*      */       {
/*  689 */         O.D(this, "sendAbortOperationCommand: Abort Operation passthrough cmd resulted in exception " + localt);
/*      */       }
/*      */       finally
/*      */       {
/*      */         try {
/*  694 */           E.this.w().T();
/*      */         }
/*      */         catch (IOException localIOException5)
/*      */         {
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/*      */     private int[] B()
/*      */       throws IOException, t
/*      */     {
/*  710 */       E.A(E.this);
/*  711 */       int i = 0;
/*  712 */       long l = O._B.A();
/*  713 */       int[] arrayOfInt1 = new int[0];
/*      */ 
/*  716 */       int j = this.K == 0 ? 1 : 0;
/*      */ 
/*  719 */       int k = this.K * (this.D + 1);
/*      */ 
/*  722 */       while ((i == 0) && ((O._B.F(l) <= k) || (j != 0))) {
/*  723 */         arrayOfInt1 = E.this.w().I(1);
/*  724 */         i = arrayOfInt1[0] == 81 ? 1 : 0;
/*      */       }
/*      */ 
/*  727 */       if (i == 0) {
/*  728 */         throw new IOException("envelope reply timeout occurred after " + k + " seconds for command " + this);
/*      */       }
/*      */ 
/*  732 */       int[] arrayOfInt2 = E.this.w().I(32);
/*  733 */       int[] arrayOfInt3 = O._B.A(arrayOfInt1, arrayOfInt2);
/*      */ 
/*  736 */       for (int m = 0; m < this.R.length; m++) {
/*  737 */         if (this.R[m] != arrayOfInt3[m]) {
/*  738 */           throw new t("checkExecutionAck: ERROR - bad envelope preamble, expected <" + O._B.G(this.R) + ">; have <" + O._B.G(arrayOfInt3) + ">");
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*  746 */       A(arrayOfInt3[27]);
/*  747 */       return arrayOfInt3;
/*      */     }
/*      */ 
/*      */     private void A(int paramInt)
/*      */       throws IOException
/*      */     {
/*  757 */       E._A local_A = E._A.B(paramInt);
/*      */ 
/*  759 */       if (local_A == null) {
/*  760 */         throw new IOException("checkAck: received UNKNOWN nak value " + O._B.H(paramInt));
/*      */       }
/*      */ 
/*  764 */       if (local_A.equals(E._A.D))
/*  765 */         O.B(this, "checkAck: received ack reply " + local_A);
/*      */       else
/*  767 */         throw new IOException("checkAck: received nak reply " + local_A);
/*      */     }
/*      */ 
/*      */     private int B(.A._C param_C)
/*      */       throws IOException
/*      */     {
/*  779 */       E.A(E.this);
/*  780 */       if (E.this.K() != 7) {
/*  781 */         E.this.D(4);
/*      */       }
/*      */ 
/*  784 */       if (this.I == E._B.C) {
/*  785 */         H(param_C);
/*      */       }
/*  787 */       int[] arrayOfInt = F(param_C);
/*      */ 
/*  789 */       O.A(this, "sendCommand: sending cmd " + param_C);
/*  790 */       O.Ã = param_C.A;
/*      */ 
/*  793 */       E(param_C);
/*      */ 
/*  796 */       if (this.I == E._B.B) {
/*  797 */         E.this.w().A(true);
/*      */       }
/*      */ 
/*  800 */       E.this.w().C(arrayOfInt);
/*  801 */       return arrayOfInt.length;
/*      */     }
/*      */ 
/*      */     private int[] F(.A._C param_C)
/*      */     {
/*  811 */       O.A(this, "createEnvelope: creating envelope for device command: " + param_C);
/*      */ 
/*  813 */       int[] arrayOfInt1 = new int[33];
/*      */ 
/*  818 */       arrayOfInt1[0] = 81;
/*      */ 
/*  821 */       arrayOfInt1[1] = 1;
/*      */ 
/*  824 */       int[] arrayOfInt2 = O._B.D(E.this.J());
/*  825 */       System.arraycopy(arrayOfInt2, 0, arrayOfInt1, 2, arrayOfInt2.length);
/*      */ 
/*  828 */       arrayOfInt1[18] = E._B.A(this.I);
/*      */ 
/*  831 */       this.R = new int[19];
/*  832 */       System.arraycopy(arrayOfInt1, 0, this.R, 0, this.R.length);
/*      */ 
/*  837 */       int i = 1;
/*  838 */       arrayOfInt1[19] = O._B.C(this.D, i);
/*  839 */       arrayOfInt1[20] = this.K;
/*  840 */       arrayOfInt1[21] = 0;
/*  841 */       arrayOfInt1[22] = 0;
/*      */ 
/*  844 */       long l = 0L;
/*  845 */       int j = param_C.E;
/*      */ 
/*  847 */       if (this.C) {
/*  848 */         k = 1;
/*  849 */         Contract.invariant(j > 0);
/*      */ 
/*  853 */         l = O._B.A(k << 4, this.A >> 4, O._B.C(O._B.D(O._B.K(this.A)), j >> 8), O._B.K(j));
/*      */       }
/*      */ 
/*  869 */       int k = O._B.D(l);
/*  870 */       int m = O._B.C(l);
/*      */ 
/*  873 */       arrayOfInt1[23] = O._B.K(k);
/*  874 */       arrayOfInt1[24] = O._B.J(k);
/*  875 */       arrayOfInt1[25] = O._B.K(m);
/*  876 */       arrayOfInt1[26] = O._B.J(m);
/*      */ 
/*  879 */       arrayOfInt1[27] = 0;
/*      */ 
/*  882 */       int n = 0;
/*  883 */       int[] arrayOfInt3 = new int[0];
/*      */ 
/*  885 */       if (this.I != E._B.B) {
/*  886 */         n = param_C.H.length;
/*  887 */         arrayOfInt3 = param_C.H;
/*      */       }
/*      */ 
/*  891 */       arrayOfInt1[28] = O._B.K(n);
/*  892 */       arrayOfInt1[29] = O._B.J(n);
/*  893 */       arrayOfInt1[30] = 0;
/*  894 */       arrayOfInt1[31] = 0;
/*      */ 
/*  897 */       arrayOfInt1[32] = 0;
/*      */ 
/*  900 */       int[] arrayOfInt4 = O._B.A(arrayOfInt1, arrayOfInt3);
/*      */ 
/*  903 */       int i1 = O._B.G(arrayOfInt4, 0, arrayOfInt4.length);
/*      */ 
/*  905 */       arrayOfInt1[32] = i1;
/*  906 */       arrayOfInt4[32] = i1;
/*      */ 
/*  908 */       O.A(this, "createEnvelope: envelope parameters-envID=" + Character.valueOf((char)arrayOfInt1[0]) + ", devType=" + arrayOfInt1[1] + ", devID=" + O._B.B(arrayOfInt1, 2, 16) + ", devCmd=" + O._B.H(arrayOfInt1[18]) + " (" + this.I + (this.C ? "-multi_pkt" : "") + ")" + ", cmdArgs=0x" + O._B.F(arrayOfInt1[22]) + O._B.F(arrayOfInt1[21]) + O._B.F(arrayOfInt1[20]) + O._B.F(arrayOfInt1[19]) + ", cmdResponse=0x" + O._B.F(arrayOfInt1[26]) + O._B.F(arrayOfInt1[25]) + O._B.F(arrayOfInt1[24]) + O._B.F(arrayOfInt1[23]) + ", dataSize=0x" + O._B.F(arrayOfInt1[31]) + O._B.F(arrayOfInt1[30]) + O._B.F(arrayOfInt1[29]) + O._B.F(arrayOfInt1[28]) + ", CRC=" + O._B.H(arrayOfInt1[32]));
/*      */ 
/*  933 */       O.B(this, "createEnvelope: envelope contents-header=<" + O._B.G(arrayOfInt1) + ">, " + "payload=<" + O._B.G(arrayOfInt3) + ">");
/*      */ 
/*  936 */       return arrayOfInt4;
/*      */     }
/*      */ 
/*      */     private void H(.A._C param_C)
/*      */     {
/*  948 */       Contract.pre(param_C.C > 0);
/*  949 */       int i = 0;
/*  950 */       int[] arrayOfInt1 = new int[7];
/*      */ 
/*  953 */       arrayOfInt1[(i++)] = 167;
/*      */ 
/*  956 */       int[] arrayOfInt2 = A();
/*  957 */       arrayOfInt1[(i++)] = arrayOfInt2[0];
/*  958 */       arrayOfInt1[(i++)] = arrayOfInt2[1];
/*  959 */       arrayOfInt1[(i++)] = arrayOfInt2[2];
/*      */ 
/*  962 */       arrayOfInt1[(i++)] = param_C.X;
/*      */ 
/*  966 */       if (param_C.I == 0) {
/*  967 */         arrayOfInt1[(i++)] = 0;
/*      */       }
/*      */       else {
/*  970 */         int[] arrayOfInt3 = new int[i + 1];
/*  971 */         System.arraycopy(arrayOfInt1, 0, arrayOfInt3, 0, i);
/*      */ 
/*  974 */         arrayOfInt3[i] = param_C.I;
/*      */ 
/*  977 */         arrayOfInt1 = O._B.A(arrayOfInt3, param_C.O);
/*      */ 
/*  980 */         arrayOfInt1 = O._B.A(arrayOfInt1, new int[1]);
/*  981 */         i = arrayOfInt1.length - 1;
/*      */       }
/*      */ 
/*  985 */       arrayOfInt1[(i++)] = O._B.F(arrayOfInt1, 0, i - 1);
/*  986 */       param_C.H = arrayOfInt1;
/*      */     }
/*      */ 
/*      */     private void E(.A._C param_C)
/*      */     {
/*  993 */       param_C.a = new int[param_C.E * param_C.Q];
/*      */     }
/*      */ 
/*      */     private int[] A()
/*      */     {
/* 1006 */       Contract.pre(E.this.J() != null);
/* 1007 */       Contract.pre(E.this.J().length() == 6);
/*      */ 
/* 1009 */       return O._B.B(E.this.J());
/*      */     }
/*      */ 
/*      */     private .A._C D(.A._C param_C)
/*      */     {
/* 1019 */       .A._C local_C = (.A._C)param_C.clone();
/* 1020 */       param_C.A += "-command packet";
/* 1021 */       local_C.E = 0;
/* 1022 */       local_C.Q = 0;
/* 1023 */       local_C._ = 0;
/* 1024 */       local_C.I = 0;
/* 1025 */       return local_C;
/*      */     }
/*      */ 
/*      */     private void C(.A._C param_C)
/*      */       throws t, IOException
/*      */     {
/* 1040 */       E.A(E.this);
/*      */ 
/* 1042 */       if ((param_C.a.length > 0) && (!A(param_C))) {
/* 1043 */         E.this.D(5);
/*      */ 
/* 1045 */         int i = 0;
/* 1046 */         int j = 0;
/* 1047 */         int k = this.A < param_C.a.length ? 1 : 0;
/*      */ 
/* 1051 */         while ((i < param_C.a.length) && (!A(param_C))) {
/* 1052 */           if ((k != 0) && (i > 0))
/*      */           {
/* 1054 */             this.P = B();
/*      */           }
/* 1056 */           int[] arrayOfInt = E.this.w().I(this.A);
/*      */ 
/* 1059 */           if (i + arrayOfInt.length > param_C.a.length) {
/* 1060 */             throw new t("data size error: byte count of " + (i + arrayOfInt.length) + " received exceeds " + "m_rawData.length of " + param_C.a.length);
/*      */           }
/*      */ 
/* 1065 */           System.arraycopy(arrayOfInt, 0, param_C.a, i, arrayOfInt.length);
/* 1066 */           i += arrayOfInt.length;
/* 1067 */           j++;
/* 1068 */           O.A(this, "readDeviceDataMultPacket: packet " + j + " = [" + arrayOfInt.length + "] <" + O._B.G(arrayOfInt) + ">");
/*      */ 
/* 1071 */           E.this.B(arrayOfInt.length);
/* 1072 */           E.this.I();
/*      */         }
/*      */       }
/*      */ 
/* 1076 */       if (!A(param_C)) {
/* 1077 */         O.A(this, "readDeviceDataMultPacket: all data = [" + param_C.a.length + "] " + "<" + O._B.G(param_C.a) + ">");
/*      */       }
/*      */ 
/* 1083 */       if (E.this.K() == 7)
/* 1084 */         E.this.D(4);
/*      */     }
/*      */ 
/*      */     private void J(.A._C param_C)
/*      */       throws IOException
/*      */     {
/* 1096 */       E.A(E.this);
/*      */ 
/* 1098 */       if (!A(param_C))
/*      */       {
/* 1101 */         if (param_C.a.length > 0) {
/* 1102 */           E.this.D(5);
/* 1103 */           int[] arrayOfInt1 = E.this.w().I(param_C.a.length);
/* 1104 */           System.arraycopy(arrayOfInt1, 0, param_C.a, 0, param_C.a.length);
/* 1105 */           O.A(this, "readDeviceDataSinglePacket: packet = [" + param_C.a.length + "] " + "<" + O._B.G(param_C.a) + ">");
/*      */ 
/* 1110 */           E.this.B(arrayOfInt1.length);
/* 1111 */           E.this.I();
/* 1112 */         } else if (this.P != null)
/*      */         {
/* 1115 */           int i = this.P[28];
/* 1116 */           if (i > 0) {
/* 1117 */             int[] arrayOfInt2 = E.this.w().I(i);
/* 1118 */             O.B(this, "readDeviceDataSinglePacket: pumpReply = [" + i + "] " + "<" + O._B.G(arrayOfInt2) + ">");
/*      */           }
/*      */ 
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/* 1126 */       if (E.this.K() == 7)
/* 1127 */         E.this.D(4);
/*      */     }
/*      */   }
/*      */ 
/*      */   private static enum _A
/*      */   {
/*      */     private int F;
/*      */     private static final Map<Integer, _A> B;
/*      */ 
/*      */     private _A(int paramInt)
/*      */     {
/*  162 */       this.F = paramInt;
/*      */     }
/*      */ 
/*      */     public String toString()
/*      */     {
/*  167 */       return super.toString() + "(" + O._B.H(this.F) + ")";
/*      */     }
/*      */ 
/*      */     private int A()
/*      */     {
/*  176 */       return this.F;
/*      */     }
/*      */ 
/*      */     private static _A A(int paramInt)
/*      */     {
/*  187 */       return (_A)B.get(Integer.valueOf(paramInt));
/*      */     }
/*      */ 
/*      */     static
/*      */     {
/*  148 */       B = new HashMap();
/*      */ 
/*  152 */       for (_A local_A : values())
/*  153 */         B.put(Integer.valueOf(local_A.A()), local_A);
/*      */     }
/*      */   }
/*      */ 
/*      */   private static enum _B
/*      */   {
/*      */     private int F;
/*      */ 
/*      */     private _B(int paramInt)
/*      */     {
/*  114 */       this.F = paramInt;
/*      */     }
/*      */ 
/*      */     public String toString()
/*      */     {
/*  119 */       return super.toString() + "(" + O._B.H(this.F) + ")";
/*      */     }
/*      */ 
/*      */     private int A()
/*      */     {
/*  128 */       return this.F;
/*      */     }
/*      */   }
/*      */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.A.E
 * JD-Core Version:    0.6.0
 */