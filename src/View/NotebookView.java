package View;

import Model.Appointment;
import Model.INotebook;

import Utils.DateParser;
import Utils.Option;
import Utils.TUI;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Stream;


public class NotebookView {
    public static void home(INotebook notebook) {
        TUI.title("Notebook", 1);
        TUI.list(notebook.getAppointments(), Appointment::toString);
        TUI.menu(new Option[] {
                new Option("A", "Add appointment", () -> NotebookView.addAppointment(notebook)),
                new Option("S", "Save notebook", () -> NotebookView.saveNotebook(notebook)),
                new Option("R", "Read notebook", () -> NotebookView.readNotebook(notebook)),
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

    private static void readNotebook(INotebook notebook) {
        TUI.title("Notebook", 1);
        TUI.title("Read notebook", 2);
        String path = TUI.input("Input file", Optional::of);

        try {
            String content = new String(Files.readAllBytes(Paths.get(path)));
            notebook.read(content);
        } catch (IOException e) {
            System.out.println("There was an error reading to the specified file");
        }

        home(notebook);
    }

    private static void saveNotebook(INotebook notebook) {
        TUI.title("Notebook", 1);
        TUI.title("Save notebook", 2);
        String path = TUI.input("Output file", Optional::of);

        try {
            Files.write(Paths.get(path), notebook.toString().getBytes());
        } catch (IOException e) {
            System.out.println("There was an error writing to the specified file");
        }

        home(notebook);
    }
}
