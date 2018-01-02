package View;

import Model.Appointment;
import Model.INotebook;

import Utils.Option;
import Utils.Parsers;
import Utils.UI;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class NotebookView {
    public static void home(INotebook notebook) {
        UI.title("Notebook");
        UI.list(notebook.getAppointments(), Appointment::toString);
        UI.menu(new Option("Add appointment", () -> NotebookView.addAppointment(notebook)),
                new Option("Delete appointment", () -> NotebookView.deleteAppointment(notebook)),
                new Option("Save notebook", () -> NotebookView.saveNotebook(notebook)),
                new Option("Read notebook", () -> NotebookView.readNotebook(notebook)),
                new Option("Back", System.out::println));
    }

    private static void addAppointment(INotebook notebook) {
        UI.title("Notebook");
        UI.subtitle("Add appointment");
        UI.list(notebook.getAppointments(), Appointment::toString);

        LocalDateTime dateTime = UI.input("Date [yyyy-mm-dd hh:mm]", Parsers::parseDateTime);
        String text = UI.input("Appointment", Optional::of);

        notebook.addAppointment(new Appointment(dateTime, text));

        home(notebook);
    }

    private static void deleteAppointment(INotebook notebook) {
        List<Appointment> appointments = notebook.getAppointments();
        Option[] options = notebook.getAppointments()
                .stream()
                .map(appointment -> new Option(appointment.toString(), () -> notebook.deleteAppointment(appointment)))
                .toArray(Option[]::new);

        UI.title("Notebook");
        UI.subtitle("Delete appointment");
        UI.menu(options);

        home(notebook);
    }

    private static void readNotebook(INotebook notebook) {
        UI.title("Notebook");
        UI.subtitle("Read notebook");
        String path = UI.input("Input file", Optional::of);

        try {
            String content = new String(Files.readAllBytes(Paths.get(path)));
            notebook.read(content);
        } catch (IOException e) {
            System.out.println("There was an error reading to the specified file");
        }

        home(notebook);
    }

    private static void saveNotebook(INotebook notebook) {
        UI.title("Notebook");
        UI.subtitle("Save notebook");
        String path = UI.input("Output file", Optional::of);

        try {
            Files.write(Paths.get(path), notebook.toString().getBytes());
        } catch (IOException e) {
            System.out.println("There was an error writing to the specified file");
        }

        home(notebook);
    }
}
