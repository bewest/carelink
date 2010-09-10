/*     */ package minimed.ddms.deviceportreader;
/*     */ 
/*     */ class MMX53 extends MMX23
/*     */ {
/*     */   public static final int SNAPSHOT_FORMAT_ID = 117;
/*     */   static final String MODEL_NUMBER1 = "553";
/*     */   static final String MODEL_NUMBER2 = "753";
/*     */ 
/*     */   MMX53(int paramInt, long paramLong1, long paramLong2, String paramString)
/*     */   {
/*  69 */     super(paramInt, paramLong1, paramLong2, paramString, 117);
/*     */   }
/*     */ 
/*     */   MMX53(int paramInt, long paramLong1, long paramLong2)
/*     */   {
/*  87 */     this(paramInt, paramLong1, paramLong2, "MiniMed MMT-553/753 (Paradigm2) Insulin Pump");
/*     */   }
/*     */ 
/*     */   static boolean isModelNumberSupported(String paramString)
/*     */   {
/* 102 */     return ("553".equals(paramString)) || ("753".equals(paramString));
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.deviceportreader.MMX53
 * JD-Core Version:    0.6.0
 */