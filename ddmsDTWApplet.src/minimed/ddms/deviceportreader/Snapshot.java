/*     */ package minimed.ddms.deviceportreader;
/*     */ 
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.FileWriter;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.io.PrintWriter;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import java.util.TreeMap;
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ final class Snapshot
/*     */ {
/*     */   private static final int SNAPLENGTH_2 = 2;
/*     */   private static final int SNAPLENGTH_4 = 4;
/*     */   private static final boolean LOG_DETAILED_DEBUG_INFO = true;
/*     */   private static final int SNAPSHOT_CRC_INDEX = 4;
/*  44 */   private static final String TERM = System.getProperty("line.separator");
/*     */   private SnapshotOutputStream m_snapshotOutputStream;
/*     */   private Header m_snapshotHeader;
/*     */   private TreeMap m_elementCollection;
/*     */ 
/*     */   Snapshot(int paramInt1, int paramInt2, int[] paramArrayOfInt1, int[] paramArrayOfInt2, int[] paramArrayOfInt3)
/*     */   {
/*  77 */     this.m_snapshotHeader = new Header(paramInt1, paramInt2, paramArrayOfInt1, paramArrayOfInt2, paramArrayOfInt3, null);
/*     */ 
/*  80 */     this.m_elementCollection = new TreeMap();
/*  81 */     this.m_snapshotOutputStream = null;
/*     */   }
/*     */ 
/*     */   Snapshot(int paramInt1, int paramInt2, String paramString1, String paramString2, String paramString3)
/*     */   {
/*  97 */     this(paramInt1, paramInt2, MedicalDevice.Util.makeIntArray(paramString1), MedicalDevice.Util.makeIntArray(paramString2), MedicalDevice.Util.makeIntArray(paramString3));
/*     */   }
/*     */ 
/*     */   Snapshot()
/*     */   {
/* 108 */     this(0, 0, new int[0], new int[0], new int[0]);
/*     */   }
/*     */ 
/*     */   int write()
/*     */     throws IOException
/*     */   {
/* 125 */     Contract.pre(this.m_snapshotHeader != null);
/* 126 */     Contract.pre(this.m_elementCollection != null);
/*     */ 
/* 128 */     MedicalDevice.logInfo(this, "write: writing snapshot to stream");
/*     */ 
/* 130 */     Header.access$102(this.m_snapshotHeader, 0);
/* 131 */     writeToStream(false);
/*     */ 
/* 134 */     int[] arrayOfInt = MedicalDevice.Util.makeIntArray(this.m_snapshotOutputStream.getOutputBuffer().toByteArray());
/* 135 */     Header.access$102(this.m_snapshotHeader, computeCRC(arrayOfInt));
/*     */ 
/* 138 */     writeToStream(true);
/* 139 */     MedicalDevice.logInfo(this, "write: wrote " + this.m_snapshotOutputStream.size() + " bytes.");
/* 140 */     return this.m_snapshotOutputStream.size();
/*     */   }
/*     */ 
/*     */   InputStream toInputStream()
/*     */   {
/* 152 */     return new ByteArrayInputStream(this.m_snapshotOutputStream.getOutputBuffer().toByteArray());
/*     */   }
/*     */ 
/*     */   void store(String paramString)
/*     */     throws FileNotFoundException, IOException
/*     */   {
/* 165 */     FileOutputStream localFileOutputStream = new FileOutputStream(paramString);
/* 166 */     localFileOutputStream.write(this.m_snapshotOutputStream.getOutputBuffer().toByteArray());
/* 167 */     localFileOutputStream.close();
/*     */ 
/* 169 */     writeText(paramString);
/*     */   }
/*     */ 
/*     */   int getSnapshotVersion()
/*     */   {
/* 178 */     return this.m_snapshotHeader.m_snapshotVersion;
/*     */   }
/*     */ 
/*     */   int[] getDeviceTime()
/*     */   {
/* 187 */     return this.m_snapshotHeader.m_deviceTime;
/*     */   }
/*     */ 
/*     */   void addElement(int paramInt1, int[] paramArrayOfInt, int paramInt2)
/*     */   {
/* 200 */     Contract.pre(paramArrayOfInt != null);
/* 201 */     Contract.pre(paramArrayOfInt.length >= paramInt2);
/*     */ 
/* 203 */     Element localElement = new Element(paramInt1, paramArrayOfInt, paramInt2, null);
/* 204 */     this.m_elementCollection.put(new Integer(paramInt1), localElement);
/*     */   }
/*     */ 
/*     */   void addElement(int paramInt, int[] paramArrayOfInt)
/*     */   {
/* 216 */     Contract.pre(paramArrayOfInt != null);
/*     */ 
/* 218 */     addElement(paramInt, paramArrayOfInt, paramArrayOfInt.length);
/*     */   }
/*     */ 
/*     */   void addElement(int paramInt, String paramString)
/*     */   {
/* 230 */     Contract.pre(paramString != null);
/*     */ 
/* 232 */     int[] arrayOfInt = MedicalDevice.Util.makeIntArray(paramString);
/* 233 */     addElement(paramInt, arrayOfInt, arrayOfInt.length);
/*     */   }
/*     */ 
/*     */   void addElement(int paramInt1, int paramInt2)
/*     */   {
/* 243 */     int[] arrayOfInt = new int[1];
/*     */ 
/* 245 */     arrayOfInt[0] = paramInt2;
/* 246 */     addElement(paramInt1, arrayOfInt);
/*     */   }
/*     */ 
/*     */   void addElement(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 257 */     int[] arrayOfInt = new int[2];
/*     */ 
/* 259 */     arrayOfInt[0] = paramInt2;
/* 260 */     arrayOfInt[1] = paramInt3;
/* 261 */     addElement(paramInt1, arrayOfInt);
/*     */   }
/*     */ 
/*     */   int getSnapshotCRC()
/*     */   {
/* 270 */     return this.m_snapshotHeader.m_snapshotCRC;
/*     */   }
/*     */ 
/*     */   private int computeCRC(int[] paramArrayOfInt)
/*     */   {
/* 281 */     paramArrayOfInt[4] = 0;
/* 282 */     paramArrayOfInt[5] = 0;
/* 283 */     int i = MedicalDevice.Util.computeCRC16CCITT(paramArrayOfInt, 0, paramArrayOfInt.length);
/*     */ 
/* 287 */     paramArrayOfInt[4] = MedicalDevice.Util.getHighByte(i);
/* 288 */     paramArrayOfInt[5] = MedicalDevice.Util.getLowByte(i);
/* 289 */     return i;
/*     */   }
/*     */ 
/*     */   private void writeToStream(boolean paramBoolean)
/*     */     throws IOException
/*     */   {
/* 299 */     this.m_snapshotOutputStream = new SnapshotOutputStream(new ByteArrayOutputStream(), null);
/*     */ 
/* 302 */     this.m_snapshotHeader.write(paramBoolean);
/*     */ 
/* 305 */     Iterator localIterator = this.m_elementCollection.keySet().iterator();
/* 306 */     while (localIterator.hasNext()) {
/* 307 */       Element localElement = (Element)this.m_elementCollection.get(localIterator.next());
/* 308 */       localElement.write(paramBoolean);
/*     */     }
/*     */ 
/* 311 */     this.m_snapshotOutputStream.close();
/*     */   }
/*     */ 
/*     */   private void writeText(String paramString)
/*     */     throws IOException
/*     */   {
/* 326 */     Contract.pre(this.m_snapshotHeader != null);
/* 327 */     Contract.pre(this.m_elementCollection != null);
/*     */ 
/* 329 */     MedicalDevice.logInfo(this, "writeText: writing snapshot " + paramString + ".txt");
/*     */ 
/* 332 */     PrintWriter localPrintWriter = new PrintWriter(new BufferedWriter(new FileWriter(paramString + ".txt")));
/*     */ 
/* 336 */     this.m_snapshotHeader.writeText(localPrintWriter);
/*     */ 
/* 339 */     Iterator localIterator = this.m_elementCollection.keySet().iterator();
/* 340 */     while (localIterator.hasNext()) {
/* 341 */       Element localElement = (Element)this.m_elementCollection.get(localIterator.next());
/* 342 */       localElement.writeText(localPrintWriter);
/*     */     }
/*     */ 
/* 345 */     localPrintWriter.close();
/*     */   }
/*     */ 
/*     */   private final class SnapshotOutputStream extends DataOutputStream
/*     */   {
/*     */     private ByteArrayOutputStream m_outputBuffer;
/*     */     private final Snapshot this$0;
/*     */ 
/*     */     private SnapshotOutputStream(ByteArrayOutputStream arg2)
/*     */     {
/* 712 */       super();
/*     */ 
/* 709 */       this.this$0 = this$1;
/*     */ 
/* 713 */       this.m_outputBuffer = localOutputStream;
/*     */     }
/*     */ 
/*     */     public void writeBytes(int[] paramArrayOfInt)
/*     */       throws IOException
/*     */     {
/* 727 */       if (paramArrayOfInt != null)
/* 728 */         for (int i = 0; i < paramArrayOfInt.length; i++)
/* 729 */           write(paramArrayOfInt[i]);
/*     */     }
/*     */ 
/*     */     void writeUnsignedInt(long paramLong)
/*     */       throws IOException
/*     */     {
/* 743 */       Contract.pre((paramLong >= 0L) && (paramLong <= 4294967295L));
/*     */ 
/* 746 */       int i = (int)(paramLong >>> 16 & 0xFFFF);
/*     */ 
/* 750 */       int j = (int)(paramLong & 0xFFFF);
/*     */ 
/* 753 */       writeShort(i);
/*     */ 
/* 756 */       writeShort(j);
/*     */     }
/*     */ 
/*     */     private ByteArrayOutputStream getOutputBuffer()
/*     */     {
/* 765 */       return this.m_outputBuffer;
/*     */     }
/*     */ 
/*     */     SnapshotOutputStream(ByteArrayOutputStream param1, Snapshot.1 arg3)
/*     */     {
/* 696 */       this(param1);
/*     */     }
/*     */   }
/*     */ 
/*     */   private final class Element
/*     */   {
/*     */     private static final int MAX_BYTE_VALUE = 255;
/*     */     private static final int MAX_STANDARD_ELEMENT_LENGTH = 65535;
/*     */     private static final int MIN_LARGE_ELEMENT_CODE = 10000;
/*     */     private int m_code;
/*     */     private int m_length;
/*     */     private int[] m_rawData;
/*     */     private final Snapshot this$0;
/*     */ 
/*     */     private Element(int paramArrayOfInt, int[] paramInt1, int arg4)
/*     */     {
/* 569 */       this.this$0 = this$1;
/* 570 */       Contract.pre(paramInt1 != null);
/*     */       int i;
/* 571 */       Contract.pre(paramInt1.length >= i);
/*     */ 
/* 573 */       this.m_code = paramArrayOfInt;
/* 574 */       this.m_length = i;
/* 575 */       this.m_rawData = paramInt1;
/*     */     }
/*     */ 
/*     */     private Element()
/*     */     {
/* 583 */       this(0, new int[0], 0);
/*     */     }
/*     */ 
/*     */     public String toString()
/*     */     {
/* 594 */       StringBuffer localStringBuffer = new StringBuffer();
/*     */ 
/* 596 */       localStringBuffer.append("code=" + this.m_code + ", dataLen=" + this.m_length + ", hex data bytes follow..." + Snapshot.TERM);
/*     */ 
/* 600 */       for (int i = 0; i < this.m_length; i++) {
/* 601 */         localStringBuffer.append(MedicalDevice.Util.getHexCompact(this.m_rawData[i]) + " ");
/*     */       }
/* 603 */       return localStringBuffer.toString();
/*     */     }
/*     */ 
/*     */     private int write(boolean paramBoolean)
/*     */       throws IOException
/*     */     {
/* 620 */       int i = 0;
/*     */ 
/* 622 */       Contract.pre(this.this$0.m_snapshotOutputStream != null);
/* 623 */       Contract.pre(this.m_rawData != null);
/* 624 */       Contract.pre(this.m_rawData.length >= this.m_length);
/*     */ 
/* 627 */       if (this.m_code < 10000) {
/* 628 */         Contract.pre(this.m_length <= 65535);
/*     */       }
/*     */ 
/* 632 */       for (int j = 0; j < this.m_length; j++) {
/* 633 */         Contract.pre(this.m_rawData[j] <= 255);
/*     */       }
/*     */ 
/* 637 */       this.this$0.m_snapshotOutputStream.writeShort(this.m_code);
/* 638 */       i += 2;
/*     */ 
/* 641 */       if (this.m_code < 10000)
/*     */       {
/* 643 */         this.this$0.m_snapshotOutputStream.writeShort(this.m_length);
/* 644 */         i += 2;
/*     */       }
/*     */       else {
/* 647 */         this.this$0.m_snapshotOutputStream.writeUnsignedInt(this.m_length);
/* 648 */         i += 4;
/*     */       }
/*     */ 
/* 652 */       for (j = 0; j < this.m_length; j++) {
/* 653 */         this.this$0.m_snapshotOutputStream.writeByte(this.m_rawData[j]);
/*     */       }
/*     */ 
/* 656 */       i += this.m_length;
/* 657 */       if (paramBoolean) {
/* 658 */         MedicalDevice.logInfo(this, "write: number of data bytes written for element " + this.m_code + "=" + this.m_length);
/*     */       }
/*     */ 
/* 662 */       return i;
/*     */     }
/*     */ 
/*     */     private void writeText(PrintWriter paramPrintWriter)
/*     */     {
/* 674 */       Contract.pre(paramPrintWriter != null);
/* 675 */       Contract.pre(this.m_rawData != null);
/* 676 */       Contract.pre(this.m_rawData.length >= this.m_length);
/*     */ 
/* 678 */       for (int i = 0; i < this.m_length; i++) {
/* 679 */         Contract.pre(this.m_rawData[i] <= 255);
/*     */       }
/*     */ 
/* 683 */       paramPrintWriter.println(toString());
/* 684 */       paramPrintWriter.println();
/*     */     }
/*     */ 
/*     */     Element(int paramArrayOfInt, int[] paramInt1, int param1, Snapshot.1 arg5)
/*     */     {
/* 538 */       this(paramArrayOfInt, paramInt1, param1);
/*     */     }
/*     */   }
/*     */ 
/*     */   private final class Header
/*     */   {
/*     */     private int m_snapshotFormatID;
/*     */     private int m_snapshotVersion;
/*     */     private int m_snapshotCRC;
/*     */     private int m_twoBytesUnused;
/*     */     private int[] m_deviceFirmwareVersion;
/*     */     private int[] m_deviceSerialNumber;
/*     */     private int[] m_deviceTime;
/*     */     private final Snapshot this$0;
/*     */ 
/*     */     private Header(int paramInt1, int paramArrayOfInt1, int[] paramArrayOfInt2, int[] paramArrayOfInt3, int[] arg6)
/*     */     {
/* 404 */       this.this$0 = this$1;
/* 405 */       this.m_snapshotFormatID = paramInt1;
/* 406 */       this.m_snapshotVersion = paramArrayOfInt1;
/* 407 */       this.m_snapshotCRC = 0;
/* 408 */       this.m_twoBytesUnused = 0;
/* 409 */       this.m_deviceFirmwareVersion = paramArrayOfInt2;
/* 410 */       this.m_deviceSerialNumber = paramArrayOfInt3;
/*     */       Object localObject;
/* 411 */       this.m_deviceTime = localObject;
/*     */     }
/*     */ 
/*     */     private Header()
/*     */     {
/* 419 */       this(0, 0, new int[0], new int[0], new int[0]);
/*     */     }
/*     */ 
/*     */     public String toString()
/*     */     {
/* 430 */       StringBuffer localStringBuffer = new StringBuffer();
/*     */ 
/* 432 */       localStringBuffer.append("snapshotFormatID=" + this.m_snapshotFormatID + Snapshot.TERM);
/*     */ 
/* 435 */       localStringBuffer.append("snapshotVersion=" + this.m_snapshotVersion + Snapshot.TERM);
/*     */ 
/* 438 */       localStringBuffer.append("snapshotCRC=" + MedicalDevice.Util.getHex(this.m_snapshotCRC) + "; ");
/*     */ 
/* 441 */       localStringBuffer.append("unused=" + MedicalDevice.Util.getHex(this.m_twoBytesUnused) + Snapshot.TERM);
/*     */ 
/* 444 */       String str = new String(MedicalDevice.Util.makeByteArray(this.m_deviceFirmwareVersion));
/* 445 */       str = MedicalDevice.Util.convertControlChars(str);
/* 446 */       localStringBuffer.append("firmware=" + str + Snapshot.TERM);
/*     */ 
/* 449 */       str = new String(MedicalDevice.Util.makeByteArray(this.m_deviceSerialNumber));
/* 450 */       str = MedicalDevice.Util.convertControlChars(str);
/* 451 */       localStringBuffer.append("serialNum=" + str + Snapshot.TERM);
/*     */ 
/* 454 */       localStringBuffer.append("timeStamp=" + MedicalDevice.Util.getHexCompact(this.m_deviceTime));
/* 455 */       return localStringBuffer.toString();
/*     */     }
/*     */ 
/*     */     private int write(boolean paramBoolean)
/*     */       throws IOException
/*     */     {
/* 472 */       int i = 0;
/*     */ 
/* 474 */       Contract.pre(this.this$0.m_snapshotOutputStream != null);
/* 475 */       Contract.pre(this.m_deviceFirmwareVersion != null);
/* 476 */       Contract.pre(this.m_deviceSerialNumber != null);
/* 477 */       Contract.pre(this.m_deviceTime != null);
/*     */ 
/* 480 */       this.this$0.m_snapshotOutputStream.writeShort(this.m_snapshotFormatID);
/* 481 */       i += 2;
/*     */ 
/* 484 */       this.this$0.m_snapshotOutputStream.writeShort(this.m_snapshotVersion);
/* 485 */       i += 2;
/*     */ 
/* 488 */       this.this$0.m_snapshotOutputStream.writeShort(this.m_snapshotCRC);
/* 489 */       i += 2;
/*     */ 
/* 492 */       this.this$0.m_snapshotOutputStream.writeShort(this.m_twoBytesUnused);
/* 493 */       i += 2;
/*     */ 
/* 496 */       this.this$0.m_snapshotOutputStream.writeBytes(this.m_deviceFirmwareVersion);
/* 497 */       i += this.m_deviceFirmwareVersion.length;
/*     */ 
/* 500 */       this.this$0.m_snapshotOutputStream.writeBytes(this.m_deviceSerialNumber);
/* 501 */       i += this.m_deviceSerialNumber.length;
/*     */ 
/* 504 */       this.this$0.m_snapshotOutputStream.writeBytes(this.m_deviceTime);
/* 505 */       i += this.m_deviceTime.length;
/*     */ 
/* 507 */       if (paramBoolean) {
/* 508 */         MedicalDevice.logInfo(this, "write: number of bytes written for header = " + i);
/*     */       }
/*     */ 
/* 511 */       return i;
/*     */     }
/*     */ 
/*     */     private void writeText(PrintWriter paramPrintWriter)
/*     */     {
/* 524 */       Contract.pre(paramPrintWriter != null);
/* 525 */       Contract.pre(this.m_deviceFirmwareVersion != null);
/* 526 */       Contract.pre(this.m_deviceSerialNumber != null);
/* 527 */       Contract.pre(this.m_deviceTime != null);
/*     */ 
/* 529 */       paramPrintWriter.println(toString());
/*     */     }
/*     */ 
/*     */     Header(int paramInt1, int paramArrayOfInt1, int[] paramArrayOfInt2, int[] paramArrayOfInt3, int[] param1, Snapshot.1 arg7)
/*     */     {
/* 353 */       this(paramInt1, paramArrayOfInt1, paramArrayOfInt2, paramArrayOfInt3, param1);
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.deviceportreader.Snapshot
 * JD-Core Version:    0.6.0
 */