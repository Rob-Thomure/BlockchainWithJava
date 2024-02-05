import java.time.Duration;
import java.time.Instant;

public class Stopwatch {
    Instant start;
    Instant stop;

    public void start() {
        this.start = Instant.now();
    }

    public void stop() {
        this.stop = Instant.now();
    }

    public long getDuration() {
        Instant now = Instant.now();
        return Duration.between(start, now).toSeconds();
    }


}
