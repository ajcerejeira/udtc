package View;

import java.util.Scanner;

public class Menu implements Runnable {
    private Option[] options;

    public Menu(Option[] options) {
        this.options = options.clone();
    }

    @Override
    public void run() {
        int option = -1;

        System.out.println(this);

        // Read user option
        Scanner sc = new Scanner(System.in);

        if (sc.hasNextInt()) {
            option = sc.nextInt();

            if (option < 0 || option > this.options.length) {
                option = -1;
            }
        }

        if (option == -1) {
            System.out.println("A opção escolhida é inválida. Por favor tente outra vez");
            this.run();
        } else {
            this.options[option].run();
        }
    }

    public String toString() {
        StringBuilder str = new StringBuilder();

        str.append('\n');

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
