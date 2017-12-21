package View;

import Model.Appointment;
import Model.Notebook;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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

    private void deleteAppointment(Appointment appointment) {
        System.out.println("Are you sure you want to remove the appointment?");

        Menu menu = new Menu(new Option[] {
                new Option("Yes", () -> this.notebook.deleteAppointment(appointment)),
                new Option("No", this::run),
        });
        menu.run();

        this.run();
    }

    private void showAppointment(Appointment appointment) {
        System.out.println(appointment);

        Menu menu = new Menu(new Option[] {
                new Option("Edit appointment", () -> System.out.println("EDIT")),
                new Option("Delete appointment", () -> deleteAppointment(appointment)),
        });
        menu.run();

        this.run();
    }

    @Override
    public void run() {
        List<Option> options = new ArrayList<>();

        // Add all the apointments as menu items
        for (int i = 0; i < this.notebook.getAppointments().size(); i++) {
            Appointment appointment = this.notebook.getAppointments().get(i);
            options.add(new Option(String.valueOf(i), appointment.toString(), () -> showAppointment(appointment)));
        }

        // Add the default commands
        options.add(new Option());
        options.add(new Option("A", "Add appointment", this::addAppointment));

        Menu menu = new Menu(options.toArray(new Option[options.size()]));
        menu.run();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < this.notebook.getAppointments().size(); i++) {
            builder.append("["); builder.append(i); builder.append("] ");
            builder.append(this.notebook.getAppointments().get(i));
            builder.append("\n");
        }

        return builder.toString();
    }
}
