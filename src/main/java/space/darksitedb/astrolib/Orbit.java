package space.darksitedb.astrolib;

import space.darksitedb.astrolib.units.angle.Angular;
import space.darksitedb.astrolib.units.angle.Degree;
import space.darksitedb.astrolib.units.length.Kilometer;
import space.darksitedb.astrolib.units.length.Length;
import space.darksitedb.astrolib.units.time.Day;
import space.darksitedb.astrolib.units.time.Time;

/**
 * Class representing orbits operations.
 * This class contains methods to calculate orbital elements, positions, and
 * other related calculations.
 */
public class Orbit {

    /**
     * Calculates the orbital radius based on the given eccentricity, semi-major
     * axis, and true anomaly.
     * 
     * @param eccentricity  The eccentricity of the orbit.
     * @param semiMajorAxis The semi-major axis of the orbit.
     * @param trueAnomaly   The true anomaly of the orbit.
     * @return The orbital radius.
     */
    public static Kilometer calculateOrbitalRadius(float eccentricity, Length semiMajorAxis, Angular trueAnomaly) {
        // Placeholder for actual distance calculation based on orbital elements
        double distance = (semiMajorAxis.toKilometers().getValue() * (1 - Math.pow(eccentricity, 2)))
                / (1 + eccentricity * Math.cos(trueAnomaly.toRadians().getValue()));
        return new Kilometer(distance);
    }

    /**
     * Calculates the mean anomaly based on the given orbital period and time since
     * perihelion.
     * 
     * @param orbitalPeriod   The orbital period of the orbit.
     * @param sincePerihelion The time since perihelion.
     * @return The mean anomaly.
     */
    public static Degree calculateMeanAnomaly(Time orbitalPeriod, Time sincePerihelion) {
        double meanAnomaly = (360.0 / orbitalPeriod.toDays().getValue()) * sincePerihelion.toDays().getValue();
        return new Degree(meanAnomaly);
    }

    /**
     * Calculates the time elapsed since perihelion based on the mean anomaly.
     * 
     * @param orbitalPeriod The orbital period of the orbit.
     * @param meanAnomaly   The mean anomaly (angle from perihelion).
     * @return The time elapsed since perihelion passage.
     */
    public static Day getTimeSincePerihelionFromMeanAnomaly(Time orbitalPeriod, Angular meanAnomaly) {
        double timeSincePerihelion = (meanAnomaly.toDegrees().getValue() * orbitalPeriod.toDays().getValue()) / 360.0;
        return new Day(timeSincePerihelion);
    }

    /**
     * Calculates the true anomaly based on the given orbital period, time since
     * perihelion, and eccentricity.
     * 
     * THis is a simplified version of the true anomaly calculation, which does not take into account the iterative nature of solving Kepler's equation. For more accurate results, a numerical method should
     * @param orbitalPeriod
     * @param sincePerihelion
     * @param eccentricity
     * @return
     */
    public static Degree calculateTrueAnomaly(Time orbitalPeriod, Time sincePerihelion, float eccentricity) {
        Degree meanAnomaly = calculateMeanAnomaly(orbitalPeriod, sincePerihelion);
        double correction = (180/Math.PI) * ( 2*eccentricity * Math.sin(meanAnomaly.toRadians().getValue()) );
        Degree trueAnomaly = new Degree(meanAnomaly.getValue() + correction);

        Time halfOrbitalPeriod = new Day(orbitalPeriod.toDays().getValue() / 2);

        meanAnomaly = calculateMeanAnomaly(orbitalPeriod, halfOrbitalPeriod);

        correction = (180/Math.PI) * ( 2*eccentricity * Math.sin(meanAnomaly.toRadians().getValue()) );

        trueAnomaly = new Degree(meanAnomaly.getValue() + correction);

        return trueAnomaly;
    }

}
