/*    */ package minimed.ddms.applet.dtw;
/*    */ 
/*    */ public final class InstallFileVersion
/*    */ {
/*    */   private final String m_name;
/*    */   private final String m_desc;
/*    */   private byte[] m_data;
/*    */ 
/*    */   public InstallFileVersion(String paramString1, String paramString2)
/*    */   {
/* 40 */     this.m_name = paramString1;
/* 41 */     this.m_desc = paramString2;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 52 */     PropertyWriter localPropertyWriter = new PropertyWriter();
/* 53 */     localPropertyWriter.add("name", this.m_name);
/* 54 */     localPropertyWriter.add("desc", this.m_desc);
/* 55 */     return localPropertyWriter.toString();
/*    */   }
/*    */ 
/*    */   public String getName()
/*    */   {
/* 64 */     return this.m_name;
/*    */   }
/*    */ 
/*    */   String getDesc()
/*    */   {
/* 73 */     return this.m_desc;
/*    */   }
/*    */ 
/*    */   public byte[] getData()
/*    */   {
/* 82 */     return this.m_data;
/*    */   }
/*    */ 
/*    */   public void setData(byte[] paramArrayOfByte)
/*    */   {
/* 91 */     this.m_data = paramArrayOfByte;
/*    */   }
/*    */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.InstallFileVersion
 * JD-Core Version:    0.6.0
 */