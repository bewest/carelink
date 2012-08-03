/*     */ package Serialio;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class SerialConfig
/*     */   implements Cloneable, Serializable
/*     */ {
/*     */   public static final int HS_NONE = 0;
/*     */   public static final int HS_XONXOFF = 1;
/*     */   public static final int HS_CTSRTS = 2;
/*     */   public static final int HS_CTSDTR = 2;
/*     */   public static final int HS_DSRDTR = 3;
/*     */   public static final int HS_HARD_IN = 16;
/*     */   public static final int HS_HARD_OUT = 32;
/*     */   public static final int HS_SOFT_IN = 64;
/*     */   public static final int HS_SOFT_OUT = 128;
/*     */   public static final int HS_SPLIT_MASK = 240;
/*  43 */   private static final String[] handshakeNames = { "NONE", "XON-XOFF", "CTS-RTS", "DSR-DTR" };
/*     */   public static final int BR_110 = 0;
/*     */   public static final int BR_150 = 1;
/*     */   public static final int BR_300 = 2;
/*     */   public static final int BR_600 = 3;
/*     */   public static final int BR_1200 = 4;
/*     */   public static final int BR_2400 = 5;
/*     */   public static final int BR_4800 = 6;
/*     */   public static final int BR_9600 = 7;
/*     */   public static final int BR_19200 = 8;
/*     */   public static final int BR_38400 = 9;
/*     */   public static final int BR_57600 = 10;
/*     */   public static final int BR_115200 = 11;
/*     */   public static final int BR_230400 = 12;
/*     */   public static final int BR_460800 = 13;
/*     */   public static final int BR_921600 = 14;
/*  62 */   private static final int[] bitRateNumbers = { 110, 150, 300, 600, 1200, 2400, 4800, 9600, 19200, 38400, 57600, 115200, 230400, 460800, 921600 };
/*     */   public static final int PY_NONE = 0;
/*     */   public static final int PY_ODD = 1;
/*     */   public static final int PY_EVEN = 2;
/*     */   public static final int PY_MARK = 3;
/*     */   public static final int PY_SPACE = 4;
/*  72 */   private static final String[] parityNames = { "NONE", "ODD", "EVEN", "MARK", "SPACE" };
/*     */   public static final int LN_5BITS = 0;
/*     */   public static final int LN_6BITS = 1;
/*     */   public static final int LN_7BITS = 2;
/*     */   public static final int LN_8BITS = 3;
/*     */   public static final int ST_1BITS = 0;
/*     */   public static final int ST_2BITS = 1;
/*     */   public static final int PWR_STANDBY = 0;
/*     */   public static final int PWR_ACTIVE = 1;
/*  90 */   private int port = 0;
/*  91 */   private String sPortName = "";
/*  92 */   private byte[] portName = new byte[1];
/*  93 */   private byte[] baseName = new byte[1];
/*  94 */   private int txLen = 4096;
/*  95 */   private int rxLen = 4096;
/*  96 */   private int bitRate = 7;
/*  97 */   private int parity = 0;
/*  98 */   private int dataBits = 3;
/*  99 */   private int stopBits = 0;
/* 100 */   private int handshake = 0;
/* 101 */   private boolean hardFlow = true;
/*     */   private String osName;
/* 103 */   private boolean disableActiveLink = false;
/*     */ 
/*     */   public SerialConfig(int paramInt)
/*     */   {
/* 116 */     this.osName = new String(System.getProperty("os.name"));
/* 117 */     this.port = paramInt;
/* 118 */     setBaseName("/dev/ttyS");
/*     */   }
/*     */ 
/*     */   public SerialConfig(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 137 */     this.osName = new String(System.getProperty("os.name"));
/* 138 */     this.port = paramInt1;
/* 139 */     this.txLen = paramInt2;
/* 140 */     this.rxLen = paramInt3;
/* 141 */     setBaseName("/dev/ttyS");
/*     */   }
/*     */ 
/*     */   public SerialConfig(String paramString)
/*     */   {
/* 156 */     this.osName = new String(System.getProperty("os.name"));
/* 157 */     this.sPortName = paramString;
/* 158 */     this.portName = paramString.getBytes();
/*     */   }
/*     */ 
/*     */   public SerialConfig()
/*     */   {
/* 165 */     this.osName = new String(System.getProperty("os.name"));
/* 166 */     this.sPortName = "COM1";
/* 167 */     this.portName = "COM1".getBytes();
/* 168 */     setBaseName("/dev/ttyS");
/*     */   }
/*     */ 
/*     */   public void setPortNum(int paramInt)
/*     */     throws IllegalArgumentException
/*     */   {
/* 179 */     if ((paramInt < 0) && (!this.osName.equals("EPOC")))
/* 180 */       throw new IllegalArgumentException("Invalid Port Number: " + paramInt);
/* 181 */     this.port = paramInt;
/*     */   }
/*     */ 
/*     */   public void setPortName(String paramString)
/*     */   {
/* 188 */     this.sPortName = paramString;
/* 189 */     this.portName = paramString.getBytes();
/*     */   }
/*     */ 
/*     */   public void setBitRate(int paramInt)
/*     */     throws IllegalArgumentException
/*     */   {
/* 198 */     if ((paramInt < 0) || (paramInt > 921600))
/* 199 */       throw new IllegalArgumentException("Invalid Serial Port Bit Rate");
/* 200 */     this.bitRate = paramInt;
/*     */   }
/*     */ 
/*     */   protected boolean validBitRateIndex()
/*     */   {
/* 207 */     return (this.bitRate >= 0) && (this.bitRate <= 14);
/*     */   }
/*     */ 
/*     */   public void setParity(int paramInt)
/*     */     throws IllegalArgumentException
/*     */   {
/* 217 */     if ((paramInt < 0) || (paramInt > 4))
/* 218 */       throw new IllegalArgumentException("Invalid Serial Port Parity");
/* 219 */     this.parity = paramInt;
/*     */   }
/*     */ 
/*     */   public void setDataBits(int paramInt)
/*     */     throws IllegalArgumentException
/*     */   {
/* 227 */     if ((paramInt < 0) || (paramInt > 3))
/* 228 */       throw new IllegalArgumentException("Invalid Serial Port Data Bits");
/* 229 */     this.dataBits = paramInt;
/*     */   }
/*     */ 
/*     */   public void setStopBits(int paramInt)
/*     */     throws IllegalArgumentException
/*     */   {
/* 237 */     if ((paramInt < 0) || (paramInt > 1))
/* 238 */       throw new IllegalArgumentException("Invalid Serial Port Stop Bits");
/* 239 */     this.stopBits = paramInt;
/*     */   }
/*     */ 
/*     */   public void setHandshake(int paramInt)
/*     */     throws IllegalArgumentException
/*     */   {
/* 248 */     int i = 0;
/* 249 */     if ((this.osName.equals("Windows 95")) || (this.osName.equals("Windows NT")) || (this.osName.equals("Windows CE")) || (this.osName.equals("Windows 98")) || (this.osName.equals("Windows XP")) || (this.osName.equals("Windows 2000")) || (this.osName.equals("Windows 2003")) || (this.osName.equals("OS/2")))
/*     */     {
/* 257 */       i = 1;
/*     */     }
/* 259 */     if ((paramInt == 3) && (i == 0)) {
/* 260 */       throw new IllegalArgumentException("DSRDTR handshaking not available on this platform");
/*     */     }
/* 262 */     if ((paramInt < 0) || ((paramInt > 3) && (paramInt < 16)) || (paramInt > 240)) {
/* 263 */       throw new IllegalArgumentException("Invalid Serial Port Handshake");
/*     */     }
/* 265 */     this.handshake = paramInt;
/*     */   }
/*     */ 
/*     */   public void setBaseName(String paramString)
/*     */     throws IllegalArgumentException
/*     */   {
/* 273 */     this.baseName = paramString.getBytes();
/*     */   }
/*     */ 
/*     */   public void setHardFlow(boolean paramBoolean)
/*     */   {
/* 287 */     this.hardFlow = paramBoolean;
/*     */   }
/*     */ 
/*     */   public void setTxLen(int paramInt)
/*     */   {
/* 295 */     if ((this.osName.equals("EPOC")) && (paramInt > 8192))
/* 296 */       throw new IllegalArgumentException("Limited to 8192 on EPOC");
/* 297 */     this.txLen = paramInt;
/*     */   }
/*     */ 
/*     */   public void setRxLen(int paramInt)
/*     */   {
/* 305 */     if ((this.osName.equals("EPOC")) && (paramInt > 8192))
/* 306 */       throw new IllegalArgumentException("Limited to 8192 on EPOC");
/* 307 */     this.rxLen = paramInt;
/*     */   }
/*     */ 
/*     */   public int getPort()
/*     */   {
/* 314 */     return this.port;
/*     */   }
/*     */ 
/*     */   public int getTxLen()
/*     */   {
/* 319 */     return this.txLen;
/*     */   }
/*     */ 
/*     */   public int getRxLen()
/*     */   {
/* 324 */     return this.rxLen;
/*     */   }
/*     */ 
/*     */   public int getBitRate()
/*     */   {
/* 329 */     return this.bitRate;
/*     */   }
/*     */ 
/*     */   public int getBitRateNumber(int paramInt)
/*     */   {
/* 335 */     if (this.bitRate > 30) return this.bitRate;
/* 336 */     return bitRateNumbers[paramInt];
/*     */   }
/*     */ 
/*     */   public int getBitRateIndex(int paramInt)
/*     */   {
/* 344 */     for (int i = 0; i < bitRateNumbers.length; i++) {
/* 345 */       if (paramInt == bitRateNumbers[i]) {
/* 346 */         return i;
/*     */       }
/*     */     }
/* 349 */     return -1;
/*     */   }
/*     */ 
/*     */   public int getParity()
/*     */   {
/* 355 */     return this.parity;
/*     */   }
/*     */ 
/*     */   public int getDataBits()
/*     */   {
/* 360 */     return this.dataBits;
/*     */   }
/*     */ 
/*     */   public int getStopBits()
/*     */   {
/* 365 */     return this.stopBits;
/*     */   }
/*     */ 
/*     */   public int getHandshake()
/*     */   {
/* 370 */     return this.handshake;
/*     */   }
/*     */ 
/*     */   public byte[] getPortName()
/*     */   {
/* 375 */     return this.portName;
/*     */   }
/*     */ 
/*     */   public String getPortNameString()
/*     */   {
/* 380 */     return this.sPortName;
/*     */   }
/*     */ 
/*     */   public byte[] getBaseName()
/*     */   {
/* 385 */     return this.baseName;
/*     */   }
/*     */ 
/*     */   public boolean getHardFlow()
/*     */   {
/* 391 */     return this.hardFlow;
/*     */   }
/*     */ 
/*     */   public boolean getDisableActiveLink()
/*     */   {
/* 397 */     return this.disableActiveLink;
/*     */   }
/*     */ 
/*     */   public void setDisableActiveLink(boolean paramBoolean)
/*     */   {
/* 406 */     this.disableActiveLink = paramBoolean;
/*     */   }
/*     */ 
/*     */   public String getSettingsString()
/*     */   {
/* 412 */     String str1 = "" + getBitRateNumber(this.bitRate);
/*     */ 
/* 414 */     str1 = str1 + " / " + parityNames[this.parity];
/* 415 */     str1 = str1 + " / " + (char)(53 + this.dataBits);
/* 416 */     str1 = str1 + " / " + (char)(49 + this.stopBits);
/*     */ 
/* 418 */     str1 = str1 + " / ";
/* 419 */     if ((this.handshake & 0xF0) > 0) {
/* 420 */       if ((this.handshake & 0x10) == 16) str1 = str1 + " HI";
/* 421 */       if ((this.handshake & 0x20) == 32) str1 = str1 + " HO";
/* 422 */       if ((this.handshake & 0x40) == 64) str1 = str1 + " SI";
/* 423 */       if ((this.handshake & 0x80) == 128) str1 = str1 + " SO"; 
/*     */     }
/*     */     else
/*     */     {
/* 426 */       String str2 = handshakeNames[this.handshake];
/* 427 */       if ((this.osName.equals("Mac OS")) && (this.handshake == 2))
/* 428 */         str2 = "CTS-DTR";
/* 429 */       str1 = str1 + str2;
/*     */     }
/*     */ 
/* 432 */     return str1;
/*     */   }
/*     */ 
/*     */   public SerialConfig copy()
/*     */     throws CloneNotSupportedException
/*     */   {
/* 440 */     return (SerialConfig)super.clone();
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     Serialio.SerialConfig
 * JD-Core Version:    0.6.0
 */