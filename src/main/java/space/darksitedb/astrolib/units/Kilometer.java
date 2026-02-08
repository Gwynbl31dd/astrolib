package space.darksitedb.astrolib.units;

public class Kilometer implements Distance {

    private final double value;

    public Kilometer(double value) {
        this.value = value;
    }

    @Override
    public double getValue() {
        return value;
    }

    public Mile toMiles() {
        return new Mile(value * (1/new Mile(1).toKilometers().getValue()));
    }

}
