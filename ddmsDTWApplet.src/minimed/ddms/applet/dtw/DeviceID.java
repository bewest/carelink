/*     */ package minimed.ddms.applet.dtw;
/*     */ 
/*     */ import java.util.StringTokenizer;
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ public class DeviceID
/*     */ {
/*     */   private static final String ID_SEPARATOR = ":";
/*     */   private final String m_id;
/*     */ 
/*     */   public DeviceID(String paramString)
/*     */   {
/*  53 */     Contract.preString(paramString);
/*  54 */     Contract.pre(paramString.indexOf(":") > 0);
/*  55 */     this.m_id = paramString;
/*     */   }
/*     */ 
/*     */   public DeviceID(String paramString1, String paramString2)
/*     */   {
/*  67 */     this(paramString1 + ":" + paramString2);
/*  68 */     Contract.preString(paramString1);
/*  69 */     Contract.preString(paramString2);
/*     */   }
/*     */ 
/*     */   public String getID()
/*     */   {
/*  81 */     return this.m_id;
/*     */   }
/*     */ 
/*     */   public String getSerialNumber()
/*     */   {
/*  91 */     StringTokenizer localStringTokenizer = new StringTokenizer(this.m_id, ":");
/*     */ 
/*  93 */     String str = localStringTokenizer.nextToken();
/*  94 */     str = localStringTokenizer.nextToken();
/*  95 */     return str;
/*     */   }
/*     */ 
/*     */   public String getClassName()
/*     */   {
/* 105 */     StringTokenizer localStringTokenizer = new StringTokenizer(this.m_id, ":");
/*     */ 
/* 107 */     String str = localStringTokenizer.nextToken();
/* 108 */     return str;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 117 */     return this.m_id;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object paramObject)
/*     */   {
/* 127 */     boolean bool = false;
/*     */ 
/* 129 */     if ((paramObject != null) && ((paramObject instanceof DeviceID))) {
/* 130 */       DeviceID localDeviceID = (DeviceID)paramObject;
/*     */ 
/* 132 */       bool = getID().equals(localDeviceID.getID());
/*     */     }
/*     */ 
/* 135 */     return bool;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 145 */     return toString().hashCode();
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.DeviceID
 * JD-Core Version:    0.6.0
 */