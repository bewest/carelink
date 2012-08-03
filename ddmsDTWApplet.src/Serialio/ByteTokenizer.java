/*     */ package Serialio;
/*     */ 
/*     */ public class ByteTokenizer
/*     */ {
/*     */   byte[] line;
/*     */   byte[] token;
/*     */   byte separator;
/*     */   int len;
/*  32 */   int positionInString = 0;
/*  33 */   int tokenGiven = 0;
/*     */   int endPoint;
/*     */ 
/*     */   public ByteTokenizer(byte[] paramArrayOfByte, byte paramByte)
/*     */   {
/*  38 */     this.line = paramArrayOfByte;
/*  39 */     this.separator = paramByte;
/*  40 */     this.len = paramArrayOfByte.length;
/*  41 */     this.token = new byte[this.len];
/*     */   }
/*     */ 
/*     */   public void setOffset(int paramInt)
/*     */   {
/*  51 */     this.positionInString = paramInt;
/*     */   }
/*     */ 
/*     */   public void setParseEnd(int paramInt)
/*     */   {
/*  60 */     this.len = paramInt;
/*     */   }
/*     */ 
/*     */   public static int numberOfTokens(byte[] paramArrayOfByte, byte paramByte)
/*     */   {
/*  71 */     if (paramArrayOfByte.length == 0) return 0;
/*     */ 
/*  73 */     int i = 0;
/*  74 */     for (int j = 0; j < paramArrayOfByte.length; j++)
/*     */     {
/*  76 */       if (paramArrayOfByte[j] == paramByte)
/*  77 */         i++;
/*     */     }
/*  79 */     i++; return i;
/*     */   }
/*     */ 
/*     */   public int numberOfTokens()
/*     */   {
/*  88 */     if (this.line.length == 0) return 0;
/*     */ 
/*  90 */     int i = 0;
/*  91 */     for (int j = 0; j < this.len; j++)
/*     */     {
/*  93 */       if (this.line[j] == this.separator)
/*  94 */         i++;
/*     */     }
/*  96 */     i++; return i;
/*     */   }
/*     */ 
/*     */   public byte[] nextToken()
/*     */   {
/* 105 */     if (this.len == 0) return "".getBytes();
/*     */ 
/* 107 */     if (this.positionInString >= this.len) return "".getBytes();
/* 108 */     int i = this.line[this.positionInString];
/*     */ 
/* 111 */     int j = 0;
/* 112 */     while (i != this.separator)
/*     */     {
/* 114 */       this.token[(j++)] = i;
/* 115 */       this.positionInString += 1;
/* 116 */       if (this.positionInString == this.len)
/*     */       {
/* 120 */         arrayOfByte = new byte[j];
/* 121 */         System.arraycopy(this.token, 0, arrayOfByte, 0, j);
/* 122 */         return arrayOfByte;
/*     */       }
/* 124 */       i = this.line[this.positionInString];
/*     */     }
/*     */ 
/* 127 */     this.tokenGiven += 1;
/* 128 */     if (this.positionInString < this.len - 1) {
/* 129 */       this.positionInString += 1;
/*     */     }
/* 131 */     byte[] arrayOfByte = new byte[j];
/* 132 */     System.arraycopy(this.token, 0, arrayOfByte, 0, j);
/* 133 */     return arrayOfByte;
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     Serialio.ByteTokenizer
 * JD-Core Version:    0.6.0
 */