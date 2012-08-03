/*     */ package minimed.ddms.applet.dtw.wizard.steps;
/*     */ 
/*     */ import java.util.ResourceBundle;
/*     */ import minimed.ddms.applet.dtw.wizard.Wizard;
/*     */ 
/*     */ public class MeterOperationStep extends DeviceOperationStep
/*     */ {
/*  36 */   private final String[] m_phaseNotification = { this.m_resources.getString("wizard.operation.meter.phase0.notification"), this.m_resources.getString("wizard.operation.meter.phase1.notification"), this.m_resources.getString("wizard.operation.meter.phase2.notification"), this.m_resources.getString("wizard.operation.meter.phase3.notification"), this.m_resources.getString("wizard.operation.meter.phase4.notification"), this.m_resources.getString("wizard.operation.meter.phase5.notification"), this.m_resources.getString("wizard.operation.meter.phase6.notification"), this.m_resources.getString("wizard.operation.meter.phase7.notification") };
/*     */ 
/*  57 */   private final String[] m_phaseElaboration = { this.m_resources.getString("wizard.operation.meter.phase0.elaboration"), this.m_resources.getString("wizard.operation.meter.phase1.elaboration"), this.m_resources.getString("wizard.operation.meter.phase2.elaboration"), this.m_resources.getString("wizard.operation.meter.phase3.elaboration"), this.m_resources.getString("wizard.operation.meter.phase4.elaboration"), this.m_resources.getString("wizard.operation.meter.phase5.elaboration"), this.m_resources.getString("wizard.operation.meter.phase6.elaboration"), this.m_resources.getString("wizard.operation.meter.phase7.elaboration") };
/*     */ 
/*     */   public MeterOperationStep(Wizard paramWizard)
/*     */   {
/*  84 */     super(paramWizard, "wizard.read.icon", TransferDataCheckStep.class, MeterOperationFailStep.class, "wizard.operation.read.title");
/*     */   }
/*     */ 
/*     */   protected String getPhaseNotification(int paramInt)
/*     */   {
/*  98 */     return this.m_phaseNotification[paramInt];
/*     */   }
/*     */ 
/*     */   protected String getPhaseElaboration(int paramInt)
/*     */   {
/* 109 */     return this.m_phaseElaboration[paramInt];
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.steps.MeterOperationStep
 * JD-Core Version:    0.6.0
 */