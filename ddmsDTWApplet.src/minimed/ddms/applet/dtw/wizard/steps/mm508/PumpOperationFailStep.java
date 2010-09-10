/*     */ package minimed.ddms.applet.dtw.wizard.steps.mm508;
/*     */ 
/*     */ import java.util.ResourceBundle;
/*     */ import minimed.ddms.applet.dtw.wizard.Wizard;
/*     */ import minimed.ddms.applet.dtw.wizard.steps.DeviceOperationFailStep;
/*     */ 
/*     */ public class PumpOperationFailStep extends DeviceOperationFailStep
/*     */ {
/*  38 */   private final String[] m_errorMessages = { this.m_resources.getString("wizard.opfail.pump508.error0"), this.m_resources.getString("wizard.opfail.pump508.error1"), this.m_resources.getString("wizard.opfail.pump508.error2"), this.m_resources.getString("wizard.opfail.pump508.error3"), this.m_resources.getString("wizard.opfail.pump508.error4"), this.m_resources.getString("wizard.opfail.pump508.error5"), this.m_resources.getString("wizard.opfail.pump508.error6"), this.m_resources.getString("wizard.opfail.pump508.error7"), this.m_resources.getString("wizard.opfail.pump508.error8"), this.m_resources.getString("wizard.opfail.pump508.error9"), this.m_resources.getString("wizard.opfail.pump508.error10") };
/*     */ 
/*  81 */   private final String[] m_suggestionMessages = { this.m_resources.getString("wizard.opfail.pump508.suggestion0"), this.m_resources.getString("wizard.opfail.pump508.suggestion1"), this.m_resources.getString("wizard.opfail.pump508.suggestion2"), this.m_resources.getString("wizard.opfail.pump508.suggestion3"), this.m_resources.getString("wizard.opfail.pump508.suggestion4"), this.m_resources.getString("wizard.opfail.pump508.suggestion5"), this.m_resources.getString("wizard.opfail.pump508.suggestion6"), this.m_resources.getString("wizard.opfail.pump508.suggestion7"), this.m_resources.getString("wizard.opfail.pump508.suggestion8"), this.m_resources.getString("wizard.opfail.pump508.suggestion9"), this.m_resources.getString("wizard.opfail.pump508.suggestion10") };
/*     */ 
/*     */   public PumpOperationFailStep(Wizard paramWizard)
/*     */   {
/* 128 */     super(paramWizard, "wizard.readfail.icon", null, "wizard.operation.read.title");
/*     */   }
/*     */ 
/*     */   protected String[] getErrorMessages()
/*     */   {
/* 140 */     return this.m_errorMessages;
/*     */   }
/*     */ 
/*     */   protected String[] getSuggestionMessages()
/*     */   {
/* 150 */     return this.m_suggestionMessages;
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.steps.mm508.PumpOperationFailStep
 * JD-Core Version:    0.6.0
 */