package Model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class DateCalculator implements IDateCalculator {

    @Override
    public Duration differenceBetweenDates(LocalDateTime d1, LocalDateTime d2) {
        return Duration.between(d1,d2);
    }

    @Override
    public LocalDateTime dateIncrease(LocalDateTime d, ChronoUnit c) {
        return d.plus(c.getDuration());
    }

    @Override
    public LocalDateTime dateDecrease(LocalDateTime d, ChronoUnit c) {
        return d.minus(c.getDuration());
    }

    @Override
    public Stream<LocalDateTime> streamInterval(LocalDateTime d1, LocalDateTime d2) {
        return Stream.iterate(d1, d -> d.plusDays(1)).limit(ChronoUnit.DAYS.between(d1, d2) + 1);
    }

    @Override
    public List<LocalDateTime> listInterval(LocalDateTime d1, LocalDateTime d2) {
        List<LocalDateTime> interval = new ArrayList<>();
        LocalDateTime d = d1;

        while(isPrevious(d,d2)){
            d.plusDays(1);
            interval.add(d);
        }

        return interval;
    }

    @Override
    public boolean isPrevious(LocalDateTime d1, LocalDateTime d2) {
        return d1.isBefore(d2);
    }

    @Override
    public boolean isAfter(LocalDateTime d1, LocalDateTime d2) {
        return d1.isAfter(d2);
    }
}
