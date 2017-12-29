package Model;

public class Calendar implements ICalendar{
    private static final int day=1;
    private int month;
    private int year;

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    @Override
    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    @Override
    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public int day() {
        int y = getYear() - (14 - getMonth()) / 12;
        int x = y + y/4 - y/100 + y/400;
        int m = getMonth() + 12 * ((14 - getMonth()) / 12) - 2;
        return (getDay() + x + (31*m)/12) % 7;
    }

    @Override
    public boolean isLeapYear() {
        if  ((getYear() % 4 == 0) && (getYear() % 100 != 0)) return true;
        if  (getYear() % 400 == 0) return true;
        return false;
    }

    @Override
    public String buildCalendar() {
        StringBuilder sb = new StringBuilder();
        String[] months = {"","January","February","March","April", "May", "June","July","August","September","October","November","December"};
        int[] days = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        if (isLeapYear() && getMonth()==2 ) days[getMonth()] = 29;

        //header
        sb.append(" \t\t\t\t\t\t " + months[getMonth()] +" \t "+ getYear()+"\n");
        sb.append("\t  Sun\t  Mon\t  Tue\t  Wed\t  Thur\t  Fri\t  Sat\n");

        //calculate first day of month
        int d = day();

        //append calendar
        for (int i = 0; i<d; i++) sb.append("\t\t");
        for (int i = 1; i <= days[getMonth()]; i++) {
            sb.append("\t\t"+i);
            if (((i + d)%7 == 0) || (i == days[getMonth()])) sb.append("\n");
        }
        return sb.toString();
    }
}
