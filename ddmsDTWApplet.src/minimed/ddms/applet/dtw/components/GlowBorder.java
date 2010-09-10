/*     */ package minimed.ddms.applet.dtw.components;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Graphics;
/*     */ import javax.swing.border.LineBorder;
/*     */ 
/*     */ public class GlowBorder extends LineBorder
/*     */ {
/*  28 */   private static final Color SEE_THROUGH = new Color(255, 255, 255, 0);
/*  29 */   private static final Color LINE1 = new Color(250, 185, 109);
/*  30 */   private static final Color LINE2 = new Color(250, 193, 126);
/*  31 */   private static final Color LINE3 = new Color(252, 208, 158);
/*  32 */   private static final Color LINE4 = new Color(253, 232, 206);
/*  33 */   private static final Color CORNER1 = new Color(251, 195, 129);
/*  34 */   private static final Color CORNER2 = new Color(252, 208, 157);
/*  35 */   private static final Color CORNER3 = new Color(253, 227, 196);
/*  36 */   private static final Color CORNER4 = new Color(254, 245, 234);
/*  37 */   private static final Color ACORNER2 = new Color(252, 221, 183);
/*  38 */   private static final Color ACORNER3 = new Color(254, 237, 218);
/*  39 */   private static final Color ACORNER4 = new Color(255, 252, 248);
/*  40 */   private static final Color BCORNER3 = new Color(255, 249, 243);
/*  41 */   private static final Color[] LINES = { LINE1, LINE2, LINE3, LINE4 };
/*  42 */   private static final Color[] DIAGONAL = { CORNER1, ACORNER2, BCORNER3, SEE_THROUGH };
/*     */ 
/*  44 */   private static final Color[] DIAGONAL2 = { CORNER2, ACORNER3 };
/*  45 */   private static final GlowBorder INSTANCE = new GlowBorder();
/*     */ 
/*     */   private GlowBorder()
/*     */   {
/*  53 */     super(SEE_THROUGH, 4, true);
/*     */   }
/*     */ 
/*     */   public static GlowBorder getInstance()
/*     */   {
/*  63 */     return INSTANCE;
/*     */   }
/*     */ 
/*     */   public void paintBorder(Component paramComponent, Graphics paramGraphics, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*     */   {
/*  78 */     Color localColor = paramGraphics.getColor();
/*  79 */     paramInt3--;
/*  80 */     paramInt4--;
/*  81 */     drawBorderMinusCorners(paramGraphics, paramInt1, paramInt2, paramInt3, paramInt4);
/*  82 */     drawCornerSecondaryDiagonals(paramGraphics, paramInt1, paramInt2, paramInt3, paramInt4);
/*  83 */     drawCornerMainDiagonals(paramGraphics, paramInt1, paramInt2, paramInt3, paramInt4);
/*  84 */     drawCornerRemainingPixels(paramGraphics, paramInt1, paramInt2, paramInt3, paramInt4);
/*  85 */     drawCornerSeeThroughPixels(paramGraphics, paramInt1, paramInt2, paramInt3, paramInt4);
/*  86 */     paramGraphics.setColor(localColor);
/*     */   }
/*     */ 
/*     */   private void drawBorderMinusCorners(Graphics paramGraphics, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*     */   {
/*  99 */     for (int i = 0; i < this.thickness; i++) {
/* 100 */       paramGraphics.setColor(LINES[i]);
/* 101 */       paramGraphics.drawLine(paramInt1 + 4, paramInt2 + 3 - i, paramInt1 + paramInt3 - 4, paramInt2 + 3 - i);
/* 102 */       paramGraphics.drawLine(paramInt1 + 4, paramInt2 + paramInt4 - 3 + i, paramInt1 + paramInt3 - 4, paramInt2 + paramInt4 - 3 + i);
/* 103 */       paramGraphics.drawLine(paramInt1 + 3 - i, paramInt2 + 4, paramInt1 + 3 - i, paramInt2 + paramInt4 - 4);
/* 104 */       paramGraphics.drawLine(paramInt1 + paramInt3 - 3 + i, paramInt2 + 4, paramInt1 + paramInt3 - 3 + i, paramInt2 + paramInt4 - 4);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void drawCornerSecondaryDiagonals(Graphics paramGraphics, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*     */   {
/* 117 */     for (int i = 0; i < 2; i++) {
/* 118 */       paramGraphics.setColor(DIAGONAL2[i]);
/* 119 */       paramGraphics.drawLine(paramInt1 + 2 - i, paramInt2 + 3 - i, paramInt1 + 3 - i, paramInt2 + 2 - i);
/* 120 */       paramGraphics.drawLine(paramInt1 + paramInt3 - 3 + i, paramInt2 + 2 - i, paramInt1 + paramInt3 - 2 + i, paramInt2 + 3 - i);
/*     */ 
/* 122 */       paramGraphics.drawLine(paramInt1 + 2 - i, paramInt2 + paramInt4 - 3 + i, paramInt1 + 3 - i, paramInt2 + paramInt4 - 2 + i);
/*     */ 
/* 124 */       paramGraphics.drawLine(paramInt1 + paramInt3 - 3 + i, paramInt2 + paramInt4 - 2 + i, paramInt1 + paramInt3 - 2 + i, paramInt2 + paramInt4 - 3 + i);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void drawCornerMainDiagonals(Graphics paramGraphics, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*     */   {
/* 138 */     for (int i = 0; i < 3; i++) {
/* 139 */       paramGraphics.setColor(DIAGONAL[i]);
/* 140 */       paramGraphics.drawLine(paramInt1 + 3 - i, paramInt2 + 3 - i, paramInt1 + 3 - i, paramInt2 + 3 - i);
/* 141 */       paramGraphics.drawLine(paramInt1 + paramInt3 - 3 + i, paramInt2 + 3 - i, paramInt1 + paramInt3 - 3 + i, paramInt2 + 3 - i);
/*     */ 
/* 143 */       paramGraphics.drawLine(paramInt1 + 3 - i, paramInt2 + paramInt4 - 3 + i, paramInt1 + 3 - i, paramInt2 + paramInt4 - 3 + i);
/*     */ 
/* 145 */       paramGraphics.drawLine(paramInt1 + paramInt3 - 3 + i, paramInt2 + paramInt4 - 3 + i, paramInt1 + paramInt3 - 3 + i, paramInt2 + paramInt4 - 3 + i);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void drawCornerRemainingPixels(Graphics paramGraphics, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*     */   {
/* 159 */     paramGraphics.setColor(CORNER3);
/* 160 */     paramGraphics.drawLine(paramInt1 + 3, paramInt2 + 1, paramInt1 + 3, paramInt2 + 1);
/* 161 */     paramGraphics.drawLine(paramInt1 + 1, paramInt2 + 3, paramInt1 + 1, paramInt2 + 3);
/* 162 */     paramGraphics.drawLine(paramInt1 + 1, paramInt2 + paramInt4 - 3, paramInt1 + 1, paramInt2 + paramInt4 - 3);
/* 163 */     paramGraphics.drawLine(paramInt1 + 3, paramInt2 + paramInt4 - 1, paramInt1 + 3, paramInt2 + paramInt4 - 1);
/* 164 */     paramGraphics.drawLine(paramInt1 + paramInt3 - 1, paramInt2 + 3, paramInt1 + paramInt3 - 1, paramInt2 + 3);
/* 165 */     paramGraphics.drawLine(paramInt1 + paramInt3 - 3, paramInt2 + 1, paramInt1 + paramInt3 - 3, paramInt2 + 1);
/* 166 */     paramGraphics.drawLine(paramInt1 + paramInt3 - 1, paramInt2 + paramInt4 - 3, paramInt1 + paramInt3 - 1, paramInt2 + paramInt4 - 3);
/*     */ 
/* 168 */     paramGraphics.drawLine(paramInt1 + paramInt3 - 3, paramInt2 + paramInt4 - 1, paramInt1 + paramInt3 - 3, paramInt2 + paramInt4 - 1);
/*     */ 
/* 170 */     paramGraphics.setColor(CORNER4);
/* 171 */     paramGraphics.drawLine(paramInt1 + 3, paramInt2, paramInt1 + 3, paramInt2);
/* 172 */     paramGraphics.drawLine(paramInt1, paramInt2 + 3, paramInt1, paramInt2 + 3);
/* 173 */     paramGraphics.drawLine(paramInt1, paramInt2 + paramInt4 - 3, paramInt1, paramInt2 + paramInt4 - 3);
/* 174 */     paramGraphics.drawLine(paramInt1 + 3, paramInt2 + paramInt4, paramInt1 + 3, paramInt2 + paramInt4);
/* 175 */     paramGraphics.drawLine(paramInt1 + paramInt3, paramInt2 + 3, paramInt1 + paramInt3, paramInt2 + 3);
/* 176 */     paramGraphics.drawLine(paramInt1 + paramInt3 - 3, paramInt2, paramInt1 + paramInt3 - 3, paramInt2);
/* 177 */     paramGraphics.drawLine(paramInt1 + paramInt3, paramInt2 + paramInt4 - 3, paramInt1 + paramInt3, paramInt2 + paramInt4 - 3);
/* 178 */     paramGraphics.drawLine(paramInt1 + paramInt3 - 3, paramInt2 + paramInt4, paramInt1 + paramInt3 - 3, paramInt2 + paramInt4);
/* 179 */     paramGraphics.setColor(ACORNER4);
/* 180 */     paramGraphics.drawLine(paramInt1 + 2, paramInt2, paramInt1 + 2, paramInt2);
/* 181 */     paramGraphics.drawLine(paramInt1, paramInt2 + 2, paramInt1, paramInt2 + 2);
/* 182 */     paramGraphics.drawLine(paramInt1, paramInt2 + paramInt4 - 2, paramInt1, paramInt2 + paramInt4 - 2);
/* 183 */     paramGraphics.drawLine(paramInt1 + 2, paramInt2 + paramInt4, paramInt1 + 2, paramInt2 + paramInt4);
/* 184 */     paramGraphics.drawLine(paramInt1 + paramInt3, paramInt2 + 2, paramInt1 + paramInt3, paramInt2 + 2);
/* 185 */     paramGraphics.drawLine(paramInt1 + paramInt3 - 2, paramInt2, paramInt1 + paramInt3 - 2, paramInt2);
/* 186 */     paramGraphics.drawLine(paramInt1 + paramInt3, paramInt2 + paramInt4 - 2, paramInt1 + paramInt3, paramInt2 + paramInt4 - 2);
/* 187 */     paramGraphics.drawLine(paramInt1 + paramInt3 - 2, paramInt2 + paramInt4, paramInt1 + paramInt3 - 2, paramInt2 + paramInt4);
/*     */   }
/*     */ 
/*     */   private void drawCornerSeeThroughPixels(Graphics paramGraphics, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*     */   {
/* 200 */     paramGraphics.setColor(SEE_THROUGH);
/* 201 */     paramGraphics.drawLine(paramInt1, paramInt2, paramInt1 + 1, paramInt2);
/* 202 */     paramGraphics.drawLine(paramInt1, paramInt2 + 1, paramInt1, paramInt2 + 1);
/* 203 */     paramGraphics.drawLine(paramInt1 + paramInt3 - 1, paramInt2, paramInt1 + paramInt3, paramInt2);
/* 204 */     paramGraphics.drawLine(paramInt1 + paramInt3, paramInt2 + 1, paramInt1 + paramInt3, paramInt2 + 1);
/* 205 */     paramGraphics.drawLine(paramInt1, paramInt2 + paramInt4 - 1, paramInt1 + 1, paramInt4 - 1);
/* 206 */     paramGraphics.drawLine(paramInt1, paramInt2 + paramInt4, paramInt1, paramInt2 + paramInt4);
/* 207 */     paramGraphics.drawLine(paramInt1 + paramInt3 - 1, paramInt2 + paramInt4, paramInt1 + paramInt3, paramInt2 + paramInt4);
/* 208 */     paramGraphics.drawLine(paramInt1 + paramInt3, paramInt2 + paramInt4 - 1, paramInt1 + paramInt3, paramInt2 + paramInt4 - 1);
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.components.GlowBorder
 * JD-Core Version:    0.6.0
 */