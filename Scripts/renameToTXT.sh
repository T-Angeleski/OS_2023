#!/bin/bash

files=$(ls | grep -i .txt | grep -v .txt)
echo $files

for item in $files; do
    justName=$(echo $item | awk -F\. '{print $1}')
    mv "$item" "$justName.txt"
done