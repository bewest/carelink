@echo off
rem Windows batch file used to pre-install the Medtronic CareLink USB Driver for Win32 systems.
rem Copyright (c) 2007-2009 by Medtronic Inc
rem The Medtronic CareLink USB device should be detached while this batch is executed.
rem Returns error code of 99 if driver install is unsuccessful, 0 (default) otherwise.
rem Writes success indicator file if successful.
rem Script can be manually executed to install driver on 32-bit platforms.

rem Install CareLink USB Driver to All Users custom folder

set JNI_DLL_DIR=%ALLUSERSPROFILE%\Application Data\Medtronic\ddmsDTWusb\ComLink2
set JNI_DLL=cl2_jni_wrapper.dll
set INSDIR=%JNI_DLL_DIR%\Jungo 10.10\Win32
set INSCMD="%INSDIR%\wdreg.exe"
set INF1="%INSDIR%\windrvr6.inf"
set INF2="%INSDIR%\MDTCLUSB.inf"
set LOG="%INSDIR%"\install_MDTCLUSB-32.log
set INSTALL_FLAG=install-success.txt

rem Remove the "install success" flag & only write after driver is successfully installed.
del %INSTALL_FLAG% > Nul

rem Driver install log file so the number of STATUS_SUCCESS occurrences can be counted.
del %LOG% > Nul

echo %date% %time%: 1. INSTALLING 32-BIT JUNGO DRIVER--BEGIN... >> %LOG%
echo %date% %time%: 2. Copying Medtronic CareLink USB Driver files from %INSDIR% to %JNI_DLL_DIR% >> %LOG%

rem copy run-time dll to run-time location.
rem copy 32-bit or 64-bit dll to DLL dir, but keep name the same so underlying code does not change.
copy "%INSDIR%"\%JNI_DLL% "%JNI_DLL_DIR%"

rem call the utility to install the driver package (Jungo)
echo %date% %time%: 3. Installing Medtronic CareLink USB Driver files >> %LOG%

echo %date% %time%: 3.1 Medtronic CareLink USB Driver installation: uninstall %INF1%
%INSCMD% -silent -log %LOG% -inf %INF1% uninstall

echo %date% %time%: 3.2 Medtronic CareLink USB Driver installation: uninstall %INF2%
%INSCMD% -silent -log %LOG% -inf %INF2% uninstall

echo %date% %time%: 3.3 Medtronic CareLink USB Driver installation: install %INF1%
%INSCMD% -silent -log %LOG% -inf %INF1% install

echo %date% %time%: 3.4 Medtronic CareLink USB Driver installation: install %INF2%
%INSCMD% -silent -log %LOG% -inf %INF2% install

REM CHECK STATUS OF DRIVER INSTALL EXECUTION; RETURN CODE FROM WDREG IS NOT GOOD ENOUGH FOR THIS.
rem wdreg cmd writes STATUS_SUCCESS to log file if install is good, STATUS_FAILURE otherwise.
rem Count number of occurrences of STATUS_SUCCESS, there should be at least one.

set GREP_FILE=result-grep-32.txt
findstr "STATUS_SUCCESS" %LOG% > "%GREP_FILE%"

rem count number of lines in GREP_FILE.
set /a COUNT=0
for /f %%a in ('type "%GREP_FILE%"^|find "" /v /c') do set /a COUNT=%%a
echo %date% %time%: 4. "%GREP_FILE%" has %COUNT% lines >> %LOG%

if %COUNT% EQU 0 (
    echo %date% %time%: 5. INSTALL FAILED >> %LOG%
    rem Exit with custom return code.
    exit /b 99
)

echo %date% %time%: 5. install successful! >> %LOG%
echo %date% %time%: 6. INSTALLING 32-BIT JUNGO DRIVER--END. >> %LOG%

rem Install successful - write "Install Success" flag.
echo %date% %time%: Install completed using "%INSDIR%"\wdreg commands > %INSTALL_FLAG%
