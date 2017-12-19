package View;

import java.util.function.Consumer;

public class Option<T> {
    private String name;
    private Consumer<T> action;

    public Option(String name, Consumer<T> action) {
        this.name = name;
        this.action = action;
    }

    public String getName() {
        return this.name;
    }

    public Consumer<T> getAction() {
        return this.action;
    }

    public String toString() {
        return this.name;
    }
}
