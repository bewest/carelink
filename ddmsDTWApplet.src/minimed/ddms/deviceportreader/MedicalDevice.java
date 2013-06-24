/*      */ package minimed.ddms.deviceportreader;
/*      */ 
/*      */ import java.io.FileNotFoundException;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.PrintWriter;
/*      */ import java.text.SimpleDateFormat;
/*      */ import java.util.Calendar;
/*      */ import java.util.Date;
/*      */ import java.util.GregorianCalendar;
/*      */ import java.util.Vector;
/*      */ import minimed.util.Contract;
/*      */ 
/*      */ abstract class MedicalDevice
/*      */   implements DevicePortReader, DeviceListener
/*      */ {
/*      */   public static final String PRODUCT_CODE_ERROR_MSG = "Incorrect product code";
/*      */   static final String PACKAGE_VERSION = "9.0.0";
/*      */   static final int SNAPLENGTH_1 = 1;
/*      */   static final int SNAPLENGTH_2 = 2;
/*      */   static final int SNAPSHOT_VERSION1 = 1;
/*      */   static final int SNAPSHOT_VERSION2 = 2;
/*      */   static final int MAX_NIBBLE = 15;
/*      */   static final int MAX_BYTE = 255;
/*      */   static final int MAX_WORD = 65535;
/*      */   static final int MAX_BYTE_3 = 16777215;
/*      */   static final long MAX_DWORD = 4294967295L;
/*      */   static final byte BITS_MODULO4 = 3;
/*      */   static final byte BITS_PER_NIBBLE = 4;
/*      */   static final byte BITS_PER_BYTE = 8;
/*      */   static final int BIT_7_MASK = 128;
/*      */   static final int BIT_6_MASK = 64;
/*      */   static final int BIT_5_MASK = 32;
/*      */   static final int BIT_4_MASK = 16;
/*      */   static final int BIT_3_MASK = 8;
/*      */   static final int BIT_2_MASK = 4;
/*      */   static final int BIT_1_MASK = 2;
/*      */   static final int BIT_0_MASK = 1;
/*      */   static final byte TWO_LSB = 3;
/*      */   static final byte THREE_LSB = 7;
/*      */   static final byte FOUR_LSB = 15;
/*      */   static final byte FIVE_LSB = 31;
/*      */   static final byte SIX_LSB = 63;
/*      */   static final byte SEVEN_LSB = 127;
/*      */   static final int WORD_SIGN_BIT = 32768;
/*      */   static final int BASE_YEAR = 2000;
/*      */   static final int BASE16 = 16;
/*      */   static final int HIGH_BYTE = 0;
/*      */   static final int LOW_BYTE = 1;
/*      */   static final short CRC16_MASK = 4129;
/*  212 */   static final Integer NO_DOW = null;
/*      */ 
/*  217 */   static final Integer NO_CHECKSUM = null;
/*      */   static final int SUBSTRING_NOT_FOUND = -1;
/*      */   static final char NUL = '\000';
/*      */   static final char STH = '\001';
/*      */   static final char STX = '\002';
/*      */   static final char ETX = '\003';
/*      */   static final char EOT = '\004';
/*      */   static final char ENQ = '\005';
/*      */   static final char ACK = '\006';
/*      */   static final char TAB = '\t';
/*      */   static final char LF = '\n';
/*      */   static final char CR = '\r';
/*      */   static final char DLE = '\020';
/*      */   static final char DC1 = '\021';
/*      */   static final char NAK = '\025';
/*      */   static final char ETB = '\027';
/*      */   static final char CAN = '\030';
/*      */   static final char SPACE = ' ';
/*      */   static final char ASCII_ZERO = '0';
/*      */   static final char FIRST_PRINT_CHAR = ' ';
/*      */   static final char LAST_PRINT_CHAR = '';
/*      */   static final String DEVICE_CR_STR = "\r";
/*      */   static final String DEVICE_LF_STR = "\n";
/*      */   static final int HOURS_IN_DAY = 24;
/*      */   static final int MINUTES_IN_HOUR = 60;
/*      */   static final int SECONDS_IN_MINUTE = 60;
/*      */   static final int MS_IN_SECOND = 1000;
/*      */   static final int MONTHS_IN_YEAR = 12;
/*      */   static final int MAX_DAYS_IN_MONTH = 31;
/*      */   static final char SINGLE_QUOTE_CHAR = '\'';
/*      */   static final String UNITS_MGDL = "MG/DL";
/*      */   static final String UNITS_MMOLL = "MMOL/L";
/*      */   static final String TIMEFORMAT_12H = "AM/PM";
/*      */   static final String TIMEFORMAT_24H = "24:00";
/*  347 */   static final int[] CRC8_LOOKUP_TABLE = { 0, 155, 173, 54, 193, 90, 108, 247, 25, 130, 180, 47, 216, 67, 117, 238, 50, 169, 159, 4, 243, 104, 94, 197, 43, 176, 134, 29, 234, 113, 71, 220, 100, 255, 201, 82, 165, 62, 8, 147, 125, 230, 208, 75, 188, 39, 17, 138, 86, 205, 251, 96, 151, 12, 58, 161, 79, 212, 226, 121, 142, 21, 35, 184, 200, 83, 101, 254, 9, 146, 164, 63, 209, 74, 124, 231, 16, 139, 189, 38, 250, 97, 87, 204, 59, 160, 150, 13, 227, 120, 78, 213, 34, 185, 143, 20, 172, 55, 1, 154, 109, 246, 192, 91, 181, 46, 24, 131, 116, 239, 217, 66, 158, 5, 51, 168, 95, 196, 242, 105, 135, 28, 42, 177, 70, 221, 235, 112, 11, 144, 166, 61, 202, 81, 103, 252, 18, 137, 191, 36, 211, 72, 126, 229, 57, 162, 148, 15, 248, 99, 85, 206, 32, 187, 141, 22, 225, 122, 76, 215, 111, 244, 194, 89, 174, 53, 3, 152, 118, 237, 219, 64, 183, 44, 26, 129, 93, 198, 240, 107, 156, 7, 49, 170, 68, 223, 233, 114, 133, 30, 40, 179, 195, 88, 110, 245, 2, 153, 175, 52, 218, 65, 119, 236, 27, 128, 182, 45, 241, 106, 92, 199, 48, 171, 157, 6, 232, 115, 69, 222, 41, 178, 132, 31, 167, 60, 10, 145, 102, 253, 203, 80, 190, 37, 19, 136, 127, 228, 210, 73, 149, 14, 56, 163, 84, 207, 249, 98, 140, 23, 33, 186, 77, 214, 224, 123 };
/*      */ 
/*  385 */   static final int[] CRC8_LOOKUP_TABLE_BD = { 0, 94, 188, 226, 97, 63, 221, 131, 194, 156, 126, 32, 163, 253, 31, 65, 157, 195, 33, 127, 252, 162, 64, 30, 95, 1, 227, 189, 62, 96, 130, 220, 35, 125, 159, 193, 66, 28, 254, 160, 225, 191, 93, 3, 128, 222, 60, 98, 190, 224, 2, 92, 223, 129, 99, 61, 124, 34, 192, 158, 29, 67, 161, 255, 70, 24, 250, 164, 39, 121, 155, 197, 132, 218, 56, 102, 229, 187, 89, 7, 219, 133, 103, 57, 186, 228, 6, 88, 25, 71, 165, 251, 120, 38, 196, 154, 101, 59, 217, 135, 4, 90, 184, 230, 167, 249, 27, 69, 198, 152, 122, 36, 248, 166, 68, 26, 153, 199, 37, 123, 58, 100, 134, 216, 91, 5, 231, 185, 140, 210, 48, 110, 237, 179, 81, 15, 78, 16, 242, 172, 47, 113, 147, 205, 17, 79, 173, 243, 112, 46, 204, 146, 211, 141, 111, 49, 178, 236, 14, 80, 175, 241, 19, 77, 206, 144, 114, 44, 109, 51, 209, 143, 12, 82, 176, 238, 50, 108, 142, 208, 83, 13, 239, 177, 240, 174, 76, 18, 145, 207, 45, 115, 202, 148, 118, 40, 171, 245, 23, 73, 8, 86, 180, 234, 105, 55, 213, 139, 87, 9, 235, 181, 54, 104, 138, 212, 149, 203, 41, 119, 244, 170, 72, 22, 233, 183, 85, 11, 136, 214, 52, 106, 43, 117, 151, 201, 74, 20, 246, 168, 116, 42, 200, 150, 21, 75, 169, 247, 182, 232, 10, 84, 215, 137, 107, 53 };
/*      */ 
/*  409 */   static final int[] CRC16_CCITT_LOOKUP_TABLE = { 0, 4129, 8258, 12387, 16516, 20645, 24774, 28903, 33032, 37161, 41290, 45419, 49548, 53677, 57806, 61935, 4657, 528, 12915, 8786, 21173, 17044, 29431, 25302, 37689, 33560, 45947, 41818, 54205, 50076, 62463, 58334, 9314, 13379, 1056, 5121, 25830, 29895, 17572, 21637, 42346, 46411, 34088, 38153, 58862, 62927, 50604, 54669, 13907, 9842, 5649, 1584, 30423, 26358, 22165, 18100, 46939, 42874, 38681, 34616, 63455, 59390, 55197, 51132, 18628, 22757, 26758, 30887, 2112, 6241, 10242, 14371, 51660, 55789, 59790, 63919, 35144, 39273, 43274, 47403, 23285, 19156, 31415, 27286, 6769, 2640, 14899, 10770, 56317, 52188, 64447, 60318, 39801, 35672, 47931, 43802, 27814, 31879, 19684, 23749, 11298, 15363, 3168, 7233, 60846, 64911, 52716, 56781, 44330, 48395, 36200, 40265, 32407, 28342, 24277, 20212, 15891, 11826, 7761, 3696, 65439, 61374, 57309, 53244, 48923, 44858, 40793, 36728, 37256, 33193, 45514, 41451, 53516, 49453, 61774, 57711, 4224, 161, 12482, 8419, 20484, 16421, 28742, 24679, 33721, 37784, 41979, 46042, 49981, 54044, 58239, 62302, 689, 4752, 8947, 13010, 16949, 21012, 25207, 29270, 46570, 42443, 38312, 34185, 62830, 58703, 54572, 50445, 13538, 9411, 5280, 1153, 29798, 25671, 21540, 17413, 42971, 47098, 34713, 38840, 59231, 63358, 50973, 55100, 9939, 14066, 1681, 5808, 26199, 30326, 17941, 22068, 55628, 51565, 63758, 59695, 39368, 35305, 47498, 43435, 22596, 18533, 30726, 26663, 6336, 2273, 14466, 10403, 52093, 56156, 60223, 64286, 35833, 39896, 43963, 48026, 19061, 23124, 27191, 31254, 2801, 6864, 10931, 14994, 64814, 60687, 56684, 52557, 48554, 44427, 40424, 36297, 31782, 27655, 23652, 19525, 15522, 11395, 7392, 3265, 61215, 65342, 53085, 57212, 44955, 49082, 36825, 40952, 28183, 32310, 20053, 24180, 11923, 16050, 3793, 7920 };
/*      */ 
/*  451 */   static final int[] CRC7_LOOKUP_TABLE = { 0, 26, 52, 46, 104, 114, 92, 70, 93, 71, 105, 115, 53, 47, 1, 27, 55, 45, 3, 25, 95, 69, 107, 113, 106, 112, 94, 68, 2, 24, 54, 44, 110, 116, 90, 64, 6, 28, 50, 40, 51, 41, 7, 29, 91, 65, 111, 117, 89, 67, 109, 119, 49, 43, 5, 31, 4, 30, 48, 42, 108, 118, 88, 66, 81, 75, 101, 127, 57, 35, 13, 23, 12, 22, 56, 34, 100, 126, 80, 74, 102, 124, 82, 72, 14, 20, 58, 32, 59, 33, 15, 21, 83, 73, 103, 125, 63, 37, 11, 17, 87, 77, 99, 121, 98, 120, 86, 76, 10, 16, 62, 36, 8, 18, 60, 38, 96, 122, 84, 78, 85, 79, 97, 123, 61, 39, 9, 19, 47, 53, 27, 1, 71, 93, 115, 105, 114, 104, 70, 92, 26, 0, 46, 52, 24, 2, 44, 54, 112, 106, 68, 94, 69, 95, 113, 107, 45, 55, 25, 3, 65, 91, 117, 111, 41, 51, 29, 7, 28, 6, 40, 50, 116, 110, 64, 90, 118, 108, 66, 88, 30, 4, 42, 48, 43, 49, 31, 5, 67, 89, 119, 109, 126, 100, 74, 80, 22, 12, 34, 56, 35, 57, 23, 13, 75, 81, 127, 101, 73, 83, 125, 103, 33, 59, 21, 15, 20, 14, 32, 58, 124, 102, 72, 82, 16, 10, 36, 62, 120, 98, 76, 86, 77, 87, 121, 99, 37, 63, 17, 11, 39, 61, 19, 9, 79, 85, 123, 97, 122, 96, 78, 84, 18, 8, 38, 60 };
/*      */   static final int MAX_AUTO_OFF_DUR = 16;
/*  477 */   private static final String[] MAX_PORT_LIST = { "COM1", "COM2", "COM3", "COM4", "COM5", "COM6", "COM7", "COM8", "COM9" };
/*      */   private static final int MIN_YEAR = 1997;
/*      */   private static final int MAX_YEAR = 2096;
/*  487 */   private static boolean m_createSnapshotTextFile = false;
/*      */ 
/*  492 */   private static PrintWriter m_logBuffer = null;
/*      */ 
/*  497 */   private static int m_messageLevel = 0;
/*      */ 
/*  502 */   private static SimpleDateFormat m_dateFormatter = null;
/*      */   static final int PERCENT_100 = 100;
/*  509 */   static String m_lastCommandDescription = " ";
/*      */ 
/*  514 */   static boolean m_haltRequested = false;
/*      */ 
/*  519 */   static int m_state = 0;
/*      */ 
/*  524 */   static String m_stateText = "0";
/*      */ 
/*  529 */   static int m_phase = 0;
/*      */ 
/*  534 */   static String m_phaseText = "0";
/*      */   static final int DEFAULT_MAX_RETRIES = 2;
/*      */   private CommunicationsLink m_communicationsLink;
/*      */   static final int DEVICE_TYPE_PUMP = 1;
/*      */   static final int DEVICE_TYPE_MONITOR = 2;
/*      */   static final int DEVICE_TYPE_METER = 3;
/*      */   TraceHistorySet m_traceHistorySet;
/*      */   int m_serialPortNumber;
/*  565 */   int m_linkDevice = 0;
/*      */   String m_description;
/*      */   SnapshotCreator m_snapshotCreator;
/*      */   Snapshot m_snapshot;
/*      */   private int m_snapshotByteCount;
/*      */   int m_snapshotFirmwareCount;
/*      */   int m_snapshotSerialCount;
/*      */   int m_snapshotTimeCount;
/*      */   String m_serialNumber;
/*      */   String m_firmwareVersion;
/*      */   Date m_timeStamp;
/*      */   int m_minYear;
/*      */   int m_maxYear;
/*      */   int m_deviceClassID;
/*      */   int m_snapshotFormatID;
/*      */   long m_lastHistoryPageNumber;
/*      */   long m_lastGlucoseHistoryPageNumber;
/*      */   String m_modelNumber;
/*  659 */   private final Vector m_listeners = new Vector(10);
/*      */ 
/*      */   MedicalDevice()
/*      */   {
/*  670 */     this.m_minYear = MIN_YEAR;
/*  671 */     this.m_maxYear = MAX_YEAR;
/*  672 */     this.m_traceHistorySet = null;
/*      */ 
/*  675 */     setCommunicationsLink(new CommunicationsLinkRS232(this, this.m_serialNumber));
/*      */   }
/*      */ 
/*      */   public String toString()
/*      */   {
/*  692 */     return "description = " + this.m_description + ", modelNumber = " + this.m_modelNumber + ", device type = " + (isDeviceMeter() ? "meter" : isDeviceMonitor() ? "monitor" : isDevicePump() ? "pump" : null) + ", serialNumber = " + this.m_serialNumber + ", firmwareVersion = " + this.m_firmwareVersion + ", timeStamp = " + this.m_timeStamp + ", communicationsLink = '" + getCommunicationsLink() + "'" + ", linkDevice ID = " + this.m_linkDevice + ", PACKAGE_VERSION = " + PACKAGE_VERSION;
/*      */   }
/*      */ 
/*      */   public void readGlucoseDataRange(DeviceListener paramDeviceListener, int paramInt, String paramString, Date paramDate1, Date paramDate2)
/*      */     throws BadDeviceCommException, BadDeviceValueException, IOException, ConnectToPumpException
/*      */   {
/*  737 */     Contract.unreachable();
/*      */   }
/*      */ 
/*      */   public void readIsigDataRange(DeviceListener paramDeviceListener, int paramInt, String paramString, Date paramDate1, Date paramDate2)
/*      */     throws BadDeviceCommException, BadDeviceValueException, IOException, ConnectToPumpException
/*      */   {
/*  773 */     Contract.unreachable();
/*      */   }
/*      */ 
/*      */   public void readClock(DeviceListener paramDeviceListener, int paramInt, String paramString)
/*      */     throws BadDeviceCommException, BadDeviceValueException, IOException, ConnectToPumpException
/*      */   {
/*  797 */     Contract.unreachable();
/*      */   }
/*      */ 
/*      */   public void readData(DeviceListener paramDeviceListener, int paramInt, String paramString, boolean paramBoolean)
/*      */     throws BadDeviceCommException, BadDeviceValueException, IOException, ConnectToPumpException
/*      */   {
/*  827 */     readData(paramDeviceListener, paramInt, paramString);
/*      */   }
/*      */ 
/*      */   public TraceHistorySet getTraceHistorySet()
/*      */   {
/*  838 */     return this.m_traceHistorySet;
/*      */   }
/*      */ 
/*      */   public String autoDetectDevice(DeviceListener paramDeviceListener)
/*      */     throws BadDeviceCommException, IOException
/*      */   {
/*      */     String str;
/*      */     try
/*      */     {
/*      */       try
/*      */       {
/*  861 */         str = autoDetectDeviceIO(paramDeviceListener, SerialPort.getPortList());
/*      */       }
/*      */       catch (BadDeviceCommException localBadDeviceCommException) {
/*  864 */         if (!isHaltRequested()) {
/*  865 */           logWarning(this, "autoDetectDevice: system list failed; trying max port list.");
/*      */ 
/*  867 */           str = autoDetectDeviceIO(paramDeviceListener, MAX_PORT_LIST);
/*      */         } else {
/*  869 */           throw localBadDeviceCommException;
/*      */         }
/*      */       }
/*      */     }
/*      */     finally
/*      */     {
/*  875 */       shutDownSerialPort();
/*      */     }
/*      */ 
/*  878 */     return str;
/*      */   }
/*      */ 
/*      */   public InputStream createSnapshot()
/*      */     throws BadDeviceValueException, IOException
/*      */   {
/*  894 */     return this.m_snapshotCreator.createSnapshot();
/*      */   }
/*      */ 
/*      */   public void storeSnapshot(String paramString)
/*      */     throws FileNotFoundException, IOException
/*      */   {
/*  908 */     this.m_snapshot.store(paramString);
/*      */   }
/*      */ 
/*      */   public void halt()
/*      */   {
/*  916 */     logInfo(this, "halt: STOPPING UPLOAD...");
/*  917 */     setHaltRequested(true);
/*      */ 
/*  919 */     if ((getCommunicationsLink() != null) && 
/*  920 */       (getCommunicationsLink().getCommPort() != null)) {
/*  921 */       getCommunicationsLink().getCommPort().setContinueIO(false);
/*      */     }
/*      */ 
/*  925 */     setState(8);
/*      */   }
/*      */ 
/*      */   public String getLastCommandDescription()
/*      */   {
/*  934 */     return m_lastCommandDescription;
/*      */   }
/*      */ 
/*      */   public String getDescription()
/*      */   {
/*  943 */     return this.m_description;
/*      */   }
/*      */ 
/*      */   public synchronized int getPhase()
/*      */   {
/*  952 */     return m_phase;
/*      */   }
/*      */ 
/*      */   public String getPhaseText()
/*      */   {
/*  961 */     return m_phaseText;
/*      */   }
/*      */ 
/*      */   public String getSerialNumber()
/*      */   {
/*  970 */     return this.m_serialNumber;
/*      */   }
/*      */ 
/*      */   public int getSnapshotVersion()
/*      */   {
/*  979 */     return this.m_snapshot.getSnapshotVersion();
/*      */   }
/*      */ 
/*      */   public Date getClock()
/*      */   {
/*  988 */     return this.m_timeStamp;
/*      */   }
/*      */ 
/*      */   public int getMaxRetryCount()
/*      */   {
/*  999 */     return DEFAULT_MAX_RETRIES;
/*      */   }
/*      */ 
/*      */   public int getSerialPortNumber()
/*      */   {
/* 1008 */     return this.m_serialPortNumber;
/*      */   }
/*      */ 
/*      */   public long getLastHistoryPageNumber()
/*      */   {
/* 1019 */     return this.m_lastHistoryPageNumber;
/*      */   }
/*      */ 
/*      */   public long getLastGlucoseHistoryPageNumber()
/*      */   {
/* 1030 */     return this.m_lastGlucoseHistoryPageNumber;
/*      */   }
/*      */ 
/*      */   public void setMessageLevel(int paramInt)
/*      */   {
/* 1042 */     Contract.pre((paramInt >= 0) && (paramInt <= 3));
/*      */ 
/* 1044 */     m_messageLevel = paramInt;
/*      */   }
/*      */ 
/*      */   public final String getPackageVersion()
/*      */   {
/* 1053 */     return PACKAGE_VERSION;
/*      */   }
/*      */ 
/*      */   public String getModelNumber()
/*      */   {
/* 1062 */     return this.m_modelNumber;
/*      */   }
/*      */ 
/*      */   public boolean isDevicePump()
/*      */   {
/* 1071 */     return getDeviceType() == DEVICE_TYPE_PUMP;
/*      */   }
/*      */ 
/*      */   public boolean isDeviceMonitor()
/*      */   {
/* 1080 */     return getDeviceType() == DEVICE_TYPE_MONITOR;
/*      */   }
/*      */ 
/*      */   public boolean isDeviceMeter()
/*      */   {
/* 1089 */     return getDeviceType() == DEVICE_TYPE_METER;
/*      */   }
/*      */ 
/*      */   public final long getLastHistoryPageNumber(String paramString1, String paramString2)
/*      */   {
/* 1104 */     Contract.unreachable();
/* 1105 */     return 0L;
/*      */   }
/*      */ 
/*      */   public final long getLastGlucoseHistoryPageNumber(String paramString1, String paramString2)
/*      */   {
/* 1120 */     Contract.unreachable();
/* 1121 */     return 0L;
/*      */   }
/*      */ 
/*      */   public void allowDeviceOperation(DevicePortReader paramDevicePortReader)
/*      */     throws ConnectToPumpException
/*      */   {
/*      */   }
/*      */ 
/*      */   public void deviceUpdateProgress(int paramInt)
/*      */   {
/* 1143 */     notifyDeviceUpdateProgress(paramInt);
/*      */   }
/*      */ 
/*      */   public void deviceUpdateState(int paramInt, String paramString)
/*      */   {
/* 1155 */     if ((paramInt != 1) && (paramInt != 2))
/*      */     {
/* 1157 */       setState(paramInt);
/*      */     }
/*      */   }
/*      */ 
/*      */   public void deviceUpdatePhase(int paramInt, String paramString)
/*      */   {
/*      */   }
/*      */ 
/*      */   private String autoDetectDeviceIO(DeviceListener paramDeviceListener, String[] paramArrayOfString)
/*      */     throws BadDeviceCommException
/*      */   {
/* 1185 */     String str1 = null;
/* 1186 */     StringBuffer localStringBuffer = new StringBuffer();
/* 1187 */     int i = 0;
/*      */ 
/* 1189 */     setHaltRequested(false);
/* 1190 */     addDeviceListener(paramDeviceListener);
/* 1191 */     setPhase(1);
/*      */ 
/* 1195 */     String str2 = DevicePortReaderFactory.mapLinkDeviceID(this.m_linkDevice);
/* 1196 */     if (str2.equals("UNKNOWN")) {
/* 1197 */       str2 = this.m_description;
/*      */     }
/*      */ 
/* 1201 */     for (int j = 0; (j < paramArrayOfString.length) && (i == 0) && (!isHaltRequested()); j++)
/*      */     {
/*      */       try
/*      */       {
/* 1205 */         str1 = paramArrayOfString[(paramArrayOfString.length - j - 1)];
/* 1206 */         if (j > 0)
/*      */         {
/* 1208 */           localStringBuffer.append(", ");
/*      */         }
/*      */ 
/* 1211 */         localStringBuffer.append(str1);
/* 1212 */         logInfo(this, "autoDetectDeviceIO: testing port '" + str1 + "'");
/*      */ 
/* 1215 */         int k = Integer.parseInt(Util.remainderOf(str1, "COM"));
/* 1216 */         initSerialPort(k);
/*      */         try
/*      */         {
/* 1220 */           if (!isHaltRequested()) {
/* 1221 */             setPhase(7);
/* 1222 */             findDevice(paramDeviceListener);
/*      */ 
/* 1224 */             i = 1;
/* 1225 */             logInfo(this, "autoDetectDeviceIO: " + str2 + " found on port '" + str1 + "'");
/*      */           }
/*      */         }
/*      */         catch (BadDeviceCommException localBadDeviceCommException)
/*      */         {
/*      */         }
/*      */       }
/*      */       catch (IOException localIOException)
/*      */       {
/* 1234 */         logInfo(this, "autoDetectDeviceIO: " + str2 + " NOT found on port '" + str1 + "'; " + "e=" + localIOException);
/*      */       }
/*      */       catch (BadDeviceValueException localBadDeviceValueException)
/*      */       {
/* 1238 */         logInfo(this, "autoDetectDeviceIO: " + str2 + " NOT found on port '" + str1 + "'; " + "e=" + localBadDeviceValueException);
/*      */       }
/*      */       catch (SerialIOHaltedException localSerialIOHaltedException) {
/* 1241 */         removeAllDeviceListeners();
/* 1242 */         throw new BadDeviceCommException("autoDetectDeviceIO: detection has been Halted...");
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1247 */     removeAllDeviceListeners();
/*      */ 
/* 1249 */     if (isHaltRequested()) {
/* 1250 */       throw new BadDeviceCommException("autoDetectDeviceIO: detection has been Halted...");
/*      */     }
/*      */ 
/* 1254 */     if (i == 0) {
/* 1255 */       throw new BadDeviceCommException("autoDetectDeviceIO: could not find " + str2 + " on any port in list '" + localStringBuffer + "'.");
/*      */     }
/*      */ 
/* 1260 */     return str1;
/*      */   }
/*      */ 
/*      */   void beginSerialPort(int paramInt)
/*      */     throws IOException
/*      */   {
/* 1271 */     this.m_serialPortNumber = paramInt;
/* 1272 */     setPhase(2);
/*      */ 
/* 1274 */     if (getRS232Port() != null)
/* 1275 */       getRS232Port().close();
/*      */   }
/*      */ 
/*      */   abstract int getDeviceType();
/*      */ 
/*      */   abstract void findDevice(DeviceListener paramDeviceListener)
/*      */     throws BadDeviceCommException, IOException;
/*      */ 
/*      */   abstract void initSerialPort(int paramInt)
/*      */     throws IOException;
/*      */ 
/*      */   abstract void shutDownSerialPort()
/*      */     throws IOException;
/*      */ 
/*      */   final synchronized void setPhase(int paramInt)
/*      */   {
/* 1320 */     Contract.pre((paramInt >= 1) && (paramInt <= 7));
/*      */ 
/* 1323 */     if (paramInt != m_phase) {
/* 1324 */       m_phase = paramInt;
/* 1325 */       m_phaseText = updateDynamicText(PHASE_TEXT[m_phase]);
/* 1326 */       notifyDeviceUpdatePhase(m_phase, m_phaseText);
/* 1327 */       logInfoLow(this, "setPhase: phase is now " + m_phase + " (" + m_phaseText + ")");
/*      */     }
/*      */   }
/*      */ 
/*      */   final synchronized void setState(int paramInt)
/*      */   {
/* 1339 */     Contract.pre((paramInt >= 1) && (paramInt <= 9));
/*      */ 
/* 1341 */     if (paramInt != m_state) {
/* 1342 */       m_state = paramInt;
/* 1343 */       m_stateText = STATE_TEXT[m_state];
/* 1344 */       notifyDeviceUpdateState(m_state, m_stateText);
/* 1345 */       logInfo(this, "setState: state is now " + m_state + " (" + m_stateText + ")");
/*      */     }
/*      */   }
/*      */ 
/*      */   final synchronized int getState()
/*      */   {
/* 1355 */     return m_state;
/*      */   }
/*      */ 
/*      */   final Snapshot getSnapshot()
/*      */   {
/* 1364 */     return this.m_snapshot;
/*      */   }
/*      */ 
/*      */   static void logInfo(Object paramObject, String paramString)
/*      */   {
/* 1375 */     if (m_messageLevel >= 2)
/* 1376 */       logMessage(paramObject, paramString, "INFO");
/*      */   }
/*      */ 
/*      */   static void logInfoHigh(Object paramObject, String paramString)
/*      */   {
/* 1388 */     if (m_messageLevel >= 3)
/* 1389 */       logMessage(paramObject, paramString, "INFO");
/*      */   }
/*      */ 
/*      */   static void logInfoLow(Object paramObject, String paramString)
/*      */   {
/* 1401 */     if (m_messageLevel >= 1)
/* 1402 */       logMessage(paramObject, paramString, "INFO");
/*      */   }
/*      */ 
/*      */   static void logWarning(Object paramObject, String paramString)
/*      */   {
/* 1413 */     if (m_messageLevel >= 2)
/* 1414 */       logMessage(paramObject, paramString, "WARNING");
/*      */   }
/*      */ 
/*      */   static void logError(Object paramObject, String paramString)
/*      */   {
/* 1425 */     if (m_messageLevel >= 1)
/* 1426 */       logMessage(paramObject, paramString, "ERROR");
/*      */   }
/*      */ 
/*      */   static void setCreateSnapshotTextFile(boolean paramBoolean)
/*      */   {
/* 1439 */     m_createSnapshotTextFile = paramBoolean;
/*      */   }
/*      */ 
/*      */   static void setLogBuffer(PrintWriter paramPrintWriter)
/*      */   {
/* 1448 */     m_logBuffer = paramPrintWriter;
/*      */   }
/*      */ 
/*      */   final Date createTimestamp(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
/*      */     throws BadDeviceValueException
/*      */   {
/* 1467 */     verifyDeviceTimeStamp(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6);
/* 1468 */     GregorianCalendar localGregorianCalendar = new GregorianCalendar(Util.convertYear(paramInt3), paramInt2 - 1, paramInt1, paramInt4, paramInt5, paramInt6);
/*      */ 
/* 1471 */     return localGregorianCalendar.getTime();
/*      */   }
/*      */ 
/*      */   void addDeviceListener(DeviceListener paramDeviceListener)
/*      */   {
/* 1482 */     Contract.pre(paramDeviceListener != null);
/*      */ 
/* 1484 */     this.m_listeners.addElement(paramDeviceListener);
/*      */   }
/*      */ 
/*      */   void removeAllDeviceListeners()
/*      */   {
/* 1493 */     this.m_listeners.removeAllElements();
/*      */   }
/*      */ 
/*      */   void notifyDeviceUpdateProgress(int paramInt)
/*      */   {
/* 1505 */     if ((m_phase != 4) && 
/* 1506 */       (paramInt >= 0) && (paramInt <= PERCENT_100))
/* 1507 */       for (int i = 0; i < this.m_listeners.size(); i++) {
/* 1508 */         DeviceListener localDeviceListener = (DeviceListener)this.m_listeners.elementAt(i);
/* 1509 */         localDeviceListener.deviceUpdateProgress(paramInt);
/*      */       }
/*      */   }
/*      */ 
/*      */   void notifyDeviceUpdateProgress(int paramInt1, int paramInt2)
/*      */   {
/* 1524 */     double d = paramInt1 / paramInt2 * 100.0D;
/*      */ 
/* 1526 */     notifyDeviceUpdateProgress((int)d);
/*      */   }
/*      */ 
/*      */   void notifyDeviceUpdateState(int paramInt, String paramString)
/*      */   {
/* 1536 */     for (int i = 0; i < this.m_listeners.size(); i++) {
/* 1537 */       DeviceListener localDeviceListener = (DeviceListener)this.m_listeners.elementAt(i);
/* 1538 */       localDeviceListener.deviceUpdateState(paramInt, paramString);
/*      */     }
/*      */   }
/*      */ 
/*      */   void notifyDeviceUpdatePhase(int paramInt, String paramString)
/*      */   {
/* 1549 */     for (int i = 0; i < this.m_listeners.size(); i++) {
/* 1550 */       DeviceListener localDeviceListener = (DeviceListener)this.m_listeners.elementAt(i);
/* 1551 */       localDeviceListener.deviceUpdatePhase(paramInt, paramString);
/*      */     }
/*      */   }
/*      */ 
/*      */   final String updateDynamicText(String paramString)
/*      */   {
/* 1562 */     String str1 = "COM" + this.m_serialPortNumber;
/*      */ 
/* 1564 */     String str2 = DevicePortReaderFactory.mapLinkDeviceID(this.m_linkDevice);
/*      */ 
/* 1566 */     paramString = paramString.replaceAll("@commport", str1);
/* 1567 */     paramString = paramString.replaceAll("@linkdevice", str2 == null ? "?" : str2);
/* 1568 */     paramString = paramString.replaceAll("@serialnumber", this.m_serialNumber == null ? "?" : this.m_serialNumber);
/* 1569 */     return paramString;
/*      */   }
/*      */ 
/*      */   void decodeSerialNumber(int[] paramArrayOfInt)
/*      */   {
/* 1580 */     Contract.pre(paramArrayOfInt != null);
/*      */ 
/* 1582 */     this.m_serialNumber = new String(Util.makeByteArray(paramArrayOfInt));
/* 1583 */     this.m_serialNumber = this.m_serialNumber.trim();
/* 1584 */     logInfo(this, "decodeSerialNumber: serial number is " + this.m_serialNumber);
/*      */   }
/*      */ 
/*      */   void decodeFirmwareVersion(int[] paramArrayOfInt)
/*      */   {
/* 1595 */     Contract.pre(paramArrayOfInt != null);
/*      */ 
/* 1597 */     this.m_firmwareVersion = new String(Util.makeByteArray(paramArrayOfInt));
/* 1598 */     this.m_firmwareVersion = this.m_firmwareVersion.trim();
/* 1599 */     logInfo(this, "decodeFirmwareVersion: firmware version is " + this.m_firmwareVersion);
/*      */   }
/*      */ 
/*      */   final CommunicationsLink getCommunicationsLink()
/*      */   {
/* 1608 */     return this.m_communicationsLink;
/*      */   }
/*      */ 
/*      */   final void setCommunicationsLink(CommunicationsLink paramCommunicationsLink)
/*      */   {
/* 1617 */     this.m_communicationsLink = paramCommunicationsLink;
/*      */   }
/*      */ 
/*      */   private void verifyDeviceTimeStamp(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
/*      */     throws BadDeviceValueException
/*      */   {
/* 1636 */     Util.verifyDeviceValue(paramInt1, 1, MAX_DAYS_IN_MONTH, "day");
/* 1637 */     Util.verifyDeviceValue(paramInt2, 1, MONTHS_IN_YEAR, "month");
/* 1638 */     Util.verifyDeviceValue(paramInt3, this.m_minYear, this.m_maxYear, "year");
/* 1639 */     Util.verifyDeviceValue(paramInt4, 0, 23, "hour");
/* 1640 */     Util.verifyDeviceValue(paramInt5, 0, 59, "minute");
/* 1641 */     Util.verifyDeviceValue(paramInt6, 0, 59, "second");
/*      */   }
/*      */ 
/*      */   private static synchronized void logMessage(Object paramObject, String paramString1, String paramString2)
/*      */   {
/* 1655 */     String str1 = Util.convertControlChars(paramString1);
/* 1656 */     if (m_logBuffer != null) {
/* 1657 */       Date localDate = new Date();
/* 1658 */       String str2 = m_dateFormatter.format(localDate);
/* 1659 */       m_logBuffer.println(str2 + " " + paramString2 + " " + paramObject.getClass().getName() + "-" + str1);
/*      */     }
/*      */   }
/*      */ 
/*      */   final synchronized void setHaltRequested(boolean paramBoolean)
/*      */   {
/* 1671 */     m_haltRequested = paramBoolean;
/*      */   }
/*      */ 
/*      */   public final synchronized boolean isHaltRequested()
/*      */   {
/* 1681 */     return m_haltRequested;
/*      */   }
/*      */ 
/*      */   void setRS232Port(SerialPort paramSerialPort)
/*      */   {
/* 1690 */     getCommunicationsLink().setCommPort(paramSerialPort);
/*      */   }
/*      */ 
/*      */   SerialPort getRS232Port()
/*      */   {
/* 1699 */     return (SerialPort)getCommunicationsLink().getCommPort();
/*      */   }
/*      */ 
/*      */   static
/*      */   {
/*  681 */     m_dateFormatter = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss:SSSS");
/*      */   }
/*      */ 
/*      */   static final class Util
/*      */   {
/* 1768 */     private static final Calendar CAL = Calendar.getInstance();
/*      */ 
/*      */     static int[] clone(int[] paramArrayOfInt)
/*      */     {
/* 1783 */       int[] arrayOfInt = new int[paramArrayOfInt.length];
/* 1784 */       for (int i = 0; i < arrayOfInt.length; i++) {
/* 1785 */         arrayOfInt[i] = paramArrayOfInt[i];
/*      */       }
/* 1787 */       return arrayOfInt;
/*      */     }
/*      */ 
/*      */     static void sleepMS(int paramInt)
/*      */     {
/*      */       try
/*      */       {
/* 1797 */         Thread.sleep(paramInt);
/*      */       }
/*      */       catch (InterruptedException localInterruptedException)
/*      */       {
/*      */       }
/*      */     }
/*      */ 
/*      */     static int getLowNibble(int paramInt)
/*      */     {
/* 1811 */       Contract.pre((paramInt >= 0) && (paramInt <= MAX_BYTE));
/*      */ 
/* 1813 */       return paramInt & MAX_NIBBLE;
/*      */     }
/*      */ 
/*      */     static int getHighNibble(int paramInt)
/*      */     {
/* 1825 */       Contract.pre((paramInt >= 0) && (paramInt <= MAX_BYTE));
/*      */ 
/* 1827 */       return paramInt >> BITS_PER_NIBBLE & MAX_NIBBLE;
/*      */     }
/*      */ 
/*      */     static int getLowByte(int paramInt)
/*      */     {
/* 1837 */       return paramInt & MAX_BYTE;
/*      */     }
/*      */ 
/*      */     static int getHighByte(int paramInt)
/*      */     {
/* 1848 */       return paramInt >>> BITS_PER_BYTE & MAX_BYTE;
/*      */     }
/*      */ 
/*      */     static int convertHexToDec(String paramString)
/*      */     {
/* 1860 */       return Integer.valueOf(paramString, BASE16).intValue();
/*      */     }
/*      */ 
/*      */     static int convertHexToASCII(int paramInt)
/*      */     {
/* 1872 */       Contract.pre((paramInt >= 0) && (paramInt <= MAX_NIBBLE));
/*      */ 
/* 1874 */       return paramInt < LF ? paramInt + ASCII_ZERO : paramInt - LF + 'A';
/*      */     }
/*      */ 
/*      */     static String convertControlChars(String paramString)
/*      */     {
/* 1885 */       StringBuffer localStringBuffer = new StringBuffer();
/*      */ 
/* 1889 */       for (int j = 0; j < paramString.length(); j++) {
/* 1890 */         int i = paramString.charAt(j);
/*      */ 
/* 1892 */         if ((i < 32) || (i > 127));
/* 1894 */         switch (i)
/*      */         {
/*      */         case 9:
/* 1897 */           localStringBuffer.append(paramString.charAt(j));
/* 1898 */           localStringBuffer.append("<TAB>");
/* 1899 */           break;
/*      */         case 10:
/* 1901 */           localStringBuffer.append("<LF>");
/* 1902 */           break;
/*      */         case 0:
/* 1904 */           localStringBuffer.append("<NUL>");
/* 1905 */           break;
/*      */         case 6:
/* 1907 */           localStringBuffer.append("<ACK>");
/* 1908 */           break;
/*      */         case 21:
/* 1910 */           localStringBuffer.append("<NAK>");
/* 1911 */           break;
/*      */         case 13:
/* 1913 */           localStringBuffer.append("<CR>");
/* 1914 */           break;
/*      */         case 32:
/* 1916 */           localStringBuffer.append("<SP>");
/* 1917 */           break;
/*      */         case 2:
/* 1919 */           localStringBuffer.append("<STX>");
/* 1920 */           break;
/*      */         case 1:
/* 1922 */           localStringBuffer.append("<STH>");
/* 1923 */           break;
/*      */         case 5:
/* 1925 */           localStringBuffer.append("<ENQ>");
/* 1926 */           break;
/*      */         case 4:
/* 1928 */           localStringBuffer.append("<EOT>");
/* 1929 */           break;
/*      */         case 23:
/* 1931 */           localStringBuffer.append("<ETB>");
/* 1932 */           break;
/*      */         case 3:
/* 1934 */           localStringBuffer.append("<ETX>");
/* 1935 */           break;
/*      */         case 7:
/*      */         case 8:
/*      */         case 11:
/*      */         case 12:
/*      */         case 14:
/*      */         case 15:
/*      */         case 16:
/*      */         case 17:
/*      */         case 18:
/*      */         case 19:
/*      */         case 20:
/*      */         case 22:
/*      */         case 24:
/*      */         case 25:
/*      */         case 26:
/*      */         case 27:
/*      */         case 28:
/*      */         case 29:
/*      */         case 30:
/*      */         case 31:
/*      */         default:
/* 1938 */           String str = '<' + getHex(i) + '>';
/* 1939 */           localStringBuffer.append(str);
/* 1940 */           continue;
/*      */ 
/* 1943 */           //localStringBuffer.append(paramString.charAt(j));
/*      */         }
/*      */       }
/* 1946 */       return new String(localStringBuffer);
/*      */     }
/*      */ 
/*      */     static boolean parseEnable(int paramInt, String paramString)
/*      */       throws BadDeviceValueException
/*      */     {
/* 1959 */       verifyDeviceValue(paramInt, 0, 1, paramString);
/* 1960 */       return paramInt == 1;
/*      */     }
/*      */ 
/*      */     static int[] makePackedBCD(String paramString)
/*      */     {
/* 1975 */       Contract.preNonNull(paramString);
/* 1976 */       Contract.pre(isEven(paramString.length()));
/* 1977 */       paramString = paramString.toLowerCase();
/* 1978 */       for (int i = 0; i < paramString.length(); i++) {
/* 1979 */         char c1 = paramString.charAt(i);
/* 1980 */         Contract.pre((Character.isDigit(c1)) || ((c1 >= 'a') && (c1 <= 'f')));
/*      */       }
/*      */ 
/* 1983 */       int[] arrayOfInt1 = makeIntArray(paramString);
/* 1984 */       int[] arrayOfInt2 = new int[arrayOfInt1.length / 2];
/*      */ 
/* 1986 */       for (int j = 0; j < arrayOfInt2.length; j++) {
/* 1987 */         char c2 = (char)arrayOfInt1[(j * 2)];
/* 1988 */         char c3 = (char)arrayOfInt1[(j * 2 + 1)];
/* 1989 */         int k = c2 - (Character.isDigit(c2) ? '0' : 'W');
/* 1990 */         int m = c3 - (Character.isDigit(c3) ? '0' : 'W');
/* 1991 */         arrayOfInt2[j] = makeByte(k, m);
/*      */       }
/*      */ 
/* 1994 */       return arrayOfInt2;
/*      */     }
/*      */ 
/*      */     static int makeByte(int paramInt1, int paramInt2)
/*      */     {
/* 2010 */       Contract.pre((paramInt1 >= 0) && (paramInt1 <= MAX_NIBBLE));
/* 2011 */       Contract.pre((paramInt2 >= 0) && (paramInt2 <= MAX_NIBBLE));
/*      */ 
/* 2013 */       int i = paramInt1 << BITS_PER_NIBBLE | paramInt2 & MAX_NIBBLE;
/*      */ 
/* 2015 */       Contract.post((i >= 0) && (i <= MAX_BYTE));
/*      */ 
/* 2017 */       return i;
/*      */     }
/*      */ 
/*      */     static long makeLong(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*      */     {
/* 2036 */       Contract.pre((paramInt1 >= 0) && (paramInt1 <= MAX_BYTE));
/* 2037 */       Contract.pre((paramInt2 >= 0) && (paramInt2 <= MAX_BYTE));
/* 2038 */       Contract.pre((paramInt3 >= 0) && (paramInt3 <= MAX_BYTE));
/* 2039 */       Contract.pre((paramInt4 >= 0) && (paramInt4 <= MAX_BYTE));
/*      */ 
/* 2042 */       long l = paramInt1 << (BITS_PER_BYTE * 3) | paramInt2 << (BITS_PER_BYTE * 2) | paramInt3 << BITS_PER_BYTE | paramInt4;
/*      */ 
/* 2047 */       Contract.post((l >= 0L) && (l <= MAX_DWORD));
/*      */ 
/* 2049 */       return l;
/*      */     }
/*      */ 
/*      */     static double toWholeUnits(int paramInt)
/*      */     {
/* 2059 */       return paramInt / 10.0D;
/*      */     }
/*      */ 
/*      */     static int makeInt(int paramInt1, int paramInt2)
/*      */     {
/* 2073 */       Contract.pre((paramInt1 >= 0) && (paramInt1 <= MAX_BYTE));
/* 2074 */       Contract.pre((paramInt2 >= 0) && (paramInt2 <= MAX_BYTE));
/*      */ 
/* 2076 */       int i = (paramInt1 & MAX_BYTE) << BITS_PER_BYTE | paramInt2 & MAX_BYTE;
/*      */ 
/* 2078 */       Contract.post((i >= 0) && (i <= MAX_WORD));
/* 2079 */       return i;
/*      */     }
/*      */ 
/*      */     static int makeInt(int paramInt1, int paramInt2, int paramInt3)
/*      */     {
/* 2096 */       Contract.pre((paramInt1 >= 0) && (paramInt1 <= MAX_BYTE));
/* 2097 */       Contract.pre((paramInt2 >= 0) && (paramInt2 <= MAX_BYTE));
/* 2098 */       Contract.pre((paramInt3 >= 0) && (paramInt3 <= MAX_BYTE));
/*      */ 
/* 2100 */       int i = (paramInt1 & MAX_BYTE) << (2 * BITS_PER_BYTE) | (paramInt2 & MAX_BYTE) << BITS_PER_BYTE | paramInt3 & MAX_BYTE;
/*      */ 
/* 2103 */       Contract.post((i >= 0) && (i <= MAX_BYTE_3));
/*      */ 
/* 2105 */       return i;
/*      */     }
/*      */ 
/*      */     static int convertUnsignedByteToInt(byte paramByte)
/*      */     {
/* 2118 */       return paramByte & MAX_BYTE;
/*      */     }
/*      */ 
/*      */     static long convertUnsignedIntToLong(int paramInt)
/*      */     {
/* 2131 */       return paramInt & 0xFFFFFFFF;
/*      */     }
/*      */ 
/*      */     static byte[] convertIntsToBytes(int[] paramArrayOfInt)
/*      */     {
/* 2143 */       Contract.preNonNull(paramArrayOfInt);
/* 2144 */       byte[] arrayOfByte = new byte[paramArrayOfInt.length];
/*      */ 
/* 2147 */       for (int i = 0; i < paramArrayOfInt.length; i++) {
/* 2148 */         Contract.pre(paramArrayOfInt[i] >> BITS_PER_BYTE == 0);
/* 2149 */         arrayOfByte[i] = (byte)(paramArrayOfInt[i] & MAX_BYTE);
/*      */       }
/* 2151 */       return arrayOfByte;
/*      */     }
/*      */ 
/*      */     static int makeUnsignedShort(int paramInt1, int paramInt2)
/*      */     {
/* 2165 */       Contract.pre((paramInt1 >= 0) && (paramInt1 <= MAX_BYTE));
/* 2166 */       Contract.pre((paramInt2 >= 0) && (paramInt2 <= MAX_BYTE));
/*      */ 
/* 2168 */       int i = (paramInt1 & MAX_BYTE) << BITS_PER_BYTE | paramInt2 & MAX_BYTE;
/*      */ 
/* 2170 */       Contract.post((i >= 0) && (i <= MAX_WORD));
/* 2171 */       return i;
/*      */     }
/*      */ 
/*      */     static byte[] makeByteArray(int[] paramArrayOfInt)
/*      */     {
/* 2182 */       if (paramArrayOfInt != null) {
/* 2183 */         byte[] arrayOfByte = new byte[paramArrayOfInt.length];
/*      */ 
/* 2185 */         for (int i = 0; i < paramArrayOfInt.length; i++) {
/* 2186 */           Contract.pre(paramArrayOfInt[i], -128, 127);
/* 2187 */           arrayOfByte[i] = (byte)paramArrayOfInt[i];
/*      */         }
/* 2189 */         return arrayOfByte;
/*      */       }
/* 2191 */       return null;
/*      */     }
/*      */ 
/*      */     static int[] makeIntArray(byte[] paramArrayOfByte)
/*      */     {
/* 2202 */       Contract.preNonNull(paramArrayOfByte);
/* 2203 */       int[] arrayOfInt = new int[paramArrayOfByte.length];
/*      */ 
/* 2206 */       for (int i = 0; i < paramArrayOfByte.length; i++) {
/* 2207 */         paramArrayOfByte[i] &= MAX_BYTE;
/*      */       }
/* 2209 */       return arrayOfInt;
/*      */     }
/*      */ 
/*      */     static String makeString(int[] paramArrayOfInt)
/*      */     {
/* 2220 */       return makeString(paramArrayOfInt, 0, paramArrayOfInt.length);
/*      */     }
/*      */ 
/*      */     static String makeString(int[] paramArrayOfInt, int paramInt1, int paramInt2)
/*      */     {
/* 2234 */       Contract.pre(paramArrayOfInt.length >= paramInt1 + paramInt2);
/* 2235 */       if (paramArrayOfInt != null) {
/* 2236 */         StringBuffer localStringBuffer = new StringBuffer();
/*      */ 
/* 2238 */         for (int i = paramInt1; i < paramInt1 + paramInt2; i++)
/*      */         {
/* 2240 */           if (paramArrayOfInt[i] != 0) {
/* 2241 */             localStringBuffer.append((char)paramArrayOfInt[i]);
/*      */           }
/*      */         }
/*      */ 
/* 2245 */         return new String(localStringBuffer);
/*      */       }
/* 2247 */       return null;
/*      */     }
/*      */ 
/*      */     static int[] makeIntArray(String paramString)
/*      */     {
/* 2258 */       if (paramString != null) {
/* 2259 */         int[] arrayOfInt = new int[paramString.length()];
/*      */ 
/* 2261 */         for (int i = 0; i < arrayOfInt.length; i++) {
/* 2262 */           arrayOfInt[i] = paramString.charAt(i);
/*      */         }
/* 2264 */         return arrayOfInt;
/*      */       }
/* 2266 */       return null;
/*      */     }
/*      */ 
/*      */     static int[] concat(int[] paramArrayOfInt1, int[] paramArrayOfInt2)
/*      */     {
/* 2281 */       Contract.pre(paramArrayOfInt1 != null);
/* 2282 */       Contract.pre(paramArrayOfInt2 != null);
/*      */ 
/* 2284 */       int[] arrayOfInt = new int[paramArrayOfInt1.length + paramArrayOfInt2.length];
/* 2285 */       System.arraycopy(paramArrayOfInt1, 0, arrayOfInt, 0, paramArrayOfInt1.length);
/* 2286 */       System.arraycopy(paramArrayOfInt2, 0, arrayOfInt, paramArrayOfInt1.length, paramArrayOfInt2.length);
/* 2287 */       return arrayOfInt;
/*      */     }
/*      */ 
/*      */     static String remainderOf(String paramString1, String paramString2)
/*      */       throws BadDeviceValueException
/*      */     {
/* 2303 */       int i = paramString1.indexOf(paramString2);
/*      */ 
/* 2305 */       if (i != SUBSTRING_NOT_FOUND) {
/* 2306 */         int j = i + paramString2.length();
/* 2307 */         if (paramString1.length() > j) {
/* 2308 */           return paramString1.substring(j);
/*      */         }
/* 2310 */         throw new BadDeviceValueException("Nothing following '" + paramString2 + "' in string '" + paramString1 + "'");
/*      */       }
/*      */ 
/* 2314 */       throw new BadDeviceValueException("Can't find '" + paramString2 + "' in string '" + paramString1 + "'");
/*      */     }
/*      */ 
/*      */     static int computeCRC7(int[] paramArrayOfInt, int startIndex, int length)
/*      */     {
/* 2332 */       int i = SEVEN_LSB;
/*      */ 
/* 2334 */       Contract.pre(paramArrayOfInt != null);
/* 2335 */       Contract.pre(paramArrayOfInt.length >= startIndex + length);
/*      */ 
/* 2337 */       for (int j = 0; j < length; j++) {
/* 2338 */         i = MedicalDevice.CRC7_LOOKUP_TABLE[i] ^ paramArrayOfInt[(j + startIndex)];
/*      */       }
/*      */ 
/* 2341 */       return i;
/*      */     }
/*      */ 
/*      */     static int computeCRC8(int[] paramArrayOfInt, int startIndex, int length)
/*      */     {
/* 2360 */       int i = 0;
/*      */ 
/* 2362 */       Contract.pre(paramArrayOfInt != null);
/* 2363 */       Contract.pre(paramArrayOfInt.length >= startIndex + length);
/*      */ 
/* 2365 */       for (int j = 0; j < length; j++) {
/* 2366 */         i = MedicalDevice.CRC8_LOOKUP_TABLE[((i ^ paramArrayOfInt[(j + startIndex)]) & MAX_BYTE)];
/*      */       }
/*      */ 
/* 2369 */       return i;
/*      */     }
/*      */ 
/*      */     static int computeCRC8BD(int[] paramArrayOfInt, int startIndex, int length)
/*      */     {
/* 2390 */       int i = 0;
/*      */ 
/* 2392 */       Contract.pre(paramArrayOfInt != null);
/* 2393 */       Contract.pre(paramArrayOfInt.length >= startIndex + length);
/*      */ 
/* 2395 */       for (int j = 0; j < length; j++) {
/* 2396 */         i = MedicalDevice.CRC8_LOOKUP_TABLE_BD[((i ^ paramArrayOfInt[(j + startIndex)]) & MAX_BYTE)];
/*      */       }
/*      */ 
/* 2399 */       return i;
/*      */     }
/*      */ 
/*      */     static int computeCRC8E1381(int[] paramArrayOfInt)
/*      */     {
/* 2415 */       int i = 0;
/*      */ 
/* 2417 */       Contract.pre(paramArrayOfInt != null);
/* 2418 */       Contract.pre(paramArrayOfInt.length >= 2);
/*      */ 
/* 2420 */       int j = 0;
/* 2421 */       int k = 0;
/* 2422 */       int m = 0;
/* 2423 */       int n = 0;
/*      */ 
/* 2425 */       for (int i1 = 0; (i1 < paramArrayOfInt.length) && (m == 0); i1++) {
/* 2426 */         switch (paramArrayOfInt[i1]) {
/*      */         case 2:
/* 2428 */           j = 1;
/* 2429 */           break;
/*      */         case 23:
/* 2431 */           k = 1;
/* 2432 */           n = paramArrayOfInt[i1];
/* 2433 */           break;
/*      */         case 3:
/* 2435 */           k = 1;
/* 2436 */           n = paramArrayOfInt[i1];
/* 2437 */           break;
/*      */         default:
/* 2439 */           if ((j != 0) && (k == 0))
/*      */           {
/* 2441 */             i += paramArrayOfInt[i1];
/* 2442 */             i &= 255; } else {
/* 2443 */             if ((j == 0) || (k == 0))
/*      */               continue;
/* 2445 */             i += n;
/* 2446 */             i &= 255;
/* 2447 */             m = 1;
/*      */           }
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/* 2453 */       return i;
/*      */     }
/*      */ 
/*      */     static int computeCRC16CCITT(int[] paramArrayOfInt, int paramInt1, int paramInt2)
/*      */     {
/* 2469 */       Contract.pre(paramArrayOfInt != null);
/* 2470 */       Contract.pre(paramArrayOfInt.length >= paramInt1 + paramInt2);
/*      */ 
/* 2472 */       int i = MAX_WORD;
/*      */ 
/* 2474 */       for (int k = paramInt1; k < paramInt1 + paramInt2; k++) {
/* 2475 */         int j = paramArrayOfInt[k] ^ i >> 8;
/* 2476 */         i = (MedicalDevice.CRC16_CCITT_LOOKUP_TABLE[j] ^ i << 8) & MAX_WORD;
/*      */       }
/*      */ 
/* 2480 */       return i;
/*      */     }
/*      */ 
/*      */     static int computeCRC16sum(int[] paramArrayOfInt, int paramInt1, int paramInt2)
/*      */     {
/* 2495 */       Contract.pre(paramArrayOfInt != null);
/* 2496 */       Contract.pre(paramArrayOfInt.length >= paramInt1 + paramInt2);
/*      */ 
/* 2498 */       int i = 0;
/*      */ 
/* 2500 */       for (int j = paramInt1; j < paramInt1 + paramInt2; j++) {
/* 2501 */         i = i + paramArrayOfInt[j] & 0xFFFF;
/*      */       }
/*      */ 
/* 2504 */       return i;
/*      */     }
/*      */ 
/*      */     static int computeCRC8XOR(int paramInt1, String paramString, int paramInt2, int paramInt3)
/*      */     {
/* 2520 */       Contract.preNonNull(paramString);
/* 2521 */       Contract.pre(paramString.length() >= paramInt2 + paramInt3);
/*      */ 
/* 2523 */       int i = paramInt1;
/*      */ 
/* 2525 */       for (int j = paramInt2; j < paramInt2 + paramInt3; j++) {
/* 2526 */         i = (i ^ paramString.charAt(j)) & 0xFF;
/*      */       }
/* 2528 */       return i;
/*      */     }
/*      */ 
/*      */     static boolean isCRC8E1381Valid(String paramString)
/*      */     {
/* 2542 */       int i = 0;
/*      */ 
/* 2544 */       Contract.pre(paramString != null);
/* 2545 */       Contract.pre(paramString.length() >= 2);
/*      */       String str;
/*      */       try {
/* 2552 */         str = remainderOf(paramString, String.valueOf('\003'));
/*      */       }
/*      */       catch (BadDeviceValueException localBadDeviceValueException1) {
/*      */         try {
/* 2556 */           str = remainderOf(paramString, String.valueOf('\027'));
/*      */         } catch (BadDeviceValueException localBadDeviceValueException2) {
/* 2558 */           return i;
/*      */         }
/*      */       }
/*      */ 
/* 2562 */       if (str.length() > 1) {
/* 2563 */         int j = str.charAt(0);
/* 2564 */         int k = str.charAt(1);
/* 2565 */         int m = computeCRC8E1381(makeIntArray(paramString));
/* 2566 */         int n = convertHexToASCII(getHighNibble(m));
/* 2567 */         int i1 = convertHexToASCII(getLowNibble(m));
/*      */ 
/* 2569 */         if ((n == j) && (i1 == k)) {
/* 2570 */           i = 1;
/*      */         }
/*      */       }
/*      */ 
/* 2574 */       return i;
/*      */     }
/*      */ 
/*      */     static boolean isEven(int paramInt)
/*      */     {
/* 2584 */       return paramInt % 2 == 0;
/*      */     }
/*      */ 
/*      */     static boolean isOdd(int paramInt)
/*      */     {
/* 2594 */       return !isEven(paramInt);
/*      */     }
/*      */ 
/*      */     static boolean isZeros(int[] paramArrayOfInt)
/*      */     {
/* 2605 */       if (paramArrayOfInt != null) {
/* 2606 */         for (int i = 0; i < paramArrayOfInt.length; i++) {
/* 2607 */           if (paramArrayOfInt[i] != 0) {
/* 2608 */             return false;
/*      */           }
/*      */         }
/* 2611 */         return true;
/*      */       }
/* 2613 */       return false;
/*      */     }
/*      */ 
/*      */     static void verifyDeviceValue(int paramInt1, int paramInt2, int paramInt3, String paramString)
/*      */       throws BadDeviceValueException
/*      */     {
/* 2628 */       if ((paramInt1 < paramInt2) || (paramInt1 > paramInt3))
/* 2629 */         throw new BadDeviceValueException("The value of " + paramInt1 + " for '" + paramString + "' is out of range; " + "must be " + paramInt2 + " to " + paramInt3);
/*      */     }
/*      */ 
/*      */     static void verifyDeviceValue(double paramDouble1, double paramDouble2, double paramDouble3, String paramString)
/*      */       throws BadDeviceValueException
/*      */     {
/* 2647 */       if ((paramDouble1 < paramDouble2) || (paramDouble1 > paramDouble3))
/* 2648 */         throw new BadDeviceValueException("The value of " + paramDouble1 + " for '" + paramString + "' is out of range; " + "must be " + paramDouble2 + " to " + paramDouble3);
/*      */     }
/*      */ 
/*      */     static void verifyDeviceValue(boolean paramBoolean, String paramString)
/*      */       throws BadDeviceValueException
/*      */     {
/* 2664 */       if (!paramBoolean)
/* 2665 */         throw new BadDeviceValueException("Condition failed: '" + paramString + "'");
/*      */     }
/*      */ 
/*      */     static int convertYear(int paramInt)
/*      */       throws BadDeviceValueException
/*      */     {
/* 2679 */       verifyDeviceValue(paramInt, 0, 9999, "year");
/*      */ 
/* 2681 */       if ((paramInt == 97) || (paramInt == 98) || (paramInt == 99)) {
/* 2682 */         paramInt += 1900;
/*      */       }
/* 2684 */       return paramInt > 1000 ? paramInt : paramInt + BASE_YEAR;
/*      */     }
/*      */ 
/*      */     static Date createDate(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
/*      */     {
/* 2706 */       Contract.pre(paramInt1, 1, 31);
/* 2707 */       Contract.pre(paramInt2, 1, 12);
/* 2708 */       Contract.pre(paramInt3, 2000, 2099);
/* 2709 */       Contract.pre(paramInt4, 0, 23);
/* 2710 */       Contract.pre(paramInt5, 0, 59);
/* 2711 */       Contract.pre(paramInt6, 0, 59);
/*      */ 
/* 2713 */       return createDateSync(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6);
/*      */     }
/*      */ 
/*      */     static String getHex(long paramLong)
/*      */     {
/* 2726 */       String str = paramLong == -1L ? "" : "0x";
/* 2727 */       return str + getHexCompact(paramLong);
/*      */     }
/*      */ 
/*      */     static String getHex(int paramInt)
/*      */     {
/* 2740 */       String str = paramInt == -1 ? "" : "0x";
/* 2741 */       return str + getHexCompact(paramInt);
/*      */     }
/*      */ 
/*      */     static String getHex(byte paramByte)
/*      */     {
/* 2754 */       String str = paramByte == -1 ? "" : "0x";
/* 2755 */       return str + getHexCompact(paramByte);
/*      */     }
/*      */ 
/*      */     static String getHex(byte[] paramArrayOfByte, int paramInt)
/*      */     {
/* 2767 */       StringBuffer localStringBuffer = new StringBuffer();
/*      */ 
/* 2769 */       if (paramArrayOfByte != null)
/*      */       {
/* 2771 */         paramInt = Math.min(paramInt, paramArrayOfByte.length);
/*      */ 
/* 2773 */         for (int i = 0; i < paramInt; i++) {
/* 2774 */           localStringBuffer.append(getHex(paramArrayOfByte[i]));
/*      */ 
/* 2776 */           if (i < paramInt - 1) {
/* 2777 */             localStringBuffer.append(" ");
/*      */           }
/*      */         }
/*      */       }
/*      */ 
/* 2782 */       return new String(localStringBuffer);
/*      */     }
/*      */ 
/*      */     static String getHex(int[] paramArrayOfInt, int paramInt)
/*      */     {
/* 2796 */       StringBuffer localStringBuffer = new StringBuffer();
/*      */ 
/* 2798 */       if (paramArrayOfInt != null)
/*      */       {
/* 2800 */         paramInt = Math.min(paramInt, paramArrayOfInt.length);
/*      */ 
/* 2802 */         for (int i = 0; i < paramInt; i++) {
/* 2803 */           localStringBuffer.append(getHex(paramArrayOfInt[i]));
/*      */ 
/* 2805 */           if (i < paramInt - 1) {
/* 2806 */             localStringBuffer.append(" ");
/*      */           }
/*      */         }
/*      */       }
/*      */ 
/* 2811 */       return new String(localStringBuffer);
/*      */     }
/*      */ 
/*      */     static String getHex(byte[] paramArrayOfByte)
/*      */     {
/* 2822 */       return paramArrayOfByte == null ? null : getHex(paramArrayOfByte, paramArrayOfByte.length);
/*      */     }
/*      */ 
/*      */     static String getHex(int[] paramArrayOfInt)
/*      */     {
/* 2833 */       return paramArrayOfInt == null ? null : getHex(paramArrayOfInt, paramArrayOfInt.length);
/*      */     }
/*      */ 
/*      */     static String getHexCompact(long paramLong)
/*      */     {
/* 2846 */       String str1 = Long.toHexString(paramLong).toUpperCase();
/*      */ 
/* 2848 */       String str2 = isOdd(str1.length()) ? "0" : "";
/* 2849 */       return str2 + str1;
/*      */     }
/*      */ 
/*      */     static String getHexCompact(int paramInt)
/*      */     {
/* 2862 */       long l = paramInt == -1 ? paramInt : convertUnsignedIntToLong(paramInt);
/* 2863 */       return getHexCompact(l);
/*      */     }
/*      */ 
/*      */     static String getHexCompact(byte paramByte)
/*      */     {
/* 2876 */       int i = paramByte == -1 ? paramByte : convertUnsignedByteToInt(paramByte);
/* 2877 */       return getHexCompact(i);
/*      */     }
/*      */ 
/*      */     static String getHexCompact(byte[] paramArrayOfByte, int paramInt)
/*      */     {
/* 2889 */       StringBuffer localStringBuffer = new StringBuffer();
/*      */ 
/* 2891 */       if (paramArrayOfByte != null)
/*      */       {
/* 2893 */         paramInt = Math.min(paramInt, paramArrayOfByte.length);
/*      */ 
/* 2895 */         for (int i = 0; i < paramInt; i++) {
/* 2896 */           localStringBuffer.append(getHexCompact(paramArrayOfByte[i]));
/*      */ 
/* 2898 */           if (i < paramInt - 1) {
/* 2899 */             localStringBuffer.append(" ");
/*      */           }
/*      */         }
/*      */       }
/*      */ 
/* 2904 */       return new String(localStringBuffer);
/*      */     }
/*      */ 
/*      */     static String getHexCompact(int[] paramArrayOfInt, int paramInt)
/*      */     {
/* 2918 */       StringBuffer localStringBuffer = new StringBuffer();
/*      */ 
/* 2920 */       if (paramArrayOfInt != null)
/*      */       {
/* 2922 */         paramInt = Math.min(paramInt, paramArrayOfInt.length);
/*      */ 
/* 2924 */         for (int i = 0; i < paramInt; i++) {
/* 2925 */           localStringBuffer.append(getHexCompact(paramArrayOfInt[i]));
/*      */ 
/* 2927 */           if (i < paramInt - 1) {
/* 2928 */             localStringBuffer.append(" ");
/*      */           }
/*      */         }
/*      */       }
/*      */ 
/* 2933 */       return new String(localStringBuffer);
/*      */     }
/*      */ 
/*      */     static String getHexCompact(byte[] paramArrayOfByte)
/*      */     {
/* 2944 */       if (paramArrayOfByte != null) {
/* 2945 */         return getHexCompact(paramArrayOfByte, paramArrayOfByte.length);
/*      */       }
/* 2947 */       return null;
/*      */     }
/*      */ 
/*      */     static String getHexCompact(int[] paramArrayOfInt)
/*      */     {
/* 2958 */       if (paramArrayOfInt != null) {
/* 2959 */         return getHexCompact(paramArrayOfInt, paramArrayOfInt.length);
/*      */       }
/* 2961 */       return null;
/*      */     }
/*      */ 
/*      */     private static synchronized Date createDateSync(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
/*      */     {
/* 2979 */       CAL.clear();
/* 2980 */       CAL.set(paramInt3, paramInt2 - 1, paramInt1, paramInt4, paramInt5, paramInt6);
/* 2981 */       return CAL.getTime();
/*      */     }
/*      */   }
/*      */ 
/*      */   abstract class SnapshotCreator
/*      */   {
/*      */     SnapshotCreator(int arg2)
/*      */     {
/*      */       int i;
/* 1722 */       MedicalDevice.access$002(MedicalDevice.this, i);
/*      */     }
/*      */ 
/*      */     InputStream createSnapshot()
/*      */       throws BadDeviceValueException, IOException
/*      */     {
/* 1741 */       createSnapshotBody();
/*      */ 
/* 1744 */       int i = MedicalDevice.this.m_snapshot.write();
/* 1745 */       MedicalDevice.logInfo(this, "createSnapshot: wrote " + i + " bytes.");
/*      */ 
/* 1747 */       if (i < MedicalDevice.this.m_snapshotByteCount) {
/* 1748 */         throw new BadDeviceValueException("Resulting snapshot size is invalid: " + i + "; must be at least " + MedicalDevice.this.m_snapshotByteCount + " bytes.");
/*      */       }
/*      */ 
/* 1752 */       return MedicalDevice.this.m_snapshot.toInputStream();
/*      */     }
/*      */ 
/*      */     abstract void createSnapshotBody();
/*      */   }
/*      */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.deviceportreader.MedicalDevice
 * JD-Core Version:    0.6.0
 */