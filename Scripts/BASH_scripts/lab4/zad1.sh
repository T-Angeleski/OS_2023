#!/bin/bash   
rm -f modified.txt           
touch modified.txt       
                         
files=$(ls)              
todayDate=$(date | awk '{print $3}')
for file in $files
do                       
    fileDate=$(ls -l $file | awk '{print $8}')

    if [[ $((todayDate - fileDate < 2)) ]]
    then
        echo $file >> modified.txt
    fi               
done
echo "============ FILE AFTER MODIFICATION ========="
cat modified.txt

