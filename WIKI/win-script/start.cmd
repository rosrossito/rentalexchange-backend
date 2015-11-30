rem TODO set java opt
java -XX:+PrintFlagsFinal -Xms512m -Xmx1024m -Xss512k -XX:PermSize=64m -XX:MaxPermSize=128m -version | findstr /i "HeapSize PermSize ThreadStackSize"
cd ../../app-re/target
java -jar application-0.0.1-SNAPSHOT.jar