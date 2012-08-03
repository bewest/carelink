/*     */ package minimed.ddms.applet.dtw.wizard.steps.mmparadigm;
/*     */ 
/*     */ import java.util.ResourceBundle;
/*     */ import minimed.ddms.applet.dtw.wizard.Wizard;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardSelections;
/*     */ import minimed.ddms.applet.dtw.wizard.steps.DeviceOperationFailStep;
/*     */ 
/*     */ public class PumpOperationFailStep extends DeviceOperationFailStep
/*     */ {
/*  38 */   private final String[] m_pumpErrorMessages = { this.m_resources.getString("wizard.opfail.pumpparadigm.error0"), this.m_resources.getString("wizard.opfail.pumpparadigm.error1"), this.m_resources.getString("wizard.opfail.pumpparadigm.error2"), this.m_resources.getString("wizard.opfail.pumpparadigm.error3"), this.m_resources.getString("wizard.opfail.pumpparadigm.error4"), this.m_resources.getString("wizard.opfail.pumpparadigm.error5"), this.m_resources.getString("wizard.opfail.pumpparadigm.error6"), this.m_resources.getString("wizard.opfail.pumpparadigm.error7"), this.m_resources.getString("wizard.opfail.pumpparadigm.error8"), this.m_resources.getString("wizard.opfail.pumpparadigm.error9"), this.m_resources.getString("wizard.opfail.pumpparadigm.error10"), this.m_resources.getString("wizard.opfail.pumpparadigm.error11"), this.m_resources.getString("wizard.opfail.meter.error11") };
/*     */ 
/*  88 */   private final String[] m_monitorErrorMessages = { this.m_resources.getString("wizard.opfail.pumpparadigm.error0"), this.m_resources.getString("wizard.opfail.pumpparadigm.error1"), this.m_resources.getString("wizard.opfail.pumpparadigm.error2"), this.m_resources.getString("wizard.opfail.pumpparadigm.error3.cgm"), this.m_resources.getString("wizard.opfail.pumpparadigm.error4.cgm"), this.m_resources.getString("wizard.opfail.pumpparadigm.error5.cgm"), this.m_resources.getString("wizard.opfail.pumpparadigm.error6"), this.m_resources.getString("wizard.opfail.pumpparadigm.error7.cgm"), this.m_resources.getString("wizard.opfail.pumpparadigm.error8.cgm"), this.m_resources.getString("wizard.opfail.pumpparadigm.error9"), this.m_resources.getString("wizard.opfail.pumpparadigm.error10"), this.m_resources.getString("wizard.opfail.pumpparadigm.error11"), this.m_resources.getString("wizard.opfail.meter.error11") };
/*     */ 
/* 138 */   private final String[] m_pumpSuggestionMessages = { this.m_resources.getString("wizard.opfail.pumpparadigm.suggestion0"), this.m_resources.getString("wizard.opfail.pumpparadigm.suggestion1"), this.m_resources.getString("wizard.opfail.pumpparadigm.suggestion2"), this.m_resources.getString("wizard.opfail.pumpparadigm.suggestion3"), this.m_resources.getString("wizard.opfail.pumpparadigm.suggestion4"), this.m_resources.getString("wizard.opfail.pumpparadigm.suggestion5"), this.m_resources.getString("wizard.opfail.pumpparadigm.suggestion6"), this.m_resources.getString("wizard.opfail.pumpparadigm.suggestion7"), this.m_resources.getString("wizard.opfail.pumpparadigm.suggestion8"), this.m_resources.getString("wizard.opfail.pumpparadigm.suggestion9"), this.m_resources.getString("wizard.opfail.pumpparadigm.suggestion10"), this.m_resources.getString("wizard.opfail.pumpparadigm.suggestion11"), this.m_resources.getString("wizard.opfail.meter.suggestion11") };
/*     */ 
/* 188 */   private final String[] m_monitorSuggestionMessages = { this.m_resources.getString("wizard.opfail.pumpparadigm.suggestion0"), this.m_resources.getString("wizard.opfail.pumpparadigm.suggestion1"), this.m_resources.getString("wizard.opfail.pumpparadigm.suggestion2"), this.m_resources.getString("wizard.opfail.pumpparadigm.suggestion3.cgm"), this.m_resources.getString("wizard.opfail.pumpparadigm.suggestion4.cgm"), this.m_resources.getString("wizard.opfail.pumpparadigm.suggestion5.cgm"), this.m_resources.getString("wizard.opfail.pumpparadigm.suggestion6"), this.m_resources.getString("wizard.opfail.pumpparadigm.suggestion7.cgm"), this.m_resources.getString("wizard.opfail.pumpparadigm.suggestion8.cgm"), this.m_resources.getString("wizard.opfail.pumpparadigm.suggestion9"), this.m_resources.getString("wizard.opfail.pumpparadigm.suggestion10.cgm"), this.m_resources.getString("wizard.opfail.pumpparadigm.suggestion11.cgm"), this.m_resources.getString("wizard.opfail.meter.suggestion11") };
/*     */ 
/*     */   public PumpOperationFailStep(Wizard paramWizard)
/*     */   {
/* 243 */     super(paramWizard, "wizard.readfail.icon", null, "wizard.operation.read.title");
/*     */   }
/*     */ 
/*     */   public String[] getErrorMessages()
/*     */   {
/* 255 */     if (getWizardSelections().isDeviceSelectionAPump()) {
/* 256 */       return this.m_pumpErrorMessages;
/*     */     }
/* 258 */     return this.m_monitorErrorMessages;
/*     */   }
/*     */ 
/*     */   public String[] getSuggestionMessages()
/*     */   {
/* 268 */     if (getWizardSelections().isDeviceSelectionAPump()) {
/* 269 */       return this.m_pumpSuggestionMessages;
/*     */     }
/* 271 */     return this.m_monitorSuggestionMessages;
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.steps.mmparadigm.PumpOperationFailStep
 * JD-Core Version:    0.6.0
 */