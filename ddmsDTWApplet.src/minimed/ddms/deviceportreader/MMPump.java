/*      */ package minimed.ddms.A;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.util.Date;
/*      */ import java.util.Vector;
/*      */ import minimed.util.Contract;
/*      */ 
/*      */ abstract class M extends O
/*      */ {
/*      */   static final int Ƭ = 0;
/*      */   static final int Ķ = 1;
/*      */   static final int Ů = 2;
/*      */   static final int ŕ = 3;
/*      */   static final int Ɨ = 4;
/*      */   static final int ŋ = 5;
/*      */   static final int ƭ = 6;
/*      */   static final int ǟ = 7;
/*      */   static final int Ţ = 8;
/*      */   static final int ơ = 9;
/*      */   static final int Ɵ = 10;
/*      */   static final int ō = 11;
/*      */   static final int ǉ = 0;
/*      */   static final int ƙ = 2;
/*      */   static final int ƃ = 4;
/*      */   static final int Ƣ = 1;
/*      */   static final int Ŋ = 2;
/*      */   static final int Ľ = 3;
/*   72 */   static final String[] ƾ = { "STD", "A", "B", "UNKNOWN" };
/*      */   static final int Ŷ = 250;
/*      */   static final int ƶ = 100;
/*      */   static final int ĸ = 50;
/*      */   static final int ŉ = 40;
/*      */   static final double ů = 10.0D;
/*      */   static final double š = 1000.0D;
/*      */   static final int œ = 0;
/*      */   static final int Ł = 1;
/*      */   static final int Ŀ = 2;
/*      */   static final int ǆ = 25;
/*      */   static final int Ǎ = 35;
/*      */   static final int Ǒ = 48;
/*      */   static final int Ť = 1;
/*      */   static final int Ɩ = 2;
/*      */   static final int ŀ = 3;
/*      */   static final int ƞ = -1;
/*  143 */   static byte Ŵ = 0;
/*      */ 
/*  148 */   static byte ǀ = 8;
/*      */   private static final byte Ʀ = 2;
/*      */   static final int Ŏ = 5000;
/*      */   static final int ƕ = 0;
/*      */   private static final int ǐ = 4000;
/*      */   private static final int Ɠ = 1000;
/*  175 */   static int ƥ = -1;
/*  176 */   static int ſ = 0;
/*      */   static final int ǁ = 64;
/*      */   static final int Ƶ = 500;
/*      */   _A Ū;
/*      */   _A Š;
/*      */   _A Ń;
/*      */   _A ƛ;
/*      */   _A ŗ;
/*      */   _A Ə;
/*      */   _A Ż;
/*      */   _A Ş;
/*      */   _A ƫ;
/*      */   _A Ƈ;
/*      */   _A Ų;
/*      */   _A ű;
/*      */   _A ƻ;
/*      */   _A ń;
/*      */   _A ǜ;
/*      */   _A Ǜ;
/*      */   _A ǚ;
/*      */   _A Ǚ;
/*      */   _A Ǘ;
/*      */   _A Ɗ;
/*      */   _A Ű;
/*      */   _A Ơ;
/*      */   _A ř;
/*      */   _A ĳ;
/*      */   _A Ʒ;
/*      */   _A ş;
/*      */   _A ǅ;
/*      */   _A Ƴ;
/*      */   _A ƈ;
/*      */   _A ǝ;
/*      */   _A Ɛ;
/*      */   _A ǘ;
/*      */   _A Ʃ;
/*      */   _A ż;
/*      */   _A Ɣ;
/*      */   _A Ŗ;
/*      */   _A ƪ;
/*      */   _A Ʈ;
/*      */   _A Ƅ;
/*      */   _A Ɯ;
/*      */   _A ŭ;
/*      */   _A ǈ;
/*      */   _A Ƥ;
/*      */   _A ƴ;
/*      */   _A Ǟ;
/*      */   _A Ř;
/*      */   _A ũ;
/*      */   _A ť;
/*      */   _A ƒ;
/*      */   _A ŵ;
/*      */   _A Œ;
/*      */   _A ļ;
/*      */   _A ź;
/*      */   _A Ʋ;
/*      */   _A Ŧ;
/*      */   _A ǎ;
/*      */   _A ǒ;
/*      */   _A ƅ;
/*      */   _A Ŕ;
/*      */   _A ǃ;
/*      */   _A Ĳ;
/*      */   _A ƽ;
/*      */   _A ƀ;
/*      */   _A Ɓ;
/*      */   _A Ũ;
/*      */   _A Ƨ;
/*      */   _A ŏ;
/*      */   _A Ǐ;
/*      */   _A Ư;
/*      */   _A ŷ;
/*      */   int ƍ;
/*      */   int Ʊ;
/*      */   double Ǉ;
/*      */   int Ɔ;
/*      */   int ǔ;
/*      */   double ĺ;
/*      */   double ƺ;
/*      */   boolean ǂ;
/*      */   int ƌ;
/*      */   boolean Ɲ;
/*      */   double ŝ;
/*      */   double Ǌ;
/*      */   double Ƽ;
/*      */   int Ƒ;
/*      */   int Ǝ;
/*      */   boolean Ž;
/*      */   boolean Ō;
/*      */   boolean ƹ;
/*      */   int ƣ;
/*      */   int Ŭ;
/*      */   int Ļ;
/*      */   int ĵ;
/*      */   String Ŝ;
/*      */   String ś;
/*      */   String Ś;
/*      */   int Ÿ;
/*      */   int Ƌ;
/*      */   boolean į;
/*      */   int Ɖ;
/*      */   int ı;
/*      */   int ő;
/*      */   int Ǔ;
/*      */   int Ň;
/*      */   boolean Ƙ;
/*      */   boolean ǖ;
/*      */   boolean Ő;
/*      */   int İ;
/*      */   boolean ƚ;
/*      */   int Ņ;
/*      */   boolean Ƃ;
/*      */   double Ǆ;
/*      */   int ţ;
/*      */   boolean Ǖ;
/*      */   double Ĵ;
/*      */   int ž;
/*      */   int Ĺ;
/*      */   boolean ŧ;
/*      */   int ņ;
/*      */   int ư;
/*      */   double ƨ;
/*      */   int ū;
/*      */   int ƿ;
/*      */   int ǌ;
/*      */   int Ź;
/*      */   int ǋ;
/*      */   boolean ľ;
/*      */   boolean ķ;
/*      */   boolean Ƹ;
/*      */   int ų;
/*      */   int ł;
/*      */   private Vector ň;
/*      */ 
/*      */   public void A(v paramv, String paramString1, String paramString2, boolean paramBoolean)
/*      */     throws t, Z, IOException, P
/*      */   {
/*  392 */     A(paramv, paramString1, paramString2, true, paramBoolean);
/*      */   }
/*      */ 
/*      */   public void A(v paramv, String paramString1, String paramString2)
/*      */     throws t, Z, IOException, P
/*      */   {
/*  417 */     A(paramv, paramString1, paramString2, true, false);
/*      */   }
/*      */ 
/*      */   void A(v paramv, String paramString1, String paramString2, boolean paramBoolean1, boolean paramBoolean2)
/*      */     throws t, Z, IOException, P
/*      */   {
/*  444 */     A(this, "readData: starting reader...");
/*  445 */     this.ă = paramString2;
/*  446 */     B(false);
/*      */ 
/*  449 */     this.ň = C(paramBoolean2);
/*      */ 
/*  452 */     _B local_B = new _B(paramv, paramString1, paramString2, null);
/*  453 */     _B.A(local_B, paramBoolean1, true);
/*      */ 
/*  456 */     this.¤ = this.ų;
/*  457 */     this.Ñ = this.ł;
/*      */ 
/*  460 */     if ((!isHaltRequested()) && (paramBoolean2))
/*  461 */       this.è = new EA(this.ă, this.Ă, new Date(), this.ź.a, this.Ʋ.a, this.Ŧ.a, this.ǎ.a);
/*      */   }
/*      */ 
/*      */   public void B(v paramv, String paramString1, String paramString2)
/*      */     throws t, Z, IOException, P
/*      */   {
/*  489 */     this.ă = paramString2;
/*  490 */     this.ň = new Vector(1);
/*  491 */     B(false);
/*      */ 
/*  494 */     this.ň.addElement(this.ń);
/*      */ 
/*  497 */     _B local_B = new _B(paramv, paramString1, paramString2, null);
/*  498 */     _B.A(local_B);
/*      */   }
/*      */ 
/*      */   String D(v paramv, String paramString1, String paramString2)
/*      */     throws t, Z, IOException, P
/*      */   {
/*  520 */     this.ă = paramString2;
/*  521 */     this.ň = new Vector(1);
/*  522 */     B(false);
/*      */ 
/*  525 */     this.ň.addElement(this.ǈ);
/*      */ 
/*  528 */     _B local_B = new _B(paramv, paramString1, paramString2, null);
/*  529 */     _B.A(local_B, true, false);
/*  530 */     return this.À;
/*      */   }
/*      */ 
/*      */   public void B(v paramv, String paramString1, String paramString2, Date paramDate1, Date paramDate2)
/*      */     throws t, Z, IOException, P
/*      */   {
/*  564 */     Contract.preNonNull(paramDate1);
/*  565 */     Contract.preNonNull(paramDate2);
/*  566 */     Contract.pre(!paramDate1.after(paramDate2));
/*      */ 
/*  568 */     this.ă = paramString2;
/*  569 */     this.ň = new Vector(1);
/*  570 */     B(false);
/*      */ 
/*  573 */     this.ƀ.B(paramDate1);
/*  574 */     this.ƀ.A(paramDate2);
/*      */ 
/*  577 */     this.ƈ.a = new int[64];
/*  578 */     this.ǃ.a = new int[64];
/*      */ 
/*  581 */     this.ň.addElement(this.ƀ);
/*      */ 
/*  584 */     _B local_B = new _B(paramv, paramString1, paramString2, null);
/*  585 */     _B.A(local_B, true, false);
/*      */ 
/*  588 */     this.Ŕ.a = this.ƀ.a;
/*      */   }
/*      */ 
/*      */   public void A(v paramv, String paramString1, String paramString2, Date paramDate1, Date paramDate2)
/*      */     throws t, Z, IOException, P
/*      */   {
/*  622 */     Contract.preNonNull(paramDate1);
/*  623 */     Contract.preNonNull(paramDate2);
/*  624 */     Contract.pre(!paramDate1.after(paramDate2));
/*      */ 
/*  626 */     this.ă = paramString2;
/*  627 */     this.ň = new Vector(1);
/*  628 */     B(false);
/*      */ 
/*  631 */     this.Ɓ.B(paramDate1);
/*  632 */     this.Ɓ.A(paramDate2);
/*      */ 
/*  635 */     this.ƈ.a = new int[64];
/*  636 */     this.Ŕ.a = new int[64];
/*      */ 
/*  639 */     this.ň.addElement(this.Ɓ);
/*      */ 
/*  642 */     _B local_B = new _B(paramv, paramString1, paramString2, null);
/*  643 */     _B.A(local_B, true, false);
/*      */ 
/*  646 */     this.ǃ.a = this.Ɓ.a;
/*      */   }
/*      */ 
/*      */   abstract void A(_A param_A)
/*      */     throws Z;
/*      */ 
/*      */   void h()
/*      */   {
/*  663 */     this.Ź = 0;
/*      */ 
/*  666 */     for (int i = 0; i < this.ň.size(); i++) {
/*  667 */       _A local_A = (_A)this.ň.elementAt(i);
/*  668 */       if (local_A != null)
/*  669 */         this.Ź += local_A.a.length;
/*      */     }
/*      */   }
/*      */ 
/*      */   void C(v paramv, String paramString1, String paramString2)
/*      */     throws t, Z, IOException, P
/*      */   {
/*      */   }
/*      */ 
/*      */   static final String A(Integer paramInteger)
/*      */   {
/*  704 */     if (paramInteger == null) {
/*  705 */       return "NOT USED";
/*      */     }
/*  707 */     return ƾ[paramInteger.intValue()];
/*      */   }
/*      */ 
/*      */   static void G(int paramInt)
/*      */     throws Z
/*      */   {
/*  718 */     O._B.A((paramInt == 40) || (paramInt == 50) || (paramInt == 100), "The Insulin Concentration value of " + paramInt + " is invalid; must be " + 40 + " or " + 50 + " or " + 100);
/*      */   }
/*      */ 
/*      */   static void A(int paramInt1, int paramInt2, int paramInt3, String paramString)
/*      */   {
/*  743 */     Contract.pre((paramInt1 >= paramInt2) && (paramInt1 <= paramInt3));
/*      */   }
/*      */ 
/*      */   long A(double paramDouble)
/*      */   {
/*  754 */     return ()(paramDouble * this.ū);
/*      */   }
/*      */ 
/*      */   long B(double paramDouble)
/*      */   {
/*  764 */     return ()(paramDouble * this.ƿ);
/*      */   }
/*      */ 
/*      */   double H(int paramInt)
/*      */   {
/*  774 */     return paramInt / this.ū;
/*      */   }
/*      */ 
/*      */   double F(int paramInt)
/*      */   {
/*  784 */     return paramInt / this.ƿ;
/*      */   }
/*      */ 
/*      */   void D(String paramString)
/*      */     throws IOException
/*      */   {
/*  795 */     C(paramString);
/*      */ 
/*  797 */     int i = 0;
/*  798 */     int j = 0;
/*      */     do
/*      */     {
/*      */       try
/*      */       {
/*  803 */         A(new d(paramString, ƥ));
/*  804 */         Y().B(500);
/*  805 */         j = 1;
/*      */       }
/*      */       catch (IOException localIOException) {
/*  808 */         i++;
/*      */ 
/*  810 */         if (i >= 0) {
/*  811 */           throw localIOException;
/*      */         }
/*      */ 
/*  814 */         A(this, "initSerialPort: waiting for port to become available...e=" + localIOException);
/*      */ 
/*  816 */         O._B.G(5000);
/*      */       }
/*      */     }
/*  818 */     while (j == 0);
/*      */ 
/*  820 */     Y().A();
/*  821 */     Y().A(ſ);
/*      */   }
/*      */ 
/*      */   abstract void g()
/*      */     throws IOException, W, t;
/*      */ 
/*      */   abstract void i()
/*      */     throws IOException, t, P;
/*      */ 
/*      */   abstract void e()
/*      */     throws t, IOException;
/*      */ 
/*      */   abstract void f()
/*      */     throws IOException;
/*      */ 
/*      */   abstract void _()
/*      */     throws IOException;
/*      */ 
/*      */   void C(v paramv)
/*      */     throws t, IOException
/*      */   {
/*  877 */     g();
/*      */   }
/*      */ 
/*      */   int Z()
/*      */   {
/*  886 */     return 1;
/*      */   }
/*      */ 
/*      */   private Vector C(boolean paramBoolean)
/*      */   {
/*  896 */     Vector localVector = new Vector();
/*      */ 
/*  900 */     localVector.addElement(this.Ń);
/*  901 */     localVector.addElement(this.ş);
/*  902 */     localVector.addElement(this.ń);
/*      */ 
/*  904 */     localVector.addElement(this.Š);
/*  905 */     localVector.addElement(this.ƛ);
/*  906 */     localVector.addElement(this.Ű);
/*      */ 
/*  909 */     localVector.addElement(this.Ǟ);
/*  910 */     localVector.addElement(this.ƅ);
/*      */ 
/*  912 */     localVector.addElement(this.ǜ);
/*  913 */     localVector.addElement(this.Ǜ);
/*  914 */     localVector.addElement(this.ǚ);
/*  915 */     localVector.addElement(this.Ǚ);
/*  916 */     localVector.addElement(this.Ǘ);
/*  917 */     localVector.addElement(this.Ɗ);
/*  918 */     localVector.addElement(this.Ơ);
/*  919 */     localVector.addElement(this.ŗ);
/*  920 */     localVector.addElement(this.Ə);
/*  921 */     localVector.addElement(this.Ş);
/*  922 */     localVector.addElement(this.ƫ);
/*  923 */     localVector.addElement(this.Ƈ);
/*  924 */     localVector.addElement(this.Ų);
/*  925 */     localVector.addElement(this.ű);
/*  926 */     localVector.addElement(this.ƻ);
/*  927 */     localVector.addElement(this.Ż);
/*  928 */     localVector.addElement(this.ř);
/*  929 */     localVector.addElement(this.ĳ);
/*  930 */     localVector.addElement(this.Ʒ);
/*  931 */     localVector.addElement(this.ǅ);
/*  932 */     localVector.addElement(this.Ƴ);
/*      */ 
/*  934 */     localVector.addElement(this.Ƥ);
/*  935 */     localVector.addElement(this.ǈ);
/*  936 */     localVector.addElement(this.Ɛ);
/*  937 */     localVector.addElement(this.ǘ);
/*  938 */     localVector.addElement(this.Ʃ);
/*  939 */     localVector.addElement(this.ż);
/*  940 */     localVector.addElement(this.Ɣ);
/*  941 */     localVector.addElement(this.Ŗ);
/*  942 */     localVector.addElement(this.ƪ);
/*  943 */     localVector.addElement(this.Ʈ);
/*  944 */     localVector.addElement(this.Ƅ);
/*  945 */     localVector.addElement(this.Ɯ);
/*  946 */     localVector.addElement(this.ŭ);
/*      */ 
/*  948 */     localVector.addElement(this.Ř);
/*  949 */     localVector.addElement(this.ũ);
/*  950 */     localVector.addElement(this.ť);
/*  951 */     localVector.addElement(this.ƒ);
/*  952 */     localVector.addElement(this.ŵ);
/*      */ 
/*  954 */     localVector.addElement(this.ƽ);
/*  955 */     localVector.addElement(this.Ĳ);
/*  956 */     localVector.addElement(this.Ũ);
/*  957 */     localVector.addElement(this.Ƨ);
/*  958 */     localVector.addElement(this.ŏ);
/*  959 */     localVector.addElement(this.Ǐ);
/*  960 */     localVector.addElement(this.ŷ);
/*      */ 
/*  962 */     localVector.addElement(this.ƈ);
/*      */ 
/*  965 */     localVector.addElement(this.ǒ);
/*      */ 
/*  969 */     localVector.addElement(this.ƅ);
/*      */ 
/*  971 */     localVector.addElement(this.Ŕ);
/*  972 */     localVector.addElement(this.ǃ);
/*  973 */     localVector.addElement(this.Ư);
/*      */ 
/*  975 */     if (paramBoolean)
/*      */     {
/*  977 */       localVector.addElement(this.ź);
/*      */ 
/*  980 */       localVector.addElement(this.ļ);
/*  981 */       localVector.addElement(this.Ʋ);
/*  982 */       localVector.addElement(this.Œ);
/*      */ 
/*  984 */       localVector.addElement(this.Ŧ);
/*  985 */       localVector.addElement(this.ǎ);
/*      */     }
/*      */ 
/*  988 */     return localVector;
/*      */   }
/*      */ 
/*      */   private final class _B
/*      */   {
/*      */     private String B;
/*      */     private String C;
/*      */     private v D;
/*      */ 
/*      */     private _B(v paramString1, String paramString2, String arg4)
/*      */     {
/* 1278 */       this.D = paramString1;
/* 1279 */       M.this.B(this.D);
/* 1280 */       this.B = paramString2;
/*      */       Object localObject;
/* 1281 */       this.C = localObject;
/*      */     }
/*      */ 
/*      */     private void A(boolean paramBoolean1, boolean paramBoolean2)
/*      */       throws t, Z, IOException, P
/*      */     {
/* 1301 */       int i = 0;
/*      */ 
/* 1303 */       Vector localVector = new Vector(M.A(M.this).size());
/* 1304 */       int j = 0;
/* 1305 */       int k = 0;
/*      */ 
/* 1307 */       if (paramBoolean1) {
/* 1308 */         M.this.ǋ = 0;
/* 1309 */         M.this.ľ = false;
/* 1310 */         M.this.ķ = false;
/* 1311 */         M.this.Ƹ = false;
/*      */       }
/*      */       else {
/* 1314 */         M.this.ľ = true;
/* 1315 */         M.this.ķ = true;
/* 1316 */         M.this.Ƹ = true;
/*      */       }
/*      */ 
/* 1319 */       M.this.h();
/* 1320 */       M.this.C(0);
/*      */       try
/*      */       {
/* 1328 */         if (paramBoolean1)
/*      */         {
/* 1330 */           M.this.B(2);
/* 1331 */           A(this.B);
/*      */         }
/*      */ 
/* 1334 */         if (paramBoolean2) {
/* 1335 */           M.this.E(5);
/*      */         }
/*      */ 
/* 1338 */         if (!M.this.isHaltRequested())
/*      */         {
/* 1341 */           i = 0;
/* 1342 */           while ((i < M.A(M.this).size()) && (!M.this.isHaltRequested()))
/*      */           {
/* 1344 */             M._A local_A = (M._A)M.A(M.this).elementAt(i);
/* 1345 */             if (local_A != null)
/*      */             {
/* 1347 */               local_A.A();
/*      */ 
/* 1350 */               if (local_A.equals(M.this.Š))
/*      */               {
/* 1352 */                 O._B.G(4000);
/*      */               }
/*      */ 
/* 1355 */               if (local_A.equals(M.this.Ń))
/*      */               {
/* 1357 */                 O._B.G(1000);
/*      */               }
/*      */ 
/* 1360 */               if (!M.this.isHaltRequested())
/*      */               {
/* 1362 */                 int[] arrayOfInt = local_A.a;
/* 1363 */                 localVector.addElement(arrayOfInt);
/* 1364 */                 M.this.A(local_A);
/*      */               }
/*      */             }
/* 1343 */             i++;
/*      */           }
/*      */ 
/* 1370 */           M.this.C(this.D, this.B, this.C);
/*      */ 
/* 1373 */           if (M.this.isHaltRequested())
/* 1374 */             j = 2;
/*      */           else
/* 1376 */             j = 1;
/*      */         }
/*      */       }
/*      */       catch (IOException localIOException) {
/* 1380 */         k = 1;
/* 1381 */         throw localIOException;
/*      */       } catch (t localt) {
/* 1383 */         k = 1;
/* 1384 */         throw localt;
/*      */       } catch (Z localZ) {
/* 1386 */         k = 1;
/* 1387 */         throw localZ;
/*      */       } catch (P localP) {
/* 1389 */         k = 1;
/* 1390 */         throw localP;
/*      */       }
/*      */       finally
/*      */       {
/*      */       }
/*      */ 
/* 1429 */       ret;
/*      */     }
/*      */ 
/*      */     private void B()
/*      */       throws t, Z, IOException, P
/*      */     {
/* 1450 */       A(true, true);
/*      */     }
/*      */ 
/*      */     private void A(String paramString)
/*      */       throws IOException, t, P
/*      */     {
/* 1465 */       M.this.D(paramString);
/* 1466 */       M.this.ľ = true;
/*      */       try
/*      */       {
/* 1470 */         M.this.g();
/*      */       }
/*      */       catch (IOException localIOException) {
/* 1473 */         M.this.g();
/*      */       }
/* 1475 */       M.this.ķ = true;
/*      */ 
/* 1478 */       M.this.i();
/* 1479 */       M.this.Ƹ = true;
/*      */     }
/*      */ 
/*      */     private void A()
/*      */       throws t, IOException
/*      */     {
/* 1489 */       O.A(this, "endCommunications: shutting down...");
/*      */       try {
/* 1491 */         if (M.this.Ƹ) {
/* 1492 */           O.A(this, "endCommunications: shutting down pump communications.");
/* 1493 */           M.this.e();
/*      */         }
/*      */       } finally {
/*      */         try {
/* 1497 */           if (M.this.ķ) {
/* 1498 */             O.A(this, "endCommunications: shutting down comStation communications.");
/*      */ 
/* 1500 */             M.this.f();
/*      */           }
/*      */         } finally {
/* 1503 */           if (M.this.ľ) {
/* 1504 */             O.A(this, "endCommunications: shutting down serial port communications.");
/*      */ 
/* 1506 */             M.this._();
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   abstract class _A extends n
/*      */     implements Cloneable
/*      */   {
/*      */     static final byte K = 1;
/*      */     static final byte L = 6;
/*      */     static final byte P = 90;
/*      */     static final byte S = -1;
/*      */     static final int J = 50;
/*      */     int[] O;
/*      */     int I;
/*      */     int M;
/*      */     int X;
/*      */     int[] a;
/*      */     int B;
/*      */     int E;
/*      */     int Q;
/*      */     int T;
/*      */     int W;
/*      */     int C;
/*      */     int F;
/*      */     int _;
/*      */     int[] H;
/* 1044 */     int N = 0;
/*      */     int Y;
/*      */     boolean V;
/*      */     Date R;
/*      */     Date G;
/* 1069 */     Object U = null;
/*      */ 
/* 1074 */     int D = 0;
/*      */ 
/*      */     _A(int paramString, String paramInt1, int paramInt2, int paramInt3, int arg6)
/*      */     {
/* 1089 */       this(paramString, paramInt1, paramInt2, paramInt3, 0, 0, i);
/* 1090 */       this.B = 0;
/* 1091 */       this.C = 2;
/* 1092 */       A(false);
/*      */     }
/*      */ 
/*      */     _A(int paramString, String paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int arg8)
/*      */     {
/* 1109 */       super();
/* 1110 */       this.X = paramString;
/* 1111 */       this.E = paramInt2;
/* 1112 */       this.Q = paramInt3;
/* 1113 */       C();
/* 1114 */       this.T = paramInt4;
/* 1115 */       this.F = paramInt5;
/* 1116 */       this.B = 2;
/*      */ 
/* 1118 */       if (paramInt5 == 1)
/* 1119 */         this.C = (2 + paramInt5);
/*      */       else {
/* 1121 */         this.C = (2 + paramInt5 + 1);
/*      */       }
/*      */ 
/* 1124 */       this.H = new int[0];
/*      */       int i;
/* 1125 */       this._ = i;
/* 1126 */       this.I = 0;
/* 1127 */       this.O = new int[64];
/* 1128 */       A(false);
/* 1129 */       this.Y = 2;
/*      */     }
/*      */ 
/*      */     void C()
/*      */     {
/* 1138 */       this.a = new int[this.E * this.Q];
/*      */     }
/*      */ 
/*      */     public Object clone()
/*      */     {
/* 1147 */       _A local_A = null;
/*      */       try {
/* 1149 */         local_A = (_A)super.clone();
/* 1150 */         local_A.H = O._B.C(this.H);
/* 1151 */         local_A.O = O._B.C(this.O);
/* 1152 */         local_A.a = O._B.C(this.a);
/* 1153 */         if (this.R != null) {
/* 1154 */           local_A.R = ((Date)this.R.clone());
/*      */         }
/* 1156 */         if (this.G != null)
/* 1157 */           local_A.G = ((Date)this.G.clone());
/*      */       }
/*      */       catch (CloneNotSupportedException localCloneNotSupportedException) {
/* 1160 */         Contract.unreachable();
/*      */       }
/* 1162 */       return local_A;
/*      */     }
/*      */ 
/*      */     public String toString()
/*      */     {
/* 1171 */       StringBuffer localStringBuffer = new StringBuffer();
/* 1172 */       localStringBuffer.append(O._B.H(this.X));
/* 1173 */       localStringBuffer.append(" (" + this.A);
/*      */ 
/* 1176 */       for (int i = 0; i < this.I; i++) {
/* 1177 */         localStringBuffer.append(" p" + i + "=" + this.O[i]);
/*      */       }
/*      */ 
/* 1180 */       localStringBuffer.append(") ");
/* 1181 */       return localStringBuffer.toString();
/*      */     }
/*      */ 
/*      */     abstract void A()
/*      */       throws t, W;
/*      */ 
/*      */     void B(Date paramDate)
/*      */     {
/* 1200 */       this.R = paramDate;
/*      */     }
/*      */ 
/*      */     void A(Date paramDate)
/*      */     {
/* 1208 */       this.G = paramDate;
/*      */     }
/*      */ 
/*      */     void A(boolean paramBoolean)
/*      */     {
/* 1218 */       this.V = paramBoolean;
/*      */     }
/*      */ 
/*      */     boolean B()
/*      */     {
/* 1228 */       return this.V;
/*      */     }
/*      */ 
/*      */     int D()
/*      */     {
/* 1237 */       return this.D;
/*      */     }
/*      */ 
/*      */     void A(int paramInt)
/*      */     {
/* 1246 */       this.D = paramInt;
/*      */     }
/*      */   }
/*      */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.A.M
 * JD-Core Version:    0.6.0
 */