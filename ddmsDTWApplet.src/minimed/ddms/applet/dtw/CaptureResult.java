/*     */ package minimed.ddms.applet.dtw;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Date;
/*     */ import minimed.ddms.A.G;
/*     */ import minimed.ddms.A.P;
/*     */ import minimed.ddms.A.W;
/*     */ import minimed.ddms.A.Z;
/*     */ import minimed.ddms.A.t;
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ public final class CaptureResult
/*     */ {
/*     */   private String m_commPortUsed;
/*     */   private G m_devicePortReader;
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
/*     */   private int m_readTimeSeconds;
/*     */ 
/*     */   public CaptureResult()
/*     */   {
/* 127 */     clear();
/*     */   }
/*     */ 
/*     */   public synchronized String toString()
/*     */   {
/* 138 */     PropertyWriter localPropertyWriter = new PropertyWriter();
/* 139 */     localPropertyWriter.add("commPortUsed", this.m_commPortUsed);
/* 140 */     localPropertyWriter.add("devicePortReader", this.m_devicePortReader);
/* 141 */     localPropertyWriter.add("deviceInitialized", this.m_deviceInitialized);
/* 142 */     localPropertyWriter.add("serverTimeAtDeviceRead", this.m_serverTimeAtDeviceRead);
/* 143 */     localPropertyWriter.add("clientTimeAtDeviceRead", this.m_clientTimeAtDeviceRead);
/* 144 */     localPropertyWriter.add("snapshotData", this.m_snapshotData != null ? this.m_snapshotData.length : 0L);
/* 145 */     localPropertyWriter.add("wasCancelled", this.m_wasCancelled);
/* 146 */     localPropertyWriter.add("deviceException", this.m_deviceException);
/* 147 */     localPropertyWriter.add("percentComplete", this.m_percentComplete);
/* 148 */     localPropertyWriter.add("acquireDataStartPercentComplete", this.m_acquireDataStartPercentComplete);
/* 149 */     localPropertyWriter.add("phase", this.m_phase);
/* 150 */     localPropertyWriter.add("state", this.m_state);
/* 151 */     localPropertyWriter.add("previousPhase", this.m_previousPhase);
/* 152 */     localPropertyWriter.add("retryCount", this.m_retryCount);
/* 153 */     localPropertyWriter.add("startTimeMS", this.m_startTimeMS);
/* 154 */     localPropertyWriter.add("readTimeSec", this.m_readTimeSeconds);
/* 155 */     return localPropertyWriter.toString();
/*     */   }
/*     */ 
/*     */   public boolean equals(Object paramObject)
/*     */   {
/* 166 */     Contract.unreachable();
/* 167 */     return false;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 177 */     Contract.unreachable();
/* 178 */     return 0;
/*     */   }
/*     */ 
/*     */   public synchronized void clear()
/*     */   {
/* 188 */     this.m_commPortUsed = null;
/* 189 */     this.m_devicePortReader = null;
/* 190 */     this.m_deviceInitialized = false;
/* 191 */     this.m_serverTimeAtDeviceRead = null;
/* 192 */     this.m_clientTimeAtDeviceRead = null;
/* 193 */     this.m_snapshotData = null;
/* 194 */     this.m_wasCancelled = false;
/* 195 */     this.m_deviceException = null;
/* 196 */     this.m_percentComplete = 0;
/* 197 */     this.m_acquireDataStartPercentComplete = null;
/* 198 */     this.m_previousPhase = null;
/* 199 */     this.m_retryCount = 0;
/* 200 */     this.m_startTimeMS = null;
/* 201 */     this.m_readTimeSeconds = 0;
/* 202 */     setPhase(0);
/* 203 */     setState(1);
/*     */   }
/*     */ 
/*     */   public synchronized void setCommPortUsed(String paramString)
/*     */   {
/* 214 */     Contract.pre(this.m_commPortUsed == null);
/* 215 */     this.m_commPortUsed = paramString;
/*     */   }
/*     */ 
/*     */   public synchronized String getCommPortUsed()
/*     */   {
/* 225 */     return this.m_commPortUsed;
/*     */   }
/*     */ 
/*     */   public int getReadTimeSeconds()
/*     */   {
/* 234 */     return this.m_readTimeSeconds;
/*     */   }
/*     */ 
/*     */   public void setReadTimeSeconds(int paramInt)
/*     */   {
/* 243 */     this.m_readTimeSeconds = paramInt;
/*     */   }
/*     */ 
/*     */   public synchronized void setSnapshotData(byte[] paramArrayOfByte)
/*     */   {
/* 253 */     Contract.pre(this.m_snapshotData == null);
/* 254 */     this.m_snapshotData = paramArrayOfByte;
/*     */   }
/*     */ 
/*     */   public synchronized byte[] getSnapshotData()
/*     */   {
/* 263 */     return this.m_snapshotData;
/*     */   }
/*     */ 
/*     */   public synchronized void setClientTimeAtDeviceRead(long paramLong)
/*     */   {
/* 273 */     Contract.pre(this.m_clientTimeAtDeviceRead == null);
/* 274 */     this.m_clientTimeAtDeviceRead = new Long(paramLong);
/*     */   }
/*     */ 
/*     */   public synchronized long getClientTimeAtDeviceRead()
/*     */   {
/* 284 */     Long localLong = this.m_clientTimeAtDeviceRead;
/* 285 */     Contract.preNonNull(localLong);
/* 286 */     return localLong.longValue();
/*     */   }
/*     */ 
/*     */   public synchronized void setServerTimeAtDeviceRead(long paramLong)
/*     */   {
/* 296 */     Contract.pre(this.m_serverTimeAtDeviceRead == null);
/* 297 */     this.m_serverTimeAtDeviceRead = new Long(paramLong);
/*     */   }
/*     */ 
/*     */   public synchronized long getServerTimeAtDeviceRead()
/*     */   {
/* 307 */     Long localLong = this.m_serverTimeAtDeviceRead;
/* 308 */     Contract.preNonNull(localLong);
/* 309 */     return localLong.longValue();
/*     */   }
/*     */ 
/*     */   public synchronized void setDevicePortReader(G paramG)
/*     */   {
/* 320 */     Contract.preNonNull(paramG);
/* 321 */     Contract.pre(this.m_devicePortReader == null);
/* 322 */     this.m_devicePortReader = paramG;
/*     */   }
/*     */ 
/*     */   public synchronized G getDevicePortReader()
/*     */   {
/* 331 */     return this.m_devicePortReader;
/*     */   }
/*     */ 
/*     */   public synchronized void setDeviceInitialized()
/*     */   {
/* 338 */     this.m_deviceInitialized = true;
/*     */   }
/*     */ 
/*     */   public synchronized boolean isDeviceInitialized()
/*     */   {
/* 347 */     return this.m_deviceInitialized;
/*     */   }
/*     */ 
/*     */   public synchronized void setCancelled()
/*     */   {
/* 354 */     this.m_wasCancelled = true;
/*     */   }
/*     */ 
/*     */   public synchronized boolean wasCancelled()
/*     */   {
/* 363 */     return this.m_wasCancelled;
/*     */   }
/*     */ 
/*     */   public synchronized void incrementRetryCount()
/*     */   {
/* 370 */     this.m_retryCount += 1;
/*     */   }
/*     */ 
/*     */   public synchronized int getRetryCount()
/*     */   {
/* 379 */     return this.m_retryCount;
/*     */   }
/*     */ 
/*     */   public synchronized void setDeviceException(Z paramZ)
/*     */   {
/* 390 */     setDeviceException(paramZ);
/*     */   }
/*     */ 
/*     */   public synchronized void setDeviceException(t paramt)
/*     */   {
/* 401 */     setDeviceException(paramt);
/*     */   }
/*     */ 
/*     */   public synchronized void setDeviceException(W paramW)
/*     */   {
/* 412 */     setDeviceException(paramW);
/*     */   }
/*     */ 
/*     */   public synchronized void setDeviceException(IOException paramIOException)
/*     */   {
/* 423 */     setDeviceException(paramIOException);
/*     */   }
/*     */ 
/*     */   public synchronized void setDeviceException(P paramP)
/*     */   {
/* 434 */     setDeviceException(paramP);
/*     */   }
/*     */ 
/*     */   public synchronized Exception getDeviceException()
/*     */   {
/* 443 */     return this.m_deviceException;
/*     */   }
/*     */ 
/*     */   public synchronized void setPercentComplete(int paramInt)
/*     */   {
/* 452 */     this.m_percentComplete = paramInt;
/*     */   }
/*     */ 
/*     */   public synchronized int getPercentComplete()
/*     */   {
/* 461 */     return this.m_percentComplete;
/*     */   }
/*     */ 
/*     */   public synchronized boolean wasAnyDeviceDataRead()
/*     */   {
/* 471 */     int i = 0;
/* 472 */     if (this.m_acquireDataStartPercentComplete != null) {
/* 473 */       i = getPercentComplete() > this.m_acquireDataStartPercentComplete.intValue() + 1 ? 1 : 0;
/*     */     }
/* 475 */     return i;
/*     */   }
/*     */ 
/*     */   public synchronized void setAcquireDataStartPercentComplete(int paramInt)
/*     */   {
/* 484 */     this.m_acquireDataStartPercentComplete = new Integer(paramInt);
/*     */   }
/*     */ 
/*     */   public synchronized void setStartTimeMS(long paramLong)
/*     */   {
/* 494 */     Contract.pre(this.m_startTimeMS == null);
/* 495 */     this.m_startTimeMS = new Long(paramLong);
/*     */   }
/*     */ 
/*     */   public synchronized long getStartTimeMS()
/*     */   {
/* 505 */     Long localLong = this.m_startTimeMS;
/* 506 */     Contract.preNonNull(localLong);
/* 507 */     return localLong.longValue();
/*     */   }
/*     */ 
/*     */   public synchronized void setState(int paramInt)
/*     */   {
/* 516 */     this.m_state = new Integer(paramInt);
/*     */   }
/*     */ 
/*     */   public synchronized void setPhase(int paramInt)
/*     */   {
/* 526 */     Contract.pre(paramInt, 0, 7);
/* 527 */     this.m_previousPhase = this.m_phase;
/* 528 */     this.m_phase = new Integer(paramInt);
/*     */   }
/*     */ 
/*     */   public synchronized int getPhase()
/*     */   {
/* 538 */     Integer localInteger = this.m_phase;
/* 539 */     Contract.preNonNull(localInteger);
/* 540 */     return localInteger.intValue();
/*     */   }
/*     */ 
/*     */   public synchronized int getLastOperationalPhase()
/*     */   {
/* 550 */     Contract.preNonNull(this.m_phase);
/* 551 */     return getPhase() != 6 ? getPhase() : this.m_previousPhase.intValue();
/*     */   }
/*     */ 
/*     */   public int getMaxRetryCount()
/*     */   {
/* 561 */     G localG = getDevicePortReader();
/* 562 */     return localG == null ? 2 : localG.B();
/*     */   }
/*     */ 
/*     */   public Date getClock()
/*     */   {
/* 571 */     G localG = getDevicePortReader();
/* 572 */     return localG == null ? new Date(System.currentTimeMillis()) : localG.N();
/*     */   }
/*     */ 
/*     */   public String getSerialNumber()
/*     */   {
/* 580 */     G localG = getDevicePortReader();
/* 581 */     return localG == null ? String.valueOf(System.currentTimeMillis()).substring(2, 8) : localG.Q();
/*     */   }
/*     */ 
/*     */   private void setDeviceException(Exception paramException)
/*     */   {
/* 592 */     Contract.preNonNull(paramException);
/* 593 */     this.m_deviceException = paramException;
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.CaptureResult
 * JD-Core Version:    0.6.0
 */