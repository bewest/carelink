/*     */ package BayerComm;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ 
/*     */ public class BCIComm extends BCICallback
/*     */ {
/*     */   public static final long BCI_MSG_ENUMDEVICES = 1L;
/*     */   private static final long BCI_MSG_SETLIBPATH = 9L;
/*     */   private static final long BCI_MSG_SETLOGOPT = 2L;
/*     */   public static final long BCI_EVENT_RX = 1L;
/*     */   public static final long BCI_EVENT_FRAME_ERROR = 2L;
/*     */   public static final long BCI_EVENT_TX = 3L;
/*     */   public static final long BCI_EVENT_IO_ERROR = 4L;
/* 153 */   private BCIUsage i_usage = new BCIUsage();
/* 154 */   private long i_rc = 0L;
/* 155 */   private BCICallback client = null;
/*     */ 
/*     */   public long init(BCICallback paramBCICallback)
/*     */   {
/*  36 */     this.client = paramBCICallback;
/*     */ 
/*  38 */     return this.i_rc = i_init(this, new Integer(0), this.i_usage);
/*     */   }
/*     */   public long open(String paramString1, String paramString2, BCIChannel paramBCIChannel) {
/*  41 */     return this.i_rc = i_open(this.i_usage, paramString1, paramString2, paramBCIChannel);
/*     */   }
/*     */   public long send(BCIChannel paramBCIChannel, BCIBuf paramBCIBuf, long paramLong) {
/*  44 */     return this.i_rc = i_send(paramBCIChannel, paramBCIBuf, paramLong);
/*     */   }
/*     */   public long recv(BCIChannel paramBCIChannel, BCIBuf paramBCIBuf, long paramLong1, long paramLong2, BCILong paramBCILong) {
/*  47 */     return this.i_rc = i_recv(paramBCIChannel, paramBCIBuf, paramLong1, paramLong2, paramBCILong);
/*     */   }
/*     */   public long msg(BCIChannel paramBCIChannel, long paramLong, Object paramObject1, Object paramObject2) {
/*  50 */     return this.i_rc = i_msg(paramBCIChannel, paramLong, paramObject1, paramObject2);
/*     */   }
/*     */   public long close(BCIChannel paramBCIChannel) {
/*  53 */     return this.i_rc = i_close(this.i_usage, paramBCIChannel);
/*     */   }
/*     */   public long term() {
/*  56 */     return this.i_rc = i_term(this.i_usage);
/*     */   }
/*     */ 
/*     */   public String[] enumDevices()
/*     */   {
/*  62 */     String[] arrayOfString = null;
/*     */ 
/*  64 */     BCIBuf localBCIBuf = new BCIBuf(1024);
/*  65 */     BCILong localBCILong = new BCILong(1024L);
/*  66 */     BCIChannel localBCIChannel = new BCIChannel();
/*     */ 
/*  68 */     if (msg(localBCIChannel, 1L, localBCIBuf, localBCILong) == 0L) {
/*  69 */       arrayOfString = localBCIBuf.asString().split("\t");
/*     */     }
/*     */ 
/*  72 */     return arrayOfString;
/*     */   }
/*     */ 
/*     */   public static void sleep(long paramLong)
/*     */   {
/*     */     try {
/*  78 */       Thread.sleep(paramLong);
/*     */     }
/*     */     catch (Exception localException)
/*     */     {
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setHidPathInJni(String paramString)
/*     */     throws IOException
/*     */   {
/*  89 */     BCIChannel localBCIChannel = new BCIChannel();
/*  90 */     BCIBuf localBCIBuf = new BCIBuf(1024);
/*  91 */     localBCIBuf.setBuf(paramString);
/*     */ 
/*  94 */     if (msg(localBCIChannel, 9L, localBCIBuf, new BCILong(0L)) != 0L)
/*  95 */       throw new IOException("Set HID Path in JNI Failed");
/*     */   }
/*     */ 
/*     */   public void disableLogging()
/*     */   {
/* 102 */     BCILong localBCILong1 = new BCILong(0L);
/* 103 */     BCILong localBCILong2 = new BCILong(0L);
/* 104 */     BCIChannel localBCIChannel = new BCIChannel();
/* 105 */     long l = msg(localBCIChannel, 2L, localBCILong1, localBCILong2);
/* 106 */     if (l != 0L)
/* 107 */       System.out.println("disableLogging: cmd failed with RC=" + l);
/*     */   }
/*     */ 
/*     */   public void enableLogging()
/*     */   {
/* 116 */     BCILong localBCILong1 = new BCILong(1L);
/* 117 */     BCILong localBCILong2 = new BCILong(0L);
/* 118 */     BCIChannel localBCIChannel = new BCIChannel();
/* 119 */     long l = msg(localBCIChannel, 2L, localBCILong1, localBCILong2);
/* 120 */     if (l != 0L)
/* 121 */       System.out.println("enableLogging: cmd failed with RC=" + l);
/*     */   }
/*     */ 
/*     */   public void onEvent(int paramInt1, int paramInt2, Object paramObject1, Object paramObject2)
/*     */   {
/*     */     try
/*     */     {
/* 129 */       System.out.println("BCIComm:onEvent(" + paramInt2 + ")");
/*     */ 
/* 131 */       if (paramInt2 == 1L) {
/* 132 */         byte[] arrayOfByte = (byte[])(byte[])paramObject1;
/* 133 */         BCIBuf.trace(arrayOfByte);
/*     */       }
/*     */ 
/* 136 */       if (this.client != null)
/* 137 */         this.client.onEvent(paramInt1, paramInt2, paramObject1, paramObject2);
/*     */     }
/*     */     catch (Exception localException)
/*     */     {
/* 141 */       System.out.println(localException.getMessage());
/*     */     }
/*     */   }
/*     */ 
/*     */   private static native long i_init(BCICallback paramBCICallback, Object paramObject, BCIUsage paramBCIUsage);
/*     */ 
/*     */   private static native long i_open(BCIUsage paramBCIUsage, String paramString1, String paramString2, BCIChannel paramBCIChannel);
/*     */ 
/*     */   private static native long i_send(BCIChannel paramBCIChannel, Object paramObject, long paramLong);
/*     */ 
/*     */   private static native long i_recv(BCIChannel paramBCIChannel, Object paramObject, long paramLong1, long paramLong2, BCILong paramBCILong);
/*     */ 
/*     */   private static native long i_msg(BCIChannel paramBCIChannel, long paramLong, Object paramObject1, Object paramObject2);
/*     */ 
/*     */   private static native long i_close(BCIUsage paramBCIUsage, BCIChannel paramBCIChannel);
/*     */ 
/*     */   private static native long i_term(BCIUsage paramBCIUsage);
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     BayerComm.BCIComm
 * JD-Core Version:    0.6.0
 */