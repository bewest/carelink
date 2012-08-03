/*    */ package minimed.ddms.A;
/*    */ 
/*    */ public final class t extends Exception
/*    */ {
/*    */   private Integer B;
/*    */   private String A;
/*    */ 
/*    */   t(String paramString)
/*    */   {
/* 45 */     super(paramString);
/*    */   }
/*    */ 
/*    */   t(String paramString1, Integer paramInteger, String paramString2)
/*    */   {
/* 58 */     super(paramString1);
/* 59 */     this.B = paramInteger;
/* 60 */     this.A = paramString2;
/*    */   }
/*    */ 
/*    */   public Integer A()
/*    */   {
/* 71 */     return this.B;
/*    */   }
/*    */ 
/*    */   public String B()
/*    */   {
/* 81 */     return this.A;
/*    */   }
/*    */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.A.t
 * JD-Core Version:    0.6.0
 */