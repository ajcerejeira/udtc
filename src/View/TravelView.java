package View;

import Model.Travel;
import Model.Travels;
import Utils.Input.DateParser;
import Utils.Static;

import java.time.Duration;
import java.time.LocalDateTime;
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
                new Option("Remove travel", this::remove),
                new Option("Check time at arrival", this::arrivalTime),
                new Option("L1","List available travels", this::listAll),
                new Option("L2","List cheapest travels", this::listCheapest),
                new Option("L3","List most expensive travels", this::listMostExpensive),
                new Option("L4","List shortest travels", this::listShortest),
                new Option("L5","List longest travels", this::listLongest),
                new Option("L6","List travels between two dates", this::listBetweenDates),
                new Option("T1","Time until next travel", this::listNext),
                new Option("T2","Time until last travel", this::listLast),
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
        t.setDepartureDate(DateParser.parseDateTime(sc.nextLine()));
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

    private void remove() {
        int index=1;
        out.println("Available travels");
        for(Travel t: this.travels.getTravels()){
            out.println("["+index+"] " + t.toString());
            index++;
        }
        out.print("Travel to remove\n>>> ");
        this.travels.removeTravel(new Scanner(System.in).nextInt()-1);
        out.println("Travel has been removed.");
    }

    private void arrivalTime() {
        int index=1;
        out.println("Available travels");
        for(Travel t: this.travels.getTravels()){
            out.println("["+index+"] " + t.toString());
            index++;
        }
        out.print("Travel to check\n>>> ");
        out.println("You will arrive at " + this.travels.getTravels().get(new Scanner(System.in).nextInt()-1).getTimeAtArrival().format(Static.dtf) + " local time.");
    }

    private void listAll() {
        for(Travel t: this.travels.getTravels()){
            out.println(t.toString());
        }
        this.run();
    }

    private void listCheapest() {
        out.print("How many travels should I list? \n>>> ");
        for(Travel t: this.travels.cheapestTravels(new Scanner(System.in).nextInt())){
            out.println(t.toString());
        }
        this.run();
    }

    private void listMostExpensive() {
        out.print("How many travels should I list? \n>>> ");
        for(Travel t: this.travels.mostExpensiveTravels(new Scanner(System.in).nextInt())){
            out.println(t.toString());
        }
        this.run();
    }

    private void listShortest(){
        out.print("How many travels should I list? \n>>> ");
        for(Travel t: this.travels.shortestTravels(new Scanner(System.in).nextInt())){
            out.println(t.toString());
        }
        this.run();
    }

    private void listLongest(){
        out.print("How many travels should I list? \n>>> ");
        for(Travel t: this.travels.longestTravels(new Scanner(System.in).nextInt())){
            out.println(t.toString());
        }
        this.run();
    }

    private void listBetweenDates(){
        LocalDateTime d1,d2;
        out.print("First date: (YYYY-MM-DD hh:mm:ss) \n>>> ");
        d1=DateParser.parseDateTime(new Scanner(System.in).nextLine());
        out.print("Second date: (YYYY-MM-DD hh:mm:ss) \n>>> ");
        d2=DateParser.parseDateTime(new Scanner(System.in).nextLine());
        for(Travel t: this.travels.travelsBetweenDates(d1,d2)){
            out.println(t.toString());
        }
        this.run();
    }

    private void listNext() {
        out.println("Next travel is due in: " + this.travels.timeUntilNextTravel());
        this.run();
    }

    private void listLast() {
        out.println("Last travel is due in: " + this.travels.timeUntilLastTravel());
        this.run();
    }
}