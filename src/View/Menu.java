package View;

import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;

public class Menu implements Runnable {
    private Option[] options;

    public Menu(Option[] options) {
        this.options = options.clone();
    }

    @Override
    public void run() {
        System.out.println(this);

        // Read user option
        final String command = new Scanner(System.in).nextLine();

        Optional<Option> option = Arrays
                .stream(this.options)
                .filter(o -> o.getCommand().equals(command))
                .findFirst();

        if (option.isPresent()) {
            option.get().run();
        } else {
            System.out.println("A opção escolhida não é válida. Tente novamente");
            this.run();
        }
    }

    public String toString() {
        StringBuilder builder = new StringBuilder().append('\n');

        for (Option option : this.options) {
            builder.append(option.toString());
            builder.append('\n');
        }

        return builder.toString();
    }
}
