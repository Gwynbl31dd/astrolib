package space.darksitedb.astrolib.units;

public class Hour extends Unit implements Time {

    public Hour(double value) {
        super(value);
        if (value < 0) {
            throw new IllegalArgumentException("Hour value cannot be negative");
        }
    }

    @Override
    public Degree toDegrees() {
        // 24h = 360°
        return new Degree(value / 24 * 360);
    }

    @Override
    public Hour toHours() {
        return this;
    }

    @Override
    public Hms toHms() {
        double totalSeconds = value * 3600;
        int hours = (int) (totalSeconds / 3600);
        int minutes = (int) ((totalSeconds % 3600) / 60);
        float seconds = (float) (totalSeconds % 60);
        return new Hms(new Hour(hours), new Minute(minutes), new Second(seconds));
    }

}
