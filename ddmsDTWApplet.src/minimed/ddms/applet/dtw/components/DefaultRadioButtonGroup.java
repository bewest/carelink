/*     */ package minimed.ddms.applet.dtw.components;
/*     */ 
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.Insets;
/*     */ import java.util.Enumeration;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.ButtonGroup;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JRadioButton;
/*     */ import minimed.ddms.applet.dtw.MessageHelper;
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ public class DefaultRadioButtonGroup
/*     */ {
/*     */   private static final int INSET_Y_SPACING = 10;
/*     */   private final ResourceBundle m_resources;
/*     */   private final ButtonGroup m_buttonGroup;
/*     */   private final JPanel m_panel;
/*     */   private int m_gridY;
/*     */ 
/*     */   public DefaultRadioButtonGroup(ResourceBundle paramResourceBundle, JPanel paramJPanel)
/*     */   {
/*  58 */     Contract.preNonNull(paramResourceBundle);
/*  59 */     Contract.preNonNull(paramJPanel);
/*     */ 
/*  61 */     this.m_resources = paramResourceBundle;
/*  62 */     this.m_buttonGroup = new ButtonGroup();
/*  63 */     this.m_panel = paramJPanel;
/*  64 */     this.m_gridY = 1;
/*     */   }
/*     */ 
/*     */   public JRadioButton add(String paramString)
/*     */   {
/*  83 */     return add(paramString, 1, this.m_gridY++);
/*     */   }
/*     */ 
/*     */   public JRadioButton add(String paramString, int paramInt1, int paramInt2)
/*     */   {
/* 100 */     Contract.preNonNull(paramString);
/* 101 */     return add(this.m_resources.getString(paramString), paramString, paramInt1, paramInt2);
/*     */   }
/*     */ 
/*     */   public JRadioButton add(String paramString1, String paramString2, int paramInt1, int paramInt2)
/*     */   {
/* 117 */     GridBagConstraints localGridBagConstraints = new GridBagConstraints();
/* 118 */     localGridBagConstraints.gridx = paramInt1;
/* 119 */     localGridBagConstraints.gridy = paramInt2;
/* 120 */     localGridBagConstraints.anchor = 17;
/*     */ 
/* 122 */     if (paramInt2 > 1) {
/* 123 */       localGridBagConstraints.insets = new Insets(10, 0, 0, 0);
/*     */     }
/* 125 */     return add(paramString1, paramString2, localGridBagConstraints);
/*     */   }
/*     */ 
/*     */   public JRadioButton add(String paramString1, String paramString2, GridBagConstraints paramGridBagConstraints)
/*     */   {
/* 145 */     Contract.pre(getButtonWithText(paramString1) == null);
/* 146 */     Contract.preNonNull(paramString2);
/*     */ 
/* 148 */     JRadioButton localJRadioButton = new JRadioButton(MessageHelper.addHtmlDocumentTags(paramString1));
/* 149 */     localJRadioButton.setName(paramString2);
/* 150 */     localJRadioButton.setBackground(this.m_panel.getBackground());
/* 151 */     localJRadioButton.setForeground(this.m_panel.getForeground());
/* 152 */     this.m_buttonGroup.add(localJRadioButton);
/* 153 */     paramGridBagConstraints.fill = 3;
/* 154 */     this.m_panel.add(localJRadioButton, paramGridBagConstraints);
/* 155 */     return localJRadioButton;
/*     */   }
/*     */ 
/*     */   public JRadioButton add(String paramString, GridBagConstraints paramGridBagConstraints)
/*     */   {
/* 171 */     Contract.preNonNull(paramString);
/* 172 */     String str = this.m_resources.getString(paramString);
/* 173 */     return add(str, paramString, paramGridBagConstraints);
/*     */   }
/*     */ 
/*     */   public JRadioButton add(String paramString1, String paramString2)
/*     */   {
/* 190 */     return add(paramString1, paramString2, 1, this.m_gridY++);
/*     */   }
/*     */ 
/*     */   public void selectButton(String paramString1, String paramString2)
/*     */   {
/* 205 */     JRadioButton localJRadioButton = getButton(paramString1);
/* 206 */     if (localJRadioButton == null)
/*     */     {
/* 208 */       localJRadioButton = getButton(paramString2);
/*     */     }
/* 210 */     Contract.pre(localJRadioButton != null);
/*     */ 
/* 213 */     localJRadioButton.setSelected(true);
/*     */   }
/*     */ 
/*     */   public String getSelectedButton()
/*     */   {
/* 223 */     String str = null;
/* 224 */     Enumeration localEnumeration = this.m_buttonGroup.getElements();
/*     */ 
/* 226 */     while ((localEnumeration.hasMoreElements()) && (str == null)) {
/* 227 */       JRadioButton localJRadioButton = (JRadioButton)localEnumeration.nextElement();
/* 228 */       if (localJRadioButton.isSelected()) {
/* 229 */         str = localJRadioButton.getName();
/*     */       }
/*     */     }
/*     */ 
/* 233 */     return str;
/*     */   }
/*     */ 
/*     */   public JRadioButton getButton(String paramString)
/*     */   {
/* 244 */     Contract.preNonNull(paramString);
/*     */ 
/* 247 */     Object localObject = null;
/* 248 */     Enumeration localEnumeration = this.m_buttonGroup.getElements();
/*     */ 
/* 250 */     while ((localEnumeration.hasMoreElements()) && (localObject == null)) {
/* 251 */       JRadioButton localJRadioButton = (JRadioButton)localEnumeration.nextElement();
/* 252 */       if (localJRadioButton.getName().equals(paramString)) {
/* 253 */         localObject = localJRadioButton;
/*     */       }
/*     */     }
/*     */ 
/* 257 */     return localObject;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 266 */     StringBuffer localStringBuffer = new StringBuffer("buttons: ");
/* 267 */     int i = 1;
/* 268 */     Enumeration localEnumeration = this.m_buttonGroup.getElements();
/*     */ 
/* 270 */     while (localEnumeration.hasMoreElements()) {
/* 271 */       JRadioButton localJRadioButton = (JRadioButton)localEnumeration.nextElement();
/* 272 */       localStringBuffer.append(i + "='");
/* 273 */       localStringBuffer.append(MessageHelper.removeHtmlDocumentTags(localJRadioButton.getText()) + "', key='" + localJRadioButton.getName() + "'; ");
/*     */ 
/* 275 */       i++;
/*     */     }
/*     */ 
/* 278 */     return localStringBuffer.toString();
/*     */   }
/*     */ 
/*     */   private JRadioButton getButtonWithText(String paramString)
/*     */   {
/* 290 */     Contract.preNonNull(paramString);
/*     */ 
/* 293 */     Object localObject = null;
/* 294 */     Enumeration localEnumeration = this.m_buttonGroup.getElements();
/*     */ 
/* 296 */     while ((localEnumeration.hasMoreElements()) && (localObject == null)) {
/* 297 */       JRadioButton localJRadioButton = (JRadioButton)localEnumeration.nextElement();
/* 298 */       if (!MessageHelper.removeHtmlDocumentTags(localJRadioButton.getText()).equals(MessageHelper.removeHtmlDocumentTags(paramString)))
/*     */         continue;
/* 300 */       localObject = localJRadioButton;
/*     */     }
/*     */ 
/* 304 */     return localObject;
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.components.DefaultRadioButtonGroup
 * JD-Core Version:    0.6.0
 */