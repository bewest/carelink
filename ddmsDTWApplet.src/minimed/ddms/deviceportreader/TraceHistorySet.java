/*     */ package minimed.ddms.deviceportreader;
/*     */ 
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ public class TraceHistorySet
/*     */ {
/*     */   private static final int PUMP_TRACE_ID = 1;
/*     */   private static final int DETAIL_TRACE_ID = 2;
/*     */   private static final int NEW_ALARM_TRACE_ID = 3;
/*     */   private static final int OLD_ALARM_TRACE_ID = 4;
/*     */   private final String m_serialNum;
/*     */   private final String m_fwVersion;
/*     */   private final Date m_timeStamp;
/*     */   private final TraceHistory m_pumpTraceHistory;
/*     */   private final TraceHistory m_detailTraceHistory;
/*     */   private final TraceHistory m_newAlarmTraceHistory;
/*     */   private final TraceHistory m_oldAlarmTraceHistory;
/*     */ 
/*     */   public TraceHistorySet(String paramString1, String paramString2, Date paramDate, int[] paramArrayOfInt1, int[] paramArrayOfInt2, int[] paramArrayOfInt3, int[] paramArrayOfInt4)
/*     */   {
/*  83 */     Contract.preNonNull(paramString1);
/*  84 */     Contract.preNonNull(paramString2);
/*  85 */     Contract.preNonNull(paramDate);
/*  86 */     Contract.preNonNull(paramArrayOfInt1);
/*  87 */     Contract.preNonNull(paramArrayOfInt2);
/*  88 */     Contract.preNonNull(paramArrayOfInt2);
/*  89 */     Contract.preNonNull(paramArrayOfInt4);
/*     */ 
/*  91 */     this.m_serialNum = paramString1;
/*  92 */     this.m_fwVersion = paramString2;
/*  93 */     this.m_timeStamp = paramDate;
/*  94 */     this.m_pumpTraceHistory = new TraceHistory("PumpTrace", paramArrayOfInt1, 1, null);
/*     */ 
/*  96 */     this.m_detailTraceHistory = new TraceHistory("DetailTrace", paramArrayOfInt2, 2, null);
/*     */ 
/*  98 */     this.m_newAlarmTraceHistory = new TraceHistory("NewAlarmTrace", paramArrayOfInt3, 3, null);
/*     */ 
/* 100 */     this.m_oldAlarmTraceHistory = new TraceHistory("OldAlarmTrace", paramArrayOfInt4, 4, null);
/*     */   }
/*     */ 
/*     */   public TraceHistory getPumpTraceHistory()
/*     */   {
/* 113 */     return this.m_pumpTraceHistory;
/*     */   }
/*     */ 
/*     */   public TraceHistory getDetailTraceHistory()
/*     */   {
/* 122 */     return this.m_detailTraceHistory;
/*     */   }
/*     */ 
/*     */   public TraceHistory getNewAlarmTraceHistory()
/*     */   {
/* 131 */     return this.m_newAlarmTraceHistory;
/*     */   }
/*     */ 
/*     */   public TraceHistory getOldAlarmTraceHistory()
/*     */   {
/* 140 */     return this.m_oldAlarmTraceHistory;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 149 */     return "TraceHistorySet (" + getTotalBytes() + " bytes total) = " + this.m_pumpTraceHistory + ", " + this.m_detailTraceHistory + ", " + this.m_newAlarmTraceHistory + ", " + this.m_oldAlarmTraceHistory;
/*     */   }
/*     */ 
/*     */   public int getTotalBytes()
/*     */   {
/* 160 */     return this.m_pumpTraceHistory.getData().length + this.m_detailTraceHistory.getData().length + this.m_oldAlarmTraceHistory.getData().length + this.m_newAlarmTraceHistory.getData().length;
/*     */   }
/*     */ 
/*     */   public class TraceHistory
/*     */   {
/*     */     private static final String UNDERSCORE = "_";
/*     */     private static final String FILE_EXTENSION = ".tup";
/*     */     private final byte[] m_data;
/*     */     private final String m_fileID;
/*     */     private final int m_bufferID;
/*     */     private final SimpleDateFormat m_dateFormatter;
/*     */     private final SimpleDateFormat m_timeFormatter;
/*     */     private final String m_bufferName;
/*     */     private final TraceHistorySet this$0;
/*     */ 
/*     */     private TraceHistory(String paramArrayOfByte, byte[] paramInt, int arg4)
/*     */     {
/* 197 */       this.this$0 = this$1;
/* 198 */       Contract.preNonNull(paramInt);
/*     */       int i;
/* 199 */       Contract.pre(i, 1, 4);
/*     */ 
/* 201 */       this.m_bufferName = paramArrayOfByte;
/* 202 */       this.m_data = paramInt;
/* 203 */       this.m_bufferID = i;
/*     */ 
/* 206 */       this.m_dateFormatter = new SimpleDateFormat("ddMMMyy");
/* 207 */       this.m_timeFormatter = new SimpleDateFormat("HHmm");
/*     */ 
/* 210 */       this.m_fileID = ("Pump" + this$1.m_serialNum + "_" + this$1.m_fwVersion + "_" + this.m_dateFormatter.format(this$1.m_timeStamp) + "_" + "time" + this.m_timeFormatter.format(this$1.m_timeStamp) + "_" + this.m_bufferID + ".tup");
/*     */     }
/*     */ 
/*     */     private TraceHistory(String paramArrayOfInt, int[] paramInt, int arg4)
/*     */     {
/* 230 */       this(paramArrayOfInt, MedicalDevice.Util.convertIntsToBytes(paramInt), i);
/*     */     }
/*     */ 
/*     */     public byte[] getData()
/*     */     {
/* 242 */       return this.m_data;
/*     */     }
/*     */ 
/*     */     public String getFileID()
/*     */     {
/* 252 */       return this.m_fileID;
/*     */     }
/*     */ 
/*     */     public String toString()
/*     */     {
/* 262 */       return this.m_bufferName + ": " + getFileID() + " - " + getData().length + " bytes";
/*     */     }
/*     */ 
/*     */     TraceHistory(String paramArrayOfInt, int[] paramInt, int param1, TraceHistorySet.1 arg5)
/*     */     {
/* 170 */       this(paramArrayOfInt, paramInt, param1);
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.deviceportreader.TraceHistorySet
 * JD-Core Version:    0.6.0
 */