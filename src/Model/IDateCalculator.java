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

public interface IDateCalculator {
    static LocalDate add(LocalDate date, TemporalAmount ammount) {
        return date.plus(ammount);
    }

    static LocalDate add(LocalDate date, int years, int months, int weeks, int days) {
        return date.plus(Period.of(years, months, days).plus(Period.ofWeeks(weeks)));
    }

    static LocalDate sub(LocalDate date, TemporalAmount ammount) {
        return date.minus(ammount);
    }

    static LocalDate sub(LocalDate date, int years, int months, int weeks, int days) {
        return date.minus(Period.of(years, months, days).plus(Period.ofWeeks(weeks)));
    }

    static Period difference(LocalDate d1, LocalDate d2) {
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

    static List<LocalDate> interval(LocalDate from, LocalDate to) {
        return Stream.iterate(from, d -> d.plusDays(1))
                .limit(ChronoUnit.DAYS.between(from, to) + 1)
                .collect(Collectors.toList());
    }

    static List<LocalDate> weekdays(LocalDate from, LocalDate to) {
        Predicate<LocalDate> isWeekday = d -> d.getDayOfWeek() != DayOfWeek.SATURDAY && d.getDayOfWeek() != DayOfWeek.SUNDAY;

        return interval(from, to).stream().filter(isWeekday).collect(Collectors.toList());
    }

    static List<LocalDate> weekends(LocalDate from, LocalDate to) {
        Predicate<LocalDate> isWeekend = d -> d.getDayOfWeek() == DayOfWeek.SATURDAY || d.getDayOfWeek() == DayOfWeek.SUNDAY;

        return interval(from, to).stream().filter(isWeekend).collect(Collectors.toList());
    }
}