package View;

import Model.IWorldClock;
import Utils.Parsers;
import Utils.Option;
import Utils.UI;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;


public class WorldClockView {
    public static void home() {
        UI.title("World clock");
        UI.menu(new Option("Difference between two time zones", WorldClockView::difference),
                new Option("Current time at zone", WorldClockView::now),
                new Option("World clock", WorldClockView::worldClock),
                new Option("Zones at time", WorldClockView::zonesAtTime),
                new Option("Back", System.out::println));
    }

    private static void difference() {
        UI.title("World clock");
        UI.subtitle("Difference between two time zones");

        ZoneId id1 = UI.input("Zone ID 1", Parsers::parseZoneId);
        ZoneId id2 = UI.input("Zone ID 2", Parsers::parseZoneId);
        Duration diff = IWorldClock.difference(id1, id2);

        UI.paragraph("The difference is " + diff.toHours() + " hour(s) and " + diff.toMinutes() % 60 + " minute(s)");
        UI.menu(new Option("Back", WorldClockView::home));
    }

    private static void now() {
        UI.title("World clock");
        UI.subtitle("Current time at zone");
        ZoneId id = UI.input("Zone ID", Parsers::parseZoneId);

        UI.paragraph("It is now " + IWorldClock.now(id) + " in " + id);
        UI.menu(new Option("Back", WorldClockView::home));
    }

    private static void worldClock() {
        UI.title("World clock");
        UI.subtitle("World clock");
        UI.list(IWorldClock.worldClock().entrySet(), d -> d.getKey().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm")) + " " + d.getValue().toString());
        UI.menu(new Option("Back", WorldClockView::home));
    }

    private static void zonesAtTime() {
        UI.title("World clock");
        UI.subtitle("Zones at time");
        LocalDateTime time = UI.input("Date [yyyy-mm-dd hh:mm]", Parsers::parseDateTime);
        UI.list(IWorldClock.zonesAtTime(time), String::toString);
        UI.menu(new Option("Back", WorldClockView::home));

    }
}
