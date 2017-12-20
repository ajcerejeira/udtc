package View;

import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;
import static java.lang.System.out;

public class Menu implements Runnable {
    private Option[] options;

    public Menu(Option[] options) {
        this.options = options.clone();
    }

    @Override
    public void run() {
        System.out.println(this);

        // Read user option
        out.print("Choice: ");
        final int number = new Scanner(System.in).nextInt();

        Optional<Option> option = Arrays.
                stream(this.options).
                filter(op -> op.getNumber() == number).
                findFirst();

        if (option.isPresent()) {
            option.get().run();
        } else {
            out.println("Invalid choice.");
            this.run();
        }
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (Option option : this.options) {
            builder.append(option.toString());
            builder.append('\n');
        }
        return builder.toString();
    }
}
