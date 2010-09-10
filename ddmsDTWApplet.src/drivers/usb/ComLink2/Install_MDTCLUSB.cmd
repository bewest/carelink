@echo off
rem Windows batch file used to pre-install the Medtronic CareLink USB Driver
rem Copyright (c) 2007 by Medtronic Inc
rem The Medtronic CareLink USB device should be detached while this batch is executed
rem This file obtained from CareLink Pro with the following modifications:
rem   a. installs to user home directory rather than to "Program Files" directory.
rem   b. copies cl2_jni_wrapper.dll
rem   c. adds 'silent' and 'log' options.

rem Install CareLink USB Driver to User custom folder
set INSDIR=%USERPROFILE%\Medtronic\ddmsDTWusb\ComLink2\Jungo 8.1.1
echo Copying Medtronic CareLink USB Driver files to %INSDIR%
mkdir "%INSDIR%" > NUL 2>NUL
copy wd811.cat "%INSDIR%"
copy windrvr6.inf "%INSDIR%"
copy windrvr6.sys "%INSDIR%"
copy MDTCLUSB.inf "%INSDIR%"
copy mdtclusb.cat "%INSDIR%"
copy difxapi.dll "%INSDIR%"
copy wdreg.exe "%INSDIR%"
copy wdreg_gui.exe "%INSDIR%"
copy ReInstall_MDTCLUSB.cmd "%INSDIR%"

rem copy run-time dll to run-time location.
set DLLDIR=%USERPROFILE%\Medtronic\ddmsDTWusb\ComLink2
copy cl2_jni_wrapper.dll "%DLLDIR%"

rem call the utility to install the driver package (Jungo)
echo on

wdreg -silent -log CL_USB_Setup.log -inf "%INSDIR%"\windrvr6.inf install
wdreg -silent -log CL_USB_Setup.log -inf "%INSDIR%"\MDTCLUSB.inf install

echo Completed installing Medtronic CareLink USB Driver
@echo off