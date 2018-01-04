package View;

import Model.ITravels;
import Model.Travel;
import Utils.Option;
import Utils.Parsers;
import Utils.UI;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

public class TravelsView {
    public static void home(ITravels travels) {
        UI.title("Travels");
        UI.menu(new Option("Show travels", () -> TravelsView.show(travels)),
                new Option("Add travel", () -> TravelsView.add(travels)),
                new Option("Remove travel", () -> TravelsView.remove(travels)),
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

/*
    @Override
    public void run() {
        new UI(new Runnable[] {
                new Title("Travels", 1),
                new Menu(new Option[] {
                        new Option("Check time at arrival", this::arrivalTime),
                        new Option("L1","List available travels", this::listAll),
                        new Option("L2","List cheapest travels", this::listCheapest),
                        new Option("L3","List most expensive travels", this::listMostExpensive),
                        new Option("L4","List shortest travels", this::listShortest),
                        new Option("L5","List longest travels", this::listLongest),
                        new Option("L6","List travels between two dates", this::listBetweenDates),
                        new Option("T1","Time until next travel", this::listNext),
                        new Option("T2","Time until last travel", this::listLast),
                        new Option("Back", out::println),
                })
        }).run();
    }

    private void arrivalTime() {
        new UI(new Runnable[]{
                new Title("All booked Travels", 1),
                new IndexedTable(this.travels.getTravels().size() - 1, this.travels.getTravels().toArray()),
                new Input<>("\nTravel to remove?\n>>>",
                        n -> out.println(this.travels.getTravels().get(n - 1).getTimeAtArrival().format(Static.dtf)),
                        NumParser::parseInt),
        }).run();
        this.run();
    }

    private void listAll() {
        new UI(new Runnable[] {
                new Title("All booked Travels", 1),
                new IndexedTable(this.travels.getTravels().size()-1,this.travels.getTravels().toArray()),
        }).run();
        this.run();
    }

    private void listCheapest() {
        new UI(new Runnable[] {
                new Title("Cheapest Travels", 1),
                new IndexedTable(this.travels.getTravels().size()-1,this.travels.cheapestTravels().toArray())
        }).run();
        this.run();
    }

    private void listMostExpensive() {
        new UI(new Runnable[] {
                new Title("Most expensive Travels", 1),
                new IndexedTable(this.travels.getTravels().size()-1,this.travels.mostExpensiveTravels().toArray())
        }).run();
        this.run();
    }

    private void listShortest(){
        new UI(new Runnable[] {
                new Title("Shortest Travels", 1),
                new IndexedTable(this.travels.getTravels().size()-1,this.travels.shortestTravels().toArray())
        }).run();
        this.run();
    }

    private void listLongest(){
        new UI(new Runnable[] {
                new Title("Longest Travels", 1),
                new IndexedTable(this.travels.getTravels().size()-1,this.travels.longestTravels().toArray())
        }).run();
        this.run();
    }

    private void listBetweenDates() {
        Mutable<LocalDateTime> d1 = new Mutable<>(LocalDateTime.now());
        Mutable<LocalDateTime> d2 = new Mutable<>(LocalDateTime.now());

        new UI(new Runnable[] {
                new Title("Travels between Dates", 1),
                new Input<>("First Date (YYYY-MM-DD hh:mm:ss)", d1::set, DateParser::parseDateTime),
                new Input<>("Second Date (YYYY-MM-DD hh:mm:ss)", d2::set, DateParser::parseDateTime),
                new IndexedTable(this.travels.travelsBetweenDates(d1.get(), d2.get()).size() - 1, this.travels.travelsBetweenDates(d1.get(), d2.get()).toArray())

        }).run();
        this.run();
    }

    private void listNext() {
        new UI(new Runnable[] {
                new Title("Next travel", 1),
                new Title("Next travel is due in " + Static.prettyChrono(this.travels.timeUntilNextTravel()),2),
        }).run();
        this.run();
    }

    private void listLast() {
        new UI(new Runnable[] {
                new Title("Last travel", 1),
                new Title("Last travel is due in " + Static.prettyChrono(this.travels.timeUntilLastTravel()),2),
        }).run();
        this.run();
    }
}*/