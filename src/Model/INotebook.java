package Model;

import java.time.LocalDateTime;
import java.util.List;

/**
 * The Notebook is responsible for handling appointments.
 * This interface is abstract enough so that its implementations can vary in design.
 *
 * @author Afonso Silva
 */
public interface INotebook {
    /**
     * Adds an Appointment to the notebook.
     *
     * @param appointment Appointment to be added.
     */
    void addAppointment(Appointment appointment);

    /**
     * Deletes an Appointment from the notebook.
     *
     * @param appointment Appointment to be deleted.
     */
    void deleteAppointment(Appointment appointment);

    /**
     * Computes a List of Appointment of all appointments in a given Notebook.
     *
     * @return List containing all appointments.
     */
    List<Appointment> getAppointments();

    /**
     * Computes a List of Appointment of a given LocalDateTime in a given Notebook.
     *
     * @param date LocalDateTime that will be used in the search.
     * @return List containing all appointments that match the given LocalDateTime.
     */
    List<Appointment> getAppointments(LocalDateTime date);

    /**
     * Computes a List of Appointment between two given LocalDateTime in a given Notebook.
     *
     * @param from LocalDateTime that will be used as a starting point in the search.
     * @param to LocalDateTime that will be used as the ending point in the search.
     * @return List containing all appointments between the given LocalDateTime.
     */
    List<Appointment> getAppointments(LocalDateTime from, LocalDateTime to);

    /**
     * Parses the given string, adding the textual represented appointments to the notebook.
     *
     * @param s String received via the input.
     * @return Integer representing the number of appointments read.
     */
    int read(String s);

    /**
     * Transforms the Notebook into a String.
     *
     * @return Notebook converted to a String format easier to read.
     */
    String toString();
}
