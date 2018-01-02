package View;

import Model.IWorldClock;
import Utils.Parsers;
import Utils.Option;
import Utils.UI;

import java.time.Duration;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;


public class WorldClockView {
    public static void home() {
        UI.title("World clock");
        UI.menu(new Option("Difference between two time zones", WorldClockView::difference),
                new Option("Current time at zone", WorldClockView::now),
                new Option("Back", System.out::println));
    }

    private static void difference() {
        UI.title("World clock");
        UI.subtitle("Difference between two time zones");

        ZoneId id1 = UI.input("Zone ID 1", Parsers::parseZoneId);
        ZoneId id2 = UI.input("Zone ID 2", Parsers::parseZoneId);
        Duration duration = IWorldClock.difference(id1, id2);

        System.out.println("The difference is " + duration.get(ChronoUnit.HOURS) + " hour(s) and "
                + duration.get(ChronoUnit.MINUTES) + " minute(s)");

        UI.menu(new Option("Back", WorldClockView::home));
    }

    private static void now() {
        UI.title("World clock");
        UI.subtitle("Current time at zone");
        ZoneId id = UI.input("Zone ID", Parsers::parseZoneId);

        System.out.println("It is now " + IWorldClock.now(id) + " in " + id);

        UI.menu(new Option("Back", WorldClockView::home));
    }

}
