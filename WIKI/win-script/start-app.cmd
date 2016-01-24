call setvars.cmd
if not defined APP_BACKEND_LOG_FILE set APP_BACKEND_LOG_FILE=%LOG_DIR%\app-log.log
echo Start application
echo Logging into %APP_BACKEND_LOG_FILE%...
cd %SOURCE_DIR%\app-re\target
call java -jar application-0.0.1-SNAPSHOT.jar  >  %APP_BACKEND_LOG_FILE% 2>&1
 