package space.darksitedb.astrolib.time;

import space.darksitedb.astrolib.units.Degree;
import space.darksitedb.astrolib.units.Hms;
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

    public GST toGST(Degree longitude) {
        
        // Convert LST to Decimal Hours
        double lstHours = hour.getValue() + minute.getValue() / 60.0 + second.getValue() / 3600.0;
        System.out.println("LST in hours: " + lstHours);
        
        // Convert Longitude to Decimal Hours (15 degrees per hour)
        double longitudeHours = longitude.getValue() / 15.0;
        System.out.println("Longitude offset in hours: " + longitudeHours);
        
        double gstHours = lstHours - longitudeHours;
        System.out.println("GST in hours before normalization: " + gstHours);

        // Handle day boundary crossings
        java.time.LocalDateTime dateTime = java.time.LocalDateTime.of(
            value.getYear(), value.getMonthValue(), value.getDayOfMonth(), 0, 0, 0);

        while(gstHours < 0) {
            gstHours += 24;
            dateTime = dateTime.minusDays(1);
            System.out.println("GST was negative, normalized to: " + gstHours + ", moved to previous day");
        }
        while(gstHours >= 24) {
            gstHours -= 24;
            dateTime = dateTime.plusDays(1);
            System.out.println("GST was >= 24, normalized to: " + gstHours + ", moved to next day");
        }

        Hms gstHms = new Hms(new Hour(gstHours));
        System.out.println("GST (HMS): " + gstHms);

        return new GST(
            new Year(dateTime.getYear()),
            new Month(dateTime.getMonthValue()),
            new Day(dateTime.getDayOfMonth()),
            gstHms.getHour(),
            gstHms.getMinute(),
            gstHms.getSecond()
        );
    }

    public UT toUT(Degree longitude) {
        return toGST(longitude).toUT();
    }

    public LCT toLCT(Degree longitude, int offset) {
        return toUT(longitude).toLCT(offset);
    }
}
