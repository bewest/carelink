/*      */ package minimed.ddms.applet.dtw.wizard;
/*      */ 
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collections;
/*      */ import java.util.HashMap;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.ResourceBundle;
/*      */ import minimed.ddms.applet.dtw.DTWApplet;
/*      */ import minimed.ddms.applet.dtw.DeviceID;
/*      */ import minimed.ddms.applet.dtw.LogWriter;
/*      */ import minimed.ddms.applet.dtw.PreferencesHelper;
/*      */ import minimed.ddms.applet.dtw.PropertyWriter;
/*      */ import minimed.util.Contract;
/*      */ 
/*      */ public final class WizardSelections
/*      */ {
/*      */   private static final int MAX_KEY_LENGTH = 80;
/*      */   public static final String KEY_RESOURCE_SELECTION_BRAND_ASCENSIA_BAYER = "wizard.selections.SELECTION_BRAND_ASCENSIA_BAYER";
/*      */   public static final String KEY_RESOURCE_SELECTION_BRAND_LIFESCAN = "wizard.selections.SELECTION_BRAND_LIFESCAN";
/*      */   public static final String KEY_RESOURCE_SELECTION_BRAND_ROCHE = "wizard.selections.SELECTION_BRAND_ROCHE";
/*      */   public static final String KEY_RESOURCE_SELECTION_BRAND_MINIMED = "wizard.selections.SELECTION_BRAND_MINIMED_BD";
/*      */   public static final String KEY_RESOURCE_SELECTION_BRAND_BD = "wizard.selections.SELECTION_BRAND_BD";
/*      */   public static final String KEY_RESOURCE_SELECTION_CONN_TYPE_SERIAL = "wizard.selections.SELECTION_CONN_TYPE_SERIAL";
/*      */   public static final String KEY_RESOURCE_SELECTION_CONN_TYPE_USB = "wizard.selections.SELECTION_CONN_TYPE_USB";
/*      */   public static final String KEY_RESOURCE_SELECTION_DEVICE_ASCENSIA_BREEZE_METER = "wizard.selections.SELECTION_DEVICE_ASCENSIA_BREEZE_METER";
/*      */   public static final String KEY_RESOURCE_SELECTION_DEVICE_ASCENSIA_CONTOUR_METER = "wizard.selections.SELECTION_DEVICE_ASCENSIA_CONTOUR_METER";
/*      */   public static final String KEY_RESOURCE_SELECTION_DEVICE_ASCENSIA_DEX_METER = "wizard.selections.SELECTION_DEVICE_ASCENSIA_DEX_METER";
/*      */   public static final String KEY_RESOURCE_SELECTION_DEVICE_ASCENSIA_ELITE_METER = "wizard.selections.SELECTION_DEVICE_ASCENSIA_ELITE_METER";
/*      */   public static final String KEY_RESOURCE_SELECTION_DEVICE_ASCENSIA_CONTOUR_USB_METER = "wizard.selections.SELECTION_DEVICE_ASCENSIA_CONTOUR_USB_METER";
/*      */   public static final String KEY_RESOURCE_SELECTION_DEVICE_ASCENSIA_CONTOUR_XT_LINK_METER = "wizard.selections.SELECTION_DEVICE_XTLINKMETER";
/*      */   public static final String KEY_RESOURCE_SELECTION_DEVICE_LIFESCAN_BASIC_METER = "wizard.selections.SELECTION_DEVICE_LIFESCAN_BASIC_METER";
/*      */   public static final String KEY_RESOURCE_SELECTION_DEVICE_LIFESCAN_FASTTAKE_METER = "wizard.selections.SELECTION_DEVICE_LIFESCAN_FASTTAKE_METER";
/*      */   public static final String KEY_RESOURCE_SELECTION_DEVICE_LIFESCAN_PROFILE_METER = "wizard.selections.SELECTION_DEVICE_LIFESCAN_PROFILE_METER";
/*      */   public static final String KEY_RESOURCE_SELECTION_DEVICE_LIFESCAN_SURESTEP_METER = "wizard.selections.SELECTION_DEVICE_LIFESCAN_SURESTEP_METER";
/*      */   public static final String KEY_RESOURCE_SELECTION_DEVICE_LIFESCAN_ULTRA_METER = "wizard.selections.SELECTION_DEVICE_LIFESCAN_ULTRA_METER";
/*      */   public static final String KEY_RESOURCE_SELECTION_DEVICE_LIFESCAN_ULTRASMART_METER = "wizard.selections.SELECTION_DEVICE_LIFESCAN_ULTRASMART_METER";
/*      */   public static final String KEY_RESOURCE_SELECTION_DEVICE_LIFESCAN_ULTRAMINI_METER = "wizard.selections.SELECTION_DEVICE_LIFESCAN_ULTRAMINI_METER";
/*      */   public static final String KEY_RESOURCE_SELECTION_DEVICE_MEDISENSE_XTRA_METER = "wizard.selections.SELECTION_DEVICE_MEDISENSE_XTRA_METER";
/*      */   public static final String KEY_RESOURCE_SELECTION_DEVICE_MMGUARDIAN3 = "wizard.selections.SELECTION_DEVICE_MMGUARDIAN3";
/*      */   public static final String KEY_RESOURCE_SELECTION_DEVICE_MMLINKMETER = "wizard.selections.SELECTION_DEVICE_MMLINKMETER";
/*      */   public static final String KEY_RESOURCE_SELECTION_DEVICE_MMLOGICMETER = "wizard.selections.SELECTION_DEVICE_MMLOGICMETER";
/*      */   public static final String KEY_RESOURCE_SELECTION_DEVICE_MMPARADIGM2 = "wizard.selections.SELECTION_DEVICE_MMPARADIGM2";
/*      */   public static final String KEY_RESOURCE_SELECTION_DEVICE_THERASENSE_FREESTYLE_METER = "wizard.selections.SELECTION_DEVICE_THERASENSE_FREESTYLE_METER";
/*      */   public static final String KEY_RESOURCE_SELECTION_GROUP_MEDISENSE_THERASENSE = "wizard.selections.SELECTION_GROUP_MEDISENSE_THERASENSE";
/*      */   public static final String KEY_RESOURCE_SELECTION_LINK_DEVICE_COMLINK = "wizard.selections.SELECTION_LINK_DEVICE_COMLINK";
/*      */   public static final String KEY_RESOURCE_SELECTION_LINK_DEVICE_XTLINKUSB = "wizard.selections.SELECTION_LINK_DEVICE_XTLINKUSB";
/*      */   public static final String KEY_RESOURCE_SELECTION_LINK_DEVICE_COMLINKUSB = "wizard.selections.SELECTION_LINK_DEVICE_COMLINKUSB";
/*      */   public static final String KEY_RESOURCE_SELECTION_LINK_DEVICE_PARADIGMLINK = "wizard.selections.SELECTION_LINK_DEVICE_PARADIGMLINK";
/*      */   public static final String KEY_RESOURCE_SELECTION_SERIAL_PORT_AUTO_DETECT = "wizard.selections.SELECTION_SERIAL_PORT_AUTO_DETECT";
/*      */   public static final String KEY_RESOURCE_SELECTION_SERIAL_PORT_SELECT_PORT = "wizard.selections.SELECTION_SERIAL_PORT_SELECT_PORT";
/*      */   public static final String KEY_RESOURCE_TOKEN_DEVICE_MMPARADIGM2_ALL = "wizard.selections.TOKEN_DEVICE_MMPARADIGM2_ALL";
/*      */   public static final String KEY_RESOURCE_TOKEN_DEVICE_MMPARADIGM2_X15 = "wizard.selections.TOKEN_DEVICE_MMPARADIGM2_X15";
/*      */   public static final String KEY_RESOURCE_TOKEN_DEVICE_THERASENSE_FREESTYLE_METER = "wizard.selections.TOKEN_DEVICE_THERASENSE_FREESTYLE_METER";
/*      */   public static final String KEY_RESOURCE_SELECTION_DEVICE_ROCHE_ACTIVE_METER = "wizard.selections.SELECTION_DEVICE_ROCHE_ACTIVE_METER";
/*      */   public static final String KEY_RESOURCE_SELECTION_DEVICE_ROCHE_AVIVA_METER = "wizard.selections.SELECTION_DEVICE_ROCHE_AVIVA_METER";
/*      */   public static final String KEY_RESOURCE_SELECTION_DEVICE_ROCHE_COMPACT_METER = "wizard.selections.SELECTION_DEVICE_ROCHE_COMPACT_METER";
/*      */   public static final String KEY_RESOURCE_SELECTION_DEVICE_ROCHE_COMPACTPLUS_METER = "wizard.selections.SELECTION_DEVICE_ROCHE_COMPACTPLUS_METER";
/*      */   public static final String KEY_RESOURCE_SELECTION_DEVICE_CGM = "wizard.selections.SELECTION_DEVICE_CGM";
/*      */   public static final String KEY_RESOURCE_SELECTION_DEVICE_METER = "wizard.selections.SELECTION_DEVICE_METER";
/*      */   public static final String KEY_RESOURCE_SELECTION_DEVICE_PUMP = "wizard.selections.SELECTION_DEVICE_PUMP";
/*      */   private static final String KEY_RESOURCE_SELECTION_SERIAL_PORT_NAMED = "wizard.selections.SELECTION_SERIAL_PORT_NAMED";
/*      */   private static final String KEY_USER_INPUT_COMPLETED_BASE_PART = "wizard2.finish-";
/*      */   private static final String KEY_CONNECTION_TYPE = "wizard2.connectiontype";
/*      */   private static final String KEY_LINK_DEVICE = "wizard2.linkdevice";
/*      */   private static final String KEY_PUMP_DEVICE = "wizard2.pumpdevice";
/*      */   private static final String KEY_METER_DEVICE = "wizard2.meterdevice";
/*      */   private static final String KEY_CGM_DEVICE = "wizard2.cgmdevice";
/*      */   private static final String KEY_METER_BRAND = "wizard2.meterbrand";
/*      */   private static final String KEY_DEVICE_TYPE = "wizard2.devicetype";
/*      */   private static final String KEY_SERIAL_PORT_METHOD = "wizard2.serialportmethod";
/*      */   private static final String KEY_SERIAL_PORT_NAME = "wizard2.serialportname";
/*      */   private static final List VALID_CONNECTION_TYPES;
/*      */   private static final List VALID_DEVICE_TYPES;
/*      */   private static final List VALID_LINK_DEVICES;
/*      */   private static final List VALID_PUMP_DEVICES;
/*      */   private static final List VALID_METER_DEVICES;
/*      */   private static final List VALID_CGM_DEVICES;
/*      */   private static final List VALID_METER_BRANDS;
/*      */   private static final List VALID_SERIAL_PORT_METHODS;
/*      */   private static final Map METERS_BY_BRAND;
/*  521 */   private final ResourceBundle m_resources = DTWApplet.getResourceBundle();
/*      */ 
/*  526 */   final String m_tokenDeviceMMParadigm2X15 = this.m_resources.getString("wizard.selections.TOKEN_DEVICE_MMPARADIGM2_X15");
/*      */ 
/*  532 */   public final String m_selectionDeviceMMLinkmeter = this.m_resources.getString("wizard.selections.SELECTION_DEVICE_MMLINKMETER");
/*      */ 
/*  538 */   public final String m_selectionDeviceMMLogicMeter = this.m_resources.getString("wizard.selections.SELECTION_DEVICE_MMLOGICMETER");
/*      */ 
/*  544 */   public final String m_selectionBrandBD = this.m_resources.getString("wizard.selections.SELECTION_BRAND_BD");
/*      */ 
/*  550 */   public final String m_selectionBrandMinimed = this.m_resources.getString("wizard.selections.SELECTION_BRAND_MINIMED_BD");
/*      */ 
/*  556 */   public final String m_selectionBrandAscensiaBayer = this.m_resources.getString("wizard.selections.SELECTION_BRAND_ASCENSIA_BAYER");
/*      */ 
/*  562 */   public final String m_selectionBrandLifeScan = this.m_resources.getString("wizard.selections.SELECTION_BRAND_LIFESCAN");
/*      */ 
/*  568 */   public final String m_selectionBrandRoche = this.m_resources.getString("wizard.selections.SELECTION_BRAND_ROCHE");
/*      */ 
/*  574 */   public final String m_selectionGroupMedisenseTherasense = this.m_resources.getString("wizard.selections.SELECTION_GROUP_MEDISENSE_THERASENSE");
/*      */ 
/*  580 */   public final String m_selectionDeviceLifeScanProfileMeter = this.m_resources.getString("wizard.selections.SELECTION_DEVICE_LIFESCAN_PROFILE_METER");
/*      */ 
/*  586 */   public final String m_selectionDeviceLifeScanUltraMeter = this.m_resources.getString("wizard.selections.SELECTION_DEVICE_LIFESCAN_ULTRA_METER");
/*      */ 
/*  592 */   public final String m_selectionDeviceAscensiaContourXTLink = this.m_resources.getString("wizard.selections.SELECTION_DEVICE_XTLINKMETER");
/*      */ 
/*  597 */   public final String m_selectionDeviceAscensiaContourLink = this.m_resources.getString("wizard.selections.SELECTION_DEVICE_ASCENSIA_CONTOUR_LINK");
/*      */ 
/*  603 */   public final String m_selectionDeviceLifeScanUltraLink = this.m_resources.getString("wizard.selections.SELECTION_DEVICE_LIFESCAN_ULTRA_LINK");
/*      */ 
/*  609 */   public final String m_selectionDeviceLifeScanUltraSmartMeter = this.m_resources.getString("wizard.selections.SELECTION_DEVICE_LIFESCAN_ULTRASMART_METER");
/*      */ 
/*  615 */   public final String m_selectionDeviceLifeScanUltraMiniMeter = this.m_resources.getString("wizard.selections.SELECTION_DEVICE_LIFESCAN_ULTRAMINI_METER");
/*      */ 
/*  621 */   public final String m_selectionDeviceLifeScanBasicMeter = this.m_resources.getString("wizard.selections.SELECTION_DEVICE_LIFESCAN_BASIC_METER");
/*      */ 
/*  627 */   public final String m_selectionDeviceLifeScanFastTakeMeter = this.m_resources.getString("wizard.selections.SELECTION_DEVICE_LIFESCAN_FASTTAKE_METER");
/*      */ 
/*  633 */   public final String m_selectionDeviceLifeScanSureStepMeter = this.m_resources.getString("wizard.selections.SELECTION_DEVICE_LIFESCAN_SURESTEP_METER");
/*      */ 
/*  639 */   final String m_tokenDeviceTheraSenseFreeStyleMeter = this.m_resources.getString("wizard.selections.TOKEN_DEVICE_THERASENSE_FREESTYLE_METER");
/*      */ 
/*  645 */   public final String m_selectionDeviceRocheActiveMeter = this.m_resources.getString("wizard.selections.SELECTION_DEVICE_ROCHE_ACTIVE_METER");
/*      */ 
/*  651 */   public final String m_selectionDeviceRocheAvivaMeter = this.m_resources.getString("wizard.selections.SELECTION_DEVICE_ROCHE_AVIVA_METER");
/*      */ 
/*  657 */   public final String m_selectionDeviceRocheCompactMeter = this.m_resources.getString("wizard.selections.SELECTION_DEVICE_ROCHE_COMPACT_METER");
/*      */ 
/*  663 */   public final String m_selectionDeviceRocheCompactPlusMeter = this.m_resources.getString("wizard.selections.SELECTION_DEVICE_ROCHE_COMPACTPLUS_METER");
/*      */ 
/*  669 */   public final String m_selectionLinkDeviceParadigmLink = this.m_resources.getString("wizard.selections.SELECTION_LINK_DEVICE_PARADIGMLINK");
/*      */ 
/*  675 */   public final String m_selectionSerialPortAutoDetect = this.m_resources.getString("wizard.selections.SELECTION_SERIAL_PORT_AUTO_DETECT");
/*      */ 
/*  681 */   public final String m_selectionSerialPortSelectPort = this.m_resources.getString("wizard.selections.SELECTION_SERIAL_PORT_SELECT_PORT");
/*      */   private final PreferencesHelper m_preferencesHelper;
/*      */   private DeviceID m_currentPumpID;
/*      */   private DeviceID m_currentMeterID;
/*      */   private DeviceID m_currentCGMID;
/*      */ 
/*      */   public WizardSelections(LogWriter paramLogWriter)
/*      */   {
/*  713 */     Contract.preNonNull(paramLogWriter);
/*  714 */     this.m_preferencesHelper = new PreferencesHelper(paramLogWriter);
/*      */   }
/*      */ 
/*      */   public String toString()
/*      */   {
/*  725 */     PropertyWriter localPropertyWriter = new PropertyWriter();
/*  726 */     localPropertyWriter.add("isUserInputCompleted", isUserInputCompleted());
/*  727 */     localPropertyWriter.add("currentPumpID", this.m_currentPumpID);
/*  728 */     localPropertyWriter.add("currentMeterID", this.m_currentMeterID);
/*  729 */     localPropertyWriter.add("currentCGMID", this.m_currentCGMID);
/*  730 */     localPropertyWriter.add("serialPortName", getSerialPortName());
/*  731 */     localPropertyWriter.add("serialPort", getSerialPort());
/*  732 */     localPropertyWriter.add("meterDevice", getMeterDevice());
/*  733 */     localPropertyWriter.add("pumpDevice", getPumpDevice());
/*  734 */     localPropertyWriter.add("linkDevice", getLinkDevice());
/*  735 */     localPropertyWriter.add("device", getDeviceType());
/*  736 */     localPropertyWriter.add("connectionType", getConnectionType());
/*  737 */     return localPropertyWriter.toString();
/*      */   }
/*      */ 
/*      */   public void flush()
/*      */   {
/*  744 */     this.m_preferencesHelper.flushSystemPreferences();
/*      */   }
/*      */ 
/*      */   public void setConnectionType(String paramString)
/*      */   {
/*  754 */     Contract.pre(VALID_CONNECTION_TYPES.contains(paramString));
/*  755 */     this.m_preferencesHelper.setSystemPreference("wizard2.connectiontype", paramString);
/*      */   }
/*      */ 
/*      */   public void setLinkDevice(String paramString)
/*      */   {
/*  765 */     Contract.pre(VALID_LINK_DEVICES.contains(paramString));
/*  766 */     this.m_preferencesHelper.setSystemPreference("wizard2.linkdevice", paramString);
/*      */   }
/*      */ 
/*      */   public void setPumpDevice(String paramString)
/*      */   {
/*  776 */     Contract.pre(VALID_PUMP_DEVICES.contains(paramString));
/*  777 */     this.m_preferencesHelper.setSystemPreference("wizard2.pumpdevice", paramString);
/*      */   }
/*      */ 
/*      */   public void setDeviceType(String paramString)
/*      */   {
/*  788 */     Contract.pre(VALID_DEVICE_TYPES.contains(paramString));
/*  789 */     this.m_preferencesHelper.setSystemPreference("wizard2.devicetype", paramString);
/*      */   }
/*      */ 
/*      */   public void setPumpSerialNumber(String paramString)
/*      */   {
/*  799 */     Contract.preString(paramString);
/*      */ 
/*  801 */     String str = Wizard.mapToServerClassName(getPumpDevice());
/*  802 */     this.m_currentPumpID = new DeviceID(str, paramString);
/*      */   }
/*      */ 
/*      */   public void clearPumpSerialNumber()
/*      */   {
/*  809 */     this.m_currentPumpID = null;
/*      */   }
/*      */ 
/*      */   public void setCGMSerialNumber(String paramString)
/*      */   {
/*  819 */     Contract.preString(paramString);
/*      */ 
/*  821 */     String str = Wizard.mapToServerClassName(getCGMDevice());
/*  822 */     this.m_currentCGMID = new DeviceID(str, paramString);
/*      */   }
/*      */ 
/*      */   public void clearCGMSerialNumber()
/*      */   {
/*  829 */     this.m_currentCGMID = null;
/*      */   }
/*      */ 
/*      */   public void setMeterSerialNumber(String paramString)
/*      */   {
/*  839 */     Contract.preString(paramString);
/*      */ 
/*  841 */     String str = Wizard.mapToServerClassName(getMeterDevice());
/*  842 */     this.m_currentMeterID = new DeviceID(str, paramString);
/*      */   }
/*      */ 
/*      */   public void setMeterDevice(String paramString)
/*      */   {
/*  852 */     Contract.pre(VALID_METER_DEVICES.contains(paramString));
/*  853 */     this.m_preferencesHelper.setSystemPreference("wizard2.meterdevice", paramString);
/*      */   }
/*      */ 
/*      */   public void setMeterBrand(String paramString)
/*      */   {
/*  863 */     Contract.pre(VALID_METER_BRANDS.contains(paramString));
/*  864 */     this.m_preferencesHelper.setSystemPreference("wizard2.meterbrand", paramString);
/*      */   }
/*      */ 
/*      */   public void setCGMDevice(String paramString)
/*      */   {
/*  874 */     Contract.pre(VALID_CGM_DEVICES.contains(paramString));
/*  875 */     this.m_preferencesHelper.setSystemPreference("wizard2.cgmdevice", paramString);
/*      */   }
/*      */ 
/*      */   public void setSerialPort(String paramString)
/*      */   {
/*  885 */     Contract.pre(VALID_SERIAL_PORT_METHODS.contains(paramString));
/*  886 */     this.m_preferencesHelper.setSystemPreference("wizard2.serialportmethod", paramString);
/*      */   }
/*      */ 
/*      */   public void setSerialPortName(String paramString)
/*      */   {
/*  898 */     Contract.preString(paramString);
/*  899 */     this.m_preferencesHelper.setSystemPreference("wizard2.serialportname", paramString);
/*      */   }
/*      */ 
/*      */   public void setUserInputCompleted()
/*      */   {
/*  909 */     this.m_preferencesHelper.setSystemPreference(getUserInputCompletedKey(), true);
/*      */   }
/*      */ 
/*      */   public String getConnectionType()
/*      */   {
/*  922 */     String str = this.m_preferencesHelper.getSystemPreference("wizard2.connectiontype", VALID_CONNECTION_TYPES, "wizard.selections.SELECTION_CONN_TYPE_USB");
/*      */ 
/*  924 */     Contract.post(VALID_CONNECTION_TYPES.contains(str));
/*  925 */     return str;
/*      */   }
/*      */ 
/*      */   public String getDeviceType()
/*      */   {
/*  937 */     String str = this.m_preferencesHelper.getSystemPreference("wizard2.devicetype", VALID_DEVICE_TYPES, "wizard.selections.SELECTION_DEVICE_PUMP");
/*      */ 
/*  939 */     Contract.post(VALID_DEVICE_TYPES.contains(str));
/*  940 */     return str;
/*      */   }
/*      */ 
/*      */   public String getLinkDevice()
/*      */   {
/*  955 */     return getLinkDevice("wizard.selections.SELECTION_LINK_DEVICE_COMLINKUSB");
/*      */   }
/*      */ 
/*      */   public String getLinkDevice(String paramString)
/*      */   {
/*  967 */     String str = this.m_preferencesHelper.getSystemPreference("wizard2.linkdevice", VALID_LINK_DEVICES, paramString);
/*      */ 
/*  969 */     Contract.post(VALID_LINK_DEVICES.contains(str));
/*  970 */     return str;
/*      */   }
/*      */ 
/*      */   public String getPumpDevice()
/*      */   {
/*  982 */     String str = this.m_preferencesHelper.getSystemPreference("wizard2.pumpdevice", VALID_PUMP_DEVICES, "wizard.selections.SELECTION_DEVICE_MMPARADIGM2");
/*      */ 
/*  984 */     Contract.post(VALID_PUMP_DEVICES.contains(str));
/*  985 */     return str;
/*      */   }
/*      */ 
/*      */   public String getMeterDevice(String paramString)
/*      */   {
/*  997 */     String str = this.m_preferencesHelper.getSystemPreference("wizard2.meterdevice", VALID_METER_DEVICES, paramString);
/*      */ 
/*  999 */     Contract.post(VALID_METER_DEVICES.contains(str));
/* 1000 */     return str;
/*      */   }
/*      */ 
/*      */   public String getMeterDevice()
/*      */   {
/* 1011 */     return getMeterDevice("wizard.selections.SELECTION_DEVICE_LIFESCAN_ULTRA_METER");
/*      */   }
/*      */ 
/*      */   public String getCGMDevice()
/*      */   {
/* 1023 */     String str = this.m_preferencesHelper.getSystemPreference("wizard2.cgmdevice", VALID_CGM_DEVICES, "wizard.selections.SELECTION_DEVICE_MMGUARDIAN3");
/*      */ 
/* 1025 */     Contract.post(VALID_CGM_DEVICES.contains(str));
/* 1026 */     return str;
/*      */   }
/*      */ 
/*      */   public String getMeterBrand()
/*      */   {
/* 1038 */     String str = this.m_preferencesHelper.getSystemPreference("wizard2.meterbrand", VALID_METER_BRANDS, "wizard.selections.SELECTION_BRAND_MINIMED_BD");
/*      */ 
/* 1040 */     Contract.post(VALID_METER_BRANDS.contains(str));
/* 1041 */     return str;
/*      */   }
/*      */ 
/*      */   public boolean isRocheMeter()
/*      */   {
/* 1050 */     return "wizard.selections.SELECTION_BRAND_ROCHE".equals(getMeterBrand());
/*      */   }
/*      */ 
/*      */   public String getSerialPort()
/*      */   {
/* 1062 */     String str = this.m_preferencesHelper.getSystemPreference("wizard2.serialportmethod", VALID_SERIAL_PORT_METHODS, "wizard.selections.SELECTION_SERIAL_PORT_AUTO_DETECT");
/*      */ 
/* 1064 */     Contract.post(VALID_SERIAL_PORT_METHODS.contains(str));
/* 1065 */     return str;
/*      */   }
/*      */ 
/*      */   public String getSerialPortName()
/*      */   {
/* 1077 */     String str = this.m_preferencesHelper.getSystemPreference("wizard2.serialportname", "wizard.selections.SELECTION_SERIAL_PORT_NAMED");
/*      */ 
/* 1079 */     Contract.postString(str);
/* 1080 */     return str;
/*      */   }
/*      */ 
/*      */   public String getDeviceSerialNumber()
/*      */   {
/*      */     String str;
/* 1090 */     if (getDeviceType().equals("wizard.selections.SELECTION_DEVICE_PUMP")) {
/* 1091 */       str = getPumpSerialNumber();
/* 1092 */     } else if (getDeviceType().equals("wizard.selections.SELECTION_DEVICE_METER")) {
/* 1093 */       str = getMeterSerialNumber();
/* 1094 */     } else if (getDeviceType().equals("wizard.selections.SELECTION_DEVICE_CGM")) {
/* 1095 */       str = getCGMSerialNumber();
/*      */     } else {
/* 1097 */       str = null;
/* 1098 */       Contract.unreachable();
/*      */     }
/* 1100 */     return str;
/*      */   }
/*      */ 
/*      */   public String getDeviceSelection()
/*      */   {
/*      */     String str;
/* 1110 */     if (getDeviceType().equals("wizard.selections.SELECTION_DEVICE_PUMP")) {
/* 1111 */       str = getPumpDevice();
/* 1112 */     } else if (getDeviceType().equals("wizard.selections.SELECTION_DEVICE_METER")) {
/* 1113 */       str = getMeterDevice();
/* 1114 */     } else if (getDeviceType().equals("wizard.selections.SELECTION_DEVICE_CGM")) {
/* 1115 */       str = getCGMDevice();
/*      */     } else {
/* 1117 */       str = null;
/* 1118 */       Contract.unreachable();
/*      */     }
/* 1120 */     return str;
/*      */   }
/*      */ 
/*      */   public String getPumpSerialNumber()
/*      */   {
/* 1129 */     return this.m_currentPumpID != null ? this.m_currentPumpID.getSerialNumber() : null;
/*      */   }
/*      */ 
/*      */   public String getMeterSerialNumber()
/*      */   {
/* 1138 */     return this.m_currentMeterID != null ? this.m_currentMeterID.getSerialNumber() : null;
/*      */   }
/*      */ 
/*      */   public String getCGMSerialNumber()
/*      */   {
/* 1147 */     return this.m_currentCGMID != null ? this.m_currentCGMID.getSerialNumber() : null;
/*      */   }
/*      */ 
/*      */   public boolean isDeviceSelectionAPump()
/*      */   {
/* 1156 */     return VALID_PUMP_DEVICES.contains(getDeviceSelection());
/*      */   }
/*      */ 
/*      */   public boolean isDeviceSelectionACGM()
/*      */   {
/* 1165 */     return VALID_CGM_DEVICES.contains(getDeviceSelection());
/*      */   }
/*      */ 
/*      */   public boolean isDeviceSelectionAMeter()
/*      */   {
/* 1174 */     return VALID_METER_DEVICES.contains(getDeviceSelection());
/*      */   }
/*      */ 
/*      */   public void setCurrentPumpID(DeviceID paramDeviceID)
/*      */   {
/* 1183 */     this.m_currentPumpID = paramDeviceID;
/*      */   }
/*      */ 
/*      */   public void setCurrentMeterID(DeviceID paramDeviceID)
/*      */   {
/* 1192 */     this.m_currentMeterID = paramDeviceID;
/*      */   }
/*      */ 
/*      */   public PreferencesHelper getPreferencesHelper()
/*      */   {
/* 1200 */     return this.m_preferencesHelper;
/*      */   }
/*      */ 
/*      */   public void setCurrentCGMID(DeviceID paramDeviceID)
/*      */   {
/* 1209 */     this.m_currentCGMID = paramDeviceID;
/*      */   }
/*      */ 
/*      */   boolean isUserInputCompleted()
/*      */   {
/* 1222 */     return this.m_preferencesHelper.getSystemPreference(getUserInputCompletedKey(), false);
/*      */   }
/*      */ 
/*      */   boolean isMeterBrandAndModelMismatch(String paramString1, String paramString2)
/*      */   {
/* 1236 */     Contract.pre(VALID_METER_BRANDS.contains(paramString1));
/* 1237 */     Contract.pre(VALID_METER_DEVICES.contains(paramString2));
/* 1238 */     List localList = (List)METERS_BY_BRAND.get(paramString1);
/* 1239 */     return !localList.contains(paramString2);
/*      */   }
/*      */ 
/*      */   boolean isDeviceTypeSet()
/*      */   {
/* 1248 */     return this.m_preferencesHelper.isSystemPreferenceSet("wizard2.devicetype");
/*      */   }
/*      */ 
/*      */   boolean isPumpDeviceSet()
/*      */   {
/* 1257 */     return this.m_preferencesHelper.isSystemPreferenceSet("wizard2.pumpdevice");
/*      */   }
/*      */ 
/*      */   boolean isMeterDeviceSet()
/*      */   {
/* 1266 */     return this.m_preferencesHelper.isSystemPreferenceSet("wizard2.meterdevice");
/*      */   }
/*      */ 
/*      */   boolean isCGMDeviceSet()
/*      */   {
/* 1275 */     return this.m_preferencesHelper.isSystemPreferenceSet("wizard2.cgmdevice");
/*      */   }
/*      */ 
/*      */   boolean isMeterBrandSet()
/*      */   {
/* 1284 */     return this.m_preferencesHelper.isSystemPreferenceSet("wizard2.meterbrand");
/*      */   }
/*      */ 
/*      */   boolean isLinkDeviceSet()
/*      */   {
/* 1293 */     return this.m_preferencesHelper.isSystemPreferenceSet("wizard2.linkdevice");
/*      */   }
/*      */ 
/*      */   boolean isConnectionTypeSet()
/*      */   {
/* 1302 */     return this.m_preferencesHelper.isSystemPreferenceSet("wizard2.connectiontype");
/*      */   }
/*      */ 
/*      */   boolean isSerialPortMethodSet()
/*      */   {
/* 1311 */     return this.m_preferencesHelper.isSystemPreferenceSet("wizard2.serialportmethod");
/*      */   }
/*      */ 
/*      */   void clear()
/*      */   {
/* 1318 */     this.m_preferencesHelper.clear();
/*      */   }
/*      */ 
/*      */   private String getUserInputCompletedKey()
/*      */   {
/* 1329 */     String str = "wizard2.finish-" + getDeviceSelection();
/* 1330 */     Contract.post(str.length() <= 80);
/* 1331 */     return str;
/*      */   }
/*      */ 
/*      */   static
/*      */   {
/*  404 */     ArrayList localArrayList = new ArrayList();
/*  405 */     localArrayList.add("wizard.selections.SELECTION_CONN_TYPE_SERIAL");
/*  406 */     localArrayList.add("wizard.selections.SELECTION_CONN_TYPE_USB");
/*  407 */     VALID_CONNECTION_TYPES = Collections.unmodifiableList(localArrayList);
/*      */ 
/*  410 */     localArrayList = new ArrayList();
/*  411 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_PUMP");
/*  412 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_METER");
/*  413 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_CGM");
/*  414 */     VALID_DEVICE_TYPES = Collections.unmodifiableList(localArrayList);
/*      */ 
/*  417 */     localArrayList = new ArrayList();
/*  418 */     localArrayList.add("wizard.selections.SELECTION_LINK_DEVICE_PARADIGMLINK");
/*  419 */     localArrayList.add("wizard.selections.SELECTION_LINK_DEVICE_COMLINKUSB");
/*  420 */     localArrayList.add("wizard.selections.SELECTION_LINK_DEVICE_COMLINK");
/*  421 */     localArrayList.add("wizard.selections.SELECTION_LINK_DEVICE_XTLINKUSB");
/*  422 */     VALID_LINK_DEVICES = Collections.unmodifiableList(localArrayList);
/*      */ 
/*  425 */     localArrayList = new ArrayList();
/*  426 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_MMPARADIGM2");
/*  427 */     VALID_PUMP_DEVICES = Collections.unmodifiableList(localArrayList);
/*      */ 
/*  430 */     localArrayList = new ArrayList();
/*  431 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_MMLINKMETER");
/*  432 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_MMLOGICMETER");
/*  433 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_LIFESCAN_PROFILE_METER");
/*  434 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_LIFESCAN_ULTRA_METER");
/*  435 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_LIFESCAN_ULTRASMART_METER");
/*  436 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_LIFESCAN_ULTRAMINI_METER");
/*  437 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_LIFESCAN_BASIC_METER");
/*  438 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_LIFESCAN_FASTTAKE_METER");
/*  439 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_LIFESCAN_SURESTEP_METER");
/*  440 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_ASCENSIA_DEX_METER");
/*  441 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_ASCENSIA_ELITE_METER");
/*  442 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_ASCENSIA_BREEZE_METER");
/*  443 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_ASCENSIA_CONTOUR_METER");
/*  444 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_ASCENSIA_CONTOUR_USB_METER");
/*  445 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_XTLINKMETER");
/*  446 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_MEDISENSE_XTRA_METER");
/*  447 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_THERASENSE_FREESTYLE_METER");
/*  448 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_ROCHE_ACTIVE_METER");
/*  449 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_ROCHE_AVIVA_METER");
/*  450 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_ROCHE_COMPACT_METER");
/*  451 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_ROCHE_COMPACTPLUS_METER");
/*  452 */     VALID_METER_DEVICES = Collections.unmodifiableList(localArrayList);
/*      */ 
/*  455 */     localArrayList = new ArrayList();
/*  456 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_MMGUARDIAN3");
/*  457 */     VALID_CGM_DEVICES = Collections.unmodifiableList(localArrayList);
/*      */ 
/*  460 */     localArrayList = new ArrayList();
/*  461 */     localArrayList.add("wizard.selections.SELECTION_BRAND_MINIMED_BD");
/*  462 */     localArrayList.add("wizard.selections.SELECTION_BRAND_ASCENSIA_BAYER");
/*  463 */     localArrayList.add("wizard.selections.SELECTION_BRAND_LIFESCAN");
/*  464 */     localArrayList.add("wizard.selections.SELECTION_GROUP_MEDISENSE_THERASENSE");
/*  465 */     localArrayList.add("wizard.selections.SELECTION_BRAND_ROCHE");
/*  466 */     localArrayList.add("wizard.selections.SELECTION_BRAND_BD");
/*  467 */     VALID_METER_BRANDS = Collections.unmodifiableList(localArrayList);
/*      */ 
/*  470 */     HashMap localHashMap = new HashMap();
/*  471 */     localArrayList = new ArrayList();
/*  472 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_MMLINKMETER");
/*  473 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_LIFESCAN_ULTRA_METER");
/*  474 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_ASCENSIA_CONTOUR_METER");
/*  475 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_XTLINKMETER");
/*  476 */     localHashMap.put("wizard.selections.SELECTION_BRAND_MINIMED_BD", Collections.unmodifiableList(localArrayList));
/*  477 */     localArrayList = new ArrayList();
/*  478 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_LIFESCAN_PROFILE_METER");
/*  479 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_LIFESCAN_ULTRA_METER");
/*  480 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_LIFESCAN_ULTRASMART_METER");
/*  481 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_LIFESCAN_ULTRAMINI_METER");
/*  482 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_LIFESCAN_BASIC_METER");
/*  483 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_LIFESCAN_FASTTAKE_METER");
/*  484 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_LIFESCAN_SURESTEP_METER");
/*  485 */     localHashMap.put("wizard.selections.SELECTION_BRAND_LIFESCAN", Collections.unmodifiableList(localArrayList));
/*  486 */     localArrayList = new ArrayList();
/*  487 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_ASCENSIA_DEX_METER");
/*  488 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_ASCENSIA_ELITE_METER");
/*  489 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_ASCENSIA_BREEZE_METER");
/*  490 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_ASCENSIA_CONTOUR_METER");
/*  491 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_ASCENSIA_CONTOUR_USB_METER");
/*  492 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_XTLINKMETER");
/*  493 */     localHashMap.put("wizard.selections.SELECTION_BRAND_ASCENSIA_BAYER", Collections.unmodifiableList(localArrayList));
/*      */ 
/*  495 */     localArrayList = new ArrayList();
/*  496 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_MEDISENSE_XTRA_METER");
/*  497 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_THERASENSE_FREESTYLE_METER");
/*  498 */     localHashMap.put("wizard.selections.SELECTION_GROUP_MEDISENSE_THERASENSE", Collections.unmodifiableList(localArrayList));
/*      */ 
/*  500 */     localArrayList = new ArrayList();
/*  501 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_ROCHE_ACTIVE_METER");
/*  502 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_ROCHE_AVIVA_METER");
/*  503 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_ROCHE_COMPACT_METER");
/*  504 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_ROCHE_COMPACTPLUS_METER");
/*  505 */     localHashMap.put("wizard.selections.SELECTION_BRAND_ROCHE", Collections.unmodifiableList(localArrayList));
/*  506 */     localArrayList = new ArrayList();
/*  507 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_MMLINKMETER");
/*  508 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_MMLOGICMETER");
/*  509 */     localHashMap.put("wizard.selections.SELECTION_BRAND_BD", Collections.unmodifiableList(localArrayList));
/*  510 */     METERS_BY_BRAND = Collections.unmodifiableMap(localHashMap);
/*      */ 
/*  513 */     localArrayList = new ArrayList();
/*  514 */     localArrayList.add("wizard.selections.SELECTION_SERIAL_PORT_AUTO_DETECT");
/*  515 */     localArrayList.add("wizard.selections.SELECTION_SERIAL_PORT_SELECT_PORT");
/*  516 */     VALID_SERIAL_PORT_METHODS = Collections.unmodifiableList(localArrayList);
/*      */   }
/*      */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.WizardSelections
 * JD-Core Version:    0.6.0
 */