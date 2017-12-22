package View;

import Model.Travel;
import Model.Travels;
import Utils.Input.DateParser;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
import static java.lang.System.out;

public class TravelView implements Runnable{
    private Travels travels;

    public TravelView(Travels travels) {
        this.travels = travels;
    }

    @Override
    public void run() {
        Menu menu = new Menu(new Option[] {
                new Option("Add travel", this::add),
                //new Option("Remove travel", this::remove),
                new Option("List available travels", this::listAll),
                /*new Option("List cheapest travels", this::listCheapest),
                new Option("List most expensive travels", this::listMostExpensive),
                new Option("List shortest travels", this::listShortest),
                new Option("List longest travels", this::listLongest),
                new Option("List travels between two dates", this::listBetweenDates),
                new Option("Time until next travel", this::listNext),
                new Option("Time until last travel", this::listLast),*/
                new Option("Return", () -> out.println())
        });
        menu.run();
    }

    private void add() {
        Travel t = new Travel();
        Duration dur = Duration.ZERO;
        LocalDateTime dt;
        Scanner sc = new Scanner(System.in);

        out.print("Origin: ");
        t.setOrigin(sc.nextLine());
        out.print("Destination: ");
        t.setDestination(sc.nextLine());
        out.print("Departure date: (YYYY-MM-DD hh:mm:ss)");
        t.setDepartureDate(DateParser.parseDate(sc.nextLine()));
        out.println("Duration:");
        out.print("       -> Hours: ");
        dur = dur.plusHours(sc.nextInt());
        out.print("       -> Minutes: ");
        dur = dur.plusMinutes(sc.nextInt());
        t.setDuration(dur);
        out.print("Cost: ");
        t.setCost(sc.nextDouble());

        this.travels.addTravel(t);
        out.println("Successfully added a new Travel");
        this.run();
    }

    private void listAll() {
        for(Travel t: this.travels.getTravels()){
            out.println(t.toString());
        }
        this.run();
    }
}
