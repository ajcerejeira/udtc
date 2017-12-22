package Model;

public interface IChronometer {
    void start();
    void stop();
    void pause();
    void resume();
    void update();
    void reset();
    String getRuntime();
}