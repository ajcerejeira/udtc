package Utils.UI;

import java.util.Optional;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;

public class Input<T> implements Runnable {
    private String text;
    private Consumer<T> action;
    private Function<String, Optional<T>> validator;

    public Input(String text, Consumer<T> action) {
        this.text = text;
        this.action = action;
     //   this.validator = Optional::of;
    }

    public Input(String text, Consumer<T> action, Function<String, Optional<T>> validator) {
        this.text = text;
        this.action = action;
        this.validator = validator;
    }

    @Override
    public void run() {
        System.out.print(this.text + " ");
        Scanner sc = new Scanner(System.in);

        try {
            String text = sc.nextLine();
            Optional<T> o = this.validator.apply(text);

            if (o.isPresent()) {
                this.action.accept(o.get());
            } else {
                System.out.println("The");
            }
        } catch (Exception e) {
            System.out.println("There was an error reading the input");
            this.run();
        }
    }
}
