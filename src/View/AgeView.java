package View;

import Exceptions.InvalidDateException;
import Model.Age;
import Utils.DateParser;
import Utils.Mutable;
import Utils.NumParser;
import Utils.UI.Input;
import Utils.UI.Title;
import Utils.UI.UI;

import java.time.LocalDate;
import java.time.Period;

import static java.lang.System.out;

public class AgeView implements Runnable{
    private Age age;

    public AgeView(Age age) {
        this.age = age;
    }

    @Override
    public void run() {
        new UI(new Runnable[] {
                new Title("Age Calculator", 1),
                new Menu(new Option[] {
                        new Option("T1","Time until birthday", () -> {
                            try {
                                birthday();
                            } catch (Exception e) {
                                out.println(e.getMessage());
                            }
                        }),
                        new Option("T2","Time until you're X years old", () -> {
                            try {
                                untilXYear();
                            } catch (Exception e) {
                                out.println(e.getMessage());
                            }
                        }),
                        new Option("B", "Back", () -> System.out.print(""))
                }),
        }).run();
    }

    private void untilXYear() {
        Mutable<LocalDate> date = new Mutable<>(LocalDate.now());
        Mutable<Integer> age = new Mutable<>(0);

        new UI(new Runnable[] {
                new Title("Time until X years old", 1),
                new Input<>("Birthday date: [yyyy-mm-dd]", date::set, DateParser::parseDate2),
                new Input<>("How old do you want to be?", age::set, NumParser::parseInt),
        }).run();

        Period p = this.age.timeUntilBirthday(date.get().plusYears(age.get()));
        out.println("You'll be " + age.get() + " years old in " + p.getYears() +" years " + p.getMonths() + " months and " + p.getDays() + " days.");

        this.run();
    }

    private void birthday() {
        LocalDate now = LocalDate.now();
        Mutable<LocalDate> birthdate = new Mutable<>(LocalDate.now());

        new UI(new Runnable[] {
                new Title("Time until birthday", 1),
                new Input<>("Birthday [yyyy-mm-dd]", birthdate::set, DateParser::parseDate2),
        }).run();

        now = now.withYear(birthdate.get().getYear());

        if (birthdate.get().equals(now)) {
            out.println("Today is your birthday! Happy birthday!");
        } else {
            if (birthdate.get().isBefore(now)) {
                Period p = Period.between(birthdate.get(), now);
                out.println("Time until your next birthday:");
                out.println(p.getYears() + " year(s), " + p.getMonths() + " month(s), " + p.getDays());
            } else {
                Period p = Period.between(birthdate.get(), now.plusYears(1));
                out.println("Time until your next birthday:");
                out.println(p.getYears() + " year(s), " + p.getMonths() + " month(s), " + p.getDays());
            }
        }
        this.run();
    }
}
