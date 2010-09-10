/*      */ package Serialio;
/*      */ 
/*      */ class NativeHandleMutex
/*      */ {
/*      */   private static NativeHandleMutex instance;
/*      */ 
/*      */   public static synchronized NativeHandleMutex getInstance()
/*      */   {
/* 1708 */     if (instance == null)
/* 1709 */       instance = new NativeHandleMutex();
/* 1710 */     return instance;
/*      */   }
/*      */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     Serialio.NativeHandleMutex
 * JD-Core Version:    0.6.0
 */