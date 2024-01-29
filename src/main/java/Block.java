import java.util.Date;

public class Block {
    private static int idIndex;
    private int id;
    private long timeStamp;
    private String previousBlockHash;
    private String blockHash;

    static {
        idIndex = 1;
    }

    public Block(String previousBlockHash) {
        this.id = idIndex;
        setNextIndex();
        this.timeStamp = new Date().getTime();
        this.previousBlockHash = previousBlockHash;
        this.blockHash = generateHash();
    }

    private static void setNextIndex() {
        idIndex++;
    }

    private String generateHash() {
        String stringValues = new StringBuilder().append(id).append(timeStamp).append(previousBlockHash).toString();
        return StringUtil.applySha256(stringValues);
    }

    public String getBlockHash() {
        return this.blockHash;
    }

    public String toString() {
        return  """
                Block:
                Id: %d
                Timestamp: %d
                Hash of the previous block:
                %s
                Hash of the block:
                %s""".formatted(id, timeStamp, previousBlockHash, blockHash);
    }


}
