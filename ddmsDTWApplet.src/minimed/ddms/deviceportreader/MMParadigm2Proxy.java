/*     */ package minimed.ddms.deviceportreader;
/*     */ 
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.Date;
/*     */ 
/*     */ class MMParadigm2Proxy extends MedicalDevice
/*     */ {
/*     */   private MMX12 m_pumpDetector;
/*     */   private MMX12 m_pumpInstance;
/*     */   private MMX12 m_currentPump;
/*     */ 
/*     */   MMParadigm2Proxy(int paramInt)
/*     */   {
/*  61 */     this.m_linkDevice = paramInt;
/*     */ 
/*  64 */     this.m_pumpDetector = new MMX12(paramInt);
/*  65 */     setCurrentPump(this.m_pumpDetector);
/*     */   }
/*     */ 
/*     */   public void readClock(DeviceListener paramDeviceListener, int paramInt, String paramString)
/*     */     throws BadDeviceCommException, BadDeviceValueException, IOException, ConnectToPumpException
/*     */   {
/*  90 */     this.m_pumpDetector.readClock(paramDeviceListener, paramInt, paramString);
/*  91 */     setCurrentPump(this.m_pumpDetector);
/*     */   }
/*     */ 
/*     */   public void readData(DeviceListener paramDeviceListener, int paramInt, String paramString, boolean paramBoolean)
/*     */     throws BadDeviceCommException, BadDeviceValueException, IOException, ConnectToPumpException
/*     */   {
/* 117 */     this.m_pumpInstance = createPumpInstance(paramDeviceListener, paramInt, paramString);
/* 118 */     setCurrentPump(this.m_pumpInstance);
/*     */ 
/* 121 */     this.m_pumpInstance.readData(paramDeviceListener, paramInt, paramString, false, paramBoolean);
/*     */   }
/*     */ 
/*     */   public void readData(DeviceListener paramDeviceListener, int paramInt, String paramString)
/*     */     throws BadDeviceCommException, BadDeviceValueException, IOException, ConnectToPumpException
/*     */   {
/* 145 */     readData(paramDeviceListener, paramInt, paramString, false);
/*     */   }
/*     */ 
/*     */   public void readGlucoseDataRange(DeviceListener paramDeviceListener, int paramInt, String paramString, Date paramDate1, Date paramDate2)
/*     */     throws BadDeviceCommException, BadDeviceValueException, IOException, ConnectToPumpException
/*     */   {
/* 180 */     this.m_pumpInstance = createPumpInstance(paramDeviceListener, paramInt, paramString);
/* 181 */     setCurrentPump(this.m_pumpInstance);
/*     */ 
/* 184 */     this.m_pumpInstance.readGlucoseDataRange(paramDeviceListener, paramInt, paramString, paramDate1, paramDate2);
/*     */   }
/*     */ 
/*     */   public void readIsigDataRange(DeviceListener paramDeviceListener, int paramInt, String paramString, Date paramDate1, Date paramDate2)
/*     */     throws BadDeviceCommException, BadDeviceValueException, IOException, ConnectToPumpException
/*     */   {
/* 220 */     this.m_pumpInstance = createPumpInstance(paramDeviceListener, paramInt, paramString);
/* 221 */     setCurrentPump(this.m_pumpInstance);
/*     */ 
/* 224 */     this.m_pumpInstance.readIsigDataRange(paramDeviceListener, paramInt, paramString, paramDate1, paramDate2);
/*     */   }
/*     */ 
/*     */   public InputStream createSnapshot()
/*     */     throws BadDeviceValueException, IOException
/*     */   {
/* 242 */     InputStream localInputStream = this.m_pumpInstance.m_snapshotCreator.createSnapshot();
/* 243 */     setCurrentPump(this.m_pumpInstance);
/* 244 */     return localInputStream;
/*     */   }
/*     */ 
/*     */   public final Date getClock()
/*     */   {
/* 253 */     return getCurrentPump().getClock();
/*     */   }
/*     */ 
/*     */   public final String getDescription()
/*     */   {
/* 262 */     return getCurrentPump().getDescription();
/*     */   }
/*     */ 
/*     */   public String getModelNumber()
/*     */   {
/* 271 */     return getCurrentPump().getModelNumber();
/*     */   }
/*     */ 
/*     */   public final String getLastCommandDescription()
/*     */   {
/* 280 */     return getCurrentPump().getLastCommandDescription();
/*     */   }
/*     */ 
/*     */   public long getLastHistoryPageNumber()
/*     */   {
/* 291 */     return getCurrentPump().getLastHistoryPageNumber();
/*     */   }
/*     */ 
/*     */   public long getLastGlucoseHistoryPageNumber()
/*     */   {
/* 302 */     return getCurrentPump().getLastGlucoseHistoryPageNumber();
/*     */   }
/*     */ 
/*     */   public TraceHistorySet getTraceHistorySet()
/*     */   {
/* 314 */     return getCurrentPump().getTraceHistorySet();
/*     */   }
/*     */ 
/*     */   public final synchronized int getPhase()
/*     */   {
/* 323 */     return getCurrentPump().getPhase();
/*     */   }
/*     */ 
/*     */   public final String getPhaseText()
/*     */   {
/* 332 */     return getCurrentPump().getPhaseText();
/*     */   }
/*     */ 
/*     */   public String getSerialNumber()
/*     */   {
/* 341 */     return getCurrentPump().getSerialNumber();
/*     */   }
/*     */ 
/*     */   public int getSerialPortNumber()
/*     */   {
/* 350 */     return getCurrentPump().getSerialPortNumber();
/*     */   }
/*     */ 
/*     */   public final int getSnapshotVersion()
/*     */   {
/* 359 */     return getCurrentPump().getSnapshotVersion();
/*     */   }
/*     */ 
/*     */   public void storeSnapshot(String paramString)
/*     */     throws FileNotFoundException, IOException
/*     */   {
/* 373 */     getCurrentPump().storeSnapshot(paramString);
/*     */   }
/*     */ 
/*     */   int getDeviceType()
/*     */   {
/* 382 */     return 1;
/*     */   }
/*     */ 
/*     */   void findDevice(DeviceListener paramDeviceListener)
/*     */     throws BadDeviceCommException, IOException
/*     */   {
/* 390 */     this.m_pumpDetector.findDevice(paramDeviceListener);
/*     */   }
/*     */ 
/*     */   void initSerialPort(int paramInt)
/*     */     throws IOException
/*     */   {
/* 397 */     this.m_pumpDetector.initSerialPort(paramInt);
/*     */   }
/*     */ 
/*     */   void shutDownSerialPort()
/*     */     throws IOException
/*     */   {
/* 404 */     this.m_pumpDetector.shutDownSerialPort();
/*     */   }
/*     */ 
/*     */   private MMX12 createPumpInstance(DeviceListener paramDeviceListener, int paramInt, String paramString)
/*     */     throws BadDeviceCommException, BadDeviceValueException, IOException, ConnectToPumpException
/*     */   {
/* 427 */     logInfo(this, "createPumpInstance: determining Paradigm2 pump model...");
/*     */ 
/* 431 */     String str = this.m_pumpDetector.readModelNumber(paramDeviceListener, paramInt, paramString);
/*     */ 
/* 433 */     logInfo(this, "createPumpInstance: Paradigm2 pump model is " + str);
/*     */     Object localObject;
/* 438 */     if (MMX12.isModelNumberSupported(str)) {
/* 439 */       localObject = new MMX12(this.m_linkDevice);
/* 440 */     } else if (MMX15.isModelNumberSupported(str))
/*     */     {
/* 442 */       this.m_lastHistoryPageNumber = paramDeviceListener.getLastHistoryPageNumber(str, paramString);
/*     */ 
/* 444 */       localObject = new MMX15(this.m_linkDevice, this.m_lastHistoryPageNumber);
/* 445 */     } else if (MMX22.isModelNumberSupported(str))
/*     */     {
/* 448 */       this.m_lastHistoryPageNumber = paramDeviceListener.getLastHistoryPageNumber(str, paramString);
/*     */ 
/* 450 */       this.m_lastGlucoseHistoryPageNumber = paramDeviceListener.getLastGlucoseHistoryPageNumber(str, paramString);
/*     */ 
/* 452 */       localObject = new MMX22(this.m_linkDevice, this.m_lastHistoryPageNumber, this.m_lastGlucoseHistoryPageNumber);
/*     */     }
/* 454 */     else if (MMGuardian3.isModelNumberSupported(str))
/*     */     {
/* 457 */       this.m_lastHistoryPageNumber = paramDeviceListener.getLastHistoryPageNumber(str, paramString);
/*     */ 
/* 459 */       this.m_lastGlucoseHistoryPageNumber = paramDeviceListener.getLastGlucoseHistoryPageNumber(str, paramString);
/*     */ 
/* 461 */       localObject = new MMGuardian3(this.m_linkDevice, this.m_lastHistoryPageNumber, this.m_lastGlucoseHistoryPageNumber);
/*     */     }
/* 463 */     else if (MMX23.isModelNumberSupported(str))
/*     */     {
/* 466 */       this.m_lastHistoryPageNumber = paramDeviceListener.getLastHistoryPageNumber(str, paramString);
/*     */ 
/* 468 */       this.m_lastGlucoseHistoryPageNumber = paramDeviceListener.getLastGlucoseHistoryPageNumber(str, paramString);
/*     */ 
/* 470 */       localObject = new MMX23(this.m_linkDevice, this.m_lastHistoryPageNumber, this.m_lastGlucoseHistoryPageNumber);
/*     */     }
/* 472 */     else if (MMX53.isModelNumberSupported(str))
/*     */     {
/* 475 */       this.m_lastHistoryPageNumber = paramDeviceListener.getLastHistoryPageNumber(str, paramString);
/*     */ 
/* 477 */       this.m_lastGlucoseHistoryPageNumber = paramDeviceListener.getLastGlucoseHistoryPageNumber(str, paramString);
/*     */ 
/* 479 */       localObject = new MMX53(this.m_linkDevice, this.m_lastHistoryPageNumber, this.m_lastGlucoseHistoryPageNumber);
/*     */     }
/* 481 */     else if (MMX54.isModelNumberSupported(str))
/*     */     {
/* 484 */       this.m_lastHistoryPageNumber = paramDeviceListener.getLastHistoryPageNumber(str, paramString);
/*     */ 
/* 486 */       this.m_lastGlucoseHistoryPageNumber = paramDeviceListener.getLastGlucoseHistoryPageNumber(str, paramString);
/*     */ 
/* 488 */       localObject = new MMX54(this.m_linkDevice, this.m_lastHistoryPageNumber, this.m_lastGlucoseHistoryPageNumber);
/*     */     }
/*     */     else {
/* 491 */       localObject = null;
/* 492 */       throw new ConnectToPumpException("Unsupported Pump Model", 1000, str);
/*     */     }
/*     */ 
/* 498 */     ((MMX12)localObject).m_serialNumber = paramString;
/* 499 */     ((MMX12)localObject).m_modelNumber = str;
/*     */     try
/*     */     {
/* 504 */       paramDeviceListener.allowDeviceOperation((DevicePortReader)localObject);
/*     */     }
/*     */     catch (ConnectToPumpException localConnectToPumpException) {
/* 507 */       forceShutDown();
/* 508 */       throw localConnectToPumpException;
/*     */     }
/*     */ 
/* 512 */     setCommunicationsLink(this.m_pumpDetector.getCommunicationsLink());
/* 513 */     ((MMX12)localObject).setCommunicationsLink(this.m_pumpDetector.getCommunicationsLink());
/*     */ 
/* 516 */     ((MMX12)localObject).initDeviceAfterModelNumberKnown();
/* 517 */     return (MMX12)localObject;
/*     */   }
/*     */ 
/*     */   private void forceShutDown()
/*     */   {
/*     */     try
/*     */     {
/* 527 */       this.m_pumpDetector.shutDownPump();
/*     */     } catch (BadDeviceCommException localBadDeviceCommException) {
/*     */     }
/*     */     catch (IOException localIOException1) {
/*     */     }
/*     */     finally {
/*     */       try {
/* 534 */         this.m_pumpDetector.shutDownComStation();
/*     */       } catch (IOException localIOException4) {
/*     */       }
/*     */       finally {
/*     */         try {
/* 539 */           this.m_pumpDetector.shutDownSerialPort();
/*     */         }
/*     */         catch (IOException localIOException5)
/*     */         {
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private MMX12 getCurrentPump()
/*     */   {
/* 553 */     return this.m_currentPump;
/*     */   }
/*     */ 
/*     */   private void setCurrentPump(MMX12 paramMMX12)
/*     */   {
/* 562 */     this.m_currentPump = paramMMX12;
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.deviceportreader.MMParadigm2Proxy
 * JD-Core Version:    0.6.0
 */