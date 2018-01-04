package Model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The Date Calculator is responsible for all the operations based on LocalDate.
 *
 * @author Octavio Maia
 */
public interface IDateCalculator {
    /**
     * All in one implementation of a add function that can add any TemporalAmount to an existing date.
     *
     * @param date LocalDate that will be added from.
     * @param amount Amount to add to the existing date.
     * @return LocalDate with the added amount.
     */
    static LocalDate add(LocalDate date, TemporalAmount amount) {
        return date.plus(amount);
    }

    /**
     * Less refined implementation of the above function, with integers representing the years, months, weeks and days to add.
     *
     * @param date LocalDate that will be added to.
     * @param years Integer representing the years to add.
     * @param months Integer representing the months to add.
     * @param weeks Integer representing the weeks to add.
     * @param days Integer representing the days to add.
     * @return LocalDate with the added amount.
     */
    static LocalDate add(LocalDate date, int years, int months, int weeks, int days) {
        return date.plus(Period.of(years, months, days).plus(Period.ofWeeks(weeks)));
    }

    /**
     * All in one implementation of a subtract function that can subtract any TemporalAmount to an existing date.
     *
     * @param date LocalDate that will be subtracted to.
     * @param amount Amount to subtract to the existing date.
     * @return New LocalDate with the subtracted amount.
     */
    static LocalDate sub(LocalDate date, TemporalAmount amount) {
        return date.minus(amount);
    }

    /**
     * Less refined implementation of the above function, with integers representing the years, months, weeks and days to subtract.
     *
     * @param date LocalDate that will be subtracted from.
     * @param years Integer representing the years to subtract.
     * @param months Integer representing the months to subtract.
     * @param weeks Integer representing the weeks to subtract.
     * @param days Integer representing the days to subtract.
     * @return LocalDate with the subtracted amount.
     */
    static LocalDate sub(LocalDate date, int years, int months, int weeks, int days) {
        return date.minus(Period.of(years, months, days).plus(Period.ofWeeks(weeks)));
    }

    /**
     * Difference between two LocalDate.
     *
     * @param d1 First LocalDate
     * @param d2 Second LocalDate
     * @return Period representing the difference between the two dates.
     */
    static Period difference(LocalDate d1, LocalDate d2) {
        if (d1.isBefore(d2)) {
            return Period.between(d1, d2);
        } else {
            return Period.between(d2, d1);
        }
    }

    /**
     * Gets the DayOfWeek of a given LocalDate.
     *
     * @param date LocalDate received by the function.
     * @return DayOfWeek representing the current day in the given LocalDate.
     */
    static DayOfWeek dayOfWeek(LocalDate date) {
        return date.getDayOfWeek();
    }

    /**
     * Calculates the week number of a given LocalDate.
     *
     * @param date LocalDate received by the function.
     * @return Integer representing the week number in the given LocalDate.
     */
    static int week(LocalDate date) {
        return date.get(ChronoField.ALIGNED_WEEK_OF_YEAR);
    }

    /**
     * Computes a List of LocalDate between two LocalDate.
     *
     * @param from First LocalDate.
     * @param to Second LocalDate.
     * @return List containing all LocalDate between the two argument.
     */
    static List<LocalDate> interval(LocalDate from, LocalDate to) {
        return Stream.iterate(from, d -> d.plusDays(1))
                .limit(ChronoUnit.DAYS.between(from, to) + 1)
                .collect(Collectors.toList());
    }

    /**
     * Computes a List of LocalDate that are weekdays between two LocalDate, given via a Stream by the interval function.
     *
     * @param from First LocalDate.
     * @param to Second LocalDate.
     * @return List containing all weekdays between the two argument.
     */
    static List<LocalDate> weekdays(LocalDate from, LocalDate to) {
        Predicate<LocalDate> isWeekday = d -> d.getDayOfWeek() != DayOfWeek.SATURDAY && d.getDayOfWeek() != DayOfWeek.SUNDAY;

        return interval(from, to).stream().filter(isWeekday).collect(Collectors.toList());
    }

    /**
     * Computes a List of LocalDate that are weekends between two LocalDate, given via a Stream by the interval function.
     *
     * @param from First LocalDate.
     * @param to Second LocalDate.
     * @return List containing all weekends between the two argument.
     */
    static List<LocalDate> weekends(LocalDate from, LocalDate to) {
        Predicate<LocalDate> isWeekend = d -> d.getDayOfWeek() == DayOfWeek.SATURDAY || d.getDayOfWeek() == DayOfWeek.SUNDAY;

        return interval(from, to).stream().filter(isWeekend).collect(Collectors.toList());
    }
}