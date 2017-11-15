package business;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Scanner;

public class Chronometer implements IChronometer {
    private LocalTime start_time;
    private LocalTime stop_time;

    public Chronometer(){
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
        stop_time = LocalTime.now();
    }

    @Override
    public void resume() {

    }

    @Override
    public Duration getCurrentTime() {
        return Duration.between(stop_time,start_time);
    }
}
