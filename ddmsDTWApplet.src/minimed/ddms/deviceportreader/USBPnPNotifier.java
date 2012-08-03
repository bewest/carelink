/*     */ package minimed.ddms.A;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import mdt.common.device.driver.minimed.IDevicePnPListener;
/*     */ 
/*     */ final class l
/*     */   implements IDevicePnPListener
/*     */ {
/*  33 */   private boolean B = false;
/*  34 */   private boolean D = true;
/*     */   private String A;
/*     */   private boolean C;
/*     */ 
/*     */   l(String paramString, boolean paramBoolean)
/*     */   {
/*  47 */     this.A = paramString;
/*  48 */     this.C = paramBoolean;
/*     */   }
/*     */ 
/*     */   public void deviceAttached(int paramInt)
/*     */   {
/*  61 */     String str = this.A + " ATTACHED (handle=" + paramInt + ")";
/*  62 */     if (this.C);
/*  64 */     O.C(this, str);
/*  65 */     this.B = true;
/*     */   }
/*     */ 
/*     */   public void deviceDetached(int paramInt)
/*     */   {
/*  74 */     String str = this.A + " DETACHED: (handle=" + paramInt + ")";
/*  75 */     if (this.C);
/*  77 */     O.C(this, str);
/*  78 */     this.B = false;
/*     */   }
/*     */ 
/*     */   boolean A()
/*     */   {
/*  87 */     if (this.D)
/*     */     {
/*  90 */       this.D = false;
/*  91 */       DA localDA = new DA();
/*  92 */       DA.A(this);
/*     */       try {
/*  94 */         localDA._();
/*  95 */         localDA.V();
/*  96 */         this.B = true;
/*     */       }
/*     */       catch (IOException localIOException) {
/*     */       }
/*     */     }
/* 101 */     return this.B;
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.A.l
 * JD-Core Version:    0.6.0
 */