package minimed.ddms.deviceportreader;

import java.io.IOException;

abstract interface CommunicationsLink
{
  public abstract void initCommunications()
    throws IOException, SerialIOHaltedException, BadDeviceCommException;

  public abstract void endCommunications()
    throws IOException;

  public abstract void execute(DeviceCommand paramDeviceCommand)
    throws BadDeviceCommException, SerialIOHaltedException, BadDeviceValueException;

  public abstract void setListener(DeviceListener paramDeviceListener);

  public abstract void resetTotalReadByteCount();

  public abstract void setTotalReadByteCountExpected(int paramInt);

  public abstract void incTotalReadByteCount(int paramInt);

  public abstract CommPort getCommPort();

  public abstract void setCommPort(CommPort paramCommPort);
}

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.deviceportreader.CommunicationsLink
 * JD-Core Version:    0.6.0
 */