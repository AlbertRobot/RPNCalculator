#!/usr/bin/env bash

export PATH=$JAVA_HOME/bin:$PATH

BATCH_HOME=$(cd $(dirname $0);cd ..;pwd)

LIB_ROOT=$BATCH_HOME/lib

CLASSPATH=$CLASSPATH:$LIB_ROOT/*

JAVA_OPTS="-Xms4096m -Xmx4096m -XX:NewSize=512m -server -XX:+DisableExplicitGC -verbose:gc -XX:+PrintGCDateStamps -XX:+PrintGCDetails -Xloggc:logs/gc.log"

java -classpath $CLASSPATH $JAVA_OPTS com.aw.Executor

RETVAL=$?

exit $RETVAL