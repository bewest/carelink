/*     */ package minimed.util;
/*     */ 
/*     */ public class OSInfo
/*     */ {
/*     */   public static String getAllUserPath()
/*     */   {
/*  38 */     return isMac() ? System.getProperty("user.home") : System.getenv("ALLUSERSPROFILE");
/*     */   }
/*     */ 
/*     */   public static String getUserData()
/*     */   {
/*  47 */     return isMac() ? "Library" : "Application Data";
/*     */   }
/*     */ 
/*     */   public static boolean isWindowsVista(String paramString)
/*     */   {
/*  59 */     if (("Windows Vista".equals(paramString)) || ("Windows 7".equals(paramString)))
/*  60 */       return true;
/*  61 */     if ("Windows NT (unknown)".equals(paramString)) {
/*  62 */       return "6.0".equals(System.getProperty("os.version"));
/*     */     }
/*  64 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean isWindowsVista()
/*     */   {
/*  74 */     return isWindowsVista(getOSName());
/*     */   }
/*     */ 
/*     */   public static boolean isWindowsNT(String paramString)
/*     */   {
/*  84 */     return "Windows NT".equals(paramString);
/*     */   }
/*     */ 
/*     */   public static boolean isWindows98(String paramString)
/*     */   {
/*  94 */     return "Windows 98".equals(paramString);
/*     */   }
/*     */ 
/*     */   public static boolean isMac()
/*     */   {
/* 103 */     String str = System.getProperty("os.name");
/* 104 */     return str.startsWith("Mac OS");
/*     */   }
/*     */ 
/*     */   public static boolean isMacOSX()
/*     */   {
/* 114 */     String str = System.getProperty("os.name");
/* 115 */     return str.startsWith("Mac OS X");
/*     */   }
/*     */ 
/*     */   public static String getOSName()
/*     */   {
/* 124 */     return System.getProperty("os.name");
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.util.OSInfo
 * JD-Core Version:    0.6.0
 */