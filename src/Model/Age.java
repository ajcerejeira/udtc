package Model;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.TemporalAmount;

public class Age implements IAge {
    @Override
    public void ageIn(TemporalAmount t) {

    }

    @Override
    public Period timeUntilBirthday(LocalDate d) {
        return Period.between(LocalDate.now(),d);
    }
}
