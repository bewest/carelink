/*     */ package minimed.ddms.A;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ abstract class J
/*     */   implements z
/*     */ {
/*     */   static final String G = "DISCONNNECTED";
/*     */   private v F;
/*     */   private final String E;
/*     */   private int D;
/*     */   private int J;
/*     */   private int B;
/*     */   private int C;
/*     */   private String A;
/*  48 */   private int I = 4;
/*     */   private c H;
/*     */ 
/*     */   J(v paramv, String paramString1, String paramString2)
/*     */   {
/*  66 */     this.F = paramv;
/*  67 */     this.E = paramString1;
/*  68 */     this.A = paramString2;
/*  69 */     D(1);
/*  70 */     O.A(this, "Created link device " + toString());
/*     */   }
/*     */ 
/*     */   public c C()
/*     */   {
/*  81 */     return this.H;
/*     */   }
/*     */ 
/*     */   public void A(c paramc)
/*     */   {
/*  90 */     this.H = paramc;
/*     */   }
/*     */ 
/*     */   public final void D()
/*     */     throws t, IOException, W
/*     */   {
/* 102 */     D(2);
/* 103 */     int i = 0;
/*     */ 
/* 105 */     for (int j = 0; (j <= this.I) && (i == 0); j++) {
/*     */       try {
/* 107 */         H();
/* 108 */         i = 1;
/*     */       } catch (IOException localIOException) {
/* 110 */         O.D(this, "initCommunications: got error " + localIOException);
/* 111 */         if (j == this.I)
/*     */         {
/* 113 */           throw localIOException;
/*     */         }
/* 115 */         O.D(this, "initCommunications: retrying...");
/*     */       }
/*     */     }
/* 118 */     D(1);
/*     */   }
/*     */ 
/*     */   public final void B()
/*     */     throws IOException
/*     */   {
/* 127 */     F();
/* 128 */     D(1);
/*     */   }
/*     */ 
/*     */   public final void A()
/*     */   {
/* 135 */     this.B = 0;
/*     */   }
/*     */ 
/*     */   public final void A(int paramInt)
/*     */   {
/* 144 */     this.J = paramInt;
/*     */   }
/*     */ 
/*     */   public final void B(int paramInt)
/*     */   {
/* 153 */     this.B += paramInt;
/*     */   }
/*     */ 
/*     */   public final void A(v paramv)
/*     */   {
/* 162 */     this.F = paramv;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 171 */     return this.A + " using " + G();
/*     */   }
/*     */ 
/*     */   final String J()
/*     */   {
/* 180 */     return this.E;
/*     */   }
/*     */ 
/*     */   abstract void H()
/*     */     throws IOException, W, t;
/*     */ 
/*     */   abstract void F()
/*     */     throws IOException;
/*     */ 
/*     */   String G()
/*     */   {
/* 206 */     return C() != null ? C().toString() : "DISCONNNECTED";
/*     */   }
/*     */ 
/*     */   void I()
/*     */   {
/* 214 */     double d = this.B / this.J * 100.0D;
/*     */ 
/* 216 */     this.F.deviceUpdateProgress((int)d);
/*     */   }
/*     */ 
/*     */   void D(int paramInt)
/*     */   {
/* 227 */     Contract.pre((paramInt >= 1) && (paramInt <= 9));
/*     */ 
/* 230 */     if (paramInt != this.D) {
/* 231 */       this.D = paramInt;
/* 232 */       this.F.deviceUpdateState(this.D, G.H[this.D]);
/*     */     }
/*     */   }
/*     */ 
/*     */   void C(int paramInt)
/*     */   {
/* 244 */     Contract.pre((paramInt >= 1) && (paramInt <= 7));
/*     */ 
/* 248 */     if (paramInt != this.C) {
/* 249 */       this.C = paramInt;
/* 250 */       this.F.deviceUpdateState(this.C, G.K[this.C]);
/*     */     }
/*     */   }
/*     */ 
/*     */   void E(int paramInt)
/*     */   {
/* 260 */     this.I = paramInt;
/*     */   }
/*     */ 
/*     */   v E()
/*     */   {
/* 269 */     return this.F;
/*     */   }
/*     */ 
/*     */   int K()
/*     */   {
/* 278 */     return this.D;
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.A.J
 * JD-Core Version:    0.6.0
 */