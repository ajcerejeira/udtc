package View;

public class Option implements Runnable {
    private int number;
    private String name;
    private Runnable action;

    public Option(int number, String name, Runnable action) {
        this.number = number;
        this.name = name;
        this.action = action;
    }

    public int getNumber() {
        return this.number;
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
        return "[" + this.number + "] " + this.name;
    }
}
