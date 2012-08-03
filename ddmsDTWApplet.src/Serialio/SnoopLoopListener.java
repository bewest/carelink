package Serialio;

import java.util.EventListener;

public abstract interface SnoopLoopListener extends EventListener
{
  public abstract void snoopLoopEvent(byte[] paramArrayOfByte, int paramInt);
}

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     Serialio.SnoopLoopListener
 * JD-Core Version:    0.6.0
 */