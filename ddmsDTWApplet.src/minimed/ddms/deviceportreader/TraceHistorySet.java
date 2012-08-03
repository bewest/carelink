/*     */ package minimed.ddms.A;
/*     */ 
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ public class EA
/*     */ {
/*     */   private static final int C = 1;
/*     */   private static final int G = 2;
/*     */   private static final int F = 3;
/*     */   private static final int A = 4;
/*     */   private final String J;
/*     */   private final String E;
/*     */   private final Date I;
/*     */   private final _A K;
/*     */   private final _A D;
/*     */   private final _A B;
/*     */   private final _A H;
/*     */ 
/*     */   public EA(String paramString1, String paramString2, Date paramDate, int[] paramArrayOfInt1, int[] paramArrayOfInt2, int[] paramArrayOfInt3, int[] paramArrayOfInt4)
/*     */   {
/*  83 */     Contract.preNonNull(paramString1);
/*  84 */     Contract.preNonNull(paramString2);
/*  85 */     Contract.preNonNull(paramDate);
/*  86 */     Contract.preNonNull(paramArrayOfInt1);
/*  87 */     Contract.preNonNull(paramArrayOfInt2);
/*  88 */     Contract.preNonNull(paramArrayOfInt2);
/*  89 */     Contract.preNonNull(paramArrayOfInt4);
/*     */ 
/*  91 */     this.J = paramString1;
/*  92 */     this.E = paramString2;
/*  93 */     this.I = paramDate;
/*  94 */     this.K = new _A("PumpTrace", paramArrayOfInt1, 1, null);
/*     */ 
/*  96 */     this.D = new _A("DetailTrace", paramArrayOfInt2, 2, null);
/*     */ 
/*  98 */     this.B = new _A("NewAlarmTrace", paramArrayOfInt3, 3, null);
/*     */ 
/* 100 */     this.H = new _A("OldAlarmTrace", paramArrayOfInt4, 4, null);
/*     */   }
/*     */ 
/*     */   public _A C()
/*     */   {
/* 113 */     return this.K;
/*     */   }
/*     */ 
/*     */   public _A E()
/*     */   {
/* 122 */     return this.D;
/*     */   }
/*     */ 
/*     */   public _A D()
/*     */   {
/* 131 */     return this.B;
/*     */   }
/*     */ 
/*     */   public _A B()
/*     */   {
/* 140 */     return this.H;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 149 */     return "TraceHistorySet (" + A() + " bytes total) = " + this.K + ", " + this.D + ", " + this.B + ", " + this.H;
/*     */   }
/*     */ 
/*     */   public int A()
/*     */   {
/* 160 */     return this.K.B().length + this.D.B().length + this.H.B().length + this.B.B().length;
/*     */   }
/*     */ 
/*     */   public class _A
/*     */   {
/*     */     private static final String I = "_";
/*     */     private static final String A = ".tup";
/*     */     private final byte[] D;
/*     */     private final String E;
/*     */     private final int B;
/*     */     private final SimpleDateFormat G;
/*     */     private final SimpleDateFormat F;
/*     */     private final String H;
/*     */ 
/*     */     private _A(String paramArrayOfByte, byte[] paramInt, int arg4)
/*     */     {
/* 198 */       Contract.preNonNull(paramInt);
/*     */       int i;
/* 199 */       Contract.pre(i, 1, 4);
/*     */ 
/* 201 */       this.H = paramArrayOfByte;
/* 202 */       this.D = paramInt;
/* 203 */       this.B = i;
/*     */ 
/* 206 */       this.G = new SimpleDateFormat("ddMMMyy");
/* 207 */       this.F = new SimpleDateFormat("HHmm");
/*     */ 
/* 210 */       this.E = ("Pump" + EA.A(this$1) + "_" + EA.B(this$1) + "_" + this.G.format(EA.C(this$1)) + "_" + "time" + this.F.format(EA.C(this$1)) + "_" + this.B + ".tup");
/*     */     }
/*     */ 
/*     */     private _A(String paramArrayOfInt, int[] paramInt, int arg4)
/*     */     {
/* 230 */       this(paramArrayOfInt, O._B.A(paramInt), i);
/*     */     }
/*     */ 
/*     */     public byte[] B()
/*     */     {
/* 242 */       return this.D;
/*     */     }
/*     */ 
/*     */     public String A()
/*     */     {
/* 252 */       return this.E;
/*     */     }
/*     */ 
/*     */     public String toString()
/*     */     {
/* 262 */       return this.H + ": " + A() + " - " + B().length + " bytes";
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.A.EA
 * JD-Core Version:    0.6.0
 */