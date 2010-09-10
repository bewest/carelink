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
/*     */ import minimed.ddms.deviceportreader.TraceHistorySet;
/*     */ import minimed.ddms.deviceportreader.TraceHistorySet.TraceHistory;
/*     */ 
/*     */ public final class NetHelper extends IOHelper
/*     */ {
/*     */   private static final int REQ_STORE_SNAPSHOT = 21;
/*     */   private static final int REQ_STORE_LOG = 22;
/*     */   private static final int REQ_GET_SERVER_TIME = 23;
/*     */   private static final int REQ_GET_LAST_PUMP_HISTORY_PAGE_READ = 24;
/*     */   private static final int REQ_GET_LAST_GLUCOSE_HISTORY_PAGE_READ = 26;
/*     */   private static final int REQ_NOTIFY = 25;
/*     */   private static final int RSP_OK = 46;
/*     */   private static final int RSP_SERVER_ERROR = 47;
/*     */   private static final int RSP_CLIENT_ERROR = 48;
/*     */   private static final int RSP_TRANSMIT_ERROR = 49;
/*     */ 
/*     */   public long getLastGlucoseHistoryPageRead(String paramString1, String paramString2, String paramString3)
/*     */     throws IOException
/*     */   {
/*  66 */     Object localObject = exchange(paramString1, new ObjectExchanger(paramString2, paramString3) { private final String val$model;
/*     */       private final String val$serialNumber;
/*     */ 
/*  68 */       public void writeRequest(ObjectOutputStream paramObjectOutputStream) throws IOException { paramObjectOutputStream.writeInt(26);
/*  69 */         paramObjectOutputStream.writeObject(this.val$model);
/*  70 */         paramObjectOutputStream.writeObject(this.val$serialNumber); }
/*     */ 
/*     */       public Object readResponse(ObjectInputStream paramObjectInputStream) throws IOException
/*     */       {
/*  74 */         long l = paramObjectInputStream.readLong();
/*  75 */         return new Long(l);
/*     */       }
/*     */     });
/*  78 */     long l = ((Long)localObject).longValue();
/*  79 */     return l;
/*     */   }
/*     */ 
/*     */   public long getLastPumpHistoryPageRead(String paramString1, String paramString2, String paramString3)
/*     */     throws IOException
/*     */   {
/*  95 */     Object localObject = exchange(paramString1, new ObjectExchanger(paramString2, paramString3) { private final String val$model;
/*     */       private final String val$serialNumber;
/*     */ 
/*  97 */       public void writeRequest(ObjectOutputStream paramObjectOutputStream) throws IOException { paramObjectOutputStream.writeInt(24);
/*  98 */         paramObjectOutputStream.writeObject(this.val$model);
/*  99 */         paramObjectOutputStream.writeObject(this.val$serialNumber); }
/*     */ 
/*     */       public Object readResponse(ObjectInputStream paramObjectInputStream) throws IOException
/*     */       {
/* 103 */         long l = paramObjectInputStream.readLong();
/* 104 */         return new Long(l);
/*     */       }
/*     */     });
/* 107 */     long l = ((Long)localObject).longValue();
/* 108 */     return l;
/*     */   }
/*     */ 
/*     */   public long getServerTime(String paramString)
/*     */     throws IOException
/*     */   {
/* 120 */     Object localObject = exchange(paramString, new ObjectExchanger() {
/*     */       public void writeRequest(ObjectOutputStream paramObjectOutputStream) throws IOException {
/* 122 */         paramObjectOutputStream.writeInt(23);
/*     */       }
/*     */ 
/*     */       public Object readResponse(ObjectInputStream paramObjectInputStream) throws IOException {
/* 126 */         long l = paramObjectInputStream.readLong();
/* 127 */         return new Long(l);
/*     */       }
/*     */     });
/* 130 */     return ((Long)localObject).longValue();
/*     */   }
/*     */ 
/*     */   public void uploadNotification(String paramString1, String paramString2)
/*     */   {
/* 143 */     4 local4 = new Runnable(paramString1, paramString2) {
/*     */       private final String val$remoteURL;
/*     */       private final String val$msg;
/*     */ 
/*     */       public void run() {
/*     */         try { NetHelper.this.exchange(this.val$remoteURL, new NetHelper.5(this));
/*     */         }
/*     */         catch (IOException localIOException)
/*     */         {
/*     */         }
/*     */       }
/*     */     };
/* 178 */     Thread localThread = new Thread(local4);
/* 179 */     localThread.setDaemon(true);
/* 180 */     localThread.start();
/*     */   }
/*     */ 
/*     */   public void uploadData(Component paramComponent, String paramString1, String paramString2, long paramLong1, long paramLong2, String paramString3, byte[] paramArrayOfByte, boolean paramBoolean, LogWriter paramLogWriter, TraceHistorySet paramTraceHistorySet)
/*     */     throws UploadFailedException, UploadCancelledException, IllegalStateException
/*     */   {
/* 207 */     ResourceBundle localResourceBundle = DTWApplet.getResourceBundle();
/*     */     ByteArrayOutputStream localByteArrayOutputStream;
/* 210 */     if (paramArrayOfByte != null)
/* 211 */       localByteArrayOutputStream = new ByteArrayOutputStream(paramArrayOfByte.length);
/*     */     else
/* 213 */       localByteArrayOutputStream = new ByteArrayOutputStream(paramString2.getBytes().length);
/*     */     Object localObject2;
/*     */     int i;
/*     */     ByteArrayInputStream localByteArrayInputStream;
/*     */     try
/*     */     {
/* 222 */       ObjectOutputStream localObjectOutputStream = new ObjectOutputStream(localByteArrayOutputStream);
/*     */       try {
/* 224 */         if (paramArrayOfByte != null) {
/* 225 */           localObjectOutputStream.writeInt(21);
/* 226 */           localObjectOutputStream.writeObject(paramString2);
/* 227 */           localObjectOutputStream.writeLong(paramLong1);
/* 228 */           localObjectOutputStream.writeLong(paramLong2);
/* 229 */           localObjectOutputStream.writeObject(paramString3);
/* 230 */           localObjectOutputStream.writeBoolean(paramBoolean);
/* 231 */           localObjectOutputStream.writeInt(paramArrayOfByte.length);
/* 232 */           localObjectOutputStream.write(paramArrayOfByte);
/*     */ 
/* 236 */           if (paramTraceHistorySet == null) {
/* 237 */             localObjectOutputStream.writeBoolean(false);
/*     */           } else {
/* 239 */             localObjectOutputStream.writeBoolean(true);
/* 240 */             writeTraceHistory(localObjectOutputStream, paramTraceHistorySet.getPumpTraceHistory());
/* 241 */             writeTraceHistory(localObjectOutputStream, paramTraceHistorySet.getDetailTraceHistory());
/* 242 */             writeTraceHistory(localObjectOutputStream, paramTraceHistorySet.getNewAlarmTraceHistory());
/* 243 */             writeTraceHistory(localObjectOutputStream, paramTraceHistorySet.getOldAlarmTraceHistory());
/*     */           }
/*     */         } else {
/* 246 */           localObjectOutputStream.writeInt(22);
/* 247 */           localObjectOutputStream.writeObject(paramString2);
/*     */         }
/* 249 */         localObjectOutputStream.flush();
/*     */       } finally {
/* 251 */         localObjectOutputStream.close();
/*     */       }
/* 253 */       localObject2 = localByteArrayOutputStream.toByteArray();
/* 254 */       i = localObject2.length;
/* 255 */       localByteArrayInputStream = new ByteArrayInputStream(localObject2);
/*     */     } catch (IOException localIOException1) {
/* 257 */       localIOException1.printStackTrace(paramLogWriter);
/* 258 */       throw new UploadFailedException(localResourceBundle.getString("error.MSG_UPLOAD_IO"));
/*     */     }URLConnection localURLConnection;
/*     */     Object localObject3;
/*     */     Object localObject4;
/*     */     try { localObject2 = new URL(paramString1);
/* 266 */       localURLConnection = ((URL)localObject2).openConnection();
/* 267 */       localURLConnection.setRequestProperty("Content-Type", "application/octet-stream");
/* 268 */       localURLConnection.setDoOutput(true);
/* 269 */       localURLConnection.setDoInput(true);
/* 270 */       localURLConnection.setUseCaches(false);
/* 271 */       localObject3 = localURLConnection.getOutputStream();
/*     */ 
/* 273 */       localObject4 = new ProgressMonitorInputStream(paramComponent, localResourceBundle.getString("wizard.upload.sending"), localByteArrayInputStream);
/*     */ 
/* 275 */       ProgressMonitor localProgressMonitor = ((ProgressMonitorInputStream)localObject4).getProgressMonitor();
/* 276 */       localProgressMonitor.setMaximum(i);
/*     */ 
/* 278 */       writeToStream((InputStream)localObject4, (OutputStream)localObject3);
/*     */     } catch (InterruptedIOException localInterruptedIOException) {
/* 280 */       localInterruptedIOException.printStackTrace(paramLogWriter);
/* 281 */       throw new UploadCancelledException(localResourceBundle.getString("error.MSG_UPLOAD_CANCELLED"));
/*     */     } catch (IOException localIOException2) {
/* 283 */       localIOException2.printStackTrace(paramLogWriter);
/* 284 */       throw new UploadFailedException(localResourceBundle.getString("error.MSG_UPLOAD_IO_SEND"));
/*     */     }
/*     */ 
/*     */     int j;
/*     */     try
/*     */     {
/* 294 */       localObject3 = localURLConnection.getInputStream();
/* 295 */       localObject4 = new ObjectInputStream((InputStream)localObject3);
/*     */       try {
/* 297 */         j = ((ObjectInputStream)localObject4).readInt();
/*     */       } finally {
/* 299 */         ((ObjectInputStream)localObject4).close();
/*     */       }
/*     */     } catch (IOException localIOException3) {
/* 302 */       localIOException3.printStackTrace(paramLogWriter);
/* 303 */       throw new UploadFailedException(localResourceBundle.getString("error.MSG_UPLOAD_IO_RESPONSE"));
/*     */     }
/*     */ 
/* 307 */     if (paramArrayOfByte == null) {
/* 308 */       return;
/*     */     }
/*     */ 
/* 311 */     switch (j) {
/*     */     case 46:
/* 313 */       return;
/*     */     case 49:
/* 315 */       throw new UploadFailedException(localResourceBundle.getString("error.MSG_RESPONSE_TRANSMIT"));
/*     */     case 47:
/* 317 */       throw new UploadFailedException(localResourceBundle.getString("error.MSG_RESPONSE_SERVER"));
/*     */     case 48:
/* 319 */       throw new UploadFailedException(localResourceBundle.getString("error.MSG_RESPONSE_CLIENT"));
/*     */     }
/* 321 */     throw new UploadFailedException(localResourceBundle.getString("error.MSG_RESPONSE"));
/*     */   }
/*     */ 
/*     */   public void uploadData(Component paramComponent, String paramString1, String paramString2, LogWriter paramLogWriter)
/*     */     throws UploadFailedException, UploadCancelledException
/*     */   {
/* 337 */     uploadData(paramComponent, paramString1, paramString2, 0L, 0L, null, null, false, paramLogWriter, null);
/*     */   }
/*     */ 
/*     */   private void writeTraceHistory(ObjectOutputStream paramObjectOutputStream, TraceHistorySet.TraceHistory paramTraceHistory)
/*     */     throws IOException
/*     */   {
/* 349 */     paramObjectOutputStream.writeObject(paramTraceHistory.getFileID());
/* 350 */     paramObjectOutputStream.writeInt(paramTraceHistory.getData().length);
/* 351 */     paramObjectOutputStream.write(paramTraceHistory.getData());
/*     */   }
/*     */ 
/*     */   private Object exchange(String paramString, ObjectExchanger paramObjectExchanger)
/*     */     throws IOException
/*     */   {
/* 364 */     URL localURL = new URL(paramString);
/* 365 */     URLConnection localURLConnection = localURL.openConnection();
/* 366 */     localURLConnection.setRequestProperty("Content-Type", "application/octet-stream");
/* 367 */     localURLConnection.setDoOutput(true);
/* 368 */     localURLConnection.setDoInput(true);
/* 369 */     localURLConnection.setUseCaches(false);
/* 370 */     OutputStream localOutputStream = localURLConnection.getOutputStream();
/* 371 */     ObjectOutputStream localObjectOutputStream = new ObjectOutputStream(localOutputStream);
/*     */     try {
/* 373 */       paramObjectExchanger.writeRequest(localObjectOutputStream);
/* 374 */       localObjectOutputStream.flush();
/*     */     } finally {
/* 376 */       localObjectOutputStream.close(); } 
/*     */ InputStream localInputStream = localURLConnection.getInputStream();
/* 380 */     ObjectInputStream localObjectInputStream = new ObjectInputStream(localInputStream);
/*     */     Object localObject2;
/*     */     try { localObject2 = paramObjectExchanger.readResponse(localObjectInputStream);
/*     */     } finally {
/* 385 */       localObjectInputStream.close();
/*     */     }
/* 387 */     return localObject2;
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