package space.darksitedb.astrolib.units;

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

}
