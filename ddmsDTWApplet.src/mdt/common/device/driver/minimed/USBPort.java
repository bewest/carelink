package mdt.common.device.driver.minimed;

import java.io.IOException;

public abstract interface USBPort
{
  public abstract void initCommunications()
    throws IOException;

  public abstract boolean hasHandle();

  public abstract void clearBuffers()
    throws IOException;

  public abstract void endCommunications();

  public abstract byte[] read()
    throws IOException;

  public abstract byte[] read(int paramInt)
    throws IOException;

  public abstract void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException;

  public abstract void write(byte[] paramArrayOfByte)
    throws IOException;

  public abstract String getVersion();
}

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     mdt.common.device.driver.minimed.USBPort
 * JD-Core Version:    0.6.0
 */