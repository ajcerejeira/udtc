package View;

import Model.IDateCalculator;
import Utils.Option;
import Utils.Parsers;
import Utils.UI;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class DateCalculatorView {
    public static void home() {
        UI.title("Date calculator");
        UI.menu(new Option("Add to date", DateCalculatorView::add),
                new Option("Subtract from date", DateCalculatorView::sub),
                new Option("Difference between two dates", DateCalculatorView::difference),
                new Option("Week number and day of a date", DateCalculatorView::week),
                new Option("Weekend list", DateCalculatorView::weekends),
                new Option("Weekdays list", DateCalculatorView::weekdays),
                new Option("Back", System.out::println));
    }

    private static void add() {
        UI.title("Date calculator");
        UI.subtitle("Add to date");

        LocalDate date = UI.input("Date [yyyy-mm-dd]", Parsers::parseDate);
        int years = UI.input("Years", Parsers::parseInt);
        int months = UI.input("Months", Parsers::parseInt);
        int weeks = UI.input("Weeks", Parsers::parseInt);
        int days = UI.input("Days", Parsers::parseInt);

        System.out.println("Result: " + IDateCalculator.add(date, years, months, weeks, days));
        UI.menu(new Option("Back", DateCalculatorView::home));
    }

    private static void sub() {
        UI.title("Date calculator");
        UI.subtitle("Subtract from date");

        LocalDate date = UI.input("Date [yyyy-mm-dd]", Parsers::parseDate);
        int years = UI.input("Years", Parsers::parseInt);
        int months = UI.input("Months", Parsers::parseInt);
        int weeks = UI.input("Weeks", Parsers::parseInt);
        int days = UI.input("Days", Parsers::parseInt);

        System.out.println("Result: " + IDateCalculator.sub(date, years, months, weeks, days));
        UI.menu(new Option("Back", DateCalculatorView::home));
    }

    private static void difference() {
        UI.title("Date calculator");
        UI.subtitle("Difference between dates");

        LocalDate d1 = UI.input("Date 1 [yyyy-mm-dd]", Parsers::parseDate);
        LocalDate d2 = UI.input("Date 2 [yyyy-mm-dd]", Parsers::parseDate);
        Period p = IDateCalculator.difference(d1, d2);

        System.out.println("Difference:");
        System.out.println(p.getYears() + " year(s), " + p.getMonths() + " month(s), " + p.get(ChronoUnit.WEEKS)
                + " week(s)" +  p.getDays() + " day(s)." );
        UI.menu(new Option("Back", DateCalculatorView::home));
    }

    private static void week() {
        UI.title("Date calculator");
        UI.subtitle("Week number and day of a date");
        LocalDate date = UI.input("Date [yyyy-mm-dd]", Parsers::parseDate);
        DayOfWeek dayOfWeek = IDateCalculator.dayOfWeek(date);
        int weekNumber = IDateCalculator.week(date);

        System.out.println(dayOfWeek + ", week " + weekNumber);
        UI.menu(new Option("Back", DateCalculatorView::home));
    }

    private static void weekends() {
        UI.title("Date calculator");
        UI.subtitle("Weekends list");
        LocalDate from = UI.input("From [yyyy-mm-dd]", Parsers::parseDate);
        LocalDate to = UI.input("To [yyyy-mm-dd]", Parsers::parseDate);

        System.out.println("Weekends between " + from + " and " + to);
        UI.list(IDateCalculator.weekends(from, to), d -> d + " " + d.getDayOfWeek());
        UI.menu(new Option("Back", DateCalculatorView::home));
    }

    private static void weekdays() {
        UI.title("Date calculator");
        UI.subtitle("Weekdays list");
        LocalDate from = UI.input("From [yyyy-mm-dd]", Parsers::parseDate);
        LocalDate to = UI.input("To [yyyy-mm-dd]", Parsers::parseDate);

        System.out.println("Weekdays between " + from + " and " + to);
        UI.list(IDateCalculator.weekdays(from, to), d -> d + " " + d.getDayOfWeek());
        UI.menu(new Option("Back", DateCalculatorView::home));
    }
}