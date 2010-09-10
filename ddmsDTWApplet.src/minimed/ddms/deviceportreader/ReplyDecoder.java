package minimed.ddms.deviceportreader;

abstract interface ReplyDecoder
{
  public abstract void decodeReply(LSMeter.AbstractCommand paramAbstractCommand)
    throws BadDeviceValueException;
}

/* Location:           /home/bewest/Documents/bb/carelink/ddmsDTWApplet.jar
 * Qualified Name:     minimed.ddms.deviceportreader.ReplyDecoder
 * JD-Core Version:    0.6.0
 */