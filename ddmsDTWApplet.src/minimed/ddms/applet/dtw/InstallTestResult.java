/*    */ package minimed.ddms.applet.dtw;
/*    */ 
/*    */ public final class InstallTestResult extends OperationResult
/*    */ {
/*    */   public static final int RESULT_NO_INSTALL = 0;
/*    */   public static final int RESULT_INSTALL = 1;
/*    */   private final InstallFileVersion m_fileToInstall;
/*    */ 
/*    */   public InstallTestResult(int paramInt)
/*    */   {
/* 51 */     this(paramInt, null);
/*    */   }
/*    */ 
/*    */   public InstallTestResult(int paramInt, InstallFileVersion paramInstallFileVersion)
/*    */   {
/* 61 */     super(paramInt, null);
/* 62 */     this.m_fileToInstall = paramInstallFileVersion;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 73 */     PropertyWriter localPropertyWriter = new PropertyWriter(super.toString());
/* 74 */     localPropertyWriter.add("fileToInstall", this.m_fileToInstall);
/* 75 */     return localPropertyWriter.toString();
/*    */   }
/*    */ 
/*    */   public InstallFileVersion getFileToInstall()
/*    */   {
/* 84 */     return this.m_fileToInstall;
/*    */   }
/*    */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.InstallTestResult
 * JD-Core Version:    0.6.0
 */