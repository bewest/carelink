/*     */ package minimed.ddms.deviceportreader;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import mdt.common.device.driver.minimed.ArtLogicUSBPort;
/*     */ import mdt.common.device.driver.minimed.JungoUSBPort;
/*     */ import mdt.common.device.driver.minimed.JungoUSBPort;
/*     */ import minimed.util.Contract;
/*     */ import minimed.util.OSInfo;
/*     */ 
/*     */ final class USBComm extends CommPort
/*     */ {
/*     */   private static final int d = 256;
/*     */   private static final int FIFO_SIZE = 64;
/*     */   private JungoUSBPort m_USBPort;           // b
/*  57 */   private boolean m_initialized = false;    // a
/*     */   private long m_startTimeMS;               // _
/*     */   private byte[] m_writeBuffer;             // Z
/*     */   private boolean m_logFake;                // e
/*     */ 
/*     */   USBComm(USBPort paramUSBPort) // DA
/*     */   {
/*  71 */     this.m_USBPort = paramUSBPort;
/*  72 */     resetClock();
/*  73 */     setContinueIO(true);
/*  74 */     MedicalDevice.logInfo(this, "Created communications device " + toString());
/*  75 */     setLogFake();
/*     */   }
/*     */ 
/*     */   USBComm()
/*     */   {
/*  83 */     this(OSInfo.isMac() ? new ArtLogicUSBPort() : new JungoUSBPort());
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/*  95 */     return "USB Communications using " + this.m_USBPort + ", version " + this.m_USBPort.getVersion();
/*     */   }
/*     */   void Y()
/*     */   void setLogFake()
/*     */   {
/* 102 */     this.m_logFake = true;
/*     */   }
/*     */   // void S()
/*     */   void setLogReal()
/*     */   {
/* 109 */     this.m_logFake = false;
/*     */   }
/*     */   // void _()
/*     */   void initCommunications()
/*     */     throws IOException, SerialIOHaltedException
/*     */   {
/* 120 */     this.m_USBPort.initCommunications();
/* 121 */     this.m_initialized = true;
/*     */   }
/*     */   // void V()
/*     */   void endCommunications()
/*     */   {
/* 128 */     this.m_USBPort.endCommunications();
/* 129 */     this.m_initialized = false;
/*     */   }
/*     */   // void T()
/*     */   void clearBuffers()
/*     */     throws IOException
/*     */   {
/* 138 */     this.m_USBPort.clearBuffers();
/*     */   }
/*     */   // void A(String paramString) throws IOException
/*     */   void A(String paramString) throws IOException
/*     */   {
/* 143 */     int[] arrayOfInt = MedicalDevice.Util.makeIntArray(paramString);
/* 144 */     write(arrayOfInt);
/*     */   }
/*     */   // void K(int paramInt)
/*     */   void write(int paramInt)
/*     */     throws IOException, SerialIOHaltedException
/*     */   {
/* 158 */     int[] arrayOfInt = new int[1];
/* 159 */     arrayOfInt[0] = paramInt;
/* 160 */     write(arrayOfInt);
/*     */   }
/*     */   // void C(int[] paramArrayOfInt)
/*     */   void write(int[] paramArrayOfInt)
/*     */     throws IOException, SerialIOHaltedException
/*     */   {
/* 174 */     checkInitialized();
/* 175 */     checkForSerialIOHalted(); // G() inherited from CommPort
/* 176 */     setWriteBuffer(paramArrayOfInt);
/*     */
/*     */   // ===================================================================================================
/*     */   // NOTE ABOUT 2012 version
/*     */   // Changed Medical.Utils.getHexCompact by USBComm.getHexCompact
/*     */   // My impresion is that module only dumps sumatory of bytes instead real bytes
/*     */   // TODO: Patch USBComm.getHexCompact with Medical.Utils.getHexCompact to see if dumps real Historical Data
/*     */   // ===================================================================================================
/*     */
/* 179 */     MedicalDevice.logInfoHigh(this, "write(" + getClockMS() + "MS)[" + paramArrayOfInt.length + "]: <" + getHexCompact(paramArrayOfInt) + ">");
/*     */ 
/* 181 */     resetClock();
/* 182 */     writeIO();
/*     */   }
/*     */   // int[] J(int paramInt)
/*     */   int[] writeAndRead(int paramInt)
/*     */     throws IOException, SerialIOHaltedException
/*     */   {
/* 197 */     write(paramInt);
/* 198 */     return read();
/*     */   }
/*     */   // int[] F(int[] paramArrayOfInt)
/*     */   int[] writeAndRead(int[] paramArrayOfInt)
/*     */     throws IOException, SerialIOHaltedException
/*     */   {
/* 213 */     write(paramArrayOfInt);
/* 214 */     return read();
/*     */   }
/*     */   // int[] A(int[] paramArrayOfInt, int paramInt)
/*     */   int[] writeAndRead(int[] paramArrayOfInt, int paramInt)
/*     */     throws IOException, SerialIOHaltedException
/*     */   {
/* 231 */     write(paramArrayOfInt);
/* 232 */     return read(paramInt);
/*     */   }
/*     */   // int[] R()
/*     */   int[] read()
/*     */     throws IOException, SerialIOHaltedException
/*     */   {
/* 245 */     return read(64);
/*     */   }
/*     */   // int[] I(int paramInt)
/*     */   int[] read(int paramInt) 
/*     */     throws IOException, SerialIOHaltedException
/*     */   {
/* 260 */     checkInitialized();
/* 261 */     checkForSerialIOHalted(); // G() inherited from CommPort
/*     */ 
/* 264 */     int[] arrayOfInt1 = readIO(paramInt);
/*     */ 
/* 267 */     int[] arrayOfInt2 = new int[paramInt];
/* 268 */     System.arraycopy(arrayOfInt1, 0, arrayOfInt2, 0, paramInt);
/*     */
/*     */   // ===================================================================================================
/*     */   // NOTE ABOUT 2012 version
/*     */   // They change Medical.Utils.getHexCompact by USBComm.getHexCompact
/*     */   // My impresion is that module only dumps sumatory of bytes instead real bytes
/*     */   // TODO: Patch USBComm.getHexCompact with Medical.Utils.getHexCompact to see if dumps real Historical Data
/*     */   // ===================================================================================================
/*     */
/* 271 */     MedicalDevice.logInfoHigh(this, "read(" + getClockMS() + "MS)[" + arrayOfInt2.length + "]: <" + getHexCompact(arrayOfInt2) + ">");
/*     */ 
/* 273 */     resetClock();
/* 274 */     Contract.post(paramInt == arrayOfInt2.length);
/* 275 */     return arrayOfInt2;
/*     */   }
/*     */   // String C() throws IOException
/*     */   String write() throws IOException
/*     */   {
/* 280 */     int[] arrayOfInt = read(256);
/* 281 */     return MedicalDevice.Util.makeString(arrayOfInt);
/*     */   }
/*     */   // static void A(l paraml)
/*     */   static void addPnPListener(USBPnPNotifier paramUSBPnPNotifier)
/*     */   {
/* 290 */     if (OSInfo.isMac())
/* 291 */       ArtLogicUSBPort.addPnPListener(paramUSBPnPNotifier);
/*     */     else
/* 293 */       JungoUSBPort.addPnPListener(paramUSBPnPNotifier);
/*     */   }
/*     */   // private void W()
/*     */   private void writeIO()
/*     */     throws IOException
/*     */   {
/* 303 */     this.m_USBPort.write(this.m_writeBuffer);
/*     */   }
/*     */   // private void D(int[] paramArrayOfInt)
/*     */   private void setWriteBuffer(int[] paramArrayOfInt)
/*     */   {
/* 313 */     this.m_writeBuffer = convertIntsToBytes(paramArrayOfInt);
/*     */   }
/*     */   // void X()
/*     */   private void checkInitialized()
/*     */     throws IOException
/*     */   {
/* 322 */     if (!this.m_initialized)
/* 323 */       throw new IOException("USB Communications not initialized");
/*     */   }
/*     */   // private int[] H(int paramInt)
/*     */   private int[] readIO(int paramInt)
/*     */     throws IOException
/*     */   {
/* 334 */     byte[] arrayOfByte = this.m_USBPort.read(paramInt);
/* 335 */     return convertBytesToInts(arrayOfByte);
/*     */   }
/*     */   // private byte[] E(int[] paramArrayOfInt)
/*     */   private byte[] convertIntsToBytes(int[] paramArrayOfInt)
/*     */   {
/* 346 */     byte[] arrayOfByte = new byte[paramArrayOfInt.length];
/* 347 */     for (int i = 0; i < paramArrayOfInt.length; i++) {
/* 348 */       Contract.pre(paramArrayOfInt[i], 0, 255);
/* 349 */       arrayOfByte[i] = (byte)MedicalDevice.Util.getLowByte(paramArrayOfInt[i]);
/*     */     }
/* 351 */     return arrayOfByte;
/*     */   }
/*     */   // private int[] C(byte[] paramArrayOfByte)
/*     */   private int[] convertBytesToInts(byte[] paramArrayOfByte)
/*     */   {
/* 361 */     int[] arrayOfInt = new int[paramArrayOfByte.length];
/*     */ 
/* 363 */     for (int i = 0; i < paramArrayOfByte.length; i++) {
/* 364 */       paramArrayOfByte[i] &= 255;
/*     */     }
/* 366 */     return arrayOfInt;
/*     */   }
/*     */   // private void Z()
/*     */   private void resetClock()
/*     */   {
/* 373 */     this.m_startTimeMS = System.currentTimeMillis();
/*     */   }
/*     */   // private long U()
/*     */   private long getClockMS()
/*     */   {
/* 382 */     return System.currentTimeMillis() - this.m_startTimeMS;
/*     */   }
/*     */   // private String G(int[] paramArrayOfInt)
/*     */   private String getHexCompact(int[] paramArrayOfInt)
/*     */   {
/* 392 */     String str = this.m_logFake ? MedicalDevice.Util.calculateSUM(paramArrayOfInt) : MedicalDevice.Util.convertControlChars(MedicalDevice.Util.makeString(paramArrayOfInt));
/* 394 */     return str;
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.A.DA
 * JD-Core Version:    0.6.0
 */