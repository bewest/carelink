/*    */ package Serialio.tool;
/*    */ 
/*    */ import java.util.Vector;
/*    */ 
/*    */ public class SortVector extends Vector
/*    */ {
/*    */   private Compare compare;
/*    */ 
/*    */   public SortVector(Compare paramCompare)
/*    */   {
/* 25 */     this.compare = paramCompare;
/*    */   }
/*    */   public void sort() {
/* 28 */     quickSort(0, size() - 1);
/*    */   }
/*    */   private void quickSort(int paramInt1, int paramInt2) {
/* 31 */     if (paramInt2 > paramInt1) {
/* 32 */       Object localObject = elementAt(paramInt2);
/* 33 */       int i = paramInt1 - 1;
/* 34 */       int j = paramInt2;
/*    */       while (true) {
/* 36 */         i++; if (this.compare.lessThan(elementAt(i), localObject))
/*    */           continue;
/* 38 */         while (j > 0) {
/* 39 */           j--; if (this.compare.lessThanOrEqual(elementAt(j), localObject))
/* 40 */             break; 
/*    */         }
/* 41 */         if (i >= j) break;
/* 42 */         swapElements(i, j);
/*    */       }
/* 44 */       swapElements(i, paramInt2);
/* 45 */       quickSort(paramInt1, i - 1);
/* 46 */       quickSort(i + 1, paramInt2);
/*    */     }
/*    */   }
/*    */ 
/*    */   private void swapElements(int paramInt1, int paramInt2) {
/* 51 */     Object localObject = elementAt(paramInt1);
/* 52 */     setElementAt(elementAt(paramInt2), paramInt1);
/* 53 */     setElementAt(localObject, paramInt2);
/*    */   }
/*    */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     Serialio.tool.SortVector
 * JD-Core Version:    0.6.0
 */