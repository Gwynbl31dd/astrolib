package space.darksitedb.astrolib.units;

public class Mile extends Unit implements Length {

    private static final double TO_KILOMETERS = 1.609344;
    private static final int TO_FOOT = 5280;

    public Mile(double value) {
        super(value);
    }

    @Override
    public Kilometer toKilometers() {
        return new Kilometer(value * TO_KILOMETERS);
    }

    @Override
    public LightYear toLightYears() {
        return new LightYear(value / new LightYear(1).toMiles().getValue());
    }

    @Override
    public Au toAus() {
        return new Au(value / new Au(1).toMiles().getValue());
    }

    @Override
    public Foot toFeet() {
        return new Foot(value * TO_FOOT);
    }

    @Override
    public Inch toInches() {
        return toFeet().toInches();
    }

    @Override
    public Millimeter toMillimeters() {
        return toInches().toMillimeters();
    }

    @Override
    public Meter toMeters() {
        return toKilometers().toMeters();
    }

    @Override
    public Mile toMiles() {
        return this;
    }

    @Override
    public Parsec toParsecs() {
        return toLightYears().toParsecs();
    }

}
