package space.darksitedb.astrolib.units;

public class LightYear implements Length {

    private final double value;
    private static final double TO_MILES = 5.879e12;
    private static final double TO_KILOMETERS = 9.4605284e+12;
    private static final double TO_AU = 63241.077084266280;

    public LightYear(double value) {
        this.value = value;
    }

    @Override
    public double getValue() {
        return value;
    }

    public Mile toMiles() {
        return new Mile(value * TO_MILES);
    }

    public Parsec toParsecs() {
        return new Parsec(value / new Parsec(1).toLightYears().getValue());
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
        return this;
    }

    @Override
    public Au toAus() {
        return new Au(value * TO_AU);
    }

}
