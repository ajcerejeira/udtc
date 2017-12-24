package View;

import Exceptions.InvalidDateException;
import Model.DateCalculator;
import Utils.DateParser;
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
                        new Option("Difference between two Dates", () -> {
                            try {
                                difference();
                            } catch (InvalidDateException e) {
                                out.println(e.getMessage());
                            }
                        }),
                        new Option("First day of next year", this::firstDay),
                        new Option("List Days between two Dates", () -> {
                            try {
                                interval();
                            } catch (InvalidDateException e) {
                                out.println(e.getMessage());
                            }
                        }),
                        new Option("WD","Workdays between two Dates", () -> {
                            try {
                                workdays();
                            } catch (InvalidDateException e) {
                                out.println(e.getMessage());
                            }
                        }),
                        new Option("WE","Weekends between two Dates", () -> {
                            try {
                                weekends();
                            } catch (InvalidDateException e) {
                                out.println(e.getMessage());
                            }
                        }),
                        new Option("Back", () -> out.println())
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

    private void difference() throws InvalidDateException {
        final LocalDate[] d1 = new LocalDate[1];
        final LocalDate[] d2 = new LocalDate[1];
        new UI(new Runnable[] {
                new Title("Difference between two Dates", 1),

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

        Period p = this.dateCalculator.differenceBetweenDates(d1[0],d2[0]);
        out.println("\nYears: " + p.getYears() + " Months: " + p.getMonths() + " Days: "+ p.getDays());
        this.run();
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
