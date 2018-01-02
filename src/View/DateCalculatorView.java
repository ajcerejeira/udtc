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
        UI.menu(new Option("Add to date", DateCalculatorView::addToDate),
                new Option("Subtract from date", DateCalculatorView::subFromDate),
                new Option("Difference between two dates", DateCalculatorView::differenceBetweenDates),
                new Option("Week number and day of a date", DateCalculatorView::week),
                new Option("Back", System.out::println));
    }

    private static void addToDate() {
        UI.title("Date calculator");
        UI.subtitle("Add to date");

        LocalDate date = UI.input("Date", Parsers::parseDate);
        int years = UI.input("Years", Parsers::parseInt);
        int months = UI.input("Months", Parsers::parseInt);
        int weeks = UI.input("Weeks", Parsers::parseInt);
        int days = UI.input("Days", Parsers::parseInt);

        System.out.println("Result: " + IDateCalculator.addToDate(date, years, months, weeks, days));
        UI.menu(new Option("Back", DateCalculatorView::home));
    }

    private static void subFromDate() {
        UI.title("Date calculator");
        UI.subtitle("Subtract from date");
        LocalDate date = UI.input("Date", Parsers::parseDate);
        int years = UI.input("Years", Parsers::parseInt);
        int months = UI.input("Months", Parsers::parseInt);
        int weeks = UI.input("Weeks", Parsers::parseInt);
        int days = UI.input("Days", Parsers::parseInt);

        System.out.println("Result: " + IDateCalculator.subFromDate(date, years, months, weeks, days));
        UI.menu(new Option("Back", DateCalculatorView::home));
    }

    private static void differenceBetweenDates() {
        UI.title("Date calculator");
        UI.subtitle("Difference between dates");
        LocalDate d1 = UI.input("Date 1", Parsers::parseDate);
        LocalDate d2 = UI.input("Date 2", Parsers::parseDate);
        Period p = IDateCalculator.differenceBetweenDates(d1, d2);

        System.out.println("Difference:");
        System.out.println(p.getYears() + " year(s), " + p.getMonths() + " month(s), " + p.get(ChronoUnit.WEEKS)
                + " week(s)" +  p.getDays() + " day(s)." );
        UI.menu(new Option("Back", DateCalculatorView::home));
    }

    private static void week() {
        UI.title("Date calculator");
        UI.subtitle("Week number and day of a date");
        LocalDate date = UI.input("Date", Parsers::parseDate);
        DayOfWeek dayOfWeek = IDateCalculator.dayOfWeek(date);
        int weekNumber = IDateCalculator.week(date);

        System.out.println(dayOfWeek + ", week" + weekNumber);
        UI.menu(new Option("Back", DateCalculatorView::home));
    }
}