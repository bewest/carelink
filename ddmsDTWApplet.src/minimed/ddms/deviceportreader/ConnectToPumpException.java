/*     */ package minimed.ddms.A;
/*     */ 
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ public final class P extends Exception
/*     */ {
/*     */   public static final int G = 2;
/*     */   public static final int A = 3;
/*     */   public static final int F = 1000;
/*     */   public static final int K = 2000;
/*     */   public static final String O = "Bad Error Status Reply";
/*     */   public static final String R = "Bad Pump Model Reply";
/*     */   public static final String E = "Bad Pump State Reply";
/*     */   public static final String H = "Bad Temp Basal Reply";
/*     */   public static final String J = "Pump Active (bolus)";
/*     */   public static final String N = "Bad Pump Active (bolus) Reply";
/*     */   public static final String L = "Pump Active (temp basal)";
/*     */   public static final String Q = "Pump Alarm (error)";
/*     */   public static final String I = "Pump Alarm (state)";
/*     */   public static final String M = "Pump Model Command Rejected";
/*     */   public static final String D = "Unsupported Pump Model";
/*     */   public static final String B = "Wrong Device Selection";
/*     */   private final int C;
/*     */   private final String P;
/*     */ 
/*     */   public P(String paramString1, int paramInt, String paramString2)
/*     */   {
/* 135 */     super(paramString1);
/* 136 */     Contract.pre((paramInt == 2) || (paramInt == 3) || (paramInt == 1000) || (paramInt == 2000));
/*     */ 
/* 140 */     Contract.preNonNull(paramString2);
/*     */ 
/* 142 */     this.C = paramInt;
/* 143 */     this.P = paramString2;
/*     */   }
/*     */ 
/*     */   public int B()
/*     */   {
/* 153 */     return this.C;
/*     */   }
/*     */ 
/*     */   public String A()
/*     */   {
/* 162 */     return this.P;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 172 */     return super.toString() + "; reasonCode=" + B() + " (" + (B() == 2000 ? "REASON_WRONG_DEVICE_SELECTION" : B() == 1000 ? "REASON_PUMP_MODEL_UNSUPPORTED" : B() == 3 ? "REASON_PUMP_ACTIVE_ERROR" : B() == 2 ? "REASON_PUMP_ALARM_ERROR" : "UNKNOWN") + "); pumpValue='" + A() + "';";
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.A.P
 * JD-Core Version:    0.6.0
 */