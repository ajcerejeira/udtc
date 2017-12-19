package View;

import Exceptions.InvalidInputException;
import Exceptions.ReturnException;
import Utils.Input.InputHandler;
import static java.lang.System.out;

public class ChronoView implements View {
    @Override
    public void printMenu() throws InvalidInputException, ReturnException {
        int input = InputHandler.readInteger();
        switch (input){
            case 1:
                start();
                break;
            case 2:
                stop();
                break;
        }
    }

    private void stop() {
        out.println("Chronometer has stopped!");
    }

    private void start() {
        out.println("Chronometer has started!");
    }
}
