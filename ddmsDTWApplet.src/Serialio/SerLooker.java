/*     */ package Serialio;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Vector;
/*     */ 
/*     */ public class SerLooker
/*     */ {
/*     */   protected SerialPort sp;
/*  37 */   private boolean saveData = false;
/*  38 */   private boolean abortReq = false;
/*  39 */   private int rcvCount = 0;
/*  40 */   private int abortCheckPeriod = -1;
/*  41 */   private int napTime = 20;
/*  42 */   private boolean saveBufReset = true;
/*  43 */   private boolean looking = false;
/*  44 */   private boolean sightedItemInList = false;
/*  45 */   private boolean showBytesAsRecvd = false;
/*  46 */   private boolean saveDataBeforeMatch = false;
/*  47 */   private Vector LookForList = new Vector();
/*  48 */   private int ringIndex = 0;
/*  49 */   private byte[] rdBuf = new byte[256];
/*     */   private byte[] saveBuf;
/*     */   private byte[] dataItemSighted;
/*     */   private byte lastByte;
/*     */ 
/*     */   public SerLooker()
/*     */   {
/*  59 */     this.saveData = false;
/*     */   }
/*     */ 
/*     */   public SerLooker(SerialPort paramSerialPort)
/*     */   {
/*  68 */     this.sp = paramSerialPort;
/*  69 */     this.saveData = false;
/*     */   }
/*     */ 
/*     */   public SerLooker(SerialPort paramSerialPort, int paramInt)
/*     */   {
/*  80 */     this.sp = paramSerialPort;
/*  81 */     if (paramInt > 0) {
/*  82 */       this.saveBuf = new byte[paramInt];
/*  83 */       this.saveData = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   private void addLookFor(String paramString, long paramLong, boolean paramBoolean)
/*     */   {
/*  95 */     addLookFor(paramString.getBytes(), paramLong, paramBoolean);
/*     */   }
/*     */ 
/*     */   private void addLookFor(byte[] paramArrayOfByte, long paramLong, boolean paramBoolean)
/*     */   {
/* 110 */     LookFor localLookFor = new LookFor();
/*     */ 
/* 112 */     localLookFor.data = paramArrayOfByte;
/* 113 */     localLookFor.compareNdx = 0;
/* 114 */     this.LookForList.addElement(localLookFor);
/*     */ 
/* 117 */     this.looking = true;
/*     */   }
/*     */ 
/*     */   public boolean waitFor(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/*     */     throws IOException
/*     */   {
/* 131 */     if (this.sp.getPortNum() == -1) {
/* 132 */       throw new IOException("Port not open");
/*     */     }
/* 134 */     int i = this.sp.getTimeoutRx();
/* 135 */     if (i > 0) {
/* 136 */       if (this.sp.rxReadyCount() > 0) {
/* 137 */         System.out.println("Warning: data in RX queue may have been lost");
/*     */       }
/* 139 */       this.sp.setTimeoutRx(0);
/*     */     }
/* 141 */     if (paramArrayOfByte != null) this.sp.putData(paramArrayOfByte, 0, paramArrayOfByte.length);
/*     */ 
/* 143 */     long l1 = System.currentTimeMillis();
/* 144 */     long l2 = l1 + paramInt2;
/* 145 */     long l3 = 0L;
/*     */     do {
/* 147 */       if (this.sp.rxReadyCount() >= paramInt1) return true; try {
/* 148 */         Thread.sleep(this.napTime); } catch (InterruptedException localInterruptedException) {
/* 149 */       }l3 = System.currentTimeMillis();
/* 150 */       if (this.abortReq) {
/* 151 */         this.sp.setTimeoutRx(i);
/* 152 */         return false;
/*     */       }
/*     */     }
/* 154 */     while (l3 < l2);
/*     */ 
/* 156 */     this.sp.setTimeoutRx(i);
/* 157 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean waitFor(String paramString1, String paramString2, int paramInt)
/*     */     throws IOException
/*     */   {
/* 173 */     if (paramString1 == null) paramString1 = "";
/* 174 */     return waitFor(paramString1.getBytes(), paramString2.getBytes(), paramInt);
/*     */   }
/*     */ 
/*     */   public boolean waitFor(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt)
/*     */     throws IOException
/*     */   {
/* 190 */     Vector localVector = new Vector();
/* 191 */     localVector.addElement(paramArrayOfByte2);
/* 192 */     return waitFor(paramArrayOfByte1, localVector, paramInt);
/*     */   }
/*     */ 
/*     */   public boolean waitFor(String paramString, Vector paramVector, int paramInt)
/*     */     throws IOException
/*     */   {
/* 206 */     if (paramString == null) paramString = "";
/* 207 */     return waitFor(paramString.getBytes(), paramVector, paramInt);
/*     */   }
/*     */ 
/*     */   public boolean waitFor(byte[] paramArrayOfByte, Vector paramVector, int paramInt)
/*     */     throws IOException
/*     */   {
/* 224 */     if (this.sp.getPortNum() == -1) {
/* 225 */       throw new IOException("Port not open");
/*     */     }
/* 227 */     for (int i = 0; i < paramVector.size(); i++) {
/* 228 */       if ((paramVector.elementAt(i) instanceof String))
/* 229 */         addLookFor((String)paramVector.elementAt(i), paramInt, false);
/* 230 */       else if ((paramVector.elementAt(i) instanceof byte[]))
/* 231 */         addLookFor((byte[])(byte[])paramVector.elementAt(i), paramInt, false);
/*     */       else
/* 233 */         throw new IOException("waitFor elements must be String or byte[]");
/*     */     }
/* 235 */     i = this.sp.getTimeoutRx();
/* 236 */     if (i > 0) {
/* 237 */       if (this.sp.rxReadyCount() > 0) {
/* 238 */         System.out.println("Warning: data in RX queue may have been lost");
/*     */       }
/* 240 */       this.sp.setTimeoutRx(0);
/*     */     }
/* 242 */     if (paramArrayOfByte != null) this.sp.putData(paramArrayOfByte, 0, paramArrayOfByte.length);
/*     */ 
/* 244 */     if (this.saveBufReset) this.rcvCount = (this.ringIndex = 0);
/* 245 */     long l1 = System.currentTimeMillis();
/* 246 */     long l2 = l1 + paramInt;
/* 247 */     long l3 = 0L;
/* 248 */     this.sightedItemInList = false;
/*     */     do
/*     */     {
/* 251 */       if (this.sp.rxReadyCount() > 0) {
/* 252 */         int j = this.sp.getData(this.rdBuf);
/* 253 */         for (int m = 0; m < j; m++) {
/* 254 */           this.rcvCount += 1;
/* 255 */           int k = this.rdBuf[m];
/* 256 */           if (isDataMatch((byte)k)) {
/* 257 */             this.LookForList.removeAllElements();
/* 258 */             if (!this.saveDataBeforeMatch)
/*     */             {
/* 260 */               m++; this.rcvCount = (j - m);
/* 261 */               for (int n = 0; n < this.rcvCount; n++)
/* 262 */                 this.saveBuf[n] = this.rdBuf[(m++)];
/*     */             }
/* 264 */             return true;
/*     */           }
/*     */         }
/*     */       }
/*     */       try {
/* 268 */         Thread.sleep(this.napTime); } catch (InterruptedException localInterruptedException) {
/* 269 */       }l3 = System.currentTimeMillis();
/* 270 */       if (this.abortReq) {
/* 271 */         this.sp.setTimeoutRx(i);
/* 272 */         return false;
/*     */       }
/*     */     }
/* 274 */     while (l3 < l2);
/*     */ 
/* 276 */     this.sp.setTimeoutRx(i);
/* 277 */     return false;
/*     */   }
/*     */ 
/*     */   private boolean isDataMatch(byte paramByte)
/*     */   {
/* 283 */     if (this.showBytesAsRecvd) System.out.print(" b=" + paramByte);
/* 284 */     if (this.saveData) {
/* 285 */       this.saveBuf[(this.ringIndex++)] = paramByte;
/* 286 */       if (this.ringIndex == this.saveBuf.length) {
/* 287 */         this.ringIndex = 0;
/*     */       }
/*     */     }
/* 290 */     for (int i = 0; i < this.LookForList.size(); i++) {
/* 291 */       LookFor localLookFor = (LookFor)this.LookForList.elementAt(i);
/* 292 */       if (this.lastByte != paramByte) localLookFor.duplicateData = false;
/* 293 */       if (localLookFor.data[localLookFor.compareNdx] == paramByte)
/*     */       {
/* 295 */         if ((localLookFor.compareNdx == 1) && (this.lastByte == paramByte))
/* 296 */           localLookFor.duplicateData = true;
/* 297 */         localLookFor.compareNdx += 1;
/* 298 */         if (localLookFor.compareNdx == localLookFor.data.length) {
/* 299 */           localLookFor.compareNdx = 0;
/* 300 */           this.lastByte = paramByte;
/* 301 */           return true;
/*     */         }
/*     */ 
/*     */       }
/* 306 */       else if (!localLookFor.duplicateData) {
/* 307 */         localLookFor.compareNdx = 0;
/* 308 */         if (localLookFor.data[localLookFor.compareNdx] == paramByte) {
/* 309 */           localLookFor.compareNdx += 1;
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 314 */     this.lastByte = paramByte;
/* 315 */     return false;
/*     */   }
/*     */ 
/*     */   public void setSaveData(int paramInt)
/*     */   {
/* 323 */     if (paramInt == 0) {
/* 324 */       this.saveData = false;
/*     */     }
/*     */     else {
/* 327 */       this.saveBuf = new byte[paramInt];
/* 328 */       this.saveData = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setNapTime(int paramInt)
/*     */   {
/* 340 */     this.napTime = paramInt;
/*     */   }
/*     */ 
/*     */   public int getNapTime()
/*     */   {
/* 346 */     return this.napTime;
/*     */   }
/*     */ 
/*     */   public int getRcvCount()
/*     */   {
/* 353 */     return this.rcvCount;
/*     */   }
/*     */ 
/*     */   public void abort()
/*     */   {
/* 358 */     this.abortReq = true;
/*     */   }
/*     */ 
/*     */   public void setSerialPort(SerialPort paramSerialPort)
/*     */   {
/* 364 */     this.sp = paramSerialPort;
/*     */   }
/*     */ 
/*     */   public SerialPort getSerialPort(SerialPort paramSerialPort)
/*     */   {
/* 369 */     return paramSerialPort;
/*     */   }
/*     */ 
/*     */   public void setAbortCheck(int paramInt)
/*     */   {
/* 375 */     this.abortCheckPeriod = paramInt;
/*     */   }
/*     */ 
/*     */   public int getAbortCheck()
/*     */   {
/* 380 */     return this.abortCheckPeriod;
/*     */   }
/*     */ 
/*     */   public void setSaveReset(boolean paramBoolean)
/*     */   {
/* 388 */     this.saveBufReset = paramBoolean;
/*     */   }
/*     */ 
/*     */   public boolean getSaveReset()
/*     */   {
/* 393 */     return this.saveBufReset;
/*     */   }
/*     */ 
/*     */   public byte[] getDataItemSighted()
/*     */   {
/* 399 */     return this.dataItemSighted;
/*     */   }
/*     */ 
/*     */   public void setShowBytesAsReceived(boolean paramBoolean)
/*     */   {
/* 405 */     this.showBytesAsRecvd = paramBoolean;
/*     */   }
/*     */ 
/*     */   public void setSaveDataBeforeMatch(boolean paramBoolean)
/*     */   {
/* 418 */     this.saveDataBeforeMatch = paramBoolean;
/*     */   }
/*     */ 
/*     */   public byte[] getData()
/*     */   {
/*     */     byte[] arrayOfByte;
/*     */     int i;
/* 427 */     if (this.rcvCount <= this.saveBuf.length) {
/* 428 */       arrayOfByte = new byte[this.rcvCount];
/* 429 */       for (i = 0; i < this.rcvCount; i++)
/* 430 */         arrayOfByte[i] = this.saveBuf[i];
/*     */     }
/*     */     else {
/* 433 */       arrayOfByte = new byte[this.saveBuf.length];
/* 434 */       i = this.ringIndex;
/* 435 */       if (i < 0) i = this.saveBuf.length - 1;
/* 436 */       for (int j = 0; j < this.saveBuf.length; j++) {
/* 437 */         arrayOfByte[j] = this.saveBuf[(i++)];
/* 438 */         if (i != this.saveBuf.length) continue; i = 0;
/*     */       }
/*     */     }
/*     */ 
/* 442 */     return arrayOfByte;
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     Serialio.SerLooker
 * JD-Core Version:    0.6.0
 */