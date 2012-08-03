/*     */ package minimed.ddms.applet.dtw;
/*     */ 
/*     */ import java.awt.Component;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InterruptedIOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.ProgressMonitor;
/*     */ import javax.swing.ProgressMonitorInputStream;
/*     */ import minimed.ddms.A.EA;
/*     */ import minimed.ddms.A.EA._A;
/*     */ 
/*     */ public final class NetHelper extends IOHelper
/*     */ {
/*     */   private static final int REQ_STORE_SNAPSHOT = 21;
/*     */   private static final int REQ_STORE_LOG = 22;
/*     */   private static final int REQ_GET_SERVER_TIME = 23;
/*     */   private static final int REQ_GET_LAST_PUMP_HISTORY_PAGE_READ = 24;
/*     */   private static final int REQ_GET_LAST_GLUCOSE_HISTORY_PAGE_READ = 26;
/*     */   private static final int REQ_NOTIFY = 25;
/*     */   private static final int REQ_GET_CONTROL_CODE = 27;
/*     */   private static final int RSP_OK = 46;
/*     */   private static final int RSP_SERVER_ERROR = 47;
/*     */   private static final int RSP_CLIENT_ERROR = 48;
/*     */   private static final int RSP_TRANSMIT_ERROR = 49;
/*     */ 
/*     */   public long getLastGlucoseHistoryPageRead(String paramString1, String paramString2, String paramString3)
/*     */     throws IOException
/*     */   {
/*  67 */     Object localObject = exchange(paramString1, new ObjectExchanger(paramString2, paramString3) {
/*     */       public void writeRequest(ObjectOutputStream paramObjectOutputStream) throws IOException {
/*  69 */         paramObjectOutputStream.writeInt(26);
/*  70 */         paramObjectOutputStream.writeObject(this.val$model);
/*  71 */         paramObjectOutputStream.writeObject(this.val$serialNumber);
/*     */       }
/*     */ 
/*     */       public Object readResponse(ObjectInputStream paramObjectInputStream) throws IOException {
/*  75 */         long l = paramObjectInputStream.readLong();
/*  76 */         return new Long(l);
/*     */       }
/*     */     });
/*  79 */     long l = ((Long)localObject).longValue();
/*  80 */     return l;
/*     */   }
/*     */ 
/*     */   public long getLastPumpHistoryPageRead(String paramString1, String paramString2, String paramString3)
/*     */     throws IOException
/*     */   {
/*  96 */     Object localObject = exchange(paramString1, new ObjectExchanger(paramString2, paramString3) {
/*     */       public void writeRequest(ObjectOutputStream paramObjectOutputStream) throws IOException {
/*  98 */         paramObjectOutputStream.writeInt(24);
/*  99 */         paramObjectOutputStream.writeObject(this.val$model);
/* 100 */         paramObjectOutputStream.writeObject(this.val$serialNumber);
/*     */       }
/*     */ 
/*     */       public Object readResponse(ObjectInputStream paramObjectInputStream) throws IOException {
/* 104 */         long l = paramObjectInputStream.readLong();
/* 105 */         return new Long(l);
/*     */       }
/*     */     });
/* 108 */     long l = ((Long)localObject).longValue();
/* 109 */     return l;
/*     */   }
/*     */ 
/*     */   public long getServerTime(String paramString)
/*     */     throws IOException
/*     */   {
/* 121 */     Object localObject = exchange(paramString, new ObjectExchanger() {
/*     */       public void writeRequest(ObjectOutputStream paramObjectOutputStream) throws IOException {
/* 123 */         paramObjectOutputStream.writeInt(23);
/*     */       }
/*     */ 
/*     */       public Object readResponse(ObjectInputStream paramObjectInputStream) throws IOException {
/* 127 */         long l = paramObjectInputStream.readLong();
/* 128 */         return new Long(l);
/*     */       }
/*     */     });
/* 131 */     return ((Long)localObject).longValue();
/*     */   }
/*     */ 
/*     */   public String getControlCode(String paramString)
/*     */     throws IOException
/*     */   {
/* 143 */     Object localObject = exchange(paramString, new ObjectExchanger() {
/*     */       public void writeRequest(ObjectOutputStream paramObjectOutputStream) throws IOException {
/* 145 */         paramObjectOutputStream.writeInt(27);
/*     */       }
/*     */ 
/*     */       public Object readResponse(ObjectInputStream paramObjectInputStream) throws IOException {
/*     */         try {
/* 150 */           return paramObjectInputStream.readObject(); } catch (ClassNotFoundException localClassNotFoundException) {
/*     */         }
/* 152 */         throw new IOException(localClassNotFoundException.getMessage());
/*     */       }
/*     */     });
/* 156 */     return (String)localObject;
/*     */   }
/*     */ 
/*     */   public void uploadNotification(String paramString1, String paramString2)
/*     */   {
/* 169 */     5 local5 = new Runnable(paramString1, paramString2)
/*     */     {
/*     */       public void run()
/*     */       {
/*     */         try
/*     */         {
/* 176 */           NetHelper.this.exchange(this.val$remoteURL, new NetHelper.ObjectExchanger()
/*     */           {
/*     */             public void writeRequest(ObjectOutputStream paramObjectOutputStream)
/*     */               throws IOException
/*     */             {
/* 184 */               paramObjectOutputStream.writeInt(25);
/* 185 */               paramObjectOutputStream.writeObject(NetHelper.5.this.val$msg);
/*     */             }
/*     */ 
/*     */             public Object readResponse(ObjectInputStream paramObjectInputStream)
/*     */               throws IOException
/*     */             {
/* 196 */               int i = paramObjectInputStream.readInt();
/* 197 */               return new Integer(i);
/*     */             }
/*     */           });
/*     */         }
/*     */         catch (IOException localIOException)
/*     */         {
/*     */         }
/*     */       }
/*     */     };
/* 204 */     Thread localThread = new Thread(local5);
/* 205 */     localThread.setDaemon(true);
/* 206 */     localThread.start();
/*     */   }
/*     */ 
/*     */   public void uploadData(Component paramComponent, String paramString1, String paramString2, long paramLong1, long paramLong2, String paramString3, byte[] paramArrayOfByte, boolean paramBoolean, LogWriter paramLogWriter, EA paramEA)
/*     */     throws UploadFailedException, UploadCancelledException, IllegalStateException
/*     */   {
/* 233 */     ResourceBundle localResourceBundle = DTWApplet.getResourceBundle();
/*     */     ByteArrayOutputStream localByteArrayOutputStream;
/* 236 */     if (paramArrayOfByte != null)
/* 237 */       localByteArrayOutputStream = new ByteArrayOutputStream(paramArrayOfByte.length);
/*     */     else
/* 239 */       localByteArrayOutputStream = new ByteArrayOutputStream(paramString2.getBytes().length);
/*     */     Object localObject2;
/*     */     int i;
/*     */     ByteArrayInputStream localByteArrayInputStream;
/*     */     try
/*     */     {
/* 248 */       ObjectOutputStream localObjectOutputStream = new ObjectOutputStream(localByteArrayOutputStream);
/*     */       try {
/* 250 */         if (paramArrayOfByte != null) {
/* 251 */           localObjectOutputStream.writeInt(21);
/* 252 */           localObjectOutputStream.writeObject(paramString2);
/* 253 */           localObjectOutputStream.writeLong(paramLong1);
/* 254 */           localObjectOutputStream.writeLong(paramLong2);
/* 255 */           localObjectOutputStream.writeObject(paramString3);
/* 256 */           localObjectOutputStream.writeBoolean(paramBoolean);
/* 257 */           localObjectOutputStream.writeInt(paramArrayOfByte.length);
/* 258 */           localObjectOutputStream.write(paramArrayOfByte);
/*     */ 
/* 262 */           if (paramEA == null) {
/* 263 */             localObjectOutputStream.writeBoolean(false);
/*     */           } else {
/* 265 */             localObjectOutputStream.writeBoolean(true);
/* 266 */             writeTraceHistory(localObjectOutputStream, paramEA.C());
/* 267 */             writeTraceHistory(localObjectOutputStream, paramEA.E());
/* 268 */             writeTraceHistory(localObjectOutputStream, paramEA.D());
/* 269 */             writeTraceHistory(localObjectOutputStream, paramEA.B());
/*     */           }
/*     */         } else {
/* 272 */           localObjectOutputStream.writeInt(22);
/* 273 */           localObjectOutputStream.writeObject(paramString2);
/*     */         }
/* 275 */         localObjectOutputStream.flush();
/*     */       } finally {
/* 277 */         localObjectOutputStream.close();
/*     */       }
/* 279 */       localObject2 = localByteArrayOutputStream.toByteArray();
/* 280 */       i = localObject2.length;
/* 281 */       localByteArrayInputStream = new ByteArrayInputStream(localObject2);
/*     */     } catch (IOException localIOException1) {
/* 283 */       localIOException1.printStackTrace(paramLogWriter);
/* 284 */       throw new UploadFailedException(localResourceBundle.getString("error.MSG_UPLOAD_IO"));
/*     */     }URLConnection localURLConnection;
/*     */     Object localObject3;
/*     */     Object localObject4;
/*     */     try { localObject2 = new URL(paramString1);
/* 292 */       localURLConnection = ((URL)localObject2).openConnection();
/* 293 */       localURLConnection.setRequestProperty("Content-Type", "application/octet-stream");
/* 294 */       localURLConnection.setDoOutput(true);
/* 295 */       localURLConnection.setDoInput(true);
/* 296 */       localURLConnection.setUseCaches(false);
/* 297 */       localObject3 = localURLConnection.getOutputStream();
/*     */ 
/* 299 */       localObject4 = new ProgressMonitorInputStream(paramComponent, localResourceBundle.getString("wizard.upload.sending"), localByteArrayInputStream);
/*     */ 
/* 301 */       ProgressMonitor localProgressMonitor = ((ProgressMonitorInputStream)localObject4).getProgressMonitor();
/* 302 */       localProgressMonitor.setMaximum(i);
/*     */ 
/* 304 */       writeToStream((InputStream)localObject4, (OutputStream)localObject3);
/*     */     } catch (InterruptedIOException localInterruptedIOException) {
/* 306 */       localInterruptedIOException.printStackTrace(paramLogWriter);
/* 307 */       throw new UploadCancelledException(localResourceBundle.getString("error.MSG_UPLOAD_CANCELLED"));
/*     */     } catch (IOException localIOException2) {
/* 309 */       localIOException2.printStackTrace(paramLogWriter);
/* 310 */       throw new UploadFailedException(localResourceBundle.getString("error.MSG_UPLOAD_IO_SEND"));
/*     */     }
/*     */ 
/*     */     int j;
/*     */     try
/*     */     {
/* 320 */       localObject3 = localURLConnection.getInputStream();
/* 321 */       localObject4 = new ObjectInputStream((InputStream)localObject3);
/*     */       try {
/* 323 */         j = ((ObjectInputStream)localObject4).readInt();
/*     */       } finally {
/* 325 */         ((ObjectInputStream)localObject4).close();
/*     */       }
/*     */     } catch (IOException localIOException3) {
/* 328 */       localIOException3.printStackTrace(paramLogWriter);
/* 329 */       throw new UploadFailedException(localResourceBundle.getString("error.MSG_UPLOAD_IO_RESPONSE"));
/*     */     }
/*     */ 
/* 333 */     if (paramArrayOfByte == null) {
/* 334 */       return;
/*     */     }
/*     */ 
/* 337 */     switch (j) {
/*     */     case 46:
/* 339 */       return;
/*     */     case 49:
/* 341 */       throw new UploadFailedException(localResourceBundle.getString("error.MSG_RESPONSE_TRANSMIT"));
/*     */     case 47:
/* 343 */       throw new UploadFailedException(localResourceBundle.getString("error.MSG_RESPONSE_SERVER"));
/*     */     case 48:
/* 345 */       throw new UploadFailedException(localResourceBundle.getString("error.MSG_RESPONSE_CLIENT"));
/*     */     }
/* 347 */     throw new UploadFailedException(localResourceBundle.getString("error.MSG_RESPONSE"));
/*     */   }
/*     */ 
/*     */   public void uploadData(Component paramComponent, String paramString1, String paramString2, LogWriter paramLogWriter)
/*     */     throws UploadFailedException, UploadCancelledException
/*     */   {
/* 363 */     uploadData(paramComponent, paramString1, paramString2, 0L, 0L, null, null, false, paramLogWriter, null);
/*     */   }
/*     */ 
/*     */   private void writeTraceHistory(ObjectOutputStream paramObjectOutputStream, EA._A param_A)
/*     */     throws IOException
/*     */   {
/* 375 */     paramObjectOutputStream.writeObject(param_A.A());
/* 376 */     paramObjectOutputStream.writeInt(param_A.B().length);
/* 377 */     paramObjectOutputStream.write(param_A.B());
/*     */   }
/*     */ 
/*     */   private Object exchange(String paramString, ObjectExchanger paramObjectExchanger)
/*     */     throws IOException
/*     */   {
/* 390 */     URL localURL = new URL(paramString);
/* 391 */     URLConnection localURLConnection = localURL.openConnection();
/* 392 */     localURLConnection.setRequestProperty("Content-Type", "application/octet-stream");
/* 393 */     localURLConnection.setDoOutput(true);
/* 394 */     localURLConnection.setDoInput(true);
/* 395 */     localURLConnection.setUseCaches(false);
/* 396 */     OutputStream localOutputStream = localURLConnection.getOutputStream();
/* 397 */     ObjectOutputStream localObjectOutputStream = new ObjectOutputStream(localOutputStream);
/*     */     try {
/* 399 */       paramObjectExchanger.writeRequest(localObjectOutputStream);
/* 400 */       localObjectOutputStream.flush();
/*     */     } finally {
/* 402 */       localObjectOutputStream.close(); } 
/*     */ InputStream localInputStream = localURLConnection.getInputStream();
/* 406 */     ObjectInputStream localObjectInputStream = new ObjectInputStream(localInputStream);
/*     */     Object localObject2;
/*     */     try { localObject2 = paramObjectExchanger.readResponse(localObjectInputStream);
/*     */     } finally {
/* 411 */       localObjectInputStream.close();
/*     */     }
/* 413 */     return localObject2;
/*     */   }
/*     */ 
/*     */   private static abstract interface ObjectExchanger
/*     */   {
/*     */     public abstract void writeRequest(ObjectOutputStream paramObjectOutputStream)
/*     */       throws IOException;
/*     */ 
/*     */     public abstract Object readResponse(ObjectInputStream paramObjectInputStream)
/*     */       throws IOException;
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.NetHelper
 * JD-Core Version:    0.6.0
 */