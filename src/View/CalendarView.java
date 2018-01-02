package View;

import Model.ICalendar;
import Utils.Option;
import Utils.Parsers;
import Utils.UI;


public class CalendarView  {
    public static void home(ICalendar calendar) {
        UI.title("Calendar");
        UI.menu(new Option("Show calendar", () -> CalendarView.showCalendar(calendar)));
    }

    private static void showCalendar(ICalendar calendar) {
        UI.title("Calendar");
        UI.subtitle("Show calendar");

        calendar.setYear(UI.input("Year", Parsers::parseInt));
        calendar.setMonth(UI.input("Month", Parsers::parseInt));

        System.out.println(calendar.buildCalendar());
    }
}
