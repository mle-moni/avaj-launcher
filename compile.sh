#!/bin/sh

if [[ $1 == "clean" ]] ; then
	echo "Deleting class files..."
	find * -name "*.class" -exec rm -f {} \;
	echo "Done."
	exit 0
fi

echo "Compiling..."
javac $(find * -name "*.java")
if [[ $? != 0 ]] ; then
	echo "Compilation error."
	exit 1
fi
echo "Done."

if [[ $1 == "run" ]] ; then
	echo "Runing simulation..."
	java fr.mle_moni.avaj.towers.Main $2
	echo "Done. Results are in ./simulation.txt"
fi
