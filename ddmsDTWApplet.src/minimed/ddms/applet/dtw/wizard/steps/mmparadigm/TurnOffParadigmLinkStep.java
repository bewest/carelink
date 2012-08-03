/*    */ package minimed.ddms.applet.dtw.wizard.steps.mmparadigm;
/*    */ 
/*    */ import java.awt.GridBagConstraints;
/*    */ import java.awt.GridBagLayout;
/*    */ import java.awt.Insets;
/*    */ import java.util.ResourceBundle;
/*    */ import javax.swing.Icon;
/*    */ import javax.swing.ImageIcon;
/*    */ import javax.swing.JLabel;
/*    */ import javax.swing.JPanel;
/*    */ import minimed.ddms.applet.dtw.wizard.Wizard;
/*    */ import minimed.ddms.applet.dtw.wizard.WizardConfig;
/*    */ import minimed.ddms.applet.dtw.wizard.WizardSelections;
/*    */ import minimed.ddms.applet.dtw.wizard.WizardStep;
/*    */ import minimed.util.OSInfo;
/*    */ 
/*    */ public class TurnOffParadigmLinkStep extends WizardStep
/*    */ {
/*    */   private static final int INSET_LEFT = 20;
/*    */ 
/*    */   public TurnOffParadigmLinkStep(Wizard paramWizard)
/*    */   {
/* 48 */     super(paramWizard, null);
/*    */ 
/* 51 */     getLeftBannerLabel().setText(this.m_resources.getString("wizard.paradigm.off"));
/* 52 */     Object localObject = new ImageIcon(getImage("wizard.read.icon"));
/* 53 */     getRightBannerLabel().setIcon((Icon)localObject);
/*    */ 
/* 56 */     localObject = getWarningIcon();
/* 57 */     getTopImageLabel().setIcon((Icon)localObject);
/*    */ 
/* 60 */     JPanel localJPanel = getContentArea();
/* 61 */     localJPanel.setLayout(new GridBagLayout());
/* 62 */     String str = this.m_resources.getString("wizard.paradigm.off.expanded");
/* 63 */     GridBagConstraints localGridBagConstraints = new GridBagConstraints();
/* 64 */     localGridBagConstraints.gridx = 1;
/* 65 */     localGridBagConstraints.gridy = 0;
/* 66 */     localGridBagConstraints.insets = new Insets(0, 20, 100, 0);
/* 67 */     localJPanel.add(new JLabel(str), localGridBagConstraints);
/*    */ 
/* 70 */     localGridBagConstraints = new GridBagConstraints();
/* 71 */     localGridBagConstraints.gridx = 0;
/* 72 */     localGridBagConstraints.gridy = 0;
/* 73 */     localGridBagConstraints.anchor = 10;
/* 74 */     localJPanel.add(new JLabel(new ImageIcon(getImage("wizard.paradigmlinkoff.pic"))), localGridBagConstraints);
/*    */   }
/*    */ 
/*    */   protected Class getNextClass()
/*    */   {
/* 86 */     Object localObject = SelectConnectionTypeStep.class;
/* 87 */     if ((getWizard().getConfig().isWindowsNT()) || (OSInfo.isMac()))
/*    */     {
/* 89 */       getWizardSelections().setConnectionType("wizard.selections.SELECTION_CONN_TYPE_SERIAL");
/*    */ 
/* 91 */       localObject = PumpSelectSerialPortStep.class;
/*    */     }
/* 93 */     return (Class)localObject;
/*    */   }
/*    */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.steps.mmparadigm.TurnOffParadigmLinkStep
 * JD-Core Version:    0.6.0
 */