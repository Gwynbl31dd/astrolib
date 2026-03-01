package space.darksitedb.astrolib.units.time;

import space.darksitedb.astrolib.units.angle.Degree;

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
        Hms lstHms = new Hms(getHour(), getMinute(), getSecond());
        // Convert LST to Decimal Hours
        double lstHours = lstHms.toHours().getValue();
        // Convert Longitude to Decimal Hours (15 degrees per hour)
        double longitudeHours = longitude.getValue() / 15.0;
        double gstHours = lstHours - longitudeHours;

        DateTimeCrossing result = getDayBondaryCrossing(gstHours);

        return new GST(
                new Year(result.dateTime().getYear()),
                new Month(result.dateTime().getMonthValue()),
                new Day(result.dateTime().getDayOfMonth()),
                result.time().getHour(),
                result.time().getMinute(),
                result.time().getSecond());
    }

    public UT toUT(Degree longitude) {
        return toGST(longitude).toUT();
    }

}
