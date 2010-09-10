/*      */ package minimed.ddms.applet.dtw.wizard;
/*      */ 
/*      */ import java.awt.Color;
/*      */ import java.awt.Container;
/*      */ import java.awt.Font;
/*      */ import java.io.InputStream;
/*      */ import java.net.URL;
/*      */ import java.util.HashMap;
/*      */ import java.util.MissingResourceException;
/*      */ import java.util.Properties;
/*      */ import minimed.ddms.applet.dtw.DTWApplet;
/*      */ import minimed.ddms.applet.dtw.DeviceID;
/*      */ import minimed.ddms.applet.dtw.LogWriter;
/*      */ import minimed.ddms.applet.dtw.PropertyWriter;
/*      */ import minimed.util.Contract;
/*      */ 
/*      */ public final class WizardConfig
/*      */ {
/*      */   private static final int COLOR_VAL_238 = 238;
/*      */   private static final int COLOR_VAL_206 = 206;
/*      */   private static final int COLOR_VAL_198 = 198;
/*      */   private static final int COLOR_VAL_239 = 239;
/*      */   private static final int COLOR_VAL_153 = 153;
/*   63 */   private static final Color FOREGROUND_COLOR = Color.BLACK;
/*      */ 
/*   68 */   private static final Color BACKGROUND_COLOR = new Color(238, 238, 238);
/*      */ 
/*   74 */   private static final Color BANNER_AREA_BACKGROUND_COLOR = new Color(206, 198, 239);
/*      */ 
/*   80 */   private static final Color BORDER_COLOR = new Color(153, 153, 153);
/*      */   private static final String BANNER_AREA_FONT_NAME = "Verdana";
/*      */   private static final int BANNER_AREA_FONT_SIZE = 17;
/*   98 */   private static final Font BANNER_AREA_FONT = new Font("Verdana", 1, 17);
/*      */   private final String m_protocol;
/*      */   private final String m_clientSysVer;
/*      */   private final String m_serverSysVer;
/*      */   private final String m_userName;
/*      */   private final String m_installSerialPortDLLDir;
/*      */   private final String m_installSerialPortDLL;
/*      */   private final String m_installSerialPortDLLDigest;
/*      */   private final InputStream m_installSerialPortDLLInputStream;
/*      */   private final String m_installMessageDigestAlgorithm;
/*      */   private final String m_transferRemoteURL;
/*      */   private final String m_unrecoverableErrorRemoteURL;
/*      */   private final boolean m_logToServer;
/*      */   private final String m_noLogMessage;
/*      */   private final boolean m_keepSnapshotOnServer;
/*      */   private final boolean m_traceUpload;
/*      */   private final int m_devicePortReaderMessageLevel;
/*      */   private final Long m_lastPumpHistoryPageReadOverride;
/*      */   private final Long m_lastGlucoseHistoryPageReadOverride;
/*      */   private final LogWriter m_logWriter;
/*      */   private final Container m_contentPane;
/*      */   private final Properties m_imageIndex;
/*      */   private final HashMap m_images;
/*      */   private final boolean m_isWindowsVista;
/*      */   private final boolean m_isWindowsNT;
/*      */   private final boolean m_isWindows98;
/*      */   private final String m_clientTimeZoneID;
/*      */   private final String m_lastPumpUniqueID;
/*      */   private final String m_lastMeterUniqueID;
/*      */   private final String m_lastCGMUniqueID;
/*      */   private final WizardSelections m_wizardSelections;
/*      */   private final WizardStepProvider m_wizardStepProvider;
/*      */   private final boolean m_uploadX15PumpOnly;
/*      */   private final boolean m_uploadParadigmLinkMeterOnly;
/*      */   private final boolean m_uploadCGM;
/*      */   private final boolean m_uploadG3B;
/*      */   private final DTWApplet m_applet;
/*      */   private final USBConfig m_bdUSBConfig;
/*      */   private final USBConfig m_comlink2Config;
/*      */ 
/*      */   public WizardConfig(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, InputStream paramInputStream, String paramString8, USBConfig paramUSBConfig1, USBConfig paramUSBConfig2, String paramString9, String paramString10, boolean paramBoolean1, String paramString11, boolean paramBoolean2, boolean paramBoolean3, int paramInt, Long paramLong1, Long paramLong2, LogWriter paramLogWriter, Container paramContainer, Properties paramProperties, HashMap paramHashMap, boolean paramBoolean4, boolean paramBoolean5, boolean paramBoolean6, String paramString12, String paramString13, String paramString14, String paramString15, WizardSelections paramWizardSelections, WizardStepProvider paramWizardStepProvider, boolean paramBoolean7, boolean paramBoolean8, boolean paramBoolean9, boolean paramBoolean10, DTWApplet paramDTWApplet)
/*      */   {
/*  416 */     Contract.preString(paramString1);
/*  417 */     Contract.preString(paramString2);
/*  418 */     Contract.preString(paramString3);
/*  419 */     Contract.preString(paramString4);
/*  420 */     Contract.preString(paramString5);
/*  421 */     Contract.preString(paramString6);
/*  422 */     Contract.preString(paramString7);
/*  423 */     Contract.preNonNull(paramInputStream);
/*  424 */     Contract.preString(paramString8);
/*  425 */     Contract.preNonNull(paramUSBConfig1);
/*  426 */     Contract.preNonNull(paramUSBConfig2);
/*  427 */     Contract.preString(paramString9);
/*  428 */     Contract.preString(paramString10);
/*  429 */     Contract.preString(paramString11);
/*  430 */     Contract.pre(paramInt, 0, 3);
/*      */ 
/*  432 */     Contract.pre((paramLong1 == null) || (paramLong1.longValue() >= 0L));
/*      */ 
/*  434 */     Contract.pre((paramLong2 == null) || (paramLong2.longValue() >= 0L));
/*      */ 
/*  436 */     Contract.preNonNull(paramLogWriter);
/*  437 */     Contract.preNonNull(paramContainer);
/*  438 */     Contract.preNonNull(paramProperties);
/*  439 */     Contract.preNonNull(paramHashMap);
/*  440 */     Contract.pre((!paramBoolean4) || (!paramBoolean5) || (!paramBoolean6));
/*  441 */     Contract.preString(paramString12);
/*  442 */     Contract.preNonNull(paramWizardSelections);
/*  443 */     Contract.preNonNull(paramWizardStepProvider);
/*  444 */     Contract.preNonNull(paramDTWApplet);
/*      */ 
/*  447 */     this.m_protocol = paramString1;
/*  448 */     this.m_clientSysVer = paramString2;
/*  449 */     this.m_serverSysVer = paramString3;
/*  450 */     this.m_userName = paramString4;
/*  451 */     this.m_installSerialPortDLLDir = paramString5;
/*  452 */     this.m_installSerialPortDLL = paramString6;
/*  453 */     this.m_installSerialPortDLLDigest = paramString7;
/*  454 */     this.m_installSerialPortDLLInputStream = paramInputStream;
/*  455 */     this.m_installMessageDigestAlgorithm = paramString8;
/*  456 */     this.m_bdUSBConfig = paramUSBConfig1;
/*  457 */     this.m_comlink2Config = paramUSBConfig2;
/*  458 */     this.m_transferRemoteURL = paramString9;
/*  459 */     this.m_unrecoverableErrorRemoteURL = paramString10;
/*  460 */     this.m_logToServer = paramBoolean1;
/*  461 */     this.m_noLogMessage = paramString11;
/*  462 */     this.m_keepSnapshotOnServer = paramBoolean2;
/*  463 */     this.m_traceUpload = paramBoolean3;
/*  464 */     this.m_devicePortReaderMessageLevel = paramInt;
/*  465 */     this.m_lastPumpHistoryPageReadOverride = paramLong1;
/*  466 */     this.m_lastGlucoseHistoryPageReadOverride = paramLong2;
/*  467 */     this.m_logWriter = paramLogWriter;
/*  468 */     this.m_contentPane = paramContainer;
/*  469 */     this.m_imageIndex = paramProperties;
/*  470 */     this.m_images = paramHashMap;
/*  471 */     this.m_isWindowsVista = paramBoolean4;
/*  472 */     this.m_isWindowsNT = paramBoolean5;
/*  473 */     this.m_isWindows98 = paramBoolean6;
/*  474 */     this.m_clientTimeZoneID = paramString12;
/*  475 */     this.m_lastPumpUniqueID = paramString13;
/*  476 */     this.m_lastMeterUniqueID = paramString14;
/*  477 */     this.m_lastCGMUniqueID = paramString15;
/*  478 */     this.m_wizardSelections = paramWizardSelections;
/*      */ 
/*  481 */     if (paramString13 != null) {
/*  482 */       this.m_wizardSelections.setCurrentPumpID(new DeviceID(paramString13));
/*      */     }
/*      */     else {
/*  485 */       this.m_wizardSelections.setCurrentPumpID(null);
/*      */     }
/*      */ 
/*  489 */     if (paramString14 != null) {
/*  490 */       this.m_wizardSelections.setCurrentMeterID(new DeviceID(paramString14));
/*      */     }
/*      */     else {
/*  493 */       this.m_wizardSelections.setCurrentMeterID(null);
/*      */     }
/*      */ 
/*  497 */     if (paramString15 != null) {
/*  498 */       this.m_wizardSelections.setCurrentCGMID(new DeviceID(paramString15));
/*      */     }
/*      */     else {
/*  501 */       this.m_wizardSelections.setCurrentCGMID(null);
/*      */     }
/*      */ 
/*  504 */     this.m_wizardStepProvider = paramWizardStepProvider;
/*  505 */     this.m_uploadX15PumpOnly = paramBoolean7;
/*  506 */     this.m_uploadParadigmLinkMeterOnly = paramBoolean8;
/*  507 */     this.m_uploadCGM = paramBoolean9;
/*  508 */     this.m_uploadG3B = paramBoolean10;
/*  509 */     this.m_applet = paramDTWApplet;
/*      */   }
/*      */ 
/*      */   public String toString()
/*      */   {
/*  519 */     PropertyWriter localPropertyWriter = new PropertyWriter();
/*      */ 
/*  521 */     localPropertyWriter.add("protocol", this.m_protocol);
/*  522 */     localPropertyWriter.add("clientSysVer", this.m_clientSysVer);
/*  523 */     localPropertyWriter.add("serverSysVer", this.m_serverSysVer);
/*  524 */     localPropertyWriter.add("userName", this.m_userName);
/*  525 */     localPropertyWriter.add("installSerialPortDLLDir", this.m_installSerialPortDLLDir);
/*  526 */     localPropertyWriter.add("installSerialPortDLL", this.m_installSerialPortDLL);
/*  527 */     localPropertyWriter.add("installSerialPortDLLDigest", this.m_installSerialPortDLLDigest);
/*  528 */     localPropertyWriter.add("installMessageDigestAlgorithm", this.m_installMessageDigestAlgorithm);
/*  529 */     localPropertyWriter.add("bdUSBConfig", this.m_bdUSBConfig);
/*  530 */     localPropertyWriter.add("comlink2Config", this.m_comlink2Config);
/*  531 */     localPropertyWriter.add("transferRemoteURL", this.m_transferRemoteURL);
/*  532 */     localPropertyWriter.add("unrecoverableErrorRemoteURL", this.m_unrecoverableErrorRemoteURL);
/*  533 */     localPropertyWriter.add("logToServer", String.valueOf(this.m_logToServer));
/*  534 */     localPropertyWriter.add("noLogMessage", this.m_noLogMessage);
/*  535 */     localPropertyWriter.add("keepSnapshotOnServer", String.valueOf(this.m_keepSnapshotOnServer));
/*  536 */     localPropertyWriter.add("traceUpload", String.valueOf(this.m_traceUpload));
/*  537 */     localPropertyWriter.add("devicePortReaderMessageLevel", String.valueOf(this.m_devicePortReaderMessageLevel));
/*  538 */     localPropertyWriter.add("lastPumpHistoryPageReadOverride", String.valueOf(this.m_lastPumpHistoryPageReadOverride));
/*      */ 
/*  540 */     localPropertyWriter.add("lastGlucoseHistoryPageReadOverride", String.valueOf(this.m_lastGlucoseHistoryPageReadOverride));
/*      */ 
/*  542 */     localPropertyWriter.add("imageIndex", this.m_imageIndex.toString());
/*  543 */     localPropertyWriter.add("isWindowsVista", String.valueOf(this.m_isWindowsVista));
/*  544 */     localPropertyWriter.add("isWindowsNT", String.valueOf(this.m_isWindowsNT));
/*  545 */     localPropertyWriter.add("isWindows98", String.valueOf(this.m_isWindows98));
/*  546 */     localPropertyWriter.add("clientTimeZoneID", this.m_clientTimeZoneID);
/*  547 */     localPropertyWriter.add("lastPumpUniqueID", this.m_lastPumpUniqueID);
/*  548 */     localPropertyWriter.add("lastMeterUniqueID", this.m_lastMeterUniqueID);
/*  549 */     localPropertyWriter.add("lastCGMUniqueID", this.m_lastCGMUniqueID);
/*  550 */     localPropertyWriter.add("wizardSelections", this.m_wizardSelections);
/*  551 */     localPropertyWriter.add("uploadX15PumpOnly", this.m_uploadX15PumpOnly);
/*  552 */     localPropertyWriter.add("uploadParadigmLinkMeterOnly", this.m_uploadParadigmLinkMeterOnly);
/*  553 */     localPropertyWriter.add("uploadCGM", this.m_uploadCGM);
/*  554 */     localPropertyWriter.add("uploadG3B", this.m_uploadG3B);
/*      */ 
/*  557 */     return localPropertyWriter.toString();
/*      */   }
/*      */ 
/*      */   public boolean equals(Object paramObject)
/*      */   {
/*  568 */     Contract.unreachable();
/*  569 */     return false;
/*      */   }
/*      */ 
/*      */   public int hashCode()
/*      */   {
/*  579 */     Contract.unreachable();
/*  580 */     return 0;
/*      */   }
/*      */ 
/*      */   public String getProtocol()
/*      */   {
/*  590 */     String str = this.m_protocol;
/*  591 */     Contract.postString(str);
/*  592 */     return str;
/*      */   }
/*      */ 
/*      */   public String getClientSysVer()
/*      */   {
/*  602 */     String str = this.m_clientSysVer;
/*  603 */     Contract.postString(str);
/*  604 */     return str;
/*      */   }
/*      */ 
/*      */   public String getServerSysVer()
/*      */   {
/*  614 */     String str = this.m_serverSysVer;
/*  615 */     Contract.postString(str);
/*  616 */     return str;
/*      */   }
/*      */ 
/*      */   public String getUserName()
/*      */   {
/*  626 */     String str = this.m_userName;
/*  627 */     Contract.postString(str);
/*  628 */     return str;
/*      */   }
/*      */ 
/*      */   public String getInstallSerialPortDLLDir()
/*      */   {
/*  638 */     String str = this.m_installSerialPortDLLDir;
/*  639 */     Contract.postString(str);
/*  640 */     return str;
/*      */   }
/*      */ 
/*      */   public String getInstallSerialPortDLL()
/*      */   {
/*  650 */     String str = this.m_installSerialPortDLL;
/*  651 */     Contract.postString(str);
/*  652 */     return str;
/*      */   }
/*      */ 
/*      */   public String getInstallSerialPortDLLDigest()
/*      */   {
/*  662 */     String str = this.m_installSerialPortDLLDigest;
/*  663 */     Contract.postString(str);
/*  664 */     return str;
/*      */   }
/*      */ 
/*      */   public InputStream getSerialPortDLLInputStream()
/*      */   {
/*  674 */     InputStream localInputStream = this.m_installSerialPortDLLInputStream;
/*  675 */     Contract.postNonNull(localInputStream);
/*  676 */     return localInputStream;
/*      */   }
/*      */ 
/*      */   public String getInstallMessageDigestAlgorithm()
/*      */   {
/*  687 */     String str = this.m_installMessageDigestAlgorithm;
/*  688 */     Contract.postString(str);
/*  689 */     return str;
/*      */   }
/*      */ 
/*      */   public String getTransferRemoteURL()
/*      */   {
/*  699 */     String str = this.m_transferRemoteURL;
/*  700 */     Contract.postString(str);
/*  701 */     return str;
/*      */   }
/*      */ 
/*      */   public String getUnrecoverableErrorRemoteURL()
/*      */   {
/*  711 */     String str = this.m_unrecoverableErrorRemoteURL;
/*  712 */     Contract.postString(str);
/*  713 */     return str;
/*      */   }
/*      */ 
/*      */   public boolean getLogToServer()
/*      */   {
/*  722 */     boolean bool = this.m_logToServer;
/*  723 */     return bool;
/*      */   }
/*      */ 
/*      */   public String getNoLogMessage()
/*      */   {
/*  734 */     String str = this.m_noLogMessage;
/*  735 */     Contract.postString(str);
/*  736 */     return str;
/*      */   }
/*      */ 
/*      */   public boolean getKeepSnapshotOnServer()
/*      */   {
/*  745 */     boolean bool = this.m_keepSnapshotOnServer;
/*  746 */     return bool;
/*      */   }
/*      */ 
/*      */   public boolean getTraceUpload()
/*      */   {
/*  755 */     return this.m_traceUpload;
/*      */   }
/*      */ 
/*      */   public int getDevicePortReaderMessageLevel()
/*      */   {
/*  765 */     int i = this.m_devicePortReaderMessageLevel;
/*  766 */     Contract.post(i, 0, 3);
/*      */ 
/*  768 */     return i;
/*      */   }
/*      */ 
/*      */   public Long getLastPumpHistoryPageReadOverride()
/*      */   {
/*  778 */     Long localLong = this.m_lastPumpHistoryPageReadOverride;
/*  779 */     Contract.post((localLong == null) || (localLong.longValue() >= 0L));
/*  780 */     return localLong;
/*      */   }
/*      */ 
/*      */   public Long getLastGlucoseHistoryPageReadOverride()
/*      */   {
/*  790 */     Long localLong = this.m_lastGlucoseHistoryPageReadOverride;
/*  791 */     Contract.post((localLong == null) || (localLong.longValue() >= 0L));
/*  792 */     return localLong;
/*      */   }
/*      */ 
/*      */   public Container getContentPane()
/*      */   {
/*  802 */     Container localContainer = this.m_contentPane;
/*  803 */     Contract.postNonNull(localContainer);
/*  804 */     return localContainer;
/*      */   }
/*      */ 
/*      */   public LogWriter getLogWriter()
/*      */   {
/*  814 */     LogWriter localLogWriter = this.m_logWriter;
/*  815 */     Contract.postNonNull(localLogWriter);
/*  816 */     return localLogWriter;
/*      */   }
/*      */ 
/*      */   public Properties getImageIndex()
/*      */   {
/*  826 */     Properties localProperties = this.m_imageIndex;
/*  827 */     Contract.postNonNull(localProperties);
/*  828 */     return localProperties;
/*      */   }
/*      */ 
/*      */   public boolean isWindowsVista()
/*      */   {
/*  839 */     boolean bool = this.m_isWindowsVista;
/*  840 */     return bool;
/*      */   }
/*      */ 
/*      */   public boolean isWindowsNT()
/*      */   {
/*  849 */     boolean bool = this.m_isWindowsNT;
/*  850 */     return bool;
/*      */   }
/*      */ 
/*      */   public boolean isWindows98()
/*      */   {
/*  859 */     boolean bool = this.m_isWindows98;
/*  860 */     return bool;
/*      */   }
/*      */ 
/*      */   public String getClientTimeZoneID()
/*      */   {
/*  870 */     String str = this.m_clientTimeZoneID;
/*  871 */     Contract.postString(str);
/*  872 */     return str;
/*      */   }
/*      */ 
/*      */   public String getLastPumpUniqueID()
/*      */   {
/*  882 */     String str = this.m_lastPumpUniqueID;
/*  883 */     if (str != null) {
/*  884 */       Contract.postString(str);
/*      */     }
/*  886 */     return str;
/*      */   }
/*      */ 
/*      */   public String getLastMeterUniqueID()
/*      */   {
/*  896 */     String str = this.m_lastMeterUniqueID;
/*  897 */     if (str != null) {
/*  898 */       Contract.postString(str);
/*      */     }
/*  900 */     return str;
/*      */   }
/*      */ 
/*      */   public String getLastCGMUniqueID()
/*      */   {
/*  910 */     String str = this.m_lastCGMUniqueID;
/*  911 */     if (str != null) {
/*  912 */       Contract.postString(str);
/*      */     }
/*  914 */     return str;
/*      */   }
/*      */ 
/*      */   public URL getImage(String paramString)
/*      */     throws MissingResourceException
/*      */   {
/*  927 */     Contract.preString(paramString);
/*  928 */     URL localURL = (URL)this.m_images.get(paramString);
/*  929 */     if (localURL == null)
/*      */     {
/*  931 */       String str = this.m_imageIndex.getProperty(paramString);
/*  932 */       if (str == null) {
/*  933 */         throw new MissingResourceException("image property not found: " + paramString, "image", paramString);
/*      */       }
/*      */ 
/*  936 */       localURL = getClass().getResource(str);
/*  937 */       this.m_images.put(paramString, localURL);
/*      */     }
/*  939 */     if (localURL == null) {
/*  940 */       throw new MissingResourceException("image not found: " + paramString, "image", paramString);
/*      */     }
/*      */ 
/*  943 */     Contract.postNonNull(localURL);
/*  944 */     return localURL;
/*      */   }
/*      */ 
/*      */   public WizardSelections getWizardSelections()
/*      */   {
/*  954 */     WizardSelections localWizardSelections = this.m_wizardSelections;
/*  955 */     Contract.postNonNull(localWizardSelections);
/*  956 */     return localWizardSelections;
/*      */   }
/*      */ 
/*      */   WizardStepProvider getWizardStepProvider()
/*      */   {
/*  966 */     WizardStepProvider localWizardStepProvider = this.m_wizardStepProvider;
/*  967 */     Contract.postNonNull(localWizardStepProvider);
/*  968 */     return localWizardStepProvider;
/*      */   }
/*      */ 
/*      */   public boolean isUploadX15PumpOnly()
/*      */   {
/*  978 */     return this.m_uploadX15PumpOnly;
/*      */   }
/*      */ 
/*      */   public boolean isUploadParadigmLinkMeterOnly()
/*      */   {
/*  988 */     return this.m_uploadParadigmLinkMeterOnly;
/*      */   }
/*      */ 
/*      */   public boolean isUploadCGM()
/*      */   {
/*  997 */     return this.m_uploadCGM;
/*      */   }
/*      */ 
/*      */   public boolean isUploadG3B()
/*      */   {
/* 1006 */     return this.m_uploadG3B;
/*      */   }
/*      */ 
/*      */   public Color getForegroundColor()
/*      */   {
/* 1015 */     return FOREGROUND_COLOR;
/*      */   }
/*      */ 
/*      */   public Color getBackgroundColor()
/*      */   {
/* 1024 */     return BACKGROUND_COLOR;
/*      */   }
/*      */ 
/*      */   public Color getBannerAreaBackgroundColor()
/*      */   {
/* 1033 */     return BANNER_AREA_BACKGROUND_COLOR;
/*      */   }
/*      */ 
/*      */   public USBConfig getBDUSBConfig()
/*      */   {
/* 1042 */     return this.m_bdUSBConfig;
/*      */   }
/*      */ 
/*      */   public USBConfig getComLink2Config()
/*      */   {
/* 1051 */     return this.m_comlink2Config;
/*      */   }
/*      */ 
/*      */   public Color getBorderColor()
/*      */   {
/* 1060 */     return BORDER_COLOR;
/*      */   }
/*      */ 
/*      */   public Font getBannerAreaFont()
/*      */   {
/* 1069 */     return BANNER_AREA_FONT;
/*      */   }
/*      */ 
/*      */   public DTWApplet getApplet()
/*      */   {
/* 1078 */     return this.m_applet;
/*      */   }
/*      */ 
/*      */   public static class USBConfig
/*      */   {
/*      */     private final String m_installUSBDriverDir;
/*      */     private final String m_secondaryInstallUSBDriverDir;
/*      */     private final String m_installUSBDriverJarLoc;
/*      */     private final String[] m_installUSBDriverFiles;
/*      */     private final String[] m_installUSBDriverCommand;
/*      */     private final String m_installUSBDriverSuccessIndicatorFile;
/*      */ 
/*      */     public USBConfig(String paramString1, String paramString2, String paramString3, String[] paramArrayOfString1, String[] paramArrayOfString2, String paramString4)
/*      */     {
/* 1146 */       Contract.preString(paramString1);
/* 1147 */       Contract.preString(paramString2);
/* 1148 */       Contract.preString(paramString3);
/* 1149 */       Contract.preNonNull(paramArrayOfString1);
/* 1150 */       Contract.pre(paramArrayOfString1.length > 0);
/* 1151 */       Contract.preNonNull(paramArrayOfString2);
/* 1152 */       Contract.pre(paramArrayOfString2.length > 0);
/* 1153 */       Contract.preString(paramString4);
/* 1154 */       this.m_installUSBDriverDir = paramString1;
/* 1155 */       this.m_secondaryInstallUSBDriverDir = paramString2;
/* 1156 */       this.m_installUSBDriverJarLoc = paramString3;
/* 1157 */       this.m_installUSBDriverFiles = paramArrayOfString1;
/* 1158 */       this.m_installUSBDriverCommand = paramArrayOfString2;
/* 1159 */       this.m_installUSBDriverSuccessIndicatorFile = paramString4;
/*      */     }
/*      */ 
/*      */     public USBConfig(String paramString1, String paramString2, String[] paramArrayOfString1, String[] paramArrayOfString2, String paramString3)
/*      */     {
/* 1184 */       this(paramString1, paramString1, paramString2, paramArrayOfString1, paramArrayOfString2, paramString3);
/*      */     }
/*      */ 
/*      */     public String toString()
/*      */     {
/* 1199 */       PropertyWriter localPropertyWriter = new PropertyWriter();
/* 1200 */       localPropertyWriter.add("installUSBDriverDir", this.m_installUSBDriverDir);
/* 1201 */       localPropertyWriter.add("secondaryInstallUSBDriverDir", this.m_secondaryInstallUSBDriverDir);
/* 1202 */       localPropertyWriter.add("installUSBDriverJarLoc", this.m_installUSBDriverJarLoc);
/*      */ 
/* 1204 */       for (int i = 0; i < this.m_installUSBDriverFiles.length; i++) {
/* 1205 */         localPropertyWriter.add("installUSBDriverFile-" + i, this.m_installUSBDriverFiles[i]);
/*      */       }
/*      */ 
/* 1208 */       for (i = 0; i < this.m_installUSBDriverCommand.length; i++) {
/* 1209 */         localPropertyWriter.add("installUSBDriverCommand-" + i, this.m_installUSBDriverCommand[i]);
/*      */       }
/*      */ 
/* 1212 */       localPropertyWriter.add("installUSBDriverSuccessIndicatorFile", this.m_installUSBDriverSuccessIndicatorFile);
/* 1213 */       return localPropertyWriter.toString();
/*      */     }
/*      */ 
/*      */     public String getInstallUSBDriverDir()
/*      */     {
/* 1223 */       String str = this.m_installUSBDriverDir;
/* 1224 */       Contract.postString(str);
/* 1225 */       return str;
/*      */     }
/*      */ 
/*      */     public String getSecondaryInstallUSBDriverDir()
/*      */     {
/* 1235 */       String str = this.m_secondaryInstallUSBDriverDir;
/* 1236 */       Contract.postString(str);
/* 1237 */       return str;
/*      */     }
/*      */ 
/*      */     public String[] getInstallUSBDriverFiles()
/*      */     {
/* 1247 */       String[] arrayOfString = this.m_installUSBDriverFiles;
/* 1248 */       Contract.postNonNull(arrayOfString);
/* 1249 */       Contract.post(arrayOfString.length > 0);
/* 1250 */       return arrayOfString;
/*      */     }
/*      */ 
/*      */     public String getInstallUSBDriverJarLoc()
/*      */     {
/* 1260 */       String str = this.m_installUSBDriverJarLoc;
/* 1261 */       Contract.postString(str);
/* 1262 */       return str;
/*      */     }
/*      */ 
/*      */     public String[] getInstallUSBDriverCommand()
/*      */     {
/* 1272 */       String[] arrayOfString = this.m_installUSBDriverCommand;
/* 1273 */       Contract.post((arrayOfString != null) && (arrayOfString.length > 0));
/* 1274 */       return arrayOfString;
/*      */     }
/*      */ 
/*      */     public String getInstallUSBDriverSuccessIndicatorFile()
/*      */     {
/* 1284 */       String str = this.m_installUSBDriverSuccessIndicatorFile;
/* 1285 */       Contract.postString(str);
/* 1286 */       return str;
/*      */     }
/*      */   }
/*      */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.WizardConfig
 * JD-Core Version:    0.6.0
 */