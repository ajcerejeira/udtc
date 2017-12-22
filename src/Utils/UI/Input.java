package Utils.UI;

import java.util.Scanner;
import java.util.function.Consumer;

public class Input implements Runnable {
    private String text;
    private Consumer<String> action;

    public Input(String text, Consumer<String> action) {
        this.text = text;
        this.action = action;
    }

    @Override
    public void run() {
        System.out.print(this.text + ": ");
        Scanner sc = new Scanner(System.in);
        this.action.accept(sc.nextLine());
    }
}
