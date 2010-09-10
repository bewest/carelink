/*    */ package minimed.ddms.applet.dtw.components;
/*    */ 
/*    */ import javax.swing.LookAndFeel;
/*    */ import javax.swing.UIManager;
/*    */ import javax.swing.text.AttributeSet;
/*    */ import javax.swing.text.BadLocationException;
/*    */ import javax.swing.text.Document;
/*    */ import javax.swing.text.DocumentFilter;
/*    */ import javax.swing.text.DocumentFilter.FilterBypass;
/*    */ 
/*    */ class NumericTextField$2 extends DocumentFilter
/*    */ {
/*    */   private final NumericTextField.1 this$1;
/*    */ 
/*    */   public void replace(DocumentFilter.FilterBypass paramFilterBypass, int paramInt1, int paramInt2, String paramString, AttributeSet paramAttributeSet)
/*    */     throws BadLocationException
/*    */   {
/* 73 */     StringBuffer localStringBuffer = new StringBuffer(paramFilterBypass.getDocument().getText(0, paramFilterBypass.getDocument().getLength()));
/*    */ 
/* 75 */     localStringBuffer.replace(paramInt1, paramInt1 + paramInt2, paramString);
/* 76 */     int i = 1;
/*    */ 
/* 78 */     if (localStringBuffer.length() > 6)
/* 79 */       i = 0;
/*    */     else {
/* 81 */       for (int j = 0; (j < localStringBuffer.length()) && (i != 0); j++) {
/* 82 */         if (!NumericTextField.access$000(localStringBuffer.charAt(j))) {
/* 83 */           i = 0;
/*    */         }
/*    */       }
/*    */     }
/*    */ 
/* 88 */     if (i != 0)
/* 89 */       paramFilterBypass.replace(paramInt1, paramInt2, paramString, paramAttributeSet);
/*    */     else
/* 91 */       UIManager.getLookAndFeel().provideErrorFeedback(NumericTextField.access$200(NumericTextField.1.access$100(this.this$1)));
/*    */   }
/*    */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.components.NumericTextField.2
 * JD-Core Version:    0.6.0
 */