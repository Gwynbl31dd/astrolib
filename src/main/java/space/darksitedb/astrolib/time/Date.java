package space.darksitedb.astrolib.time;

import space.darksitedb.astrolib.units.Hour;
import space.darksitedb.astrolib.units.Minute;
import space.darksitedb.astrolib.units.Second;
/**
 * The Date is represented as UT (Universal Time)
 */
public class Date {

    private final java.time.LocalDate value;
    private final Hour hour;
    private final Minute minute;
    private final Second second;

    public Date(Year year, Month month, Day day, Hour hour, Minute minute, Second second) {

        if(day.getValue() > 31) {
            // If day greater than a month, interpret as day-of-year and calculate the actual date
            java.time.LocalDate baseDate = java.time.LocalDate.of(year.getValue(), 1, 1);
            this.value = baseDate.plusDays(day.getValue() - 1);
        } else {
            this.value = java.time.LocalDate.of(year.getValue(), month.getValue(), day.getValue());
        }
        
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public Date(Year year, Day day) {
        this(year, new Month(1), day, new Hour(0), new Minute(0), new Second(0));
    }

    public Date(Year year, Month month, Day day) {
        this(year, month, day, new Hour(0), new Minute(0), new Second(0));
    }

    public Date(Year year, Month month, Day day, Hour hour) {
        this(year, month, day, hour, new Minute(0), new Second(0));
    }

    public int getYear() {
        return value.getYear();
    }

    public int getMonth() {
        return value.getMonthValue();
    }

    public int getDay() {
        return value.getDayOfMonth();
    }

    /**
     * Converts the date to Julian Day Number.
     * 
     * Julian Day Number is a continuous count of days since the beginning of the Julian Period used primarily by astronomers. 
     * It is defined as the number of days that have elapsed since January 1, 4713 BCE (Julian calendar) at 12:00 noon UT.
     * 
     * @return the Julian Day Number corresponding to this date
     */
    public JulianDate toJulianDayNumber() {
        int year = getYear();
        int month = getMonth();
        int day = getDay();

        if (month <= 2) {
            year--;
            month += 12;
        }

        int A = year / 100;
        int B = 2 - A + (A / 4);

        double jd = Math.floor(365.25 * (year + 4716))
                + Math.floor(30.6001 * (month + 1))
                + day + B - 1524.5;

        double dayFraction = (hour.getValue() + minute.getValue() / 60.0 + second.getValue() / 3600.0) / 24.0;
        jd += dayFraction;

        return new JulianDate(jd);
    }

    public String getDayOfWeek() {
        java.time.DayOfWeek dayOfWeek = value.getDayOfWeek();
        return dayOfWeek.toString().substring(0, 1) + dayOfWeek.toString().substring(1).toLowerCase();
    }

    public Integer getDayOfYear() {
        return value.getDayOfYear();
    }

}
