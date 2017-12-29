package Controller;

import Model.*;
import Utils.Option;
import View.*;
import Utils.TUI;

public class Controller implements Runnable{
    private IChronometer chronometer;
    private Notebook notebook;
    private Travels travels;
    private Calendar calendar;

    public Controller() {
        this.chronometer = new Chronometer();
        this.notebook = new Notebook();
        this.travels = new Travels();
        this.calendar = new Calendar();
    }

    @Override
    public void run(){
        while (true) {
            TUI.title("UDTC", 1);
            TUI.menu(new Utils.Option[] {
                    new Option("A", "Age calculator", AgeCalculatorView::home),
                    new Option("CL", "Calendar", () -> CalendarView.home(this.calendar)),
                    new Option("CH", "Chronometer", () -> ChronometerView.home(this.chronometer)),
                    new Option("N", "Notebook", () -> NotebookView.home(this.notebook)),
                    new Option("Q", "Quit", () -> System.exit(-1)),
            });
        }

        /*
        while (true) {
            new UI(new Runnable[] {
                    new Title("UDTC", 1),
                    new Menu(new Option[] {
                            new Option("A","Age Calculator", new AgeCalculatorView()),
                            new Option("CL","Calendar", new CalendarView(this.calendar)),
                            new Option("C", "Chronometer", new ChronometerView(new Chronometer())),
                            new Option("D", "DateTime Calculator", new DateView()),
                            new Option("N", "Notebook", new NotebookView(this.notebook)),
                            new Option("T", "Traveling", new TravelView(this.travels)),
                            new Option("Q", "Quit", () -> System.exit(-1)),
                    }),
            }).run();
        }*/
    }
}