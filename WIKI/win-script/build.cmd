rem INSTRUCTION:
rem instad of '-gs WIKI/maven/settings.xml' - setup path to settings.xml stored on instance
call setvars.cmd

if not defined BUILD_BACKEND_LOG_FILE set BUILD_BACKEND_LOG_FILE=%LOG_DIR%\build-backend.log
echo Start build..
echo Logging into %BUILD_BACKEND_LOG_FILE%
cd rentalexchange-backend                                                                   
mvn -gs ../maven/settings.xml -PliquibaseProps -PdeployDBSchema clean install >  %BUILD_BACKEND_LOG_FILE% 2>&1
