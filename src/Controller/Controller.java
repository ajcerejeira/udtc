package Controller;

import Model.*;
import Utils.UI.*;
import View.*;

public class Controller implements Runnable{
    private Notebook notebook;
    private Travels travels;
    private Calendar calendar;

    public Controller() {
        this.notebook = new Notebook();
        this.travels = new Travels();
        this.calendar = new Calendar();
    }

    @Override
    public void run(){
        while (true) {
            new UI(new Runnable[] {
                    new Title("UDTC", 1),
                    new Menu(new Option[] {
                            new Option("A","Age Calculator", new AgeView()),
                            new Option("CL","Calendar", new CalendarView(this.calendar)),
                            new Option("C", "Chronometer", new ChronometerView(new Chronometer())),
                            new Option("D", "DateTime Calculator", new DateView()),
                            new Option("N", "Notebook", new NotebookView(this.notebook)),
                            new Option("T", "Traveling", new TravelView(this.travels)),
                            new Option("Q", "Quit", () -> System.exit(-1)),
                    }),
            }).run();
        }
    }
}