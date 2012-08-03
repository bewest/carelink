/*     */ package Serialio;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.io.PrintStream;
/*     */ 
/*     */ public class SerOutputStream extends OutputStream
/*     */ {
/*     */   protected SerialPort sp;
/*  37 */   private boolean abort = false;
/*  38 */   private boolean doDrain = true;
/*  39 */   private boolean writeDrain = true;
/*  40 */   private int wrTimeout = -1;
/*  41 */   private int txBlkSize = 1024;
/*     */ 
/*     */   public SerOutputStream(SerialPort paramSerialPort)
/*     */     throws IOException
/*     */   {
/*  50 */     if (paramSerialPort.getPortNum() < 0)
/*  51 */       throw new IOException("Port not open");
/*  52 */     this.sp = paramSerialPort;
/*  53 */     paramSerialPort.setTimeoutTx(2147483647);
/*  54 */     String str = new String(System.getProperty("os.name"));
/*     */ 
/*  58 */     if ((str.equals("Windows NT")) || (str.equals("Windows 2000")) || (str.equals("Windows XP")) || (str.equals("Windows 7")) || (str.equals("Windows Vista")))
/*     */     {
/*  63 */       this.doDrain = false;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void write(int paramInt)
/*     */     throws IOException
/*     */   {
/*  73 */     this.sp.putByte((byte)paramInt);
/*     */   }
/*     */ 
/*     */   public void write(byte[] paramArrayOfByte)
/*     */     throws IOException
/*     */   {
/*  86 */     write(paramArrayOfByte, 0, paramArrayOfByte.length);
/*     */   }
/*     */ 
/*     */   public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/*     */     throws IOException
/*     */   {
/*  99 */     if (!this.writeDrain) {
/* 100 */       arrayOfByte1 = new byte[paramInt2];
/* 101 */       System.arraycopy(paramArrayOfByte, paramInt1, arrayOfByte1, 0, paramInt2);
/* 102 */       this.sp.putData(arrayOfByte1);
/* 103 */       return;
/*     */     }
/*     */ 
/* 106 */     byte[] arrayOfByte1 = new byte[this.txBlkSize];
/*     */ 
/* 108 */     int i = paramInt2 / this.txBlkSize;
/* 109 */     int j = paramInt2 % this.txBlkSize;
/*     */ 
/* 111 */     for (int m = 0; m < i; m++) {
/* 112 */       k = paramInt1 + this.txBlkSize * m;
/* 113 */       System.arraycopy(paramArrayOfByte, k, arrayOfByte1, 0, this.txBlkSize);
/* 114 */       this.sp.putData(arrayOfByte1);
/* 115 */       flush();
/*     */     }
/* 117 */     int k = paramInt2 - j;
/* 118 */     if (j > 0)
/*     */     {
/* 120 */       if (i == 0) k += paramInt1;
/* 121 */       byte[] arrayOfByte2 = new byte[j];
/* 122 */       System.arraycopy(paramArrayOfByte, k, arrayOfByte2, 0, j);
/* 123 */       this.sp.putData(arrayOfByte2);
/* 124 */       flush();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void flush()
/*     */     throws IOException
/*     */   {
/* 143 */     if (this.doDrain) {
/* 144 */       if (this.sp.txDrain() == -1)
/* 145 */         throw new IOException("txDrain failed, check transmit timeout");
/* 146 */       return;
/*     */     }
/*     */ 
/* 149 */     long l3 = 0L;
/* 150 */     if (this.wrTimeout > 0) {
/* 151 */       long l2 = System.currentTimeMillis();
/* 152 */       l3 = l2 + this.wrTimeout;
/*     */     }
/*     */ 
/* 158 */     while (this.sp.txBufCount() > 0) {
/*     */       try { Thread.sleep(20L); } catch (InterruptedException localInterruptedException) {
/* 160 */       }if (l3 > 0L) {
/* 161 */         long l1 = System.currentTimeMillis();
/* 162 */         if (l1 >= l3)
/* 163 */           throw new ExTimeout("Flush timeout after " + this.wrTimeout + " ms");
/*     */       }
/* 165 */       if (this.abort) {
/* 166 */         this.abort = false;
/* 167 */         System.out.println(getClass().toString() + ": flush aborted");
/* 168 */         return;
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setWriteDrain(boolean paramBoolean)
/*     */   {
/* 179 */     this.writeDrain = paramBoolean;
/*     */   }
/*     */   public boolean getWriteDrain() {
/* 182 */     return this.writeDrain;
/*     */   }
/*     */ 
/*     */   public void flushAbort()
/*     */   {
/* 189 */     this.abort = true;
/*     */   }
/*     */ 
/*     */   public void close()
/*     */     throws IOException
/*     */   {
/* 196 */     this.abort = true;
/* 197 */     super.close();
/*     */   }
/*     */ 
/*     */   public void setWriteTimeout(int paramInt)
/*     */     throws IOException
/*     */   {
/* 204 */     this.wrTimeout = paramInt;
/* 205 */     if (this.sp.isSupported("setTimeoutTx"))
/* 206 */       this.sp.setTimeoutTx(paramInt); 
/*     */   }
/*     */   public int getWriteTimeout() {
/* 208 */     return this.wrTimeout;
/*     */   }
/*     */ 
/*     */   public void setTxBlockSize(int paramInt)
/*     */   {
/* 215 */     this.txBlkSize = paramInt; } 
/* 216 */   public int getTxBlockSize() { return this.txBlkSize;
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     Serialio.SerOutputStream
 * JD-Core Version:    0.6.0
 */