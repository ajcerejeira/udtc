package View;

import Model.Calendar;
import Utils.Static;
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
                            new Option("Show Calendar", () -> {
                                try{
                                    printCalendar();
                                }catch (NumberFormatException e){
                                    out.println(Static.RED_BOLD  + e.getMessage() + Static.RESET);
                                }
                            })
                    }),
            }).run();
    }

    private void printCalendar() throws NumberFormatException{
        new UI(new Runnable[] {
                new Title("Show Calendar", 1),

                new Input("Year", m -> this.calendar.setYear(Integer.parseInt(m))),
                new Input("Month", d -> this.calendar.setMonth(Integer.parseInt(d)))
        }).run();
        out.println(this.calendar.buildCalendar());
    }
}
