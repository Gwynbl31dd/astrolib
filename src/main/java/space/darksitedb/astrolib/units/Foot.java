package space.darksitedb.astrolib.units;

public class Foot implements Distance {

    private final double value;

    private static final double TO_METERS = 0.3048;

    public Foot(double value) {
        this.value = value;
    }

    @Override
    public double getValue() {
        return value;
    }

    public Meter toMeters() {
        return new Meter(value * TO_METERS);
    }

}
