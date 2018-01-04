package Model;

import java.time.*;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/*
 * The WorldClock is responsible for handling dates and times in different zones of the world.
 *
 * @author Afonso Silva
 */
public interface IWorldClock {
    /**
     * Calculates the difference between two ZoneId.
     *
     * @param id1 ZoneId of country A.
     * @param id2 ZoneId of country B.
     * @return Duration of the difference of both countries current time.
     */
    static Duration difference(ZoneId id1, ZoneId id2) {
        return Duration.between(now(id1), now(id2));
    }

    /**
     * Computes the list of zones that are currently at a given time
     *
     * @param t current time
     * @return list of zones that are currently at a given time
     */
    static List<String> zonesAtTime(LocalDateTime t) {
        final LocalDateTime time = t.withNano(0).withSecond(0);
        List<String> zones = ZoneId.getAvailableZoneIds()
                .stream()
                .filter(id -> now(ZoneId.of(id)).withNano(0).withSecond(0).equals(time))
                .collect(Collectors.toList());
        return zones;
    }

    /**
     * Calculates the current LocalDateTime on a given country via a given ZoneId.
     *
     * @param id ZoneId of a given country.
     * @return LocalDateTime in the given country.
     */
    static LocalDateTime now(ZoneId id) {
        return LocalDateTime.now(id);
    }

    /**
     * Generates a world clock. Associates, for each current time, the zones that carray that same time.
     *
     * @return Map containing LocalDateTime and the countries that also carry that same LocalDateTime.
     */
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
