/*     */ package minimed.ddms.deviceportreader;
/*     */ 
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.Date;
/*     */ 
/*     */ public abstract interface DevicePortReader
/*     */ {
/*     */   public static final int UPLOAD_RESULT_BAD = 0;
/*     */   public static final int UPLOAD_RESULT_OK = 1;
/*     */   public static final int UPLOAD_RESULT_HALT = 2;
/*     */   public static final int PHASE_NONE = 0;
/*     */   public static final int PHASE_AUTO_DETECT_PORT = 1;
/*     */   public static final int PHASE_INIT_SERIAL_PORT = 2;
/*     */   public static final int PHASE_INIT_COM_STATION = 3;
/*     */   public static final int PHASE_INIT_DEVICE = 4;
/*     */   public static final int PHASE_ACQUIRE_DATA = 5;
/*     */   public static final int PHASE_CLEAN_UP = 6;
/*     */   public static final int PHASE_AUTO_DETECT_DEVICE = 7;
/*     */   public static final int MAX_PHASE = 7;
/*     */   public static final int ML_NONE = 0;
/*     */   public static final int ML_LOW = 1;
/*     */   public static final int ML_MEDIUM = 2;
/*     */   public static final int ML_HIGH = 3;
/*     */   public static final String TAG_COMMPORT = "@commport";
/*     */   public static final String TAG_LINKDEVICE = "@linkdevice";
/*     */   public static final String TAG_SERIALNUMBER = "@serialnumber";
/* 140 */   public static final String[] PHASE_TEXT = { "not used", "Auto Detect Port", "Initializing Serial Port: @commport", "Initializing Link Device: @linkdevice on @commport", "Initializing Device: @serialnumber", "Acquiring Device Data", "Clean Up", "Auto Detect Device" };
/*     */   public static final int STATE_IDLE = 1;
/*     */   public static final int STATE_INITIALIZING = 2;
/*     */   public static final int STATE_SETTING_ACCESS_CODE = 3;
/*     */   public static final int STATE_SENDING_COMMAND = 4;
/*     */   public static final int STATE_GETTING_DATA = 5;
/*     */   public static final int STATE_GETTING_BLOCK_DATA = 6;
/*     */   public static final int STATE_RETRYING = 7;
/*     */   public static final int STATE_HALTING = 8;
/*     */   public static final int STATE_HALTED = 9;
/*     */   public static final int STATE_LAST = 9;
/* 207 */   public static final String[] STATE_TEXT = { "not used", "Idle", "Initializing", "Setting AccessCode", "Sending Command", "Getting Data", "Getting Block Data", "Retrying", "Halting", "Halted" };
/*     */ 
/*     */   public abstract void readData(DeviceListener paramDeviceListener, int paramInt, String paramString, boolean paramBoolean)
/*     */     throws BadDeviceCommException, BadDeviceValueException, IOException, ConnectToPumpException;
/*     */ 
/*     */   public abstract void readData(DeviceListener paramDeviceListener, int paramInt, String paramString)
/*     */     throws BadDeviceCommException, BadDeviceValueException, IOException, ConnectToPumpException;
/*     */ 
/*     */   public abstract void readGlucoseDataRange(DeviceListener paramDeviceListener, int paramInt, String paramString, Date paramDate1, Date paramDate2)
/*     */     throws BadDeviceCommException, BadDeviceValueException, IOException, ConnectToPumpException;
/*     */ 
/*     */   public abstract void readIsigDataRange(DeviceListener paramDeviceListener, int paramInt, String paramString, Date paramDate1, Date paramDate2)
/*     */     throws BadDeviceCommException, BadDeviceValueException, IOException, ConnectToPumpException;
/*     */ 
/*     */   public abstract void readClock(DeviceListener paramDeviceListener, int paramInt, String paramString)
/*     */     throws BadDeviceCommException, BadDeviceValueException, IOException, ConnectToPumpException;
/*     */ 
/*     */   public abstract String autoDetectDevice(DeviceListener paramDeviceListener)
/*     */     throws BadDeviceCommException, IOException;
/*     */ 
/*     */   public abstract int getSerialPortNumber();
/*     */ 
/*     */   public abstract InputStream createSnapshot()
/*     */     throws BadDeviceValueException, IOException;
/*     */ 
/*     */   public abstract TraceHistorySet getTraceHistorySet();
/*     */ 
/*     */   public abstract void storeSnapshot(String paramString)
/*     */     throws FileNotFoundException, IOException;
/*     */ 
/*     */   public abstract void halt();
/*     */ 
/*     */   public abstract String getDescription();
/*     */ 
/*     */   public abstract String getModelNumber();
/*     */ 
/*     */   public abstract String getLastCommandDescription();
/*     */ 
/*     */   public abstract int getPhase();
/*     */ 
/*     */   public abstract String getPhaseText();
/*     */ 
/*     */   public abstract int getMaxRetryCount();
/*     */ 
/*     */   public abstract String getSerialNumber();
/*     */ 
/*     */   public abstract int getSnapshotVersion();
/*     */ 
/*     */   public abstract String getPackageVersion();
/*     */ 
/*     */   public abstract Date getClock();
/*     */ 
/*     */   public abstract void setMessageLevel(int paramInt);
/*     */ 
/*     */   public abstract long getLastHistoryPageNumber();
/*     */ 
/*     */   public abstract long getLastGlucoseHistoryPageNumber();
/*     */ 
/*     */   public abstract boolean isDevicePump();
/*     */ 
/*     */   public abstract boolean isDeviceMonitor();
/*     */ 
/*     */   public abstract boolean isDeviceMeter();
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.deviceportreader.DevicePortReader
 * JD-Core Version:    0.6.0
 */