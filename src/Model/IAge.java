package Model;

import java.time.LocalDate;
import java.time.Period;

public interface IAge {
    static Period timeUntilAge(LocalDate birthday, int age) {
        int current_age = Period.between(birthday, LocalDate.now()).getYears();

        System.out.println(current_age);
        System.out.println(age);

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
