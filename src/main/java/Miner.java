import java.util.Date;

public class Miner {
    int minerNumber;
    int id;
    Blockchain blockchain;

    public Miner(int minerNumber,int id, Blockchain blockchain) {
        this.minerNumber = minerNumber;
        this.id = id;
        this.blockchain = blockchain;
    }

    public Block mine() {
        Stopwatch stopwatch = new Stopwatch();
        stopwatch.start();
        boolean validBlock = false;
        int magicNumber = 0;
        Block block = null;
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
}
