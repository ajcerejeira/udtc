package View;

public class Option implements Runnable {
    private String name;
    private Runnable action;

    public Option(String name, Runnable action) {
        this.name = name;
        this.action = action;
    }

    public String getName() {
        return this.name;
    }

    public Runnable getAction() {
        return this.action;
    }

    @Override
    public void run() {
        this.action.run();
    }

    public String toString() {
        return this.name;
    }
}
