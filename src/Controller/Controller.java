package Controller;

import Model.Chronometer;
import Model.Travels;
import View.*;


public class Controller implements Runnable{

    @Override
    public void run(){
        Menu menu = new Menu(new Option[] {
                new Option("Chronometer", () -> new ChronoView(new Chronometer()).run()),
                new Option("Notebook", () -> new NotebookView().run()),
                new Option("Traveling", () -> new TravelView(new Travels()).run())
        });

        while (true)
            menu.run();
    }
}
