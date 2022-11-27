#! /bin/bash

javac *.java

java Demo 10 >> results.txt
java Demo 20 >> results.txt
java Demo 50 >> results.txt
java Demo 100 >> results.txt
java Demo 200 >> results.txt
java Demo 500 >> results.txt
java Demo 1000 >> results.txt
java Demo 2000 >> results.txt
java Demo 5000 >> results.txt

cat results.txt
