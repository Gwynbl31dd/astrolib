package space.darksitedb.astrolib.units;

public class Millimeter implements Length {

    private final double value;

    public Millimeter(double value) {
        this.value = value;
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public Inch toInches() {
        return new Inch(value / new Inch(1).toMillimeters().getValue());
    }

    @Override
    public Millimeter toMillimeters() {
        return this;
    }

    @Override
    public Meter toMeters() {
        return new Meter(value / 1000);
    }

    @Override
    public Kilometer toKilometers() {
        return toMeters().toKilometers();
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
    public LightYear toLightYears() {
        return toKilometers().toLightYears();
    }

    @Override
    public Parsec toParsecs() {
        return toLightYears().toParsecs();
    }

    @Override
    public Au toAus() {
        return toKilometers().toAus();
    }
}
