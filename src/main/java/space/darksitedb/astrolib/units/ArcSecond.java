package space.darksitedb.astrolib.units;

public class ArcSecond extends Unit implements Angular {

    public ArcSecond(double value) {
        super(value);
    }

    @Override
    public Radian toRadians() {
        return toDegrees().toRadians();
    }

    @Override
    public Degree toDegrees() {
        return new Degree(value / 3600);
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
