package space.darksitedb.astrolib.units;

/**
 * Interface representing a length unit. It provides methods to convert the length to different units.
 */
public interface Length {

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
