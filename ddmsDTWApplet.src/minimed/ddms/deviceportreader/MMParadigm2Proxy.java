/*     */ package minimed.ddms.A;
/*     */ 
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.Date;
/*     */ 
/*     */ class X extends O
/*     */ {
/*     */   private u б;
/*     */   private u Я;
/*     */   private u а;
/*     */ 
/*     */   X(int paramInt)
/*     */   {
/*  61 */     this.đ = paramInt;
/*     */ 
/*  64 */     this.б = new u(paramInt, 0L);
/*  65 */     A(this.б);
/*     */   }
/*     */ 
/*     */   public void B(v paramv, String paramString1, String paramString2)
/*     */     throws t, Z, IOException, P
/*     */   {
/*  90 */     this.б.B(paramv, paramString1, paramString2);
/*  91 */     A(this.б);
/*     */   }
/*     */ 
/*     */   public void A(v paramv, String paramString1, String paramString2, boolean paramBoolean)
/*     */     throws t, Z, IOException, P
/*     */   {
/* 117 */     this.Я = E(paramv, paramString1, paramString2);
/* 118 */     A(this.Я);
/*     */ 
/* 121 */     this.Я.A(paramv, paramString1, paramString2, false, paramBoolean);
/*     */   }
/*     */ 
/*     */   public void A(v paramv, String paramString1, String paramString2)
/*     */     throws t, Z, IOException, P
/*     */   {
/* 145 */     A(paramv, paramString1, paramString2, false);
/*     */   }
/*     */ 
/*     */   public void B(v paramv, String paramString1, String paramString2, Date paramDate1, Date paramDate2)
/*     */     throws t, Z, IOException, P
/*     */   {
/* 180 */     this.Я = E(paramv, paramString1, paramString2);
/* 181 */     A(this.Я);
/*     */ 
/* 184 */     this.Я.B(paramv, paramString1, paramString2, paramDate1, paramDate2);
/*     */   }
/*     */ 
/*     */   public void A(v paramv, String paramString1, String paramString2, Date paramDate1, Date paramDate2)
/*     */     throws t, Z, IOException, P
/*     */   {
/* 220 */     this.Я = E(paramv, paramString1, paramString2);
/* 221 */     A(this.Я);
/*     */ 
/* 224 */     this.Я.A(paramv, paramString1, paramString2, paramDate1, paramDate2);
/*     */   }
/*     */ 
/*     */   public InputStream C()
/*     */     throws Z, IOException
/*     */   {
/* 242 */     InputStream localInputStream = this.Я.Ė.B();
/* 243 */     A(this.Я);
/* 244 */     return localInputStream;
/*     */   }
/*     */ 
/*     */   public final Date N()
/*     */   {
/* 253 */     return À().N();
/*     */   }
/*     */ 
/*     */   public final String R()
/*     */   {
/* 262 */     return À().R();
/*     */   }
/*     */ 
/*     */   public String S()
/*     */   {
/* 271 */     return À().S();
/*     */   }
/*     */ 
/*     */   public final String D()
/*     */   {
/* 280 */     return À().D();
/*     */   }
/*     */ 
/*     */   public long E()
/*     */   {
/* 291 */     return À().E();
/*     */   }
/*     */ 
/*     */   public long G()
/*     */   {
/* 302 */     return À().G();
/*     */   }
/*     */ 
/*     */   public EA J()
/*     */   {
/* 314 */     return À().J();
/*     */   }
/*     */ 
/*     */   public final synchronized int I()
/*     */   {
/* 323 */     return À().I();
/*     */   }
/*     */ 
/*     */   public final String O()
/*     */   {
/* 332 */     return À().O();
/*     */   }
/*     */ 
/*     */   public String Q()
/*     */   {
/* 341 */     return À().Q();
/*     */   }
/*     */ 
/*     */   public String L()
/*     */   {
/* 350 */     return À().L();
/*     */   }
/*     */ 
/*     */   public final int F()
/*     */   {
/* 359 */     return À().F();
/*     */   }
/*     */ 
/*     */   public void A(String paramString)
/*     */     throws FileNotFoundException, IOException
/*     */   {
/* 373 */     À().A(paramString);
/*     */   }
/*     */ 
/*     */   int Z()
/*     */   {
/* 382 */     return 1;
/*     */   }
/*     */ 
/*     */   void C(v paramv)
/*     */     throws t, IOException
/*     */   {
/* 390 */     this.б.C(paramv);
/*     */   }
/*     */ 
/*     */   void D(String paramString)
/*     */     throws IOException
/*     */   {
/* 397 */     this.б.D(paramString);
/*     */   }
/*     */ 
/*     */   void _()
/*     */     throws IOException
/*     */   {
/* 404 */     this.б._();
/*     */   }
/*     */ 
/*     */   private u E(v paramv, String paramString1, String paramString2)
/*     */     throws t, Z, IOException, P
/*     */   {
/* 427 */     A(this, "createPumpInstance: determining Paradigm2 pump model...");
/*     */ 
/* 431 */     String str = this.б.D(paramv, paramString1, paramString2);
/*     */ 
/* 433 */     A(this, "createPumpInstance: Paradigm2 pump model is " + str);
/*     */     Object localObject;
/* 438 */     if (u.G(str))
/*     */     {
/* 440 */       this.¤ = paramv.getLastHistoryPageNumber(str, paramString2);
/*     */ 
/* 442 */       localObject = new u(this.đ, this.¤);
/* 443 */     } else if (b.G(str))
/*     */     {
/* 446 */       this.¤ = paramv.getLastHistoryPageNumber(str, paramString2);
/*     */ 
/* 448 */       this.Ñ = paramv.getLastGlucoseHistoryPageNumber(str, paramString2);
/*     */ 
/* 450 */       localObject = new b(this.đ, this.¤, this.Ñ);
/*     */     }
/* 452 */     else if (H.G(str))
/*     */     {
/* 455 */       this.¤ = paramv.getLastHistoryPageNumber(str, paramString2);
/*     */ 
/* 457 */       this.Ñ = paramv.getLastGlucoseHistoryPageNumber(str, paramString2);
/*     */ 
/* 459 */       localObject = new H(this.đ, this.¤, this.Ñ);
/*     */     }
/* 461 */     else if (_.G(str))
/*     */     {
/* 464 */       this.¤ = paramv.getLastHistoryPageNumber(str, paramString2);
/*     */ 
/* 466 */       this.Ñ = paramv.getLastGlucoseHistoryPageNumber(str, paramString2);
/*     */ 
/* 468 */       localObject = new _(this.đ, this.¤, this.Ñ);
/*     */     }
/* 470 */     else if (e.G(str))
/*     */     {
/* 473 */       this.¤ = paramv.getLastHistoryPageNumber(str, paramString2);
/*     */ 
/* 475 */       this.Ñ = paramv.getLastGlucoseHistoryPageNumber(str, paramString2);
/*     */ 
/* 477 */       localObject = new e(this.đ, this.¤, this.Ñ);
/*     */     }
/* 479 */     else if (a.G(str))
/*     */     {
/* 482 */       this.¤ = paramv.getLastHistoryPageNumber(str, paramString2);
/*     */ 
/* 484 */       this.Ñ = paramv.getLastGlucoseHistoryPageNumber(str, paramString2);
/*     */ 
/* 486 */       localObject = new a(this.đ, this.¤, this.Ñ);
/*     */     }
/*     */     else {
/* 489 */       localObject = null;
/* 490 */       throw new P("Unsupported Pump Model", 1000, str);
/*     */     }
/*     */ 
/* 496 */     ((u)localObject).ă = paramString2;
/* 497 */     ((u)localObject).À = str;
/*     */     try
/*     */     {
/* 502 */       paramv.allowDeviceOperation((G)localObject);
/*     */     }
/*     */     catch (P localP) {
/* 505 */       º();
/* 506 */       throw localP;
/*     */     }
/*     */ 
/* 510 */     A(this.б.W());
/* 511 */     ((u)localObject).A(this.б.W());
/*     */ 
/* 514 */     ((u)localObject).p();
/* 515 */     return (u)localObject;
/*     */   }
/*     */ 
/*     */   private void º()
/*     */   {
/*     */     try
/*     */     {
/* 525 */       this.б.e();
/*     */     } catch (t localIOException6) {
/*     */     }
/*     */     catch (IOException localIOException10) {
/*     */     }
/*     */     finally {
/*     */       try {
/* 532 */         this.б.f();
/*     */       } catch (IOException localIOException16) {
/*     */       }
/*     */       finally {
/*     */         try {
/* 537 */           this.б._();
/*     */         }
/*     */         catch (IOException localIOException17)
/*     */         {
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private u À()
/*     */   {
/* 551 */     return this.а;
/*     */   }
/*     */ 
/*     */   private void A(u paramu)
/*     */   {
/* 560 */     this.а = paramu;
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.A.X
 * JD-Core Version:    0.6.0
 */