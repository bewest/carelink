/*     */ package minimed.ddms.deviceportreader;
/*     */ 
/*     */ public class RocheAviva extends RocheMeter
/*     */ {
/*     */   private static final int SNAPSHOT_FORMAT_ID = 172;
/*  35 */   private static final int[] READ_FIRMWARE_CMD = { 67, 9, 49, 13 };
/*  36 */   private static final int[] READ_SERIAL_NUMBER_CMD = { 67, 9, 51, 13 };
/*  37 */   private static final int[] READ_MODEL_NUMBER_CMD = { 67, 9, 52, 13 };
/*  38 */   private static final int[] READ_DATE_CMD = { 83, 9, 49, 13 };
/*  39 */   private static final int[] READ_TIME_CMD = { 83, 9, 50, 13 };
/*  40 */   private static final int[] GET_NUM_RESULTS = { 96, 13 };
/*     */ 
/*  46 */   private static final int[] EXTRACT_RESULTS_CMD = { 97, 9, 49, 9, 48, 48, 49, 13 };
/*     */   private static final int RESULTS_REC_LENGTH = 31;
/*     */   private static final int MAX_RESULTS_SIZE = 15500;
/*     */ 
/*     */   public RocheAviva()
/*     */   {
/*  60 */     super("Roche Aviva", 172, 22, READ_FIRMWARE_CMD, READ_SERIAL_NUMBER_CMD, READ_TIME_CMD, READ_DATE_CMD, READ_MODEL_NUMBER_CMD, GET_NUM_RESULTS, EXTRACT_RESULTS_CMD, 15500, 31);
/*     */   }
/*     */ 
/*     */   String extractData(int[] paramArrayOfInt)
/*     */     throws BadDeviceValueException
/*     */   {
/*  85 */     int i = -1;
/*  86 */     for (int j = 0; (j < paramArrayOfInt.length) && (i == -1); j++) {
/*  87 */       if (paramArrayOfInt[j] == 9) {
/*  88 */         i = j;
/*     */       }
/*     */     }
/*     */ 
/*  92 */     if (i == -1) {
/*  93 */       throw new BadDeviceValueException("extractData: can find TAB1 in response " + MedicalDevice.Util.getHex(paramArrayOfInt));
/*     */     }
/*     */ 
/*  98 */     j = -1;
/*  99 */     for (int k = i + 1; (k < paramArrayOfInt.length) && (j == -1); k++) {
/* 100 */       if (paramArrayOfInt[k] == 9) {
/* 101 */         j = k;
/*     */       }
/*     */     }
/*     */ 
/* 105 */     if (j == -1) {
/* 106 */       throw new BadDeviceValueException("extractData: can find TAB2 in response " + MedicalDevice.Util.getHex(paramArrayOfInt));
/*     */     }
/*     */ 
/* 111 */     k = j - i - 1;
/* 112 */     if (k < 1) {
/* 113 */       throw new BadDeviceValueException("extractData: response length (" + k + ") too short: " + MedicalDevice.Util.getHex(paramArrayOfInt));
/*     */     }
/*     */ 
/* 118 */     int[] arrayOfInt = new int[k];
/*     */ 
/* 120 */     System.arraycopy(paramArrayOfInt, i + 1, arrayOfInt, 0, k);
/* 121 */     return MedicalDevice.Util.makeString(arrayOfInt);
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.deviceportreader.RocheAviva
 * JD-Core Version:    0.6.0
 */