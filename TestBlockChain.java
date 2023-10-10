
public class TestBlockChain {
    public static void main(String[] args) {
        String reset = "\u001B[0m";
        String color;
        boolean verified;
        Blockchain chain = new Blockchain();
        long time = System.currentTimeMillis();
        int prefix = 4;

        Block block = new Block("genesis", null, time, prefix);


        // add blocks, starting with the genesis block
        chain.addBlock(block);
        // for loop that starts at 4 and adds 4 until we get to 12.
        for (int i = 4; i <= 12; i += 4){
            block = new Block("Block-" + i, chain.prevBlock().getHash(), System.currentTimeMillis(), i);
            chain.addBlock(block);
        }

        // verify, and print a nicely colored statement
        verified = chain.verify();
        if (verified) {
            color = "\u001B[32m"; // green
        } else {
            color = "\u001B[31m"; // red
        }
        // print the verified status and the complete chain size
        System.out.printf("%nVerified?= %s%b%s%n", color, verified, reset);
//        System.out.printf("Chain Size: %s%n", chain.getChainSize());

        // print the entire chain
        System.out.print(chain);
    }
}