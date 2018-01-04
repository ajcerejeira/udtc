package View;

import Model.ITravels;
import Model.Travel;
import Utils.Option;
import Utils.Parsers;
import Utils.UI;
import org.omg.CORBA.TRANSACTION_MODE;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class TravelsView {
    private static Function<Duration, String> durationToString = (d) -> {
        long hours = d.getSeconds() / 3600;
        long minutes = (d.getSeconds() % 3600) / 60;
        long seconds = d.getSeconds();

        return String.format("%d:%02d:%02d", hours, minutes, seconds);
    };

    public static void home(ITravels travels) {
        UI.title("Travels");
        UI.menu(new Option("Show all travels", () -> TravelsView.show(travels)),
                new Option("Add travel", () -> TravelsView.add(travels)),
                new Option("Remove travel", () -> TravelsView.remove(travels)),
                new Option("Travels between dates", () -> TravelsView.between(travels)),
                new Option("Next travel", () -> TravelsView.next(travels)),
                new Option("Last travel", () -> TravelsView.last(travels)),
                new Option("Check time at arrival", () -> TravelsView.checkTimeAtArrival(travels)),
                new Option("Cheapest travels", () -> TravelsView.cheapest(travels)),
                new Option("Most expensive travels", () -> TravelsView.mostExpensive(travels)),
                new Option("Shortest travels", () -> TravelsView.shortest(travels)),
                new Option("Longest travels", () -> TravelsView.longest(travels)),
                new Option("Save travels", () -> TravelsView.save(travels)),
                new Option("Read travels", () -> TravelsView.read(travels)),
                new Option("Back", System.out::println));
    }

    private static void show(ITravels travels) {
        UI.title("Travels");
        UI.subtitle("Show travels");
        UI.list(travels.getTravels(), Travel::toString);
        UI.menu(new Option("Back", () -> TravelsView.home(travels)));
    }

    private static void add(ITravels travels) {
        UI.title("Travels");
        UI.subtitle("Add travel");

        String origin = UI.input("Origin", Optional::of);
        String destination = UI.input("Destination", Optional::of);
        Duration duration = UI.input("Duration [hh:mm]", Parsers::parseDuration);
        LocalDateTime departure = UI.input("Departure date [yyyy-md-dd hh:mm]", Parsers::parseDateTime);
        double cost = UI.input("Cost", Parsers::parseDouble);

        travels.addTravel(new Travel(origin, destination, duration, departure, cost));

        home(travels);
    }

    private static void remove(ITravels travels) {
        if (travels.getTravels().size() == 0) {
            UI.paragraph("There are no travels to remove.");
            UI.menu(new Option("Back", () -> TravelsView.home(travels)));
            return;
        }

        Option[] options = travels.getTravels()
                .stream()
                .map(travel -> new Option(travel.toString(), () -> travels.removeTravel(travel)))
                .toArray(Option[]::new);
        UI.title("Travels");
        UI.subtitle("Remove travel");
        UI.menu(options);

        home(travels);
    }

    private static void between(ITravels travels) {
        UI.title("Travels");
        UI.subtitle("Travels between");
        LocalDateTime from = UI.input("From [yyyy-mm-dd hh:mm]", Parsers::parseDateTime);
        LocalDateTime to = UI.input("To [yyyy-mm-dd hh:mm]", Parsers::parseDateTime);
        UI.list(travels.travelsBetweenDates(from, to), Travel::toString);
        UI.menu(new Option("Back", () -> home(travels)));
    }

    private static void next(ITravels travels) {
        UI.title("Travels");
        UI.subtitle("Next travel");
        UI.paragraph("Next travel is due in " + durationToString.apply(travels.timeUntilNextTravel()));
        UI.menu(new Option("Back", () -> home(travels)));
    }

    private static void last(ITravels travels) {
        UI.title("Travels");
        UI.subtitle("Last travel");
        UI.paragraph("Last travel is due in" + durationToString.apply(travels.timeUntilLastTravel()));
        UI.menu(new Option("Back", () -> home(travels)));
    }

    private static void checkTimeAtArrival(ITravels travels) {
        if (travels.getTravels().size() == 0) {
            UI.paragraph("There are no travels to check.");
            UI.menu(new Option("Back", () -> TravelsView.home(travels)));
            return;
        }

        Option[] options = travels.getTravels().stream()
                .map(travel -> new Option(travel.toString(),
                        () -> System.out.println("Arrives at " + travel.getTimeAtArrival().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm")))))
                .toArray(Option[]::new);

        UI.title("Travels");
        UI.subtitle("Check time at arrival");
        UI.paragraph("Select a travel to check its time at arrival");
        UI.menu(options);
        UI.menu(new Option("Back", () -> home(travels)));
    }

    private static void cheapest(ITravels travels) {
        UI.title("Travels");
        UI.subtitle("Chepeast travels");
        List<Travel> t = travels.cheapestTravels();
        int n = UI.input("Number of travels [0-" + t.size() + "]", Parsers::parseInt);
        UI.list(t.subList(0, n), Travel::toString);
        UI.menu(new Option("Back", () -> home(travels)));
    }

    private static void mostExpensive(ITravels travels) {
        UI.title("Travels");
        UI.subtitle("Most expensive travels");
        List<Travel> t = travels.mostExpensiveTravels();
        int n = UI.input("Number of travels [0-" + t.size() + "]", Parsers::parseInt);
        UI.list(t.subList(0, n), Travel::toString);
        UI.menu(new Option("Back", () -> home(travels)));
    }

    private static void shortest(ITravels travels) {
        UI.title("Travels");
        UI.subtitle("Shortest travels");
        List<Travel> t = travels.shortestTravels();
        int n = UI.input("Number of travels [0-" + t.size() + "]", Parsers::parseInt);
        UI.list(t.subList(0, n), Travel::toString);
        UI.menu(new Option("Back", () -> home(travels)));
    }

    private static void longest(ITravels travels) {
        UI.title("Travels");
        UI.subtitle("Longest travels");
        List<Travel> t = travels.longestTravels();
        int n = UI.input("Number of travels [0-" + t.size() + "]", Parsers::parseInt);
        UI.list(t.subList(0, n), Travel::toString);
        UI.menu(new Option("Back", () -> home(travels)));
    }

    private static void save(ITravels travels) {
        UI.title("Travels");
        UI.subtitle("Save travels");
        String path = UI.input("Output file", Optional::of);

        try {
            Files.write(Paths.get(path), travels.toString().getBytes());
        } catch (IOException e) {
            System.out.println("There was an error writing to the specified file");
        }

        home(travels);
    }

    private static void read(ITravels travels) {
        UI.title("Travels");
        UI.subtitle("Read travels");
        String path = UI.input("Input file", Optional::of);

        try {
            String content = new String(Files.readAllBytes(Paths.get(path)));
            travels.read(content);
        } catch (IOException e) {
            System.out.println("There was an error reading the specified file");
        }

        home(travels);
    }
}