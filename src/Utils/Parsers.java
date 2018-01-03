package Utils;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public static Optional<Duration> parseDuration(String input) {
        Pattern p = Pattern.compile("(\\d+):(\\d+)");
        Matcher m = p.matcher(input);
        Duration duration;

        try {
            if (m.find()) {
                int hours = Integer.valueOf(m.group(1));
                int minutes = Integer.valueOf(m.group(2));
                duration = Duration.ofMinutes(hours * 60 + minutes);
            } else {
                duration = null;
            }
        } catch (Exception e) {
            duration = null;
        }

        return Optional.ofNullable(duration);
    }

    public static Optional<ZoneId> parseZoneId(String input) {
        ZoneId id;

        try {
            id = ZoneId.of(input);
        } catch(Exception e) {
            id = null;
        }

        return Optional.ofNullable(id);
    }
}
