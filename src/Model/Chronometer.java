package Model;

import java.time.*;

public class Chronometer implements IChronometer {
    private Thread thread = null;
    private LocalDateTime start;
    private LocalDateTime stop;
    private LocalDateTime pause;
    private LocalDateTime resume;
    private Duration paused;
    private Duration previousPause;

    public Chronometer(){
        this.resume=LocalDateTime.MIN;
        this.pause=LocalDateTime.MIN;
        this.paused=Duration.ZERO;
        this.previousPause=Duration.ZERO;
    }

    @Override
    public void update() {
        this.stop=LocalDateTime.now();
    }

    @Override
    public void reset() {
        this.resume=LocalDateTime.MIN;
        this.pause=LocalDateTime.MIN;
        this.paused=Duration.ZERO;
        this.previousPause=Duration.ZERO;
    }

    @Override
    public void pause() {
        this.pause=LocalDateTime.now();
    }

    @Override
    public void resume() {
        this.resume=LocalDateTime.now();
        previousPause=Duration.between(pause,resume);
        paused=paused.plus(previousPause);
    }

    @Override
    public Duration getRuntime() {
        return Duration.between(start,stop).minus(paused);
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
    }
}