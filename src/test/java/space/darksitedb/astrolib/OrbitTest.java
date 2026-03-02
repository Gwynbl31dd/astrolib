package space.darksitedb.astrolib;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import space.darksitedb.astrolib.units.angle.Degree;
import space.darksitedb.astrolib.units.length.Kilometer;
import space.darksitedb.astrolib.units.time.Day;

public class OrbitTest {

    @Test
    void givenEccentricitySemiMajorAxisTrueAnomaly_whenCalculateOrbitalRadius_thenCorrect() {
        float eccentricity = 0.5f;
        Kilometer semiMajorAxis = new Kilometer(40000);
        Degree trueAnomaly = new Degree(45);
        Kilometer expectedDistance = new Kilometer(22163.88);

        Kilometer actualDistance = Orbit.calculateOrbitalRadius(eccentricity, semiMajorAxis, trueAnomaly);

        assertEquals(expectedDistance.getValue(), actualDistance.getValue(), 0.01);
    }

    @Test
    void givenEccentricitySemiMajorAxisTrueAnomaly_whenCalculateOrbitalRadiusAtPerigee_thenCorrect() {
        float eccentricity = 0.5f;
        Kilometer semiMajorAxis = new Kilometer(40000);
        Degree trueAnomaly = new Degree(0);
        Kilometer expectedDistance = new Kilometer(20000);

        Kilometer actualDistance = Orbit.calculateOrbitalRadius(eccentricity, semiMajorAxis, trueAnomaly);

        assertEquals(expectedDistance.getValue(), actualDistance.getValue(), 0.01);
    }

    @Test
    void givenEccentricitySemiMajorAxisTrueAnomaly_whenCalculateOrbitalRadiusAtApogee_thenCorrect() {
        float eccentricity = 0.5f;
        Kilometer semiMajorAxis = new Kilometer(40000);
        Degree trueAnomaly = new Degree(180);
        Kilometer expectedDistance = new Kilometer(60000);  

        Kilometer actualDistance = Orbit.calculateOrbitalRadius(eccentricity, semiMajorAxis, trueAnomaly);

        assertEquals(expectedDistance.getValue(), actualDistance.getValue(), 0.01);
    }

    @ParameterizedTest(name = "orbital period {0} Since perihelion {1} corresponds mean anomaly {2}")
    @CsvSource({ "686, 300, 157.434", "365.2564, 100.25, 98.8073", "4332.59, 2000, 166.1823", "365.2564, 182.6282, 180" })
    void givenOrbitalPeriodSincePerihelion_whenCalculateMeanAnomaly_thenCorrect(double orbitalPeriodValue, double sincePerihelionValue, double expectedMeanAnomalyValue) {
        Day orbitalPeriod = new Day(orbitalPeriodValue);
        Day sincePerihelion = new Day(sincePerihelionValue);
        Degree expectedMeanAnomaly = new Degree(expectedMeanAnomalyValue);

        Degree actualMeanAnomaly = Orbit.calculateMeanAnomaly(orbitalPeriod, sincePerihelion);

        assertEquals(expectedMeanAnomaly.getValue(), actualMeanAnomaly.getValue(), 0.001);
    }

    @Test
    void givenAngleAndOrbitalPeriod_whenRequestTimeToReachAngle_thenCorrect() {
        Day orbitalPeriod = new Day(686.97);
        Degree angle = new Degree(230);
        Day expectedTime = new Day(438.8975);

        Day actualTime = Orbit.getTimeSincePerihelionFromMeanAnomaly(orbitalPeriod, angle);

        assertEquals(expectedTime.getValue(), actualTime.getValue(), 0.0000001);
    }

    @Test
    void givenAngleOrbitalPeriodAndTimeSincePerihelion_whenCalculateTrueAnomaly_thenCorrect() {
        Day orbitalPeriod = new Day(365.2564);
        Day sincePerihelion = new Day(100.25);
        float eccentricity = 0.0167f;
        Degree expectedTrueAnomaly = new Degree(180);

        Degree actualTrueAnomaly = Orbit.calculateTrueAnomaly(orbitalPeriod, sincePerihelion, eccentricity);

        assertEquals(expectedTrueAnomaly.getValue(), actualTrueAnomaly.getValue(), 0.0000001);
    }
    
}
