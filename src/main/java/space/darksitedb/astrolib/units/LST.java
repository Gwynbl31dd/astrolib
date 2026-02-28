package space.darksitedb.astrolib.units;

/**
 * Local Sidereal Time (LST) is the right ascension of the local meridian.
 * It is used in astronomy to determine the position of celestial objects in the
 * sky at a given location and time.
 */
public class LST extends Date {

    public LST(Year year, Month month, Day day, Hour hour, Minute minute, Second second) {
        super(year, month, day, hour, minute, second);
    }

    public GST toGST(Degree longitude) {
        // Convert LST to Decimal Hours
        double lstHours = getHour().getValue() + getMinute().getValue() / 60.0 + getSecond().getValue() / 3600.0;
        // Convert Longitude to Decimal Hours (15 degrees per hour)
        double longitudeHours = longitude.getValue() / 15.0;
        double gstHours = lstHours - longitudeHours;

        // Handle day boundary crossings
        java.time.LocalDateTime dateTime = java.time.LocalDateTime.of(
                getYear().getValue(), getMonth().getValue(), getDay().getValue(), 0, 0, 0);

        while (gstHours < 0) {
            gstHours += 24;
            dateTime = dateTime.minusDays(1);
        }
        while (gstHours >= 24) {
            gstHours -= 24;
            dateTime = dateTime.plusDays(1);
        }

        Hms gstHms = new Hms(new Hour(gstHours));

        return new GST(
                new Year(dateTime.getYear()),
                new Month(dateTime.getMonthValue()),
                new Day(dateTime.getDayOfMonth()),
                gstHms.getHour(),
                gstHms.getMinute(),
                gstHms.getSecond());
    }

    public UT toUT(Degree longitude) {
        return toGST(longitude).toUT();
    }

    public LCT toLCT(Degree longitude, int offset) {
        return toUT(longitude).toLCT(offset);
    }

}
