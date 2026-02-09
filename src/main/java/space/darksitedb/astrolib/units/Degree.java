package space.darksitedb.astrolib.units;

public class Degree extends Unit implements Angular {

    public Degree(double value) {
        super(value);
    }

    @Override
    public Radian toRadians() {
        return new Radian(value * Math.PI / 180);
    }

    @Override
    public Degree toDegrees() {
        return this;
    }

    @Override
    public Hour toHours() {
        // 24h = 360°
        return new Hour(value / 360 * 24);
        
    }

}
