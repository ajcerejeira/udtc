package View;

import Model.Clock;
import Utils.UI.IndexedTable;
import Utils.UI.Title;
import Utils.UI.UI;

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
                        new Option("B", "Back", () -> System.out.print("")),
                }),
        }).run();
    }

    private void showTime() {
        new UI(new Runnable[] {
                new Title("Current time at different countries", 1),
                new IndexedTable(this.clock.timeAt().length-1,this.clock.timeAt())
        }).run();
        this.run();
    }
}
