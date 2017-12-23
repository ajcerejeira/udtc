package Controller;

import Model.Chronometer;
import Model.DateCalculator;
import Model.Notebook;
import Model.Travels;
import Utils.UI.*;
import View.ChronoView;
import View.DateView;
import View.NotebookView;
import View.TravelView;


public class Controller implements Runnable{
    private Chronometer chronometer;
    private Notebook notebook;
    private DateCalculator dateCalculator;
    private Travels travels;

    public Controller() {
        this.chronometer = new Chronometer();
        this.notebook = new Notebook();
        this.dateCalculator = new DateCalculator();
        this.travels = new Travels();
    }

    @Override
    public void run(){
        Chronometer chronometer = new Chronometer();

        while (true) {
            new UI(new Runnable[] {
                    new Title("UDTC", 1),
                    new Menu(new Option[] {
                            new Option("C", "Chronometer", new ChronoView(this.chronometer)),
                            new Option("N", "Notebook", new NotebookView(this.notebook)),
                            new Option("T","Traveling", new TravelView(this.travels)),
                            new Option("D","DateTime Calculator", new DateView(this.dateCalculator)),
                            new Option("Q", "Quit", () -> System.exit(-1)),
                    }),
            }).run();
        }
    }
}