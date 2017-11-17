package business;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.TemporalUnit;

public class Chronometer implements IChronometer {
    private LocalTime start_time;
    private LocalTime stop_time;
    private LocalTime pause_time;
    private Duration elapsed_time;

    public Chronometer(){
        elapsed_time=Duration.ofMillis(0);
    }

    @Override
    public void start() {
        start_time = LocalTime.now();
    }

    @Override
    public void stop() {
        stop_time = LocalTime.now();
    }

    @Override
    public void pause(){
        pause_time = LocalTime.now();
    }

    @Override
    public void resume() {
        Duration tempo_pausado = Duration.between(pause_time,LocalTime.now());
        elapsed_time = elapsed_time.plus(tempo_pausado);
        //System.out.println("time paused: " + elapsed_time);
    }

    @Override
    public Duration getElapsedTime() {
        return Duration.between(start_time,stop_time).minus(elapsed_time);
    }
}
