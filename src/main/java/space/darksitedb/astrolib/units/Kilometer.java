package space.darksitedb.astrolib.units;

public class Kilometer implements Length {

    private final double value;

    public Kilometer(double value) {
        this.value = value;
    }

    @Override
    public double getValue() {
        return value;
    }

    public Mile toMiles() {
        return new Mile(value / new Mile(1).toKilometers().getValue());
    }

    @Override
    public Inch toInches() {
        return toMiles().toInches();
    }

    @Override
    public Millimeter toMillimeters() {
        return toMeters().toMillimeters();
    }

    @Override
    public Meter toMeters() {
        return new Meter(value * 1000);
    }

    @Override
    public Kilometer toKilometers() {
        return this;
    }

    @Override
    public Foot toFeet() {
        return toMeters().toFeet();
    }

    @Override
    public LightYear toLightYears() {
        return new LightYear( value / new LightYear(1).toKilometers().getValue());
    }

    @Override
    public Parsec toParsecs() {
        return toLightYears().toParsecs();
    }

    @Override
    public Au toAus() {
        return new Au(1 / new Au(1).toKilometers().getValue());
    }

}
