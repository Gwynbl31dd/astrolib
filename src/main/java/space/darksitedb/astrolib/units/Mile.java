package space.darksitedb.astrolib.units;

public class Mile implements Length {

    private final double value;

    private static final double TO_KILOMETERS = 1.609344;
    private static final int TO_FOOT = 5280;

    public Mile(double value) {
        this.value = value;
    }

    @Override
    public double getValue() {
        return value;
    }

    public Kilometer toKilometers() {
        return new Kilometer(value * TO_KILOMETERS);
    }

    public LightYear toLightYears() {
        return new LightYear(value / new LightYear(1).toMiles().getValue());
    }

    public Au toAus() {
        return new Au(value / new Au(1).toMiles().getValue());
    }

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
