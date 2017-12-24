package View;

import Exceptions.InvalidDateException;
import Exceptions.InvalidInputException;
import Exceptions.ReturnException;
import Model.Travel;
import Model.Travels;
import Utils.DateParser;
import Utils.Static;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Scanner;

import static java.lang.System.out;
import Utils.UI.*;

public class TravelView implements Runnable{
    private Travels travels;

    public TravelView(Travels travels) {
        this.travels = travels;
        travels.addTravel(new Travel("Portugal","Iran", Duration.ZERO, LocalDateTime.of(2017,12,01,14,00,00) , 400.0));
        travels.addTravel(new Travel("America/Indianapolis","America/Virgin", Duration.ZERO, LocalDateTime.of(2017,12,01,14,00,00) , 500.0));
        travels.addTravel(new Travel("Canada/Atlantic","Canada/Newfoundland", Duration.ZERO, LocalDateTime.of(2017,12,01,14,00,00) , 600.0));
        travels.addTravel(new Travel("Brazil/East","Portugal", Duration.ZERO, LocalDateTime.of(2017,12,01,14,00,00) , 0.0));
        travels.addTravel(new Travel("Africa/Luanda","Asia/Jerusalem", Duration.ZERO, LocalDateTime.of(2017,12,01,14,00,00) , 400.0));
    }

    @Override
    public void run() {
        new UI(new Runnable[] {
                new Title("Travels", 1),
                new Menu(new Option[] {
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
                        new Option("Back", () -> out.println())
                })
        }).run();
    }

    private void add() {
        Travel t = new Travel();

        new UI(new Runnable[] {
                new Title("Travels", 1),
                new Title("Add travel", 2),

                new Input("Origin", t::setOrigin),
                new Input("Destination", t::setDestination),
                new Input("Departure date (YYYY-MM-DD hh:mm:ss)", x -> {
                    try {
                        t.setDepartureDate(DateParser.parseDateTime(x));
                    } catch (InvalidDateException e) {
                        out.println(e.getMessage());
                    }
                }),
                new Input("Duration\n Hours", m -> {
                    try{
                        t.setDuration(t.getDuration().plusHours(Integer.valueOf(m)));
                    }catch(NumberFormatException e){
                        out.println("Invalid input received!");
                    }
                }),
                new Input(" Minutes", m -> {
                    try {
                        t.setDuration(t.getDuration().plusMinutes(Integer.valueOf(m)));
                    } catch (NumberFormatException e) {
                        out.println("Invalid input received!");
                    }
                }),
                new Input("Cost", c -> {
                    try{
                        t.setCost(Double.valueOf(c));
                    }catch(NumberFormatException e){
                        out.println("Invalid input received!");
                    }
                }),
        }).run();

        if(t.isValid())
            this.travels.addTravel(t);
        else
            out.println("\nInvalid data");
        this.run();
    }

    private void remove() {
        new UI(new Runnable[] {
                new Title("Available travels", 1),
                new IndexedTable(this.travels.getTravels().size()-1,this.travels.getTravels().toArray()),
                new Input("\nTravel to remove?\n>>>", x -> {
                    try {
                        this.travels.removeTravel(Integer.parseInt(x)-1);
                        out.println("Travel has been removed.");
                    } catch (Exception e) {
                        out.println("Invalid range/input!");
                    }
                })
        }).run();
        this.run();
    }

    private void arrivalTime() {
        new UI(new Runnable[] {
                new Title("All booked Travels", 1),
                new IndexedTable(this.travels.getTravels().size()-1,this.travels.getTravels().toArray()),
                new Input("\nTravel to remove?\n>>>", x -> {
                    try {
                        String str = this.travels.getTravels().get(Integer.parseInt(x)-1).getTimeAtArrival().format(Static.dtf);
                        out.println("You will arrive at " + str + " local time.");
                    } catch (Exception e) {
                        out.println(e.getMessage());
                    }
                })
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
        LocalDateTime[] d1 = new LocalDateTime[1];
        LocalDateTime[] d2 = new LocalDateTime[1];
        final boolean[] readyToList = {true};

        new UI(new Runnable[] {
                new Title("Travels between Dates", 1),
                new Input("First Date (YYYY-MM-DD hh:mm:ss)", x -> {
                    try {
                        d1[0] = DateParser.parseDateTime(x);
                    } catch (InvalidDateException e) {
                        readyToList[0] =false;
                        out.println(e.getMessage());
                    }
                }),
                new Input("Second Date (YYYY-MM-DD hh:mm:ss)", y -> {
                    try {
                        d2[0] = DateParser.parseDateTime(y);
                    } catch (InvalidDateException e) {
                        readyToList[0] =false;
                        out.println(e.getMessage());
                    }
                })
        }).run();

        if(readyToList[0]) {
            new UI(new Runnable[]{
                    new IndexedTable(this.travels.travelsBetweenDates(d1[0], d2[0]).size() - 1, this.travels.travelsBetweenDates(d1[0], d2[0]).toArray())
            }).run();
        }
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
}