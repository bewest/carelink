/*    */ package minimed.ddms.applet.dtw.components;
/*    */ 
/*    */ import java.awt.event.ItemEvent;
/*    */ import java.awt.event.ItemListener;
/*    */ import java.awt.event.MouseAdapter;
/*    */ import java.awt.event.MouseEvent;
/*    */ import javax.swing.BorderFactory;
/*    */ import javax.swing.JComponent;
/*    */ import javax.swing.JRadioButton;
/*    */ import javax.swing.border.Border;
/*    */ 
/*    */ public class ClickAdapter extends MouseAdapter
/*    */   implements ItemListener
/*    */ {
/*    */   private final JRadioButton button;
/*    */   private final JComponent imageComponent;
/* 47 */   private final Border emptyBorder = BorderFactory.createEmptyBorder(4, 4, 4, 4);
/*    */ 
/*    */   public ClickAdapter(JRadioButton paramJRadioButton, JComponent paramJComponent)
/*    */   {
/* 55 */     this.button = paramJRadioButton;
/* 56 */     this.imageComponent = paramJComponent;
/* 57 */     paramJRadioButton.addItemListener(this);
/*    */   }
/*    */ 
/*    */   public void mouseReleased(MouseEvent paramMouseEvent)
/*    */   {
/* 66 */     this.button.setSelected(true);
/* 67 */     this.button.grabFocus();
/*    */   }
/*    */ 
/*    */   public void itemStateChanged(ItemEvent paramItemEvent)
/*    */   {
/* 76 */     if (paramItemEvent.getStateChange() == 2)
/* 77 */       this.imageComponent.setBorder(this.emptyBorder);
/* 78 */     else if (paramItemEvent.getStateChange() == 1)
/* 79 */       this.imageComponent.setBorder(GlowBorder.getInstance());
/*    */   }
/*    */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.components.ClickAdapter
 * JD-Core Version:    0.6.0
 */