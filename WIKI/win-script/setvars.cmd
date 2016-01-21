rem Path to JDK installation folder
set JAVA_HOME=C:\Program Files\Java\jdk1.7.0_79
set JAVA_OPTS=-Xms1024m -Xmx4000m -XX:MaxPermSize=2048m -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=8011 -Dorg.apache.el.parser.SKIP_IDENTIFIER_CHECK=true
set MAVEN_OPTS=-Xms1024m -Xmx5000m -XX:MaxPermSize=1024m

set COMMON_USER=postgres
set COMMON_PASS=qa1234

set DB_SERVER=localhost
set DB_PORT=5432
                                                               
set LOG_DIR=D:\Oleg\logs
                       
