/*     */ package minimed.ddms.deviceportreader;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ abstract class CommunicationsLinkDefault
/*     */   implements CommunicationsLink
/*     */ {
/*     */   static final String DISCONNNECTED = "DISCONNNECTED";
/*     */   private static final int COMM_RETRIES = 4;
/*     */   private DeviceListener m_deviceListener;
/*     */   private final String m_deviceSerialNumber;
/*     */   private int m_state;
/*     */   private int m_totalReadByteCountExpected;
/*     */   private int m_totalReadByteCount;
/*     */   private int m_phase;
/*     */   private String m_linkDeviceDescription;
/*     */   private CommPort m_commPort;
/*     */ 
/*     */   CommunicationsLinkDefault(DeviceListener paramDeviceListener, String paramString1, String paramString2)
/*     */   {
/*  65 */     this.m_deviceListener = paramDeviceListener;
/*  66 */     this.m_deviceSerialNumber = paramString1;
/*  67 */     this.m_linkDeviceDescription = paramString2;
/*  68 */     setState(DevicePortReader.STATE_IDLE);
/*  69 */     MedicalDevice.logInfo(this, "Created link device " + toString());
/*     */   }
/*     */ 
/*     */   public CommPort getCommPort()
/*     */   {
/*  80 */     return this.m_commPort;
/*     */   }
/*     */ 
/*     */   public void setCommPort(CommPort paramCommPort)
/*     */   {
/*  89 */     this.m_commPort = paramCommPort;
/*     */   }
/*     */ 
/*     */   public final void initCommunications()
/*     */     throws BadDeviceCommException, IOException, SerialIOHaltedException
/*     */   {
/* 101 */     setState(DevicePortReader.STATE_INITIALIZING);
/* 102 */     int initialized = 0;
/*     */ 
/* 104 */     for (int numRetries = 0; (numRetries <= COMM_RETRIES) && (initialized == 0); numRetries++) {
/*     */       try {
/* 106 */         initCommunicationsIO();
/* 107 */         initialized = 1;
/*     */       } catch (IOException localIOException) {
/* 109 */         MedicalDevice.logWarning(this, "initCommunications: got error " + localIOException);
/* 110 */         if (numRetries == COMM_RETRIES)
/*     */         {
/* 112 */           throw localIOException;
/*     */         }
/* 114 */         MedicalDevice.logWarning(this, "initCommunications: retrying...");
/*     */       }
/*     */     }
/* 117 */     setState(DevicePortReader.STATE_IDLE);
/*     */   }
/*     */ 
/*     */   public final void endCommunications()
/*     */     throws IOException
/*     */   {
/* 126 */     endCommunicationsIO();
/* 127 */     setState(DevicePortReader.STATE_IDLE);
/*     */   }
/*     */ 
/*     */   public final void resetTotalReadByteCount()
/*     */   {
/* 134 */     this.m_totalReadByteCount = 0;
/*     */   }
/*     */ 
/*     */   public final void setTotalReadByteCountExpected(int paramInt)
/*     */   {
/* 143 */     this.m_totalReadByteCountExpected = paramInt;
/*     */   }
/*     */ 
/*     */   public final void incTotalReadByteCount(int paramInt)
/*     */   {
/* 152 */     this.m_totalReadByteCount += paramInt;
/*     */   }
/*     */ 
/*     */   public final void setListener(DeviceListener paramDeviceListener)
/*     */   {
/* 161 */     this.m_deviceListener = paramDeviceListener;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 170 */     return this.m_linkDeviceDescription + " using " + getPortDescription();
/*     */   }
/*     */ 
/*     */   final String getDeviceSerialNumber()
/*     */   {
/* 179 */     return this.m_deviceSerialNumber;
/*     */   }
/*     */ 
/*     */   abstract void initCommunicationsIO()
/*     */     throws IOException, SerialIOHaltedException, BadDeviceCommException;
/*     */ 
/*     */   abstract void endCommunicationsIO()
/*     */     throws IOException;
/*     */ 
/*     */   String getPortDescription()
/*     */   {
/* 205 */     return getCommPort() != null ? getCommPort().toString() : DISCONNNECTED;
/*     */   }
/*     */ 
/*     */   void notifyDeviceUpdateProgress()
/*     */   {
/* 213 */     double d = this.m_totalReadByteCount / this.m_totalReadByteCountExpected * 100.0D;
/*     */ 
/* 215 */     this.m_deviceListener.deviceUpdateProgress((int)d);
/*     */   }
/*     */ 
/*     */   void setState(int paramInt)
/*     */   {
/* 226 */     Contract.pre((paramInt >= DevicePortReader.STATE_IDLE) && (paramInt <= DevicePortReader.STATE_LAST));
/*     */ 
/* 229 */     if (paramInt != this.m_state) {
/* 230 */       this.m_state = paramInt;
/* 231 */       this.m_deviceListener.deviceUpdateState(this.m_state, DevicePortReader.STATE_TEXT[this.m_state]);
/*     */     }
/*     */   }
/*     */ 
/*     */   void setPhase(int paramInt)
/*     */   {
/* 243 */     Contract.pre((paramInt >= DevicePortReader.PHASE_AUTO_DETECT_PORT) && (paramInt <= DevicePortReader.PHASE_AUTO_DETECT_DEVICE));
/*     */ 
/* 247 */     if (paramInt != this.m_phase) {
/* 248 */       this.m_phase = paramInt;
/* 249 */       this.m_deviceListener.deviceUpdateState(this.m_phase, DevicePortReader.PHASE_TEXT[this.m_phase]);
/*     */     }
/*     */   }
/*     */ 
/*     */   DeviceListener getDeviceListener()
/*     */   {
/* 259 */     return this.m_deviceListener;
/*     */   }
/*     */ 
/*     */   int getState()
/*     */   {
/* 268 */     return this.m_state;
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.deviceportreader.CommunicationsLinkDefault
 * JD-Core Version:    0.6.0
 */