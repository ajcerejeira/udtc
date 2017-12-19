package View;

import java.util.Scanner;

public class Menu {
    private Option[] options;

    public Menu(Option[] options) {
        this.options = options.clone();
    }

    public void run(Object o) {
        int option = -1;

        do {
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
            } else {
                this.options[option].getAction().accept(o);
            }
        } while(option != 0);
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
