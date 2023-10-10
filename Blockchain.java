import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Blockchain extends Block{
    private List<Block> chain;
    private int chainSize;
    // some colors to customize the lookup my toString method
    String red = "\u001B[31m";
    String green = "\u001B[32m";
    String yellow = "\u001B[33m";
    String brightYellow = "\u001B[93m";
    String reset = "\u001B[0m";

    public Blockchain() {
        this.chain = new LinkedList<Block>();
        chainSize = 0;
    }

    public void addBlock(Block block) {
        chain.add(block);
        this.chainSize++;
    }

    public boolean verify() {
        Block block;
        Boolean verified = true;
        String prevHash = "";
        ListIterator<Block> iter = chain.listIterator(chain.size());

        // iterate through the blocks in the list, starting from the most recently added
        while (iter.hasPrevious()) {
            block = iter.previous();
            String hash = block.getHash();
            String genHash = block.toHex(block.generateBlockHash());
            int prefix = block.getPrefix();
            String prefixString = "";

            // build a prefix string for comparison during the verification
            for (int i = 0; i < prefix; i++ ) {
                prefixString += "0";
            }

            // check that the current hash is correct
            if (!hash.equals(genHash)) {
                verified = false;
                break;
            }
            // check that the "prevHash" from one block ahead matches current hash
            if (!prevHash.equals("")) {
                if (!block.getHash().equals(prevHash)) {
                    verified = false;
                    break;
                }
            }
            // verify that the block was mined properly by checking that the hash
            // matches the recorded prefix number of zeroes.
            if (!toBin(hash).substring(0, prefix).equals(prefixString)) {
                verified = false;
                break;
            }

            prevHash = block.getPrevHash();
        }
        return verified;
    }

    public void setChain(List<Block> chain) {
        this.chain = chain;
    }

    public List<Block> getChain() {
        return chain;
    }

    public Block prevBlock() {
        if (!chain.isEmpty()) {
            return chain.get(chain.size() -1);
        } else {
            return null;
        }
    }

    public int getChainSize() {
        return chainSize;
    }

    public void setChainSize(int chainSize) {
        this.chainSize = chainSize;
    }

    public String toString() {
        String str = "";

        for (Block block : chain) {
            str += block.toString();
        }
        return String.format("%n%sBLOCKCHAIN:%s%n%s%s", brightYellow, yellow, str, reset);
    }
}
