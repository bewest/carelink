/*    */ package Serialio;
/*    */ 
/*    */ public class PortDriverInfo
/*    */ {
/*    */   protected String name;
/*    */   protected String manufacture;
/*    */   protected String description;
/*    */ 
/*    */   public PortDriverInfo(String paramString1, String paramString2, String paramString3)
/*    */   {
/* 35 */     this.name = paramString1;
/* 36 */     this.manufacture = paramString2;
/* 37 */     this.description = paramString3;
/*    */   }
/*    */   public String getPortName() {
/* 40 */     return this.name; } 
/* 41 */   public String getDriverMfgr() { return this.manufacture; } 
/* 42 */   public String getDriverDesc() { return this.description;
/*    */   }
/*    */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     Serialio.PortDriverInfo
 * JD-Core Version:    0.6.0
 */