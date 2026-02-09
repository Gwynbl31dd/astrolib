package space.darksitedb.astrolib.units;

public class Hms implements Time {

    private final Hour hour;
    private final Minute minute;
    private final Second second;

    public Hms(Hour hour, Minute minute, Second second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    @Override
    public Degree toDegrees() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toDegrees'");
    }

    @Override
    public Hour toHours() {
        double totalHours = hour.getValue() + minute.getValue() / 60 + second.getValue() / 3600;
        return new Hour(totalHours);
    }

    @Override
    public Hms toHms() {
        return this;
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d:%04.1f", (int) hour.getValue(), (int) minute.getValue(), second.getValue());
    }

}
