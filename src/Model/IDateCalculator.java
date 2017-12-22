package Model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Stream;

public interface IDateCalculator {
    Duration differenceBetweenDates(LocalDateTime d1, LocalDateTime d2);
    LocalDateTime dateIncrease(LocalDateTime d, ChronoUnit c);
    LocalDateTime dateDecrease(LocalDateTime d, ChronoUnit c);
    Stream<LocalDateTime> streamInterval(LocalDateTime d1, LocalDateTime d2);
    List<LocalDateTime> listInterval(LocalDateTime d1, LocalDateTime d2);
    boolean isPrevious(LocalDateTime d1, LocalDateTime d2);
    boolean isAfter(LocalDateTime d1, LocalDateTime d2);
}