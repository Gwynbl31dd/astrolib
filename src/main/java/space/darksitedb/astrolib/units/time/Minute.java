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
        return this;
    }

}
