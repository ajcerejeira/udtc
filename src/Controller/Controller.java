package Controller;

import Model.*;
import Utils.UI.*;
import Utils.UI.Menu;
import Utils.UI.Option;
import View.*;

public class Controller implements Runnable{
    private Notebook notebook;
    private DateCalculator dateCalculator;
    private Travels travels;
    private Calendar calendar;
    private Age age;

    public Controller() {
        this.age = new Age();
        this.notebook = new Notebook();
        this.dateCalculator = new DateCalculator();
        this.travels = new Travels();
        this.calendar = new Calendar();
    }

    @Override
    public void run(){
        while (true) {
            new UI(new Runnable[] {
                    new Title("UDTC", 1),
                    new Menu(new Option[] {
                            new Option("A","Age Calculator", new AgeView(this.age)),
                            new Option("CL","Calendar", new CalendarView(this.calendar)),
                            new Option("C", "Chronometer", new ChronoView(new Chronometer())),
                            new Option("D", "DateTime Calculator", new DateView(this.dateCalculator)),
                            new Option("N", "Notebook", new NotebookView(this.notebook)),
                            new Option("T", "Traveling", new TravelView(this.travels)),
                            new Option("Q", "Quit", () -> System.exit(-1)),
                    }),
            }).run();
        }
    }
}