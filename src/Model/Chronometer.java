package Model;

import java.time.Duration;
import java.time.LocalDateTime;

public class Chronometer implements IChronometer {
    private LocalDateTime start;
    private LocalDateTime stop;
    private Duration elapsed;

    public Chronometer() {
        this.start = LocalDateTime.now();
        this.stop = LocalDateTime.now();
        this.elapsed = Duration.ZERO;
    }

    @Override
    public void start() {
        this.start = LocalDateTime.now();
    }

    @Override
    public Duration stop() {
        this.stop = LocalDateTime.now();
        Duration elapsed = Duration.between(this.start, this.stop).plus(this.elapsed);
        this.elapsed = Duration.ZERO;

        return elapsed;
    }

    @Override
    public Duration pause() {
        this.stop = LocalDateTime.now();
        this.elapsed = Duration.between(this.start, this.stop).plus(this.elapsed);

        return this.elapsed;
    }

    @Override
    public void resume() {
        this.start = LocalDateTime.now();
    }
}
