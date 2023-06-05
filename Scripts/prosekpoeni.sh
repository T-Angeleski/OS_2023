#!/bin/bash

if [ $# -ne 1 ]
then
    echo "USAGE: $0 filename.csv"
    exit 1
fi
file=$1

if [[ ! $file =~ \.csv$ ]]
then
    echo "Not a csv file"
    exit 1
fi

if [ -f "passed_$file" ]
then
    rm "passed_$file"
fi

touch "passed_$file"

echo 'Surname,"First name",Index,Points' >> "passed_$file"

students=$(cat $file)
avg=0
count=0

for s in $students
do
    p=$(echo $s | awk -F\, '{print $NF}')
    if [ $p -gt 4 ]
    then
        echo $s >> "passed_$file"
        avg=$(($avg + $p))
        count=$(($count + 1))
    fi
done

avg=$(($avg / $count))
echo "Passed students average points: ${avg}" >> "passed_$file"

cat "passed_$file"