package Serialio;

import java.util.EventListener;

public abstract interface SnoopListener extends EventListener
{
  public abstract void snoopEvent(byte[] paramArrayOfByte);
}

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     Serialio.SnoopListener
 * JD-Core Version:    0.6.0
 */