package space.darksitedb.astrolib.units;

public class Meter implements Distance {

    private final double value;

    public Meter(double value) {
        this.value = value;
    }

    @Override
    public double getValue() {
        return value;
    }

    public Foot toFeet() {
        return new Foot(value * (1/new Foot(1).toMeters().getValue()));
    }

}
