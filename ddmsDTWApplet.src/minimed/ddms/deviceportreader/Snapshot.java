/*     */ package minimed.ddms.A;
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
/*     */ final class CA
/*     */ {
/*     */   private static final int H = 2;
/*     */   private static final int E = 4;
/*     */   private static final boolean B = true;
/*     */   private static final int A = 4;
/*  44 */   private static final String F = System.getProperty("line.separator");
/*     */   private _B G;
/*     */   private _C C;
/*     */   private TreeMap D;
/*     */ 
/*     */   CA(int paramInt1, int paramInt2, int[] paramArrayOfInt1, int[] paramArrayOfInt2, int[] paramArrayOfInt3)
/*     */   {
/*  77 */     this.C = new _C(paramInt1, paramInt2, paramArrayOfInt1, paramArrayOfInt2, paramArrayOfInt3, null);
/*     */ 
/*  80 */     this.D = new TreeMap();
/*  81 */     this.G = null;
/*     */   }
/*     */ 
/*     */   CA(int paramInt1, int paramInt2, String paramString1, String paramString2, String paramString3)
/*     */   {
/*  97 */     this(paramInt1, paramInt2, O._B.D(paramString1), O._B.D(paramString2), O._B.D(paramString3));
/*     */   }
/*     */ 
/*     */   CA()
/*     */   {
/* 108 */     this(0, 0, new int[0], new int[0], new int[0]);
/*     */   }
/*     */ 
/*     */   int D()
/*     */     throws IOException
/*     */   {
/* 125 */     Contract.pre(this.C != null);
/* 126 */     Contract.pre(this.D != null);
/*     */ 
/* 128 */     O.A(this, "write: writing snapshot to stream");
/*     */ 
/* 130 */     _C.A(this.C, 0);
/* 131 */     A(false);
/*     */ 
/* 134 */     int[] arrayOfInt = O._B.C(_B.A(this.G).toByteArray());
/* 135 */     _C.A(this.C, A(arrayOfInt));
/*     */ 
/* 138 */     A(true);
/* 139 */     O.A(this, "write: wrote " + this.G.size() + " bytes.");
/* 140 */     return this.G.size();
/*     */   }
/*     */ 
/*     */   InputStream E()
/*     */   {
/* 152 */     return new ByteArrayInputStream(_B.A(this.G).toByteArray());
/*     */   }
/*     */ 
/*     */   void B(String paramString)
/*     */     throws FileNotFoundException, IOException
/*     */   {
/* 165 */     FileOutputStream localFileOutputStream = new FileOutputStream(paramString);
/* 166 */     localFileOutputStream.write(_B.A(this.G).toByteArray());
/* 167 */     localFileOutputStream.close();
/*     */ 
/* 169 */     A(paramString);
/*     */   }
/*     */ 
/*     */   int B()
/*     */   {
/* 178 */     return _C.A(this.C);
/*     */   }
/*     */ 
/*     */   int[] F()
/*     */   {
/* 187 */     return _C.B(this.C);
/*     */   }
/*     */ 
/*     */   void A(int paramInt1, int[] paramArrayOfInt, int paramInt2)
/*     */   {
/* 200 */     Contract.pre(paramArrayOfInt != null);
/* 201 */     Contract.pre(paramArrayOfInt.length >= paramInt2);
/*     */ 
/* 203 */     _A local_A = new _A(paramInt1, paramArrayOfInt, paramInt2, null);
/* 204 */     this.D.put(new Integer(paramInt1), local_A);
/*     */   }
/*     */ 
/*     */   void A(int paramInt, int[] paramArrayOfInt)
/*     */   {
/* 216 */     Contract.pre(paramArrayOfInt != null);
/*     */ 
/* 218 */     A(paramInt, paramArrayOfInt, paramArrayOfInt.length);
/*     */   }
/*     */ 
/*     */   void A(int paramInt, String paramString)
/*     */   {
/* 230 */     Contract.pre(paramString != null);
/*     */ 
/* 232 */     int[] arrayOfInt = O._B.D(paramString);
/* 233 */     A(paramInt, arrayOfInt, arrayOfInt.length);
/*     */   }
/*     */ 
/*     */   void A(int paramInt1, int paramInt2)
/*     */   {
/* 243 */     int[] arrayOfInt = new int[1];
/*     */ 
/* 245 */     arrayOfInt[0] = paramInt2;
/* 246 */     A(paramInt1, arrayOfInt);
/*     */   }
/*     */ 
/*     */   void A(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 257 */     int[] arrayOfInt = new int[2];
/*     */ 
/* 259 */     arrayOfInt[0] = paramInt2;
/* 260 */     arrayOfInt[1] = paramInt3;
/* 261 */     A(paramInt1, arrayOfInt);
/*     */   }
/*     */ 
/*     */   int A()
/*     */   {
/* 270 */     return _C.C(this.C);
/*     */   }
/*     */ 
/*     */   private int A(int[] paramArrayOfInt)
/*     */   {
/* 281 */     paramArrayOfInt[4] = 0;
/* 282 */     paramArrayOfInt[5] = 0;
/* 283 */     int i = O._B.C(paramArrayOfInt, 0, paramArrayOfInt.length);
/*     */ 
/* 287 */     paramArrayOfInt[4] = O._B.J(i);
/* 288 */     paramArrayOfInt[5] = O._B.K(i);
/* 289 */     return i;
/*     */   }
/*     */ 
/*     */   private void A(boolean paramBoolean)
/*     */     throws IOException
/*     */   {
/* 299 */     this.G = new _B(new ByteArrayOutputStream(), null);
/*     */ 
/* 302 */     _C.A(this.C, paramBoolean);
/*     */ 
/* 305 */     Iterator localIterator = this.D.keySet().iterator();
/* 306 */     while (localIterator.hasNext()) {
/* 307 */       _A local_A = (_A)this.D.get(localIterator.next());
/* 308 */       _A.A(local_A, paramBoolean);
/*     */     }
/*     */ 
/* 311 */     this.G.close();
/*     */   }
/*     */ 
/*     */   private void A(String paramString)
/*     */     throws IOException
/*     */   {
/* 326 */     Contract.pre(this.C != null);
/* 327 */     Contract.pre(this.D != null);
/*     */ 
/* 329 */     O.A(this, "writeText: writing snapshot " + paramString + ".txt");
/*     */ 
/* 332 */     PrintWriter localPrintWriter = new PrintWriter(new BufferedWriter(new FileWriter(paramString + ".txt")));
/*     */ 
/* 336 */     _C.A(this.C, localPrintWriter);
/*     */ 
/* 339 */     Iterator localIterator = this.D.keySet().iterator();
/* 340 */     while (localIterator.hasNext()) {
/* 341 */       _A local_A = (_A)this.D.get(localIterator.next());
/* 342 */       _A.A(local_A, localPrintWriter);
/*     */     }
/*     */ 
/* 345 */     localPrintWriter.close();
/*     */   }
/*     */ 
/*     */   private final class _B extends DataOutputStream
/*     */   {
/*     */     private ByteArrayOutputStream B;
/*     */ 
/*     */     private _B(ByteArrayOutputStream arg2)
/*     */     {
/* 712 */       super();
/* 713 */       this.B = localOutputStream;
/*     */     }
/*     */ 
/*     */     public void A(int[] paramArrayOfInt)
/*     */       throws IOException
/*     */     {
/* 727 */       if (paramArrayOfInt != null)
/* 728 */         for (int i = 0; i < paramArrayOfInt.length; i++)
/* 729 */           write(paramArrayOfInt[i]);
/*     */     }
/*     */ 
/*     */     void A(long paramLong)
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
/*     */     private ByteArrayOutputStream A()
/*     */     {
/* 765 */       return this.B;
/*     */     }
/*     */   }
/*     */ 
/*     */   private final class _A
/*     */   {
/*     */     private static final int E = 255;
/*     */     private static final int B = 65535;
/*     */     private static final int D = 10000;
/*     */     private int F;
/*     */     private int C;
/*     */     private int[] G;
/*     */ 
/*     */     private _A(int paramArrayOfInt, int[] paramInt1, int arg4)
/*     */     {
/* 570 */       Contract.pre(paramInt1 != null);
/*     */       int i;
/* 571 */       Contract.pre(paramInt1.length >= i);
/*     */ 
/* 573 */       this.F = paramArrayOfInt;
/* 574 */       this.C = i;
/* 575 */       this.G = paramInt1;
/*     */     }
/*     */ 
/*     */     private _A()
/*     */     {
/* 583 */       this(0, new int[0], 0);
/*     */     }
/*     */ 
/*     */     public String toString()
/*     */     {
/* 594 */       StringBuffer localStringBuffer = new StringBuffer();
/*     */ 
/* 596 */       localStringBuffer.append("code=" + this.F + ", dataLen=" + this.C + ", hex data bytes follow..." + CA.C());
/*     */ 
/* 600 */       for (int i = 0; i < this.C; i++) {
/* 601 */         localStringBuffer.append(O._B.F(this.G[i]) + " ");
/*     */       }
/* 603 */       return localStringBuffer.toString();
/*     */     }
/*     */ 
/*     */     private int A(boolean paramBoolean)
/*     */       throws IOException
/*     */     {
/* 620 */       int i = 0;
/*     */ 
/* 622 */       Contract.pre(CA.A(this.A) != null);
/* 623 */       Contract.pre(this.G != null);
/* 624 */       Contract.pre(this.G.length >= this.C);
/*     */ 
/* 627 */       if (this.F < 10000) {
/* 628 */         Contract.pre(this.C <= 65535);
/*     */       }
/*     */ 
/* 632 */       for (int j = 0; j < this.C; j++) {
/* 633 */         Contract.pre(this.G[j] <= 255);
/*     */       }
/*     */ 
/* 637 */       CA.A(this.A).writeShort(this.F);
/* 638 */       i += 2;
/*     */ 
/* 641 */       if (this.F < 10000)
/*     */       {
/* 643 */         CA.A(this.A).writeShort(this.C);
/* 644 */         i += 2;
/*     */       }
/*     */       else {
/* 647 */         CA.A(this.A).A(this.C);
/* 648 */         i += 4;
/*     */       }
/*     */ 
/* 652 */       for (j = 0; j < this.C; j++) {
/* 653 */         CA.A(this.A).writeByte(this.G[j]);
/*     */       }
/*     */ 
/* 656 */       i += this.C;
/* 657 */       if (paramBoolean) {
/* 658 */         O.A(this, "write: number of data bytes written for element " + this.F + "=" + this.C);
/*     */       }
/*     */ 
/* 662 */       return i;
/*     */     }
/*     */ 
/*     */     private void A(PrintWriter paramPrintWriter)
/*     */     {
/* 674 */       Contract.pre(paramPrintWriter != null);
/* 675 */       Contract.pre(this.G != null);
/* 676 */       Contract.pre(this.G.length >= this.C);
/*     */ 
/* 678 */       for (int i = 0; i < this.C; i++) {
/* 679 */         Contract.pre(this.G[i] <= 255);
/*     */       }
/*     */ 
/* 683 */       paramPrintWriter.println(toString());
/* 684 */       paramPrintWriter.println();
/*     */     }
/*     */   }
/*     */ 
/*     */   private final class _C
/*     */   {
/*     */     private int H;
/*     */     private int D;
/*     */     private int F;
/*     */     private int C;
/*     */     private int[] E;
/*     */     private int[] G;
/*     */     private int[] B;
/*     */ 
/*     */     private _C(int paramInt1, int paramArrayOfInt1, int[] paramArrayOfInt2, int[] paramArrayOfInt3, int[] arg6)
/*     */     {
/* 405 */       this.H = paramInt1;
/* 406 */       this.D = paramArrayOfInt1;
/* 407 */       this.F = 0;
/* 408 */       this.C = 0;
/* 409 */       this.E = paramArrayOfInt2;
/* 410 */       this.G = paramArrayOfInt3;
/*     */       Object localObject;
/* 411 */       this.B = localObject;
/*     */     }
/*     */ 
/*     */     private _C()
/*     */     {
/* 419 */       this(0, 0, new int[0], new int[0], new int[0]);
/*     */     }
/*     */ 
/*     */     public String toString()
/*     */     {
/* 430 */       StringBuffer localStringBuffer = new StringBuffer();
/*     */ 
/* 432 */       localStringBuffer.append("snapshotFormatID=" + this.H + CA.C());
/*     */ 
/* 435 */       localStringBuffer.append("snapshotVersion=" + this.D + CA.C());
/*     */ 
/* 438 */       localStringBuffer.append("snapshotCRC=" + O._B.H(this.F) + "; ");
/*     */ 
/* 441 */       localStringBuffer.append("unused=" + O._B.H(this.C) + CA.C());
/*     */ 
/* 444 */       String str = new String(O._B.B(this.E));
/* 445 */       str = O._B.E(str);
/* 446 */       localStringBuffer.append("firmware=" + str + CA.C());
/*     */ 
/* 449 */       str = new String(O._B.B(this.G));
/* 450 */       str = O._B.E(str);
/* 451 */       localStringBuffer.append("serialNum=" + str + CA.C());
/*     */ 
/* 454 */       localStringBuffer.append("timeStamp=" + O._B.G(this.B));
/* 455 */       return localStringBuffer.toString();
/*     */     }
/*     */ 
/*     */     private int A(boolean paramBoolean)
/*     */       throws IOException
/*     */     {
/* 472 */       int i = 0;
/*     */ 
/* 474 */       Contract.pre(CA.A(this.A) != null);
/* 475 */       Contract.pre(this.E != null);
/* 476 */       Contract.pre(this.G != null);
/* 477 */       Contract.pre(this.B != null);
/*     */ 
/* 480 */       CA.A(this.A).writeShort(this.H);
/* 481 */       i += 2;
/*     */ 
/* 484 */       CA.A(this.A).writeShort(this.D);
/* 485 */       i += 2;
/*     */ 
/* 488 */       CA.A(this.A).writeShort(this.F);
/* 489 */       i += 2;
/*     */ 
/* 492 */       CA.A(this.A).writeShort(this.C);
/* 493 */       i += 2;
/*     */ 
/* 496 */       CA.A(this.A).A(this.E);
/* 497 */       i += this.E.length;
/*     */ 
/* 500 */       CA.A(this.A).A(this.G);
/* 501 */       i += this.G.length;
/*     */ 
/* 504 */       CA.A(this.A).A(this.B);
/* 505 */       i += this.B.length;
/*     */ 
/* 507 */       if (paramBoolean) {
/* 508 */         O.A(this, "write: number of bytes written for header = " + i);
/*     */       }
/*     */ 
/* 511 */       return i;
/*     */     }
/*     */ 
/*     */     private void A(PrintWriter paramPrintWriter)
/*     */     {
/* 524 */       Contract.pre(paramPrintWriter != null);
/* 525 */       Contract.pre(this.E != null);
/* 526 */       Contract.pre(this.G != null);
/* 527 */       Contract.pre(this.B != null);
/*     */ 
/* 529 */       paramPrintWriter.println(toString());
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.A.CA
 * JD-Core Version:    0.6.0
 */