package View;

import Model.IAgeCalculator;
import Utils.DateParser;
import Utils.NumParser;
import Utils.TUI;
import Utils.Option;

import java.time.LocalDate;
import java.time.Period;


public class AgeCalculatorView {
    public static void home() {
        TUI.title("Age calculator", 1);
        TUI.menu(new Option[] {
                new Option("T1","Time until birthday", AgeCalculatorView::birthday),
                new Option("T2","Time until you're X years old", AgeCalculatorView::timeUntilAge),
                new Option("B", "Back", System.out::println)
        });
    }

    private static void birthday() {
        TUI.title("Age calculator", 1);
        TUI.title("Time until birthday", 2);
        LocalDate birthday = TUI.input("Birthday [yyyy-mm-dd]", DateParser::parseDate);
        Period p = IAgeCalculator.timeUntilBirthday(birthday);

        if (p.getDays() == 0) {
            System.out.println("Today is your birthday! Happy birthday!\n");
        } else {
            System.out.println("Time until your next birthday:");
            System.out.println(p.getYears() + " year(s), " + p.getMonths() + " month(s), " + p.getDays() + " day(s)");
        }

        TUI.menu(new Option[] {
                new Option("B", "Back", AgeCalculatorView::home)
        });
    }

    private static void timeUntilAge() {
        TUI.title("Age calculator", 1);
        TUI.title("Time until X years old", 2);

        LocalDate birthday = TUI.input("Birthday date: [yyyy-mm-dd]", DateParser::parseDate);
        Integer age = TUI.input("How old do you want to be?", NumParser::parseInt);
        Period timeLeft = IAgeCalculator.timeUntilAge(birthday, age);

        System.out.println("You'll be " + age + " years old in "
                + timeLeft.getYears() + " years "
                + timeLeft.getMonths() + " months and "
                + timeLeft.getDays() + " days");

        TUI.menu(new Option[] {
                new Option("B", "Back", AgeCalculatorView::home)
        });
    }
}
