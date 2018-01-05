package Controller;

import Model.*;
import Utils.Option;
import View.*;
import Utils.UI;

public class Controller implements Runnable{
    private IChronometer chronometer;
    private INotebook notebook;
    private ITravels travels;
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
            UI.title("UDTC");
            UI.paragraph("Welcome to the Universal Date and Time Calculator.\nTo start select one of the options bellow:");
            UI.menu(new Option("Age calculator", AgeCalculatorView::home),
                    new Option("Calendar", () -> CalendarView.home(this.calendar)),
                    new Option("Chronometer", () -> ChronometerView.home(this.chronometer)),
                    new Option("Date calculator", DateCalculatorView::home),
                    new Option("Notebook", () -> NotebookView.home(this.notebook)),
//                    new Option("Time calculator", TimeCalculatorView::home),
                    new Option("Travels", () -> TravelsView.home(this.travels)),
                    new Option("World clock", WorldClockView::home),
                    new Option("Quit", () -> System.exit(-1))
            );
        }
    }
}