package space.darksitedb.astrolib.units;

public class Dms implements Angular {

    private final Degree degrees;
    private final ArcMinute minutes;
    private final ArcSecond seconds;
    private final boolean isNegative;

    public Dms(Degree degrees, ArcMinute minutes, ArcSecond seconds) {
        this.degrees = degrees;
        this.minutes = minutes;
        this.seconds = seconds;
        this.isNegative = degrees.getValue() < 0;
    }

    public Dms(Degree degrees, ArcMinute minutes, ArcSecond seconds, boolean isNegative) {
        this.degrees = degrees;
        this.minutes = minutes;
        this.seconds = seconds;
        this.isNegative = isNegative;
    }

    public Degree getDegrees() {
        return degrees;
    }

    public ArcMinute getMinutes() {
        return minutes;
    }

    public ArcSecond getSeconds() {
        return seconds;
    }

    public boolean isNegative() {
        return isNegative;
    }

    @Override
    public Radian toRadians() {
        return toDegrees().toRadians();
    }

    @Override
    public Degree toDegrees() {
        double totalDegrees = degrees.getValue() + (minutes.getValue() / 60.0) + (seconds.getValue() / 3600.0);
        return new Degree(totalDegrees);
    }

    @Override
    public Hour toHours() {
        return toDegrees().toHours();
    }

    @Override
    public Dms toDms() {
        return this;
    }

    public String toString() {
        return String.format("%s%d° %d' %.2f\"", isNegative ? "-" : "", (int) Math.abs(degrees.getValue()),(int) minutes.getValue(), seconds.getValue());
    }

}
