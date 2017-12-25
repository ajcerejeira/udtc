package View;

import Exceptions.InvalidDateException;
import Model.Age;
import Utils.DateParser;
import Utils.Static;
import Utils.UI.*;

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
                                out.println(Static.RED_BOLD + e.getMessage() + Static.RESET);
                            }
                        }),
                        new Option("T2","Time until you're X years old", () -> {
                            try {
                                untilXYear();
                            } catch (Exception e) {
                                out.println(Static.RED_BOLD + e.getMessage() + Static.RESET);
                            }
                        }),
                        new Option("B", "Back", () -> System.out.print(""))
                }),
        }).run();
    }

    private void untilXYear() {
        final LocalDate[] d1 = new LocalDate[1];
        final int[] age = new int[1];

        new UI(new Runnable[] {
                new Title("Time until X years old", 1),
                new Input("Birthday date: (YYYY-MM-DD)\n>>> ", m -> {
                    try {
                        d1[0] = DateParser.parseDate(m);
                    } catch (Exception e) {
                        out.println(Static.RED_BOLD + e.getMessage() + Static.RESET);
                        this.run();
                    }
                }),
                new Input("How old do you want to be?\n>>> ", m -> {
                    try {
                        age[0] = Integer.parseInt(m);
                    } catch (Exception e) {
                        out.println(Static.RED_BOLD + e.getMessage() + Static.RESET);
                        this.run();
                    }
                }),
        }).run();

        Period p = this.age.timeUntilBirthday(d1[0].plusYears(age[0]));
        out.println("You'll be " + age[0] + " years old in " + p.getYears() +" years " + p.getMonths() + " months and " + p.getDays() + " days.");

        this.run();
    }

    private void birthday() throws InvalidDateException {
        final int[] d = new int[2];

        new UI(new Runnable[] {
                new Title("Time until birthday", 1),
                new Input("Birthday Month\n>>> ", m -> {
                    try{
                        d[0] = Integer.parseInt(m);
                    }catch(NumberFormatException e){
                        out.println(Static.RED_BOLD + "Invalid input received!" + Static.RESET);
                    }
                }),
                new Input("Birthday Day\n>>> ", m -> {
                    try{
                        d[1] = Integer.parseInt(m);
                    }catch(NumberFormatException e){
                        out.println(Static.RED_BOLD + "Invalid input received!" + Static.RESET);
                    }
                }),
        }).run();

        if(d[0] == LocalDate.now().getMonth().getValue() && d[1]==LocalDate.now().getDayOfMonth()){
            out.println("Today is your birthday! Happy birthday!");
        }else{
            if(d[0] <= LocalDate.now().getMonth().getValue()) {
                if (d[1] <= LocalDate.now().getDayOfMonth()) {
                    LocalDate date = LocalDate.of(LocalDate.now().getYear()+1, d[0], d[1]);
                    Period p = this.age.timeUntilBirthday(date);
                    out.println("\nTime until your next birthday -> Years: " + p.getYears() + " Months: " + p.getMonths() + " Days: "+ p.getDays());
                }else if (d[1] > LocalDate.now().getDayOfMonth()) {
                    LocalDate date = LocalDate.of(LocalDate.now().getYear(), d[0], d[1]);
                    Period p = this.age.timeUntilBirthday(date);
                    out.println("\nTime until your next birthday -> Years: " + p.getYears() + " Months: " + p.getMonths() + " Days: "+ p.getDays());
                }
            }else{
                LocalDate date = LocalDate.of(LocalDate.now().getYear()+1, d[0], d[1]);
                Period p = this.age.timeUntilBirthday(date);
                out.println("\nTime until your next birthday -> Years: " + p.getYears() + " Months: " + p.getMonths() + " Days: "+ p.getDays());
            }
        }
        this.run();
    }
}
