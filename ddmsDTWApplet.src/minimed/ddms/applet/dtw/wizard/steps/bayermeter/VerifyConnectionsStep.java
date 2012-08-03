/*     */ package minimed.ddms.applet.dtw.wizard.steps.bayermeter;
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
/*     */ import minimed.ddms.applet.dtw.wizard.WizardSelections;
/*     */ import minimed.ddms.applet.dtw.wizard.WizardStep;
/*     */ import minimed.ddms.applet.dtw.wizard.steps.USBDriverPostInstallStep;
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ public class VerifyConnectionsStep extends WizardStep
/*     */ {
/*     */   private final JLabel m_labelMainText;
/*     */ 
/*     */   public VerifyConnectionsStep(Wizard paramWizard)
/*     */   {
/*  48 */     super(paramWizard, null);
/*     */ 
/*  50 */     getLeftBannerLabel().setText(this.m_resources.getString("wizard.device.verify_connections"));
/*  51 */     Object localObject = new ImageIcon(getImage("wizard.read.icon"));
/*  52 */     getRightBannerLabel().setIcon((Icon)localObject);
/*     */ 
/*  55 */     localObject = getWarningIcon();
/*  56 */     getTopImageLabel().setIcon((Icon)localObject);
/*     */ 
/*  59 */     this.m_labelMainText = new JLabel();
/*  60 */     this.m_labelMainText.setHorizontalAlignment(0);
/*     */ 
/*  63 */     JPanel localJPanel = getContentArea();
/*  64 */     localJPanel.setLayout(new BorderLayout());
/*  65 */     localJPanel.add(this.m_labelMainText, "Center");
/*     */   }
/*     */ 
/*     */   protected void stepShown()
/*     */   {
/*  75 */     super.stepShown();
/*     */ 
/*  79 */     getNextButton().setEnabled(false);
/*  80 */     enableWithFocus(getFinishButton());
/*  81 */     updateContent();
/*     */   }
/*     */ 
/*     */   protected void finishRequest()
/*     */   {
/*  89 */     getWizard().setUserInputCompleted();
/*  90 */     super.finishRequest();
/*     */   }
/*     */ 
/*     */   protected void updateContent()
/*     */   {
/*  97 */     String str1 = getWizardSelections().getMeterDevice();
/*  98 */     String str2 = getWizardSelections().getMeterBrand();
/*     */     String str4;
/*     */     String str3;
/* 101 */     if (str2.equals("wizard.selections.SELECTION_BRAND_MINIMED_BD")) {
/* 102 */       if (str1.equals("wizard.selections.SELECTION_DEVICE_XTLINKMETER"))
/*     */       {
/* 104 */         str4 = "wizard.ascensia.contour_xtlink.ul_instructions";
/* 105 */         str3 = getWizardSelections().m_selectionDeviceAscensiaContourXTLink;
/*     */       } else {
/* 107 */         str4 = "wizard.ascensia.contour_link.ul_instructions";
/* 108 */         str3 = getWizardSelections().m_selectionDeviceAscensiaContourLink;
/*     */       }
/*     */     } else {
/* 111 */       str3 = formatMeterDevice(str1).replaceAll("<br>", " / ");
/*     */ 
/* 113 */       if (str1.equals("wizard.selections.SELECTION_DEVICE_ASCENSIA_DEX_METER")) {
/* 114 */         str4 = "wizard.ascensia.dex.ul_instructions";
/* 115 */       } else if (str1.equals("wizard.selections.SELECTION_DEVICE_ASCENSIA_ELITE_METER"))
/*     */       {
/* 117 */         str4 = "wizard.ascensia.elite.ul_instructions";
/* 118 */       } else if (str1.equals("wizard.selections.SELECTION_DEVICE_ASCENSIA_BREEZE_METER"))
/*     */       {
/* 120 */         str4 = "wizard.ascensia.breeze_or_contour.ul_instructions";
/* 121 */       } else if (str1.equals("wizard.selections.SELECTION_DEVICE_ASCENSIA_CONTOUR_METER"))
/*     */       {
/* 123 */         str4 = "wizard.ascensia.contour_link.ul_instructions";
/* 124 */       } else if (str1.equals("wizard.selections.SELECTION_DEVICE_ASCENSIA_CONTOUR_USB_METER"))
/*     */       {
/* 126 */         str4 = "wizard.ascensia.contour_usb.ul_instructions";
/* 127 */       } else if (str1.equals("wizard.selections.SELECTION_DEVICE_XTLINKMETER"))
/*     */       {
/* 129 */         str4 = "wizard.ascensia.contour_xtlink.ul_instructions";
/*     */       } else {
/* 131 */         str4 = null;
/* 132 */         Contract.unreachable();
/*     */       }
/*     */     }
/* 135 */     this.m_labelMainText.setText(MessageHelper.format(this.m_resources.getString(str4), new Object[] { str3 }));
/*     */   }
/*     */ 
/*     */   protected void backRequest()
/*     */   {
/* 145 */     if ((getWizard().getPreviousStep() instanceof USBDriverPostInstallStep))
/*     */     {
/* 147 */       getWizard().showPreviousStep(3);
/*     */     }
/* 149 */     else getWizard().showPreviousStep();
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.steps.bayermeter.VerifyConnectionsStep
 * JD-Core Version:    0.6.0
 */