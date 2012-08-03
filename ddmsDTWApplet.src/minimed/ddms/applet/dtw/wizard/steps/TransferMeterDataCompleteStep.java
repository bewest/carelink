/*    */ package minimed.ddms.applet.dtw.wizard.steps;
/*    */ 
/*    */ import java.util.ResourceBundle;
/*    */ import minimed.ddms.applet.dtw.wizard.Wizard;
/*    */ 
/*    */ public class TransferMeterDataCompleteStep extends TransferDataCompleteStep
/*    */ {
/*    */   public TransferMeterDataCompleteStep(Wizard paramWizard)
/*    */   {
/* 42 */     super(paramWizard, "wizard.read.icon", null);
/*    */   }
/*    */ 
/*    */   protected String getBanner()
/*    */   {
/* 53 */     return this.m_resources.getString("wizard.transfercomplete.title");
/*    */   }
/*    */ 
/*    */   protected String getDialogText(Wizard paramWizard)
/*    */   {
/* 64 */     return this.m_resources.getString("wizard.transfercomplete.meter.dialog");
/*    */   }
/*    */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.steps.TransferMeterDataCompleteStep
 * JD-Core Version:    0.6.0
 */