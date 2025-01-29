// Class to demonstrate tampering with the blockchain and detecting invalid changes
class TamperingTest {
    public static void main(String[] args) {
        // Initialize the blockchain with difficulty level 4
        Blockchain blockchain = new Blockchain(4);

        // Add blocks with transaction data
        System.out.println("Mining block 1...");
        blockchain.addBlock("First transaction data");

        System.out.println("Mining block 2...");
        blockchain.addBlock("Second transaction data");

        // Print the blockchain before tampering
        System.out.println("\nOriginal Blockchain:");
        blockchain.printBlockchain();
        System.out.println("Blockchain valid: " + blockchain.isChainValid());

        // Tampering with the blockchain
        System.out.println("\nAttempting to tamper with blockchain...");
        Block tamperedBlock = blockchain.getBlock(1); // Get block #1
        if (tamperedBlock != null) {
            tamperedBlock.setData("Hacked transaction data"); // Modify the data in block 1
        }

        // Print the blockchain after tampering
        System.out.println("\nBlockchain After Tampering:");
        blockchain.printBlockchain();
        System.out.println("Blockchain valid: " + blockchain.isChainValid());
    }
}
