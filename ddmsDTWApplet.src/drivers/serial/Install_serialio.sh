#!/bin/ksh
SERIALIO_LOG="install_serialio.log"
SERIALIO_SUCCESS="install-success.txt"

echo $(date) 1. COPYING JNILIB FILES  >> $SERIALIO_LOG
if cp *.jnilib ../ 2>> $SERIALIO_LOG ; then
	echo $(date) 2. Install successfully completed  >> $SERIALIO_LOG
	echo $(date) 2. Install successfully completed  > $SERIALIO_SUCCESS
else
	echo $(date) 2. Install failed  >> $SERIALIO_LOG
	if test -s $SERIALIO_SUCCESS ; then
	      rm $SERIALIO_SUCCESS	      
   	fi
fi