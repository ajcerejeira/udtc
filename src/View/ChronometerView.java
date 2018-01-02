package View;

import Model.IChronometer;
import Utils.Option;
import Utils.UI;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.function.Function;

public class ChronometerView {
    private static Function<Duration, String> durationToString = (d) -> {
        long hours = d.getSeconds() / 3600;
        long minutes = (d.getSeconds() % 3600) / 60;
        long seconds = d.getSeconds();

        return String.format("%d:%02d:%02d", hours, minutes, seconds);
    };

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
        UI.subtitle("Elapsed time: " + durationToString.apply(runtime));

        UI.menu(new Option("Back", () -> home(chronometer)));
    }

    private static void pause(IChronometer chronometer) {
        Duration runtime = chronometer.pause();

        UI.title("Chronometer");
        UI.subtitle("Chronometer has been paused...");
        UI.subtitle("Elapsed time: " + durationToString.apply(runtime));
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
