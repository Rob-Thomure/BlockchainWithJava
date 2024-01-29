import java.util.ArrayList;
import java.util.List;

public class Blockchain {
    List<Block> blocks;

    public Blockchain() {
        this.blocks = new ArrayList<>();
    }

    public void generateBlock() {
        if (blocks.isEmpty())
            blocks.add(new Block("0"));
        else
            blocks.add(new Block(getPreviousBlockHash()));
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
        blocks.forEach(block -> stringBuilder.append(block).append("\n\n"));
        return stringBuilder.toString();
    }


}
