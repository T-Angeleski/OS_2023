#!/bin/bash

# find DIR -type f -exec cat {} \;

if [ $# -lt 1 ]; then
    echo "Wrong format!"
    echo "USAGE: $0 <directory>"
    exit 1
fi

dir=$1
matches=$(find $dir -type f -exec cat {} \;)
# Dobi lista od unikatni timovi
teams=$(find $dir -type f -exec cat {} \; | awk -F, '{print $2}' | sort -u)
for team in $teams; do # za sekoj tim proveri rezultat
    score=0
    for match in $matches; do
        teamMatch=$(echo $match | awk -F, '{print $2}')
        if [ $team = $teamMatch ]; then # dali e toj tim
            rezult=$(echo $match | awk -F, '{print $1}')
            # w, d = +1, l = 0
            if [ $rezult = "w" ] || [ $rezult = "d" ]; then
                score=$(($score + 1))
            fi
        fi
    done
    # Pecati samo tie so score > 0
    if [ $score -gt 0 ]; then
        echo $score $team
    fi
done
