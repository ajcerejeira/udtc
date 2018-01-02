package Utils;

public class Option {
    private String name;
    private Runnable action;

    public Option(String name, Runnable action) {
        this.name = name;
        this.action = action;
    }

    public String getName() {
        return name;
    }

    public Runnable getAction() {
        return action;
    }
}
