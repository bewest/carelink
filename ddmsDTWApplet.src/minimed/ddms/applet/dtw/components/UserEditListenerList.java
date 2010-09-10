/*    */ package minimed.ddms.applet.dtw.components;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import minimed.util.Contract;
/*    */ 
/*    */ class UserEditListenerList
/*    */ {
/* 30 */   private final ArrayList listeners = new ArrayList();
/*    */ 
/*    */   void add(UserEditListener paramUserEditListener)
/*    */   {
/* 41 */     Contract.preNonNull(paramUserEditListener);
/* 42 */     this.listeners.add(paramUserEditListener);
/*    */   }
/*    */ 
/*    */   void fireUserEdited()
/*    */   {
/* 49 */     for (int i = 0; i < this.listeners.size(); i++) {
/* 50 */       UserEditListener localUserEditListener = (UserEditListener)this.listeners.get(i);
/* 51 */       localUserEditListener.userEdited();
/*    */     }
/*    */   }
/*    */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.components.UserEditListenerList
 * JD-Core Version:    0.6.0
 */