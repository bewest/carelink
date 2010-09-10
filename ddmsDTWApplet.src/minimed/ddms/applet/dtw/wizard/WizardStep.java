/*     */ package minimed.ddms.applet.dtw.wizard;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Container;
/*     */ import java.awt.Cursor;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.Insets;
/*     */ import java.awt.KeyboardFocusManager;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.net.URL;
/*     */ import java.util.MissingResourceException;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JRadioButton;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.border.Border;
/*     */ import minimed.ddms.applet.dtw.DTWApplet;
/*     */ import minimed.ddms.applet.dtw.LogWriter;
/*     */ import minimed.ddms.applet.dtw.PropertyWriter;
/*     */ import minimed.ddms.applet.dtw.components.ClickAdapter;
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ public abstract class WizardStep extends JPanel
/*     */ {
/*     */   public static final int ROW_INSET_SPACING = 10;
/*     */   public static final int COL_INSET_SPACING = 20;
/*     */   private static final int MS_PER_SECOND = 1000;
/*  71 */   protected final ResourceBundle m_resources = DTWApplet.getResourceBundle();
/*     */ 
/*  76 */   public final String m_unexpectedErrorMsg = this.m_resources.getString("error.MSG_UNEXPECTED_ERROR");
/*     */   private final JLabel m_leftBannerLabel;
/*     */   private final JLabel m_rightBannerLabel;
/*     */   private final JPanel m_bannerArea;
/*     */   private final JLabel m_topImageLabel;
/*     */   private final JLabel m_bottomImageLabel;
/*     */   private final JPanel m_imageArea;
/*     */   private final JButton m_backButton;
/*     */   private final JButton m_nextButton;
/*     */   private final JButton m_finishButton;
/*     */   private final JButton m_cancelButton;
/*     */   private final JPanel m_controlArea;
/*     */   private final JPanel m_contentArea;
/*     */   private final Wizard m_wizard;
/*     */   private Class m_nextClass;
/*  97 */   private final ActionListener m_controlButtonListener = new ActionListener()
/*     */   {
/*     */     public void actionPerformed(ActionEvent paramActionEvent)
/*     */     {
/*     */       try
/*     */       {
/* 106 */         if (paramActionEvent.getSource() == WizardStep.this.m_backButton)
/* 107 */           WizardStep.this.backRequest();
/* 108 */         else if (paramActionEvent.getSource() == WizardStep.this.m_nextButton)
/* 109 */           WizardStep.this.nextRequest();
/* 110 */         else if (paramActionEvent.getSource() == WizardStep.this.m_finishButton)
/* 111 */           WizardStep.this.finishRequest();
/* 112 */         else if (paramActionEvent.getSource() == WizardStep.this.m_cancelButton)
/* 113 */           WizardStep.this.cancelRequest();
/*     */         else
/* 115 */           Contract.unreachable();
/*     */       }
/*     */       catch (RuntimeException localRuntimeException) {
/* 118 */         WizardConfig localWizardConfig = WizardStep.this.m_wizard.getConfig();
/* 119 */         localRuntimeException.printStackTrace(localWizardConfig.getLogWriter());
/* 120 */         WizardStep.this.m_wizard.setFailureReason(WizardStep.this.m_unexpectedErrorMsg);
/* 121 */         WizardStep.this.m_wizard.showNextStep(localWizardConfig.getWizardStepProvider().getUnrecoverableErrorStep(WizardStep.this.m_wizard));
/*     */       }
/*     */     }
/*  97 */   };
/*     */   private long m_startTimeMS;
/*     */ 
/*     */   protected WizardStep(Wizard paramWizard, Class paramClass)
/*     */   {
/* 140 */     Contract.preNonNull(paramWizard);
/*     */ 
/* 142 */     this.m_wizard = paramWizard;
/* 143 */     this.m_nextClass = paramClass;
/* 144 */     WizardConfig localWizardConfig = this.m_wizard.getConfig();
/*     */ 
/* 146 */     logInfo("WizardStep: constructing step " + getName() + " nextClass=" + paramClass);
/*     */ 
/* 149 */     setLayout(new BorderLayout());
/*     */ 
/* 154 */     Border localBorder1 = BorderFactory.createEmptyBorder(6, 11, 0, 0);
/* 155 */     Border localBorder2 = BorderFactory.createEmptyBorder(7, 0, 7, 30);
/* 156 */     Border localBorder3 = BorderFactory.createEmptyBorder(5, 5, 0, 5);
/* 157 */     Border localBorder4 = BorderFactory.createEmptyBorder(0, 5, 5, 5);
/*     */ 
/* 159 */     this.m_leftBannerLabel = new JLabel();
/* 160 */     this.m_leftBannerLabel.setVerticalAlignment(1);
/* 161 */     this.m_leftBannerLabel.setBorder(localBorder1);
/* 162 */     this.m_leftBannerLabel.setFont(localWizardConfig.getBannerAreaFont());
/* 163 */     this.m_rightBannerLabel = new JLabel();
/* 164 */     this.m_rightBannerLabel.setBorder(localBorder2);
/*     */ 
/* 166 */     this.m_bannerArea = new JPanel();
/* 167 */     this.m_bannerArea.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, localWizardConfig.getBorderColor()));
/*     */ 
/* 169 */     this.m_bannerArea.setBackground(localWizardConfig.getBannerAreaBackgroundColor());
/* 170 */     this.m_bannerArea.setForeground(localWizardConfig.getForegroundColor());
/* 171 */     this.m_bannerArea.setLayout(new GridBagLayout());
/* 172 */     this.m_bannerArea.add(this.m_leftBannerLabel, new GridBagConstraints(0, 0, 1, 1, 1.0D, 0.0D, 17, 2, new Insets(0, 0, 0, 0), 0, 0));
/*     */ 
/* 174 */     this.m_bannerArea.add(this.m_rightBannerLabel, new GridBagConstraints(1, 0, 1, 1, 0.0D, 0.0D, 13, 0, new Insets(0, 0, 0, 0), 0, 0));
/*     */ 
/* 177 */     this.m_topImageLabel = new JLabel();
/* 178 */     this.m_topImageLabel.setBorder(localBorder3);
/* 179 */     this.m_bottomImageLabel = new JLabel();
/* 180 */     this.m_bottomImageLabel.setBorder(localBorder4);
/* 181 */     this.m_bottomImageLabel.setVisible(false);
/* 182 */     this.m_imageArea = new JPanel();
/* 183 */     setCustomColors(this.m_imageArea);
/* 184 */     this.m_imageArea.setLayout(new BorderLayout());
/* 185 */     this.m_imageArea.add(this.m_topImageLabel, "North");
/* 186 */     this.m_imageArea.add(this.m_bottomImageLabel, "South");
/*     */ 
/* 188 */     this.m_backButton = new JButton();
/* 189 */     this.m_nextButton = new JButton();
/* 190 */     this.m_finishButton = new JButton();
/* 191 */     this.m_cancelButton = new JButton();
/*     */ 
/* 194 */     this.m_backButton.setEnabled(false);
/* 195 */     this.m_nextButton.setEnabled(false);
/* 196 */     this.m_finishButton.setEnabled(false);
/* 197 */     this.m_cancelButton.setEnabled(false);
/*     */ 
/* 199 */     this.m_backButton.addActionListener(this.m_controlButtonListener);
/* 200 */     this.m_nextButton.addActionListener(this.m_controlButtonListener);
/* 201 */     this.m_finishButton.addActionListener(this.m_controlButtonListener);
/* 202 */     this.m_cancelButton.addActionListener(this.m_controlButtonListener);
/*     */ 
/* 206 */     this.m_backButton.setText(this.m_resources.getString("wizard.backButton.Text"));
/* 207 */     this.m_backButton.setMnemonic(this.m_resources.getString("wizard.backButton.Mnemonic").charAt(0));
/* 208 */     this.m_nextButton.setText(this.m_resources.getString("wizard.nextButton.Text"));
/* 209 */     this.m_nextButton.setMnemonic(this.m_resources.getString("wizard.nextButton.Mnemonic").charAt(0));
/* 210 */     this.m_finishButton.setText(this.m_resources.getString("wizard.finishButton.Text"));
/* 211 */     this.m_finishButton.setMnemonic(this.m_resources.getString("wizard.finishButton.Mnemonic").charAt(0));
/* 212 */     this.m_cancelButton.setText(this.m_resources.getString("wizard.cancelButton.Text"));
/* 213 */     this.m_cancelButton.setMnemonic(this.m_resources.getString("wizard.cancelButton.Mnemonic").charAt(0));
/*     */ 
/* 217 */     this.m_controlArea = new JPanel();
/* 218 */     setCustomColors(this.m_controlArea);
/* 219 */     this.m_controlArea.setLayout(new BorderLayout());
/* 220 */     JPanel localJPanel = new JPanel();
/* 221 */     localJPanel.setLayout(new GridBagLayout());
/* 222 */     setCustomColors(localJPanel);
/* 223 */     GridBagConstraints localGridBagConstraints = new GridBagConstraints();
/* 224 */     localGridBagConstraints.anchor = 13;
/* 225 */     localGridBagConstraints.insets = new Insets(5, 0, 5, 0);
/* 226 */     localJPanel.add(this.m_backButton, localGridBagConstraints);
/* 227 */     localGridBagConstraints = new GridBagConstraints();
/* 228 */     localGridBagConstraints.insets = new Insets(5, 0, 5, 0);
/* 229 */     localJPanel.add(this.m_nextButton, localGridBagConstraints);
/* 230 */     localGridBagConstraints = new GridBagConstraints();
/* 231 */     localGridBagConstraints.insets = new Insets(5, 10, 5, 0);
/* 232 */     localJPanel.add(this.m_finishButton, localGridBagConstraints);
/* 233 */     localGridBagConstraints = new GridBagConstraints();
/* 234 */     localGridBagConstraints.insets = new Insets(5, 5, 5, 5);
/* 235 */     localJPanel.add(this.m_cancelButton, localGridBagConstraints);
/* 236 */     this.m_controlArea.add(localJPanel, "East");
/*     */ 
/* 238 */     this.m_contentArea = new JPanel();
/* 239 */     setCustomColors(this.m_contentArea);
/*     */ 
/* 242 */     add(this.m_bannerArea, "North");
/* 243 */     add(this.m_imageArea, "West");
/* 244 */     add(this.m_contentArea, "Center");
/* 245 */     add(this.m_controlArea, "South");
/*     */   }
/*     */ 
/*     */   public final String getName()
/*     */   {
/* 259 */     return getClass().getName();
/*     */   }
/*     */ 
/*     */   public final boolean equals(Object paramObject)
/*     */   {
/* 272 */     boolean bool = false;
/* 273 */     if ((paramObject instanceof WizardStep)) {
/* 274 */       WizardStep localWizardStep = (WizardStep)paramObject;
/* 275 */       bool = getName().equals(localWizardStep.getName());
/*     */     }
/* 277 */     return bool;
/*     */   }
/*     */ 
/*     */   public final int hashCode()
/*     */   {
/* 286 */     return getName().hashCode();
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 297 */     PropertyWriter localPropertyWriter = new PropertyWriter();
/* 298 */     localPropertyWriter.add("Step-Name", getName());
/* 299 */     return localPropertyWriter.toString();
/*     */   }
/*     */ 
/*     */   protected void appletStopped()
/*     */   {
/* 309 */     logInfo(getName() + " received applet-stopped notification");
/*     */   }
/*     */ 
/*     */   protected void stepShown()
/*     */   {
/* 321 */     enableCursor();
/*     */ 
/* 325 */     this.m_backButton.setEnabled(true);
/* 326 */     enableWithFocus(this.m_nextButton);
/*     */ 
/* 329 */     this.m_cancelButton.setEnabled(false);
/*     */ 
/* 332 */     getFinishButton().setEnabled(getWizard().canFinish());
/*     */   }
/*     */ 
/*     */   protected void backRequest()
/*     */   {
/* 343 */     rememberUserSelections();
/* 344 */     Wizard localWizard = getWizard();
/* 345 */     localWizard.showPreviousStep();
/*     */   }
/*     */ 
/*     */   protected void nextRequest()
/*     */   {
/* 354 */     rememberUserSelections();
/* 355 */     Class localClass = getNextClass();
/* 356 */     Contract.preNonNull(localClass);
/* 357 */     Wizard localWizard = getWizard();
/* 358 */     localWizard.showNextStep(localClass);
/*     */   }
/*     */ 
/*     */   protected void finishRequest()
/*     */   {
/* 366 */     rememberUserSelections();
/* 367 */     Wizard localWizard = getWizard();
/* 368 */     Class localClass = localWizard.getConfig().getWizardStepProvider().getFinishStep(localWizard);
/*     */ 
/* 370 */     logInfo("finishRequest: jumping to " + localClass);
/* 371 */     localWizard.showNextStep(localClass);
/*     */   }
/*     */ 
/*     */   protected void cancelRequest()
/*     */   {
/*     */   }
/*     */ 
/*     */   protected Class getNextClass()
/*     */   {
/* 391 */     return this.m_nextClass;
/*     */   }
/*     */ 
/*     */   protected final URL getImage(String paramString)
/*     */     throws MissingResourceException
/*     */   {
/* 405 */     return getWizard().getConfig().getImage(paramString);
/*     */   }
/*     */ 
/*     */   protected final Wizard getWizard()
/*     */   {
/* 414 */     return this.m_wizard;
/*     */   }
/*     */ 
/*     */   protected final void logInfo(String paramString)
/*     */   {
/* 423 */     getWizard().getConfig().getLogWriter().logInfo(paramString);
/*     */   }
/*     */ 
/*     */   protected final void logWarning(String paramString)
/*     */   {
/* 432 */     getWizard().getConfig().getLogWriter().logWarning(paramString);
/*     */   }
/*     */ 
/*     */   protected final void logError(String paramString)
/*     */   {
/* 441 */     getWizard().getConfig().getLogWriter().logError(paramString);
/*     */   }
/*     */ 
/*     */   protected final WizardSelections getWizardSelections()
/*     */   {
/* 450 */     return getWizard().getConfig().getWizardSelections();
/*     */   }
/*     */ 
/*     */   protected final String formatPumpDevice(String paramString)
/*     */   {
/* 467 */     Contract.pre(("wizard.selections.SELECTION_DEVICE_MM508".equals(paramString)) || ("wizard.selections.SELECTION_DEVICE_MM511".equals(paramString)) || ("wizard.selections.SELECTION_DEVICE_MMPARADIGM2".equals(paramString)));
/*     */     String str;
/* 473 */     if (paramString.equals("wizard.selections.SELECTION_DEVICE_MMPARADIGM2")) {
/* 474 */       if (getWizard().getConfig().isUploadX15PumpOnly())
/* 475 */         str = getWizardSelections().m_tokenDeviceMMParadigm2X15;
/*     */       else
/* 477 */         str = getWizardSelections().m_tokenDeviceMMParadigm2All;
/*     */     }
/*     */     else {
/* 480 */       str = this.m_resources.getString(paramString);
/*     */     }
/* 482 */     Contract.postString(str);
/* 483 */     return str;
/*     */   }
/*     */ 
/*     */   protected final String parsePumpDevice(String paramString)
/*     */   {
/* 507 */     Contract.pre(("wizard.selections.SELECTION_DEVICE_MM508".equals(paramString)) || ("wizard.selections.SELECTION_DEVICE_MM511".equals(paramString)) || ("wizard.selections.SELECTION_DEVICE_MMPARADIGM2".equals(paramString)) || (getWizardSelections().m_tokenDeviceMMParadigm2All.equals(paramString)) || (getWizardSelections().m_tokenDeviceMMParadigm2X15.equals(paramString)));
/*     */ 
/* 516 */     String str = paramString;
/* 517 */     if ((str.equals(getWizardSelections().m_tokenDeviceMMParadigm2All)) || (str.equals(getWizardSelections().m_tokenDeviceMMParadigm2X15)))
/*     */     {
/* 519 */       str = "wizard.selections.SELECTION_DEVICE_MMPARADIGM2";
/*     */     }
/* 521 */     Contract.postString(str);
/* 522 */     return str;
/*     */   }
/*     */ 
/*     */   protected final String formatMeterDevice(String paramString)
/*     */   {
/*     */     String str;
/* 539 */     if ("wizard.selections.SELECTION_DEVICE_THERASENSE_FREESTYLE_METER".equals(paramString))
/*     */     {
/* 541 */       str = getWizardSelections().m_tokenDeviceTheraSenseFreeStyleMeter;
/*     */     }
/* 543 */     else str = this.m_resources.getString(paramString);
/*     */ 
/* 545 */     return str;
/*     */   }
/*     */ 
/*     */   protected final String parseMeterDevice(String paramString)
/*     */   {
/* 565 */     String str = paramString;
/* 566 */     if (getWizardSelections().m_tokenDeviceTheraSenseFreeStyleMeter.equals(str)) {
/* 567 */       str = "wizard.selections.SELECTION_DEVICE_THERASENSE_FREESTYLE_METER";
/*     */     }
/* 569 */     return str;
/*     */   }
/*     */ 
/*     */   protected final void enableWithFocus(JComponent paramJComponent)
/*     */   {
/* 579 */     Contract.preNonNull(paramJComponent);
/*     */ 
/* 581 */     KeyboardFocusManager localKeyboardFocusManager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
/*     */ 
/* 584 */     if ((localKeyboardFocusManager.getFocusOwner() == null) || (localKeyboardFocusManager.getFocusOwner() == getWizard().getConfig().getContentPane()))
/*     */     {
/* 586 */       localKeyboardFocusManager.clearGlobalFocusOwner();
/*     */     }
/*     */ 
/* 589 */     paramJComponent.setEnabled(true);
/* 590 */     paramJComponent.requestFocus();
/*     */   }
/*     */ 
/*     */   protected final void enableCursor()
/*     */   {
/* 598 */     Cursor localCursor = Cursor.getPredefinedCursor(0);
/* 599 */     if (!getWizard().getConfig().getContentPane().getCursor().equals(localCursor))
/* 600 */       getWizard().getConfig().getContentPane().setCursor(localCursor);
/*     */   }
/*     */ 
/*     */   protected final void disableCursor()
/*     */   {
/* 608 */     Cursor localCursor = Cursor.getPredefinedCursor(3);
/* 609 */     getWizard().getConfig().getContentPane().setCursor(localCursor);
/*     */   }
/*     */ 
/*     */   protected final void disable(JComponent paramJComponent)
/*     */   {
/* 619 */     Contract.preNonNull(paramJComponent);
/*     */ 
/* 622 */     if (paramJComponent.hasFocus()) {
/* 623 */       KeyboardFocusManager.getCurrentKeyboardFocusManager().clearGlobalFocusOwner();
/*     */     }
/*     */ 
/* 626 */     paramJComponent.setEnabled(false);
/*     */   }
/*     */ 
/*     */   protected final Icon getErrorIcon()
/*     */   {
/* 635 */     return UIManager.getIcon("OptionPane.errorIcon");
/*     */   }
/*     */ 
/*     */   protected final Icon getInfoIcon()
/*     */   {
/* 644 */     return UIManager.getIcon("OptionPane.informationIcon");
/*     */   }
/*     */ 
/*     */   protected final Icon getWarningIcon()
/*     */   {
/* 653 */     return UIManager.getIcon("OptionPane.warningIcon");
/*     */   }
/*     */ 
/*     */   protected final Icon getQuestionIcon()
/*     */   {
/* 662 */     return UIManager.getIcon("OptionPane.questionIcon");
/*     */   }
/*     */ 
/*     */   protected final JLabel getLeftBannerLabel()
/*     */   {
/* 671 */     return this.m_leftBannerLabel;
/*     */   }
/*     */ 
/*     */   protected final JLabel getRightBannerLabel()
/*     */   {
/* 680 */     return this.m_rightBannerLabel;
/*     */   }
/*     */ 
/*     */   protected final JLabel getTopImageLabel()
/*     */   {
/* 689 */     return this.m_topImageLabel;
/*     */   }
/*     */ 
/*     */   protected final JLabel getBottomImageLabel()
/*     */   {
/* 698 */     return this.m_bottomImageLabel;
/*     */   }
/*     */ 
/*     */   protected final JButton getBackButton()
/*     */   {
/* 707 */     return this.m_backButton;
/*     */   }
/*     */ 
/*     */   protected final JButton getNextButton()
/*     */   {
/* 716 */     return this.m_nextButton;
/*     */   }
/*     */ 
/*     */   protected final JButton getFinishButton()
/*     */   {
/* 725 */     return this.m_finishButton;
/*     */   }
/*     */ 
/*     */   protected final JButton getCancelButton()
/*     */   {
/* 734 */     return this.m_cancelButton;
/*     */   }
/*     */ 
/*     */   protected final JPanel getContentArea()
/*     */   {
/* 743 */     return this.m_contentArea;
/*     */   }
/*     */ 
/*     */   protected final JPanel getControlArea()
/*     */   {
/* 752 */     return this.m_controlArea;
/*     */   }
/*     */ 
/*     */   protected final String centerLabelText(String paramString)
/*     */   {
/* 762 */     String str = "<html><center>" + paramString + "</center></html>";
/* 763 */     return str;
/*     */   }
/*     */ 
/*     */   protected void rememberUserSelections()
/*     */   {
/*     */   }
/*     */ 
/*     */   protected void setCustomColors(JComponent paramJComponent)
/*     */   {
/* 783 */     Contract.preNonNull(paramJComponent);
/* 784 */     WizardConfig localWizardConfig = this.m_wizard.getConfig();
/* 785 */     paramJComponent.setBackground(localWizardConfig.getBackgroundColor());
/* 786 */     paramJComponent.setForeground(localWizardConfig.getForegroundColor());
/*     */   }
/*     */ 
/*     */   protected void updateContent()
/*     */   {
/*     */   }
/*     */ 
/*     */   protected void updateButtonStates()
/*     */   {
/*     */   }
/*     */ 
/*     */   protected JLabel createImageButton(String paramString, JRadioButton paramJRadioButton)
/*     */   {
/* 812 */     JLabel localJLabel = new JLabel();
/* 813 */     localJLabel.setFocusable(false);
/* 814 */     localJLabel.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
/* 815 */     localJLabel.setIcon(new ImageIcon(getImage(paramString)));
/* 816 */     localJLabel.addMouseListener(new ClickAdapter(paramJRadioButton, localJLabel));
/* 817 */     return localJLabel;
/*     */   }
/*     */ 
/*     */   protected void startClock()
/*     */   {
/* 824 */     this.m_startTimeMS = System.currentTimeMillis();
/*     */   }
/*     */ 
/*     */   protected int getClockET()
/*     */   {
/* 833 */     return (int)(System.currentTimeMillis() - this.m_startTimeMS) / 1000;
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.WizardStep
 * JD-Core Version:    0.6.0
 */