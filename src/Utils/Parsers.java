package Utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class Parsers {
    public static Optional<Integer> parseInt(String s) {
        Integer i;

        try {
            i = Integer.valueOf(s);
        } catch (Exception e) {
            i = null;
        }

        return Optional.ofNullable(i);
    }

    public static Optional<Double> parseDouble(String s) {
        Double d;

        try {
            d = Double.valueOf(s);
        } catch (Exception e) {
            d = null;
        }

        return Optional.ofNullable(d);
    }

    public static Optional<LocalDateTime> parseDateTime(String input) {
        LocalDateTime date = null;
        String[] patterns = new String[] {
                "yyyy-MM-dd HH:mm:ss",
                "yyyy-MM-dd HH:mm",
                "yyyy-MM-dd HH",
        };

        for (String pattern : patterns) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

            try {
                date = LocalDateTime.parse(input, formatter);
                break;
            } catch(DateTimeParseException e) {
                date = null;
            }
        }

        return Optional.ofNullable(date);
    }

    public static Optional<LocalDate> parseDate(String input) {
        LocalDate date;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            date = LocalDate.parse(input, formatter);
        } catch(DateTimeParseException e) {
            date = null;
        }

        return Optional.ofNullable(date);
    }

    public static Optional<ZoneId> parseZoneId(String id) {
        return Optional.ofNullable(ZoneId.of(id));
    }
}
