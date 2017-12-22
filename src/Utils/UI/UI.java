package Utils.UI;

public class UI implements Runnable {
    private Runnable[] elements;

    public UI(Runnable[] elements) {
        this.elements = elements.clone();
    }

    @Override
    public void run() {
        System.out.println("\u001B[2J");

        for (Runnable element : this.elements) {
            element.run();
        }
    }
}
