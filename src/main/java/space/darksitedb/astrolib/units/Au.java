package space.darksitedb.astrolib.units;

/**
 * The Astronomical Unit (AU) is a unit of length used in astronomy to measure
 * distances within our solar system. It is defined as the average distance
 * between the Earth and the Sun, which is approximately 149,597,870.7
 * kilometers (92,955,807.3 miles). The AU provides a convenient way to express
 * distances between celestial objects in our solar system without resorting to
 * extremely large numbers.
 *
 * The AU is commonly used to describe the distances of planets from the Sun, as
 * well as the distances between other celestial bodies such as asteroids and
 * comets. For example, Earth is located at approximately 1 AU from the Sun,
 * while Jupiter is about 5.2 AU away. The AU also serves as a fundamental unit
 * for calculating orbital parameters and understanding the scale of our solar
 * system.
 *
 * In addition to its use in astronomy, the AU has historical significance in
 * navigation and geodesy, where it was used as a reference distance for
 * measuring the size of the Earth and mapping the stars. Today, it remains an
 * essential unit for astronomers and space scientists when discussing distances
 * within our solar system.
 */
public class Au extends Unit implements Length {

    private static final double TO_MILES = 9.296e+7;

    private static final long TO_METERS = 149597870700L;

    public Au(double value) {
        super(value);
    }

    @Override
    public Mile toMiles() {
        return new Mile(value * TO_MILES);
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
        return new Meter(value * TO_METERS);
    }

    @Override
    public Kilometer toKilometers() {
        return new Kilometer(value * TO_METERS / 1000);
    }

    @Override
    public Foot toFeet() {
        return toMiles().toFeet();
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
        return this;
    }

}
