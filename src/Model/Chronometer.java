package Model;

import Exceptions.*;
import java.time.*;
import Utils.Input.InputHandler;

import static java.lang.System.out;

public class Chronometer implements IChronometer {
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
    public void run() {
        Thread thread = new Thread(() -> {
            while(true){
                update();
            }
        });
        thread.start();
        try {
            int i = InputHandler.readInteger();
            if(i==1){
                thread.interrupt();
                stop();
                out.println(getRuntime());
            }
        } catch (ReturnException e) {
            e.printStackTrace();
        } catch (InvalidInputException e) {
            e.printStackTrace();
        }
    }
}