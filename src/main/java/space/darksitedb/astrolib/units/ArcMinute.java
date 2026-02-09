package space.darksitedb.astrolib.units;

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
