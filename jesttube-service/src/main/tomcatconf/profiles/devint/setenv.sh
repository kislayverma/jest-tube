export CATALINA_PID="/myntra/pid/silkroute.pid"
export JRE_HOME=/usr/java/java7/
export JAVA_OPTS="-Xms256m -Xmx256m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/myntra/silkroute/service/releases/current/heapdump/ -XX:MaxPermSize=256m -Dspring.profiles.active=qa -Dorg.apache.cxf.Logger=org.apache.cxf.common.logging.Log4jLogger  -Djavamelody.http-transform-pattern='\d+' -Djavamelody.storage-directory=/myntra/silkroute/service/releases/javamelody/"

