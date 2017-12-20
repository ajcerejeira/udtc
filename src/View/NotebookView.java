package View;

import java.time.LocalDateTime;
import java.util.Scanner;
import Model.Notebook;

public class NotebookView implements Runnable {
    private Notebook notebook;

    public NotebookView() {
        this.notebook = new Notebook();
    }

    private void addAppointment() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Date: [dd-mm-yyyy hh:mm]");

        String text = sc.nextLine();

        this.notebook.addAppointment(LocalDateTime.now(), text);
    }
    
    @Override
    public void run() {
        Menu menu = new Menu(new Option[] {
                new Option(1, "Add appointment", this::addAppointment),
                new Option(0, "Return", () -> System.out.println("Return")),
        });
        menu.run();
    }
}
