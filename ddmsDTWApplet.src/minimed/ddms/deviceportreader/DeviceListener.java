package minimed.ddms.deviceportreader;

import java.util.EventListener;

public abstract interface DeviceListener extends EventListener
{
  public abstract long getLastHistoryPageNumber(String paramString1, String paramString2);

  public abstract long getLastGlucoseHistoryPageNumber(String paramString1, String paramString2);

  public abstract void allowDeviceOperation(DevicePortReader paramDevicePortReader)
    throws ConnectToPumpException;

  public abstract void deviceUpdateProgress(int paramInt);

  public abstract void deviceUpdateState(int paramInt, String paramString);

  public abstract void deviceUpdatePhase(int paramInt, String paramString);

  public abstract boolean isHaltRequested();
}

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.deviceportreader.DeviceListener
 * JD-Core Version:    0.6.0
 */