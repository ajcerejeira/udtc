package Model;

import java.time.Duration;
import java.time.LocalDateTime;

public class DateCalculator implements IDateCalculator {

    @Override
    public Duration differenceBetweenDates(LocalDateTime d1, LocalDateTime d2) {
        return Duration.between(d1,d2);
    }
}
