/*      */ package Serialio;
/*      */ 
/*      */ import Serialio.tool.SortVector;
/*      */ import Serialio.tool.StringCompare;
/*      */ import java.io.File;
/*      */ import java.io.FileNotFoundException;
/*      */ import java.io.IOException;
/*      */ import java.io.PrintStream;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collections;
/*      */ import java.util.Enumeration;
/*      */ import java.util.List;
/*      */ import java.util.StringTokenizer;
/*      */ import java.util.Vector;
/*      */ import minimed.util.OSInfo;
/*      */ 
/*      */ public class SerialPortLocal
/*      */   implements SerialPort
/*      */ {
/*      */   public static final String DRIVER_INSTALLATION_ERROR = "Driver Installation Error";
/*      */   public static final String UNSUPPORTED_PLATFORM_ERROR = "Unsupported Platform Error";
/*   73 */   public static final String LIB_PATH = OSInfo.getAllUserPath() + File.separator + OSInfo.getUserData() + File.separator + "Medtronic" + File.separator + "ddmsDTWserial" + File.separator;
/*      */   public static final String WINDOWS_LIB_NAME_32 = "ddmsDTWSerialPort.dll";
/*      */   public static final String WINDOWS_LIB_NAME_64 = "ddmsDTWSerialPort-64.dll";
/*      */   public static final String MAC_LIB_NAME_32 = "ddmsDTWSerialPort.jnilib";
/*      */   public static final String MAC_LIB_NAME_64 = "ddmsDTWSerialPort-64.jnilib";
/*      */   public static final String INSTALL_SERIALIO_WINDOWS = "Install_serialio.cmd";
/*      */   public static final String INSTALL_SERIALIO_MAC = "Install_serialio.sh";
/*  111 */   protected static int buildNum = 9212;
/*  112 */   private static String banner1 = "Serialio Library: version 10.0.4: build " + buildNum;
/*  113 */   private static String banner2 = "Copyright (c) 1996-2009 Serialio.com, All Rights Reserved.";
/*  114 */   protected static StringCompare stringCompare = new StringCompare();
/*  115 */   protected static SortVector svPortList = new SortVector(stringCompare);
/*  116 */   protected static Vector portList = new Vector();
/*  117 */   protected static Vector portListInfo = new Vector();
/*      */   protected static PortDriverInfo portInfo;
/*  119 */   protected static String[] comPortNums = null;
/*  120 */   protected static List portNumList = null;
/*  121 */   protected static String jspLib = null;
/*  122 */   protected static boolean jspLibLoaded = false;
/*  123 */   protected static int maxBitRate = 115200;
/*  124 */   private static int portListMode = 0;
/*      */   protected ErrorStatusHandler esh;
/*  126 */   private String nativeLib = null;
/*  127 */   private int port = -1;
/*  128 */   private boolean isOpen = false;
/*  129 */   private boolean isReady = false;
/*  130 */   private String name = "Serial Port";
/*      */   private SerialConfig config;
/*      */   private FlowControlTask flowControl;
/*      */   private SerialServerTask ssTask;
/*      */   private boolean wrSinceTxBufEmpty;
/*      */   private boolean rdSinceRxBufCheck;
/*  135 */   private boolean tex = true;
/*      */ 
/*  137 */   private static String osName = ""; private static String osArch = ""; private static String vendor = ""; private static String jdkVer = "";
/*  138 */   private static String devName = "";
/*  139 */   private static boolean supGetPortList = false;
/*  140 */   private static boolean javaGetDataTimeout = false;
/*  141 */   private static boolean supSendBreak = true;
/*  142 */   private static boolean supSigBreak = false;
/*  143 */   private static boolean supSigFrameErr = false;
/*  144 */   private static boolean supSigOverrun = false;
/*  145 */   private static boolean supSigParityErr = false;
/*  146 */   private static boolean supRxOverflow = false;
/*  147 */   private static boolean supSigRing = false;
/*  148 */   private static boolean supSigCTS = false;
/*  149 */   private static boolean supSigDSR = false;
/*  150 */   private static boolean supSigCD = false;
/*  151 */   private static boolean supSetRTS = false;
/*  152 */   private static boolean supSetDTR = false;
/*  153 */   private static boolean supRxReadyCount = false;
/*  154 */   private static boolean supTxBufCount = false;
/*  155 */   private static boolean supSetTimeoutTx = false;
/*  156 */   private static boolean supGetTimeoutTx = false;
/*  157 */   private static boolean supGetStatusAPM = false;
/*  158 */   private static boolean supSetPowerMode = false;
/*  159 */   private static boolean supGetResumeState = false;
/*  160 */   private static boolean supInstanceLock = false;
/*  161 */   private static boolean supParityMarkSpace = false;
/*      */ 
/*  163 */   private static boolean supNoIndexBitRate = false;
/*  164 */   private static boolean supDriverInfo = false;
/*      */   private static NativeHandleMutex mutExSem;
/*  166 */   private static boolean osFamilyWindows = false;
/*      */ 
/*      */   public static String getLibAndPath64()
/*      */   {
/*  218 */     return LIB_PATH + (OSInfo.isMac() ? "ddmsDTWSerialPort-64.jnilib" : "ddmsDTWSerialPort-64.dll");
/*      */   }
/*      */ 
/*      */   public static String getLibAndPath32()
/*      */   {
/*  227 */     return LIB_PATH + (OSInfo.isMac() ? "ddmsDTWSerialPort.jnilib" : "ddmsDTWSerialPort.dll");
/*      */   }
/*      */ 
/*      */   private static void loadLibrary(String paramString)
/*      */     throws UnsatisfiedLinkError, FileNotFoundException
/*      */   {
/*  240 */     displayMsg("loading library " + paramString);
/*  241 */     if (new File(paramString).exists()) {
/*  242 */       System.load(paramString);
/*      */     } else {
/*  244 */       displayMsg("ERROR: cannot locate library " + paramString);
/*  245 */       throw new FileNotFoundException("Cannot find native library: " + jspLib);
/*      */     }
/*      */ 
/*  248 */     jspLibLoaded = true;
/*  249 */     displayMsg("SerialPort class loaded: " + jspLib);
/*      */   }
/*      */ 
/*      */   private static void setNativeLibName()
/*      */   {
/*  254 */     String str1 = System.getProperty("JSP_OSNAME");
/*  255 */     String str2 = System.getProperty("JSP_OSARCH");
/*  256 */     if ((str1 != null) && (str2 != null)) {
/*  257 */       osName = str1;
/*  258 */       osArch = str2;
/*      */     }
/*      */ 
/*  262 */     if ((osName.equals("Windows 95")) || (osName.equals("Windows 98")) || (osName.equals("Windows Me")) || (osName.startsWith("Windows NT")) || (osName.equals("Windows XP")) || (osName.equals("Windows 2000")) || (osName.equals("Windows 2003")) || (osName.equals("Windows 2008")) || (osName.equals("Windows Vista")) || (osName.equals("Windows Server 2008")) || (osName.equals("Windows 7")) || (osName.equals("Windows NT (unknown)")))
/*      */     {
/*  276 */       osFamilyWindows = true;
/*  277 */       jspLib = "ddmsDTWSerialPort.dll";
/*  278 */       if ((jdkVer.equals("1.0.2")) || (jdkVer.equals("102"))) {
/*  279 */         jspLib = "jspWinNm";
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  288 */     if (osName.equals("EPOC")) {
/*  289 */       if (osArch.equals("x86")) jspLib = "jspEpocx86";
/*  290 */       if (osArch.equals("arm")) jspLib = "jspEpocArm";
/*      */     }
/*      */ 
/*  293 */     if ((osName.equals("Windows CE")) || (osName.equals("WindowsCE")) || (osName.equals("Windows CE  Java")))
/*      */     {
/*  297 */       if (osArch.equalsIgnoreCase("MIPS"))
/*  298 */         jspLib = "jspWceMips";
/*  299 */       if (osArch.equalsIgnoreCase("SH3"))
/*  300 */         jspLib = "jspWceSh3";
/*  301 */       if (osArch.equalsIgnoreCase("SH4"))
/*  302 */         jspLib = "jspWceSh4";
/*  303 */       if ((osArch.equalsIgnoreCase("ARM")) || (osArch.equalsIgnoreCase("Strong ARM")) || (osArch.equalsIgnoreCase("XSCALE")))
/*      */       {
/*  306 */         jspLib = "jspWceArm";
/*  307 */       }if (osArch.equalsIgnoreCase("x86")) {
/*  308 */         jspLib = "jspWceX86";
/*      */       }
/*      */     }
/*  311 */     if (osName.equals("OS/2")) {
/*  312 */       jspLib = "jspos2j";
/*      */     }
/*  314 */     if (osName.equals("Mac OS")) {
/*  315 */       jspLib = "jspMac";
/*      */     }
/*  317 */     if (osName.equals("Mac OS X")) {
/*  318 */       jspLib = "jspMacOSX";
/*      */     }
/*  320 */     if (osName.equals("Linux")) {
/*  321 */       if ((osArch.equals("x86")) || (osArch.equals("i386")) || (osArch.equals("i486")) || (osArch.equals("i586")) || (osArch.equals("i686")))
/*      */       {
/*  324 */         jspLib = "jspLux86";
/*  325 */       }if ((osArch.equals("armv4l")) || (osArch.equals("arm")) || (osArch.equals("armv5tel")) || (osArch.equals("armv5l")))
/*      */       {
/*  327 */         jspLib = "jspLuxArm";
/*  328 */       }if (osArch.equals("amd64"))
/*  329 */         jspLib = "libLux86_64bit";
/*      */     }
/*  331 */     if ((osName.equals("FreeBSD")) && (
/*  332 */       (osArch.equals("i386")) || (osArch.equals("x86"))))
/*  333 */       jspLib = "jspBsdx86";
/*  334 */     if ((osName.equals("Solaris")) || (osName.equals("SunOS"))) {
/*  335 */       if (osArch.equals("x86")) jspLib = "jspSolx86";
/*  336 */       if (osArch.equals("sparc")) jspLib = "jspSolSparc";
/*      */     }
/*  338 */     if ((osName.equals("HP-UX")) && ((osArch.equals("PA-RISC")) || (osArch.equals("PA_RISC")) || (osArch.equals("PA_RISC2.0"))))
/*      */     {
/*  341 */       jspLib = "jspHpxPaRisc";
/*      */     }
/*  343 */     if ((osName.equals("Irix")) && (osArch.equals("mips"))) {
/*  344 */       jspLib = "jspIrxMips";
/*      */     }
/*  346 */     if ((osName.equals("AIX")) && (
/*  347 */       (osArch.equals("POWER_PC")) || (osArch.equals("ppc")))) {
/*  348 */       jspLib = "jspAixPpc";
/*      */     }
/*  350 */     if (((osName.equals("UnixWare")) || (osName.equals("OpenServer"))) && (osArch.equals("IA32")))
/*      */     {
/*  352 */       jspLib = "jspUxWareIA32";
/*      */     }
/*  354 */     if ((osName.equals("OSF1")) && (osArch.equals("alpha"))) {
/*  355 */       jspLib = "jspTru64Alpha";
/*      */     }
/*  357 */     if (osName.toLowerCase().indexOf("netware") != -1)
/*  358 */       jspLib = "JspNw";
/*      */   }
/*      */ 
/*      */   static void displayMsg(String paramString)
/*      */   {
/*  371 */     System.out.println("SerialPortLocal-" + paramString);
/*      */   }
/*      */ 
/*      */   public SerialPortLocal()
/*      */   {
/*  384 */     if (!jspLibLoaded) {
/*  385 */       if (jspLib == null) setNativeLibName();
/*  386 */       displayMsg("Attempt to load: " + jspLib);
/*  387 */       System.loadLibrary(jspLib);
/*      */ 
/*  389 */       displayMsg("SerialPort class loaded: " + jspLib);
/*      */     }
/*  391 */     osName = System.getProperty("os.name");
/*  392 */     this.config = new SerialConfig(devName);
/*      */   }
/*      */ 
/*      */   public SerialPortLocal(SerialConfig paramSerialConfig)
/*      */     throws IOException
/*      */   {
/*  404 */     osName = System.getProperty("os.name");
/*  405 */     this.config = paramSerialConfig;
/*  406 */     open();
/*      */   }
/*      */ 
/*      */   public void open()
/*      */     throws IOException
/*      */   {
/*  431 */     if (!osName.equals("Windows 95")) {
/*  432 */       this.config.setHardFlow(false);
/*      */     }
/*      */ 
/*  435 */     srvTaskCheck();
/*      */     int i;
/*  436 */     synchronized (mutExSem) { i = SerOpenPort(this.config);
/*      */     }
/*  438 */     if (i != 0) {
/*  439 */       ??? = this.config.getPortNameString() + ": SerOpenPort failed: ";
/*      */       String str;
/*  440 */       if (i == 1000)
/*  441 */         str = (String)??? + "Port is already open";
/*  442 */       else if (i == 1002)
/*  443 */         str = (String)??? + "Port is in use";
/*  444 */       else if (i == 1003)
/*  445 */         str = (String)??? + "Port not valid";
/*  446 */       else if (i == 1004)
/*  447 */         str = (String)??? + "2: No such device";
/*  448 */       else if (i == 1006)
/*  449 */         str = (String)??? + "Permission denied";
/*  450 */       else if (i == 1007)
/*  451 */         str = (String)??? + "Device busy";
/*  452 */       else if (i == 1008)
/*  453 */         str = (String)??? + "19: No such device";
/*      */       else {
/*  455 */         str = (String)??? + ": " + i;
/*      */       }
/*  457 */       throw new IOException(str);
/*      */     }
/*      */ 
/*  464 */     this.isOpen = true;
/*  465 */     this.wrSinceTxBufEmpty = false;
/*  466 */     this.rdSinceRxBufCheck = false;
/*  467 */     this.port = (this.config.getPort() - 1);
/*      */ 
/*  469 */     configure(this.config);
/*      */ 
/*  483 */     if (osName.equals("Mac OS")) {
/*  484 */       setTimeoutRx(2000);
/*  485 */       setTimeoutTx(2000);
/*      */     } else {
/*  487 */       setTimeoutRx(0);
/*  488 */       int j = 0;
/*      */ 
/*  490 */       int k = this.config.getTxLen();
/*  491 */       int m = this.config.getBitRateNumber(getBitRate()) / 10;
/*  492 */       int n = 1000000 / m;
/*  493 */       j = k * n / 1000 * 100;
/*      */ 
/*  495 */       if (supSetTimeoutTx) setTimeoutTx(j);
/*      */     }
/*      */   }
/*      */ 
/*      */   public void configure()
/*      */     throws IOException
/*      */   {
/*  506 */     configure(this.config);
/*      */   }
/*      */ 
/*      */   public void configure(SerialConfig paramSerialConfig)
/*      */     throws IOException
/*      */   {
/*  518 */     if (!this.isOpen) {
/*  519 */       throw new IOException("configure: Port is not open");
/*      */     }
/*  521 */     if (this.flowControl != null) { this.flowControl.abort(); this.flowControl = null;
/*      */     }
/*  523 */     if (!supNoIndexBitRate)
/*      */     {
/*  525 */       i = paramSerialConfig.getBitRateIndex(paramSerialConfig.getBitRate());
/*  526 */       if (i >= 0) paramSerialConfig.setBitRate(i);
/*  527 */       else if (!paramSerialConfig.validBitRateIndex())
/*  528 */         throw new IOException("Unsupported Serial Port Bit Rate");
/*      */     }
/*  530 */     if ((!supParityMarkSpace) && 
/*  531 */       (paramSerialConfig.getParity() > 2)) {
/*  532 */       throw new IOException("Unsupported Mark/Space parity");
/*      */     }
/*      */ 
/*  535 */     int i = 0;
/*  536 */     synchronized (mutExSem) { i = SerConfigure(paramSerialConfig);
/*      */     }
/*  538 */     if (i != 0) {
/*  539 */       ??? = new Integer(i);
/*  540 */       throw new IOException("Configure failed: " + ((Integer)???).toString());
/*      */     }
/*      */ 
/*  543 */     boolean bool = paramSerialConfig.getHardFlow();
/*  544 */     if ((bool) && (osName.equals("Windows 95"))) {
/*  545 */       int j = paramSerialConfig.getHandshake();
/*  546 */       if ((j == 2) || (j == 3))
/*      */       {
/*  548 */         if (this.flowControl == null) {
/*  549 */           this.flowControl = new FlowControlTask(this);
/*  550 */           this.flowControl.start();
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public void setConfig(SerialConfig paramSerialConfig)
/*      */   {
/*  565 */     if (!this.isOpen) paramSerialConfig.setPortNum(this.port + 1);
/*  566 */     this.config = paramSerialConfig;
/*  567 */     devName = paramSerialConfig.getPortNameString();
/*      */   }
/*      */ 
/*      */   public SerialConfig getConfig()
/*      */   {
/*  574 */     return this.config;
/*      */   }
/*      */ 
/*      */   public void close()
/*      */     throws IOException
/*      */   {
/*  594 */     this.wrSinceTxBufEmpty = false;
/*  595 */     this.rdSinceRxBufCheck = false;
/*  596 */     if (this.flowControl != null) this.flowControl.abort();
/*      */ 
/*  598 */     if (this.isOpen) {
/*  599 */       this.isOpen = false;
/*      */       int i;
/*  600 */       synchronized (mutExSem) { i = SerClosePort(this.port); }
/*  601 */       this.port = -1;
/*      */ 
/*  603 */       if (i != 0)
/*  604 */         throw new IOException("ClosePort failed");
/*      */     }
/*      */   }
/*      */ 
/*      */   public void reset()
/*      */     throws IOException
/*      */   {
/*  613 */     int j = 0;
/*      */ 
/*  615 */     if (!this.isOpen) {
/*  616 */       throw new IOException("reset: Port is not open");
/*      */     }
/*  618 */     if (supSetTimeoutTx) j = getTimeoutTx();
/*  619 */     close();
/*      */ 
/*  622 */     srvTaskCheck();
/*      */     int i;
/*  623 */     synchronized (mutExSem) { i = SerOpenPort(this.config); }
/*  624 */     if (i != 0) {
/*  625 */       i = checkBusy(i);
/*  626 */       if (i != 0)
/*  627 */         throw new IOException("reset: SerOpenPort failed: " + i);
/*      */     }
/*  629 */     this.isOpen = true;
/*  630 */     this.port = (this.config.getPort() - 1);
/*  631 */     if (supSetTimeoutTx) setTimeoutTx(j);
/*      */ 
/*  633 */     configure(this.config);
/*      */   }
/*      */ 
/*      */   private int checkBusy(int paramInt)
/*      */   {
/*  640 */     if ((!osName.equals("Solaris")) && (!osName.equals("SunOS")))
/*      */     {
/*  642 */       return paramInt;
/*      */     }
/*  644 */     int i = 16;
/*  645 */     long l1 = System.currentTimeMillis();
/*  646 */     long l2 = l1 + 3000L;
/*  647 */     while (System.currentTimeMillis() < l2) {
/*      */       try { Thread.sleep(500L); } catch (InterruptedException localInterruptedException) {
/*  649 */       }synchronized (mutExSem) { paramInt = SerOpenPort(this.config); }
/*  650 */       if (paramInt == 0) break; if (paramInt == i) continue;
/*      */     }
/*  652 */     return paramInt;
/*      */   }
/*      */ 
/*      */   public void resetPortDriver(SerialConfig paramSerialConfig)
/*      */     throws IOException
/*      */   {
/*  668 */     if (!supDriverInfo) {
/*  669 */       throw new IOException("resetPortDriver not supported on " + osName);
/*      */     }
/*  671 */     if (this.isOpen == true) {
/*  672 */       throw new IOException("resetPortDriver: Port must be closed");
/*      */     }
/*  674 */     int i = 0;
/*  675 */     synchronized (mutExSem) { i = SerResetPortDriver(paramSerialConfig);
/*      */     }
/*  677 */     if (i != 0) {
/*  678 */       ??? = new Integer(i);
/*  679 */       throw new IOException("Reset port driver failed: " + ((Integer)???).toString());
/*      */     }
/*      */   }
/*      */ 
/*      */   public void putByte(byte paramByte)
/*      */     throws IOException
/*      */   {
/*  693 */     int i = SerPutByte(this.port, paramByte);
/*  694 */     this.wrSinceTxBufEmpty = true;
/*      */ 
/*  696 */     if (i != 0) throw new IOException("PutByte failed: " + i);
/*      */   }
/*      */ 
/*      */   public void putString(String paramString)
/*      */     throws IOException
/*      */   {
/*  711 */     byte[] arrayOfByte = paramString.getBytes();
/*      */ 
/*  713 */     int i = arrayOfByte.length;
/*  714 */     if (i == 0)
/*      */     {
/*  716 */       return;
/*      */     }
/*      */ 
/*  719 */     int j = SerPutData(this.port, arrayOfByte, i);
/*  720 */     this.wrSinceTxBufEmpty = true;
/*      */ 
/*  722 */     if (j != i) {
/*  723 */       String str = new String("PutString: sent only " + j + " of " + i + " bytes");
/*  724 */       throw new IOException(str);
/*      */     }
/*      */   }
/*      */ 
/*      */   public void putData(byte[] paramArrayOfByte)
/*      */     throws IOException
/*      */   {
/*  752 */     int i = paramArrayOfByte.length;
/*  753 */     if (i == 0)
/*      */     {
/*  755 */       return;
/*      */     }
/*      */ 
/*  758 */     int j = SerPutData(this.port, paramArrayOfByte, i);
/*  759 */     this.wrSinceTxBufEmpty = true;
/*      */ 
/*  761 */     if (j != i)
/*      */     {
/*  763 */       String str = new String("PutData1: Sent only " + j + " of " + i + " bytes");
/*  764 */       throw new IOException(str);
/*      */     }
/*      */   }
/*      */ 
/*      */   public void putData(byte[] paramArrayOfByte, int paramInt)
/*      */     throws IOException
/*      */   {
/*  783 */     if (paramInt > paramArrayOfByte.length) {
/*  784 */       throw new IOException("putData: count out of bounds");
/*      */     }
/*  786 */     int i = SerPutData(this.port, paramArrayOfByte, paramInt);
/*  787 */     this.wrSinceTxBufEmpty = true;
/*      */ 
/*  789 */     if (i != paramInt) {
/*  790 */       String str = new String("PutData2: Sent only " + i + " of " + paramInt + " bytes");
/*  791 */       throw new IOException(str);
/*      */     }
/*      */   }
/*      */ 
/*      */   public void putData(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/*      */     throws IOException
/*      */   {
/*  806 */     if (paramInt1 + paramInt2 > paramArrayOfByte.length) {
/*  807 */       throw new IOException("putData3: count out of bounds");
/*      */     }
/*  809 */     byte[] arrayOfByte = new byte[paramInt2];
/*  810 */     System.arraycopy(paramArrayOfByte, paramInt1, arrayOfByte, 0, paramInt2);
/*  811 */     int i = SerPutData(this.port, arrayOfByte, paramInt2);
/*  812 */     this.wrSinceTxBufEmpty = true;
/*      */ 
/*  814 */     if (i != paramInt2) {
/*  815 */       String str = new String("putData3: Sent only " + i + " of " + paramInt2 + " bytes");
/*  816 */       throw new IOException(str);
/*      */     }
/*      */   }
/*      */ 
/*      */   public int getByte()
/*      */     throws IOException
/*      */   {
/*  829 */     if ((javaGetDataTimeout) && (SerGetTimeoutRx(this.port) > 0)) { long l1 = System.currentTimeMillis() + SerGetTimeoutRx(this.port);
/*      */       long l2;
/*      */       do { if (SerRxReadyCount(this.port) > 0) {
/*  834 */           this.rdSinceRxBufCheck = true;
/*  835 */           return SerGetByte(this.port);
/*      */         }
/*  837 */         l2 = System.currentTimeMillis();
/*  838 */         Thread.yield(); }
/*  839 */       while (l2 < l1);
/*  840 */       return -1;
/*      */     }
/*      */ 
/*  843 */     int i = SerGetByte(this.port);
/*  844 */     this.rdSinceRxBufCheck = true;
/*      */ 
/*  846 */     return i;
/*      */   }
/*      */ 
/*      */   public int getData(byte[] paramArrayOfByte)
/*      */     throws IOException
/*      */   {
/*  862 */     int i = getData(paramArrayOfByte, 0, paramArrayOfByte.length);
/*  863 */     this.rdSinceRxBufCheck = true;
/*      */ 
/*  865 */     return i;
/*      */   }
/*      */ 
/*      */   public int getData(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/*      */     throws IOException
/*      */   {
/*  882 */     int i = 0;
/*  883 */     if (paramInt2 > paramArrayOfByte.length) paramInt2 = paramArrayOfByte.length;
/*  884 */     if ((javaGetDataTimeout) && (SerGetTimeoutRx(this.port) > 0)) { long l1 = System.currentTimeMillis() + SerGetTimeoutRx(this.port);
/*      */       long l2;
/*      */       do { i = SerRxReadyCount(this.port);
/*  889 */         if (i >= paramInt2)
/*      */           break;
/*  891 */         l2 = System.currentTimeMillis();
/*  892 */         Thread.yield(); }
/*  893 */       while (l2 < l1);
/*  894 */       if (i < paramInt2) return 0;
/*      */     }
/*      */     Object localObject;
/*  897 */     if (paramInt1 != 0) {
/*  898 */       localObject = new byte[paramInt2];
/*  899 */       i = SerGetData(this.port, localObject, paramInt2);
/*  900 */       if (i > 0) System.arraycopy(localObject, 0, paramArrayOfByte, paramInt1, i); 
/*      */     }
/*      */     else
/*      */     {
/*  903 */       i = SerGetData(this.port, paramArrayOfByte, paramInt2);
/*      */     }
/*  905 */     this.rdSinceRxBufCheck = true;
/*  906 */     if (i == -1) {
/*  907 */       localObject = new String("getData: failed");
/*  908 */       throw new IOException((String)localObject);
/*      */     }
/*      */ 
/*  911 */     return i;
/*      */   }
/*      */ 
/*      */   public int rxFlush()
/*      */     throws IOException
/*      */   {
/*  920 */     int i = SerRxFlush(this.port);
/*      */ 
/*  922 */     if (i == -1) throw new IOException("rxFlush failed");
/*      */ 
/*  924 */     return i;
/*      */   }
/*      */ 
/*      */   public int txFlush()
/*      */     throws IOException
/*      */   {
/*  933 */     int i = SerTxFlush(this.port);
/*      */ 
/*  935 */     if (i == -1) throw new IOException("txFlush failed");
/*      */ 
/*  937 */     return i;
/*      */   }
/*      */ 
/*      */   public int txDrain()
/*      */     throws IOException
/*      */   {
/*  948 */     int i = SerTxDrain(this.port);
/*      */ 
/*  950 */     if (i == -1) throw new IOException("txDrain failed");
/*      */ 
/*  952 */     return i;
/*      */   }
/*      */ 
/*      */   public int rxReadyCount()
/*      */     throws IOException
/*      */   {
/*  961 */     if (!supRxReadyCount) {
/*  962 */       throw new IOException("rxReadyCount not supported on " + osName);
/*      */     }
/*      */ 
/*  966 */     int i = SerRxReadyCount(this.port);
/*      */ 
/*  977 */     if (i == -1) {
/*  978 */       if (this.esh != null) this.esh.sioErrorEvent(10); else
/*  979 */         throw new IOException("rxReadyCount failed");
/*      */     }
/*  981 */     return i;
/*      */   }
/*      */ 
/*      */   public int txBufCount()
/*      */     throws IOException
/*      */   {
/*  994 */     if (!supTxBufCount) {
/*  995 */       throw new IOException("txBufCount not supported on " + osName);
/*      */     }
/*  997 */     if (this.port < 0) throw new IOException("txBufCount: Port not open");
/*      */ 
/*  999 */     int i = SerTxBufCount(this.port);
/*      */ 
/* 1002 */     if (i == -1) throw new IOException("txBufCount failed");
/*      */ 
/* 1004 */     return i;
/*      */   }
/*      */ 
/*      */   public void clearReadSinceRxBufCheck()
/*      */   {
/* 1012 */     this.rdSinceRxBufCheck = false;
/*      */   }
/*      */ 
/*      */   public boolean sendSinceTxBufEmpty()
/*      */   {
/* 1020 */     return this.wrSinceTxBufEmpty;
/*      */   }
/*      */ 
/*      */   public void clearSendSinceTxBufEmpty()
/*      */   {
/* 1036 */     this.wrSinceTxBufEmpty = false;
/*      */   }
/*      */ 
/*      */   public boolean readSinceRxBufCheck()
/*      */   {
/* 1044 */     return this.rdSinceRxBufCheck;
/*      */   }
/*      */ 
/*      */   public boolean rxOverflow()
/*      */     throws IOException
/*      */   {
/* 1055 */     if (!supRxOverflow) {
/* 1056 */       throw new IOException("rxOverflow not supported on " + osName);
/*      */     }
/* 1058 */     if (this.port < 0) throw new IOException("rxOverflow: Port not open");
/*      */ 
/* 1060 */     int i = SerRxOverflow(this.port);
/*      */ 
/* 1062 */     if (i == -1) throw new IOException("rxOverflow failed");
/*      */ 
/* 1064 */     return i == 1;
/*      */   }
/*      */ 
/*      */   public boolean sigDSR()
/*      */     throws IOException
/*      */   {
/* 1073 */     if (this.port < 0) throw new IOException("sigDSR: Port not open");
/*      */ 
/* 1075 */     int i = SerSigDSR(this.port);
/*      */ 
/* 1077 */     if (i == -1) throw new IOException("sigDSR failed");
/*      */ 
/* 1079 */     return i == 1;
/*      */   }
/*      */ 
/*      */   public boolean sigCTS()
/*      */     throws IOException
/*      */   {
/* 1088 */     if (this.port < 0) throw new IOException("sigCTS: Port not open");
/*      */ 
/* 1090 */     int i = SerSigCTS(this.port);
/*      */ 
/* 1092 */     if (i == -1) throw new IOException("sigCTS failed");
/*      */ 
/* 1094 */     return i == 1;
/*      */   }
/*      */ 
/*      */   public boolean sigCD()
/*      */     throws IOException
/*      */   {
/* 1104 */     if (!supSigCD) {
/* 1105 */       throw new IOException("sigCD not supported on " + osName);
/*      */     }
/* 1107 */     if (this.port < 0) throw new IOException("sigCD: Port not open");
/*      */ 
/* 1109 */     int i = SerSigCD(this.port);
/*      */ 
/* 1111 */     if (i == -1) throw new IOException("sigCD failed");
/*      */ 
/* 1113 */     return i == 1;
/*      */   }
/*      */ 
/*      */   public boolean sigFrameErr()
/*      */     throws IOException
/*      */   {
/* 1124 */     if (!supSigFrameErr) {
/* 1125 */       throw new IOException("sigFrameErr not supported on " + osName);
/*      */     }
/* 1127 */     if (this.port < 0) throw new IOException("sigFrameErr: Port not open");
/*      */ 
/* 1129 */     int i = SerSigFrameErr(this.port);
/*      */ 
/* 1131 */     if (i == -1) throw new IOException("sigFrameErr failed");
/*      */ 
/* 1133 */     return i == 1;
/*      */   }
/*      */ 
/*      */   public boolean sigOverrun()
/*      */     throws IOException
/*      */   {
/* 1144 */     if (!supSigOverrun) {
/* 1145 */       throw new IOException("sigOverrun not supported on " + osName);
/*      */     }
/* 1147 */     if (this.port < 0) throw new IOException("sigOverrun: Port not open");
/*      */ 
/* 1149 */     int i = SerSigOverrun(this.port);
/*      */ 
/* 1151 */     if (i == -1) throw new IOException("sigOverrun failed");
/*      */ 
/* 1153 */     return i == 1;
/*      */   }
/*      */ 
/*      */   public boolean sigParityErr()
/*      */     throws IOException
/*      */   {
/* 1164 */     if (!supSigParityErr) {
/* 1165 */       throw new IOException("sigParityErr not supported on " + osName);
/*      */     }
/* 1167 */     if (this.port < 0) throw new IOException("sigParityErr: Port not open");
/*      */ 
/* 1169 */     int i = SerSigParityErr(this.port);
/*      */ 
/* 1171 */     return i == 1;
/*      */   }
/*      */ 
/*      */   public boolean sigRing()
/*      */     throws IOException
/*      */   {
/* 1181 */     if (!supSigRing) {
/* 1182 */       throw new IOException("sigRing not supported on " + osName);
/*      */     }
/* 1184 */     if (this.port < 0) throw new IOException("sigRing: Port not open");
/*      */ 
/* 1186 */     int i = SerSigRing(this.port);
/*      */ 
/* 1188 */     return i == 1;
/*      */   }
/*      */ 
/*      */   public boolean sigBreak()
/*      */     throws IOException
/*      */   {
/* 1199 */     if (!supSigBreak) {
/* 1200 */       throw new IOException("sigBreak not supported on " + osName);
/*      */     }
/* 1202 */     if (this.port < 0) throw new IOException("sigBreak: Port not open");
/*      */ 
/* 1204 */     int i = SerSigBreak(this.port);
/*      */ 
/* 1206 */     return i == 1;
/*      */   }
/*      */ 
/*      */   public void setDTR(boolean paramBoolean)
/*      */     throws IOException
/*      */   {
/* 1217 */     if (!supSetDTR) {
/* 1218 */       throw new IOException("setDTR not supported on " + osName);
/*      */     }
/* 1220 */     if (this.port < 0) throw new IOException("setDTR: Port not open");
/*      */ 
/* 1222 */     int i = SerSetDTR(this.port, paramBoolean);
/*      */ 
/* 1224 */     if (i == -1) throw new IOException("setDTR failed");
/*      */   }
/*      */ 
/*      */   public void setRTS(boolean paramBoolean)
/*      */     throws IOException
/*      */   {
/* 1237 */     if (!supSetRTS) {
/* 1238 */       throw new IOException("setRts not supported on " + osName);
/*      */     }
/* 1240 */     if (this.port < 0) throw new IOException("setRTS: Port not open");
/*      */ 
/* 1242 */     int i = SerSetRTS(this.port, paramBoolean);
/*      */ 
/* 1244 */     if (i == -1) throw new IOException("setRTS failed");
/*      */   }
/*      */ 
/*      */   public void sendBreak(int paramInt)
/*      */     throws IOException
/*      */   {
/* 1254 */     if (!supSendBreak) {
/* 1255 */       throw new IOException("sendBreak not supported on " + osName);
/*      */     }
/* 1257 */     if (this.port < 0) throw new IOException("sendBreak: Port not open");
/*      */ 
/* 1259 */     int i = SerSendBreak(this.port, paramInt);
/*      */ 
/* 1261 */     if (i == -1) throw new IOException("sendBreak failed");
/*      */   }
/*      */ 
/*      */   public int getTimeoutRx()
/*      */     throws IOException
/*      */   {
/* 1270 */     int i = SerGetTimeoutRx(this.port);
/*      */ 
/* 1272 */     return i;
/*      */   }
/*      */ 
/*      */   public int getTimeoutTx()
/*      */     throws IOException
/*      */   {
/* 1281 */     int i = SerGetTimeoutTx(this.port);
/*      */ 
/* 1283 */     return i;
/*      */   }
/*      */ 
/*      */   public void setTimeoutRx(int paramInt)
/*      */     throws IOException
/*      */   {
/* 1295 */     int i = SerSetTimeoutRx(this.port, paramInt);
/*      */ 
/* 1297 */     if (i == -1) throw new IOException("setTimeoutRx failed");
/*      */   }
/*      */ 
/*      */   public void setTimeoutTx(int paramInt)
/*      */     throws IOException
/*      */   {
/* 1309 */     int i = SerSetTimeoutTx(this.port, paramInt);
/*      */ 
/* 1311 */     if (i == -1) throw new IOException("setTimeoutTx failed");
/*      */   }
/*      */ 
/*      */   public void setErrorStatusHandler(ErrorStatusHandler paramErrorStatusHandler)
/*      */   {
/* 1328 */     this.esh = paramErrorStatusHandler;
/*      */   }
/*      */ 
/*      */   public static void setPowerMode(int paramInt1, int paramInt2)
/*      */     throws IOException
/*      */   {
/* 1342 */     if (!supSetPowerMode) return;
/*      */ 
/* 1344 */     int i = 0;
/* 1345 */     synchronized (mutExSem) {
/* 1346 */       i = SerSetPowerMode(paramInt1, paramInt2);
/*      */     }
/*      */ 
/* 1349 */     if (i != 0) throw new IOException("setPowerMode failed: " + i);
/*      */   }
/*      */ 
/*      */   public static int getPowerMode(int paramInt)
/*      */     throws IOException
/*      */   {
/* 1363 */     if (!supSetPowerMode) return -1;
/*      */ 
/* 1365 */     int i = 0;
/* 1366 */     synchronized (mutExSem) {
/* 1367 */       i = SerGetPowerMode(paramInt);
/*      */     }
/*      */ 
/* 1370 */     if (i >= 5000) throw new IOException("getPowerMode failed: " + i);
/* 1371 */     return i;
/*      */   }
/*      */ 
/*      */   public static int getResumeState(boolean paramBoolean)
/*      */   {
/* 1383 */     if (!supGetResumeState) return -1;
/*      */ 
/* 1385 */     return SerGetResumeState(paramBoolean);
/*      */   }
/*      */ 
/*      */   public static int getStatusAPM()
/*      */   {
/* 1404 */     if (!supGetStatusAPM) return -1;
/*      */ 
/* 1406 */     int i = SerGetStatusAPM();
/*      */ 
/* 1409 */     return i;
/*      */   }
/*      */ 
/*      */   public int getPortNum()
/*      */   {
/* 1416 */     return this.port;
/*      */   }
/*      */ 
/*      */   public void setName(String paramString)
/*      */   {
/* 1423 */     this.name = paramString;
/*      */   }
/*      */ 
/*      */   public String getName()
/*      */   {
/* 1430 */     return this.name;
/*      */   }
/*      */ 
/*      */   public String getDevName()
/*      */   {
/* 1437 */     return new String(this.config.getPortName());
/*      */   }
/*      */ 
/*      */   public static PortDriverInfo[] getDriverInfoList()
/*      */     throws IOException
/*      */   {
/* 1444 */     if (!supDriverInfo)
/* 1445 */       throw new IOException("Not supported on this platform");
/* 1446 */     if (portList.size() < 1) {
/* 1447 */       throw new IOException("Must call SerialPortLocal.getPortList method first");
/*      */     }
/*      */ 
/* 1450 */     PortDriverInfo[] arrayOfPortDriverInfo = new PortDriverInfo[portListInfo.size()];
/* 1451 */     Enumeration localEnumeration = portListInfo.elements();
/*      */ 
/* 1453 */     int i = 0;
/* 1454 */     while (localEnumeration.hasMoreElements()) {
/* 1455 */       arrayOfPortDriverInfo[(i++)] = ((PortDriverInfo)localEnumeration.nextElement());
/*      */     }
/*      */ 
/* 1458 */     return arrayOfPortDriverInfo;
/*      */   }
/*      */ 
/*      */   public String getName(int paramInt)
/*      */   {
/* 1465 */     String str = "Not implemented";
/* 1466 */     if ((osName.equals("Mac OS") == true) || (osName.equals("Mac OS X") == true)) {
/* 1467 */       str = SerGetName(paramInt);
/*      */     }
/*      */ 
/* 1471 */     return str;
/*      */   }
/*      */ 
/*      */   public int getBuildNum()
/*      */   {
/* 1480 */     return buildNum;
/*      */   }
/*      */ 
/*      */   public int getLibVer()
/*      */   {
/* 1490 */     return SerGetLibVer();
/*      */   }
/*      */ 
/*      */   public static int setInstanceLock(int paramInt)
/*      */   {
/* 1507 */     if (!supInstanceLock) return -1;
/*      */ 
/* 1509 */     int i = SerSetInstanceLock(paramInt);
/* 1510 */     return i;
/*      */   }
/*      */ 
/*      */   public static int getInstanceLock()
/*      */   {
/* 1519 */     if (!supInstanceLock) return -1;
/*      */ 
/* 1521 */     int i = SerGetInstanceLock();
/*      */ 
/* 1523 */     return i;
/*      */   }
/*      */ 
/*      */   public boolean isOpen()
/*      */   {
/* 1530 */     return this.isOpen;
/*      */   }
/*      */ 
/*      */   private static void featureSetup()
/*      */   {
/* 1537 */     mutExSem = NativeHandleMutex.getInstance();
/*      */ 
/* 1539 */     if ((osName.equals("Windows 95")) || (osName.startsWith("Windows NT")) || (osName.equals("Windows 2000")) || (osName.equals("Windows 2003")) || (osName.equals("Windows Vista")) || (osName.equals("Windows Server 2008")) || (osName.equals("Windows 7")) || (osName.equals("Windows XP")) || (osName.equals("Windows 98")) || (osName.equals("Windows Me")) || (osName.equals("Windows CE")) || (osName.equals("WindowsCE")) || (osName.equals("OS/2")) || (osName.toLowerCase().indexOf("netware") != -1))
/*      */     {
/* 1556 */       if ((osName.equals("Windows 7")) || (osName.equals("Windows Server 2008")) || (osName.equals("Windows Vista")) || (osName.equals("Windows 2003")) || (osName.equals("Windows 2000")) || (osName.equals("Windows XP")))
/*      */       {
/* 1563 */         maxBitRate = 921600;
/* 1564 */         supDriverInfo = true;
/*      */       }
/*      */ 
/* 1567 */       supParityMarkSpace = true;
/* 1568 */       supInstanceLock = true;
/* 1569 */       supGetPortList = true;
/* 1570 */       supGetStatusAPM = true;
/* 1571 */       supSigBreak = true;
/* 1572 */       supSigFrameErr = true;
/* 1573 */       supSigParityErr = true;
/* 1574 */       supSigOverrun = true;
/* 1575 */       supRxOverflow = true;
/* 1576 */       supRxReadyCount = true;
/* 1577 */       supTxBufCount = true;
/* 1578 */       supSetTimeoutTx = true;
/* 1579 */       supGetTimeoutTx = true;
/* 1580 */       supSigRing = true;
/* 1581 */       supSetRTS = true;
/* 1582 */       supSetDTR = true;
/* 1583 */       supSigCD = true;
/* 1584 */       supSigCTS = true;
/* 1585 */       supSigDSR = true;
/* 1586 */       javaGetDataTimeout = false;
/*      */ 
/* 1588 */       supNoIndexBitRate = true;
/* 1589 */       if (osName.equals("OS/2")) supNoIndexBitRate = false;
/* 1590 */       if (osName.toLowerCase().indexOf("netware") != -1) {
/* 1591 */         javaGetDataTimeout = true;
/* 1592 */         supGetPortList = false;
/*      */       }
/*      */ 
/* 1595 */       devName = "COM1";
/*      */     }
/*      */ 
/* 1598 */     if (osName.equals("Mac OS"))
/*      */     {
/* 1600 */       maxBitRate = 921600;
/* 1601 */       supNoIndexBitRate = true;
/* 1602 */       supGetPortList = true;
/* 1603 */       supSigBreak = true;
/* 1604 */       supSigFrameErr = true;
/* 1605 */       supSigParityErr = true;
/* 1606 */       supSigOverrun = true;
/* 1607 */       supRxOverflow = true;
/* 1608 */       supRxReadyCount = true;
/* 1609 */       supTxBufCount = true;
/* 1610 */       supSetTimeoutTx = true;
/* 1611 */       supGetTimeoutTx = true;
/* 1612 */       supSigCTS = true;
/* 1613 */       supSetDTR = true;
/* 1614 */       javaGetDataTimeout = true;
/* 1615 */       devName = "Modem Port";
/*      */     }
/*      */ 
/* 1618 */     if (osName.equals("Mac OS X"))
/*      */     {
/* 1620 */       maxBitRate = 230400;
/* 1621 */       supParityMarkSpace = true;
/* 1622 */       supGetStatusAPM = true;
/* 1623 */       supGetPortList = true;
/*      */ 
/* 1629 */       supRxReadyCount = true;
/* 1630 */       supTxBufCount = true;
/*      */ 
/* 1633 */       supSigCTS = true;
/* 1634 */       supSigDSR = true;
/* 1635 */       supSigCD = true;
/* 1636 */       supSetDTR = true;
/* 1637 */       supSetRTS = true;
/* 1638 */       javaGetDataTimeout = true;
/* 1639 */       devName = "";
/*      */     }
/*      */ 
/* 1642 */     if ((osName.equals("Linux")) || (osName.equals("FreeBSD")) || (osName.equals("Solaris")) || (osName.equals("SunOS")) || (osName.equals("HP-UX")) || (osName.equals("Irix")) || (osName.equals("AIX")) || (osName.equals("OSF1")) || (osName.equals("UnixWare")))
/*      */     {
/* 1652 */       supParityMarkSpace = true;
/* 1653 */       supSigRing = true;
/* 1654 */       supSetRTS = true;
/* 1655 */       supSetDTR = true;
/* 1656 */       supSigCD = true;
/* 1657 */       supRxReadyCount = true;
/* 1658 */       supTxBufCount = true;
/* 1659 */       supSigCTS = true;
/* 1660 */       supSigDSR = true;
/* 1661 */       javaGetDataTimeout = true;
/*      */ 
/* 1666 */       devName = "/dev/tty0";
/*      */ 
/* 1668 */       if (osName.equals("Linux")) {
/* 1669 */         devName = "/dev/ttyS0";
/* 1670 */         supGetStatusAPM = true;
/* 1671 */         if ((osArch.equals("armv4l")) || (osArch.equals("arm"))) {
/* 1672 */           supSetPowerMode = true;
/* 1673 */           supGetResumeState = true;
/*      */         }
/*      */       }
/* 1676 */       if ((osName.equals("Solaris")) || (osName.equals("SunOS")))
/*      */       {
/* 1679 */         devName = "/dev/ttya";
/*      */       }
/*      */ 
/* 1682 */       if (osName.equals("Irix")) {
/* 1683 */         supSetRTS = false;
/* 1684 */         supSetDTR = false;
/* 1685 */         supSigCTS = false;
/*      */       }
/* 1687 */       if (osName.equals("UnixWare")) {
/* 1688 */         supSetRTS = false;
/*      */       }
/* 1690 */       if (osName.equals("OSF1")) {
/* 1691 */         devName = "/dev/tty00";
/*      */       }
/*      */     }
/*      */ 
/* 1695 */     if (osName.equals("EPOC")) {
/* 1696 */       supGetPortList = true;
/* 1697 */       supSendBreak = false;
/* 1698 */       supTxBufCount = true;
/* 1699 */       supSigFrameErr = true;
/* 1700 */       supSigParityErr = true;
/* 1701 */       supSigOverrun = true;
/* 1702 */       supRxReadyCount = true;
/* 1703 */       supSetTimeoutTx = true;
/* 1704 */       supGetTimeoutTx = true;
/* 1705 */       supSetRTS = true;
/* 1706 */       supSetDTR = true;
/* 1707 */       supSigCD = true;
/* 1708 */       supSigCTS = true;
/* 1709 */       supSigDSR = true;
/* 1710 */       javaGetDataTimeout = false;
/* 1711 */       supNoIndexBitRate = true;
/* 1712 */       devName = "COMM::0";
/*      */     }
/*      */   }
/*      */ 
/*      */   public int getMaxBitRate()
/*      */   {
/* 1719 */     return maxBitRate;
/*      */   }
/*      */ 
/*      */   public boolean isSupported(String paramString)
/*      */   {
/* 1725 */     if (paramString.equals("sigBreak")) return supSigBreak;
/* 1726 */     if (paramString.equals("sendBreak")) return supSendBreak;
/* 1727 */     if (paramString.equals("sigFrameErr")) return supSigFrameErr;
/* 1728 */     if (paramString.equals("sigOverrun")) return supSigOverrun;
/* 1729 */     if (paramString.equals("sigParityErr")) return supSigParityErr;
/* 1730 */     if (paramString.equals("rxOverflow")) return supRxOverflow;
/* 1731 */     if (paramString.equals("sigRing")) return supSigRing;
/* 1732 */     if (paramString.equals("sigCTS")) return supSigCTS;
/* 1733 */     if (paramString.equals("sigDSR")) return supSigDSR;
/* 1734 */     if (paramString.equals("sigCD")) return supSigCD;
/* 1735 */     if (paramString.equals("setRTS")) return supSetRTS;
/* 1736 */     if (paramString.equals("setDTR")) return supSetDTR;
/* 1737 */     if (paramString.equals("rxReadyCount")) return supRxReadyCount;
/* 1738 */     if (paramString.equals("txBufCount")) return supTxBufCount;
/* 1739 */     if (paramString.equals("setTimeoutTx")) return supSetTimeoutTx;
/* 1740 */     if (paramString.equals("noIndexBitRate")) return supNoIndexBitRate;
/* 1741 */     if (paramString.equals("getPortList")) return supGetPortList;
/* 1742 */     if (paramString.equals("setPowerMode")) return supSetPowerMode;
/* 1743 */     if (paramString.equals("getStatusAPM")) return supGetStatusAPM;
/* 1744 */     if (paramString.equals("InstanceLock")) return supInstanceLock;
/* 1745 */     if (paramString.equals("ParityMarkSpace")) return supParityMarkSpace;
/* 1746 */     if (paramString.equals("supDriverInfo")) return supDriverInfo;
/* 1747 */     return false;
/*      */   }
/*      */ 
/*      */   public static String[] getPortList()
/*      */     throws IOException
/*      */   {
/*      */     String[] arrayOfString;
/*      */     try
/*      */     {
/* 1771 */       arrayOfString = getPortListIO();
/*      */     } catch (UnsatisfiedLinkError localUnsatisfiedLinkError) {
/* 1773 */       throw new IOException("Driver Installation Error-" + localUnsatisfiedLinkError.getMessage());
/*      */     }
/*      */ 
/* 1776 */     return arrayOfString;
/*      */   }
/*      */ 
/*      */   private static String[] getPortListIO()
/*      */     throws IOException
/*      */   {
/* 1796 */     String str = System.getProperty("SERIAL_PORT_LIST");
/* 1797 */     if ((str != null) && (str.trim().length() > 4)) {
/* 1798 */       StringTokenizer localStringTokenizer = new StringTokenizer(str, ";:");
/* 1799 */       while (localStringTokenizer.hasMoreTokens()) {
/* 1800 */         addPortName(localStringTokenizer.nextToken());
/*      */       }
/*      */     }
/* 1803 */     else if (supGetPortList) {
/* 1804 */       portList = new Vector();
/* 1805 */       int i = SerGetPortList();
/* 1806 */       if ((osFamilyWindows) || (OSInfo.isMac())) {
/* 1807 */         int j = portList.size();
/* 1808 */         comPortNums = new String[j];
/* 1809 */         for (int k = 0; k < j; k++) {
/* 1810 */           comPortNums[k] = ((String)portList.elementAt(k));
/*      */         }
/* 1812 */         portNumList = stripCOM(comPortNums);
/* 1813 */         Collections.sort(portNumList);
/* 1814 */         String[] arrayOfString2 = new String[j];
/* 1815 */         int n = portNumList.size();
/* 1816 */         for (int i1 = 0; i1 < n; i1++) {
/* 1817 */           arrayOfString2[i1] = ("COM" + portNumList.get(i1));
/*      */         }
/*      */ 
/* 1820 */         i1 = 0; for (int i2 = n; i1 < j; i1++) {
/* 1821 */           if (!comPortNums[i1].substring(0, 3).equals("COM")) {
/* 1822 */             arrayOfString2[(i2++)] = comPortNums[i1];
/*      */           }
/*      */         }
/* 1825 */         return arrayOfString2;
/*      */       }
/*      */ 
/*      */     }
/* 1829 */     else if (portList.size() > 0) {
/* 1830 */       displayMsg("getPortList: using list created with addPortName");
/*      */     }
/*      */     else {
/* 1833 */       displayMsg("getPortList: port discovery not currently supported on this platform.");
/* 1834 */       displayMsg("Options:");
/* 1835 */       displayMsg("A) addPortName can be used to build a list of device names ");
/* 1836 */       displayMsg("B) java -DSERIAL_PORT_LIST=COM1;COM2;COM3");
/* 1837 */       displayMsg("or java -DSERIAL_PORT_LIST=/dev/ttyS0:/dev/ttyS1");
/*      */     }
/*      */ 
/* 1841 */     String[] arrayOfString1 = new String[portList.size()];
/*      */ 
/* 1843 */     Enumeration localEnumeration = portList.elements();
/* 1844 */     int m = 0;
/* 1845 */     while (localEnumeration.hasMoreElements()) {
/* 1846 */       arrayOfString1[(m++)] = ((String)localEnumeration.nextElement());
/*      */     }
/*      */ 
/* 1849 */     return arrayOfString1;
/*      */   }
/*      */ 
/*      */   public static void addPortName(String paramString)
/*      */   {
/* 1855 */     Enumeration localEnumeration = portList.elements();
/*      */ 
/* 1858 */     while (localEnumeration.hasMoreElements()) {
/* 1859 */       String str = (String)localEnumeration.nextElement();
/* 1860 */       if (str.equalsIgnoreCase(paramString)) {
/* 1861 */         return;
/*      */       }
/*      */     }
/*      */ 
/* 1865 */     portList.addElement(paramString);
/*      */   }
/*      */ 
/*      */   public static void addPortInfo(String paramString1, String paramString2, String paramString3)
/*      */   {
/* 1876 */     Enumeration localEnumeration = portListInfo.elements();
/*      */ 
/* 1880 */     while (localEnumeration.hasMoreElements()) {
/* 1881 */       String str = ((PortDriverInfo)localEnumeration.nextElement()).getPortName();
/*      */ 
/* 1883 */       if (str.equalsIgnoreCase(paramString1)) {
/* 1884 */         return;
/*      */       }
/*      */     }
/*      */ 
/* 1888 */     portInfo = new PortDriverInfo(paramString1, paramString2, paramString3);
/* 1889 */     portListInfo.addElement(portInfo);
/*      */   }
/*      */ 
/*      */   public static void setPortListMode(int paramInt)
/*      */   {
/* 1898 */     portListMode = paramInt; } 
/* 1899 */   public static int getPortListMode() { return portListMode;
/*      */   }
/*      */ 
/*      */   public static ArrayList stripCOM(String[] paramArrayOfString)
/*      */   {
/* 1908 */     ArrayList localArrayList = new ArrayList();
/*      */ 
/* 1910 */     for (int i = 0; i < paramArrayOfString.length; i++) {
/* 1911 */       String str = paramArrayOfString[i].substring(3);
/* 1912 */       int j = 1;
/* 1913 */       for (int k = 0; k < str.length(); k++) {
/* 1914 */         if (!Character.isDigit(str.charAt(k)))
/* 1915 */           j = 0;
/*      */       }
/* 1917 */       if (j != 0) {
/* 1918 */         localArrayList.add(new Integer(str));
/*      */       }
/*      */     }
/*      */ 
/* 1922 */     return localArrayList;
/*      */   }
/*      */ 
/*      */   protected void finalize()
/*      */   {
/*      */     try
/*      */     {
/* 1930 */       if (this.flowControl != null) this.flowControl.abort();
/* 1931 */       close();
/*      */     }
/*      */     catch (Exception localException)
/*      */     {
/*      */     }
/*      */   }
/*      */ 
/*      */   private void srvTaskCheck() throws IOException
/*      */   {
/* 1940 */     if (osName.equals("EPOC")) {
/* 1941 */       if ((this.ssTask != null) && 
/* 1942 */         (this.ssTask.isAlive())) {
/* 1943 */         return;
/*      */       }
/* 1945 */       this.config.setPortNum(0);
/* 1946 */       this.ssTask = new SerialServerTask(this);
/* 1947 */       this.ssTask.start();
/* 1948 */       while (this.config.getPort() == 0) try {
/* 1949 */           Thread.sleep(100L);
/*      */         } catch (InterruptedException localInterruptedException) {
/*      */         } if (this.config.getPort() == -1) {
/* 1952 */         String str = "srvTaskCheck: " + this.ssTask.eMsg;
/* 1953 */         throw new IOException(str);
/*      */       }
/*      */ 
/* 1957 */       this.port = (this.config.getPort() - 1);
/*      */     }
/*      */   }
/*      */ 
/*      */   public void setPortName(String paramString)
/*      */   {
/* 1970 */     this.config.setPortName(paramString);
/* 1971 */     devName = paramString;
/*      */   }
/* 1973 */   public String getPortName() { return new String(this.config.getPortName()); } 
/*      */   public void setBitRate(int paramInt) {
/* 1975 */     this.config.setBitRate(paramInt); } 
/* 1976 */   public int getBitRate() { return this.config.getBitRate(); } 
/*      */   public void setDataBits(int paramInt) {
/* 1978 */     this.config.setDataBits(paramInt); } 
/* 1979 */   public int getDataBits() { return this.config.getDataBits(); } 
/*      */   public void setStopBits(int paramInt) {
/* 1981 */     this.config.setStopBits(paramInt); } 
/* 1982 */   public int getStopBits() { return this.config.getStopBits(); } 
/*      */   public void setParity(int paramInt) {
/* 1984 */     this.config.setParity(paramInt); } 
/* 1985 */   public int getParity() { return this.config.getParity(); } 
/*      */   public void setHandshake(int paramInt) {
/* 1987 */     this.config.setHandshake(paramInt); } 
/* 1988 */   public int getHandshake() { return this.config.getHandshake();
/*      */   }
/*      */ 
/*      */   native int SerGetLibVer();
/*      */ 
/*      */   native int SerOpenPort(SerialConfig paramSerialConfig);
/*      */ 
/*      */   native int SerConfigure(SerialConfig paramSerialConfig);
/*      */ 
/*      */   native int SerResetPortDriver(SerialConfig paramSerialConfig);
/*      */ 
/*      */   native int SerClosePort(int paramInt);
/*      */ 
/*      */   native int SerPutByte(int paramInt, byte paramByte);
/*      */ 
/*      */   native int SerPutData(int paramInt1, byte[] paramArrayOfByte, int paramInt2);
/*      */ 
/*      */   native int SerGetByte(int paramInt);
/*      */ 
/*      */   native int SerGetData(int paramInt1, byte[] paramArrayOfByte, int paramInt2);
/*      */ 
/*      */   native int SerRxFlush(int paramInt);
/*      */ 
/*      */   native int SerTxFlush(int paramInt);
/*      */ 
/*      */   native int SerTxDrain(int paramInt);
/*      */ 
/*      */   native int SerRxReadyCount(int paramInt);
/*      */ 
/*      */   native int SerTxBufCount(int paramInt);
/*      */ 
/*      */   native int SerRxOverflow(int paramInt);
/*      */ 
/*      */   native int SerSetDTR(int paramInt, boolean paramBoolean);
/*      */ 
/*      */   native int SerSetRTS(int paramInt, boolean paramBoolean);
/*      */ 
/*      */   native int SerSendBreak(int paramInt1, int paramInt2);
/*      */ 
/*      */   native int SerSetTimeoutRx(int paramInt1, int paramInt2);
/*      */ 
/*      */   native int SerSetTimeoutTx(int paramInt1, int paramInt2);
/*      */ 
/*      */   native int SerGetTimeoutRx(int paramInt);
/*      */ 
/*      */   native int SerGetTimeoutTx(int paramInt);
/*      */ 
/*      */   native int SerSigCTS(int paramInt);
/*      */ 
/*      */   native int SerSigDSR(int paramInt);
/*      */ 
/*      */   native int SerSigCD(int paramInt);
/*      */ 
/*      */   native int SerSigFrameErr(int paramInt);
/*      */ 
/*      */   native int SerSigParityErr(int paramInt);
/*      */ 
/*      */   native int SerSigOverrun(int paramInt);
/*      */ 
/*      */   native int SerSigRing(int paramInt);
/*      */ 
/*      */   native int SerSigBreak(int paramInt);
/*      */ 
/*      */   static native int SerGetPowerMode(int paramInt);
/*      */ 
/*      */   static native int SerSetPowerMode(int paramInt1, int paramInt2);
/*      */ 
/*      */   static native int SerGetResumeState(boolean paramBoolean);
/*      */ 
/*      */   static native int SerGetStatusAPM();
/*      */ 
/*      */   static native int SerGetPortList()
/*      */     throws IOException;
/*      */ 
/*      */   static native int SerGetPortMfgr(int paramInt);
/*      */ 
/*      */   static native int SerSetInstanceLock(int paramInt);
/*      */ 
/*      */   static native int SerGetInstanceLock();
/*      */ 
/*      */   native int SerFlowTask(int paramInt);
/*      */ 
/*      */   native String SerGetName(int paramInt);
/*      */ 
/*      */   native int SerServerTask()
/*      */     throws IOException;
/*      */ 
/*      */   static
/*      */   {
/*      */     try
/*      */     {
/*  171 */       osName = System.getProperty("os.name");
/*  172 */       jdkVer = System.getProperty("java.version");
/*  173 */       vendor = System.getProperty("java.vendor");
/*  174 */       osArch = System.getProperty("os.arch");
/*      */ 
/*  180 */       displayMsg(banner1 + "\r\n" + banner2);
/*  181 */       displayMsg("os.name=\"" + osName + "\"  os.arch=\"" + osArch + "\"");
/*  182 */       featureSetup();
/*      */ 
/*  184 */       setNativeLibName();
/*      */ 
/*  186 */       if (jspLib == null) {
/*  187 */         str1 = "osName=" + osName + " osArch=" + osArch;
/*  188 */         String str2 = "Platform '" + str1 + "' not supported, check VM properties os.name & os.arch";
/*      */ 
/*  190 */         displayMsg(str2);
/*  191 */         throw new IOException("Unsupported Platform Error: " + str2);
/*      */       }
/*      */ 
/*  195 */       String str1 = getLibAndPath32();
/*      */       try
/*      */       {
/*  198 */         loadLibrary(str1);
/*      */       }
/*      */       catch (UnsatisfiedLinkError localUnsatisfiedLinkError2) {
/*  201 */         displayMsg("load library failed: " + localUnsatisfiedLinkError2.getMessage());
/*  202 */         str1 = getLibAndPath64();
/*  203 */         loadLibrary(str1);
/*      */       }
/*      */     }
/*      */     catch (UnsatisfiedLinkError localUnsatisfiedLinkError1) {
/*  207 */       displayMsg(localUnsatisfiedLinkError1 + ": Check that native library " + jspLib + " is in proper directory");
/*      */     }
/*      */     catch (Exception localException)
/*      */     {
/*      */     }
/*      */   }
/*      */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     Serialio.SerialPortLocal
 * JD-Core Version:    0.6.0
 */