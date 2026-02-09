package space.darksitedb.astrolib.units;

public interface Length {

    double getValue();

    Inch toInches();

    Millimeter toMillimeters();

    Meter toMeters();

    Kilometer toKilometers();

    Foot toFeet();

    Mile toMiles();

    LightYear toLightYears();

    Parsec toParsecs();

    Au toAus();
}
