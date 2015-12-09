rem TODO set maven opt

set MAVEN_OPTS=-Xmx512m -XX:MaxPermSize=128m
cd ../../app-re
mvn -T 10 -gs ../WIKI/maven/settings.xml clean install 
