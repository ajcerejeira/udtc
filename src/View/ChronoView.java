package View;

import Model.Chronometer;
import Utils.UI.*;
import Utils.Static;

public class ChronoView implements Runnable {
    private Chronometer chronometer;

    public ChronoView(Chronometer chronometer) {
        this.chronometer = chronometer;
    }

    private void start() {
        chronometer.start();

        new UI(new Runnable[] {
                new Title("Chronometer", 1),
                new Title("Chronometer is running...", 2),
                new Menu(new Option[] {
                        new Option("P", "Pause", this::pause),
                        new Option("S", "Stop", this::stop),
                }),
        }).run();
    }

    private void stop() {
        chronometer.stop();

        new UI(new Runnable[] {
                new Title("Chronometer", 1),
                new Title("Chronometer has stopped...", 2),
                new Title("Elapsed time: " + Static.prettyChrono(this.chronometer.getRuntime()), 2),
                new Menu(new Option[] {
                        new Option("B", "Back", this),
                })
        }).run();

        chronometer.reset();
        this.run();
    }

    private void pause() {
        chronometer.pause();

        new UI(new Runnable[] {
                new Title("Chronometer", 1),
                new Title("Chronometer has been paused...", 2),
                new Menu(new Option[] {
                        new Option("R", "Resume", this::resume),
                }),
        }).run();
    }

    private void resume() {
        chronometer.resume();
        new UI(new Runnable[] {
                new Title("Chronometer", 1),
                new Title("Chronometer has been resumed...", 2),
                new Menu(new Option[] {
                        new Option("P", "Pause", this::pause),
                        new Option("S", "Stop", this::stop),
                }),
        }).run();
    }

    @Override
    public void run() {
        new UI(new Runnable[] {
                new Title("Chronometer", 1),
                new Menu(new Option[] {
                        new Option("S", "Start", this::start),
                        new Option("B", "Back", System.out::println),
                }),
        }).run();
    }
}
