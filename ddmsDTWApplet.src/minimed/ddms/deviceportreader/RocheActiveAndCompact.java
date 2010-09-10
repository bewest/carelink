/*     */ package minimed.ddms.deviceportreader;
/*     */ 
/*     */ import java.util.Vector;
/*     */ 
/*     */ public class RocheActiveAndCompact extends RocheMeter
/*     */ {
/*     */   private static final int SNAPSHOT_FORMAT_ID = 173;
/*     */   private static final int RESULTS_REC_LENGTH = 22;
/*     */   private static final int MAX_RESULTS_SIZE = 11000;
/*  41 */   private static final int[] READ_FIRMWARE_CMD = { 67, 32, 49, 13 };
/*  42 */   private static final int[] READ_SERIAL_NUMBER_CMD = { 67, 32, 51, 13 };
/*  43 */   private static final int[] READ_MODEL_NUMBER_CMD = { 67, 32, 52, 13 };
/*  44 */   private static final int[] READ_DATE_CMD = { 83, 32, 49, 13 };
/*  45 */   private static final int[] READ_TIME_CMD = { 83, 32, 50, 13 };
/*  46 */   private static final int[] GET_NUM_RESULTS = { 96, 13 };
/*     */ 
/*  49 */   private static final int[] READ_INSTRUMENT_NAME_CMD = { 73, 13 };
/*  50 */   private static final int[] READ_BG_UNITS_CMD = { 83, 32, 51, 13 };
/*     */   private static final String ACTIVE_INSTRUMENT_NAME = "ACCU-CHEK Active";
/*     */   private static final String COMPACT_INSTRUMENT_NAME = "GCP";
/*     */   private static final String COMPACTPLUS_INSTRUMENT_NAME = "GCP2";
/*     */   private static final String COMPACTPLUS_INSTRUMENT_NAME2 = "GCP2-LCM";
/*     */   private static final String MSG_METER_DEVICE_SELECTION = "Wrong Meter Selection";
/*  71 */   private static final int[] EXTRACT_RESULTS_CMD = { 97, 32, 49, 32, 48, 48, 49, 13 };
/*     */   private RocheMeter.Command m_cmdReadInstrumentName;
/*     */   private RocheMeter.Command m_cmdReadBGUnits;
/*     */   private String m_instrumentName;
/*     */   private String m_bgUnits;
/*     */ 
/*     */   RocheActiveAndCompact(int paramInt)
/*     */   {
/*  89 */     super("Roche Active-Compact-CompactPlus", 173, paramInt, READ_FIRMWARE_CMD, READ_SERIAL_NUMBER_CMD, READ_TIME_CMD, READ_DATE_CMD, READ_MODEL_NUMBER_CMD, GET_NUM_RESULTS, EXTRACT_RESULTS_CMD, 11000, 22);
/*     */ 
/*  96 */     this.m_cmdReadInstrumentName = new RocheMeter.Command(this, READ_INSTRUMENT_NAME_CMD, "Read Instrument Name", new RocheMeter.ReplyDecoder()
/*     */     {
/*     */       public void decodeReply(RocheMeter.Command paramCommand)
/*     */         throws BadDeviceValueException
/*     */       {
/* 107 */         RocheActiveAndCompact.access$002(RocheActiveAndCompact.this, RocheActiveAndCompact.this.extractData(paramCommand.getRawData()));
/* 108 */         MedicalDevice.logInfo(this, "decodeReply: instrument name is " + RocheActiveAndCompact.this.m_instrumentName);
/*     */ 
/* 110 */         int i = RocheActiveAndCompact.this.getDeviceID(RocheActiveAndCompact.this.m_instrumentName);
/* 111 */         if (i != RocheActiveAndCompact.this.m_deviceClassID)
/* 112 */           throw new BadDeviceValueException("Wrong Meter Selection: deviceID found = " + i + ", expected = " + RocheActiveAndCompact.this.m_deviceClassID);
/*     */       }
/*     */     });
/* 120 */     this.m_cmdReadBGUnits = new RocheMeter.Command(this, READ_BG_UNITS_CMD, "Read BG Units", new RocheMeter.ReplyDecoder()
/*     */     {
/*     */       public void decodeReply(RocheMeter.Command paramCommand) throws BadDeviceValueException {
/* 123 */         RocheActiveAndCompact.access$202(RocheActiveAndCompact.this, RocheActiveAndCompact.this.extractData(paramCommand.getRawData()));
/* 124 */         MedicalDevice.logInfo(this, "decodeReply: BG units are " + RocheActiveAndCompact.this.m_bgUnits);
/*     */       }
/*     */     });
/* 129 */     this.m_snapshotCreator = new SnapshotCreator();
/*     */   }
/*     */ 
/*     */   private int getDeviceID(String paramString)
/*     */     throws BadDeviceValueException
/*     */   {
/*     */     int i;
/* 142 */     if ("ACCU-CHEK Active".equalsIgnoreCase(paramString))
/* 143 */       i = 23;
/* 144 */     else if ("GCP".equalsIgnoreCase(paramString))
/* 145 */       i = 24;
/* 146 */     else if (("GCP2".equalsIgnoreCase(paramString)) || ("GCP2-LCM".equalsIgnoreCase(paramString)))
/*     */     {
/* 148 */       i = 25;
/*     */     }
/* 150 */     else throw new BadDeviceValueException("unknown Instrument Name: " + paramString);
/*     */ 
/* 152 */     return i;
/*     */   }
/*     */ 
/*     */   String extractData(int[] paramArrayOfInt)
/*     */     throws BadDeviceValueException
/*     */   {
/* 173 */     int i = paramArrayOfInt.length - 4;
/*     */ 
/* 175 */     if (i < 1) {
/* 176 */       throw new BadDeviceValueException("extractData: response length (" + i + ") too short: " + MedicalDevice.Util.getHex(paramArrayOfInt));
/*     */     }
/*     */ 
/* 181 */     int[] arrayOfInt = new int[i];
/* 182 */     System.arraycopy(paramArrayOfInt, 2, arrayOfInt, 0, i);
/* 183 */     return MedicalDevice.Util.makeString(arrayOfInt);
/*     */   }
/*     */ 
/*     */   Vector createCommandList()
/*     */   {
/* 193 */     Vector localVector = super.createCommandList();
/*     */ 
/* 197 */     localVector.insertElementAt(this.m_cmdReadBGUnits, 0);
/* 198 */     localVector.insertElementAt(this.m_cmdReadInstrumentName, 1);
/*     */ 
/* 200 */     return localVector;
/*     */   }
/*     */ 
/*     */   private class SnapshotCreator extends RocheMeter.SnapshotCreator
/*     */   {
/*     */     private static final int SNAPCODE_READ_INSTRUMENT_NAME = 4;
/*     */     private static final int SNAPCODE_READ_BG_UNITS = 5;
/*     */ 
/*     */     SnapshotCreator()
/*     */     {
/* 226 */       super();
/*     */     }
/*     */ 
/*     */     void createSnapshotBody()
/*     */     {
/* 236 */       super.createSnapshotBody();
/*     */ 
/* 241 */       RocheActiveAndCompact.this.m_snapshot.addElement(4, RocheActiveAndCompact.this.pad(RocheActiveAndCompact.this.m_instrumentName));
/*     */ 
/* 244 */       RocheActiveAndCompact.this.m_snapshot.addElement(5, RocheActiveAndCompact.this.pad(RocheActiveAndCompact.this.m_bgUnits));
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.deviceportreader.RocheActiveAndCompact
 * JD-Core Version:    0.6.0
 */