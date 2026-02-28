package space.darksitedb.astrolib.units;

import java.time.LocalDateTime;

/**
 * The Date is represented as UT (Universal Time)
 * 
 * UT is a time standard that reflects the mean solar time at the Prime Meridian
 * (0° longitude) in Greenwich, England.
 * It is used as a basis for civil timekeeping worldwide and serves as a
 * reference for astronomical observations and calculations.
 * UT is closely related to Greenwich Mean Time (GMT) but is more precisely
 * defined and maintained by atomic clocks,
 * ensuring its accuracy and stability over time.
 */
public class UT extends Date {

    public UT(Year year, Month month, Day day, Hour hour, Minute minute, Second second) {
        super(year, month, day, hour, minute, second);
    }

    public UT(Year year, Day day) {
        this(year, new Month(1), day, new Hour(0), new Minute(0), new Second(0));
    }

    public UT(Year year, Month month, Day day) {
        this(year, month, day, new Hour(0), new Minute(0), new Second(0));
    }

    /**
     * Converts the date to Julian Day Number.
     * 
     * Julian Day Number is a continuous count of days since the beginning of the
     * Julian Period used primarily by astronomers.
     * It is defined as the number of days that have elapsed since January 1, 4713
     * BCE (Julian calendar) at 12:00 noon UT.
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

        int a = year / 100;
        int b = 2 - a + (a / 4);

        double jd = Math.floor(365.25 * (year + 4716))
                + Math.floor(30.6001 * (month + 1))
                + day + b - 1524.5;

        double dayFraction = (getHour().getValue() 
                + getMinute().getValue() / 60.0 
                + getSecond().getValue() / 3600.0)
                / 24.0;

        jd += dayFraction;

        return new JulianDate(jd);
    }

    public String getDayOfWeek() {
        java.time.DayOfWeek dayOfWeek = getDayOfTheWeek();
        return dayOfWeek.toString().substring(0, 1) + dayOfWeek.toString().substring(1).toLowerCase();
    }

    public LCT toLCT(int timeZoneOffset) {
        // Convert UT to LCT by adding the time zone offset
        LocalDateTime utcDateTime = LocalDateTime.of(getYear().getValue(), getMonth().getValue(),
                getDay().getValue(), (int) getHour().getValue(), (int) getMinute().getValue(),
                (int) getSecond().getValue());
        LocalDateTime localDateTime = utcDateTime.plusHours(timeZoneOffset);
        return new LCT(new Year(localDateTime.getYear()), new Month(localDateTime.getMonthValue()),
                new Day(localDateTime.getDayOfMonth()), new Hour(localDateTime.getHour()),
                new Minute(localDateTime.getMinute()), new Second(localDateTime.getSecond()), timeZoneOffset);
    }

    public GST toGST() {
        // Convert the Date to Julian Day Number at 0h UT (non-fractional day)
        JulianDate jd = new UT(getYear(), getMonth(), getDay()).toJulianDayNumber();
        // Calculate the Julian Day Number at 0h UT for January 0.0 of the given year
        // This is equal to previous year's December 31st at 0h UT
        JulianDate jd0 = new UT(new Year(getYear().getValue() - 1), new Month(12), new Day(31)).toJulianDayNumber();
        int days = (int) Math.floor(jd.getValue() - jd0.getValue());
        double t = (jd0.getValue() - 2415020.0) / 36525.0;
        double r = 6.6460656 + 2400.051262 * t + 0.00002581 * (t * t);
        double b = 24 - r + 24 * (getYear().getValue() - 1900);
        double t0 = 0.0657098 * days - b;
        double utHours = getHour().getValue() + getMinute().getValue() / 60.0 + getSecond().getValue() / 3600.0;
        double gst = t0 + 1.002737909 * utHours;

        DateTimeCrossing result = getDayBondaryCrossing(gst);

        return new GST(
                new Year(result.dateTime().getYear()),
                new Month(result.dateTime().getMonthValue()),
                new Day(result.dateTime().getDayOfMonth()),
                result.time().getHour(),
                result.time().getMinute(),
                result.time().getSecond());
    }
}
