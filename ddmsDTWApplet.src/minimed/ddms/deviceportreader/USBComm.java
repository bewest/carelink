/*     */ package minimed.ddms.A;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import mdt.common.device.driver.minimed.ArtLogicUSBPort;
/*     */ import mdt.common.device.driver.minimed.JungoUSBPort;
/*     */ import mdt.common.device.driver.minimed.USBPort;
/*     */ import minimed.util.Contract;
/*     */ import minimed.util.OSInfo;
/*     */ 
/*     */ final class DA extends c
/*     */ {
/*     */   private static final int d = 256;
/*     */   private static final int c = 64;
/*     */   private USBPort b;
/*  60 */   private boolean a = false;
/*     */   private long _;
/*     */   private byte[] Z;
/*     */   private boolean e;
/*     */ 
/*     */   DA(USBPort paramUSBPort)
/*     */   {
/*  71 */     this.b = paramUSBPort;
/*  72 */     Z();
/*  73 */     A(true);
/*  74 */     O.A(this, "Created communications device " + toString());
/*  75 */     Y();
/*     */   }
/*     */ 
/*     */   DA()
/*     */   {
/*  83 */     this(OSInfo.isMac() ? new ArtLogicUSBPort() : new JungoUSBPort());
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/*  95 */     return "USB Communications using " + this.b + ", version " + this.b.getVersion();
/*     */   }
/*     */ 
/*     */   void Y()
/*     */   {
/* 102 */     this.e = true;
/*     */   }
/*     */ 
/*     */   void S()
/*     */   {
/* 109 */     this.e = false;
/*     */   }
/*     */ 
/*     */   void _()
/*     */     throws IOException, W
/*     */   {
/* 120 */     this.b.initCommunications();
/* 121 */     this.a = true;
/*     */   }
/*     */ 
/*     */   void V()
/*     */   {
/* 128 */     this.b.endCommunications();
/* 129 */     this.a = false;
/*     */   }
/*     */ 
/*     */   void T()
/*     */     throws IOException
/*     */   {
/* 138 */     this.b.clearBuffers();
/*     */   }
/*     */ 
/*     */   void A(String paramString) throws IOException
/*     */   {
/* 143 */     int[] arrayOfInt = O._B.D(paramString);
/* 144 */     C(arrayOfInt);
/*     */   }
/*     */ 
/*     */   void K(int paramInt)
/*     */     throws IOException, W
/*     */   {
/* 158 */     int[] arrayOfInt = new int[1];
/* 159 */     arrayOfInt[0] = paramInt;
/* 160 */     C(arrayOfInt);
/*     */   }
/*     */ 
/*     */   void C(int[] paramArrayOfInt)
/*     */     throws IOException, W
/*     */   {
/* 174 */     X();
/* 175 */     G();
/* 176 */     D(paramArrayOfInt);
/*     */ 
/* 179 */     O.B(this, "write(" + U() + "MS)[" + paramArrayOfInt.length + "]: <" + G(paramArrayOfInt) + ">");
/*     */ 
/* 181 */     Z();
/* 182 */     W();
/*     */   }
/*     */ 
/*     */   int[] J(int paramInt)
/*     */     throws IOException, W
/*     */   {
/* 197 */     K(paramInt);
/* 198 */     return R();
/*     */   }
/*     */ 
/*     */   int[] F(int[] paramArrayOfInt)
/*     */     throws IOException, W
/*     */   {
/* 213 */     C(paramArrayOfInt);
/* 214 */     return R();
/*     */   }
/*     */ 
/*     */   int[] A(int[] paramArrayOfInt, int paramInt)
/*     */     throws IOException, W
/*     */   {
/* 231 */     C(paramArrayOfInt);
/* 232 */     return I(paramInt);
/*     */   }
/*     */ 
/*     */   int[] R()
/*     */     throws IOException, W
/*     */   {
/* 245 */     return I(64);
/*     */   }
/*     */ 
/*     */   int[] I(int paramInt)
/*     */     throws IOException, W
/*     */   {
/* 260 */     X();
/* 261 */     G();
/*     */ 
/* 264 */     int[] arrayOfInt1 = H(paramInt);
/*     */ 
/* 267 */     int[] arrayOfInt2 = new int[paramInt];
/* 268 */     System.arraycopy(arrayOfInt1, 0, arrayOfInt2, 0, paramInt);
/*     */ 
/* 271 */     O.B(this, "read(" + U() + "MS)[" + arrayOfInt2.length + "]: <" + G(arrayOfInt2) + ">");
/*     */ 
/* 273 */     Z();
/* 274 */     Contract.post(paramInt == arrayOfInt2.length);
/* 275 */     return arrayOfInt2;
/*     */   }
/*     */ 
/*     */   String C() throws IOException
/*     */   {
/* 280 */     int[] arrayOfInt = I(256);
/* 281 */     return O._B.E(arrayOfInt);
/*     */   }
/*     */ 
/*     */   static void A(l paraml)
/*     */   {
/* 290 */     if (OSInfo.isMac())
/* 291 */       ArtLogicUSBPort.addPnPListener(paraml);
/*     */     else
/* 293 */       JungoUSBPort.addPnPListener(paraml);
/*     */   }
/*     */ 
/*     */   private void W()
/*     */     throws IOException
/*     */   {
/* 303 */     this.b.write(this.Z);
/*     */   }
/*     */ 
/*     */   private void D(int[] paramArrayOfInt)
/*     */   {
/* 313 */     this.Z = E(paramArrayOfInt);
/*     */   }
/*     */ 
/*     */   private void X()
/*     */     throws IOException
/*     */   {
/* 322 */     if (!this.a)
/* 323 */       throw new IOException("USB Communications not initialized");
/*     */   }
/*     */ 
/*     */   private int[] H(int paramInt)
/*     */     throws IOException
/*     */   {
/* 334 */     byte[] arrayOfByte = this.b.read(paramInt);
/* 335 */     return C(arrayOfByte);
/*     */   }
/*     */ 
/*     */   private byte[] E(int[] paramArrayOfInt)
/*     */   {
/* 346 */     byte[] arrayOfByte = new byte[paramArrayOfInt.length];
/* 347 */     for (int i = 0; i < paramArrayOfInt.length; i++) {
/* 348 */       Contract.pre(paramArrayOfInt[i], 0, 255);
/* 349 */       arrayOfByte[i] = (byte)O._B.K(paramArrayOfInt[i]);
/*     */     }
/* 351 */     return arrayOfByte;
/*     */   }
/*     */ 
/*     */   private int[] C(byte[] paramArrayOfByte)
/*     */   {
/* 361 */     int[] arrayOfInt = new int[paramArrayOfByte.length];
/*     */ 
/* 363 */     for (int i = 0; i < paramArrayOfByte.length; i++) {
/* 364 */       paramArrayOfByte[i] &= 255;
/*     */     }
/* 366 */     return arrayOfInt;
/*     */   }
/*     */ 
/*     */   private void Z()
/*     */   {
/* 373 */     this._ = System.currentTimeMillis();
/*     */   }
/*     */ 
/*     */   private long U()
/*     */   {
/* 382 */     return System.currentTimeMillis() - this._;
/*     */   }
/*     */ 
/*     */   private String G(int[] paramArrayOfInt)
/*     */   {
/* 392 */     String str = this.e ? O._B.G(paramArrayOfInt) : O._B.E(O._B.E(paramArrayOfInt));
/*     */ 
/* 394 */     return str;
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.A.DA
 * JD-Core Version:    0.6.0
 */