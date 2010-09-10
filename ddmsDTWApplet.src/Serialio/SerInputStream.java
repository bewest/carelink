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
/* 130 */     long l = 0L;
/*     */ 
/* 132 */     if (this.tmo > 0) l = System.currentTimeMillis() + this.tmo;
/*     */ 
/* 134 */     this.abort = false;
/*     */     int i;
/* 135 */     while (!this.abort) {
/* 136 */       i = this.sp.rxReadyCount();
/* 137 */       if ((i >= this.threshold) && (
/* 138 */         (!this.avoidNativeBlock) || 
/* 139 */         (i >= paramInt2)))
/*     */       {
/*     */         break;
/*     */       }
/*     */ 
/* 144 */       if ((this.tmo > 0) && 
/* 145 */         (System.currentTimeMillis() > l)) break;
/*     */       try {
/* 147 */         Thread.sleep(this.napTime);
/*     */       } catch (InterruptedException localInterruptedException) {
/* 149 */         if (this.abort) return -2;
/* 150 */         return -1;
/*     */       }
/*     */     }
/* 153 */     if (paramInt1 != 0) {
/* 154 */       if (paramInt1 + paramInt2 > paramArrayOfByte.length) {
/* 155 */         throw new IOException("buf[] will not accomidate the off/len request");
/*     */       }
/* 157 */       byte[] arrayOfByte = new byte[paramInt2];
/* 158 */       i = this.sp.getData(arrayOfByte, 0, paramInt2);
/* 159 */       if (i > 0) System.arraycopy(arrayOfByte, 0, paramArrayOfByte, paramInt1, i); 
/*     */     }
/*     */     else
/*     */     {
/* 162 */       i = this.sp.getData(paramArrayOfByte, 0, paramInt2);
/*     */     }
/* 164 */     if (this.abort) return -2;
/* 165 */     return i;
/*     */   }
/*     */ 
/*     */   public int available()
/*     */     throws IOException
/*     */   {
/* 175 */     return this.sp.rxReadyCount();
/*     */   }
/*     */ 
/*     */   public void close()
/*     */     throws IOException
/*     */   {
/* 184 */     this.sp.close();
/*     */   }
/*     */ 
/*     */   public void abort()
/*     */   {
/* 192 */     this.abort = true;
/*     */   }
/*     */ 
/*     */   public void setRcvThreshold(int paramInt)
/*     */   {
/* 199 */     int i = this.sp.getConfig().getRxLen();
/* 200 */     if (paramInt > i) paramInt = i;
/* 201 */     if (paramInt < 1) paramInt = 1;
/*     */ 
/* 203 */     this.threshold = paramInt;
/*     */   }
/*     */ 
/*     */   public int getRcvThreshold() {
/* 207 */     return this.threshold;
/*     */   }
/*     */ 
/*     */   public void setRcvTimeout(int paramInt)
/*     */   {
/* 214 */     this.tmo = paramInt;
/*     */   }
/*     */ 
/*     */   public int getRcvTimeout() {
/* 218 */     return this.tmo;
/*     */   }
/*     */ 
/*     */   public void setRcvFrameChar(int paramInt)
/*     */   {
/* 225 */     this.frameData = paramInt;
/*     */   }
/*     */ 
/*     */   public int getRcvFrameChar() {
/* 229 */     return this.frameData;
/*     */   }
/*     */ 
/*     */   public void setNapTime(int paramInt)
/*     */   {
/* 235 */     this.napTime = paramInt; } 
/* 236 */   public int getNapTime() { return this.napTime;
/*     */   }
/*     */ 
/*     */   public void setNativeBlock(boolean paramBoolean)
/*     */   {
/* 242 */     this.avoidNativeBlock = (!paramBoolean); } 
/* 243 */   public boolean getNativeBlock() { return !this.avoidNativeBlock;
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     Serialio.SerInputStream
 * JD-Core Version:    0.6.0
 */