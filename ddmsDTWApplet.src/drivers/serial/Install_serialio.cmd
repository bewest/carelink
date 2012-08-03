@echo off
rem Windows batch file used to install the SerialIO DLL files.
rem Copyright (c) 2009 by Medtronic Inc
rem Install consists of copying all DLL files to the run-time directory,
rem one level from the install directory.

set INSTALL_LOG=install_serialio.log
set INSTALL_FLAG=install-success.txt

rem Remove the "install success" flag & only write after driver is successfully installed.
del %INSTALL_FLAG% > Nul

rem APPEND LOG FILE
echo %date% %time%: 1. COPYING DLL FILES >> %INSTALL_LOG%
copy *.dll "..\"

echo %date% %time%: 2. Install successfully completed >> %INSTALL_LOG%
echo %date% %time%: 2. Install successfully completed > %INSTALL_FLAG%

:end
Rem End with separator
echo %date% %time%: ============================================================  >> %INSTALL_LOG%
