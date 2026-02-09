package space.darksitedb.astrolib.units;

public class Foot extends Unit implements Length {

    private static final double TO_METERS = 0.3048;
    private static final int TO_INCHES = 12;

    public Foot(double value) {
        super(value);
    }

    @Override
    public Meter toMeters() {
        return new Meter(value * TO_METERS);
    }

    @Override
    public Inch toInches() {
        return new Inch(value * TO_INCHES);
    }

    @Override
    public Millimeter toMillimeters() {
        return toMeters().toMillimeters();
    }

    @Override
    public Kilometer toKilometers() {
        return toMeters().toKilometers();
    }

    @Override
    public Foot toFeet() {
        return this;
    }

    @Override
    public Mile toMiles() {
        return toInches().toMiles();
    }

    @Override
    public LightYear toLightYears() {
        return toMiles().toLightYears();
    }

    @Override
    public Parsec toParsecs() {
        return toLightYears().toParsecs();
    }

    @Override
    public Au toAus() {
        return toMiles().toAus();
    }

}
