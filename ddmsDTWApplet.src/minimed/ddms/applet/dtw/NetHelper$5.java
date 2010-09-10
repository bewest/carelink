/*     */ package minimed.ddms.applet.dtw;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ 
/*     */ class NetHelper$5
/*     */   implements NetHelper.ObjectExchanger
/*     */ {
/*     */   private final NetHelper.4 this$1;
/*     */ 
/*     */   public void writeRequest(ObjectOutputStream paramObjectOutputStream)
/*     */     throws IOException
/*     */   {
/* 158 */     paramObjectOutputStream.writeInt(25);
/* 159 */     paramObjectOutputStream.writeObject(NetHelper.4.access$000(this.this$1));
/*     */   }
/*     */ 
/*     */   public Object readResponse(ObjectInputStream paramObjectInputStream)
/*     */     throws IOException
/*     */   {
/* 170 */     int i = paramObjectInputStream.readInt();
/* 171 */     return new Integer(i);
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.NetHelper.5
 * JD-Core Version:    0.6.0
 */