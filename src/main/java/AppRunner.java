public class AppRunner {

    public void execute() {
//        System.out.print("Enter how many zeros the hash must start with: ");
//        Scanner keyboard = new Scanner(System.in);
//        int numZeros = keyboard.nextInt();
//        System.out.println();
//        Blockchain blockChain = new Blockchain(numZeros);
//        IntStream.rangeClosed(1, 5)
//                .forEach(index -> blockChain.generateBlock());
//        System.out.println(blockChain.getStringFormattedBlockChain());

        testVersion();
    }

    private void testVersion() {
        Blockchain blockchain = new Blockchain();
        Miners miners = new Miners(blockchain);
        for (int i = 0; i < 5; i++) {
            miners.doSomeMining();
        }
        blockchain.print();
    }

}
