/*     */ package minimed.ddms.deviceportreader;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import mdt.common.device.driver.minimed.JungoUSBPort;
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ final class USBComm extends CommPort
/*     */ {
/*     */   private static final int FIFO_SIZE = 64;
/*     */   private JungoUSBPort m_USBPort;
/*  57 */   private boolean m_initialized = false;
/*     */   private long m_startTimeMS;
/*     */   private byte[] m_writeBuffer;
/*     */ 
/*     */   USBComm()
/*     */   {
/*  67 */     this.m_USBPort = new JungoUSBPort();
/*  68 */     resetClock();
/*  69 */     setContinueIO(true);
/*  70 */     MedicalDevice.logInfo(this, "Created communications device " + toString());
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/*  81 */     return "USB Communications using " + this.m_USBPort;
/*     */   }
/*     */ 
/*     */   void initCommunications()
/*     */     throws IOException, SerialIOHaltedException
/*     */   {
/*  92 */     this.m_USBPort.initCommunications();
/*  93 */     this.m_initialized = true;
/*     */   }
/*     */ 
/*     */   void endCommunications()
/*     */   {
/* 100 */     this.m_USBPort.endCommunications();
/* 101 */     this.m_initialized = false;
/*     */   }
/*     */ 
/*     */   void clearBuffers()
/*     */     throws IOException
/*     */   {
/* 110 */     this.m_USBPort.clearBuffers();
/*     */   }
/*     */ 
/*     */   void write(int paramInt)
/*     */     throws IOException, SerialIOHaltedException
/*     */   {
/* 124 */     checkInitialized();
/* 125 */     int[] arrayOfInt = new int[1];
/* 126 */     arrayOfInt[0] = paramInt;
/* 127 */     write(arrayOfInt);
/*     */   }
/*     */ 
/*     */   void write(int[] paramArrayOfInt)
/*     */     throws IOException, SerialIOHaltedException
/*     */   {
/* 141 */     checkInitialized();
/* 142 */     setWriteBuffer(paramArrayOfInt);
/*     */ 
/* 145 */     MedicalDevice.logInfoHigh(this, "write(" + getClockMS() + "MS)[" + paramArrayOfInt.length + "]: <" + MedicalDevice.Util.getHexCompact(paramArrayOfInt) + ">");
/*     */ 
/* 147 */     resetClock();
/* 148 */     writeIO();
/*     */   }
/*     */ 
/*     */   int[] writeAndRead(int paramInt)
/*     */     throws IOException, SerialIOHaltedException
/*     */   {
/* 163 */     checkInitialized();
/* 164 */     write(paramInt);
/* 165 */     return read();
/*     */   }
/*     */ 
/*     */   int[] writeAndRead(int[] paramArrayOfInt)
/*     */     throws IOException, SerialIOHaltedException
/*     */   {
/* 180 */     checkInitialized();
/* 181 */     write(paramArrayOfInt);
/* 182 */     return read();
/*     */   }
/*     */ 
/*     */   int[] writeAndRead(int[] paramArrayOfInt, int paramInt)
/*     */     throws IOException, SerialIOHaltedException
/*     */   {
/* 199 */     checkInitialized();
/* 200 */     write(paramArrayOfInt);
/* 201 */     return read(paramInt);
/*     */   }
/*     */ 
/*     */   int[] read()
/*     */     throws IOException, SerialIOHaltedException
/*     */   {
/* 214 */     return read(FIFO_SIZE);
/*     */   }
/*     */ 
/*     */   int[] read(int paramInt)
/*     */     throws IOException, SerialIOHaltedException
/*     */   {
/* 229 */     checkInitialized();
/*     */ 
/* 232 */     int[] arrayOfInt1 = readIO();
/*     */ 
/* 235 */     while (arrayOfInt1.length < paramInt) {
/* 237 */       arrayOfInt1 = MedicalDevice.Util.concat(arrayOfInt1, readIO());
/*     */     }
/*     */ 
/* 241 */     int[] arrayOfInt2 = new int[paramInt];
/* 242 */     System.arraycopy(arrayOfInt1, 0, arrayOfInt2, 0, paramInt);
/*     */ 
/* 245 */     MedicalDevice.logInfoHigh(this, "read(" + getClockMS() + "MS)[" + arrayOfInt2.length + "]: <" + MedicalDevice.Util.getHexCompact(arrayOfInt2) + ">");
/*     */ 
/* 247 */     resetClock();
/* 248 */     Contract.post(paramInt == arrayOfInt2.length);
/* 249 */     return arrayOfInt2;
/*     */   }
/*     */ 
/*     */   static void addPnPListener(USBPnPNotifier paramUSBPnPNotifier)
/*     */   {
/* 258 */     JungoUSBPort.addPnPListener(paramUSBPnPNotifier);
/*     */   }
/*     */ 
/*     */   private void writeIO()
/*     */     throws IOException
/*     */   {
/* 267 */     this.m_USBPort.write(this.m_writeBuffer);
/*     */   }
/*     */ 
/*     */   private void setWriteBuffer(int[] paramArrayOfInt)
/*     */   {
/* 277 */     this.m_writeBuffer = convertIntsToBytes(paramArrayOfInt);
/*     */   }
/*     */ 
/*     */   private void checkInitialized()
/*     */     throws IOException
/*     */   {
/* 286 */     if (!this.m_initialized)
/* 287 */       throw new IOException("USB Communications not initialized");
/*     */   }
/*     */ 
/*     */   private int[] readIO()
/*     */     throws IOException
/*     */   {
/* 298 */     byte[] arrayOfByte = this.m_USBPort.read();
/* 299 */     return convertBytesToInts(arrayOfByte);
/*     */   }
/*     */ 
/*     */   private byte[] convertIntsToBytes(int[] paramArrayOfInt)
/*     */   {
/* 310 */     byte[] arrayOfByte = new byte[paramArrayOfInt.length];
/* 311 */     for (int i = 0; i < paramArrayOfInt.length; i++) {
/* 312 */       Contract.pre(paramArrayOfInt[i], 0, 255);
/* 313 */       arrayOfByte[i] = (byte)MedicalDevice.Util.getLowByte(paramArrayOfInt[i]);
/*     */     }
/* 315 */     return arrayOfByte;
/*     */   }
/*     */ 
/*     */   private int[] convertBytesToInts(byte[] paramArrayOfByte)
/*     */   {
/* 325 */     int[] arrayOfInt = new int[paramArrayOfByte.length];
/*     */ 
/* 327 */     for (int i = 0; i < paramArrayOfByte.length; i++) {
/* 328 */       paramArrayOfByte[i] &= 255;
/*     */     }
/* 330 */     return arrayOfInt;
/*     */   }
/*     */ 
/*     */   private void resetClock()
/*     */   {
/* 337 */     this.m_startTimeMS = System.currentTimeMillis();
/*     */   }
/*     */ 
/*     */   private long getClockMS()
/*     */   {
/* 346 */     return System.currentTimeMillis() - this.m_startTimeMS;
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.deviceportreader.USBComm
 * JD-Core Version:    0.6.0
 */