package minimed.ddms.A;

import java.util.EventListener;

public abstract interface v extends EventListener
{
  public abstract long getLastHistoryPageNumber(String paramString1, String paramString2);

  public abstract long getLastGlucoseHistoryPageNumber(String paramString1, String paramString2);

  public abstract void allowDeviceOperation(G paramG)
    throws P;

  public abstract void deviceUpdateProgress(int paramInt);

  public abstract void deviceUpdateState(int paramInt, String paramString);

  public abstract void deviceUpdatePhase(int paramInt, String paramString);

  public abstract boolean isHaltRequested();
}

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.A.v
 * JD-Core Version:    0.6.0
 */