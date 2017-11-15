package business;

import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.TemporalUnit;

public interface IDate {

    public Period diffBetweenDatess(LocalDateTime d1, LocalDateTime d2);
    public LocalDateTime add(LocalDateTime d, TemporalUnit u);
    public LocalDateTime subtract(LocalDateTime d, TemporalUnit u);
}
