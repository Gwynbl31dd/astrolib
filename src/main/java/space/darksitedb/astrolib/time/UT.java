package space.darksitedb.astrolib.time;

import space.darksitedb.astrolib.units.Hour;
import space.darksitedb.astrolib.units.Minute;
import space.darksitedb.astrolib.units.Second;

/**
 * The Date is represented as UT (Universal Time)
 * 
 * UT is a time standard that reflects the mean solar time at the Prime Meridian (0° longitude) in Greenwich, England.
 * It is used as a basis for civil timekeeping worldwide and serves as a reference for astronomical observations and calculations.
 * UT is closely related to Greenwich Mean Time (GMT) but is more precisely defined and maintained by atomic clocks,
 * ensuring its accuracy and stability over time.
 */
public class UT implements Date {

    private final java.time.LocalDate value;
    private final Hour hour;
    private final Minute minute;
    private final Second second;

    public UT(Year year, Month month, Day day, Hour hour, Minute minute, Second second) {

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

    public UT(Year year, Day day) {
        this(year, new Month(1), day, new Hour(0), new Minute(0), new Second(0));
    }

    public UT(Year year, Month month, Day day) {
        this(year, month, day, new Hour(0), new Minute(0), new Second(0));
    }

    public UT(Year year, Month month, Day day, Hour hour) {
        this(year, month, day, hour, new Minute(0), new Second(0));
    }

    public Year getYear() {
        return new Year(value.getYear());
    }

    public Month getMonth() {
        return new Month(value.getMonthValue());
    }

    public Day getDay() {
        return new Day(value.getDayOfMonth());
    }

    public Hour getHour() {
        return hour;
    }

    public Minute getMinute() {
        return minute;
    }

    public Second getSecond() {
        return second;
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
        int year = getYear().getValue();
        int month = getMonth().getValue();
        int day = getDay().getValue();

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

    public LCT toLCT(int timeZoneOffset) {
        // Convert UT to LCT by adding the time zone offset
        java.time.LocalDateTime utcDateTime = java.time.LocalDateTime.of(value.getYear(), value.getMonthValue(), value.getDayOfMonth(), (int) hour.getValue(), (int) minute.getValue(), (int) second.getValue());
        java.time.LocalDateTime localDateTime = utcDateTime.plusHours(timeZoneOffset);
        return new LCT(new Year(localDateTime.getYear()), new Month(localDateTime.getMonthValue()), new Day(localDateTime.getDayOfMonth()), new Hour(localDateTime.getHour()), new Minute(localDateTime.getMinute()), new Second(localDateTime.getSecond()), timeZoneOffset);
    }

}
