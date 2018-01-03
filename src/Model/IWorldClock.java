package Model;

import java.time.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public interface IWorldClock {
    static Duration difference(ZoneId id1, ZoneId id2) {
        return Duration.between(now(id1), now(id2));
    }

    static LocalDateTime now(ZoneId id) {
        return LocalDateTime.now(id);
    }

    static Map<LocalDateTime, List<String>> worldClock() {
        TreeMap<LocalDateTime, List<String>> zones = new TreeMap<>();

        for (String id : ZoneId.getAvailableZoneIds()) {
            LocalDateTime now = now(ZoneId.of(id)).withMinute(0).withSecond(0).withNano(0);

            if (!zones.containsKey(now)) {
                zones.put(now, new ArrayList<>());
            }

            zones.get(now).add(id);
        }

        return zones;
    }
}
