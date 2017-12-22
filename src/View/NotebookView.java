package View;

import Model.Appointment;
import Model.Notebook;

import Utils.UI.*;

public class NotebookView implements Runnable {
    private Notebook notebook;

    public NotebookView() {
        this.notebook = new Notebook();
    }

    private void addAppointment() {
        Appointment appointment = new Appointment();

        new UI(new Runnable[] {
                new Title("Notebook", 1),
                new Title("Add appointment", 2),
                new Table(this.notebook.getAppointments().toArray()),
                new Input("Date [dd-mm-yyy]", text -> System.out.println()),
                new Input("Appointment", appointment::setText),
        }).run();

        this.notebook.addAppointment(appointment);
        this.run();
    }

    private void editAppointment() {
        new UI(new Runnable[] {
                new Title("Notebook", 1),
                new Title("Edit appointment",2),
                new Table(this.notebook.getAppointments().toArray()),
        }).run();
    }

    private void deleteAppointment() {
        new UI(new Runnable[] {
                new Title("Notebook", 1),
                new Title("Delete appointment",2),
                new Table(this.notebook.getAppointments().toArray()),
        }).run();
    }

    @Override
    public void run() {
        new UI(new Runnable[] {
                new Title("Notebook", 1),
                new Table(this.notebook.getAppointments().toArray()),
                new Menu(new Option[] {
                        new Option("A", "Add appointment", this::addAppointment),
                        new Option("E", "Edit appointment", this::editAppointment),
                        new Option("D", "Delete appointment", this::deleteAppointment),
                        new Option("S", "Search", this::addAppointment),
                        new Option("B", "Back", this::addAppointment)
                }),
        }).run();
    }
}
