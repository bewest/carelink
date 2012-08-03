/*     */ package mdt.common.device.driver.minimed;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Hashtable;
/*     */ import minimed.util.OSInfo;
/*     */ 
/*     */ public class ArtLogicUSBPort
/*     */   implements USBPort, Runnable
/*     */ {
/*     */   private static final int USB_XFER_LEN = 64;
/*     */   private static final String ARTANDLOGIC_JNI_LIB = "libCareLinkUSB.dylib";
/*  35 */   private static final String APPLICATION_DATA_DIR = OSInfo.getUserData();
/*     */   private static final String MEDTRONIC_DIR = "Medtronic";
/*     */   private static final String DDMS_DTW_USB_DIR = "ddmsDTWusb";
/*     */   private static final String COM_LINK2_DIR = "ComLink2";
/*  43 */   private static final String INSTALL_LIB_DIRECTORY = OSInfo.getAllUserPath() + File.separator + APPLICATION_DATA_DIR + File.separator + "Medtronic" + File.separator + "ddmsDTWusb" + File.separator + "ComLink2" + File.separator;
/*     */ 
/*  51 */   private static final String INSTALL_LIB_PATH = INSTALL_LIB_DIRECTORY + "libCareLinkUSB.dylib";
/*     */ 
/*  53 */   private static final ArrayList<IDevicePnPListener> PNP_LISTENERS = new ArrayList();
/*     */ 
/*  59 */   private Hashtable<?, ?> m_runloopMap = new Hashtable();
/*     */   private int m_handle;
/*     */   private Exception m_runException;
/*     */   private Thread m_jniThread;
/*     */ 
/*     */   public void initCommunications()
/*     */     throws IOException
/*     */   {
/*  79 */     if (hasHandle()) {
/*  80 */       endCommunications();
/*     */     }
/*     */ 
/*  83 */     if (this.m_jniThread == null) {
/*  84 */       this.m_jniThread = new Thread(this);
/*  85 */       this.m_jniThread.start();
/*     */     }
/*     */ 
/*  88 */     if (this.m_runException != null)
/*     */     {
/*  90 */       throw new IOException(this.m_runException.getMessage());
/*     */     }
/*     */ 
/*  94 */     int[] arrayOfInt = getHandles();
/*     */ 
/*  96 */     if (arrayOfInt.length == 0) {
/*  97 */       throw new IOException("Available CareLink USB Device not found (1)");
/*     */     }
/*     */ 
/* 100 */     this.m_handle = arrayOfInt[0];
/*     */ 
/* 102 */     if (!hasHandle())
/* 103 */       throw new IOException("Available CareLink USB Device not found (2)");
/*     */   }
/*     */ 
/*     */   public boolean hasHandle()
/*     */   {
/* 111 */     return this.m_handle != 0;
/*     */   }
/*     */ 
/*     */   public void clearBuffers()
/*     */     throws IOException
/*     */   {
/* 118 */     resetPipes(this.m_handle);
/*     */   }
/*     */ 
/*     */   public void endCommunications()
/*     */   {
/* 125 */     if (this.m_jniThread != null)
/*     */     {
/*     */       try {
/* 128 */         stop(this.m_runloopMap);
/*     */       }
/*     */       catch (Exception localException) {
/*     */       }
/* 132 */       this.m_jniThread = null;
/*     */     }
/* 134 */     this.m_handle = 0;
/*     */   }
/*     */ 
/*     */   public byte[] read()
/*     */     throws IOException
/*     */   {
/* 141 */     return read(64);
/*     */   }
/*     */ 
/*     */   public byte[] read(int paramInt)
/*     */     throws IOException
/*     */   {
/* 149 */     checkHandle();
/* 150 */     byte[] arrayOfByte = new byte[paramInt];
/* 151 */     readNative(this.m_handle, arrayOfByte, 0, paramInt);
/* 152 */     return arrayOfByte;
/*     */   }
/*     */ 
/*     */   public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/*     */     throws IOException
/*     */   {
/* 159 */     byte[] arrayOfByte = new byte[paramInt2];
/* 160 */     System.arraycopy(paramArrayOfByte, paramInt1, arrayOfByte, 0, paramInt2);
/* 161 */     write(arrayOfByte);
/*     */   }
/*     */ 
/*     */   public void write(byte[] paramArrayOfByte)
/*     */     throws IOException
/*     */   {
/* 169 */     checkHandle();
/* 170 */     writeNative(this.m_handle, paramArrayOfByte);
/*     */   }
/*     */ 
/*     */   public static void addPnPListener(IDevicePnPListener paramIDevicePnPListener)
/*     */   {
/* 179 */     synchronized (PNP_LISTENERS) {
/* 180 */       if (PNP_LISTENERS.contains(paramIDevicePnPListener)) {
/* 181 */         return;
/*     */       }
/* 183 */       PNP_LISTENERS.add(paramIDevicePnPListener);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void removePnPListener(IDevicePnPListener paramIDevicePnPListener)
/*     */   {
/* 192 */     synchronized (PNP_LISTENERS) {
/* 193 */       if (!PNP_LISTENERS.contains(paramIDevicePnPListener)) {
/* 194 */         return;
/*     */       }
/* 196 */       PNP_LISTENERS.remove(paramIDevicePnPListener);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void run()
/*     */   {
/* 204 */     this.m_runException = null;
/*     */     try
/*     */     {
/* 207 */       init(this.m_runloopMap);
/*     */     } catch (Exception localException) {
/* 209 */       this.m_runException = localException;
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getVersion()
/*     */   {
/* 219 */     return version();
/*     */   }
/*     */ 
/*     */   public native int[] getHandles();
/*     */ 
/*     */   public native void resetPipes(int paramInt)
/*     */     throws IOException;
/*     */ 
/*     */   public native void readNative(int paramInt1, byte[] paramArrayOfByte, int paramInt2, int paramInt3)
/*     */     throws IOException;
/*     */ 
/*     */   public native void writeNative(int paramInt, byte[] paramArrayOfByte)
/*     */     throws IOException;
/*     */ 
/*     */   public native String version();
/*     */ 
/*     */   protected native void stop(Hashtable<?, ?> paramHashtable);
/*     */ 
/*     */   protected native void init(Hashtable<?, ?> paramHashtable)
/*     */     throws Exception;
/*     */ 
/*     */   protected native void setReadTimeouts(int paramInt1, int paramInt2);
/*     */ 
/*     */   protected void finalize()
/*     */     throws Throwable
/*     */   {
/* 291 */     endCommunications();
/* 292 */     super.finalize();
/*     */   }
/*     */ 
/*     */   protected synchronized void deviceInserted()
/*     */   {
/* 297 */     int[] arrayOfInt = getHandles();
/*     */ 
/* 299 */     this.m_handle = arrayOfInt[0];
/*     */ 
/* 301 */     synchronized (PNP_LISTENERS) {
/* 302 */       for (int i = 0; i < PNP_LISTENERS.size(); i++) {
/* 303 */         IDevicePnPListener localIDevicePnPListener = (IDevicePnPListener)PNP_LISTENERS.get(i);
/* 304 */         localIDevicePnPListener.deviceAttached(this.m_handle);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   protected synchronized void deviceRemoved()
/*     */   {
/* 311 */     synchronized (PNP_LISTENERS) {
/* 312 */       for (int i = 0; i < PNP_LISTENERS.size(); i++) {
/* 313 */         IDevicePnPListener localIDevicePnPListener = (IDevicePnPListener)PNP_LISTENERS.get(i);
/* 314 */         localIDevicePnPListener.deviceDetached(this.m_handle);
/*     */       }
/*     */     }
/* 317 */     this.m_handle = 0;
/*     */   }
/*     */ 
/*     */   private void checkHandle()
/*     */     throws IOException
/*     */   {
/* 326 */     if (!hasHandle())
/* 327 */       throw new IOException("device not found");
/*     */   }
/*     */ 
/*     */   public native void transactNative(int paramInt1, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt2, int paramInt3)
/*     */     throws IOException;
/*     */ 
/*     */   static
/*     */   {
/*  70 */     System.load(INSTALL_LIB_PATH);
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     mdt.common.device.driver.minimed.ArtLogicUSBPort
 * JD-Core Version:    0.6.0
 */