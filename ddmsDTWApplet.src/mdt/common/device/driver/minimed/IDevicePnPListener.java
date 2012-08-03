package mdt.common.device.driver.minimed;

public abstract interface IDevicePnPListener
{
  public abstract void deviceAttached(int paramInt);

  public abstract void deviceDetached(int paramInt);
}

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     mdt.common.device.driver.minimed.IDevicePnPListener
 * JD-Core Version:    0.6.0
 */