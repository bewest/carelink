/*     */ package Serialio;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ 
/*     */ public class SnoopLoop
/*     */   implements SnoopListener
/*     */ {
/*  39 */   protected InputStream in = null;
/*     */   private SnoopInputStream sis;
/*     */   private SnoopLoopListener lsnr;
/*  42 */   private int napTime = 10;
/*     */   private boolean snoopLoopDataSighted;
/*  44 */   private boolean dataDiscard = false;
/*  45 */   private byte[] rdBuf = new byte[1024];
/*     */   private boolean abortWait;
/*     */ 
/*     */   public SnoopLoop()
/*     */   {
/*     */   }
/*     */ 
/*     */   public SnoopLoop(SnoopLoopListener paramSnoopLoopListener, SnoopInputStream paramSnoopInputStream, int paramInt)
/*     */   {
/*  62 */     this.sis = paramSnoopInputStream;
/*  63 */     this.lsnr = paramSnoopLoopListener;
/*  64 */     this.napTime = paramInt;
/*     */   }
/*     */ 
/*     */   public synchronized int waitFor(String paramString, int paramInt)
/*     */     throws IOException, InterruptedException
/*     */   {
/*  84 */     return waitFor(paramString.getBytes(), paramInt);
/*     */   }
/*     */ 
/*     */   public synchronized int waitFor(byte[] paramArrayOfByte, int paramInt)
/*     */     throws IOException, InterruptedException
/*     */   {
/* 112 */     long l1 = System.currentTimeMillis();
/* 113 */     long l2 = l1 + paramInt;
/*     */ 
/* 115 */     int j = 0; int k = 0;
/* 116 */     this.sis.addSnoop(this, paramArrayOfByte, false);
/* 117 */     this.snoopLoopDataSighted = false;
/* 118 */     this.abortWait = false;
/*     */     do {
/* 120 */       j = this.sis.available();
/* 121 */       if (j > 0) {
/* 122 */         if (k + j > this.rdBuf.length) k = 0;
/* 123 */         int i = this.sis.read(this.rdBuf, k, j);
/* 124 */         k += i;
/* 125 */         if (!this.dataDiscard)
/* 126 */           this.lsnr.snoopLoopEvent(this.rdBuf, i);
/*     */       }
/* 128 */       if (this.abortWait) break;
/* 129 */       long l3 = System.currentTimeMillis();
/* 130 */       if (l3 >= l2) break;
/* 131 */       Thread.sleep(this.napTime);
/* 132 */     }while (!this.snoopLoopDataSighted);
/*     */ 
/* 134 */     this.sis.removeSnoop(this);
/* 135 */     if (!this.snoopLoopDataSighted) return -1;
/* 136 */     return (int)(System.currentTimeMillis() - l1);
/*     */   }
/*     */ 
/*     */   public synchronized int waitFor(int paramInt1, int paramInt2)
/*     */     throws IOException, InterruptedException
/*     */   {
/* 156 */     long l1 = System.currentTimeMillis();
/* 157 */     long l2 = l1 + paramInt2;
/*     */ 
/* 159 */     int j = 0; int k = 0; int m = 0;
/* 160 */     this.abortWait = false;
/*     */     do {
/* 162 */       j = this.sis.available();
/* 163 */       if (j > 0) {
/* 164 */         if (m + j > this.rdBuf.length) m = 0;
/* 165 */         int i = this.sis.read(this.rdBuf, m, j);
/* 166 */         k += i;
/* 167 */         m += i;
/* 168 */         if (!this.dataDiscard)
/* 169 */           this.lsnr.snoopLoopEvent(this.rdBuf, i);
/*     */       }
/* 171 */       if (this.abortWait) break;
/* 172 */       long l3 = System.currentTimeMillis();
/* 173 */       if (l3 >= l2) break;
/* 174 */       Thread.sleep(this.napTime);
/* 175 */     }while (k < paramInt1);
/*     */ 
/* 177 */     if (k < paramInt1) return -1;
/* 178 */     return (int)(System.currentTimeMillis() - l1);
/*     */   }
/*     */ 
/*     */   public void snoopEvent(byte[] paramArrayOfByte)
/*     */   {
/* 186 */     this.snoopLoopDataSighted = true;
/*     */   }
/*     */ 
/*     */   public void setDataDiscard(boolean paramBoolean)
/*     */   {
/* 194 */     this.dataDiscard = paramBoolean;
/*     */   }
/*     */ 
/*     */   public boolean getDataDiscard(boolean paramBoolean)
/*     */   {
/* 199 */     return this.dataDiscard;
/*     */   }
/*     */ 
/*     */   public void setReadBuffer(byte[] paramArrayOfByte)
/*     */   {
/* 205 */     this.rdBuf = paramArrayOfByte;
/*     */   }
/*     */ 
/*     */   public byte[] getReadBuffer()
/*     */   {
/* 210 */     return this.rdBuf;
/*     */   }
/*     */ 
/*     */   public void setNapTime(int paramInt)
/*     */   {
/* 218 */     this.napTime = paramInt;
/*     */   }
/*     */ 
/*     */   public int getNapTime()
/*     */   {
/* 223 */     return this.napTime;
/*     */   }
/*     */ 
/*     */   public void abort()
/*     */   {
/* 228 */     this.abortWait = true;
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     Serialio.SnoopLoop
 * JD-Core Version:    0.6.0
 */