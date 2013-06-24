/*      */ package minimed.ddms.deviceportreader;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import minimed.util.Contract;
/*      */ 
/*      */ final class ComLink1 extends CommunicationsLinkDefault
/*      */ {
/*      */   private static final byte CMD_READ_STATUS = 2;
/*      */   private static final byte CMD_TRANSMIT_PACKET = 4;
/*      */   private static final byte CMD_TRANSMIT_LAST_PACKET = 5;
/*      */   private static final byte CMD_RS232_MODE_SELECT = 6;
/*      */   private static final byte CMD_RF_MODE_SELECT = 7;
/*      */   private static final byte CMD_TRANSFER_DATA = 8;
/*      */   private static final byte CMD_MULTI_TRANSMIT = 10;
/*      */   private static final byte READY = 51;
/*      */   private static final byte ACK = 85;
/*      */   private static final byte NAK = 102;
/*      */   private static final byte PUMP_ACK = 6;
/*      */   private static final byte PUMP_NAK = 21;
/*      */   private static final int COMM_RETRIES = 1;
/*      */   private static final int PARADIGMLINK_DELAY_LIMIT = 150;
/*      */   private static final int PARADIGMLINK_DELAY_BASE = 70;
/*      */   private static final int PARADIGMLINK_DELAY_INCREMENT = 5;
/*   81 */   private static final int[] DC_ENCODE_TABLE = { 21, 49, 50, 35, 52, 37, 38, 22, 26, 25, 42, 11, 44, 13, 14, 28 };
/*      */ 
/*  104 */   private int m_paradigmLinkDelay = 0;
/*      */   private int m_status;
/*      */   private int m_numDataBytes;
/*      */   private final int m_linkDeviceType;
/*      */   int m_serialPortNumber;
/*      */ 
/*      */   ComLink1(DeviceListener paramDeviceListener, String paramString, int paramInt1, int paramInt2)
/*      */   {
/*  124 */     super(paramDeviceListener, paramString, "ComLink1");
/*  125 */     this.m_serialPortNumber = paramInt2;
/*  126 */     this.m_linkDeviceType = paramInt1;
/*      */ 
/*  130 */     if (this.m_linkDeviceType == 15)
/*  131 */       this.m_paradigmLinkDelay = 70;
/*      */     else
/*  133 */       this.m_paradigmLinkDelay = 0;
/*      */   }
/*      */ 
/*      */   public void execute(DeviceCommand paramDeviceCommand)
/*      */     throws BadDeviceValueException, BadDeviceCommException, SerialIOHaltedException
/*      */   {
/*  149 */     RS232Command localRS232Command = new RS232Command(paramDeviceCommand);
/*  150 */     localRS232Command.execute();
/*      */   }
/*      */ 
/*      */   void initCommunicationsIO()
/*      */     throws IOException, SerialIOHaltedException
/*      */   {
/*  161 */     MedicalDevice.logInfo(this, "initCommunicationsIO: waking up ComLink1.");
/*  162 */     initSerialPort(this.m_serialPortNumber);
/*  163 */     getRS232Port().readUntilEmpty();
/*  164 */     sendCommandCheckReply(6, 51);
/*      */     try
/*      */     {
/*  168 */       int i = readStatus();
/*  169 */       if (i > 0) {
/*  170 */         MedicalDevice.logWarning(this, "initCommunicationsIO: dumping " + i + " bytes.");
/*      */ 
/*  173 */         sendTransferDataCommand();
/*      */ 
/*  176 */         int[] arrayOfInt = new int[i];
/*  177 */         getRS232Port().read(arrayOfInt);
/*  178 */         readAckByte();
/*      */       }
/*      */     }
/*      */     catch (BadDeviceCommException localBadDeviceCommException)
/*      */     {
/*      */     }
/*      */     catch (IOException localIOException)
/*      */     {
/*      */     }
/*      */   }
/*      */ 
/*      */   void endCommunicationsIO()
/*      */     throws IOException
/*      */   {
/*  193 */     if (getRS232Port() != null)
/*      */     {
/*  195 */       getRS232Port().close();
/*  196 */       setCommPort(null);
/*      */     }
/*      */   }
/*      */ 
/*      */   void initSerialPort(int paramInt)
/*      */     throws IOException
/*      */   {
/*  208 */     beginSerialPort(paramInt);
/*      */ 
/*  210 */     int i = 0;
/*  211 */     int j = 0;
/*      */     do
/*      */     {
/*      */       try
/*      */       {
/*  216 */         setCommPort(new SerialPort(paramInt, MMPump.m_baudRate));
/*  217 */         getRS232Port().setReadTimeOut(500);
/*  218 */         j = 1;
/*      */       }
/*      */       catch (IOException localIOException) {
/*  221 */         i++;
/*      */ 
/*  223 */         if (i >= 0) {
/*  224 */           throw localIOException;
/*      */         }
/*      */ 
/*  227 */         MedicalDevice.logInfo(this, "initSerialPort: waiting for port to become available...e=" + localIOException);
/*      */ 
/*  229 */         MedicalDevice.Util.sleepMS(5000);
/*      */       }
/*      */     }
/*  231 */     while (j == 0);
/*      */ 
/*  233 */     getRS232Port().readUntilEmpty();
/*  234 */     getRS232Port().setIODelay(MMPump.m_ioDelayMS);
/*      */   }
/*      */ 
/*      */   void beginSerialPort(int paramInt)
/*      */     throws IOException
/*      */   {
/*  245 */     this.m_serialPortNumber = paramInt;
/*  246 */     setPhase(2);
/*      */ 
/*  248 */     if (getRS232Port() != null)
/*  249 */       getRS232Port().close();
/*      */   }
/*      */ 
/*      */   private SerialPort getRS232Port()
/*      */   {
/*  259 */     return (SerialPort)getCommPort();
/*      */   }
/*      */ 
/*      */   private void sendTransferDataCommand()
/*      */     throws IOException, SerialIOHaltedException
/*      */   {
/*  271 */     Contract.pre(getRS232Port() != null);
/*  272 */     Contract.pre(getRS232Port().isOpen());
/*      */ 
/*  274 */     MedicalDevice.logInfoHigh(this, "sendTransferDataCommand: sending cmd.");
/*  275 */     sendCommand(8);
/*      */   }
/*      */ 
/*      */   private void readReadyByte(boolean paramBoolean)
/*      */     throws IOException, SerialIOHaltedException
/*      */   {
/*  286 */     int i = 0;
/*      */ 
/*  288 */     for (int j = 0; (j <= 1) && (i == 0); j++)
/*      */       try {
/*  290 */         readReadyByteIO(paramBoolean);
/*  291 */         i = 1;
/*      */       }
/*      */       catch (IOException localIOException)
/*      */       {
/*  295 */         if (this.m_linkDeviceType == 15) {
/*  296 */           this.m_paradigmLinkDelay = Math.min(this.m_paradigmLinkDelay + 5, 150);
/*      */ 
/*  299 */           MedicalDevice.logInfo(this, "readReadyByte: increasing m_paradigmLinkDelay to " + this.m_paradigmLinkDelay);
/*      */         }
/*      */ 
/*  303 */         if (j != 1)
/*      */           continue;
/*  305 */         throw localIOException;
/*      */       }
/*      */   }
/*      */ 
/*      */   private void readAckByte()
/*      */     throws IOException, SerialIOHaltedException
/*      */   {
/*  318 */     int i = 0;
/*  319 */     int j = 0;
/*  320 */     int k = 0;
/*      */ 
/*  323 */     for (int m = 0; (m <= 1) && (j == 0); m++) {
/*  324 */       i = getRS232Port().read();
/*  325 */       j = i == 85 ? 1 : 0;
/*  326 */       if (i == 102) {
/*  327 */         k = 1;
/*      */       }
/*      */     }
/*      */ 
/*  331 */     if (j == 0) {
/*  332 */       getRS232Port().dumpSerialStatus();
/*  333 */       if (k != 0) {
/*  334 */         throw new IOException("readAckByte: reply " + MedicalDevice.Util.getHex(102) + " (NAK) does not match expected ACK reply of " + MedicalDevice.Util.getHex(85));
/*      */       }
/*      */ 
/*  338 */       throw new IOException("readAckByte: reply " + MedicalDevice.Util.getHex(i) + " does not match expected ACK reply of " + MedicalDevice.Util.getHex(85));
/*      */     }
/*      */   }
/*      */ 
/*      */   private int readStatus()
/*      */     throws BadDeviceCommException, IOException, SerialIOHaltedException
/*      */   {
/*  356 */     this.m_status = sendCommandGetReply(2);
/*      */ 
/*  358 */     this.m_numDataBytes = getRS232Port().read();
/*  359 */     readAckByte();
/*  360 */     MedicalDevice.logInfoHigh(this, "readStatus: CS status follows: NumberReceivedDataBytes=" + this.m_numDataBytes + ", ReceivedData=" + isStatusReceivedData() + ", RS232Mode=" + isStatusRS232Mode() + ", FilterRepeat=" + isStatusFilterRepeat() + ", AutoSleep=" + isStatusAutoSleep() + ", StatusError=" + isStatusError() + ", SelfTestError=" + isStatusSelfTestError());
/*      */ 
/*  368 */     if (isStatusError()) {
/*  369 */       throw new BadDeviceCommException("readStatus: ComLink1 has STATUS ERROR");
/*      */     }
/*      */ 
/*  372 */     if (isStatusSelfTestError()) {
/*  373 */       throw new BadDeviceCommException("readStatus: ComLink1 has SELFTEST ERROR");
/*      */     }
/*      */ 
/*  376 */     return isStatusReceivedData() ? this.m_numDataBytes : 0;
/*      */   }
/*      */ 
/*      */   private void readReadyByteIO(boolean paramBoolean)
/*      */     throws IOException, SerialIOHaltedException
/*      */   {
/*  388 */     int i = 0;
/*  389 */     int j = 0;
/*      */ 
/*  391 */     if (paramBoolean)
/*      */     {
/*  395 */       MedicalDevice.Util.sleepMS(this.m_paradigmLinkDelay);
/*  396 */       sendCommand(7);
/*      */ 
/*  398 */       MedicalDevice.Util.sleepMS(this.m_paradigmLinkDelay);
/*  399 */       readAckByte();
/*      */     } else {
/*  401 */       MedicalDevice.Util.sleepMS(this.m_paradigmLinkDelay);
/*      */     }
/*      */ 
/*  405 */     i = getRS232Port().read();
/*  406 */     j = i == 51 ? 1 : 0;
/*      */ 
/*  408 */     if (j == 0)
/*  409 */       throw new IOException("readReadyByteIO: reply " + MedicalDevice.Util.getHex(i) + " does not match expected READY reply of " + MedicalDevice.Util.getHex(51));
/*      */   }
/*      */ 
/*      */   private int sendCommandGetReply(byte paramByte)
/*      */     throws IOException, SerialIOHaltedException
/*      */   {
/*  427 */     Contract.pre(getRS232Port() != null);
/*  428 */     Contract.pre(getRS232Port().isOpen());
/*      */ 
/*  430 */     sendCommand(paramByte);
/*  431 */     return getRS232Port().read();
/*      */   }
/*      */ 
/*      */   private void sendCommandCheckReply(byte paramByte1, byte paramByte2)
/*      */     throws IOException, SerialIOHaltedException
/*      */   {
/*  448 */     Contract.pre(getRS232Port() != null);
/*  449 */     Contract.pre(getRS232Port().isOpen());
/*      */ 
/*  451 */     byte b = 0;
/*  452 */     int i = 0;
/*      */ 
/*  455 */     for (int j = 0; (j <= 1) && (i == 0); j++) {
/*  456 */       b = sendCommandGetReply(paramByte1);
/*  457 */       i = b == paramByte2 ? 1 : 0;
/*      */     }
/*      */ 
/*  460 */     if (i == 0) {
/*  461 */       getRS232Port().dumpSerialStatus();
/*  462 */       throw new IOException("SendCommand: command " + MedicalDevice.Util.getHex(paramByte1) + " reply " + MedicalDevice.Util.getHex(b) + " does not match expected command " + MedicalDevice.Util.getHex(paramByte2));
/*      */     }
/*      */   }
/*      */ 
/*      */   private void sendCommand(byte paramByte)
/*      */     throws IOException, SerialIOHaltedException
/*      */   {
/*  479 */     Contract.pre(getRS232Port() != null);
/*  480 */     Contract.pre(getRS232Port().isOpen());
/*      */ 
/*  482 */     getRS232Port().write(paramByte);
/*      */   }
/*      */ 
/*      */   private boolean isStatusFilterRepeat()
/*      */   {
/*  492 */     return (this.m_status & 0x40) > 0;
/*      */   }
/*      */ 
/*      */   private boolean isStatusAutoSleep()
/*      */   {
/*  501 */     return (this.m_status & 0x20) > 0;
/*      */   }
/*      */ 
/*      */   private boolean isStatusError()
/*      */   {
/*  510 */     return (this.m_status & 0x10) > 0;
/*      */   }
/*      */ 
/*      */   private boolean isStatusSelfTestError()
/*      */   {
/*  519 */     return (this.m_status & 0x8) > 0;
/*      */   }
/*      */ 
/*      */   private boolean isStatusRS232Mode()
/*      */   {
/*  528 */     return (this.m_status & 0x4) > 0;
/*      */   }
/*      */ 
/*      */   private boolean isStatusReceivedData()
/*      */   {
/*  537 */     return (this.m_status & 0x1) > 0;
/*      */   }
/*      */ 
/*      */   private class RS232Command
/*      */   {
/*      */     private static final int TYPE_CODE = 167;
/*      */     private static final int CMD_PACKET_LENGTH = 7;
/*      */     private static final int DC_ENCODE_WIDTH = 6;
/*      */     private static final int DC_DECODE_WIDTH = 4;
/*      */     private static final int HEADER_SIZE = 6;
/*      */     private static final int MAX_SEQUENCE_NUM = 127;
/*      */     private static final int PUMP_EOT = 128;
/*      */     private static final int REC_COUNT_NONE = 0;
/*      */     private static final int REC_SIZE_MIN = 64;
/*      */     private static final int REC_SIZE_NONE = 0;
/*      */     private static final int RF_ON_DELAY = 17000;
/*      */     MM511.Command m_deviceCommand;
/*      */     private Integer m_sequenceNumber;
/*      */     private final ComLink1 this$0;
/*      */ 
/*      */     private RS232Command(DeviceCommand arg2)
/*      */     {
/*  591 */       this.this$0 = this$1;
/*      */       Object localObject;
/*  592 */       this.m_deviceCommand = ((MM511.Command)localObject);
/*      */     }
/*      */ 
/*      */     public String toString()
/*      */     {
/*  601 */       return this.m_deviceCommand.toString();
/*      */     }
/*      */ 
/*      */     private void execute()
/*      */       throws BadDeviceCommException, BadDeviceValueException, SerialIOHaltedException
/*      */     {
/*  615 */       if (this.m_deviceCommand.m_commandParameterCount > 0)
/*      */       {
/*  619 */         DeviceCommand localDeviceCommand = makeCommandPacket();
/*      */ 
/*  623 */         localDeviceCommand.execute();
/*      */       }
/*      */ 
/*  630 */       MedicalDevice.m_lastCommandDescription = this.m_deviceCommand.m_description;
/*      */ 
/*  632 */       allocateRawData();
/*      */ 
/*  635 */       sendAndRead();
/*      */     }
/*      */ 
/*      */     private void sendAndRead()
/*      */       throws BadDeviceCommException
/*      */     {
/*  648 */       if (this.this$0.getState() != 7) {
/*  649 */         this.this$0.setState(4);
/*      */       }
/*  651 */       sendCommand();
/*      */ 
/*  654 */       if ((this.m_deviceCommand.m_rawData.length > 0) && (!this.this$0.getDeviceListener().isHaltRequested()))
/*      */       {
/*  657 */         if (this.m_deviceCommand.m_rawData.length > 64)
/*      */         {
/*  659 */           this.m_deviceCommand.m_rawData = readDeviceDataPage(this.m_deviceCommand.m_rawData.length);
/*      */         }
/*      */         else {
/*  662 */           this.this$0.setState(5);
/*      */ 
/*  664 */           this.m_deviceCommand.m_rawData = readDeviceData();
/*  665 */           this.this$0.incTotalReadByteCount(this.m_deviceCommand.m_rawData.length);
/*      */ 
/*  668 */           this.this$0.notifyDeviceUpdateProgress();
/*      */         }
/*      */       }
/*      */       else {
/*      */         try {
/*  673 */           checkAck();
/*      */         } catch (IOException localIOException) {
/*  675 */           throw new BadDeviceCommException("sendAndRead: ERROR - problem checking ACK; exception = " + localIOException);
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*  681 */       if (this.this$0.getState() == 7)
/*  682 */         this.this$0.setState(4);
/*      */     }
/*      */ 
/*      */     private void sendCommand()
/*      */       throws BadDeviceCommException, SerialIOHaltedException
/*      */     {
/*  697 */       Contract.pre(this.this$0.getRS232Port() != null);
/*  698 */       Contract.pre(this.this$0.getRS232Port().isOpen());
/*      */ 
/*  700 */       MedicalDevice.logInfo(this, "sendCommand: SENDING CMD " + this.m_deviceCommand + "for pump #" + this.this$0.getDeviceSerialNumber());
/*      */       try
/*      */       {
/*  705 */         int[] arrayOfInt1 = buildPacket();
/*  706 */         int[] arrayOfInt2 = new int[2];
/*      */ 
/*  711 */         if (this.m_deviceCommand.isUseMultiXmitMode()) {
/*  712 */           arrayOfInt2[0] = 10;
/*      */         }
/*  714 */         else if (this.m_deviceCommand.m_commandParameterCount == 0)
/*      */         {
/*  716 */           arrayOfInt2[0] = 5;
/*      */         }
/*      */         else {
/*  719 */           arrayOfInt2[0] = 4;
/*      */         }
/*      */ 
/*  724 */         arrayOfInt2[1] = arrayOfInt1.length;
/*  725 */         arrayOfInt1 = MedicalDevice.Util.concat(arrayOfInt2, arrayOfInt1);
/*      */ 
/*  728 */         this.this$0.getRS232Port().write(arrayOfInt1);
/*      */ 
/*  730 */         MedicalDevice.logInfo(this, "sendCommand: reading link device ACK & (optional) RDY byte.");
/*      */ 
/*  732 */         this.this$0.readAckByte();
/*      */ 
/*  736 */         if ((this.m_deviceCommand.m_commandCode == 93) && (this.m_deviceCommand.m_commandParameterCount == 0) && (this.m_deviceCommand.m_commandParameters[0] == 1))
/*      */         {
/*  740 */           int i = this.this$0.getRS232Port().getReadTimeOut();
/*  741 */           this.this$0.getRS232Port().setReadTimeOut(17000);
/*      */ 
/*  744 */           this.this$0.readReadyByte(arrayOfInt2[0] == 4);
/*      */ 
/*  747 */           this.this$0.getRS232Port().setReadTimeOut(i);
/*      */         } else {
/*  749 */           this.this$0.readReadyByte(arrayOfInt2[0] == 4);
/*      */         }
/*      */       } catch (IOException localIOException) {
/*  752 */         throw new BadDeviceCommException("sendCommand: ERROR - an IOException  has occurred processing cmd " + MedicalDevice.Util.getHex(this.m_deviceCommand.m_commandCode) + " (" + this.m_deviceCommand.m_description + "); exception = " + localIOException);
/*      */       }
/*      */     }
/*      */ 
/*      */     private void checkHeaderAndCRC(int[] paramArrayOfInt)
/*      */       throws BadDeviceCommException
/*      */     {
/*  771 */       int i = paramArrayOfInt.length - 1;
/*  772 */       int j = paramArrayOfInt[i];
/*  773 */       int k = MedicalDevice.Util.computeCRC8(paramArrayOfInt, 0, i) & 0xFF;
/*      */ 
/*  775 */       if (j != k) {
/*  776 */         throw new BadDeviceCommException("checkHeaderAndCRC: cmd " + MedicalDevice.Util.getHex(this.m_deviceCommand.m_commandCode) + " (" + this.m_deviceCommand.m_description + ")" + " resulted in bad CRC value of " + j + " (expected " + k + ") " + "(byte data = " + "<" + MedicalDevice.Util.getHex(paramArrayOfInt) + ">)");
/*      */       }
/*      */ 
/*  784 */       if (paramArrayOfInt[0] != 167) {
/*  785 */         throw new BadDeviceCommException("checkHeaderAndCRC: cmd " + MedicalDevice.Util.getHex(this.m_deviceCommand.m_commandCode) + " (" + this.m_deviceCommand.m_description + ")" + " resulted in bad Type Code value of " + MedicalDevice.Util.getHex(paramArrayOfInt[0]));
/*      */       }
/*      */ 
/*  794 */       int[] arrayOfInt = packSerialNumber();
/*      */ 
/*  796 */       if ((arrayOfInt[0] != paramArrayOfInt[1]) || (arrayOfInt[1] != paramArrayOfInt[2]) || (arrayOfInt[2] != paramArrayOfInt[3]))
/*      */       {
/*  799 */         throw new BadDeviceCommException("checkHeaderAndCRC: cmd " + MedicalDevice.Util.getHex(this.m_deviceCommand.m_commandCode) + " (" + this.m_deviceCommand.m_description + ")" + " resulted in bad serial number value of <" + MedicalDevice.Util.getHex(paramArrayOfInt[1]) + " " + MedicalDevice.Util.getHex(paramArrayOfInt[2]) + " " + MedicalDevice.Util.getHex(paramArrayOfInt[3]) + ">");
/*      */       }
/*      */     }
/*      */ 
/*      */     private void allocateRawData()
/*      */     {
/*  813 */       this.m_deviceCommand.m_rawData = new int[this.m_deviceCommand.m_bytesPerRecord * this.m_deviceCommand.m_maxRecords];
/*      */     }
/*      */ 
/*      */     private void checkAck()
/*      */       throws BadDeviceCommException, IOException, SerialIOHaltedException
/*      */     {
/*  829 */       Contract.pre(this.this$0.getRS232Port() != null);
/*  830 */       Contract.pre(this.this$0.getRS232Port().isOpen());
/*      */ 
/*  832 */       MedicalDevice.logInfo(this, "checkAck: retrieving pump ACK packet...");
/*      */ 
/*  834 */       int i = this.this$0.readStatus();
/*      */ 
/*  837 */       this.this$0.sendTransferDataCommand();
/*      */ 
/*  840 */       int[] arrayOfInt1 = new int[i];
/*  841 */       this.this$0.getRS232Port().read(arrayOfInt1);
/*  842 */       int[] arrayOfInt2 = decodeDC(arrayOfInt1);
/*      */ 
/*  845 */       this.this$0.readAckByte();
/*      */ 
/*  848 */       checkHeaderAndCRC(arrayOfInt2);
/*      */ 
/*  851 */       if (arrayOfInt2[4] != 6)
/*      */       {
/*  853 */         int j = arrayOfInt2[5];
/*  854 */         throw new BadDeviceCommException("checkAck: cmd " + MedicalDevice.Util.getHex(this.m_deviceCommand.m_commandCode) + " (" + this.m_deviceCommand.m_description + ")" + " failed; error code = <" + MedicalDevice.Util.getHex(j) + ">" + "(" + MM511.getNAKDescription(j) + ") " + "(byte data = " + "<" + MedicalDevice.Util.getHex(arrayOfInt2) + ">)", new Integer(j), MM511.getNAKDescription(j));
/*      */       }
/*      */ 
/*  863 */       MedicalDevice.logInfo(this, "checkAck: GOOD pump ACK reply received.");
/*      */     }
/*      */ 
/*      */     private void sendAck()
/*      */       throws IOException, SerialIOHaltedException
/*      */     {
/*  877 */       int i = 0;
/*      */ 
/*  879 */       Contract.pre(this.this$0.getRS232Port() != null);
/*  880 */       Contract.pre(this.this$0.getRS232Port().isOpen());
/*      */ 
/*  882 */       MedicalDevice.logInfo(this, "sendAck: sending cmd " + MedicalDevice.Util.getHex(6));
/*      */ 
/*  885 */       int[] arrayOfInt1 = new int[7];
/*      */ 
/*  888 */       arrayOfInt1[(i++)] = 167;
/*      */ 
/*  891 */       int[] arrayOfInt2 = packSerialNumber();
/*  892 */       arrayOfInt1[(i++)] = arrayOfInt2[0];
/*  893 */       arrayOfInt1[(i++)] = arrayOfInt2[1];
/*  894 */       arrayOfInt1[(i++)] = arrayOfInt2[2];
/*      */ 
/*  897 */       arrayOfInt1[(i++)] = 6;
/*      */ 
/*  900 */       arrayOfInt1[(i++)] = 0;
/*      */ 
/*  903 */       arrayOfInt1[(i++)] = MedicalDevice.Util.computeCRC8(arrayOfInt1, 0, i - 1);
/*      */ 
/*  906 */       arrayOfInt1 = encodeDC(arrayOfInt1);
/*      */ 
/*  909 */       int[] arrayOfInt3 = new int[2];
/*  910 */       arrayOfInt3[0] = 5;
/*  911 */       arrayOfInt3[1] = arrayOfInt1.length;
/*  912 */       arrayOfInt1 = MedicalDevice.Util.concat(arrayOfInt3, arrayOfInt1);
/*      */ 
/*  915 */       this.this$0.getRS232Port().write(arrayOfInt1);
/*  916 */       this.this$0.readAckByte();
/*  917 */       this.this$0.readReadyByte(arrayOfInt3[0] == 4);
/*      */     }
/*      */ 
/*      */     private int[] readDeviceDataPage(int paramInt)
/*      */       throws BadDeviceCommException, SerialIOHaltedException
/*      */     {
/*  933 */       int[] arrayOfInt1 = new int[70];
/*  934 */       int[] arrayOfInt2 = new int[0];
/*  935 */       int i = 0;
/*  936 */       int j = 1;
/*  937 */       int k = 0;
/*      */ 
/*  939 */       MedicalDevice.logInfo(this, "readDeviceDataPage: processing cmd " + MedicalDevice.Util.getHex(this.m_deviceCommand.m_commandCode) + " (" + this.m_deviceCommand.m_description + ")");
/*      */       do
/*      */       {
/*  945 */         this.this$0.setState(6);
/*  946 */         arrayOfInt1 = readDeviceData();
/*  947 */         if (arrayOfInt1.length == 0)
/*      */         {
/*  949 */           k = 1;
/*      */         } else {
/*  951 */           arrayOfInt2 = MedicalDevice.Util.concat(arrayOfInt2, arrayOfInt1);
/*  952 */           MedicalDevice.logInfo(this, "readDeviceDataPage: just read packet " + i + ", bytes = " + arrayOfInt1.length + ", total bytes = " + arrayOfInt2.length);
/*      */ 
/*  955 */           this.this$0.incTotalReadByteCount(arrayOfInt1.length);
/*  956 */           i++;
/*      */ 
/*  959 */           this.this$0.notifyDeviceUpdateProgress();
/*      */ 
/*  964 */           int m = (this.m_deviceCommand.m_dataCount & 0x80) == 128 ? 1 : 0;
/*  965 */           int n = this.m_deviceCommand.m_dataCount & 0x7F;
/*  966 */           if (n != j) {
/*  967 */             throw new BadDeviceCommException("readDeviceDataPage: ERROR - sequence number mismatch); expected " + j + ", read " + n);
/*      */           }
/*      */ 
/*  973 */           j++; if (j > 127) {
/*  974 */             j = 1;
/*      */           }
/*      */ 
/*  977 */           k = (arrayOfInt2.length >= paramInt) || (this.this$0.getDeviceListener().isHaltRequested()) || (m != 0) ? 1 : 0;
/*      */           try
/*      */           {
/*  981 */             if ((k == 0) && (!this.this$0.getDeviceListener().isHaltRequested())) {
/*  982 */               this.this$0.setState(4);
/*  983 */               sendAck();
/*      */             }
/*      */           } catch (IOException localIOException) {
/*  986 */             throw new BadDeviceCommException("readDeviceDataPage: ERROR - problem sending ACK; exception = " + localIOException);
/*      */           }
/*      */         }
/*      */       }
/*  990 */       while (k == 0);
/*      */ 
/*  993 */       MedicalDevice.logInfoLow(this, "readDeviceDataPage: cmd " + MedicalDevice.Util.getHex(this.m_deviceCommand.m_commandCode) + " (" + this.m_deviceCommand.m_description + ") returned " + arrayOfInt2.length + " bytes.");
/*      */ 
/*  996 */       return arrayOfInt2;
/*      */     }
/*      */ 
/*      */     private int[] readDeviceData() throws BadDeviceCommException, SerialIOHaltedException {
/* 1010 */       Contract.pre(this.this$0.getRS232Port() != null);
/* 1011 */       Contract.pre(this.this$0.getRS232Port().isOpen());
/*      */ 
/* 1014 */       int[] arrayOfInt2 = new int[0];
/*      */       int[] arrayOfInt1;
/*      */       try {
/* 1018 */         int i = this.this$0.readStatus();
/*      */ 
/* 1021 */         this.this$0.sendTransferDataCommand();
/*      */ 
/* 1024 */         arrayOfInt1 = new int[i];
/* 1025 */         this.this$0.getRS232Port().read(arrayOfInt1);
/*      */ 
/* 1027 */         this.this$0.readAckByte();
/*      */       } catch (IOException localIOException) {
/* 1029 */         throw new BadDeviceCommException("readDeviceData: ERROR - an IOException  has occurred processing cmd " + MedicalDevice.Util.getHex(this.m_deviceCommand.m_commandCode) + " (" + this.m_deviceCommand.m_description + "); exception = " + localIOException);
/*      */       }
/*      */ 
/* 1034 */       int[] arrayOfInt3 = decodeDC(arrayOfInt1);
/*      */ 
/* 1037 */       checkHeaderAndCRC(arrayOfInt3);
/* 1038 */       int j = 0;
/*      */ 
/* 1041 */       if (arrayOfInt3[4] == 21) {
/* 1042 */         int k = arrayOfInt3[5];
/* 1043 */         if (k == 13)
/* 1044 */           MedicalDevice.logInfo(this, "readDeviceData: NAK received = no more data.");
/*      */         else {
/* 1046 */           throw new BadDeviceCommException("readDeviceData: cmd " + MedicalDevice.Util.getHex(this.m_deviceCommand.m_commandCode) + " (" + this.m_deviceCommand.m_description + ")" + " failed; error code = <" + MedicalDevice.Util.getHex(k) + ">" + "(" + MM511.getNAKDescription(k) + ") " + "(byte data = " + "<" + MedicalDevice.Util.getHex(arrayOfInt3) + ">)", new Integer(k), MM511.getNAKDescription(k));
/*      */         }
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/* 1056 */         if (arrayOfInt3[4] != this.m_deviceCommand.m_commandCode) {
/* 1057 */           throw new BadDeviceCommException("readDeviceData: cmd " + MedicalDevice.Util.getHex(this.m_deviceCommand.m_commandCode) + " (" + this.m_deviceCommand.m_description + ")" + " has bad command code value of " + MedicalDevice.Util.getHex(arrayOfInt3[4]) + " (expected " + MedicalDevice.Util.getHex(this.m_deviceCommand.m_commandCode) + ") " + "(byte data = " + "<" + MedicalDevice.Util.getHex(arrayOfInt3) + ">)");
/*      */         }
/*      */ 
/* 1067 */         this.m_deviceCommand.m_dataCount = arrayOfInt3[5];
/*      */ 
/* 1070 */         j = arrayOfInt3.length - 6 - 1;
/* 1071 */         arrayOfInt2 = new int[j];
/* 1072 */         System.arraycopy(arrayOfInt3, 6, arrayOfInt2, 0, j);
/* 1073 */         MedicalDevice.logInfoHigh(this, "readDeviceData: decoded packet = <" + MedicalDevice.Util.getHex(arrayOfInt2) + ">");
/*      */       }
/*      */ 
/* 1078 */       return arrayOfInt2;
/*      */     }
/*      */ 
/*      */     private int[] buildPacket()
/*      */     {
/* 1090 */       int i = 0;
/*      */ 
/* 1092 */       Contract.pre(this.m_deviceCommand.m_cmdLength > 0);
/*      */ 
/* 1094 */       int[] arrayOfInt1 = new int[7];
/*      */ 
/* 1097 */       arrayOfInt1[(i++)] = 167;
/*      */ 
/* 1100 */       int[] arrayOfInt2 = packSerialNumber();
/* 1101 */       arrayOfInt1[(i++)] = arrayOfInt2[0];
/* 1102 */       arrayOfInt1[(i++)] = arrayOfInt2[1];
/* 1103 */       arrayOfInt1[(i++)] = arrayOfInt2[2];
/*      */ 
/* 1106 */       arrayOfInt1[(i++)] = this.m_deviceCommand.m_commandCode;
/*      */ 
/* 1110 */       if (this.m_deviceCommand.m_commandParameterCount == 0) {
/* 1111 */         arrayOfInt1[(i++)] = 0;
/*      */       }
/*      */       else {
/* 1114 */         int[] arrayOfInt3 = new int[i + 1];
/* 1115 */         System.arraycopy(arrayOfInt1, 0, arrayOfInt3, 0, i);
/*      */ 
/* 1120 */         if (this.m_sequenceNumber != null)
/*      */         {
/* 1122 */           arrayOfInt3[i] = this.m_sequenceNumber.intValue();
/*      */         }
/*      */         else {
/* 1125 */           arrayOfInt3[i] = this.m_deviceCommand.m_commandParameterCount;
/*      */         }
/*      */ 
/* 1129 */         arrayOfInt1 = MedicalDevice.Util.concat(arrayOfInt3, this.m_deviceCommand.m_commandParameters);
/*      */ 
/* 1132 */         arrayOfInt1 = MedicalDevice.Util.concat(arrayOfInt1, new int[1]);
/* 1133 */         i = arrayOfInt1.length - 1;
/*      */       }
/*      */ 
/* 1137 */       arrayOfInt1[(i++)] = MedicalDevice.Util.computeCRC8(arrayOfInt1, 0, i - 1);
/* 1138 */       this.m_deviceCommand.m_packet = arrayOfInt1;
/*      */ 
/* 1140 */       return encodeDC(arrayOfInt1);
/*      */     }
/*      */ 
/*      */     private DeviceCommand makeCommandPacket()
/*      */     {
/* 1149 */       MM511.Command localCommand = (MM511.Command)this.m_deviceCommand.clone();
/* 1150 */       localCommand.m_description = (this.m_deviceCommand.m_description + "-command packet");
/* 1151 */       localCommand.m_bytesPerRecord = 0;
/* 1152 */       localCommand.m_maxRecords = 0;
/* 1153 */       localCommand.m_commandType = 0;
/* 1154 */       localCommand.m_commandParameterCount = 0;
/*      */ 
/* 1157 */       if ((this.m_deviceCommand.m_commandCode == 93) && (this.m_deviceCommand.m_commandParameters[0] == 1))
/*      */       {
/* 1159 */         localCommand.setUseMultiXmitMode(true);
/*      */       }
/* 1161 */       return localCommand;
/*      */     }
/*      */ 
/*      */     private int[] packSerialNumber()
/*      */     {
/* 1173 */       Contract.pre(this.this$0.getDeviceSerialNumber() != null);
/* 1174 */       Contract.pre(this.this$0.getDeviceSerialNumber().length() == 6);
/*      */ 
/* 1176 */       return MedicalDevice.Util.makePackedBCD(this.this$0.getDeviceSerialNumber());
/*      */     }
/*      */ 
/*      */     private int[] encodeDC(int[] paramArrayOfInt)
/*      */     {
/* 1192 */       int[] arrayOfInt1 = new int[paramArrayOfInt.length * 3];
/* 1193 */       int i = 0;
/*      */ 
/* 1196 */       MedicalDevice.logInfo(this, "encodeDC: about to encode bytes = <" + MedicalDevice.Util.getHexCompact(paramArrayOfInt) + ">");
/*      */       int n;
/* 1200 */       for (int j = 0; j < paramArrayOfInt.length; j++) {
/* 1201 */         k = paramArrayOfInt[j];
/*      */ 
/* 1203 */         Contract.pre((k >= 0) && (k <= 255));
/*      */ 
/* 1207 */         int m = k >> 4 & 0xF;
/*      */ 
/* 1209 */         n = k & 0xF;
/*      */ 
/* 1212 */         i1 = ComLink1.DC_ENCODE_TABLE[m];
/* 1213 */         int i2 = ComLink1.DC_ENCODE_TABLE[n];
/*      */ 
/* 1216 */         arrayOfInt1[(i++)] = (i1 >> 2);
/*      */ 
/* 1220 */         int i3 = i1 & 0x3;
/*      */ 
/* 1223 */         int i4 = i2 >> 4 & 0x3;
/*      */ 
/* 1225 */         arrayOfInt1[(i++)] = (i3 << 2 | i4);
/*      */ 
/* 1228 */         arrayOfInt1[(i++)] = (i2 & 0xF);
/*      */       }
/*      */ 
/* 1232 */       j = 0;
/* 1233 */       int k = (int)Math.ceil(paramArrayOfInt.length * 6.0D / 4.0D);
/*      */ 
/* 1237 */       int[] arrayOfInt2 = new int[k];
/*      */ 
/* 1240 */       for (int i1 = 0; i1 < arrayOfInt1.length; i1 += 2)
/*      */       {
/* 1242 */         if (i1 < arrayOfInt1.length - 1) {
/* 1243 */           n = MedicalDevice.Util.makeByte(arrayOfInt1[i1], arrayOfInt1[(i1 + 1)]);
/*      */         }
/*      */         else {
/* 1246 */           n = MedicalDevice.Util.makeByte(arrayOfInt1[i1], 5);
/*      */         }
/*      */ 
/* 1249 */         Contract.post((n >= 0) && (n <= 255));
/* 1250 */         arrayOfInt2[(j++)] = n;
/*      */       }
/*      */ 
/* 1253 */       return arrayOfInt2;
/*      */     }
/*      */ 
/*      */     private int[] decodeDC(int[] paramArrayOfInt)
/*      */       throws BadDeviceCommException
/*      */     {
/* 1270 */       int i = 0;
/* 1271 */       int j = 0;
/* 1272 */       int k = 0;
/* 1273 */       int m = 0;
/*      */ 
/* 1276 */       int i1 = 0;
/* 1277 */       int i2 = (int)Math.floor(paramArrayOfInt.length * 4.0D / 6.0D);
/*      */ 
/* 1281 */       int[] arrayOfInt = new int[i2];
/*      */       int i4;
/* 1284 */       for (int i3 = 0; i3 < paramArrayOfInt.length; i3++)
/*      */       {
/* 1286 */         for (i4 = 7; i4 >= 0; )
/*      */         {
/* 1288 */           int i5 = paramArrayOfInt[i3] >> i4 & 0x1;
/*      */ 
/* 1290 */           k = k << 1 | i5;
/* 1291 */           i++;
/*      */ 
/* 1293 */           if (i == 6)
/*      */           {
/* 1295 */             j++;
/* 1296 */             if (j == 1) {
/* 1297 */               m = decodeDC(k);
/*      */             }
/*      */             else {
/* 1300 */               int n = decodeDC(k);
/* 1301 */               int i6 = MedicalDevice.Util.makeByte(m, n);
/* 1302 */               arrayOfInt[(i1++)] = i6;
/* 1303 */               j = 0;
/*      */             }
/*      */ 
/* 1307 */             k = 0;
/* 1308 */             i = 0;
/*      */           }
/* 1287 */           i4--;
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/* 1313 */       MedicalDevice.logInfo(this, "decodeDC: decoded bytes = <" + MedicalDevice.Util.getHexCompact(arrayOfInt) + ">");
/*      */ 
/* 1316 */       return arrayOfInt;
/*      */     }
/*      */ 
/*      */     private int decodeDC(int paramInt)
/*      */       throws BadDeviceCommException
/*      */     {
/* 1331 */       if ((paramInt < 0) || (paramInt > 63)) {
/* 1332 */         throw new BadDeviceCommException("decodeDC: value of " + paramInt + " is out of expected range 0.." + 63);
/*      */       }
/*      */ 
/* 1337 */       for (int i = 0; i < ComLink1.DC_ENCODE_TABLE.length; i++) {
/* 1338 */         if (ComLink1.DC_ENCODE_TABLE[i] == paramInt) {
/* 1339 */           return i;
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/* 1344 */       throw new BadDeviceCommException("decodeDC: Can't find value of " + MedicalDevice.Util.getHex(paramInt) + " in table.");
/*      */     }
/*      */ 
/*      */     RS232Command(DeviceCommand param1, ComLink1.1 arg3)
/*      */     {
/*  545 */       this(param1);
/*      */     }
/*      */   }
/*      */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.deviceportreader.ComLink1
 * JD-Core Version:    0.6.0
 */