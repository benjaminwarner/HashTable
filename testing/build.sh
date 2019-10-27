#!/bin/bash

for f in HashTableTest*.java
do
	echo "compiling $f"
	javac $f
done

javac TestCase.java
