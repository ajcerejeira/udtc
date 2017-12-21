package View;

import Model.Chronometer;
import static java.lang.System.out;

public class ChronoView implements Runnable {
    private Chronometer chronometer;

    public ChronoView() {
        this.chronometer = new Chronometer();
    }

    public ChronoView(Chronometer chronometer) {
        this.chronometer = chronometer;
    }

    private void start() {
        out.println("Initializing Chronometer.");
        chronometer.start();

        Menu menu = new Menu(new Option[] {
                new Option(1,"Pause", this::pause),
                new Option(2,"Stop", this::stop)
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
                new Option(1,"Resume", this::resume)
        });
        menu.run();
    }

    private void resume() {
        out.println("Chronometer has resumed.");
        chronometer.resume();
        Menu menu = new Menu(new Option[] {
                new Option(1,"Pause", this::pause),
                new Option(2,"Stop", this::stop)
        });
        menu.run();
    }

    @Override
    public void run() {
        Menu menu = new Menu(new Option[] {
                new Option(1,"Start", this::start),
                new Option(0,"Return", () -> System.exit(0))
        });
        menu.run();
    }
}
