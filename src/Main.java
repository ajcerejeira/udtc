import Exceptions.*;
import Model.Chronometer;
import View.*;
import static java.lang.System.out;

public class Main {

    public static void main(String[] args) {
        Chronometer c = new Chronometer();
        View v = new ChronoView();
        v.run(c);
     /*   View v = new ChronoView();
        try {
            v.printMenu();
        } catch (InvalidInputException e) {
            out.println("Invalid input!");
        } catch (ReturnException e) {
            out.println("Return to previous menu detected!");
        }*/
    }
}