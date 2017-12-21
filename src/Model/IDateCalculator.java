package Model;

import java.time.Duration;
import java.time.LocalDateTime;

public interface IDateCalculator {
    Duration differenceBetweenDates(LocalDateTime d1, LocalDateTime d2);
}
