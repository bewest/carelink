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
/*     */ import java.io.File;
/*     */ import java.io.IOException;
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
/*     */ import minimed.ddms.applet.dtw.ExeHelper;
/*     */ import minimed.ddms.applet.dtw.LogWriter;
/*     */ import minimed.ddms.applet.dtw.MessageHelper;
/*     */ import minimed.ddms.applet.dtw.PropertyWriter;
/*     */ import minimed.ddms.applet.dtw.components.ClickAdapter;
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ public abstract class WizardStep extends JPanel
/*     */ {
/*     */   public static final int ROW_INSET_SPACING = 10;
/*     */   public static final int COL_INSET_SPACING = 20;
/*  69 */   public static final String[] PORTS_FILTERED_MAC_LIST = { "Bluetooth" };
/*     */   private static final int MS_PER_SECOND = 1000;
/*     */   private static final int STATUS_OK = 0;
/*  80 */   protected final ResourceBundle m_resources = DTWApplet.getResourceBundle();
/*     */ 
/*  85 */   public final String m_unexpectedErrorMsg = this.m_resources.getString("error.MSG_UNEXPECTED_ERROR");
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
/* 106 */   private final ActionListener m_controlButtonListener = new ActionListener()
/*     */   {
/*     */     public void actionPerformed(ActionEvent paramActionEvent)
/*     */     {
/*     */       try
/*     */       {
/* 115 */         if (paramActionEvent.getSource() == WizardStep.this.m_backButton)
/* 116 */           WizardStep.this.backRequest();
/* 117 */         else if (paramActionEvent.getSource() == WizardStep.this.m_nextButton)
/* 118 */           WizardStep.this.nextRequest();
/* 119 */         else if (paramActionEvent.getSource() == WizardStep.this.m_finishButton)
/* 120 */           WizardStep.this.finishRequest();
/* 121 */         else if (paramActionEvent.getSource() == WizardStep.this.m_cancelButton)
/* 122 */           WizardStep.this.cancelRequest();
/*     */         else
/* 124 */           Contract.unreachable();
/*     */       }
/*     */       catch (RuntimeException localRuntimeException) {
/* 127 */         WizardConfig localWizardConfig = WizardStep.this.m_wizard.getConfig();
/* 128 */         localRuntimeException.printStackTrace(localWizardConfig.getLogWriter());
/* 129 */         WizardStep.this.m_wizard.setFailureReason(WizardStep.this.m_unexpectedErrorMsg);
/* 130 */         WizardStep.this.m_wizard.showNextStep(localWizardConfig.getWizardStepProvider().getUnrecoverableErrorStep(WizardStep.this.m_wizard));
/*     */       }
/*     */     }
/* 106 */   };
/*     */   private long m_startTimeMS;
/*     */ 
/*     */   protected WizardStep(Wizard paramWizard, Class paramClass)
/*     */   {
/* 149 */     Contract.preNonNull(paramWizard);
/*     */ 
/* 151 */     this.m_wizard = paramWizard;
/* 152 */     this.m_nextClass = paramClass;
/* 153 */     WizardConfig localWizardConfig = this.m_wizard.getConfig();
/*     */ 
/* 155 */     logInfo("WizardStep: constructing step " + getName() + " nextClass=" + paramClass);
/*     */ 
/* 158 */     setLayout(new BorderLayout());
/*     */ 
/* 163 */     Border localBorder1 = BorderFactory.createEmptyBorder(6, 11, 0, 0);
/* 164 */     Border localBorder2 = BorderFactory.createEmptyBorder(7, 0, 7, 30);
/* 165 */     Border localBorder3 = BorderFactory.createEmptyBorder(5, 5, 0, 5);
/* 166 */     Border localBorder4 = BorderFactory.createEmptyBorder(0, 5, 5, 5);
/*     */ 
/* 168 */     this.m_leftBannerLabel = new JLabel();
/* 169 */     this.m_leftBannerLabel.setVerticalAlignment(1);
/* 170 */     this.m_leftBannerLabel.setBorder(localBorder1);
/* 171 */     this.m_leftBannerLabel.setFont(localWizardConfig.getBannerAreaFont());
/* 172 */     this.m_rightBannerLabel = new JLabel();
/* 173 */     this.m_rightBannerLabel.setBorder(localBorder2);
/*     */ 
/* 175 */     this.m_bannerArea = new JPanel();
/* 176 */     this.m_bannerArea.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, localWizardConfig.getBorderColor()));
/*     */ 
/* 178 */     this.m_bannerArea.setBackground(localWizardConfig.getBannerAreaBackgroundColor());
/* 179 */     this.m_bannerArea.setForeground(localWizardConfig.getForegroundColor());
/* 180 */     this.m_bannerArea.setLayout(new GridBagLayout());
/* 181 */     this.m_bannerArea.add(this.m_leftBannerLabel, new GridBagConstraints(0, 0, 1, 1, 1.0D, 0.0D, 17, 2, new Insets(0, 0, 0, 0), 0, 0));
/*     */ 
/* 183 */     this.m_bannerArea.add(this.m_rightBannerLabel, new GridBagConstraints(1, 0, 1, 1, 0.0D, 0.0D, 13, 0, new Insets(0, 0, 0, 0), 0, 0));
/*     */ 
/* 186 */     this.m_topImageLabel = new JLabel();
/* 187 */     this.m_topImageLabel.setBorder(localBorder3);
/* 188 */     this.m_bottomImageLabel = new JLabel();
/* 189 */     this.m_bottomImageLabel.setBorder(localBorder4);
/* 190 */     this.m_bottomImageLabel.setVisible(false);
/* 191 */     this.m_imageArea = new JPanel();
/* 192 */     setCustomColors(this.m_imageArea);
/* 193 */     this.m_imageArea.setLayout(new BorderLayout());
/* 194 */     this.m_imageArea.add(this.m_topImageLabel, "North");
/* 195 */     this.m_imageArea.add(this.m_bottomImageLabel, "South");
/*     */ 
/* 197 */     this.m_backButton = new JButton();
/* 198 */     this.m_nextButton = new JButton();
/* 199 */     this.m_finishButton = new JButton();
/* 200 */     this.m_cancelButton = new JButton();
/*     */ 
/* 203 */     this.m_backButton.setEnabled(false);
/* 204 */     this.m_nextButton.setEnabled(false);
/* 205 */     this.m_finishButton.setEnabled(false);
/* 206 */     this.m_cancelButton.setEnabled(false);
/*     */ 
/* 208 */     this.m_backButton.addActionListener(this.m_controlButtonListener);
/* 209 */     this.m_nextButton.addActionListener(this.m_controlButtonListener);
/* 210 */     this.m_finishButton.addActionListener(this.m_controlButtonListener);
/* 211 */     this.m_cancelButton.addActionListener(this.m_controlButtonListener);
/*     */ 
/* 215 */     this.m_backButton.setText(this.m_resources.getString("wizard.backButton.Text"));
/* 216 */     this.m_backButton.setMnemonic(this.m_resources.getString("wizard.backButton.Mnemonic").charAt(0));
/* 217 */     this.m_nextButton.setText(this.m_resources.getString("wizard.nextButton.Text"));
/* 218 */     this.m_nextButton.setMnemonic(this.m_resources.getString("wizard.nextButton.Mnemonic").charAt(0));
/* 219 */     this.m_finishButton.setText(this.m_resources.getString("wizard.finishButton.Text"));
/* 220 */     this.m_finishButton.setMnemonic(this.m_resources.getString("wizard.finishButton.Mnemonic").charAt(0));
/* 221 */     this.m_cancelButton.setText(this.m_resources.getString("wizard.cancelButton.Text"));
/* 222 */     this.m_cancelButton.setMnemonic(this.m_resources.getString("wizard.cancelButton.Mnemonic").charAt(0));
/*     */ 
/* 226 */     this.m_controlArea = new JPanel();
/* 227 */     setCustomColors(this.m_controlArea);
/* 228 */     this.m_controlArea.setLayout(new BorderLayout());
/* 229 */     JPanel localJPanel = new JPanel();
/* 230 */     localJPanel.setLayout(new GridBagLayout());
/* 231 */     setCustomColors(localJPanel);
/* 232 */     GridBagConstraints localGridBagConstraints = new GridBagConstraints();
/* 233 */     localGridBagConstraints.anchor = 13;
/* 234 */     localGridBagConstraints.insets = new Insets(5, 0, 5, 0);
/* 235 */     localJPanel.add(this.m_backButton, localGridBagConstraints);
/* 236 */     localGridBagConstraints = new GridBagConstraints();
/* 237 */     localGridBagConstraints.insets = new Insets(5, 0, 5, 0);
/* 238 */     localJPanel.add(this.m_nextButton, localGridBagConstraints);
/* 239 */     localGridBagConstraints = new GridBagConstraints();
/* 240 */     localGridBagConstraints.insets = new Insets(5, 10, 5, 0);
/* 241 */     localJPanel.add(this.m_finishButton, localGridBagConstraints);
/* 242 */     localGridBagConstraints = new GridBagConstraints();
/* 243 */     localGridBagConstraints.insets = new Insets(5, 5, 5, 5);
/* 244 */     localJPanel.add(this.m_cancelButton, localGridBagConstraints);
/* 245 */     this.m_controlArea.add(localJPanel, "East");
/*     */ 
/* 247 */     this.m_contentArea = new JPanel();
/* 248 */     setCustomColors(this.m_contentArea);
/*     */ 
/* 251 */     add(this.m_bannerArea, "North");
/* 252 */     add(this.m_imageArea, "West");
/* 253 */     add(this.m_contentArea, "Center");
/* 254 */     add(this.m_controlArea, "South");
/*     */   }
/*     */ 
/*     */   public final String getName()
/*     */   {
/* 268 */     return getClass().getName();
/*     */   }
/*     */ 
/*     */   public final boolean equals(Object paramObject)
/*     */   {
/* 281 */     boolean bool = false;
/* 282 */     if ((paramObject instanceof WizardStep)) {
/* 283 */       WizardStep localWizardStep = (WizardStep)paramObject;
/* 284 */       bool = getName().equals(localWizardStep.getName());
/*     */     }
/* 286 */     return bool;
/*     */   }
/*     */ 
/*     */   public final int hashCode()
/*     */   {
/* 295 */     return getName().hashCode();
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 306 */     PropertyWriter localPropertyWriter = new PropertyWriter();
/* 307 */     localPropertyWriter.add("Step-Name", getName());
/* 308 */     return localPropertyWriter.toString();
/*     */   }
/*     */ 
/*     */   protected void appletStopped()
/*     */   {
/* 318 */     logInfo(getName() + " received applet-stopped notification");
/*     */   }
/*     */ 
/*     */   protected void stepShown()
/*     */   {
/* 330 */     enableCursor();
/*     */ 
/* 334 */     this.m_backButton.setEnabled(true);
/* 335 */     enableWithFocus(this.m_nextButton);
/*     */ 
/* 338 */     this.m_cancelButton.setEnabled(false);
/*     */ 
/* 341 */     getFinishButton().setEnabled(getWizard().canFinish());
/*     */   }
/*     */ 
/*     */   protected void backRequest()
/*     */   {
/* 352 */     rememberUserSelections();
/* 353 */     Wizard localWizard = getWizard();
/* 354 */     localWizard.showPreviousStep();
/*     */   }
/*     */ 
/*     */   protected void nextRequest()
/*     */   {
/* 363 */     rememberUserSelections();
/* 364 */     Class localClass = getNextClass();
/* 365 */     Contract.preNonNull(localClass);
/* 366 */     Wizard localWizard = getWizard();
/* 367 */     localWizard.showNextStep(localClass);
/*     */   }
/*     */ 
/*     */   protected void finishRequest()
/*     */   {
/* 375 */     rememberUserSelections();
/* 376 */     Wizard localWizard = getWizard();
/* 377 */     Class localClass = localWizard.getConfig().getWizardStepProvider().getFinishStep(localWizard);
/*     */ 
/* 379 */     logInfo("finishRequest: jumping to " + localClass);
/* 380 */     localWizard.showNextStep(localClass);
/*     */   }
/*     */ 
/*     */   protected void cancelRequest()
/*     */   {
/*     */   }
/*     */ 
/*     */   protected Class getNextClass()
/*     */   {
/* 400 */     return this.m_nextClass;
/*     */   }
/*     */ 
/*     */   protected final URL getImage(String paramString)
/*     */     throws MissingResourceException
/*     */   {
/* 414 */     return getWizard().getConfig().getImage(paramString);
/*     */   }
/*     */ 
/*     */   protected final Wizard getWizard()
/*     */   {
/* 423 */     return this.m_wizard;
/*     */   }
/*     */ 
/*     */   protected final void logInfo(String paramString)
/*     */   {
/* 432 */     getWizard().getConfig().getLogWriter().logInfo(paramString);
/*     */   }
/*     */ 
/*     */   protected final void logWarning(String paramString)
/*     */   {
/* 441 */     getWizard().getConfig().getLogWriter().logWarning(paramString);
/*     */   }
/*     */ 
/*     */   protected final void logError(String paramString)
/*     */   {
/* 450 */     getWizard().getConfig().getLogWriter().logError(paramString);
/*     */   }
/*     */ 
/*     */   protected final WizardSelections getWizardSelections()
/*     */   {
/* 459 */     return getWizard().getConfig().getWizardSelections();
/*     */   }
/*     */ 
/*     */   protected final String formatPumpDevice(String paramString)
/*     */   {
/* 474 */     Contract.pre("wizard.selections.SELECTION_DEVICE_MMPARADIGM2".equals(paramString));
/*     */     String str;
/* 478 */     if (paramString.equals("wizard.selections.SELECTION_DEVICE_MMPARADIGM2"))
/* 479 */       str = getWizardSelections().m_tokenDeviceMMParadigm2X15;
/*     */     else {
/* 481 */       str = this.m_resources.getString(paramString);
/*     */     }
/* 483 */     Contract.postString(str);
/* 484 */     return str;
/*     */   }
/*     */ 
/*     */   protected final String parsePumpDevice(String paramString)
/*     */   {
/* 505 */     Contract.pre(("wizard.selections.SELECTION_DEVICE_MMPARADIGM2".equals(paramString)) || (getWizardSelections().m_tokenDeviceMMParadigm2X15.equals(paramString)));
/*     */ 
/* 511 */     String str = paramString;
/* 512 */     if (str.equals(getWizardSelections().m_tokenDeviceMMParadigm2X15)) {
/* 513 */       str = "wizard.selections.SELECTION_DEVICE_MMPARADIGM2";
/*     */     }
/* 515 */     Contract.postString(str);
/* 516 */     return str;
/*     */   }
/*     */ 
/*     */   protected final String formatMeterDevice(String paramString)
/*     */   {
/*     */     String str;
/* 533 */     if ("wizard.selections.SELECTION_DEVICE_THERASENSE_FREESTYLE_METER".equals(paramString))
/*     */     {
/* 535 */       str = getWizardSelections().m_tokenDeviceTheraSenseFreeStyleMeter;
/*     */     }
/* 537 */     else str = this.m_resources.getString(paramString);
/*     */ 
/* 539 */     return str;
/*     */   }
/*     */ 
/*     */   protected final String parseMeterDevice(String paramString)
/*     */   {
/* 559 */     String str = paramString;
/* 560 */     if (getWizardSelections().m_tokenDeviceTheraSenseFreeStyleMeter.equals(str)) {
/* 561 */       str = "wizard.selections.SELECTION_DEVICE_THERASENSE_FREESTYLE_METER";
/*     */     }
/* 563 */     return str;
/*     */   }
/*     */ 
/*     */   protected final void enableWithFocus(JComponent paramJComponent)
/*     */   {
/* 573 */     Contract.preNonNull(paramJComponent);
/*     */ 
/* 575 */     KeyboardFocusManager localKeyboardFocusManager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
/*     */ 
/* 578 */     if ((localKeyboardFocusManager.getFocusOwner() == null) || (localKeyboardFocusManager.getFocusOwner() == getWizard().getConfig().getContentPane()))
/*     */     {
/* 580 */       localKeyboardFocusManager.clearGlobalFocusOwner();
/*     */     }
/*     */ 
/* 583 */     paramJComponent.setEnabled(true);
/* 584 */     paramJComponent.requestFocus();
/*     */   }
/*     */ 
/*     */   protected final void enableCursor()
/*     */   {
/* 592 */     Cursor localCursor = Cursor.getPredefinedCursor(0);
/* 593 */     if (!getWizard().getConfig().getContentPane().getCursor().equals(localCursor))
/* 594 */       getWizard().getConfig().getContentPane().setCursor(localCursor);
/*     */   }
/*     */ 
/*     */   protected final void disableCursor()
/*     */   {
/* 602 */     Cursor localCursor = Cursor.getPredefinedCursor(3);
/* 603 */     getWizard().getConfig().getContentPane().setCursor(localCursor);
/*     */   }
/*     */ 
/*     */   protected final void disable(JComponent paramJComponent)
/*     */   {
/* 613 */     Contract.preNonNull(paramJComponent);
/*     */ 
/* 616 */     if (paramJComponent.hasFocus()) {
/* 617 */       KeyboardFocusManager.getCurrentKeyboardFocusManager().clearGlobalFocusOwner();
/*     */     }
/*     */ 
/* 620 */     paramJComponent.setEnabled(false);
/*     */   }
/*     */ 
/*     */   protected final Icon getErrorIcon()
/*     */   {
/*     */     try
/*     */     {
/* 630 */       return new ImageIcon(getImage("wizard.erroricon.pic")); } catch (MissingResourceException localMissingResourceException) {
/*     */     }
/* 632 */     return UIManager.getIcon("OptionPane.errorIcon");
/*     */   }
/*     */ 
/*     */   protected final Icon getInfoIcon()
/*     */   {
/*     */     try
/*     */     {
/* 643 */       return new ImageIcon(getImage("wizard.infoicon.pic")); } catch (MissingResourceException localMissingResourceException) {
/*     */     }
/* 645 */     return UIManager.getIcon("OptionPane.informationIcon");
/*     */   }
/*     */ 
/*     */   protected final Icon getWarningIcon()
/*     */   {
/*     */     try
/*     */     {
/* 656 */       return new ImageIcon(getImage("wizard.warningicon.pic")); } catch (MissingResourceException localMissingResourceException) {
/*     */     }
/* 658 */     return UIManager.getIcon("OptionPane.warningIcon");
/*     */   }
/*     */ 
/*     */   protected final Icon getQuestionIcon()
/*     */   {
/*     */     try
/*     */     {
/* 669 */       return new ImageIcon(getImage("wizard.questionmark.pic")); } catch (MissingResourceException localMissingResourceException) {
/*     */     }
/* 671 */     return UIManager.getIcon("OptionPane.questionIcon");
/*     */   }
/*     */ 
/*     */   protected final JLabel getLeftBannerLabel()
/*     */   {
/* 681 */     return this.m_leftBannerLabel;
/*     */   }
/*     */ 
/*     */   protected final JLabel getRightBannerLabel()
/*     */   {
/* 690 */     return this.m_rightBannerLabel;
/*     */   }
/*     */ 
/*     */   protected final JLabel getTopImageLabel()
/*     */   {
/* 699 */     return this.m_topImageLabel;
/*     */   }
/*     */ 
/*     */   protected final JLabel getBottomImageLabel()
/*     */   {
/* 708 */     return this.m_bottomImageLabel;
/*     */   }
/*     */ 
/*     */   protected final JButton getBackButton()
/*     */   {
/* 717 */     return this.m_backButton;
/*     */   }
/*     */ 
/*     */   protected final JButton getNextButton()
/*     */   {
/* 726 */     return this.m_nextButton;
/*     */   }
/*     */ 
/*     */   protected final JButton getFinishButton()
/*     */   {
/* 735 */     return this.m_finishButton;
/*     */   }
/*     */ 
/*     */   protected final JButton getCancelButton()
/*     */   {
/* 744 */     return this.m_cancelButton;
/*     */   }
/*     */ 
/*     */   protected final JPanel getContentArea()
/*     */   {
/* 753 */     return this.m_contentArea;
/*     */   }
/*     */ 
/*     */   protected final JPanel getControlArea()
/*     */   {
/* 762 */     return this.m_controlArea;
/*     */   }
/*     */ 
/*     */   protected final String centerLabelText(String paramString)
/*     */   {
/* 772 */     String str = "<html><center>" + paramString + "</center></html>";
/* 773 */     return str;
/*     */   }
/*     */ 
/*     */   protected void rememberUserSelections()
/*     */   {
/*     */   }
/*     */ 
/*     */   protected void setCustomColors(JComponent paramJComponent)
/*     */   {
/* 793 */     Contract.preNonNull(paramJComponent);
/* 794 */     WizardConfig localWizardConfig = this.m_wizard.getConfig();
/* 795 */     paramJComponent.setBackground(localWizardConfig.getBackgroundColor());
/* 796 */     paramJComponent.setForeground(localWizardConfig.getForegroundColor());
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
/* 822 */     JLabel localJLabel = new JLabel();
/* 823 */     localJLabel.setFocusable(false);
/* 824 */     localJLabel.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
/* 825 */     localJLabel.setIcon(new ImageIcon(getImage(paramString)));
/* 826 */     localJLabel.addMouseListener(new ClickAdapter(paramJRadioButton, localJLabel));
/* 827 */     return localJLabel;
/*     */   }
/*     */ 
/*     */   protected void startClock()
/*     */   {
/* 834 */     this.m_startTimeMS = System.currentTimeMillis();
/*     */   }
/*     */ 
/*     */   protected int getClockET()
/*     */   {
/* 843 */     return (int)(System.currentTimeMillis() - this.m_startTimeMS) / 1000;
/*     */   }
/*     */ 
/*     */   protected void enableControlCodeHotCorner()
/*     */   {
/* 850 */     WizardConfig localWizardConfig = getWizard().getConfig();
/*     */ 
/* 852 */     if (localWizardConfig.inHotCornerMode())
/* 853 */       new ControlCodeHotCorner(this);
/*     */   }
/*     */ 
/*     */   protected void displayMustBeAdminMsg(Class<?> paramClass)
/*     */   {
/* 865 */     String str1 = this.m_resources.getString("wizard.driver.install.administrator") + "<br>";
/* 866 */     getWizard().getConfig().getLogWriter().println("install failed because " + str1);
/* 867 */     String str2 = MessageHelper.format(this.m_resources.getString("wizard.driver.failed"), new Object[] { str1 });
/*     */ 
/* 869 */     getWizard().setFailureReason(str2);
/* 870 */     getWizard().showNextStep(paramClass);
/*     */   }
/*     */ 
/*     */   protected boolean isUserRunningAsAdmin()
/*     */   {
/* 880 */     boolean bool = false;
/* 881 */     LogWriter localLogWriter = getWizard().getConfig().getLogWriter();
/*     */ 
/* 883 */     ExeHelper localExeHelper = new ExeHelper();
/* 884 */     File localFile = null;
/* 885 */     String[] arrayOfString = new String[3];
/*     */ 
/* 888 */     String str1 = System.getenv("ComSpec");
/*     */ 
/* 891 */     Contract.invariant(str1 != null);
/* 892 */     Contract.invariant(str1.length() > 1);
/*     */ 
/* 895 */     int i = str1.lastIndexOf(File.separator);
/* 896 */     String str2 = str1.substring(0, i) + File.separator;
/*     */ 
/* 899 */     arrayOfString[0] = str1;
/* 900 */     arrayOfString[1] = "/C";
/* 901 */     arrayOfString[2] = (str2 + "net.exe localgroup administrators | " + str2 + "find.exe \"%USERNAME%\"");
/*     */ 
/* 904 */     localLogWriter.logInfo("isUserRunningAsAdmin: executing cmd '" + arrayOfString[2] + "'");
/*     */     try
/*     */     {
/* 908 */       int j = localExeHelper.execute(arrayOfString, localFile);
/* 909 */       bool = j == 0;
/*     */     }
/*     */     catch (IOException localIOException) {
/* 912 */       localIOException.printStackTrace(localLogWriter);
/*     */     }
/*     */     catch (InterruptedException localInterruptedException) {
/* 915 */       localInterruptedException.printStackTrace(localLogWriter);
/*     */     }
/*     */ 
/* 918 */     if (bool)
/* 919 */       localLogWriter.logInfo("isUserRunningAsAdmin: " + bool);
/*     */     else {
/* 921 */       localLogWriter.logWarning("isUserRunningAsAdmin: " + bool);
/*     */     }
/* 923 */     return bool;
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.wizard.WizardStep
 * JD-Core Version:    0.6.0
 */