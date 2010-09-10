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
/*      */   public static final String KEY_RESOURCE_SELECTION_DEVICE_LIFESCAN_BASIC_METER = "wizard.selections.SELECTION_DEVICE_LIFESCAN_BASIC_METER";
/*      */   public static final String KEY_RESOURCE_SELECTION_DEVICE_LIFESCAN_FASTTAKE_METER = "wizard.selections.SELECTION_DEVICE_LIFESCAN_FASTTAKE_METER";
/*      */   public static final String KEY_RESOURCE_SELECTION_DEVICE_LIFESCAN_PROFILE_METER = "wizard.selections.SELECTION_DEVICE_LIFESCAN_PROFILE_METER";
/*      */   public static final String KEY_RESOURCE_SELECTION_DEVICE_LIFESCAN_SURESTEP_METER = "wizard.selections.SELECTION_DEVICE_LIFESCAN_SURESTEP_METER";
/*      */   public static final String KEY_RESOURCE_SELECTION_DEVICE_LIFESCAN_ULTRA_METER = "wizard.selections.SELECTION_DEVICE_LIFESCAN_ULTRA_METER";
/*      */   public static final String KEY_RESOURCE_SELECTION_DEVICE_LIFESCAN_ULTRASMART_METER = "wizard.selections.SELECTION_DEVICE_LIFESCAN_ULTRASMART_METER";
/*      */   public static final String KEY_RESOURCE_SELECTION_DEVICE_LIFESCAN_ULTRAMINI_METER = "wizard.selections.SELECTION_DEVICE_LIFESCAN_ULTRAMINI_METER";
/*      */   public static final String KEY_RESOURCE_SELECTION_DEVICE_MEDISENSE_XTRA_METER = "wizard.selections.SELECTION_DEVICE_MEDISENSE_XTRA_METER";
/*      */   public static final String KEY_RESOURCE_SELECTION_DEVICE_MM508 = "wizard.selections.SELECTION_DEVICE_MM508";
/*      */   public static final String KEY_RESOURCE_SELECTION_DEVICE_MM511 = "wizard.selections.SELECTION_DEVICE_MM511";
/*      */   public static final String KEY_RESOURCE_SELECTION_DEVICE_MMGUARDIAN3 = "wizard.selections.SELECTION_DEVICE_MMGUARDIAN3";
/*      */   public static final String KEY_RESOURCE_SELECTION_DEVICE_MMLINKMETER = "wizard.selections.SELECTION_DEVICE_MMLINKMETER";
/*      */   public static final String KEY_RESOURCE_SELECTION_DEVICE_MMLOGICMETER = "wizard.selections.SELECTION_DEVICE_MMLOGICMETER";
/*      */   public static final String KEY_RESOURCE_SELECTION_DEVICE_MMPARADIGM2 = "wizard.selections.SELECTION_DEVICE_MMPARADIGM2";
/*      */   public static final String KEY_RESOURCE_SELECTION_DEVICE_THERASENSE_FREESTYLE_METER = "wizard.selections.SELECTION_DEVICE_THERASENSE_FREESTYLE_METER";
/*      */   public static final String KEY_RESOURCE_SELECTION_GROUP_MEDISENSE_THERASENSE = "wizard.selections.SELECTION_GROUP_MEDISENSE_THERASENSE";
/*      */   public static final String KEY_RESOURCE_SELECTION_LINK_DEVICE_COMLINK = "wizard.selections.SELECTION_LINK_DEVICE_COMLINK";
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
/*  511 */   private final ResourceBundle m_resources = DTWApplet.getResourceBundle();
/*      */ 
/*  516 */   final String m_tokenDeviceMMParadigm2X15 = this.m_resources.getString("wizard.selections.TOKEN_DEVICE_MMPARADIGM2_X15");
/*      */ 
/*  522 */   final String m_tokenDeviceMMParadigm2All = this.m_resources.getString("wizard.selections.TOKEN_DEVICE_MMPARADIGM2_ALL");
/*      */ 
/*  528 */   public final String m_selectionDeviceMMLinkmeter = this.m_resources.getString("wizard.selections.SELECTION_DEVICE_MMLINKMETER");
/*      */ 
/*  534 */   public final String m_selectionDeviceMMLogicMeter = this.m_resources.getString("wizard.selections.SELECTION_DEVICE_MMLOGICMETER");
/*      */ 
/*  540 */   public final String m_selectionBrandBD = this.m_resources.getString("wizard.selections.SELECTION_BRAND_BD");
/*      */ 
/*  546 */   public final String m_selectionBrandMinimed = this.m_resources.getString("wizard.selections.SELECTION_BRAND_MINIMED_BD");
/*      */ 
/*  552 */   public final String m_selectionBrandAscensiaBayer = this.m_resources.getString("wizard.selections.SELECTION_BRAND_ASCENSIA_BAYER");
/*      */ 
/*  558 */   public final String m_selectionBrandLifeScan = this.m_resources.getString("wizard.selections.SELECTION_BRAND_LIFESCAN");
/*      */ 
/*  564 */   public final String m_selectionBrandRoche = this.m_resources.getString("wizard.selections.SELECTION_BRAND_ROCHE");
/*      */ 
/*  570 */   public final String m_selectionGroupMedisenseTherasense = this.m_resources.getString("wizard.selections.SELECTION_GROUP_MEDISENSE_THERASENSE");
/*      */ 
/*  576 */   public final String m_selectionDeviceLifeScanProfileMeter = this.m_resources.getString("wizard.selections.SELECTION_DEVICE_LIFESCAN_PROFILE_METER");
/*      */ 
/*  582 */   public final String m_selectionDeviceLifeScanUltraMeter = this.m_resources.getString("wizard.selections.SELECTION_DEVICE_LIFESCAN_ULTRA_METER");
/*      */ 
/*  588 */   public final String m_selectionDeviceAscensiaContourLink = this.m_resources.getString("wizard.selections.SELECTION_DEVICE_ASCENSIA_CONTOUR_LINK");
/*      */ 
/*  594 */   public final String m_selectionDeviceLifeScanUltraLink = this.m_resources.getString("wizard.selections.SELECTION_DEVICE_LIFESCAN_ULTRA_LINK");
/*      */ 
/*  600 */   public final String m_selectionDeviceLifeScanUltraSmartMeter = this.m_resources.getString("wizard.selections.SELECTION_DEVICE_LIFESCAN_ULTRASMART_METER");
/*      */ 
/*  606 */   public final String m_selectionDeviceLifeScanUltraMiniMeter = this.m_resources.getString("wizard.selections.SELECTION_DEVICE_LIFESCAN_ULTRAMINI_METER");
/*      */ 
/*  612 */   public final String m_selectionDeviceLifeScanBasicMeter = this.m_resources.getString("wizard.selections.SELECTION_DEVICE_LIFESCAN_BASIC_METER");
/*      */ 
/*  618 */   public final String m_selectionDeviceLifeScanFastTakeMeter = this.m_resources.getString("wizard.selections.SELECTION_DEVICE_LIFESCAN_FASTTAKE_METER");
/*      */ 
/*  624 */   public final String m_selectionDeviceLifeScanSureStepMeter = this.m_resources.getString("wizard.selections.SELECTION_DEVICE_LIFESCAN_SURESTEP_METER");
/*      */ 
/*  630 */   final String m_tokenDeviceTheraSenseFreeStyleMeter = this.m_resources.getString("wizard.selections.TOKEN_DEVICE_THERASENSE_FREESTYLE_METER");
/*      */ 
/*  636 */   public final String m_selectionDeviceRocheActiveMeter = this.m_resources.getString("wizard.selections.SELECTION_DEVICE_ROCHE_ACTIVE_METER");
/*      */ 
/*  642 */   public final String m_selectionDeviceRocheAvivaMeter = this.m_resources.getString("wizard.selections.SELECTION_DEVICE_ROCHE_AVIVA_METER");
/*      */ 
/*  648 */   public final String m_selectionDeviceRocheCompactMeter = this.m_resources.getString("wizard.selections.SELECTION_DEVICE_ROCHE_COMPACT_METER");
/*      */ 
/*  654 */   public final String m_selectionDeviceRocheCompactPlusMeter = this.m_resources.getString("wizard.selections.SELECTION_DEVICE_ROCHE_COMPACTPLUS_METER");
/*      */ 
/*  660 */   public final String m_selectionLinkDeviceParadigmLink = this.m_resources.getString("wizard.selections.SELECTION_LINK_DEVICE_PARADIGMLINK");
/*      */ 
/*  666 */   public final String m_selectionSerialPortAutoDetect = this.m_resources.getString("wizard.selections.SELECTION_SERIAL_PORT_AUTO_DETECT");
/*      */ 
/*  672 */   public final String m_selectionSerialPortSelectPort = this.m_resources.getString("wizard.selections.SELECTION_SERIAL_PORT_SELECT_PORT");
/*      */   private final PreferencesHelper m_preferencesHelper;
/*      */   private DeviceID m_currentPumpID;
/*      */   private DeviceID m_currentMeterID;
/*      */   private DeviceID m_currentCGMID;
/*      */ 
/*      */   public WizardSelections(LogWriter paramLogWriter)
/*      */   {
/*  704 */     Contract.preNonNull(paramLogWriter);
/*  705 */     this.m_preferencesHelper = new PreferencesHelper(paramLogWriter);
/*      */   }
/*      */ 
/*      */   public String toString()
/*      */   {
/*  716 */     PropertyWriter localPropertyWriter = new PropertyWriter();
/*  717 */     localPropertyWriter.add("isUserInputCompleted", isUserInputCompleted());
/*  718 */     localPropertyWriter.add("currentPumpID", this.m_currentPumpID);
/*  719 */     localPropertyWriter.add("currentMeterID", this.m_currentMeterID);
/*  720 */     localPropertyWriter.add("currentCGMID", this.m_currentCGMID);
/*  721 */     localPropertyWriter.add("serialPortName", getSerialPortName());
/*  722 */     localPropertyWriter.add("serialPort", getSerialPort());
/*  723 */     localPropertyWriter.add("meterDevice", getMeterDevice());
/*  724 */     localPropertyWriter.add("pumpDevice", getPumpDevice());
/*  725 */     localPropertyWriter.add("linkDevice", getLinkDevice());
/*  726 */     localPropertyWriter.add("device", getDeviceType());
/*  727 */     localPropertyWriter.add("connectionType", getConnectionType());
/*  728 */     return localPropertyWriter.toString();
/*      */   }
/*      */ 
/*      */   public void flush()
/*      */   {
/*  735 */     this.m_preferencesHelper.flushSystemPreferences();
/*      */   }
/*      */ 
/*      */   public void setConnectionType(String paramString)
/*      */   {
/*  745 */     Contract.pre(VALID_CONNECTION_TYPES.contains(paramString));
/*  746 */     this.m_preferencesHelper.setSystemPreference("wizard2.connectiontype", paramString);
/*      */   }
/*      */ 
/*      */   public void setLinkDevice(String paramString)
/*      */   {
/*  756 */     Contract.pre(VALID_LINK_DEVICES.contains(paramString));
/*  757 */     this.m_preferencesHelper.setSystemPreference("wizard2.linkdevice", paramString);
/*      */   }
/*      */ 
/*      */   public void setPumpDevice(String paramString)
/*      */   {
/*  767 */     Contract.pre(VALID_PUMP_DEVICES.contains(paramString));
/*  768 */     this.m_preferencesHelper.setSystemPreference("wizard2.pumpdevice", paramString);
/*      */   }
/*      */ 
/*      */   public void setDeviceType(String paramString)
/*      */   {
/*  779 */     Contract.pre(VALID_DEVICE_TYPES.contains(paramString));
/*  780 */     this.m_preferencesHelper.setSystemPreference("wizard2.devicetype", paramString);
/*      */   }
/*      */ 
/*      */   public void setPumpSerialNumber(String paramString)
/*      */   {
/*  790 */     Contract.preString(paramString);
/*      */ 
/*  792 */     String str = Wizard.mapToServerClassName(getPumpDevice());
/*  793 */     this.m_currentPumpID = new DeviceID(str, paramString);
/*      */   }
/*      */ 
/*      */   public void clearPumpSerialNumber()
/*      */   {
/*  800 */     this.m_currentPumpID = null;
/*      */   }
/*      */ 
/*      */   public void setCGMSerialNumber(String paramString)
/*      */   {
/*  810 */     Contract.preString(paramString);
/*      */ 
/*  812 */     String str = Wizard.mapToServerClassName(getCGMDevice());
/*  813 */     this.m_currentCGMID = new DeviceID(str, paramString);
/*      */   }
/*      */ 
/*      */   public void clearCGMSerialNumber()
/*      */   {
/*  820 */     this.m_currentCGMID = null;
/*      */   }
/*      */ 
/*      */   public void setMeterSerialNumber(String paramString)
/*      */   {
/*  830 */     Contract.preString(paramString);
/*      */ 
/*  832 */     String str = Wizard.mapToServerClassName(getMeterDevice());
/*  833 */     this.m_currentMeterID = new DeviceID(str, paramString);
/*      */   }
/*      */ 
/*      */   public void setMeterDevice(String paramString)
/*      */   {
/*  843 */     Contract.pre(VALID_METER_DEVICES.contains(paramString));
/*  844 */     this.m_preferencesHelper.setSystemPreference("wizard2.meterdevice", paramString);
/*      */   }
/*      */ 
/*      */   public void setMeterBrand(String paramString)
/*      */   {
/*  854 */     Contract.pre(VALID_METER_BRANDS.contains(paramString));
/*  855 */     this.m_preferencesHelper.setSystemPreference("wizard2.meterbrand", paramString);
/*      */   }
/*      */ 
/*      */   public void setCGMDevice(String paramString)
/*      */   {
/*  865 */     Contract.pre(VALID_CGM_DEVICES.contains(paramString));
/*  866 */     this.m_preferencesHelper.setSystemPreference("wizard2.cgmdevice", paramString);
/*      */   }
/*      */ 
/*      */   public void setSerialPort(String paramString)
/*      */   {
/*  876 */     Contract.pre(VALID_SERIAL_PORT_METHODS.contains(paramString));
/*  877 */     this.m_preferencesHelper.setSystemPreference("wizard2.serialportmethod", paramString);
/*      */   }
/*      */ 
/*      */   public void setSerialPortName(String paramString)
/*      */   {
/*  889 */     Contract.preString(paramString);
/*  890 */     this.m_preferencesHelper.setSystemPreference("wizard2.serialportname", paramString);
/*      */   }
/*      */ 
/*      */   public void setUserInputCompleted()
/*      */   {
/*  900 */     this.m_preferencesHelper.setSystemPreference(getUserInputCompletedKey(), true);
/*      */   }
/*      */ 
/*      */   public String getConnectionType()
/*      */   {
/*  913 */     String str = this.m_preferencesHelper.getSystemPreference("wizard2.connectiontype", VALID_CONNECTION_TYPES, "wizard.selections.SELECTION_CONN_TYPE_USB");
/*      */ 
/*  915 */     Contract.post(VALID_CONNECTION_TYPES.contains(str));
/*  916 */     return str;
/*      */   }
/*      */ 
/*      */   public String getDeviceType()
/*      */   {
/*  928 */     String str = this.m_preferencesHelper.getSystemPreference("wizard2.devicetype", VALID_DEVICE_TYPES, "wizard.selections.SELECTION_DEVICE_PUMP");
/*      */ 
/*  930 */     Contract.post(VALID_DEVICE_TYPES.contains(str));
/*  931 */     return str;
/*      */   }
/*      */ 
/*      */   public String getLinkDevice()
/*      */   {
/*  947 */     String str = this.m_preferencesHelper.getSystemPreference("wizard2.linkdevice", VALID_LINK_DEVICES, "wizard.selections.SELECTION_LINK_DEVICE_COMLINKUSB");
/*      */ 
/*  949 */     Contract.post(VALID_LINK_DEVICES.contains(str));
/*  950 */     return str;
/*      */   }
/*      */ 
/*      */   public String getPumpDevice()
/*      */   {
/*  962 */     String str = this.m_preferencesHelper.getSystemPreference("wizard2.pumpdevice", VALID_PUMP_DEVICES, "wizard.selections.SELECTION_DEVICE_MMPARADIGM2");
/*      */ 
/*  964 */     Contract.post(VALID_PUMP_DEVICES.contains(str));
/*  965 */     return str;
/*      */   }
/*      */ 
/*      */   public String getMeterDevice()
/*      */   {
/*  977 */     String str = this.m_preferencesHelper.getSystemPreference("wizard2.meterdevice", VALID_METER_DEVICES, "wizard.selections.SELECTION_DEVICE_LIFESCAN_ULTRA_METER");
/*      */ 
/*  979 */     Contract.post(VALID_METER_DEVICES.contains(str));
/*  980 */     return str;
/*      */   }
/*      */ 
/*      */   public String getCGMDevice()
/*      */   {
/*  992 */     String str = this.m_preferencesHelper.getSystemPreference("wizard2.cgmdevice", VALID_CGM_DEVICES, "wizard.selections.SELECTION_DEVICE_MMGUARDIAN3");
/*      */ 
/*  994 */     Contract.post(VALID_CGM_DEVICES.contains(str));
/*  995 */     return str;
/*      */   }
/*      */ 
/*      */   public String getMeterBrand()
/*      */   {
/* 1007 */     String str = this.m_preferencesHelper.getSystemPreference("wizard2.meterbrand", VALID_METER_BRANDS, "wizard.selections.SELECTION_BRAND_MINIMED_BD");
/*      */ 
/* 1009 */     Contract.post(VALID_METER_BRANDS.contains(str));
/* 1010 */     return str;
/*      */   }
/*      */ 
/*      */   public String getSerialPort()
/*      */   {
/* 1022 */     String str = this.m_preferencesHelper.getSystemPreference("wizard2.serialportmethod", VALID_SERIAL_PORT_METHODS, "wizard.selections.SELECTION_SERIAL_PORT_AUTO_DETECT");
/*      */ 
/* 1024 */     Contract.post(VALID_SERIAL_PORT_METHODS.contains(str));
/* 1025 */     return str;
/*      */   }
/*      */ 
/*      */   public String getSerialPortName()
/*      */   {
/* 1037 */     String str = this.m_preferencesHelper.getSystemPreference("wizard2.serialportname", "wizard.selections.SELECTION_SERIAL_PORT_NAMED");
/*      */ 
/* 1039 */     Contract.postString(str);
/* 1040 */     return str;
/*      */   }
/*      */ 
/*      */   public String getDeviceSerialNumber()
/*      */   {
/*      */     String str;
/* 1050 */     if (getDeviceType().equals("wizard.selections.SELECTION_DEVICE_PUMP")) {
/* 1051 */       str = getPumpSerialNumber();
/* 1052 */     } else if (getDeviceType().equals("wizard.selections.SELECTION_DEVICE_METER")) {
/* 1053 */       str = getMeterSerialNumber();
/* 1054 */     } else if (getDeviceType().equals("wizard.selections.SELECTION_DEVICE_CGM")) {
/* 1055 */       str = getCGMSerialNumber();
/*      */     } else {
/* 1057 */       str = null;
/* 1058 */       Contract.unreachable();
/*      */     }
/* 1060 */     return str;
/*      */   }
/*      */ 
/*      */   public String getDeviceSelection()
/*      */   {
/*      */     String str;
/* 1070 */     if (getDeviceType().equals("wizard.selections.SELECTION_DEVICE_PUMP")) {
/* 1071 */       str = getPumpDevice();
/* 1072 */     } else if (getDeviceType().equals("wizard.selections.SELECTION_DEVICE_METER")) {
/* 1073 */       str = getMeterDevice();
/* 1074 */     } else if (getDeviceType().equals("wizard.selections.SELECTION_DEVICE_CGM")) {
/* 1075 */       str = getCGMDevice();
/*      */     } else {
/* 1077 */       str = null;
/* 1078 */       Contract.unreachable();
/*      */     }
/* 1080 */     return str;
/*      */   }
/*      */ 
/*      */   public String getPumpSerialNumber()
/*      */   {
/* 1089 */     return this.m_currentPumpID != null ? this.m_currentPumpID.getSerialNumber() : null;
/*      */   }
/*      */ 
/*      */   public String getMeterSerialNumber()
/*      */   {
/* 1098 */     return this.m_currentMeterID != null ? this.m_currentMeterID.getSerialNumber() : null;
/*      */   }
/*      */ 
/*      */   public String getCGMSerialNumber()
/*      */   {
/* 1107 */     return this.m_currentCGMID != null ? this.m_currentCGMID.getSerialNumber() : null;
/*      */   }
/*      */ 
/*      */   public boolean isDeviceSelectionAPump()
/*      */   {
/* 1116 */     return VALID_PUMP_DEVICES.contains(getDeviceSelection());
/*      */   }
/*      */ 
/*      */   public boolean isDeviceSelectionACGM()
/*      */   {
/* 1125 */     return VALID_CGM_DEVICES.contains(getDeviceSelection());
/*      */   }
/*      */ 
/*      */   public boolean isDeviceSelectionAMeter()
/*      */   {
/* 1134 */     return VALID_METER_DEVICES.contains(getDeviceSelection());
/*      */   }
/*      */ 
/*      */   public void setCurrentPumpID(DeviceID paramDeviceID)
/*      */   {
/* 1143 */     this.m_currentPumpID = paramDeviceID;
/*      */   }
/*      */ 
/*      */   public void setCurrentMeterID(DeviceID paramDeviceID)
/*      */   {
/* 1152 */     this.m_currentMeterID = paramDeviceID;
/*      */   }
/*      */ 
/*      */   public PreferencesHelper getPreferencesHelper()
/*      */   {
/* 1160 */     return this.m_preferencesHelper;
/*      */   }
/*      */ 
/*      */   public void setCurrentCGMID(DeviceID paramDeviceID)
/*      */   {
/* 1169 */     this.m_currentCGMID = paramDeviceID;
/*      */   }
/*      */ 
/*      */   boolean isUserInputCompleted()
/*      */   {
/* 1182 */     return this.m_preferencesHelper.getSystemPreference(getUserInputCompletedKey(), false);
/*      */   }
/*      */ 
/*      */   boolean isMeterBrandAndModelMismatch(String paramString1, String paramString2)
/*      */   {
/* 1196 */     Contract.pre(VALID_METER_BRANDS.contains(paramString1));
/* 1197 */     Contract.pre(VALID_METER_DEVICES.contains(paramString2));
/* 1198 */     List localList = (List)METERS_BY_BRAND.get(paramString1);
/* 1199 */     return !localList.contains(paramString2);
/*      */   }
/*      */ 
/*      */   boolean isDeviceTypeSet()
/*      */   {
/* 1208 */     return this.m_preferencesHelper.isSystemPreferenceSet("wizard2.devicetype");
/*      */   }
/*      */ 
/*      */   boolean isPumpDeviceSet()
/*      */   {
/* 1217 */     return this.m_preferencesHelper.isSystemPreferenceSet("wizard2.pumpdevice");
/*      */   }
/*      */ 
/*      */   boolean isMeterDeviceSet()
/*      */   {
/* 1226 */     return this.m_preferencesHelper.isSystemPreferenceSet("wizard2.meterdevice");
/*      */   }
/*      */ 
/*      */   boolean isCGMDeviceSet()
/*      */   {
/* 1235 */     return this.m_preferencesHelper.isSystemPreferenceSet("wizard2.cgmdevice");
/*      */   }
/*      */ 
/*      */   boolean isMeterBrandSet()
/*      */   {
/* 1244 */     return this.m_preferencesHelper.isSystemPreferenceSet("wizard2.meterbrand");
/*      */   }
/*      */ 
/*      */   boolean isLinkDeviceSet()
/*      */   {
/* 1253 */     return this.m_preferencesHelper.isSystemPreferenceSet("wizard2.linkdevice");
/*      */   }
/*      */ 
/*      */   boolean isConnectionTypeSet()
/*      */   {
/* 1262 */     return this.m_preferencesHelper.isSystemPreferenceSet("wizard2.connectiontype");
/*      */   }
/*      */ 
/*      */   boolean isSerialPortMethodSet()
/*      */   {
/* 1271 */     return this.m_preferencesHelper.isSystemPreferenceSet("wizard2.serialportmethod");
/*      */   }
/*      */ 
/*      */   void clear()
/*      */   {
/* 1278 */     this.m_preferencesHelper.clear();
/*      */   }
/*      */ 
/*      */   private String getUserInputCompletedKey()
/*      */   {
/* 1289 */     String str = "wizard2.finish-" + getDeviceSelection();
/* 1290 */     Contract.post(str.length() <= 80);
/* 1291 */     return str;
/*      */   }
/*      */ 
/*      */   static
/*      */   {
/*  398 */     ArrayList localArrayList = new ArrayList();
/*  399 */     localArrayList.add("wizard.selections.SELECTION_CONN_TYPE_SERIAL");
/*  400 */     localArrayList.add("wizard.selections.SELECTION_CONN_TYPE_USB");
/*  401 */     VALID_CONNECTION_TYPES = Collections.unmodifiableList(localArrayList);
/*      */ 
/*  404 */     localArrayList = new ArrayList();
/*  405 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_PUMP");
/*  406 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_METER");
/*  407 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_CGM");
/*  408 */     VALID_DEVICE_TYPES = Collections.unmodifiableList(localArrayList);
/*      */ 
/*  411 */     localArrayList = new ArrayList();
/*  412 */     localArrayList.add("wizard.selections.SELECTION_LINK_DEVICE_PARADIGMLINK");
/*  413 */     localArrayList.add("wizard.selections.SELECTION_LINK_DEVICE_COMLINKUSB");
/*  414 */     localArrayList.add("wizard.selections.SELECTION_LINK_DEVICE_COMLINK");
/*  415 */     VALID_LINK_DEVICES = Collections.unmodifiableList(localArrayList);
/*      */ 
/*  418 */     localArrayList = new ArrayList();
/*  419 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_MMPARADIGM2");
/*  420 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_MM511");
/*  421 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_MM508");
/*  422 */     VALID_PUMP_DEVICES = Collections.unmodifiableList(localArrayList);
/*      */ 
/*  425 */     localArrayList = new ArrayList();
/*  426 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_MMLINKMETER");
/*  427 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_MMLOGICMETER");
/*  428 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_LIFESCAN_PROFILE_METER");
/*  429 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_LIFESCAN_ULTRA_METER");
/*  430 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_LIFESCAN_ULTRASMART_METER");
/*  431 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_LIFESCAN_ULTRAMINI_METER");
/*  432 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_LIFESCAN_BASIC_METER");
/*  433 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_LIFESCAN_FASTTAKE_METER");
/*  434 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_LIFESCAN_SURESTEP_METER");
/*  435 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_ASCENSIA_DEX_METER");
/*  436 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_ASCENSIA_ELITE_METER");
/*  437 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_ASCENSIA_BREEZE_METER");
/*  438 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_ASCENSIA_CONTOUR_METER");
/*  439 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_MEDISENSE_XTRA_METER");
/*  440 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_THERASENSE_FREESTYLE_METER");
/*  441 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_ROCHE_ACTIVE_METER");
/*  442 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_ROCHE_AVIVA_METER");
/*  443 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_ROCHE_COMPACT_METER");
/*  444 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_ROCHE_COMPACTPLUS_METER");
/*  445 */     VALID_METER_DEVICES = Collections.unmodifiableList(localArrayList);
/*      */ 
/*  448 */     localArrayList = new ArrayList();
/*  449 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_MMGUARDIAN3");
/*  450 */     VALID_CGM_DEVICES = Collections.unmodifiableList(localArrayList);
/*      */ 
/*  453 */     localArrayList = new ArrayList();
/*  454 */     localArrayList.add("wizard.selections.SELECTION_BRAND_MINIMED_BD");
/*  455 */     localArrayList.add("wizard.selections.SELECTION_BRAND_ASCENSIA_BAYER");
/*  456 */     localArrayList.add("wizard.selections.SELECTION_BRAND_LIFESCAN");
/*  457 */     localArrayList.add("wizard.selections.SELECTION_GROUP_MEDISENSE_THERASENSE");
/*  458 */     localArrayList.add("wizard.selections.SELECTION_BRAND_ROCHE");
/*  459 */     localArrayList.add("wizard.selections.SELECTION_BRAND_BD");
/*  460 */     VALID_METER_BRANDS = Collections.unmodifiableList(localArrayList);
/*      */ 
/*  463 */     HashMap localHashMap = new HashMap();
/*  464 */     localArrayList = new ArrayList();
/*  465 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_MMLINKMETER");
/*  466 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_LIFESCAN_ULTRA_METER");
/*  467 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_ASCENSIA_CONTOUR_METER");
/*  468 */     localHashMap.put("wizard.selections.SELECTION_BRAND_MINIMED_BD", Collections.unmodifiableList(localArrayList));
/*  469 */     localArrayList = new ArrayList();
/*  470 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_LIFESCAN_PROFILE_METER");
/*  471 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_LIFESCAN_ULTRA_METER");
/*  472 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_LIFESCAN_ULTRASMART_METER");
/*  473 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_LIFESCAN_ULTRAMINI_METER");
/*  474 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_LIFESCAN_BASIC_METER");
/*  475 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_LIFESCAN_FASTTAKE_METER");
/*  476 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_LIFESCAN_SURESTEP_METER");
/*  477 */     localHashMap.put("wizard.selections.SELECTION_BRAND_LIFESCAN", Collections.unmodifiableList(localArrayList));
/*  478 */     localArrayList = new ArrayList();
/*  479 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_ASCENSIA_DEX_METER");
/*  480 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_ASCENSIA_ELITE_METER");
/*  481 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_ASCENSIA_BREEZE_METER");
/*  482 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_ASCENSIA_CONTOUR_METER");
/*  483 */     localHashMap.put("wizard.selections.SELECTION_BRAND_ASCENSIA_BAYER", Collections.unmodifiableList(localArrayList));
/*      */ 
/*  485 */     localArrayList = new ArrayList();
/*  486 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_MEDISENSE_XTRA_METER");
/*  487 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_THERASENSE_FREESTYLE_METER");
/*  488 */     localHashMap.put("wizard.selections.SELECTION_GROUP_MEDISENSE_THERASENSE", Collections.unmodifiableList(localArrayList));
/*      */ 
/*  490 */     localArrayList = new ArrayList();
/*  491 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_ROCHE_ACTIVE_METER");
/*  492 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_ROCHE_AVIVA_METER");
/*  493 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_ROCHE_COMPACT_METER");
/*  494 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_ROCHE_COMPACTPLUS_METER");
/*  495 */     localHashMap.put("wizard.selections.SELECTION_BRAND_ROCHE", Collections.unmodifiableList(localArrayList));
/*  496 */     localArrayList = new ArrayList();
/*  497 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_MMLINKMETER");
/*  498 */     localArrayList.add("wizard.selections.SELECTION_DEVICE_MMLOGICMETER");
/*  499 */     localHashMap.put("wizard.selections.SELECTION_BRAND_BD", Collections.unmodifiableList(localArrayList));
/*  500 */     METERS_BY_BRAND = Collections.unmodifiableMap(localHashMap);
/*      */ 
/*  503 */     localArrayList = new ArrayList();
/*  504 */     localArrayList.add("wizard.selections.SELECTION_SERIAL_PORT_AUTO_DETECT");
/*  505 */     localArrayList.add("wizard.selections.SELECTION_SERIAL_PORT_SELECT_PORT");
/*  506 */     VALID_SERIAL_PORT_METHODS = Collections.unmodifiableList(localArrayList);
/*      */   }
/*      */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.WizardSelections
 * JD-Core Version:    0.6.0
 */