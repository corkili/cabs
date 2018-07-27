#!/usr/bin/env bash

bin=`dirname "$0"`
CABS_HOME=`cd "$bin/.."; pwd`

echo $CABS_HOME

nohup java -jar $CABS_HOME/lib/cabs-2.0.0.jar -Dspring.config.location=$CABS_HOME/config/application.properties > ../cabs.log 2>&1 &

PID=`ps aux | grep $CABS_HOME | grep -v grep | awk '{print $2}'`

echo "start successfully, pid: $PID"
