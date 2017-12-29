package View;

import Model.ICalendar;
import Utils.NumParser;
import Utils.Option;
import Utils.TUI;


public class CalendarView  {
    public static void home(ICalendar calendar) {
        TUI.title("Calendar", 1);
        TUI.menu(new Option[] {
                new Option("S", "Show calendar", () -> CalendarView.showCalendar(calendar))
        });
    }

    private static void showCalendar(ICalendar calendar) {
        TUI.title("Calendar", 1);
        TUI.title("Show calendar", 2);

        calendar.setYear(TUI.input("Year", NumParser::parseInt));
        calendar.setMonth(TUI.input("Month", NumParser::parseInt));

        System.out.println(calendar.buildCalendar());
    }
}
