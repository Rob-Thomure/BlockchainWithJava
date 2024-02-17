import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Blockchain {
    private static int nextAvailableBlockId = 1;
    private List<Block> blocks;
    private int zeroesPrefixSize;
    private Map<Integer, ZeroesPrefixRecord> prefixRecords;

    public Blockchain() {
        this.blocks = new ArrayList<>();
        this.zeroesPrefixSize = 0;
        this.prefixRecords = new HashMap<>();
    }

    public boolean addBlock(Block block) {
        if (blocks.isEmpty() || previousBlockAndPrefixSizeMatches(block)) {
            blocks.add(block);
            setNextAvailableBlockId();
            adjustZeroesPrefixSize(block);
            return true;
        }
        return false;
    }

    public boolean isValidBlock(Block block) {
        if (blocks.isEmpty()) return true;
        return previousBlockAndPrefixSizeMatches(block);
    }

    private boolean previousBlockAndPrefixSizeMatches(Block block) {
        return previousBlockHashMatches(block) && hashMatchesPrefixSize(block);
    }

    private boolean previousBlockHashMatches(Block block) {
        return block.getPreviousBlockHash().equals(blocks.get(blocks.size() - 1).getBlockHash());
    }

    private boolean hashMatchesPrefixSize(Block block) {
        String zeroesRegExp = "0".repeat(zeroesPrefixSize) + "[^0].+";
        return block.getBlockHash().matches(zeroesRegExp);
    }

    private void adjustZeroesPrefixSize(Block block) {
        if (block.getSecondsToGenerate() < 15) {
            zeroesPrefixSize++;
            prefixRecords.put(block.getId(), new ZeroesPrefixRecord(zeroesPrefixSize, ZeroesPrefixRecord.Adjustment.INCREASED));
        } else if (block.getSecondsToGenerate() > 64) {
            zeroesPrefixSize--;
            prefixRecords.put(block.getId(), new ZeroesPrefixRecord(zeroesPrefixSize, ZeroesPrefixRecord.Adjustment.DECREASED));
        } else {
            prefixRecords.put(block.getId(), new ZeroesPrefixRecord(zeroesPrefixSize, ZeroesPrefixRecord.Adjustment.STAYS));
        }

    }

    public boolean isValidBlockChain() {
        String previousBlockHash = "0";
        for (Block block : blocks) {
            if (previousBlockHash != block.getPreviousBlockHash())
                return false;
            else
                previousBlockHash = block.getPreviousBlockHash();
        }
        return true;
    }

    public String getPreviousBlockHash() {
        if (blocks.isEmpty())
            return "0";
        else
            return blocks.get(blocks.size() - 1).getBlockHash();
    }

    public void print() {
        blocks.forEach(block -> {
            System.out.println("""
                    Block:
                    Created by miner # %d
                    Id: %d
                    Timestamp: %d
                    Magic number: %d
                    Hash of the previous block:
                    %s
                    Hash of the block:
                    %s
                    Block was generating for %d seconds
                    %s\n""".formatted(
                            block.getGeneratedByMiner(),
                            block.getId(),
                    block.getTimeStamp(),
                    block.getMagicNumber(),
                    block.getPreviousBlockHash(),
                    block.getBlockHash(),
                    block.getSecondsToGenerate(),
                    formatPrefixString(block)
            ));
        });
    }

    private String formatPrefixString(Block block) {
        ZeroesPrefixRecord zeroesPrefixRecord = prefixRecords.get(block.getId());
        //String adjustment = "";
        String adjustment = switch (zeroesPrefixRecord.getAdjustment()) {
            case INCREASED -> "N was increased to " + zeroesPrefixRecord.getPrefixSize();
            case DECREASED -> "N was decrease by " + zeroesPrefixRecord.getPrefixSize();
            case STAYS -> "N stays the same";
        };
        return adjustment;
    }

    public static int getNextAvailableBlockId() {
        return nextAvailableBlockId;
    }

    private static void setNextAvailableBlockId() {
        nextAvailableBlockId += 1;
    }


}
