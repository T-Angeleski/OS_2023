#!/bin/bash

tvHost=$1
thisMonth=$(date | awk '{print $2}')

IFS=$'\n'
comedians=$(cat comedians.txt)

for c in $comedians
do
    month=$(echo $c | awk '{print $3}')
    year=$(echo $c | awk '{print $5}')
    company=$(echo $c | awk '{print $6}')
    echo $month $year $company $thisMonth

    if [ $month = $thisMonth ] && [ $year -gt 2013 ]
    then
        if [ $company = $tvHost ]
        then
            name=$(echo $c | awk '{print $1,$2}')
            echo $name
        fi
    fi
done