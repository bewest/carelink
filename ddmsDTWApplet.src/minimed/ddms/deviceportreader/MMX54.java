/*    */ package minimed.ddms.deviceportreader;
/*    */ 
/*    */ class MMX54 extends MMX53
/*    */ {
/*    */   public static final int SNAPSHOT_FORMAT_ID = 117;
/*    */   static final String MODEL_NUMBER1 = "554";
/*    */   static final String MODEL_NUMBER2 = "754";
/*    */ 
/*    */   MMX54(int paramInt, long paramLong1, long paramLong2)
/*    */   {
/* 68 */     super(paramInt, paramLong1, paramLong2, "MiniMed MMT-554/754 (Paradigm2) Insulin Pump");
/*    */   }
/*    */ 
/*    */   static boolean isModelNumberSupported(String paramString)
/*    */   {
/* 83 */     return ("554".equals(paramString)) || ("754".equals(paramString));
/*    */   }
/*    */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.deviceportreader.MMX54
 * JD-Core Version:    0.6.0
 */