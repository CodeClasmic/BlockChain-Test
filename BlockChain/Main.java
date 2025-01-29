public class Main {
    public static void main(String[] args) {
        Blockchain blockchain = new Blockchain(4); // Set difficulty level

        System.out.println("Mining block 1...");
        blockchain.addBlock("First transaction data");

        System.out.println("Mining block 2...");
        blockchain.addBlock("Second transaction data");

        System.out.println("Mining block 3...");
        blockchain.addBlock("Third transaction data");

        blockchain.printBlockchain();
        System.out.println("Blockchain valid: " + blockchain.isChainValid());
    }
}