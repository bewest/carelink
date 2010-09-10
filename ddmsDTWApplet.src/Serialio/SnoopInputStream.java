/*     */ package Serialio;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
/*     */ 
/*     */ public class SnoopInputStream extends InputStream
/*     */   implements SnoopListener
/*     */ {
/*  42 */   protected InputStream in = null;
/*  43 */   private Vector snoopList = new Vector();
/*  44 */   private boolean snooping = false;
/*     */ 
/*  46 */   private Vector snoopTokenList = new Vector();
/*     */   private int subTokenCount;
/*     */   protected byte lastByte;
/*     */ 
/*     */   public SnoopInputStream()
/*     */   {
/*     */   }
/*     */ 
/*     */   public SnoopInputStream(InputStream paramInputStream)
/*     */   {
/*  60 */     this.in = paramInputStream;
/*     */   }
/*     */ 
/*     */   public void snoop(byte paramByte)
/*     */     throws IOException, ExTokenOverflow
/*     */   {
/*  72 */     checkDataForMatch(paramByte);
/*  73 */     checkForTokenMatch(paramByte);
/*     */   }
/*     */ 
/*     */   public int read()
/*     */     throws IOException, ExTokenOverflow
/*     */   {
/*  84 */     int i = this.in.read();
/*  85 */     if (this.snooping) {
/*  86 */       checkDataForMatch((byte)i);
/*  87 */       checkForTokenMatch((byte)i);
/*     */     }
/*  89 */     return i;
/*     */   }
/*     */ 
/*     */   public int read(byte[] paramArrayOfByte)
/*     */     throws IOException, ExTokenOverflow
/*     */   {
/* 102 */     int i = this.in.read(paramArrayOfByte);
/* 103 */     for (int j = 0; j < i; j++)
/*     */     {
/* 105 */       if (this.snooping) {
/* 106 */         checkDataForMatch(paramArrayOfByte[j]);
/* 107 */         checkForTokenMatch(paramArrayOfByte[j]);
/*     */       }
/*     */     }
/*     */ 
/* 111 */     return i;
/*     */   }
/*     */ 
/*     */   public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/*     */     throws IOException, ExTokenOverflow
/*     */   {
/* 126 */     int i = this.in.read(paramArrayOfByte, paramInt1, paramInt2);
/*     */ 
/* 128 */     int j = 0; for (int k = paramInt1; j < i; k++)
/*     */     {
/* 131 */       if (this.snooping) {
/* 132 */         checkDataForMatch(paramArrayOfByte[k]);
/* 133 */         checkForTokenMatch(paramArrayOfByte[k]);
/*     */       }
/* 128 */       j++;
/*     */     }
/*     */ 
/* 137 */     return i;
/*     */   }
/*     */ 
/*     */   public void checkForTokenMatch(int paramInt)
/*     */     throws ExTokenOverflow
/*     */   {
/* 143 */     int i = paramInt;
/* 144 */     for (int j = 0; j < this.snoopTokenList.size(); j++)
/*     */     {
/* 146 */       Token localToken = (Token)this.snoopTokenList.elementAt(j);
/*     */ 
/* 150 */       if (localToken.gotTokenBeg)
/*     */       {
/* 152 */         localToken.tokenBuffer[localToken.count] = (byte)i;
/* 153 */         localToken.incrementCount();
/*     */       }
/* 155 */       if (!localToken.gotTokenEnd) {
/*     */         continue;
/*     */       }
/* 158 */       for (int k = 0; k < localToken.count; k++)
/*     */       {
/* 160 */         localToken.parseToken[k] = localToken.tokenBuffer[k];
/*     */       }
/*     */ 
/* 164 */       parseToken(localToken.tokenBuffer, localToken.count - localToken.tokEndLen, localToken);
/* 165 */       localToken.count = 0;
/* 166 */       localToken.gotTokenEnd = false;
/*     */     }
/*     */   }
/*     */ 
/*     */   private void parseToken(byte[] paramArrayOfByte, int paramInt, Token paramToken)
/*     */   {
/* 174 */     byte[] arrayOfByte = null;
/* 175 */     String str = new String(paramArrayOfByte, 1, paramInt);
/*     */ 
/* 177 */     ByteTokenizer localByteTokenizer = new ByteTokenizer(str.getBytes(), paramToken.separator);
/* 178 */     this.subTokenCount = localByteTokenizer.numberOfTokens();
/* 179 */     if (paramToken.tknNbr == 0)
/*     */     {
/* 181 */       paramToken.snp.snoopTokenEvent(str.getBytes(), paramToken.tokenId);
/*     */     }
/*     */     else
/*     */     {
/* 187 */       for (int i = 1; i <= paramToken.tknNbr; i++) {
/* 188 */         arrayOfByte = localByteTokenizer.nextToken();
/*     */       }
/*     */ 
/* 191 */       paramToken.snp.snoopTokenEvent(arrayOfByte, paramToken.tokenId);
/*     */     }
/*     */   }
/*     */ 
/*     */   public int available()
/*     */     throws IOException
/*     */   {
/* 202 */     return this.in.available();
/*     */   }
/*     */ 
/*     */   public void close()
/*     */     throws IOException
/*     */   {
/* 211 */     this.in.close();
/*     */   }
/*     */ 
/*     */   public void setSnoopEnable(boolean paramBoolean)
/*     */   {
/* 220 */     this.snooping = paramBoolean;
/*     */   }
/*     */ 
/*     */   public boolean getSnoopEnable()
/*     */   {
/* 228 */     return this.snooping;
/*     */   }
/*     */ 
/*     */   public InputStream getInputStream()
/*     */   {
/* 248 */     return this.in;
/*     */   }
/*     */ 
/*     */   public int getSubTokenCount()
/*     */   {
/* 257 */     return this.subTokenCount;
/*     */   }
/*     */ 
/*     */   public void setInputStream(InputStream paramInputStream)
/*     */   {
/* 266 */     this.in = paramInputStream;
/*     */   }
/*     */ 
/*     */   public boolean markSupported() {
/* 270 */     return this.in.markSupported();
/*     */   }
/*     */ 
/*     */   public synchronized void mark(int paramInt) {
/* 274 */     this.in.mark(paramInt);
/*     */   }
/*     */ 
/*     */   public synchronized void reset() throws IOException {
/* 278 */     this.in.reset();
/*     */   }
/*     */ 
/*     */   public void addTokenSnoop(SnoopTokenListener paramSnoopTokenListener, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte paramByte, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 317 */     addSnoop(this, paramArrayOfByte1, false);
/* 318 */     addSnoop(this, paramArrayOfByte2, false);
/* 319 */     Token localToken = new Token(paramSnoopTokenListener, paramArrayOfByte1, paramArrayOfByte2, paramByte, paramInt1, paramInt2, paramInt3);
/*     */ 
/* 321 */     this.snoopTokenList.addElement(localToken);
/*     */   }
/*     */ 
/*     */   public void addTokenSnoop(SnoopTokenListener paramSnoopTokenListener, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte paramByte, int paramInt1, int paramInt2)
/*     */   {
/* 341 */     int i = 1024;
/* 342 */     addTokenSnoop(paramSnoopTokenListener, paramArrayOfByte1, paramArrayOfByte2, paramByte, paramInt1, paramInt2, i);
/*     */   }
/*     */ 
/*     */   public void addTokenSnoop(SnoopTokenListener paramSnoopTokenListener, String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2)
/*     */   {
/* 360 */     int i = 1024;
/* 361 */     addTokenSnoop(paramSnoopTokenListener, paramString1.getBytes(), paramString2.getBytes(), paramString3.getBytes()[0], paramInt1, paramInt2, i);
/*     */   }
/*     */ 
/*     */   public void addTokenSnoop(SnoopTokenListener paramSnoopTokenListener, String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 381 */     addTokenSnoop(paramSnoopTokenListener, paramString1.getBytes(), paramString2.getBytes(), paramString3.getBytes()[0], paramInt1, paramInt2, paramInt3);
/*     */   }
/*     */ 
/*     */   public void removeTokenSnoop(int paramInt)
/*     */   {
/* 393 */     for (int i = 0; i < this.snoopTokenList.size(); i++)
/*     */     {
/* 399 */       if (((Token)this.snoopTokenList.elementAt(i)).tokenId != paramInt)
/*     */         continue;
/* 401 */       this.snoopTokenList.removeElementAt(i);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void addSnoop(SnoopListener paramSnoopListener, String paramString, boolean paramBoolean)
/*     */   {
/* 425 */     addSnoop(paramSnoopListener, paramString.getBytes(), paramBoolean);
/*     */   }
/*     */ 
/*     */   public synchronized void addSnoop(SnoopListener paramSnoopListener, byte[] paramArrayOfByte, boolean paramBoolean)
/*     */   {
/* 442 */     Snoop localSnoop = new Snoop(paramSnoopListener);
/* 443 */     if (paramBoolean) {
/* 444 */       localSnoop.dataOriginal = new byte[paramArrayOfByte.length];
/* 445 */       System.arraycopy(paramArrayOfByte, 0, localSnoop.dataOriginal, 0, paramArrayOfByte.length);
/* 446 */       for (int i = 0; i < paramArrayOfByte.length; i++)
/* 447 */         if ((paramArrayOfByte[i] >= 97) && (paramArrayOfByte[i] <= 122))
/*     */         {
/*     */           int tmp68_66 = i;
/*     */           byte[] tmp68_65 = paramArrayOfByte; tmp68_65[tmp68_66] = (byte)(tmp68_65[tmp68_66] - 32);
/*     */         }
/*     */     }
/* 451 */     localSnoop.dataOriginal = paramArrayOfByte;
/*     */ 
/* 453 */     localSnoop.ignoreCase = paramBoolean;
/* 454 */     localSnoop.data = paramArrayOfByte;
/* 455 */     localSnoop.compareNdx = 0;
/*     */ 
/* 457 */     this.snoopList.addElement(localSnoop);
/*     */ 
/* 460 */     this.snooping = true;
/*     */   }
/*     */ 
/*     */   public synchronized void removeSnoop(SnoopListener paramSnoopListener, String paramString)
/*     */   {
/* 471 */     removeSnoop(paramSnoopListener, paramString.getBytes());
/*     */   }
/*     */ 
/*     */   public synchronized void removeSnoop(SnoopListener paramSnoopListener, byte[] paramArrayOfByte)
/*     */   {
/* 481 */     for (int i = 0; i < this.snoopList.size(); i++) {
/* 482 */       Snoop localSnoop = (Snoop)this.snoopList.elementAt(i);
/* 483 */       if (paramSnoopListener == localSnoop.lsnr) {
/* 484 */         dataMatch(paramArrayOfByte, localSnoop.dataOriginal);
/* 485 */         this.snoopList.removeElementAt(i);
/* 486 */         if (this.snoopList.size() == 0) this.snooping = false;
/* 487 */         return;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 492 */     System.out.println("removeSnoop: Snoop not found");
/*     */   }
/*     */ 
/*     */   public synchronized void removeSnoop(SnoopListener paramSnoopListener)
/*     */   {
/* 502 */     Enumeration localEnumeration = this.snoopList.elements();
/* 503 */     while (localEnumeration.hasMoreElements()) {
/* 504 */       Snoop localSnoop = (Snoop)localEnumeration.nextElement();
/* 505 */       if (localSnoop.lsnr == paramSnoopListener) {
/* 506 */         this.snoopList.removeElement(localSnoop);
/*     */       }
/*     */     }
/*     */ 
/* 510 */     if (this.snoopList.size() == 0) this.snooping = false;
/*     */   }
/*     */ 
/*     */   public boolean dataMatch(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
/*     */   {
/* 520 */     if (paramArrayOfByte1.length != paramArrayOfByte2.length) {
/* 521 */       return false;
/*     */     }
/* 523 */     for (int i = 0; i < paramArrayOfByte1.length; i++) {
/* 524 */       if (paramArrayOfByte1[i] != paramArrayOfByte2[i])
/* 525 */         return false;
/*     */     }
/* 527 */     return true;
/*     */   }
/*     */ 
/*     */   public void snoopEvent(byte[] paramArrayOfByte)
/*     */   {
/* 533 */     byte[] arrayOfByte = paramArrayOfByte;
/* 534 */     for (int i = 0; i < this.snoopTokenList.size(); i++)
/*     */     {
/* 536 */       Token localToken = (Token)this.snoopTokenList.elementAt(i);
/* 537 */       if (dataMatch(arrayOfByte, localToken.tokBeg))
/*     */       {
/* 540 */         localToken.gotTokenBeg = true;
/*     */       }
/*     */ 
/* 544 */       if (!dataMatch(arrayOfByte, localToken.tokEnd)) {
/*     */         continue;
/*     */       }
/* 547 */       if ((localToken.gotTokenBeg == true) && (!localToken.gotTokenEnd))
/* 548 */         localToken.gotTokenEnd = true;
/* 549 */       localToken.gotTokenBeg = false;
/*     */     }
/*     */   }
/*     */ 
/*     */   protected void notifySighting(Snoop paramSnoop)
/*     */   {
/* 560 */     paramSnoop.lsnr.snoopEvent(paramSnoop.dataOriginal);
/*     */   }
/*     */ 
/*     */   public void checkDataForMatch(byte paramByte)
/*     */   {
/* 577 */     for (int i = 0; i < this.snoopList.size(); i++) {
/* 578 */       int j = 0;
/* 579 */       Snoop localSnoop = (Snoop)this.snoopList.elementAt(i);
/* 580 */       if ((localSnoop.ignoreCase) && 
/* 581 */         (paramByte >= 97) && (paramByte <= 122)) {
/* 582 */         paramByte = (byte)(paramByte - 32);
/*     */       }
/* 584 */       if (this.lastByte != paramByte) localSnoop.duplicateData = false;
/* 585 */       if (localSnoop.data[localSnoop.compareNdx] == paramByte)
/*     */       {
/* 588 */         if ((localSnoop.compareNdx == 1) && (this.lastByte == paramByte)) localSnoop.duplicateData = true;
/* 589 */         localSnoop.compareNdx += 1;
/* 590 */         if (localSnoop.compareNdx == localSnoop.data.length) {
/* 591 */           localSnoop.compareNdx = 0;
/* 592 */           notifySighting(localSnoop);
/*     */         }
/*     */ 
/*     */       }
/* 597 */       else if (!localSnoop.duplicateData) {
/* 598 */         localSnoop.compareNdx = 0;
/* 599 */         if (localSnoop.data[0] == paramByte) {
/* 600 */           localSnoop.compareNdx += 1;
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 605 */     this.lastByte = paramByte;
/*     */   }
/*     */ 
/*     */   class Token
/*     */   {
/*     */     SnoopTokenListener snp;
/*     */     int tknNbr;
/*     */     byte[] tokBeg;
/*     */     byte[] tokEnd;
/*     */     byte separator;
/* 636 */     boolean gotTokenBeg = false; boolean gotTokenEnd = false;
/*     */     byte[] tokenBuffer;
/*     */     byte[] parseToken;
/* 638 */     int count = 0;
/*     */     int tokBegLen;
/*     */     int tokEndLen;
/*     */     int tokenId;
/*     */     int buffer;
/*     */ 
/*     */     public Token(SnoopTokenListener paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramByte, byte paramInt1, int paramInt2, int paramInt3, int arg8)
/*     */     {
/* 646 */       this.snp = paramArrayOfByte1;
/* 647 */       this.tknNbr = paramInt2;
/* 648 */       this.separator = paramInt1;
/* 649 */       this.tokEnd = paramByte;
/* 650 */       this.tokBeg = paramArrayOfByte2;
/* 651 */       this.tokenId = paramInt3;
/* 652 */       this.tokBegLen = paramArrayOfByte2.length;
/* 653 */       this.tokEndLen = paramByte.length;
/*     */       int i;
/* 654 */       this.tokenBuffer = new byte[i + 1];
/* 655 */       this.parseToken = new byte[i + 1];
/* 656 */       this.buffer = i;
/*     */     }
/*     */ 
/*     */     private void incrementCount() throws ExTokenOverflow
/*     */     {
/* 661 */       this.count += 1;
/* 662 */       if (this.count > this.buffer)
/*     */       {
/* 664 */         throw new ExTokenOverflow("Buffer exceeded for token with TokenId = " + this.tokenId);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   class Snoop
/*     */   {
/* 613 */     protected boolean ignoreCase = false;
/* 614 */     protected boolean duplicateData = false;
/* 615 */     protected byte[] data = null;
/* 616 */     protected byte[] dataOriginal = null;
/* 617 */     protected int compareNdx = 0;
/*     */     protected SnoopListener lsnr;
/*     */ 
/*     */     public Snoop(SnoopListener arg2)
/*     */     {
/*     */       Object localObject;
/* 622 */       this.lsnr = localObject;
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     Serialio.SnoopInputStream
 * JD-Core Version:    0.6.0
 */