package Utils.Input;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateParser {

    public static LocalDateTime parseDateTime(String input) throws DateTimeException{
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime date = LocalDateTime.parse(input, formatter);

        return date;
    }

    public static LocalDate parseDate(String input) throws DateTimeException{
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(input, formatter);

        return date;
    }

}
