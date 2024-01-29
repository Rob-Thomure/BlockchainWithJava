import java.util.stream.IntStream;

public class AppRunner {

    public void execute() {
        Blockchain blockChain = new Blockchain();
        IntStream.rangeClosed(1, 5)
                .forEach(index -> blockChain.generateBlock());
        System.out.println(blockChain.getStringFormattedBlockChain());
    }
}
