import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Block {
    private String hash;
    private String prevHash;
    private String data;
    private long time;
    private int pow;
    private int prefix;

    // default constructor
    public Block() {
        this.data = "";
        this.prevHash = "";
        this.time = 0;
        this.prefix = 0;
        this.hash = "";
    }

    //Main constructors
    public Block(String data, String prevHash, long time, int prefix) {
        this.data = data;
        this.prevHash = prevHash;
        this.time = time;
        this.prefix = prefix;
        this.hash = toHex(mineBlock());
    }

    public String generateBlockHash() {
        String new_hash = "";
        // build string for hash
        String str = this.prevHash + this.data + this.time + this.pow;

        // try generating the hash byte array.
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] dataBytes = str.getBytes();
            byte[] hashBytes = md.digest(dataBytes);

            // convert the binary byte array into a string version
            StringBuilder binString = new StringBuilder();
            for (byte b : hashBytes) {
                String binByte = String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
                binString.append(binByte);
            }
            // new string of hash to be returned
            new_hash = binString.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.printf("Threw exception: %s", e);
        }
        return new_hash;
    }

    public String mineBlock() {
        int count = 1;
        String binHash = "";
        String prefixStr = "";
        String compareStr = "";

        // build a prefix string to compare
        for (int i = 0; i < prefix; i++) {
            compareStr += "0";
        }
//        System.out.printf("My String: %s%nPrefix: %d", compareStr, prefix);

        // loop through until we find the pow
        while(true) {
            binHash = generateBlockHash();
            prefixStr = binHash.substring(0, prefix);
            // compare prefix string, and break when found
            if (compareStr.equals(prefixStr)) {
                break;
            }
            // Since the pow is hashed with the rest of the data, we increment it directly.
            this.pow++;
        }
        return binHash;
    }

    // a method for converting to hex for concatenating inside a block.
    public String toHex(String bin) {
        StringBuilder hexStringBuilder = new StringBuilder();
        for (int i = 0; i < bin.length(); i += 4) {
            String binaryChunk = bin.substring(i, i + 4);
            int decimalValue = Integer.parseInt(binaryChunk, 2);
            String hex = Integer.toHexString(decimalValue);
            hexStringBuilder.append(hex);
        }
        return hexStringBuilder.toString();
    }

    // A method that converts a hex hash into its binary string version for mining
    public  String toBin(String hex) {
        StringBuilder binaryStringBuilder = new StringBuilder();
        for (int i = 0; i < hex.length(); i++) {
            char hexChar = hex.charAt(i);
            int hexValue = Character.digit(hexChar, 16);

            // Convert the hex digit to a 4-bit binary string
            String binaryDigit = String.format("%4s", Integer.toBinaryString(hexValue));
            binaryStringBuilder.append(binaryDigit.replace(' ', '0'));
        }
        return binaryStringBuilder.toString();
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getPrevHash() {
        return prevHash;
    }

    public void setPrevHash(String prevHash) {
        this.prevHash = prevHash;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getPow() {
        return pow;
    }

    public void setPow(int pow) {
        this.pow = pow;
    }

    public int getPrefix() {
        return prefix;
    }

    public void setPrefix(int prefix) {
        this.prefix = prefix;
    }

    public String toString() {
        return String.format("Data: %s%nPrefix: %d%nPrevious Hash: %s%nHash: %s%n%n", data, prefix, prevHash, hash);
    }
}
