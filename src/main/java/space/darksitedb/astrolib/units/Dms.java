package space.darksitedb.astrolib.units;

public class Dms implements Angular {

    private final int degrees;
    private final int minutes;
    private final double seconds;
    private final boolean isNegative;

    public Dms(int degrees, int minutes, double seconds) {
        this.degrees = degrees;
        this.minutes = minutes;
        this.seconds = seconds;
        this.isNegative = degrees < 0;
    }

    public int getDegrees() {
        return degrees;
    }

    public int getMinutes() {
        return minutes;
    }

    public double getSeconds() {
        return seconds;
    }

    public boolean isNegative() {
        return isNegative;
    }

    public Dms(int degrees, int minutes, double seconds, boolean isNegative) {
        this.degrees = degrees;
        this.minutes = minutes;
        this.seconds = seconds;
        this.isNegative = isNegative;
    }

    @Override
    public Radian toRadians() {
        return toDegrees().toRadians();
    }

    @Override
    public Degree toDegrees() {
        double totalDegrees = degrees + (minutes / 60.0) + (seconds / 3600.0);
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
        return String.format("%s%d° %d' %.2f\"", isNegative ? "-" : "", Math.abs(degrees), minutes, seconds);
    }

}
