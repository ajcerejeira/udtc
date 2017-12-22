package Utils.UI;

public class Title implements Runnable {
    private String text;
    private int level;

    public Title() {
        this.text = "";
        this.level = 1;
    }

    public Title(String text, int level) {
        this.text = text;
        this.level = level;
    }

    @Override
    public void run() {
        switch(this.level) {
            case 1:
                System.out.println("\n\u001B[7m" + this.text.toUpperCase() + "\n\u001B[m");
                break;
            case 2:
                System.out.println("\u001B[1m" + this.text + "\n\u001B[m");
                break;
            case 3:
                System.out.println("\n" + this.text.toUpperCase());
                break;
            default:
                System.out.println(this.text);
        }
    }
}
