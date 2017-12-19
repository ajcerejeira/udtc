package View;

import java.util.Optional;
import java.util.Scanner;

public class Menu {
    private String[] options;

    public Menu(String[] options) {
        this.options = options.clone();
    }

    public void print() {
        for (int i = 0; i < options.length; i++) {
            System.out.println(" [" + i + "] " + options[i]);
        }
    }

    public Optional<Integer> getOption() {
        Scanner sc = new Scanner(System.in);
        Optional<Integer> option = Optional.empty();

        if (sc.hasNextInt()) {
            Integer i = sc.nextInt();

            if (i >= 0 && i < options.length) {
                option = Optional.of(i);
            }
        }

        return option;
    }

}
