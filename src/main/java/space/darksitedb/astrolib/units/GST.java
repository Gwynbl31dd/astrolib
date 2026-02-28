package space.darksitedb.astrolib.units;

import java.time.LocalDateTime;

/**
 * Represents Greenwich Sidereal Time (GST).
 * 
 * GST is a time standard used in astronomy to measure the rotation of the Earth
 * relative to distant celestial objects.
 * It is based on the position of the vernal equinox and is used to calculate
 * the positions of stars and other celestial objects in the sky at a given
 * time.
 * GST is closely related to Universal Time (UT) but accounts for the Earth's
 * rotation and is used for astronomical observations and calculations
 */
public class GST extends Date {

    public GST(Year year, Month month, Day day, Hour hour, Minute minute, Second second) {
        super(year, month, day, hour, minute, second);
    }

    public UT toUT() {
        // Give the given date at 0h, convert to Julian Day Number
        UT utAtZeroHour = new UT(getYear(), getMonth(), getDay());
        JulianDate jd = utAtZeroHour.toJulianDayNumber();

        // Calculate the Julian Day Number at 0h UT for January 0.0 of the given year
        // This is equal to previous year's December 31st at 0h UT
        JulianDate jd0 = new UT(new Year(getYear().getValue() - 1),
                new Month(12), new Day(31)).toJulianDayNumber();

        int days = (int) Math.floor(jd.getValue() - jd0.getValue());

        double t = (jd0.getValue() - 2415020.0) / 36525.0;

        double r = 6.6460656 + 2400.051262 * t + 0.00002581 * (t * t);
        double b = 24 - r + 24 * (getYear().getValue() - 1900);
        double t0 = 0.0657098 * days - b;

        if (t0 < 0) {
            t0 += 24;
        } else if (t0 >= 24) {
            t0 -= 24;
        }

        double gstHours = getHour().getValue() + getMinute().getValue() / 60.0 + getSecond().getValue() / 3600.0;

        // If GST is less than t0, we've wrapped around midnight, so add 24
        if (gstHours < t0) {
            gstHours += 24;
        }

        // Convert GST to UT: utHours = (gstHours - t0) / 1.002737909
        double utHours = (gstHours - t0) / 1.002737909;
        // Handle day boundary crossings (should rarely happen now)
        LocalDateTime dateTime = LocalDateTime.of(
                getYear().getValue(), getMonth().getValue(), getDay().getValue(), 0, 0, 0);

        while (utHours < 0) {
            utHours += 24;
            dateTime = dateTime.minusDays(1);
        }
        while (utHours >= 24) {
            utHours -= 24;
            dateTime = dateTime.plusDays(1);
        }

        Hms utHms = new Hms(new Hour(utHours));

        UT ut = new UT(
                new Year(dateTime.getYear()),
                new Month(dateTime.getMonthValue()),
                new Day(dateTime.getDayOfMonth()),
                utHms.getHour(),
                utHms.getMinute(),
                utHms.getSecond());

        return ut;
    }

    public LST toLST(Degree longitude) {
        // Convert GST to Decimal Hours
        double gstHours = getHour().getValue() 
            + getMinute().getValue() / 60.0 + getSecond().getValue() / 3600.0;

        double offset = longitude.getValue() / 15.0; // Convert longitude to hours
        // Adjust GST for longitude offset to get LST
        double lstHours = gstHours + offset;

        // Handle day boundary crossings
        LocalDateTime dateTime = LocalDateTime.of(
                getYear().getValue(), getMonth().getValue(), getDay().getValue(), 0, 0, 0);

        while (lstHours < 0) {
            lstHours += 24;
            dateTime = dateTime.minusDays(1);
        }
        while (lstHours >= 24) {
            lstHours -= 24;
            dateTime = dateTime.plusDays(1);
        }

        Hms lstHms = new Hms(new Hour(lstHours));

        return new LST(
                new Year(dateTime.getYear()),
                new Month(dateTime.getMonthValue()),
                new Day(dateTime.getDayOfMonth()),
                lstHms.getHour(),
                lstHms.getMinute(),
                lstHms.getSecond());
    }
}
