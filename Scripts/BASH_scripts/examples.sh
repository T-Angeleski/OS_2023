#!/bin/bash

echo "First line"
a=123
echo "Value of a is: ${a}"

#Command line
echo "Entered argument $1"
echo Num\ arguments\ $#
echo "All args: $*"

printFiles=`ls -l`
echo $printFiles
#Expression
echo "Value of a:${a} plus 10 is: "$(($a + 10))

echo "Enter 2 numbers"
read b
read c
echo $b "+" $c "=" $(($b + $c))

if [ $b -eq $c ]
then
    echo "Both numbers equal"
elif [ $b -gt $c ] 
then
    echo "${b} > ${c}"
else
    echo "${b} < ${c}"
fi

echo 'Enter apple, kiwi or orange'
read FRUIT
case "${FRUIT}" in
    apple) echo "Entered apple";;
    kiwi|orange) echo "Kiwi or orange?";;
    *) echo "None of the above";;
esac

#While
i=1
echo "Counting to five!"
while [ $i -le 5 ] 
do
    echo "${i}.."
    i=$(($i + 1))
    sleep 1
done

for item in {0..10}
do
    echo "${item}"
done


# Don't close
trap 'sleep infinity' EXIT

