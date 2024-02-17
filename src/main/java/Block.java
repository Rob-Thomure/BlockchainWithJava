import java.util.Date;
import java.util.Objects;

public class Block {
    private static int idIndex;
    private int numZeroes;
    private int hashLength = 64;

    private String previousBlockHash;
    private String blockHash;
    private int id;
    private long timeStamp;
    private int magicNumber;
    private long secondsToGenerate;
    private int generatedByMiner;


    static {
        idIndex = 1;
    }

    public Block(String previousBlockHash, int numZeroes) {
        this.id = idIndex;
        setNextIndex();
        this.timeStamp = new Date().getTime();
        this.previousBlockHash = previousBlockHash;
        this.numZeroes = numZeroes;
        this.magicNumber = -1;
        this.blockHash = generateHash();
    }

    public Block(int id, long timeStamp, int magicNumber, String previousBlockHash, String blockHash,
                 long secondsToGenerate) {
        this.id = id;
        this.timeStamp = timeStamp;
        this.magicNumber = magicNumber;
        this.previousBlockHash = previousBlockHash;
        this.blockHash = blockHash;
        this.secondsToGenerate = secondsToGenerate;
    }

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

    private static void setNextIndex() {
        idIndex++;
    }

    private String generateHash() {
        String zeroes = "0".repeat(numZeroes) + "[^0].+";
        String hashString = "";
        while (!hashString.matches(zeroes)) {
            magicNumber++;
            String classString = new StringBuilder().append(id).append(timeStamp).append(previousBlockHash).append(magicNumber).toString();
            hashString = StringUtil.applySha256(classString);
        }
        return hashString;
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
