package Serialio;

import java.io.IOException;

public abstract interface SerialPort
{
  public static final int RXREADYCOUNT = 10;

  public abstract void setConfig(SerialConfig paramSerialConfig);

  public abstract SerialConfig getConfig();

  public abstract void configure(SerialConfig paramSerialConfig)
    throws IOException;

  public abstract void close()
    throws IOException;

  public abstract void putByte(byte paramByte)
    throws IOException;

  public abstract void putString(String paramString)
    throws IOException;

  public abstract void putData(byte[] paramArrayOfByte)
    throws IOException;

  public abstract void putData(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException;

  public abstract int getByte()
    throws IOException;

  public abstract int getData(byte[] paramArrayOfByte)
    throws IOException;

  public abstract int getData(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException;

  public abstract int rxFlush()
    throws IOException;

  public abstract int txFlush()
    throws IOException;

  public abstract int txDrain()
    throws IOException;

  public abstract int rxReadyCount()
    throws IOException;

  public abstract int txBufCount()
    throws IOException;

  public abstract boolean rxOverflow()
    throws IOException;

  public abstract boolean sigDSR()
    throws IOException;

  public abstract boolean sigCTS()
    throws IOException;

  public abstract boolean sigCD()
    throws IOException;

  public abstract boolean sigFrameErr()
    throws IOException;

  public abstract boolean sigOverrun()
    throws IOException;

  public abstract boolean sigParityErr()
    throws IOException;

  public abstract boolean sigRing()
    throws IOException;

  public abstract boolean sigBreak()
    throws IOException;

  public abstract void setDTR(boolean paramBoolean)
    throws IOException;

  public abstract void setRTS(boolean paramBoolean)
    throws IOException;

  public abstract void sendBreak(int paramInt)
    throws IOException;

  public abstract int getTimeoutRx()
    throws IOException;

  public abstract int getTimeoutTx()
    throws IOException;

  public abstract void setTimeoutRx(int paramInt)
    throws IOException;

  public abstract void setTimeoutTx(int paramInt)
    throws IOException;

  public abstract int getLibVer()
    throws IOException;

  public abstract int getPortNum();

  public abstract void setName(String paramString);

  public abstract String getName();

  public abstract boolean isSupported(String paramString);
}

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     Serialio.SerialPort
 * JD-Core Version:    0.6.0
 */