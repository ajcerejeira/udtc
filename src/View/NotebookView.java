package View;

import Model.Appointment;
import Model.INotebook;

import Utils.DateParser;
import Utils.Option;
import Utils.TUI;

import java.time.LocalDateTime;
import java.util.Optional;


public class NotebookView {
    public static void home(INotebook notebook) {
        TUI.title("Notebook", 1);
        TUI.list(notebook.getAppointments(), Appointment::toString);
        TUI.menu(new Option[] {
                new Option("A", "Add appointment", () -> NotebookView.addAppointment(notebook)),
                new Option("B", "Back", System.out::println),
           /*     new Option("E", "Edit appointment", this::editAppointment),
                new Option("D", "Delete appointment", this::deleteAppointment),
                new Option("S", "Search", this::searchAppointment),
                new Option("B", "Back", System.out::println)*/
        });
    }

    private static void addAppointment(INotebook notebook) {
        TUI.title("Notebook", 1);
        TUI.title("Add appointment", 2);
        TUI.list(notebook.getAppointments(), Appointment::toString);

        LocalDateTime dateTime = TUI.input("Date [yyyy-mm-dd hh:mm]", DateParser::parseDateTime);
        String text = TUI.input("Appointment", Optional::of);

        notebook.addAppointment(new Appointment(dateTime, text));

        home(notebook);
    }
}
