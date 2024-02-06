import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;
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

        testVersion();
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

    private void testVersion() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Blockchain blockchain = new Blockchain();
        IdCounter idCounter = new IdCounter();

        Miner miner1 = new Miner(1, blockchain);
        Miner miner2 = new Miner(2, blockchain);
        Miner miner3 = new Miner(3, blockchain);
        Miner miner4 = new Miner(4, blockchain);
        Miner miner5 = new Miner(5, blockchain);

        List<Callable<Miner>> miners = List.of(() -> miner1.mine(idCounter.getCounter()),
                () -> miner2.mine(idCounter.getCounter()),
                () -> miner3.mine(idCounter.getCounter()),
                () -> miner4.mine(idCounter.getCounter()),
                () -> miner5.mine(idCounter.getCounter())
        );
        Miner result;

        for (int i = 0; i < 5; i++) {
            doSomeMining(miners, blockchain, idCounter);
        }
        executorService.shutdown();

        blockchain.print();


    }

    synchronized private void doSomeMining(List<Callable<Miner>> miners, Blockchain blockchain, IdCounter idCounter) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        try {
            Miner result = executorService.invokeAny(miners);
            blockchain.addBlock(result.getBlock());
            idCounter.iterate();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    class IdCounter {
        int counter;
        IdCounter() {
            counter = 1;
        }

        void iterate() {
            counter++;
        }

        int getCounter() {
            return counter;
        }
    }


}
