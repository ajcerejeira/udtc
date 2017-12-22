package Utils.UI;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Menu implements Runnable {
    private Option[] options;

    public Menu(Option[] options) {
        this.options = options.clone();
    }

    @Override
    public void run() {
        for (Option option : this.options) {
            System.out.println(" " + option);
        }

        try {
            System.out.print("\n>>> ");
            String command = new Scanner(System.in).nextLine();
            Option option = Arrays.stream(this.options).filter(o -> o.getCommand().equals(command)).findFirst().get();
            option.run();
        } catch (NoSuchElementException e) {
            System.out.println("Invalid option. Please try again.\n");
            this.run();
        }
    }
}