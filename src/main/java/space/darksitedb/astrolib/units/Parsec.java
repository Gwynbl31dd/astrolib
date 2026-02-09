package space.darksitedb.astrolib.units;

public class Parsec implements Length {

    private final double value;
    private static final double TO_LIGHTYEARS = 3.26156;
    private static final double TO_AU = 206264.81;

    public Parsec(double value) {
        this.value = value;
    }

    @Override
    public double getValue() {
        return value;
    }

    public LightYear toLightYears() {
        return new LightYear(value * TO_LIGHTYEARS);
    }

    @Override
    public Inch toInches() {
        return toLightYears().toInches();
    }

    @Override
    public Millimeter toMillimeters() {
        return toLightYears().toMillimeters();
    }

    @Override
    public Meter toMeters() {
        return toLightYears().toMeters();
    }

    @Override
    public Kilometer toKilometers() {
        return toLightYears().toKilometers();
    }

    @Override
    public Foot toFeet() {
        return toInches().toFeet();
    }

    @Override
    public Mile toMiles() {
        return toInches().toMiles();
    }

    @Override
    public Parsec toParsecs() {
        return this;
    }

    @Override
    public Au toAus() {
        return new Au(value * TO_AU);
    }

}