#!/bin/bash
# Команда да ги печати луѓето со исто име од различен град
IFS=$'\n'
file=$(cat $1)

echo '++++++++++++++++++++++'

for person in $file
do
    name1=$(echo $person | awk '{print $1}')
    fullname1=$(echo $person | awk '{print $1,$2}')
    city1=$(echo $person | awk -F\| '{print $2}' | sed 's/ //')
    
   
    for p2 in $file
    do
        name2=$(echo $p2 | awk '{print $1}')
        fullname2=$(echo $p2 | awk '{print $1,$2}')
        city2=$(echo $p2 | awk -F\| '{print $2}' | sed 's/ //')
        
        if [ $name1 = "$name2" ] && [ $city1 != "$city2" ] 
        then
            echo "${fullname1} ${fullname2}"
        fi
    done

done