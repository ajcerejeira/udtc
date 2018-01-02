package Utils;

import java.util.Optional;
import java.util.Scanner;
import java.util.function.Function;

public class UI {
    public static void title(String text) {
        System.out.println("\u001B[2J");
        System.out.println("\u001B[7m" + text + "\u001B[m");
        System.out.println();
    }

    public static void subtitle(String text) {
        System.out.println("\u001B[1m" + text + "\u001B[m");
        System.out.println();
    }

    public static void paragraph(String text) {
        System.out.println(text);
        System.out.println();
    }

    public static <T> T input(String prompt, Function<String, Optional<T>> parser) {
        System.out.print(prompt + ": ");
        Optional<T> result = Optional.empty();
        Scanner sc = new Scanner(System.in);

        while (!result.isPresent()) {
            String line = sc.nextLine();
            result = parser.apply(line);
        }

        return result.get();
    }

    @SafeVarargs
    public static void menu(Option ... options) {
        // Print the menu
        System.out.println();

        for (int i = 0; i < options.length; i++) {
            System.out.println(" " + (i + 1) + ") " + options[i].getName());
        }

        // Ask for the input and run the desired option if valid.
        Scanner sc = new Scanner(System.in);
        int option = -1;

        for (int i = 0; !(option >= 0 && option < options.length); i++) {
            if (i != 0) {
                System.out.println("The chosen option is not valid. Please try again.");
            }

            System.out.println();
            System.out.print("[1-" + options.length + "]: ");

            try {
                option = Integer.valueOf(sc.nextLine()) - 1;
            } catch (Exception e) {
                option = -1;
            }
        }

        options[option].getAction().run();
    }

    public static <T> void list(Iterable<T> elements, Function<T, String> toString) {
        for (T element : elements) {
            System.out.println(toString.apply(element));
        }
    }
}
