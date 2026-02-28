package space.darksitedb.astrolib.time;

import space.darksitedb.astrolib.units.Degree;
import space.darksitedb.astrolib.units.Dms;
import space.darksitedb.astrolib.units.Hour;
import space.darksitedb.astrolib.units.Minute;
import space.darksitedb.astrolib.units.Second;

/**
 * The Date is represented as LCT (Local Civil Time)
 * 
 * LCT is a time standard that reflects the local time of a specific location on Earth, taking into account the time zone and any applicable daylight saving time adjustments.
 * It is used for everyday activities and civil purposes, such as scheduling events, coordinating transportation, and conducting business operations within a particular region.
 * LCT can vary from one location to another based on their respective time zones and daylight saving time rules, making it essential for accurate timekeeping in local contexts.
 */
public class LCT implements Date {

    private final java.time.LocalDate value;
    private final Hour hour;
    private final Minute minute;
    private final Second second;
    private int timeZoneOffset; // Time zone offset in hours from UTC

    public LCT(Year year, Month month, Day day, Hour hour, Minute minute, Second second, int timeZoneOffset) {

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
        this.timeZoneOffset = timeZoneOffset;
    }

    public static int getTimeZoneOffsetFromLongitude(Degree longitude) {
        return (int) Math.round(longitude.getValue() / 15.0);
    }

    public static int getTimeZoneOffsetFromLongitude(Dms longitude) {
        return getTimeZoneOffsetFromLongitude(longitude.toDegrees());
    }

}
