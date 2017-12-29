package View;

import Model.IDateCalculator;
import Utils.DateParser;
import Utils.Mutable;
import Utils.Static;
import Utils.UI.*;

import java.time.LocalDate;
import java.time.Period;

import static java.lang.System.out;

public class DateView implements Runnable {
    public DateView() {

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
                new Title("Date Calculator", 1),
                new Title("First day of next year", 2),
                new Title("First day of " + (LocalDate.now().getYear()+1) + " will be a " + LocalDate.of(LocalDate.now().getYear()+1,1,1).getDayOfWeek(), 2)
        }).run();
        this.run();
    }

    private void difference() {
        Mutable<LocalDate> d1 = new Mutable<>(LocalDate.now());
        Mutable<LocalDate> d2 = new Mutable<>(LocalDate.now());

        new UI(new Runnable[] {
                new Title("Date Calculator", 1),
                new Title("Difference between two Dates", 2),
                new Input<>("First date [yyyy-mm-dd]", d1::set, DateParser::parseDate),
                new Input<>("Second date [yyyy-mm-dd]", d2::set, DateParser::parseDate),

                () -> {
                    out.println();

                    Period p = d1.get().isBefore(d2.get()) ?
                            IDateCalculator.differenceBetweenDates(d1.get(), d2.get())
                            : IDateCalculator.differenceBetweenDates(d2.get(), d1.get());

                    out.println("Difference between " + d1 + " and " + d2 + ":");
                    out.println(p.getYears() + "year(s), " + p.getMonths() + " month(s), " + p.getDays() + " day(s)");
                },

                new Menu(new Option[] {
                        new Option("B", "Back", this),
                })
        }).run();
    }

    private void interval() {
        Mutable<LocalDate> d1 = new Mutable<>(LocalDate.now());
        Mutable<LocalDate> d2 = new Mutable<>(LocalDate.now());

        new UI(new Runnable[]{
                new Title("Date Interval", 1),
                new Input<>("First date [yyyy-mm-dd]", d1::set, DateParser::parseDate),
                new Input<>("Second date [yyyy-mm-dd]", d2::set, DateParser::parseDate),

                // FIXME
                new Table<>(d1.get().isBefore(d2.get()) ?
                        IDateCalculator.listInterval(d1.get(), d2.get()) :
                        IDateCalculator.listInterval(d2.get(), d1.get()),
                        d -> "[" + d.format(Static.df) + "] " + d.getDayOfWeek()
                ),

                () -> {
                    if (d1.get().isBefore(d2.get())) {
                        for(LocalDate dt: IDateCalculator.listInterval(d1.get(), d2.get())){
                            out.println(dt.format(Static.df) + " -> " + dt.getDayOfWeek());
                        }
                    } else {
                        for(LocalDate dt: IDateCalculator.listInterval(d2.get(), d1.get())){
                            out.println(dt.format(Static.df) + " -> " + dt.getDayOfWeek());
                        }
                    } },

                new Menu(new Option[] {
                        new Option("B", "Back", this),
                })
        }).run();
    }

    private void workdays() {
        Mutable<LocalDate> d1 = new Mutable<>(LocalDate.now());
        Mutable<LocalDate> d2 = new Mutable<>(LocalDate.now());

        new UI(new Runnable[] {
                new Title("Workdays in interval", 1),
                new Input<>("First date [yyyy-mm-dd]", d1::set, DateParser::parseDate),
                new Input<>("Second date [yyyy-mm-dd]", d2::set, DateParser::parseDate),

                () -> {
                    if (d1.get().isBefore(d2.get())) {
                        for(LocalDate dt: IDateCalculator.workDays(d1.get(), d2.get())){
                            out.println(dt.format(Static.df) + " -> " + dt.getDayOfWeek());
                        }
                    } else {
                        for(LocalDate dt: IDateCalculator.workDays(d2.get(), d1.get())){
                            out.println(dt.format(Static.df) + " -> " + dt.getDayOfWeek());
                        }
                    } },

                new Menu(new Option[] {
                        new Option("B", "Back", this),
                })
        }).run();
    }

    private void weekends() {
        Mutable<LocalDate> d1 = new Mutable<>(LocalDate.now());
        Mutable<LocalDate> d2 = new Mutable<>(LocalDate.now());

        new UI(new Runnable[] {
                new Title("Weekends in interval", 1),
                new Input<>("First date [yyyy-mm-dd]", d1::set, DateParser::parseDate),
                new Input<>("Second date [yyyy-mm-dd]", d2::set, DateParser::parseDate),

                () -> {
                    out.println();

                    if (d1.get().isBefore(d2.get())) {
                        for(LocalDate dt: IDateCalculator.weekends(d1.get(), d2.get())){
                            out.println(dt.format(Static.df) + " -> " + dt.getDayOfWeek());
                        }
                    } else {
                        for(LocalDate dt: IDateCalculator.weekends(d2.get(), d1.get())){
                            out.println(dt.format(Static.df) + " -> " + dt.getDayOfWeek());
                        }
                    }
                },

                new Menu(new Option[] {
                        new Option("B", "Back", this),
                })
        }).run();
    }
}
