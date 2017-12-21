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
    private LocalDateTime pause;
    private LocalDateTime resume;

    public Chronometer(){
        this.resume=LocalDateTime.MIN;
        this.pause=LocalDateTime.MIN;
    }

    @Override
    public void update() {
        this.stop=LocalDateTime.now();
    }

    @Override
    public void pause() {
        this.pause=LocalDateTime.now();
    }

    @Override
    public void resume() {
        this.resume=LocalDateTime.now();
    }

    @Override
    public String getRuntime() {
        return Duration.between(start,stop).minus(Duration.between(pause,resume)).toString();
    }

    @Override
    public void start() {
        this.start=LocalDateTime.now();
        thread = new Thread(() -> {
            while(true){
                update();
            }
        });
        thread.start();
    }

    @Override
    public void stop() {
        thread.interrupt();
        this.stop=LocalDateTime.now();
        this.resume=LocalDateTime.MIN;
        this.pause=LocalDateTime.MIN;
    }
}