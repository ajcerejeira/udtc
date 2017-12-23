package Model;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import Utils.Static;

public class DateCalculator implements IDateCalculator {

    @Override
    public Duration differenceBetweenDates(LocalDate d1, LocalDate d2) {
        return Duration.between(d1,d2);
    }

    @Override
    public LocalDate dateIncrease(LocalDate d, ChronoUnit c) {
        return d.plus(c.getDuration());
    }

    @Override
    public LocalDate dateDecrease(LocalDate d, ChronoUnit c) {
        return d.minus(c.getDuration());
    }

    @Override
    public Stream<LocalDate> streamInterval(LocalDate d1, LocalDate d2) {
        return Stream.iterate(d1, d -> d.plusDays(1)).limit(ChronoUnit.DAYS.between(d1, d2) + 1);
    }

    @Override
    public List<LocalDate> listInterval(LocalDate d1, LocalDate d2) {
        return this.streamInterval(d1,d2).collect(Collectors.toList());
    }

    @Override
    public List<LocalDate> workDaysYear(LocalDate d1, LocalDate d2) {
        return this.streamInterval(d1,d2).filter(this::isWeekday).collect(Collectors.toList());
    }

    @Override
    public List<LocalDate> weekendsYear(LocalDate d1, LocalDate d2) {
        return this.streamInterval(d1,d2).filter(Static.not(this::isWeekday)).collect(Collectors.toList());
    }

    @Override
    public boolean isWeekday(LocalDate d1) {
        DayOfWeek d = d1.getDayOfWeek();
        return d == DayOfWeek.SATURDAY || d == DayOfWeek.SUNDAY;
    }

    @Override
    public boolean isPrevious(LocalDate d1, LocalDate d2) {
        return d1.isBefore(d2);
    }

    @Override
    public boolean isAfter(LocalDate d1, LocalDate d2) {
        return d1.isAfter(d2);
    }
}
