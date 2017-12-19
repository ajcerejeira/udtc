package View;

import java.util.function.Consumer;

public class Option {
    private String name;
    private Consumer<Object> action;

    public Option(String name, Consumer<Object> action) {
        this.name = name;
        this.action = action;
    }

    public String getName() {
        return this.name;
    }

    public Consumer<Object> getAction() {
        return this.action;
    }

    public String toString() {
        return this.name;
    }
}
