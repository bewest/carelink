/*     */ package minimed.ddms.deviceportreader;
/*     */ 
/*     */ import java.io.PrintWriter;
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ public final class DevicePortReaderFactory
/*     */ {
/*     */   public static final int ID_MINIMED_508 = 0;
/*     */   public static final int ID_MINIMED_511 = 1;
/*     */   public static final int ID_LIFESCAN_OTPROFILE = 2;
/*     */   public static final int ID_LIFESCAN_OTBASIC = 4;
/*     */   public static final int ID_LIFESCAN_OTULTRA = 5;
/*     */   public static final int ID_LIFESCAN_FASTTAKE = 6;
/*     */   public static final int ID_LIFESCAN_SURESTEP = 7;
/*     */   public static final int ID_BAYER_DEX = 8;
/*     */   public static final int ID_BAYER_ELITE_XL = 9;
/*     */   public static final int ID_MEDISENSE_XTRA = 10;
/*     */   public static final int ID_MEDISENSE_SOFTACT = 11;
/*     */   public static final int ID_THERASENSE_FREESTYLE = 12;
/*     */   public static final int ID_MINIMED_PARADIGM2 = 13;
/*     */   public static final int LINK_DEVICE_COMLINK = 14;
/*     */   public static final int LINK_DEVICE_PARADIGMLINK = 15;
/*     */   public static final int LINK_DEVICE_COMLINKUSB = 19;
/*     */   public static final int ID_MINIMED_PARADIGMLINK_METER = 16;
/*     */   public static final int ID_MINIMED_LOGIC_METER = 17;
/*     */   public static final int LINK_DEVICE_COMSTATION = 18;
/*     */   public static final int ID_BAYER_BREEZE = 19;
/*     */   public static final int ID_BAYER_CONTOUR = 20;
/*     */   public static final int ID_LIFESCAN_OTULTRASMART = 21;
/*     */   public static final int ID_ROCHE_AVIVA = 22;
/*     */   public static final int ID_ROCHE_ACTIVE = 23;
/*     */   public static final int ID_ROCHE_COMPACT = 24;
/*     */   public static final int ID_ROCHE_COMPACTPLUS = 25;
/*     */   public static final int ID_LIFESCAN_OTULTRAMINI = 26;
/*     */   public static final String MINIMED_PARADIGM2_MODEL_512 = "512";
/*     */   public static final String MINIMED_PARADIGM2_MODEL_712 = "712";
/*     */   public static final String MINIMED_PARADIGM2_MODEL_515 = "515";
/*     */   public static final String MINIMED_PARADIGM2_MODEL_715 = "715";
/*     */   public static final String MINIMED_PARADIGM2_MODEL_522 = "522";
/*     */   public static final String MINIMED_PARADIGM2_MODEL_722 = "722";
/*     */   public static final String MINIMED_PARADIGM2_MODEL_522K = "522K";
/*     */   public static final String MINIMED_PARADIGM2_MODEL_722K = "722K";
/*     */   public static final String MINIMED_PARADIGM2_MODEL_GUARDIAN3 = "7100";
/*     */   public static final String MINIMED_PARADIGM2_MODEL_GUARDIAN3B = "7100B";
/*     */   public static final String MINIMED_PARADIGM2_MODEL_GUARDIAN3_7200 = "7200";
/*     */   public static final String MINIMED_PARADIGM2_MODEL_GUARDIAN3K = "7100K";
/*     */   public static final String MINIMED_PARADIGM2_MODEL_523 = "523";
/*     */   public static final String MINIMED_PARADIGM2_MODEL_723 = "723";
/*     */   public static final String MINIMED_PARADIGM2_MODEL_523K = "523K";
/*     */   public static final String MINIMED_PARADIGM2_MODEL_723K = "723K";
/*     */   public static final String MINIMED_PARADIGM2_MODEL_553 = "553";
/*     */   public static final String MINIMED_PARADIGM2_MODEL_753 = "753";
/*     */   public static final String MINIMED_PARADIGM2_MODEL_554 = "554";
/*     */   public static final String MINIMED_PARADIGM2_MODEL_754 = "754";
/*     */ 
/*     */   public static DevicePortReader makeDevice(int paramInt1, PrintWriter paramPrintWriter, int paramInt2, int paramInt3)
/*     */   {
/* 328 */     Object localObject = null;
/*     */ 
/* 331 */     if (paramPrintWriter != null) {
/* 332 */       MedicalDevice.setLogBuffer(paramPrintWriter);
/*     */     }
/*     */ 
/* 335 */     switch (paramInt1) {
/*     */     case 0:
/* 337 */       localObject = new MM508();
/* 338 */       break;
/*     */     case 1:
/* 340 */       localObject = new MM511(paramInt3);
/* 341 */       break;
/*     */     case 13:
/* 344 */       localObject = new MMParadigm2Proxy(paramInt3);
/* 345 */       break;
/*     */     case 16:
/* 347 */       localObject = new MMParadigmLinkMeter();
/* 348 */       break;
/*     */     case 17:
/* 350 */       localObject = new MMLogicMeter();
/* 351 */       break;
/*     */     case 2:
/* 353 */       localObject = new LSOneTouchProfile();
/* 354 */       break;
/*     */     case 4:
/* 356 */       localObject = new LSOneTouchBasic();
/* 357 */       break;
/*     */     case 6:
/* 359 */       localObject = new LSFastTake();
/* 360 */       break;
/*     */     case 7:
/* 362 */       localObject = new LSSureStep();
/* 363 */       break;
/*     */     case 5:
/* 365 */       localObject = new LSOneTouchUltra();
/* 366 */       break;
/*     */     case 21:
/* 368 */       localObject = new LSOneTouchUltraSmart();
/* 369 */       break;
/*     */     case 26:
/* 371 */       localObject = new LSOneTouchUltraMini();
/* 372 */       break;
/*     */     case 10:
/* 374 */       localObject = new MedisenseXtra();
/* 375 */       break;
/*     */     case 11:
/* 377 */       localObject = new MedisenseSofTact();
/* 378 */       break;
/*     */     case 8:
/* 380 */       localObject = new BayerDEX();
/* 381 */       break;
/*     */     case 9:
/* 383 */       localObject = new BayerEliteXL();
/* 384 */       break;
/*     */     case 19:
/* 386 */       localObject = new BayerBreeze();
/* 387 */       break;
/*     */     case 20:
/* 389 */       localObject = new BayerContour();
/* 390 */       break;
/*     */     case 12:
/* 392 */       localObject = new TheraSenseFreeStyle();
/* 393 */       break;
/*     */     case 22:
/* 395 */       localObject = new RocheAviva();
/* 396 */       break;
/*     */     case 23:
/*     */     case 24:
/*     */     case 25:
/* 400 */       localObject = new RocheActiveAndCompact(paramInt1);
/* 401 */       break;
/*     */     case 3:
/*     */     case 14:
/*     */     case 15:
/*     */     case 18:
/*     */     default:
/* 404 */       Contract.unreachable();
/*     */     }
/*     */ 
/* 408 */     if (localObject != null) {
/* 409 */       ((DevicePortReader)localObject).setMessageLevel(paramInt2);
/*     */     }
/*     */ 
/* 412 */     return (DevicePortReader)localObject;
/*     */   }
/*     */ 
/*     */   static String mapLinkDeviceID(int paramInt)
/*     */   {
/*     */     String str;
/* 424 */     switch (paramInt) {
/*     */     case 14:
/* 426 */       str = "ComLink";
/* 427 */       break;
/*     */     case 19:
/* 429 */       str = "ComLink2";
/* 430 */       break;
/*     */     case 15:
/* 432 */       str = "ParadigmLink";
/* 433 */       break;
/*     */     case 18:
/* 435 */       str = "ComStation";
/* 436 */       break;
/*     */     case 16:
/*     */     case 17:
/*     */     default:
/* 438 */       str = "UNKNOWN";
/*     */     }
/*     */ 
/* 442 */     return str;
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.deviceportreader.DevicePortReaderFactory
 * JD-Core Version:    0.6.0
 */