# // Mac shell file used to pre-install the Bayer USB Driver
# // Copyright (c) 2010 by Medtronic Inc
# // Copies dll files of Mac driver. Same version works for 32 and 64 bit.
#!/bin/ksh
USB_SUCCESS="install-success.txt"

if test -s $USB_SUCCESS ; then
	rm $USB_SUCCESS	      
fi

if cp Mac/*.dll ../ ; then
	echo $(date) Install successfully completed  > $USB_SUCCESS
fi