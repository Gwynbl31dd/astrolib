package space.darksitedb.astrolib.units.time;

import space.darksitedb.astrolib.units.Unit;
import space.darksitedb.astrolib.units.angle.Degree;

public class Day extends Unit implements Time {

    public Day(double days) {
        super(days);
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

    @Override
    public Second toSeconds() {
        return toHours().toSeconds();
    }

    @Override
    public Minute toMinutes() {
        return toHours().toMinutes();
    }

}
