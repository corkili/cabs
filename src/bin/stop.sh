#!/usr/bin/env bash

bin=`dirname "$0"`
CABS_HOME=`cd "$bin/.."; pwd`

echo $CABS_HOME

PID=`ps aux | grep $CABS_HOME | grep -v grep | awk '{print $2}'`

if [ "$PID" == "" ]; then
    echo "process not exists"
else
    echo "killing process with pid $PID"
    kill -9 $PID
fi