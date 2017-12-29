package View;

import Model.IAge;
import Utils.DateParser;
import Utils.Mutable;
import Utils.NumParser;
import Utils.UI.*;

import java.time.LocalDate;
import java.time.Period;

import static java.lang.System.out;

public class AgeView implements Runnable{

    public AgeView() {
    }

    @Override
    public void run() {
        new UI(new Runnable[] {
                new Title("Age Calculator", 1),
                new Menu(new Option[] {
                        new Option("T1","Time until birthday", this::birthday),
                        new Option("T2","Time until you're X years old", this::untilXYear),
                        new Option("B", "Back", System.out::println),
                }),
        }).run();
    }

    private void untilXYear() {
        Mutable<LocalDate> birthday = new Mutable<>(LocalDate.now());
        Mutable<Integer> age = new Mutable<>(0);

        new UI(new Runnable[] {
                new Title("Age Calculator", 1),
                new Title("Time until X years old", 2),
                new Input<>("Birthday date: [yyyy-mm-dd]", birthday::set, DateParser::parseDate),
                new Input<>("How old do you want to be?", age::set, NumParser::parseInt),
                () -> {
                    Period timeLeft = IAge.timeUntilAge(birthday.get(), age.get());
                    out.println("You'll be " + age.get() + " years old in "
                                + timeLeft.getYears() + " years "
                                + timeLeft.getMonths() + " months and "
                                + timeLeft.getDays() + " days"); },
                new Menu(new Option[] {
                        new Option("Back", this),
                })
        }).run();
    }

    private void birthday() {
        new UI(new Runnable[] {
                new Title("Age Calculator", 1),
                new Title("Time until birthday", 2),
                new Input<>("Birthday [yyyy-mm-dd]", d -> {
                    Period p = IAge.timeUntilBirthday(d);

                    if (p.getDays() == 0) {
                        out.println("Today is your birthday! Happy birthday!\n");
                    } else {
                        out.println("Time until your next birthday:");
                        out.println(p.getYears() + " year(s), " + p.getMonths() + " month(s), " + p.getDays() + " day(s)");
                    } }, DateParser::parseDate),

                new Menu(new Option[] {
                        new Option("Back", this)
                })
        }).run();
    }
}
