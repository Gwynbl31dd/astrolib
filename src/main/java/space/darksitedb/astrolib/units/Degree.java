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

    @Override
    public Dms toDms() {
        int sign = value < 0 ? -1 : 1;
        double absValue = Math.abs(value);
        int degrees = (int) absValue;

        double fractional = absValue - degrees;
        int minutes = (int) (fractional * 60);
        double seconds = (fractional * 60 - minutes) * 60;

        // Force the sign to be negative if the degrees part is zero but the original
        // value was negative
        return new Dms(sign * degrees, minutes, seconds, sign < 0);
    }

}
