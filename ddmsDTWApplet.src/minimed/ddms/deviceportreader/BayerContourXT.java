/*    */ package minimed.ddms.A;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import mdt.common.device.driver.minimed.BayerUSB;
/*    */ 
/*    */ final class j extends k
/*    */ {
/*    */   private static final String Т = "Bayer6200";
/*    */   public static final String У = "vid_1a79";
/*    */   public static final String Ф = "pid_6200";
/*    */ 
/*    */   j()
/*    */   {
/* 48 */     super("Bayer Contour XT Meter", 28, "Bayer6200");
/*    */   }
/*    */ 
/*    */   void ª()
/*    */     throws IOException
/*    */   {
/* 55 */     this.К = new BayerUSB(10L, "vid_1a79", "pid_6200");
/*    */   }
/*    */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.A.j
 * JD-Core Version:    0.6.0
 */