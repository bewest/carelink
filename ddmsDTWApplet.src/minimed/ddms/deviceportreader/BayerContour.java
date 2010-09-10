/*    */ package minimed.ddms.deviceportreader;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import java.util.Set;
/*    */ import java.util.Vector;
/*    */ 
/*    */ final class BayerContour extends BayerMeter
/*    */ {
/*    */   private static final int MAX_RECORD_COUNT = 480;
/*    */   private static final String PRODUCT_CODE = "Bayer7150";
/*    */ 
/*    */   BayerContour()
/*    */   {
/* 48 */     super(480);
/* 49 */     this.m_description = "Bayer Glucometer Contour Meter";
/* 50 */     logInfo(this, "creating interface to the '" + this.m_description + "', package version " + getPackageVersion());
/*    */ 
/* 52 */     this.m_deviceClassID = 20;
/* 53 */     this.m_productCodes.add("Bayer7150");
/*    */   }
/*    */ 
/*    */   void createCommands()
/*    */   {
/* 63 */     this.m_cmdGetData = new BayerMeter.CommandGetData(this);
/*    */   }
/*    */ 
/*    */   Vector createCommandList()
/*    */   {
/* 72 */     System.out.println("created command list");
/* 73 */     Vector localVector = new Vector();
/*    */ 
/* 76 */     localVector.addElement(this.m_cmdGetData);
/* 77 */     return localVector;
/*    */   }
/*    */ 
/*    */   void addCurrSettingsElementToSnapshot(int paramInt)
/*    */   {
/*    */   }
/*    */ 
/*    */   void decodeCurrentSettings()
/*    */     throws BadDeviceValueException
/*    */   {
/*    */   }
/*    */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.deviceportreader.BayerContour
 * JD-Core Version:    0.6.0
 */