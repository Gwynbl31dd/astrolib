package space.darksitedb.astrolib;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

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

    @Test
    void givenOrbitalPeriodSincePerihelion_whenCalculateMeanAnomaly_thenCorrect() {
        Day orbitalPeriod = new Day(686);
        Day sincePerihelion = new Day(300);
        Degree expectedMeanAnomaly = new Degree(157.434);

        Degree actualMeanAnomaly = Orbit.calculateMeanAnomaly(orbitalPeriod, sincePerihelion);

        assertEquals(expectedMeanAnomaly.getValue(), actualMeanAnomaly.getValue(), 0.01);
    }

    @Test
    void givenAngleAndOrbitalPeriod_whenRequestTimeToReachAngle_thenCorrect() {
        Day orbitalPeriod = new Day(686.97);
        Degree angle = new Degree(230);
        Day expectedTime = new Day(438.8975);

        Day actualTime = Orbit.getTimeSincePerihelionFromMeanAnomaly(orbitalPeriod, angle);

        assertEquals(expectedTime.getValue(), actualTime.getValue(), 0.0000001);
    }
    
}
