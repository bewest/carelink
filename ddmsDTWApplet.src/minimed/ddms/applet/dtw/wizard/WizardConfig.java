/*      */ package minimed.ddms.applet.dtw.wizard;
/*      */ 
/*      */ import java.awt.Color;
/*      */ import java.awt.Container;
/*      */ import java.awt.Font;
/*      */ import java.io.File;
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
/*      */   private final String m_transferRemoteURL;
/*      */   private final String m_unrecoverableErrorRemoteURL;
/*      */   private final boolean m_logToServer;
/*      */   private final String m_noLogMessage;
/*      */   private final boolean m_keepSnapshotOnServer;
/*      */   private final boolean m_traceUpload;
/*      */   private int m_devicePortReaderMessageLevel;
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
/*      */   private final boolean m_uploadParadigmLinkMeterOnly;
/*      */   private final boolean m_uploadCGM;
/*      */   private final boolean m_uploadG3B;
/*      */   private final DTWApplet m_applet;
/*      */   private final DriverConfig m_bdDriverConfig;
/*      */   private final DriverConfig m_comlink2Config;
/*      */   private final DriverConfig m_bayerUSBConfig;
/*      */   private final DriverConfig m_serialConfig;
/*      */   private final boolean m_hotCornerMode;
/*      */   private boolean m_loadSnapshotMode;
/*      */ 
/*      */   public WizardConfig(String paramString1, String paramString2, String paramString3, String paramString4, DriverConfig paramDriverConfig1, DriverConfig paramDriverConfig2, DriverConfig paramDriverConfig3, DriverConfig paramDriverConfig4, String paramString5, String paramString6, boolean paramBoolean1, String paramString7, boolean paramBoolean2, boolean paramBoolean3, int paramInt, Long paramLong1, Long paramLong2, LogWriter paramLogWriter, Container paramContainer, Properties paramProperties, HashMap paramHashMap, boolean paramBoolean4, boolean paramBoolean5, boolean paramBoolean6, String paramString8, String paramString9, String paramString10, String paramString11, WizardSelections paramWizardSelections, WizardStepProvider paramWizardStepProvider, boolean paramBoolean7, boolean paramBoolean8, boolean paramBoolean9, boolean paramBoolean10, boolean paramBoolean11, DTWApplet paramDTWApplet)
/*      */   {
/*  396 */     Contract.preString(paramString1);
/*  397 */     Contract.preString(paramString2);
/*  398 */     Contract.preString(paramString3);
/*  399 */     Contract.preString(paramString4);
/*  400 */     Contract.preNonNull(paramDriverConfig1);
/*  401 */     Contract.preNonNull(paramDriverConfig2);
/*  402 */     Contract.preNonNull(paramDriverConfig3);
/*  403 */     Contract.preNonNull(paramDriverConfig4);
/*  404 */     Contract.preString(paramString5);
/*  405 */     Contract.preString(paramString6);
/*  406 */     Contract.preString(paramString7);
/*  407 */     Contract.pre(paramInt, 0, 3);
/*      */ 
/*  409 */     Contract.pre((paramLong1 == null) || (paramLong1.longValue() >= 0L));
/*      */ 
/*  411 */     Contract.pre((paramLong2 == null) || (paramLong2.longValue() >= 0L));
/*      */ 
/*  413 */     Contract.preNonNull(paramLogWriter);
/*  414 */     Contract.preNonNull(paramContainer);
/*  415 */     Contract.preNonNull(paramProperties);
/*  416 */     Contract.preNonNull(paramHashMap);
/*  417 */     Contract.pre((!paramBoolean4) || (!paramBoolean5) || (!paramBoolean6));
/*  418 */     Contract.preString(paramString8);
/*  419 */     Contract.preNonNull(paramWizardSelections);
/*  420 */     Contract.preNonNull(paramWizardStepProvider);
/*  421 */     Contract.preNonNull(paramDTWApplet);
/*      */ 
/*  424 */     this.m_protocol = paramString1;
/*  425 */     this.m_clientSysVer = paramString2;
/*  426 */     this.m_serverSysVer = paramString3;
/*  427 */     this.m_userName = paramString4;
/*  428 */     this.m_serialConfig = paramDriverConfig1;
/*  429 */     this.m_bdDriverConfig = paramDriverConfig2;
/*  430 */     this.m_comlink2Config = paramDriverConfig3;
/*  431 */     this.m_bayerUSBConfig = paramDriverConfig4;
/*  432 */     this.m_transferRemoteURL = paramString5;
/*  433 */     this.m_unrecoverableErrorRemoteURL = paramString6;
/*  434 */     this.m_logToServer = paramBoolean1;
/*  435 */     this.m_noLogMessage = paramString7;
/*  436 */     this.m_keepSnapshotOnServer = paramBoolean2;
/*  437 */     this.m_traceUpload = paramBoolean3;
/*  438 */     this.m_devicePortReaderMessageLevel = paramInt;
/*  439 */     this.m_lastPumpHistoryPageReadOverride = paramLong1;
/*  440 */     this.m_lastGlucoseHistoryPageReadOverride = paramLong2;
/*  441 */     this.m_logWriter = paramLogWriter;
/*  442 */     this.m_contentPane = paramContainer;
/*  443 */     this.m_imageIndex = paramProperties;
/*  444 */     this.m_images = paramHashMap;
/*  445 */     this.m_isWindowsVista = paramBoolean4;
/*  446 */     this.m_isWindowsNT = paramBoolean5;
/*  447 */     this.m_isWindows98 = paramBoolean6;
/*  448 */     this.m_clientTimeZoneID = paramString8;
/*  449 */     this.m_lastPumpUniqueID = paramString9;
/*  450 */     this.m_lastMeterUniqueID = paramString10;
/*  451 */     this.m_lastCGMUniqueID = paramString11;
/*  452 */     this.m_wizardSelections = paramWizardSelections;
/*      */ 
/*  455 */     if (paramString9 != null) {
/*  456 */       this.m_wizardSelections.setCurrentPumpID(new DeviceID(paramString9));
/*      */     }
/*      */     else {
/*  459 */       this.m_wizardSelections.setCurrentPumpID(null);
/*      */     }
/*      */ 
/*  463 */     if (paramString10 != null) {
/*  464 */       this.m_wizardSelections.setCurrentMeterID(new DeviceID(paramString10));
/*      */     }
/*      */     else {
/*  467 */       this.m_wizardSelections.setCurrentMeterID(null);
/*      */     }
/*      */ 
/*  471 */     if (paramString11 != null) {
/*  472 */       this.m_wizardSelections.setCurrentCGMID(new DeviceID(paramString11));
/*      */     }
/*      */     else {
/*  475 */       this.m_wizardSelections.setCurrentCGMID(null);
/*      */     }
/*      */ 
/*  478 */     this.m_wizardStepProvider = paramWizardStepProvider;
/*  479 */     this.m_uploadParadigmLinkMeterOnly = paramBoolean7;
/*  480 */     this.m_uploadCGM = paramBoolean8;
/*  481 */     this.m_uploadG3B = paramBoolean9;
/*  482 */     this.m_loadSnapshotMode = paramBoolean10;
/*  483 */     this.m_hotCornerMode = paramBoolean11;
/*  484 */     this.m_applet = paramDTWApplet;
/*      */   }
/*      */ 
/*      */   public String toString()
/*      */   {
/*  494 */     PropertyWriter localPropertyWriter = new PropertyWriter();
/*      */ 
/*  496 */     localPropertyWriter.add("protocol", this.m_protocol);
/*  497 */     localPropertyWriter.add("clientSysVer", this.m_clientSysVer);
/*  498 */     localPropertyWriter.add("serverSysVer", this.m_serverSysVer);
/*  499 */     localPropertyWriter.add("userName", this.m_userName);
/*  500 */     localPropertyWriter.add("serialConfig", this.m_serialConfig);
/*  501 */     localPropertyWriter.add("bdDriverConfig", this.m_bdDriverConfig);
/*  502 */     localPropertyWriter.add("comlink2Config", this.m_comlink2Config);
/*  503 */     localPropertyWriter.add("bayerUSBConfig", this.m_bayerUSBConfig);
/*  504 */     localPropertyWriter.add("transferRemoteURL", this.m_transferRemoteURL);
/*  505 */     localPropertyWriter.add("unrecoverableErrorRemoteURL", this.m_unrecoverableErrorRemoteURL);
/*  506 */     localPropertyWriter.add("logToServer", String.valueOf(this.m_logToServer));
/*  507 */     localPropertyWriter.add("noLogMessage", this.m_noLogMessage);
/*  508 */     localPropertyWriter.add("keepSnapshotOnServer", String.valueOf(this.m_keepSnapshotOnServer));
/*  509 */     localPropertyWriter.add("traceUpload", String.valueOf(this.m_traceUpload));
/*  510 */     localPropertyWriter.add("devicePortReaderMessageLevel", String.valueOf(this.m_devicePortReaderMessageLevel));
/*  511 */     localPropertyWriter.add("lastPumpHistoryPageReadOverride", String.valueOf(this.m_lastPumpHistoryPageReadOverride));
/*      */ 
/*  513 */     localPropertyWriter.add("lastGlucoseHistoryPageReadOverride", String.valueOf(this.m_lastGlucoseHistoryPageReadOverride));
/*      */ 
/*  515 */     localPropertyWriter.add("imageIndex", this.m_imageIndex.toString());
/*  516 */     localPropertyWriter.add("isWindowsVista", String.valueOf(this.m_isWindowsVista));
/*  517 */     localPropertyWriter.add("isWindowsNT", String.valueOf(this.m_isWindowsNT));
/*  518 */     localPropertyWriter.add("isWindows98", String.valueOf(this.m_isWindows98));
/*  519 */     localPropertyWriter.add("clientTimeZoneID", this.m_clientTimeZoneID);
/*  520 */     localPropertyWriter.add("lastPumpUniqueID", this.m_lastPumpUniqueID);
/*  521 */     localPropertyWriter.add("lastMeterUniqueID", this.m_lastMeterUniqueID);
/*  522 */     localPropertyWriter.add("lastCGMUniqueID", this.m_lastCGMUniqueID);
/*  523 */     localPropertyWriter.add("wizardSelections", this.m_wizardSelections);
/*  524 */     localPropertyWriter.add("uploadParadigmLinkMeterOnly", this.m_uploadParadigmLinkMeterOnly);
/*  525 */     localPropertyWriter.add("uploadCGM", this.m_uploadCGM);
/*  526 */     localPropertyWriter.add("uploadG3B", this.m_uploadG3B);
/*      */ 
/*  529 */     return localPropertyWriter.toString();
/*      */   }
/*      */ 
/*      */   public boolean equals(Object paramObject)
/*      */   {
/*  535 */     Contract.unreachable();
/*  536 */     return false;
/*      */   }
/*      */ 
/*      */   public int hashCode()
/*      */   {
/*  541 */     Contract.unreachable();
/*  542 */     return 0;
/*      */   }
/*      */ 
/*      */   public boolean getLoadSnapshotMode()
/*      */   {
/*  549 */     return this.m_loadSnapshotMode;
/*      */   }
/*      */ 
/*      */   public void setLoadSnapshotMode(boolean paramBoolean)
/*      */   {
/*  557 */     this.m_loadSnapshotMode = paramBoolean;
/*      */   }
/*      */ 
/*      */   public String getProtocol()
/*      */   {
/*  567 */     String str = this.m_protocol;
/*  568 */     Contract.postString(str);
/*  569 */     return str;
/*      */   }
/*      */ 
/*      */   public String getClientSysVer()
/*      */   {
/*  579 */     String str = this.m_clientSysVer;
/*  580 */     Contract.postString(str);
/*  581 */     return str;
/*      */   }
/*      */ 
/*      */   public String getServerSysVer()
/*      */   {
/*  591 */     String str = this.m_serverSysVer;
/*  592 */     Contract.postString(str);
/*  593 */     return str;
/*      */   }
/*      */ 
/*      */   public String getUserName()
/*      */   {
/*  603 */     String str = this.m_userName;
/*  604 */     Contract.postString(str);
/*  605 */     return str;
/*      */   }
/*      */ 
/*      */   public String getTransferRemoteURL()
/*      */   {
/*  615 */     String str = this.m_transferRemoteURL;
/*  616 */     Contract.postString(str);
/*  617 */     return str;
/*      */   }
/*      */ 
/*      */   public boolean inHotCornerMode()
/*      */   {
/*  625 */     return this.m_hotCornerMode;
/*      */   }
/*      */ 
/*      */   public String getUnrecoverableErrorRemoteURL()
/*      */   {
/*  635 */     String str = this.m_unrecoverableErrorRemoteURL;
/*  636 */     Contract.postString(str);
/*  637 */     return str;
/*      */   }
/*      */ 
/*      */   public boolean getLogToServer()
/*      */   {
/*  646 */     boolean bool = this.m_logToServer;
/*  647 */     return bool;
/*      */   }
/*      */ 
/*      */   public String getNoLogMessage()
/*      */   {
/*  658 */     String str = this.m_noLogMessage;
/*  659 */     Contract.postString(str);
/*  660 */     return str;
/*      */   }
/*      */ 
/*      */   public boolean getKeepSnapshotOnServer()
/*      */   {
/*  669 */     boolean bool = this.m_keepSnapshotOnServer;
/*  670 */     return bool;
/*      */   }
/*      */ 
/*      */   public boolean getTraceUpload()
/*      */   {
/*  679 */     return this.m_traceUpload;
/*      */   }
/*      */ 
/*      */   public int getDevicePortReaderMessageLevel()
/*      */   {
/*  689 */     int i = this.m_devicePortReaderMessageLevel;
/*  690 */     Contract.post(i, 0, 3);
/*      */ 
/*  692 */     return i;
/*      */   }
/*      */ 
/*      */   public void setDevicePortReaderMessageLevel(int paramInt)
/*      */   {
/*  700 */     this.m_devicePortReaderMessageLevel = paramInt;
/*      */   }
/*      */ 
/*      */   public Long getLastPumpHistoryPageReadOverride()
/*      */   {
/*  710 */     Long localLong = this.m_lastPumpHistoryPageReadOverride;
/*  711 */     Contract.post((localLong == null) || (localLong.longValue() >= 0L));
/*  712 */     return localLong;
/*      */   }
/*      */ 
/*      */   public Long getLastGlucoseHistoryPageReadOverride()
/*      */   {
/*  722 */     Long localLong = this.m_lastGlucoseHistoryPageReadOverride;
/*  723 */     Contract.post((localLong == null) || (localLong.longValue() >= 0L));
/*  724 */     return localLong;
/*      */   }
/*      */ 
/*      */   public Container getContentPane()
/*      */   {
/*  734 */     Container localContainer = this.m_contentPane;
/*  735 */     Contract.postNonNull(localContainer);
/*  736 */     return localContainer;
/*      */   }
/*      */ 
/*      */   public LogWriter getLogWriter()
/*      */   {
/*  746 */     LogWriter localLogWriter = this.m_logWriter;
/*  747 */     Contract.postNonNull(localLogWriter);
/*  748 */     return localLogWriter;
/*      */   }
/*      */ 
/*      */   public Properties getImageIndex()
/*      */   {
/*  758 */     Properties localProperties = this.m_imageIndex;
/*  759 */     Contract.postNonNull(localProperties);
/*  760 */     return localProperties;
/*      */   }
/*      */ 
/*      */   public boolean isWindowsVista()
/*      */   {
/*  771 */     boolean bool = this.m_isWindowsVista;
/*  772 */     return bool;
/*      */   }
/*      */ 
/*      */   public boolean isWindowsNT()
/*      */   {
/*  781 */     boolean bool = this.m_isWindowsNT;
/*  782 */     return bool;
/*      */   }
/*      */ 
/*      */   public boolean isWindows98()
/*      */   {
/*  791 */     boolean bool = this.m_isWindows98;
/*  792 */     return bool;
/*      */   }
/*      */ 
/*      */   public String getClientTimeZoneID()
/*      */   {
/*  802 */     String str = this.m_clientTimeZoneID;
/*  803 */     Contract.postString(str);
/*  804 */     return str;
/*      */   }
/*      */ 
/*      */   public String getLastPumpUniqueID()
/*      */   {
/*  814 */     String str = this.m_lastPumpUniqueID;
/*  815 */     if (str != null) {
/*  816 */       Contract.postString(str);
/*      */     }
/*  818 */     return str;
/*      */   }
/*      */ 
/*      */   public String getLastMeterUniqueID()
/*      */   {
/*  828 */     String str = this.m_lastMeterUniqueID;
/*  829 */     if (str != null) {
/*  830 */       Contract.postString(str);
/*      */     }
/*  832 */     return str;
/*      */   }
/*      */ 
/*      */   public String getLastCGMUniqueID()
/*      */   {
/*  842 */     String str = this.m_lastCGMUniqueID;
/*  843 */     if (str != null) {
/*  844 */       Contract.postString(str);
/*      */     }
/*  846 */     return str;
/*      */   }
/*      */ 
/*      */   public URL getImage(String paramString)
/*      */     throws MissingResourceException
/*      */   {
/*  859 */     Contract.preString(paramString);
/*  860 */     URL localURL = (URL)this.m_images.get(paramString);
/*  861 */     if (localURL == null)
/*      */     {
/*  863 */       String str = this.m_imageIndex.getProperty(paramString);
/*  864 */       if (str == null) {
/*  865 */         throw new MissingResourceException("image property not found: " + paramString, "image", paramString);
/*      */       }
/*      */ 
/*  868 */       localURL = getClass().getResource(str);
/*  869 */       this.m_images.put(paramString, localURL);
/*      */     }
/*  871 */     if (localURL == null) {
/*  872 */       throw new MissingResourceException("image not found: " + paramString, "image", paramString);
/*      */     }
/*      */ 
/*  875 */     Contract.postNonNull(localURL);
/*  876 */     return localURL;
/*      */   }
/*      */ 
/*      */   public WizardSelections getWizardSelections()
/*      */   {
/*  886 */     WizardSelections localWizardSelections = this.m_wizardSelections;
/*  887 */     Contract.postNonNull(localWizardSelections);
/*  888 */     return localWizardSelections;
/*      */   }
/*      */ 
/*      */   WizardStepProvider getWizardStepProvider()
/*      */   {
/*  898 */     WizardStepProvider localWizardStepProvider = this.m_wizardStepProvider;
/*  899 */     Contract.postNonNull(localWizardStepProvider);
/*  900 */     return localWizardStepProvider;
/*      */   }
/*      */ 
/*      */   public boolean isUploadParadigmLinkMeterOnly()
/*      */   {
/*  910 */     return this.m_uploadParadigmLinkMeterOnly;
/*      */   }
/*      */ 
/*      */   public boolean isUploadCGM()
/*      */   {
/*  919 */     return this.m_uploadCGM;
/*      */   }
/*      */ 
/*      */   public boolean isUploadG3B()
/*      */   {
/*  928 */     return this.m_uploadG3B;
/*      */   }
/*      */ 
/*      */   public Color getForegroundColor()
/*      */   {
/*  937 */     return FOREGROUND_COLOR;
/*      */   }
/*      */ 
/*      */   public Color getBackgroundColor()
/*      */   {
/*  946 */     return BACKGROUND_COLOR;
/*      */   }
/*      */ 
/*      */   public Color getBannerAreaBackgroundColor()
/*      */   {
/*  955 */     return BANNER_AREA_BACKGROUND_COLOR;
/*      */   }
/*      */ 
/*      */   public DriverConfig getSerialConfig()
/*      */   {
/*  964 */     return this.m_serialConfig;
/*      */   }
/*      */ 
/*      */   public DriverConfig getBDDriverConfig()
/*      */   {
/*  973 */     return this.m_bdDriverConfig;
/*      */   }
/*      */ 
/*      */   public DriverConfig getComLink2Config()
/*      */   {
/*  982 */     return this.m_comlink2Config;
/*      */   }
/*      */ 
/*      */   public DriverConfig getBayerUSBConfig()
/*      */   {
/*  991 */     return this.m_bayerUSBConfig;
/*      */   }
/*      */ 
/*      */   public Color getBorderColor()
/*      */   {
/* 1000 */     return BORDER_COLOR;
/*      */   }
/*      */ 
/*      */   public Font getBannerAreaFont()
/*      */   {
/* 1009 */     return BANNER_AREA_FONT;
/*      */   }
/*      */ 
/*      */   public DTWApplet getApplet()
/*      */   {
/* 1018 */     return this.m_applet;
/*      */   }
/*      */ 
/*      */   public static class DriverConfig
/*      */   {
/*      */     private final String m_installDriverDir;
/*      */     private final String m_installDriverJarLoc;
/*      */     private final String[] m_installDriverFiles;
/*      */     private final String[] m_installDriverCommand;
/*      */     private final String m_installDriverSuccessIndicatorFile;
/*      */ 
/*      */     public DriverConfig(String paramString1, String paramString2, String[] paramArrayOfString1, String[] paramArrayOfString2, String paramString3)
/*      */     {
/* 1076 */       Contract.preString(paramString1);
/* 1077 */       Contract.preString(paramString2);
/* 1078 */       Contract.preNonNull(paramArrayOfString1);
/* 1079 */       Contract.pre(paramArrayOfString1.length > 0);
/* 1080 */       Contract.preNonNull(paramArrayOfString2);
/* 1081 */       Contract.pre(paramArrayOfString2.length > 0);
/* 1082 */       Contract.preString(paramString3);
/* 1083 */       this.m_installDriverDir = paramString1;
/* 1084 */       this.m_installDriverJarLoc = paramString2;
/* 1085 */       this.m_installDriverFiles = paramArrayOfString1;
/* 1086 */       this.m_installDriverCommand = paramArrayOfString2;
/* 1087 */       this.m_installDriverSuccessIndicatorFile = paramString3;
/*      */     }
/*      */ 
/*      */     public String toString()
/*      */     {
/* 1094 */       PropertyWriter localPropertyWriter = new PropertyWriter();
/* 1095 */       localPropertyWriter.add("installDriverDir", this.m_installDriverDir);
/* 1096 */       localPropertyWriter.add("installDriverJarLoc", this.m_installDriverJarLoc);
/*      */ 
/* 1098 */       for (int i = 0; i < this.m_installDriverFiles.length; i++) {
/* 1099 */         localPropertyWriter.add("installDriverFile-" + i, this.m_installDriverFiles[i]);
/*      */       }
/*      */ 
/* 1102 */       for (i = 0; i < this.m_installDriverCommand.length; i++) {
/* 1103 */         localPropertyWriter.add("installDriverCommand-" + i, this.m_installDriverCommand[i]);
/*      */       }
/*      */ 
/* 1106 */       localPropertyWriter.add("installDriverSuccessIndicatorFile", this.m_installDriverSuccessIndicatorFile);
/* 1107 */       return localPropertyWriter.toString();
/*      */     }
/*      */ 
/*      */     public String getInstallDriverDir()
/*      */     {
/* 1117 */       String str = this.m_installDriverDir;
/* 1118 */       Contract.postString(str);
/* 1119 */       return str;
/*      */     }
/*      */ 
/*      */     public String[] getInstallDriverFiles()
/*      */     {
/* 1129 */       String[] arrayOfString = this.m_installDriverFiles;
/* 1130 */       Contract.postNonNull(arrayOfString);
/* 1131 */       Contract.post(arrayOfString.length > 0);
/* 1132 */       return arrayOfString;
/*      */     }
/*      */ 
/*      */     public String getInstallDriverJarLoc()
/*      */     {
/* 1142 */       String str = this.m_installDriverJarLoc;
/* 1143 */       Contract.postString(str);
/* 1144 */       return str;
/*      */     }
/*      */ 
/*      */     public String[] getInstallDriverCommand()
/*      */     {
/* 1154 */       String[] arrayOfString = this.m_installDriverCommand;
/* 1155 */       Contract.post((arrayOfString != null) && (arrayOfString.length > 0));
/* 1156 */       return arrayOfString;
/*      */     }
/*      */ 
/*      */     public String getInstallDriverSuccessIndicatorFile()
/*      */     {
/* 1166 */       String str = this.m_installDriverSuccessIndicatorFile;
/* 1167 */       Contract.postString(str);
/* 1168 */       return str;
/*      */     }
/*      */ 
/*      */     public boolean isDriverInstallNeeded()
/*      */     {
/* 1177 */       String str = getInstallDriverDir() + File.separator + getInstallDriverSuccessIndicatorFile();
/*      */ 
/* 1179 */       File localFile = new File(str);
/* 1180 */       int i = (!localFile.exists()) || (!localFile.isFile()) ? 1 : 0;
/* 1181 */       return i;
/*      */     }
/*      */   }
/*      */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.WizardConfig
 * JD-Core Version:    0.6.0
 */