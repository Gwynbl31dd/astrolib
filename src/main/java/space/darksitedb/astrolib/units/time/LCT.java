package space.darksitedb.astrolib.units.time;

import java.time.LocalDateTime;

import space.darksitedb.astrolib.units.angle.Degree;
import space.darksitedb.astrolib.units.angle.Dms;

/**
 * The Date is represented as LCT (Local Civil Time)
 * 
 * LCT is a time standard that reflects the local time of a specific location on
 * Earth, taking into account the time zone and any applicable daylight saving
 * time adjustments.
 * It is used for everyday activities and civil purposes, such as scheduling
 * events, coordinating transportation, and conducting business operations
 * within a particular region.
 * LCT can vary from one location to another based on their respective time
 * zones and daylight saving time rules, making it essential for accurate
 * timekeeping in local contexts.
 */
public class LCT extends Date {

    private int timeZoneOffset; // Time zone offset in hours from UTC

    public LCT(Year year, Month month, Day day, Hour hour, Minute minute, Second second, int timeZoneOffset) {
        super(year, month, day, hour, minute, second);
        this.timeZoneOffset = timeZoneOffset;
    }

    public static int getTimeZoneOffsetFromLongitude(Degree longitude) {
        return (int) Math.round(longitude.getValue() / 15.0);
    }

    public static int getTimeZoneOffsetFromLongitude(Dms longitude) {
        return getTimeZoneOffsetFromLongitude(longitude.toDegrees());
    }

    public UT toUT() {
        // Convert LCT to UT by subtracting the time zone offset
        LocalDateTime localDateTime = LocalDateTime.of(getYear().getValue(), getMonth().getValue(),
                (int) getDay().getValue(), (int) getHour().getValue(), (int) getMinute().getValue(),
                (int) getSecond().getValue());

        LocalDateTime utcDateTime = localDateTime.minusHours(timeZoneOffset);
        return new UT(new Year(utcDateTime.getYear()), new Month(utcDateTime.getMonthValue()),
                new Day(utcDateTime.getDayOfMonth()), new Hour(utcDateTime.getHour()),
                new Minute(utcDateTime.getMinute()), new Second(utcDateTime.getSecond()));
    }

    public GST toGST() {
        return toUT().toGST();
    }

    public LST toLST(Degree longitude) {
        return toGST().toLST(longitude);
    }

}
