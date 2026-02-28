package space.darksitedb.astrolib.units;

public class Second extends Unit implements Time {

    public Second(double value) {
        super(value);
        if (value < 0) {
            throw new IllegalArgumentException("Second value cannot be negative");
        }
    }

    @Override
    public Degree toDegrees() {
        return toDecimalHours().toDegrees();
    }

    @Override
    public Hour toDecimalHours() {
        return new Hour(value / 3600);
    }

    @Override
    public Hms toHms() {
        return toDecimalHours().toHms();
    }

}
