package Utils.UI;

public class Option implements Runnable {
    private String command;
    private String name;
    private Runnable action;

    public Option() {
        this.command = "";
        this.name = "";
        this.action = null;
    }

    public Option(String command, String name, Runnable action) {
        this.command = command;
        this.name = name;
        this.action = action;
    }

    public Option(String name, Runnable action) {
        this.command = String.valueOf(name.charAt(0));
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
    public void run() {
        this.action.run();
    }

    @Override
    public String toString() {
        return "[" + this.command + "] " + this.name;
    }
}