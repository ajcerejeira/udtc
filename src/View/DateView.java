package View;

import Exceptions.InvalidDateException;
import Model.DateCalculator;
import Utils.DateParser;
import Utils.Static;
import Utils.UI.*;

import java.time.LocalDate;
import java.util.Scanner;

import static java.lang.System.out;

public class DateView implements Runnable {
    private DateCalculator dateCalculator;

    public DateView(DateCalculator dateCalculator) {
        this.dateCalculator = dateCalculator;
    }

    @Override
    public void run() {
        Menu menu = new Menu(new Option[] {
                new Option("Days between two Dates", () -> {
                    try {
                        interval();
                    } catch (Exception e) {
                        out.println(e.getMessage());
                        this.run();
                    }
                }),
                new Option("WD","Workdays between two Dates", () -> {
                    try {
                        workdays();
                    } catch (Exception e) {
                        out.println(e.getMessage());
                        this.run();
                    }
                }),
                new Option("WE","Weekends between two Dates", () -> {
                    try {
                        weekends();
                    } catch (Exception e) {
                        out.println(e.getMessage());
                        this.run();
                    }
                }),
                new Option("Return", () -> out.println())
        });
        menu.run();
    }

    private void interval() throws InvalidDateException {
        final LocalDate[] d1 = new LocalDate[1];
        final LocalDate[] d2 = new LocalDate[1];
        new UI(new Runnable[] {
                new Title("Date Interval", 1),

                new Input("First date: (YYYY-MM-DD)\n>>> ", m -> {
                    try {
                        d1[0] = DateParser.parseDate(m);
                    } catch (Exception e) {
                        out.println(e.getMessage());
                        this.run();
                    }
                }),
                new Input("Second date: (YYYY-MM-DD)\n>>> ", n -> {
                    try {
                        d2[0] = DateParser.parseDate(n);
                    } catch (Exception e) {
                        out.println(e.getMessage());
                        this.run();
                    }
                })
        }).run();

        if(d1[0].isAfter(d2[0]) || d2[0].isBefore(d1[0]))
            throw new InvalidDateException("Invalid date interval");

        for(LocalDate dt: this.dateCalculator.listInterval(d1[0], d2[0])){
            out.println(dt.format(Static.df).toString() + " -> " + dt.getDayOfWeek());
        }
        this.run();
    }

    private void workdays() throws InvalidDateException {
        final LocalDate[] d1 = new LocalDate[1];
        final LocalDate[] d2 = new LocalDate[1];
        new UI(new Runnable[] {
                new Title("Workdays in interval", 1),

                new Input("First date: (YYYY-MM-DD)\n>>> ", m -> {
                    try {
                        d1[0] = DateParser.parseDate(m);
                    } catch (Exception e) {
                        out.println(e.getMessage());
                        this.run();
                    }
                }),
                new Input("Second date: (YYYY-MM-DD)\n>>> ", n -> {
                    try {
                        d2[0] = DateParser.parseDate(n);
                    } catch (Exception e) {
                        out.println(e.getMessage());
                        this.run();
                    }
                })
        }).run();

        if(d1[0].isAfter(d2[0]) || d2[0].isBefore(d1[0]))
            throw new InvalidDateException("Invalid date interval");

        for(LocalDate dt: this.dateCalculator.workDays(d1[0], d2[0])){
            out.println(dt.format(Static.df).toString() + " -> " + dt.getDayOfWeek());
        }
        this.run();
    }

    private void weekends() throws InvalidDateException {
        final LocalDate[] d1 = new LocalDate[1];
        final LocalDate[] d2 = new LocalDate[1];
        new UI(new Runnable[] {
                new Title("Weekends in interval", 1),

                new Input("First date: (YYYY-MM-DD)\n>>> ", m -> {
                    try {
                        d1[0] = DateParser.parseDate(m);
                    } catch (Exception e) {
                        out.println(e.getMessage());
                        this.run();
                    }
                }),
                new Input("Second date: (YYYY-MM-DD)\n>>> ", n -> {
                    try {
                        d2[0] = DateParser.parseDate(n);
                    } catch (Exception e) {
                        out.println(e.getMessage());
                        this.run();
                    }
                })
        }).run();

        if(d1[0].isAfter(d2[0]) || d2[0].isBefore(d1[0]))
            throw new InvalidDateException("Invalid date interval");

        for(LocalDate dt: this.dateCalculator.weekends(d1[0], d2[0])){
            out.println(dt.format(Static.df).toString() + " -> " + dt.getDayOfWeek());
        }
        this.run();
    }
}
