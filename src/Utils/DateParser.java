package Utils;

import Exceptions.InvalidDateException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class DateParser {

    public static LocalDateTime parseDateTime(String input) throws InvalidDateException {
        LocalDateTime date;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        try {
            date = LocalDateTime.parse(input, formatter);
        } catch(DateTimeParseException e){
            throw new InvalidDateException("Invalid date format!");
        }
        return date;
    }

    public static Optional<LocalDateTime> parseDateTime2(String input) {
        LocalDateTime date;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try {
            date = LocalDateTime.parse(input, formatter);
        } catch(DateTimeParseException e) {
            date = null;
        }

        return Optional.ofNullable(date);
    }

    public static LocalDate parseDate(String input) throws InvalidDateException{
        LocalDate date;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            date = LocalDate.parse(input, formatter);
        }catch(DateTimeParseException e){
            throw new InvalidDateException("Invalid date format!");
        }
        return date;
    }

    public static Optional<LocalDate> parseDate2(String input) {
        LocalDate date;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            date = LocalDate.parse(input, formatter);
        } catch(DateTimeParseException e) {
            date = null;
        }

        return Optional.ofNullable(date);
    }

}
