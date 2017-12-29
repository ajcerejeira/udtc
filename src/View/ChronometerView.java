package View;

import Model.IChronometer;
import Utils.Static;

import Utils.TUI;
import Utils.Option;

import java.time.Duration;

public class ChronometerView {
    public static void home(IChronometer chronometer) {
        TUI.title("Chronometer", 1);
        TUI.menu(new Option[] {
                new Option("S", "Start", () -> start(chronometer)),
                new Option("B", "Back", System.out::println),
        });
    }

    private static void start(IChronometer chronometer) {
        chronometer.start();

        TUI.title("Chronometer", 1);
        TUI.title("Chronometer is running", 2);
        TUI.menu(new Option[] {
                new Option("P", "Pause", () -> pause(chronometer)),
                new Option("S", "Stop", () -> stop(chronometer)),
        });
    }

    private static void stop(IChronometer chronometer) {
        Duration runtime = chronometer.stop();

        TUI.title("Chronometer", 1);
        TUI.title("Chronometer has stopped...", 2);
        TUI.title("Elapsed time: " + Static.prettyChrono(runtime), 3);

        TUI.menu(new Option[] {
                new Option("B", "Back", () -> home(chronometer)),
        });
    }

    private static void pause(IChronometer chronometer) {
        Duration runtime = chronometer.pause();

        TUI.title("Chronometer", 1);
        TUI.title("Chronometer has been paused...", 2);
        TUI.title("Elapsed time: " + Static.prettyChrono(runtime), 3);
        TUI.menu(new Option[] {
                new Option("R", "Resume", () -> resume(chronometer)),
                new Option("S", "Stop", () -> stop(chronometer)),
        });
    }

    private static void resume(IChronometer chronometer) {
        chronometer.resume();

        TUI.title("Chronometer", 1);
        TUI.title("Chronometer has been resumed...", 2);
        TUI.menu(new Option[] {
                new Option("P", "Pause", () -> pause(chronometer)),
                new Option("S", "Stop", () -> stop(chronometer)),
        });
    }
}
