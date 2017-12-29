package Utils;

import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Function;

public class TUI {
    /**
     *
     * @param title
     * @param level
     */
    public static void title(String title, int level) {
        switch(level) {
            case 1:
                System.out.println("\u001B[7m" + title.toUpperCase() + "\n\u001B[m");
                break;
            case 2:
                System.out.println("\u001B[1m" + title + "\n\u001B[m");
                break;
            case 3:
                System.out.println(title.toUpperCase() + "\n");
                break;
            default:
                System.out.println(title);
        }
    }

    /**
     *
     * @param label
     * @param action
     * @param <T>
     * @return
     */
    public static <T> T input(String label, Function<String, Optional<T>> action) {
        System.out.print(label + ": ");
        Scanner sc = new Scanner(System.in);

        try {
            String line = sc.nextLine();
            Optional<T> result = action.apply(line);

            if (result.isPresent()) {
                return result.get();
            } else {
                System.out.println("The input value is invalid. Please try again.\n");
                return input(label, action);
            }
        } catch (Exception e) {
            System.out.println("There was an error reading the input. Please try again.\n");
            return input(label, action);
        }
    }

    /**
     *
     * @param options
     */
    public static void menu(Option[] options) {
        System.out.println();

        for (Option option : options) {
            System.out.println(" " + option);
        }

        System.out.print(">>> ");
        Scanner sc = new Scanner(System.in);

        try {
            String command = sc.nextLine();
            Optional<Option> option = Arrays.stream(options).filter(o -> o.getCommand().equals(command)).findFirst();

            if (option.isPresent()) {
                option.get().getAction().run();
            } else {
                System.out.println("Invalid option. Please try again.\n");
                menu(options);
            }

        } catch (Exception e) {
            System.out.println("There was an error reading your option. Please try again\n");
            menu(options);
        }
    }

    /**
     *
     * @param list
     * @param show
     * @param <T>
     */
    public static <T> void list(Iterable<T> list, Function<T, String> show) {
        for (T e : list) {
            System.out.println(show.apply(e));
        }
    }
}
