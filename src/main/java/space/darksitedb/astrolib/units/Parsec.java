package space.darksitedb.astrolib.units;

public class Parsec extends Unit implements Length {

    private static final double TO_LIGHTYEARS = 3.26156;

    public Parsec(double value) {
        super(value);
    }

    @Override
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
        return toAus().toMeters();
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
        return new Au((648000 / Math.PI) * value);
    }

}