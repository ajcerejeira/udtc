import Controller.Controller;
import Utils.UI.Menu;
import Utils.UI.Option;
import Utils.UI.Title;
import Utils.UI.UI;
import View.NotebookView;

public class Main {
    public static void main(String[] args){
        new UI(new Runnable[] {
           new Title("UDTC", 1),
           new Menu(new Option[] {
                   new Option("C", "Chronometer", () -> System.exit(-1)),
                   new Option("N", "Notebook", new NotebookView()),
                   new Option("Q", "Quit", () -> System.exit(-1)),
           }),
        }).run();

        //Controller c = new Controller();
        //c.run();
    }
}