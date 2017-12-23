package View;

import Model.Chronometer;
import static java.lang.System.out;

public class ChronoView implements Runnable {
    private Chronometer chronometer;

    public ChronoView(Chronometer chronometer) {
        this.chronometer = chronometer;
    }

    private void start() {
        out.println("Initializing Chronometer.");
        chronometer.start();

        Menu menu = new Menu(new Option[] {
                new Option("Pause", this::pause),
                new Option("Stop", this::stop)
        });
        menu.run();
    }

    private void stop() {
        out.println("Stopping Chronometer.");
        chronometer.stop();
        out.println("Runtime: " + chronometer.getRuntime().replace("PT",""));
        chronometer.reset();
        this.run();
    }

    private void pause() {
        out.println("Chronometer has paused.");
        chronometer.pause();
        Menu menu = new Menu(new Option[] {
                new Option("Resume", this::resume)
        });
        menu.run();
    }

    private void resume() {
        out.println("Chronometer has resumed.");
        chronometer.resume();
        Menu menu = new Menu(new Option[] {
                new Option("Pause", this::pause),
                new Option("Stop", this::stop)
        });
        menu.run();
    }

    @Override
    public void run() {
        Menu menu = new Menu(new Option[] {
                new Option("Start", this::start),
                new Option("Return", () -> System.out.println())
        });
        menu.run();
    }
}
