package space.darksitedb.astrolib.time;

import space.darksitedb.astrolib.units.Hour;
import space.darksitedb.astrolib.units.Minute;
import space.darksitedb.astrolib.units.Second;

/**
 * Local Sidereal Time (LST) is the right ascension of the local meridian.
 * It is used in astronomy to determine the position of celestial objects in the sky at a given location and time.
 */
public class LST implements Date {

    private final java.time.LocalDate value;
    private final Hour hour;
    private final Minute minute;
    private final Second second;

    public LST(Year year, Month month, Day day, Hour hour, Minute minute, Second second) {
        this.value = java.time.LocalDate.of(year.getValue(), month.getValue(), day.getValue());
        this.hour = hour;
        this.minute = minute;
        this.second = second;
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
}
