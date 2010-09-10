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
/*     */ 
/* 139 */     getSystemPreferences().putBoolean(paramString, paramBoolean);
/*     */   }
/*     */ 
/*     */   public void setSystemPreference(String paramString1, String paramString2)
/*     */   {
/* 149 */     this.m_log.logInfo("SETTING SYSTEM PREFERENCE '" + paramString1 + "' to '" + paramString2 + "'");
/*     */ 
/* 152 */     getSystemPreferences().put(paramString1, paramString2);
/*     */   }
/*     */ 
/*     */   public void flushSystemPreferences()
/*     */   {
/*     */     try
/*     */     {
/* 161 */       getSystemPreferences().flush();
/*     */     }
/*     */     catch (BackingStoreException localBackingStoreException)
/*     */     {
/* 165 */       this.m_log.logWarning("i/o; e = " + localBackingStoreException);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void clear()
/*     */   {
/*     */     try
/*     */     {
/* 174 */       getSystemPreferences().clear();
/*     */     } catch (BackingStoreException localBackingStoreException) {
/* 176 */       Contract.unreachable();
/*     */     }
/*     */   }
/*     */ 
/*     */   private Preferences getSystemPreferences()
/*     */   {
/* 192 */     Preferences localPreferences = Preferences.userNodeForPackage(getClass());
/* 193 */     Contract.post(localPreferences != null);
/* 194 */     return localPreferences;
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.PreferencesHelper
 * JD-Core Version:    0.6.0
 */