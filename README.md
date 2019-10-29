*****************
* Project 3: Hash Tables
* CS321
* 2019-10-28
* Benjamin Warner
*****************

# OVERVIEW

This project implements the Hash Table abstract data type using an array.
Collision resolution is accomplished using open addressing techniques.
A command line tester is available from the HashTest.java file.

## INCLUDED FILES

- HashTable.java
- HashObject.java
- HashTest.java
- README (this file)

## COMPILING AND RUNNING

To compile the project, simply execute the following command: `javac HashTest.java`.

This will compile all the necessary class files (HashObject.java and HashTable.java).
**NOTE**: this will place the binaries with the source files.

Sample output:

```
$ java HashTest 0.25 584 sample.txt 2

Using Linear Probing...
Inserted 103 values with 43 duplicates
Average number of probes: 39.834953


Using Double Hashing...
Inserted 103 values with 43 duplicates
Average number of probes: 3.2330098

Using Quadratic Probing...
Inserted 103 values with 43 duplicates
Average number of probes: 8.330097
```

To run the compiled binary, use this command: `java HashTest [OPTIONS...]`.

## PROGRAM DESIGN AND IMPORTANT CONCEPTS

The main concept to understand here, is how a hash table adt works.
A hash table is sort of like an array, but it guarantees constant time
for adding values, removing values, and checking if a value already
exists. This is accomplished by assigning a hash for each element
in the table.

Each element in the table has a value and a key associated with it.
The key is important, because to calculate the hash, the key is
required.

Hashing functions commonly use modulus to calculate the actual index
to place the element in the table.

One common problem with hash tables, is collisions. In other words,
when two elements hash to the same spot in the table. This implementation
uses open addressing, otherwise known as probing. Basically, when
a collision occurs, you increment a probe counter and try to hash again
using the probe counter.

This implementation supports three open addressing techniques:

- Linear probing (evaluating the next slot in the table)
- Quadratic (jumping around the table)
- Double Hashing (hashing the returned index again)

The HashTable.java is the actual implementation of the hash table adt.
It uses the HashObject to store the elements in the table.

The HashTest.java is a simple tester of the HashTable implementation.
It reads in a text file, and spits out some statistics of the three
probing techniques. 

## TESTING

TestNG test cases were provided by the instructor Matthew Thomas (mhthomas@boisestate.edu).
These ensure that the HashTable and HashObject classes were working as expected.

As for testing the HashTest command line tool, sample output files with their
associated commands, were also provided by the instructor. The HashTest
was run using the same command line options and the resulting output
was checked against the sample. If the resulting output didn't match,
that was considered a failure.

## DISCUSSION

This project was rather straight forward. The concept of hashing is simple,
but pretty easy to mess up. There were some things that I'd rather do
differently (for instance, I wouldn't keep track of the total number of probes
with the table), but that's really not my place to decide.

Still don't really like java as a language. Not much I can do about that.

The HashTest source file is really barbaric. Argument parsing is subpar
at best, and the main method is a little too long for my tastes. Would
factor out that functionality into separate functions but I ran out of
time. Still, it gets the job done.

Had an issue with determining what values were duplicates and what weren't.
The bug was in HashObject, because I was using == to do comparisons when I 
needed to use the .equals method. Was the cause of a rather big head ache.
