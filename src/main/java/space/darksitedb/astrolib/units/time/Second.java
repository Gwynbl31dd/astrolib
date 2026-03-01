package space.darksitedb.astrolib.units.time;

import space.darksitedb.astrolib.units.Unit;
import space.darksitedb.astrolib.units.angle.Degree;

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

    @Override
    public Day toDays() {
        return toHours().toDays();
    }

    @Override
    public Second toSeconds() {
        return this;
    }

    @Override
    public Minute toMinutes() {
        return toHours().toMinutes();
    }

}
