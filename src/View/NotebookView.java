package View;

import Model.Appointment;
import Model.Notebook;

import java.time.LocalDateTime;
import java.util.Scanner;

public class NotebookView implements Runnable {
    private Notebook notebook;

    public NotebookView() {
        this.notebook = new Notebook();
    }

    private void addAppointment() {
        System.out.println("Date:");
        // TODO: read date from input
        System.out.println("Appointment:");
        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();

        this.notebook.addAppointment(new Appointment(LocalDateTime.now(), text));

        this.run();
    }

    private void removeAppointment() {

    }

    @Override
    public void run() {
        System.out.println(this);

        Menu menu = new Menu(new Option[] {
                new Option(1, "Add appointment", this::addAppointment),
                new Option(2, "Remove appointment", this::removeAppointment),
                new Option(0, "Return", () -> System.out.println("Return")),
        });
        menu.run();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < this.notebook.getAppointments().size(); i++) {
            builder.append(i); builder.append(". ");
            builder.append(this.notebook.getAppointments().get(i));
            builder.append("\n");
        }

        return builder.toString();
    }
}
