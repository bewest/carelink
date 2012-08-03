/*     */ package minimed.ddms.applet.dtw.components;
/*     */ 
/*     */ import java.awt.Component;
/*     */ import javax.swing.JFormattedTextField;
/*     */ import javax.swing.LookAndFeel;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.event.DocumentEvent;
/*     */ import javax.swing.event.DocumentListener;
/*     */ import javax.swing.text.AttributeSet;
/*     */ import javax.swing.text.BadLocationException;
/*     */ import javax.swing.text.DefaultFormatter;
/*     */ import javax.swing.text.DefaultFormatterFactory;
/*     */ import javax.swing.text.Document;
/*     */ import javax.swing.text.DocumentFilter;
/*     */ import javax.swing.text.DocumentFilter.FilterBypass;
/*     */ 
/*     */ public final class NumericTextField extends JFormattedTextField
/*     */   implements DocumentListener
/*     */ {
/*     */   private static final int DEFAULT_INPUT_LEN = 6;
/*     */   private final Component m_component;
/*     */   private final UserEditListenerList m_userEditListenerList;
/*  41 */   private boolean m_okToFireUserEdited = true;
/*     */ 
/*     */   public NumericTextField()
/*     */   {
/*  49 */     this.m_userEditListenerList = new UserEditListenerList();
/*  50 */     setFocusLostBehavior(3);
/*  51 */     setColumns(6);
/*  52 */     this.m_component = this;
/*     */ 
/*  54 */     1 local1 = new DefaultFormatter() {
/*  55 */       private final DocumentFilter filter = new DocumentFilter()
/*     */       {
/*     */         public void replace(DocumentFilter.FilterBypass paramFilterBypass, int paramInt1, int paramInt2, String paramString, AttributeSet paramAttributeSet)
/*     */           throws BadLocationException
/*     */         {
/*  73 */           StringBuffer localStringBuffer = new StringBuffer(paramFilterBypass.getDocument().getText(0, paramFilterBypass.getDocument().getLength()));
/*     */ 
/*  75 */           localStringBuffer.replace(paramInt1, paramInt1 + paramInt2, paramString);
/*  76 */           int i = 1;
/*     */ 
/*  78 */           if (localStringBuffer.length() > 6)
/*  79 */             i = 0;
/*     */           else {
/*  81 */             for (int j = 0; (j < localStringBuffer.length()) && (i != 0); j++) {
/*  82 */               if (!NumericTextField.access$000(localStringBuffer.charAt(j))) {
/*  83 */                 i = 0;
/*     */               }
/*     */             }
/*     */           }
/*     */ 
/*  88 */           if (i != 0)
/*  89 */             paramFilterBypass.replace(paramInt1, paramInt2, paramString, paramAttributeSet);
/*     */           else
/*  91 */             UIManager.getLookAndFeel().provideErrorFeedback(NumericTextField.this.m_component);
/*     */         }
/*  55 */       };
/*     */ 
/*     */       public DocumentFilter getDocumentFilter()
/*     */       {
/* 102 */         return this.filter;
/*     */       }
/*     */ 
/*     */       public void install(JFormattedTextField paramJFormattedTextField)
/*     */       {
/* 114 */         NumericTextField.access$202(NumericTextField.this, false);
/*     */         try {
/* 116 */           super.install(paramJFormattedTextField);
/*     */         } finally {
/* 118 */           NumericTextField.access$202(NumericTextField.this, true);
/*     */         }
/*     */       }
/*     */     };
/* 123 */     DefaultFormatterFactory localDefaultFormatterFactory = new DefaultFormatterFactory();
/* 124 */     localDefaultFormatterFactory.setDefaultFormatter(local1);
/* 125 */     localDefaultFormatterFactory.setDisplayFormatter(local1);
/* 126 */     localDefaultFormatterFactory.setEditFormatter(local1);
/* 127 */     localDefaultFormatterFactory.setNullFormatter(local1);
/* 128 */     setFormatterFactory(localDefaultFormatterFactory);
/*     */ 
/* 130 */     getDocument().addDocumentListener(this);
/*     */   }
/*     */ 
/*     */   public void addUserEditListener(UserEditListener paramUserEditListener)
/*     */   {
/* 142 */     this.m_userEditListenerList.add(paramUserEditListener);
/*     */   }
/*     */ 
/*     */   public void changedUpdate(DocumentEvent paramDocumentEvent)
/*     */   {
/* 151 */     if (this.m_okToFireUserEdited)
/* 152 */       this.m_userEditListenerList.fireUserEdited();
/*     */   }
/*     */ 
/*     */   public void insertUpdate(DocumentEvent paramDocumentEvent)
/*     */   {
/* 162 */     if (this.m_okToFireUserEdited)
/* 163 */       this.m_userEditListenerList.fireUserEdited();
/*     */   }
/*     */ 
/*     */   public void removeUpdate(DocumentEvent paramDocumentEvent)
/*     */   {
/* 173 */     if (this.m_okToFireUserEdited)
/* 174 */       this.m_userEditListenerList.fireUserEdited();
/*     */   }
/*     */ 
/*     */   public void setTextNoUserEditNotify(String paramString)
/*     */   {
/* 185 */     this.m_okToFireUserEdited = false;
/*     */     try {
/* 187 */       setText(paramString);
/*     */     } finally {
/* 189 */       this.m_okToFireUserEdited = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean isValidEntry()
/*     */   {
/* 199 */     return isValidEntry(getText());
/*     */   }
/*     */ 
/*     */   public static boolean isValidEntry(String paramString)
/*     */   {
/* 209 */     int i = paramString.trim().length() == 6 ? 1 : 0;
/*     */ 
/* 211 */     for (int j = 0; (j < paramString.length()) && (i != 0); j++) {
/* 212 */       if (!isValidChar(paramString.charAt(j))) {
/* 213 */         i = 0;
/*     */       }
/*     */     }
/*     */ 
/* 217 */     return i;
/*     */   }
/*     */ 
/*     */   private static boolean isValidChar(char paramChar)
/*     */   {
/* 228 */     return ((paramChar >= '0') && (paramChar <= '9')) || ((paramChar >= 'a') && (paramChar <= 'f')) || ((paramChar >= 'A') && (paramChar <= 'F'));
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.components.NumericTextField
 * JD-Core Version:    0.6.0
 */