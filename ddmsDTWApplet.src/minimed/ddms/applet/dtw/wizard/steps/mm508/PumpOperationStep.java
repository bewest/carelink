/*     */ package minimed.ddms.applet.dtw.wizard.steps.mm508;
/*     */ 
/*     */ import java.util.ResourceBundle;
/*     */ import minimed.ddms.applet.dtw.wizard.Wizard;
/*     */ import minimed.ddms.applet.dtw.wizard.steps.DeviceOperationStep;
/*     */ import minimed.ddms.applet.dtw.wizard.steps.TransferDataCheckStep;
/*     */ 
/*     */ public class PumpOperationStep extends DeviceOperationStep
/*     */ {
/*  37 */   private final String[] m_phaseNotification = { this.m_resources.getString("wizard.operation.pump508.phase0.notification"), this.m_resources.getString("wizard.operation.pump508.phase1.notification"), this.m_resources.getString("wizard.operation.pump508.phase2.notification"), this.m_resources.getString("wizard.operation.pump508.phase3.notification"), this.m_resources.getString("wizard.operation.pump508.phase4.notification"), this.m_resources.getString("wizard.operation.pump508.phase5.notification"), this.m_resources.getString("wizard.operation.pump508.phase6.notification"), this.m_resources.getString("wizard.operation.pump508.phase7.notification") };
/*     */ 
/*  58 */   private final String[] m_phaseElaboration = { this.m_resources.getString("wizard.operation.pump508.phase0.elaboration"), this.m_resources.getString("wizard.operation.pump508.phase1.elaboration"), this.m_resources.getString("wizard.operation.pump508.phase2.elaboration"), this.m_resources.getString("wizard.operation.pump508.phase3.elaboration"), this.m_resources.getString("wizard.operation.pump508.phase4.elaboration"), this.m_resources.getString("wizard.operation.pump508.phase5.elaboration"), this.m_resources.getString("wizard.operation.pump508.phase6.elaboration"), this.m_resources.getString("wizard.operation.pump508.phase7.elaboration") };
/*     */ 
/*     */   public PumpOperationStep(Wizard paramWizard)
/*     */   {
/*  85 */     super(paramWizard, "wizard.read.icon", TransferDataCheckStep.class, PumpOperationFailStep.class, "wizard.operation.read.title");
/*     */   }
/*     */ 
/*     */   protected String getPhaseNotification(int paramInt)
/*     */   {
/*  99 */     return this.m_phaseNotification[paramInt];
/*     */   }
/*     */ 
/*     */   protected String getPhaseElaboration(int paramInt)
/*     */   {
/* 110 */     return this.m_phaseElaboration[paramInt];
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.steps.mm508.PumpOperationStep
 * JD-Core Version:    0.6.0
 */