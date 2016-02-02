call setvars.cmd
if not defined BUILD_BACKEND_LOG_FILE set BUILD_BACKEND_LOG_FILE=%LOG_DIR%\build-backend.log
echo Build application
echo Logging into %BUILD_BACKEND_LOG_FILE%  ...
cd %SOURCE_DIR%                                                                   
mvn clean install -gs WIKI\maven\settings.xml -PliquibaseProps -PdeployDBSchema  >  %BUILD_BACKEND_LOG_FILE% 2>&1
