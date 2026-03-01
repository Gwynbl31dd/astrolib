package space.darksitedb.astrolib.units.time;

import space.darksitedb.astrolib.units.angle.Degree;

/**
 * Represents a time in Hours, Minutes and Seconds (HMS) format.
 * The HMS format is commonly used in astronomy to represent time.
 * It consists of three components: hours, minutes, and seconds.
 * The hours component can be positive or negative, while the minutes and seconds components are always non-negative.
 */
public class Hms implements Time {

    private final Hour hour;
    private final Minute minute;
    private final Second second;

    public Hms(Hour hour, Minute minute, Second second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public Hms(Hour hour) {
        double totalHours = hour.getValue();
        int h = (int) totalHours;
        double fractionalHours = totalHours - h;
        int m = (int) (fractionalHours * 60);
        double fractionalMinutes = (fractionalHours * 60) - m;
        double s = fractionalMinutes * 60;
        this.hour = new Hour(h);
        this.minute = new Minute(m);
        this.second = new Second(s);
    }

    @Override
    public Degree toDegrees() {
        return toHours().toDegrees();
    }

    @Override
    public Hour toHours() {
        double totalHours = hour.getValue() + minute.getValue() / 60 + second.getValue() / 3600;
        return new Hour(totalHours);
    }

    @Override
    public Hms toHms() {
        return this;
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

    @Override
    public String toString() {
        return String.format("%02d:%02d:%04.1f", (int) hour.getValue(), (int) minute.getValue(), second.getValue());
    }

    @Override
    public Day toDays() {
        return toHours().toDays();
    }

    @Override
    public Second toSeconds() {
        return toHours().toSeconds();
    }

    @Override
    public Minute toMinutes() {
        return toHours().toMinutes();
    }

}
