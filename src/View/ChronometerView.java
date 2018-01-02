package View;

import Model.IChronometer;
import Utils.Static;
import Utils.Option;
import Utils.UI;

import java.time.Duration;

public class ChronometerView {
    public static void home(IChronometer chronometer) {
        UI.title("Chronometer");
        UI.menu(new Option("Start", () -> start(chronometer)),
                new Option("Back", System.out::println));
    }

    private static void start(IChronometer chronometer) {
        chronometer.start();

        UI.title("Chronometer");
        UI.subtitle("Chronometer is running");
        UI.menu(new Option("Pause", () -> pause(chronometer)),
                new Option("Stop", () -> stop(chronometer)));
    }

    private static void stop(IChronometer chronometer) {
        Duration runtime = chronometer.stop();

        UI.title("Chronometer");
        UI.subtitle("Chronometer has stopped...");
        UI.subtitle("Elapsed time: " + Static.prettyChrono(runtime));

        UI.menu(new Option("Back", () -> home(chronometer)));
    }

    private static void pause(IChronometer chronometer) {
        Duration runtime = chronometer.pause();

        UI.title("Chronometer");
        UI.subtitle("Chronometer has been paused...");
        UI.subtitle("Elapsed time: " + Static.prettyChrono(runtime));
        UI.menu(new Option("Resume", () -> resume(chronometer)),
                new Option("Stop", () -> stop(chronometer)));
    }

    private static void resume(IChronometer chronometer) {
        chronometer.resume();

        UI.title("Chronometer");
        UI.subtitle("Chronometer has been resumed...");
        UI.menu(new Option("Pause", () -> pause(chronometer)),
                new Option("Stop", () -> stop(chronometer)));
    }
}
