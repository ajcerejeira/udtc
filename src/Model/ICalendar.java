package Model;

/**
 * A calendar has only one possible action: buildCalendar, which allows to show the calendar given by the month and
 * year the user inputs.
 *
 * @author Octavio Maia
 */
public interface ICalendar {
    /**
     * Calculates the first day of the month. (e.g: Monday, Tuesday, ... , Sunday)
     *
     * @return number of the day (1-7).
     */
    int day();

    /**
     * A calendar has only one possible action: buildCalendar, which allows to show the calendar given by the month and
     * year the user inputs.
     *
     * @author Octavio Maia
     */
    boolean isLeapYear();

    /**
     * Main function which builds the calendar and appends it to a string.
     *
     * @return String containing the calendar.
     */
    String buildCalendar();

    /**
     * Sets the year to the value the user has given in the input.
     *
     * @param year year read by the input handler.
     */
    void setYear(int year);

    /**
     * Sets the month to the value the user has given in the input.
     *
     * @param month month read by the input handler.
     */
    void setMonth(int month);
}
