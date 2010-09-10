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
/*  61 */   private static final int[] bitRateNumbers = { 110, 150, 300, 600, 1200, 2400, 4800, 9600, 19200, 38400, 57600, 115200, 230400, 460800 };
/*     */   public static final int PY_NONE = 0;
/*     */   public static final int PY_ODD = 1;
/*     */   public static final int PY_EVEN = 2;
/*     */   public static final int PY_MARK = 3;
/*     */   public static final int PY_SPACE = 4;
/*  71 */   private static final String[] parityNames = { "NONE", "ODD", "EVEN", "MARK", "SPACE" };
/*     */   public static final int LN_5BITS = 0;
/*     */   public static final int LN_6BITS = 1;
/*     */   public static final int LN_7BITS = 2;
/*     */   public static final int LN_8BITS = 3;
/*     */   public static final int ST_1BITS = 0;
/*     */   public static final int ST_2BITS = 1;
/*     */   public static final int PWR_STANDBY = 0;
/*     */   public static final int PWR_ACTIVE = 1;
/*  89 */   private int port = 0;
/*  90 */   private String sPortName = "";
/*  91 */   private byte[] portName = new byte[1];
/*  92 */   private byte[] baseName = new byte[1];
/*  93 */   private int txLen = 4096;
/*  94 */   private int rxLen = 4096;
/*  95 */   private int bitRate = 7;
/*  96 */   private int parity = 0;
/*  97 */   private int dataBits = 3;
/*  98 */   private int stopBits = 0;
/*  99 */   private int handshake = 0;
/* 100 */   private boolean hardFlow = true;
/*     */   private String osName;
/* 102 */   private boolean disableActiveLink = false;
/*     */ 
/*     */   /** @deprecated */
/*     */   public SerialConfig(int paramInt)
/*     */   {
/* 115 */     this.osName = new String(System.getProperty("os.name"));
/* 116 */     this.port = paramInt;
/* 117 */     setBaseName("/dev/ttyS");
/*     */   }
/*     */ 
/*     */   /** @deprecated */
/*     */   public SerialConfig(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 136 */     this.osName = new String(System.getProperty("os.name"));
/* 137 */     this.port = paramInt1;
/* 138 */     this.txLen = paramInt2;
/* 139 */     this.rxLen = paramInt3;
/* 140 */     setBaseName("/dev/ttyS");
/*     */   }
/*     */ 
/*     */   public SerialConfig(String paramString)
/*     */   {
/* 155 */     this.osName = new String(System.getProperty("os.name"));
/* 156 */     this.sPortName = paramString;
/* 157 */     this.portName = paramString.getBytes();
/*     */   }
/*     */ 
/*     */   public SerialConfig()
/*     */   {
/* 164 */     this.osName = new String(System.getProperty("os.name"));
/* 165 */     this.sPortName = "COM1";
/* 166 */     this.portName = "COM1".getBytes();
/* 167 */     setBaseName("/dev/ttyS");
/*     */   }
/*     */ 
/*     */   public void setPortNum(int paramInt)
/*     */     throws IllegalArgumentException
/*     */   {
/* 178 */     if ((paramInt < 0) && (!this.osName.equals("EPOC")))
/* 179 */       throw new IllegalArgumentException("Invalid Port Number: " + paramInt);
/* 180 */     this.port = paramInt;
/*     */   }
/*     */ 
/*     */   public void setPortName(String paramString)
/*     */   {
/* 187 */     this.sPortName = paramString;
/* 188 */     this.portName = paramString.getBytes();
/*     */   }
/*     */ 
/*     */   public void setBitRate(int paramInt)
/*     */     throws IllegalArgumentException
/*     */   {
/* 197 */     if ((paramInt < 0) || (paramInt > 460800))
/* 198 */       throw new IllegalArgumentException("Invalid Serial Port Bit Rate");
/* 199 */     this.bitRate = paramInt;
/*     */   }
/*     */ 
/*     */   protected boolean validBitRateIndex()
/*     */   {
/* 206 */     return (this.bitRate >= 0) && (this.bitRate <= 12);
/*     */   }
/*     */ 
/*     */   public void setParity(int paramInt)
/*     */     throws IllegalArgumentException
/*     */   {
/* 216 */     if ((paramInt < 0) || (paramInt > 4))
/* 217 */       throw new IllegalArgumentException("Invalid Serial Port Parity");
/* 218 */     this.parity = paramInt;
/*     */   }
/*     */ 
/*     */   public void setDataBits(int paramInt)
/*     */     throws IllegalArgumentException
/*     */   {
/* 226 */     if ((paramInt < 0) || (paramInt > 3))
/* 227 */       throw new IllegalArgumentException("Invalid Serial Port Data Bits");
/* 228 */     this.dataBits = paramInt;
/*     */   }
/*     */ 
/*     */   public void setStopBits(int paramInt)
/*     */     throws IllegalArgumentException
/*     */   {
/* 236 */     if ((paramInt < 0) || (paramInt > 1))
/* 237 */       throw new IllegalArgumentException("Invalid Serial Port Stop Bits");
/* 238 */     this.stopBits = paramInt;
/*     */   }
/*     */ 
/*     */   public void setHandshake(int paramInt)
/*     */     throws IllegalArgumentException
/*     */   {
/* 247 */     int i = 0;
/* 248 */     if ((this.osName.equals("Windows 95")) || (this.osName.equals("Windows NT")) || (this.osName.equals("Windows CE")) || (this.osName.equals("Windows 98")) || (this.osName.equals("Windows 2000")) || (this.osName.equals("OS/2")))
/*     */     {
/* 254 */       i = 1;
/*     */     }
/* 256 */     if ((paramInt == 3) && (i == 0)) {
/* 257 */       throw new IllegalArgumentException("DSRDTR handshaking not available on this platform");
/*     */     }
/* 259 */     if ((paramInt < 0) || ((paramInt > 3) && (paramInt < 16)) || (paramInt > 240)) {
/* 260 */       throw new IllegalArgumentException("Invalid Serial Port Handshake");
/*     */     }
/* 262 */     this.handshake = paramInt;
/*     */   }
/*     */ 
/*     */   public void setBaseName(String paramString)
/*     */     throws IllegalArgumentException
/*     */   {
/* 270 */     this.baseName = paramString.getBytes();
/*     */   }
/*     */ 
/*     */   public void setHardFlow(boolean paramBoolean)
/*     */   {
/* 284 */     this.hardFlow = paramBoolean;
/*     */   }
/*     */ 
/*     */   public void setTxLen(int paramInt)
/*     */   {
/* 292 */     if ((this.osName.equals("EPOC")) && (paramInt > 8192))
/* 293 */       throw new IllegalArgumentException("Limited to 8192 on EPOC");
/* 294 */     this.txLen = paramInt;
/*     */   }
/*     */ 
/*     */   public void setRxLen(int paramInt)
/*     */   {
/* 302 */     if ((this.osName.equals("EPOC")) && (paramInt > 8192))
/* 303 */       throw new IllegalArgumentException("Limited to 8192 on EPOC");
/* 304 */     this.rxLen = paramInt;
/*     */   }
/*     */ 
/*     */   public int getPort()
/*     */   {
/* 311 */     return this.port;
/*     */   }
/*     */ 
/*     */   public int getTxLen()
/*     */   {
/* 316 */     return this.txLen;
/*     */   }
/*     */ 
/*     */   public int getRxLen()
/*     */   {
/* 321 */     return this.rxLen;
/*     */   }
/*     */ 
/*     */   public int getBitRate()
/*     */   {
/* 326 */     return this.bitRate;
/*     */   }
/*     */ 
/*     */   public int getBitRateNumber(int paramInt)
/*     */   {
/* 332 */     if (this.bitRate > 30) return this.bitRate;
/* 333 */     return bitRateNumbers[paramInt];
/*     */   }
/*     */ 
/*     */   public int getBitRateIndex(int paramInt)
/*     */   {
/* 341 */     for (int i = 0; i < bitRateNumbers.length; i++) {
/* 342 */       if (paramInt == bitRateNumbers[i]) {
/* 343 */         return i;
/*     */       }
/*     */     }
/* 346 */     return -1;
/*     */   }
/*     */ 
/*     */   public int getParity()
/*     */   {
/* 352 */     return this.parity;
/*     */   }
/*     */ 
/*     */   public int getDataBits()
/*     */   {
/* 357 */     return this.dataBits;
/*     */   }
/*     */ 
/*     */   public int getStopBits()
/*     */   {
/* 362 */     return this.stopBits;
/*     */   }
/*     */ 
/*     */   public int getHandshake()
/*     */   {
/* 367 */     return this.handshake;
/*     */   }
/*     */ 
/*     */   public byte[] getPortName()
/*     */   {
/* 372 */     return this.portName;
/*     */   }
/*     */ 
/*     */   public String getPortNameString()
/*     */   {
/* 377 */     return this.sPortName;
/*     */   }
/*     */ 
/*     */   public byte[] getBaseName()
/*     */   {
/* 382 */     return this.baseName;
/*     */   }
/*     */ 
/*     */   public boolean getHardFlow()
/*     */   {
/* 388 */     return this.hardFlow;
/*     */   }
/*     */ 
/*     */   public boolean getDisableActiveLink()
/*     */   {
/* 394 */     return this.disableActiveLink;
/*     */   }
/*     */ 
/*     */   public void setDisableActiveLink(boolean paramBoolean)
/*     */   {
/* 403 */     this.disableActiveLink = paramBoolean;
/*     */   }
/*     */ 
/*     */   public String getSettingsString()
/*     */   {
/* 409 */     String str1 = "" + getBitRateNumber(this.bitRate);
/*     */ 
/* 411 */     str1 = str1 + " / " + parityNames[this.parity];
/* 412 */     str1 = str1 + " / " + (char)(53 + this.dataBits);
/* 413 */     str1 = str1 + " / " + (char)(49 + this.stopBits);
/*     */ 
/* 415 */     str1 = str1 + " / ";
/* 416 */     if ((this.handshake & 0xF0) > 0) {
/* 417 */       if ((this.handshake & 0x10) == 16) str1 = str1 + " HI";
/* 418 */       if ((this.handshake & 0x20) == 32) str1 = str1 + " HO";
/* 419 */       if ((this.handshake & 0x40) == 64) str1 = str1 + " SI";
/* 420 */       if ((this.handshake & 0x80) == 128) str1 = str1 + " SO"; 
/*     */     }
/*     */     else
/*     */     {
/* 423 */       String str2 = handshakeNames[this.handshake];
/* 424 */       if ((this.osName.equals("Mac OS")) && (this.handshake == 2))
/* 425 */         str2 = "CTS-DTR";
/* 426 */       str1 = str1 + str2;
/*     */     }
/*     */ 
/* 429 */     return str1;
/*     */   }
/*     */ 
/*     */   public SerialConfig copy()
/*     */     throws CloneNotSupportedException
/*     */   {
/* 437 */     return (SerialConfig)super.clone();
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     Serialio.SerialConfig
 * JD-Core Version:    0.6.0
 */