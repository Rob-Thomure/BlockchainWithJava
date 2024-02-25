import java.time.Duration;
import java.time.Instant;

public class Stopwatch {
    Instant start;

    public void start() {
        this.start = Instant.now();
    }

    public long getDuration() {
        Instant now = Instant.now();
        return Duration.between(start, now).toSeconds();
    }


}
