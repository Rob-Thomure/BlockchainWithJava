import java.util.Objects;

public class Block {
    private final String previousBlockHash;
    private final String blockHash;
    private final int id;
    private final long timeStamp;
    private final int magicNumber;
    private final long secondsToGenerate;
    private final int generatedByMiner;

    public Block(int id, long timeStamp, int magicNumber, String previousBlockHash, String blockHash,
                 long secondsToGenerate, int generatedByMiner) {
        this.id = id;
        this.timeStamp = timeStamp;
        this.magicNumber = magicNumber;
        this.previousBlockHash = previousBlockHash;
        this.blockHash = blockHash;
        this.secondsToGenerate = secondsToGenerate;
        this.generatedByMiner = generatedByMiner;
    }

    public String getBlockHash() {
        return this.blockHash;
    }

    public String getPreviousBlockHash() {
        return this.previousBlockHash;
    }

    public long getSecondsToGenerate() {
        return this.secondsToGenerate;
    }

    public int getId() {
        return this.id;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public int getMagicNumber() {
        return magicNumber;
    }

    public int getGeneratedByMiner() {
        return generatedByMiner;
    }

    public String toString() {
        return  """
                Block:
                Id: %d
                Timestamp: %d
                Magic number: %d
                Hash of the previous block:
                %s
                Hash of the block:
                %s""".formatted(id, timeStamp, magicNumber, previousBlockHash, blockHash);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Block block = (Block) o;
        return id == block.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
