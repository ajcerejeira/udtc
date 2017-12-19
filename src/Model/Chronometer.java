package Model;

import Exceptions.*;
import java.time.*;
import Utils.Input.InputHandler;

import static java.lang.System.out;

public class Chronometer implements IChronometer {
    Thread thread = null;
    private double runTime;
    private LocalDateTime start;
    private LocalDateTime stop;

    public Chronometer(){
    }

    @Override
    public void start() {
        this.start=LocalDateTime.now();
    }

    @Override
    public void update() {
        this.stop=LocalDateTime.now();
    }

    @Override
    public void resume() {

    }

    @Override
    public void stop() {
        this.stop=LocalDateTime.now();
    }

    @Override
    public String getRuntime() {
        return Duration.between(start,stop).toString();
    }

    @Override
    public void startChronometer() {
        start();
        thread = new Thread(() -> {
            while(true){
                update();
            }
        });
        thread.start();
    }

    @Override
    public void stopChronometer() {
        thread.interrupt();
        stop();
        out.println(getRuntime());
    }
}