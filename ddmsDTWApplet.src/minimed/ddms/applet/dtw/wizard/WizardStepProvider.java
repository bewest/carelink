package minimed.ddms.applet.dtw.wizard;

public abstract interface WizardStepProvider
{
  public abstract Class getStartStep(Wizard paramWizard);

  public abstract Class getFinishStep(Wizard paramWizard);

  public abstract Class getTransferStep(Wizard paramWizard);

  public abstract Class getUnrecoverableErrorStep(Wizard paramWizard);
}

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.WizardStepProvider
 * JD-Core Version:    0.6.0
 */