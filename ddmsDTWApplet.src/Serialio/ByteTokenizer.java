/*    */ package Serialio;
/*    */ 
/*    */ class ByteTokenizer
/*    */ {
/*    */   byte[] line;
/*    */   byte[] token;
/*    */   byte separator;
/*    */   int len;
/* 55 */   int positionInString = 0;
/* 56 */   int tokenGiven = 0;
/*    */ 
/*    */   public ByteTokenizer(byte[] paramArrayOfByte, byte paramByte)
/*    */   {
/* 34 */     this.line = paramArrayOfByte;
/* 35 */     this.separator = paramByte;
/* 36 */     this.len = paramArrayOfByte.length;
/* 37 */     this.token = new byte[this.len];
/*    */   }
/*    */ 
/*    */   public int numberOfTokens()
/*    */   {
/* 46 */     int i = 0;
/* 47 */     for (int j = 0; j < this.len; j++)
/*    */     {
/* 49 */       if (this.line[j] == this.separator)
/* 50 */         i++;
/*    */     }
/* 52 */     i++; return i;
/*    */   }
/*    */ 
/*    */   public byte[] nextToken()
/*    */   {
/* 64 */     if (this.len == 0) return "".getBytes();
/*    */ 
/* 66 */     if (this.positionInString >= this.len) return "".getBytes();
/* 67 */     int i = this.line[this.positionInString];
/*    */ 
/* 70 */     int j = 0;
/* 71 */     while (i != this.separator)
/*    */     {
/* 73 */       this.token[(j++)] = i;
/* 74 */       this.positionInString += 1;
/* 75 */       if (this.positionInString == this.len)
/*    */       {
/* 79 */         arrayOfByte = new byte[j];
/* 80 */         System.arraycopy(this.token, 0, arrayOfByte, 0, j);
/* 81 */         return arrayOfByte;
/*    */       }
/* 83 */       i = this.line[this.positionInString];
/*    */     }
/*    */ 
/* 86 */     this.tokenGiven += 1;
/* 87 */     if (this.positionInString < this.len - 1) {
/* 88 */       this.positionInString += 1;
/*    */     }
/* 90 */     byte[] arrayOfByte = new byte[j];
/* 91 */     System.arraycopy(this.token, 0, arrayOfByte, 0, j);
/* 92 */     return arrayOfByte;
/*    */   }
/*    */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     Serialio.ByteTokenizer
 * JD-Core Version:    0.6.0
 */