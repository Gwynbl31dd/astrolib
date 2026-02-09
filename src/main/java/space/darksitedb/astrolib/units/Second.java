package space.darksitedb.astrolib.units;

public class Second extends Unit implements Time {

    public Second(double value) {
        super(value);
    }

    @Override
    public Degree toDegrees() {
        return toHours().toDegrees();
    }

    @Override
    public Hour toHours() {
        return new Hour(value / 3600);
    }

    @Override
    public Hms toHms() {
        return toHours().toHms();
    }

}
