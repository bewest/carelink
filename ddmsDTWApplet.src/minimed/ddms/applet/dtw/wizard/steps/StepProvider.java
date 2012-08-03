/*     */ package minimed.ddms.applet.dtw.wizard.steps;
/*     */ 
/*     */ import minimed.ddms.applet.dtw.wizard.Wizard;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardConfig;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardSelections;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardStepProvider;
/*     */ import minimed.ddms.applet.dtw.wizard.steps.mmparadigm.PumpOperationStep;
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ public final class StepProvider
/*     */   implements WizardStepProvider
/*     */ {
/*     */   public Class getStartStep(Wizard paramWizard)
/*     */   {
/*  40 */     Contract.preNonNull(paramWizard);
/*  41 */     InitializationStep localInitializationStep = InitializationStep.class;
/*  42 */     Contract.postNonNull(localInitializationStep);
/*  43 */     return localInitializationStep;
/*     */   }
/*     */ 
/*     */   public Class getTransferStep(Wizard paramWizard)
/*     */   {
/*  55 */     return TransferDataStep.class;
/*     */   }
/*     */ 
/*     */   public Class getFinishStep(Wizard paramWizard)
/*     */   {
/*  67 */     Contract.preNonNull(paramWizard);
/*     */ 
/*  72 */     String str = paramWizard.getConfig().getWizardSelections().getDeviceSelection();
/*     */     Object localObject;
/*  74 */     if ((str.equals("wizard.selections.SELECTION_DEVICE_MMPARADIGM2")) || (str.equals("wizard.selections.SELECTION_DEVICE_MMGUARDIAN3")))
/*     */     {
/*  76 */       localObject = PumpOperationStep.class;
/*     */     }
/*  78 */     else if ((str.equals("wizard.selections.SELECTION_DEVICE_MMLINKMETER")) || (str.equals("wizard.selections.SELECTION_DEVICE_MMLOGICMETER")) || (str.equals("wizard.selections.SELECTION_DEVICE_ASCENSIA_DEX_METER")) || (str.equals("wizard.selections.SELECTION_DEVICE_ASCENSIA_ELITE_METER")) || (str.equals("wizard.selections.SELECTION_DEVICE_ASCENSIA_BREEZE_METER")) || (str.equals("wizard.selections.SELECTION_DEVICE_ASCENSIA_CONTOUR_METER")) || (str.equals("wizard.selections.SELECTION_DEVICE_ASCENSIA_CONTOUR_USB_METER")) || (str.equals("wizard.selections.SELECTION_DEVICE_XTLINKMETER")) || (str.equals("wizard.selections.SELECTION_DEVICE_LIFESCAN_FASTTAKE_METER")) || (str.equals("wizard.selections.SELECTION_DEVICE_LIFESCAN_PROFILE_METER")) || (str.equals("wizard.selections.SELECTION_DEVICE_LIFESCAN_SURESTEP_METER")) || (str.equals("wizard.selections.SELECTION_DEVICE_LIFESCAN_ULTRA_METER")) || (str.equals("wizard.selections.SELECTION_DEVICE_LIFESCAN_ULTRAMINI_METER")) || (str.equals("wizard.selections.SELECTION_DEVICE_LIFESCAN_ULTRASMART_METER")) || (str.equals("wizard.selections.SELECTION_DEVICE_LIFESCAN_BASIC_METER")) || (str.equals("wizard.selections.SELECTION_DEVICE_ROCHE_ACTIVE_METER")) || (str.equals("wizard.selections.SELECTION_DEVICE_ROCHE_AVIVA_METER")) || (str.equals("wizard.selections.SELECTION_DEVICE_ROCHE_COMPACT_METER")) || (str.equals("wizard.selections.SELECTION_DEVICE_ROCHE_COMPACTPLUS_METER")) || (str.equals("wizard.selections.SELECTION_DEVICE_MEDISENSE_XTRA_METER")) || (str.equals("wizard.selections.SELECTION_DEVICE_THERASENSE_FREESTYLE_METER")))
/*     */     {
/* 120 */       localObject = MeterOperationStep.class;
/*     */     }
/*     */     else {
/* 123 */       localObject = null;
/*     */     }
/*     */ 
/* 128 */     Contract.postNonNull(localObject);
/* 129 */     return (Class)localObject;
/*     */   }
/*     */ 
/*     */   public Class getUnrecoverableErrorStep(Wizard paramWizard)
/*     */   {
/* 141 */     Contract.preNonNull(paramWizard);
/* 142 */     UnrecoverableErrorStep localUnrecoverableErrorStep = UnrecoverableErrorStep.class;
/* 143 */     Contract.postNonNull(localUnrecoverableErrorStep);
/* 144 */     return localUnrecoverableErrorStep;
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.steps.StepProvider
 * JD-Core Version:    0.6.0
 */