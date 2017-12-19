package View;

import Exceptions.InvalidInputException;
import Exceptions.ReturnException;
import Model.Chronometer;
import Utils.Input.InputHandler;

import java.util.Optional;

import static java.lang.System.out;

public class ChronoView implements View {
    @Override
    public void printMenu() throws InvalidInputException, ReturnException {
        out.print("Choice: ");
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

    @Override
    public void run(Object o) {
        Menu.run(new String[] { "Start", "Stop", "Get Runtime" },
                x -> out.println("Chronometer has started"),
                x -> out.println("Chronometer has stopped"),
                x -> out.println("RunTime: " + ((Chronometer) o).getRuntime()));
    }

    private void stop() {
        out.println("Chronometer has stopped!");
    }

    private void start() {
        out.println("Chronometer has started!");
    }
}
