/*    */ package minimed.ddms.deviceportreader;
/*    */ 
/*    */ import java.util.Set;
/*    */ 
/*    */ final class MedisenseXtra extends MedisenseBGKetone
/*    */ {
/*    */   public static final int SNAPSHOT_FORMAT_ID = 131;
/*    */   private static final String PRODUCT_CODE1 = "QID2";
/*    */   private static final String PRODUCT_CODE2 = "XCEED";
/*    */ 
/*    */   MedisenseXtra()
/*    */   {
/* 50 */     super("Medisense Precision Xtra / Xceed Meter", 131, 10, "QID2");
/*    */ 
/* 54 */     getProductCodes().add("XCEED");
/*    */   }
/*    */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.deviceportreader.MedisenseXtra
 * JD-Core Version:    0.6.0
 */