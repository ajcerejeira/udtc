package View;

import Exceptions.InvalidDateException;
import Model.DateCalculator;
import Utils.DateParser;
import Utils.Static;
import Utils.UI.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.concurrent.atomic.AtomicBoolean;

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
                                out.println(Static.RED_BOLD  + e.getMessage() + Static.RESET);
                            }
                        }),
                        new Option("F1","First day of next year", this::firstDay),
                        new Option("F2","First day of X year", this::firstDayX),
                        new Option("List Days between two Dates", () -> {
                            try {
                                interval();
                            } catch (InvalidDateException e) {
                                out.println(Static.RED_BOLD  + e.getMessage() + Static.RESET);
                            }
                        }),
                        new Option("WD","Workdays between two Dates", () -> {
                            try {
                                workdays();
                            } catch (InvalidDateException e) {
                                out.println(Static.RED_BOLD  + e.getMessage() + Static.RESET);
                            }
                        }),
                        new Option("WE","Weekends between two Dates", () -> {
                            try {
                                weekends();
                            } catch (InvalidDateException e) {
                                out.println(Static.RED_BOLD  + e.getMessage() + Static.RESET);
                            }
                        }),
                        new Option("Back", () -> out.println())
                })
        }).run();
    }

    private void firstDayX() {
        final int[] year = new int[1];
        AtomicBoolean flag = new AtomicBoolean(true);
        new UI(new Runnable[] {
                new Title("Date Calculator", 1),
                new Title("First day of next year", 2),
                new Input("Input year\n>>> ", m -> {
                    try {
                        year[0] = Integer.parseInt(m);
                    } catch (Exception e) {
                        flag.set(false);
                        out.println(Static.RED_BOLD  + e.getMessage() + Static.RESET);
                        this.run();
                    }
                }),
        }).run();

        if(flag.get()==true){
            new Title("First day of " + year[0] + " will be a " + LocalDate.of(year[0],1,1).getDayOfWeek(), 2).run();
        }

        this.run();
    }

    private void firstDay() {
        new UI(new Runnable[] {
                new Title("Date Calculator", 1),
                new Title("First day of next year", 2),
                new Title("First day of " + (LocalDate.now().getYear()+1) + " will be a " + LocalDate.of(LocalDate.now().getYear()+1,1,1).getDayOfWeek(), 2)
        }).run();
        this.run();
    }

    private void difference() throws InvalidDateException {
        final LocalDate[] d1 = new LocalDate[1];
        final LocalDate[] d2 = new LocalDate[1];
        new UI(new Runnable[] {
                new Title("Date Calculator", 1),
                new Title("Difference between two Dates", 2),
                new Input("First date: (YYYY-MM-DD)\n>>> ", m -> {
                    try {
                        d1[0] = DateParser.parseDate(m);
                    } catch (Exception e) {
                        out.println(Static.RED_BOLD  + e.getMessage() + Static.RESET);
                        this.run();
                    }
                }),
                new Input("Second date: (YYYY-MM-DD)\n>>> ", n -> {
                    try {
                        d2[0] = DateParser.parseDate(n);
                    } catch (Exception e) {
                        out.println(Static.RED_BOLD  + e.getMessage() + Static.RESET);
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
                new Title("Date Calculator", 1),
                new Title("Date Interval", 2),
                new Input("First date: (YYYY-MM-DD)\n>>> ", m -> {
                    try {
                        d1[0] = DateParser.parseDate(m);
                    } catch (Exception e) {
                        out.println(Static.RED_BOLD  + e.getMessage() + Static.RESET);
                        this.run();
                    }
                }),
                new Input("Second date: (YYYY-MM-DD)\n>>> ", n -> {
                    try {
                        d2[0] = DateParser.parseDate(n);
                    } catch (Exception e) {
                        out.println(Static.RED_BOLD  + e.getMessage() + Static.RESET);
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
                new Title("Date Calculator", 1),
                new Title("Workdays in interval", 2),
                new Input("First date: (YYYY-MM-DD)\n>>> ", m -> {
                    try {
                        d1[0] = DateParser.parseDate(m);
                    } catch (Exception e) {
                        out.println(Static.RED_BOLD  + e.getMessage() + Static.RESET);
                        this.run();
                    }
                }),
                new Input("Second date: (YYYY-MM-DD)\n>>> ", n -> {
                    try {
                        d2[0] = DateParser.parseDate(n);
                    } catch (Exception e) {
                        out.println(Static.RED_BOLD  + e.getMessage() + Static.RESET);
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
                new Title("Date Calculator", 1),
                new Title("Weekends in interval", 2),
                new Input("First date: (YYYY-MM-DD)\n>>> ", m -> {
                    try {
                        d1[0] = DateParser.parseDate(m);
                    } catch (Exception e) {
                        out.println(Static.RED_BOLD  + e.getMessage() + Static.RESET);
                        this.run();
                    }
                }),
                new Input("Second date: (YYYY-MM-DD)\n>>> ", n -> {
                    try {
                        d2[0] = DateParser.parseDate(n);
                    } catch (Exception e) {
                        out.println(Static.RED_BOLD  + e.getMessage() + Static.RESET);
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
