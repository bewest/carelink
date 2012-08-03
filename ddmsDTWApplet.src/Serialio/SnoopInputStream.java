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
/*  50 */   protected InputStream in = null;
/*  51 */   private Vector snoopList = new Vector();
/*  52 */   private boolean snooping = false;
/*  53 */   private boolean singleTokenSnoop = false;
/*     */ 
/*  55 */   private Vector snoopTokenList = new Vector();
/*     */   private int subTokenCount;
/*     */   protected byte lastByte;
/*     */   private int sisID;
/*     */ 
/*     */   public SnoopInputStream()
/*     */   {
/*     */   }
/*     */ 
/*     */   public SnoopInputStream(InputStream paramInputStream)
/*     */   {
/*  70 */     this.in = paramInputStream;
/*     */   }
/*     */ 
/*     */   public void setStreamID(int paramInt)
/*     */   {
/*  76 */     this.sisID = paramInt;
/*     */   }
/*     */ 
/*     */   public void snoop(byte paramByte)
/*     */     throws IOException, ExTokenOverflow
/*     */   {
/*  87 */     checkDataForMatch(paramByte);
/*  88 */     checkForTokenMatch(paramByte);
/*     */   }
/*     */ 
/*     */   public int read()
/*     */     throws IOException, ExTokenOverflow
/*     */   {
/*  99 */     int i = this.in.read();
/* 100 */     if (this.snooping) {
/* 101 */       checkDataForMatch((byte)i);
/* 102 */       checkForTokenMatch((byte)i);
/*     */     }
/* 104 */     return i;
/*     */   }
/*     */ 
/*     */   public int read(byte[] paramArrayOfByte)
/*     */     throws IOException, ExTokenOverflow
/*     */   {
/* 117 */     int i = this.in.read(paramArrayOfByte);
/* 118 */     for (int j = 0; j < i; j++)
/*     */     {
/* 120 */       if (this.snooping) {
/* 121 */         checkDataForMatch(paramArrayOfByte[j]);
/* 122 */         checkForTokenMatch(paramArrayOfByte[j]);
/*     */       }
/*     */     }
/*     */ 
/* 126 */     return i;
/*     */   }
/*     */ 
/*     */   public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/*     */     throws IOException, ExTokenOverflow
/*     */   {
/* 141 */     int i = this.in.read(paramArrayOfByte, paramInt1, paramInt2);
/*     */ 
/* 143 */     int j = 0; for (int k = paramInt1; j < i; k++)
/*     */     {
/* 146 */       if (this.snooping) {
/* 147 */         checkDataForMatch(paramArrayOfByte[k]);
/* 148 */         checkForTokenMatch(paramArrayOfByte[k]);
/*     */       }
/* 143 */       j++;
/*     */     }
/*     */ 
/* 152 */     return i;
/*     */   }
/*     */ 
/*     */   public void checkForTokenMatch(int paramInt)
/*     */     throws ExTokenOverflow
/*     */   {
/* 158 */     int i = paramInt;
/* 159 */     for (int j = 0; j < this.snoopTokenList.size(); j++)
/*     */     {
/* 161 */       Token localToken = (Token)this.snoopTokenList.elementAt(j);
/*     */ 
/* 165 */       if (localToken.gotTokenBeg)
/*     */       {
/* 168 */         localToken.tokenBuffer[localToken.count] = (byte)i;
/* 169 */         localToken.incrementCount();
/*     */       }
/* 171 */       if (!localToken.gotTokenEnd)
/*     */         continue;
/* 173 */       for (int k = 0; k < localToken.count; k++)
/*     */       {
/* 175 */         localToken.parseToken[k] = localToken.tokenBuffer[k];
/*     */       }
/*     */ 
/* 179 */       parseToken(localToken.tokenBuffer, localToken.count - localToken.tokEndLen, localToken);
/* 180 */       localToken.count = 0;
/* 181 */       localToken.gotTokenEnd = false;
/*     */     }
/*     */   }
/*     */ 
/*     */   private void parseToken(byte[] paramArrayOfByte, int paramInt, Token paramToken)
/*     */   {
/* 189 */     byte[] arrayOfByte = null;
/* 190 */     int i = 1;
/* 191 */     if (this.singleTokenSnoop) { i--; paramInt++; }
/* 192 */     String str = new String(paramArrayOfByte, i, paramInt);
/* 193 */     if ((paramToken.tknNbr == 0) || (paramToken.separator == 0))
/*     */     {
/* 195 */       paramToken.snp.snoopTokenEvent(str.getBytes(), paramToken.tokenId);
/*     */     }
/*     */     else {
/* 198 */       ByteTokenizer localByteTokenizer = new ByteTokenizer(str.getBytes(), paramToken.separator);
/* 199 */       this.subTokenCount = localByteTokenizer.numberOfTokens();
/*     */ 
/* 203 */       for (int j = 1; j <= paramToken.tknNbr; j++) {
/* 204 */         arrayOfByte = localByteTokenizer.nextToken();
/*     */       }
/*     */ 
/* 207 */       paramToken.snp.snoopTokenEvent(arrayOfByte, paramToken.tokenId);
/*     */     }
/*     */   }
/*     */ 
/*     */   public int available()
/*     */     throws IOException
/*     */   {
/* 218 */     return this.in.available();
/*     */   }
/*     */ 
/*     */   public void close()
/*     */     throws IOException
/*     */   {
/* 227 */     this.in.close();
/*     */   }
/*     */ 
/*     */   public void setSnoopEnable(boolean paramBoolean)
/*     */   {
/* 236 */     this.snooping = paramBoolean;
/*     */   }
/*     */ 
/*     */   public boolean getSnoopEnable()
/*     */   {
/* 244 */     return this.snooping;
/*     */   }
/*     */ 
/*     */   public InputStream getInputStream()
/*     */   {
/* 264 */     return this.in;
/*     */   }
/*     */ 
/*     */   public int getSubTokenCount()
/*     */   {
/* 273 */     return this.subTokenCount;
/*     */   }
/*     */ 
/*     */   public void setInputStream(InputStream paramInputStream)
/*     */   {
/* 282 */     this.in = paramInputStream;
/*     */   }
/*     */ 
/*     */   public boolean markSupported() {
/* 286 */     return this.in.markSupported();
/*     */   }
/*     */ 
/*     */   public synchronized void mark(int paramInt) {
/* 290 */     this.in.mark(paramInt);
/*     */   }
/*     */ 
/*     */   public synchronized void reset() throws IOException {
/* 294 */     this.in.reset();
/*     */   }
/*     */ 
/*     */   public void addTokenSnoop(SnoopTokenListener paramSnoopTokenListener, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte paramByte, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 333 */     if (this.singleTokenSnoop)
/* 334 */       throw new RuntimeException("In single token snoop mode, can't add another snoop");
/* 335 */     if (paramArrayOfByte1 != null)
/* 336 */       addSnoop(this, paramArrayOfByte1, false);
/*     */     else {
/* 338 */       this.singleTokenSnoop = true;
/*     */     }
/* 340 */     addSnoop(this, paramArrayOfByte2, false);
/* 341 */     Token localToken = new Token(paramSnoopTokenListener, paramArrayOfByte1, paramArrayOfByte2, paramByte, paramInt1, paramInt2, paramInt3);
/*     */ 
/* 343 */     this.snoopTokenList.addElement(localToken);
/*     */   }
/*     */ 
/*     */   public void setEndTokenSnoop(SnoopTokenListener paramSnoopTokenListener, byte[] paramArrayOfByte, int paramInt)
/*     */   {
/* 361 */     int i = 1024;
/* 362 */     byte[] arrayOfByte = null;
/* 363 */     byte b = 0;
/* 364 */     addTokenSnoop(paramSnoopTokenListener, arrayOfByte, paramArrayOfByte, b, 0, paramInt, i);
/*     */   }
/*     */ 
/*     */   public void addTokenSnoop(SnoopTokenListener paramSnoopTokenListener, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte paramByte, int paramInt1, int paramInt2)
/*     */   {
/* 382 */     int i = 1024;
/* 383 */     addTokenSnoop(paramSnoopTokenListener, paramArrayOfByte1, paramArrayOfByte2, paramByte, paramInt1, paramInt2, i);
/*     */   }
/*     */ 
/*     */   public void addTokenSnoop(SnoopTokenListener paramSnoopTokenListener, String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2)
/*     */   {
/* 401 */     int i = 1024;
/* 402 */     addTokenSnoop(paramSnoopTokenListener, paramString1.getBytes(), paramString2.getBytes(), paramString3.getBytes()[0], paramInt1, paramInt2, i);
/*     */   }
/*     */ 
/*     */   public void addTokenSnoop(SnoopTokenListener paramSnoopTokenListener, String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 423 */     addTokenSnoop(paramSnoopTokenListener, paramString1.getBytes(), paramString2.getBytes(), paramString3.getBytes()[0], paramInt1, paramInt2, paramInt3);
/*     */   }
/*     */ 
/*     */   public void removeTokenSnoop(int paramInt)
/*     */   {
/* 434 */     for (int i = 0; i < this.snoopTokenList.size(); i++)
/*     */     {
/* 440 */       Token localToken = (Token)this.snoopTokenList.elementAt(i);
/* 441 */       if (localToken.tokenId != paramInt)
/*     */         continue;
/* 443 */       if (localToken.tokBeg != null) removeSnoop(this, localToken.tokBeg);
/* 444 */       removeSnoop(this, localToken.tokEnd);
/* 445 */       this.snoopTokenList.removeElementAt(i);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void addSnoop(SnoopListener paramSnoopListener, String paramString, boolean paramBoolean)
/*     */   {
/* 469 */     addSnoop(paramSnoopListener, paramString.getBytes(), paramBoolean);
/*     */   }
/*     */ 
/*     */   public synchronized void addSnoop(SnoopListener paramSnoopListener, byte[] paramArrayOfByte, boolean paramBoolean)
/*     */   {
/* 486 */     Snoop localSnoop = new Snoop(paramSnoopListener);
/* 487 */     if (paramBoolean) {
/* 488 */       localSnoop.dataOriginal = new byte[paramArrayOfByte.length];
/* 489 */       System.arraycopy(paramArrayOfByte, 0, localSnoop.dataOriginal, 0, paramArrayOfByte.length);
/* 490 */       for (int i = 0; i < paramArrayOfByte.length; i++)
/* 491 */         if ((paramArrayOfByte[i] >= 97) && (paramArrayOfByte[i] <= 122))
/*     */         {
/*     */           int tmp68_66 = i;
/*     */           byte[] tmp68_65 = paramArrayOfByte; tmp68_65[tmp68_66] = (byte)(tmp68_65[tmp68_66] - 32);
/*     */         }
/*     */     } else {
/* 495 */       localSnoop.dataOriginal = paramArrayOfByte;
/*     */     }
/* 497 */     localSnoop.ignoreCase = paramBoolean;
/* 498 */     localSnoop.data = paramArrayOfByte;
/* 499 */     localSnoop.compareNdx = 0;
/*     */ 
/* 501 */     this.snoopList.addElement(localSnoop);
/*     */ 
/* 504 */     this.snooping = true;
/*     */   }
/*     */ 
/*     */   public synchronized void removeSnoop(SnoopListener paramSnoopListener, String paramString)
/*     */   {
/* 515 */     removeSnoop(paramSnoopListener, paramString.getBytes());
/*     */   }
/*     */ 
/*     */   public synchronized void removeSnoop(SnoopListener paramSnoopListener, byte[] paramArrayOfByte)
/*     */   {
/* 526 */     for (int i = 0; i < this.snoopList.size(); i++) {
/* 527 */       Snoop localSnoop = (Snoop)this.snoopList.elementAt(i);
/* 528 */       if ((paramSnoopListener != localSnoop.lsnr) || 
/* 529 */         (!dataMatch(paramArrayOfByte, localSnoop.dataOriginal))) continue;
/* 530 */       this.snoopList.removeElementAt(i);
/* 531 */       if (this.snoopList.size() == 0) {
/* 532 */         this.snooping = false;
/* 533 */         this.singleTokenSnoop = false;
/*     */       }
/* 535 */       return;
/*     */     }
/*     */ 
/* 540 */     System.out.println("removeSnoop: Snoop not found");
/*     */   }
/*     */ 
/*     */   public synchronized void removeSnoop(SnoopListener paramSnoopListener)
/*     */   {
/* 550 */     Enumeration localEnumeration = this.snoopList.elements();
/* 551 */     while (localEnumeration.hasMoreElements()) {
/* 552 */       Snoop localSnoop = (Snoop)localEnumeration.nextElement();
/* 553 */       if (localSnoop.lsnr == paramSnoopListener) {
/* 554 */         this.snoopList.removeElement(localSnoop);
/*     */       }
/*     */     }
/*     */ 
/* 558 */     if (this.snoopList.size() == 0) {
/* 559 */       this.snooping = false;
/* 560 */       this.singleTokenSnoop = false;
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean dataMatch(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
/*     */   {
/* 571 */     if (paramArrayOfByte1.length != paramArrayOfByte2.length) {
/* 572 */       return false;
/*     */     }
/* 574 */     for (int i = 0; i < paramArrayOfByte1.length; i++) {
/* 575 */       if (paramArrayOfByte1[i] != paramArrayOfByte2[i])
/* 576 */         return false;
/*     */     }
/* 578 */     return true;
/*     */   }
/*     */ 
/*     */   public void snoopEvent(byte[] paramArrayOfByte)
/*     */   {
/* 584 */     byte[] arrayOfByte = paramArrayOfByte;
/* 585 */     for (int i = 0; i < this.snoopTokenList.size(); i++)
/*     */     {
/* 587 */       Token localToken = (Token)this.snoopTokenList.elementAt(i);
/* 588 */       if ((!this.singleTokenSnoop) && 
/* 589 */         (dataMatch(arrayOfByte, localToken.tokBeg)))
/*     */       {
/* 592 */         localToken.gotTokenBeg = true;
/*     */       }
/*     */ 
/* 598 */       if (!dataMatch(arrayOfByte, localToken.tokEnd)) {
/*     */         continue;
/*     */       }
/* 601 */       if ((localToken.gotTokenBeg != true) || (localToken.gotTokenEnd) || (localToken.count <= 0)) {
/*     */         continue;
/*     */       }
/* 604 */       localToken.gotTokenEnd = true;
/* 605 */       if (this.singleTokenSnoop) continue; localToken.gotTokenBeg = false;
/*     */     }
/*     */   }
/*     */ 
/*     */   protected void notifySighting(Snoop paramSnoop)
/*     */   {
/* 617 */     paramSnoop.lsnr.snoopEvent(paramSnoop.dataOriginal);
/*     */   }
/*     */ 
/*     */   public void checkDataForMatch(byte paramByte)
/*     */   {
/* 634 */     for (int i = 0; i < this.snoopList.size(); i++) {
/* 635 */       int j = 0;
/* 636 */       Snoop localSnoop = (Snoop)this.snoopList.elementAt(i);
/*     */ 
/* 638 */       if ((localSnoop.ignoreCase) && 
/* 639 */         (paramByte >= 97) && (paramByte <= 122)) {
/* 640 */         paramByte = (byte)(paramByte - 32);
/*     */       }
/* 642 */       if (this.lastByte != paramByte) localSnoop.duplicateData = false;
/* 643 */       if (localSnoop.data[localSnoop.compareNdx] == paramByte)
/*     */       {
/* 646 */         if ((localSnoop.compareNdx == 1) && (this.lastByte == paramByte)) localSnoop.duplicateData = true;
/* 647 */         localSnoop.compareNdx += 1;
/* 648 */         if (localSnoop.compareNdx == localSnoop.data.length) {
/* 649 */           localSnoop.compareNdx = 0;
/* 650 */           notifySighting(localSnoop);
/*     */         }
/*     */ 
/*     */       }
/* 655 */       else if (!localSnoop.duplicateData) {
/* 656 */         localSnoop.compareNdx = 0;
/* 657 */         if (localSnoop.data[0] == paramByte) {
/* 658 */           localSnoop.compareNdx += 1;
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 663 */     this.lastByte = paramByte;
/*     */   }
/*     */ 
/*     */   class Token
/*     */   {
/*     */     SnoopTokenListener snp;
/*     */     int tknNbr;
/*     */     byte[] tokBeg;
/*     */     byte[] tokEnd;
/*     */     byte separator;
/* 694 */     boolean gotTokenBeg = false; boolean gotTokenEnd = false;
/*     */     byte[] tokenBuffer;
/*     */     byte[] parseToken;
/* 696 */     int count = 0;
/* 697 */     int tokBegLen = 0;
/*     */     int tokEndLen;
/*     */     int tokenId;
/*     */     int buffer;
/*     */ 
/*     */     public Token(SnoopTokenListener paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramByte, byte paramInt1, int paramInt2, int paramInt3, int arg8)
/*     */     {
/* 704 */       this.snp = paramArrayOfByte1;
/* 705 */       this.tknNbr = paramInt2;
/* 706 */       this.separator = paramInt1;
/* 707 */       this.tokEnd = paramByte;
/* 708 */       this.tokBeg = paramArrayOfByte2;
/* 709 */       this.tokenId = paramInt3;
/* 710 */       if (paramArrayOfByte2 == null) this.gotTokenBeg = true; else
/* 711 */         this.tokBegLen = paramArrayOfByte2.length;
/* 712 */       this.tokEndLen = paramByte.length;
/*     */       int i;
/* 713 */       this.tokenBuffer = new byte[i + 1];
/* 714 */       this.parseToken = new byte[i + 1];
/* 715 */       this.buffer = i;
/*     */     }
/*     */ 
/*     */     private void incrementCount() throws ExTokenOverflow
/*     */     {
/* 720 */       this.count += 1;
/* 721 */       if (this.count > this.buffer)
/*     */       {
/* 723 */         throw new ExTokenOverflow("Buffer exceeded for token with TokenId = " + this.tokenId);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   class Snoop
/*     */   {
/* 671 */     protected boolean ignoreCase = false;
/* 672 */     protected boolean duplicateData = false;
/* 673 */     protected byte[] data = null;
/* 674 */     protected byte[] dataOriginal = null;
/* 675 */     protected int compareNdx = 0;
/*     */     protected SnoopListener lsnr;
/*     */ 
/*     */     public Snoop(SnoopListener arg2)
/*     */     {
/*     */       Object localObject;
/* 680 */       this.lsnr = localObject;
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     Serialio.SnoopInputStream
 * JD-Core Version:    0.6.0
 */