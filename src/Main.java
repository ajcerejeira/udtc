import Exceptions.*;
import View.*;
import static java.lang.System.out;

public class Main {

    public static void main(String[] args) {
        View v = new ChronoView();
        try {
            v.printMenu();
        } catch (InvalidInputException e) {
            out.println("Invalid input!");
        } catch (ReturnException e) {
            out.println("Return to previous menu detected!");
        }
    }
}