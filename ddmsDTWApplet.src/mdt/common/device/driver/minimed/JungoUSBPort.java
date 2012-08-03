/*     */ package mdt.common.device.driver.minimed;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import minimed.util.OSInfo;
/*     */ 
/*     */ public class JungoUSBPort
/*     */   implements USBPort
/*     */ {
/*     */   private static final int USB_XFER_LEN = 64;
/*     */   private static final String JUNGO_JNI_LIB = "cl2_jni_wrapper.dll";
/*     */   private static final String JUNGO_JNI_LIB_64 = "cl2_jni_wrapper_64.dll";
/*  56 */   private static final String APPLICATION_DATA_DIR = OSInfo.getUserData();
/*     */   private static final String MEDTRONIC_DIR = "Medtronic";
/*     */   private static final String DDMS_DTW_USB_DIR = "ddmsDTWusb";
/*     */   private static final String COM_LINK2_DIR = "ComLink2";
/*  64 */   private static final String INSTALL_DLL_DIRECTORY = OSInfo.getAllUserPath() + File.separator + APPLICATION_DATA_DIR + File.separator + "Medtronic" + File.separator + "ddmsDTWusb" + File.separator + "ComLink2" + File.separator;
/*     */ 
/*  72 */   private static final String INSTALL_DLL_PATH = INSTALL_DLL_DIRECTORY + "cl2_jni_wrapper.dll";
/*     */ 
/*  77 */   private static final String INSTALL_DLL_PATH_64 = INSTALL_DLL_DIRECTORY + "cl2_jni_wrapper_64.dll";
/*     */ 
/*  80 */   private static final ArrayList HANDLES_IN_USE = new ArrayList();
/*  81 */   private static final ArrayList PNP_LISTENERS = new ArrayList();
/*     */   private static final String VERSION = "10.10";
/*  97 */   private int handle = 0;
/*     */ 
/*     */   public void initCommunications()
/*     */     throws IOException
/*     */   {
/* 105 */     if (this.handle != 0) {
/* 106 */       endCommunications();
/*     */     }
/*     */ 
/* 110 */     int[] arrayOfInt = getDeviceHandles();
/*     */ 
/* 112 */     if (arrayOfInt.length == 0) {
/* 113 */       throw new IOException("Available CareLink USB Device not found");
/*     */     }
/*     */ 
/* 116 */     synchronized (HANDLES_IN_USE) {
/* 117 */       for (int i = 0; i < arrayOfInt.length; i++) {
/* 118 */         Integer localInteger = new Integer(arrayOfInt[i]);
/* 119 */         if (HANDLES_IN_USE.contains(localInteger))
/*     */           continue;
/* 121 */         HANDLES_IN_USE.add(localInteger);
/* 122 */         this.handle = arrayOfInt[i];
/* 123 */         break;
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean hasHandle()
/*     */   {
/* 133 */     return this.handle != 0;
/*     */   }
/*     */ 
/*     */   public void clearBuffers()
/*     */     throws IOException
/*     */   {
/* 140 */     resetPipes(this.handle);
/*     */   }
/*     */ 
/*     */   public void endCommunications()
/*     */   {
/* 147 */     if (this.handle == 0) {
/* 148 */       return;
/*     */     }
/*     */ 
/* 151 */     synchronized (HANDLES_IN_USE) {
/* 152 */       HANDLES_IN_USE.remove(new Integer(this.handle));
/* 153 */       this.handle = 0;
/*     */     }
/*     */   }
/*     */ 
/*     */   public byte[] read()
/*     */     throws IOException
/*     */   {
/* 161 */     return read(64);
/*     */   }
/*     */ 
/*     */   public byte[] read(int paramInt)
/*     */     throws IOException
/*     */   {
/* 168 */     byte[] arrayOfByte = new byte[paramInt];
/*     */ 
/* 171 */     readNative(this.handle, arrayOfByte, 0, paramInt);
/*     */ 
/* 174 */     return arrayOfByte;
/*     */   }
/*     */ 
/*     */   public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/*     */     throws IOException
/*     */   {
/* 181 */     byte[] arrayOfByte = new byte[paramInt2];
/* 182 */     System.arraycopy(paramArrayOfByte, paramInt1, arrayOfByte, 0, paramInt2);
/*     */ 
/* 184 */     write(arrayOfByte);
/*     */   }
/*     */ 
/*     */   public void write(byte[] paramArrayOfByte)
/*     */     throws IOException
/*     */   {
/* 192 */     writeNative(this.handle, paramArrayOfByte);
/*     */   }
/*     */ 
/*     */   public static void addPnPListener(IDevicePnPListener paramIDevicePnPListener)
/*     */   {
/* 201 */     synchronized (PNP_LISTENERS) {
/* 202 */       if (PNP_LISTENERS.contains(paramIDevicePnPListener)) {
/* 203 */         return;
/*     */       }
/* 205 */       PNP_LISTENERS.add(paramIDevicePnPListener);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void removePnPListener(IDevicePnPListener paramIDevicePnPListener)
/*     */   {
/* 214 */     synchronized (PNP_LISTENERS) {
/* 215 */       if (!PNP_LISTENERS.contains(paramIDevicePnPListener)) {
/* 216 */         return;
/*     */       }
/*     */ 
/* 219 */       PNP_LISTENERS.remove(paramIDevicePnPListener);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getVersion()
/*     */   {
/* 231 */     return "10.10";
/*     */   }
/*     */ 
/*     */   protected void finalize() throws Throwable {
/* 235 */     endCommunications();
/* 236 */     super.finalize();
/*     */   }
/*     */ 
/*     */   protected static void onAdd(int paramInt)
/*     */   {
/* 241 */     synchronized (PNP_LISTENERS) {
/* 242 */       for (int i = 0; i < PNP_LISTENERS.size(); i++) {
/* 243 */         IDevicePnPListener localIDevicePnPListener = (IDevicePnPListener)PNP_LISTENERS.get(i);
/* 244 */         localIDevicePnPListener.deviceAttached(paramInt);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   protected static void onRemove(int paramInt)
/*     */   {
/* 251 */     synchronized (PNP_LISTENERS) {
/* 252 */       for (int i = 0; i < PNP_LISTENERS.size(); i++) {
/* 253 */         IDevicePnPListener localIDevicePnPListener = (IDevicePnPListener)PNP_LISTENERS.get(i);
/* 254 */         localIDevicePnPListener.deviceDetached(paramInt);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private native int[] getDeviceHandles();
/*     */ 
/*     */   private native void resetPipes(int paramInt);
/*     */ 
/*     */   private native void readNative(int paramInt1, byte[] paramArrayOfByte, int paramInt2, int paramInt3)
/*     */     throws IOException;
/*     */ 
/*     */   private native void writeNative(int paramInt, byte[] paramArrayOfByte)
/*     */     throws IOException;
/*     */ 
/*     */   static
/*     */   {
/*     */     try
/*     */     {
/*  88 */       System.load(INSTALL_DLL_PATH);
/*     */     }
/*     */     catch (UnsatisfiedLinkError localUnsatisfiedLinkError) {
/*  91 */       System.load(INSTALL_DLL_PATH_64);
/*     */     } catch (Throwable localThrowable) {
/*  93 */       localThrowable.printStackTrace();
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     mdt.common.device.driver.minimed.JungoUSBPort
 * JD-Core Version:    0.6.0
 */