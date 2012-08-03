/*      */ package minimed.ddms.A;
/*      */ 
/*      */ import java.io.FileNotFoundException;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.PrintWriter;
/*      */ import java.text.SimpleDateFormat;
/*      */ import java.util.Calendar;
/*      */ import java.util.Date;
/*      */ import java.util.GregorianCalendar;
/*      */ import java.util.Vector;
/*      */ import minimed.util.Contract;
/*      */ 
/*      */ abstract class O
/*      */   implements G, v
/*      */ {
/*      */   public static final String f = "Incorrect product code";
/*      */   static final String ç = "10.0.0";
/*      */   static final int Ā = 1;
/*      */   static final int ÿ = 2;
/*      */   static final int º = 1;
/*      */   static final int µ = 2;
/*      */   static final int ý = 15;
/*      */   static final int r = 255;
/*      */   static final int Ê = 65535;
/*      */   static final int ª = 16777215;
/*      */   static final long č = 4294967295L;
/*      */   static final byte ą = 3;
/*      */   static final byte Á = 4;
/*      */   static final byte v = 8;
/*      */   static final byte Ô = 16;
/*      */   static final int ¥ = 128;
/*      */   static final int k = 64;
/*      */   static final int Đ = 32;
/*      */   static final int ú = 16;
/*      */   static final int ë = 8;
/*      */   static final int Ý = 4;
/*      */   static final int Ä = 2;
/*      */   static final int q = 1;
/*      */   static final byte i = 3;
/*      */   static final byte Ü = 7;
/*      */   static final byte ó = 15;
/*      */   static final byte Ë = 31;
/*      */   static final byte Å = 63;
/*      */   static final byte s = 127;
/*      */   static final int ė = 32768;
/*      */   static final int ä = 2000;
/*      */   static final int ď = 16;
/*      */   static final int á = 0;
/*      */   static final int Ě = 1;
/*      */   static final short g = 4129;
/*  218 */   static final Integer Í = null;
/*      */ 
/*  223 */   static final Integer Ċ = null;
/*      */   static final int Ú = -1;
/*      */   static final char Õ = '\000';
/*      */   static final char Ø = '\001';
/*      */   static final char É = '\002';
/*      */   static final char û = '\003';
/*      */   static final char ê = '\004';
/*      */   static final char þ = '\005';
/*      */   static final char ô = '\006';
/*      */   static final char ß = '\t';
/*      */   static final char È = '\n';
/*      */   static final char õ = '\r';
/*      */   static final char z = '\020';
/*      */   static final char ù = '\021';
/*      */   static final char ï = '\025';
/*      */   static final char ċ = '\027';
/*      */   static final char Ē = '\030';
/*      */   static final char ¢ = ' ';
/*      */   static final char å = '0';
/*      */   static final char ö = ' ';
/*      */   static final char Č = '';
/*      */   static final String ð = "\r";
/*      */   static final String Ç = "\n";
/*      */   static final int é = 24;
/*      */   static final int Æ = 60;
/*      */   static final int ò = 60;
/*      */   static final int Ï = 1000;
/*      */   static final int í = 12;
/*      */   static final int ñ = 31;
/*      */   static final char ã = '\'';
/*      */   static final String h = "MG/DL";
/*      */   static final String â = "MMOL/L";
/*      */   static final String Ò = "AM/PM";
/*      */   static final String Ę = "24:00";
/*  353 */   static final int[] m = { 0, 155, 173, 54, 193, 90, 108, 247, 25, 130, 180, 47, 216, 67, 117, 238, 50, 169, 159, 4, 243, 104, 94, 197, 43, 176, 134, 29, 234, 113, 71, 220, 100, 255, 201, 82, 165, 62, 8, 147, 125, 230, 208, 75, 188, 39, 17, 138, 86, 205, 251, 96, 151, 12, 58, 161, 79, 212, 226, 121, 142, 21, 35, 184, 200, 83, 101, 254, 9, 146, 164, 63, 209, 74, 124, 231, 16, 139, 189, 38, 250, 97, 87, 204, 59, 160, 150, 13, 227, 120, 78, 213, 34, 185, 143, 20, 172, 55, 1, 154, 109, 246, 192, 91, 181, 46, 24, 131, 116, 239, 217, 66, 158, 5, 51, 168, 95, 196, 242, 105, 135, 28, 42, 177, 70, 221, 235, 112, 11, 144, 166, 61, 202, 81, 103, 252, 18, 137, 191, 36, 211, 72, 126, 229, 57, 162, 148, 15, 248, 99, 85, 206, 32, 187, 141, 22, 225, 122, 76, 215, 111, 244, 194, 89, 174, 53, 3, 152, 118, 237, 219, 64, 183, 44, 26, 129, 93, 198, 240, 107, 156, 7, 49, 170, 68, 223, 233, 114, 133, 30, 40, 179, 195, 88, 110, 245, 2, 153, 175, 52, 218, 65, 119, 236, 27, 128, 182, 45, 241, 106, 92, 199, 48, 171, 157, 6, 232, 115, 69, 222, 41, 178, 132, 31, 167, 60, 10, 145, 102, 253, 203, 80, 190, 37, 19, 136, 127, 228, 210, 73, 149, 14, 56, 163, 84, 207, 249, 98, 140, 23, 33, 186, 77, 214, 224, 123 };
/*      */ 
/*  391 */   static final int[] o = { 0, 94, 188, 226, 97, 63, 221, 131, 194, 156, 126, 32, 163, 253, 31, 65, 157, 195, 33, 127, 252, 162, 64, 30, 95, 1, 227, 189, 62, 96, 130, 220, 35, 125, 159, 193, 66, 28, 254, 160, 225, 191, 93, 3, 128, 222, 60, 98, 190, 224, 2, 92, 223, 129, 99, 61, 124, 34, 192, 158, 29, 67, 161, 255, 70, 24, 250, 164, 39, 121, 155, 197, 132, 218, 56, 102, 229, 187, 89, 7, 219, 133, 103, 57, 186, 228, 6, 88, 25, 71, 165, 251, 120, 38, 196, 154, 101, 59, 217, 135, 4, 90, 184, 230, 167, 249, 27, 69, 198, 152, 122, 36, 248, 166, 68, 26, 153, 199, 37, 123, 58, 100, 134, 216, 91, 5, 231, 185, 140, 210, 48, 110, 237, 179, 81, 15, 78, 16, 242, 172, 47, 113, 147, 205, 17, 79, 173, 243, 112, 46, 204, 146, 211, 141, 111, 49, 178, 236, 14, 80, 175, 241, 19, 77, 206, 144, 114, 44, 109, 51, 209, 143, 12, 82, 176, 238, 50, 108, 142, 208, 83, 13, 239, 177, 240, 174, 76, 18, 145, 207, 45, 115, 202, 148, 118, 40, 171, 245, 23, 73, 8, 86, 180, 234, 105, 55, 213, 139, 87, 9, 235, 181, 54, 104, 138, 212, 149, 203, 41, 119, 244, 170, 72, 22, 233, 183, 85, 11, 136, 214, 52, 106, 43, 117, 151, 201, 74, 20, 246, 168, 116, 42, 200, 150, 21, 75, 169, 247, 182, 232, 10, 84, 215, 137, 107, 53 };
/*      */ 
/*  415 */   static final int[] ĉ = { 0, 4129, 8258, 12387, 16516, 20645, 24774, 28903, 33032, 37161, 41290, 45419, 49548, 53677, 57806, 61935, 4657, 528, 12915, 8786, 21173, 17044, 29431, 25302, 37689, 33560, 45947, 41818, 54205, 50076, 62463, 58334, 9314, 13379, 1056, 5121, 25830, 29895, 17572, 21637, 42346, 46411, 34088, 38153, 58862, 62927, 50604, 54669, 13907, 9842, 5649, 1584, 30423, 26358, 22165, 18100, 46939, 42874, 38681, 34616, 63455, 59390, 55197, 51132, 18628, 22757, 26758, 30887, 2112, 6241, 10242, 14371, 51660, 55789, 59790, 63919, 35144, 39273, 43274, 47403, 23285, 19156, 31415, 27286, 6769, 2640, 14899, 10770, 56317, 52188, 64447, 60318, 39801, 35672, 47931, 43802, 27814, 31879, 19684, 23749, 11298, 15363, 3168, 7233, 60846, 64911, 52716, 56781, 44330, 48395, 36200, 40265, 32407, 28342, 24277, 20212, 15891, 11826, 7761, 3696, 65439, 61374, 57309, 53244, 48923, 44858, 40793, 36728, 37256, 33193, 45514, 41451, 53516, 49453, 61774, 57711, 4224, 161, 12482, 8419, 20484, 16421, 28742, 24679, 33721, 37784, 41979, 46042, 49981, 54044, 58239, 62302, 689, 4752, 8947, 13010, 16949, 21012, 25207, 29270, 46570, 42443, 38312, 34185, 62830, 58703, 54572, 50445, 13538, 9411, 5280, 1153, 29798, 25671, 21540, 17413, 42971, 47098, 34713, 38840, 59231, 63358, 50973, 55100, 9939, 14066, 1681, 5808, 26199, 30326, 17941, 22068, 55628, 51565, 63758, 59695, 39368, 35305, 47498, 43435, 22596, 18533, 30726, 26663, 6336, 2273, 14466, 10403, 52093, 56156, 60223, 64286, 35833, 39896, 43963, 48026, 19061, 23124, 27191, 31254, 2801, 6864, 10931, 14994, 64814, 60687, 56684, 52557, 48554, 44427, 40424, 36297, 31782, 27655, 23652, 19525, 15522, 11395, 7392, 3265, 61215, 65342, 53085, 57212, 44955, 49082, 36825, 40952, 28183, 32310, 20053, 24180, 11923, 16050, 3793, 7920 };
/*      */ 
/*  457 */   static final int[] Â = { 0, 26, 52, 46, 104, 114, 92, 70, 93, 71, 105, 115, 53, 47, 1, 27, 55, 45, 3, 25, 95, 69, 107, 113, 106, 112, 94, 68, 2, 24, 54, 44, 110, 116, 90, 64, 6, 28, 50, 40, 51, 41, 7, 29, 91, 65, 111, 117, 89, 67, 109, 119, 49, 43, 5, 31, 4, 30, 48, 42, 108, 118, 88, 66, 81, 75, 101, 127, 57, 35, 13, 23, 12, 22, 56, 34, 100, 126, 80, 74, 102, 124, 82, 72, 14, 20, 58, 32, 59, 33, 15, 21, 83, 73, 103, 125, 63, 37, 11, 17, 87, 77, 99, 121, 98, 120, 86, 76, 10, 16, 62, 36, 8, 18, 60, 38, 96, 122, 84, 78, 85, 79, 97, 123, 61, 39, 9, 19, 47, 53, 27, 1, 71, 93, 115, 105, 114, 104, 70, 92, 26, 0, 46, 52, 24, 2, 44, 54, 112, 106, 68, 94, 69, 95, 113, 107, 45, 55, 25, 3, 65, 91, 117, 111, 41, 51, 29, 7, 28, 6, 40, 50, 116, 110, 64, 90, 118, 108, 66, 88, 30, 4, 42, 48, 43, 49, 31, 5, 67, 89, 119, 109, 126, 100, 74, 80, 22, 12, 34, 56, 35, 57, 23, 13, 75, 81, 127, 101, 73, 83, 125, 103, 33, 59, 21, 15, 20, 14, 32, 58, 124, 102, 72, 82, 16, 10, 36, 62, 120, 98, 76, 86, 77, 87, 121, 99, 37, 63, 17, 11, 39, 61, 19, 9, 79, 85, 123, 97, 122, 96, 78, 84, 18, 8, 38, 60 };
/*      */   static final int Ĉ = 16;
/*  483 */   private static final String[] t = { "COM1", "COM2", "COM3", "COM4", "COM5", "COM6", "COM7", "COM8", "COM9", "usbserial", "usbserial1", "usbserial2", "usbserial3", "usbserial4", "usbserial5", "usbserial6", "usbserial7", "usbserial8", "usbserial9" };
/*      */   private static final int Û = 1997;
/*      */   private static final int Ö = 2096;
/*      */   private static final String w = "USB";
/*  496 */   private static boolean Ć = false;
/*      */ 
/*  501 */   private static PrintWriter p = null;
/*      */ 
/*  506 */   private static int ę = 0;
/*      */ 
/*  511 */   private static SimpleDateFormat l = null;
/*      */   static final int ü = 100;
/*  518 */   static String Ã = " ";
/*      */ 
/*  523 */   static boolean n = false;
/*      */ 
/*  528 */   static int Ì = 0;
/*      */ 
/*  533 */   static String ē = "0";
/*      */ 
/*  538 */   static int ø = 0;
/*      */ 
/*  543 */   static String Ď = "0";
/*      */   static final int æ = 2;
/*      */   private z Ó;
/*      */   static final int Ù = 1;
/*      */   static final int Ą = 2;
/*      */   static final int î = 3;
/*      */   EA è;
/*      */   String u;
/*  574 */   int đ = 0;
/*      */   String ć;
/*      */   _A Ė;
/*      */   CA Î;
/*      */   private int Ĕ;
/*      */   int e;
/*      */   int ì;
/*      */   int £;
/*      */   String ă;
/*      */   String Ă;
/*      */   Date ĕ;
/*      */   int à;
/*      */   int Ð;
/*      */   int ā;
/*      */   int Þ;
/*      */   long ¤;
/*      */   long Ñ;
/*      */   String À;
/*  668 */   private final Vector j = new Vector(10);
/*      */ 
/*      */   O()
/*      */   {
/*  679 */     this.à = 1997;
/*  680 */     this.Ð = 2096;
/*  681 */     this.è = null;
/*      */ 
/*  684 */     A(new K(this, this.ă));
/*      */   }
/*      */ 
/*      */   public String toString()
/*      */   {
/*  701 */     return "description = " + this.ć + ", modelNumber = " + this.À + ", device type = " + (H() ? "meter" : A() ? "monitor" : M() ? "pump" : null) + ", serialNumber = " + this.ă + ", firmwareVersion = " + this.Ă + ", timeStamp = " + this.ĕ + ", communicationsLink = '" + W() + "'" + ", linkDevice ID = " + this.đ + ", PACKAGE_VERSION = " + "10.0.0";
/*      */   }
/*      */ 
/*      */   public void B(v paramv, String paramString1, String paramString2, Date paramDate1, Date paramDate2)
/*      */     throws t, Z, IOException, P
/*      */   {
/*  746 */     Contract.unreachable();
/*      */   }
/*      */ 
/*      */   public void A(v paramv, String paramString1, String paramString2, Date paramDate1, Date paramDate2)
/*      */     throws t, Z, IOException, P
/*      */   {
/*  782 */     Contract.unreachable();
/*      */   }
/*      */ 
/*      */   public void B(v paramv, String paramString1, String paramString2)
/*      */     throws t, Z, IOException, P
/*      */   {
/*  806 */     Contract.unreachable();
/*      */   }
/*      */ 
/*      */   public void A(v paramv, String paramString1, String paramString2, boolean paramBoolean)
/*      */     throws t, Z, IOException, P
/*      */   {
/*  836 */     A(paramv, paramString1, paramString2);
/*      */   }
/*      */ 
/*      */   public EA J()
/*      */   {
/*  847 */     return this.è;
/*      */   }
/*      */ 
/*      */   public String A(v paramv)
/*      */     throws t, IOException
/*      */   {
/*      */     String str;
/*  867 */     if ((this.đ == 19) || ((this instanceof k)) || ((this instanceof j)))
/*      */     {
/*  869 */       str = "USB";
/*      */     }
/*      */     else {
/*      */       try {
/*      */         try {
/*  874 */           str = A(paramv, d.M());
/*      */         }
/*      */         catch (t localt) {
/*  877 */           if (!isHaltRequested()) {
/*  878 */             D(this, "autoDetectDevice: system list failed; trying max port list.");
/*      */ 
/*  880 */             str = A(paramv, t);
/*      */           } else {
/*  882 */             throw localt;
/*      */           }
/*      */         }
/*      */       }
/*      */       finally
/*      */       {
/*  888 */         _();
/*      */       }
/*      */     }
/*      */ 
/*  892 */     return str;
/*      */   }
/*      */ 
/*      */   public InputStream C()
/*      */     throws Z, IOException
/*      */   {
/*  908 */     return this.Ė.B();
/*      */   }
/*      */ 
/*      */   public void A(String paramString)
/*      */     throws FileNotFoundException, IOException
/*      */   {
/*  922 */     this.Î.B(paramString);
/*      */   }
/*      */ 
/*      */   public void P()
/*      */   {
/*  930 */     A(this, "halt: STOPPING UPLOAD...");
/*  931 */     B(true);
/*      */ 
/*  933 */     if ((W() != null) && 
/*  934 */       (W().C() != null)) {
/*  935 */       W().C().A(false);
/*      */     }
/*      */ 
/*  939 */     B(8);
/*      */   }
/*      */ 
/*      */   public String D()
/*      */   {
/*  948 */     return Ã;
/*      */   }
/*      */ 
/*      */   public String R()
/*      */   {
/*  957 */     return this.ć;
/*      */   }
/*      */ 
/*      */   public synchronized int I()
/*      */   {
/*  966 */     return ø;
/*      */   }
/*      */ 
/*      */   public String O()
/*      */   {
/*  975 */     return Ď;
/*      */   }
/*      */ 
/*      */   public String Q()
/*      */   {
/*  984 */     return this.ă;
/*      */   }
/*      */ 
/*      */   public int F()
/*      */   {
/*  993 */     return this.Î.B();
/*      */   }
/*      */ 
/*      */   public Date N()
/*      */   {
/* 1002 */     return this.ĕ;
/*      */   }
/*      */ 
/*      */   public int B()
/*      */   {
/* 1013 */     return 2;
/*      */   }
/*      */ 
/*      */   public String L()
/*      */   {
/* 1022 */     return this.u;
/*      */   }
/*      */ 
/*      */   public long E()
/*      */   {
/* 1033 */     return this.¤;
/*      */   }
/*      */ 
/*      */   public long G()
/*      */   {
/* 1044 */     return this.Ñ;
/*      */   }
/*      */ 
/*      */   public void A(int paramInt)
/*      */   {
/* 1056 */     D(paramInt);
/*      */   }
/*      */ 
/*      */   public static void D(int paramInt)
/*      */   {
/* 1068 */     Contract.pre((paramInt >= 0) && (paramInt <= 3));
/* 1069 */     ę = paramInt;
/*      */   }
/*      */ 
/*      */   public final String K()
/*      */   {
/* 1078 */     return "10.0.0";
/*      */   }
/*      */ 
/*      */   public String S()
/*      */   {
/* 1087 */     return this.À;
/*      */   }
/*      */ 
/*      */   public boolean M()
/*      */   {
/* 1096 */     return Z() == 1;
/*      */   }
/*      */ 
/*      */   public boolean A()
/*      */   {
/* 1105 */     return Z() == 2;
/*      */   }
/*      */ 
/*      */   public boolean H()
/*      */   {
/* 1114 */     return Z() == 3;
/*      */   }
/*      */ 
/*      */   public final long getLastHistoryPageNumber(String paramString1, String paramString2)
/*      */   {
/* 1129 */     Contract.unreachable();
/* 1130 */     return 0L;
/*      */   }
/*      */ 
/*      */   public final long getLastGlucoseHistoryPageNumber(String paramString1, String paramString2)
/*      */   {
/* 1145 */     Contract.unreachable();
/* 1146 */     return 0L;
/*      */   }
/*      */ 
/*      */   public void allowDeviceOperation(G paramG)
/*      */     throws P
/*      */   {
/*      */   }
/*      */ 
/*      */   public void deviceUpdateProgress(int paramInt)
/*      */   {
/* 1168 */     C(paramInt);
/*      */   }
/*      */ 
/*      */   public void deviceUpdateState(int paramInt, String paramString)
/*      */   {
/* 1180 */     if ((paramInt != 1) && (paramInt != 2))
/*      */     {
/* 1182 */       B(paramInt);
/*      */     }
/*      */   }
/*      */ 
/*      */   public void deviceUpdatePhase(int paramInt, String paramString)
/*      */   {
/*      */   }
/*      */ 
/*      */   private String A(v paramv, String[] paramArrayOfString)
/*      */     throws t
/*      */   {
/* 1210 */     String str1 = null;
/* 1211 */     StringBuffer localStringBuffer = new StringBuffer();
/* 1212 */     int i1 = 0;
/*      */ 
/* 1214 */     B(false);
/* 1215 */     B(paramv);
/* 1216 */     E(1);
/*      */ 
/* 1220 */     String str2 = I.A(this.đ);
/* 1221 */     if (str2.equals("UNKNOWN")) {
/* 1222 */       str2 = this.ć;
/*      */     }
/*      */ 
/* 1226 */     for (int i2 = 0; (i2 < paramArrayOfString.length) && (i1 == 0) && (!isHaltRequested()); i2++)
/*      */     {
/*      */       try
/*      */       {
/* 1230 */         str1 = paramArrayOfString[(paramArrayOfString.length - i2 - 1)];
/* 1231 */         if (i2 > 0)
/*      */         {
/* 1233 */           localStringBuffer.append(", ");
/*      */         }
/*      */ 
/* 1236 */         localStringBuffer.append(str1);
/* 1237 */         A(this, "autoDetectDeviceIO: testing port '" + str1 + "'");
/*      */ 
/* 1240 */         String str3 = str1;
/* 1241 */         D(str3);
/*      */         try
/*      */         {
/* 1245 */           if (!isHaltRequested()) {
/* 1246 */             E(7);
/* 1247 */             C(paramv);
/*      */ 
/* 1249 */             i1 = 1;
/* 1250 */             A(this, "autoDetectDeviceIO: " + str2 + " found on port '" + str1 + "'");
/*      */           }
/*      */         }
/*      */         catch (t localt)
/*      */         {
/*      */         }
/*      */       }
/*      */       catch (IOException localIOException)
/*      */       {
/* 1259 */         A(this, "autoDetectDeviceIO: " + str2 + " NOT found on port '" + str1 + "'; " + "e=" + localIOException);
/*      */       }
/*      */       catch (W localW) {
/* 1262 */         U();
/* 1263 */         throw new t("autoDetectDeviceIO: detection has been Halted...");
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1268 */     U();
/*      */ 
/* 1270 */     if (isHaltRequested()) {
/* 1271 */       throw new t("autoDetectDeviceIO: detection has been Halted...");
/*      */     }
/*      */ 
/* 1275 */     if (i1 == 0) {
/* 1276 */       throw new t("autoDetectDeviceIO: could not find " + str2 + " on any port in list '" + localStringBuffer + "'.");
/*      */     }
/*      */ 
/* 1281 */     return str1;
/*      */   }
/*      */ 
/*      */   void C(String paramString)
/*      */     throws IOException
/*      */   {
/* 1292 */     this.u = paramString;
/* 1293 */     E(2);
/*      */ 
/* 1295 */     if (Y() != null)
/* 1296 */       Y().B();
/*      */   }
/*      */ 
/*      */   abstract int Z();
/*      */ 
/*      */   abstract void C(v paramv)
/*      */     throws t, IOException;
/*      */ 
/*      */   abstract void D(String paramString)
/*      */     throws IOException;
/*      */ 
/*      */   abstract void _()
/*      */     throws IOException;
/*      */ 
/*      */   final synchronized void E(int paramInt)
/*      */   {
/* 1341 */     Contract.pre((paramInt >= 1) && (paramInt <= 7));
/*      */ 
/* 1344 */     if (paramInt != ø) {
/* 1345 */       ø = paramInt;
/* 1346 */       Ď = B(K[ø]);
/* 1347 */       A(ø, Ď);
/* 1348 */       C(this, "setPhase: phase is now " + ø + " (" + Ď + ")");
/*      */     }
/*      */   }
/*      */ 
/*      */   final synchronized void B(int paramInt)
/*      */   {
/* 1360 */     Contract.pre((paramInt >= 1) && (paramInt <= 9));
/*      */ 
/* 1362 */     if (paramInt != Ì) {
/* 1363 */       Ì = paramInt;
/* 1364 */       ē = H[Ì];
/* 1365 */       B(Ì, ē);
/* 1366 */       A(this, "setState: state is now " + Ì + " (" + ē + ")");
/*      */     }
/*      */   }
/*      */ 
/*      */   final synchronized int T()
/*      */   {
/* 1376 */     return Ì;
/*      */   }
/*      */ 
/*      */   final CA X()
/*      */   {
/* 1385 */     return this.Î;
/*      */   }
/*      */ 
/*      */   static void A(Object paramObject, String paramString)
/*      */   {
/* 1396 */     if (ę >= 2)
/* 1397 */       A(paramObject, paramString, "INFO");
/*      */   }
/*      */ 
/*      */   static void B(Object paramObject, String paramString)
/*      */   {
/* 1409 */     if (ę >= 3)
/* 1410 */       A(paramObject, paramString, "INFO");
/*      */   }
/*      */ 
/*      */   static void C(Object paramObject, String paramString)
/*      */   {
/* 1422 */     if (ę >= 1)
/* 1423 */       A(paramObject, paramString, "INFO");
/*      */   }
/*      */ 
/*      */   static void D(Object paramObject, String paramString)
/*      */   {
/* 1434 */     if (ę >= 2)
/* 1435 */       A(paramObject, paramString, "WARNING");
/*      */   }
/*      */ 
/*      */   static void E(Object paramObject, String paramString)
/*      */   {
/* 1446 */     if (ę >= 1)
/* 1447 */       A(paramObject, paramString, "ERROR");
/*      */   }
/*      */ 
/*      */   static void A(boolean paramBoolean)
/*      */   {
/* 1460 */     Ć = paramBoolean;
/*      */   }
/*      */ 
/*      */   static void A(PrintWriter paramPrintWriter)
/*      */   {
/* 1469 */     p = paramPrintWriter;
/*      */   }
/*      */ 
/*      */   final Date A(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
/*      */     throws Z
/*      */   {
/* 1488 */     B(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6);
/* 1489 */     GregorianCalendar localGregorianCalendar = new GregorianCalendar(_B.E(paramInt3), paramInt2 - 1, paramInt1, paramInt4, paramInt5, paramInt6);
/*      */ 
/* 1492 */     return localGregorianCalendar.getTime();
/*      */   }
/*      */ 
/*      */   void B(v paramv)
/*      */   {
/* 1503 */     Contract.pre(paramv != null);
/*      */ 
/* 1505 */     this.j.addElement(paramv);
/*      */   }
/*      */ 
/*      */   void U()
/*      */   {
/* 1514 */     this.j.removeAllElements();
/*      */   }
/*      */ 
/*      */   void C(int paramInt)
/*      */   {
/* 1526 */     if ((ø != 4) && 
/* 1527 */       (paramInt >= 0) && (paramInt <= 100))
/* 1528 */       for (int i1 = 0; i1 < this.j.size(); i1++) {
/* 1529 */         v localv = (v)this.j.elementAt(i1);
/* 1530 */         localv.deviceUpdateProgress(paramInt);
/*      */       }
/*      */   }
/*      */ 
/*      */   void A(int paramInt1, int paramInt2)
/*      */   {
/* 1545 */     double d = paramInt1 / paramInt2 * 100.0D;
/*      */ 
/* 1547 */     C((int)d);
/*      */   }
/*      */ 
/*      */   void B(int paramInt, String paramString)
/*      */   {
/* 1557 */     for (int i1 = 0; i1 < this.j.size(); i1++) {
/* 1558 */       v localv = (v)this.j.elementAt(i1);
/* 1559 */       localv.deviceUpdateState(paramInt, paramString);
/*      */     }
/*      */   }
/*      */ 
/*      */   void A(int paramInt, String paramString)
/*      */   {
/* 1570 */     for (int i1 = 0; i1 < this.j.size(); i1++) {
/* 1571 */       v localv = (v)this.j.elementAt(i1);
/* 1572 */       localv.deviceUpdatePhase(paramInt, paramString);
/*      */     }
/*      */   }
/*      */ 
/*      */   final String B(String paramString)
/*      */   {
/* 1583 */     String str1 = (this.đ == 15) || (this.đ == 14) ? this.u : "USB";
/*      */ 
/* 1587 */     String str2 = I.A(this.đ);
/*      */ 
/* 1589 */     paramString = paramString.replaceAll("@commport", str1);
/* 1590 */     paramString = paramString.replaceAll("@linkdevice", str2 == null ? "?" : str2);
/* 1591 */     paramString = paramString.replaceAll("@serialnumber", this.ă == null ? "?" : this.ă);
/* 1592 */     return paramString;
/*      */   }
/*      */ 
/*      */   void A(int[] paramArrayOfInt)
/*      */   {
/* 1603 */     Contract.pre(paramArrayOfInt != null);
/*      */ 
/* 1605 */     this.ă = new String(_B.B(paramArrayOfInt));
/* 1606 */     this.ă = this.ă.trim();
/* 1607 */     A(this, "decodeSerialNumber: serial number is " + this.ă);
/*      */   }
/*      */ 
/*      */   void B(int[] paramArrayOfInt)
/*      */   {
/* 1618 */     Contract.pre(paramArrayOfInt != null);
/*      */ 
/* 1620 */     this.Ă = new String(_B.B(paramArrayOfInt));
/* 1621 */     this.Ă = this.Ă.trim();
/* 1622 */     A(this, "decodeFirmwareVersion: firmware version is " + this.Ă);
/*      */   }
/*      */ 
/*      */   final z W()
/*      */   {
/* 1631 */     return this.Ó;
/*      */   }
/*      */ 
/*      */   final void A(z paramz)
/*      */   {
/* 1640 */     this.Ó = paramz;
/*      */   }
/*      */ 
/*      */   private void B(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
/*      */     throws Z
/*      */   {
/* 1659 */     _B.A(paramInt1, 1, 31, "day");
/* 1660 */     _B.A(paramInt2, 1, 12, "month");
/* 1661 */     _B.A(paramInt3, this.à, this.Ð, "year");
/* 1662 */     _B.A(paramInt4, 0, 23, "hour");
/* 1663 */     _B.A(paramInt5, 0, 59, "minute");
/* 1664 */     _B.A(paramInt6, 0, 59, "second");
/*      */   }
/*      */ 
/*      */   private static synchronized void A(Object paramObject, String paramString1, String paramString2)
/*      */   {
/* 1678 */     String str1 = _B.E(paramString1);
/* 1679 */     if (p != null) {
/* 1680 */       Date localDate = new Date();
/* 1681 */       String str2 = l.format(localDate);
/* 1682 */       p.println(str2 + " " + paramString2 + " " + paramObject.getClass().getName() + "-" + str1);
/*      */     }
/*      */   }
/*      */ 
/*      */   final synchronized void B(boolean paramBoolean)
/*      */   {
/* 1694 */     n = paramBoolean;
/*      */   }
/*      */ 
/*      */   public final synchronized boolean isHaltRequested()
/*      */   {
/* 1704 */     return n;
/*      */   }
/*      */ 
/*      */   void A(d paramd)
/*      */   {
/* 1713 */     W().A(paramd);
/*      */   }
/*      */ 
/*      */   d Y()
/*      */   {
/* 1722 */     return (d)W().C();
/*      */   }
/*      */ 
/*      */   void A(c paramc)
/*      */   {
/* 1731 */     W().A(paramc);
/*      */   }
/*      */ 
/*      */   c V()
/*      */   {
/* 1740 */     return W().C();
/*      */   }
/*      */ 
/*      */   static
/*      */   {
/*  690 */     l = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss:SSSS");
/*      */   }
/*      */ 
/*      */   static final class _B
/*      */   {
/* 1809 */     private static final Calendar A = Calendar.getInstance();
/*      */ 
/*      */     static int[] C(int[] paramArrayOfInt)
/*      */     {
/* 1824 */       int[] arrayOfInt = new int[paramArrayOfInt.length];
/* 1825 */       for (int i = 0; i < arrayOfInt.length; i++) {
/* 1826 */         arrayOfInt[i] = paramArrayOfInt[i];
/*      */       }
/* 1828 */       return arrayOfInt;
/*      */     }
/*      */ 
/*      */     static void G(int paramInt)
/*      */     {
/*      */       try
/*      */       {
/* 1838 */         Thread.sleep(paramInt);
/*      */       }
/*      */       catch (InterruptedException localInterruptedException)
/*      */       {
/*      */       }
/*      */     }
/*      */ 
/*      */     static int D(int paramInt)
/*      */     {
/* 1852 */       Contract.pre((paramInt >= 0) && (paramInt <= 255));
/*      */ 
/* 1854 */       return paramInt & 0xF;
/*      */     }
/*      */ 
/*      */     static int M(int paramInt)
/*      */     {
/* 1866 */       Contract.pre((paramInt >= 0) && (paramInt <= 255));
/*      */ 
/* 1868 */       return paramInt >> 4 & 0xF;
/*      */     }
/*      */ 
/*      */     static int K(int paramInt)
/*      */     {
/* 1878 */       return paramInt & 0xFF;
/*      */     }
/*      */ 
/*      */     static int J(int paramInt)
/*      */     {
/* 1889 */       return paramInt >>> 8 & 0xFF;
/*      */     }
/*      */ 
/*      */     static int A(String paramString)
/*      */     {
/* 1901 */       return Integer.valueOf(paramString, 16).intValue();
/*      */     }
/*      */ 
/*      */     static int L(int paramInt)
/*      */     {
/* 1913 */       Contract.pre((paramInt >= 0) && (paramInt <= 15));
/*      */ 
/* 1915 */       return paramInt < 10 ? paramInt + 48 : paramInt - 10 + 65;
/*      */     }
/*      */ 
/*      */     static String E(String paramString)
/*      */     {
/* 1926 */       StringBuffer localStringBuffer = new StringBuffer();
/*      */ 
/* 1930 */       for (int j = 0; j < paramString.length(); j++) {
/* 1931 */         int i = paramString.charAt(j);
/*      */ 
/* 1933 */         if ((i < 32) || (i > 127));
/* 1935 */         switch (i)
/*      */         {
/*      */         case 9:
/* 1938 */           localStringBuffer.append(paramString.charAt(j));
/* 1939 */           localStringBuffer.append("<TAB>");
/* 1940 */           break;
/*      */         case 10:
/* 1942 */           localStringBuffer.append("<LF>");
/* 1943 */           break;
/*      */         case 0:
/* 1945 */           localStringBuffer.append("<NUL>");
/* 1946 */           break;
/*      */         case 6:
/* 1948 */           localStringBuffer.append("<ACK>");
/* 1949 */           break;
/*      */         case 21:
/* 1951 */           localStringBuffer.append("<NAK>");
/* 1952 */           break;
/*      */         case 13:
/* 1954 */           localStringBuffer.append("<CR>");
/* 1955 */           break;
/*      */         case 32:
/* 1957 */           localStringBuffer.append("<SP>");
/* 1958 */           break;
/*      */         case 2:
/* 1960 */           localStringBuffer.append("<STX>");
/* 1961 */           break;
/*      */         case 1:
/* 1963 */           localStringBuffer.append("<STH>");
/* 1964 */           break;
/*      */         case 5:
/* 1966 */           localStringBuffer.append("<ENQ>");
/* 1967 */           break;
/*      */         case 4:
/* 1969 */           localStringBuffer.append("<EOT>");
/* 1970 */           break;
/*      */         case 23:
/* 1972 */           localStringBuffer.append("<ETB>");
/* 1973 */           break;
/*      */         case 3:
/* 1975 */           localStringBuffer.append("<ETX>");
/* 1976 */           break;
/*      */         case 7:
/*      */         case 8:
/*      */         case 11:
/*      */         case 12:
/*      */         case 14:
/*      */         case 15:
/*      */         case 16:
/*      */         case 17:
/*      */         case 18:
/*      */         case 19:
/*      */         case 20:
/*      */         case 22:
/*      */         case 24:
/*      */         case 25:
/*      */         case 26:
/*      */         case 27:
/*      */         case 28:
/*      */         case 29:
/*      */         case 30:
/*      */         case 31:
/*      */         default:
/* 1979 */           String str = '<' + H(i) + '>';
/* 1980 */           localStringBuffer.append(str);
/* 1981 */           continue;
/*      */ 
/* 1984 */           localStringBuffer.append(paramString.charAt(j));
/*      */         }
/*      */       }
/* 1987 */       return new String(localStringBuffer);
/*      */     }
/*      */ 
/*      */     static boolean A(int paramInt, String paramString)
/*      */       throws Z
/*      */     {
/* 2000 */       A(paramInt, 0, 1, paramString);
/* 2001 */       return paramInt == 1;
/*      */     }
/*      */ 
/*      */     static int[] B(String paramString)
/*      */     {
/* 2016 */       Contract.preNonNull(paramString);
/* 2017 */       Contract.pre(C(paramString.length()));
/* 2018 */       paramString = paramString.toLowerCase();
/* 2019 */       for (int i = 0; i < paramString.length(); i++) {
/* 2020 */         char c1 = paramString.charAt(i);
/* 2021 */         Contract.pre((Character.isDigit(c1)) || ((c1 >= 'a') && (c1 <= 'f')));
/*      */       }
/*      */ 
/* 2024 */       int[] arrayOfInt1 = D(paramString);
/* 2025 */       int[] arrayOfInt2 = new int[arrayOfInt1.length / 2];
/*      */ 
/* 2027 */       for (int j = 0; j < arrayOfInt2.length; j++) {
/* 2028 */         char c2 = (char)arrayOfInt1[(j * 2)];
/* 2029 */         char c3 = (char)arrayOfInt1[(j * 2 + 1)];
/* 2030 */         int k = c2 - (Character.isDigit(c2) ? '0' : 'W');
/* 2031 */         int m = c3 - (Character.isDigit(c3) ? '0' : 'W');
/* 2032 */         arrayOfInt2[j] = C(k, m);
/*      */       }
/*      */ 
/* 2035 */       return arrayOfInt2;
/*      */     }
/*      */ 
/*      */     static int C(int paramInt1, int paramInt2)
/*      */     {
/* 2051 */       Contract.pre((paramInt1 >= 0) && (paramInt1 <= 15));
/* 2052 */       Contract.pre((paramInt2 >= 0) && (paramInt2 <= 15));
/*      */ 
/* 2054 */       int i = paramInt1 << 4 | paramInt2 & 0xF;
/*      */ 
/* 2056 */       Contract.post((i >= 0) && (i <= 255));
/*      */ 
/* 2058 */       return i;
/*      */     }
/*      */ 
/*      */     static long A(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*      */     {
/* 2077 */       Contract.pre((paramInt1 >= 0) && (paramInt1 <= 255));
/* 2078 */       Contract.pre((paramInt2 >= 0) && (paramInt2 <= 255));
/* 2079 */       Contract.pre((paramInt3 >= 0) && (paramInt3 <= 255));
/* 2080 */       Contract.pre((paramInt4 >= 0) && (paramInt4 <= 255));
/*      */ 
/* 2083 */       long l = paramInt1 << 24 | paramInt2 << 16 | paramInt3 << 8 | paramInt4;
/*      */ 
/* 2088 */       Contract.post((l >= 0L) && (l <= 4294967295L));
/*      */ 
/* 2090 */       return l;
/*      */     }
/*      */ 
/*      */     static int D(long paramLong)
/*      */     {
/* 2100 */       return (int)(paramLong & 0xFFFF);
/*      */     }
/*      */ 
/*      */     static int C(long paramLong)
/*      */     {
/* 2111 */       return (int)(paramLong >>> 16) & 0xFFFF;
/*      */     }
/*      */ 
/*      */     static double B(int paramInt)
/*      */     {
/* 2121 */       return paramInt / 10.0D;
/*      */     }
/*      */ 
/*      */     static int A(int paramInt1, int paramInt2)
/*      */     {
/* 2135 */       Contract.pre((paramInt1 >= 0) && (paramInt1 <= 255));
/* 2136 */       Contract.pre((paramInt2 >= 0) && (paramInt2 <= 255));
/*      */ 
/* 2138 */       int i = (paramInt1 & 0xFF) << 8 | paramInt2 & 0xFF;
/*      */ 
/* 2140 */       Contract.post((i >= 0) && (i <= 65535));
/* 2141 */       return i;
/*      */     }
/*      */ 
/*      */     static int A(int paramInt1, int paramInt2, int paramInt3)
/*      */     {
/* 2158 */       Contract.pre((paramInt1 >= 0) && (paramInt1 <= 255));
/* 2159 */       Contract.pre((paramInt2 >= 0) && (paramInt2 <= 255));
/* 2160 */       Contract.pre((paramInt3 >= 0) && (paramInt3 <= 255));
/*      */ 
/* 2162 */       int i = (paramInt1 & 0xFF) << 16 | (paramInt2 & 0xFF) << 8 | paramInt3 & 0xFF;
/*      */ 
/* 2165 */       Contract.post((i >= 0) && (i <= 16777215));
/*      */ 
/* 2167 */       return i;
/*      */     }
/*      */ 
/*      */     static int C(byte paramByte)
/*      */     {
/* 2180 */       return paramByte & 0xFF;
/*      */     }
/*      */ 
/*      */     static long A(int paramInt)
/*      */     {
/* 2193 */       return paramInt & 0xFFFFFFFF;
/*      */     }
/*      */ 
/*      */     static byte[] A(int[] paramArrayOfInt)
/*      */     {
/* 2205 */       Contract.preNonNull(paramArrayOfInt);
/* 2206 */       byte[] arrayOfByte = new byte[paramArrayOfInt.length];
/*      */ 
/* 2209 */       for (int i = 0; i < paramArrayOfInt.length; i++) {
/* 2210 */         Contract.pre(paramArrayOfInt[i] >> 8 == 0);
/* 2211 */         arrayOfByte[i] = (byte)(paramArrayOfInt[i] & 0xFF);
/*      */       }
/* 2213 */       return arrayOfByte;
/*      */     }
/*      */ 
/*      */     static int B(int paramInt1, int paramInt2)
/*      */     {
/* 2227 */       Contract.pre((paramInt1 >= 0) && (paramInt1 <= 255));
/* 2228 */       Contract.pre((paramInt2 >= 0) && (paramInt2 <= 255));
/*      */ 
/* 2230 */       int i = (paramInt1 & 0xFF) << 8 | paramInt2 & 0xFF;
/*      */ 
/* 2232 */       Contract.post((i >= 0) && (i <= 65535));
/* 2233 */       return i;
/*      */     }
/*      */ 
/*      */     static byte[] B(int[] paramArrayOfInt)
/*      */     {
/* 2244 */       if (paramArrayOfInt != null) {
/* 2245 */         byte[] arrayOfByte = new byte[paramArrayOfInt.length];
/*      */ 
/* 2247 */         for (int i = 0; i < paramArrayOfInt.length; i++) {
/* 2248 */           Contract.pre(paramArrayOfInt[i], -128, 127);
/* 2249 */           arrayOfByte[i] = (byte)paramArrayOfInt[i];
/*      */         }
/* 2251 */         return arrayOfByte;
/*      */       }
/* 2253 */       return null;
/*      */     }
/*      */ 
/*      */     static int[] C(byte[] paramArrayOfByte)
/*      */     {
/* 2264 */       Contract.preNonNull(paramArrayOfByte);
/* 2265 */       int[] arrayOfInt = new int[paramArrayOfByte.length];
/*      */ 
/* 2268 */       for (int i = 0; i < paramArrayOfByte.length; i++) {
/* 2269 */         paramArrayOfByte[i] &= 255;
/*      */       }
/* 2271 */       return arrayOfInt;
/*      */     }
/*      */ 
/*      */     static String E(int[] paramArrayOfInt)
/*      */     {
/* 2282 */       return B(paramArrayOfInt, 0, paramArrayOfInt.length);
/*      */     }
/*      */ 
/*      */     static String B(int[] paramArrayOfInt, int paramInt1, int paramInt2)
/*      */     {
/* 2296 */       Contract.pre(paramArrayOfInt.length >= paramInt1 + paramInt2);
/* 2297 */       if (paramArrayOfInt != null) {
/* 2298 */         StringBuffer localStringBuffer = new StringBuffer();
/*      */ 
/* 2300 */         for (int i = paramInt1; i < paramInt1 + paramInt2; i++)
/*      */         {
/* 2302 */           if (paramArrayOfInt[i] != 0) {
/* 2303 */             localStringBuffer.append((char)paramArrayOfInt[i]);
/*      */           }
/*      */         }
/*      */ 
/* 2307 */         return new String(localStringBuffer);
/*      */       }
/* 2309 */       return null;
/*      */     }
/*      */ 
/*      */     static int[] D(String paramString)
/*      */     {
/* 2320 */       if (paramString != null) {
/* 2321 */         int[] arrayOfInt = new int[paramString.length()];
/*      */ 
/* 2323 */         for (int i = 0; i < arrayOfInt.length; i++) {
/* 2324 */           arrayOfInt[i] = paramString.charAt(i);
/*      */         }
/* 2326 */         return arrayOfInt;
/*      */       }
/* 2328 */       return null;
/*      */     }
/*      */ 
/*      */     static int[] A(int[] paramArrayOfInt1, int[] paramArrayOfInt2)
/*      */     {
/* 2343 */       Contract.pre(paramArrayOfInt1 != null);
/* 2344 */       Contract.pre(paramArrayOfInt2 != null);
/*      */ 
/* 2346 */       int[] arrayOfInt = new int[paramArrayOfInt1.length + paramArrayOfInt2.length];
/* 2347 */       System.arraycopy(paramArrayOfInt1, 0, arrayOfInt, 0, paramArrayOfInt1.length);
/* 2348 */       System.arraycopy(paramArrayOfInt2, 0, arrayOfInt, paramArrayOfInt1.length, paramArrayOfInt2.length);
/* 2349 */       return arrayOfInt;
/*      */     }
/*      */ 
/*      */     static String A(String paramString1, String paramString2)
/*      */       throws Z
/*      */     {
/* 2365 */       int i = paramString1.indexOf(paramString2);
/*      */ 
/* 2367 */       if (i != -1) {
/* 2368 */         int j = i + paramString2.length();
/* 2369 */         if (paramString1.length() > j) {
/* 2370 */           return paramString1.substring(j);
/*      */         }
/* 2372 */         throw new Z("Nothing following '" + paramString2 + "' in string '" + paramString1 + "'");
/*      */       }
/*      */ 
/* 2376 */       throw new Z("Can't find '" + paramString2 + "' in string '" + paramString1 + "'");
/*      */     }
/*      */ 
/*      */     static int E(int[] paramArrayOfInt, int paramInt1, int paramInt2)
/*      */     {
/* 2394 */       int i = 127;
/*      */ 
/* 2396 */       Contract.pre(paramArrayOfInt != null);
/* 2397 */       Contract.pre(paramArrayOfInt.length >= paramInt1 + paramInt2);
/*      */ 
/* 2399 */       for (int j = 0; j < paramInt2; j++) {
/* 2400 */         i = O.Â[i] ^ paramArrayOfInt[(j + paramInt1)];
/*      */       }
/*      */ 
/* 2403 */       return i;
/*      */     }
/*      */ 
/*      */     static int F(int[] paramArrayOfInt, int paramInt1, int paramInt2)
/*      */     {
/* 2422 */       int i = 0;
/*      */ 
/* 2424 */       Contract.pre(paramArrayOfInt != null);
/* 2425 */       Contract.pre(paramArrayOfInt.length >= paramInt1 + paramInt2);
/*      */ 
/* 2427 */       for (int j = 0; j < paramInt2; j++) {
/* 2428 */         i = O.m[((i ^ paramArrayOfInt[(j + paramInt1)]) & 0xFF)];
/*      */       }
/*      */ 
/* 2431 */       return i;
/*      */     }
/*      */ 
/*      */     static int G(int[] paramArrayOfInt, int paramInt1, int paramInt2)
/*      */     {
/* 2445 */       Contract.pre(paramArrayOfInt != null);
/* 2446 */       Contract.pre(paramArrayOfInt.length >= paramInt1 + paramInt2);
/* 2447 */       int i = 0;
/*      */ 
/* 2449 */       for (int j = 0; j < paramInt2; j++) {
/* 2450 */         i += paramArrayOfInt[(j + paramInt1)];
/* 2451 */         i &= 255;
/*      */       }
/* 2453 */       return i;
/*      */     }
/*      */ 
/*      */     static int A(int[] paramArrayOfInt, int paramInt1, int paramInt2)
/*      */     {
/* 2474 */       int i = 0;
/*      */ 
/* 2476 */       Contract.pre(paramArrayOfInt != null);
/* 2477 */       Contract.pre(paramArrayOfInt.length >= paramInt1 + paramInt2);
/*      */ 
/* 2479 */       for (int j = 0; j < paramInt2; j++) {
/* 2480 */         i = O.o[((i ^ paramArrayOfInt[(j + paramInt1)]) & 0xFF)];
/*      */       }
/*      */ 
/* 2483 */       return i;
/*      */     }
/*      */ 
/*      */     static int H(int[] paramArrayOfInt)
/*      */     {
/* 2499 */       int i = 0;
/*      */ 
/* 2501 */       Contract.pre(paramArrayOfInt != null);
/* 2502 */       Contract.pre(paramArrayOfInt.length >= 2);
/*      */ 
/* 2504 */       int j = 0;
/* 2505 */       int k = 0;
/* 2506 */       int m = 0;
/* 2507 */       int n = 0;
/*      */ 
/* 2509 */       for (int i1 = 0; (i1 < paramArrayOfInt.length) && (m == 0); i1++) {
/* 2510 */         switch (paramArrayOfInt[i1]) {
/*      */         case 2:
/* 2512 */           j = 1;
/* 2513 */           break;
/*      */         case 23:
/* 2515 */           k = 1;
/* 2516 */           n = paramArrayOfInt[i1];
/* 2517 */           break;
/*      */         case 3:
/* 2519 */           k = 1;
/* 2520 */           n = paramArrayOfInt[i1];
/* 2521 */           break;
/*      */         default:
/* 2523 */           if ((j != 0) && (k == 0))
/*      */           {
/* 2525 */             i += paramArrayOfInt[i1];
/* 2526 */             i &= 255; } else {
/* 2527 */             if ((j == 0) || (k == 0))
/*      */               continue;
/* 2529 */             i += n;
/* 2530 */             i &= 255;
/* 2531 */             m = 1;
/*      */           }
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/* 2537 */       return i;
/*      */     }
/*      */ 
/*      */     static int C(int[] paramArrayOfInt, int paramInt1, int paramInt2)
/*      */     {
/* 2553 */       Contract.pre(paramArrayOfInt != null);
/* 2554 */       Contract.pre(paramArrayOfInt.length >= paramInt1 + paramInt2);
/*      */ 
/* 2556 */       int i = 65535;
/*      */ 
/* 2558 */       for (int k = paramInt1; k < paramInt1 + paramInt2; k++) {
/* 2559 */         int j = paramArrayOfInt[k] ^ i >> 8;
/* 2560 */         i = (O.ĉ[j] ^ i << 8) & 0xFFFF;
/*      */       }
/*      */ 
/* 2564 */       return i;
/*      */     }
/*      */ 
/*      */     static int D(int[] paramArrayOfInt, int paramInt1, int paramInt2)
/*      */     {
/* 2579 */       Contract.pre(paramArrayOfInt != null);
/* 2580 */       Contract.pre(paramArrayOfInt.length >= paramInt1 + paramInt2);
/*      */ 
/* 2582 */       int i = 0;
/*      */ 
/* 2584 */       for (int j = paramInt1; j < paramInt1 + paramInt2; j++) {
/* 2585 */         i = i + paramArrayOfInt[j] & 0xFFFF;
/*      */       }
/*      */ 
/* 2588 */       return i;
/*      */     }
/*      */ 
/*      */     static int A(int paramInt1, String paramString, int paramInt2, int paramInt3)
/*      */     {
/* 2604 */       Contract.preNonNull(paramString);
/* 2605 */       Contract.pre(paramString.length() >= paramInt2 + paramInt3);
/*      */ 
/* 2607 */       int i = paramInt1;
/*      */ 
/* 2609 */       for (int j = paramInt2; j < paramInt2 + paramInt3; j++) {
/* 2610 */         i = (i ^ paramString.charAt(j)) & 0xFF;
/*      */       }
/* 2612 */       return i;
/*      */     }
/*      */ 
/*      */     static boolean C(String paramString)
/*      */     {
/* 2626 */       int i = 0;
/*      */ 
/* 2628 */       Contract.pre(paramString != null);
/* 2629 */       Contract.pre(paramString.length() >= 2);
/*      */       String str;
/*      */       try {
/* 2636 */         str = A(paramString, String.valueOf('\003'));
/*      */       }
/*      */       catch (Z localZ1) {
/*      */         try {
/* 2640 */           str = A(paramString, String.valueOf('\027'));
/*      */         } catch (Z localZ2) {
/* 2642 */           return i;
/*      */         }
/*      */       }
/*      */ 
/* 2646 */       if (str.length() > 1) {
/* 2647 */         int j = str.charAt(0);
/* 2648 */         int k = str.charAt(1);
/* 2649 */         int m = H(D(paramString));
/* 2650 */         int n = L(M(m));
/* 2651 */         int i1 = L(D(m));
/*      */ 
/* 2653 */         if ((n == j) && (i1 == k)) {
/* 2654 */           i = 1;
/*      */         }
/*      */       }
/*      */ 
/* 2658 */       return i;
/*      */     }
/*      */ 
/*      */     static boolean C(int paramInt)
/*      */     {
/* 2668 */       return paramInt % 2 == 0;
/*      */     }
/*      */ 
/*      */     static boolean I(int paramInt)
/*      */     {
/* 2678 */       return !C(paramInt);
/*      */     }
/*      */ 
/*      */     static boolean F(int[] paramArrayOfInt)
/*      */     {
/* 2689 */       if (paramArrayOfInt != null) {
/* 2690 */         for (int i = 0; i < paramArrayOfInt.length; i++) {
/* 2691 */           if (paramArrayOfInt[i] != 0) {
/* 2692 */             return false;
/*      */           }
/*      */         }
/* 2695 */         return true;
/*      */       }
/* 2697 */       return false;
/*      */     }
/*      */ 
/*      */     static void A(int paramInt1, int paramInt2, int paramInt3, String paramString)
/*      */       throws Z
/*      */     {
/* 2712 */       if ((paramInt1 < paramInt2) || (paramInt1 > paramInt3))
/* 2713 */         throw new Z("The value of " + paramInt1 + " for '" + paramString + "' is out of range; " + "must be " + paramInt2 + " to " + paramInt3);
/*      */     }
/*      */ 
/*      */     static void A(double paramDouble1, double paramDouble2, double paramDouble3, String paramString)
/*      */       throws Z
/*      */     {
/* 2731 */       if ((paramDouble1 < paramDouble2) || (paramDouble1 > paramDouble3))
/* 2732 */         throw new Z("The value of " + paramDouble1 + " for '" + paramString + "' is out of range; " + "must be " + paramDouble2 + " to " + paramDouble3);
/*      */     }
/*      */ 
/*      */     static void A(boolean paramBoolean, String paramString)
/*      */       throws Z
/*      */     {
/* 2748 */       if (!paramBoolean)
/* 2749 */         throw new Z("Condition failed: '" + paramString + "'");
/*      */     }
/*      */ 
/*      */     static int E(int paramInt)
/*      */       throws Z
/*      */     {
/* 2763 */       A(paramInt, 0, 9999, "year");
/*      */ 
/* 2765 */       if ((paramInt == 97) || (paramInt == 98) || (paramInt == 99)) {
/* 2766 */         paramInt += 1900;
/*      */       }
/* 2768 */       return paramInt > 1000 ? paramInt : paramInt + 2000;
/*      */     }
/*      */ 
/*      */     static Date B(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
/*      */     {
/* 2790 */       Contract.pre(paramInt1, 1, 31);
/* 2791 */       Contract.pre(paramInt2, 1, 12);
/* 2792 */       Contract.pre(paramInt3, 2000, 2099);
/* 2793 */       Contract.pre(paramInt4, 0, 23);
/* 2794 */       Contract.pre(paramInt5, 0, 59);
/* 2795 */       Contract.pre(paramInt6, 0, 59);
/*      */ 
/* 2797 */       return A(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6);
/*      */     }
/*      */ 
/*      */     static long A()
/*      */     {
/* 2806 */       return System.currentTimeMillis();
/*      */     }
/*      */ 
/*      */     static long F(long paramLong)
/*      */     {
/* 2816 */       return A(paramLong) / 1000L;
/*      */     }
/*      */ 
/*      */     static long A(long paramLong)
/*      */     {
/* 2826 */       return System.currentTimeMillis() - paramLong;
/*      */     }
/*      */ 
/*      */     static String E(long paramLong)
/*      */     {
/* 2839 */       String str = paramLong == -1L ? "" : "0x";
/* 2840 */       return str + B(paramLong);
/*      */     }
/*      */ 
/*      */     static String H(int paramInt)
/*      */     {
/* 2853 */       String str = paramInt == -1 ? "" : "0x";
/* 2854 */       return str + F(paramInt);
/*      */     }
/*      */ 
/*      */     static String B(byte paramByte)
/*      */     {
/* 2867 */       String str = paramByte == -1 ? "" : "0x";
/* 2868 */       return str + A(paramByte);
/*      */     }
/*      */ 
/*      */     static String B(byte[] paramArrayOfByte, int paramInt)
/*      */     {
/* 2880 */       StringBuffer localStringBuffer = new StringBuffer();
/*      */ 
/* 2882 */       if (paramArrayOfByte != null)
/*      */       {
/* 2884 */         paramInt = Math.min(paramInt, paramArrayOfByte.length);
/*      */ 
/* 2886 */         for (int i = 0; i < paramInt; i++) {
/* 2887 */           localStringBuffer.append(B(paramArrayOfByte[i]));
/*      */ 
/* 2889 */           if (i < paramInt - 1) {
/* 2890 */             localStringBuffer.append(" ");
/*      */           }
/*      */         }
/*      */       }
/*      */ 
/* 2895 */       return new String(localStringBuffer);
/*      */     }
/*      */ 
/*      */     static String A(int[] paramArrayOfInt, int paramInt)
/*      */     {
/* 2909 */       StringBuffer localStringBuffer = new StringBuffer();
/*      */ 
/* 2911 */       if (paramArrayOfInt != null)
/*      */       {
/* 2913 */         paramInt = Math.min(paramInt, paramArrayOfInt.length);
/*      */ 
/* 2915 */         for (int i = 0; i < paramInt; i++) {
/* 2916 */           localStringBuffer.append(H(paramArrayOfInt[i]));
/*      */ 
/* 2918 */           if (i < paramInt - 1) {
/* 2919 */             localStringBuffer.append(" ");
/*      */           }
/*      */         }
/*      */       }
/*      */ 
/* 2924 */       return new String(localStringBuffer);
/*      */     }
/*      */ 
/*      */     static String A(byte[] paramArrayOfByte)
/*      */     {
/* 2935 */       return paramArrayOfByte == null ? null : B(paramArrayOfByte, paramArrayOfByte.length);
/*      */     }
/*      */ 
/*      */     static String D(int[] paramArrayOfInt)
/*      */     {
/* 2946 */       return paramArrayOfInt == null ? null : A(paramArrayOfInt, paramArrayOfInt.length);
/*      */     }
/*      */ 
/*      */     static String B(long paramLong)
/*      */     {
/* 2959 */       String str1 = Long.toHexString(paramLong).toUpperCase();
/*      */ 
/* 2961 */       String str2 = I(str1.length()) ? "0" : "";
/* 2962 */       return str2 + str1;
/*      */     }
/*      */ 
/*      */     static String F(int paramInt)
/*      */     {
/* 2975 */       long l = paramInt == -1 ? paramInt : A(paramInt);
/* 2976 */       return B(l);
/*      */     }
/*      */ 
/*      */     static String A(byte paramByte)
/*      */     {
/* 2989 */       int i = paramByte == -1 ? paramByte : C(paramByte);
/* 2990 */       return F(i);
/*      */     }
/*      */ 
/*      */     static String A(byte[] paramArrayOfByte, int paramInt)
/*      */     {
/* 3002 */       StringBuffer localStringBuffer = new StringBuffer();
/*      */ 
/* 3004 */       if (paramArrayOfByte != null)
/*      */       {
/* 3006 */         paramInt = Math.min(paramInt, paramArrayOfByte.length);
/*      */ 
/* 3008 */         for (int i = 0; i < paramInt; i++) {
/* 3009 */           localStringBuffer.append(A(paramArrayOfByte[i]));
/*      */ 
/* 3011 */           if (i < paramInt - 1) {
/* 3012 */             localStringBuffer.append(" ");
/*      */           }
/*      */         }
/*      */       }
/*      */ 
/* 3017 */       return new String(localStringBuffer);
/*      */     }
/*      */ 
/*      */     static String B(int[] paramArrayOfInt, int paramInt)
/*      */     {
/* 3031 */       StringBuffer localStringBuffer = new StringBuffer();
/*      */ 
/* 3033 */       if (paramArrayOfInt != null)
/*      */       {
/* 3035 */         paramInt = Math.min(paramInt, paramArrayOfInt.length);
/*      */ 
/* 3037 */         for (int i = 0; i < paramInt; i++) {
/* 3038 */           localStringBuffer.append(F(paramArrayOfInt[i]));
/*      */ 
/* 3040 */           if (i < paramInt - 1) {
/* 3041 */             localStringBuffer.append(" ");
/*      */           }
/*      */         }
/*      */       }
/*      */ 
/* 3046 */       return new String(localStringBuffer);
/*      */     }
/*      */ 
/*      */     static String B(byte[] paramArrayOfByte)
/*      */     {
/* 3057 */       if (paramArrayOfByte != null) {
/* 3058 */         return A(paramArrayOfByte, paramArrayOfByte.length);
/*      */       }
/* 3060 */       return null;
/*      */     }
/*      */ 
/*      */     static String G(int[] paramArrayOfInt)
/*      */     {
/* 3071 */       if (paramArrayOfInt != null) {
/* 3072 */         return B(paramArrayOfInt, paramArrayOfInt.length);
/*      */       }
/* 3074 */       return null;
/*      */     }
/*      */ 
/*      */     private static synchronized Date A(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
/*      */     {
/* 3092 */       A.clear();
/* 3093 */       A.set(paramInt3, paramInt2 - 1, paramInt1, paramInt4, paramInt5, paramInt6);
/* 3094 */       return A.getTime();
/*      */     }
/*      */   }
/*      */ 
/*      */   abstract class _A
/*      */   {
/*      */     _A(int arg2)
/*      */     {
/*      */       int i;
/* 1763 */       O.A(O.this, i);
/*      */     }
/*      */ 
/*      */     InputStream B()
/*      */       throws Z, IOException
/*      */     {
/* 1782 */       A();
/*      */ 
/* 1785 */       int i = O.this.Î.D();
/* 1786 */       O.A(this, "createSnapshot: wrote " + i + " bytes.");
/*      */ 
/* 1788 */       if (i < O.A(O.this)) {
/* 1789 */         throw new Z("Resulting snapshot size is invalid: " + i + "; must be at least " + O.A(O.this) + " bytes.");
/*      */       }
/*      */ 
/* 1793 */       return O.this.Î.E();
/*      */     }
/*      */ 
/*      */     abstract void A();
/*      */   }
/*      */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.A.O
 * JD-Core Version:    0.6.0
 */