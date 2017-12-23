package Model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Stream;

public interface IDateCalculator {
    Duration differenceBetweenDates(LocalDate d1, LocalDate d2);
    LocalDate dateIncrease(LocalDate d, ChronoUnit c);
    LocalDate dateDecrease(LocalDate d, ChronoUnit c);
    Stream<LocalDate> streamInterval(LocalDate d1, LocalDate d2);
    List<LocalDate> listInterval(LocalDate d1, LocalDate d2);
    List<LocalDate> workDaysYear(LocalDate d1, LocalDate d2);
    List<LocalDate> weekendsYear(LocalDate d1, LocalDate d2);
    boolean isWeekday(LocalDate d1);
    boolean isPrevious(LocalDate d1, LocalDate d2);
    boolean isAfter(LocalDate d1, LocalDate d2);
}