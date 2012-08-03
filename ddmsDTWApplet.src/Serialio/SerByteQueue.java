/*     */ package Serialio;
/*     */ 
/*     */ public class SerByteQueue
/*     */ {
/*     */   private byte[] bQueue;
/*     */   private int head;
/*     */   private int tail;
/*     */   private int rdyCnt;
/*  31 */   private int qID = 0;
/*     */ 
/*     */   public SerByteQueue(int paramInt) {
/*  34 */     this.bQueue = new byte[paramInt];
/*  35 */     this.head = (this.tail = 0);
/*  36 */     this.rdyCnt = 0;
/*     */   }
/*     */ 
/*     */   public void get(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/*     */     throws InterruptedException
/*     */   {
/*  50 */     int i = 0; for (int j = paramInt1; i < paramInt2; j++) {
/*  51 */       paramArrayOfByte[j] = get();
/*     */ 
/*  50 */       i++;
/*     */     }
/*     */   }
/*     */ 
/*     */   public synchronized byte get()
/*     */     throws InterruptedException
/*     */   {
/*  62 */     while (isEmpty())
/*     */     {
/*  64 */       wait();
/*     */     }
/*     */ 
/*  68 */     int i = this.bQueue[this.head];
/*  69 */     this.rdyCnt -= 1;
/*  70 */     this.head += 1;
/*  71 */     if (this.head == this.bQueue.length) {
/*  72 */       this.head = 0;
/*     */     }
/*     */ 
/*  76 */     notify();
/*  77 */     return i;
/*     */   }
/*     */ 
/*     */   public synchronized void put(byte paramByte)
/*     */     throws InterruptedException
/*     */   {
/*  87 */     while (isFull())
/*     */     {
/*  89 */       wait();
/*     */     }
/*     */ 
/*  93 */     this.bQueue[this.tail] = paramByte;
/*  94 */     this.rdyCnt += 1;
/*  95 */     this.tail += 1;
/*  96 */     if (this.tail == this.bQueue.length) {
/*  97 */       this.tail = 0;
/*     */     }
/*     */ 
/* 101 */     notify();
/*     */   }
/*     */ 
/*     */   public synchronized boolean isEmpty()
/*     */   {
/* 111 */     return this.head == this.tail;
/*     */   }
/*     */ 
/*     */   public synchronized boolean isFull()
/*     */   {
/* 122 */     int i = this.tail + 1;
/* 123 */     if (i == this.bQueue.length) {
/* 124 */       i = 0;
/*     */     }
/*     */ 
/* 127 */     return i == this.head;
/*     */   }
/*     */ 
/*     */   public synchronized int available()
/*     */   {
/* 137 */     return this.rdyCnt;
/*     */   }
/*     */ 
/*     */   public int size()
/*     */   {
/* 144 */     return this.bQueue.length;
/*     */   }
/*     */ 
/*     */   public synchronized void clear()
/*     */   {
/* 151 */     this.head = (this.tail = 0);
/*     */   }
/*     */ 
/*     */   public void setID(int paramInt)
/*     */   {
/* 157 */     this.qID = paramInt;
/*     */   }
/*     */ 
/*     */   public int getID()
/*     */   {
/* 162 */     return this.qID;
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     Serialio.SerByteQueue
 * JD-Core Version:    0.6.0
 */