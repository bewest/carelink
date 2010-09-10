package Serialio;

import java.util.EventListener;

public abstract interface SnoopTokenListener extends EventListener
{
  public abstract void snoopTokenEvent(byte[] paramArrayOfByte, int paramInt);
}

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     Serialio.SnoopTokenListener
 * JD-Core Version:    0.6.0
 */