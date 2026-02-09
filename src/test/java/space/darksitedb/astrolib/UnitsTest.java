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
    void givenFoot_whenConvertToInches_thenCorrect() {
        Foot foot = new Foot(3);
        Inch inch = foot.toInches();
        assertEquals(36.0, inch.getValue(), 0.0001);
    }

    @Test
    void givenInches_whenConvertToInches_thenCorrect() {
        Inch inch = new Inch(15);
        Inch inch2 = inch.toInches();
        assertEquals(15.0, inch2.getValue());
    }

    @Test
    void givenMiles_whenConvertToInches_thenCorrect() {
        Mile mile = new Mile(2);
        Inch inch = mile.toInches();
        assertEquals(126720.0, inch.getValue(), 0.0001);
    }

    @Test
    void givenKilometers_whenConvertToInches_thenCorrect() {
        Kilometer km = new Kilometer(0.5);
        Inch inch = km.toInches();
        assertEquals(19685.039, inch.getValue(), 0.001);
    }

    @Test
    void givenLightYears_whenConvertToInches_thenCorrect() {
        LightYear ly = new LightYear(1);
        Inch inch = ly.toInches();
        assertEquals(3.7249344E17, inch.getValue(), 0.001);
    }

    @Test
    void givenMeters_whenConvertToInches_thenCorrect() {
        Meter meter = new Meter(2);
        Inch inch = meter.toInches();
        assertEquals(78.740157, inch.getValue(), 0.00001);
    }

    @Test
    void givenParsecs_whenConvertToInches_thenCorrect() {
        Parsec pc = new Parsec(0.1);
        Inch inch = pc.toInches();
        assertEquals(1.2149097041664E17, inch.getValue(), 0.001);
    }

    @Test
    void givenAus_whenConvertToInches_thenCorrect() {
        Au au = new Au(0.5);
        Inch inch = au.toInches();
        assertEquals(2.9449728E12, inch.getValue(), 0.001);
    }

    @Test
    void givenInches_whenConvertToMillimeters_thenCorrect() {
        Inch inch = new Inch(10);
        Millimeter mm = inch.toMillimeters();
        assertEquals(254.0, mm.getValue());
    }

    @Test
    void givenMeters_whenConvertToMillimeters_thenCorrect() {
        Meter meter = new Meter(0.75);
        Millimeter mm = meter.toMillimeters();
        assertEquals(750.0, mm.getValue());
    }

    @Test
    void givenMillimeters_whenConvertToMillimeters_thenCorrect() {
        Millimeter mm = new Millimeter(123);
        Millimeter mm2 = mm.toMillimeters();
        assertEquals(123.0, mm2.getValue());
    }

    @Test
    void givenKilometers_whenConvertToMillimeters_thenCorrect() {
        Kilometer km = new Kilometer(0.002);
        Millimeter mm = km.toMillimeters();
        assertEquals(2000.0, mm.getValue());
    }

    @Test
    void givenLightYears_whenConvertToMillimeters_thenCorrect() {
        LightYear ly = new LightYear(0.5);
        Millimeter mm = ly.toMillimeters();
        assertEquals(4.7302642E18, mm.getValue(), 0.001);
    }

    @Test
    void givenParsecs_whenConvertToMillimeters_thenCorrect() {
        Parsec pc = new Parsec(0.01);
        Millimeter mm = pc.toMillimeters();
        assertEquals(3.0856081008304006E17, mm.getValue(), 0.001);
    }

    @Test
    void givenAus_whenConvertToMillimeters_thenCorrect() {
        Au au = new Au(1);
        Millimeter mm = au.toMillimeters();
        assertEquals(1.49597870691E14, mm.getValue(), 0.001);
    }

    @Test
    void givenFeet_whenConvertToMillimeters_thenCorrect() {
        Foot foot = new Foot(5);
        Millimeter mm = foot.toMillimeters();
        assertEquals(1524.0, mm.getValue(), 0.001);
    }

    @Test
    void givenMiles_whenConvertToMillimeters_thenCorrect() {
        Mile mile = new Mile(0.1);
        Millimeter mm = mile.toMillimeters();
        assertEquals(160934.4, mm.getValue(), 0.001);
    }

    @Test
    void givenMeters_whenConvertToFeet_thenCorrect() {
        Meter meter = new Meter(30);
        Foot feet = meter.toFeet();
        assertEquals(98.425197, feet.getValue(), 0.000001);
    }

    @Test
    void givenKilometers_whenConvertToFeet_thenCorrect() {
        Kilometer km = new Kilometer(0.5);
        Foot feet = km.toFeet();
        assertEquals(1640.4199, feet.getValue(), 0.0001);
    }

    @Test
    void givenFeet_whenConvertToMeters_thenCorrect() {
        Foot feet = new Foot(25);
        Meter meter = feet.toMeters();
        assertEquals(7.62, meter.getValue(), 0.00001);
    }

    @Test
    void givenKilometers_whenConvertToMeters_thenCorrect() {
        Kilometer km = new Kilometer(0.5);
        Meter meter = km.toMeters();
        assertEquals(500.0, meter.getValue());
    }

    @Test
    void givenMiles_whenConvertToMeters_thenCorrect() {
        Mile mile = new Mile(0.2);
        Meter meter = mile.toMeters();
        assertEquals(321.869, meter.getValue(), 0.001);
    }

    @Test
    void givenMiles_whenConvertToKilometers_thenCorrect() {
        Mile mile = new Mile(100);
        Kilometer km = mile.toKilometers();
        assertEquals(160.9344, km.getValue(), 0.0001);
    }

    @Test
    void givenKilometers_whenConvertToKilometers_thenCorrect() {
        Kilometer km = new Kilometer(42);
        Kilometer km2 = km.toKilometers();
        assertEquals(42.0, km2.getValue());
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
    void givenInches_whenConvertToMiles_thenCorrect() {
        Inch inch = new Inch(100000);
        Mile mile = inch.toMiles();
        assertEquals(1.5783, mile.getValue(), 0.0001);
    }

    @Test
    void givenAu_whenConvertToMiles_thenCorrect() {
        Au au = new Au(2);
        Mile mile = au.toMiles();
        assertEquals(1.8592e+8, mile.getValue(), 0.0001);
    }

    @Test
    void givenMiles_whenConvertToMiles_thenCorrect() {
        Mile mile = new Mile(42);
        Mile mile2 = mile.toMiles();
        assertEquals(42.0, mile2.getValue());
    }

    @Test
    void givenMiles_whenConvertToLightYears_thenCorrect() {
        Mile mile = new Mile(9.3e+7);
        LightYear ly = mile.toLightYears();
        assertEquals(1.5843e-5, ly.getValue(), 0.000001);
    }

    @Test
    void givenKilometers_whenConvertToLightYears_thenCorrect() {
        Kilometer km = new Kilometer(1e+9);
        LightYear ly = km.toLightYears();
        assertEquals(0.0001057, ly.getValue(), 0.0000001);
    }

    @Test
    void givenParsecs_whenConvertToLightYears_thenCorrect() {
        Parsec pc = new Parsec(3);
        LightYear ly = pc.toLightYears();
        assertEquals(9.78468, ly.getValue(), 0.0001);
    }

    @Test
    void givenLightYears_whenConvertToParsecs_thenCorrect() {
        LightYear ly = new LightYear(5);
        Parsec pc = ly.toParsecs();
        assertEquals(1.5330070, pc.getValue(), 0.001);
    }

    @Test
    void givenMiles_whenConvertToParsecs_thenCorrect() {
        Mile mile = new Mile(1e+13);
        Parsec pc = mile.toParsecs();
        assertEquals(0.521520239592406, pc.getValue(), 0.0001);
    }

    @Test
    void givenKilometers_whenConvertToParsecs_thenCorrect() {
        Kilometer km = new Kilometer(1000);
        Parsec pc = km.toParsecs();
        assertEquals(3.24078e-11, pc.getValue(), 0.0001);
    }

    @Test
    void givenMiles_whenConvertToAu_thenCorrect() {
        Mile mile = new Mile(10000);
        Au au = mile.toAus();
        assertEquals(1.07642263e-4, au.getValue(), 0.00001);
    }

    @Test
    void givenKilometers_whenConvertToAu_thenCorrect() {
        Kilometer km = new Kilometer(1);
        Au au = km.toAus();
        assertEquals(6.6845871226706E-9, au.getValue(), 0.00001);
    }

    @Test
    void givenInches_whenConvertToAu_thenCorrect() {
        Inch inch = new Inch(1e+9);
        Au au = inch.toAus();
        assertEquals(1.6979e-4, au.getValue(), 0.00001);
    }

    @Test
    void givenInch_whenConvertFeet_thenCorrect() {
        Inch inch = new Inch(100);
        Foot foot = inch.toFeet();
        assertEquals(8.3333, foot.getValue(), 0.0001);
    }

    @Test
    void givenInch_whenConvertToMeter_thenCorrect() {
        Inch inch = new Inch(100);
        Meter meter = inch.toMeters();
        assertEquals(2.54, meter.getValue(), 0.0001);
    }

    @Test
    void givenInch_whenConvertToKilometer_thenCorrect() {
        Inch inch = new Inch(100);
        Kilometer km = inch.toKilometers();
        assertEquals(0.00254, km.getValue(), 0.00001);
    }

    @Test
    void givenInch_whenConvertToLightYear_thenCorrect() {
        Inch inch = new Inch(1e+9);
        LightYear ly = inch.toLightYears();
        assertEquals(1.8097e-8, ly.getValue(), 0.00001);
    }

    @Test
    void givenInch_whenConvertToParsec_thenCorrect() {
        Inch inch = new Inch(1e+9);
        Parsec pc = inch.toParsecs();
        assertEquals(5.5431e-9, pc.getValue(), 0.00001);
    }

    @Test
    void givenMillimeters_whenConvertToMeter_thenCorrect() {
        Millimeter mm = new Millimeter(500);
        Meter meter = mm.toMeters();
        assertEquals(0.5, meter.getValue());
    }

    @Test
    void givenMillimeters_whenConvertToKilometer_thenCorrect() {
        Millimeter mm = new Millimeter(500);
        Kilometer km = mm.toKilometers();
        assertEquals(0.0005, km.getValue(), 0.00001);
    }

    @Test
    void givenMillimeters_whenConvertToLightYear_thenCorrect() {
        Millimeter mm = new Millimeter(1e+12);
        LightYear ly = mm.toLightYears();
        assertEquals(1.057e-10, ly.getValue(), 0.00001);
    }

    @Test
    void givenMillimeters_whenConvertToParsec_thenCorrect() {
        Millimeter mm = new Millimeter(1e+12);
        Parsec pc = mm.toParsecs();
        assertEquals(3.24078e-11, pc.getValue(), 0.00001);
    }

    @Test
    void givenMillimeters_whenConvertToAu_thenCorrect() {
        Millimeter mm = new Millimeter(1e+12);
        Au au = mm.toAus();
        assertEquals(6.6845871226706E-9, au.getValue(), 0.00001);
    }

    @Test
    void givenMillimeters_whenConvertToFeet_thenCorrect() {
        Millimeter mm = new Millimeter(500);
        Foot foot = mm.toFeet();
        assertEquals(1.6404199, foot.getValue(), 0.00001);
    }

    @Test
    void givenMillimeters_whenConvertToMiles_thenCorrect() {
        Millimeter mm = new Millimeter(1e+12);
        Mile mile = mm.toMiles();
        assertEquals(621371.1922373341, mile.getValue(), 0.001);
    }

    @Test
    void givenFeet_whenConvertToKilometers_thenCorrect() {
        Foot foot = new Foot(1000);
        Kilometer km = foot.toKilometers();
        assertEquals(0.3048, km.getValue(), 0.00001);
    }

    @Test
    void givenFeet_whenConvertToMiles_thenCorrect() {
        Foot foot = new Foot(5280);
        Mile mile = foot.toMiles();
        assertEquals(1.0, mile.getValue(), 0.00001);
    }

    @Test
    void givenFeet_whenConvertToLightYears_thenCorrect() {
        Foot foot = new Foot(1e+9);
        LightYear ly = foot.toLightYears();
        assertEquals(3.037e-8, ly.getValue(), 0.00001);
    }

    @Test
    void givenFeet_whenConvertToParsecs_thenCorrect() {
        Foot foot = new Foot(1e+9);
        Parsec pc = foot.toParsecs();
        assertEquals(9.306e-9, pc.getValue(), 0.00001);
    }

    @Test
    void givenFeet_whenConvertToAu_thenCorrect() {
        Foot foot = new Foot(1e+9);
        Au au = foot.toAus();
        assertEquals(0.0020373702602618266, au.getValue(), 0.00001);
    }

    @Test
    void givenFeet_whenConvertToFeet_thenCorrect() {
        Foot foot = new Foot(123);
        Foot foot2 = foot.toFeet();
        assertEquals(123.0, foot2.getValue());
    }

    @Test
    void givenParsecs_whenConvertToMeters_thenCorrect() {
        Parsec pc = new Parsec(0.5);
        Meter meter = pc.toMeters();
        assertEquals(1.5428040504151998E16, meter.getValue(), 0.001);
    }

    @Test
    void givenParsecs_whenConvertToKilometers_thenCorrect() {
        Parsec pc = new Parsec(0.5);
        Kilometer km = pc.toKilometers();
        assertEquals(1.5428040504151998E13, km.getValue(), 0.001);
    }

    @Test
    void givenParsecs_whenConvertToFeet_thenCorrect() {
        Parsec pc = new Parsec(0.5);
        Foot foot = pc.toFeet();
        assertEquals(5.06212376736E16, foot.getValue(), 0.001);
    }

    @Test
    void givenParsecs_whenConvertToMiles_thenCorrect() {
        Parsec pc = new Parsec(0.5);
        Mile mile = pc.toMiles();
        assertEquals(9.58735562E12, mile.getValue(), 0.001);
    }

    @Test
    void givenParsecs_whenConverToAu_thenCorrect() {
        Parsec pc = new Parsec(1);
        Au au = pc.toAus();
        assertEquals(206264.81, au.getValue(), 0.001);
    }

    @Test
    void givenParsecs_whenConvertToParsecs_thenCorrect() {
        Parsec pc = new Parsec(0.5);
        Parsec pc2 = pc.toParsecs();
        assertEquals(0.5, pc2.getValue());
    }

    @Test
    void givenLightYears_whenConvertToFeet_thenCorrect() {
        LightYear ly = new LightYear(0.1);
        Foot foot = ly.toFeet();
        assertEquals(3.104112E15, foot.getValue(), 0.001);
    }

    @Test
    void givenLightYears_whenConvertToAus_thenCorrect() {
        LightYear ly = new LightYear(1);
        Au au = ly.toAus();
        assertEquals(63241.077084266280, au.getValue(), 0.0000001);
    }

    @Test
    void givenLightYears_whenConvertToLightYears_thenCorrect() {
        LightYear ly = new LightYear(0.5);
        LightYear ly2 = ly.toLightYears();
        assertEquals(0.5, ly2.getValue());
    }

    @Test
    void givenMeters_whenConvertToAu_thenCorrect() {
        Meter meter = new Meter(1e+9);
        Au au = meter.toAus();
        assertEquals(6.6845871226706E-9, au.getValue(), 0.00001);
    }

    @Test
    void givenMeters_whenConvertToMeters_thenCorrect() {
        Meter meter = new Meter(123);
        Meter meter2 = meter.toMeters();
        assertEquals(123.0, meter2.getValue());
    }

    @Test
    void givenMeters_whenConvertToMiles_thenCorrect() {
        Meter meter = new Meter(1e+6);
        Mile mile = meter.toMiles();
        assertEquals(621.371192237334, mile.getValue(), 0.001);
    }

    @Test
    void givenMeters_whenConvertToLightYears_thenCorrect() {
        Meter meter = new Meter(1e+16);
        LightYear ly = meter.toLightYears();
        assertEquals(1.0570234110813514, ly.getValue(), 0.00001);
    }

    @Test
    void givenMeters_whenConvertToParsecs_thenCorrect() {
        Meter meter = new Meter(1e+16);
        Parsec pc = meter.toParsecs();
        assertEquals(0.324078, pc.getValue(), 0.00001);
    }

    @Test
    void givenAus_whenConvertToMeters_thenCorrect() {
        Au au = new Au(1);
        Meter meter = au.toMeters();
        assertEquals(1.49597870691E11, meter.getValue(), 0.001);
    }

    @Test
    void givenAus_whenConvertToFeet_thenCorrect() {
        Au au = new Au(1);
        Foot foot = au.toFeet();
        assertEquals(4.908288E11, foot.getValue(), 0.001);
    }

    @Test
    void givenAus_whenConvertToLightYears_thenCorrect() {
        Au au = new Au(1);
        LightYear ly = au.toLightYears();
        assertEquals(1.58125e-5, ly.getValue(), 0.00001);
    }

    @Test
    void givenAus_whenConvertToParsecs_thenCorrect() {
        Au au = new Au(1);
        Parsec pc = au.toParsecs();
        assertEquals(4.84814e-6, pc.getValue(), 0.00001);
    }

    @Test
    void givenAus_whenConvertToAu_thenCorrect() {
        Au au = new Au(1);
        Au au2 = au.toAus();
        assertEquals(1.0, au2.getValue());
    }
}