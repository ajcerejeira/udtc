package Model;

import java.time.Duration;
import java.time.LocalDateTime;

public class Chronometer implements IChronometer {
    private LocalDateTime start;
    private LocalDateTime stop;
    private Duration ellapsed;

    public Chronometer() {
        this.start = LocalDateTime.now();
        this.stop = LocalDateTime.now();
        this.ellapsed = Duration.ZERO;
    }

    @Override
    public void start() {
        this.start = LocalDateTime.now();
    }

    @Override
    public Duration stop() {
        this.stop = LocalDateTime.now();
        Duration ellapsed = Duration.between(this.start, this.stop).plus(this.ellapsed);
        this.ellapsed = Duration.ZERO;

        return ellapsed;
    }

    @Override
    public Duration pause() {
        this.stop = LocalDateTime.now();
        this.ellapsed = Duration.between(this.start, this.stop).plus(this.ellapsed);

        return this.ellapsed;
    }

    @Override
    public void resume() {
        this.start = LocalDateTime.now();
    }
}
