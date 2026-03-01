package space.darksitedb.astrolib.units.angle;

import space.darksitedb.astrolib.units.Unit;
import space.darksitedb.astrolib.units.time.Hour;

public class Radian extends Unit implements Angular {

    public Radian(double value) {
        super(value);
    }

    @Override
    public Radian toRadians() {
        return this;
    }

    @Override
    public Degree toDegrees() {
        return new Degree(value * 180 / Math.PI);
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
