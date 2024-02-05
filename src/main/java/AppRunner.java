import java.util.Date;
import java.util.Scanner;
import java.util.stream.IntStream;

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

        newVersion();
    }


    // TODO replace with list of callables and invokeAny
    private void newVersion() {
        Blockchain blockchain = new Blockchain();
        Stopwatch stopwatch = new Stopwatch();
        int id = 1;
        while (id <= 5) {
            stopwatch.start();
            boolean validBlock = false;
            int magicNumber = 0;
            while (!validBlock) {
                String blockString = new StringBuilder(id)
                        .append(new Date().getTime())
                        .append(blockchain.getPreviousBlockHash())
                        .append(magicNumber)
                        .toString();
                String hash = StringUtil.applySha256(blockString);
                Block block = new Block(id, new Date().getTime(), magicNumber, blockchain.getPreviousBlockHash(),
                        hash, stopwatch.getDuration());
                validBlock = blockchain.addBlock(block);
                magicNumber++;
            }
            id++;
        }
        blockchain.print();
    }
}
