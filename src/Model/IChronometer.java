package Model;

import java.time.Duration;

public interface IChronometer {
    void start();
    void stop();
    void pause();
    void resume();
    void update();
    void reset();
    Duration getRuntime();
}