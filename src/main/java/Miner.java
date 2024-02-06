import java.util.Date;
import java.util.concurrent.Callable;

public class Miner implements Callable<Block> {
    int minerNumber;
    int id;
    Blockchain blockchain;
    Block block;

    public Miner(int minerNumber,int id, Blockchain blockchain) {
        this.minerNumber = minerNumber;
        this.id = id;
        this.blockchain = blockchain;
    }

    public Miner(int minerNumber, Blockchain blockchain) {
        this.minerNumber = minerNumber;
        this.blockchain = blockchain;
    }



    public Miner mine() {
        Stopwatch stopwatch = new Stopwatch();
        stopwatch.start();
        boolean validBlock = false;
        int magicNumber = 0;
        block = null;
        while (!validBlock) {
            String blockString = new StringBuilder(id)
                    .append(new Date().getTime())
                    .append(blockchain.getPreviousBlockHash())
                    .append(magicNumber)
                    .toString();
            String hash = StringUtil.applySha256(blockString);
            block = new Block(id, new Date().getTime(), magicNumber, blockchain.getPreviousBlockHash(),
                    hash, stopwatch.getDuration());
            validBlock = blockchain.isValidBlock(block);
            magicNumber++;
        }
        return this;
    }

    public int getMinerNumber() {
        return minerNumber;
    }



    public Block getBlock() {
        return block;
    }

    @Override
    public Block call() throws Exception {
        Stopwatch stopwatch = new Stopwatch();
        stopwatch.start();
        boolean validBlock = false;
        int magicNumber = 0;
        block = null;
        while (!validBlock) {
            String blockString = new StringBuilder(id)
                    .append(new Date().getTime())
                    .append(blockchain.getPreviousBlockHash())
                    .append(magicNumber)
                    .toString();
            String hash = StringUtil.applySha256(blockString);
            block = new Block(id, new Date().getTime(), magicNumber, blockchain.getPreviousBlockHash(),
                    hash, stopwatch.getDuration());
            validBlock = blockchain.isValidBlock(block);
            magicNumber++;
        }
        return block;
    }

    public Miner mine(int id) {
        this.id = id;
        Stopwatch stopwatch = new Stopwatch();
        stopwatch.start();
        boolean validBlock = false;
        int magicNumber = 0;
        block = null;
        while (!validBlock) {
            String blockString = new StringBuilder(id)
                    .append(new Date().getTime())
                    .append(blockchain.getPreviousBlockHash())
                    .append(magicNumber)
                    .toString();
            String hash = StringUtil.applySha256(blockString);
            block = new Block(id, new Date().getTime(), magicNumber, blockchain.getPreviousBlockHash(),
                    hash, stopwatch.getDuration());
            validBlock = blockchain.isValidBlock(block);
            magicNumber++;
        }
        return this;
    }




}
