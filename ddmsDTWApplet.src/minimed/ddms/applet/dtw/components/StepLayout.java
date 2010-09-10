/*     */ package minimed.ddms.applet.dtw.components;
/*     */ 
/*     */ import java.awt.CardLayout;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import minimed.util.Contract;
/*     */ 
/*     */ public final class StepLayout extends CardLayout
/*     */ {
/*  33 */   private static final String LINE_SEP = System.getProperty("line.separator");
/*     */   private static final String SPACE = " ";
/*  42 */   private final HashMap m_namesToSteps = new HashMap();
/*     */ 
/*  48 */   private final HashMap m_stepsToNames = new HashMap();
/*     */ 
/*  54 */   private final ArrayList m_stepsShown = new ArrayList();
/*     */ 
/*     */   public String toString()
/*     */   {
/*  65 */     StringBuffer localStringBuffer = new StringBuffer();
/*  66 */     Iterator localIterator = getSteps();
/*     */     Object localObject;
/*  67 */     while (localIterator.hasNext()) {
/*  68 */       localObject = localIterator.next();
/*  69 */       localStringBuffer.append(localObject);
/*  70 */       if (localIterator.hasNext()) {
/*  71 */         localStringBuffer.append(LINE_SEP);
/*     */       }
/*     */     }
/*  74 */     if (localStringBuffer.length() > 0) {
/*  75 */       localStringBuffer.append(LINE_SEP);
/*     */     }
/*     */ 
/*  79 */     localIterator = this.m_stepsShown.iterator();
/*  80 */     while (localIterator.hasNext()) {
/*  81 */       localObject = localIterator.next();
/*  82 */       String str = (String)this.m_stepsToNames.get(localObject);
/*  83 */       localStringBuffer.append(str);
/*  84 */       if (localIterator.hasNext()) {
/*  85 */         localStringBuffer.append(" ");
/*     */       }
/*     */     }
/*  88 */     return localStringBuffer.toString();
/*     */   }
/*     */ 
/*     */   public void addLayoutComponent(Component paramComponent, Object paramObject)
/*     */   {
/*  98 */     super.addLayoutComponent(paramComponent, paramObject);
/*  99 */     this.m_namesToSteps.put(paramObject, paramComponent);
/* 100 */     this.m_stepsToNames.put(paramComponent, paramObject);
/*     */   }
/*     */ 
/*     */   public void show(Container paramContainer, String paramString)
/*     */   {
/* 110 */     super.show(paramContainer, paramString);
/* 111 */     Component localComponent = getStep(paramString);
/* 112 */     this.m_stepsShown.add(localComponent);
/* 113 */     localComponent.setVisible(true);
/*     */   }
/*     */ 
/*     */   public void showPreviousStep(Container paramContainer, int paramInt)
/*     */   {
/* 127 */     Contract.pre(this.m_stepsShown.size() > paramInt - 1);
/*     */ 
/* 130 */     for (int i = 1; i <= paramInt; i++) {
/* 131 */       this.m_stepsShown.remove(this.m_stepsShown.size() - 1);
/*     */     }
/*     */ 
/* 135 */     super.show(paramContainer, getCurrentStep().getName());
/*     */   }
/*     */ 
/*     */   public Component getCurrentStep()
/*     */   {
/* 144 */     return getStep(1);
/*     */   }
/*     */ 
/*     */   public Component getPreviousStep()
/*     */   {
/* 153 */     return getStep(2);
/*     */   }
/*     */ 
/*     */   public Component getStep(String paramString)
/*     */   {
/* 164 */     Contract.preString(paramString);
/* 165 */     return (Component)this.m_namesToSteps.get(paramString);
/*     */   }
/*     */ 
/*     */   public Component getStep(Class paramClass)
/*     */   {
/* 178 */     Contract.preNonNull(paramClass);
/* 179 */     Object localObject = null;
/* 180 */     Iterator localIterator = getSteps();
/* 181 */     while (localIterator.hasNext()) {
/* 182 */       Component localComponent = (Component)localIterator.next();
/* 183 */       if (paramClass.isInstance(localComponent)) {
/* 184 */         localObject = localComponent;
/*     */       }
/*     */     }
/*     */ 
/* 188 */     return localObject;
/*     */   }
/*     */ 
/*     */   Iterator getSteps()
/*     */   {
/* 201 */     ArrayList localArrayList = new ArrayList();
/* 202 */     for (int i = this.m_stepsShown.size() - 1; i >= 0; i--) {
/* 203 */       Object localObject = this.m_stepsShown.get(i);
/* 204 */       if (!localArrayList.contains(localObject)) {
/* 205 */         localArrayList.add(localObject);
/*     */       }
/*     */     }
/* 208 */     return localArrayList.iterator();
/*     */   }
/*     */ 
/*     */   private Component getStep(int paramInt)
/*     */   {
/* 219 */     int i = this.m_stepsShown.size();
/* 220 */     Component localComponent = null;
/* 221 */     if (i >= paramInt) {
/* 222 */       localComponent = (Component)this.m_stepsShown.get(i - paramInt);
/*     */     }
/* 224 */     return localComponent;
/*     */   }
/*     */ }

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.applet.dtw.components.StepLayout
 * JD-Core Version:    0.6.0
 */