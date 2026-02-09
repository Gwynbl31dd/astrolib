package space.darksitedb.astrolib.units;

public class Meter extends Unit implements Length {

    public Meter(double value) {
        super(value);
    }

    @Override
    public Foot toFeet() {
        return new Foot(value / new Foot(1).toMeters().getValue());
    }

    @Override
    public Inch toInches() {
        return toFeet().toInches();
    }

    @Override
    public Millimeter toMillimeters() {
        return new Millimeter(value * 1000);
    }

    @Override
    public Meter toMeters() {
        return this;
    }

    @Override
    public Kilometer toKilometers() {
        return new Kilometer(value / 1000);
    }

    @Override
    public Mile toMiles() {
        return toFeet().toMiles();
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
