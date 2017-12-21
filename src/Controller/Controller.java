package Controller;

import Model.Chronometer;
import View.NotebookView;
import View.ChronoView;
import View.Menu;
import View.Option;


public class Controller implements Runnable{

    @Override
    public void run(){
        Menu menu = new Menu(new Option[] {
                new Option("Chronometer", () -> new ChronoView(new Chronometer()).run()),
                new Option("Notebook", () -> new NotebookView().run())
        });

        while (true)
            menu.run();
    }
}
