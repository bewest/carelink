/*     */ package mdt.common.device.driver.minimed;
/*     */ 
/*     */ import BayerComm.BCIBuf;
/*     */ import BayerComm.BCIChannel;
/*     */ import BayerComm.BCIComm;
/*     */ import BayerComm.BCILong;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.StringTokenizer;
/*     */ import minimed.util.OSInfo;
/*     */ 
/*     */ public class BayerUSB
/*     */   implements USBPort
/*     */ {
/*     */   private static final int USB_XFER_LEN = 1024;
/*     */   private static final String BCI_JNI_LIB = "bci_jni_wrapper.dll";
/*     */   private static final String BCI_JNI_LIB_64 = "bci_jni_wrapper_64.dll";
/*     */   private static final String BCI_JNI_LIB_MAC = "bci_jni_wrapper.dll";
/*     */   private static final String BCI_HID_LIB = "BayerHID00.dll";
/*     */   private static final String BCI_HID_LIB_64 = "BayerHID00_64.dll";
/*     */   private static final String BCI_HID_LIB_MAC = "BayerHID00.dll";
/*  60 */   private static final String APPLICATION_DATA_DIR = OSInfo.getUserData();
/*     */   private static final String MEDTRONIC_DIR = "Medtronic";
/*     */   private static final String DDMS_DTW_USB_DIR = "ddmsDTWusb";
/*     */   private static final String BAYER_DIR = "Bayer";
/*     */   private static final String VERSION = "1.1";
/*  69 */   private static final String INSTALL_DLL_DIRECTORY = OSInfo.getAllUserPath() + File.separator + APPLICATION_DATA_DIR + File.separator + "Medtronic" + File.separator + "ddmsDTWusb" + File.separator + "Bayer" + File.separator;
/*     */ 
/*  77 */   private static final String INSTALL_DLL_PATH = INSTALL_DLL_DIRECTORY + "bci_jni_wrapper.dll";
/*     */ 
/*  83 */   private static final String INSTALL_DLL_PATH_64 = INSTALL_DLL_DIRECTORY + "bci_jni_wrapper_64.dll";
/*     */ 
/*  89 */   private static final String INSTALL_DLL_PATH_MAC = INSTALL_DLL_DIRECTORY + "bci_jni_wrapper.dll";
/*     */   private static final int INIT_COMM_SLEEP = 5000;
/*     */   private static final int END_COMM_SLEEP = 3000;
/*     */   private long m_rc;
/*     */   private BCIBuf m_buf;
/*     */   private BCILong m_len;
/*     */   private BCIChannel m_channel;
/*     */   private String m_config;
/*     */   private BCIComm m_comm;
/*     */   private long m_maxWaitMS;
/*     */   private boolean m_opened;
/*     */   private String m_vid;
/*     */   private String m_pid;
/*     */ 
/*     */   public BayerUSB(long paramLong, String paramString1, String paramString2)
/*     */   {
/* 167 */     this.m_rc = 0L;
/* 168 */     this.m_buf = new BCIBuf(1024);
/* 169 */     this.m_len = new BCILong(1024L);
/* 170 */     this.m_channel = new BCIChannel();
/* 171 */     this.m_config = "config";
/* 172 */     this.m_comm = new BCIComm();
/* 173 */     this.m_maxWaitMS = paramLong;
/* 174 */     this.m_vid = paramString1;
/* 175 */     this.m_pid = paramString2;
/*     */   }
/*     */ 
/*     */   public void initCommunications()
/*     */     throws IOException
/*     */   {
/* 184 */     this.m_opened = false;
/*     */ 
/* 186 */     sleepMS(5000);
/*     */ 
/* 188 */     String[] arrayOfString1 = getVidPidStrings();
/*     */ 
/* 190 */     for (int i = 0; i < arrayOfString1.length; i++) {
/* 191 */       String[] arrayOfString2 = getVidPidValues(arrayOfString1[i]);
/* 192 */       if ((arrayOfString2[0].equals(this.m_vid)) && (arrayOfString2[1].equals(this.m_pid))) {
/* 193 */         if ((this.m_rc = this.m_comm.open(arrayOfString1[i], this.m_config, this.m_channel)) != 0L)
/* 194 */           throw new IOException("open failed:" + this.m_rc);
/* 195 */         this.m_opened = true;
/* 196 */         break;
/*     */       }
/*     */     }
/* 199 */     checkOpened();
/* 200 */     clearBuffers();
/*     */   }
/*     */ 
/*     */   public void write(byte[] paramArrayOfByte)
/*     */     throws IOException
/*     */   {
/* 208 */     checkOpened();
/* 209 */     this.m_buf.setBuf(paramArrayOfByte, paramArrayOfByte.length);
/* 210 */     if ((this.m_rc = this.m_comm.send(this.m_channel, this.m_buf, paramArrayOfByte.length)) != 0L)
/* 211 */       throw new IOException("send failed:" + this.m_rc);
/*     */   }
/*     */ 
/*     */   public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/*     */     throws IOException
/*     */   {
/* 219 */     byte[] arrayOfByte = new byte[paramInt2];
/* 220 */     System.arraycopy(paramArrayOfByte, paramInt1, arrayOfByte, 0, paramInt2);
/* 221 */     write(arrayOfByte);
/*     */   }
/*     */ 
/*     */   public byte[] read()
/*     */     throws IOException
/*     */   {
/* 228 */     return read(1024);
/*     */   }
/*     */ 
/*     */   public byte[] read(int paramInt)
/*     */     throws IOException
/*     */   {
/* 235 */     checkOpened();
/* 236 */     BCIBuf localBCIBuf = new BCIBuf(paramInt);
/* 237 */     BCILong localBCILong = new BCILong(paramInt);
/* 238 */     if ((this.m_rc = this.m_comm.recv(this.m_channel, localBCIBuf, localBCIBuf.getLength(), this.m_maxWaitMS, localBCILong)) != 0L) {
/* 239 */       throw new IOException("recv failed:" + this.m_rc);
/*     */     }
/* 241 */     return localBCIBuf.getBuf();
/*     */   }
/*     */ 
/*     */   public void endCommunications()
/*     */   {
/* 248 */     if (this.m_opened)
/*     */     {
/* 250 */       sleepMS(3000);
/* 251 */       this.m_comm.close(this.m_channel);
/* 252 */       this.m_comm.term();
/* 253 */       this.m_opened = false;
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getVersion()
/*     */   {
/* 263 */     return "1.1";
/*     */   }
/*     */ 
/*     */   public void clearBuffers()
/*     */     throws IOException
/*     */   {
/* 270 */     checkOpened();
/* 271 */     BCIBuf localBCIBuf = new BCIBuf(1024);
/* 272 */     BCILong localBCILong = new BCILong(1024L);
/*     */ 
/* 274 */     if ((this.m_rc = this.m_comm.recv(this.m_channel, localBCIBuf, -1L, 100L, localBCILong)) != 0L)
/* 275 */       throw new IOException("recv flush failed:" + this.m_rc);
/*     */   }
/*     */ 
/*     */   public boolean hasHandle()
/*     */   {
/* 283 */     return true;
/*     */   }
/*     */ 
/*     */   public void checkOpened()
/*     */     throws IOException
/*     */   {
/* 292 */     if (!this.m_opened)
/* 293 */       throw new IOException("connection not open");
/*     */   }
/*     */ 
/*     */   public boolean isConnOpened()
/*     */   {
/* 303 */     return this.m_opened;
/*     */   }
/*     */ 
/*     */   public long getMaxWaitMS()
/*     */   {
/* 312 */     return this.m_maxWaitMS;
/*     */   }
/*     */ 
/*     */   public void setMaxWaitMS(long paramLong)
/*     */   {
/* 321 */     this.m_maxWaitMS = paramLong;
/*     */   }
/*     */ 
/*     */   private void sleepMS(int paramInt)
/*     */   {
/*     */     try
/*     */     {
/* 331 */       Thread.sleep(paramInt);
/*     */     }
/*     */     catch (InterruptedException localInterruptedException)
/*     */     {
/*     */     }
/*     */   }
/*     */ 
/*     */   private String[] getVidPidStrings()
/*     */     throws IOException
/*     */   {
/* 344 */     if ((this.m_rc = this.m_comm.init(null)) != 0L) {
/* 345 */       throw new IOException("init failed:" + this.m_rc);
/*     */     }
/*     */ 
/* 349 */     this.m_comm.disableLogging();
/*     */ 
/* 351 */     this.m_buf.setBuf("X");
/* 352 */     if ((this.m_rc = this.m_comm.msg(this.m_channel, 1L, this.m_buf, this.m_len)) != 0L) {
/* 353 */       throw new IOException("msg failed:" + this.m_rc);
/*     */     }
/*     */ 
/* 356 */     String[] arrayOfString = this.m_buf.asString().split("\t");
/* 357 */     return arrayOfString;
/*     */   }
/*     */ 
/*     */   private String[] getVidPidValues(String paramString)
/*     */     throws IOException
/*     */   {
/* 369 */     if (paramString == null) {
/* 370 */       throw new IOException("null VID/PID reply");
/*     */     }
/*     */ 
/* 373 */     StringTokenizer localStringTokenizer = new StringTokenizer(paramString, "#&");
/* 374 */     String[] arrayOfString = new String[2];
/*     */     try {
/* 376 */       localStringTokenizer.nextToken();
/* 377 */       arrayOfString[0] = localStringTokenizer.nextToken();
/* 378 */       arrayOfString[1] = localStringTokenizer.nextToken();
/*     */     } catch (NoSuchElementException localNoSuchElementException) {
/* 380 */       throw new IOException("bad VID/PID reply: <" + paramString + ">");
/*     */     }
/* 382 */     return arrayOfString;
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/* 117 */     BCIComm localBCIComm = new BCIComm();
/* 118 */     if (OSInfo.isMac())
/*     */       try {
/* 120 */         System.out.println("Loading Mac Bayer USB JNI Library:  " + INSTALL_DLL_PATH_MAC);
/* 121 */         System.load(INSTALL_DLL_PATH_MAC);
/* 122 */         System.out.println("Loading Mac Bayer USB HID Library:  " + INSTALL_DLL_DIRECTORY + "BayerHID00.dll");
/*     */ 
/* 124 */         System.load(INSTALL_DLL_DIRECTORY + "BayerHID00.dll");
/* 125 */         localBCIComm.setHidPathInJni(INSTALL_DLL_DIRECTORY + "BayerHID00.dll");
/*     */       } catch (Throwable localThrowable1) {
/* 127 */         localThrowable1.printStackTrace();
/*     */       }
/*     */     else
/*     */       try
/*     */       {
/* 132 */         System.out.println("Loading Windows 32-Bit Bayer USB Libraries from:  " + INSTALL_DLL_DIRECTORY);
/*     */ 
/* 134 */         System.load(INSTALL_DLL_PATH);
/* 135 */         System.out.println("loaded: " + INSTALL_DLL_PATH);
/* 136 */         System.load(INSTALL_DLL_DIRECTORY + "BayerHID00.dll");
/* 137 */         System.out.println("loaded: " + INSTALL_DLL_DIRECTORY + "BayerHID00.dll");
/* 138 */         localBCIComm.setHidPathInJni(INSTALL_DLL_DIRECTORY + "BayerHID00.dll");
/*     */       }
/*     */       catch (UnsatisfiedLinkError localUnsatisfiedLinkError) {
/*     */         try {
/* 142 */           System.out.println("Loading Windows 64-Bit Bayer USB Libraries from:  " + INSTALL_DLL_DIRECTORY);
/*     */ 
/* 144 */           System.load(INSTALL_DLL_PATH_64);
/* 145 */           System.out.println("loaded: " + INSTALL_DLL_PATH_64);
/* 146 */           System.load(INSTALL_DLL_DIRECTORY + "BayerHID00_64.dll");
/* 147 */           System.out.println("loaded: " + INSTALL_DLL_DIRECTORY + "BayerHID00_64.dll");
/* 148 */           localBCIComm.setHidPathInJni(INSTALL_DLL_DIRECTORY + "BayerHID00_64.dll");
/*     */         } catch (Throwable localThrowable3) {
/* 150 */           localThrowable3.printStackTrace();
/*     */         }
/*     */       } catch (Throwable localThrowable2) {
/* 153 */         localThrowable2.printStackTrace();
/*     */       }
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     mdt.common.device.driver.minimed.BayerUSB
 * JD-Core Version:    0.6.0
 */