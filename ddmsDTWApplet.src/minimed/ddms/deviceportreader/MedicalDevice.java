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
/*      */   static final String PACKAGE_VERSION = "10.0.0";
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
/*      */   static final byte Ô = 16;
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
/*  218 */   static final Integer NO_DOW = null;
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
/*  353 */   static final int[] CRC8_LOOKUP_TABLE = { 0, 155, 173, 54, 193, 90, 108, 247, 25, 130, 180, 47, 216, 67, 117, 238, 50, 169, 159, 4, 243, 104, 94, 197, 43, 176, 134, 29, 234, 113, 71, 220, 100, 255, 201, 82, 165, 62, 8, 147, 125, 230, 208, 75, 188, 39, 17, 138, 86, 205, 251, 96, 151, 12, 58, 161, 79, 212, 226, 121, 142, 21, 35, 184, 200, 83, 101, 254, 9, 146, 164, 63, 209, 74, 124, 231, 16, 139, 189, 38, 250, 97, 87, 204, 59, 160, 150, 13, 227, 120, 78, 213, 34, 185, 143, 20, 172, 55, 1, 154, 109, 246, 192, 91, 181, 46, 24, 131, 116, 239, 217, 66, 158, 5, 51, 168, 95, 196, 242, 105, 135, 28, 42, 177, 70, 221, 235, 112, 11, 144, 166, 61, 202, 81, 103, 252, 18, 137, 191, 36, 211, 72, 126, 229, 57, 162, 148, 15, 248, 99, 85, 206, 32, 187, 141, 22, 225, 122, 76, 215, 111, 244, 194, 89, 174, 53, 3, 152, 118, 237, 219, 64, 183, 44, 26, 129, 93, 198, 240, 107, 156, 7, 49, 170, 68, 223, 233, 114, 133, 30, 40, 179, 195, 88, 110, 245, 2, 153, 175, 52, 218, 65, 119, 236, 27, 128, 182, 45, 241, 106, 92, 199, 48, 171, 157, 6, 232, 115, 69, 222, 41, 178, 132, 31, 167, 60, 10, 145, 102, 253, 203, 80, 190, 37, 19, 136, 127, 228, 210, 73, 149, 14, 56, 163, 84, 207, 249, 98, 140, 23, 33, 186, 77, 214, 224, 123 };
/*      */
/*  391 */   static final int[] CRC8_LOOKUP_TABLE_BD = { 0, 94, 188, 226, 97, 63, 221, 131, 194, 156, 126, 32, 163, 253, 31, 65, 157, 195, 33, 127, 252, 162, 64, 30, 95, 1, 227, 189, 62, 96, 130, 220, 35, 125, 159, 193, 66, 28, 254, 160, 225, 191, 93, 3, 128, 222, 60, 98, 190, 224, 2, 92, 223, 129, 99, 61, 124, 34, 192, 158, 29, 67, 161, 255, 70, 24, 250, 164, 39, 121, 155, 197, 132, 218, 56, 102, 229, 187, 89, 7, 219, 133, 103, 57, 186, 228, 6, 88, 25, 71, 165, 251, 120, 38, 196, 154, 101, 59, 217, 135, 4, 90, 184, 230, 167, 249, 27, 69, 198, 152, 122, 36, 248, 166, 68, 26, 153, 199, 37, 123, 58, 100, 134, 216, 91, 5, 231, 185, 140, 210, 48, 110, 237, 179, 81, 15, 78, 16, 242, 172, 47, 113, 147, 205, 17, 79, 173, 243, 112, 46, 204, 146, 211, 141, 111, 49, 178, 236, 14, 80, 175, 241, 19, 77, 206, 144, 114, 44, 109, 51, 209, 143, 12, 82, 176, 238, 50, 108, 142, 208, 83, 13, 239, 177, 240, 174, 76, 18, 145, 207, 45, 115, 202, 148, 118, 40, 171, 245, 23, 73, 8, 86, 180, 234, 105, 55, 213, 139, 87, 9, 235, 181, 54, 104, 138, 212, 149, 203, 41, 119, 244, 170, 72, 22, 233, 183, 85, 11, 136, 214, 52, 106, 43, 117, 151, 201, 74, 20, 246, 168, 116, 42, 200, 150, 21, 75, 169, 247, 182, 232, 10, 84, 215, 137, 107, 53 };
/*      */ 
/*  415 */   static final int[] CRC16_CCITT_LOOKUP_TABLE = { 0, 4129, 8258, 12387, 16516, 20645, 24774, 28903, 33032, 37161, 41290, 45419, 49548, 53677, 57806, 61935, 4657, 528, 12915, 8786, 21173, 17044, 29431, 25302, 37689, 33560, 45947, 41818, 54205, 50076, 62463, 58334, 9314, 13379, 1056, 5121, 25830, 29895, 17572, 21637, 42346, 46411, 34088, 38153, 58862, 62927, 50604, 54669, 13907, 9842, 5649, 1584, 30423, 26358, 22165, 18100, 46939, 42874, 38681, 34616, 63455, 59390, 55197, 51132, 18628, 22757, 26758, 30887, 2112, 6241, 10242, 14371, 51660, 55789, 59790, 63919, 35144, 39273, 43274, 47403, 23285, 19156, 31415, 27286, 6769, 2640, 14899, 10770, 56317, 52188, 64447, 60318, 39801, 35672, 47931, 43802, 27814, 31879, 19684, 23749, 11298, 15363, 3168, 7233, 60846, 64911, 52716, 56781, 44330, 48395, 36200, 40265, 32407, 28342, 24277, 20212, 15891, 11826, 7761, 3696, 65439, 61374, 57309, 53244, 48923, 44858, 40793, 36728, 37256, 33193, 45514, 41451, 53516, 49453, 61774, 57711, 4224, 161, 12482, 8419, 20484, 16421, 28742, 24679, 33721, 37784, 41979, 46042, 49981, 54044, 58239, 62302, 689, 4752, 8947, 13010, 16949, 21012, 25207, 29270, 46570, 42443, 38312, 34185, 62830, 58703, 54572, 50445, 13538, 9411, 5280, 1153, 29798, 25671, 21540, 17413, 42971, 47098, 34713, 38840, 59231, 63358, 50973, 55100, 9939, 14066, 1681, 5808, 26199, 30326, 17941, 22068, 55628, 51565, 63758, 59695, 39368, 35305, 47498, 43435, 22596, 18533, 30726, 26663, 6336, 2273, 14466, 10403, 52093, 56156, 60223, 64286, 35833, 39896, 43963, 48026, 19061, 23124, 27191, 31254, 2801, 6864, 10931, 14994, 64814, 60687, 56684, 52557, 48554, 44427, 40424, 36297, 31782, 27655, 23652, 19525, 15522, 11395, 7392, 3265, 61215, 65342, 53085, 57212, 44955, 49082, 36825, 40952, 28183, 32310, 20053, 24180, 11923, 16050, 3793, 7920 };
/*      */ 
/*  457 */   static final int[] CRC7_LOOKUP_TABLE = { 0, 26, 52, 46, 104, 114, 92, 70, 93, 71, 105, 115, 53, 47, 1, 27, 55, 45, 3, 25, 95, 69, 107, 113, 106, 112, 94, 68, 2, 24, 54, 44, 110, 116, 90, 64, 6, 28, 50, 40, 51, 41, 7, 29, 91, 65, 111, 117, 89, 67, 109, 119, 49, 43, 5, 31, 4, 30, 48, 42, 108, 118, 88, 66, 81, 75, 101, 127, 57, 35, 13, 23, 12, 22, 56, 34, 100, 126, 80, 74, 102, 124, 82, 72, 14, 20, 58, 32, 59, 33, 15, 21, 83, 73, 103, 125, 63, 37, 11, 17, 87, 77, 99, 121, 98, 120, 86, 76, 10, 16, 62, 36, 8, 18, 60, 38, 96, 122, 84, 78, 85, 79, 97, 123, 61, 39, 9, 19, 47, 53, 27, 1, 71, 93, 115, 105, 114, 104, 70, 92, 26, 0, 46, 52, 24, 2, 44, 54, 112, 106, 68, 94, 69, 95, 113, 107, 45, 55, 25, 3, 65, 91, 117, 111, 41, 51, 29, 7, 28, 6, 40, 50, 116, 110, 64, 90, 118, 108, 66, 88, 30, 4, 42, 48, 43, 49, 31, 5, 67, 89, 119, 109, 126, 100, 74, 80, 22, 12, 34, 56, 35, 57, 23, 13, 75, 81, 127, 101, 73, 83, 125, 103, 33, 59, 21, 15, 20, 14, 32, 58, 124, 102, 72, 82, 16, 10, 36, 62, 120, 98, 76, 86, 77, 87, 121, 99, 37, 63, 17, 11, 39, 61, 19, 9, 79, 85, 123, 97, 122, 96, 78, 84, 18, 8, 38, 60 };
/*      */   static final int MAX_AUTO_OFF_DUR = 16;
/*  483 */   private static final String[] MAX_PORT_LIST = { "COM1", "COM2", "COM3", "COM4", "COM5", "COM6", "COM7", "COM8", "COM9", "usbserial", "usbserial1", "usbserial2", "usbserial3", "usbserial4", "usbserial5", "usbserial6", "usbserial7", "usbserial8", "usbserial9" };
/*      */   private static final int MIN_YEAR = 1997;
/*      */   private static final int MAX_YEAR = 2096;
/*      */   private static final String w = "USB";
/*  496 */   private static boolean m_createSnapshotTextFile = false;
/*      */ 
/*  501 */   private static PrintWriter m_messageLevel = null;
/*      */ 
/*  506 */   private static int m_messageLevel = 0;
/*      */ 
/*  511 */   private static SimpleDateFormat m_dateFormatter = null;
/*      */   static final int PERCENT_100 = 100;
/*  518 */   static String m_lastCommandDescription = " ";
/*      */ 
/*  523 */   static boolean m_haltRequested = false;
/*      */ 
/*  528 */   static int m_state = 0;
/*      */ 
/*  533 */   static String m_stateText = "0";
/*      */ 
/*  538 */   static int m_phase = 0;
/*      */ 
/*  543 */   static String m_phaseText = "0";
/*      */   static final int DEFAULT_MAX_RETRIES = 2;
/*      */   private CommunicationsLink m_communicationsLink;
/*      */   static final int DEVICE_TYPE_PUMP = 1;
/*      */   static final int DEVICE_TYPE_MONITOR = 2;
/*      */   static final int DEVICE_TYPE_METER = 3;
/*      */   TraceHistorySet m_traceHistorySet;
/*      */   String u;
/*  574 */   int m_linkDevice = 0;
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
/*  668 */   private final Vector m_listeners = new Vector(10);
/*      */ 
/*      */   MedicalDevice()
/*      */   {
/*  679 */     this.m_minYear = 1997;
/*  680 */     this.m_maxYear = 2096;
/*  681 */     this.m_traceHistorySet = null;
/*      */ 
/*  684 */     setCommunicationsLink(new CommunicationsLinkRS232(this, this.m_serialNumber));
/*      */   }
/*      */
/*      */   public String toString()
/*      */   {
/*  701 */     return "description = " + this.m_description + ", modelNumber = " + this.m_modelNumber + ", device type = " + (isDeviceMeter() ? "meter" : isDeviceMonitor() ? "monitor" : isDevicePump() ? "pump" : null) + ", serialNumber = " + this.m_serialNumber + ", firmwareVersion = " + this.m_firmwareVersion + ", timeStamp = " + this.m_timeStamp + ", communicationsLink = '" + getCommunicationsLink() + "'" + ", linkDevice ID = " + this.m_linkDevice + ", PACKAGE_VERSION = " + "10.0.0";
/*      */   }
/*      */   // B(v paramv, String paramString1, String paramString2, Date paramDate1, Date paramDate2)
/*      */   public void readGlucoseDataRange(DeviceListener paramDeviceListener, int paramInt, String paramString, Date paramDate1, Date paramDate2)
/*      */     throws BadDeviceCommException, BadDeviceValueException, IOException, ConnectToPumpException
/*      */   {
/*  746 */     Contract.unreachable();
/*      */   }
/*      */   // A(v paramv, String paramString1, String paramString2, Date paramDate1, Date paramDate2)
/*      */   public void readIsigDataRange(DeviceListener paramDeviceListener, int paramInt, String paramString, Date paramDate1, Date paramDate2)
/*      */     throws BadDeviceCommException, BadDeviceValueException, IOException, ConnectToPumpException
/*      */   {
/*  782 */     Contract.unreachable();
/*      */   }
/*      */   // B(v paramv, String paramString1, String paramString2)
/*      */   public void readClock(DeviceListener paramDeviceListener, int paramInt, String paramString)
/*      */     throws BadDeviceCommException, BadDeviceValueException, IOException, ConnectToPumpException
/*      */   {
/*  806 */     Contract.unreachable();
/*      */   }
/*      */   // A(v paramv, String paramString1, String paramString2, boolean paramBoolean)
/*      */   public void readData(DeviceListener paramDeviceListener, int paramInt, String paramString, boolean paramBoolean)
/*      */     throws BadDeviceCommException, BadDeviceValueException, IOException, ConnectToPumpException
/*      */   {
/*  836 */     readData(paramDeviceListener, paramInt, paramString);
/*      */   }
/*      */   // EA J()
/*      */   public TraceHistorySet getTraceHistorySet()
/*      */   {
/*  847 */     return this.m_traceHistorySet;
/*      */   }
/*      */   // A(v paramv)
/*      */   public String autoDetectDevice(DeviceListener paramDeviceListener)
/*      */     throws BadDeviceCommException, IOException
/*      */   {
/*      */     String str;
/*  867 */     if ((this.m_linkDevice == 19) || ((this instanceof k)) || ((this instanceof j)))
/*      */     {
/*  869 */       str = "USB";
/*      */     }
/*      */     else {
/*      */       try {
/*      */         try {
/*  874 */           str = autoDetectDeviceIO(paramDeviceListener, SerialPort.getPortList());
/*      */         }
/*      */         catch (BadDeviceCommException localBadDeviceCommException) {
/*  877 */           if (!isHaltRequested()) {
/*  878 */             logWarning(this, "autoDetectDevice: system list failed; trying max port list.");
/*      */ 
/*  880 */             str = autoDetectDeviceIO(paramDeviceListener, MAX_PORT_LIST);
/*      */           } else {
/*  882 */             throw localBadDeviceCommException;
/*      */           }
/*      */         }
/*      */       }
/*      */       finally
/*      */       {
/*  888 */         shutDownSerialPort();
/*      */       }
/*      */     }
/*      */ 
/*  892 */     return str;
/*      */   }
/*      */   // InputStream C()
/*      */   public InputStream createSnapshot()
/*      */     throws BadDeviceValueException, IOException
/*      */   {
/*  908 */     return this.m_snapshotCreator.createSnapshot();
/*      */   }
/*      */   // void A(String paramString)
/*      */   public void storeSnapshot(String paramString)
/*      */     throws FileNotFoundException, IOException
/*      */   {
/*  922 */     this.m_snapshot.store(paramString);
/*      */   }
/*      */   // void P()
/*      */   public void halt()
/*      */   {
/*  930 */     logInfo(this, "halt: STOPPING UPLOAD...");
/*  931 */     setHaltRequested(true);
/*      */ 
/*  933 */     if ((getCommunicationsLink() != null) && 
/*  934 */       (getCommunicationsLink().getCommPort() != null)) {
/*  935 */       getCommunicationsLink().getCommPort().setContinueIO(false);
/*      */     }
/*      */ 
/*  939 */     setState(8);
/*      */   }
/*      */   // String D()
/*      */   public String getLastCommandDescription()
/*      */   {
/*  948 */     return m_lastCommandDescription;
/*      */   }
/*      */   // String R()
/*      */   public String getDescription()
/*      */   {
/*  957 */     return this.m_description;
/*      */   }
/*      */   // synchronized int I()
/*      */   public synchronized int getPhase()
/*      */   {
/*  966 */     return m_phase;
/*      */   }
/*      */   // String O()
/*      */   public String getPhaseText()
/*      */   {
/*  975 */     return m_phaseText;
/*      */   }
/*      */   // String Q()
/*      */   public String getSerialNumber()
/*      */   {
/*  984 */     return this.m_serialNumber;
/*      */   }
/*      */   // int F()
/*      */   public int getSnapshotVersion()
/*      */   {
/*  993 */     return this.m_snapshot.getSnapshotVersion();
/*      */   }
/*      */   // Date N()
/*      */   public Date getClock()
/*      */   {
/* 1002 */     return this.m_timeStamp;
/*      */   }
/*      */   // int B()
/*      */   public int getMaxRetryCount()
/*      */   {
/* 1013 */     return 2;
/*      */   }
/*      */   // String L()
/*      */   public int getSerialPortNumber()
/*      */   {
/* 1022 */     return this.m_serialPortNumber;
/*      */   }
/*      */   // long E()
/*      */   public long getLastHistoryPageNumber()
/*      */   {
/* 1033 */     return this.m_lastHistoryPageNumber;
/*      */   }
/*      */   // long G()
/*      */   public long getLastGlucoseHistoryPageNumber()
/*      */   {
/* 1044 */     return this.m_lastGlucoseHistoryPageNumber;
/*      */   }
/*      */ 
/*      */   public void A(int paramInt)
/*      */   {
/* 1056 */     D(paramInt);
/*      */   }
/*      */   // static void D(int paramInt)
/*      */   public void setMessageLevel(int paramInt)
/*      */   {
/* 1068 */     Contract.pre((paramInt >= 0) && (paramInt <= 3));
/* 1069 */     m_messageLevel = paramInt;
/*      */   }
/*      */   // final String K()
/*      */   public final String getPackageVersion()
/*      */   {
/* 1078 */     return "10.0.0";
/*      */   }
/*      */   // String S()
/*      */   public String getModelNumber()
/*      */   {
/* 1087 */     return this.m_modelNumber;
/*      */   }
/*      */   // boolean M()
/*      */   public boolean isDevicePump()
/*      */   {
/* 1096 */     return getDeviceType() == 1;
/*      */   }
/*      */   // boolean A()
/*      */   public boolean isDeviceMonitor()
/*      */   {
/* 1105 */     return getDeviceType() == 2;
/*      */   }
/*      */   // boolean H()
/*      */   public boolean isDeviceMeter()
/*      */   {
/* 1114 */     return getDeviceType() == 3;
/*      */   }
/*      */ 
/*      */   public final long getLastHistoryPageNumber(String paramString1, String paramString2)
/*      */   {
/* 1129 */     Contract.unreachable();
/* 1130 */     return 0L;
/*      */   }
/*      */ 
/*      */   public final long getLastGlucoseHistoryPageNumber(String paramString1, String paramString2)
/*      */   {
/* 1145 */     Contract.unreachable();
/* 1146 */     return 0L;
/*      */   }
/*      */   // void allowDeviceOperation(G paramG)
/*      */   public void allowDeviceOperation(DevicePortReader paramDevicePortReader)
/*      */     throws ConnectToPumpException
/*      */   {
/*      */   }
/*      */ 
/*      */   public void deviceUpdateProgress(int paramInt)
/*      */   {
/* 1168 */     notifyDeviceUpdateProgress(paramInt);
/*      */   }
/*      */ 
/*      */   public void deviceUpdateState(int paramInt, String paramString)
/*      */   {
/* 1180 */     if ((paramInt != 1) && (paramInt != 2))
/*      */     {
/* 1182 */       setState(paramInt);
/*      */     }
/*      */   }
/*      */ 
/*      */   public void deviceUpdatePhase(int paramInt, String paramString)
/*      */   {
/*      */   }
/*      */   // String A(v paramv, String[] paramArrayOfString)
/*      */   private String autoDetectDeviceIO(DeviceListener paramDeviceListener, String[] paramArrayOfString)
/*      */     throws BadDeviceCommException
/*      */   {
/* 1210 */     String str1 = null;
/* 1211 */     StringBuffer localStringBuffer = new StringBuffer();
/* 1212 */     int i = 0;
/*      */ 
/* 1214 */     setHaltRequested(false);
/* 1215 */     addDeviceListener(paramDeviceListener);
/* 1216 */     setPhase(1);
/*      */ 
/* 1220 */     String str2 = DevicePortReaderFactory.mapLinkDeviceID(this.m_linkDevice);
/* 1221 */     if (str2.equals("UNKNOWN")) {
/* 1222 */       str2 = this.m_description;
/*      */     }
/*      */ 
/* 1226 */     for (int j = 0; (j < paramArrayOfString.length) && (i == 0) && (!isHaltRequested()); j++)
/*      */     {
/*      */       try
/*      */       {
/* 1230 */         str1 = paramArrayOfString[(paramArrayOfString.length - j - 1)];
/* 1231 */         if (j > 0)
/*      */         {
/* 1233 */           localStringBuffer.append(", ");
/*      */         }
/*      */ 
/* 1236 */         localStringBuffer.append(str1);
/* 1237 */         logInfo(this, "autoDetectDeviceIO: testing port '" + str1 + "'");
/*      */ 
/* 1240 */         String str3 = str1;
/* 1241 */         D(str3);
/*      */         try
/*      */         {
/* 1245 */           if (!isHaltRequested()) {
/* 1246 */             setPhase(7);
/* 1247 */             findDevice(paramDeviceListener);
/*      */ 
/* 1249 */             i = 1;
/* 1250 */             logInfo(this, "autoDetectDeviceIO: " + str2 + " found on port '" + str1 + "'");
/*      */           }
/*      */         }
/*      */         catch (BadDeviceCommException localBadDeviceCommException)
/*      */         {
/*      */         }
/*      */       }
/*      */       catch (IOException localIOException)
/*      */       {
/* 1259 */         logInfo(this, "autoDetectDeviceIO: " + str2 + " NOT found on port '" + str1 + "'; " + "e=" + localIOException);
/*      */       }
/*      */       catch (BadDeviceValueException localBadDeviceValueException)
/* 1262 */         removeAllDeviceListeners();
/* 1263 */         throw new BadDeviceCommException("autoDetectDeviceIO: detection has been Halted...");
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1268 */     removeAllDeviceListeners();
/*      */ 
/* 1270 */     if (isHaltRequested()) {
/* 1271 */       throw new BadDeviceCommException("autoDetectDeviceIO: detection has been Halted...");
/*      */     }
/*      */ 
/* 1275 */     if (i == 0) {
/* 1276 */       throw new BadDeviceCommException("autoDetectDeviceIO: could not find " + str2 + " on any port in list '" + localStringBuffer + "'.");
/*      */     }
/*      */ 
/* 1281 */     return str1;
/*      */   }
/*      */ 
/*      */   void C(String paramString)
/*      */     throws IOException
/*      */   {
/* 1292 */     this.m_serialPortNumber = paramString;
/* 1293 */     setPhase(2);
/*      */ 
/* 1295 */     if (Y() != null)
/* 1296 */       Y().B();
/*      */   }
/*      */ 
/*      */   abstract int Z();
/*      */ 
/*      */   abstract void C(v paramv)
/*      */     throws t, IOException;
/*      */ 
/*      */   abstract void D(String paramString)
/*      */     throws IOException;
/*      */   // abstract void _()
/*      */   abstract void shutDownSerialPort()
/*      */     throws IOException;
/*      */   // final synchronized void E(int paramInt)
/*      */   final synchronized void setPhase(int paramInt)
/*      */   {
/* 1341 */     Contract.pre((paramInt >= 1) && (paramInt <= 7));
/*      */ 
/* 1344 */     if (paramInt != m_phase) {
/* 1345 */       m_phase = paramInt;
/* 1346 */       m_phaseText = updateDynamicText(DeviceListener.PHASE_TEXT[m_phase]);
/* 1347 */       notifyDeviceUpdatePhase(m_phase, m_phaseText);
/* 1348 */       logInfoLow(this, "setPhase: phase is now " + ø + " (" + Ď + ")");
/*      */     }
/*      */   }
/*      */   // final synchronized void B(int paramInt)
/*      */   final synchronized void setState(int paramInt)
/*      */   {
/* 1360 */     Contract.pre((paramInt >= 1) && (paramInt <= 9));
/*      */ 
/* 1362 */     if (paramInt != m_state) {
/* 1363 */       m_state = paramInt;
/* 1364 */       m_stateText = DeviceListener.STATE_TEXT[m_state];
/* 1365 */       notifyDeviceUpdateState(m_state, m_stateText);
/* 1366 */       logInfo(this, "setState: state is now " + Ì + " (" + ē + ")");
/*      */     }
/*      */   }
/*      */   // final synchronized int T()
/*      */   final synchronized int getState()
/*      */   {
/* 1376 */     return m_state;
/*      */   }
/*      */   // final CA X()
/*      */   final Snapshot getSnapshot()
/*      */   {
/* 1385 */     return this.m_snapshot;
/*      */   }
/*      */   // static void A(Object paramObject, String paramString)
/*      */   static void logInfo(Object paramObject, String paramString)
/*      */   {
/* 1396 */     if (m_messageLevel >= 2)
/* 1397 */       logMessage(paramObject, paramString, "INFO");
/*      */   }
/*      */   // static void B(Object paramObject, String paramString)
/*      */   static void logInfoHigh(Object paramObject, String paramString)
/*      */   {
/* 1409 */     if (m_messageLevel >= 3)
/* 1410 */       logMessage(paramObject, paramString, "INFO");
/*      */   }
/*      */   // static void C(Object paramObject, String paramString)
/*      */   static void logInfoLow(Object paramObject, String paramString)
/*      */   {
/* 1422 */     if (m_messageLevel >= 1)
/* 1423 */       logMessage(paramObject, paramString, "INFO");
/*      */   }
/*      */   // static void D(Object paramObject, String paramString)
/*      */   static void logWarning(Object paramObject, String paramString)
/*      */   {
/* 1434 */     if (m_messageLevel >= 2)
/* 1435 */       logMessage(paramObject, paramString, "WARNING");
/*      */   }
/*      */   // static void E(Object paramObject, String paramString)
/*      */   static void logError(Object paramObject, String paramString)
/*      */   {
/* 1446 */     if (m_messageLevel >= 1)
/* 1447 */       logMessage(paramObject, paramString, "ERROR");
/*      */   }
/*      */   // static void A(boolean paramBoolean)
/*      */   static void setCreateSnapshotTextFile(boolean paramBoolean)
/*      */   {
/* 1460 */     m_createSnapshotTextFile = paramBoolean;
/*      */   }
/*      */   // static void A(PrintWriter paramPrintWriter)
/*      */   static void setLogBuffer(PrintWriter paramPrintWriter)
/*      */   {
/* 1469 */     m_logBuffer = paramPrintWriter;
/*      */   }
/*      */   // final Date A(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
/*      */   final Date createTimestamp(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
/*      */     throws BadDeviceValueException
/*      */   {
/* 1488 */     verifyDeviceTimeStamp(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6);
/* 1489 */     GregorianCalendar localGregorianCalendar = new GregorianCalendar(Util.convertYear(paramInt3), paramInt2 - 1, paramInt1, paramInt4, paramInt5, paramInt6);
/*      */ 
/* 1492 */     return localGregorianCalendar.getTime();
/*      */   }
/*      */   // void B(v paramv)
/*      */   void addDeviceListener(DeviceListener paramDeviceListener)
/*      */   {
/* 1503 */     Contract.pre(paramv != null);
/*      */ 
/* 1505 */     this.m_listeners.addElement(paramv);
/*      */   }
/*      */   // void U()
/*      */   void removeAllDeviceListeners()
/*      */   {
/* 1514 */     this.m_listeners.removeAllElements();
/*      */   }
/*      */   // void C(int paramInt)
/*      */   void notifyDeviceUpdateProgress(int paramInt)
/*      */   {
/* 1526 */     if ((ø != 4) && 
/* 1527 */       (paramInt >= 0) && (paramInt <= 100))
/* 1528 */       for (int i1 = 0; i1 < this.m_listeners.size(); i1++) {
/* 1529 */         v localv = (v)this.m_listeners.elementAt(i1);
/* 1530 */         localv.deviceUpdateProgress(paramInt);
/*      */       }
/*      */   }
/*      */   // void A(int paramInt1, int paramInt2)
/*      */   void notifyDeviceUpdateProgress(int paramInt1, int paramInt2)
/*      */   {
/* 1545 */     double d = paramInt1 / paramInt2 * 100.0D;
/*      */ 
/* 1547 */     C((int)d);
/*      */   }
/*      */   // void B(int paramInt, String paramString)
/*      */   void notifyDeviceUpdateState(int paramInt, String paramString)
/*      */   {
/* 1557 */     for (int i = 0; i < this.m_listeners.size(); i++) {
/* 1558 */       DeviceListener localDeviceListener = (DeviceListener)this.m_listeners.elementAt(i);
/* 1559 */       localDeviceListener.deviceUpdateState(paramInt, paramString);
/*      */     }
/*      */   }
/*      */   // void A(int paramInt, String paramString)
/*      */   void notifyDeviceUpdatePhase(int paramInt, String paramString)
/*      */   {
/* 1570 */     for (int i = 0; i < this.m_listeners.size(); i++) {
/* 1571 */       DeviceListener localDeviceListener = (DeviceListener)this.m_listeners.elementAt(i);
/* 1572 */       localDeviceListener.deviceUpdatePhase(paramInt, paramString);
/*      */     }
/*      */   }
/*      */   // final String B(String paramString)
/*      */   final String updateDynamicText(String paramString)
/*      */   {
/* 1583 */     String str1 = (this.m_linkDevice == 15) || (this.m_linkDevice == 14) ? this.m_serialPortNumber : "USB";
/*      */ 
/* 1587 */     String str2 = DevicePortReaderFactory.mapLinkDeviceID(this.m_linkDevice);
/*      */ 
/* 1589 */     paramString = paramString.replaceAll("@commport", str1);
/* 1590 */     paramString = paramString.replaceAll("@linkdevice", str2 == null ? "?" : str2);
/* 1591 */     paramString = paramString.replaceAll("@serialnumber", this.m_serialNumber == null ? "?" : this.m_serialNumber);
/* 1592 */     return paramString;
/*      */   }
/*      */   // void A(int[] paramArrayOfInt)
/*      */   void decodeSerialNumber(int[] paramArrayOfInt)
/*      */   {
/* 1603 */     Contract.pre(paramArrayOfInt != null);
/*      */ 
/* 1605 */     this.m_serialNumber = new String(Util.makeByteArray(paramArrayOfInt));
/* 1606 */     this.m_serialNumber = this.m_serialNumber.trim();
/* 1607 */     logInfo(this, "decodeSerialNumber: serial number is " + this.m_serialNumber);
/*      */   }
/*      */   // void B(int[] paramArrayOfInt)
/*      */   void decodeFirmwareVersion(int[] paramArrayOfInt)
/*      */   {
/* 1618 */     Contract.pre(paramArrayOfInt != null);
/*      */ 
/* 1620 */     this.m_firmwareVersion = new String(Util.makeByteArray(paramArrayOfInt));
/* 1621 */     this.m_firmwareVersion = this.m_firmwareVersion.trim();
/* 1622 */     logInfo(this, "decodeFirmwareVersion: firmware version is " + this.m_firmwareVersion
/*      */   }
/*      */   // z W()
/*      */   final CommunicationsLink getCommunicationsLink()
/*      */   {
/* 1631 */     return this.m_communicationsLink;
/*      */   }
/*      */   // void A(z paramz)
/*      */   final void setCommunicationsLink(CommunicationsLink paramCommunicationsLink)
/*      */   {
/* 1640 */     this.m_communicationsLink� = paramCommunicationsLink;
/*      */   }
/*      */   // void B(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
/*      */   private void verifyDeviceTimeStamp(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
/*      */     throws BadDeviceValueException
/*      */   {
/* 1659 */     Util.verifyDeviceValue(paramInt1, 1, 31, "day");
/* 1660 */     Util.verifyDeviceValue(paramInt2, 1, 12, "month");
/* 1661 */     Util.verifyDeviceValue(paramInt3, this.m_minYear, this.m_maxYear, "year");
/* 1662 */     Util.verifyDeviceValue(paramInt4, 0, 23, "hour");
/* 1663 */     Util.verifyDeviceValue(paramInt5, 0, 59, "minute");
/* 1664 */     Util.verifyDeviceValue(paramInt6, 0, 59, "second");
/*      */   }
/*      */   // void A(Object paramObject, String paramString1, String paramString2)
/*      */   private static synchronized void logMessage(Object paramObject, String paramString1, String paramString2)
/*      */   {
/* 1678 */     String str1 = Util.convertControlChars(paramString1);
/* 1679 */     if (m_logBuffer != null) {
/* 1680 */       Date localDate = new Date();
/* 1681 */       String str2 = m_dateFormatter.format(localDate);
/* 1682 */       m_logBuffer.println(str2 + " " + paramString2 + " " + paramObject.getClass().getName() + "-" + str1);
/*      */     }
/*      */   }
/*      */   // void B(boolean paramBoolean)
/*      */   final synchronized void setHaltRequested(boolean paramBoolean)
/*      */   {
/* 1694 */     m_haltRequested = paramBoolean;
/*      */   }
/*      */ 
/*      */   public final synchronized boolean isHaltRequested()
/*      */   {
/* 1704 */     return m_haltRequested;
/*      */   }
/*      */   // void A(d paramd)
/*      */   void setRS232Port(SerialPort paramSerialPort)
/*      */   {
/* 1713 */     getCommunicationsLink().setCommPort(paramSerialPort);
/*      */   }
/*      */   // d Y()
/*      */   SerialPort getRS232Port()
/*      */   {
/* 1722 */     return (SerialPort)getCommunicationsLink().getCommPort()
/*      */   }
/*      */ 
/*      */   void A(c paramc)
/*      */   {
/* 1731 */     W().A(paramc);
/*      */   }
/*      */   // c V()
/*      */   CommPort V()
/*      */   {
/* 1740 */     return W().C();
/*      */   }
/*      */ 
/*      */   static
/*      */   {
/*  690 */     m_dateFormatter = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss:SSSS");
/*      */   }
/*      */ 
/*      */   static final class Util
/*      */   {
/* 1809 */     private static final Calendar CAL = Calendar.getInstance(); // A
/*      */     // int[] C(int[] paramArrayOfInt)
/*      */     static int[] clone(int[] paramArrayOfInt)
/*      */     {
/* 1824 */       int[] arrayOfInt = new int[paramArrayOfInt.length];
/* 1825 */       for (int i = 0; i < arrayOfInt.length; i++) {
/* 1826 */         arrayOfInt[i] = paramArrayOfInt[i];
/*      */       }
/* 1828 */       return arrayOfInt;
/*      */     }
/*      */     // void G(int paramInt)
/*      */     static void sleepMS(int paramInt)
/*      */     {
/*      */       try
/*      */       {
/* 1838 */         Thread.sleep(paramInt);
/*      */       }
/*      */       catch (InterruptedException localInterruptedException)
/*      */       {
/*      */       }
/*      */     }
/*      */     // int D(int paramInt)
/*      */     static int getLowNibble(int paramInt)
/*      */     {
/* 1852 */       Contract.pre((paramInt >= 0) && (paramInt <= 255));
/*      */ 
/* 1854 */       return paramInt & 0xF;
/*      */     }
/*      */     // int M(int paramInt)
/*      */     static int getHighNibble(int paramInt)
/*      */     {
/* 1866 */       Contract.pre((paramInt >= 0) && (paramInt <= 255));
/*      */ 
/* 1868 */       return paramInt >> 4 & 0xF;
/*      */     }
/*      */     // int K(int paramInt)
/*      */     static int getLowByte(int paramInt)
/*      */     {
/* 1878 */       return paramInt & 0xFF;
/*      */     }
/*      */     // int J(int paramInt)
/*      */     static int getHighByte(int paramInt)
/*      */     {
/* 1889 */       return paramInt >>> 8 & 0xFF;
/*      */     }
/*      */     // int A(String paramString)
/*      */     static int convertHexToDec(String paramString)
/*      */     {
/* 1901 */       return Integer.valueOf(paramString, 16).intValue();
/*      */     }
/*      */     // int L(int paramInt)
/*      */     static int convertHexToASCII(int paramInt)
/*      */     {
/* 1913 */       Contract.pre((paramInt >= 0) && (paramInt <= 15));
/*      */ 
/* 1915 */       return paramInt < 10 ? paramInt + 48 : paramInt - 10 + 65;
/*      */     }
/*      */     // String E(String paramString)
/*      */     static String convertControlChars(String paramString)
/*      */     {
/* 1926 */       StringBuffer localStringBuffer = new StringBuffer();
/*      */ 
/* 1930 */       for (int j = 0; j < paramString.length(); j++) {
/* 1931 */         int i = paramString.charAt(j);
/*      */ 
/* 1933 */         if ((i < 32) || (i > 127));
/* 1935 */         switch (i)
/*      */         {
/*      */         case 9:
/* 1938 */           localStringBuffer.append(paramString.charAt(j));
/* 1939 */           localStringBuffer.append("<TAB>");
/* 1940 */           break;
/*      */         case 10:
/* 1942 */           localStringBuffer.append("<LF>");
/* 1943 */           break;
/*      */         case 0:
/* 1945 */           localStringBuffer.append("<NUL>");
/* 1946 */           break;
/*      */         case 6:
/* 1948 */           localStringBuffer.append("<ACK>");
/* 1949 */           break;
/*      */         case 21:
/* 1951 */           localStringBuffer.append("<NAK>");
/* 1952 */           break;
/*      */         case 13:
/* 1954 */           localStringBuffer.append("<CR>");
/* 1955 */           break;
/*      */         case 32:
/* 1957 */           localStringBuffer.append("<SP>");
/* 1958 */           break;
/*      */         case 2:
/* 1960 */           localStringBuffer.append("<STX>");
/* 1961 */           break;
/*      */         case 1:
/* 1963 */           localStringBuffer.append("<STH>");
/* 1964 */           break;
/*      */         case 5:
/* 1966 */           localStringBuffer.append("<ENQ>");
/* 1967 */           break;
/*      */         case 4:
/* 1969 */           localStringBuffer.append("<EOT>");
/* 1970 */           break;
/*      */         case 23:
/* 1972 */           localStringBuffer.append("<ETB>");
/* 1973 */           break;
/*      */         case 3:
/* 1975 */           localStringBuffer.append("<ETX>");
/* 1976 */           break;
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
/* 1979 */           String str = '<' + H(i) + '>';
/* 1980 */           localStringBuffer.append(str);
/* 1981 */           continue;
/*      */ 
/* 1984 */           localStringBuffer.append(paramString.charAt(j));
/*      */         }
/*      */       }
/* 1987 */       return new String(localStringBuffer);
/*      */     }
/*      */     // boolean A(int paramInt, String paramString)
/*      */     static boolean parseEnable(int paramInt, String paramString)
/*      */       throws BadDeviceValueException
/*      */     {
/* 2000 */       verifyDeviceValue(paramInt, 0, 1, paramString);
/* 2001 */       return paramInt == 1;
/*      */     }
/*      */     // int[] B(String paramString)
/*      */     static int[] makePackedBCD(String paramString)
/*      */     {
/* 2016 */       Contract.preNonNull(paramString);
/* 2017 */       Contract.pre(isEven(paramString.length()));
/* 2018 */       paramString = paramString.toLowerCase();
/* 2019 */       for (int i = 0; i < paramString.length(); i++) {
/* 2020 */         char c1 = paramString.charAt(i);
/* 2021 */         Contract.pre((Character.isDigit(c1)) || ((c1 >= 'a') && (c1 <= 'f')));
/*      */       }
/*      */ 
/* 2024 */       int[] arrayOfInt1 = makeIntArray(paramString);
/* 2025 */       int[] arrayOfInt2 = new int[arrayOfInt1.length / 2];
/*      */ 
/* 2027 */       for (int j = 0; j < arrayOfInt2.length; j++) {
/* 2028 */         char c2 = (char)arrayOfInt1[(j * 2)];
/* 2029 */         char c3 = (char)arrayOfInt1[(j * 2 + 1)];
/* 2030 */         int k = c2 - (Character.isDigit(c2) ? '0' : 'W');
/* 2031 */         int m = c3 - (Character.isDigit(c3) ? '0' : 'W');
/* 2032 */         arrayOfInt2[j] = makeByte(k, m);
/*      */       }
/*      */ 
/* 2035 */       return arrayOfInt2;
/*      */     }
/*      */     // int C(int paramInt1, int paramInt2)
/*      */     static int makeByte(int paramInt1, int paramInt2)
/*      */     {
/* 2051 */       Contract.pre((paramInt1 >= 0) && (paramInt1 <= 15));
/* 2052 */       Contract.pre((paramInt2 >= 0) && (paramInt2 <= 15));
/*      */ 
/* 2054 */       int i = paramInt1 << 4 | paramInt2 & 0xF;
/*      */ 
/* 2056 */       Contract.post((i >= 0) && (i <= 255));
/*      */ 
/* 2058 */       return i;
/*      */     }
/*      */     // long A(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*      */     static long makeLong(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*      */     {
/* 2077 */       Contract.pre((paramInt1 >= 0) && (paramInt1 <= 255));
/* 2078 */       Contract.pre((paramInt2 >= 0) && (paramInt2 <= 255));
/* 2079 */       Contract.pre((paramInt3 >= 0) && (paramInt3 <= 255));
/* 2080 */       Contract.pre((paramInt4 >= 0) && (paramInt4 <= 255));
/*      */ 
/* 2083 */       long l = paramInt1 << 24 | paramInt2 << 16 | paramInt3 << 8 | paramInt4;
/*      */ 
/* 2088 */       Contract.post((l >= 0L) && (l <= 4294967295L));
/*      */ 
/* 2090 */       return l;
/*      */     }
/*      */     // int D(long paramLong)
/*      */     static int getLowWord(long paramLong)
/*      */     {
/* 2100 */       return (int)(paramLong & 0xFFFF);
/*      */     }
/*      */     // int C(long paramLong)
/*      */     static int getHighWord(long paramLong)
/*      */     {
/* 2111 */       return (int)(paramLong >>> 16) & 0xFFFF;
/*      */     }
/*      */     // double B(int paramInt)
/*      */     static double toWholeUnits(int paramInt)
/*      */     {
/* 2121 */       return paramInt / 10.0D;
/*      */     }
/*      */     // int A(int paramInt1, int paramInt2)
/*      */     static int makeInt(int paramInt1, int paramInt2)
/*      */     {
/* 2135 */       Contract.pre((paramInt1 >= 0) && (paramInt1 <= 255));
/* 2136 */       Contract.pre((paramInt2 >= 0) && (paramInt2 <= 255));
/*      */ 
/* 2138 */       int i = (paramInt1 & 0xFF) << 8 | paramInt2 & 0xFF;
/*      */ 
/* 2140 */       Contract.post((i >= 0) && (i <= 65535));
/* 2141 */       return i;
/*      */     }
/*      */     // int A(int paramInt1, int paramInt2, int paramInt3)
/*      */     static int makeInt(int paramInt1, int paramInt2, int paramInt3)
/*      */     {
/* 2158 */       Contract.pre((paramInt1 >= 0) && (paramInt1 <= 255));
/* 2159 */       Contract.pre((paramInt2 >= 0) && (paramInt2 <= 255));
/* 2160 */       Contract.pre((paramInt3 >= 0) && (paramInt3 <= 255));
/*      */ 
/* 2162 */       int i = (paramInt1 & 0xFF) << 16 | (paramInt2 & 0xFF) << 8 | paramInt3 & 0xFF;
/*      */ 
/* 2165 */       Contract.post((i >= 0) && (i <= 16777215));
/*      */ 
/* 2167 */       return i;
/*      */     }
/*      */     // int C(byte paramByte)
/*      */     static int convertUnsignedByteToInt(byte paramByte)
/*      */     {
/* 2180 */       return paramByte & 0xFF;
/*      */     }
/*      */     // long A(int paramInt)
/*      */     static long convertUnsignedIntToLong(int paramInt)
/*      */     {
/* 2193 */       return paramInt & 0xFFFFFFFF;
/*      */     }
/*      */     // byte[] A(int[] paramArrayOfInt)
/*      */     static byte[] convertIntsToBytes(int[] paramArrayOfInt)
/*      */     {
/* 2205 */       Contract.preNonNull(paramArrayOfInt);
/* 2206 */       byte[] arrayOfByte = new byte[paramArrayOfInt.length];
/*      */ 
/* 2209 */       for (int i = 0; i < paramArrayOfInt.length; i++) {
/* 2210 */         Contract.pre(paramArrayOfInt[i] >> 8 == 0);
/* 2211 */         arrayOfByte[i] = (byte)(paramArrayOfInt[i] & 0xFF);
/*      */       }
/* 2213 */       return arrayOfByte;
/*      */     }
/*      */     // int B(int paramInt1, int paramInt2)
/*      */     static int makeUnsignedShort(int paramInt1, int paramInt2)
/*      */     {
/* 2227 */       Contract.pre((paramInt1 >= 0) && (paramInt1 <= 255));
/* 2228 */       Contract.pre((paramInt2 >= 0) && (paramInt2 <= 255));
/*      */ 
/* 2230 */       int i = (paramInt1 & 0xFF) << 8 | paramInt2 & 0xFF;
/*      */ 
/* 2232 */       Contract.post((i >= 0) && (i <= 65535));
/* 2233 */       return i;
/*      */     }
/*      */     // byte[] B(int[] paramArrayOfInt)
/*      */     static byte[] makeByteArray(int[] paramArrayOfInt)
/*      */     {
/* 2244 */       if (paramArrayOfInt != null) {
/* 2245 */         byte[] arrayOfByte = new byte[paramArrayOfInt.length];
/*      */ 
/* 2247 */         for (int i = 0; i < paramArrayOfInt.length; i++) {
/* 2248 */           Contract.pre(paramArrayOfInt[i], -128, 127);
/* 2249 */           arrayOfByte[i] = (byte)paramArrayOfInt[i];
/*      */         }
/* 2251 */         return arrayOfByte;
/*      */       }
/* 2253 */       return null;
/*      */     }
/*      */     // int[] C(byte[] paramArrayOfByte)
/*      */     static int[] makeIntArray(byte[] paramArrayOfByte)
/*      */     {
/* 2264 */       Contract.preNonNull(paramArrayOfByte);
/* 2265 */       int[] arrayOfInt = new int[paramArrayOfByte.length];
/*      */ 
/* 2268 */       for (int i = 0; i < paramArrayOfByte.length; i++) {
/* 2269 */         paramArrayOfByte[i] &= 255;
/*      */       }
/* 2271 */       return arrayOfInt;
/*      */     }
/*      */     // String E(int[] paramArrayOfInt)
/*      */     static String makeString(int[] paramArrayOfInt)
/*      */     {
/* 2282 */       return makeString(paramArrayOfInt, 0, paramArrayOfInt.length);
/*      */     }
/*      */     // String B(int[] paramArrayOfInt, int paramInt1, int paramInt2)
/*      */     static String makeString(int[] paramArrayOfInt, int paramInt1, int paramInt2)
/*      */     {
/* 2296 */       Contract.pre(paramArrayOfInt.length >= paramInt1 + paramInt2);
/* 2297 */       if (paramArrayOfInt != null) {
/* 2298 */         StringBuffer localStringBuffer = new StringBuffer();
/*      */ 
/* 2300 */         for (int i = paramInt1; i < paramInt1 + paramInt2; i++)
/*      */         {
/* 2302 */           if (paramArrayOfInt[i] != 0) {
/* 2303 */             localStringBuffer.append((char)paramArrayOfInt[i]);
/*      */           }
/*      */         }
/*      */ 
/* 2307 */         return new String(localStringBuffer);
/*      */       }
/* 2309 */       return null;
/*      */     }
/*      */     // static int[] D(String paramString)
/*      */     static int[] makeIntArray(String paramString)
/*      */     {
/* 2320 */       if (paramString != null) {
/* 2321 */         int[] arrayOfInt = new int[paramString.length()];
/*      */ 
/* 2323 */         for (int i = 0; i < arrayOfInt.length; i++) {
/* 2324 */           arrayOfInt[i] = paramString.charAt(i);
/*      */         }
/* 2326 */         return arrayOfInt;
/*      */       }
/* 2328 */       return null;
/*      */     }
/*      */     // static int[] A(int[] paramArrayOfInt1, int[] paramArrayOfInt2) // concat
/*      */     static int[] concat(int[] paramArrayOfInt1, int[] paramArrayOfInt2)
/*      */     {
/* 2343 */       Contract.pre(paramArrayOfInt1 != null);
/* 2344 */       Contract.pre(paramArrayOfInt2 != null);
/*      */ 
/* 2346 */       int[] arrayOfInt = new int[paramArrayOfInt1.length + paramArrayOfInt2.length];
/* 2347 */       System.arraycopy(paramArrayOfInt1, 0, arrayOfInt, 0, paramArrayOfInt1.length);
/* 2348 */       System.arraycopy(paramArrayOfInt2, 0, arrayOfInt, paramArrayOfInt1.length, paramArrayOfInt2.length);
/* 2349 */       return arrayOfInt;
/*      */     }
/*      */     // static String A(String paramString1, String paramString2)
/*      */     static String remainderOf(String paramString1, String paramString2)
/*      */       throws BadDeviceValueException
/*      */     {
/* 2365 */       int i = paramString1.indexOf(paramString2);
/*      */ 
/* 2367 */       if (i != -1) {
/* 2368 */         int j = i + paramString2.length();
/* 2369 */         if (paramString1.length() > j) {
/* 2370 */           return paramString1.substring(j);
/*      */         }
/* 2372 */         throw new BadDeviceValueException("Nothing following '" + paramString2 + "' in string '" + paramString1 + "'");
/*      */       }
/*      */ 
/* 2376 */       throw new BadDeviceValueException("Can't find '" + paramString2 + "' in string '" + paramString1 + "'");
/*      */     }
/*      */     // static int E(int[] paramArrayOfInt, int paramInt1, int paramInt2)
/*      */     static int computeCRC7(int[] paramArrayOfInt, int paramInt1, int paramInt2)
/*      */     {
/* 2394 */       int i = 127;
/*      */ 
/* 2396 */       Contract.pre(paramArrayOfInt != null);
/* 2397 */       Contract.pre(paramArrayOfInt.length >= paramInt1 + paramInt2);
/*      */ 
/* 2399 */       for (int j = 0; j < paramInt2; j++) {
/* 2400 */         i = MedicalDevice.CRC7_LOOKUP_TABLE[i] ^ paramArrayOfInt[(j + paramInt1)];
/*      */       }
/*      */ 
/* 2403 */       return i;
/*      */     }
/*      */     // static int F(int[] paramArrayOfInt, int paramInt1, int paramInt2) // computeCRC8
/*      */     static int computeCRC8(int[] paramArrayOfInt, int paramInt1, int paramInt2)
/*      */     {
/* 2422 */       int i = 0;
/*      */ 
/* 2424 */       Contract.pre(paramArrayOfInt != null);
/* 2425 */       Contract.pre(paramArrayOfInt.length >= paramInt1 + paramInt2);
/*      */ 
/* 2427 */       for (int j = 0; j < paramInt2; j++) {
/* 2428 */         i = MedicalDevice.CRC8_LOOKUP_TABLE[((i ^ paramArrayOfInt[(j + paramInt1)]) & 0xFF)];
/*      */       }
/*      */ 
/* 2431 */       return i;
/*      */     }
/*      */ 
/*      */     // static int G(int[] paramArrayOfInt, int paramInt1, int paramInt2)
/*      */     // Calculate low byte of adding paramInt1 ints of paramArrayOfInt starting at paramInt2 !!!!!
/*      */     static int computeSUM(int[] paramArrayOfInt, int paramInt1, int paramInt2)
/*      */     {
/* 2445 */       Contract.pre(paramArrayOfInt != null);
/* 2446 */       Contract.pre(paramArrayOfInt.length >= paramInt1 + paramInt2);
/* 2447 */       int i = 0;
/*      */ 
/* 2449 */       for (int j = 0; j < paramInt2; j++) {
/* 2450 */         i += paramArrayOfInt[(j + paramInt1)];
/* 2451 */         i &= 255;
/*      */       }
/* 2453 */       return i;
/*      */     }
/*      */     // int A(int[] paramArrayOfInt, int paramInt1, int paramInt2)
/*      */     static int computeCRC8BD(int[] paramArrayOfInt, int paramInt1, int paramInt2)
/*      */     {
/* 2474 */       int i = 0;
/*      */ 
/* 2476 */       Contract.pre(paramArrayOfInt != null);
/* 2477 */       Contract.pre(paramArrayOfInt.length >= paramInt1 + paramInt2);
/*      */ 
/* 2479 */       for (int j = 0; j < paramInt2; j++) {
/* 2480 */         i = MedicalDevice.CRC8_LOOKUP_TABLE_BD[((i ^ paramArrayOfInt[(j + paramInt1)]) & 0xFF)];
/*      */       }
/*      */ 
/* 2483 */       return i;
/*      */     }
/*      */     // static int H(int[] paramArrayOfInt)
/*      */     static int computeCRC8E1381(int[] paramArrayOfInt)
/*      */     {
/* 2499 */       int i = 0;
/*      */ 
/* 2501 */       Contract.pre(paramArrayOfInt != null);
/* 2502 */       Contract.pre(paramArrayOfInt.length >= 2);
/*      */ 
/* 2504 */       int j = 0;
/* 2505 */       int k = 0;
/* 2506 */       int m = 0;
/* 2507 */       int n = 0;
/*      */ 
/* 2509 */       for (int i1 = 0; (i1 < paramArrayOfInt.length) && (m == 0); i1++) {
/* 2510 */         switch (paramArrayOfInt[i1]) {
/*      */         case 2:
/* 2512 */           j = 1;
/* 2513 */           break;
/*      */         case 23:
/* 2515 */           k = 1;
/* 2516 */           n = paramArrayOfInt[i1];
/* 2517 */           break;
/*      */         case 3:
/* 2519 */           k = 1;
/* 2520 */           n = paramArrayOfInt[i1];
/* 2521 */           break;
/*      */         default:
/* 2523 */           if ((j != 0) && (k == 0))
/*      */           {
/* 2525 */             i += paramArrayOfInt[i1];
/* 2526 */             i &= 255; } else {
/* 2527 */             if ((j == 0) || (k == 0))
/*      */               continue;
/* 2529 */             i += n;
/* 2530 */             i &= 255;
/* 2531 */             m = 1;
/*      */           }
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/* 2537 */       return i;
/*      */     }
/*      */     // static int C(int[] paramArrayOfInt, int paramInt1, int paramInt2)
/*      */     static int computeCRC16CCITT(int[] paramArrayOfInt, int paramInt1, int paramInt2)
/*      */     {
/* 2553 */       Contract.pre(paramArrayOfInt != null);
/* 2554 */       Contract.pre(paramArrayOfInt.length >= paramInt1 + paramInt2);
/*      */ 
/* 2556 */       int i = 65535;
/*      */ 
/* 2558 */       for (int k = paramInt1; k < paramInt1 + paramInt2; k++) {
/* 2559 */         int j = paramArrayOfInt[k] ^ i >> 8;
/* 2560 */         i = (O.ĉ[j] ^ i << 8) & 0xFFFF;
/*      */       }
/*      */ 
/* 2564 */       return i;
/*      */     }
/*      */     // static int D(int[] paramArrayOfInt, int paramInt1, int paramInt2)
/*      */     static int computeCRC16sum(int[] paramArrayOfInt, int paramInt1, int paramInt2)
/*      */     {
/* 2579 */       Contract.pre(paramArrayOfInt != null);
/* 2580 */       Contract.pre(paramArrayOfInt.length >= paramInt1 + paramInt2);
/*      */ 
/* 2582 */       int i = 0;
/*      */ 
/* 2584 */       for (int j = paramInt1; j < paramInt1 + paramInt2; j++) {
/* 2585 */         i = i + paramArrayOfInt[j] & 0xFFFF;
/*      */       }
/*      */ 
/* 2588 */       return i;
/*      */     }
/*      */     // static int A(int paramInt1, String paramString, int paramInt2, int paramInt3)
/*      */     static int computeCRC8XOR(int paramInt1, String paramString, int paramInt2, int paramInt3)
/*      */     {
/* 2604 */       Contract.preNonNull(paramString);
/* 2605 */       Contract.pre(paramString.length() >= paramInt2 + paramInt3);
/*      */ 
/* 2607 */       int i = paramInt1;
/*      */ 
/* 2609 */       for (int j = paramInt2; j < paramInt2 + paramInt3; j++) {
/* 2610 */         i = (i ^ paramString.charAt(j)) & 0xFF;
/*      */       }
/* 2612 */       return i;
/*      */     }
/*      */     // static boolean C(String paramString)
/*      */     static boolean isCRC8E1381Valid(String paramString)
/*      */     {
/* 2626 */       int i = 0;
/*      */ 
/* 2628 */       Contract.pre(paramString != null);
/* 2629 */       Contract.pre(paramString.length() >= 2);
/*      */       String str;
/*      */       try {
/* 2636 */         str = remainderOf(paramString, String.valueOf('\003'));
/*      */       }
/*      */       catch (BadDeviceValueException localBadDeviceValueException1) {
/*      */         try {
/* 2640 */           str = remainderOf(paramString, String.valueOf('\027'));
/*      */         } catch (BadDeviceValueException localBadDeviceValueException2) {
/* 2642 */           return i;
/*      */         }
/*      */       }
/*      */ 
/* 2646 */       if (str.length() > 1) {
/* 2647 */         int j = str.charAt(0);
/* 2648 */         int k = str.charAt(1);
/* 2649 */         int m = computeCRC8E1381(makeIntArray(paramString));
/* 2650 */         int n = convertHexToASCII(getHighNibble(m));
/* 2651 */         int i1 = convertHexToASCII(getLowNibble(m));
/*      */ 
/* 2653 */         if ((n == j) && (i1 == k)) {
/* 2654 */           i = 1;
/*      */         }
/*      */       }
/*      */ 
/* 2658 */       return i;
/*      */     }
/*      */     // static boolean C(int paramInt)
/*      */     static boolean isEven(int paramInt)
/*      */     {
/* 2668 */       return paramInt % 2 == 0;
/*      */     }
/*      */     // static boolean I(int paramInt)
/*      */     static boolean isOdd(int paramInt)
/*      */     {
/* 2678 */       return !isEven(paramInt);
/*      */     }
/*      */     // static boolean F(int[] paramArrayOfInt)
/*      */     static boolean isZeros(int[] paramArrayOfInt)
/*      */     {
/* 2689 */       if (paramArrayOfInt != null) {
/* 2690 */         for (int i = 0; i < paramArrayOfInt.length; i++) {
/* 2691 */           if (paramArrayOfInt[i] != 0) {
/* 2692 */             return false;
/*      */           }
/*      */         }
/* 2695 */         return true;
/*      */       }
/* 2697 */       return false;
/*      */     }
/*      */     // static void A(int paramInt1, int paramInt2, int paramInt3, String paramString)
/*      */     static void verifyDeviceValue(int paramInt1, int paramInt2, int paramInt3, String paramString)
/*      */       throws BadDeviceValueException
/*      */     {
/* 2712 */       if ((paramInt1 < paramInt2) || (paramInt1 > paramInt3))
/* 2713 */         throw new BadDeviceValueException("The value of " + paramInt1 + " for '" + paramString + "' is out of range; " + "must be " + paramInt2 + " to " + paramInt3);
/*      */     }
/*      */     // static void A(double paramDouble1, double paramDouble2, double paramDouble3, String paramString)
/*      */     static void verifyDeviceValue(double paramDouble1, double paramDouble2, double paramDouble3, String paramString)
    /*      */       throws BadDeviceValueException
/*      */     {
/* 2731 */       if ((paramDouble1 < paramDouble2) || (paramDouble1 > paramDouble3))
/* 2732 */         throw new BadDeviceValueException("The value of " + paramDouble1 + " for '" + paramString + "' is out of range; " + "must be " + paramDouble2 + " to " + paramDouble3);
/*      */     }
/*      */     // static void A(boolean paramBoolean, String paramString)
/*      */     static void verifyDeviceValue(boolean paramBoolean, String paramString)
/*      */       throws BadDeviceValueException
/*      */     {
/* 2748 */       if (!paramBoolean)
/* 2749 */         throw new Z("Condition failed: '" + paramString + "'");
/*      */     }
/*      */     // static int E(int paramInt)
/*      */     static int convertYear(int paramInt)
/*      */       throws BadDeviceValueException
/*      */     {
/* 2763 */       A(paramInt, 0, 9999, "year");
/*      */ 
/* 2765 */       if ((paramInt == 97) || (paramInt == 98) || (paramInt == 99)) {
/* 2766 */         paramInt += 1900;
/*      */       }
/* 2768 */       return paramInt > 1000 ? paramInt : paramInt + 2000;
/*      */     }
/*      */     // static Date B(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
/*      */     static Date createDate(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
/*      */     {
/* 2790 */       Contract.pre(paramInt1, 1, 31);
/* 2791 */       Contract.pre(paramInt2, 1, 12);
/* 2792 */       Contract.pre(paramInt3, 2000, 2099);
/* 2793 */       Contract.pre(paramInt4, 0, 23);
/* 2794 */       Contract.pre(paramInt5, 0, 59);
/* 2795 */       Contract.pre(paramInt6, 0, 59);
/*      */ 
/* 2797 */       return createDateSync(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6);
/*      */     }
/*      */     // static long A()
/*      */     static long getCurrentTimeMillis()
/*      */     {
/* 2806 */       return System.currentTimeMillis();
/*      */     }
/*      */     // static long F(long paramLong)
/*      */     static long getElapsedTimeSecs(long paramLong)
/*      */     {
/* 2816 */       return A(paramLong) / 1000L;
/*      */     }
/*      */     // static long A(long paramLong)
/*      */     static long getElapsedTimeMillis(long paramLong)
/*      */     {
/* 2826 */       return System.currentTimeMillis() - paramLong;
/*      */     }
/*      */     // static String E(long paramLong)
/*      */     static String getHex(long paramLong)
/*      */     {
/* 2839 */       String str = paramLong == -1L ? "" : "0x";
/* 2840 */       return str + B(paramLong);
/*      */     }
/*      */     // static String H(int paramInt)
/*      */     static String getHex(int paramInt)
/*      */     {
/* 2853 */       String str = paramInt == -1 ? "" : "0x";
/* 2854 */       return str + F(paramInt);
/*      */     }
/*      */     // static String B(byte paramByte)
/*      */     static String getHex(byte paramByte)
/*      */     {
/* 2867 */       String str = paramByte == -1 ? "" : "0x";
/* 2868 */       return str + A(paramByte);
/*      */     }
/*      */     // static String B(byte[] paramArrayOfByte, int paramInt)
/*      */     static String getHex(byte[] paramArrayOfByte, int paramInt)
/*      */     {
/* 2880 */       StringBuffer localStringBuffer = new StringBuffer();
/*      */ 
/* 2882 */       if (paramArrayOfByte != null)
/*      */       {
/* 2884 */         paramInt = Math.min(paramInt, paramArrayOfByte.length);
/*      */ 
/* 2886 */         for (int i = 0; i < paramInt; i++) {
/* 2887 */           localStringBuffer.append(B(paramArrayOfByte[i]));
/*      */ 
/* 2889 */           if (i < paramInt - 1) {
/* 2890 */             localStringBuffer.append(" ");
/*      */           }
/*      */         }
/*      */       }
/*      */ 
/* 2895 */       return new String(localStringBuffer);
/*      */     }
/*      */     // static String A(int[] paramArrayOfInt, int paramInt)
/*      */     static String getHex(int[] paramArrayOfInt, int paramInt)
/*      */     {
/* 2909 */       StringBuffer localStringBuffer = new StringBuffer();
/*      */ 
/* 2911 */       if (paramArrayOfInt != null)
/*      */       {
/* 2913 */         paramInt = Math.min(paramInt, paramArrayOfInt.length);
/*      */ 
/* 2915 */         for (int i = 0; i < paramInt; i++) {
/* 2916 */           localStringBuffer.append(H(paramArrayOfInt[i]));
/*      */ 
/* 2918 */           if (i < paramInt - 1) {
/* 2919 */             localStringBuffer.append(" ");
/*      */           }
/*      */         }
/*      */       }
/*      */ 
/* 2924 */       return new String(localStringBuffer);
/*      */     }
/*      */     // static String A(byte[] paramArrayOfByte)
/*      */     static String getHex(byte[] paramArrayOfByte)
/*      */     {
/* 2935 */       return paramArrayOfByte == null ? null : B(paramArrayOfByte, paramArrayOfByte.length);
/*      */     }
/*      */     // static String D(int[] paramArrayOfInt)
/*      */     static String getHex(int[] paramArrayOfInt)
/*      */     {
/* 2946 */       return paramArrayOfInt == null ? null : A(paramArrayOfInt, paramArrayOfInt.length);
/*      */     }
/*      */     // static String B(long paramLong)
/*      */     static String getHexCompact(long paramLong)
/*      */     {
/* 2959 */       String str1 = Long.toHexString(paramLong).toUpperCase();
/*      */ 
/* 2961 */       String str2 = isOdd(str1.length()) ? "0" : "";
/* 2962 */       return str2 + str1;
/*      */     }
/*      */     // static String F(int paramInt)
/*      */     static String getHexCompact(int paramInt)
/*      */     {
/* 2975 */       long l = paramInt == -1 ? paramInt : A(paramInt);
/* 2976 */       return B(l);
/*      */     }
/*      */     // static String A(byte paramByte)
/*      */     static String getHexCompact(byte paramByte)
/*      */     {
/* 2989 */       int i = paramByte == -1 ? paramByte : C(paramByte);
/* 2990 */       return F(i);
/*      */     }
/*      */     // static String A(byte[] paramArrayOfByte, int paramInt)
/*      */     static String getHexCompact(byte[] paramArrayOfByte, int paramInt)
/*      */     {
/* 3002 */       StringBuffer localStringBuffer = new StringBuffer();
/*      */ 
/* 3004 */       if (paramArrayOfByte != null)
/*      */       {
/* 3006 */         paramInt = Math.min(paramInt, paramArrayOfByte.length);
/*      */ 
/* 3008 */         for (int i = 0; i < paramInt; i++) {
/* 3009 */           localStringBuffer.append(A(paramArrayOfByte[i]));
/*      */ 
/* 3011 */           if (i < paramInt - 1) {
/* 3012 */             localStringBuffer.append(" ");
/*      */           }
/*      */         }
/*      */       }
/*      */ 
/* 3017 */       return new String(localStringBuffer);
/*      */     }
/*      */     // static String B(int[] paramArrayOfInt, int paramInt)
/*      */     static String getHexCompact(int[] paramArrayOfInt, int paramInt)
/*      */     {
/* 3031 */       StringBuffer localStringBuffer = new StringBuffer();
/*      */ 
/* 3033 */       if (paramArrayOfInt != null)
/*      */       {
/* 3035 */         paramInt = Math.min(paramInt, paramArrayOfInt.length);
/*      */ 
/* 3037 */         for (int i = 0; i < paramInt; i++) {
/* 3038 */           localStringBuffer.append(F(paramArrayOfInt[i]));
/*      */ 
/* 3040 */           if (i < paramInt - 1) {
/* 3041 */             localStringBuffer.append(" ");
/*      */           }
/*      */         }
/*      */       }
/*      */ 
/* 3046 */       return new String(localStringBuffer);
/*      */     }
/*      */     // static String B(byte[] paramArrayOfByte)
/*      */     static String getHexCompact(byte[] paramArrayOfByte)
/*      */     {
/* 3057 */       if (paramArrayOfByte != null) {
/* 3058 */         return getHexCompact(paramArrayOfByte, paramArrayOfByte.length);
/*      */       }
/* 3060 */       return null;
/*      */     }
/*      */     // static String G(int[] paramArrayOfInt)
/*      */     static String getHexCompact(int[] paramArrayOfInt)
/*      */     {
/* 3071 */       if (paramArrayOfInt != null) {
/* 3072 */         return getHexCompact(paramArrayOfInt, paramArrayOfInt.length);
/*      */       }
/* 3074 */       return null;
/*      */     }
/*      */     // private static synchronized Date A(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
/*      */     private static synchronized Date createDateSync(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
/*      */     {
/* 3092 */       CAL.clear();
/* 3093 */       CAL.set(paramInt3, paramInt2 - 1, paramInt1, paramInt4, paramInt5, paramInt6);
/* 3094 */       return CAL.getTime();
/*      */     }
/*      */   }
/*      */ 
/*      */   abstract class SnapshotCreator
/*      */   {
/*      */     SnapshotCreator(int arg2)
/*      */     {
/*      */       int i;
/* 1763 */       MedicalDevice.A(MedicalDevice.this, i);
/*      */     }
/*      */ 
/*      */     InputStream B()
/*      */       throws BadDeviceValueException, IOException
/*      */     {
/* 1782 */       createSnapshotBody();
/*      */ 
/* 1785 */       int i = MedicalDevice.this.m_lastCommandDescription.D();
/* 1786 */       MedicalDevice.logInfo(this, "createSnapshot: wrote " + i + " bytes.");
/*      */ 
/* 1788 */       if (i < MedicalDevice.A(MedicalDevice.this)) {
/* 1789 */         throw new Z("Resulting snapshot size is invalid: " + i + "; must be at least " + O.A(O.this) + " bytes.");
/*      */       }
/*      */ 
/* 1793 */       return MedicalDevice.this.m_lastCommandDescription.E();
/*      */     }
/*      */ 
/*      */     abstract void createSnapshotBody();
/*      */   }
/*      */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.A.O
 * JD-Core Version:    0.6.0
 */