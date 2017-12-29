package Model;

public interface ICalendar {
    int day();
    boolean isLeapYear();
    String buildCalendar();
    void setYear(int year);
    void setMonth(int month);
}
