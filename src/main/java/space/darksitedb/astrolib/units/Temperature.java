package space.darksitedb.astrolib.units;

/**
 * Interface representing a temperature unit. It provides methods to convert the temperature to different units.
 */
public interface Temperature {

    DegreeKelvin toKelvin();
    DegreeCelsius toCelsius();
    DegreeFahrenheit toFahrenheit();

}
