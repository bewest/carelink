@echo off
rem Windows batch file used to pre-install the Medtronic CareLink USB Driver
rem Copyright (c) 2007-2009 by Medtronic Inc
rem The Medtronic CareLink USB device should be detached while this batch is executed.
rem Determines if OS is 32 or 64 bits and calls appropriate driver install cmd.
rem Note, when running 32-bit browser on 64 bit platform, OS reports as 32-bits.
rem To deal with this problem, if the 32-bit install fails, the 64-bit install is called.

set INSTALL_LOG=install_MDTCLUSB.log
set INSTALL_EXE=unknown
set INSTALL_32=Install_MDTCLUSB-32.cmd
set INSTALL_64=Install_MDTCLUSB-64.cmd
set REINSTALL_EXE=%INSTALL_64%

rem APPEND LOG FILE
echo %date% %time%: 1. DETERMINING CPU TYPE >> %INSTALL_LOG%
rem Install of driver version (32 or 64 bits) depends upon CPU platform.

if "%PROCESSOR_ARCHITECTURE%"=="x86" set INSTALL_EXE=%INSTALL_32%
if "%PROCESSOR_ARCHITECTURE%"=="AMD64" set INSTALL_EXE=%INSTALL_64%
echo %date% %time%: 2. INSTALL_EXE=%INSTALL_EXE% >> %INSTALL_LOG%
if %INSTALL_EXE%==unknown goto error

rem Execute the pre-install.
echo %date% %time%: 3. INSTALLING CareLink USB Driver USING %INSTALL_EXE% >> %INSTALL_LOG%
call %INSTALL_EXE% >> %INSTALL_LOG%

echo %date% %time%: 4. Completed installing Medtronic CareLink USB Driver with error code %errorlevel% >> %INSTALL_LOG%

rem IF INSTALLED FAILED, TRY 64-BIT INSTALL (custom error code)
if %errorlevel% EQU 99 (
    echo %date% %time%: 4.1 Driver Install Failed-trying 64-bit install... >> %INSTALL_LOG%
    echo %date% %time%: 4.2 INSTALLING CareLink USB Driver USING %REINSTALL_EXE% >> %INSTALL_LOG%
    call %REINSTALL_EXE% >> %INSTALL_LOG%
    echo %date% %time%: 4.3 Completed re-installing Medtronic CareLink USB Driver with error code %errorlevel% >> %INSTALL_LOG%
)

rem CATCH ALL FINAL ERRORS HERE.
if %errorlevel% NEQ 0 (
    echo %date% %time%: 4.4 Driver Install Failed with error code %errorlevel% >> %INSTALL_LOG%
    goto end:
)

rem All is correct.
echo %date% %time%: 5. SUCCESS! >> %INSTALL_LOG%
goto end:

:error
echo %date% %time%: 6. ERROR cannot determine cpu type >> %INSTALL_LOG%
goto end

:end
Rem End with separator
echo %date% %time%: ============================================================  >> %INSTALL_LOG%

exit /b %errorlevel%
