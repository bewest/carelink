/*     */ package minimed.ddms.deviceportreader;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ final class ComStation1
/*     */ {
/*     */   static final String CS_WAKEUP_SEQUENCE = "--I must NEVER (really) exist!--";
/*     */   static final byte CS_WAKEUP_REQUEST = 5;
/*     */   static final byte CS_HELLO_REPLY = 4;
/*     */   static final byte CS_ACK = 23;
/*     */   static final byte CS_NAK = 24;
/*     */   static final byte CS_CMD_NULL = 0;
/*     */   static final byte CS_CMD_WAKEUP_508 = 9;
/*     */   static final int COMM_RETRIES = 1;
/*     */   static final int WAKEUP_COUNT = 4;
/*     */   static final byte CCP_PACKET_START = 14;
/*     */   static final byte CCP_PACKET_END = 16;
/*     */   private SerialPort m_serialPort;
/*     */ 
/*     */   ComStation1(SerialPort paramSerialPort)
/*     */   {
/*  78 */     this.m_serialPort = paramSerialPort;
/*     */   }
/*     */ 
/*     */   private boolean sendWakeupRequestCommand()
/*     */     throws IOException
/*     */   {
/*  93 */     int i = 0;
/*     */ 
/*  95 */     Contract.pre(this.m_serialPort != null);
/*  96 */     Contract.pre(this.m_serialPort.isOpen());
/*     */ 
/*  98 */     this.m_serialPort.write("--I must NEVER (really) exist!--");
/*     */ 
/* 101 */     for (int j = 0; j < 4; j++) {
/* 102 */       this.m_serialPort.write(5);
/* 103 */       i = this.m_serialPort.read();
/*     */     }
/* 105 */     return i == 4;
/*     */   }
/*     */ 
/*     */   private boolean sendInit508Command()
/*     */     throws IOException
/*     */   {
/* 117 */     int i = 0;
/*     */ 
/* 119 */     Contract.pre(this.m_serialPort != null);
/* 120 */     Contract.pre(this.m_serialPort.isOpen());
/*     */ 
/* 122 */     sendCommand(9);
/* 123 */     i = this.m_serialPort.read();
/* 124 */     return i == 23;
/*     */   }
/*     */ 
/*     */   void initCommunications(int paramInt)
/*     */     throws IOException
/*     */   {
/* 138 */     int i = 0;
/*     */ 
/* 140 */     Contract.pre(this.m_serialPort != null);
/* 141 */     Contract.pre(this.m_serialPort.isOpen());
/*     */ 
/* 143 */     MedicalDevice.logInfoHigh(this, "initCommunications: waking up Com Station.");
/*     */     do
/*     */     {
/* 146 */       bool = sendWakeupRequestCommand();
/* 147 */       i++;
/*     */     }
/* 149 */     while ((!bool) && (i <= 1));
/*     */ 
/* 151 */     if (!bool) {
/* 152 */       this.m_serialPort.dumpSerialStatus();
/* 153 */       throw new IOException("ComStation1 did not wakeup.");
/*     */     }
/*     */ 
/* 157 */     MedicalDevice.logInfoHigh(this, "initCommunications: waking up device with id=" + paramInt);
/*     */ 
/* 159 */     i = 0;
/* 160 */     boolean bool = false;
/* 161 */     this.m_serialPort.readUntilEmpty();
/*     */ 
/* 163 */     switch (paramInt) {
/*     */     case 0:
/*     */     }
/*     */     while (true) {
/* 167 */       bool = sendInit508Command();
/* 168 */       i++;
/*     */ 
/* 170 */       if (bool) break; if (i > 1) {
/* 171 */         break;
/*     */ 
/* 174 */         Contract.unreachable();
/* 175 */         return;
/*     */       }
/*     */     }
/* 177 */     if (!bool) {
/* 178 */       this.m_serialPort.dumpSerialStatus();
/* 179 */       throw new IOException("ComStation1 did not initialize the device with id=" + paramInt);
/*     */     }
/*     */   }
/*     */ 
/*     */   void endCommunications()
/*     */   {
/*     */   }
/*     */ 
/*     */   private byte[] buildPacket(byte paramByte)
/*     */   {
/* 205 */     byte[] arrayOfByte = { 14, 0, 0, 0, 0, 0, 0, 16 };
/*     */ 
/* 207 */     arrayOfByte[1] = paramByte;
/*     */ 
/* 210 */     for (int i = 1; i < 6; i++)
/*     */     {
/*     */       byte[] tmp56_53 = arrayOfByte; tmp56_53[6] = (byte)(tmp56_53[6] + arrayOfByte[i]);
/*     */     }
/* 213 */     return arrayOfByte;
/*     */   }
/*     */ 
/*     */   private void sendCommand(byte paramByte)
/*     */     throws IOException
/*     */   {
/* 225 */     byte[] arrayOfByte = buildPacket(paramByte);
/*     */ 
/* 227 */     Contract.pre(this.m_serialPort != null);
/* 228 */     Contract.pre(this.m_serialPort.isOpen());
/*     */ 
/* 230 */     this.m_serialPort.write(arrayOfByte);
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.deviceportreader.ComStation1
 * JD-Core Version:    0.6.0
 */