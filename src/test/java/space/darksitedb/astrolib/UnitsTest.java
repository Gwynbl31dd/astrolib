package space.darksitedb.astrolib;

import space.darksitedb.astrolib.units.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UnitsTest {

    @Test
    void givenMillimeters_whenConvertToInches_thenCorrect() {
        Millimeter mm = new Millimeter(5);
        Inch inch = mm.toInches();
        assertEquals(0.196850, inch.getValue(), 0.00001);
    }

    @Test
    void givenInches_whenConvertToMillimeters_thenCorrect() {
        Inch inch = new Inch(10);
        Millimeter mm = inch.toMillimeters();
        assertEquals(254.0, mm.getValue());
    }

    @Test
    void givenMeters_whenConvertToFeet_thenCorrect() {
        Meter meter = new Meter(30);
        Foot feet = meter.toFeet();
        assertEquals(98.425197, feet.getValue(), 0.000001);
    }

    @Test
    void givenFeet_whenConvertToMeters_thenCorrect() {
        Foot feet = new Foot(25);
        Meter meter = feet.toMeters();
        assertEquals(7.62, meter.getValue(), 0.00001);
    }

    @Test
    void givenMiles_whenConvertToKilometers_thenCorrect() {
        Mile mile = new Mile(100);
        Kilometer km = mile.toKilometers();
        assertEquals(160.9344, km.getValue(), 0.0001);
    }

    @Test
    void givenKilometers_whenConvertToMiles_thenCorrect() {
        Kilometer km = new Kilometer(88);
        Mile mile = km.toMiles();
        assertEquals(54.680665, mile.getValue(), 0.00001);
    }

    @Test
    void givenLightYears_whenConvertToMiles_thenCorrect() {
        LightYear ly = new LightYear(12);
        Mile mile = ly.toMiles();
        assertEquals(7.0548e+13, mile.getValue(), 0.0001);
    }

    @Test
    void givenMiles_whenConvertToLightYears_thenCorrect() {
        Mile mile = new Mile(9.3e+7);
        LightYear ly = mile.toLightYears();
        assertEquals(1.5843e-5, ly.getValue(), 0.000001);
    }

    @Test
    void givenLightYears_whenConvertToParsecs_thenCorrect() {
        LightYear ly = new LightYear(5);
        Parsec pc = ly.toParsecs();
        assertEquals(1.53301, pc.getValue(), 0.001);
    }

    @Test
    void givenParsecs_whenConvertToLightYears_thenCorrect() {
        Parsec pc = new Parsec(3);
        LightYear ly = pc.toLightYears();
        assertEquals(9.78468, ly.getValue(), 0.0001);
    }

    @Test
    void givenAu_whenConvertToMiles_thenCorrect() {
        Au au = new Au(2);
        Mile mile = au.toMiles();
        assertEquals(1.8592e+8, mile.getValue(), 0.0001);
    }

    @Test
    void givenMiles_whenConvertToAu_thenCorrect() {
        Mile mile = new Mile(10000);
        Au au = mile.toAu();
        assertEquals(1.07642263e-4, au.getValue(), 0.00001);
    }
}