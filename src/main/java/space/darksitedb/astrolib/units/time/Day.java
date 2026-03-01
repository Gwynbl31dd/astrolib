package space.darksitedb.astrolib.units.time;

import space.darksitedb.astrolib.units.Unit;
import space.darksitedb.astrolib.units.angle.Degree;

public class Day extends Unit implements Time {

    public Day(double days) {
        super(days);
        if (days < 0) {
            throw new IllegalArgumentException("Time cannot be negative");
        }
    }

    @Override
    public Degree toDegrees() {
        return toHours().toDegrees();
    }

    @Override
    public Hour toHours() {
        return new Hour(value * 24);
    }

    @Override
    public Hms toHms() {
        return toHours().toHms();
    }

    @Override
    public Day toDays() {
        return this;
    }
}
