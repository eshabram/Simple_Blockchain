
public class TestBlockChain {
    public static void main(String[] args) {
        String reset = "\u001B[0m";
        String color;
        boolean verified;
        Blockchain chain = new Blockchain();
        long time = System.currentTimeMillis();
        Block block = new Block("genesis", null, time, 3);

        chain.addBlock(block);
        chain.verify();
        // add block
        for (int i = 0; i < 5; i++) {
            block = new Block("Block-" + i, chain.prevBlock().getHash(), System.currentTimeMillis(), 10);
            chain.addBlock(block);
            chain.verify();
        }
        // verify, and print a nicely colored statement
        verified = chain.verify();
        if (verified) {
            color = "\u001B[32m"; // green
        } else {
            color = "\u001B[31m"; // red
        }
        System.out.printf("%nVerified?= %s%b%s%n", color, verified, reset);

        // print the entire chain
        System.out.print(chain);

    }
}