package View;

import java.util.Scanner;
import java.util.function.Consumer;

public class Menu {
    public static void run(String[] options, Consumer<Void>... actions) {
        // Print menu options
        for (int i = 0; i < options.length; i++) {
            System.out.println(" [" + i + "] " + options[i]);
        }

        // Read user input
        Scanner sc = new Scanner(System.in);
        int i = -1;

        if (sc.hasNextInt()) {
            i = sc.nextInt();

            if (i < 0 || i < options.length) {
                // throw invalidinput
            }

        } else {
            // thro invalidinput
        }

        // Apply action
        actions[i].accept(null);
    }
}
