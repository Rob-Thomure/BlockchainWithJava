import java.util.Date;

public class Miner {
    private final int minerNumber;
    private final Blockchain blockchain;
    private Block block;

    public Miner(int minerNumber, Blockchain blockchain) {
        this.minerNumber = minerNumber;
        this.blockchain = blockchain;
    }

    public Block getBlock() {
        return block;
    }

    public Miner mine() {
        int blockId = Blockchain.getNextAvailableBlockId();
        Stopwatch stopwatch = new Stopwatch();
        stopwatch.start();
        boolean validBlock = false;
        int magicNumber = 0;
        block = null;
        while (!validBlock) {
            String blockString = new Date().getTime() +
                    blockchain.getPreviousBlockHash() +
                    magicNumber;
            String hash = StringUtil.applySha256(blockString);
            block = new Block(blockId, new Date().getTime(), magicNumber, blockchain.getPreviousBlockHash(),
                    hash, stopwatch.getDuration(), minerNumber);
            validBlock = blockchain.isValidBlock(block);
            magicNumber++;
        }
        return this;
    }




}
