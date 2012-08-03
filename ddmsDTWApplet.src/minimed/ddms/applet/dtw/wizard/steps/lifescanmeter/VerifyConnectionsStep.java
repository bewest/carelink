/*     */ package minimed.ddms.applet.dtw.wizard.steps.lifescanmeter;
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
/*  97 */     String str1 = formatMeterDevice(getWizardSelections().getMeterDevice());
/*  98 */     String str2 = this.m_resources.getString(getWizardSelections().getMeterBrand());
/*  99 */     Object[] arrayOfObject = { str1.replaceAll("<br>", " / "), str2.replaceAll("<br>", " / ") };
/*     */     String str3;
/* 102 */     if ((str1.equals(getWizardSelections().m_selectionDeviceLifeScanBasicMeter)) || (str1.equals(getWizardSelections().m_selectionDeviceLifeScanProfileMeter)))
/*     */     {
/* 104 */       str3 = "wizard.lifescan.basic_or_profile.ul_instructions";
/* 105 */     } else if (str1.equals(getWizardSelections().m_selectionDeviceLifeScanUltraMeter)) {
/* 106 */       if (str2.equals(getWizardSelections().m_selectionBrandLifeScan)) {
/* 107 */         str3 = "wizard.lifescan.ultra.ul_instructions";
/* 108 */       } else if (str2.equals(getWizardSelections().m_selectionBrandMinimed)) {
/* 109 */         str3 = "wizard.lifescan.ultra.ul_instructions";
/* 110 */         arrayOfObject[0] = this.m_resources.getString("wizard.selections.SELECTION_DEVICE_ULTRA_LINK");
/* 111 */         arrayOfObject[1] = getWizardSelections().m_selectionBrandLifeScan;
/*     */       } else {
/* 113 */         str3 = null;
/* 114 */         Contract.unreachable();
/*     */       }
/* 116 */     } else if (str1.equals(getWizardSelections().m_selectionDeviceLifeScanUltraMiniMeter)) {
/* 117 */       str3 = "wizard.lifescan.ultramini.ul_instructions";
/* 118 */     } else if (str1.equals(getWizardSelections().m_selectionDeviceLifeScanUltraSmartMeter)) {
/* 119 */       str3 = "wizard.lifescan.ultrasmart.ul_instructions";
/* 120 */     } else if (str1.equals(getWizardSelections().m_selectionDeviceLifeScanSureStepMeter)) {
/* 121 */       str3 = "wizard.lifescan.surestep.ul_instructions";
/* 122 */     } else if (str1.equals(getWizardSelections().m_selectionDeviceLifeScanFastTakeMeter)) {
/* 123 */       str3 = "wizard.lifescan.fasttake.ul_instructions";
/*     */     } else {
/* 125 */       str3 = null;
/* 126 */       Contract.unreachable();
/*     */     }
/* 128 */     this.m_labelMainText.setText(MessageHelper.format(this.m_resources.getString(str3), arrayOfObject));
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.steps.lifescanmeter.VerifyConnectionsStep
 * JD-Core Version:    0.6.0
 */