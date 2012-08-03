/*     */ package minimed.ddms.A;
/*     */ 
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ class L extends O
/*     */ {
/*     */   private O ĭ;
/*     */   private O Į;
/*     */   private List<O> Ĭ;
/*     */ 
/*     */   L()
/*     */   {
/*  61 */     this.Ĭ = new ArrayList();
/*  62 */     this.Ĭ.add(new FA());
/*  63 */     this.Ĭ.add(new f());
/*     */   }
/*     */ 
/*     */   public void A(v paramv, String paramString1, String paramString2)
/*     */     throws t, IOException, P
/*     */   {
/*  82 */     B(false);
/*     */ 
/*  85 */     for (O localO : this.Ĭ) {
/*     */       try {
/*  87 */         this.Į = localO;
/*  88 */         A(this, "readData: trying meter " + this.Į);
/*  89 */         localO.A(paramv, paramString1, paramString2);
/*  90 */         B(localO);
/*     */       }
/*     */       catch (t localt) {
/*  93 */         if (isHaltRequested()) {
/*  94 */           throw localt;
/*     */         }
/*     */ 
/* 100 */         break label107;
/*     */       }
/*     */       catch (Z localZ)
/*     */       {
/*     */       }
/*  99 */       continue;
/*     */     }
/*     */ 
/* 103 */     label107: if (d() == null)
/* 104 */       throw new t("could not find any Xtra/Xceed meter on port " + paramString1);
/*     */   }
/*     */ 
/*     */   public String A(v paramv)
/*     */     throws t, IOException
/*     */   {
/* 112 */     String str = null;
/* 113 */     B(false);
/* 114 */     B(null);
/*     */ 
/* 117 */     for (O localO : this.Ĭ) {
/*     */       try {
/* 119 */         this.Į = localO;
/* 120 */         A(this, "autoDetectDevice: trying meter " + this.Į);
/* 121 */         str = localO.A(paramv);
/* 122 */         B(localO);
/*     */       }
/*     */       catch (t localt) {
/* 125 */         if (isHaltRequested()) {
/* 126 */           throw localt;
/*     */         }
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 132 */     if (d() == null) {
/* 133 */       throw new t("could not find any Xtra/Xceed meter");
/*     */     }
/*     */ 
/* 136 */     return str;
/*     */   }
/*     */ 
/*     */   public void A(String paramString) throws FileNotFoundException, IOException
/*     */   {
/* 141 */     Contract.preNonNull(this.ĭ);
/* 142 */     this.ĭ.A(paramString);
/*     */   }
/*     */ 
/*     */   public InputStream C() throws Z, IOException
/*     */   {
/* 147 */     Contract.preNonNull(this.ĭ);
/* 148 */     return this.ĭ.C();
/*     */   }
/*     */ 
/*     */   public String L()
/*     */   {
/* 153 */     return this.Į != null ? this.Į.L() : "unknown";
/*     */   }
/*     */ 
/*     */   public void P()
/*     */   {
/* 158 */     if (this.Į != null)
/* 159 */       this.Į.P();
/*     */   }
/*     */ 
/*     */   int Z()
/*     */   {
/* 165 */     return 3;
/*     */   }
/*     */ 
/*     */   void C(v paramv) throws t, IOException
/*     */   {
/* 170 */     B(false);
/* 171 */     B(null);
/*     */ 
/* 174 */     for (O localO : this.Ĭ) {
/*     */       try {
/* 176 */         this.Į = localO;
/* 177 */         A(this, "findDevice: trying meter " + this.Į);
/* 178 */         localO.C(paramv);
/* 179 */         B(localO);
/*     */       }
/*     */       catch (t localt) {
/* 182 */         if (isHaltRequested()) {
/* 183 */           throw localt;
/*     */         }
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 189 */     if (d() == null)
/* 190 */       throw new t("could not find any Xtra/Xceed meter");
/*     */   }
/*     */ 
/*     */   void D(String paramString)
/*     */     throws IOException
/*     */   {
/* 199 */     Contract.unreachable();
/*     */   }
/*     */ 
/*     */   void _()
/*     */     throws IOException
/*     */   {
/* 206 */     Contract.unreachable();
/*     */   }
/*     */ 
/*     */   private void B(O paramO)
/*     */   {
/* 216 */     this.ĭ = paramO;
/* 217 */     if (paramO != null) {
/* 218 */       A(this, "found meter " + paramO);
/*     */ 
/* 220 */       this.ć = paramO.ć;
/* 221 */       this.Ă = paramO.Ă;
/* 222 */       this.ă = paramO.ă;
/* 223 */       this.u = paramO.u;
/* 224 */       this.Þ = paramO.Þ;
/* 225 */       this.ĕ = paramO.ĕ;
/*     */ 
/* 228 */       ArrayList localArrayList = new ArrayList();
/*     */ 
/* 231 */       localArrayList.add(paramO);
/*     */ 
/* 234 */       for (O localO : this.Ĭ) {
/* 235 */         if (!localO.equals(paramO)) {
/* 236 */           localArrayList.add(localO);
/*     */         }
/*     */       }
/*     */ 
/* 240 */       Contract.invariant(localArrayList.size() == this.Ĭ.size());
/* 241 */       this.Ĭ = localArrayList;
/*     */     }
/*     */   }
/*     */ 
/*     */   private O d()
/*     */   {
/* 251 */     return this.ĭ;
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.A.L
 * JD-Core Version:    0.6.0
 */