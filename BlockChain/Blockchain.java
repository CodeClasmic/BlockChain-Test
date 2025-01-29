import java.util.ArrayList;
import java.util.Date;

// Blockchain class to manage the chain of blocks
class Blockchain {
    private ArrayList<Block> chain; // List to store blocks in the blockchain
    private int difficulty; // Difficulty level for Proof-of-Work (number of leading zeros in hash)

    // Constructor to initialize the blockchain with a difficulty level
    public Blockchain(int difficulty) {
        this.chain = new ArrayList<>();
        this.difficulty = difficulty;
        chain.add(createGenesisBlock()); // Add the first block (Genesis Block)
    }

    // Method to create the Genesis Block (the first block of the blockchain)
    private Block createGenesisBlock() {
        return new Block(0, "Genesis Block", "0"); // Genesis Block has previous hash as "0"
    }

    // Method to add a new block to the blockchain
    public void addBlock(String data) {
        Block previousBlock = chain.get(chain.size() - 1); // Get the last block in the chain
        Block newBlock = new Block(chain.size(), data, previousBlock.getHash()); // Create new block
        newBlock.mineBlock(difficulty); // Perform Proof-of-Work to mine the block
        chain.add(newBlock); // Add the newly mined block to the blockchain
    }

    // Method to validate the blockchain's integrity
    public boolean isChainValid() {
        for (int i = 1; i < chain.size(); i++) { // Loop through all blocks (except Genesis Block)
            Block currentBlock = chain.get(i);
            Block previousBlock = chain.get(i - 1);

            // Recalculate the hash of the current block and check if it matches the stored hash
            if (!currentBlock.getHash().equals(currentBlock.calculateHash())) {
                System.out.println("Block " + i + " has been tampered!");
                return false;
            }

            // Check if the previous hash stored in the current block matches the actual hash of the previous block
            if (!currentBlock.getPreviousHash().equals(previousBlock.getHash())) {
                System.out.println("Block " + i + " previous hash is invalid!");
                return false;
            }
        }
        return true; // Blockchain is valid if all checks pass
    }

    // Method to print the blockchain details
    public void printBlockchain() {
        for (Block block : chain) {
            System.out.println("Block #" + chain.indexOf(block)); // Display block index
            System.out.println("Timestamp: " + new Date(block.getTimestamp())); // Convert timestamp to readable date
            System.out.println("Data: " + block.getData()); // Display transaction data
            System.out.println("Previous Hash: " + block.getPreviousHash()); // Display previous block hash
            System.out.println("Hash: " + block.getHash()); // Display current block hash
            System.out.println("--------------------------------");
        }
    }

    // Method to access the blockchain for tampering (for testing purposes)
    public Block getBlock(int index) {
        if (index >= 0 && index < chain.size()) {
            return chain.get(index);
        }
        return null;
    }
}
