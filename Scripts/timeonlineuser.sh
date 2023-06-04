#!/bin/bash

if [ $# -ne 1 ]
then
    echo "USAGE: $0 <indeks>"
    exit 1
fi

student=$1
logins=$(last | grep "$student" | grep 'in' -v | awk '{print $10}')
totalTime=0
for entry in $logins
do
    clearedTime=$(echo $entry | sed -e 's/(//' -e 's/)//')
    time=$(echo $clearedTime | awk -F: '{print $1*60+$2}')
    totalTime=$((totalTime + time))
    echo $totalTime
done

echo $totalTime > resultTimes.txt
echo "Total time spent for user ${student} is"
echo "======================================="

cat resultTimes.txt