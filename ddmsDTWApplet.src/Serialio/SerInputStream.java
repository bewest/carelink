/*     */ package Serialio;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ 
/*     */ public class SerInputStream extends InputStream
/*     */ {
/*  33 */   private boolean abort = false;
/*  34 */   private boolean avoidNativeBlock = true;
/*  35 */   private int tmo = 0;
/*  36 */   private int threshold = 1;
/*  37 */   private int frameData = -1;
/*  38 */   private int napTime = 10;
/*     */   protected SerialPort sp;
/*     */ 
/*     */   public SerInputStream(SerialPort paramSerialPort)
/*     */     throws IOException
/*     */   {
/*  48 */     if (paramSerialPort.getPortNum() < 0)
/*  49 */       throw new IOException("Port not open");
/*  50 */     this.sp = paramSerialPort;
/*  51 */     paramSerialPort.setTimeoutRx(0);
/*     */   }
/*     */ 
/*     */   public int read()
/*     */     throws IOException
/*     */   {
/*  65 */     long l = 0L;
/*     */ 
/*  67 */     if (this.tmo > 0) l = System.currentTimeMillis() + this.tmo;
/*     */ 
/*  69 */     this.abort = false;
/*     */     while (true) {
/*  71 */       int i = this.sp.rxReadyCount();
/*  72 */       if (i >= this.threshold) {
/*  73 */         if (this.frameData > -1) { int j;
/*     */           do { if (i-- <= 0) break;
/*  75 */             j = this.sp.getByte(); }
/*  76 */           while (j != this.frameData); return j;
/*     */         }
/*     */ 
/*  80 */         return this.sp.getByte();
/*  81 */       }if ((this.tmo > 0) && 
/*  82 */         (System.currentTimeMillis() > l)) {
/*  83 */         if (i > 0) {
/*  84 */           return this.sp.getByte();
/*     */         }
/*  86 */         if (this.abort) return -2;
/*  87 */         return -1;
/*     */       }
/*     */       try {
/*  90 */         Thread.sleep(this.napTime);
/*     */       } catch (InterruptedException localInterruptedException) {
/*  92 */         if (!this.abort) break; return -2;
/*     */       }
/*  93 */     }return -1;
/*     */   }
/*     */ 
/*     */   public int read(byte[] paramArrayOfByte)
/*     */     throws IOException
/*     */   {
/* 110 */     return read(paramArrayOfByte, 0, paramArrayOfByte.length);
/*     */   }
/*     */ 
/*     */   public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/*     */     throws IOException
/*     */   {
/* 129 */     int i = 1;
/* 130 */     long l = 0L;
/*     */ 
/* 132 */     if ((paramInt1 < 0) || (paramInt2 < 0) || (paramInt1 + paramInt2 > paramArrayOfByte.length)) {
/* 133 */       throw new IOException("buf[] will not accomidate the off/len request");
/*     */     }
/*     */ 
/* 136 */     if (this.tmo > 0) l = System.currentTimeMillis() + this.tmo;
/*     */ 
/* 138 */     this.abort = false;
/* 139 */     while (!this.abort) {
/* 140 */       i = this.sp.rxReadyCount();
/* 141 */       if (i >= this.threshold) {
/* 142 */         if (!this.avoidNativeBlock)
/*     */           break;
/* 144 */         if (i > 0)
/*     */           break;
/*     */       }
/*     */       else
/*     */       {
/* 149 */         if ((this.tmo > 0) && 
/* 150 */           (System.currentTimeMillis() > l)) break;
/*     */         try {
/* 152 */           Thread.sleep(this.napTime);
/*     */         } catch (InterruptedException localInterruptedException) {
/* 154 */           if (this.abort) return -2;
/* 155 */           return -1;
/*     */         }
/*     */       }
/*     */     }
/*     */     int j;
/* 158 */     if (paramInt1 != 0) {
/* 159 */       byte[] arrayOfByte = new byte[paramInt2];
/* 160 */       j = this.sp.getData(arrayOfByte, 0, paramInt2);
/* 161 */       if (j > 0) System.arraycopy(arrayOfByte, 0, paramArrayOfByte, paramInt1, j); 
/*     */     }
/*     */     else
/*     */     {
/* 164 */       j = this.sp.getData(paramArrayOfByte, 0, paramInt2);
/*     */     }
/*     */ 
/* 167 */     if (this.abort) return -2;
/* 168 */     return j;
/*     */   }
/*     */ 
/*     */   public int available()
/*     */     throws IOException
/*     */   {
/* 178 */     return this.sp.rxReadyCount();
/*     */   }
/*     */ 
/*     */   public void close()
/*     */     throws IOException
/*     */   {
/* 187 */     this.sp.close();
/*     */   }
/*     */ 
/*     */   public void abort()
/*     */   {
/* 195 */     this.abort = true;
/*     */   }
/*     */ 
/*     */   public void setRcvThreshold(int paramInt)
/*     */   {
/* 202 */     int i = this.sp.getConfig().getRxLen();
/* 203 */     if (paramInt > i) paramInt = i;
/* 204 */     if (paramInt < 1) paramInt = 1;
/*     */ 
/* 206 */     this.threshold = paramInt;
/*     */   }
/*     */ 
/*     */   public int getRcvThreshold() {
/* 210 */     return this.threshold;
/*     */   }
/*     */ 
/*     */   public void setRcvTimeout(int paramInt)
/*     */   {
/* 217 */     this.tmo = paramInt;
/*     */   }
/*     */ 
/*     */   public int getRcvTimeout() {
/* 221 */     return this.tmo;
/*     */   }
/*     */ 
/*     */   public void setRcvFrameChar(int paramInt)
/*     */   {
/* 228 */     this.frameData = paramInt;
/*     */   }
/*     */ 
/*     */   public int getRcvFrameChar() {
/* 232 */     return this.frameData;
/*     */   }
/*     */ 
/*     */   public void setNapTime(int paramInt)
/*     */   {
/* 238 */     this.napTime = paramInt; } 
/* 239 */   public int getNapTime() { return this.napTime;
/*     */   }
/*     */ 
/*     */   public void setNativeBlock(boolean paramBoolean)
/*     */   {
/* 245 */     this.avoidNativeBlock = (!paramBoolean); } 
/* 246 */   public boolean getNativeBlock() { return !this.avoidNativeBlock;
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     Serialio.SerInputStream
 * JD-Core Version:    0.6.0
 */