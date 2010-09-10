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
/*    */ 
/*    */ public class TurnOffParadigmLinkStep extends WizardStep
/*    */ {
/*    */   private static final int INSET_LEFT = 20;
/*    */ 
/*    */   public TurnOffParadigmLinkStep(Wizard paramWizard)
/*    */   {
/* 47 */     super(paramWizard, null);
/*    */ 
/* 50 */     getLeftBannerLabel().setText(this.m_resources.getString("wizard.paradigm.off"));
/* 51 */     Object localObject = new ImageIcon(getImage("wizard.read.icon"));
/* 52 */     getRightBannerLabel().setIcon((Icon)localObject);
/*    */ 
/* 55 */     localObject = getWarningIcon();
/* 56 */     getTopImageLabel().setIcon((Icon)localObject);
/*    */ 
/* 59 */     JPanel localJPanel = getContentArea();
/* 60 */     localJPanel.setLayout(new GridBagLayout());
/* 61 */     String str = this.m_resources.getString("wizard.paradigm.off.expanded");
/* 62 */     GridBagConstraints localGridBagConstraints = new GridBagConstraints();
/* 63 */     localGridBagConstraints.gridx = 1;
/* 64 */     localGridBagConstraints.gridy = 0;
/* 65 */     localGridBagConstraints.insets = new Insets(0, 20, 100, 0);
/* 66 */     localJPanel.add(new JLabel(str), localGridBagConstraints);
/*    */ 
/* 69 */     localGridBagConstraints = new GridBagConstraints();
/* 70 */     localGridBagConstraints.gridx = 0;
/* 71 */     localGridBagConstraints.gridy = 0;
/* 72 */     localGridBagConstraints.anchor = 10;
/* 73 */     localJPanel.add(new JLabel(new ImageIcon(getImage("wizard.paradigmlinkoff.pic"))), localGridBagConstraints);
/*    */   }
/*    */ 
/*    */   protected Class getNextClass()
/*    */   {
/* 85 */     Class localClass = SelectConnectionTypeStep.class;
/* 86 */     if (getWizard().getConfig().isWindowsNT())
/*    */     {
/* 88 */       getWizardSelections().setConnectionType("wizard.selections.SELECTION_CONN_TYPE_SERIAL");
/*    */ 
/* 90 */       localClass = PumpSelectSerialPortStep.class;
/*    */     }
/* 92 */     return localClass;
/*    */   }
/*    */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.steps.mmparadigm.TurnOffParadigmLinkStep
 * JD-Core Version:    0.6.0
 */