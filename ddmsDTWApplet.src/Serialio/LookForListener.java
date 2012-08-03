package Serialio;

import java.util.EventListener;

public abstract interface LookForListener extends EventListener
{
  public abstract void eventLookForData(byte[] paramArrayOfByte1, boolean paramBoolean, long paramLong, byte[] paramArrayOfByte2);
}

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     Serialio.LookForListener
 * JD-Core Version:    0.6.0
 */