/*     */ package minimed.ddms.deviceportreader;
/*     */ 
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ public final class ConnectToPumpException extends Exception
/*     */ {
/*     */   public static final int REASON_PUMP_ALARM_ERROR = 2;
/*     */   public static final int REASON_PUMP_ACTIVE_ERROR = 3;
/*     */   public static final int REASON_PUMP_MODEL_UNSUPPORTED = 1000;
/*     */   public static final int REASON_WRONG_DEVICE_SELECTION = 2000;
/*     */   public static final String MSG_BAD_ERROR_STATUS_REPLY = "Bad Error Status Reply";
/*     */   public static final String MSG_BAD_PUMP_MODEL_REPLY = "Bad Pump Model Reply";
/*     */   public static final String MSG_BAD_PUMP_STATE_REPLY = "Bad Pump State Reply";
/*     */   public static final String MSG_BAD_TEMP_BASAL_REPLY = "Bad Temp Basal Reply";
/*     */   public static final String MSG_PUMP_ACTIVE_BOLUS = "Pump Active (bolus)";
/*     */   public static final String MSG_BAD_PUMP_ACTIVE_BOLUS = "Bad Pump Active (bolus) Reply";
/*     */   public static final String MSG_PUMP_ACTIVE_TEMP_BASAL = "Pump Active (temp basal)";
/*     */   public static final String MSG_PUMP_ALARM_ERROR = "Pump Alarm (error)";
/*     */   public static final String MSG_PUMP_ALARM_STATE = "Pump Alarm (state)";
/*     */   public static final String MSG_PUMP_MODEL_COMMAND_REJECTED = "Pump Model Command Rejected";
/*     */   public static final String MSG_UNSUPPORTED_PUMP_MODEL = "Unsupported Pump Model";
/*     */   public static final String MSG_WRONG_DEVICE_SELECTION = "Wrong Device Selection";
/*     */   private final int m_reasonCode;
/*     */   private final String m_pumpValue;
/*     */ 
/*     */   public ConnectToPumpException(String paramString1, int paramInt, String paramString2)
/*     */   {
/* 135 */     super(paramString1);
/* 136 */     Contract.pre((paramInt == 2) || (paramInt == 3) || (paramInt == 1000) || (paramInt == 2000));
/*     */ 
/* 140 */     Contract.preNonNull(paramString2);
/*     */ 
/* 142 */     this.m_reasonCode = paramInt;
/* 143 */     this.m_pumpValue = paramString2;
/*     */   }
/*     */ 
/*     */   public int getReasonCode()
/*     */   {
/* 153 */     return this.m_reasonCode;
/*     */   }
/*     */ 
/*     */   public String getPumpValue()
/*     */   {
/* 162 */     return this.m_pumpValue;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 172 */     return super.toString() + "; reasonCode=" + getReasonCode() + " (" + (getReasonCode() == 2000 ? "REASON_WRONG_DEVICE_SELECTION" : getReasonCode() == 1000 ? "REASON_PUMP_MODEL_UNSUPPORTED" : getReasonCode() == 3 ? "REASON_PUMP_ACTIVE_ERROR" : getReasonCode() == 2 ? "REASON_PUMP_ALARM_ERROR" : "UNKNOWN") + "); pumpValue='" + getPumpValue() + "';";
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.deviceportreader.ConnectToPumpException
 * JD-Core Version:    0.6.0
 */