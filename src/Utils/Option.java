package Utils;

public class Option {
    private String command;
    private String name;
    private Runnable action;

    public Option(String command, String name, Runnable action) {
        this.command = command;
        this.name = name;
        this.action = action;
    }

    public String getCommand() {
        return this.command;
    }

    public String getName() {
        return this.name;
    }

    public Runnable getAction() {
        return this.action;
    }

    @Override
    public String toString() {
        return "[" + this.command + "] " + this.name;
    }
}
