/*     */ package Serialio;
/*     */ 
/*     */ public class SerByteQueue
/*     */ {
/*     */   private byte[] bQueue;
/*     */   private int head;
/*     */   private int tail;
/*     */   private int rdyCnt;
/*     */ 
/*     */   public SerByteQueue(int paramInt)
/*     */   {
/*  32 */     this.bQueue = new byte[paramInt];
/*  33 */     this.head = (this.tail = 0);
/*  34 */     this.rdyCnt = 0;
/*     */   }
/*     */ 
/*     */   public void get(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/*     */     throws InterruptedException
/*     */   {
/*  48 */     int i = 0; for (int j = paramInt1; i < paramInt2; j++) {
/*  49 */       paramArrayOfByte[j] = get();
/*     */ 
/*  48 */       i++;
/*     */     }
/*     */   }
/*     */ 
/*     */   public synchronized byte get()
/*     */     throws InterruptedException
/*     */   {
/*  60 */     while (isEmpty()) {
/*  61 */       wait();
/*     */     }
/*     */ 
/*  64 */     int i = this.bQueue[this.head];
/*  65 */     this.rdyCnt -= 1;
/*  66 */     this.head += 1;
/*  67 */     if (this.head == this.bQueue.length) {
/*  68 */       this.head = 0;
/*     */     }
/*  70 */     notify();
/*  71 */     return i;
/*     */   }
/*     */ 
/*     */   public synchronized void put(byte paramByte)
/*     */     throws InterruptedException
/*     */   {
/*  81 */     while (isFull()) {
/*  82 */       wait();
/*     */     }
/*     */ 
/*  85 */     this.bQueue[this.tail] = paramByte;
/*  86 */     this.rdyCnt += 1;
/*  87 */     this.tail += 1;
/*  88 */     if (this.tail == this.bQueue.length) {
/*  89 */       this.tail = 0;
/*     */     }
/*  91 */     notify();
/*     */   }
/*     */ 
/*     */   public boolean isEmpty()
/*     */   {
/* 100 */     return this.head == this.tail;
/*     */   }
/*     */ 
/*     */   public boolean isFull()
/*     */   {
/* 110 */     int i = this.tail + 1;
/* 111 */     if (i == this.bQueue.length) {
/* 112 */       i = 0;
/*     */     }
/*     */ 
/* 115 */     return i == this.head;
/*     */   }
/*     */ 
/*     */   public int available()
/*     */   {
/* 125 */     return this.rdyCnt;
/*     */   }
/*     */ 
/*     */   public int size()
/*     */   {
/* 132 */     return this.bQueue.length;
/*     */   }
/*     */ 
/*     */   public void clear()
/*     */   {
/* 139 */     this.head = (this.tail = 0);
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     Serialio.SerByteQueue
 * JD-Core Version:    0.6.0
 */