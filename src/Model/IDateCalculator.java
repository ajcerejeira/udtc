package Model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAmount;

public interface IDateCalculator {
    static LocalDate addToDate(LocalDate date, TemporalAmount ammount) {
        return date.plus(ammount);
    }

    static LocalDate addToDate(LocalDate date, int years, int months, int weeks, int days) {
        return date.plus(Period.of(years, months, days).plus(Period.ofWeeks(weeks)));
    }

    static LocalDate subFromDate(LocalDate date, TemporalAmount ammount) {
        return date.minus(ammount);
    }

    static LocalDate subFromDate(LocalDate date, int years, int months, int weeks, int days) {
        return date.minus(Period.of(years, months, days).plus(Period.ofWeeks(weeks)));
    }

    static Period differenceBetweenDates(LocalDate d1, LocalDate d2) {
        if (d1.isBefore(d2)) {
            return Period.between(d1, d2);
        } else {
            return Period.between(d2, d1);
        }
    }

    static DayOfWeek dayOfWeek(LocalDate date) {
        return date.getDayOfWeek();
    }

    static int week(LocalDate date) {
        return date.get(ChronoField.ALIGNED_WEEK_OF_YEAR);
    }

    /*
    static Period differenceBetweenDates(LocalDate d1, LocalDate d2) {
        return Period.between(d1,d2);
    }

    static boolean isWeekend(LocalDate d1) {
        DayOfWeek d = d1.getDayOfWeek();
        return d == DayOfWeek.SATURDAY || d == DayOfWeek.SUNDAY;
    }

    static Stream<LocalDate> streamInterval(LocalDate d1, LocalDate d2) {
        return Stream.iterate(d1, d -> d.plusDays(1)).limit(ChronoUnit.DAYS.between(d1, d2) + 1);
    }

    static List<LocalDate> listInterval(LocalDate d1, LocalDate d2) {
        return streamInterval(d1,d2).collect(Collectors.toList());
    }

    static List<LocalDate> workDays(LocalDate d1, LocalDate d2) {
        return streamInterval(d1,d2).filter(Static.not(IDateCalculator::isWeekend)).collect(Collectors.toList());
    }

    static List<LocalDate> weekends(LocalDate d1, LocalDate d2) {
        return streamInterval(d1,d2).filter(IDateCalculator::isWeekend).collect(Collectors.toList());
    }*/
}