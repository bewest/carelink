/*    */ package minimed.ddms.A;
/*    */ 
/*    */ public abstract class n
/*    */ {
/*    */   String A;
/*    */ 
/*    */   n(String paramString)
/*    */   {
/* 44 */     this.A = paramString;
/*    */   }
/*    */ 
/*    */   abstract void A()
/*    */     throws t, Z;
/*    */ 
/*    */   public String toString()
/*    */   {
/* 62 */     return "(" + this.A + ")";
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
 * Qualified Name:     minimed.ddms.A.n
 * JD-Core Version:    0.6.0
 */