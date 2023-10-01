# Elliot's Simple Blockchain

### Running the program:
First, download and extract the zip file and cd into the folder which contains several .java files. 
Run these commands to compile and run the program:
```agsl
javac *.java
java TestBlockChain
```
This will add 6 blocks to the chain, including the genesis block.
If you'd like to run more tests by adding blocks to the chain, you
can simply increase the range of the for loop that adds them to
the chain. To change the difficulty of the POW, simply change the 
prefix variable at the top of the test program.