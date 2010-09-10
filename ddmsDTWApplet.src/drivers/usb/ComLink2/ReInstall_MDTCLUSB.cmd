@echo off
rem Windows batch file used to re-install the Medtronic CareLink USB Driver
rem Copyright (c) 2007 by Medtronic Inc
rem The files needed for install should be available in local folder from where the reinstall batch file is executed
rem The Medtronic CareLink USB device should be detached while this batch is executed
rem This file obtained from CareLink Pro with the following modifications:
rem   a. adds 'silent' and 'log' options.

rem call the utility to install the driver package (Jungo)
echo on

wdreg -silent -log CL_USB_Setup.log -inf windrvr6.inf install
wdreg -silent -log CL_USB_Setup.log -inf MDTCLUSB.inf install

echo Completed installing Medtronic CareLink USB Driver
@echo off