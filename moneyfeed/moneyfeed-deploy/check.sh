#!/bin/sh
status=`curl -s $1|sed 's/{\(.[^,]*\),\(.[^,]*\),/\1,\2\n/g'|head -1|tr -d \"|cut -d: -f3`
if [[ $status == UP ]]
then
  exit 0
else
  exit 1
fi
