import java.util.Date;

public class Block {
    private static int idIndex;
    private int id;
    private long timeStamp;
    private String previousBlockHash;
    private String blockHash;
    private int numZeroes;
    private int hashLength = 64;
    private int magic;

    static {
        idIndex = 1;
    }

    public Block(String previousBlockHash, int numZeroes) {
        this.id = idIndex;
        setNextIndex();
        this.timeStamp = new Date().getTime();
        this.previousBlockHash = previousBlockHash;
        this.numZeroes = numZeroes;
        this.magic = -1;
        this.blockHash = generateHash();
    }

    private static void setNextIndex() {
        idIndex++;
    }

    private String generateHash() {
        String zeroes = "0".repeat(numZeroes) + "[^0].+";
        String hashString = "";
        while (!hashString.matches(zeroes)) {
            magic++;
            String classString = new StringBuilder().append(id).append(timeStamp).append(previousBlockHash).append(magic).toString();
            hashString = StringUtil.applySha256(classString);
        }
        return hashString;
    }

    public String getBlockHash() {
        return this.blockHash;
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
                %s""".formatted(id, timeStamp, magic, previousBlockHash, blockHash);
    }


}
