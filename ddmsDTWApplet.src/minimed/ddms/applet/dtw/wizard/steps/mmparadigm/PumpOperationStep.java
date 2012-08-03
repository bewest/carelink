/*     */ package minimed.ddms.applet.dtw.wizard.steps.mmparadigm;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.ResourceBundle;
/*     */ import minimed.ddms.applet.dtw.LogWriter;
/*     */ import minimed.ddms.applet.dtw.NetHelper;
/*     */ import minimed.ddms.applet.dtw.wizard.Wizard;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardConfig;
/*     */ import minimed.ddms.applet.dtw.wizard.steps.DeviceOperationStep;
/*     */ import minimed.ddms.applet.dtw.wizard.steps.TransferDataCheckStep;
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ public class PumpOperationStep extends DeviceOperationStep
/*     */ {
/*  42 */   private final String[] m_pumpPhaseNotification = { this.m_resources.getString("wizard.operation.pumpparadigm.phase0.notification"), this.m_resources.getString("wizard.operation.pumpparadigm.phase1.notification"), this.m_resources.getString("wizard.operation.pumpparadigm.phase2.notification"), this.m_resources.getString("wizard.operation.pumpparadigm.phase3.notification"), this.m_resources.getString("wizard.operation.pumpparadigm.phase4.notification"), this.m_resources.getString("wizard.operation.pumpparadigm.phase5.notification"), this.m_resources.getString("wizard.operation.pumpparadigm.phase6.notification"), this.m_resources.getString("wizard.operation.pumpparadigm.phase7.notification") };
/*     */ 
/*  63 */   private final String[] m_monitorPhaseNotification = { this.m_resources.getString("wizard.operation.pumpparadigm.phase0.notification"), this.m_resources.getString("wizard.operation.pumpparadigm.phase1.notification"), this.m_resources.getString("wizard.operation.pumpparadigm.phase2.notification"), this.m_resources.getString("wizard.operation.pumpparadigm.phase3.notification"), this.m_resources.getString("wizard.operation.pumpparadigm.phase4.notifica.cgm"), this.m_resources.getString("wizard.operation.pumpparadigm.phase5.notification"), this.m_resources.getString("wizard.operation.pumpparadigm.phase6.notifica.cgm"), this.m_resources.getString("wizard.operation.pumpparadigm.phase7.notification") };
/*     */ 
/*  84 */   private final String[] m_pumpPhaseElaboration = { this.m_resources.getString("wizard.operation.pumpparadigm.phase0.elaboration"), this.m_resources.getString("wizard.operation.pumpparadigm.phase1.elaboration"), this.m_resources.getString("wizard.operation.pumpparadigm.phase2.elaboration"), this.m_resources.getString("wizard.operation.pumpparadigm.phase3.elaboration"), this.m_resources.getString("wizard.operation.pumpparadigm.phase4.elaboration"), this.m_resources.getString("wizard.operation.pumpparadigm.phase5.elaboration"), this.m_resources.getString("wizard.operation.pumpparadigm.phase6.elaboration"), this.m_resources.getString("wizard.operation.pumpparadigm.phase7.elaboration") };
/*     */ 
/* 105 */   private final String[] m_monitorPhaseElaboration = { this.m_resources.getString("wizard.operation.pumpparadigm.phase0.elaboration"), this.m_resources.getString("wizard.operation.pumpparadigm.phase1.elaboration"), this.m_resources.getString("wizard.operation.pumpparadigm.phase2.elaboration"), this.m_resources.getString("wizard.operation.pumpparadigm.phase3.elaboration"), this.m_resources.getString("wizard.operation.pumpparadigm.phase4.elabora.cgm"), this.m_resources.getString("wizard.operation.pumpparadigm.phase5.elabora.cgm"), this.m_resources.getString("wizard.operation.pumpparadigm.phase6.elabora.cgm"), this.m_resources.getString("wizard.operation.pumpparadigm.phase7.elaboration") };
/*     */   private final Map m_lastPumpHistoryPageReadMaps;
/*     */   private final Map m_lastGlucoseHistoryPageReadMaps;
/*     */ 
/*     */   public PumpOperationStep(Wizard paramWizard)
/*     */   {
/* 135 */     super(paramWizard, "wizard.read.icon", TransferDataCheckStep.class, PumpOperationFailStep.class, "wizard.operation.read.title");
/*     */ 
/* 139 */     this.m_lastPumpHistoryPageReadMaps = new HashMap();
/* 140 */     HashMap localHashMap = new HashMap();
/* 141 */     this.m_lastPumpHistoryPageReadMaps.put("515", localHashMap);
/* 142 */     localHashMap = new HashMap();
/* 143 */     this.m_lastPumpHistoryPageReadMaps.put("715", localHashMap);
/* 144 */     localHashMap = new HashMap();
/* 145 */     this.m_lastPumpHistoryPageReadMaps.put("522", localHashMap);
/* 146 */     localHashMap = new HashMap();
/* 147 */     this.m_lastPumpHistoryPageReadMaps.put("522K", localHashMap);
/* 148 */     localHashMap = new HashMap();
/* 149 */     this.m_lastPumpHistoryPageReadMaps.put("722", localHashMap);
/* 150 */     localHashMap = new HashMap();
/* 151 */     this.m_lastPumpHistoryPageReadMaps.put("722K", localHashMap);
/* 152 */     localHashMap = new HashMap();
/* 153 */     this.m_lastPumpHistoryPageReadMaps.put("523", localHashMap);
/* 154 */     localHashMap = new HashMap();
/* 155 */     this.m_lastPumpHistoryPageReadMaps.put("523K", localHashMap);
/* 156 */     localHashMap = new HashMap();
/* 157 */     this.m_lastPumpHistoryPageReadMaps.put("723", localHashMap);
/* 158 */     localHashMap = new HashMap();
/* 159 */     this.m_lastPumpHistoryPageReadMaps.put("723K", localHashMap);
/* 160 */     localHashMap = new HashMap();
/* 161 */     this.m_lastPumpHistoryPageReadMaps.put("553", localHashMap);
/* 162 */     localHashMap = new HashMap();
/* 163 */     this.m_lastPumpHistoryPageReadMaps.put("753", localHashMap);
/* 164 */     localHashMap = new HashMap();
/* 165 */     this.m_lastPumpHistoryPageReadMaps.put("554", localHashMap);
/* 166 */     localHashMap = new HashMap();
/* 167 */     this.m_lastPumpHistoryPageReadMaps.put("754", localHashMap);
/* 168 */     localHashMap = new HashMap();
/* 169 */     this.m_lastPumpHistoryPageReadMaps.put("7100", localHashMap);
/*     */ 
/* 171 */     localHashMap = new HashMap();
/* 172 */     this.m_lastPumpHistoryPageReadMaps.put("7100B", localHashMap);
/*     */ 
/* 174 */     localHashMap = new HashMap();
/* 175 */     this.m_lastPumpHistoryPageReadMaps.put("7100K", localHashMap);
/*     */ 
/* 177 */     localHashMap = new HashMap();
/* 178 */     this.m_lastPumpHistoryPageReadMaps.put("7200", localHashMap);
/*     */ 
/* 181 */     this.m_lastGlucoseHistoryPageReadMaps = new HashMap();
/* 182 */     localHashMap = new HashMap();
/* 183 */     this.m_lastGlucoseHistoryPageReadMaps.put("522", localHashMap);
/*     */ 
/* 185 */     localHashMap = new HashMap();
/* 186 */     this.m_lastGlucoseHistoryPageReadMaps.put("522K", localHashMap);
/*     */ 
/* 188 */     localHashMap = new HashMap();
/* 189 */     this.m_lastGlucoseHistoryPageReadMaps.put("722", localHashMap);
/*     */ 
/* 191 */     localHashMap = new HashMap();
/* 192 */     this.m_lastGlucoseHistoryPageReadMaps.put("722K", localHashMap);
/*     */ 
/* 194 */     localHashMap = new HashMap();
/* 195 */     this.m_lastGlucoseHistoryPageReadMaps.put("523", localHashMap);
/*     */ 
/* 197 */     localHashMap = new HashMap();
/* 198 */     this.m_lastGlucoseHistoryPageReadMaps.put("523K", localHashMap);
/*     */ 
/* 200 */     localHashMap = new HashMap();
/* 201 */     this.m_lastGlucoseHistoryPageReadMaps.put("723", localHashMap);
/*     */ 
/* 203 */     localHashMap = new HashMap();
/* 204 */     this.m_lastGlucoseHistoryPageReadMaps.put("723K", localHashMap);
/*     */ 
/* 206 */     localHashMap = new HashMap();
/* 207 */     this.m_lastGlucoseHistoryPageReadMaps.put("553", localHashMap);
/*     */ 
/* 209 */     localHashMap = new HashMap();
/* 210 */     this.m_lastGlucoseHistoryPageReadMaps.put("753", localHashMap);
/*     */ 
/* 212 */     localHashMap = new HashMap();
/* 213 */     this.m_lastGlucoseHistoryPageReadMaps.put("554", localHashMap);
/*     */ 
/* 215 */     localHashMap = new HashMap();
/* 216 */     this.m_lastGlucoseHistoryPageReadMaps.put("754", localHashMap);
/*     */ 
/* 218 */     localHashMap = new HashMap();
/* 219 */     this.m_lastGlucoseHistoryPageReadMaps.put("7100", localHashMap);
/*     */ 
/* 221 */     localHashMap = new HashMap();
/* 222 */     this.m_lastGlucoseHistoryPageReadMaps.put("7100B", localHashMap);
/*     */ 
/* 224 */     localHashMap = new HashMap();
/* 225 */     this.m_lastGlucoseHistoryPageReadMaps.put("7100K", localHashMap);
/*     */ 
/* 227 */     localHashMap = new HashMap();
/* 228 */     this.m_lastGlucoseHistoryPageReadMaps.put("7200", localHashMap);
/*     */   }
/*     */ 
/*     */   public long getLastHistoryPageNumber(String paramString1, String paramString2)
/*     */   {
/* 265 */     Contract.pre(("515".equals(paramString1)) || ("715".equals(paramString1)) || ("522".equals(paramString1)) || ("522K".equals(paramString1)) || ("722".equals(paramString1)) || ("722K".equals(paramString1)) || ("523".equals(paramString1)) || ("523K".equals(paramString1)) || ("723".equals(paramString1)) || ("723K".equals(paramString1)) || ("553".equals(paramString1)) || ("753".equals(paramString1)) || ("554".equals(paramString1)) || ("754".equals(paramString1)) || ("7100".equals(paramString1)) || ("7100B".equals(paramString1)) || ("7100K".equals(paramString1)) || ("7200".equals(paramString1)));
/*     */ 
/* 283 */     Contract.preString(paramString2);
/*     */ 
/* 285 */     return getLastHistoryPageNumber(paramString1, paramString2, new LastHistoryPageReadCustomizer()
/*     */     {
/*     */       public Long getOverride()
/*     */       {
/* 294 */         return PumpOperationStep.this.getWizard().getConfig().getLastPumpHistoryPageReadOverride();
/*     */       }
/*     */ 
/*     */       public String getLogTerm()
/*     */       {
/* 303 */         return "pump";
/*     */       }
/*     */ 
/*     */       public Map getMap()
/*     */       {
/* 312 */         return PumpOperationStep.this.m_lastPumpHistoryPageReadMaps;
/*     */       }
/*     */ 
/*     */       public long getResult(String paramString1, String paramString2, String paramString3)
/*     */         throws IOException
/*     */       {
/* 326 */         return new NetHelper().getLastPumpHistoryPageRead(paramString1, paramString2, paramString3);
/*     */       }
/*     */     });
/*     */   }
/*     */ 
/*     */   public long getLastGlucoseHistoryPageNumber(String paramString1, String paramString2)
/*     */   {
/* 361 */     Contract.pre(("522".equals(paramString1)) || ("522K".equals(paramString1)) || ("722".equals(paramString1)) || ("722K".equals(paramString1)) || ("523".equals(paramString1)) || ("523K".equals(paramString1)) || ("723".equals(paramString1)) || ("723K".equals(paramString1)) || ("553".equals(paramString1)) || ("753".equals(paramString1)) || ("554".equals(paramString1)) || ("754".equals(paramString1)) || ("7100".equals(paramString1)) || ("7100B".equals(paramString1)) || ("7100K".equals(paramString1)) || ("7200".equals(paramString1)));
/*     */ 
/* 377 */     Contract.preString(paramString2);
/*     */ 
/* 379 */     return getLastHistoryPageNumber(paramString1, paramString2, new LastHistoryPageReadCustomizer()
/*     */     {
/*     */       public Long getOverride()
/*     */       {
/* 388 */         return PumpOperationStep.this.getWizard().getConfig().getLastGlucoseHistoryPageReadOverride();
/*     */       }
/*     */ 
/*     */       public String getLogTerm()
/*     */       {
/* 397 */         return "glucose";
/*     */       }
/*     */ 
/*     */       public Map getMap()
/*     */       {
/* 406 */         return PumpOperationStep.this.m_lastGlucoseHistoryPageReadMaps;
/*     */       }
/*     */ 
/*     */       public long getResult(String paramString1, String paramString2, String paramString3)
/*     */         throws IOException
/*     */       {
/* 420 */         return new NetHelper().getLastGlucoseHistoryPageRead(paramString1, paramString2, paramString3);
/*     */       }
/*     */     });
/*     */   }
/*     */ 
/*     */   protected String getPhaseNotification(int paramInt)
/*     */   {
/* 434 */     if (getWizard().isDeviceSelectionAPump()) {
/* 435 */       return this.m_pumpPhaseNotification[paramInt];
/*     */     }
/* 437 */     return this.m_monitorPhaseNotification[paramInt];
/*     */   }
/*     */ 
/*     */   protected String getPhaseElaboration(int paramInt)
/*     */   {
/* 448 */     if (getWizard().isDeviceSelectionAPump()) {
/* 449 */       return this.m_pumpPhaseElaboration[paramInt];
/*     */     }
/* 451 */     return this.m_monitorPhaseElaboration[paramInt];
/*     */   }
/*     */ 
/*     */   private long getLastHistoryPageNumber(String paramString1, String paramString2, LastHistoryPageReadCustomizer paramLastHistoryPageReadCustomizer)
/*     */   {
/* 467 */     LogWriter localLogWriter = getWizard().getConfig().getLogWriter();
/*     */     long l1;
/* 470 */     if ("7100B".equals(paramString1))
/*     */     {
/* 472 */       localLogWriter.logInfo("IGNORING last " + paramLastHistoryPageReadCustomizer.getLogTerm() + " history page for " + paramString1 + "-" + paramString2);
/*     */ 
/* 474 */       l1 = 0L;
/*     */     } else {
/* 476 */       Map localMap = (Map)paramLastHistoryPageReadCustomizer.getMap().get(paramString1);
/* 477 */       if (!localMap.containsKey(paramString2))
/*     */       {
/*     */         long l2;
/* 480 */         if (paramLastHistoryPageReadCustomizer.getOverride() != null) {
/* 481 */           localLogWriter.logInfo("Get last " + paramLastHistoryPageReadCustomizer.getLogTerm() + " history page for " + paramString1 + "-" + paramString2 + " from override.");
/*     */ 
/* 483 */           l2 = paramLastHistoryPageReadCustomizer.getOverride().longValue();
/*     */         } else {
/* 485 */           String str = getWizard().getConfig().getTransferRemoteURL();
/* 486 */           localLogWriter.logInfo("Get last " + paramLastHistoryPageReadCustomizer.getLogTerm() + " history page for " + paramString1 + "-" + paramString2 + " from server.");
/*     */           try
/*     */           {
/* 489 */             l2 = paramLastHistoryPageReadCustomizer.getResult(str, paramString1, paramString2);
/*     */           } catch (IOException localIOException) {
/* 491 */             localIOException.printStackTrace(localLogWriter);
/*     */ 
/* 495 */             l2 = 0L;
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/* 501 */         localMap.put(paramString2, new Long(l2));
/* 502 */         localLogWriter.logInfo("Cached last " + paramLastHistoryPageReadCustomizer.getLogTerm() + " history page for " + paramString1 + "-" + paramString2 + ": " + l2);
/*     */       }
/*     */ 
/* 506 */       l1 = ((Long)localMap.get(paramString2)).longValue();
/* 507 */       localLogWriter.logInfo("Got last " + paramLastHistoryPageReadCustomizer.getLogTerm() + " history page for " + paramString1 + "-" + paramString2 + ": " + l1);
/*     */     }
/*     */ 
/* 511 */     Contract.post(l1 >= 0L);
/* 512 */     return l1;
/*     */   }
/*     */ 
/*     */   private static abstract interface LastHistoryPageReadCustomizer
/*     */   {
/*     */     public abstract Long getOverride();
/*     */ 
/*     */     public abstract String getLogTerm();
/*     */ 
/*     */     public abstract Map getMap();
/*     */ 
/*     */     public abstract long getResult(String paramString1, String paramString2, String paramString3)
/*     */       throws IOException;
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.steps.mmparadigm.PumpOperationStep
 * JD-Core Version:    0.6.0
 */