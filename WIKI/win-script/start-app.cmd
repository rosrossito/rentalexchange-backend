call setvars.cmd
if not defined APP_BACKEND_LOG_FILE set APP_BACKEND_LOG_FILE=%LOG_DIR%\app-log.log
echo Start application
echo Logging into %APP_BACKEND_LOG_FILE%...
cd %SOURCE_DIR%\app-re\target
                                                                                                                      
call java -jar application-0.0.1-SNAPSHOT.jar --spring.config.location=%APP_CONFIG%  >  %APP_BACKEND_LOG_FILE% 2>&1
rem java -jar project.jar --spring.config.location=/usr/local/etc/application.properties
						    
 