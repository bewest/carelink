/*     */ package Serialio;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.io.PrintStream;
/*     */ 
/*     */ public class SerOutputStream extends OutputStream
/*     */ {
/*     */   protected SerialPort sp;
/*  38 */   private boolean abort = false;
/*  39 */   private boolean doDrain = true;
/*  40 */   private boolean writeDrain = true;
/*  41 */   private int wrTimeout = -1;
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
/* 100 */       byte[] arrayOfByte1 = new byte[paramInt2];
/* 101 */       System.arraycopy(paramArrayOfByte, paramInt1, arrayOfByte1, 0, paramInt2);
/* 102 */       this.sp.putData(arrayOfByte1);
/* 103 */       return;
/*     */     }
/*     */ 
/* 106 */     int i = 4000;
/* 107 */     byte[] arrayOfByte2 = new byte[i];
/*     */ 
/* 109 */     int j = paramInt2 / i;
/* 110 */     int k = paramInt2 % i;
/*     */ 
/* 112 */     for (int n = 0; n < j; n++) {
/* 113 */       m = paramInt1 + i * n;
/* 114 */       System.arraycopy(paramArrayOfByte, m, arrayOfByte2, 0, i);
/* 115 */       this.sp.putData(arrayOfByte2);
/* 116 */       flush();
/*     */     }
/* 118 */     int m = paramInt2 - k;
/* 119 */     if (k > 0) {
/* 120 */       byte[] arrayOfByte3 = new byte[k];
/* 121 */       System.arraycopy(paramArrayOfByte, m, arrayOfByte3, 0, k);
/* 122 */       this.sp.putData(arrayOfByte3);
/* 123 */       flush();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void flush()
/*     */     throws IOException
/*     */   {
/* 142 */     if (this.doDrain) {
/* 143 */       if (this.sp.txDrain() == -1)
/* 144 */         throw new IOException("txDrain failed, check transmit timeout");
/* 145 */       return;
/*     */     }
/*     */ 
/* 148 */     long l3 = 0L;
/* 149 */     if (this.wrTimeout > 0) {
/* 150 */       long l2 = System.currentTimeMillis();
/* 151 */       l3 = l2 + this.wrTimeout;
/*     */     }
/*     */ 
/* 157 */     while (this.sp.txBufCount() > 0) {
/*     */       try { Thread.sleep(20L); } catch (InterruptedException localInterruptedException) {
/* 159 */       }if (l3 > 0L) {
/* 160 */         long l1 = System.currentTimeMillis();
/* 161 */         if (l1 >= l3)
/* 162 */           throw new ExTimeout("Flush timeout after " + this.wrTimeout + " ms");
/*     */       }
/* 164 */       if (this.abort) {
/* 165 */         this.abort = false;
/* 166 */         System.out.println(getClass().toString() + ": flush aborted");
/* 167 */         return;
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setWriteDrain(boolean paramBoolean)
/*     */   {
/* 178 */     this.writeDrain = paramBoolean;
/*     */   }
/*     */   public boolean getWriteDrain() {
/* 181 */     return this.writeDrain;
/*     */   }
/*     */ 
/*     */   public void flushAbort()
/*     */   {
/* 188 */     this.abort = true;
/*     */   }
/*     */ 
/*     */   public void close()
/*     */     throws IOException
/*     */   {
/* 195 */     this.abort = true;
/* 196 */     super.close();
/*     */   }
/*     */ 
/*     */   public void setWriteTimeout(int paramInt)
/*     */   {
/* 202 */     this.wrTimeout = paramInt; } 
/* 203 */   public int getWriteTimeout() { return this.wrTimeout;
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     Serialio.SerOutputStream
 * JD-Core Version:    0.6.0
 */