/*     */ package minimed.ddms.A;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ public class GA
/*     */   implements Serializable
/*     */ {
/*     */   private static final long B = 1L;
/*     */   private final String A;
/*     */ 
/*     */   private GA(String paramString)
/*     */   {
/*  54 */     this.A = paramString;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/*  64 */     return toString().hashCode();
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/*  75 */     return this.A;
/*     */   }
/*     */ 
/*     */   public static final class _C extends GA
/*     */   {
/* 389 */     public static final _C F = new _C("TIME");
/*     */ 
/* 394 */     public static final _C G = new _C("INSULIN");
/*     */ 
/*     */     private _C(String paramString)
/*     */     {
/* 406 */       super(null);
/*     */     }
/*     */ 
/*     */     public boolean equals(Object paramObject)
/*     */     {
/* 417 */       boolean bool = false;
/*     */ 
/* 419 */       if ((paramObject != null) && ((paramObject instanceof _C))) {
/* 420 */         _C local_C = (_C)paramObject;
/* 421 */         bool = toString().equals(local_C.toString());
/*     */       }
/*     */ 
/* 424 */       return bool;
/*     */     }
/*     */ 
/*     */     public int B()
/*     */     {
/*     */       int i;
/* 435 */       if (equals(G)) {
/* 436 */         i = 0;
/*     */       }
/* 438 */       else if (equals(F)) {
/* 439 */         i = 1;
/*     */       }
/*     */       else {
/* 442 */         i = 0;
/* 443 */         Contract.unreachable();
/*     */       }
/*     */ 
/* 447 */       return i;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static final class _A extends GA
/*     */   {
/* 318 */     public static final _A N = new _A("INSULIN_RATE");
/*     */ 
/* 323 */     public static final _A M = new _A("PERCENTAGE");
/*     */ 
/*     */     private _A(String paramString)
/*     */     {
/* 335 */       super(null);
/*     */     }
/*     */ 
/*     */     public boolean equals(Object paramObject)
/*     */     {
/* 346 */       boolean bool = false;
/*     */ 
/* 348 */       if ((paramObject != null) && ((paramObject instanceof _A))) {
/* 349 */         _A local_A = (_A)paramObject;
/* 350 */         bool = toString().equals(local_A.toString());
/*     */       }
/*     */ 
/* 353 */       return bool;
/*     */     }
/*     */ 
/*     */     public int E()
/*     */     {
/*     */       int i;
/* 364 */       if (equals(N)) {
/* 365 */         i = 0;
/*     */       }
/* 367 */       else if (equals(M)) {
/* 368 */         i = 1;
/*     */       }
/*     */       else {
/* 371 */         i = 0;
/* 372 */         Contract.unreachable();
/*     */       }
/*     */ 
/* 376 */       return i;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static final class _E extends GA
/*     */   {
/* 247 */     public static final _E L = new _E("FAST_ACTING");
/*     */ 
/* 252 */     public static final _E K = new _E("REGULAR");
/*     */ 
/*     */     private _E(String paramString)
/*     */     {
/* 264 */       super(null);
/*     */     }
/*     */ 
/*     */     public boolean equals(Object paramObject)
/*     */     {
/* 275 */       boolean bool = false;
/*     */ 
/* 277 */       if ((paramObject != null) && ((paramObject instanceof _E))) {
/* 278 */         _E local_E = (_E)paramObject;
/* 279 */         bool = toString().equals(local_E.toString());
/*     */       }
/*     */ 
/* 282 */       return bool;
/*     */     }
/*     */ 
/*     */     public int D()
/*     */     {
/*     */       int i;
/* 293 */       if (equals(L)) {
/* 294 */         i = 0;
/*     */       }
/* 296 */       else if (equals(K)) {
/* 297 */         i = 1;
/*     */       }
/*     */       else {
/* 300 */         i = 0;
/* 301 */         Contract.unreachable();
/*     */       }
/*     */ 
/* 305 */       return i;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static final class _D extends GA
/*     */   {
/* 167 */     public static final _D I = new _D("UNSET");
/*     */ 
/* 172 */     public static final _D H = new _D("GRAMS");
/*     */ 
/* 177 */     public static final _D J = new _D("EXCHANGES");
/*     */ 
/*     */     private _D(String paramString)
/*     */     {
/* 189 */       super(null);
/*     */     }
/*     */ 
/*     */     public boolean equals(Object paramObject)
/*     */     {
/* 200 */       boolean bool = false;
/*     */ 
/* 202 */       if ((paramObject != null) && ((paramObject instanceof _D))) {
/* 203 */         _D local_D = (_D)paramObject;
/* 204 */         bool = toString().equals(local_D.toString());
/*     */       }
/*     */ 
/* 207 */       return bool;
/*     */     }
/*     */ 
/*     */     public int C()
/*     */     {
/*     */       int i;
/* 218 */       if (equals(I)) {
/* 219 */         i = 0;
/*     */       }
/* 221 */       else if (equals(H)) {
/* 222 */         i = 1;
/*     */       }
/* 224 */       else if (equals(J)) {
/* 225 */         i = 2;
/*     */       }
/*     */       else {
/* 228 */         i = 0;
/* 229 */         Contract.unreachable();
/*     */       }
/*     */ 
/* 234 */       return i;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static final class _B extends GA
/*     */   {
/*  87 */     public static final _B E = new _B("UNSET");
/*     */ 
/*  92 */     public static final _B D = new _B("MG_DL");
/*     */ 
/*  97 */     public static final _B C = new _B("MMOL_L");
/*     */ 
/*     */     private _B(String paramString)
/*     */     {
/* 109 */       super(null);
/*     */     }
/*     */ 
/*     */     public boolean equals(Object paramObject)
/*     */     {
/* 120 */       boolean bool = false;
/*     */ 
/* 122 */       if ((paramObject != null) && ((paramObject instanceof _B))) {
/* 123 */         _B local_B = (_B)paramObject;
/* 124 */         bool = toString().equals(local_B.toString());
/*     */       }
/*     */ 
/* 127 */       return bool;
/*     */     }
/*     */ 
/*     */     public int A()
/*     */     {
/*     */       int i;
/* 138 */       if (equals(E)) {
/* 139 */         i = 0;
/*     */       }
/* 141 */       else if (equals(D)) {
/* 142 */         i = 1;
/*     */       }
/* 144 */       else if (equals(C)) {
/* 145 */         i = 2;
/*     */       }
/*     */       else {
/* 148 */         i = 0;
/* 149 */         Contract.unreachable();
/*     */       }
/*     */ 
/* 154 */       return i;
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
s * Qualified Name:     minimed.ddms.A.GA
 * JD-Core Version:    0.6.0
 */