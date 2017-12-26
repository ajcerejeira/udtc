package View;

import Exceptions.InvalidDateException;
import Model.DateCalculator;
import Utils.DateParser;
import Utils.Mutable;
import Utils.Static;
import Utils.UI.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

import static java.lang.System.out;

public class DateView implements Runnable {
    private DateCalculator dateCalculator;

    public DateView(DateCalculator dateCalculator) {
        this.dateCalculator = dateCalculator;
    }

    @Override
    public void run() {
        new UI(new Runnable[] {
                new Title("Date Calculator", 1),
                new Menu(new Option[] {
                        new Option("Difference between two Dates", this::difference),
                        new Option("First day of next year", this::firstDay),
                        new Option("List Days between two Dates", this::interval),
                        new Option("WD","Workdays between two Dates", this::workdays),
                        new Option("WE","Weekends between two Dates", this::weekends),
                        new Option("Back", out::println)
                })
        }).run();
    }

    private void firstDay() {
        new UI(new Runnable[] {
                new Title("First day of next year", 1),
                new Title("First day of " + (LocalDate.now().getYear()+1) + " will be a " + LocalDate.of(LocalDate.now().getYear()+1,1,1).getDayOfWeek(), 2)
        }).run();
        this.run();
    }

    private void difference() {
        Mutable<LocalDate> d1 = new Mutable<>(LocalDate.now());
        Mutable<LocalDate> d2 = new Mutable<>(LocalDate.now());
        new UI(new Runnable[] {
                new Title("Difference between two Dates", 1),
                new Input<>("First date [yyyy-mm-dd]", d1::set, DateParser::parseDate2),
                new Input<>("Second date [yyyy-mm-dd]", d2::set, DateParser::parseDate2),
        }).run();

        Period p = d1.get().isBefore(d2.get()) ?
                  this.dateCalculator.differenceBetweenDates(d1.get(), d2.get())
                : this.dateCalculator.differenceBetweenDates(d2.get(), d1.get());

        out.println("Years: " + p.getYears() + " Months: " + p.getMonths() + " Days: "+ p.getDays());
        this.run();
    }

    private void interval() {
        Mutable<LocalDate> d1 = new Mutable<>(LocalDate.now());
        Mutable<LocalDate> d2 = new Mutable<>(LocalDate.now());

        new UI(new Runnable[]{
                new Title("Date Interval", 1),
                new Input<>("First date [yyyy-mm-dd]", d1::set, DateParser::parseDate2),
                new Input<>("Second date [yyyy-mm-dd]", d2::set, DateParser::parseDate2),
        }).run();

        if (d1.get().isBefore(d2.get())) {
            for(LocalDate dt: this.dateCalculator.listInterval(d1.get(), d2.get())){
                out.println(dt.format(Static.df) + " -> " + dt.getDayOfWeek());
            }
        } else {
            for(LocalDate dt: this.dateCalculator.listInterval(d2.get(), d1.get())){
                out.println(dt.format(Static.df) + " -> " + dt.getDayOfWeek());
            }
        }
        this.run();
    }

    private void workdays() {
        Mutable<LocalDate> d1 = new Mutable<>(LocalDate.now());
        Mutable<LocalDate> d2 = new Mutable<>(LocalDate.now());

        new UI(new Runnable[] {
                new Title("Workdays in interval", 1),
                new Input<>("First date [yyyy-mm-dd]", d1::set, DateParser::parseDate2),
                new Input<>("Second date [yyyy-mm-dd]", d2::set, DateParser::parseDate2),
        }).run();

        if (d1.get().isBefore(d2.get())) {
            for(LocalDate dt: this.dateCalculator.workDays(d1.get(), d2.get())){
                out.println(dt.format(Static.df) + " -> " + dt.getDayOfWeek());
            }
        } else {
            for(LocalDate dt: this.dateCalculator.workDays(d2.get(), d1.get())){
                out.println(dt.format(Static.df) + " -> " + dt.getDayOfWeek());
            }
        }

        this.run();
    }

    private void weekends() {
        Mutable<LocalDate> d1 = new Mutable<>(LocalDate.now());
        Mutable<LocalDate> d2 = new Mutable<>(LocalDate.now());

        new UI(new Runnable[] {
                new Title("Weekends in interval", 1),
                new Input<>("First date [yyyy-mm-dd]", d1::set, DateParser::parseDate2),
                new Input<>("Second date [yyyy-mm-dd]", d2::set, DateParser::parseDate2),
        }).run();

        if (d1.get().isBefore(d2.get())) {
            for(LocalDate dt: this.dateCalculator.weekends(d1.get(), d2.get())){
                out.println(dt.format(Static.df) + " -> " + dt.getDayOfWeek());
            }
        } else {
            for(LocalDate dt: this.dateCalculator.weekends(d2.get(), d1.get())){
                out.println(dt.format(Static.df) + " -> " + dt.getDayOfWeek());
            }
        }

        this.run();
    }
}
