/*    */ package Serialio.tool;
/*    */ 
/*    */ public class StringCompare
/*    */   implements Compare
/*    */ {
/*    */   public boolean lessThan(Object paramObject1, Object paramObject2)
/*    */   {
/* 19 */     return ((String)paramObject1).toLowerCase().compareTo(((String)paramObject2).toLowerCase()) < 0;
/*    */   }
/*    */ 
/*    */   public boolean lessThanOrEqual(Object paramObject1, Object paramObject2) {
/* 23 */     return ((String)paramObject1).toLowerCase().compareTo(((String)paramObject2).toLowerCase()) <= 0;
/*    */   }
/*    */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     Serialio.tool.StringCompare
 * JD-Core Version:    0.6.0
 */