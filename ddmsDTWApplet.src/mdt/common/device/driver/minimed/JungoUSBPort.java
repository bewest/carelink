/*     */ package mdt.common.device.driver.minimed;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class JungoUSBPort
/*     */ {
/*     */   private static final String JUNGO_JNI_LIB = "cl2_jni_wrapper.dll";
/*  49 */   private static final String INSTALL_DLL_DIRECTORY = System.getProperty("user.home") + File.separator + "Medtronic" + File.separator + "ddmsDTWusb" + File.separator + "ComLink2" + File.separator;
/*     */   private static final String INSTALL_DLL_FILENAME = "cl2_jni_wrapper.dll";
/*  61 */   private static final String INSTALL_DLL_PATH = INSTALL_DLL_DIRECTORY + "cl2_jni_wrapper.dll";
/*     */ 
/*  64 */   private static final ArrayList HANDLES_IN_USE = new ArrayList();
/*  65 */   private static final ArrayList PNP_LISTENERS = new ArrayList();
/*     */ 
/*  77 */   private int handle = 0;
/*     */ 
/*     */   public void initCommunications()
/*     */     throws IOException
/*     */   {
/*  88 */     if (this.handle != 0) {
/*  89 */       endCommunications();
/*     */     }
/*     */ 
/*  93 */     int[] arrayOfInt = getDeviceHandles();
/*     */ 
/*  95 */     if (arrayOfInt.length == 0) {
/*  96 */       throw new IOException("Available CareLink USB Device not found");
/*     */     }
/*     */ 
/*  99 */     synchronized (HANDLES_IN_USE) {
/* 100 */       for (int i = 0; i < arrayOfInt.length; i++) {
/* 101 */         Integer localInteger = new Integer(arrayOfInt[i]);
/* 102 */         if (HANDLES_IN_USE.contains(localInteger))
/*     */           continue;
/* 104 */         HANDLES_IN_USE.add(localInteger);
/* 105 */         this.handle = arrayOfInt[i];
/* 106 */         break;
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean hasHandle()
/*     */   {
/* 118 */     return this.handle != 0;
/*     */   }
/*     */ 
/*     */   public void clearBuffers()
/*     */     throws IOException
/*     */   {
/* 126 */     resetPipes(this.handle);
/*     */   }
/*     */ 
/*     */   public void endCommunications()
/*     */   {
/* 133 */     if (this.handle == 0) {
/* 134 */       return;
/*     */     }
/*     */ 
/* 137 */     synchronized (HANDLES_IN_USE) {
/* 138 */       HANDLES_IN_USE.remove(new Integer(this.handle));
/* 139 */       this.handle = 0;
/*     */     }
/*     */   }
/*     */ 
/*     */   public byte[] read()
/*     */     throws IOException
/*     */   {
/* 149 */     return read(64);
/*     */   }
/*     */ 
/*     */   public byte[] read(int paramInt)
/*     */     throws IOException
/*     */   {
/* 159 */     byte[] arrayOfByte = new byte[paramInt];
/*     */ 
/* 162 */     readNative(this.handle, arrayOfByte, 0, paramInt);
/*     */ 
/* 165 */     return arrayOfByte;
/*     */   }
/*     */ 
/*     */   public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/*     */     throws IOException
/*     */   {
/* 176 */     byte[] arrayOfByte = new byte[paramInt2];
/* 177 */     System.arraycopy(paramArrayOfByte, paramInt1, arrayOfByte, 0, paramInt2);
/*     */ 
/* 179 */     write(arrayOfByte);
/*     */   }
/*     */ 
/*     */   public void write(byte[] paramArrayOfByte)
/*     */     throws IOException
/*     */   {
/* 189 */     writeNative(this.handle, paramArrayOfByte);
/*     */   }
/*     */ 
/*     */   public static void addPnPListener(IDevicePnPListener paramIDevicePnPListener)
/*     */   {
/* 198 */     synchronized (PNP_LISTENERS) {
/* 199 */       if (PNP_LISTENERS.contains(paramIDevicePnPListener)) {
/* 200 */         return;
/*     */       }
/* 202 */       PNP_LISTENERS.add(paramIDevicePnPListener);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void removePnPListener(IDevicePnPListener paramIDevicePnPListener)
/*     */   {
/* 211 */     synchronized (PNP_LISTENERS) {
/* 212 */       if (!PNP_LISTENERS.contains(paramIDevicePnPListener)) {
/* 213 */         return;
/*     */       }
/*     */ 
/* 216 */       PNP_LISTENERS.remove(paramIDevicePnPListener);
/*     */     }
/*     */   }
/*     */ 
/*     */   protected void finalize() throws Throwable {
/* 221 */     endCommunications();
/* 222 */     super.finalize();
/*     */   }
/*     */ 
/*     */   protected static void onAdd(int paramInt)
/*     */   {
/* 227 */     synchronized (PNP_LISTENERS) {
/* 228 */       for (int i = 0; i < PNP_LISTENERS.size(); i++) {
/* 229 */         IDevicePnPListener localIDevicePnPListener = (IDevicePnPListener)PNP_LISTENERS.get(i);
/* 230 */         localIDevicePnPListener.deviceAttached(paramInt);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   protected static void onRemove(int paramInt)
/*     */   {
/* 237 */     synchronized (PNP_LISTENERS) {
/* 238 */       for (int i = 0; i < PNP_LISTENERS.size(); i++) {
/* 239 */         IDevicePnPListener localIDevicePnPListener = (IDevicePnPListener)PNP_LISTENERS.get(i);
/* 240 */         localIDevicePnPListener.deviceDetached(paramInt);
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
/*  71 */       System.load(INSTALL_DLL_PATH);
/*     */     } catch (Throwable localThrowable) {
/*  73 */       localThrowable.printStackTrace();
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     mdt.common.device.driver.minimed.JungoUSBPort
 * JD-Core Version:    0.6.0
 */