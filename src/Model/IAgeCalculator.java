package Model;

import java.time.LocalDate;
import java.time.Period;

public interface IAgeCalculator {
    static int age(LocalDate birthday) {
        return Period.between(birthday, LocalDate.now()).getYears();
    }

    static Period timeUntilAge(LocalDate birthday, int age) {
        int current_age = Period.between(birthday, LocalDate.now()).getYears();

        return timeUntilBirthday(birthday).plusYears(age - current_age - 1);
    }

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
