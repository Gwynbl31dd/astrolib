package space.darksitedb.astrolib.units;

public class Au extends Unit implements Length {

    private static final double TO_MILES = 9.296e+7;
    private static final double TO_KILOMETERS = 149597870.691;

    public Au(double value) {
        super(value);
    }

    @Override
    public Mile toMiles() {
        return new Mile(value * TO_MILES);
    }

    @Override
    public Inch toInches() {
        return toMiles().toInches();
    }

    @Override
    public Millimeter toMillimeters() {
        return toKilometers().toMillimeters();
    }

    @Override
    public Meter toMeters() {
        return toKilometers().toMeters();
    }

    @Override
    public Kilometer toKilometers() {
        return new Kilometer(value * TO_KILOMETERS);
    }

    @Override
    public Foot toFeet() {
        return toMiles().toFeet();
    }

    @Override
    public LightYear toLightYears() {
        return toKilometers().toLightYears();
    }

    @Override
    public Parsec toParsecs() {
        return toLightYears().toParsecs();
    }

    @Override
    public Au toAus() {
        return this;
    }

}
