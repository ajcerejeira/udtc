package Model;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.TemporalAmount;

public interface IAge {
    void ageIn(TemporalAmount t);
    Period timeUntilBirthday(LocalDate d);
}
