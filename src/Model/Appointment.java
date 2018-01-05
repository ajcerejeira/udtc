package Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * The Appointment class corresponds to an entry in the {@link INotebook}. It is a pair containing the date of the
 * appointment and its description.
 *
 * @author Afonso Silva
 */
public class Appointment implements Comparable {
    private LocalDateTime date;
    private String text;

    /**
     * Empty constructor, creates a new Appointment with no text and assumes that its date is now.
     */
    public Appointment() {
        this.date = LocalDateTime.now();
        this.text = "";
    }

    /**
     * Parametrized constructor, creates a new Appointment with the given date and text.
     *
     * @param date date of the appointment
     * @param text text describing the appointment
     */
    public Appointment(LocalDateTime date, String text) {
        this.date = date;
        this.text = text;
    }

    /**
     * Returns the date of the appointment.
     *
     * @return date of the appointment
     */
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * Returns the text of the appointment.
     *
     * @return text of the appointment
     */
    public String getText() {
        return text;
    }

    /**
     * Changes the appointment date.
     *
     * @param date new date to change in the appointment
     */
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    /**
     * Changes the appointment text.
     *
     * @param text new text to change in the appointment
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Compares two appointments by them dates.
     *
     * @param o object to compare
     * @return -1 if the date is before, 1 if the date is after, 0 if the date is the same
     */
    @Override
    public int compareTo(Object o) {
        Appointment appointment = (Appointment) o;
        return this.date.compareTo(appointment.getDate());
    }

    /**
     * Checks if two appointments are equal.
     *
     * @param o object to compare
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return Objects.equals(date, that.date) &&
                Objects.equals(text, that.text);
    }

    /**
     * Converts the appointment to a string with the format: <code>[date] text</code>
     *
     * @return string representing the appointment
     */
    @Override
    public String toString() {
        return "[" + this.date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm")) + "] " + this.text;
    }
}
