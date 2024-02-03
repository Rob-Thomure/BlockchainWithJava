import java.util.ArrayList;
import java.util.List;

public class Blockchain {
    private List<Block> blocks;
    private List<Long> times;
    private int numZeroes;

    public Blockchain(int numZeroes) {
        this.blocks = new ArrayList<>();
        this.times = new ArrayList<>();
        this.numZeroes = numZeroes;
    }

    // blockchain shouldn't create new blocks. Should keep the chain valid and accept
    // the new blocks from outside
    public void generateBlock() {
        Stopwatch stopwatch = new Stopwatch();
        stopwatch.start();
        if (blocks.isEmpty())
            blocks.add(new Block("0", numZeroes));
        else
            blocks.add(new Block(getPreviousBlockHash(), numZeroes));
        stopwatch.stop();
        times.add(stopwatch.getDuration());
    }

    public boolean validateBlockchain() {
        return true;
    }

    private String getPreviousBlockHash() {
        int lastIndex = blocks.size() - 1;
        return  blocks.get(lastIndex).getBlockHash();
    }

    public String getStringFormattedBlockChain() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < blocks.size(); i++) {
            stringBuilder.append(blocks.get(i)).append("\n")
                    .append("Block was generating for %d seconds".formatted(times.get(i)))
                    .append("\n\n");
        }
        return stringBuilder.toString();
    }


}
