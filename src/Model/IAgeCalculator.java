package Model;

import java.time.LocalDate;
import java.time.Period;

/**
 * The Age Calculator has only three functions.
 * These are: your current age, the time left until you're X years old, and the time left until your birthday.
 *
 * @author Octavio Maia
 */
public interface IAgeCalculator {
    /**
     * Calculates your current age.
     *
     * @param birthday LocalDate representing your birthday.
     * @return Integer representing your age in years.
     */
    static int age(LocalDate birthday) {
        return Period.between(birthday, LocalDate.now()).getYears();
    }

    /**
     * Calculates the time left until you're a certain age.
     *
     * @param birthday LocalDate representing your birthday.
     * @param age Integer representing the age you want to be.
     * @return Period representing the time left until you're X years old.
     */
    static Period timeUntilAge(LocalDate birthday, int age) {
        int current_age = Period.between(birthday, LocalDate.now()).getYears();

        return timeUntilBirthday(birthday).plusYears(age - current_age - 1);
    }

    /**
     * Calculates the time left until your next birthday.
     *
     * @param date LocalDate representing your birthday.
     * @return Period representing the time left until your next birthday.
     */
    static Period timeUntilBirthday(LocalDate date) {
        Period p;
        LocalDate now = LocalDate.now().withYear(date.getYear());

        if (date.isBefore(now)) {
            p = Period.between(now, date.plusYears(1));
        } else {
            p = Period.between(now, date);
        }

        return p;
    }
}
