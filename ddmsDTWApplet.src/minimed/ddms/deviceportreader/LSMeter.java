/*      */ package minimed.ddms.A;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.text.ParsePosition;
/*      */ import java.text.SimpleDateFormat;
/*      */ import java.util.Date;
/*      */ import java.util.NoSuchElementException;
/*      */ import java.util.StringTokenizer;
/*      */ import java.util.Vector;
/*      */ import minimed.util.Contract;
/*      */ 
/*      */ abstract class w extends O
/*      */   implements D
/*      */ {
/*      */   static final int ұ = 50;
/*      */   static final int Ғ = 15310;
/*      */   static final char Ҫ = '$';
/*      */   static final String ҵ = "S?";
/*      */   static final String ҝ = "@";
/*      */   static final String ґ = "?";
/*      */   static final String ғ = "F";
/*      */   static final char Ӏ = 'S';
/*      */   static final char ѷ = 'L';
/*      */   static final char ҡ = 'X';
/*      */   static final char Қ = 'C';
/*      */   static final char Ҕ = 'R';
/*      */   static final char Ң = 'B';
/*      */   static final char ҽ = 'U';
/*      */   static final char Ҙ = 'P';
/*      */   static final char Ѿ = 'D';
/*      */   static final char қ = 'T';
/*      */   static final char Ѷ = 'E';
/*      */   static final char җ = 'I';
/*      */   static final char ҥ = 'W';
/*      */   static final char Ҩ = 'Y';
/*      */   static final char Ҝ = 'M';
/*      */   static final char ҙ = 'A';
/*      */   static final String ҁ = "DM?";
/*      */   static final String һ = "DM@";
/*      */   static final String Ҭ = "DMP";
/*      */   static final String Ҽ = "DMS?";
/*      */   static final String ҳ = "DMF";
/*      */   static final int ѱ = 50;
/*      */   static final int ң = 50;
/*      */   static final int ҕ = 13900;
/*      */   static final int Ҿ = 50;
/*      */   static final int Ѵ = 50;
/*      */   private static final int Ҁ = 50;
/*   83 */   private static final String[] ү = { "INSERT", "STRIP", "CODE" };
/*      */   private static final String Ѹ = "\021\r";
/*      */   String ѻ;
/*      */   String ѽ;
/*      */   String ҷ;
/*      */   String Ҟ;
/*      */   Character Ҵ;
/*      */   Character Ҡ;
/*      */   Boolean Җ;
/*      */   Integer ҫ;
/*      */   Integer Ҥ;
/*      */   Boolean Ҹ;
/*      */   Boolean ҿ;
/*      */   Boolean Ґ;
/*      */   Boolean Һ;
/*      */   Boolean ҟ;
/*      */   Boolean Ҳ;
/*      */   Boolean Ү;
/*      */   Boolean Ѽ;
/*      */   Boolean Ѻ;
/*      */   Boolean Ӂ;
/*      */   Boolean ҩ;
/*      */   _B ҭ;
/*      */   _B ѳ;
/*      */   _B ѵ;
/*      */   _B ѹ;
/*      */   _B Ұ;
/*      */   _B Ҷ;
/*      */   int ҧ;
/*      */   int ѿ;
/*      */   int Ҧ;
/*      */   int ҹ;
/*      */   private _D Ѳ;
/*      */ 
/*      */   w()
/*      */   {
/*  136 */     this.Ă = new String(new byte[50]);
/*  137 */     this.ă = new String(new byte[50]);
/*  138 */     this.ѻ = new String(new byte[50]);
/*  139 */     this.ѽ = new String(new byte[50]);
/*  140 */     this.Ҟ = new String(new byte[15310]);
/*  141 */     this.Ė = new _A();
/*      */ 
/*  145 */     this.Ҵ = null;
/*  146 */     this.Ҡ = null;
/*  147 */     this.Җ = null;
/*  148 */     this.ҫ = null;
/*  149 */     this.Ҥ = null;
/*  150 */     this.Ҹ = null;
/*  151 */     this.ҿ = null;
/*  152 */     this.Ґ = null;
/*  153 */     this.Һ = null;
/*  154 */     this.ҟ = null;
/*  155 */     this.Ҳ = null;
/*  156 */     this.Ү = null;
/*  157 */     this.Ѽ = null;
/*  158 */     this.Ѻ = null;
/*  159 */     this.Ӂ = null;
/*  160 */     this.ҩ = null;
/*      */ 
/*  163 */     this.ҭ = new _C("DM?", "Read Firmware Version", 50);
/*      */ 
/*  166 */     this.ѳ = new _C("DM@", "Read Serial Number", 50);
/*      */ 
/*  168 */     this.ѵ = new _C("DMP", "Read Datalog", 13900);
/*  169 */     this.ѹ = new _C("DMS?", "Read Current Settings", 50);
/*      */ 
/*  172 */     this.Ұ = null;
/*  173 */     this.Ҷ = new _C("DMF", "Read Real Time Clock", 50);
/*      */ 
/*  176 */     this.ҹ = 50;
/*  177 */     this.Ҧ = 7;
/*      */   }
/*      */ 
/*      */   public void A(v paramv, String paramString1, String paramString2)
/*      */     throws t, Z, IOException
/*      */   {
/*  200 */     A(this, "readData: starting reader...");
/*  201 */     B(false);
/*      */ 
/*  204 */     Vector localVector = É();
/*      */ 
/*  206 */     this.Ѳ = new _D(paramv, paramString1, localVector, null);
/*  207 */     _D.B(this.Ѳ);
/*      */   }
/*      */ 
/*      */   public void B(v paramv, String paramString1, String paramString2)
/*      */     throws t, Z, IOException
/*      */   {
/*  226 */     Vector localVector = new Vector();
/*      */ 
/*  229 */     localVector.addElement(this.Ҷ);
/*      */ 
/*  232 */     _D local_D = new _D(paramv, paramString1, localVector, null);
/*  233 */     _D.B(local_D);
/*      */   }
/*      */ 
/*      */   public String Q()
/*      */   {
/*      */     String str;
/*      */     try
/*      */     {
/*  246 */       str = O._B.A(this.ă, "@ ");
/*      */     } catch (Z localZ) {
/*  248 */       return this.ă;
/*      */     }
/*      */ 
/*  252 */     if (str != null) {
/*  253 */       StringTokenizer localStringTokenizer = new StringTokenizer(str, "\"");
/*  254 */       if (localStringTokenizer.hasMoreTokens())
/*      */       {
/*  256 */         return localStringTokenizer.nextToken();
/*      */       }
/*      */     }
/*  259 */     return this.ă;
/*      */   }
/*      */ 
/*      */   public String Ï()
/*      */   {
/*      */     String str1;
/*      */     try
/*      */     {
/*  273 */       str1 = O._B.A(this.Ă, "?");
/*      */     } catch (Z localZ) {
/*  275 */       return this.Ă;
/*      */     }
/*      */ 
/*  279 */     if (str1 != null) {
/*  280 */       StringTokenizer localStringTokenizer = new StringTokenizer(str1, " ");
/*  281 */       if (localStringTokenizer.hasMoreTokens()) {
/*  282 */         String str2 = localStringTokenizer.nextToken();
/*      */ 
/*  284 */         return str2.substring(1);
/*      */       }
/*      */     }
/*  287 */     return this.Ă;
/*      */   }
/*      */ 
/*      */   public void A(_B param_B)
/*      */     throws Z
/*      */   {
/*  301 */     String str = O._B.E(param_B.d());
/*      */ 
/*  303 */     if (param_B.equals(this.ҭ))
/*      */     {
/*  305 */       this.Ă = str;
/*  306 */       A(this, "decodeReply: firmware version is '" + Ï() + "'");
/*      */     }
/*  309 */     else if (param_B.equals(this.ѳ))
/*      */     {
/*  311 */       this.ă = str;
/*  312 */       A(this, "decodeReply: serial number is '" + Q() + "'");
/*      */ 
/*  315 */       Î();
/*      */ 
/*  318 */       _D.C(this.Ѳ);
/*      */     }
/*  320 */     else if (param_B.equals(this.ѹ))
/*      */     {
/*  322 */       this.ѽ = str;
/*  323 */       A(this, "decodeReply: current settings is '" + this.ѽ + "'");
/*      */ 
/*  325 */       Ë();
/*      */     }
/*  327 */     else if ((this.Ұ != null) && (param_B.equals(this.Ұ)))
/*      */     {
/*  330 */       this.ҷ = str;
/*  331 */       A(this, "decodeReply: current settings2 is '" + this.ҷ + "'");
/*      */ 
/*  333 */       Ð();
/*      */     }
/*  335 */     else if (param_B.equals(this.Ҷ))
/*      */     {
/*  337 */       this.ѻ = str;
/*  338 */       Í();
/*  339 */       A(this, "decodeReply: device clock reads '" + N() + "'");
/*      */     }
/*  341 */     else if (param_B.equals(this.ѵ)) {
/*  342 */       this.Ҟ = str.trim();
/*  343 */       A(this, "decodeReply: reply is datalog records: '" + this.Ҟ + "'");
/*      */     }
/*      */     else {
/*  346 */       throw new Z("Command '" + param_B + " ' is unrecognized");
/*      */     }
/*      */   }
/*      */ 
/*      */   void N(int paramInt)
/*      */   {
/*  358 */     this.ѿ += paramInt;
/*      */   }
/*      */ 
/*      */   abstract void Ë()
/*      */     throws Z;
/*      */ 
/*      */   void Ð()
/*      */     throws Z
/*      */   {
/*      */   }
/*      */ 
/*      */   Vector É()
/*      */   {
/*  388 */     Vector localVector = new Vector();
/*      */ 
/*  391 */     localVector.addElement(this.ѹ);
/*  392 */     if (this.Ұ != null) {
/*  393 */       localVector.addElement(this.Ұ);
/*      */     }
/*  395 */     localVector.addElement(this.Ҷ);
/*  396 */     localVector.addElement(this.ҭ);
/*  397 */     localVector.addElement(this.ѳ);
/*  398 */     localVector.addElement(this.ѵ);
/*  399 */     return localVector;
/*      */   }
/*      */ 
/*      */   void Ì()
/*      */   {
/*  406 */     A(this.ѿ, this.ҧ);
/*      */   }
/*      */ 
/*      */   int Z()
/*      */   {
/*  415 */     return 3;
/*      */   }
/*      */ 
/*      */   Vector Ê()
/*      */   {
/*  424 */     return _D.A(this.Ѳ);
/*      */   }
/*      */ 
/*      */   void Ñ()
/*      */     throws t, Z
/*      */   {
/*      */   }
/*      */ 
/*      */   void Î()
/*      */     throws Z
/*      */   {
/*      */   }
/*      */ 
/*      */   void C(v paramv)
/*      */     throws t, IOException
/*      */   {
/*      */     try
/*      */     {
/*  456 */       this.Ҷ.A();
/*      */     }
/*      */     catch (Z localZ) {
/*  459 */       throw new t("Got reply, but format is bad.");
/*      */     }
/*      */   }
/*      */ 
/*      */   void D(String paramString)
/*      */     throws IOException
/*      */   {
/*  471 */     C(paramString);
/*  472 */     A(new d(paramString, this.Ҧ));
/*  473 */     Y().A();
/*  474 */     Y().A(this.ҹ);
/*      */   }
/*      */ 
/*      */   void _()
/*      */     throws IOException
/*      */   {
/*  483 */     if (Y() != null)
/*      */     {
/*  485 */       Y().B();
/*  486 */       A(null);
/*      */     }
/*      */   }
/*      */   private void Í() throws Z {
/*  506 */     String str3 = O._B.A(this.ѻ, "F ").trim();
/*      */ 
/*  510 */     StringTokenizer localStringTokenizer = new StringTokenizer(str3, "\",");
/*      */     String str1;
/*      */     String str2;
/*      */     try {
/*  512 */       localStringTokenizer.nextToken();
/*      */ 
/*  515 */       str1 = localStringTokenizer.nextToken();
/*  516 */       str2 = localStringTokenizer.nextToken();
/*      */     } catch (NoSuchElementException localNoSuchElementException) {
/*  518 */       throw new Z("Bad device timestamp reply '" + this.ѻ + "'");
/*      */     }
/*      */ 
/*  522 */     if ((this.Һ != null) && (this.ҟ != null)) {
/*  523 */       this.ĕ = A(str1, str2, this.Һ.booleanValue());
/*      */     }
/*      */     else
/*      */     {
/*  527 */       this.ĕ = A(str1, str2, true);
/*      */     }
/*      */ 
/*  530 */     A(this, "decodeCurrentTimeStamp: current time stamp for device is " + this.ĕ);
/*      */   }
/*      */ 
/*      */   private final Date A(String paramString1, String paramString2, boolean paramBoolean)
/*      */     throws Z
/*      */   {
/*  553 */     Contract.pre(paramString1 != null);
/*  554 */     Contract.pre(paramString2 != null);
/*      */ 
/*  556 */     int i = (paramString2.indexOf("AM") >= 0) || (paramString2.indexOf("PM") >= 0) ? 1 : 0;
/*      */     String str1;
/*  559 */     if (paramBoolean) {
/*  560 */       if (i != 0) {
/*  561 */         str1 = new String("M/dd/yy h:mm:ss aa");
/*      */       }
/*      */       else {
/*  564 */         str1 = new String("M/dd/yy H:mm:ss");
/*      */       }
/*      */ 
/*      */     }
/*  568 */     else if (i != 0) {
/*  569 */       str1 = new String("dd/M/yy h:mm:ss aa");
/*      */     }
/*      */     else {
/*  572 */       str1 = new String("dd/M/yy H:mm:ss");
/*      */     }
/*      */ 
/*  577 */     SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(str1);
/*  578 */     String str2 = paramString1 + " " + paramString2;
/*  579 */     Date localDate = localSimpleDateFormat.parse(str2, new ParsePosition(0));
/*      */ 
/*  581 */     if (localDate == null)
/*      */     {
/*  583 */       throw new Z("Bad device time string (null returned) '" + str2 + "' received");
/*      */     }
/*      */ 
/*  587 */     return localDate;
/*      */   }
/*      */ 
/*      */   class _C extends w._B
/*      */   {
/*      */     static final byte Õ = -1;
/*      */     static final int Ù = 2;
/*      */     static final int Ô = 10000;
/*      */     static final int Ö = 4;
/*      */     private String Ø;
/*      */ 
/*      */     _C(String paramString1, String paramInt, int paramD, D arg5)
/*      */     {
/* 1005 */       super(paramInt);
/* 1006 */       this.Ø = paramString1;
/* 1007 */       B(new int[paramD]);
/* 1008 */       this.Ç = 2;
/*      */       Object localObject;
/* 1009 */       this.É = localObject;
/*      */     }
/*      */ 
/*      */     _C(String paramString1, String paramInt, int arg4)
/*      */     {
/* 1021 */       this(paramString1, paramInt, i, this$1);
/*      */     }
/*      */ 
/*      */     public String toString()
/*      */     {
/* 1031 */       return this.Ø + " ( " + this.A + ")";
/*      */     }
/*      */ 
/*      */     final String k()
/*      */     {
/* 1040 */       return this.Ø;
/*      */     }
/*      */ 
/*      */     void c()
/*      */       throws t, Z
/*      */     {
/* 1053 */       int j = 0;
/* 1054 */       String str = "";
/*      */ 
/* 1059 */       if (this.Æ.T() != 7) {
/* 1060 */         this.Æ.B(4);
/*      */       }
/* 1062 */       m();
/*      */ 
/* 1064 */       if (e() > 0)
/*      */       {
/* 1066 */         if (!this.Æ.isHaltRequested())
/*      */         {
/* 1068 */           this.Æ.B(5);
/* 1069 */           str = l();
/*      */         }
/*      */ 
/* 1073 */         if ((str.length() == 0) && (!this.Æ.isHaltRequested()))
/*      */         {
/* 1075 */           O._B.G(10000);
/* 1076 */           str = l();
/*      */         }
/*      */         int i;
/* 1081 */         while (((i = str.length()) > 0) && (j < e()) && (!this.Æ.isHaltRequested()))
/*      */         {
/* 1083 */           int[] arrayOfInt = d();
/* 1084 */           int k = Math.min(i, arrayOfInt.length - j);
/* 1085 */           System.arraycopy(O._B.D(str), 0, arrayOfInt, j, k);
/*      */ 
/* 1087 */           B(arrayOfInt);
/*      */ 
/* 1089 */           j += k;
/* 1090 */           this.Æ.ѿ += k;
/*      */ 
/* 1093 */           this.Æ.Ì();
/*      */ 
/* 1095 */           if ((j < e()) && 
/* 1096 */             (!this.Æ.isHaltRequested()))
/*      */           {
/* 1098 */             str = l();
/*      */           }
/*      */ 
/*      */         }
/*      */ 
/* 1103 */         if (!this.Æ.isHaltRequested())
/* 1104 */           this.É.A(this);
/*      */       }
/*      */     }
/*      */ 
/*      */     void m()
/*      */       throws t
/*      */     {
/* 1117 */       this.Æ.Y().E();
/*      */ 
/* 1119 */       O.A(this, "sendCommand: sending cmd " + this.Ø + " (" + this.A + ")");
/*      */       try
/*      */       {
/* 1124 */         for (int i = 0; i < "\021\r".length(); i++) {
/* 1125 */           this.Æ.Y().A("\021\r".substring(i, i + 1));
/*      */         }
/*      */ 
/* 1129 */         for (i = 0; i < this.Ø.length(); i++)
/* 1130 */           this.Æ.Y().A(this.Ø.substring(i, i + 1));
/*      */       }
/*      */       catch (IOException localIOException) {
/* 1133 */         throw new t("sendCommand: ERROR - an IOException  has occurred processing cmd " + this.Ø + " (" + this.A + ")");
/*      */       }
/*      */     }
/*      */ 
/*      */     private final String l() throws t, Z
/*      */     {
/* 1153 */       this.Æ.Y().E();
/*      */ 
/* 1155 */       O.A(this, "readDeviceData: reading reply to cmd " + this.Ø + " (" + this.A + ")");
/*      */       String str;
/*      */       try {
/* 1160 */         str = this.Æ.Y().C();
/*      */ 
/* 1164 */         for (int i = 0; i < w.È().length; i++) {
/* 1165 */           if (str.indexOf(w.È()[i]) < 0)
/*      */             continue;
/* 1167 */           str = this.Æ.Y().C();
/*      */ 
/* 1170 */           for (int j = 0; j < w.È().length; j++) {
/* 1171 */             if (str.indexOf(w.È()[j]) < 0)
/*      */               continue;
/* 1173 */             str = "";
/* 1174 */             break;
/*      */           }
/*      */ 
/* 1177 */           break;
/*      */         }
/*      */       }
/*      */       catch (IOException localIOException) {
/* 1181 */         throw new t("readDeviceData: ERROR - an IOException  has occurred processing cmd " + this.Ø + " (" + this.A + ")");
/*      */       }
/*      */ 
/* 1186 */       if (str.length() > 0)
/*      */       {
/* 1188 */         if (str.length() > 5) {
/* 1189 */           D(str);
/*      */         }
/* 1191 */         O.A(this, "readDeviceData: cmd " + this.Ø + " (" + this.A + ") returned " + str.length() + " data bytes, with reply = <" + str + ">");
/*      */       }
/*      */ 
/* 1195 */       return str;
/*      */     }
/*      */ 
/*      */     final int D(String paramString)
/*      */       throws Z
/*      */     {
/* 1211 */       Contract.pre(paramString != null);
/* 1212 */       Contract.pre(paramString.length() > 5);
/*      */ 
/* 1218 */       int i = paramString.indexOf('\r');
/*      */       String str1;
/* 1220 */       if (i > 0) {
/* 1221 */         str1 = paramString.substring(0, paramString.indexOf('\r'));
/*      */       }
/*      */       else {
/* 1224 */         throw new Z("ERROR - '" + O._B.E(paramString) + "' record has bad CRC format");
/*      */       }
/*      */ 
/* 1227 */       int[] arrayOfInt = O._B.D(str1);
/* 1228 */       int j = O._B.D(arrayOfInt, 0, arrayOfInt.length - 4 - 1);
/*      */ 
/* 1230 */       String str2 = str1.substring(arrayOfInt.length - 4 + 1).trim();
/*      */       int k;
/*      */       try {
/* 1237 */         k = Integer.parseInt(str2, 16);
/*      */       } catch (NumberFormatException localNumberFormatException) {
/* 1239 */         throw new Z("ERROR - '" + O._B.E(paramString) + "' record has bad CRC value of " + str2 + " (expected " + O._B.H(j) + ").");
/*      */       }
/*      */ 
/* 1246 */       if (j != k) {
/* 1247 */         throw new Z("ERROR - '" + O._B.E(str1) + "' record has incorrect CRC value of " + O._B.H(k) + " (expected " + O._B.H(j) + ").");
/*      */       }
/*      */ 
/* 1253 */       return k;
/*      */     }
/*      */   }
/*      */ 
/*      */   abstract class _B extends n
/*      */   {
/*      */     private int[] È;
/*  829 */     D É = null;
/*      */     int Ç;
/*      */ 
/*      */     public _B(String arg2)
/*      */     {
/*  844 */       super();
/*      */     }
/*      */ 
/*      */     void A()
/*      */       throws t, Z
/*      */     {
/*  858 */       int i = 0;
/*  859 */       int j = 0;
/*      */ 
/*  861 */       w.this.Y().E();
/*      */ 
/*  863 */       O.Ã = this.A;
/*      */       do
/*      */       {
/*      */         try
/*      */         {
/*  869 */           c();
/*  870 */           j = 1;
/*      */         } catch (t localt) {
/*  872 */           i++;
/*  873 */           a();
/*  874 */           if (i <= this.Ç) {
/*  875 */             O.D(this, "execute: cmd failed with exception: " + localt + "; retrying (attempts = " + (i + 1) + ")");
/*      */ 
/*  877 */             w.this.B(7);
/*      */           } else {
/*  879 */             O.E(this, "cmd " + this + " failed after " + i + " attempts" + "; exception = " + localt);
/*      */ 
/*  885 */             throw new t("execute: cmd " + this + " failed after " + i + " attempts");
/*      */           }
/*      */         }
/*      */         catch (Z localZ)
/*      */         {
/*  890 */           i++;
/*  891 */           a();
/*  892 */           if (i <= this.Ç) {
/*  893 */             O.D(this, "execute: cmd failed with exception: " + localZ + "; retrying (attempts = " + (i + 1) + ")");
/*      */ 
/*  895 */             w.this.B(7);
/*      */           } else {
/*  897 */             O.E(this, "cmd " + this + " failed after " + i + " attempts" + "; exception = " + localZ);
/*      */ 
/*  903 */             throw new Z("execute: cmd " + this + " failed after " + i + " attempts");
/*      */           }
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*  909 */       while ((j == 0) && (i <= this.Ç));
/*      */     }
/*      */ 
/*      */     void a()
/*      */     {
/*  916 */       w.this.Y().A();
/*      */     }
/*      */ 
/*      */     abstract void c()
/*      */       throws t, Z;
/*      */ 
/*      */     final int[] d()
/*      */     {
/*  936 */       return this.È;
/*      */     }
/*      */ 
/*      */     final void B(int[] paramArrayOfInt)
/*      */     {
/*  945 */       this.È = paramArrayOfInt;
/*      */     }
/*      */ 
/*      */     int e()
/*      */     {
/*  954 */       int[] arrayOfInt = d();
/*  955 */       return arrayOfInt == null ? 0 : arrayOfInt.length;
/*      */     }
/*      */ 
/*      */     final String b()
/*      */     {
/*  964 */       return this.A;
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
/*  681 */       w.this.B(paramString);
/*  682 */       this.B = paramVector;
/*      */       Object localObject;
/*  683 */       this.C = localObject;
/*      */     }
/*      */ 
/*      */     private final void A()
/*      */     {
/*  693 */       w.this.ҧ = 0;
/*      */ 
/*  695 */       for (int i = 0; i < this.C.size(); i++) {
/*  696 */         w._B local_B = (w._B)this.C.elementAt(i);
/*  697 */         if (local_B != null)
/*  698 */           w.this.ҧ += local_B.e();
/*      */       }
/*      */     }
/*      */ 
/*      */     private final void C()
/*      */       throws t, Z, IOException
/*      */     {
/*  714 */       int i = 0;
/*      */ 
/*  716 */       Vector localVector = new Vector();
/*      */ 
/*  720 */       w.this.ѿ = 0;
/*  721 */       A();
/*      */ 
/*  723 */       w.this.C(0);
/*      */       try
/*      */       {
/*  731 */         w.this.B(2);
/*      */         try
/*      */         {
/*  734 */           A(this.B);
/*      */         } catch (IOException localIOException) {
/*  736 */           if (!w.this.isHaltRequested()) {
/*  737 */             O.D(this, "acquireDataFromDevice: initCommunications failed with IOException; retrying... (exception = " + localIOException + ")");
/*      */ 
/*  740 */             A(this.B);
/*      */           }
/*      */         }
/*      */ 
/*  744 */         w.this.E(5);
/*      */ 
/*  747 */         for (i = 0; (i < this.C.size()) && (!w.this.isHaltRequested()); )
/*      */         {
/*  749 */           w._B local_B = (w._B)this.C.elementAt(i);
/*  750 */           if (local_B != null)
/*      */           {
/*  752 */             local_B.A();
/*  753 */             if (!w.this.isHaltRequested())
/*      */             {
/*  755 */               int[] arrayOfInt = local_B.d();
/*  756 */               localVector.addElement(arrayOfInt);
/*      */             }
/*      */           }
/*  748 */           i++;
/*      */         }
/*      */ 
/*      */       }
/*      */       finally
/*      */       {
/*      */         try
/*      */         {
/*  767 */           O.A(this, "run: shutting down communications...");
/*  768 */           w.this.E(6);
/*  769 */           B();
/*      */         } finally {
/*  771 */           O.A(this, "run: done!  Number of commands processed: " + i);
/*      */ 
/*  774 */           if (w.this.isHaltRequested())
/*  775 */             w.this.B(9);
/*      */           else {
/*  777 */             w.this.B(1);
/*      */           }
/*      */ 
/*  780 */           w.this.U();
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/*      */     private final void A(String paramString)
/*      */       throws IOException, t, Z
/*      */     {
/*  797 */       w.this.D(paramString);
/*      */ 
/*  800 */       w.this.Ñ();
/*      */     }
/*      */ 
/*      */     private final void B()
/*      */       throws IOException
/*      */     {
/*  809 */       O.A(this, "endCommunications: shutting down serial port.");
/*  810 */       if (w.this.Y() != null)
/*      */       {
/*  812 */         w.this.Y().B();
/*  813 */         w.this.A(null);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   class _A extends O._A
/*      */   {
/*      */     static final int è = 170;
/*      */     static final int æ = 1;
/*      */     static final int é = 2;
/*      */     static final int ç = 2;
/*      */ 
/*      */     _A()
/*      */     {
/*  613 */       super(170);
/*  614 */       w.this.e = w.this.Ă.length();
/*  615 */       w.this.ì = w.this.ă.length();
/*  616 */       w.this.£ = w.this.ѻ.length();
/*      */     }
/*      */ 
/*      */     void A()
/*      */     {
/*  626 */       w.this.Î = new CA(w.this.Þ, 1, B(w.this.Ă), B(w.this.ă), B(w.this.ѻ));
/*      */ 
/*  629 */       O.A(this, "createSnapshot: creating snapshot ");
/*      */ 
/*  633 */       w.this.Î.A(1, w.this.ѽ);
/*      */ 
/*  637 */       w.this.Î.A(2, w.this.Ҟ);
/*      */     }
/*      */ 
/*      */     String B(String paramString)
/*      */     {
/*  647 */       Contract.preNonNull(paramString);
/*  648 */       StringBuffer localStringBuffer = new StringBuffer(paramString);
/*      */ 
/*  650 */       for (int i = paramString.length(); i < 50; i++) {
/*  651 */         localStringBuffer.append('$');
/*      */       }
/*      */ 
/*  654 */       return localStringBuffer.toString();
/*      */     }
/*      */   }
/*      */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.A.w
 * JD-Core Version:    0.6.0
 */