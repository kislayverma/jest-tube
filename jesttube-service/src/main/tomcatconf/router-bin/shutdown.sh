#!/bin/sh

export CATALINA_BASE=/myntra/silkroute/service/releases/current/silkroute-tomcat
export CATALINA_HOME=/myntra/lib/tomcat
$CATALINA_HOME/bin/catalina.sh stop -force
