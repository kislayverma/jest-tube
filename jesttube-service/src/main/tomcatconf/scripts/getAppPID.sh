ps -eaf | grep tomcat-juli.jar | grep -v grep | grep silkroute-tomcat | awk '{print $2}'

