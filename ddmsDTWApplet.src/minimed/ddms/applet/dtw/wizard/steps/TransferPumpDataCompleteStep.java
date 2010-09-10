/*    */ package minimed.ddms.applet.dtw.wizard.steps;
/*    */ 
/*    */ import java.util.ResourceBundle;
/*    */ import minimed.ddms.applet.dtw.wizard.Wizard;
/*    */ 
/*    */ public class TransferPumpDataCompleteStep extends TransferDataCompleteStep
/*    */ {
/*    */   public TransferPumpDataCompleteStep(Wizard paramWizard)
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
/*    */     String str;
/* 65 */     if (paramWizard.isDeviceSelectionAPump()) {
/* 66 */       str = this.m_resources.getString("wizard.transfercomplete.pump.dialog");
/*    */     }
/*    */     else {
/* 69 */       str = this.m_resources.getString("wizard.transfercomplete.monitor.dialog");
/*    */     }
/*    */ 
/* 72 */     return str;
/*    */   }
/*    */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.steps.TransferPumpDataCompleteStep
 * JD-Core Version:    0.6.0
 */