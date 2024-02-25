public class AppRunner {

    public void execute() {
        Blockchain blockchain = new Blockchain();
        Miners miners = new Miners(blockchain);
        for (int i = 0; i < 5; i++) {
            miners.doSomeMining();
        }
        blockchain.print();
    }

}
