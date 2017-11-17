package business;

import java.time.Duration;

public interface IChronometer {
    public void start();
    public void stop();
    public void pause();
    public void resume();
    public Duration getElapsedTime();
}