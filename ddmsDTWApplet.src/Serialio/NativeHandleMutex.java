/*      */ package Serialio;
/*      */ 
/*      */ class NativeHandleMutex
/*      */ {
/*      */   private static NativeHandleMutex instance;
/*      */ 
/*      */   public static synchronized NativeHandleMutex getInstance()
/*      */   {
/* 2098 */     if (instance == null)
/* 2099 */       instance = new NativeHandleMutex();
/* 2100 */     return instance;
/*      */   }
/*      */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     Serialio.NativeHandleMutex
 * JD-Core Version:    0.6.0
 */