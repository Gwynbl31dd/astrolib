package space.darksitedb.astrolib.units;

public class Inch extends Unit implements Length {

    private static final double TO_MILLIMETERS = 25.4;

    public Inch(double value) {
        super(value);
    }

    @Override
    public Millimeter toMillimeters() {
        return new Millimeter(value * TO_MILLIMETERS);
    }

    @Override
    public Foot toFeet() {
        return new Foot(value / new Foot(1).toInches().getValue());
    }

    @Override
    public Inch toInches() {
        return this;
    }

    @Override
    public Meter toMeters() {
        return toFeet().toMeters();
    }

    @Override
    public Kilometer toKilometers() {
        return toMeters().toKilometers();
    }

    @Override
    public Mile toMiles() {
        return new Mile(value / new Mile(1).toInches().getValue());
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
