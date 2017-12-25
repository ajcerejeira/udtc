package View;

import Model.Clock;
import Utils.Static;
import Utils.UI.*;

import java.time.LocalDateTime;
import java.time.ZoneId;

import static java.lang.System.out;

public class ClockView implements Runnable {
    private Clock clock;

    public ClockView(Clock clock) {
        this.clock = clock;
    }

    @Override
    public void run() {
        new UI(new Runnable[] {
                new Title("Clock", 1),
                new Menu(new Option[] {
                        new Option("S", "Show current time at different countries", this::showTime),
                        new Option("C", "Get local time of certain country", this::getLocalTime),
                        new Option("B", "Back", () -> System.out.print("")),
                }),
        }).run();
    }

    private void showTime() {
        new UI(new Runnable[] {
                new Title("Clock", 1),
                new Title("Current time at different countries", 2),
                new IndexedTable(this.clock.timeAt().length-1,this.clock.timeAt())
        }).run();
        this.run();
    }

    private void getLocalTime() {
        new UI(new Runnable[] {
                new Title("Clock", 1),
                new Title("Local time", 2),
                new Title("Check available Time Zone IDs here: http://bit.ly/2pue8HL", 2),
                new Input("Country code to check?\n>>>", x -> {
                    try {
                        LocalDateTime d = this.clock.getLocalTimeAt(ZoneId.of(x));
                        String str = String.format(String.format(d.toString()),Static.dtf);
                        out.println("Time at " + x + " is " + str.substring(0,str.indexOf(".")).replace("T"," "));
                    } catch (Exception e) {
                        out.println(Static.RED_BOLD + e.getMessage() + Static.RESET);
                    }
                })
        }).run();
        this.run();
    }
}