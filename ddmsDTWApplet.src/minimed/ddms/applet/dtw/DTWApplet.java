/*     */ package minimed.ddms.applet.dtw;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.net.URL;
/*     */ import java.security.AccessControlException;
/*     */ import java.util.HashMap;
/*     */ import java.util.Locale;
/*     */ import java.util.MissingResourceException;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.Properties;
/*     */ import java.util.ResourceBundle;
/*     */ import java.util.StringTokenizer;
/*     */ import java.util.TimeZone;
/*     */ import javax.swing.JApplet;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.UIManager;
/*     */ import minimed.ddms.applet.dtw.wizard.Wizard;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardConfig;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardConfig.USBConfig;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardSelections;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardStepProvider;
/*     */ import minimed.util.Contract;
/*     */ import netscape.javascript.JSObject;
/*     */ 
/*     */ public final class DTWApplet extends JApplet
/*     */ {
/*     */   private static ResourceBundle resourceBundle;
/*     */   private Wizard m_wizard;
/*     */ 
/*     */   public static ResourceBundle getResourceBundle()
/*     */   {
/*  57 */     if (resourceBundle == null)
/*     */     {
/*  60 */       setResourceBundle(Locale.US);
/*     */     }
/*  62 */     return resourceBundle;
/*     */   }
/*     */ 
/*     */   private static void setResourceBundle(Locale paramLocale)
/*     */   {
/*  70 */     resourceBundle = ResourceBundle.getBundle("minimed.ddms.applet.dtw.AppletResources", paramLocale);
/*     */   }
/*     */ 
/*     */   private static Locale getLocale(String paramString)
/*     */   {
/*  82 */     Contract.preNonNull(paramString);
/*     */     try {
/*  84 */       StringTokenizer localStringTokenizer = new StringTokenizer(paramString, "_");
/*  85 */       String str1 = localStringTokenizer.nextToken();
/*  86 */       String str2 = localStringTokenizer.nextToken();
/*  87 */       return new Locale(str1, str2);
/*     */     } catch (NoSuchElementException localNoSuchElementException) {
/*  89 */       Contract.unreachable();
/*     */     } catch (MissingResourceException localMissingResourceException) {
/*  91 */       Contract.unreachable();
/*     */     }
/*  93 */     return null;
/*     */   }
/*     */ 
/*     */   public void start()
/*     */   {
/* 100 */     this.m_wizard = null;
/*     */ 
/* 102 */     if (!wasAcceptedByUser()) {
/* 103 */       getContentPane().setLayout(new BorderLayout());
/* 104 */       localObject = new JLabel("Signed applet was not accepted - permission denied.");
/* 105 */       ((JLabel)localObject).setHorizontalAlignment(0);
/* 106 */       ((JLabel)localObject).setIcon(UIManager.getIcon("OptionPane.errorIcon"));
/* 107 */       getContentPane().add((Component)localObject, "Center");
/* 108 */       return;
/*     */     }
/*     */ 
/* 112 */     Object localObject = getSystemVariables();
/* 113 */     Properties localProperties = getImageIndex();
/*     */ 
/* 116 */     Locale localLocale = getLocale(getParameter("locale"));
/* 117 */     setResourceBundle(localLocale);
/*     */ 
/* 119 */     setLocale(localLocale);
/*     */ 
/* 121 */     JComponent.setDefaultLocale(localLocale);
/*     */ 
/* 124 */     String str = getParameter("controlCode");
/* 125 */     ControlCodeParser localControlCodeParser = new ControlCodeParser(str);
/* 126 */     LogWriter localLogWriter = new LogWriter(localControlCodeParser.getLogToConsole());
/*     */ 
/* 128 */     localLogWriter.logInfo("DTWApplet start");
/* 129 */     localLogWriter.logInfo("System variables are: " + localObject);
/*     */ 
/* 132 */     WizardConfig localWizardConfig = createWizardConfig((Properties)localObject, localProperties, localControlCodeParser, localLogWriter);
/*     */ 
/* 135 */     this.m_wizard = new Wizard(localWizardConfig);
/*     */ 
/* 138 */     localLogWriter.logInfo(this.m_wizard.toString());
/*     */ 
/* 141 */     this.m_wizard.start();
/*     */   }
/*     */ 
/*     */   public void stop()
/*     */   {
/* 148 */     if (this.m_wizard != null)
/* 149 */       this.m_wizard.stop();
/*     */   }
/*     */ 
/*     */   public void enableUnloadMessage(boolean paramBoolean)
/*     */   {
/* 163 */     String str = "deviceUpload_enableUnloadMessage";
/* 164 */     Object[] arrayOfObject = { Boolean.valueOf(paramBoolean) };
/* 165 */     JSObject localJSObject = JSObject.getWindow(this);
/* 166 */     localJSObject.call(str, arrayOfObject);
/*     */ 
/* 169 */     str = "deviceUpload_isUnloadMessageEnabled";
/* 170 */     arrayOfObject = null;
/* 171 */     boolean bool = ((Boolean)localJSObject.call(str, arrayOfObject)).booleanValue();
/*     */ 
/* 173 */     Contract.post(paramBoolean == bool);
/*     */   }
/*     */ 
/*     */   private WizardConfig createWizardConfig(Properties paramProperties1, Properties paramProperties2, ControlCodeParser paramControlCodeParser, LogWriter paramLogWriter)
/*     */   {
/* 196 */     Contract.preNonNull(paramProperties1);
/* 197 */     Contract.preNonNull(paramProperties2);
/* 198 */     Contract.preNonNull(paramControlCodeParser);
/* 199 */     Contract.preNonNull(paramLogWriter);
/*     */ 
/* 201 */     String str1 = System.getProperty("user.home");
/* 202 */     String str2 = System.getProperty("java.home");
/* 203 */     String str3 = paramProperties1.getProperty("DTWSerialPortDllFileName");
/* 204 */     String str4 = str1 + File.separator + "Medtronic";
/* 205 */     String str5 = str2 + File.separator + "bin" + File.separator + "ddmsDTWusb";
/*     */ 
/* 207 */     String str6 = str4 + File.separator + "ddmsDTWusb" + File.separator + "bd";
/*     */ 
/* 217 */     String str7 = str4 + File.separator + "ddmsDTWusb" + File.separator + "ComLink2" + File.separator + "Jungo 8.1.1";
/*     */ 
/* 226 */     String str8 = getParameter("lastPumpUniqueID");
/* 227 */     String str9 = getParameter("lastMeterUniqueID");
/* 228 */     String str10 = getParameter("lastCGMUniqueID");
/* 229 */     WizardStepProvider localWizardStepProvider = null;
/*     */     try
/*     */     {
/* 232 */       Class localClass = Class.forName(paramProperties1.getProperty("DTWWizardStepProvider"));
/* 233 */       localWizardStepProvider = (WizardStepProvider)localClass.newInstance();
/*     */     }
/*     */     catch (ClassNotFoundException localClassNotFoundException) {
/* 236 */       Contract.unreachable();
/*     */     }
/*     */     catch (InstantiationException localInstantiationException) {
/* 239 */       Contract.unreachable();
/*     */     }
/*     */     catch (IllegalAccessException localIllegalAccessException) {
/* 242 */       Contract.unreachable();
/*     */     }
/*     */ 
/* 246 */     WizardConfig.USBConfig localUSBConfig1 = new WizardConfig.USBConfig(str6, str5, "/drivers/usb/bd/", new String[] { "WDMMDMLD.VXD", "bdhidcom.inf", "BdHidCom.sys", "BDHidComInst.exe", "CCPORT.SYS", "bdhidcom.cat" }, new String[] { str6 + File.separator + "BDHidComInst.exe" }, "install-success.txt");
/*     */ 
/* 264 */     WizardConfig.USBConfig localUSBConfig2 = new WizardConfig.USBConfig(str7, str7, "/drivers/usb/ComLink2/", new String[] { "Install_MDTCLUSB.cmd", "cl2_jni_wrapper.dll", "difxapi.dll", "MDTCLUSB.inf", "Install_MDTCLUSB.cmd", "ReInstall_MDTCLUSB.cmd", "wd811.cat", "mdtclusb.cat", "wdreg.exe", "wdreg_gui.exe", "windrvr6.inf", "windrvr6.sys" }, new String[] { str7 + File.separator + "Install_MDTCLUSB.cmd" }, "install-success.txt");
/*     */ 
/* 286 */     String str11 = System.getProperty("os.name");
/* 287 */     WizardConfig localWizardConfig = new WizardConfig(getCodeBase().getProtocol(), paramProperties1.getProperty("systemVersion"), getParameter("systemVersion"), getParameter("userName"), str4, str3, paramProperties1.getProperty("DTWSerialPortDLLFileDigest"), getClass().getResourceAsStream("/drivers/serial/" + str3), paramProperties1.getProperty("messageDigestAlgorithm"), localUSBConfig1, localUSBConfig2, getCodeBase() + paramProperties1.getProperty("DTWServletPath"), getCodeBase() + paramProperties1.getProperty("DTWServletPath"), paramControlCodeParser.getLogToServer(), paramProperties1.getProperty("DTWNoLogMessage"), paramControlCodeParser.getKeepSnapshotOnServer(), paramControlCodeParser.getTraceUpload(), paramControlCodeParser.getDevicePortReaderMessageLevel(), paramControlCodeParser.getLastPumpHistoryPageReadOverride(), paramControlCodeParser.getLastGlucoseHistoryPageReadOverride(), paramLogWriter, getContentPane(), paramProperties2, new HashMap(), isWindowsVista(str11), "Windows NT".equals(str11), "Windows 98".equals(str11), TimeZone.getDefault().getID(), (str8 != null) && (str8.length() > 0) ? str8 : null, (str9 != null) && (str9.length() > 0) ? str9 : null, (str10 != null) && (str10.length() > 0) ? str10 : null, new WizardSelections(paramLogWriter), localWizardStepProvider, !"false".equals(getParameter("uploadX15PumpOnly")), !"false".equals(getParameter("uploadParadigmLinkMeterOnly")), "true".equals(getParameter("uploadCGM")), "true".equals(getParameter("uploadG3B")), this);
/*     */ 
/* 335 */     Contract.postNonNull(localWizardConfig);
/* 336 */     return localWizardConfig;
/*     */   }
/*     */ 
/*     */   private boolean isWindowsVista(String paramString)
/*     */   {
/* 348 */     if (("Windows Vista".equals(paramString)) || ("Windows 7".equals(paramString)))
/* 349 */       return true;
/* 350 */     if ("Windows NT (unknown)".equals(paramString)) {
/* 351 */       return "6.0".equals(System.getProperty("os.version"));
/*     */     }
/* 353 */     return false;
/*     */   }
/*     */ 
/*     */   private Properties getSystemVariables()
/*     */   {
/* 364 */     Properties localProperties = new Properties();
/* 365 */     InputStream localInputStream = getClass().getResourceAsStream("/config/SystemVariables.properties");
/* 366 */     if (localInputStream == null)
/*     */     {
/* 369 */       Contract.unreachable();
/*     */     }
/*     */     try
/*     */     {
/* 373 */       localProperties.load(localInputStream);
/*     */     }
/*     */     catch (IOException localIOException)
/*     */     {
/* 377 */       localIOException.printStackTrace();
/* 378 */       Contract.unreachable();
/*     */     }
/*     */ 
/* 381 */     Contract.postNonNull(localProperties);
/* 382 */     return localProperties;
/*     */   }
/*     */ 
/*     */   private Properties getImageIndex()
/*     */   {
/* 393 */     Properties localProperties = new Properties();
/* 394 */     InputStream localInputStream = getClass().getResourceAsStream("/config/images.properties");
/* 395 */     if (localInputStream == null)
/*     */     {
/* 398 */       Contract.unreachable();
/*     */     }
/*     */     try
/*     */     {
/* 402 */       localProperties.load(localInputStream);
/*     */     }
/*     */     catch (IOException localIOException)
/*     */     {
/* 406 */       localIOException.printStackTrace();
/* 407 */       Contract.unreachable();
/*     */     }
/*     */ 
/* 410 */     Contract.postNonNull(localProperties);
/* 411 */     return localProperties;
/*     */   }
/*     */ 
/*     */   private boolean wasAcceptedByUser()
/*     */   {
/* 420 */     int i = 1;
/*     */     try
/*     */     {
/* 424 */       System.getProperty("java.home");
/*     */     } catch (AccessControlException localAccessControlException) {
/* 426 */       i = 0;
/*     */     }
/* 428 */     return i;
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.DTWApplet
 * JD-Core Version:    0.6.0
 */