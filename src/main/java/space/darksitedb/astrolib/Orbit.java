package space.darksitedb.astrolib;

import space.darksitedb.astrolib.units.angle.Angular;
import space.darksitedb.astrolib.units.angle.Degree;
import space.darksitedb.astrolib.units.length.Kilometer;
import space.darksitedb.astrolib.units.length.Length;
import space.darksitedb.astrolib.units.time.Day;

/**
 * Class representing orbits operations. 
 * This class contains methods to calculate orbital elements, positions, and other related calculations.
 */
public class Orbit {

    /**
     * Calculates the orbital radius based on the given eccentricity, semi-major axis, and true anomaly.
     * @param eccentricity The eccentricity of the orbit.
     * @param semiMajorAxis The semi-major axis of the orbit.
     * @param trueAnomaly The true anomaly of the orbit.
     * @return The orbital radius.
     */
    public static Kilometer calculateOrbitalRadius(float eccentricity, Length semiMajorAxis, Angular trueAnomaly) {
        // Placeholder for actual distance calculation based on orbital elements
        double distance = (semiMajorAxis.toKilometers().getValue() * (1 - Math.pow(eccentricity, 2))) / (1 + eccentricity * Math.cos(trueAnomaly.toRadians().getValue()));
        return new Kilometer(distance);
    }

    /**
     * Calculates the mean anomaly based on the given orbital period and time since perihelion.
     * @param orbitalPeriod
     * @param sincePerihelion
     * @return
     */
    public static Degree calculateMeanAnomaly(Day orbitalPeriod, Day sincePerihelion) {
        double meanAnomaly = (360.0 / orbitalPeriod.getValue()) * sincePerihelion.getValue();
        return new Degree(meanAnomaly);
    }
    
}
