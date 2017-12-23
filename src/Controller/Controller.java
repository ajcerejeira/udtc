package Controller;

import Model.Chronometer;
import Model.DateCalculator;
import Model.Notebook;
import Model.Travels;
import Utils.UI.Menu;
import Utils.UI.Option;
import Utils.UI.Title;
import Utils.UI.UI;
import View.*;


public class Controller implements Runnable{

    @Override
    public void run(){
        new UI(new Runnable[] {
                new Title("UDTC", 1),
                new Menu    (new Option[] {
                        new Option("C", "Chronometer", new ChronoView(new Chronometer())),
                        new Option("N", "Notebook", new NotebookView(new Notebook())),
                        new Option("T","Traveling", new TravelView(new Travels())),
                        new Option("D","DateTime Calculator", new DateView(new DateCalculator())),
                        new Option("Q", "Quit", () -> System.exit(-1)),
                }),
        }).run();
    }
}