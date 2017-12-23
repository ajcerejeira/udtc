package View;

import Model.DateCalculator;
import Utils.Input.DateParser;
import Utils.Static;

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
                new Option("Days between two Dates", this::interval),
                new Option("Return", () -> out.println())
        });
        menu.run();
    }

    private void interval() {
        LocalDate d1,d2;
        out.print("First date: (YYYY-MM-DD)\n>>> ");
        d1= DateParser.parseDate(new Scanner(System.in).nextLine());
        out.print("Second date: (YYYY-MM-DD)\n>>> ");
        d2=DateParser.parseDate(new Scanner(System.in).nextLine());
        for(LocalDate dt: this.dateCalculator.listInterval(d1,d2)){
            out.println(dt.format(Static.df).toString() + " -> " + dt.getDayOfWeek());
        }
        this.run();
    }
}
