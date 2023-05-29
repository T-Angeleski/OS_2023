#!/bin/bash

if [ $# -lt 2 ]
then
    echo "Wrong format, please provide 2 directories"
    echo "Usage: $0 dir1 dir2"
    exit 1
fi

FROM=$1
TO=$2

# mv dir1/DATONE.csv dir2
filesSource=$(ls $FROM | grep '^[A-Z]*\.csv')

for f in $filesSource
do
    mv $FROM/$f $TO
done

# Zadacha 3
totalSize=0
movedFilesSizes=$(ls -l $TO | awk '{print $6}')
for i in $movedFilesSizes
do
    echo "Size: $i"
    totalSize=$((totalSize + i))
done

echo "=====Total size of all moved folders is====="
echo $totalSize

echo "=====FOLDERS AFTER MOVING====="
ls $FROM
echo "=============================="
ls $TO