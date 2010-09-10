/*    */ package minimed.ddms.deviceportreader;
/*    */ 
/*    */ public final class BadDeviceCommException extends Exception
/*    */ {
/*    */   private Integer m_errorCode;
/*    */   private String m_errorCodeDescription;
/*    */ 
/*    */   BadDeviceCommException(String paramString)
/*    */   {
/* 45 */     super(paramString);
/*    */   }
/*    */ 
/*    */   BadDeviceCommException(String paramString1, Integer paramInteger, String paramString2)
/*    */   {
/* 58 */     super(paramString1);
/* 59 */     this.m_errorCode = paramInteger;
/* 60 */     this.m_errorCodeDescription = paramString2;
/*    */   }
/*    */ 
/*    */   public Integer getErrorCode()
/*    */   {
/* 71 */     return this.m_errorCode;
/*    */   }
/*    */ 
/*    */   public String getErrorCodeDescription()
/*    */   {
/* 81 */     return this.m_errorCodeDescription;
/*    */   }
/*    */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.deviceportreader.BadDeviceCommException
 * JD-Core Version:    0.6.0
 */