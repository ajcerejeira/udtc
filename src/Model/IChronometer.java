package Model;

import java.time.Duration;

public interface IChronometer {
    void start();
    Duration stop();
    Duration pause();
    void resume();
}