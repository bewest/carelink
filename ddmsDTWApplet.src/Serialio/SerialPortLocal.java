/*      */ package Serialio;
/*      */ 
/*      */ import java.io.File;
/*      */ import java.io.IOException;
/*      */ import java.io.PrintStream;
/*      */ import java.util.Enumeration;
/*      */ import java.util.StringTokenizer;
/*      */ import java.util.Vector;
/*      */ 
/*      */ public class SerialPortLocal
/*      */   implements SerialPort
/*      */ {
/*   41 */   protected static int buildNum = 9139;
/*   42 */   private static String banner1 = "Serialio.SerialPortLocal: version 9.4: build " + buildNum;
/*   43 */   private static String banner2 = "Copyright (c) 1996-2003 Serialio.com, All Rights Reserved.";
/*   44 */   protected static Vector portList = new Vector();
/*   45 */   protected static String jspLib = null;
/*   46 */   protected static boolean jspLibLoaded = false;
/*   47 */   protected static int maxBitRate = 115200;
/*      */   protected ErrorStatusHandler esh;
/*   49 */   private String nativeLib = null;
/*   50 */   private int port = -1;
/*   51 */   private boolean isOpen = false;
/*   52 */   private String name = "Serial Port";
/*      */   private SerialConfig config;
/*      */   private FlowControlTask flowControl;
/*      */   private SerialServerTask ssTask;
/*      */   private boolean wrSinceTxBufEmpty;
/*      */   private boolean rdSinceRxBufCheck;
/*   57 */   private boolean tex = true;
/*      */ 
/*   59 */   private static String osName = ""; private static String osArch = ""; private static String vendor = ""; private static String jdkVer = "";
/*   60 */   private static String devName = "";
/*   61 */   private static boolean supGetPortList = false;
/*   62 */   private static boolean javaGetDataTimeout = false;
/*   63 */   private static boolean supSendBreak = true;
/*   64 */   private static boolean supSigBreak = false;
/*   65 */   private static boolean supSigFrameErr = false;
/*   66 */   private static boolean supSigOverrun = false;
/*   67 */   private static boolean supSigParityErr = false;
/*   68 */   private static boolean supRxOverflow = false;
/*   69 */   private static boolean supSigRing = false;
/*   70 */   private static boolean supSigCTS = false;
/*   71 */   private static boolean supSigDSR = false;
/*   72 */   private static boolean supSigCD = false;
/*   73 */   private static boolean supSetRTS = false;
/*   74 */   private static boolean supSetDTR = false;
/*   75 */   private static boolean supRxReadyCount = false;
/*   76 */   private static boolean supTxBufCount = false;
/*   77 */   private static boolean supSetTimeoutTx = false;
/*   78 */   private static boolean supGetTimeoutTx = false;
/*   79 */   private static boolean supSetPowerMode = false;
/*   80 */   private static boolean supGetResumeState = false;
/*   81 */   private static boolean supGetStatusAPM = false;
/*      */ 
/*   83 */   private static boolean supNoIndexBitRate = false;
/*      */   private static NativeHandleMutex mutExSem;
/*      */ 
/*      */   private static void setNativeLibName()
/*      */   {
/*  129 */     String str1 = System.getProperty("JSP_OSNAME");
/*  130 */     String str2 = System.getProperty("JSP_OSARCH");
/*  131 */     if ((str1 != null) && (str2 != null)) {
/*  132 */       osName = str1;
/*  133 */       osArch = str2;
/*      */     }
/*      */ 
/*  136 */     if ((osName.equals("Windows 95")) || (osName.equals("Windows 98")) || (osName.equals("Windows Me")) || (osName.startsWith("Windows NT")) || (osName.equals("Windows XP")) || (osName.equals("Windows Vista")) || (osName.equals("Windows 7")) || (osName.equals("Windows 2000")))
/*      */     {
/*  144 */       jspLib = "ddmsDTWSerialPort";
/*  145 */       if ((jdkVer.equals("1.0.2")) || (jdkVer.equals("102"))) {
/*  146 */         jspLib = "jspWinNm";
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  155 */     if (osName.equals("EPOC")) {
/*  156 */       if (osArch.equals("x86")) jspLib = "jspEpocx86";
/*  157 */       if (osArch.equals("arm")) jspLib = "jspEpocArm";
/*      */     }
/*      */ 
/*  160 */     if ((osName.equals("Windows CE")) || (osName.equals("WindowsCE")) || (osName.equals("Windows CE  Java")))
/*      */     {
/*  164 */       if (osArch.equalsIgnoreCase("MIPS"))
/*  165 */         jspLib = "jspWceMips";
/*  166 */       if (osArch.equalsIgnoreCase("SH3"))
/*  167 */         jspLib = "jspWceSh3";
/*  168 */       if (osArch.equalsIgnoreCase("SH4"))
/*  169 */         jspLib = "jspWceSh4";
/*  170 */       if ((osArch.equalsIgnoreCase("ARM")) || (osArch.equalsIgnoreCase("Strong ARM")))
/*      */       {
/*  172 */         jspLib = "jspWceArm";
/*  173 */       }if (osArch.equalsIgnoreCase("x86")) {
/*  174 */         jspLib = "jspWceX86";
/*      */       }
/*      */     }
/*  177 */     if (osName.equals("OS/2")) {
/*  178 */       jspLib = "jspos2j";
/*      */     }
/*  180 */     if (osName.equals("Mac OS")) {
/*  181 */       jspLib = "jspMac";
/*      */     }
/*  183 */     if (osName.equals("Mac OS X")) {
/*  184 */       jspLib = "jspMacOSX";
/*      */     }
/*  186 */     if (osName.equals("Linux")) {
/*  187 */       if ((osArch.equals("x86")) || (osArch.equals("i386")) || (osArch.equals("i486")) || (osArch.equals("i586")) || (osArch.equals("i686")))
/*      */       {
/*  190 */         jspLib = "jspLux86";
/*  191 */       }if ((osArch.equals("armv4l")) || (osArch.equals("arm")))
/*  192 */         jspLib = "jspLuxArm";
/*      */     }
/*  194 */     if ((osName.equals("FreeBSD")) && (
/*  195 */       (osArch.equals("i386")) || (osArch.equals("x86"))))
/*  196 */       jspLib = "jspBsdx86";
/*  197 */     if ((osName.equals("Solaris")) || (osName.equals("SunOS"))) {
/*  198 */       if (osArch.equals("x86")) jspLib = "jspSolx86";
/*  199 */       if (osArch.equals("sparc")) jspLib = "jspSolSparc";
/*      */     }
/*  201 */     if ((osName.equals("HP-UX")) && ((osArch.equals("PA-RISC")) || (osArch.equals("PA_RISC")) || (osArch.equals("PA_RISC2.0"))))
/*      */     {
/*  204 */       jspLib = "jspHpxPaRisc";
/*      */     }
/*  206 */     if ((osName.equals("Irix")) && (osArch.equals("mips"))) {
/*  207 */       jspLib = "jspIrxMips";
/*      */     }
/*  209 */     if ((osName.equals("AIX")) && (
/*  210 */       (osArch.equals("POWER_PC")) || (osArch.equals("ppc")))) {
/*  211 */       jspLib = "jspAixPpc";
/*      */     }
/*  213 */     if (((osName.equals("UnixWare")) || (osName.equals("OpenServer"))) && (osArch.equals("IA32")))
/*      */     {
/*  215 */       jspLib = "jspUxWareIA32";
/*      */     }
/*  217 */     if ((osName.equals("OSF1")) && (osArch.equals("alpha"))) {
/*  218 */       jspLib = "jspTru64Alpha";
/*      */     }
/*  220 */     if (osName.toLowerCase().indexOf("netware") != -1)
/*  221 */       jspLib = "JspNw";
/*      */   }
/*      */ 
/*      */   public SerialPortLocal()
/*      */   {
/*  236 */     if (!jspLibLoaded) {
/*  237 */       if (jspLib == null) setNativeLibName();
/*  238 */       System.out.println("SerialPortLocal: Attempt to load: " + jspLib);
/*  239 */       System.loadLibrary(jspLib);
/*  240 */       System.out.println("SerialPortLocal: SerialPort class loaded: " + jspLib);
/*      */     }
/*  242 */     osName = System.getProperty("os.name");
/*  243 */     this.config = new SerialConfig(devName);
/*      */   }
/*      */ 
/*      */   public SerialPortLocal(SerialConfig paramSerialConfig)
/*      */     throws IOException
/*      */   {
/*  255 */     osName = System.getProperty("os.name");
/*  256 */     this.config = paramSerialConfig;
/*  257 */     open();
/*      */   }
/*      */ 
/*      */   public void open()
/*      */     throws IOException
/*      */   {
/*  282 */     if (!osName.equals("Windows 95")) {
/*  283 */       this.config.setHardFlow(false);
/*      */     }
/*      */ 
/*  286 */     srvTaskCheck();
/*      */     int i;
/*  287 */     synchronized (mutExSem) { i = SerOpenPort(this.config);
/*      */     }
/*  289 */     if (i != 0) {
/*  290 */       ??? = this.config.getPortNameString() + ": SerOpenPort failed: ";
/*      */       String str;
/*  291 */       if (i == 1000)
/*  292 */         str = (String)??? + "Port is already open";
/*  293 */       else if (i == 1002)
/*  294 */         str = (String)??? + "Port is in use";
/*  295 */       else if (i == 1003)
/*  296 */         str = (String)??? + "Port not valid";
/*  297 */       else if (i == 1004)
/*  298 */         str = (String)??? + "No such device";
/*  299 */       else if (i == 1006)
/*  300 */         str = (String)??? + "Permission denied";
/*      */       else {
/*  302 */         str = (String)??? + ": " + i;
/*      */       }
/*  304 */       throw new IOException(str);
/*      */     }
/*      */ 
/*  311 */     this.isOpen = true;
/*  312 */     this.wrSinceTxBufEmpty = false;
/*  313 */     this.rdSinceRxBufCheck = false;
/*  314 */     this.port = (this.config.getPort() - 1);
/*      */ 
/*  316 */     configure(this.config);
/*      */ 
/*  330 */     if (osName.equals("Mac OS")) {
/*  331 */       setTimeoutRx(2000);
/*  332 */       setTimeoutTx(2000);
/*      */     } else {
/*  334 */       setTimeoutRx(0);
/*  335 */       int j = 0;
/*      */ 
/*  337 */       int k = this.config.getTxLen();
/*  338 */       int m = this.config.getBitRateNumber(getBitRate()) / 10;
/*  339 */       int n = 1000000 / m;
/*  340 */       j = k * n / 1000 * 100;
/*      */ 
/*  342 */       if (supSetTimeoutTx) setTimeoutTx(j);
/*      */     }
/*      */   }
/*      */ 
/*      */   public void configure()
/*      */     throws IOException
/*      */   {
/*  353 */     configure(this.config);
/*      */   }
/*      */ 
/*      */   public void configure(SerialConfig paramSerialConfig)
/*      */     throws IOException
/*      */   {
/*  365 */     if (this.flowControl != null) { this.flowControl.abort(); this.flowControl = null;
/*      */     }
/*  367 */     if (!supNoIndexBitRate)
/*      */     {
/*  369 */       i = paramSerialConfig.getBitRateIndex(paramSerialConfig.getBitRate());
/*  370 */       if (i > 0) paramSerialConfig.setBitRate(i);
/*  371 */       else if (!paramSerialConfig.validBitRateIndex()) {
/*  372 */         throw new IOException("Unsupported Serial Port Bit Rate");
/*      */       }
/*      */     }
/*  375 */     int i = SerConfigure(paramSerialConfig);
/*      */ 
/*  377 */     if (i != 0) {
/*  378 */       Integer localInteger = new Integer(i);
/*  379 */       throw new IOException("Configure failed: " + localInteger.toString());
/*      */     }
/*      */ 
/*  382 */     boolean bool = paramSerialConfig.getHardFlow();
/*  383 */     if ((bool) && (osName.equals("Windows 95"))) {
/*  384 */       int j = paramSerialConfig.getHandshake();
/*  385 */       if ((j == 2) || (j == 3))
/*      */       {
/*  387 */         if (this.flowControl == null) {
/*  388 */           this.flowControl = new FlowControlTask(this);
/*  389 */           this.flowControl.start();
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public void setConfig(SerialConfig paramSerialConfig)
/*      */   {
/*  404 */     if (!this.isOpen) paramSerialConfig.setPortNum(this.port + 1);
/*  405 */     this.config = paramSerialConfig;
/*  406 */     devName = paramSerialConfig.getPortNameString();
/*      */   }
/*      */ 
/*      */   public SerialConfig getConfig()
/*      */   {
/*  413 */     return this.config;
/*      */   }
/*      */ 
/*      */   public void close()
/*      */     throws IOException
/*      */   {
/*  433 */     this.wrSinceTxBufEmpty = false;
/*  434 */     this.rdSinceRxBufCheck = false;
/*  435 */     if (this.flowControl != null) this.flowControl.abort();
/*      */ 
/*  437 */     if (this.isOpen) {
/*  438 */       this.isOpen = false;
/*      */       int i;
/*  439 */       synchronized (mutExSem) { i = SerClosePort(this.port); }
/*  440 */       this.port = -1;
/*      */ 
/*  442 */       if (i != 0)
/*  443 */         throw new IOException("ClosePort failed");
/*      */     }
/*      */   }
/*      */ 
/*      */   public void reset()
/*      */     throws IOException
/*      */   {
/*  452 */     int j = 0;
/*      */ 
/*  454 */     if (!this.isOpen) {
/*  455 */       throw new IOException("reset: Port is not open");
/*      */     }
/*  457 */     if (supSetTimeoutTx) j = getTimeoutTx();
/*  458 */     close();
/*      */ 
/*  461 */     srvTaskCheck();
/*      */     int i;
/*  462 */     synchronized (mutExSem) { i = SerOpenPort(this.config); }
/*  463 */     if (i != 0) {
/*  464 */       i = checkBusy(i);
/*  465 */       if (i != 0)
/*  466 */         throw new IOException("reset: SerOpenPort failed: " + i);
/*      */     }
/*  468 */     this.isOpen = true;
/*  469 */     this.port = (this.config.getPort() - 1);
/*  470 */     if (supSetTimeoutTx) setTimeoutTx(j);
/*      */ 
/*  472 */     configure(this.config);
/*      */   }
/*      */ 
/*      */   private int checkBusy(int paramInt)
/*      */   {
/*  479 */     if ((!osName.equals("Solaris")) && (!osName.equals("SunOS")))
/*      */     {
/*  481 */       return paramInt;
/*      */     }
/*  483 */     int i = 16;
/*  484 */     long l1 = System.currentTimeMillis();
/*  485 */     long l2 = l1 + 3000L;
/*  486 */     while (System.currentTimeMillis() < l2) {
/*      */       try { Thread.sleep(500L); } catch (InterruptedException localInterruptedException) {
/*  488 */       }synchronized (mutExSem) { paramInt = SerOpenPort(this.config); }
/*  489 */       if (paramInt == 0) break; if (paramInt == i) continue;
/*      */     }
/*  491 */     return paramInt;
/*      */   }
/*      */ 
/*      */   public void putByte(byte paramByte)
/*      */     throws IOException
/*      */   {
/*  504 */     int i = SerPutByte(this.port, paramByte);
/*  505 */     this.wrSinceTxBufEmpty = true;
/*      */ 
/*  507 */     if (i != 0) throw new IOException("PutByte failed: " + i);
/*      */   }
/*      */ 
/*      */   public void putString(String paramString)
/*      */     throws IOException
/*      */   {
/*  522 */     byte[] arrayOfByte = paramString.getBytes();
/*      */ 
/*  524 */     int i = arrayOfByte.length;
/*  525 */     if (i == 0)
/*      */     {
/*  527 */       return;
/*      */     }
/*      */ 
/*  530 */     int j = SerPutData(this.port, arrayOfByte, i);
/*  531 */     this.wrSinceTxBufEmpty = true;
/*      */ 
/*  533 */     if (j != i) {
/*  534 */       String str = new String("PutString: sent only " + j + " of " + i + " bytes");
/*  535 */       throw new IOException(str);
/*      */     }
/*      */   }
/*      */ 
/*      */   public void putData(byte[] paramArrayOfByte)
/*      */     throws IOException
/*      */   {
/*  562 */     int i = paramArrayOfByte.length;
/*  563 */     if (i == 0)
/*      */     {
/*  565 */       return;
/*      */     }
/*      */ 
/*  568 */     int j = SerPutData(this.port, paramArrayOfByte, i);
/*  569 */     this.wrSinceTxBufEmpty = true;
/*      */ 
/*  571 */     if (j != i)
/*      */     {
/*  573 */       String str = new String("PutData1: Sent only " + j + " of " + i + " bytes");
/*  574 */       throw new IOException(str);
/*      */     }
/*      */   }
/*      */ 
/*      */   /** @deprecated */
/*      */   public void putData(byte[] paramArrayOfByte, int paramInt)
/*      */     throws IOException
/*      */   {
/*  593 */     if (paramInt > paramArrayOfByte.length) {
/*  594 */       throw new IOException("putData: count out of bounds");
/*      */     }
/*  596 */     int i = SerPutData(this.port, paramArrayOfByte, paramInt);
/*  597 */     this.wrSinceTxBufEmpty = true;
/*      */ 
/*  599 */     if (i != paramInt) {
/*  600 */       String str = new String("PutData2: Sent only " + i + " of " + paramInt + " bytes");
/*  601 */       throw new IOException(str);
/*      */     }
/*      */   }
/*      */ 
/*      */   public void putData(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/*      */     throws IOException
/*      */   {
/*  616 */     if (paramInt1 + paramInt2 > paramArrayOfByte.length) {
/*  617 */       throw new IOException("putData3: count out of bounds");
/*      */     }
/*  619 */     byte[] arrayOfByte = new byte[paramInt2];
/*  620 */     System.arraycopy(paramArrayOfByte, paramInt1, arrayOfByte, 0, paramInt2);
/*  621 */     int i = SerPutData(this.port, arrayOfByte, paramInt2);
/*  622 */     this.wrSinceTxBufEmpty = true;
/*      */ 
/*  624 */     if (i != paramInt2) {
/*  625 */       String str = new String("putData3: Sent only " + i + " of " + paramInt2 + " bytes");
/*  626 */       throw new IOException(str);
/*      */     }
/*      */   }
/*      */ 
/*      */   public int getByte()
/*      */     throws IOException
/*      */   {
/*  638 */     if ((javaGetDataTimeout) && (SerGetTimeoutRx(this.port) > 0)) { long l1 = System.currentTimeMillis() + SerGetTimeoutRx(this.port);
/*      */       long l2;
/*      */       do { if (SerRxReadyCount(this.port) > 0) {
/*  643 */           this.rdSinceRxBufCheck = true;
/*  644 */           return SerGetByte(this.port);
/*      */         }
/*  646 */         l2 = System.currentTimeMillis();
/*  647 */         Thread.yield(); }
/*  648 */       while (l2 < l1);
/*  649 */       return -1;
/*      */     }
/*      */ 
/*  652 */     int i = SerGetByte(this.port);
/*  653 */     this.rdSinceRxBufCheck = true;
/*      */ 
/*  655 */     return i;
/*      */   }
/*      */ 
/*      */   public int getData(byte[] paramArrayOfByte)
/*      */     throws IOException
/*      */   {
/*  671 */     int i = getData(paramArrayOfByte, 0, paramArrayOfByte.length);
/*  672 */     this.rdSinceRxBufCheck = true;
/*      */ 
/*  674 */     return i;
/*      */   }
/*      */ 
/*      */   public int getData(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/*      */     throws IOException
/*      */   {
/*  691 */     int i = 0;
/*  692 */     if (paramInt2 > paramArrayOfByte.length) paramInt2 = paramArrayOfByte.length;
/*  693 */     if ((javaGetDataTimeout) && (SerGetTimeoutRx(this.port) > 0)) { long l1 = System.currentTimeMillis() + SerGetTimeoutRx(this.port);
/*      */       long l2;
/*      */       do { i = SerRxReadyCount(this.port);
/*  698 */         if (i >= paramInt2)
/*      */           break;
/*  700 */         l2 = System.currentTimeMillis();
/*  701 */         Thread.yield(); }
/*  702 */       while (l2 < l1);
/*  703 */       if (i < paramInt2) return 0;
/*      */     }
/*      */     Object localObject;
/*  706 */     if (paramInt1 != 0) {
/*  707 */       localObject = new byte[paramInt2];
/*  708 */       i = SerGetData(this.port, localObject, paramInt2);
/*  709 */       if (i > 0) System.arraycopy(localObject, 0, paramArrayOfByte, paramInt1, i); 
/*      */     }
/*      */     else
/*      */     {
/*  712 */       i = SerGetData(this.port, paramArrayOfByte, paramInt2);
/*      */     }
/*  714 */     this.rdSinceRxBufCheck = true;
/*  715 */     if (i == -1) {
/*  716 */       localObject = new String("getData: failed");
/*  717 */       throw new IOException((String)localObject);
/*      */     }
/*      */ 
/*  720 */     return i;
/*      */   }
/*      */ 
/*      */   public int rxFlush()
/*      */     throws IOException
/*      */   {
/*  729 */     int i = SerRxFlush(this.port);
/*      */ 
/*  731 */     if (i == -1) throw new IOException("rxFlush failed");
/*      */ 
/*  733 */     return i;
/*      */   }
/*      */ 
/*      */   public int txFlush()
/*      */     throws IOException
/*      */   {
/*  742 */     int i = SerTxFlush(this.port);
/*      */ 
/*  744 */     if (i == -1) throw new IOException("txFlush failed");
/*      */ 
/*  746 */     return i;
/*      */   }
/*      */ 
/*      */   public int txDrain()
/*      */     throws IOException
/*      */   {
/*  757 */     int i = SerTxDrain(this.port);
/*      */ 
/*  759 */     if (i == -1) throw new IOException("txDrain failed");
/*      */ 
/*  761 */     return i;
/*      */   }
/*      */ 
/*      */   public int rxReadyCount()
/*      */     throws IOException
/*      */   {
/*  770 */     if (!supRxReadyCount) {
/*  771 */       throw new IOException("rxReadyCount not supported on " + osName);
/*      */     }
/*      */ 
/*  775 */     int i = SerRxReadyCount(this.port);
/*      */ 
/*  778 */     if (i == -1) {
/*  779 */       if (this.esh != null) this.esh.sioErrorEvent(10); else
/*  780 */         throw new IOException("rxReadyCount failed");
/*      */     }
/*  782 */     return i;
/*      */   }
/*      */ 
/*      */   public int txBufCount()
/*      */     throws IOException
/*      */   {
/*  795 */     if (!supTxBufCount) {
/*  796 */       throw new IOException("txBufCount not supported on " + osName);
/*      */     }
/*  798 */     if (this.port < 0) throw new IOException("txBufCount: Port not open");
/*      */ 
/*  800 */     int i = SerTxBufCount(this.port);
/*      */ 
/*  803 */     if (i == -1) throw new IOException("txBufCount failed");
/*      */ 
/*  805 */     return i;
/*      */   }
/*      */ 
/*      */   public void clearReadSinceRxBufCheck()
/*      */   {
/*  813 */     this.rdSinceRxBufCheck = false;
/*      */   }
/*      */ 
/*      */   public boolean sendSinceTxBufEmpty()
/*      */   {
/*  821 */     return this.wrSinceTxBufEmpty;
/*      */   }
/*      */ 
/*      */   public void clearSendSinceTxBufEmpty()
/*      */   {
/*  837 */     this.wrSinceTxBufEmpty = false;
/*      */   }
/*      */ 
/*      */   public boolean readSinceRxBufCheck()
/*      */   {
/*  845 */     return this.rdSinceRxBufCheck;
/*      */   }
/*      */ 
/*      */   public boolean rxOverflow()
/*      */     throws IOException
/*      */   {
/*  856 */     if (!supRxOverflow) {
/*  857 */       throw new IOException("rxOverflow not supported on " + osName);
/*      */     }
/*  859 */     if (this.port < 0) throw new IOException("rxOverflow: Port not open");
/*      */ 
/*  861 */     int i = SerRxOverflow(this.port);
/*      */ 
/*  863 */     if (i == -1) throw new IOException("rxOverflow failed");
/*      */ 
/*  865 */     return i == 1;
/*      */   }
/*      */ 
/*      */   public boolean sigDSR()
/*      */     throws IOException
/*      */   {
/*  874 */     if (this.port < 0) throw new IOException("sigDSR: Port not open");
/*      */ 
/*  876 */     int i = SerSigDSR(this.port);
/*      */ 
/*  878 */     if (i == -1) throw new IOException("sigDSR failed");
/*      */ 
/*  880 */     return i == 1;
/*      */   }
/*      */ 
/*      */   public boolean sigCTS()
/*      */     throws IOException
/*      */   {
/*  889 */     if (this.port < 0) throw new IOException("sigCTS: Port not open");
/*      */ 
/*  891 */     int i = SerSigCTS(this.port);
/*      */ 
/*  893 */     if (i == -1) throw new IOException("sigCTS failed");
/*      */ 
/*  895 */     return i == 1;
/*      */   }
/*      */ 
/*      */   public boolean sigCD()
/*      */     throws IOException
/*      */   {
/*  905 */     if (!supSigCD) {
/*  906 */       throw new IOException("sigCD not supported on " + osName);
/*      */     }
/*  908 */     if (this.port < 0) throw new IOException("sigCD: Port not open");
/*      */ 
/*  910 */     int i = SerSigCD(this.port);
/*      */ 
/*  912 */     if (i == -1) throw new IOException("sigCD failed");
/*      */ 
/*  914 */     return i == 1;
/*      */   }
/*      */ 
/*      */   public boolean sigFrameErr()
/*      */     throws IOException
/*      */   {
/*  925 */     if (!supSigFrameErr) {
/*  926 */       throw new IOException("sigFrameErr not supported on " + osName);
/*      */     }
/*  928 */     if (this.port < 0) throw new IOException("sigFrameErr: Port not open");
/*      */ 
/*  930 */     int i = SerSigFrameErr(this.port);
/*      */ 
/*  932 */     if (i == -1) throw new IOException("sigFrameErr failed");
/*      */ 
/*  934 */     return i == 1;
/*      */   }
/*      */ 
/*      */   public boolean sigOverrun()
/*      */     throws IOException
/*      */   {
/*  945 */     if (!supSigOverrun) {
/*  946 */       throw new IOException("sigOverrun not supported on " + osName);
/*      */     }
/*  948 */     if (this.port < 0) throw new IOException("sigOverrun: Port not open");
/*      */ 
/*  950 */     int i = SerSigOverrun(this.port);
/*      */ 
/*  952 */     if (i == -1) throw new IOException("sigOverrun failed");
/*      */ 
/*  954 */     return i == 1;
/*      */   }
/*      */ 
/*      */   public boolean sigParityErr()
/*      */     throws IOException
/*      */   {
/*  965 */     if (!supSigParityErr) {
/*  966 */       throw new IOException("sigParityErr not supported on " + osName);
/*      */     }
/*  968 */     if (this.port < 0) throw new IOException("sigParityErr: Port not open");
/*      */ 
/*  970 */     int i = SerSigParityErr(this.port);
/*      */ 
/*  972 */     return i == 1;
/*      */   }
/*      */ 
/*      */   public boolean sigRing()
/*      */     throws IOException
/*      */   {
/*  982 */     if (!supSigRing) {
/*  983 */       throw new IOException("sigRing not supported on " + osName);
/*      */     }
/*  985 */     if (this.port < 0) throw new IOException("sigRing: Port not open");
/*      */ 
/*  987 */     int i = SerSigRing(this.port);
/*      */ 
/*  989 */     return i == 1;
/*      */   }
/*      */ 
/*      */   public boolean sigBreak()
/*      */     throws IOException
/*      */   {
/* 1000 */     if (!supSigBreak) {
/* 1001 */       throw new IOException("sigBreak not supported on " + osName);
/*      */     }
/* 1003 */     if (this.port < 0) throw new IOException("sigBreak: Port not open");
/*      */ 
/* 1005 */     int i = SerSigBreak(this.port);
/*      */ 
/* 1007 */     return i == 1;
/*      */   }
/*      */ 
/*      */   public void setDTR(boolean paramBoolean)
/*      */     throws IOException
/*      */   {
/* 1017 */     if (!supSetDTR) {
/* 1018 */       throw new IOException("setDTR not supported on " + osName);
/*      */     }
/* 1020 */     if (this.port < 0) throw new IOException("setDTR: Port not open");
/*      */ 
/* 1022 */     int i = SerSetDTR(this.port, paramBoolean);
/*      */ 
/* 1024 */     if (i == -1) throw new IOException("setDTR failed");
/*      */   }
/*      */ 
/*      */   public void setRTS(boolean paramBoolean)
/*      */     throws IOException
/*      */   {
/* 1037 */     if (!supSetRTS) {
/* 1038 */       throw new IOException("setRts not supported on " + osName);
/*      */     }
/* 1040 */     if (this.port < 0) throw new IOException("setRTS: Port not open");
/*      */ 
/* 1042 */     int i = SerSetRTS(this.port, paramBoolean);
/*      */ 
/* 1044 */     if (i == -1) throw new IOException("setRTS failed");
/*      */   }
/*      */ 
/*      */   public void sendBreak(int paramInt)
/*      */     throws IOException
/*      */   {
/* 1054 */     if (!supSendBreak) {
/* 1055 */       throw new IOException("sendBreak not supported on " + osName);
/*      */     }
/* 1057 */     if (this.port < 0) throw new IOException("sendBreak: Port not open");
/*      */ 
/* 1059 */     int i = SerSendBreak(this.port, paramInt);
/*      */ 
/* 1061 */     if (i == -1) throw new IOException("sendBreak failed");
/*      */   }
/*      */ 
/*      */   public int getTimeoutRx()
/*      */     throws IOException
/*      */   {
/* 1070 */     int i = SerGetTimeoutRx(this.port);
/*      */ 
/* 1072 */     return i;
/*      */   }
/*      */ 
/*      */   public int getTimeoutTx()
/*      */     throws IOException
/*      */   {
/* 1081 */     int i = SerGetTimeoutTx(this.port);
/*      */ 
/* 1083 */     return i;
/*      */   }
/*      */ 
/*      */   public void setTimeoutRx(int paramInt)
/*      */     throws IOException
/*      */   {
/* 1095 */     int i = SerSetTimeoutRx(this.port, paramInt);
/*      */ 
/* 1097 */     if (i == -1) throw new IOException("setTimeoutRx failed");
/*      */   }
/*      */ 
/*      */   public void setTimeoutTx(int paramInt)
/*      */     throws IOException
/*      */   {
/* 1109 */     int i = SerSetTimeoutTx(this.port, paramInt);
/*      */ 
/* 1111 */     if (i == -1) throw new IOException("setTimeoutTx failed");
/*      */   }
/*      */ 
/*      */   public void setErrorStatusHandler(ErrorStatusHandler paramErrorStatusHandler)
/*      */   {
/* 1128 */     this.esh = paramErrorStatusHandler;
/*      */   }
/*      */ 
/*      */   public static void setPowerMode(int paramInt1, int paramInt2)
/*      */     throws IOException
/*      */   {
/* 1142 */     if (!supSetPowerMode) return;
/*      */ 
/* 1144 */     int i = 0;
/* 1145 */     synchronized (mutExSem) {
/* 1146 */       i = SerSetPowerMode(paramInt1, paramInt2);
/*      */     }
/*      */ 
/* 1149 */     if (i != 0) throw new IOException("setPowerMode failed: " + i);
/*      */   }
/*      */ 
/*      */   public static int getPowerMode(int paramInt)
/*      */     throws IOException
/*      */   {
/* 1163 */     if (!supSetPowerMode) return -1;
/*      */ 
/* 1165 */     int i = 0;
/* 1166 */     synchronized (mutExSem) {
/* 1167 */       i = SerGetPowerMode(paramInt);
/*      */     }
/*      */ 
/* 1170 */     if (i >= 5000) throw new IOException("getPowerMode failed: " + i);
/* 1171 */     return i;
/*      */   }
/*      */ 
/*      */   public static int getResumeState(boolean paramBoolean)
/*      */   {
/* 1183 */     if (!supGetResumeState) return -1;
/*      */ 
/* 1185 */     return SerGetResumeState(paramBoolean);
/*      */   }
/*      */ 
/*      */   public static int getStatusAPM()
/*      */   {
/* 1204 */     if (!supGetStatusAPM) return -1;
/*      */ 
/* 1206 */     int i = SerGetStatusAPM();
/*      */ 
/* 1209 */     return i;
/*      */   }
/*      */ 
/*      */   public int getPortNum()
/*      */   {
/* 1216 */     return this.port;
/*      */   }
/*      */ 
/*      */   public void setName(String paramString)
/*      */   {
/* 1223 */     this.name = paramString;
/*      */   }
/*      */ 
/*      */   public String getName()
/*      */   {
/* 1230 */     return this.name;
/*      */   }
/*      */ 
/*      */   public String getDevName()
/*      */   {
/* 1237 */     return new String(this.config.getPortName());
/*      */   }
/*      */ 
/*      */   public String getName(int paramInt)
/*      */   {
/* 1244 */     String str = "Not implemented";
/* 1245 */     if ((osName.equals("Mac OS") == true) || (osName.equals("Mac OS X") == true)) {
/* 1246 */       str = SerGetName(paramInt);
/*      */     }
/*      */ 
/* 1250 */     return str;
/*      */   }
/*      */ 
/*      */   public int getBuildNum()
/*      */   {
/* 1259 */     return buildNum;
/*      */   }
/*      */ 
/*      */   public int getLibVer()
/*      */   {
/* 1269 */     return SerGetLibVer();
/*      */   }
/*      */ 
/*      */   public boolean isOpen()
/*      */   {
/* 1276 */     return this.isOpen;
/*      */   }
/*      */ 
/*      */   private static void featureSetup()
/*      */   {
/* 1283 */     mutExSem = NativeHandleMutex.getInstance();
/*      */ 
/* 1285 */     if ((osName.equals("Windows 95")) || (osName.startsWith("Windows NT")) || (osName.equals("Windows Vista")) || (osName.equals("Windows 7")) || (osName.equals("Windows 2000")) || (osName.equals("Windows XP")) || (osName.equals("Windows 98")) || (osName.equals("Windows Me")) || (osName.equals("Windows CE")) || (osName.equals("WindowsCE")) || (osName.equals("OS/2")) || (osName.toLowerCase().indexOf("netware") != -1))
/*      */     {
/* 1298 */       if ((osName.equals("Windows Vista")) || (osName.equals("Windows 7")) || (osName.equals("Windows 2000")) || (osName.equals("Windows XP")))
/* 1299 */         maxBitRate = 460800;
/* 1300 */       supGetPortList = true;
/* 1301 */       supGetStatusAPM = true;
/* 1302 */       supSigBreak = true;
/* 1303 */       supSigFrameErr = true;
/* 1304 */       supSigParityErr = true;
/* 1305 */       supSigOverrun = true;
/* 1306 */       supRxOverflow = true;
/* 1307 */       supRxReadyCount = true;
/* 1308 */       supTxBufCount = true;
/* 1309 */       supSetTimeoutTx = true;
/* 1310 */       supGetTimeoutTx = true;
/* 1311 */       supSigRing = true;
/* 1312 */       supSetRTS = true;
/* 1313 */       supSetDTR = true;
/* 1314 */       supSigCD = true;
/* 1315 */       supSigCTS = true;
/* 1316 */       supSigDSR = true;
/* 1317 */       javaGetDataTimeout = false;
/*      */ 
/* 1319 */       supNoIndexBitRate = true;
/* 1320 */       if (osName.equals("OS/2")) supNoIndexBitRate = false;
/* 1321 */       if (osName.toLowerCase().indexOf("netware") != -1) {
/* 1322 */         javaGetDataTimeout = true;
/* 1323 */         supGetPortList = false;
/*      */       }
/*      */ 
/* 1326 */       devName = "COM1";
/*      */     }
/*      */ 
/* 1329 */     if (osName.equals("Mac OS"))
/*      */     {
/* 1331 */       maxBitRate = 230400;
/* 1332 */       supGetPortList = true;
/* 1333 */       supSigBreak = true;
/* 1334 */       supSigFrameErr = true;
/* 1335 */       supSigParityErr = true;
/* 1336 */       supSigOverrun = true;
/* 1337 */       supRxOverflow = true;
/* 1338 */       supRxReadyCount = true;
/* 1339 */       supTxBufCount = true;
/* 1340 */       supSetTimeoutTx = true;
/* 1341 */       supGetTimeoutTx = true;
/* 1342 */       supSigCTS = true;
/* 1343 */       supSetDTR = true;
/* 1344 */       javaGetDataTimeout = true;
/* 1345 */       devName = "Modem Port";
/*      */     }
/*      */ 
/* 1348 */     if (osName.equals("Mac OS X"))
/*      */     {
/* 1350 */       maxBitRate = 230400;
/* 1351 */       supGetStatusAPM = true;
/* 1352 */       supGetPortList = true;
/*      */ 
/* 1358 */       supRxReadyCount = true;
/* 1359 */       supTxBufCount = true;
/*      */ 
/* 1362 */       supSigCTS = true;
/* 1363 */       supSetDTR = true;
/* 1364 */       javaGetDataTimeout = true;
/* 1365 */       devName = "";
/*      */     }
/*      */ 
/* 1368 */     if ((osName.equals("Linux")) || (osName.equals("FreeBSD")) || (osName.equals("Solaris")) || (osName.equals("SunOS")) || (osName.equals("HP-UX")) || (osName.equals("Irix")) || (osName.equals("AIX")) || (osName.equals("OSF1")) || (osName.equals("UnixWare")))
/*      */     {
/* 1378 */       supSigRing = true;
/* 1379 */       supSetRTS = true;
/* 1380 */       supSetDTR = true;
/* 1381 */       supSigCD = true;
/* 1382 */       supRxReadyCount = true;
/* 1383 */       supTxBufCount = true;
/* 1384 */       supSigCTS = true;
/* 1385 */       supSigDSR = true;
/* 1386 */       javaGetDataTimeout = true;
/*      */ 
/* 1391 */       devName = "/dev/tty0";
/*      */ 
/* 1393 */       if (osName.equals("Linux")) {
/* 1394 */         devName = "/dev/ttyS0";
/* 1395 */         supGetStatusAPM = true;
/* 1396 */         if ((osArch.equals("armv4l")) || (osArch.equals("arm"))) {
/* 1397 */           supSetPowerMode = true;
/* 1398 */           supGetResumeState = true;
/*      */         }
/*      */       }
/* 1401 */       if ((osName.equals("Solaris")) || (osName.equals("SunOS")))
/*      */       {
/* 1404 */         devName = "/dev/ttya";
/*      */       }
/*      */ 
/* 1407 */       if (osName.equals("Irix")) {
/* 1408 */         supSetRTS = false;
/* 1409 */         supSetDTR = false;
/* 1410 */         supSigCTS = false;
/*      */       }
/* 1412 */       if (osName.equals("UnixWare")) {
/* 1413 */         supSetRTS = false;
/*      */       }
/* 1415 */       if (osName.equals("OSF1")) {
/* 1416 */         devName = "/dev/tty00";
/*      */       }
/*      */     }
/*      */ 
/* 1420 */     if (osName.equals("EPOC")) {
/* 1421 */       supGetPortList = true;
/* 1422 */       supSendBreak = false;
/* 1423 */       supTxBufCount = true;
/* 1424 */       supSigFrameErr = true;
/* 1425 */       supSigParityErr = true;
/* 1426 */       supSigOverrun = true;
/* 1427 */       supRxReadyCount = true;
/* 1428 */       supSetTimeoutTx = true;
/* 1429 */       supGetTimeoutTx = true;
/* 1430 */       supSetRTS = true;
/* 1431 */       supSetDTR = true;
/* 1432 */       supSigCD = true;
/* 1433 */       supSigCTS = true;
/* 1434 */       supSigDSR = true;
/* 1435 */       javaGetDataTimeout = false;
/* 1436 */       supNoIndexBitRate = true;
/* 1437 */       devName = "COMM::0";
/*      */     }
/*      */   }
/*      */ 
/*      */   public int getMaxBitRate()
/*      */   {
/* 1445 */     return maxBitRate;
/*      */   }
/*      */ 
/*      */   public boolean isSupported(String paramString)
/*      */   {
/* 1451 */     if (paramString.equals("sigBreak")) return supSigBreak;
/* 1452 */     if (paramString.equals("sendBreak")) return supSendBreak;
/* 1453 */     if (paramString.equals("sigFrameErr")) return supSigFrameErr;
/* 1454 */     if (paramString.equals("sigOverrun")) return supSigOverrun;
/* 1455 */     if (paramString.equals("sigParityErr")) return supSigParityErr;
/* 1456 */     if (paramString.equals("rxOverflow")) return supRxOverflow;
/* 1457 */     if (paramString.equals("sigRing")) return supSigRing;
/* 1458 */     if (paramString.equals("sigCTS")) return supSigCTS;
/* 1459 */     if (paramString.equals("sigDSR")) return supSigDSR;
/* 1460 */     if (paramString.equals("sigCD")) return supSigCD;
/* 1461 */     if (paramString.equals("setRTS")) return supSetRTS;
/* 1462 */     if (paramString.equals("setDTR")) return supSetDTR;
/* 1463 */     if (paramString.equals("rxReadyCount")) return supRxReadyCount;
/* 1464 */     if (paramString.equals("txBufCount")) return supTxBufCount;
/* 1465 */     if (paramString.equals("setTimeoutTx")) return supSetTimeoutTx;
/* 1466 */     if (paramString.equals("noIndexBitRate")) return supNoIndexBitRate;
/* 1467 */     if (paramString.equals("getPortList")) return supGetPortList;
/* 1468 */     if (paramString.equals("setPowerMode")) return supSetPowerMode;
/* 1469 */     if (paramString.equals("getStatusAPM")) return supGetStatusAPM;
/* 1470 */     return false;
/*      */   }
/*      */ 
/*      */   public static String[] getPortList()
/*      */     throws IOException
/*      */   {
/* 1490 */     String str = System.getProperty("SERIAL_PORT_LIST");
/*      */     StringTokenizer localStringTokenizer;
/* 1491 */     if ((str != null) && (str.trim().length() > 4))
/* 1492 */       localStringTokenizer = new StringTokenizer(str, ";:");
/* 1493 */     while (localStringTokenizer.hasMoreTokens()) {
/* 1494 */       addPortName(localStringTokenizer.nextToken()); continue;
/*      */ 
/* 1497 */       if (supGetPortList) {
/* 1498 */         int i = SerGetPortList();
/*      */       }
/* 1501 */       else if (portList.size() > 0) {
/* 1502 */         System.out.println("getPortList: using list created with addPortName");
/*      */       }
/*      */       else {
/* 1505 */         System.out.println("getPortList: port discovery not currently supported on this platform.");
/* 1506 */         System.out.println("Options:");
/* 1507 */         System.out.println("A) addPortName can be used to build a list of device names ");
/* 1508 */         System.out.println("B) java -DSERIAL_PORT_LIST=COM1;COM2;COM3");
/* 1509 */         System.out.println("or java -DSERIAL_PORT_LIST=/dev/ttyS0:/dev/ttyS1");
/*      */       }
/*      */     }
/*      */ 
/* 1513 */     String[] arrayOfString = new String[portList.size()];
/*      */ 
/* 1515 */     Enumeration localEnumeration = portList.elements();
/* 1516 */     int j = 0;
/* 1517 */     while (localEnumeration.hasMoreElements()) {
/* 1518 */       arrayOfString[(j++)] = ((String)localEnumeration.nextElement());
/*      */     }
/*      */ 
/* 1521 */     return arrayOfString;
/*      */   }
/*      */ 
/*      */   public static void addPortName(String paramString)
/*      */   {
/* 1528 */     Enumeration localEnumeration = portList.elements();
/*      */ 
/* 1530 */     while (localEnumeration.hasMoreElements()) {
/* 1531 */       String str = (String)localEnumeration.nextElement();
/* 1532 */       if (str.equalsIgnoreCase(paramString)) {
/* 1533 */         return;
/*      */       }
/*      */     }
/*      */ 
/* 1537 */     portList.addElement(paramString);
/*      */   }
/*      */ 
/*      */   protected void finalize()
/*      */   {
/*      */     try
/*      */     {
/* 1545 */       if (this.flowControl != null) this.flowControl.abort();
/* 1546 */       close();
/*      */     }
/*      */     catch (Exception localException)
/*      */     {
/*      */     }
/*      */   }
/*      */ 
/*      */   private void srvTaskCheck() throws IOException
/*      */   {
/* 1555 */     if (osName.equals("EPOC")) {
/* 1556 */       if ((this.ssTask != null) && 
/* 1557 */         (this.ssTask.isAlive())) {
/* 1558 */         return;
/*      */       }
/* 1560 */       this.config.setPortNum(0);
/* 1561 */       this.ssTask = new SerialServerTask(this);
/* 1562 */       this.ssTask.start();
/* 1563 */       while (this.config.getPort() == 0) try {
/* 1564 */           Thread.sleep(100L);
/*      */         } catch (InterruptedException localInterruptedException) {
/*      */         } if (this.config.getPort() == -1) {
/* 1567 */         String str = "srvTaskCheck: " + this.ssTask.eMsg;
/* 1568 */         throw new IOException(str);
/*      */       }
/*      */ 
/* 1572 */       this.port = (this.config.getPort() - 1);
/*      */     }
/*      */   }
/*      */ 
/*      */   public void setPortName(String paramString)
/*      */   {
/* 1585 */     this.config.setPortName(paramString);
/* 1586 */     devName = paramString;
/*      */   }
/* 1588 */   public String getPortName() { return new String(this.config.getPortName()); } 
/*      */   public void setBitRate(int paramInt) {
/* 1590 */     this.config.setBitRate(paramInt); } 
/* 1591 */   public int getBitRate() { return this.config.getBitRate(); } 
/*      */   public void setDataBits(int paramInt) {
/* 1593 */     this.config.setDataBits(paramInt); } 
/* 1594 */   public int getDataBits() { return this.config.getDataBits(); } 
/*      */   public void setStopBits(int paramInt) {
/* 1596 */     this.config.setStopBits(paramInt); } 
/* 1597 */   public int getStopBits() { return this.config.getStopBits(); } 
/*      */   public void setParity(int paramInt) {
/* 1599 */     this.config.setParity(paramInt); } 
/* 1600 */   public int getParity() { return this.config.getParity(); } 
/*      */   public void setHandshake(int paramInt) {
/* 1602 */     this.config.setHandshake(paramInt); } 
/* 1603 */   public int getHandshake() { return this.config.getHandshake();
/*      */   }
/*      */ 
/*      */   native int SerGetLibVer();
/*      */ 
/*      */   native int SerOpenPort(SerialConfig paramSerialConfig);
/*      */ 
/*      */   native int SerConfigure(SerialConfig paramSerialConfig);
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
/*   89 */       osName = System.getProperty("os.name");
/*   90 */       jdkVer = System.getProperty("java.version");
/*   91 */       vendor = System.getProperty("java.vendor");
/*   92 */       osArch = System.getProperty("os.arch");
/*      */ 
/*   98 */       System.out.println(banner1 + "\r\n" + banner2);
/*   99 */       System.out.println("os.name=\"" + osName + "\"  os.arch=\"" + osArch + "\"");
/*  100 */       featureSetup();
/*      */ 
/*  102 */       setNativeLibName();
/*      */ 
/*  104 */       if (jspLib == null) {
/*  105 */         System.out.println("osName=" + osName + " osArch=" + osArch);
/*  106 */         str1 = "Platform not supported, check VM properties os.name & os.arch";
/*  107 */         System.out.println(str1);
/*  108 */         System.exit(-1);
/*      */       }
/*  110 */       String str1 = System.getProperty("java.home") + "\\bin\\" + jspLib + ".dll";
/*  111 */       String str2 = System.getProperty("user.home") + "\\Medtronic\\" + jspLib + ".dll";
/*  112 */       if (new File(str2).exists())
/*  113 */         System.load(str2);
/*      */       else {
/*  115 */         System.load(str1);
/*      */       }
/*      */ 
/*  118 */       jspLibLoaded = true;
/*  119 */       System.out.println("SerialPort class loaded: " + jspLib);
/*      */     }
/*      */     catch (UnsatisfiedLinkError localUnsatisfiedLinkError) {
/*  122 */       System.out.println(localUnsatisfiedLinkError + ": Check that native library " + jspLib + " is in proper directory");
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