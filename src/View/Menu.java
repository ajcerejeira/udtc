package View;

import Exceptions.InvalidInputException;

import java.util.Scanner;

public class Menu {
    private Option[] options;

    public Menu(Option[] options) {
        this.options = options.clone();
    }

    public void run(Object o) throws InvalidInputException {
        System.out.println(this);

        // Read user option
        Scanner sc = new Scanner(System.in);
        int i = -1;

        if (sc.hasNextInt()) {
            i = sc.nextInt();

            if (i < 0 || i > this.options.length) {
                throw new InvalidInputException();
            }
        } else {
            throw new InvalidInputException();
        }

        this.options[i].getAction().accept(o);
    }

    public String toString() {
        StringBuilder str = new StringBuilder();

        for (int i = 1; i < this.options.length; i++) {
            str.append(" [");
            str.append(i);
            str.append("] ");
            str.append(this.options[i]);
            str.append('\n');
        }

        str.append('\n');
        str.append(" [0] ");
        str.append(this.options[0]);
        str.append('\n');

        return str.toString();
    }
}
