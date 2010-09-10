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
/*      */ import minimed.ddms.applet.dtw.DTWApplet;
/*      */ import minimed.ddms.applet.dtw.LogWriter;
/*      */ import minimed.ddms.applet.dtw.PropertyWriter;
/*      */ import minimed.ddms.applet.dtw.components.NumericTextField;
/*      */ import minimed.ddms.applet.dtw.components.StepLayout;
/*      */ import minimed.ddms.deviceportreader.ComLink2;
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
/*      */   private static final String SERVER_CLASS_NAME_511 = "MiniMed511";
/*      */   private static final String SERVER_CLASS_NAME_508 = "MiniMed508";
/*      */   private static final String SERVER_CLASS_NAME_GUARDIAN3 = "MiniMedGuardian3";
/*      */   private static final String SERVER_CLASS_NAME_GUARDIAN3K = "MiniMedGuardian3K";
/*      */   private static final String SERVER_CLASS_NAME_PARADIGMLINK = "BDParadigmLink";
/*      */   private static final String SERVER_CLASS_NAME_LOGIC = "BDLogic";
/*      */   private static final String SERVER_CLASS_NAME_BAYER_DEX = "BayerDex";
/*      */   private static final String SERVER_CLASS_NAME_BAYER_ELITE_XL = "BayerEliteXL";
/*      */   private static final String SERVER_CLASS_NAME_BAYER_BREEZE = "BayerBreeze";
/*      */   private static final String SERVER_CLASS_NAME_BAYER_CONTOUR = "BayerContour";
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
/*  285 */   private static final Class[] STEP_CONSTRUCTOR_ARG_TYPES = { Wizard.class };
/*      */   public static final boolean ALLOW_CARELINK_USB = true;
/*      */   private static final int COMLINKUSB_TIMER_UPDATE = 2000;
/*      */   private static final String COMLINKUSB_CONNECT_SUCCESS_INDICATOR = "connect-success.txt";
/*      */   private final Object[] m_stepConstructorArgs;
/*      */   private final StepLayout m_stepLayout;
/*      */   private final WizardConfig m_config;
/*      */   private boolean m_stopped;
/*      */   private String m_failureReason;
/*      */   private boolean m_enabledUnloadMessage;
/*  338 */   private boolean m_isBDUSBDriverInstallNeeded = true;
/*      */ 
/*  347 */   private final USBDriverInstallNeededDecider m_standardBDUSBDriverInstallNeededDecider = new USBDriverInstallNeededDecider()
/*      */   {
/*      */     public boolean isUSBDriverInstallNeeded()
/*      */     {
/*  359 */       if (Wizard.this.m_isBDUSBDriverInstallNeeded) {
/*  360 */         File localFile = new File(Wizard.this.getConfig().getBDUSBConfig().getInstallUSBDriverDir() + File.separator + Wizard.this.getConfig().getBDUSBConfig().getInstallUSBDriverSuccessIndicatorFile());
/*      */ 
/*  363 */         Wizard.access$002(Wizard.this, (!localFile.exists()) || (!localFile.isFile()));
/*      */ 
/*  367 */         if (Wizard.this.m_isBDUSBDriverInstallNeeded) {
/*  368 */           localFile = new File(Wizard.this.getConfig().getBDUSBConfig().getSecondaryInstallUSBDriverDir() + File.separator + Wizard.this.getConfig().getBDUSBConfig().getInstallUSBDriverSuccessIndicatorFile());
/*      */ 
/*  372 */           Wizard.access$002(Wizard.this, (!localFile.exists()) || (!localFile.isFile()));
/*      */         }
/*      */       }
/*  375 */       return Wizard.this.m_isBDUSBDriverInstallNeeded;
/*      */     }
/*  347 */   };
/*      */   private Timer m_comLinkUSBMonitor;
/*      */ 
/*      */   public Wizard(WizardConfig paramWizardConfig)
/*      */   {
/*  515 */     Contract.preNonNull(paramWizardConfig);
/*  516 */     this.m_config = paramWizardConfig;
/*  517 */     this.m_stepLayout = new StepLayout();
/*  518 */     Container localContainer = this.m_config.getContentPane();
/*  519 */     localContainer.setLayout(this.m_stepLayout);
/*  520 */     this.m_stepConstructorArgs = new Object[] { this };
/*      */   }
/*      */ 
/*      */   public String toString()
/*      */   {
/*  531 */     PropertyWriter localPropertyWriter = new PropertyWriter();
/*  532 */     localPropertyWriter.add("config", this.m_config);
/*  533 */     localPropertyWriter.add("stopped", isStopped());
/*  534 */     localPropertyWriter.add("failureReason", getFailureReason());
/*  535 */     localPropertyWriter.add("stepLayout", this.m_stepLayout);
/*  536 */     return localPropertyWriter.toString();
/*      */   }
/*      */ 
/*      */   public void start()
/*      */   {
/*  543 */     Class localClass = this.m_config.getWizardStepProvider().getStartStep(this);
/*  544 */     showNextStep(localClass);
/*      */   }
/*      */ 
/*      */   public void stop()
/*      */   {
/*  551 */     this.m_config.getLogWriter().logInfo("stop wizard execution requested");
/*  552 */     stopComLinkUSBMonitor();
/*      */ 
/*  554 */     if (!isStopped()) {
/*  555 */       setStopped();
/*      */ 
/*  557 */       WizardStep localWizardStep = (WizardStep)this.m_stepLayout.getCurrentStep();
/*  558 */       if (localWizardStep != null)
/*  559 */         localWizardStep.appletStopped();
/*      */     }
/*      */   }
/*      */ 
/*      */   public WizardConfig getConfig()
/*      */   {
/*  571 */     WizardConfig localWizardConfig = this.m_config;
/*  572 */     Contract.postNonNull(localWizardConfig);
/*  573 */     return localWizardConfig;
/*      */   }
/*      */ 
/*      */   boolean isComLinkUSB(String paramString)
/*      */   {
/*  583 */     return "wizard.selections.SELECTION_LINK_DEVICE_COMLINKUSB".equals(paramString);
/*      */   }
/*      */ 
/*      */   private static void uniAssociate(HashMap paramHashMap, Object paramObject1, Object paramObject2)
/*      */   {
/*  595 */     Object localObject = paramHashMap.put(paramObject1, paramObject2);
/*  596 */     Contract.pre(localObject == null);
/*      */   }
/*      */ 
/*      */   private static void biAssociate(HashMap paramHashMap, Object paramObject1, Object paramObject2)
/*      */   {
/*  610 */     uniAssociate(paramHashMap, paramObject1, paramObject2);
/*  611 */     Object localObject = paramHashMap.put(paramObject2, paramObject1);
/*  612 */     Contract.pre(localObject == null);
/*      */   }
/*      */ 
/*      */   private synchronized void setStopped()
/*      */   {
/*  619 */     this.m_stopped = true;
/*      */   }
/*      */ 
/*      */   public synchronized boolean isStopped()
/*      */   {
/*  628 */     return this.m_stopped;
/*      */   }
/*      */ 
/*      */   public synchronized void setFailureReason(String paramString)
/*      */   {
/*  638 */     Contract.preString(paramString);
/*  639 */     this.m_failureReason = paramString;
/*      */   }
/*      */ 
/*      */   public synchronized String getFailureReason()
/*      */   {
/*  648 */     return this.m_failureReason;
/*      */   }
/*      */ 
/*      */   public void enableVerifySuspendOffMessage()
/*      */   {
/*  656 */     if (!this.m_enabledUnloadMessage) {
/*  657 */       DTWApplet localDTWApplet = getConfig().getApplet();
/*  658 */       localDTWApplet.enableUnloadMessage(true);
/*  659 */       this.m_enabledUnloadMessage = true;
/*      */     }
/*      */   }
/*      */ 
/*      */   public boolean isComLink2USBDriverInstallNeeded()
/*      */   {
/*  669 */     File localFile = new File(getConfig().getComLink2Config().getInstallUSBDriverDir() + File.separator + getConfig().getComLink2Config().getInstallUSBDriverSuccessIndicatorFile());
/*      */ 
/*  672 */     return (!localFile.exists()) || (!localFile.isFile());
/*      */   }
/*      */ 
/*      */   public boolean hasComLink2EverBeenDetected()
/*      */   {
/*  683 */     File localFile = new File(getConfig().getComLink2Config().getInstallUSBDriverDir() + File.separator + "connect-success.txt");
/*      */ 
/*  685 */     return (localFile.exists()) && (localFile.isFile());
/*      */   }
/*      */ 
/*      */   public void writeComLink2EverBeenDetectedIndicator()
/*      */     throws IOException
/*      */   {
/*  694 */     File localFile = new File(getConfig().getComLink2Config().getInstallUSBDriverDir() + File.separator + "connect-success.txt");
/*      */ 
/*  698 */     localFile.createNewFile();
/*      */   }
/*      */ 
/*      */   public void setUserInputCompleted()
/*      */   {
/*  705 */     getConfig().getWizardSelections().setUserInputCompleted();
/*      */   }
/*      */ 
/*      */   public boolean isDeviceSelectionAPump()
/*      */   {
/*  714 */     return getConfig().getWizardSelections().isDeviceSelectionAPump();
/*      */   }
/*      */ 
/*      */   public boolean isDeviceSelectionAMeter()
/*      */   {
/*  723 */     return getConfig().getWizardSelections().isDeviceSelectionAMeter();
/*      */   }
/*      */ 
/*      */   public static int mapToDevicePortReaderFactoryID(String paramString)
/*      */   {
/*  734 */     Integer localInteger = (Integer)SELECTIONS_ID_MAP.get(paramString);
/*  735 */     Contract.preNonNull(localInteger);
/*  736 */     return localInteger.intValue();
/*      */   }
/*      */ 
/*      */   public static String mapToServerClassName(String paramString)
/*      */   {
/*  747 */     String str = (String)SELECTIONS_CLASSNAME_MAP.get(paramString);
/*  748 */     Contract.preNonNull(str);
/*  749 */     return str;
/*      */   }
/*      */ 
/*      */   public static String mapToWizardDeviceSelection(String paramString)
/*      */   {
/*  760 */     String str = (String)SELECTIONS_CLASSNAME_MAP.get(resolveServerClassName(paramString));
/*  761 */     Contract.preNonNull(str);
/*  762 */     return str;
/*      */   }
/*      */ 
/*      */   public static String resolveServerClassName(String paramString)
/*      */   {
/*  779 */     if (("MiniMed754".equals(paramString)) || ("MiniMed554".equals(paramString)) || ("MiniMed753".equals(paramString)) || ("MiniMed553".equals(paramString)) || ("MiniMed723".equals(paramString)) || ("MiniMed523".equals(paramString)) || ("MiniMed723K".equals(paramString)) || ("MiniMed523K".equals(paramString)) || ("MiniMed722".equals(paramString)) || ("MiniMed522".equals(paramString)) || ("MiniMed722K".equals(paramString)) || ("MiniMed522K".equals(paramString)) || ("MiniMed715".equals(paramString)) || ("MiniMed515".equals(paramString)) || ("MiniMed712".equals(paramString)) || ("MiniMed512".equals(paramString)))
/*      */     {
/*  789 */       paramString = "MiniMedParadigm2";
/*  790 */     } else if ("MiniMedGuardian3K".equals(paramString))
/*      */     {
/*  792 */       paramString = "MiniMedGuardian3";
/*  793 */     } else if (("LifescanOTUltra2".equals(paramString)) || ("LifescanOTUltraLink".equals(paramString)))
/*      */     {
/*  796 */       paramString = "LifescanOTUltra";
/*  797 */     } else if ("BayerContourLink".equals(paramString))
/*      */     {
/*  799 */       paramString = "BayerContour";
/*      */     }
/*      */ 
/*  802 */     return paramString;
/*      */   }
/*      */ 
/*      */   public void showNextStep(Class paramClass)
/*      */   {
/*  813 */     Contract.preNonNull(paramClass);
/*      */ 
/*  815 */     LogWriter localLogWriter = this.m_config.getLogWriter();
/*  816 */     localLogWriter.logInfo("showNextStep: showing " + paramClass.getName() + " " + isStopped());
/*  817 */     if (isStopped()) {
/*  818 */       localLogWriter.logInfo("showNextStep: abort showing " + paramClass.getName() + " - stopped");
/*  819 */       return;
/*      */     }
/*      */ 
/*  822 */     WizardStep localWizardStep = (WizardStep)this.m_stepLayout.getStep(paramClass.getName());
/*  823 */     if (localWizardStep == null)
/*      */     {
/*      */       try {
/*  826 */         Constructor localConstructor = paramClass.getConstructor(STEP_CONSTRUCTOR_ARG_TYPES);
/*  827 */         localWizardStep = (WizardStep)localConstructor.newInstance(this.m_stepConstructorArgs);
/*      */       }
/*      */       catch (NoSuchMethodException localNoSuchMethodException) {
/*  830 */         Contract.unreachable();
/*      */       }
/*      */       catch (InstantiationException localInstantiationException) {
/*  833 */         Contract.unreachable();
/*      */       }
/*      */       catch (InvocationTargetException localInvocationTargetException) {
/*  836 */         Contract.unreachable();
/*      */       }
/*      */       catch (IllegalAccessException localIllegalAccessException) {
/*  839 */         Contract.unreachable();
/*      */       }
/*  841 */       this.m_config.getContentPane().add(localWizardStep, localWizardStep.getName());
/*      */     }
/*      */ 
/*  845 */     this.m_stepLayout.show(this.m_config.getContentPane(), localWizardStep.getName());
/*  846 */     localWizardStep.stepShown();
/*      */ 
/*  850 */     startComLinkUSBMonitor();
/*      */   }
/*      */ 
/*      */   public void showPreviousStep()
/*      */   {
/*  859 */     showPreviousStep(1);
/*      */   }
/*      */ 
/*      */   public void showPreviousStep(int paramInt)
/*      */   {
/*  869 */     this.m_stepLayout.showPreviousStep(this.m_config.getContentPane(), paramInt);
/*  870 */     WizardStep localWizardStep = (WizardStep)this.m_stepLayout.getCurrentStep();
/*  871 */     localWizardStep.stepShown();
/*      */   }
/*      */ 
/*      */   public WizardStep getPreviousStep()
/*      */   {
/*  880 */     return (WizardStep)this.m_stepLayout.getPreviousStep();
/*      */   }
/*      */ 
/*      */   public WizardStep getStep(Class paramClass)
/*      */   {
/*  893 */     return (WizardStep)this.m_stepLayout.getStep(paramClass);
/*      */   }
/*      */ 
/*      */   public boolean canDeviceUseUSB()
/*      */   {
/*  903 */     WizardSelections localWizardSelections = this.m_config.getWizardSelections();
/*  904 */     String str1 = localWizardSelections.getDeviceType();
/*      */     String str2;
/*      */     int i;
/*  911 */     if (str1.equals("wizard.selections.SELECTION_DEVICE_PUMP"))
/*      */     {
/*  913 */       str2 = localWizardSelections.getPumpDevice();
/*  914 */       if (!str2.equals("wizard.selections.SELECTION_DEVICE_MM508"))
/*      */       {
/*  916 */         i = 1;
/*      */       }
/*  918 */       else i = 0;
/*      */     }
/*  920 */     else if (str1.equals("wizard.selections.SELECTION_DEVICE_METER"))
/*      */     {
/*  922 */       str2 = localWizardSelections.getMeterDevice();
/*  923 */       if ((str2.equals("wizard.selections.SELECTION_DEVICE_MMLINKMETER")) || (str2.equals("wizard.selections.SELECTION_DEVICE_MMLOGICMETER")))
/*      */       {
/*  926 */         i = 1;
/*      */       }
/*  928 */       else i = 0;
/*      */     }
/*      */     else
/*      */     {
/*  932 */       i = 1;
/*      */     }
/*  934 */     return i;
/*      */   }
/*      */ 
/*      */   public boolean isBDUSBDriverInstallNeeded()
/*      */   {
/*  943 */     return this.m_standardBDUSBDriverInstallNeededDecider.isUSBDriverInstallNeeded();
/*      */   }
/*      */ 
/*      */   public boolean canFinish()
/*      */   {
/*  956 */     return canFinish(this.m_standardBDUSBDriverInstallNeededDecider);
/*      */   }
/*      */ 
/*      */   boolean canFinish(USBDriverInstallNeededDecider paramUSBDriverInstallNeededDecider)
/*      */   {
/* 1033 */     WizardSelections localWizardSelections = this.m_config.getWizardSelections();
/*      */     boolean bool;
/* 1037 */     if (!localWizardSelections.isDeviceTypeSet()) {
/* 1038 */       bool = false;
/* 1039 */       return bool;
/*      */     }
/* 1041 */     String str = localWizardSelections.getDeviceType();
/*      */ 
/* 1043 */     if (isPump(str)) {
/* 1044 */       bool = canFinishPump(localWizardSelections, paramUSBDriverInstallNeededDecider);
/* 1045 */     } else if (isMeter(str)) {
/* 1046 */       bool = canFinishMeter(localWizardSelections, paramUSBDriverInstallNeededDecider);
/* 1047 */     } else if (isCGM(str)) {
/* 1048 */       bool = canFinishCGM(localWizardSelections, paramUSBDriverInstallNeededDecider);
/*      */     }
/*      */     else {
/* 1051 */       bool = false;
/* 1052 */       Contract.unreachable();
/*      */     }
/*      */ 
/* 1062 */     return bool ? localWizardSelections.isUserInputCompleted() : false;
/*      */   }
/*      */ 
/*      */   private boolean isComLinkUSBConnected()
/*      */   {
/* 1071 */     boolean bool = false;
/*      */ 
/* 1074 */     if (!isComLink2USBDriverInstallNeeded()) {
/* 1075 */       bool = ComLink2.isLinkPresent();
/*      */     }
/* 1077 */     return bool;
/*      */   }
/*      */ 
/*      */   private boolean canFinishPump(WizardSelections paramWizardSelections, USBDriverInstallNeededDecider paramUSBDriverInstallNeededDecider)
/*      */   {
/*      */     boolean bool;
/* 1093 */     if (!paramWizardSelections.isPumpDeviceSet()) {
/* 1094 */       bool = false;
/* 1095 */       return bool;
/*      */     }
/* 1097 */     String str1 = paramWizardSelections.getPumpDevice();
/*      */ 
/* 1099 */     if (is508(str1)) {
/* 1100 */       bool = paramWizardSelections.isSerialPortMethodSet();
/*      */     }
/*      */     else {
/* 1103 */       String str2 = paramWizardSelections.getPumpSerialNumber();
/* 1104 */       if (str2 != null)
/*      */       {
/* 1106 */         if (NumericTextField.isValidEntry(str2))
/*      */         {
/* 1108 */           if (!paramWizardSelections.isLinkDeviceSet()) {
/* 1109 */             bool = false;
/* 1110 */             return bool;
/*      */           }
/* 1112 */           String str3 = paramWizardSelections.getLinkDevice();
/* 1113 */           if (isComLinkUSB(str3)) {
/* 1114 */             bool = isComLinkUSBConnected();
/* 1115 */           } else if (isComLink(str3)) {
/* 1116 */             bool = paramWizardSelections.isSerialPortMethodSet();
/* 1117 */           } else if (isParadigmLink(str3))
/*      */           {
/* 1119 */             if (!paramWizardSelections.isConnectionTypeSet()) {
/* 1120 */               bool = false;
/* 1121 */               return bool;
/*      */             }
/* 1123 */             String str4 = paramWizardSelections.getConnectionType();
/* 1124 */             if (isSerial(str4)) {
/* 1125 */               bool = paramWizardSelections.isSerialPortMethodSet();
/* 1126 */             } else if (isUSB(str4)) {
/* 1127 */               bool = !paramUSBDriverInstallNeededDecider.isUSBDriverInstallNeeded();
/*      */             } else {
/* 1129 */               bool = false;
/* 1130 */               Contract.unreachable();
/*      */             }
/*      */           }
/*      */           else {
/* 1134 */             bool = false;
/* 1135 */             Contract.unreachable();
/*      */           }
/*      */         }
/*      */         else {
/* 1139 */           bool = false;
/*      */         }
/*      */       }
/*      */       else {
/* 1143 */         bool = false;
/*      */       }
/*      */     }
/*      */ 
/* 1147 */     return bool;
/*      */   }
/*      */ 
/*      */   private boolean canFinishMeter(WizardSelections paramWizardSelections, USBDriverInstallNeededDecider paramUSBDriverInstallNeededDecider)
/*      */   {
/*      */     boolean bool;
/* 1163 */     if (!paramWizardSelections.isMeterBrandSet()) {
/* 1164 */       bool = false;
/* 1165 */       return bool;
/*      */     }
/* 1167 */     String str1 = paramWizardSelections.getMeterBrand();
/*      */ 
/* 1170 */     if (!paramWizardSelections.isMeterDeviceSet()) {
/* 1171 */       bool = false;
/* 1172 */       return bool;
/*      */     }
/* 1174 */     String str2 = paramWizardSelections.getMeterDevice();
/*      */ 
/* 1177 */     if (paramWizardSelections.isMeterBrandAndModelMismatch(str1, str2)) {
/* 1178 */       bool = false;
/* 1179 */       return bool;
/*      */     }
/*      */ 
/* 1182 */     if (!isMiniMedOrBD(str1))
/*      */     {
/* 1184 */       bool = paramWizardSelections.isSerialPortMethodSet();
/*      */     }
/* 1186 */     else if (("wizard.selections.SELECTION_DEVICE_MMLINKMETER".equals(str2)) || ("wizard.selections.SELECTION_DEVICE_MMLOGICMETER".equals(str2)))
/*      */     {
/* 1192 */       if (!paramWizardSelections.isConnectionTypeSet()) {
/* 1193 */         bool = false;
/* 1194 */         return bool;
/*      */       }
/* 1196 */       String str3 = paramWizardSelections.getConnectionType();
/* 1197 */       if (isSerial(str3)) {
/* 1198 */         bool = paramWizardSelections.isSerialPortMethodSet();
/* 1199 */       } else if (isUSB(str3)) {
/* 1200 */         bool = !paramUSBDriverInstallNeededDecider.isUSBDriverInstallNeeded();
/*      */       }
/*      */       else {
/* 1203 */         bool = false;
/* 1204 */         Contract.unreachable();
/*      */       }
/*      */     }
/*      */     else {
/* 1208 */       bool = paramWizardSelections.isSerialPortMethodSet();
/*      */     }
/*      */ 
/* 1213 */     if ((bool) && (getConfig().isUploadParadigmLinkMeterOnly()) && (!"wizard.selections.SELECTION_DEVICE_MMLINKMETER".equals(str2)))
/*      */     {
/* 1217 */       return false;
/*      */     }
/*      */ 
/* 1220 */     return bool;
/*      */   }
/*      */ 
/*      */   private boolean canFinishCGM(WizardSelections paramWizardSelections, USBDriverInstallNeededDecider paramUSBDriverInstallNeededDecider)
/*      */   {
/*      */     boolean bool;
/* 1236 */     if (!paramWizardSelections.isCGMDeviceSet()) {
/* 1237 */       bool = false;
/* 1238 */       return bool;
/*      */     }
/*      */ 
/* 1242 */     String str1 = paramWizardSelections.getCGMSerialNumber();
/* 1243 */     if (str1 != null)
/*      */     {
/* 1245 */       if (NumericTextField.isValidEntry(str1))
/*      */       {
/* 1247 */         if (!paramWizardSelections.isLinkDeviceSet()) {
/* 1248 */           bool = false;
/* 1249 */           return bool;
/*      */         }
/* 1251 */         String str2 = paramWizardSelections.getLinkDevice();
/* 1252 */         if (isComLinkUSB(str2)) {
/* 1253 */           bool = isComLinkUSBConnected();
/* 1254 */         } else if (isComLink(str2)) {
/* 1255 */           bool = paramWizardSelections.isSerialPortMethodSet();
/* 1256 */         } else if (isParadigmLink(str2))
/*      */         {
/* 1258 */           if (!paramWizardSelections.isConnectionTypeSet()) {
/* 1259 */             bool = false;
/* 1260 */             return bool;
/*      */           }
/* 1262 */           String str3 = paramWizardSelections.getConnectionType();
/* 1263 */           if (isSerial(str3)) {
/* 1264 */             bool = paramWizardSelections.isSerialPortMethodSet();
/* 1265 */           } else if (isUSB(str3)) {
/* 1266 */             bool = !paramUSBDriverInstallNeededDecider.isUSBDriverInstallNeeded();
/*      */           } else {
/* 1268 */             bool = false;
/* 1269 */             Contract.unreachable();
/*      */           }
/*      */         }
/*      */         else {
/* 1273 */           bool = false;
/* 1274 */           Contract.unreachable();
/*      */         }
/*      */       }
/*      */       else {
/* 1278 */         bool = false;
/*      */       }
/*      */     }
/*      */     else {
/* 1282 */       bool = false;
/*      */     }
/*      */ 
/* 1285 */     return bool;
/*      */   }
/*      */ 
/*      */   private boolean isPump(String paramString)
/*      */   {
/* 1295 */     return "wizard.selections.SELECTION_DEVICE_PUMP".equals(paramString);
/*      */   }
/*      */ 
/*      */   private boolean isMeter(String paramString)
/*      */   {
/* 1305 */     return "wizard.selections.SELECTION_DEVICE_METER".equals(paramString);
/*      */   }
/*      */ 
/*      */   private boolean isCGM(String paramString)
/*      */   {
/* 1315 */     return "wizard.selections.SELECTION_DEVICE_CGM".equals(paramString);
/*      */   }
/*      */ 
/*      */   private boolean isMiniMedOrBD(String paramString)
/*      */   {
/* 1325 */     return ("wizard.selections.SELECTION_BRAND_MINIMED_BD".equals(paramString)) || ("wizard.selections.SELECTION_BRAND_BD".equals(paramString));
/*      */   }
/*      */ 
/*      */   private boolean is508(String paramString)
/*      */   {
/* 1336 */     return "wizard.selections.SELECTION_DEVICE_MM508".equals(paramString);
/*      */   }
/*      */ 
/*      */   private boolean isComLink(String paramString)
/*      */   {
/* 1346 */     return "wizard.selections.SELECTION_LINK_DEVICE_COMLINK".equals(paramString);
/*      */   }
/*      */ 
/*      */   private boolean isParadigmLink(String paramString)
/*      */   {
/* 1356 */     return "wizard.selections.SELECTION_LINK_DEVICE_PARADIGMLINK".equals(paramString);
/*      */   }
/*      */ 
/*      */   private boolean isSerial(String paramString)
/*      */   {
/* 1366 */     return "wizard.selections.SELECTION_CONN_TYPE_SERIAL".equals(paramString);
/*      */   }
/*      */ 
/*      */   private boolean isUSB(String paramString)
/*      */   {
/* 1376 */     return "wizard.selections.SELECTION_CONN_TYPE_USB".equals(paramString);
/*      */   }
/*      */ 
/*      */   private void startComLinkUSBMonitor()
/*      */   {
/* 1385 */     2 local2 = new ActionListener()
/*      */     {
/*      */       public void actionPerformed(ActionEvent paramActionEvent)
/*      */       {
/* 1393 */         if (Wizard.this.isComLinkUSB(Wizard.this.getConfig().getWizardSelections().getLinkDevice()))
/*      */         {
/* 1395 */           WizardStep localWizardStep = (WizardStep)Wizard.this.m_stepLayout.getCurrentStep();
/* 1396 */           if (localWizardStep != null) {
/* 1397 */             localWizardStep.updateButtonStates();
/* 1398 */             localWizardStep.updateContent();
/*      */           }
/*      */         }
/*      */       }
/*      */     };
/* 1405 */     if (this.m_comLinkUSBMonitor == null) {
/* 1406 */       this.m_comLinkUSBMonitor = new Timer(2000, local2);
/* 1407 */       this.m_comLinkUSBMonitor.start();
/*      */     }
/*      */   }
/*      */ 
/*      */   private void stopComLinkUSBMonitor()
/*      */   {
/* 1415 */     if (this.m_comLinkUSBMonitor != null)
/* 1416 */       this.m_comLinkUSBMonitor.stop();
/*      */   }
/*      */ 
/*      */   static
/*      */   {
/*  390 */     HashMap localHashMap = new HashMap();
/*      */ 
/*  393 */     uniAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_MMPARADIGM2", new Integer(13));
/*      */ 
/*  395 */     uniAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_MM511", new Integer(1));
/*      */ 
/*  397 */     uniAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_MM508", new Integer(0));
/*      */ 
/*  401 */     uniAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_MMLINKMETER", new Integer(16));
/*      */ 
/*  403 */     uniAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_MMLOGICMETER", new Integer(17));
/*      */ 
/*  405 */     uniAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_ASCENSIA_DEX_METER", new Integer(8));
/*      */ 
/*  407 */     uniAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_ASCENSIA_ELITE_METER", new Integer(9));
/*      */ 
/*  409 */     uniAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_ASCENSIA_BREEZE_METER", new Integer(19));
/*      */ 
/*  411 */     uniAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_ASCENSIA_CONTOUR_METER", new Integer(20));
/*      */ 
/*  413 */     uniAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_LIFESCAN_BASIC_METER", new Integer(4));
/*      */ 
/*  415 */     uniAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_LIFESCAN_FASTTAKE_METER", new Integer(6));
/*      */ 
/*  417 */     uniAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_LIFESCAN_PROFILE_METER", new Integer(2));
/*      */ 
/*  419 */     uniAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_LIFESCAN_SURESTEP_METER", new Integer(7));
/*      */ 
/*  421 */     uniAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_LIFESCAN_ULTRA_METER", new Integer(5));
/*      */ 
/*  423 */     uniAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_LIFESCAN_ULTRASMART_METER", new Integer(21));
/*      */ 
/*  425 */     uniAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_LIFESCAN_ULTRAMINI_METER", new Integer(26));
/*      */ 
/*  427 */     uniAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_MEDISENSE_XTRA_METER", new Integer(10));
/*      */ 
/*  429 */     uniAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_THERASENSE_FREESTYLE_METER", new Integer(12));
/*      */ 
/*  431 */     uniAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_ROCHE_ACTIVE_METER", new Integer(23));
/*      */ 
/*  433 */     uniAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_ROCHE_AVIVA_METER", new Integer(22));
/*      */ 
/*  435 */     uniAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_ROCHE_COMPACT_METER", new Integer(24));
/*      */ 
/*  437 */     uniAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_ROCHE_COMPACTPLUS_METER", new Integer(25));
/*      */ 
/*  441 */     uniAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_MMGUARDIAN3", new Integer(13));
/*      */ 
/*  444 */     SELECTIONS_ID_MAP = Collections.unmodifiableMap(localHashMap);
/*      */ 
/*  448 */     localHashMap = new HashMap();
/*      */ 
/*  451 */     biAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_MMPARADIGM2", "MiniMedParadigm2");
/*      */ 
/*  453 */     biAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_MM511", "MiniMed511");
/*      */ 
/*  455 */     biAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_MM508", "MiniMed508");
/*      */ 
/*  459 */     biAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_MMLINKMETER", "BDParadigmLink");
/*      */ 
/*  461 */     biAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_MMLOGICMETER", "BDLogic");
/*      */ 
/*  463 */     biAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_ASCENSIA_DEX_METER", "BayerDex");
/*      */ 
/*  465 */     biAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_ASCENSIA_ELITE_METER", "BayerEliteXL");
/*      */ 
/*  467 */     biAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_ASCENSIA_BREEZE_METER", "BayerBreeze");
/*      */ 
/*  469 */     biAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_ASCENSIA_CONTOUR_METER", "BayerContour");
/*      */ 
/*  471 */     biAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_LIFESCAN_BASIC_METER", "LifescanOTBasic");
/*      */ 
/*  473 */     biAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_LIFESCAN_FASTTAKE_METER", "LifescanFastTake");
/*      */ 
/*  475 */     biAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_LIFESCAN_PROFILE_METER", "LifescanOTProfile");
/*      */ 
/*  477 */     biAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_LIFESCAN_SURESTEP_METER", "LifescanSureStep");
/*      */ 
/*  479 */     biAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_LIFESCAN_ULTRA_METER", "LifescanOTUltra");
/*      */ 
/*  481 */     biAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_LIFESCAN_ULTRAMINI_METER", "LifescanOTUltraMini");
/*      */ 
/*  483 */     biAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_LIFESCAN_ULTRASMART_METER", "LifescanOTUltraSmart");
/*      */ 
/*  485 */     biAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_MEDISENSE_XTRA_METER", "MedisenseXtra");
/*      */ 
/*  487 */     biAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_THERASENSE_FREESTYLE_METER", "TherasenseFreestyle");
/*      */ 
/*  489 */     biAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_ROCHE_ACTIVE_METER", "RocheActive");
/*      */ 
/*  491 */     biAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_ROCHE_AVIVA_METER", "RocheAviva");
/*      */ 
/*  493 */     biAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_ROCHE_COMPACT_METER", "RocheCompact");
/*      */ 
/*  495 */     biAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_ROCHE_COMPACTPLUS_METER", "RocheCompactPlus");
/*      */ 
/*  500 */     biAssociate(localHashMap, "wizard.selections.SELECTION_DEVICE_MMGUARDIAN3", "MiniMedGuardian3");
/*      */ 
/*  503 */     SELECTIONS_CLASSNAME_MAP = Collections.unmodifiableMap(localHashMap);
/*      */   }
/*      */ 
/*      */   static abstract interface USBDriverInstallNeededDecider
/*      */   {
/*      */     public abstract boolean isUSBDriverInstallNeeded();
/*      */   }
/*      */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.Wizard
 * JD-Core Version:    0.6.0
 */