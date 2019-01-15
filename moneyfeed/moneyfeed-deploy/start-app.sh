#!/usr/bin/env bash  
#
# Startup script for webapp under *nix systems (it works under NT/cygwin too).

CMD_PATH=$(dirname $0)
echo "current cmd path:$CMD_PATH"
cd $CMD_PATH

CURRENT_DIR=$PWD
APP_HOME=$CURRENT_DIR/webapp
SPRING_PROFILES=$3
ACTION=$2
APP_NAME=$1
APP_RUN=$CURRENT_DIR
APP_PID="$APP_RUN/app.pid"
CHECK_URL=$(cat "check.url")

JAVA_OPTION=" -Xmx2048m -Xms2048m -XX:NewRatio=2 -XX:+UseParNewGC -XX:ParallelGCThreads=2 -XX:+UseConcMarkSweepGC "
JAVA_OPTION="$JAVA_OPTION -verbose:gc -XX:+PrintGCTimeStamps -XX:+PrintGCDetails -Xloggc:/var/log/webapps/$APP_NAME/jvm.log"

echo $CHECK_URL
echo $SPRING_PROFILES
CONFIGS=()

checkst()
{
  rm -f ck.json
  curl "$1" 2>/dev/null > ck.json
  STATUS=$(sh ./JSON.sh -l < ck.json 2>/dev/null |grep status|head -1|awk -F '\t' '{print $2}'|sed 's/"//g')
  if [ "$STATUS" == "UP" ]; then
     return 0
  fi
  return 1
}

usage()
{
    echo "Usage: ${0##*/} [-d] {start|stop} [ CONFIGS ... ] "
    exit 1
}

[ $# -gt 0 ] || usage


case "$ACTION" in
  start)
    nohup java -jar $JAVA_OPTION -Dspring.cloud.config.label=$NEWHOPE_PROFILE_GROUP $APP_HOME/ROOT.war profile=$SPRING_PROFILES >/dev/null 2>nohup.out &
    disown $!
    echo $! > "$APP_PID"
    echo "-----startup-------"
    
    COUNTER=1
    echo "$COUNTER"
    checkst "$CHECK_URL"
    CK=$?
    while [ $CK -gt 0 -a $COUNTER -lt 200 ]; do
      sleep 1
      let COUNTER=COUNTER+1
      echo $COUNTER
      checkst "$CHECK_URL"
      CK=$?
    done
    if [ $CK -eq 0 ]; then
     echo "startup ending!!"
    fi
    ;;
  stop)
    echo "----kill----"
    PID=$(cat "$APP_PID" 2>/dev/null)
    if [ "$PID" == "" ];then
      echo "not found pid!" 
      exit 0
    fi
    kill -9 "$PID" 2>/dev/null
    sleep 1
    COUNTER=1
    echo "$COUNTER"
    JPCOUNT=$(ps -aef |grep -v grep|awk -F ' ' '{print $2"\t"$3}' |grep "\<$PID\>" | wc -l) 
    while [ "$JPCOUNT" != "0" ]; do
       sleep 1
       let COUNTER=COUNTER+1
       echo "$COUNTER"
       JPCOUNT=$(ps -aef |grep -v grep|awk -F ' ' '{print $2"\t"$3}' |grep "\<$PID\>" | wc -l)
    done
    sleep 1
    rm -f "$APP_PID"
    echo "kill success" 
    ;;
  *)
    usage

    ;;
esac

exit 0
