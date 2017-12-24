package Model;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Stream;

public interface IDateCalculator {
    Period differenceBetweenDates(LocalDate d1, LocalDate d2);
    LocalDate dateIncrease(LocalDate d, ChronoUnit c);
    LocalDate dateDecrease(LocalDate d, ChronoUnit c);
    Stream<LocalDate> streamInterval(LocalDate d1, LocalDate d2);
    List<LocalDate> listInterval(LocalDate d1, LocalDate d2);
    List<LocalDate> workDays(LocalDate d1, LocalDate d2);
    List<LocalDate> weekends(LocalDate d1, LocalDate d2);
    boolean isWeekend(LocalDate d1);
    boolean isPrevious(LocalDate d1, LocalDate d2);
    boolean isAfter(LocalDate d1, LocalDate d2);
}