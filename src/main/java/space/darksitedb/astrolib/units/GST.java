package space.darksitedb.astrolib.units;

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

        DateTimeCrossing result = getDayBondaryCrossing(utHours);

        UT ut = new UT(
                new Year(result.dateTime().getYear()),
                new Month(result.dateTime().getMonthValue()),
                new Day(result.dateTime().getDayOfMonth()),
                result.time().getHour(),
                result.time().getMinute(),
                result.time().getSecond());

        return ut;
    }

    public LST toLST(Degree longitude) {

        Hms gstHms = new Hms(getHour(),getMinute(),getSecond());
        // Convert GST to Decimal Hours
        double gstHours = gstHms.toDecimalHours().getValue();

        double offset = longitude.getValue() / 15.0; // Convert longitude to hours
        // Adjust GST for longitude offset to get LST
        double lstHours = gstHours + offset;

        DateTimeCrossing result = getDayBondaryCrossing(lstHours);

        Hms lstHms = result.time();

        return new LST(
                new Year(result.dateTime().getYear()),
                new Month(result.dateTime().getMonthValue()),
                new Day(result.dateTime().getDayOfMonth()),
                lstHms.getHour(),
                lstHms.getMinute(),
                lstHms.getSecond());
    }
}
