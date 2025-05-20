CS 1181 - Project 4: Password Cracker
Author: Utsav Acharya


SingleThreadCracker.java -Cracks protected3.zip using recursion (3-letter password)
 MultiThreadCracker.java  -Cracks protected5.zip using multithreading (5-letter password)
 Example.java -Example file provided in assignment.

 How to Compile and Run --

 Step 1: Make sure zip4j-1.3.2.jar is in the same folder.

 Step 2: Compile:
    javac -cp .;zip4j-1.3.2.jar SingleThreadCracker.java
    javac -cp .;zip4j-1.3.2.jar MultiThreadCracker.java

 Step 3: Run:
    java -cp .;zip4j-1.3.2.jar SingleThreadCracker
    java -cp .;zip4j-1.3.2.jar MultiThreadCracker

 (Note: Use ':' instead of ';' on Mac/Linux)

 -- Special Notes --
 - Multi-thread version has "numThreads" variable which can be set to 3 or 4 as required.
 - Correct password and time taken are printed automatically.

