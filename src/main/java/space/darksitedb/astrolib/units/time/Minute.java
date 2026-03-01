package space.darksitedb.astrolib.units.time;

import space.darksitedb.astrolib.units.Unit;
import space.darksitedb.astrolib.units.angle.Degree;

public class Minute extends Unit implements Time {

    public Minute(double value) {
        super(value);
        if (value < 0) {
            throw new IllegalArgumentException("Minute value cannot be negative");
        }
    }

    @Override
    public Degree toDegrees() {
        return toDecimalHours().toDegrees();
    }

    @Override
    public Hour toDecimalHours() {
        return new Hour(value / 60);
    }

    @Override
    public Hms toHms() {
        return toDecimalHours().toHms();
    }

}
