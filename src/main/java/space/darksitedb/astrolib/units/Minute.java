package space.darksitedb.astrolib.units;

public class Minute extends Unit implements Time {

    public Minute(double value) {
        super(value);
        if(value < 0) {
            throw new IllegalArgumentException("Minute value cannot be negative");
        }
    }

    @Override
    public Degree toDegrees() {
       return toHours().toDegrees();
    }

    @Override
    public Hour toHours() {
        return new Hour(value / 60);
    }

    @Override
    public Hms toHms() {
        return toHours().toHms();
    }

}
