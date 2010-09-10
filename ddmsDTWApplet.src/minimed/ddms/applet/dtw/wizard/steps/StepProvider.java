/*     */ package minimed.ddms.applet.dtw.wizard.steps;
/*     */ 
/*     */ import minimed.ddms.applet.dtw.wizard.Wizard;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardConfig;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardSelections;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardStepProvider;
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ public final class StepProvider
/*     */   implements WizardStepProvider
/*     */ {
/*     */   public Class getStartStep(Wizard paramWizard)
/*     */   {
/*  40 */     Contract.preNonNull(paramWizard);
/*  41 */     Class localClass = InitializationStep.class;
/*  42 */     Contract.postNonNull(localClass);
/*  43 */     return localClass;
/*     */   }
/*     */ 
/*     */   public Class getFinishStep(Wizard paramWizard)
/*     */   {
/*  55 */     Contract.preNonNull(paramWizard);
/*     */ 
/*  60 */     String str = paramWizard.getConfig().getWizardSelections().getDeviceSelection();
/*     */     Class localClass;
/*  62 */     if ((str.equals("wizard.selections.SELECTION_DEVICE_MMPARADIGM2")) || (str.equals("wizard.selections.SELECTION_DEVICE_MM511")) || (str.equals("wizard.selections.SELECTION_DEVICE_MMGUARDIAN3")))
/*     */     {
/*  65 */       localClass = minimed.ddms.applet.dtw.wizard.steps.mmparadigm.PumpOperationStep.class;
/*     */     }
/*  67 */     else if (str.equals("wizard.selections.SELECTION_DEVICE_MM508")) {
/*  68 */       localClass = minimed.ddms.applet.dtw.wizard.steps.mm508.PumpOperationStep.class;
/*     */     }
/*  70 */     else if ((str.equals("wizard.selections.SELECTION_DEVICE_MMLINKMETER")) || (str.equals("wizard.selections.SELECTION_DEVICE_MMLOGICMETER")) || (str.equals("wizard.selections.SELECTION_DEVICE_ASCENSIA_DEX_METER")) || (str.equals("wizard.selections.SELECTION_DEVICE_ASCENSIA_ELITE_METER")) || (str.equals("wizard.selections.SELECTION_DEVICE_ASCENSIA_BREEZE_METER")) || (str.equals("wizard.selections.SELECTION_DEVICE_ASCENSIA_CONTOUR_METER")) || (str.equals("wizard.selections.SELECTION_DEVICE_LIFESCAN_FASTTAKE_METER")) || (str.equals("wizard.selections.SELECTION_DEVICE_LIFESCAN_PROFILE_METER")) || (str.equals("wizard.selections.SELECTION_DEVICE_LIFESCAN_SURESTEP_METER")) || (str.equals("wizard.selections.SELECTION_DEVICE_LIFESCAN_ULTRA_METER")) || (str.equals("wizard.selections.SELECTION_DEVICE_LIFESCAN_ULTRAMINI_METER")) || (str.equals("wizard.selections.SELECTION_DEVICE_LIFESCAN_ULTRASMART_METER")) || (str.equals("wizard.selections.SELECTION_DEVICE_LIFESCAN_BASIC_METER")) || (str.equals("wizard.selections.SELECTION_DEVICE_ROCHE_ACTIVE_METER")) || (str.equals("wizard.selections.SELECTION_DEVICE_ROCHE_AVIVA_METER")) || (str.equals("wizard.selections.SELECTION_DEVICE_ROCHE_COMPACT_METER")) || (str.equals("wizard.selections.SELECTION_DEVICE_ROCHE_COMPACTPLUS_METER")) || (str.equals("wizard.selections.SELECTION_DEVICE_MEDISENSE_XTRA_METER")) || (str.equals("wizard.selections.SELECTION_DEVICE_THERASENSE_FREESTYLE_METER")))
/*     */     {
/* 108 */       localClass = MeterOperationStep.class;
/*     */     }
/*     */     else {
/* 111 */       localClass = null;
/*     */     }
/*     */ 
/* 116 */     Contract.postNonNull(localClass);
/* 117 */     return localClass;
/*     */   }
/*     */ 
/*     */   public Class getUnrecoverableErrorStep(Wizard paramWizard)
/*     */   {
/* 129 */     Contract.preNonNull(paramWizard);
/* 130 */     Class localClass = UnrecoverableErrorStep.class;
/* 131 */     Contract.postNonNull(localClass);
/* 132 */     return localClass;
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.steps.StepProvider
 * JD-Core Version:    0.6.0
 */