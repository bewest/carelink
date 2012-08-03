@echo off
rem Windows batch file used to pre-install the Bayer USB Driver
rem Copyright (c) 2010 by Medtronic Inc
rem Copies both 32-bit and 64-bit versions of the dll files.
rem Note, when running 32-bit browser on 64 bit platform, OS reports as 32-bits.
rem To deal with this problem, if the 32-bit install fails, the 64-bit install is called.

set INSTALL_FLAG=install-success.txt
del %INSTALL_FLAG% > Nul

//copies dll files to Medtronic\ddmsDTWusb\Bayer\
copy Win32\*.dll ..\
copy Win64\*.dll ..\

rem Install successful - write "Install Success" flag.
echo %date% Install successfully completed > %INSTALL_FLAG%
