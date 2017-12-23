package View;

import Model.Chronometer;
import Utils.UI.*;

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
                new Title("Ellapsed time: " + chronometer.getRuntime().replace("PT", ""), 2),
                new Menu(new Option[] {
                        new Option("B", "Back", this::run),
                }),
        }).run();

        chronometer.reset();
        this.run();
    }

    private void pause() {
        chronometer.pause();

        new UI(new Runnable[] {
                new Title("Chronometer", 1),
                new Title("Paused", 2),
                new Menu(new Option[] {
                        new Option("R", "Resume", this::resume),
                }),
        }).run();
    }

    private void resume() {
        chronometer.resume();
        this.start();
    }

    @Override
    public void run() {
        new UI(new Runnable[] {
                new Title("Chronometer", 1),
                new Menu(new Option[] {
                        new Option("S", "Start", this::start),
                        new Option("B", "Back", () -> System.out.println()),
                }),
        }).run();
    }
}
