/*     */ package minimed.ddms.A;
/*     */ 
/*     */ import java.util.Vector;
/*     */ 
/*     */ public class C extends N
/*     */ {
/*     */   private static final int ς = 173;
/*     */   private static final int ω = 22;
/*     */   private static final int ν = 11000;
/*  41 */   private static final int[] ψ = { 67, 32, 49, 13 };
/*  42 */   private static final int[] φ = { 67, 32, 51, 13 };
/*  43 */   private static final int[] λ = { 67, 32, 52, 13 };
/*  44 */   private static final int[] ϋ = { 83, 32, 49, 13 };
/*  45 */   private static final int[] ο = { 83, 32, 50, 13 };
/*  46 */   private static final int[] κ = { 96, 13 };
/*     */ 
/*  49 */   private static final int[] ρ = { 73, 13 };
/*  50 */   private static final int[] ϊ = { 83, 32, 51, 13 };
/*     */   private static final String ξ = "ACCU-CHEK Active";
/*     */   private static final String ζ = "ACCU-CHEK Active LCM";
/*     */   private static final String ι = "GCP";
/*     */   private static final String μ = "GCP2";
/*     */   private static final String π = "GCP2-LCM";
/*     */   private static final String τ = "Wrong Meter Selection";
/*  72 */   private static final int[] θ = { 97, 32, 49, 32, 48, 48, 49, 13 };
/*     */   private N._A χ;
/*     */   private N._A υ;
/*     */   private String σ;
/*     */   private String η;
/*     */ 
/*     */   C(int paramInt)
/*     */   {
/*  90 */     super("Roche Active-Compact-CompactPlus", 173, paramInt, ψ, φ, ο, ϋ, λ, κ, θ, 11000, 22);
/*     */ 
/*  97 */     this.χ = new N._A(this, ρ, "Read Instrument Name", new N._D()
/*     */     {
/*     */       public void A(N._A param_A)
/*     */         throws Z
/*     */       {
/* 108 */         C.A(C.this, C.this.U(param_A.I()));
/* 109 */         O.A(this, "decodeReply: instrument name is " + C.B(C.this));
/*     */ 
/* 111 */         int i = C.C(C.this, C.B(C.this));
/* 112 */         if (i != C.this.ā)
/* 113 */           throw new Z("Wrong Meter Selection: deviceID found = " + i + ", expected = " + C.this.ā);
/*     */       }
/*     */     });
/* 121 */     this.υ = new N._A(this, ϊ, "Read BG Units", new N._D()
/*     */     {
/*     */       public void A(N._A param_A) throws Z {
/* 124 */         C.B(C.this, C.this.U(param_A.I()));
/* 125 */         O.A(this, "decodeReply: BG units are " + C.A(C.this));
/*     */       }
/*     */     });
/* 130 */     this.Ė = new _A();
/*     */   }
/*     */ 
/*     */   private int J(String paramString)
/*     */     throws Z
/*     */   {
/*     */     int i;
/* 143 */     if (("ACCU-CHEK Active".equalsIgnoreCase(paramString)) || ("ACCU-CHEK Active LCM".equalsIgnoreCase(paramString)))
/*     */     {
/* 145 */       i = 23;
/* 146 */     } else if ("GCP".equalsIgnoreCase(paramString))
/* 147 */       i = 24;
/* 148 */     else if (("GCP2".equalsIgnoreCase(paramString)) || ("GCP2-LCM".equalsIgnoreCase(paramString)))
/*     */     {
/* 150 */       i = 25;
/*     */     }
/* 152 */     else throw new Z("unknown Instrument Name: " + paramString);
/*     */ 
/* 154 */     return i;
/*     */   }
/*     */ 
/*     */   String U(int[] paramArrayOfInt)
/*     */     throws Z
/*     */   {
/* 175 */     int i = paramArrayOfInt.length - 4;
/*     */ 
/* 177 */     if (i < 1) {
/* 178 */       throw new Z("extractData: response length (" + i + ") too short: " + O._B.D(paramArrayOfInt));
/*     */     }
/*     */ 
/* 183 */     int[] arrayOfInt = new int[i];
/* 184 */     System.arraycopy(paramArrayOfInt, 2, arrayOfInt, 0, i);
/* 185 */     return O._B.E(arrayOfInt);
/*     */   }
/*     */ 
/*     */   Vector r()
/*     */   {
/* 195 */     Vector localVector = super.r();
/*     */ 
/* 199 */     localVector.insertElementAt(this.υ, 0);
/* 200 */     localVector.insertElementAt(this.χ, 1);
/*     */ 
/* 202 */     return localVector;
/*     */   }
/*     */ 
/*     */   private class _A extends N._C
/*     */   {
/*     */     private static final int e = 4;
/*     */     private static final int f = 5;
/*     */ 
/*     */     _A()
/*     */     {
/* 228 */       super();
/*     */     }
/*     */ 
/*     */     void A()
/*     */     {
/* 238 */       super.A();
/*     */ 
/* 243 */       C.this.Î.A(4, C.this.I(C.B(C.this)));
/*     */ 
/* 246 */       C.this.Î.A(5, C.this.I(C.A(C.this)));
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.A.C
 * JD-Core Version:    0.6.0
 */