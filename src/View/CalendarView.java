package View;

import Model.Calendar;
import Utils.NumParser;
import Utils.UI.*;

import static java.lang.System.out;

public class CalendarView implements Runnable {
    private Calendar calendar;

    public CalendarView(Calendar calendar) {
        this.calendar = calendar;
    }


    @Override
    public void run() {
        new UI(new Runnable[] {
                    new Title("Calendar", 1),
                    new Menu(new Option[] {
                            new Option("Show Calendar", this::printCalendar)
                    }),
            }).run();
    }

    private void printCalendar() {
        new UI(new Runnable[] {
                new Title("Show Calendar", 1),

                new Input<>("Year", this.calendar::setYear, NumParser::parseInt),
                new Input<>("Month", this.calendar::setMonth, NumParser::parseInt)
        }).run();
        out.println(this.calendar.buildCalendar());
    }
}
