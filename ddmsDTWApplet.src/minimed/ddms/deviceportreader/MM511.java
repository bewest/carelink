/*      */ package minimed.ddms.A;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import minimed.util.Contract;
/*      */ 
/*      */ abstract class $A extends M
/*      */ {
/*      */   static final int ɞ = 112;
/*      */   private static final int Ȓ = 77;
/*      */   static final int ɖ = 93;
/*      */   private static final int Ǫ = 112;
/*      */   private static final int ǲ = 113;
/*      */   private static final int ɤ = 114;
/*      */   private static final int ǳ = 115;
/*      */   private static final int ɑ = 116;
/*      */   private static final int ǰ = 117;
/*      */   private static final int ɐ = 118;
/*      */   private static final int Ǵ = 120;
/*      */   private static final int ǯ = 121;
/*      */   private static final int ɣ = 122;
/*      */   private static final int ɩ = 123;
/*      */   private static final int ȉ = 124;
/*      */   private static final int ȃ = 127;
/*      */   private static final int ɥ = 128;
/*      */   private static final int Ȉ = 131;
/*      */   private static final int ǩ = 160;
/*      */   private static final int ɮ = 163;
/*      */   private static final int ȋ = 164;
/*      */   private static final int ȓ = 166;
/*      */   private static final int ɫ = 167;
/*      */   private static final int Ȑ = 0;
/*      */   private static final int ǥ = 1;
/*      */   private static final int ɗ = 2;
/*      */   private static final int Ǣ = 3;
/*      */   private static final int ǻ = 4;
/*      */   private static final int ǿ = 5;
/*      */   private static final int ǧ = 6;
/*      */   private static final int Ǧ = 8;
/*      */   private static final int ɘ = 9;
/*      */   private static final int ǫ = 10;
/*      */   private static final int Ǳ = 11;
/*      */   private static final int ǣ = 12;
/*      */   private static final int Ǡ = 13;
/*      */   private static final int Ȃ = 64;
/*      */   private static final int ȁ = 128;
/*      */   private static final int ɠ = 1024;
/*      */   private static final int ɜ = 1024;
/*      */   private static final int Ȇ = 0;
/*      */   private static final int Ȕ = 1;
/*      */   private static final int ɨ = 32;
/*      */   private static final int ǡ = 49;
/*      */   private static final int ɦ = 11;
/*      */   private static final int Ǽ = 1;
/*      */   private static final int ȏ = 20;
/*      */   private static final int ɔ = 310;
/*      */   private static final int Ȏ = 0;
/*      */   private static final int ɕ = 67;
/*      */   private static final int ǭ = 100;
/*      */   private static final int Ȅ = 2;
/*      */   private static final int ɭ = 3;
/*      */   private static final int ɟ = 24;
/*      */   private static final int Ȍ = 0;
/*      */   private static final int Ȋ = 1;
/*      */   private static final int ɡ = 4;
/*      */   private static final int ɬ = 1;
/*      */   private static final int ɛ = 2;
/*      */   private static final int Ǭ = 0;
/*      */   private static final int ȍ = 2;
/*      */   private static final int ɢ = 0;
/*      */   private static final int ǽ = 1;
/*      */   private static final int Ȁ = 2000;
/*      */   private static final int Ǿ = 2099;
/*      */   static final int Ȗ = 0;
/*      */   static final int ɒ = 1;
/*      */   private static final int ɚ = 10;
/*      */   private static final int Ǯ = 0;
/*      */   private static final int ȑ = 4;
/*  207 */   private static final String[] ɝ = { "UNKNOWN NAK DESCRIPTION", "REQUEST PAUSE FOR 3 SECONDS", "REQUEST PAUSE UNTIL ACK RECEIVED", "CRC ERROR", "REFUSE PROGRAM UPLOAD", "TIMEOUT ERROR", "COUNTER SEQUENCE ERROR", "PUMP IN ERROR STATE", "INCONSISTENT COMMAND REQUEST", "DATA OUT OF RANGE", "DATA CONSISTENCY", "ATTEMPT TO ACTIVATE UNUSED PROFILES", "PUMP DELIVERING BOLUS", "REQUESTED HISTORY BLOCK HAS NO DATA", "HARDWARE FAILURE" };
/*      */   private static final int ȇ = 12;
/*      */   static final int ɓ = 13;
/*      */   private static final int ȕ = 500;
/*      */   private static final int Ǥ = 1;
/*      */   private static final int ə = 2;
/*      */   private static final String ɪ = "VER 1.6";
/*      */   private static final String ɧ = "VER 1.7";
/*      */   private static final int Ǩ = 10;
/*      */   private static final int Ǻ = 10;
/*      */   private static final double ȅ = 100.0D;
/*      */   private static final int ǵ = 17000;
/*      */   private int ȗ;
/*      */ 
/*      */   $A(int paramInt1, String paramString, int paramInt2, int paramInt3, int paramInt4)
/*      */   {
/*  297 */     Contract.pre(paramInt1, 14, 20);
/*      */ 
/*  300 */     this.ū = 10;
/*  301 */     this.ƿ = 10;
/*      */ 
/*  304 */     this.ć = paramString;
/*  305 */     this.đ = paramInt1;
/*  306 */     A(this, "creating interface to the '" + this.ć + "', link device = " + I.A(this.đ) + ", package version = " + K());
/*      */ 
/*  309 */     this.ā = paramInt2;
/*  310 */     this.Þ = paramInt3;
/*  311 */     this.ȗ = paramInt4;
/*  312 */     this.à = 2000;
/*  313 */     this.Ð = 2099;
/*      */ 
/*  315 */     this.Ű = new _C(116, "Read Firmware Version");
/*      */ 
/*  318 */     this.ƛ = new _C(113, "Read Pump ID");
/*      */ 
/*  320 */     this.ń = new _C(112, "Read Real Time Clock");
/*      */ 
/*  322 */     this.ǜ = new _C(127, "Read Current Settings");
/*      */ 
/*  324 */     this.Ɗ = new _C(121, "Read Today's Total Insulin");
/*      */ 
/*  327 */     this.ř = new _C(120, "Read Temporary Basal");
/*      */ 
/*  330 */     this.Ƈ = new _C(122, "Read Standard Profiles Data", 128, 1, 8);
/*      */ 
/*  339 */     this.ƻ = null;
/*      */ 
/*  342 */     this.Ų = new _C(123, "Read Profiles A Data", 128, 1, 9);
/*      */ 
/*  351 */     this.ű = new _C(124, "Read Profiles B Data", 128, 1, 10);
/*      */ 
/*  359 */     this.ĳ = new _C(114, "Read Battery Status");
/*      */ 
/*  361 */     this.Ʒ = new _C(115, "Read Remaining Insulin");
/*      */ 
/*  364 */     this.ş = new _C(117, "Read Pump Error Status (current alarm code)");
/*      */ 
/*  367 */     this.ǅ = new _C(118, "Read Remote Control Ids");
/*      */ 
/*  370 */     this.Ƴ = new _C(131, "Read Pump State");
/*      */ 
/*  372 */     this.ƈ = new _B();
/*      */ 
/*  376 */     this.ǝ = new _C(93, "Set RF Power On", 2);
/*  377 */     this.ǝ.O[0] = 1;
/*  378 */     this.ǝ.O[1] = 10;
/*  379 */     this.ǝ.Y = 0;
/*  380 */     this.ǝ.A(17000);
/*      */ 
/*  383 */     this.Š = new _C(77, "Set Suspend", 1);
/*  384 */     this.Š.O[0] = 1;
/*      */ 
/*  387 */     this.Ń = new _C(77, "Cancel Suspend", 1);
/*  388 */     this.Ń.O[0] = 0;
/*      */ 
/*  391 */     this.Œ = new _C(160, "Enable Detail Trace", 1);
/*      */ 
/*  393 */     this.Œ.O[0] = 1;
/*      */ 
/*  395 */     this.ļ = new _C(160, "Disable Detail Trace", 1);
/*      */ 
/*  397 */     this.ļ.O[0] = 0;
/*      */ 
/*  399 */     this.ź = new _C(163, "Read Pump Trace", 1024, 49, 0);
/*      */ 
/*  401 */     this.Ʋ = new _C(164, "Read Detail Trace", 1024, 11, 0);
/*      */ 
/*  403 */     this.Ŧ = new _C(166, "Read New Alarm Trace", 1024, 11, 0);
/*      */ 
/*  405 */     this.ǎ = new _C(167, "Read Old Alarm Trace", 1024, 11, 0);
/*      */ 
/*  408 */     this.Ė = new _A();
/*  409 */     ƥ = 10;
/*  410 */     ſ = 4;
/*  411 */     B(1);
/*      */   }
/*      */ 
/*      */   void D(String paramString)
/*      */     throws IOException
/*      */   {
/*  423 */     this.u = paramString;
/*      */   }
/*      */ 
/*      */   void F(String paramString)
/*      */     throws IOException, W, t
/*      */   {
/*  436 */     E(3);
/*      */ 
/*  438 */     switch (this.đ) {
/*      */     case 14:
/*      */     case 15:
/*  441 */       A(new BA(this, this.ă, this.đ, paramString));
/*      */ 
/*  443 */       break;
/*      */     case 19:
/*  445 */       A(new AA(this, this.ă));
/*  446 */       break;
/*      */     case 20:
/*  448 */       A(new E(this, this.ă));
/*  449 */       break;
/*      */     case 16:
/*      */     case 17:
/*      */     case 18:
/*      */     default:
/*  451 */       Contract.unreachable();
/*      */     }
/*      */ 
/*  455 */     W().D();
/*  456 */     A(this, "initCommunicationsLink: ************************ DONE **************************");
/*      */   }
/*      */ 
/*      */   void j()
/*      */     throws IOException
/*      */   {
/*  467 */     if (W() != null) {
/*  468 */       W().B();
/*  469 */       A(null);
/*      */     }
/*      */   }
/*      */ 
/*      */   void _()
/*      */     throws IOException
/*      */   {
/*  480 */     if (W() != null)
/*      */     {
/*  482 */       W().B();
/*      */     }
/*      */   }
/*      */ 
/*      */   void i()
/*      */     throws IOException, t, P
/*      */   {
/*  498 */     Contract.pre(W() != null);
/*      */ 
/*  501 */     E(4);
/*  502 */     W().A();
/*  503 */     this.ǝ.A();
/*      */ 
/*  508 */     this.ş.A();
/*      */     try {
/*  510 */       A(this.ş);
/*      */     } catch (Z localZ1) {
/*  512 */       throw new P("Bad Error Status Reply", 2, O._B.E(this.ş.a));
/*      */     }
/*      */ 
/*  518 */     if (this.Ļ != 0) {
/*  519 */       throw new P("Pump Alarm (error)", 2, Integer.toString(this.Ļ));
/*      */     }
/*      */ 
/*  525 */     this.Ƴ.A();
/*      */     try {
/*  527 */       A(this.Ƴ);
/*      */     } catch (Z localZ2) {
/*  529 */       throw new P("Bad Pump State Reply", 2, O._B.E(this.Ƴ.a));
/*      */     }
/*      */ 
/*  535 */     if (this.ĵ != this.ȗ) {
/*  536 */       throw new P("Pump Alarm (state)", 2, Integer.toString(this.ĵ));
/*      */     }
/*      */ 
/*  542 */     this.ř.A();
/*      */     try {
/*  544 */       A(this.ř);
/*      */     } catch (Z localZ3) {
/*  546 */       throw new P("Bad Temp Basal Reply", 2, O._B.E(this.ř.a));
/*      */     }
/*      */ 
/*  552 */     if (this.Ɔ != 0) {
/*  553 */       throw new P("Pump Active (temp basal)", 3, "PUMP DELIVERING TEMPORARY BASAL");
/*      */     }
/*      */ 
/*  560 */     k();
/*  561 */     W().A();
/*      */   }
/*      */ 
/*      */   void k()
/*      */     throws t, P
/*      */   {
/*      */     boolean bool;
/*      */     try
/*      */     {
/*  579 */       bool = l();
/*      */     } catch (Z localZ) {
/*  581 */       throw new P("Bad Pump Active (bolus) Reply", 3, O._B.E(this.ƴ.a));
/*      */     }
/*      */ 
/*  586 */     if (bool) {
/*  587 */       throw new P("Pump Active (bolus)", 3, ɝ[12]);
/*      */     }
/*      */ 
/*  592 */     A(this, "initDevice2: ************************ DONE **************************");
/*      */   }
/*      */ 
/*      */   abstract boolean l()
/*      */     throws t, Z;
/*      */ 
/*      */   void e()
/*      */     throws t, IOException
/*      */   {
/*  613 */     if (W() != null) {
/*  614 */       if (this.Ń != null)
/*      */       {
/*  616 */         n();
/*  617 */         this.Ń.A();
/*      */       }
/*      */ 
/*  622 */       int[] arrayOfInt = new int[64];
/*  623 */       arrayOfInt[0] = 0;
/*  624 */       _C local_C = new _C(93, "Set RF Power Off", arrayOfInt, 2);
/*      */ 
/*  626 */       local_C.Y = 0;
/*      */       try
/*      */       {
/*  633 */         local_C.A();
/*      */       } catch (t localt) {
/*  635 */         A(this, "shutDownPump: ignoring BadDeviceCommException: " + localt);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   abstract void n()
/*      */     throws t, W;
/*      */ 
/*      */   static String J(int paramInt)
/*      */   {
/*      */     String str;
/*  659 */     if (paramInt <= ɝ.length - 1)
/*  660 */       str = ɝ[paramInt];
/*      */     else {
/*  662 */       str = "UNKNOWN NAK DESCRIPTION";
/*      */     }
/*  664 */     return str;
/*      */   }
/*      */ 
/*      */   int o()
/*      */   {
/*  673 */     return 6;
/*      */   }
/*      */ 
/*      */   int m()
/*      */   {
/*  682 */     return 8;
/*      */   }
/*      */ 
/*      */   void J(int[] paramArrayOfInt)
/*      */     throws Z
/*      */   {
/*  696 */     Contract.pre(paramArrayOfInt != null);
/*  697 */     Contract.pre(paramArrayOfInt.length == 64);
/*      */ 
/*  700 */     int i = paramArrayOfInt[5];
/*  701 */     O._B.A(i, 0.0D, B(25.0D), "Maximum Bolus Rate");
/*  702 */     this.ƺ = F(i);
/*      */   }
/*      */ 
/*      */   void A(M._A param_A)
/*      */     throws Z
/*      */   {
/*  716 */     switch (param_A.X) {
/*      */     case 113:
/*  718 */       A(param_A.a);
/*  719 */       break;
/*      */     case 116:
/*  721 */       B(param_A.a);
/*  722 */       break;
/*      */     case 112:
/*  724 */       K(param_A.a);
/*  725 */       break;
/*      */     case 127:
/*  727 */       I(param_A.a);
/*  728 */       break;
/*      */     case 120:
/*  730 */       H(param_A.a);
/*  731 */       break;
/*      */     case 121:
/*  733 */       C(param_A.a);
/*  734 */       break;
/*      */     case 114:
/*  736 */       L(param_A.a);
/*  737 */       break;
/*      */     case 115:
/*  739 */       D(param_A.a);
/*  740 */       break;
/*      */     case 117:
/*  742 */       F(param_A.a);
/*  743 */       break;
/*      */     case 131:
/*  745 */       E(param_A.a);
/*  746 */       break;
/*      */     case 118:
/*  748 */       G(param_A.a);
/*  749 */       break;
/*      */     case 119:
/*      */     case 122:
/*      */     case 123:
/*      */     case 124:
/*      */     case 125:
/*      */     case 126:
/*      */     case 128:
/*      */     case 129:
/*      */     case 130:
/*      */     }
/*      */   }
/*      */ 
/*      */   static void G(int paramInt) throws Z {
/*  763 */     O._B.A((paramInt == 1) || (paramInt == 0), "The Insulin Concentration value of " + paramInt + " is invalid; must be " + 1 + " or " + 0);
/*      */   }
/*      */ 
/*      */   private void K(int[] paramArrayOfInt)
/*      */     throws Z
/*      */   {
/*  785 */     Contract.pre(paramArrayOfInt != null);
/*  786 */     Contract.pre(paramArrayOfInt.length == 64);
/*      */ 
/*  790 */     int i = paramArrayOfInt[0];
/*  791 */     int j = paramArrayOfInt[1];
/*  792 */     int k = paramArrayOfInt[2];
/*  793 */     int m = O._B.B(paramArrayOfInt[3], paramArrayOfInt[4]);
/*      */ 
/*  795 */     int n = paramArrayOfInt[5];
/*  796 */     int i1 = paramArrayOfInt[6];
/*      */ 
/*  798 */     this.ĕ = A(i1, n, m, i, j, k);
/*      */ 
/*  807 */     A(this, "decodeCurrentTimeStamp: current time stamp for device is " + this.ĕ);
/*      */   }
/*      */ 
/*      */   void I(int[] paramArrayOfInt)
/*      */     throws Z
/*      */   {
/*  825 */     Contract.pre(paramArrayOfInt != null);
/*  826 */     Contract.pre(paramArrayOfInt.length == 64);
/*      */ 
/*  831 */     this.Ʊ = paramArrayOfInt[0];
/*  832 */     O._B.A(this.Ʊ, 0, 24, "Auto-off Duration");
/*      */ 
/*  839 */     int i = paramArrayOfInt[1];
/*  840 */     O._B.A(i, 1, 4, "Alert type-Beep volume");
/*      */ 
/*  846 */     if (i == 4) {
/*  847 */       this.ƍ = -1;
/*  848 */       this.Ǝ = 2;
/*      */     } else {
/*  850 */       this.ƍ = i;
/*  851 */       this.Ǝ = 1;
/*      */     }
/*      */ 
/*  855 */     i = paramArrayOfInt[2];
/*  856 */     O._B.A(i, 0, 1, "Easy Bolus Enable");
/*  857 */     this.Ɲ = (i == 1);
/*      */ 
/*  860 */     if (this.Ɲ) {
/*  861 */       j = paramArrayOfInt[3];
/*  862 */       O._B.A(j, 1, 20, "Easy (Audio) Bolus Step");
/*      */ 
/*  867 */       this.ŝ = F(j);
/*      */     } else {
/*  869 */       this.ŝ = 0.0D;
/*      */     }
/*      */ 
/*  873 */     i = paramArrayOfInt[4];
/*  874 */     O._B.A(i, 0, 1, "Variable Bolus Enable");
/*  875 */     this.ǂ = (i == 1);
/*      */ 
/*  878 */     J(paramArrayOfInt);
/*      */ 
/*  881 */     int j = O._B.B(paramArrayOfInt[o()], paramArrayOfInt[(o() + 1)]);
/*      */ 
/*  884 */     O._B.A(j, 0.0D, A(35.0D), "Maximum Basal Rate");
/*      */ 
/*  889 */     this.ĺ = H(j);
/*      */ 
/*  892 */     this.ƌ = paramArrayOfInt[m()];
/*  893 */     O._B.A(this.ƌ, 0, 1, "Time Display Format");
/*      */ 
/*  896 */     this.ǔ = paramArrayOfInt[9];
/*  897 */     G(this.ǔ);
/*  898 */     this.ǔ = I(this.ǔ);
/*      */ 
/*  901 */     i = paramArrayOfInt[10];
/*  902 */     O._B.A(i, 0, 1, "Basal Patterns Enable");
/*  903 */     this.Ž = (i == 1);
/*      */ 
/*  906 */     this.Ƒ = paramArrayOfInt[11];
/*  907 */     O._B.A(this.Ƒ, 0, 2, "Selected Pattern");
/*      */ 
/*  914 */     i = paramArrayOfInt[12];
/*  915 */     O._B.A(i, 0, 1, "RF Enable");
/*  916 */     this.Ō = (i == 1);
/*      */ 
/*  919 */     i = paramArrayOfInt[13];
/*  920 */     O._B.A(i, 0, 1, "Block Enable");
/*  921 */     this.ƹ = (i == 1);
/*      */ 
/*  923 */     A(this, "decodeCurrentSettings: *** CURRENT SETTINGS ***");
/*  924 */     A(this, "decodeCurrentSettings: Auto Off Dur = " + this.Ʊ);
/*      */ 
/*  927 */     if (this.ƍ != -1) {
/*  928 */       A(this, "decodeCurrentSettings: Beep Volume = " + this.ƍ);
/*      */     }
/*  930 */     A(this, "decodeCurrentSettings: Alarm Mode = " + this.Ǝ);
/*  931 */     A(this, "decodeCurrentSettings: Easy (Audio) Bolus On = " + this.Ɲ);
/*      */ 
/*  934 */     A(this, "decodeCurrentSettings: Easy (Audio) Bolus Step Size = " + this.ŝ);
/*      */ 
/*  937 */     A(this, "decodeCurrentSettings: Variable Bolus On = " + this.ǂ);
/*      */ 
/*  940 */     A(this, "decodeCurrentSettings: Max Bolus = " + this.ƺ);
/*  941 */     A(this, "decodeCurrentSettings: Max Basal Rate = " + this.ĺ);
/*  942 */     A(this, "decodeCurrentSettings: Time Format = " + (this.ƌ == 1 ? "24h" : "12h"));
/*      */ 
/*  946 */     A(this, "decodeCurrentSettings: Insulin Concen = " + this.ǔ);
/*  947 */     A(this, "decodeCurrentSettings: Pattern On = " + this.Ž);
/*  948 */     A(this, "decodeCurrentSettings: Current Basal Profile = " + A(new Integer(this.Ƒ)));
/*      */ 
/*  952 */     A(this, "decodeCurrentSettings: RF On = " + this.Ō);
/*  953 */     A(this, "decodeCurrentSettings: Block On = " + this.ƹ);
/*      */   }
/*      */ 
/*      */   private void H(int[] paramArrayOfInt)
/*      */     throws Z
/*      */   {
/*  967 */     Contract.pre(paramArrayOfInt != null);
/*  968 */     Contract.pre(paramArrayOfInt.length == 64);
/*      */ 
/*  974 */     int i = O._B.B(paramArrayOfInt[0], paramArrayOfInt[1]);
/*  975 */     O._B.A(i, 0.0D, A(35.0D), "Temporary Basal Rate");
/*      */ 
/*  980 */     this.Ǉ = H(i);
/*      */ 
/*  983 */     this.Ɔ = O._B.B(paramArrayOfInt[2], paramArrayOfInt[3]);
/*  984 */     O._B.A(this.Ɔ, 0, 1440, "Temporary Basal Duration");
/*      */ 
/*  990 */     A(this, "decodeTempBasal: Temp Basal Rate = " + this.Ǉ);
/*  991 */     A(this, "decodeTempBasal: Temp Basal Remain Dur = " + this.Ɔ + " minutes");
/*      */   }
/*      */ 
/*      */   private void F(int[] paramArrayOfInt)
/*      */     throws Z
/*      */   {
/* 1008 */     Contract.pre(paramArrayOfInt != null);
/* 1009 */     Contract.pre(paramArrayOfInt.length == 64);
/*      */ 
/* 1015 */     this.Ļ = paramArrayOfInt[0];
/* 1016 */     if (this.Ļ > 100) {
/* 1017 */       this.Ļ -= 100;
/*      */     }
/*      */ 
/* 1020 */     O._B.A(this.Ļ, 0, 67, "Error Status");
/*      */ 
/* 1026 */     A(this, "decodeErrorStatus: error code = " + this.Ļ);
/*      */   }
/*      */ 
/*      */   private void G(int[] paramArrayOfInt)
/*      */     throws Z
/*      */   {
/* 1040 */     Contract.pre(paramArrayOfInt != null);
/* 1041 */     Contract.pre(paramArrayOfInt.length == 64);
/*      */ 
/* 1046 */     int i = 0;
/* 1047 */     int[] arrayOfInt = new int[6];
/*      */ 
/* 1049 */     System.arraycopy(paramArrayOfInt, i, arrayOfInt, 0, arrayOfInt.length);
/* 1050 */     i += arrayOfInt.length;
/* 1051 */     this.Ŝ = O._B.E(arrayOfInt);
/*      */ 
/* 1054 */     System.arraycopy(paramArrayOfInt, i, arrayOfInt, 0, arrayOfInt.length);
/* 1055 */     i += arrayOfInt.length;
/* 1056 */     this.ś = O._B.E(arrayOfInt);
/*      */ 
/* 1059 */     System.arraycopy(paramArrayOfInt, i, arrayOfInt, 0, arrayOfInt.length);
/* 1060 */     i += arrayOfInt.length;
/* 1061 */     this.Ś = O._B.E(arrayOfInt);
/*      */ 
/* 1063 */     A(this, "decodeRemoteIds: id1 = " + this.Ŝ + " id2 = " + this.ś + " id3 = " + this.Ś);
/*      */   }
/*      */ 
/*      */   private void E(int[] paramArrayOfInt)
/*      */     throws Z
/*      */   {
/* 1084 */     Contract.pre(paramArrayOfInt != null);
/* 1085 */     Contract.pre(paramArrayOfInt.length == 64);
/*      */ 
/* 1090 */     this.ĵ = paramArrayOfInt[0];
/* 1091 */     O._B.A(this.ĵ, 0, 3, "Pump State");
/*      */ 
/* 1093 */     A(this, "decodePumpState: state = " + this.ĵ);
/*      */   }
/*      */ 
/*      */   private void D(int[] paramArrayOfInt)
/*      */     throws Z
/*      */   {
/* 1107 */     Contract.pre(paramArrayOfInt != null);
/* 1108 */     Contract.pre(paramArrayOfInt.length == 64);
/*      */ 
/* 1113 */     this.Ŭ = O._B.B(paramArrayOfInt[0], paramArrayOfInt[1]);
/* 1114 */     this.Ŭ /= 10;
/* 1115 */     O._B.A(this.Ŭ, 0, 310, "Remaining Insulin");
/*      */ 
/* 1121 */     A(this, "decodeRemainingInsulin: insulin = " + this.Ŭ);
/*      */   }
/*      */ 
/*      */   private void L(int[] paramArrayOfInt)
/*      */     throws Z
/*      */   {
/* 1135 */     Contract.pre(paramArrayOfInt != null);
/* 1136 */     Contract.pre(paramArrayOfInt.length == 64);
/*      */ 
/* 1141 */     this.ƣ = paramArrayOfInt[0];
/* 1142 */     O._B.A(this.ƣ, 0, 2, "Battery Status");
/*      */ 
/* 1147 */     String str = this.ƣ == 1 ? "Low" : this.ƣ == 0 ? "Normal" : "Off";
/*      */ 
/* 1149 */     double d = O._B.A(paramArrayOfInt[1], paramArrayOfInt[2]) / 100.0D;
/*      */ 
/* 1151 */     A(this, "decodeBatteryStatus: status = " + str + ", voltage = " + d + "V");
/*      */   }
/*      */ 
/*      */   private void C(int[] paramArrayOfInt)
/*      */   {
/* 1163 */     Contract.pre(paramArrayOfInt != null);
/* 1164 */     Contract.pre(paramArrayOfInt.length == 64);
/*      */ 
/* 1170 */     int i = O._B.B(paramArrayOfInt[0], paramArrayOfInt[1]);
/* 1171 */     this.Ǌ = F(i);
/*      */ 
/* 1174 */     i = O._B.B(paramArrayOfInt[2], paramArrayOfInt[3]);
/* 1175 */     this.Ƽ = F(i);
/*      */ 
/* 1177 */     A(this, "decodeTodaysDailyTotals: Today's Total Insulin = " + this.Ǌ);
/* 1178 */     A(this, "decodeTodaysDailyTotals: Yesterday's Total Insulin = " + this.Ƽ);
/*      */   }
/*      */ 
/*      */   private int I(int paramInt)
/*      */   {
/* 1190 */     return paramInt == 0 ? 100 : 50;
/*      */   }
/*      */ 
/*      */   void g()
/*      */     throws IOException, W, t
/*      */   {
/* 1639 */     F(this.u);
/*      */   }
/*      */ 
/*      */   void f()
/*      */     throws IOException
/*      */   {
/* 1648 */     j();
/*      */   }
/*      */ 
/*      */   private final class _A extends O._A
/*      */   {
/*      */     private static final int Đ = 1144;
/*      */     private static final int Č = 1;
/*      */     private static final int ė = 2;
/*      */     private static final int ċ = 3;
/*      */     private static final int ď = 4;
/*      */     private static final int č = 5;
/*      */     private static final int ē = 6;
/*      */     private static final int Ē = 7;
/*      */     private static final int Ď = 8;
/*      */     private static final int ĕ = 9;
/*      */     private static final int đ = 10;
/*      */     private static final int Ė = 11;
/*      */     private static final int Ĕ = 12;
/*      */ 
/*      */     _A()
/*      */     {
/* 1563 */       super(1144);
/* 1564 */       .A.this.e = 64;
/* 1565 */       .A.this.ì = 64;
/* 1566 */       .A.this.£ = 64;
/*      */     }
/*      */ 
/*      */     void A()
/*      */     {
/* 1576 */       .A.this.Î = new CA(.A.this.Þ, 1, .A.this.Ű.a, .A.this.ƛ.a, .A.this.ń.a);
/*      */ 
/* 1584 */       O.A(this, "createSnapshot: creating snapshot");
/*      */ 
/* 1587 */       .A.this.Î.A(1, .A.this.ǜ.a);
/*      */ 
/* 1591 */       .A.this.Î.A(2, .A.this.Ɗ.a);
/*      */ 
/* 1595 */       .A.this.Î.A(3, .A.this.ř.a);
/*      */ 
/* 1599 */       .A.this.Î.A(4, .A.this.ƈ.a);
/*      */ 
/* 1602 */       .A.this.Î.A(5, .A.this.Ƈ.a);
/*      */ 
/* 1606 */       .A.this.Î.A(6, .A.this.Ų.a);
/*      */ 
/* 1609 */       .A.this.Î.A(7, .A.this.ű.a);
/*      */ 
/* 1612 */       .A.this.Î.A(8, .A.this.ĳ.a);
/*      */ 
/* 1615 */       .A.this.Î.A(9, .A.this.Ʒ.a);
/*      */ 
/* 1619 */       .A.this.Î.A(10, .A.this.ş.a);
/*      */ 
/* 1622 */       .A.this.Î.A(11, .A.this.ǅ.a);
/*      */ 
/* 1625 */       .A.this.Î.A(12, .A.this.Ƴ.a);
/*      */     }
/*      */   }
/*      */ 
/*      */   class _B extends .A._C
/*      */   {
/*      */     .A._C g;
/*      */ 
/*      */     _B()
/*      */     {
/* 1360 */       this(32);
/*      */     }
/*      */ 
/*      */     _B(int arg2)
/*      */     {
/* 1370 */       this(128, "Read History Data", 1024, i);
/*      */     }
/*      */ 
/*      */     _B(int paramString, String paramInt1, int paramInt2, int arg5)
/*      */     {
/* 1383 */       super(paramString, paramInt1, paramInt2, i, 0);
/*      */ 
/* 1386 */       int[] arrayOfInt = new int[64];
/* 1387 */       arrayOfInt[0] = 0;
/* 1388 */       this.g = new .A._C(.A.this, paramString, paramInt1 + "(Page)", arrayOfInt, 1);
/*      */ 
/* 1391 */       this.g.E = paramInt2;
/* 1392 */       this.g.Q = 1;
/*      */     }
/*      */ 
/*      */     void A(int paramInt1, int paramInt2)
/*      */       throws t, W
/*      */     {
/* 1415 */       Contract.pre(paramInt1 >= 0);
/* 1416 */       Contract.pre(paramInt2 >= 0);
/* 1417 */       boolean bool = false;
/* 1418 */       this.a = new int[0];
/*      */       int i;
/*      */       long l;
/* 1421 */       if (paramInt1 <= paramInt2)
/*      */       {
/* 1423 */         for (i = paramInt1; (i <= paramInt2) && (!bool); i++) {
/* 1424 */           O.C(this, "execute: (" + this.A + ") reading page " + i + " (reading up to page " + paramInt2 + ")");
/*      */ 
/* 1426 */           l = O._B.A();
/* 1427 */           bool = C(i);
/* 1428 */           O.C(this, "execute: (" + this.A + ") read page " + i + " in " + O._B.A(l) + "MS");
/*      */         }
/*      */       }
/*      */       else
/*      */       {
/* 1433 */         for (i = paramInt1; (i >= paramInt2) && (!bool); i--) {
/* 1434 */           O.C(this, "execute: (" + this.A + ") reading page " + i + " (reading down to page " + paramInt2 + ")");
/*      */ 
/* 1436 */           l = O._B.A();
/* 1437 */           bool = C(i);
/* 1438 */           O.C(this, "execute: (" + this.A + ") read page " + i + " in " + O._B.A(l) + "MS");
/*      */         }
/*      */       }
/*      */ 
/* 1442 */       O.C(this, "execute: " + this.A + " returned " + this.a.length + " bytes");
/*      */     }
/*      */ 
/*      */     private boolean C(int paramInt)
/*      */       throws W, t
/*      */     {
/* 1460 */       int i = 0;
/* 1461 */       int j = 0;
/* 1462 */       B(paramInt);
/*      */       do {
/*      */         try
/*      */         {
/* 1466 */           this.g.A();
/* 1467 */           i = 1;
/*      */         } catch (t localt) {
/* 1469 */           j++;
/* 1470 */           if (j <= this.Y) {
/* 1471 */             O.D(this, "readPage: re-reading history data page " + paramInt + " (attempts = " + (j + 1) + ")");
/*      */             try
/*      */             {
/* 1474 */               .A.this.W().D();
/*      */             } catch (IOException localIOException) {
/*      */             }
/*      */           }
/*      */           else {
/* 1479 */             O.E(this, "page " + paramInt + " failed for " + this.A + " after " + j + " attempts" + "; exception = " + localt);
/*      */ 
/* 1490 */             throw new t("readPage: page " + paramInt + " failed for " + this.A + " after " + j + " attempts", localt.A(), localt.B());
/*      */           }
/*      */         }
/*      */       }
/*      */ 
/* 1495 */       while ((i == 0) && (j <= this.Y) && (!.A.this.isHaltRequested()));
/*      */ 
/* 1498 */       int[] arrayOfInt = this.g.a;
/* 1499 */       int k = (arrayOfInt.length == 0) || (.A.this.isHaltRequested()) ? 1 : 0;
/*      */ 
/* 1501 */       if (arrayOfInt.length > 0) {
/* 1502 */         this.a = O._B.A(this.a, arrayOfInt);
/*      */       }
/* 1504 */       return k;
/*      */     }
/*      */ 
/*      */     void B(int paramInt)
/*      */     {
/* 1513 */       this.g.O[0] = paramInt;
/*      */     }
/*      */ 
/*      */     void A()
/*      */       throws t
/*      */     {
/* 1527 */       A(0, this.Q - 1);
/*      */     }
/*      */   }
/*      */ 
/*      */   class _C extends M._A
/*      */   {
/*      */     _C(int paramString, String arg3)
/*      */     {
/* 1221 */       super(paramString, str, 64, 1, 0);
/*      */     }
/*      */ 
/*      */     _C(int paramString, String paramArrayOfInt, int[] paramInt1, int arg5)
/*      */     {
/* 1239 */       super(paramString, paramArrayOfInt, 0, 1, 11);
/* 1240 */       this.O = paramInt1;
/*      */       int i;
/* 1241 */       this.I = i;
/*      */     }
/*      */ 
/*      */     _C(int paramString, String paramInt1, int paramInt2, int paramInt3, int arg6)
/*      */     {
/* 1259 */       super(paramString, paramInt1, paramInt2, paramInt3, i);
/*      */     }
/*      */ 
/*      */     _C(int paramString, String paramInt1, int arg4)
/*      */     {
/* 1272 */       super(paramString, paramInt1, 0, 1, 11);
/*      */       int i;
/* 1273 */       this.I = i;
/* 1274 */       int j = i / 64 + 1;
/* 1275 */       this.O = new int[64 * j];
/*      */     }
/*      */ 
/*      */     private _C()
/*      */     {
/* 1282 */       super(0, "(empty command)", 64, 1, 0);
/*      */     }
/*      */ 
/*      */     void A()
/*      */       throws t, W
/*      */     {
/* 1296 */       int i = 0;
/* 1297 */       int j = 0;
/*      */       do
/*      */       {
/*      */         try
/*      */         {
/* 1302 */           O.C(this, "execute: cmd=" + this.A + ", m_maxRetries=" + this.Y + ", attempts=" + j);
/*      */ 
/* 1306 */           .A.this.W().A(.A.this.Ź);
/*      */           try {
/* 1308 */             .A.this.W().A(this);
/*      */           }
/*      */           catch (Z localZ) {
/* 1311 */             localZ.printStackTrace();
/*      */           }
/* 1313 */           i = 1;
/*      */         } catch (t localt) {
/* 1315 */           j++;
/* 1316 */           if (j <= this.Y) {
/* 1317 */             O.D(this, "execute: cmd failed with exception: " + localt + "; retrying (attempts = " + (j + 1) + ")");
/*      */ 
/* 1319 */             .A.this.B(7);
/*      */             try {
/* 1321 */               .A.this.W().D();
/*      */             } catch (IOException localIOException) {
/*      */             }
/*      */           }
/*      */           else {
/* 1326 */             O.E(this, "cmd " + O._B.H(this.X) + " (" + this.A + ") failed after " + j + " attempts" + "; exception = " + localt);
/*      */ 
/* 1330 */             throw new t("execute: cmd " + O._B.H(this.X) + " (" + this.A + ") failed after " + j + " attempts", localt.A(), localt.B());
/*      */           }
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/* 1336 */       while ((i == 0) && (j <= this.Y) && (!.A.this.isHaltRequested()));
/*      */     }
/*      */   }
/*      */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.A..A
 * JD-Core Version:    0.6.0
 */