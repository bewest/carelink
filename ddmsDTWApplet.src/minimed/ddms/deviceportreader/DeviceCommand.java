/*    */ package minimed.ddms.deviceportreader;
/*    */ 
/*    */ public abstract class DeviceCommand
/*    */ {
/*    */   String m_description;
/*    */ 
/*    */   DeviceCommand(String paramString)
/*    */   {
/* 44 */     this.m_description = paramString;
/*    */   }
/*    */ 
/*    */   abstract void execute()
/*    */     throws BadDeviceCommException, BadDeviceValueException;
/*    */ 
/*    */   public String toString()
/*    */   {
/* 62 */     return "(" + this.m_description + ")";
/*    */   }
/*    */ 
/*    */   public int hashCode()
/*    */   {
/* 70 */     return toString().hashCode();
/*    */   }
/*    */ 
/*    */   public boolean equals(Object paramObject)
/*    */   {
/* 82 */     return (paramObject != null) && (hashCode() == paramObject.hashCode());
/*    */   }
/*    */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.deviceportreader.DeviceCommand
 * JD-Core Version:    0.6.0
 */