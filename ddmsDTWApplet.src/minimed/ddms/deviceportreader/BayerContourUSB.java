/*     */ package minimed.ddms.A;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.Set;
/*     */ import java.util.StringTokenizer;
/*     */ import java.util.Vector;
/*     */ import mdt.common.device.driver.minimed.BayerUSB;
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ class k extends Q
/*     */ {
/*     */   public static final int ϳ = 141;
/*     */   protected static final int О = 3000;
/*     */   protected static final int С = 10;
/*     */   protected static final int Р = 2000;
/*     */   protected static final String Ѓ = "|\r\n";
/*     */   protected static final String Н = "\\^&";
/*     */   private static final String П = "Bayer7390";
/*     */   private static final String Л = "vid_1a79";
/*     */   private static final String З = "pid_6002";
/*  62 */   private static final byte[] И = { 21 };
/*     */   private static final int Й = 6;
/*     */   protected BayerUSB К;
/*     */   protected DA М;
/*     */ 
/*     */   k()
/*     */   {
/*  80 */     this("Bayer Contour USB Meter", 27, "Bayer7390");
/*     */   }
/*     */ 
/*     */   k(String paramString1, int paramInt, String paramString2)
/*     */   {
/*  92 */     super(2000);
/*  93 */     this.ć = paramString1;
/*  94 */     A(this, "creating interface to the '" + this.ć + "', package version " + K());
/*     */ 
/*  96 */     this.ā = paramInt;
/*  97 */     this.ϰ.add(paramString2);
/*  98 */     this.Þ = 141;
/*  99 */     this.Ė = new _A();
/*     */   }
/*     */ 
/*     */   void y()
/*     */   {
/* 106 */     this.ϭ = new Q._B(this);
/*     */   }
/*     */ 
/*     */   Vector<Q._B> w()
/*     */   {
/* 111 */     Vector localVector = new Vector();
/*     */ 
/* 114 */     localVector.addElement(this.ϭ);
/* 115 */     return localVector;
/*     */   }
/*     */ 
/*     */   void L(int paramInt)
/*     */   {
/*     */   }
/*     */ 
/*     */   void ¢()
/*     */     throws Z
/*     */   {
/*     */   }
/*     */ 
/*     */   void L(String paramString)
/*     */     throws Z
/*     */   {
/* 132 */     StringTokenizer localStringTokenizer1 = null;
/* 133 */     String str1 = null;
/*     */ 
/* 135 */     String str3 = "1";
/*     */ 
/* 137 */     Contract.pre(paramString != null);
/*     */     try
/*     */     {
/* 144 */       A(this, "decodeDataUploadHeader: parsing dataUpload header: '" + paramString + '\'');
/*     */ 
/* 147 */       localStringTokenizer1 = new StringTokenizer(paramString, "|\r\n");
/*     */ 
/* 150 */       str1 = localStringTokenizer1.nextToken();
/* 151 */       if (!str1.startsWith(str3 + "H")) {
/* 152 */         throw new Z("Bad header record type (last token='" + str1 + "')");
/*     */       }
/*     */ 
/* 157 */       str1 = localStringTokenizer1.nextToken();
/* 158 */       str1 = localStringTokenizer1.nextToken();
/* 159 */       String str4 = localStringTokenizer1.nextToken();
/*     */ 
/* 161 */       StringTokenizer localStringTokenizer2 = new StringTokenizer(str4, "\\^&");
/* 162 */       String str2 = localStringTokenizer2.nextToken();
/*     */ 
/* 164 */       if (!M(str2)) {
/* 165 */         throw new Z("Product code '" + str2 + "' not found in list '" + this.ϰ + "' (last token='" + str1 + "')");
/*     */       }
/*     */ 
/* 170 */       this.Ă = (localStringTokenizer2.nextToken() + "^" + localStringTokenizer2.nextToken() + "^" + localStringTokenizer2.nextToken());
/*     */ 
/* 174 */       this.ă = localStringTokenizer2.nextToken();
/*     */ 
/* 176 */       A(this, "decodeDataUploadHeader: current firmware version for device is " + this.Ă);
/*     */ 
/* 178 */       A(this, "decodeDataUploadHeader: current serial number for device is " + this.ă);
/*     */ 
/* 181 */       str1 = localStringTokenizer1.nextToken();
/* 182 */       str1 = localStringTokenizer1.nextToken();
/* 183 */       int i = Integer.parseInt(str1);
/* 184 */       M(i);
/* 185 */       str1 = localStringTokenizer1.nextToken();
/* 186 */       str1 = localStringTokenizer1.nextToken();
/* 187 */       this.ϫ = localStringTokenizer1.nextToken();
/* 188 */       £();
/*     */     } catch (NoSuchElementException localNoSuchElementException) {
/* 190 */       throw new Z("Bad dataUpload header field '" + paramString + "' received (last token='" + str1 + "')");
/*     */     }
/*     */     catch (NumberFormatException localNumberFormatException)
/*     */     {
/* 194 */       throw new Z("Bad dataUpload header field number '" + paramString + "' received (last token='" + str1 + "')");
/*     */     }
/*     */   }
/*     */ 
/*     */   void D(String paramString)
/*     */     throws IOException
/*     */   {
/* 203 */     E(4);
/* 204 */     ª();
/* 205 */     this.М = new DA(this.К);
/* 206 */     this.М.S();
/* 207 */     A(this.М);
/* 208 */     this.М._();
/*     */   }
/*     */ 
/*     */   void ¥()
/*     */     throws t, Z
/*     */   {
/* 215 */     O._B.G(3000);
/*     */   }
/*     */ 
/*     */   void _()
/*     */     throws IOException
/*     */   {
/* 221 */     if (this.К.isConnOpened()) {
/* 222 */       µ();
/*     */     }
/* 224 */     this.М.V();
/*     */   }
/*     */ 
/*     */   void ª()
/*     */     throws IOException
/*     */   {
/* 231 */     this.К = new BayerUSB(10L, "vid_1a79", "pid_6002");
/*     */   }
/*     */ 
/*     */   public void A(long paramLong)
/*     */   {
/* 237 */     this.К.setMaxWaitMS(paramLong);
/*     */   }
/*     */ 
/*     */   public long x()
/*     */   {
/* 243 */     return this.К.getMaxWaitMS();
/*     */   }
/*     */ 
/*     */   private void µ()
/*     */   {
/* 251 */     for (int i = 0; i < 6; i++)
/*     */       try {
/* 253 */         this.К.write(И);
/*     */       } catch (IOException localIOException) {
/* 255 */         break;
/*     */       }
/*     */   }
/*     */ 
/*     */   private final class _A extends Q._A
/*     */   {
/*     */     static final int h = 10000;
/*     */ 
/*     */     _A()
/*     */     {
/* 278 */       super(10000);
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.A.k
 * JD-Core Version:    0.6.0
 */