package Model;

import Utils.Static;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface IDateCalculator {
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
    }
}