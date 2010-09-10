/*    */ package minimed.ddms.deviceportreader;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import minimed.util.Contract;
/*    */ 
/*    */ class CommunicationsLinkRS232 extends CommunicationsLinkDefault
/*    */ {
/*    */   CommunicationsLinkRS232(DeviceListener paramDeviceListener, String paramString)
/*    */   {
/* 45 */     super(paramDeviceListener, paramString, "pass-thru");
/*    */   }
/*    */ 
/*    */   public void execute(DeviceCommand paramDeviceCommand)
/*    */     throws BadDeviceCommException, SerialIOHaltedException, BadDeviceValueException
/*    */   {
/* 63 */     Contract.unreachable();
/*    */   }
/*    */ 
/*    */   void endCommunicationsIO()
/*    */     throws IOException
/*    */   {
/* 73 */     Contract.unreachable();
/*    */   }
/*    */ 
/*    */   void initCommunicationsIO()
/*    */     throws IOException, SerialIOHaltedException, BadDeviceCommException
/*    */   {
/* 86 */     Contract.unreachable();
/*    */   }
/*    */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.deviceportreader.CommunicationsLinkRS232
 * JD-Core Version:    0.6.0
 */