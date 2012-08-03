/*     */ package minimed.ddms.applet.dtw;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.prefs.BackingStoreException;
/*     */ import java.util.prefs.Preferences;
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ public final class PreferencesHelper
/*     */ {
/*     */   private final LogWriter m_log;
/*     */ 
/*     */   public PreferencesHelper(LogWriter paramLogWriter)
/*     */   {
/*  52 */     Contract.preNonNull(paramLogWriter);
/*  53 */     this.m_log = paramLogWriter;
/*     */   }
/*     */ 
/*     */   public boolean getSystemPreference(String paramString, boolean paramBoolean)
/*     */   {
/*  70 */     return getSystemPreferences().getBoolean(paramString, paramBoolean);
/*     */   }
/*     */ 
/*     */   public String getSystemPreference(String paramString1, String paramString2)
/*     */   {
/*  83 */     return getSystemPreferences().get(paramString1, paramString2);
/*     */   }
/*     */ 
/*     */   public String getSystemPreference(String paramString1, List paramList, String paramString2)
/*     */   {
/* 102 */     Contract.preNonNull(paramList);
/* 103 */     Contract.preNonNull(paramString2);
/* 104 */     Contract.pre(paramList.contains(paramString2));
/*     */ 
/* 106 */     String str = getSystemPreferences().get(paramString1, paramString2);
/* 107 */     if (!paramList.contains(str)) {
/* 108 */       str = paramString2;
/*     */     }
/*     */ 
/* 111 */     Contract.post(paramList.contains(str));
/* 112 */     return str;
/*     */   }
/*     */ 
/*     */   public boolean isSystemPreferenceSet(String paramString)
/*     */   {
/* 125 */     String str = "value representing no value!";
/* 126 */     return !str.equals(getSystemPreference(paramString, str));
/*     */   }
/*     */ 
/*     */   public void setSystemPreference(String paramString, boolean paramBoolean)
/*     */   {
/* 136 */     this.m_log.logInfo("SETTING SYSTEM PREFERENCE '" + paramString + "' to '" + paramBoolean + "'");
/*     */     try
/*     */     {
/* 140 */       getSystemPreferences().putBoolean(paramString, paramBoolean);
/*     */     }
/*     */     catch (IllegalStateException localIllegalStateException)
/*     */     {
/* 146 */       this.m_log.logWarning("setSystemPreference: setting '" + paramString + "' to '" + paramBoolean + "'" + " resulted in " + localIllegalStateException);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setSystemPreference(String paramString1, String paramString2)
/*     */   {
/* 158 */     this.m_log.logInfo("SETTING SYSTEM PREFERENCE '" + paramString1 + "' to '" + paramString2 + "'");
/*     */     try
/*     */     {
/* 162 */       getSystemPreferences().put(paramString1, paramString2);
/*     */     }
/*     */     catch (IllegalStateException localIllegalStateException)
/*     */     {
/* 168 */       this.m_log.logWarning("setSystemPreference: setting '" + paramString1 + "' to '" + paramString2 + "'" + " resulted in " + localIllegalStateException);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void flushSystemPreferences()
/*     */   {
/*     */     try
/*     */     {
/* 179 */       getSystemPreferences().flush();
/*     */     }
/*     */     catch (BackingStoreException localBackingStoreException)
/*     */     {
/* 183 */       this.m_log.logWarning("i/o; e = " + localBackingStoreException);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void clear()
/*     */   {
/*     */     try
/*     */     {
/* 192 */       getSystemPreferences().clear();
/*     */     } catch (BackingStoreException localBackingStoreException) {
/* 194 */       Contract.unreachable();
/*     */     }
/*     */   }
/*     */ 
/*     */   private Preferences getSystemPreferences()
/*     */   {
/* 210 */     Preferences localPreferences = Preferences.userNodeForPackage(getClass());
/* 211 */     Contract.post(localPreferences != null);
/* 212 */     return localPreferences;
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.PreferencesHelper
 * JD-Core Version:    0.6.0
 */