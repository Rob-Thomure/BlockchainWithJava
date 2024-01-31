import java.util.Scanner;
import java.util.stream.IntStream;

public class AppRunner {

    public void execute() {
        System.out.print("Enter how many zeros the hash must start with: ");
        Scanner keyboard = new Scanner(System.in);
        int numZeros = keyboard.nextInt();
        System.out.println();
        Blockchain blockChain = new Blockchain(numZeros);
        IntStream.rangeClosed(1, 5)
                .forEach(index -> blockChain.generateBlock());
        System.out.println(blockChain.getStringFormattedBlockChain());
    }
}
