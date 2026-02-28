package space.darksitedb.astrolib.time;

import space.darksitedb.astrolib.units.Degree;
import space.darksitedb.astrolib.units.Hms;
import space.darksitedb.astrolib.units.Hour;
import space.darksitedb.astrolib.units.Minute;
import space.darksitedb.astrolib.units.Second;

/**
 * Represents Greenwich Sidereal Time (GST).
 * 
 * GST is a time standard used in astronomy to measure the rotation of the Earth relative to distant celestial objects.
 * It is based on the position of the vernal equinox and is used to calculate the positions of stars and other celestial objects in the sky at a given time.
 * GST is closely related to Universal Time (UT) but accounts for the Earth's rotation and is used for astronomical observations and calculations
 */
public class GST implements Date {
    
    private final java.time.LocalDate value;
    private final Hour hour;
    private final Minute minute;
    private final Second second;

    public GST(Year year, Month month, Day day, Hour hour, Minute minute, Second second) {
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

    public UT toUT() {
        // Give the given date at 0h, convert to Julian Day Number
        UT utAtZeroHour = new UT(getYear(), getMonth(), getDay());
        JulianDate jd = utAtZeroHour.toJulianDayNumber();
        System.out.println("Julian Day Number: " + jd.getValue());

        // Calculate the Julian Day Number at 0h UT for January 0.0 of the given year
        // This is equal to previous year's December 31st at 0h UT
        JulianDate jd0 = new UT(new Year(getYear().getValue()-1), new Month(12), new Day(31)).toJulianDayNumber();
        System.out.println("Julian Day Number at 0h UT for January 0.0: " + jd0.getValue());

        int days = (int) Math.floor(jd.getValue() - jd0.getValue());
        System.out.println("Elapsed days: " + days);

        double t = (jd0.getValue() - 2415020.0) / 36525.0;
        System.out.println("Centuries since 1900: " + t);

        double r = 6.6460656 + 2400.051262 * t + 0.00002581 * (t * t);
        double b = 24 - r + 24 * (getYear().getValue() - 1900);
        double t0 = 0.0657098 * days - b;
        System.out.println("Greenwich Mean Sidereal Time at 0h UT in hours: " + t0);

        if(t0 < 0) {
            t0 += 24;
            System.out.println("Greenwich Mean Sidereal Time at 0h UT was negative, normalized to: " + t0);
        } else if(t0 >= 24) {
            t0 -= 24;
            System.out.println("Greenwich Mean Sidereal Time at 0h UT was >= 24, normalized to: " + t0);
        }

         double gstHours = hour.getValue() + minute.getValue() / 60.0 + second.getValue() / 3600.0;
         System.out.println("GST in hours: " + gstHours);
         
         // If GST is less than t0, we've wrapped around midnight, so add 24
         if (gstHours < t0) {
             gstHours += 24;
             System.out.println("GST wrapped around midnight, adjusted to: " + gstHours);
         }
         
         // Convert GST to UT: utHours = (gstHours - t0) / 1.002737909
         double utHours = (gstHours - t0) / 1.002737909;
         System.out.println("UT in hours: " + utHours);

         // Handle day boundary crossings (should rarely happen now)
         java.time.LocalDateTime dateTime = java.time.LocalDateTime.of(
            value.getYear(), value.getMonthValue(), value.getDayOfMonth(), 0, 0, 0);

         while(utHours < 0) {
            utHours += 24;
            dateTime = dateTime.minusDays(1);
            System.out.println("UT was negative, normalized to: " + utHours + ", moved to previous day");
        }
        while(utHours >= 24) {
            utHours -= 24;
            dateTime = dateTime.plusDays(1);
            System.out.println("UT was >= 24, normalized to: " + utHours + ", moved to next day");
        }

        Hms utHms = new Hms(new Hour(utHours));
        System.out.println("UT (HMS): " + utHms);

        UT ut = new UT(
            new Year(dateTime.getYear()),
            new Month(dateTime.getMonthValue()),
            new Day(dateTime.getDayOfMonth()),
            utHms.getHour(),
            utHms.getMinute(),
            utHms.getSecond()
        );

        return ut;
    }

    public LST toLST(Degree longitude) {
        // Convert GST to Decimal Hours
        double gstHours = hour.getValue() + minute.getValue() / 60.0 + second.getValue() / 3600.0;
        System.out.println("GST in hours: " + gstHours);

        double offset = longitude.getValue() / 15.0; // Convert longitude to hours
        // Adjust GST for longitude offset to get LST
        double lstHours = gstHours + offset;
        System.out.println("LST in hours before normalization: " + lstHours);

        // Handle day boundary crossings
        java.time.LocalDateTime dateTime = java.time.LocalDateTime.of(
            value.getYear(), value.getMonthValue(), value.getDayOfMonth(), 0, 0, 0);

        while(lstHours < 0) {
            lstHours += 24;
            dateTime = dateTime.minusDays(1);
            System.out.println("LST was negative, normalized to: " + lstHours + ", moved to previous day");
        }
        while(lstHours >= 24) {
            lstHours -= 24;
            dateTime = dateTime.plusDays(1);
            System.out.println("LST was >= 24, normalized to: " + lstHours + ", moved to next day");
        }

        Hms lstHms = new Hms(new Hour(lstHours));
        System.out.println("LST (HMS): " + lstHms);

        return new LST(
            new Year(dateTime.getYear()),
            new Month(dateTime.getMonthValue()),
            new Day(dateTime.getDayOfMonth()),
            lstHms.getHour(),
            lstHms.getMinute(),
            lstHms.getSecond()
        );
    }
}
