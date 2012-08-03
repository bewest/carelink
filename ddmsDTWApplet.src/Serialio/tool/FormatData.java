/*     */ package Serialio.tool;
/*     */ 
/*     */ public final class FormatData
/*     */ {
/*  33 */   private static char[] hexSym = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
/*     */ 
/*     */   public static String getHexString(short paramShort)
/*     */   {
/*  42 */     byte[] arrayOfByte = new byte[2];
/*     */ 
/*  45 */     arrayOfByte[0] = (byte)(paramShort >> 8);
/*  46 */     arrayOfByte[1] = (byte)paramShort;
/*  47 */     String str = getHexString(arrayOfByte, 0, arrayOfByte.length, 0, 4);
/*  48 */     return str;
/*     */   }
/*     */ 
/*     */   public static String getHexString(int paramInt)
/*     */   {
/*  55 */     byte[] arrayOfByte = new byte[4];
/*     */ 
/*  58 */     arrayOfByte[0] = (byte)(paramInt >> 24);
/*  59 */     arrayOfByte[1] = (byte)(paramInt >> 16);
/*  60 */     arrayOfByte[2] = (byte)(paramInt >> 8);
/*  61 */     arrayOfByte[3] = (byte)paramInt;
/*  62 */     String str = getHexString(arrayOfByte, 0, arrayOfByte.length, 1, 16);
/*  63 */     return str;
/*     */   }
/*     */ 
/*     */   public static String getHexString(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*     */   {
/*  76 */     Character localCharacter1 = new Character(' ');
/*  77 */     Character localCharacter2 = new Character('\n');
/*  78 */     StringBuffer localStringBuffer = new StringBuffer();
/*  79 */     int i = 0;
/*  80 */     int j = 0;
/*     */ 
/*  82 */     for (int k = paramInt1; k < paramInt1 + paramInt2; ) {
/*  83 */       int m = paramArrayOfByte[k];
/*  84 */       for (int n = 1; n >= 0; n--) {
/*  85 */         int i1 = 4 * n;
/*  86 */         int i2 = m >> i1 & 0xF;
/*  87 */         Character localCharacter3 = new Character(hexSym[i2]);
/*  88 */         localStringBuffer.append(localCharacter3);
/*     */       }
/*     */ 
/*  91 */       k++;
/*  92 */       for (n = 0; n < paramInt3; n++) {
/*  93 */         localStringBuffer.append(localCharacter1);
/*     */       }
/*  95 */       j++;
/*  96 */       if ((paramInt3 > 0) && (j % paramInt4 == 0)) {
/*  97 */         localStringBuffer.append(localCharacter2);
/*     */       }
/*     */     }
/*     */ 
/* 101 */     return new String(localStringBuffer);
/*     */   }
/*     */ 
/*     */   public static String getAsciiHexString(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*     */   {
/* 118 */     Character localCharacter1 = new Character(' ');
/* 119 */     Character localCharacter2 = new Character('\n');
/* 120 */     StringBuffer localStringBuffer = new StringBuffer();
/* 121 */     int i = 0;
/* 122 */     int j = 0;
/*     */ 
/* 125 */     for (int k = paramInt1; k < paramInt1 + paramInt2; ) {
/* 126 */       int m = paramArrayOfByte[k];
/*     */       Character localCharacter3;
/* 127 */       if ((m >= 32) && (m <= 126)) {
/* 128 */         localCharacter3 = new Character((char)m);
/* 129 */         localStringBuffer.append(localCharacter3);
/*     */       }
/*     */       else {
/* 132 */         localCharacter3 = new Character('<');
/* 133 */         localStringBuffer.append(localCharacter3);
/* 134 */         for (n = 1; n >= 0; n--) {
/* 135 */           int i1 = 4 * n;
/* 136 */           int i2 = m >> i1 & 0xF;
/* 137 */           localCharacter3 = new Character(hexSym[i2]);
/* 138 */           localStringBuffer.append(localCharacter3);
/*     */         }
/* 140 */         localCharacter3 = new Character('>');
/* 141 */         localStringBuffer.append(localCharacter3);
/*     */       }
/* 143 */       k++;
/* 144 */       for (int n = 0; n < paramInt3; n++) {
/* 145 */         localStringBuffer.append(localCharacter1);
/*     */       }
/* 147 */       j++;
/* 148 */       if ((paramInt3 > 0) && (j % paramInt4 == 0)) {
/* 149 */         localStringBuffer.append(localCharacter2);
/*     */       }
/*     */     }
/*     */ 
/* 153 */     return new String(localStringBuffer);
/*     */   }
/*     */ 
/*     */   public static int getHexValue(byte[] paramArrayOfByte) {
/* 157 */     return getHexValue(paramArrayOfByte, paramArrayOfByte.length);
/*     */   }
/*     */ 
/*     */   public static int getHexValue(byte[] paramArrayOfByte, int paramInt) {
/* 161 */     int i = 0;
/* 162 */     int m = 1;
/*     */ 
/* 165 */     for (int n = paramInt - 1; n >= 0; n--) {
/* 166 */       int k = paramArrayOfByte[n];
/*     */       int j;
/* 168 */       if ((k >= 48) && (k <= 57)) j = (byte)(k - 48);
/* 169 */       else if ((k >= 97) && (k <= 102)) j = (byte)(k - 97 + 10);
/* 170 */       else if ((k >= 65) && (k <= 70)) j = (byte)(k - 65 + 10); else {
/* 171 */         throw new RuntimeException("Invalid hex value: " + k);
/*     */       }
/* 173 */       i += j * m;
/* 174 */       m *= 16;
/*     */     }
/*     */ 
/* 177 */     return i;
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     Serialio.tool.FormatData
 * JD-Core Version:    0.6.0
 */