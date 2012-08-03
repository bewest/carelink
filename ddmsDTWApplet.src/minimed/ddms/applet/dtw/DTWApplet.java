/*     */ package minimed.ddms.applet.dtw;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.Font;
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
/*     */ import minimed.ddms.applet.dtw.wizard.WizardConfig.DriverConfig;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardSelections;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardStepProvider;
/*     */ import minimed.util.Contract;
/*     */ import minimed.util.OSInfo;
/*     */ import netscape.javascript.JSObject;
/*     */ 
/*     */ public final class DTWApplet extends JApplet
/*     */ {
/*     */   private static final String DDMS_DTW_SERIAL_DIR = "ddmsDTWserial";
/*     */   private static final String MEDTRONIC_DIR = "Medtronic";
/*  44 */   private static final String APPLICATION_DATA_DIR = OSInfo.getUserData();
/*     */   private static final String BD_DIR = "bd";
/*     */   private static final String DDMS_DTW_USB_DIR = "ddmsDTWusb";
/*     */   private static final String COM_LINK2_DIR = "ComLink2";
/*     */   private static final String BAYER_DIR = "Bayer";
/*     */   private static final String WIN64_DIR = "Win64";
/*     */   private static final String WIN32_DIR = "Win32";
/*     */   private static final String MAC_DIR = "Mac";
/*     */   private static ResourceBundle resourceBundle;
/*     */   private static final String CL2_USB_DRIVER_VERSION_WINDOWS = "Jungo 10.10";
/*     */   private static final String CL2_USB_DRIVER_VERSION_MAC = "ArtAndLogic 1.0";
/*     */   private static final String BAYER_USB_DRIVER_VERSION = "1.1";
/*  73 */   private final String allUserHome = OSInfo.getAllUserPath();
/*  74 */   private final String allUserHomeDir = this.allUserHome + File.separator + APPLICATION_DATA_DIR + File.separator + "Medtronic";
/*     */   private Wizard m_wizard;
/*     */ 
/*     */   public static ResourceBundle getResourceBundle()
/*     */   {
/*  88 */     if (resourceBundle == null)
/*     */     {
/*  91 */       setResourceBundle(Locale.US);
/*     */     }
/*  93 */     return resourceBundle;
/*     */   }
/*     */ 
/*     */   private static void setResourceBundle(Locale paramLocale)
/*     */   {
/* 101 */     resourceBundle = ResourceBundle.getBundle("minimed.ddms.applet.dtw.AppletResources", paramLocale);
/*     */   }
/*     */ 
/*     */   private static Locale getLocale(String paramString)
/*     */   {
/* 113 */     Contract.preNonNull(paramString);
/*     */     try {
/* 115 */       StringTokenizer localStringTokenizer = new StringTokenizer(paramString, "_");
/* 116 */       String str1 = localStringTokenizer.nextToken();
/* 117 */       String str2 = localStringTokenizer.nextToken();
/* 118 */       return new Locale(str1, str2);
/*     */     } catch (NoSuchElementException localNoSuchElementException) {
/* 120 */       Contract.unreachable();
/*     */     } catch (MissingResourceException localMissingResourceException) {
/* 122 */       Contract.unreachable();
/*     */     }
/* 124 */     return null;
/*     */   }
/*     */ 
/*     */   public void init()
/*     */   {
/* 131 */     this.m_wizard = null;
/*     */ 
/* 133 */     if (!wasAcceptedByUser()) {
/* 134 */       getContentPane().setLayout(new BorderLayout());
/* 135 */       localObject = new JLabel("Signed applet was not accepted - permission denied.");
/* 136 */       ((JLabel)localObject).setHorizontalAlignment(0);
/* 137 */       ((JLabel)localObject).setIcon(UIManager.getIcon("OptionPane.errorIcon"));
/* 138 */       getContentPane().add((Component)localObject, "Center");
/* 139 */       return; } 
/*     */ Object localObject = getSystemVariables();
/* 144 */     Properties localProperties = getImageIndex();
/*     */ 
/* 147 */     Locale localLocale = getLocale(getParameter("locale"));
/* 148 */     setResourceBundle(localLocale);
/*     */ 
/* 150 */     setLocale(localLocale);
/*     */ 
/* 152 */     JComponent.setDefaultLocale(localLocale);
/*     */ 
/* 155 */     String str1 = getCodeBase() + ((Properties)localObject).getProperty("DTWServletPath");
/*     */     String str2;
/*     */     try { str2 = new NetHelper().getControlCode(str1);
/*     */     }
/*     */     catch (IOException localIOException)
/*     */     {
/* 163 */       str2 = "";
/* 164 */       localIOException.printStackTrace(System.err);
/*     */     }
/* 166 */     ControlCodeParser localControlCodeParser = new ControlCodeParser(str2);
/* 167 */     LogWriter localLogWriter = new LogWriter(localControlCodeParser.getLogToConsole(), localControlCodeParser.getConsoleEncryptionKey());
/*     */ 
/* 170 */     localLogWriter.logInfo("DTWApplet init");
/* 171 */     localLogWriter.logInfo("System variables are: " + localObject);
/*     */ 
/* 174 */     WizardConfig localWizardConfig = createWizardConfig((Properties)localObject, localProperties, localControlCodeParser, str1, localLogWriter);
/*     */ 
/* 177 */     this.m_wizard = new Wizard(localWizardConfig);
/*     */ 
/* 180 */     localLogWriter.logInfo(this.m_wizard.toString());
/*     */ 
/* 186 */     Font localFont = UIManager.getFont("Label.font");
/* 187 */     if ((localFont != null) && (localFont.getSize() > 12)) {
/* 188 */       UIManager.put("Label.font", new Font(localFont.getFamily(), localFont.getStyle(), 12));
/*     */     }
/*     */ 
/* 192 */     this.m_wizard.start();
/*     */   }
/*     */ 
/*     */   public void destroy()
/*     */   {
/* 199 */     if (this.m_wizard != null)
/* 200 */       this.m_wizard.stop();
/*     */   }
/*     */ 
/*     */   public void showHelp(String paramString)
/*     */   {
/* 213 */     String str = "removeClassFromId";
/* 214 */     Object[] arrayOfObject = { paramString, "notselected" };
/* 215 */     JSObject localJSObject = JSObject.getWindow(this);
/* 216 */     localJSObject.call(str, arrayOfObject);
/*     */   }
/*     */ 
/*     */   public void enableUnloadMessage(boolean paramBoolean)
/*     */   {
/* 228 */     String str = "deviceUpload_enableUnloadMessage";
/* 229 */     Object[] arrayOfObject = { Boolean.valueOf(paramBoolean) };
/* 230 */     JSObject localJSObject = JSObject.getWindow(this);
/* 231 */     localJSObject.call(str, arrayOfObject);
/*     */ 
/* 234 */     str = "deviceUpload_isUnloadMessageEnabled";
/* 235 */     arrayOfObject = null;
/* 236 */     boolean bool = ((Boolean)localJSObject.call(str, arrayOfObject)).booleanValue();
/*     */ 
/* 238 */     Contract.post(paramBoolean == bool);
/*     */   }
/*     */ 
/*     */   private WizardConfig createWizardConfig(Properties paramProperties1, Properties paramProperties2, ControlCodeParser paramControlCodeParser, String paramString, LogWriter paramLogWriter)
/*     */   {
/* 264 */     Contract.preNonNull(paramProperties1);
/* 265 */     Contract.preNonNull(paramProperties2);
/* 266 */     Contract.preNonNull(paramControlCodeParser);
/* 267 */     Contract.preNonNull(paramString);
/* 268 */     Contract.preNonNull(paramLogWriter);
/*     */ 
/* 271 */     paramLogWriter.logInfo("User Home directory=" + this.allUserHomeDir);
/*     */ 
/* 276 */     String str1 = paramProperties1.getProperty("DTWSerialPortDriverVersion");
/*     */ 
/* 278 */     String str2 = this.allUserHomeDir + File.separator + "ddmsDTWserial" + File.separator + str1;
/*     */ 
/* 282 */     String str3 = this.allUserHomeDir + File.separator + "ddmsDTWusb" + File.separator + "bd";
/*     */ 
/* 288 */     String str4 = this.allUserHomeDir + File.separator + "ddmsDTWusb" + File.separator + "ComLink2" + File.separator + getCL2USBDriverVersion();
/*     */ 
/* 292 */     String str5 = this.allUserHomeDir + File.separator + "ddmsDTWusb" + File.separator + "Bayer" + File.separator + getBayerUSBDriverVersion();
/*     */ 
/* 302 */     String str6 = getParameter("lastPumpUniqueID");
/* 303 */     String str7 = getParameter("lastMeterUniqueID");
/* 304 */     String str8 = getParameter("lastCGMUniqueID");
/* 305 */     WizardStepProvider localWizardStepProvider = null;
/*     */     try
/*     */     {
/* 308 */       Class localClass = Class.forName(paramProperties1.getProperty("DTWWizardStepProvider"));
/* 309 */       localWizardStepProvider = (WizardStepProvider)localClass.newInstance();
/*     */     }
/*     */     catch (ClassNotFoundException localClassNotFoundException) {
/* 312 */       Contract.unreachable();
/*     */     }
/*     */     catch (InstantiationException localInstantiationException) {
/* 315 */       Contract.unreachable();
/*     */     }
/*     */     catch (IllegalAccessException localIllegalAccessException) {
/* 318 */       Contract.unreachable();
/*     */     }
/*     */     WizardConfig.DriverConfig localDriverConfig1;
/* 322 */     if (OSInfo.isMac()) {
/* 323 */       localDriverConfig1 = new WizardConfig.DriverConfig(str2, "/drivers/serial/", new String[] { "ddmsDTWSerialPort.jnilib", "ddmsDTWSerialPort-64.jnilib", "Install_serialio.sh" }, new String[] { "/bin/ksh", str2 + File.separator + "Install_serialio.sh" }, "install-success.txt");
/*     */     }
/*     */     else
/*     */     {
/* 336 */       localDriverConfig1 = new WizardConfig.DriverConfig(str2, "/drivers/serial/", new String[] { "ddmsDTWSerialPort.dll", "ddmsDTWSerialPort-64.dll", "Install_serialio.cmd" }, new String[] { str2 + File.separator + "Install_serialio.cmd" }, "install-success.txt");
/*     */     }
/*     */ 
/* 350 */     WizardConfig.DriverConfig localDriverConfig2 = new WizardConfig.DriverConfig(str3, "/drivers/usb/bd/", new String[] { "WDMMDMLD.VXD", "bdhidcom.inf", "BdHidCom.sys", "BDHidComInst.exe", "CCPORT.SYS", "bdhidcom.cat" }, new String[] { str3 + File.separator + "BDHidComInst.exe" }, "BDHidComInst.exe");
/*     */     WizardConfig.DriverConfig localDriverConfig3;
/* 369 */     if (OSInfo.isMac()) {
/* 370 */       localDriverConfig3 = new WizardConfig.DriverConfig(str4, "/drivers/usb/ComLink2/", new String[] { "Mac/libCareLinkUSB.dylib", "Install_MDTCLUSB.sh" }, new String[] { "/bin/ksh", str4 + File.separator + "Install_MDTCLUSB.sh" }, "install-success.txt");
/*     */     }
/*     */     else
/*     */     {
/* 382 */       localDriverConfig3 = new WizardConfig.DriverConfig(str4, "/drivers/usb/ComLink2/", new String[] { "Install_MDTCLUSB.cmd", "Install_MDTCLUSB.cmd", "Install_MDTCLUSB-32.cmd", "Install_MDTCLUSB-64.cmd", "Win32/cl2_jni_wrapper.dll", "Win32/difxapi.dll", "Win32/mdtclusb.inf", "Win32/mdtclusb.cat", "Win32/wd1010.cat", "Win32/wdapi1010.dll", "Win32/wdreg.exe", "Win32/wdreg_gui.exe", "Win32/windrvr6.inf", "Win32/windrvr6.pdb", "Win32/windrvr6.sys", "Win64/cl2_jni_wrapper.dll", "Win64/cl2_jni_wrapper_64.dll", "Win64/difxapi.dll", "Win64/mdtclusb.inf", "Win64/mdtclusb.cat", "Win64/wd1010.cat", "Win64/wdapi1010.dll", "Win64/wdapi1010_32.dll", "Win64/wdreg.exe", "Win64/wdreg_gui.exe", "Win64/windrvr6.inf", "Win64/windrvr6.pdb", "Win64/windrvr6.sys" }, new String[] { str4 + File.separator + "Install_MDTCLUSB.cmd" }, "install-success.txt");
/*     */     }
/*     */ 
/* 423 */     WizardConfig.DriverConfig localDriverConfig4 = null;
/*     */ 
/* 425 */     if (OSInfo.isMac()) {
/* 426 */       localDriverConfig4 = new WizardConfig.DriverConfig(str5, "/drivers/usb/Bayer/", new String[] { "Mac/BayerHID00.dll", "Mac/bci_jni_wrapper.dll", "Install_Bayer.sh" }, new String[] { "/bin/ksh", str5 + File.separator + "Install_Bayer.sh" }, "install-success.txt");
/*     */     }
/*     */     else
/*     */     {
/* 439 */       localDriverConfig4 = new WizardConfig.DriverConfig(str5, "/drivers/usb/Bayer/", new String[] { "Install_Bayer.cmd", "Win32/BayerHID00.dll", "Win32/bci_jni_wrapper.dll", "Win64/BayerHID00_64.dll", "Win64/bci_jni_wrapper_64.dll" }, new String[] { str5 + File.separator + "Install_Bayer.cmd" }, "install-success.txt");
/*     */     }
/*     */ 
/* 457 */     String str9 = OSInfo.getOSName();
/* 458 */     WizardConfig localWizardConfig = new WizardConfig(getCodeBase().getProtocol(), paramProperties1.getProperty("systemVersion"), getParameter("systemVersion"), getParameter("userName"), localDriverConfig1, localDriverConfig2, localDriverConfig3, localDriverConfig4, paramString, paramString, paramControlCodeParser.getLogToServer(), paramProperties1.getProperty("DTWNoLogMessage"), paramControlCodeParser.getKeepSnapshotOnServer(), paramControlCodeParser.getTraceUpload(), paramControlCodeParser.getDevicePortReaderMessageLevel(), paramControlCodeParser.getLastPumpHistoryPageReadOverride(), paramControlCodeParser.getLastGlucoseHistoryPageReadOverride(), paramLogWriter, getContentPane(), paramProperties2, new HashMap(), OSInfo.isWindowsVista(str9), OSInfo.isWindowsNT(str9), OSInfo.isWindows98(str9), TimeZone.getDefault().getID(), (str6 != null) && (str6.length() > 0) ? str6 : null, (str7 != null) && (str7.length() > 0) ? str7 : null, (str8 != null) && (str8.length() > 0) ? str8 : null, new WizardSelections(paramLogWriter), localWizardStepProvider, !"false".equals(getParameter("uploadParadigmLinkMeterOnly")), "true".equals(getParameter("uploadCGM")), "true".equals(getParameter("uploadG3B")), false, paramControlCodeParser.isHotCornerMode(), this);
/*     */ 
/* 503 */     Contract.postNonNull(localWizardConfig);
/* 504 */     return localWizardConfig;
/*     */   }
/*     */ 
/*     */   private String getCL2USBDriverVersion()
/*     */   {
/* 513 */     return OSInfo.isMac() ? "ArtAndLogic 1.0" : "Jungo 10.10";
/*     */   }
/*     */ 
/*     */   private String getBayerUSBDriverVersion()
/*     */   {
/* 522 */     return "1.1";
/*     */   }
/*     */ 
/*     */   private Properties getSystemVariables()
/*     */   {
/* 533 */     Properties localProperties = new Properties();
/* 534 */     InputStream localInputStream = getClass().getResourceAsStream("/config/SystemVariables.properties");
/* 535 */     if (localInputStream == null)
/*     */     {
/* 538 */       Contract.unreachable();
/*     */     }
/*     */     try
/*     */     {
/* 542 */       localProperties.load(localInputStream);
/*     */     }
/*     */     catch (IOException localIOException)
/*     */     {
/* 546 */       localIOException.printStackTrace();
/* 547 */       Contract.unreachable();
/*     */     }
/*     */ 
/* 550 */     Contract.postNonNull(localProperties);
/* 551 */     return localProperties;
/*     */   }
/*     */ 
/*     */   private Properties getImageIndex()
/*     */   {
/* 562 */     Properties localProperties = new Properties();
/* 563 */     InputStream localInputStream = getClass().getResourceAsStream("/config/images.properties");
/* 564 */     if (localInputStream == null)
/*     */     {
/* 567 */       Contract.unreachable();
/*     */     }
/*     */     try
/*     */     {
/* 571 */       localProperties.load(localInputStream);
/*     */     }
/*     */     catch (IOException localIOException)
/*     */     {
/* 575 */       localIOException.printStackTrace();
/* 576 */       Contract.unreachable();
/*     */     }
/*     */ 
/* 579 */     Contract.postNonNull(localProperties);
/* 580 */     return localProperties;
/*     */   }
/*     */ 
/*     */   private boolean wasAcceptedByUser()
/*     */   {
/* 589 */     int i = 1;
/*     */     try
/*     */     {
/* 593 */       System.getProperty("java.home");
/*     */     } catch (AccessControlException localAccessControlException) {
/* 595 */       i = 0;
/*     */     }
/* 597 */     return i;
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.DTWApplet
 * JD-Core Version:    0.6.0
 */