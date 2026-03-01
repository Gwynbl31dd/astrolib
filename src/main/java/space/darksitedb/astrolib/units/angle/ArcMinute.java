package space.darksitedb.astrolib.units.angle;

import space.darksitedb.astrolib.units.Unit;
import space.darksitedb.astrolib.units.time.Hour;

public class ArcMinute extends Unit implements Angular {

    public ArcMinute(double value) {
        super(value);
    }

    @Override
    public Radian toRadians() {
        return toDegrees().toRadians();
    }

    @Override
    public Degree toDegrees() {
        return new Degree(value / 60);
    }

    @Override
    public Hour toHours() {
        return toDegrees().toHours();
    }

    @Override
    public Dms toDms() {
        return toDegrees().toDms();
    }

}
