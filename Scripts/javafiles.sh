#!/bin/bash

# print all .java files with user rw permissions newline

if [ $# -lt 1 ]
then
    echo "USAGE: $0 <file to write to>"
    exit 1
fi

if [ ! -f $1 ]; then
    echo "File doesn't exist"
    exit 1
fi

files=$(ls -l | grep '.*\.java$' | grep '^.rw.*' | awk '{print $10}')
for f in $files
do
    echo $f >> $1
    echo '' >> $1
done

cat $1