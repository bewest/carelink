/*     */ package minimed.ddms.A;
/*     */ 
/*     */ import java.io.PrintWriter;
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ public final class I
/*     */ {
/*     */   public static final int o = 2;
/*     */   public static final int E = 4;
/*     */   public static final int R = 5;
/*     */   public static final int W = 6;
/*     */   public static final int S = 7;
/*     */   public static final int C = 8;
/*     */   public static final int Q = 9;
/*     */   public static final int Y = 10;
/*     */   public static final int F = 11;
/*     */   public static final int G = 12;
/*     */   public static final int X = 13;
/*     */   public static final int f = 14;
/*     */   public static final int d = 15;
/*     */   public static final int r = 19;
/*     */   public static final int l = 20;
/*     */   public static final int p = 16;
/*     */   public static final int h = 17;
/*     */   public static final int I = 19;
/*     */   public static final int K = 20;
/*     */   public static final int t = 21;
/*     */   public static final int A = 22;
/*     */   public static final int J = 23;
/*     */   public static final int Z = 24;
/*     */   public static final int P = 25;
/*     */   public static final int g = 26;
/*     */   public static final int H = 27;
/*     */   public static final int B = 28;
/*     */   public static final String O = "512";
/*     */   public static final String s = "712";
/*     */   public static final String L = "515";
/*     */   public static final String q = "715";
/*     */   public static final String m = "522";
/*     */   public static final String b = "722";
/*     */   public static final String T = "522K";
/*     */   public static final String N = "722K";
/*     */   public static final String M = "7100";
/*     */   public static final String V = "7100B";
/*     */   public static final String e = "7200";
/*     */   public static final String U = "7100K";
/*     */   public static final String j = "523";
/*     */   public static final String _ = "723";
/*     */   public static final String D = "523K";
/*     */   public static final String i = "723K";
/*     */   public static final String n = "553";
/*     */   public static final String c = "753";
/*     */   public static final String k = "554";
/*     */   public static final String a = "754";
/*     */ 
/*     */   public static G A(int paramInt1, PrintWriter paramPrintWriter, int paramInt2, int paramInt3)
/*     */   {
/* 328 */     Object localObject = null;
/*     */ 
/* 331 */     if (paramPrintWriter != null) {
/* 332 */       O.A(paramPrintWriter);
/*     */     }
/* 334 */     O.D(paramInt2);
/*     */ 
/* 336 */     switch (paramInt1)
/*     */     {
/*     */     case 13:
/* 339 */       localObject = new X(paramInt3);
/* 340 */       break;
/*     */     case 16:
/* 342 */       localObject = new F();
/* 343 */       break;
/*     */     case 17:
/* 345 */       localObject = new T();
/* 346 */       break;
/*     */     case 2:
/* 348 */       localObject = new V();
/* 349 */       break;
/*     */     case 4:
/* 351 */       localObject = new q();
/* 352 */       break;
/*     */     case 6:
/* 354 */       localObject = new s();
/* 355 */       break;
/*     */     case 7:
/* 357 */       localObject = new i();
/* 358 */       break;
/*     */     case 5:
/* 360 */       localObject = new B();
/* 361 */       break;
/*     */     case 21:
/* 363 */       localObject = new R();
/* 364 */       break;
/*     */     case 26:
/* 366 */       localObject = new h();
/* 367 */       break;
/*     */     case 10:
/* 369 */       localObject = new L();
/* 370 */       break;
/*     */     case 11:
/* 372 */       localObject = new U();
/* 373 */       break;
/*     */     case 8:
/* 375 */       localObject = new o();
/* 376 */       break;
/*     */     case 9:
/* 378 */       localObject = new g();
/* 379 */       break;
/*     */     case 19:
/* 381 */       localObject = new Y();
/* 382 */       break;
/*     */     case 20:
/* 384 */       localObject = new r();
/* 385 */       break;
/*     */     case 27:
/* 387 */       localObject = new k();
/* 388 */       break;
/*     */     case 28:
/* 390 */       localObject = new j();
/* 391 */       break;
/*     */     case 12:
/* 393 */       localObject = new A();
/* 394 */       break;
/*     */     case 22:
/* 396 */       localObject = new S();
/* 397 */       break;
/*     */     case 23:
/*     */     case 24:
/*     */     case 25:
/* 401 */       localObject = new C(paramInt1);
/* 402 */       break;
/*     */     case 3:
/*     */     case 14:
/*     */     case 15:
/*     */     case 18:
/*     */     default:
/* 405 */       Contract.unreachable();
/*     */     }
/*     */ 
/* 409 */     return (G)localObject;
/*     */   }
/*     */ 
/*     */   static String A(int paramInt)
/*     */   {
/*     */     String str;
/* 421 */     switch (paramInt) {
/*     */     case 14:
/* 423 */       str = "ComLink";
/* 424 */       break;
/*     */     case 19:
/* 426 */       str = "ComLink2";
/* 427 */       break;
/*     */     case 15:
/* 429 */       str = "ParadigmLink";
/* 430 */       break;
/*     */     case 20:
/* 432 */       str = "Bayer Contour XT Link";
/* 433 */       break;
/*     */     case 16:
/*     */     case 17:
/*     */     case 18:
/*     */     default:
/* 435 */       str = "UNKNOWN";
/*     */     }
/*     */ 
/* 439 */     return str;
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.A.I
 * JD-Core Version:    0.6.0
 */