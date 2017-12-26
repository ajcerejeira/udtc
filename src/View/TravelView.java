package View;

import Exceptions.InvalidDateException;
import Exceptions.InvalidInputException;
import Exceptions.ReturnException;
import Model.Travel;
import Model.Travels;
import Utils.DateParser;
import Utils.Mutable;
import Utils.NumParser;
import Utils.Static;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Scanner;

import static java.lang.System.out;
import Utils.UI.*;

public class TravelView implements Runnable{
    private Travels travels;

    public TravelView(Travels travels) {
        this.travels = travels;
        travels.addTravel(new Travel("Portugal","Iran", Duration.ZERO, LocalDateTime.of(2017,12,1,14,0,0) , 400.0));
        travels.addTravel(new Travel("America/Indianapolis","America/Virgin", Duration.ZERO, LocalDateTime.of(2017,12,1,14,0,0) , 500.0));
        travels.addTravel(new Travel("Canada/Atlantic","Canada/Newfoundland", Duration.ZERO, LocalDateTime.of(2017,12,1,14,0,0) , 600.0));
        travels.addTravel(new Travel("Brazil/East","Portugal", Duration.ZERO, LocalDateTime.of(2017,12,1,14,0,0) , 0.0));
        travels.addTravel(new Travel("Africa/Luanda","Asia/Jerusalem", Duration.ZERO, LocalDateTime.of(2017,12,1,14,0,0) , 400.0));
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
                        new Option("Back", out::println),
                })
        }).run();
    }

    private void add() {
        Travel t = new Travel();

        new UI(new Runnable[] {
                new Title("Travels", 1),
                new Title("Add travel", 2),

                new Input<>("Origin", t::setOrigin, Optional::of),
                new Input<>("Destination", t::setDestination, Optional::of),
                new Input<>("Departure date (YYYY-MM-DD hh:mm:ss)", t::setDepartureDate, DateParser::parseDateTime2),
                new Input<>("Duration\n Hours", h -> t.setDuration(t.getDuration().plusHours(h)), NumParser::parseInt),
                new Input<>(" Minutes", m -> t.setDuration(t.getDuration().plusMinutes(m)), NumParser::parseInt),
                new Input<>("Cost", t::setCost, NumParser::parseDouble),
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
                new Input<>("\nTravel to remove?\n>>>", n -> this.travels.removeTravel(n - 1), NumParser::parseInt),
        }).run();
        this.run();
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
                new Input<>("First Date (YYYY-MM-DD hh:mm:ss)", d1::set, DateParser::parseDateTime2),
                new Input<>("Second Date (YYYY-MM-DD hh:mm:ss)", d2::set, DateParser::parseDateTime2),
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
}