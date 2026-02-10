package space.darksitedb.astrolib.units;

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

    @Override
    public String toString() {
        return String.format("%02d:%02d:%04.1f", (int) hour.getValue(), (int) minute.getValue(), second.getValue());
    }

}
