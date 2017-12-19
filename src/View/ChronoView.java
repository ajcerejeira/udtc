package View;

import Exceptions.InvalidInputException;
import Exceptions.ReturnException;
import Utils.Input.InputHandler;

import java.util.Optional;

import static java.lang.System.out;

public class ChronoView implements View {
    @Override
    public void printMenu() throws InvalidInputException, ReturnException {
        printIntro();
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

    private void printIntro() {
        out.println("      _____________________________________CHRONOMETER___________________________________");
        out.println("     /                                                                                   \\");
        out.println("     | ----------------------------------------------------------------------------------|");
        out.println("     | 1 - START                                                                         |");
        out.println("     | ----------------------------------------------------------------------------------|");
        out.println("     | 2 - STOP                                                                          |");
        out.println("     | ----------------------------------------------------------------------------------|");
        out.println("     | 3 - '<-' RETURN                                                                   |");
        out.println("     | ----------------------------------------------------------------------------------|");
        out.println("     \\___________________________________________________________________________________/\n");
    }

    private void stop() {
        out.println("Chronometer has stopped!");
    }

    private void start() {
        out.println("Chronometer has started!");
    }

    public void run() {
        Menu.run(new String[] { "Start", "Stop" },
                 x -> out.println("Chronometer has started"),
                 x -> out.println("Chronometer has stopped"));
    }
}
