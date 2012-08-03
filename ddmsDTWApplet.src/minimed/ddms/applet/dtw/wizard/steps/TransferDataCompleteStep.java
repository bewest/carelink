/*     */ package minimed.ddms.applet.dtw.wizard.steps;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import minimed.ddms.applet.dtw.MessageHelper;
/*     */ import minimed.ddms.applet.dtw.wizard.Wizard;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardConfig;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardSelections;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardStep;
/*     */ 
/*     */ public abstract class TransferDataCompleteStep extends WizardStep
/*     */ {
/*     */   private static final String GUEST_USER = "guest";
/*     */   private JLabel m_dialogLabel;
/*     */ 
/*     */   public TransferDataCompleteStep(Wizard paramWizard, String paramString, Class paramClass)
/*     */   {
/*  50 */     super(paramWizard, paramClass);
/*     */ 
/*  53 */     getLeftBannerLabel().setText("<html>" + getBanner() + "</html>");
/*  54 */     Object localObject = new ImageIcon(getImage(paramString));
/*  55 */     getRightBannerLabel().setIcon((Icon)localObject);
/*     */ 
/*  58 */     localObject = getInfoIcon();
/*  59 */     getTopImageLabel().setIcon((Icon)localObject);
/*     */ 
/*  62 */     JPanel localJPanel = getContentArea();
/*  63 */     localJPanel.setLayout(new BorderLayout());
/*     */     String str1;
/*  67 */     if (displayDisconnectParadigmLinkMessage())
/*  68 */       str1 = this.m_resources.getString("wizard.transfercomplete.disconnect");
/*     */     else {
/*  70 */       str1 = "";
/*     */     }
/*     */ 
/*  73 */     String str2 = getWizard().getConfig().getUserName();
/*  74 */     if (displayGuestMessage(str2)) {
/*  75 */       str1 = str1 + this.m_resources.getString("wizard.transfercomplete.guestmessage");
/*     */     }
/*     */ 
/*  78 */     String str3 = MessageHelper.format(this.m_resources.getString("wizard.transfercomplete.dialogtext"), new Object[] { getDialogText(paramWizard), str1 });
/*     */ 
/*  82 */     this.m_dialogLabel = new JLabel(str3);
/*  83 */     this.m_dialogLabel.setHorizontalAlignment(0);
/*  84 */     localJPanel.add(this.m_dialogLabel);
/*     */   }
/*     */ 
/*     */   protected void stepShown()
/*     */   {
/*  94 */     super.stepShown();
/*     */ 
/*  97 */     getFinishButton().setEnabled(false);
/*  98 */     getBackButton().setEnabled(false);
/*  99 */     getNextButton().setEnabled(false);
/* 100 */     getCancelButton().setEnabled(false);
/*     */   }
/*     */ 
/*     */   protected abstract String getBanner();
/*     */ 
/*     */   protected abstract String getDialogText(Wizard paramWizard);
/*     */ 
/*     */   private boolean displayDisconnectParadigmLinkMessage()
/*     */   {
/* 125 */     WizardSelections localWizardSelections = getWizardSelections();
/*     */     int i;
/* 136 */     if ("wizard.selections.SELECTION_DEVICE_MMLINKMETER".equals(localWizardSelections.getDeviceSelection()))
/*     */     {
/* 139 */       i = 1;
/* 140 */     } else if (((localWizardSelections.isDeviceSelectionACGM()) || (localWizardSelections.isDeviceSelectionAPump())) && ("wizard.selections.SELECTION_LINK_DEVICE_PARADIGMLINK".equals(localWizardSelections.getLinkDevice())))
/*     */     {
/* 144 */       i = 1;
/*     */     }
/* 146 */     else i = 0;
/*     */ 
/* 149 */     return i;
/*     */   }
/*     */ 
/*     */   private boolean displayGuestMessage(String paramString)
/*     */   {
/* 159 */     return "guest".equals(paramString);
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.steps.TransferDataCompleteStep
 * JD-Core Version:    0.6.0
 */