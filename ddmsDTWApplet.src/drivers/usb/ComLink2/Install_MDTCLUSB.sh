#!/bin/ksh
USB_LOG="install_MDTCLUSB.log"
USB_SUCCESS="install-success.txt"

echo $(date) 1. COPYING DYLIB FILES  >> $USB_LOG
if cp Mac/libCareLinkUSB.dylib ../ 2>> $USB_LOG ; then
	echo $(date) 2. Install successfully completed  >> $USB_LOG
	echo $(date) 2. Install successfully completed  > $USB_SUCCESS
else
	echo $(date) 2. Install failed  >> $USB_LOG
	if test -s $USB_SUCCESS ; then
	      rm $USB_SUCCESS	      
   	fi
fi