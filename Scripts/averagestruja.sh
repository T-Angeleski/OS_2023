#!/bin/bash

if [ $# -lt 1 ]
then
    echo "USAGE: $0 <month>"
    exit 1
fi

month=$1
IFS=$'\n'
people=$(cat podatoci.csv)
average=0
count=0
for p in $people; do
    m=$(echo $p | awk -F"|" '{print $3}')
    kwh=$(echo $p | awk -F"|" '{print $5}')
    
    if [ $m = $month ]; then
        average=$(($average + $kwh))
        count=$(($count + 1))
    fi
done

if [ $count = 0 ]
then
    echo "No entries for that month"
    exit 1
fi

average=$(($average / $count))
echo "Average consumption: $average"

touch result.txt

for p in $people
do
    name=$(echo $p | awk -F"|" '{print $1}')
    kwh=$(echo $p | awk -F"|" '{print $5}')

    if [ $kwh -gt $average ]
    then
        echo $name $kwh >> result.txt
    fi
done
touch sorted.txt
sort result.txt -k3r > sorted.txt
cat sorted.txt