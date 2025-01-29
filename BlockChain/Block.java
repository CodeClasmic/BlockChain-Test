import java.util.Date;

// Block class represents an individual block in the blockchain
class Block {
    private int index; // Position of the block in the chain
    private long timestamp; // Time when the block was created
    private String data; // Data stored in the block (e.g., transactions)
    private String previousHash; // Hash of the previous block (ensures linking)
    private String hash; // Unique identifier of the block
    private int nonce; // Used for Proof-of-Work (mining)

    // Constructor: Initializes a new block with data and reference to the previous block
    public Block(int index, String data, String previousHash) {
        this.index = index;
        this.timestamp = new Date().getTime(); // Store current time as timestamp
        this.data = data;
        this.previousHash = previousHash;
        this.nonce = 0; // Initialize nonce for mining
        this.hash = calculateHash(); // Generate hash for the block
    }

    // Method to calculate the block's hash using SHA-256
    public String calculateHash() {
        return HashUtil.applySHA256(index + timestamp + data + previousHash + nonce);
    }

    // Method to perform Proof-of-Work (mining) based on difficulty
    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0'); // Generate target prefix

        while (!hash.substring(0, difficulty).equals(target)) { // Keep hashing until we find a valid hash
            nonce++; // Increase nonce to change hash
            hash = calculateHash(); // Recalculate hash with new nonce
        }

        System.out.println("Block Mined: " + hash); // Output the mined block's hash
    }

    // Getters for block attributes
    public String getHash() { return hash; }
    public String getPreviousHash() { return previousHash; }
    public String getData() { return data; }
    public long getTimestamp() { return timestamp; }

    // Setter to modify data (for testing tampering)
    public void setData(String newData) {
        this.data = newData; // Change block data
    }
}
