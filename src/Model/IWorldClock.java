package Model;

import java.time.Duration;
import java.time.LocalTime;
import java.time.ZoneId;

public interface IWorldClock {
    static Duration difference(ZoneId id1, ZoneId id2) {
        System.out.println(id1);
        System.out.println(id2);

        return Duration.between(now(id1), now(id2));
    }

    static LocalTime now(ZoneId id) {
        return LocalTime.now(id);
    }

    static LocalTime gmt(String id) {
        return null;
    }

}
