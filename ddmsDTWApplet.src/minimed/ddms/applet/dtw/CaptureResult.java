/*     */ package minimed.ddms.applet.dtw;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import minimed.ddms.deviceportreader.BadDeviceCommException;
/*     */ import minimed.ddms.deviceportreader.BadDeviceValueException;
/*     */ import minimed.ddms.deviceportreader.ConnectToPumpException;
/*     */ import minimed.ddms.deviceportreader.DevicePortReader;
/*     */ import minimed.ddms.deviceportreader.SerialIOHaltedException;
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ public final class CaptureResult
/*     */ {
/*     */   private String m_commPortUsed;
/*     */   private DevicePortReader m_devicePortReader;
/*     */   private boolean m_deviceInitialized;
/*     */   private Long m_serverTimeAtDeviceRead;
/*     */   private Long m_clientTimeAtDeviceRead;
/*     */   private byte[] m_snapshotData;
/*     */   private boolean m_wasCancelled;
/*     */   private Exception m_deviceException;
/*     */   private int m_percentComplete;
/*     */   private Integer m_acquireDataStartPercentComplete;
/*     */   private Integer m_phase;
/*     */   private Integer m_state;
/*     */   private Integer m_previousPhase;
/*     */   private int m_retryCount;
/*     */   private Long m_startTimeMS;
/*     */ 
/*     */   public CaptureResult()
/*     */   {
/* 121 */     clear();
/*     */   }
/*     */ 
/*     */   public synchronized String toString()
/*     */   {
/* 132 */     PropertyWriter localPropertyWriter = new PropertyWriter();
/* 133 */     localPropertyWriter.add("commPortUsed", this.m_commPortUsed);
/* 134 */     localPropertyWriter.add("devicePortReader", this.m_devicePortReader);
/* 135 */     localPropertyWriter.add("deviceInitialized", this.m_deviceInitialized);
/* 136 */     localPropertyWriter.add("serverTimeAtDeviceRead", this.m_serverTimeAtDeviceRead);
/* 137 */     localPropertyWriter.add("clientTimeAtDeviceRead", this.m_clientTimeAtDeviceRead);
/* 138 */     localPropertyWriter.add("snapshotData", this.m_snapshotData != null ? this.m_snapshotData.length : 0L);
/* 139 */     localPropertyWriter.add("wasCancelled", this.m_wasCancelled);
/* 140 */     localPropertyWriter.add("deviceException", this.m_deviceException);
/* 141 */     localPropertyWriter.add("percentComplete", this.m_percentComplete);
/* 142 */     localPropertyWriter.add("acquireDataStartPercentComplete", this.m_acquireDataStartPercentComplete);
/* 143 */     localPropertyWriter.add("phase", this.m_phase);
/* 144 */     localPropertyWriter.add("state", this.m_state);
/* 145 */     localPropertyWriter.add("previousPhase", this.m_previousPhase);
/* 146 */     localPropertyWriter.add("retryCount", this.m_retryCount);
/* 147 */     localPropertyWriter.add("startTimeMS", this.m_startTimeMS);
/* 148 */     return localPropertyWriter.toString();
/*     */   }
/*     */ 
/*     */   public boolean equals(Object paramObject)
/*     */   {
/* 159 */     Contract.unreachable();
/* 160 */     return false;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 170 */     Contract.unreachable();
/* 171 */     return 0;
/*     */   }
/*     */ 
/*     */   public synchronized void clear()
/*     */   {
/* 182 */     this.m_commPortUsed = null;
/* 183 */     this.m_devicePortReader = null;
/* 184 */     this.m_deviceInitialized = false;
/* 185 */     this.m_serverTimeAtDeviceRead = null;
/* 186 */     this.m_clientTimeAtDeviceRead = null;
/* 187 */     this.m_snapshotData = null;
/* 188 */     this.m_wasCancelled = false;
/* 189 */     this.m_deviceException = null;
/* 190 */     this.m_percentComplete = 0;
/* 191 */     this.m_acquireDataStartPercentComplete = null;
/* 192 */     this.m_previousPhase = null;
/* 193 */     this.m_retryCount = 0;
/* 194 */     this.m_startTimeMS = null;
/* 195 */     setPhase(0);
/* 196 */     setState(1);
/*     */   }
/*     */ 
/*     */   public synchronized void setCommPortUsed(String paramString)
/*     */   {
/* 207 */     Contract.pre(this.m_commPortUsed == null);
/* 208 */     this.m_commPortUsed = paramString;
/*     */   }
/*     */ 
/*     */   public synchronized String getCommPortUsed()
/*     */   {
/* 218 */     return this.m_commPortUsed;
/*     */   }
/*     */ 
/*     */   public synchronized void setSnapshotData(byte[] paramArrayOfByte)
/*     */   {
/* 228 */     Contract.pre(this.m_snapshotData == null);
/* 229 */     this.m_snapshotData = paramArrayOfByte;
/*     */   }
/*     */ 
/*     */   public synchronized byte[] getSnapshotData()
/*     */   {
/* 238 */     return this.m_snapshotData;
/*     */   }
/*     */ 
/*     */   public synchronized void setClientTimeAtDeviceRead(long paramLong)
/*     */   {
/* 248 */     Contract.pre(this.m_clientTimeAtDeviceRead == null);
/* 249 */     this.m_clientTimeAtDeviceRead = new Long(paramLong);
/*     */   }
/*     */ 
/*     */   public synchronized long getClientTimeAtDeviceRead()
/*     */   {
/* 259 */     Long localLong = this.m_clientTimeAtDeviceRead;
/* 260 */     Contract.preNonNull(localLong);
/* 261 */     return localLong.longValue();
/*     */   }
/*     */ 
/*     */   public synchronized void setServerTimeAtDeviceRead(long paramLong)
/*     */   {
/* 271 */     Contract.pre(this.m_serverTimeAtDeviceRead == null);
/* 272 */     this.m_serverTimeAtDeviceRead = new Long(paramLong);
/*     */   }
/*     */ 
/*     */   public synchronized long getServerTimeAtDeviceRead()
/*     */   {
/* 282 */     Long localLong = this.m_serverTimeAtDeviceRead;
/* 283 */     Contract.preNonNull(localLong);
/* 284 */     return localLong.longValue();
/*     */   }
/*     */ 
/*     */   public synchronized void setDevicePortReader(DevicePortReader paramDevicePortReader)
/*     */   {
/* 295 */     Contract.preNonNull(paramDevicePortReader);
/* 296 */     Contract.pre(this.m_devicePortReader == null);
/* 297 */     this.m_devicePortReader = paramDevicePortReader;
/*     */   }
/*     */ 
/*     */   public synchronized DevicePortReader getDevicePortReader()
/*     */   {
/* 306 */     return this.m_devicePortReader;
/*     */   }
/*     */ 
/*     */   public synchronized void setDeviceInitialized()
/*     */   {
/* 313 */     this.m_deviceInitialized = true;
/*     */   }
/*     */ 
/*     */   public synchronized boolean isDeviceInitialized()
/*     */   {
/* 322 */     return this.m_deviceInitialized;
/*     */   }
/*     */ 
/*     */   public synchronized void setCancelled()
/*     */   {
/* 329 */     this.m_wasCancelled = true;
/*     */   }
/*     */ 
/*     */   public synchronized boolean wasCancelled()
/*     */   {
/* 338 */     return this.m_wasCancelled;
/*     */   }
/*     */ 
/*     */   public synchronized void incrementRetryCount()
/*     */   {
/* 345 */     this.m_retryCount += 1;
/*     */   }
/*     */ 
/*     */   public synchronized int getRetryCount()
/*     */   {
/* 354 */     return this.m_retryCount;
/*     */   }
/*     */ 
/*     */   public synchronized void setDeviceException(BadDeviceValueException paramBadDeviceValueException)
/*     */   {
/* 365 */     setDeviceException(paramBadDeviceValueException);
/*     */   }
/*     */ 
/*     */   public synchronized void setDeviceException(BadDeviceCommException paramBadDeviceCommException)
/*     */   {
/* 376 */     setDeviceException(paramBadDeviceCommException);
/*     */   }
/*     */ 
/*     */   public synchronized void setDeviceException(SerialIOHaltedException paramSerialIOHaltedException)
/*     */   {
/* 387 */     setDeviceException(paramSerialIOHaltedException);
/*     */   }
/*     */ 
/*     */   public synchronized void setDeviceException(IOException paramIOException)
/*     */   {
/* 398 */     setDeviceException(paramIOException);
/*     */   }
/*     */ 
/*     */   public synchronized void setDeviceException(ConnectToPumpException paramConnectToPumpException)
/*     */   {
/* 409 */     setDeviceException(paramConnectToPumpException);
/*     */   }
/*     */ 
/*     */   public synchronized Exception getDeviceException()
/*     */   {
/* 418 */     return this.m_deviceException;
/*     */   }
/*     */ 
/*     */   public synchronized void setPercentComplete(int paramInt)
/*     */   {
/* 427 */     this.m_percentComplete = paramInt;
/*     */   }
/*     */ 
/*     */   public synchronized int getPercentComplete()
/*     */   {
/* 436 */     return this.m_percentComplete;
/*     */   }
/*     */ 
/*     */   public synchronized boolean wasAnyDeviceDataRead()
/*     */   {
/* 446 */     int i = 0;
/* 447 */     if (this.m_acquireDataStartPercentComplete != null) {
/* 448 */       i = getPercentComplete() > this.m_acquireDataStartPercentComplete.intValue() + 1 ? 1 : 0;
/*     */     }
/* 450 */     return i;
/*     */   }
/*     */ 
/*     */   public synchronized void setAcquireDataStartPercentComplete(int paramInt)
/*     */   {
/* 459 */     this.m_acquireDataStartPercentComplete = new Integer(paramInt);
/*     */   }
/*     */ 
/*     */   public synchronized void setStartTimeMS(long paramLong)
/*     */   {
/* 469 */     Contract.pre(this.m_startTimeMS == null);
/* 470 */     this.m_startTimeMS = new Long(paramLong);
/*     */   }
/*     */ 
/*     */   public synchronized long getStartTimeMS()
/*     */   {
/* 480 */     Long localLong = this.m_startTimeMS;
/* 481 */     Contract.preNonNull(localLong);
/* 482 */     return localLong.longValue();
/*     */   }
/*     */ 
/*     */   public synchronized void setState(int paramInt)
/*     */   {
/* 491 */     this.m_state = new Integer(paramInt);
/*     */   }
/*     */ 
/*     */   public synchronized void setPhase(int paramInt)
/*     */   {
/* 501 */     Contract.pre(paramInt, 0, 7);
/* 502 */     this.m_previousPhase = this.m_phase;
/* 503 */     this.m_phase = new Integer(paramInt);
/*     */   }
/*     */ 
/*     */   public synchronized int getPhase()
/*     */   {
/* 513 */     Integer localInteger = this.m_phase;
/* 514 */     Contract.preNonNull(localInteger);
/* 515 */     return localInteger.intValue();
/*     */   }
/*     */ 
/*     */   public synchronized int getLastOperationalPhase()
/*     */   {
/* 525 */     Contract.preNonNull(this.m_phase);
/* 526 */     return getPhase() != 6 ? getPhase() : this.m_previousPhase.intValue();
/*     */   }
/*     */ 
/*     */   private void setDeviceException(Exception paramException)
/*     */   {
/* 538 */     Contract.preNonNull(paramException);
/* 539 */     Contract.pre(this.m_deviceException == null);
/* 540 */     this.m_deviceException = paramException;
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.CaptureResult
 * JD-Core Version:    0.6.0
 */