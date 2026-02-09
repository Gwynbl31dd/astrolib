package space.darksitedb.astrolib;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import space.darksitedb.astrolib.units.*;

import org.junit.jupiter.api.Test;

public class TemperatureTest {

    @Test
    void givenDegreeCelsius_whenGetValue_thenCorrect() {
        DegreeCelsius celsius = new DegreeCelsius(25);
        assertEquals(25, celsius.getValue(), 1e-10);
    }

    @Test
    void givenDegreeFahrenheit_whenGetValue_thenCorrect() {
        DegreeFahrenheit fahrenheit = new DegreeFahrenheit(77);
        assertEquals(77, fahrenheit.getValue(), 1e-10);
    }

    @Test
    void givenDegreeKelvin_whenGetValue_thenCorrect() {
        DegreeKelvin kelvin = new DegreeKelvin(300);
        assertEquals(300, kelvin.getValue(), 1e-10);
    }

    @Test
    void givenCelsuis_whenConvertToFahrenheit_thenCorrect() {
        DegreeCelsius celsius = new DegreeCelsius(100);
        DegreeFahrenheit fahrenheit = celsius.toFahrenheit();
        assertEquals(212, fahrenheit.getValue(), 1e-10);
    }

    @Test
    void givenFahrenheit_whenConvertToCelsius_thenCorrect() {
        DegreeFahrenheit fahrenheit = new DegreeFahrenheit(32);
        DegreeCelsius celsius = fahrenheit.toCelsius();
        assertEquals(0, celsius.getValue(), 1e-10);
    }

    @Test
    void givenKelvin_whenConvertToCelsius_thenCorrect() {
        DegreeKelvin kelvin = new DegreeKelvin(273.15);
        DegreeCelsius celsius = kelvin.toCelsius();
        assertEquals(0, celsius.getValue(), 1e-10);
    }

    @Test
    void givenKelvin_whenConvertToFahrenheit_thenCorrect() {
        DegreeKelvin kelvin = new DegreeKelvin(255.372);
        DegreeFahrenheit fahrenheit = kelvin.toFahrenheit();
        assertEquals(-0.000399999999, fahrenheit.getValue(), 1e-10);
    }

    @Test
    void givenKelvin_whenConvertToKelvin_thenSame() {
        DegreeKelvin kelvin = new DegreeKelvin(100);
        DegreeKelvin converted = kelvin.toKelvin();
        assertEquals(kelvin.getValue(), converted.getValue(), 1e-10);
    }

    @Test
    void givenCelsius_whenConvertToCelsius_thenSame() {
        DegreeCelsius celsius = new DegreeCelsius(50);
        DegreeCelsius converted = celsius.toCelsius();
        assertEquals(celsius.getValue(), converted.getValue(), 1e-10);
    }

    @Test
    void givenCelsius_whenConvertToKelvin_thenCorrect() {
        DegreeCelsius celsius = new DegreeCelsius(0);
        DegreeKelvin kelvin = celsius.toKelvin();
        assertEquals(273.15, kelvin.getValue(), 1e-10);
    }

    @Test
    void givenFahrenheit_whenConvertToFahrenheit_thenSame() {
        DegreeFahrenheit fahrenheit = new DegreeFahrenheit(68);
        DegreeFahrenheit converted = fahrenheit.toFahrenheit();
        assertEquals(fahrenheit.getValue(), converted.getValue(), 1e-10);
    }

    @Test
    void givenFahrenheit_whenConvertToKelvin_thenCorrect() {
        DegreeFahrenheit fahrenheit = new DegreeFahrenheit(32);
        DegreeKelvin kelvin = fahrenheit.toKelvin();
        assertEquals(273.15, kelvin.getValue(), 1e-10);
    }
    

    @Test 
    void givenMinTemperature_whenLowerThanAbsoluteZero_thenThrow() {
        assertAll(
            () -> assertThrows(IllegalArgumentException.class, () -> new DegreeKelvin(-1)),
            () -> assertThrows(IllegalArgumentException.class, () -> new DegreeCelsius(-274)),
            () -> assertThrows(IllegalArgumentException.class, () -> new DegreeFahrenheit(-460))
        );
    }

}
