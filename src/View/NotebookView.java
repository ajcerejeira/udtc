package View;

import Model.Appointment;
import Model.Notebook;

import Utils.DateParser;
import Utils.UI.*;
import java.util.Optional;

public class NotebookView implements Runnable {
    private Notebook notebook;

    public NotebookView(Notebook notebook) {
        this.notebook = notebook;
    }

    private void addAppointment() {
        Appointment appointment = new Appointment();

        new UI(new Runnable[] {
                new Title("Notebook", 1),
                new Title("Add appointment", 2),
                new Table<>(this.notebook.getAppointments(), Appointment::toString),
                new Input<>("Date [dd-mm-yyyy]", appointment::setDate, DateParser::parseDateTime),
                new Input<>("Appointment", appointment::setText, Optional::of),
        }).run();

        this.notebook.addAppointment(appointment);
        this.run();
    }

    private void editAppointment() {
        new UI(new Runnable[] {
                new Title("Notebook", 1),
                new Title("Edit appointment",2),
                new Table<>(this.notebook.getAppointments(), Appointment::toString),
        }).run();

        this.run();
    }

    private void deleteAppointment() {
        new UI(new Runnable[] {
                new Title("Notebook", 1),
                new Title("Delete appointment",2),
                new Table<>(this.notebook.getAppointments(), Appointment::toString),
        }).run();

        this.run();
    }

    private void searchAppointment() {
        new UI(new Runnable[] {
                new Title("Notebook", 1),
                new Title("Search appointment",2),
                new Table<>(this.notebook.getAppointments(), Appointment::toString),
        }).run();

        this.run();
    }

    @Override
    public void run() {
        new UI(new Runnable[] {
                new Title("Notebook", 1),
                new Table<>(this.notebook.getAppointments(), Appointment::toString),
                new Menu(new Option[] {
                        new Option("A", "Add appointment", this::addAppointment),
                        new Option("E", "Edit appointment", this::editAppointment),
                        new Option("D", "Delete appointment", this::deleteAppointment),
                        new Option("S", "Search", this::searchAppointment),
                        new Option("B", "Back", () -> System.out.print(""))
                }),
        }).run();
    }
}
