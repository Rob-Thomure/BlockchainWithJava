import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Miners {
    private Blockchain blockchain;
    private List<Callable<Miner>> callableMiners;

    public Miners(Blockchain blockchain) {
        this.blockchain = blockchain;
        this.callableMiners = new ArrayList<>();

        List<Miner> miners = makeMiners(blockchain);
        List<Callable<Miner>> callableMiners = makeCallableMiners(miners);
        this.callableMiners = callableMiners;
    }

    private List<Miner> makeMiners(Blockchain blockchain) {
        List<Miner> miners = new ArrayList<>();
        for (int minerId = 1; minerId <= 10; minerId++) {
            Miner miner = new Miner(minerId, blockchain);
            miners.add(miner);
        }
        return miners;
    }

    private List<Callable<Miner>> makeCallableMiners(List<Miner> miners) {
        List<Callable<Miner>> callableMiners = new ArrayList<>();
        miners.forEach(miner -> {
            Callable<Miner> callableMiner = () -> miner.mine();
            callableMiners.add(callableMiner);
        });
        return callableMiners;
    }

    public void doSomeMining() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        try {
            Miner result = executorService.invokeAny(callableMiners);
            blockchain.addBlock(result.getBlock());
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
}
