package View;

import Model.IAgeCalculator;
import Utils.Parsers;
import Utils.UI;
import Utils.Option;

import java.time.LocalDate;
import java.time.Period;


public class AgeCalculatorView {
    public static void home() {
        UI.title("Age calculator");
        UI.menu(new Option("Age", AgeCalculatorView::age),
                new Option("Time until birthday", AgeCalculatorView::birthday),
                new Option("Time until you're X years old", AgeCalculatorView::timeUntilAge),
                new Option("Back", System.out::println));
    }

    private static void age() {
        UI.title("Age calculator");
        UI.subtitle("Age");
        LocalDate birthday = UI.input("Birthday [yyyy-mm-dd]", Parsers::parseDate);

        System.out.println("You are " + IAgeCalculator.age(birthday) + " years old.");

        UI.menu(new Option("Back", AgeCalculatorView::home));
    }

    private static void birthday() {
        UI.title("Age calculator");
        UI.subtitle("Time until birthday");
        LocalDate birthday = UI.input("Birthday [yyyy-mm-dd]", Parsers::parseDate);
        Period p = IAgeCalculator.timeUntilBirthday(birthday);

        if (p.isZero()) {
            System.out.println("Today is your birthday! Happy birthday!\n");
        } else {
            System.out.println("Time until your next birthday:");
            System.out.println(p.getYears() + " year(s), " + p.getMonths() + " month(s), " + p.getDays() + " day(s)");
        }

        UI.menu(new Option("Back", AgeCalculatorView::home));
    }

    private static void timeUntilAge() {
        UI.title("Age calculator");
        UI.subtitle("Time until X years old");

        LocalDate birthday = UI.input("Birthday date: [yyyy-mm-dd]", Parsers::parseDate);
        Integer age = UI.input("How old do you want to be?", Parsers::parseInt);
        Period timeLeft = IAgeCalculator.timeUntilAge(birthday, age);

        System.out.println("You'll be " + age + " years old in "
                + timeLeft.getYears() + " years "
                + timeLeft.getMonths() + " months and "
                + timeLeft.getDays() + " days");

        UI.menu(new Option("Back", AgeCalculatorView::home));
    }
}
