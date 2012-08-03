/*     */ package minimed.ddms.A;
/*     */ 
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.Date;
/*     */ 
/*     */ public abstract interface G
/*     */ {
/*     */   public static final int V = 0;
/*     */   public static final int U = 1;
/*     */   public static final int a = 2;
/*     */   public static final int b = 0;
/*     */   public static final int d = 1;
/*     */   public static final int R = 2;
/*     */   public static final int Q = 3;
/*     */   public static final int T = 4;
/*     */   public static final int N = 5;
/*     */   public static final int M = 6;
/*     */   public static final int c = 7;
/*     */   public static final int O = 7;
/*     */   public static final int _ = 0;
/*     */   public static final int S = 1;
/*     */   public static final int C = 2;
/*     */   public static final int F = 3;
/*     */   public static final String X = "@commport";
/*     */   public static final String B = "@linkdevice";
/*     */   public static final String Z = "@serialnumber";
/* 140 */   public static final String[] K = { "not used", "Auto Detect Port", "Initializing Serial Port: @commport", "Initializing Link Device: @linkdevice on @commport", "Initializing Device: @serialnumber", "Acquiring Device Data", "Clean Up", "Auto Detect Device" };
/*     */   public static final int J = 1;
/*     */   public static final int W = 2;
/*     */   public static final int D = 3;
/*     */   public static final int P = 4;
/*     */   public static final int G = 5;
/*     */   public static final int L = 6;
/*     */   public static final int Y = 7;
/*     */   public static final int E = 8;
/*     */   public static final int I = 9;
/*     */   public static final int A = 9;
/* 207 */   public static final String[] H = { "not used", "Idle", "Initializing", "Setting AccessCode", "Sending Command", "Getting Data", "Getting Block Data", "Retrying", "Halting", "Halted" };
/*     */ 
/*     */   public abstract void A(v paramv, String paramString1, String paramString2, boolean paramBoolean)
/*     */     throws t, Z, IOException, P;
/*     */ 
/*     */   public abstract void A(v paramv, String paramString1, String paramString2)
/*     */     throws t, Z, IOException, P;
/*     */ 
/*     */   public abstract void B(v paramv, String paramString1, String paramString2, Date paramDate1, Date paramDate2)
/*     */     throws t, Z, IOException, P;
/*     */ 
/*     */   public abstract void A(v paramv, String paramString1, String paramString2, Date paramDate1, Date paramDate2)
/*     */     throws t, Z, IOException, P;
/*     */ 
/*     */   public abstract void B(v paramv, String paramString1, String paramString2)
/*     */     throws t, Z, IOException, P;
/*     */ 
/*     */   public abstract String A(v paramv)
/*     */     throws t, IOException;
/*     */ 
/*     */   public abstract String L();
/*     */ 
/*     */   public abstract InputStream C()
/*     */     throws Z, IOException;
/*     */ 
/*     */   public abstract EA J();
/*     */ 
/*     */   public abstract void A(String paramString)
/*     */     throws FileNotFoundException, IOException;
/*     */ 
/*     */   public abstract void P();
/*     */ 
/*     */   public abstract String R();
/*     */ 
/*     */   public abstract String S();
/*     */ 
/*     */   public abstract String D();
/*     */ 
/*     */   public abstract int I();
/*     */ 
/*     */   public abstract String O();
/*     */ 
/*     */   public abstract int B();
/*     */ 
/*     */   public abstract String Q();
/*     */ 
/*     */   public abstract int F();
/*     */ 
/*     */   public abstract String K();
/*     */ 
/*     */   public abstract Date N();
/*     */ 
/*     */   public abstract void A(int paramInt);
/*     */ 
/*     */   public abstract long E();
/*     */ 
/*     */   public abstract long G();
/*     */ 
/*     */   public abstract boolean M();
/*     */ 
/*     */   public abstract boolean A();
/*     */ 
/*     */   public abstract boolean H();
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.A.G
 * JD-Core Version:    0.6.0
 */