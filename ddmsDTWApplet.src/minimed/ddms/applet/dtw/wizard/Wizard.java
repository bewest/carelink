/*      */ package minimed.ddms.applet.dtw.wizard;
/*      */ 
/*      */ import java.awt.Container;
/*      */ import java.awt.event.ActionEvent;
/*      */ import java.awt.event.ActionListener;
/*      */ import java.io.File;
/*      */ import java.io.IOException;
/*      */ import java.lang.reflect.Constructor;
/*      */ import java.lang.reflect.InvocationTargetException;
/*      */ import java.util.Collections;
/*      */ import java.util.HashMap;
/*      */ import java.util.Map;
/*      */ import javax.swing.Timer;
/*      */ import minimed.ddms.A.AA;
/*      */ import minimed.ddms.applet.dtw.CaptureResult;
/*      */ import minimed.ddms.applet.dtw.DTWApplet;
/*      */ import minimed.ddms.applet.dtw.LogWriter;
/*      */ import minimed.ddms.applet.dtw.PropertyWriter;
/*      */ import minimed.ddms.applet.dtw.components.NumericTextField;
/*      */ import minimed.ddms.applet.dtw.components.StepLayout;
/*      */ import minimed.util.Contract;
/*      */ 
/*      */ public final class Wizard
/*      */ {
/*      */   private static final String SERVER_CLASS_NAME_PARADIGM2 = "MiniMedParadigm2";
/*      */   private static final String SERVER_CLASS_NAME_753 = "MiniMed753";
/*      */   private static final String SERVER_CLASS_NAME_553 = "MiniMed553";
/*      */   private static final String SERVER_CLASS_NAME_754 = "MiniMed754";
/*      */   private static final String SERVER_CLASS_NAME_554 = "MiniMed554";
/*      */   private static final String SERVER_CLASS_NAME_723 = "MiniMed723";
/*      */   private static final String SERVER_CLASS_NAME_523 = "MiniMed523";
/*      */   private static final String SERVER_CLASS_NAME_723K = "MiniMed723K";
/*      */   private static final String SERVER_CLASS_NAME_523K = "MiniMed523K";
/*      */   private static final String SERVER_CLASS_NAME_722 = "MiniMed722";
/*      */   private static final String SERVER_CLASS_NAME_522 = "MiniMed522";
/*      */   private static final String SERVER_CLASS_NAME_722K = "MiniMed722K";
/*      */   private static final String SERVER_CLASS_NAME_522K = "MiniMed522K";
/*      */   private static final String SERVER_CLASS_NAME_715 = "MiniMed715";
/*      */   private static final String SERVER_CLASS_NAME_515 = "MiniMed515";
/*      */   private static final String SERVER_CLASS_NAME_712 = "MiniMed712";
/*      */   private static final String SERVER_CLASS_NAME_512 = "MiniMed512";
/*      */   private static final String SERVER_CLASS_NAME_GUARDIAN3 = "MiniMedGuardian3";
/*      */   private static final String SERVER_CLASS_NAME_GUARDIAN3K = "MiniMedGuardian3K";
/*      */   private static final String SERVER_CLASS_NAME_PARADIGMLINK = "BDParadigmLink";
/*      */   private static final String SERVER_CLASS_NAME_LOGIC = "BDLogic";
/*      */   private static final String SERVER_CLASS_NAME_BAYER_DEX = "BayerDex";
/*      */   private static final String SERVER_CLASS_NAME_BAYER_ELITE_XL = "BayerEliteXL";
/*      */   private static final String SERVER_CLASS_NAME_BAYER_BREEZE = "BayerBreeze";
/*      */   private static final String SERVER_CLASS_NAME_BAYER_CONTOUR = "BayerContour";
/*      */   private static final String SERVER_CLASS_NAME_BAYER_CONTOUR_USB = "BayerContourUSB";
/*      */   private static final String SERVER_CLASS_NAME_BAYER_CONTOUR_XT_LINK = "BayerContourXTLink";
/*      */   private static final String SERVER_CLASS_NAME_BAYER_CONTOURLINK = "BayerContourLink";
/*      */   private static final String SERVER_CLASS_NAME_LIFESCAN_FASTTAKE = "LifescanFastTake";
/*      */   private static final String SERVER_CLASS_NAME_LIFESCAN_OT_BASIC = "LifescanOTBasic";
/*      */   private static final String SERVER_CLASS_NAME_LIFESCAN_OT_PROFILE = "LifescanOTProfile";
/*      */   private static final String SERVER_CLASS_NAME_LIFESCAN_OT_ULTRA = "LifescanOTUltra";
/*      */   private static final String SERVER_CLASS_NAME_LIFESCAN_OT_ULTRA2 = "LifescanOTUltra2";
/*      */   private static final String SERVER_CLASS_NAME_LIFESCAN_OT_ULTRALINK = "LifescanOTUltraLink";
/*      */   private static final String SERVER_CLASS_NAME_LIFESCAN_OT_ULTRAMINI = "LifescanOTUltraMini";
/*      */   private static final String SERVER_CLASS_NAME_LIFESCAN_OT_ULTRASMART = "LifescanOTUltraSmart";
/*      */   private static final String SERVER_CLASS_NAME_LIFESCAN_SURESTEP = "LifescanSureStep";
/*      */   private static final String SERVER_CLASS_NAME_MEDISENSE_XTRA = "MedisenseXtra";
/*      */   private static final String SERVER_CLASS_NAME_THERASENSE_FREESTYLE = "TherasenseFreestyle";
/*      */   private static final String SERVER_CLASS_NAME_ROCHE_ACTIVE = "RocheActive";
/*      */   private static final String SERVER_CLASS_NAME_ROCHE_AVIVA = "RocheAviva";
/*      */   private static final String SERVER_CLASS_NAME_ROCHE_COMPACT = "RocheCompact";
/*      */   private static final String SERVER_CLASS_NAME_ROCHE_COMPACTPLUS = "RocheCompactPlus";
/*      */   private static final Map SELECTIONS_ID_MAP;
/*      */   private static final Map SELECTIONS_CLASSNAME_MAP;
/*  272 */   private static final Class[] STEP_CONSTRUCTOR_ARG_TYPES = { Wizard.class };
/*      */   public static final boolean ALLOW_CARELINK_USB = true;
/*      */   private static final int COMLINKUSB_TIMER_UPDATE = 2000;
/*      */   private static final String COMLINKUSB_CONNECT_SUCCESS_INDICATOR = "connect-success.txt";
/*      */   private final Object[] m_stepConstructorArgs;
/*      */   private final StepLayout m_stepLayout;
/*      */   private final WizardConfig m_config;
/*      */   private boolean m_stopped;
/*      */   private String m_failureReason;
/*      */   private boolean m_enabledUnloadMessage;
/*      */   private Timer m_comLinkUSBMonitor;
/*  331 */   private final CaptureResult m_captureResult = new CaptureResult();
/*      */ 
/*      */   public Wizard(WizardConfig paramWizardConfig)
/*      */   {
/*  463 */     Contract.preNonNull(paramWizardConfig);
/*  464 */     this.m_config = paramWizardConfig;
/*  465 */     this.m_stepLayout = new StepLayout();
/*  466 */     Container localContainer = this.m_config.getContentPane();
/*  467 */     localContainer.setLayout(this.m_stepLayout);
/*  468 */     this.m_stepConstructorArgs = new Object[] { this };
/*      */   }
/*      */ 
/*      */   public String toString()
/*      */   {
/*  479 */     PropertyWriter localPropertyWriter = new PropertyWriter();
/*  480 */     localPropertyWriter.add("config", this.m_config);
/*  481 */     localPropertyWriter.add("stopped", isStopped());
/*  482 */     localPropertyWriter.add("failureReason", getFailureReason());
/*  483 */     localPropertyWriter.add("stepLayout", this.m_stepLayout);
/*  484 */     return localPropertyWriter.toString();
/*      */   }
/*      */ 
/*      */   public void start()
/*      */   {
/*  491 */     Class localClass = this.m_config.getWizardStepProvider().getStartStep(this);
/*  492 */     showNextStep(localClass);
/*      */   }
/*      */ 
/*      */   public void stop()
/*      */   {
/*  499 */     this.m_config.getLogWriter().logInfo("stop wizard execution requested");
/*  500 */     stopComLinkUSBMonitor();
/*      */ 
/*  502 */     if (!isStopped()) {
/*  503 */       setStopped();
/*      */ 
/*  505 */       WizardStep localWizardStep = (WizardStep)this.m_stepLayout.getCurrentStep();
/*  506 */       if (localWizardStep != null)
/*  507 */         localWizardStep.appletStopped();
/*      */     }
/*      */   }
/*      */ 
/*      */   public WizardConfig getConfig()
/*      */   {
/*  519 */     WizardConfig localWizardConfig = this.m_config;
/*  520 */     Contract.postNonNull(localWizardConfig);
/*  521 */     return localWizardConfig;
/*      */   }
/*      */ 
/*      */   boolean isComLinkUSB(String paramString)
/*      */   {
/*  531 */     return "wizard.selections.SELECTION_LINK_DEVICE_COMLINKUSB".equals(paramString);
/*      */   }
/*      */ 
/*      */   boolean isXTLinkUSB(String paramString)
/*      */   {
/*  541 */     return "wizard.selections.SELECTION_LINK_DEVICE_XTLINKUSB".equals(paramString);
/*      */   }
/*      */ 
/*      */   private static void uniAssociate(HashMap paramHashMap, Object paramObject1, Object paramObject2)
/*      */   {
/*  553 */     Object localObject = paramHashMap.put(paramObject1, paramObject2);
/*  554 */     Contract.pre(localObject == null);
/*      */   }
/*      */ 
/*      */   private static void biAssociate(HashMap paramHashMap, Object paramObject1, Object paramObject2)
/*      */   {
/*  568 */     uniAssociate(paramHashMap, paramObject1, paramObject2);
/*  569 */     Object localObject = paramHashMap.put(paramObject2, paramObject1);
/*  570 */     Contract.pre(localObject == null);
/*      */   }
/*      */ 
/*      */   private synchronized void setStopped()
/*      */   {
/*  577 */     this.m_stopped = true;
/*      */   }
/*      */ 
/*      */   public synchronized boolean isStopped()
/*      */   {
/*  586 */     return this.m_stopped;
/*      */   }
/*      */ 
/*      */   public synchronized void setFailureReason(String paramString)
/*      */   {
/*  596 */     Contract.preString(paramString);
/*  597 */     this.m_failureReason = paramString;
/*      */   }
/*      */ 
/*      */   public synchronized String getFailureReason()
/*      */   {
/*  606 */     return this.m_failureReason;
/*      */   }
/*      */ 
/*      */   public void enableVerifySuspendOffMessage()
/*      */   {
/*  614 */     if (!this.m_enabledUnloadMessage) {
/*  615 */       DTWApplet localDTWApplet = getConfig().getApplet();
/*  616 */       localDTWApplet.enableUnloadMessage(true);
/*  617 */       this.m_enabledUnloadMessage = true;
/*      */     }
/*      */   }
/*      */ 
/*      */   public boolean isSerialDriverInstallNeeded()
/*      */   {
/*  627 */     return isDriverInstallNeeded(this.m_config.getSerialConfig());
/*      */   }
/*      */ 
/*      */   public boolean isComLink2USBDriverInstallNeeded()
/*      */   {
/*  636 */     return isDriverInstallNeeded(this.m_config.getComLink2Config());
/*      */   }
/*      */ 
/*      */   public boolean isXTLink2USBDriverInstallNeeded()
/*      */   {
/*  645 */     return isDriverInstallNeeded(this.m_config.getBayerUSBConfig());
/*      */   }
/*      */ 
/*      */   public boolean hasComLink2EverBeenDetected()
/*      */   {
/*  656 */     File localFile = new File(getConfig().getComLink2Config().getInstallDriverDir() + File.separator + "connect-success.txt");
/*      */ 
/*  658 */     return (localFile.exists()) && (localFile.isFile());
/*      */   }
/*      */ 
/*      */   public void writeComLink2EverBeenDetectedIndicator()
/*      */     throws IOException
/*      */   {
/*  667 */     File localFile = new File(getConfig().getComLink2Config().getInstallDriverDir() + File.separator + "connect-success.txt");
/*      */ 
/*  671 */     localFile.createNewFile();
/*      */   }
/*      */ 
/*      */   public void setUserInputCompleted()
/*      */   {
/*  678 */     getConfig().getWizardSelections().setUserInputCompleted();
/*      */   }
/*      */ 
/*      */   public boolean isDeviceSelectionAPump()
/*      */   {
/*  687 */     return getConfig().getWizardSelections().isDeviceSelectionAPump();
/*      */   }
/*      */ 
/*      */   public boolean isDeviceSelectionAMeter()
/*      */   {
/*  696 */     return getConfig().getWizardSelections().isDeviceSelectionAMeter();
/*      */   }
/*      */ 
/*      */   public static int mapToDevicePortReaderFactoryID(String paramString)
/*      */   {
/*  707 */     Integer localInteger = (Integer)SELECTIONS_ID_MAP.get(paramString);
/*  708 */     Contract.preNonNull(localInteger);
/*  709 */     return localInteger.intValue();
/*      */   }
/*      */ 
/*      */   public static String mapToServerClassName(String paramString)
/*      */   {
/*  720 */     String str = (String)SELECTIONS_CLASSNAME_MAP.get(paramString);
/*  721 */     Contract.preNonNull(str);
/*  722 */     return str;
/*      */   }
/*      */ 
/*      */   public static String mapToWizardDeviceSelection(String paramString)
/*      */   {
/*  733 */     String str = (String)SELECTIONS_CLASSNAME_MAP.get(resolveServerClassName(paramString));
/*  734 */     Contract.preNonNull(str);
/*  735 */     return str;
/*      */   }
/*      */ 
/*      */   public static String resolveServerClassName(String paramString)
/*      */   {
/*  752 */     if (("MiniMed754".equals(paramString)) || ("MiniMed554".equals(paramString)) || ("MiniMed753".equals(paramString)) || ("MiniMed553".equals(paramString)) || ("MiniMed723".equals(paramString)) || ("MiniMed523".equals(paramString)) || ("MiniMed723K".equals(paramString)) || ("MiniMed523K".equals(paramString)) || ("MiniMed722".equals(paramString)) || ("MiniMed522".equals(paramString)) || ("MiniMed722K".equals(paramString)) || ("MiniMed522K".equals(paramString)) || ("MiniMed715".equals(paramString)) || ("MiniMed515".equals(paramString)) || ("MiniMed712".equals(paramString)) || ("MiniMed512".equals(paramString)))
/*      */     {
/*  762 */       paramString = "MiniMedParadigm2";
/*  763 */     } else if ("MiniMedGuardian3K".equals(paramString))
/*      */     {
/*  765 */       paramString = "MiniMedGuardian3";
/*  766 */     } else if (("LifescanOTUltra2".equals(paramString)) || ("LifescanOTUltraLink".equals(paramString)))
/*      */     {
/*  769 */       paramString = "LifescanOTUltra";
/*  770 */     } else if ("BayerContourLink".equals(paramString))
/*      */     {
/*  772 */       paramString = "BayerContour";
/*      */     }
/*      */ 
/*  775 */     return paramString;
/*      */   }
/*      */ 
/*      */   public void showNextStep(Class paramClass)
/*      */   {
/*  786 */     Contract.preNonNull(paramClass);
/*      */ 
/*  788 */     LogWriter localLogWriter = this.m_config.getLogWriter();
/*  789 */     localLogWriter.logInfo("showNextStep: showing " + paramClass.getName() + " " + isStopped());
/*  790 */     if (isStopped()) {
/*  791 */       localLogWriter.logInfo("showNextStep: abort showing " + paramClass.getName() + " - stopped");
/*  792 */       return;
/*      */     }
/*      */ 
/*  795 */     WizardStep localWizardStep = (WizardStep)this.m_stepLayout.getStep(paramClass.getName());
/*  796 */     if (localWizardStep == null)
/*      */     {
/*      */       try {
/*  799 */         Constructor localConstructor = paramClass.getConstructor(STEP_CONSTRUCTOR_ARG_TYPES);
/*  800 */         localWizardStep = (WizardStep)localConstructor.newInstance(this.m_stepConstructorArgs);
/*      */       }
/*      */       catch (NoSuchMethodException localNoSuchMethodException) {
/*  803 */         Contract.unreachable();
/*      */       }
/*      */       catch (InstantiationException localInstantiationException) {
/*  806 */         Contract.unreachable();
/*      */       }
/*      */       catch (InvocationTargetException localInvocationTargetException) {
/*  809 */         Contract.unreachable();
/*      */       }
/*      */       catch (IllegalAccessException localIllegalAccessException) {
/*  812 */         Contract.unreachable();
/*      */       }
/*  814 */       this.m_config.getContentPane().add(localWizardStep, localWizardStep.getName());
/*      */     }
/*      */ 
/*  818 */     this.m_stepLayout.show(this.m_config.getContentPane(), localWizardStep.getName());
/*  819 */     localWizardStep.stepShown();
/*      */ 
/*  823 */     startComLinkUSBMonitor();
/*      */   }
/*      */ 
/*      */   public void showPreviousStep()
/*      */   {
/*  832 */     showPreviousStep(1);
/*      */   }
/*      */ 
/*      */   public void showPreviousStep(int paramInt)
/*      */   {
/*  842 */     this.m_stepLayout.showPreviousStep(this.m_config.getContentPane(), paramInt);
/*  843 */     WizardStep localWizardStep = (WizardStep)this.m_stepLayout.getCurrentStep();
/*  844 */     localWizardStep.stepShown();
/*      */   }
/*      */ 
/*      */   public WizardStep getPreviousStep()
/*      */   {
/*  853 */     return (WizardStep)this.m_stepLayout.getPreviousStep();
/*      */   }
/*      */ 
/*      */   public WizardStep getStep(Class paramClass)
/*      */   {
/*  866 */     return (WizardStep)this.m_stepLayout.getStep(paramClass);
/*      */   }
/*      */ 
/*      */   public boolean isAutoDetect()
/*      */   {
/*  875 */     WizardSelections localWizardSelections = this.m_config.getWizardSelections();
/*  876 */     String str1 = localWizardSelections.getDeviceType();
/*      */ 
/*  878 */     boolean bool = localWizardSelections.getSerialPort().equals("wizard.selections.SELECTION_SERIAL_PORT_AUTO_DETECT");
/*      */     int i;
/*  884 */     if (str1.equals("wizard.selections.SELECTION_DEVICE_METER"))
/*      */     {
/*  886 */       String str2 = localWizardSelections.getMeterDevice();
/*      */ 
/*  888 */       if ((str2.equals("wizard.selections.SELECTION_DEVICE_MMLINKMETER")) || (str2.equals("wizard.selections.SELECTION_DEVICE_MMLOGICMETER")))
/*      */       {
/*  890 */         if (localWizardSelections.getConnectionType().equals("wizard.selections.SELECTION_CONN_TYPE_USB"))
/*      */         {
/*  892 */           i = 1;
/*      */         }
/*  894 */         else i = bool;
/*      */ 
/*      */       }
/*  897 */       else if ((str2.equals("wizard.selections.SELECTION_DEVICE_ASCENSIA_CONTOUR_USB_METER")) || (str2.equals("wizard.selections.SELECTION_DEVICE_XTLINKMETER")))
/*      */       {
/*  900 */         i = 0;
/*      */       }
/*  902 */       else i = bool;
/*      */ 
/*      */     }
/*  906 */     else if (localWizardSelections.getLinkDevice().equals("wizard.selections.SELECTION_LINK_DEVICE_COMLINK")) {
/*  907 */       i = bool;
/*  908 */     } else if (localWizardSelections.getLinkDevice().equals("wizard.selections.SELECTION_LINK_DEVICE_PARADIGMLINK"))
/*      */     {
/*  910 */       if (localWizardSelections.getConnectionType().equals("wizard.selections.SELECTION_CONN_TYPE_SERIAL"))
/*      */       {
/*  912 */         i = bool;
/*      */       }
/*  914 */       else i = 1; 
/*      */     }
/*      */     else
/*      */     {
/*  917 */       i = 0;
/*      */     }
/*      */ 
/*  920 */     return i;
/*      */   }
/*      */ 
/*      */   public boolean isBDUSBDriverInstallNeeded()
/*      */   {
/*  929 */     return isDriverInstallNeeded(this.m_config.getBDDriverConfig());
/*      */   }
/*      */ 
/*      */   public boolean isBayerUSBDriverInstallNeeded()
/*      */   {
/*  938 */     return isDriverInstallNeeded(this.m_config.getBayerUSBConfig());
/*      */   }
/*      */ 
/*      */   public boolean canFinish()
/*      */   {
/*  951 */     return canFinish(isBDUSBDriverInstallNeeded());
/*      */   }
/*      */ 
/*      */   public CaptureResult getCaptureResult()
/*      */   {
/*  961 */     return this.m_captureResult;
/*      */   }
/*      */ 
/*      */   boolean canFinish(boolean paramBoolean)
/*      */   {
/* 1032 */     WizardSelections localWizardSelections = this.m_config.getWizardSelections();
/*      */     boolean bool;
/* 1036 */     if (!localWizardSelections.isDeviceTypeSet()) {
/* 1037 */       bool = false;
/* 1038 */       return bool;
/*      */     }
/* 1040 */     String str = localWizardSelections.getDeviceType();
/*      */ 
/* 1042 */     if (isPump(str)) {
/* 1043 */       bool = canFinishPump(localWizardSelections, paramBoolean);
/* 1044 */     } else if (isMeter(str)) {
/* 1045 */       bool = canFinishMeter(localWizardSelections, paramBoolean);
/* 1046 */     } else if (isCGM(str)) {
/* 1047 */       bool = canFinishCGM(localWizardSelections, paramBoolean);
/*      */     }
/*      */     else {
/* 1050 */       bool = false;
/* 1051 */       Contract.unreachable();
/*      */     }
/*      */ 
/* 1061 */     return bool ? localWizardSelections.isUserInputCompleted() : false;
/*      */   }
/*      */ 
/*      */   private boolean isComLinkUSBConnected()
/*      */   {
/* 1070 */     boolean bool = false;
/*      */ 
/* 1073 */     if (!isComLink2USBDriverInstallNeeded()) {
/* 1074 */       bool = AA.R();
/*      */     }
/* 1076 */     return bool;
/*      */   }
/*      */ 
/*      */   private boolean canFinishPump(WizardSelections paramWizardSelections, boolean paramBoolean)
/*      */   {
/*      */     boolean bool;
/* 1092 */     if (!paramWizardSelections.isPumpDeviceSet()) {
/* 1093 */       bool = false;
/* 1094 */       return bool;
/*      */     }
/*      */ 
/* 1098 */     String str1 = paramWizardSelections.getPumpSerialNumber();
/* 1099 */     if (str1 != null)
/*      */     {
/* 1101 */       if (NumericTextField.isValidEntry(str1))
/*      */       {
/* 1103 */         if (!paramWizardSelections.isLinkDeviceSet()) {
/* 1104 */           bool = false;
/* 1105 */           return bool;
/*      */         }
/* 1107 */         String str2 = paramWizardSelections.getLinkDevice();
/* 1108 */         if (isComLinkUSB(str2)) {
/* 1109 */           bool = isComLinkUSBConnected();
/* 1110 */         } else if (isComLink(str2)) {
/* 1111 */           bool = paramWizardSelections.isSerialPortMethodSet();
/* 1112 */         } else if (isXTLinkUSB(str2)) {
/* 1113 */           bool = !isXTLink2USBDriverInstallNeeded();
/* 1114 */         } else if (isParadigmLink(str2))
/*      */         {
/* 1116 */           if (!paramWizardSelections.isConnectionTypeSet()) {
/* 1117 */             bool = false;
/* 1118 */             return bool;
/*      */           }
/* 1120 */           String str3 = paramWizardSelections.getConnectionType();
/* 1121 */           if (isSerial(str3)) {
/* 1122 */             bool = paramWizardSelections.isSerialPortMethodSet();
/* 1123 */           } else if (isUSB(str3)) {
/* 1124 */             bool = !paramBoolean;
/*      */           } else {
/* 1126 */             bool = false;
/* 1127 */             Contract.unreachable();
/*      */           }
/*      */         }
/*      */         else {
/* 1131 */           bool = false;
/* 1132 */           Contract.unreachable();
/*      */         }
/*      */       }
/*      */       else {
/* 1136 */         bool = false;
/*      */       }
/*      */     }
/*      */     else {
/* 1140 */       bool = false;
/*      */     }
/*      */ 
/* 1143 */     return bool;
/*      */   }
/*      */ 
/*      */   private boolean isDriverInstallNeeded(WizardConfig.DriverConfig paramDriverConfig)
/*      */   {
/* 1153 */     boolean bool = paramDriverConfig.isDriverInstallNeeded();
/* 1154 */     String str = bool ? "" : "NOT ";
/* 1155 */     this.m_config.getLogWriter().logInfo("install " + str + "needed for " + paramDriverConfig.getInstallDriverDir());
/*      */ 
/* 1157 */     return bool;
/*      */   }
/*      */ 
/*      */   private boolean canFinishMeter(WizardSelections paramWizardSelections, boolean paramBoolean)
/*      */   {
/*      */     boolean bool;
/* 1173 */     if (!paramWizardSelections.isMeterBrandSet()) {
/* 1174 */       bool = false;
/* 1175 */       return bool;
/*      */     }
/* 1177 */     String str1 = paramWizardSelections.getMeterBrand();
/*      */ 
/* 1180 */     if (!paramWizardSelections.isMeterDeviceSet()) {
/* 1181 */       bool = false;
/* 1182 */       return bool;
/*      */     }
/* 1184 */     String str2 = paramWizardSelections.getMeterDevice();
/*      */ 
/* 1187 */     if (paramWizardSelections.isMeterBrandAndModelMismatch(str1, str2)) {
/* 1188 */       bool = false;
/* 1189 */       return bool;
/*      */     }
/*      */ 
/* 1192 */     if (!isMiniMedOrBD(str1)) {
/* 1193 */       if (("wizard.selections.SELECTION_DEVICE_ASCENSIA_CONTOUR_USB_METER".equals(str2)) || ("wizard.selections.SELECTION_DEVICE_XTLINKMETER".equals(str2)))
/*      */       {
/* 1197 */         bool = !isBayerUSBDriverInstallNeeded();
/*      */       }
/* 1199 */       else bool = paramWizardSelections.isSerialPortMethodSet();
/*      */ 
/*      */     }
/* 1202 */     else if (("wizard.selections.SELECTION_DEVICE_MMLINKMETER".equals(str2)) || ("wizard.selections.SELECTION_DEVICE_MMLOGICMETER".equals(str2)))
/*      */     {
/* 1208 */       if (!paramWizardSelections.isConnectionTypeSet()) {
/* 1209 */         bool = false;
/* 1210 */         return bool;
/*      */       }
/* 1212 */       String str3 = paramWizardSelections.getConnectionType();
/* 1213 */       if (isSerial(str3)) {
/* 1214 */         bool = paramWizardSelections.isSerialPortMethodSet();
/* 1215 */       } else if (isUSB(str3)) {
/* 1216 */         bool = !paramBoolean;
/*      */       }
/*      */       else {
/* 1219 */         bool = false;
/* 1220 */         Contract.unreachable();
/*      */       }
/* 1222 */     } else if ("wizard.selections.SELECTION_DEVICE_XTLINKMETER".equals(str2))
/*      */     {
/* 1224 */       bool = !isBayerUSBDriverInstallNeeded();
/*      */     }
/*      */     else {
/* 1227 */       bool = paramWizardSelections.isSerialPortMethodSet();
/*      */     }
/*      */ 
/* 1232 */     if ((bool) && (getConfig().isUploadParadigmLinkMeterOnly()) && (!"wizard.selections.SELECTION_DEVICE_MMLINKMETER".equals(str2)))
/*      */     {
/* 1236 */       return false;
/*      */     }
/*      */ 
/* 1239 */     return bool;
/*      */   }
/*      */ 
/*      */   private boolean canFinishCGM(WizardSelections paramWizardSelections, boolean paramBoolean)
/*      */   {
/*      */     boolean bool;
/* 1255 */     if (!paramWizardSelections.isCGMDeviceSet()) {
/* 1256 */       bool = false;
/* 1257 */       return bool;
/*      */     }
/*      */ 
/* 1261 */     String str1 = paramWizardSelections.getCGMSerialNumber();
/* 1262 */     if (str1 != null)
/*      */     {
/* 1264 */       if (NumericTextField.isValidEntry(str1))
/*      */       {
/* 1266 */         if (!paramWizardSelections.isLinkDeviceSet()) {
/* 1267 */           bool = false;
/* 1268 */           return bool;
/*      */         }
/* 1270 */         String str2 = paramWizardSelections.getLinkDevice();
/* 1271 */         if (isComLinkUSB(str2)) {
/* 1272 */           bool = isComLinkUSBConnected();
/* 1273 */         } else if (isComLink(str2)) {
/* 1274 */           bool = paramWizardSelections.isSerialPortMethodSet();
/* 1275 */         } else if (isXTLinkUSB(str2)) {
/* 1276 */           bool = !isXTLink2USBDriverInstallNeeded();
/* 1277 */         } else if (isParadigmLink(str2))
/*      */         {
/* 1279 */           if (!paramWizardSelections.isConnectionTypeSet()) {
/* 1280 */             bool = false;
/* 1281 */             return bool;
/*      */           }
/* 1283 */           String str3 = paramWizardSelections.getConnectionType();
/* 1284 */           if (isSerial(str3)) {
/* 1285 */             bool = paramWizardSelections.isSerialPortMethodSet();
/* 1286 */           } else if (isUSB(str3)) {
/* 1287 */             bool = !paramBoolean;
/*      */           } else {
/* 1289 */             bool = false;
/* 1290 */             Contract.unreachable();
/*      */           }
/*      */         }
/*      */         else {
/* 1294 */           bool = false;
/* 1295 */           Contract.unreachable();
/*      */         }
/*      */       }
/*      */       else {
/* 1299 */         bool = false;
/*      */       }
/*      */     }
/*      */     else {
/* 1303 */       bool = false;
/*      */     }
/*      */ 
/* 1306 */     return bool;
/*      */   }
/*      */ 
/*      */   private boolean isPump(String paramString)
/*      */   {
/* 1316 */     return "wizard.selections.SELECTION_DEVICE_PUMP".equals(paramString);
/*      */   }
/*      */ 
/*      */   private boolean isMeter(String paramString)
/*      */   {
/* 1326 */     return "wizard.selections.SELECTION_DEVICE_METER".equals(paramString);
/*      */   }
/*      */ 
/*      */   private boolean isCGM(String paramString)
/*      */   {
/* 1336 */     return "wizard.selections.SELECTION_DEVICE_CGM".equals(paramString);
/*      */   }
/*      */ 
/*      */   private boolean isMiniMedOrBD(String paramString)
/*      */   {
/* 1346 */     return ("wizard.selections.SELECTION_BRAND_MINIMED_BD".equals(paramString)) || ("wizard.selections.SELECTION_BRAND_BD".equals(paramString));
/*      */   }
/*      */ 
/*      */   private boolean isComLink(String paramString)
/*      */   {
/* 1357 */     return "wizard.selections.SELECTION_LINK_DEVICE_COMLINK".equals(paramString);
/*      */   }
/*      */ 
/*      */   private boolean isParadigmLink(String paramString)
/*      */   {
/* 1367 */     return "wizard.selections.SELECTION_LINK_DEVICE_PARADIGMLINK".equals(paramString);
/*      */   }
/*      */ 
/*      */   private boolean isSerial(String paramString)
/*      */   {
/* 1377 */     return "wizard.selections.SELECTION_CONN_TYPE_SERIAL".equals(paramString);
/*      */   }
/*      */ 
/*      */   private boolean isUSB(String paramString)
/*      */   {
/* 1387 */     return "wizard.selections.SELECTION_CONN_TYPE_USB".equals(paramString);
/*      */   }
/*      */ 
/*      */   private void startComLinkUSBMonitor()
/*      */   {
/* 1396 */     1 local1 = new ActionListener()
/*      */     {
/*      */       public void actionPerformed(ActionEvent paramActionEvent)
/*      */       {
/* 1404 */         if (Wizard.this.isComLinkUSB(Wizard.this.getConfig().getWizardSelections().getLinkDevice()))
/*      */         {
/* 1406 */           WizardStep localWizardStep = (WizardStep)Wizard.this.m_stepLayout.getCurrentStep();
/* 1407 */           if (localWizardStep != null) {
/* 1408 */             localWizardStep.updateButtonStates();
/* 1409 */             localWizardStep.updateContent();
/*      */           }
/*      */         }
/*      */       }
/*      */     };
/* 1416 */     if (this.m_comLinkUSBMonitor == null) {
/* 1417 */       this.m_comLinkUSBMonitor = new Timer(2000, local1);
/* 1418 */       this.m_comLinkUSBMonitor.start();
/*      */     }
/*      */   }
/*      */ 
/*      */   private void stopComLinkUSBMonitor()
/*      */   {
/* 1426 */     if (this.m_comLinkUSBMonitor != null) {
/* 1427 */       this.m_comLinkUSBMonitor.stop();
/* 1428 */       AA.O();
/*      */     }
/*      */   }
/*      */ 
/*      */   static
/*      */   {
/*  338 */     HashMap localHashMap = new HashMap();
/*      */ 
/*  341 */     uniAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_MMPARADIGM2", new Integer(13));
/*      */ 
/*  345 */     uniAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_MMLINKMETER", new Integer(16));
/*      */ 
/*  347 */     uniAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_MMLOGICMETER", new Integer(17));
/*      */ 
/*  349 */     uniAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_ASCENSIA_DEX_METER", new Integer(8));
/*      */ 
/*  351 */     uniAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_ASCENSIA_ELITE_METER", new Integer(9));
/*      */ 
/*  353 */     uniAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_ASCENSIA_BREEZE_METER", new Integer(19));
/*      */ 
/*  355 */     uniAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_ASCENSIA_CONTOUR_METER", new Integer(20));
/*      */ 
/*  357 */     uniAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_ASCENSIA_CONTOUR_USB_METER", new Integer(27));
/*      */ 
/*  359 */     uniAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_XTLINKMETER", new Integer(28));
/*      */ 
/*  361 */     uniAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_LIFESCAN_BASIC_METER", new Integer(4));
/*      */ 
/*  363 */     uniAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_LIFESCAN_FASTTAKE_METER", new Integer(6));
/*      */ 
/*  365 */     uniAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_LIFESCAN_PROFILE_METER", new Integer(2));
/*      */ 
/*  367 */     uniAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_LIFESCAN_SURESTEP_METER", new Integer(7));
/*      */ 
/*  369 */     uniAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_LIFESCAN_ULTRA_METER", new Integer(5));
/*      */ 
/*  371 */     uniAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_LIFESCAN_ULTRASMART_METER", new Integer(21));
/*      */ 
/*  373 */     uniAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_LIFESCAN_ULTRAMINI_METER", new Integer(26));
/*      */ 
/*  375 */     uniAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_MEDISENSE_XTRA_METER", new Integer(10));
/*      */ 
/*  377 */     uniAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_THERASENSE_FREESTYLE_METER", new Integer(12));
/*      */ 
/*  379 */     uniAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_ROCHE_ACTIVE_METER", new Integer(23));
/*      */ 
/*  381 */     uniAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_ROCHE_AVIVA_METER", new Integer(22));
/*      */ 
/*  383 */     uniAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_ROCHE_COMPACT_METER", new Integer(24));
/*      */ 
/*  385 */     uniAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_ROCHE_COMPACTPLUS_METER", new Integer(25));
/*      */ 
/*  389 */     uniAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_MMGUARDIAN3", new Integer(13));
/*      */ 
/*  392 */     SELECTIONS_ID_MAP = Collections.unmodifiableMap(localHashMap);
/*      */ 
/*  396 */     localHashMap = new HashMap();
/*      */ 
/*  399 */     biAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_MMPARADIGM2", "MiniMedParadigm2");
/*      */ 
/*  403 */     biAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_MMLINKMETER", "BDParadigmLink");
/*      */ 
/*  405 */     biAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_MMLOGICMETER", "BDLogic");
/*      */ 
/*  407 */     biAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_ASCENSIA_DEX_METER", "BayerDex");
/*      */ 
/*  409 */     biAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_ASCENSIA_ELITE_METER", "BayerEliteXL");
/*      */ 
/*  411 */     biAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_ASCENSIA_BREEZE_METER", "BayerBreeze");
/*      */ 
/*  413 */     biAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_ASCENSIA_CONTOUR_METER", "BayerContour");
/*      */ 
/*  415 */     biAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_ASCENSIA_CONTOUR_USB_METER", "BayerContourUSB");
/*      */ 
/*  417 */     biAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_XTLINKMETER", "BayerContourXTLink");
/*      */ 
/*  419 */     biAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_LIFESCAN_BASIC_METER", "LifescanOTBasic");
/*      */ 
/*  421 */     biAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_LIFESCAN_FASTTAKE_METER", "LifescanFastTake");
/*      */ 
/*  423 */     biAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_LIFESCAN_PROFILE_METER", "LifescanOTProfile");
/*      */ 
/*  425 */     biAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_LIFESCAN_SURESTEP_METER", "LifescanSureStep");
/*      */ 
/*  427 */     biAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_LIFESCAN_ULTRA_METER", "LifescanOTUltra");
/*      */ 
/*  429 */     biAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_LIFESCAN_ULTRAMINI_METER", "LifescanOTUltraMini");
/*      */ 
/*  431 */     biAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_LIFESCAN_ULTRASMART_METER", "LifescanOTUltraSmart");
/*      */ 
/*  433 */     biAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_MEDISENSE_XTRA_METER", "MedisenseXtra");
/*      */ 
/*  435 */     biAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_THERASENSE_FREESTYLE_METER", "TherasenseFreestyle");
/*      */ 
/*  437 */     biAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_ROCHE_ACTIVE_METER", "RocheActive");
/*      */ 
/*  439 */     biAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_ROCHE_AVIVA_METER", "RocheAviva");
/*      */ 
/*  441 */     biAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_ROCHE_COMPACT_METER", "RocheCompact");
/*      */ 
/*  443 */     biAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_ROCHE_COMPACTPLUS_METER", "RocheCompactPlus");
/*      */ 
/*  448 */     biAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_MMGUARDIAN3", "MiniMedGuardian3");
/*      */ 
/*  451 */     SELECTIONS_CLASSNAME_MAP = Collections.unmodifiableMap(localHashMap);
/*      */   }
/*      */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.Wizard
 * JD-Core Version:    0.6.0
 */