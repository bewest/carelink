/*      */ package minimed.ddms.A;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.text.ParsePosition;
/*      */ import java.text.SimpleDateFormat;
/*      */ import java.util.Date;
/*      */ import java.util.HashSet;
/*      */ import java.util.NoSuchElementException;
/*      */ import java.util.Set;
/*      */ import java.util.StringTokenizer;
/*      */ import java.util.Vector;
/*      */ import minimed.util.Contract;
/*      */ 
/*      */ abstract class Q extends O
/*      */ {
/*      */   public static final int ϳ = 140;
/*   39 */   static final String Ѕ = String.valueOf('\006');
/*      */   static final String ϧ = "\rR|";
/*      */   static final String Ϧ = "C|";
/*   42 */   static final String Є = '\r' + String.valueOf('\004');
/*      */   static final int Ј = 1;
/*      */   static final int ϣ = 7;
/*      */   static final int Ї = 5000;
/*      */   static final String Ϛ = "|P|1|";
/*      */   static final String Ѓ = "|\\^&\r\n";
/*      */   private static final String Љ = "X";
/*      */   private static final int І = 10;
/*      */   private static final int Ϯ = 2500;
/*      */   static final String ϥ = "H";
/*      */   _B ϭ;
/*      */   private _C ϱ;
/*      */   private _C Ϫ;
/*      */   private _C Ϣ;
/*      */   private int ϩ;
/*      */   private int Ϡ;
/*      */   String Ϥ;
/*      */   Set ϰ;
/*      */   Boolean Ђ;
/*      */   Boolean Ϩ;
/*      */   Boolean ϲ;
/*      */   Boolean Ϭ;
/*      */   Boolean Ϝ;
/*      */   Boolean Ϟ;
/*      */   private int ϯ;
/*      */   private String Ё;
/*      */   String ϫ;
/*      */ 
/*      */   Q(int paramInt)
/*      */   {
/*  139 */     this.Ă = new String("x.xx");
/*  140 */     this.ă = new String("xxxx-xxxxxxx");
/*  141 */     this.Þ = 140;
/*  142 */     this.Ė = new _A();
/*  143 */     this.ϯ = paramInt;
/*  144 */     this.ϰ = new HashSet();
/*      */ 
/*  147 */     y();
/*      */   }
/*      */ 
/*      */   public void A(v paramv, String paramString1, String paramString2)
/*      */     throws t, Z, IOException
/*      */   {
/*  169 */     A(this, "readData: starting reader...");
/*  170 */     B(false);
/*      */ 
/*  173 */     Vector localVector = w();
/*      */ 
/*  176 */     _D local_D = new _D(paramv, paramString1, localVector, null);
/*  177 */     _D.A(local_D);
/*      */   }
/*      */ 
/*      */   int Z()
/*      */   {
/*  186 */     return 3;
/*      */   }
/*      */ 
/*      */   void D(String paramString)
/*      */     throws IOException
/*      */   {
/*  197 */     C(paramString);
/*  198 */     A(new d(paramString, 7));
/*  199 */     V().A();
/*  200 */     V().A(10);
/*  201 */     V().B(2500);
/*      */   }
/*      */ 
/*      */   void _()
/*      */     throws IOException
/*      */   {
/*  210 */     if (V() != null)
/*      */     {
/*  212 */       V().B();
/*  213 */       A(null);
/*      */     }
/*      */   }
/*      */ 
/*      */   void C(v paramv)
/*      */     throws t, IOException
/*      */   {
/*      */     try
/*      */     {
/*  226 */       z();
/*      */     }
/*      */     catch (Z localZ) {
/*  229 */       throw new t("Got reply, but format is bad.");
/*      */     }
/*      */   }
/*      */ 
/*      */   void y()
/*      */   {
/*  237 */     this.ϱ = new _C("\rR|", "Read Current Settings", 1, null);
/*      */ 
/*  239 */     this.Ϫ = new _C("C|", "Read Current Settings Data", 7, null);
/*      */ 
/*  241 */     this.Ϫ.B(false);
/*  242 */     this.Ϣ = new _C(String.valueOf('\005'), "Begin Remote Commands", 1, null);
/*  243 */     this.ϭ = new _B();
/*      */   }
/*      */ 
/*      */   Vector w()
/*      */   {
/*  252 */     Vector localVector = new Vector();
/*      */ 
/*  255 */     localVector.addElement(this.ϭ);
/*  256 */     localVector.addElement(this.Ϣ);
/*  257 */     localVector.addElement(this.ϱ);
/*  258 */     localVector.addElement(this.Ϫ);
/*  259 */     return localVector;
/*      */   }
/*      */ 
/*      */   void L(int paramInt)
/*      */   {
/*  267 */     this.Î.A(paramInt, this.Ϥ);
/*      */   }
/*      */ 
/*      */   void ¤()
/*      */     throws Z
/*      */   {
/*      */   }
/*      */ 
/*      */   void M(int paramInt)
/*      */   {
/*  286 */     this.ϯ = paramInt;
/*      */ 
/*  288 */     this.ϩ = new _B().v.length;
/*      */   }
/*      */ 
/*      */   abstract void ¢()
/*      */     throws Z;
/*      */ 
/*      */   boolean M(String paramString)
/*      */   {
/*  307 */     return this.ϰ.contains(paramString);
/*      */   }
/*      */ 
/*      */   void ¥()
/*      */     throws t, Z
/*      */   {
/*  317 */     V().E();
/*      */ 
/*  319 */     E(4);
/*  320 */     z();
/*      */   }
/*      */ 
/*      */   private void z()
/*      */     throws t, Z
/*      */   {
/*  331 */     O._B.G(5000);
/*  332 */     _C local_C = new _C("X", "Initialize Communications", 2, null);
/*      */ 
/*  334 */     local_C.B(false);
/*  335 */     local_C.A();
/*      */ 
/*  338 */     int i = local_C.v.length;
/*  339 */     if (i < 2) {
/*  340 */       throw new Z("initCommunicationIO: got bad reply");
/*      */     }
/*  342 */     if ((local_C.v[(i - 2)] != 4) || (local_C.v[(i - 1)] != 5))
/*      */     {
/*  344 */       throw new Z("initCommunicationIO: got bad reply");
/*      */     }
/*      */   }
/*      */ 
/*      */   private final void v()
/*      */     throws t, Z
/*      */   {
/*  357 */     if (V() != null)
/*      */     {
/*  359 */       _C local_C = new _C(Є, "End Session", 0, null);
/*  360 */       local_C.B(false);
/*  361 */       local_C.A();
/*      */     }
/*      */   }
/*      */ 
/*      */   private final void A(_C param_C)
/*      */     throws Z
/*      */   {
/*  376 */     String str = O._B.E(param_C.v);
/*      */ 
/*  378 */     if ((this.Ϫ != null) && (param_C.t.equals(this.Ϫ.t)))
/*      */     {
/*  381 */       this.Ϥ = str;
/*  382 */       A(this, "decodeReply: current settings is '" + this.Ϥ + "'");
/*      */ 
/*  384 */       ¢();
/*      */     }
/*  386 */     else if (param_C.t.equals("X")) {
/*  387 */       A(this, "decodeReply: reply is initialization: '" + str + "'");
/*      */     }
/*      */   }
/*      */ 
/*      */   void L(String paramString)
/*      */     throws Z
/*      */   {
/*  405 */     StringTokenizer localStringTokenizer = null;
/*  406 */     String str1 = null;
/*      */ 
/*  408 */     String str3 = "1";
/*      */ 
/*  410 */     Contract.pre(paramString != null);
/*      */     try
/*      */     {
/*  417 */       A(this, "decodeDataUploadHeader: parsing dataUpload header: '" + paramString + '\'');
/*      */ 
/*  420 */       localStringTokenizer = new StringTokenizer(paramString, "|\\^&\r\n");
/*      */ 
/*  424 */       str1 = localStringTokenizer.nextToken();
/*  425 */       if (!str1.startsWith(str3 + "H")) {
/*  426 */         throw new Z("Bad header record type (last token='" + str1 + "')");
/*      */       }
/*      */ 
/*  431 */       str1 = localStringTokenizer.nextToken();
/*  432 */       String str2 = localStringTokenizer.nextToken();
/*      */ 
/*  434 */       if (!M(str2)) {
/*  435 */         throw new Z("Product code '" + str2 + "' not found in list '" + this.ϰ + "' (last token='" + str1 + "')");
/*      */       }
/*      */ 
/*  441 */       this.Ă = localStringTokenizer.nextToken();
/*      */ 
/*  444 */       str1 = localStringTokenizer.nextToken();
/*  445 */       this.ă = localStringTokenizer.nextToken();
/*      */ 
/*  447 */       A(this, "decodeDataUploadHeader: current firmware version for device is " + this.Ă);
/*      */ 
/*  449 */       A(this, "decodeDataUploadHeader: current serial number for device is " + this.ă);
/*      */ 
/*  453 */       str1 = localStringTokenizer.nextToken();
/*  454 */       str1 = localStringTokenizer.nextToken();
/*  455 */       this.ϫ = localStringTokenizer.nextToken();
/*  456 */       £();
/*      */     } catch (NoSuchElementException localNoSuchElementException) {
/*  458 */       throw new Z("Bad dataUpload header field '" + paramString + "' received (last token='" + str1 + "')");
/*      */     }
/*      */     catch (NumberFormatException localNumberFormatException)
/*      */     {
/*  462 */       throw new Z("Bad dataUpload header field number '" + paramString + "' received (last token='" + str1 + "')");
/*      */     }
/*      */   }
/*      */ 
/*      */   void £()
/*      */     throws Z
/*      */   {
/*  478 */     this.ĕ = K(this.ϫ);
/*  479 */     A(this, "decodeCurrentTimeStamp: current time stamp for device is " + this.ĕ);
/*      */   }
/*      */ 
/*      */   private final Date K(String paramString)
/*      */     throws Z
/*      */   {
/*  497 */     String str = new String("yyyyMMddHHmm");
/*      */ 
/*  499 */     Contract.pre(paramString != null);
/*  500 */     Contract.pre(paramString.length() == str.length());
/*      */ 
/*  502 */     SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(str);
/*  503 */     Date localDate = localSimpleDateFormat.parse(paramString, new ParsePosition(0));
/*      */ 
/*  505 */     if (localDate == null)
/*      */     {
/*  507 */       throw new Z("Bad device time string (null returned) '" + paramString + "' received");
/*      */     }
/*      */ 
/*  511 */     return localDate;
/*      */   }
/*      */ 
/*      */   protected void A(long paramLong)
/*      */   {
/*      */     try
/*      */     {
/*  524 */       V().B((int)paramLong);
/*      */     }
/*      */     catch (IOException localIOException)
/*      */     {
/*      */     }
/*      */   }
/*      */ 
/*      */   protected long x()
/*      */   {
/*      */     try
/*      */     {
/*  540 */       return V().D(); } catch (IOException localIOException) {
/*      */     }
/*  542 */     return 10L;
/*      */   }
/*      */ 
/*      */   protected class _A extends O._A
/*      */   {
/*      */     static final int h = 1;
/*      */     static final int g = 2;
/*      */     static final int k = 80;
/*      */     static final int i = 2;
/*      */     int j;
/*      */ 
/*      */     _A()
/*      */     {
/* 1226 */       this(1);
/*      */     }
/*      */ 
/*      */     _A(int arg2)
/*      */     {
/* 1236 */       super(80);
/*      */       int m;
/* 1237 */       this.j = m;
/* 1238 */       Q.this.e = 0;
/* 1239 */       Q.this.ì = 0;
/* 1240 */       Q.this.£ = 0;
/*      */     }
/*      */ 
/*      */     void A()
/*      */     {
/* 1250 */       String str1 = "";
/* 1251 */       String str2 = "";
/* 1252 */       String str3 = "";
/* 1253 */       Q.this.Î = new CA(Q.this.Þ, 1, str1, str2, str3);
/*      */ 
/* 1256 */       O.A(this, "createSnapshot: creating snapshot");
/*      */ 
/* 1260 */       Q.this.Î.A(this.j, Q.E(Q.this));
/*      */ 
/* 1264 */       Q.this.L(2);
/*      */     }
/*      */   }
/*      */ 
/*      */   final class _B extends Q._C
/*      */   {
/*      */     private static final int y = 300;
/*      */     private static final int x = 55;
/*      */     private static final int w = 10000;
/*      */     static final int ¢ = 10;
/*      */     private Q._C z;
/*      */ 
/*      */     _B()
/*      */     {
/* 1021 */       super(Q.Ѕ, "Read Data Records", 300 + Q.C(Q.this) * 55, null);
/*      */     }
/*      */ 
/*      */     void A()
/*      */       throws t, Z
/*      */     {
/* 1040 */       int i = 0;
/* 1041 */       int j = 0;
/* 1042 */       this.v = new int[0];
/*      */ 
/* 1044 */       while (i == 0) {
/* 1045 */         O.A(this, "execute: ****** reading record " + j + " ******");
/* 1046 */         int[] arrayOfInt = new int[0];
/* 1047 */         if (j == 0)
/*      */         {
/* 1050 */           int k = 0;
/* 1051 */           int m = 0;
/*      */           try {
/* 1053 */             k = Q.this.V().D();
/* 1054 */             m = 1;
/* 1055 */             Q.this.V().B(10000);
/*      */ 
/* 1058 */             arrayOfInt = R();
/*      */ 
/* 1061 */             Q.this.L(O._B.E(arrayOfInt).trim());
/*      */ 
/* 1064 */             Q.this.¤();
/*      */           } catch (IOException localIOException3) {
/*      */           }
/*      */           finally {
/* 1068 */             if (m != 0)
/*      */               try
/*      */               {
/* 1071 */                 Q.this.V().B(k);
/*      */               }
/*      */               catch (IOException localIOException4) {
/*      */               }
/*      */           }
/*      */         }
/*      */         else {
/* 1078 */           arrayOfInt = R();
/*      */         }
/*      */ 
/* 1082 */         String str = O._B.E(arrayOfInt);
/* 1083 */         i = str.indexOf(4) >= 0 ? 1 : 0;
/*      */ 
/* 1085 */         j++;
/*      */       }
/* 1087 */       Q.A(Q.this, O._B.E(this.v).trim());
/*      */     }
/*      */ 
/*      */     private int[] R()
/*      */       throws t, Z
/*      */     {
/* 1100 */       int[] arrayOfInt = new int[0];
/*      */ 
/* 1102 */       Q.this.V().E();
/*      */       try
/*      */       {
/* 1107 */         arrayOfInt = P();
/*      */       } catch (t localt) {
/* 1109 */         Q.this.V().A();
/* 1110 */         throw localt;
/*      */       } catch (Z localZ) {
/* 1112 */         Q.this.V().A();
/* 1113 */         throw localZ;
/*      */       }
/*      */ 
/* 1116 */       this.v = O._B.A(this.v, arrayOfInt);
/* 1117 */       return arrayOfInt;
/*      */     }
/*      */ 
/*      */     private String Q()
/*      */       throws t, Z
/*      */     {
/* 1131 */       String str = "";
/*      */ 
/* 1133 */       Q.this.V().E();
/*      */ 
/* 1135 */       O.A(this, "readDeviceData: reading reply to cmd " + this.t + " (" + this.A + ")");
/*      */ 
/* 1138 */       long l = Q.this.x();
/*      */       try
/*      */       {
/* 1141 */         int i = 1;
/* 1142 */         while ((str.indexOf('\n') < 0) && (str.indexOf(4) < 0) && (i <= 10)) {
/* 1143 */           Q.this.A(l * i);
/* 1144 */           O.A(this, "Reading Buffer(" + str + ") - Attempt # " + i);
/* 1145 */           str = str.concat(Q.this.V().C());
/* 1146 */           i++;
/*      */         }
/*      */       } catch (IOException localIOException) {
/* 1149 */         throw new t("readDeviceData: ERROR - an IOException  has occurred processing cmd " + this.t + " (" + this.A + ")");
/*      */       }
/*      */       finally
/*      */       {
/* 1153 */         Q.this.A(l);
/*      */       }
/*      */ 
/* 1157 */       C(str);
/*      */ 
/* 1159 */       return str;
/*      */     }
/*      */ 
/*      */     private int[] P()
/*      */       throws t, Z
/*      */     {
/* 1174 */       int[] arrayOfInt = new int[0];
/*      */ 
/* 1178 */       if (Q.this.T() != 7) {
/* 1179 */         Q.this.B(4);
/*      */       }
/* 1181 */       N();
/*      */ 
/* 1183 */       if (!Q.this.isHaltRequested())
/*      */       {
/* 1185 */         Q.this.B(5);
/* 1186 */         arrayOfInt = O._B.D(Q());
/*      */       }
/*      */ 
/* 1189 */       Q.D(Q.this, arrayOfInt.length);
/*      */ 
/* 1191 */       Q.this.A(Q.D(Q.this), Q.B(Q.this));
/*      */ 
/* 1193 */       if (!Q.this.isHaltRequested()) {
/* 1194 */         Q.A(Q.this, this);
/*      */       }
/*      */ 
/* 1197 */       return arrayOfInt;
/*      */     }
/*      */   }
/*      */ 
/*      */   private final class _D
/*      */   {
/*      */     private String B;
/*      */     private Vector C;
/*      */ 
/*      */     private _D(v paramString, String paramVector, Vector arg4)
/*      */     {
/*  855 */       Q.this.B(paramString);
/*  856 */       this.B = paramVector;
/*      */       Object localObject;
/*  857 */       this.C = localObject;
/*      */     }
/*      */ 
/*      */     private final void B()
/*      */       throws t, Z, IOException
/*      */     {
/*  873 */       int i = 0;
/*      */ 
/*  875 */       Vector localVector = new Vector();
/*      */ 
/*  879 */       Q.this.Ă = "";
/*  880 */       Q.this.ă = "";
/*  881 */       Q.this.ϫ = "";
/*      */ 
/*  885 */       Q.A(Q.this, 0);
/*  886 */       Q.B(Q.this, 0);
/*      */       Q._C local_C;
/*  887 */       for (int j = 0; j < this.C.size(); j++) {
/*  888 */         local_C = (Q._C)this.C.elementAt(j);
/*  889 */         if (local_C != null) {
/*  890 */           Q.C(Q.this, local_C.v.length);
/*      */         }
/*      */       }
/*      */ 
/*  894 */       Q.this.C(0);
/*      */       try
/*      */       {
/*  902 */         Q.this.B(2);
/*      */         try
/*      */         {
/*  905 */           A(this.B);
/*      */         } catch (IOException localIOException) {
/*  907 */           if (!Q.this.isHaltRequested()) {
/*  908 */             O.D(this, "acquireDataFromDevice: initCommunications failed with IOException; retrying... (exception = " + localIOException + ")");
/*      */ 
/*  911 */             A(this.B);
/*      */           }
/*      */         }
/*      */ 
/*  915 */         Q.this.E(5);
/*      */ 
/*  918 */         for (i = 0; (i < this.C.size()) && (!Q.this.isHaltRequested()); )
/*      */         {
/*  920 */           local_C = (Q._C)this.C.elementAt(i);
/*  921 */           if (local_C != null)
/*      */           {
/*  923 */             local_C.A();
/*  924 */             if (!Q.this.isHaltRequested())
/*      */             {
/*  926 */               int[] arrayOfInt = Q._C.A(local_C);
/*  927 */               localVector.addElement(arrayOfInt);
/*      */             }
/*      */           }
/*  919 */           i++;
/*      */         }
/*      */ 
/*      */       }
/*      */       finally
/*      */       {
/*      */         try
/*      */         {
/*  938 */           O.A(this, "run: shutting down communications...");
/*  939 */           Q.this.E(6);
/*  940 */           A();
/*      */         } finally {
/*  942 */           O.A(this, "run: done!  Number of commands processed: " + i);
/*      */ 
/*  945 */           if (Q.this.isHaltRequested())
/*  946 */             Q.this.B(9);
/*      */           else {
/*  948 */             Q.this.B(1);
/*      */           }
/*      */ 
/*  951 */           Q.this.U();
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/*      */     private final void A(String paramString)
/*      */       throws IOException, t, Z
/*      */     {
/*  968 */       Q.this.D(paramString);
/*      */ 
/*  971 */       Q.this.¥();
/*      */     }
/*      */ 
/*      */     private final void A()
/*      */       throws Z, t, IOException
/*      */     {
/*      */       try
/*      */       {
/*  985 */         O.A(this, "endCommunications: shutting down meter communications.");
/*  986 */         Q.A(Q.this);
/*      */       } finally {
/*  988 */         O.A(this, "endCommunications: shutting down serial port.");
/*  989 */         Q.this._();
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private class _C extends n
/*      */   {
/*      */     static final byte q = -1;
/*      */     static final int s = 2;
/*      */     static final int u = 2;
/*      */     static final int p = 100;
/*      */     int[] v;
/*      */     String t;
/*      */     private boolean r;
/*      */ 
/*      */     private _C(String paramString1, String paramInt, int arg4)
/*      */     {
/*  582 */       super();
/*  583 */       this.t = paramString1;
/*      */       int i;
/*  584 */       this.v = new int[i];
/*  585 */       this.r = true;
/*      */     }
/*      */ 
/*      */     void A()
/*      */       throws t, Z
/*      */     {
/*  599 */       int i = 0;
/*  600 */       int j = 0;
/*      */ 
/*  602 */       Q.this.V().E();
/*      */ 
/*  604 */       O.Ã = this.A;
/*      */       do
/*      */       {
/*      */         try
/*      */         {
/*  610 */           M();
/*  611 */           j = 1;
/*      */         } catch (t localt) {
/*  613 */           i++;
/*  614 */           Q.this.V().A();
/*  615 */           if (i <= 2) {
/*  616 */             O.D(this, "execute: cmd failed with exception: " + localt + "; retrying (attempts = " + (i + 1) + ")");
/*      */ 
/*  618 */             Q.this.B(7);
/*      */           } else {
/*  620 */             O.E(this, "cmd " + this.t + " (" + this.A + ") failed after " + i + " attempts" + "; exception = " + localt);
/*      */ 
/*  627 */             throw new t("execute: cmd " + this.t + " (" + this.A + ") failed after " + i + " attempts");
/*      */           }
/*      */         }
/*      */         catch (Z localZ)
/*      */         {
/*  632 */           i++;
/*  633 */           Q.this.V().A();
/*  634 */           if (i <= 2) {
/*  635 */             O.D(this, "execute: cmd failed with exception: " + localZ + "; retrying (attempts = " + (i + 1) + ")");
/*      */ 
/*  637 */             Q.this.B(7);
/*      */           } else {
/*  639 */             O.E(this, "cmd " + this.t + " (" + this.A + ") failed after " + i + " attempts" + "; exception = " + localZ);
/*      */ 
/*  646 */             throw new Z("execute: cmd " + this.t + " (" + this.A + ") failed after " + i + " attempts");
/*      */           }
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*  652 */       while ((j == 0) && (i <= 2));
/*      */     }
/*      */ 
/*      */     private final int[] O()
/*      */     {
/*  661 */       return this.v;
/*      */     }
/*      */ 
/*      */     protected final void C(String paramString)
/*      */       throws t, Z
/*      */     {
/*  674 */       if (paramString.length() == 0)
/*      */       {
/*  676 */         throw new t("readDeviceData: ERROR - no reply for cmd " + this.t + " (" + this.A + ")");
/*      */       }
/*      */ 
/*  679 */       if (paramString.length() == 1)
/*      */       {
/*  681 */         O.A(this, "readDeviceData: control code received: " + O._B.E(paramString));
/*      */       }
/*      */       else {
/*  684 */         if (L())
/*      */         {
/*  686 */           boolean bool = false;
/*  687 */           if (paramString.length() > 3) {
/*  688 */             bool = O._B.C(paramString);
/*      */           }
/*  690 */           if (!bool) {
/*  691 */             O.E(this, "readDeviceData: ERROR - cmd " + this.t + " resulted in bad CRC reply of " + O._B.E(paramString));
/*      */ 
/*  694 */             throw new Z("readDeviceData: ERROR - cmd " + this.t + " resulted in bad CRC reply of " + O._B.E(paramString));
/*      */           }
/*      */ 
/*      */         }
/*      */ 
/*  700 */         O.A(this, "readDeviceData: cmd " + this.t + " (" + this.A + ") returned " + paramString.length() + " data bytes, with reply = <" + paramString + ">");
/*      */       }
/*      */     }
/*      */ 
/*      */     private final String K() throws t, Z
/*      */     {
/*  719 */       Q.this.V().E();
/*      */ 
/*  721 */       O.A(this, "readDeviceData: reading reply to cmd " + this.t + " (" + this.A + ")");
/*      */       String str;
/*      */       try {
/*  726 */         str = Q.this.V().C();
/*      */       } catch (IOException localIOException) {
/*  728 */         throw new t("readDeviceData: ERROR - an IOException  has occurred processing cmd " + this.t + " (" + this.A + ")");
/*      */       }
/*      */ 
/*  734 */       C(str);
/*      */ 
/*  736 */       return str;
/*      */     }
/*      */ 
/*      */     protected final void N()
/*      */       throws t
/*      */     {
/*  747 */       Q.this.V().E();
/*      */ 
/*  749 */       O.A(this, "sendCommand: sending cmd " + this.t + " (" + this.A + ")");
/*      */       try
/*      */       {
/*  759 */         Q.this.V().A(this.t);
/*      */       }
/*      */       catch (IOException localIOException) {
/*  762 */         throw new t("sendCommand: ERROR - an IOException  has occurred processing cmd " + this.t + " (" + this.A + ")");
/*      */       }
/*      */     }
/*      */ 
/*      */     private final void M()
/*      */       throws t, Z
/*      */     {
/*  781 */       if (Q.this.T() != 7) {
/*  782 */         Q.this.B(4);
/*      */       }
/*  784 */       N();
/*      */ 
/*  786 */       if (this.v.length > 0)
/*      */       {
/*  788 */         if (!Q.this.isHaltRequested())
/*      */         {
/*  790 */           Q.this.B(5);
/*  791 */           this.v = O._B.D(K());
/*      */         }
/*      */ 
/*  795 */         if ((this.v.length == 0) && (!Q.this.isHaltRequested()))
/*      */         {
/*  797 */           O._B.G(100);
/*  798 */           this.v = O._B.D(K());
/*      */         }
/*      */ 
/*  801 */         Q.D(Q.this, this.v.length);
/*      */ 
/*  804 */         Q.this.A(Q.D(Q.this), Q.B(Q.this));
/*      */ 
/*  806 */         if (!Q.this.isHaltRequested())
/*  807 */           Q.A(Q.this, this);
/*      */       }
/*      */     }
/*      */ 
/*      */     protected final boolean L()
/*      */     {
/*  818 */       return this.r;
/*      */     }
/*      */ 
/*      */     protected final void B(boolean paramBoolean)
/*      */     {
/*  828 */       this.r = paramBoolean;
/*      */     }
/*      */   }
/*      */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.A.Q
 * JD-Core Version:    0.6.0
 */