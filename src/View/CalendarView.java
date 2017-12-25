package View;

import Model.Calendar;
import Utils.Static;
import Utils.UI.*;

import java.util.concurrent.atomic.AtomicBoolean;

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
                            }),
                            new Option("Back", () -> out.println())
                    }),
            }).run();
    }

    private void printCalendar() throws NumberFormatException{
        AtomicBoolean flag= new AtomicBoolean(true);

        new UI(new Runnable[] {
                new Title("Calendar", 1),
                new Title("Show Calendar", 2),

                new Input("Year\n>>> ", m -> {
                    try{
                        this.calendar.setYear(Integer.parseInt(m));
                    }catch(Exception e) {
                        flag.set(false);
                        out.println(Static.RED_BOLD  + e.getMessage() + Static.RESET);
                    }
                }),
                new Input("Month\n>>> ", d -> {
                    try{
                        this.calendar.setMonth(Integer.parseInt(d));
                    }catch(Exception e) {
                        flag.set(false);
                        out.println(Static.RED_BOLD  + e.getMessage() + Static.RESET);
                    }
                })
        }).run();
        if(flag.get()==true)
            out.println(this.calendar.buildCalendar());
        this.run();
    }
}
