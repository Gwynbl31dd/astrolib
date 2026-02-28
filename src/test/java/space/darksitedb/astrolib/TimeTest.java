package space.darksitedb.astrolib;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import space.darksitedb.astrolib.time.UT;
import space.darksitedb.astrolib.time.Day;
import space.darksitedb.astrolib.time.JulianDate;
import space.darksitedb.astrolib.time.LCT;
import space.darksitedb.astrolib.time.Month;
import space.darksitedb.astrolib.time.Year;
import space.darksitedb.astrolib.units.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class TimeTest {

    @Test
    void givenHms_whenConvertToHours_thenCorrect() {
        Hms hms = new Hms(new Hour(2), new Minute(30), new Second(45));
        Hour hour = hms.toHours();
        assertEquals(2.5125, hour.getValue(), 1e-10);
    }

    @Test
    void givenHms_whenConvertToHms_thenSame() {
        Hms hms = new Hms(new Hour(1), new Minute(15), new Second(30));
        Hms converted = hms.toHms();
        assertEquals(hms.toHours().getValue(), converted.toHours().getValue(), 1e-10);
    }

    @Test
    void givenHours_whenConvertToHms_thenCorrect() {
        Hour hour = new Hour(20.352);
        Hms hms = hour.toHms();
        assertEquals("20:21:07.2", hms.toString());
    }

    @Test
    void givenHours_whenConvertToHours_thenSame() {
        Hour hour = new Hour(5);
        Hour converted = hour.toHours();
        assertEquals(hour.getValue(), converted.getValue(), 1e-10);
    }

    @Test
    void givenHms_whenConvertToDegrees_thenCorrect() {
        Hms hms = new Hms(new Hour(3), new Minute(45), new Second(0));
        Degree degree = hms.toDegrees();
        assertEquals(56.25, degree.getValue(), 1e-10);
    }

    @Test
    void givenMinute_whenConvertToHours_thenCorrect() {
        Minute minute = new Minute(90);
        Hour hour = minute.toHours();
        assertEquals(1.5, hour.getValue(), 1e-10);
    }

    @Test
    void givenMinutes_whenConvertToDegrees_thenCorrect() {
        Minute minute = new Minute(30);
        Degree degree = minute.toDegrees();
        assertEquals(7.5, degree.getValue(), 1e-10);
    }

    @Test
    void givenMinutes_whenConvertToHms_thenCorrect() {
        Minute minute = new Minute(45);
        Hms hms = minute.toHms();
        assertEquals("00:45:00.0", hms.toString());
    }

    @Test
    void givenSeconds_whenConvertToDegrees_thenCorrect() {
        Second second = new Second(3600);
        Degree degree = second.toDegrees();
        assertEquals(15.0, degree.getValue(), 1e-10);
    }

    @Test
    void givenSeconds_whenConvertToHours_thenCorrect() {
        Second second = new Second(7200);
        Hour hour = second.toHours();
        assertEquals(2.0, hour.getValue(), 1e-10);
    }

    @Test
    void givenSeconds_whenConvertToHms_thenCorrect() {
        Second second = new Second(3661);
        Hms hms = second.toHms();
        assertEquals("01:01:01.0", hms.toString());
    }

    @Test
    void givenHour_whenValueIsNegative_thenThrowException() {
        assertThrows(IllegalArgumentException.class, () -> new Hour(-1));
    }

    @Test
    void givenMinute_whenValueIsNegative_thenThrowException() {
        assertThrows(IllegalArgumentException.class, () -> new Minute(-1));
    }

    @Test
    void givenSecond_whenValueIsNegative_thenThrowException() {
        assertThrows(IllegalArgumentException.class, () -> new Second(-1));
    }

    @ParameterizedTest(name = "year {0} is leap year: {1}")
    @CsvSource({"1984, true", "1974, false", "2000, true", "1900, false"})
    void givenYear_whenRequestIsLeapYear_thenCorrect(int yearValue, boolean expected) {
        Year year = new Year(yearValue);
        assertEquals(expected, year.isLeapYear());
    }

    @Test
    void givenADate_whenConvertToJulianDayNumber_thenCorrect() {
        UT date = new UT(new Year(2010), new Month(11), new Day(1));
        JulianDate julianDate = date.toJulianDayNumber();
        assertEquals(2455501.5, julianDate.getValue());
    }
    
    @ParameterizedTest(name = "Date UT {0}-{1}-{2} {3}:{4}:{5} corresponds to Julian Date {6}")
    @CsvSource(
        {"2015, 5, 10, 6, 0, 0, 2457152.75",
        "2015, 5, 10, 18, 0, 0, 2457153.25",
        "1776, 7, 4, 0, 0, 0, 2369915.5",
        "2010, 5, 6, 12, 0, 0, 2455323.0",
        "2012, 4, 1, 20, 52, 48, 2456019.37"
        }
    )
    void givenADateWithTime_whenConvertToJulianDayNumber_thenCorrect(int year, int month, int day, int hour, int minute, int second, double expectedJulianDate) {
        UT date = new UT(new Year(year), new Month(month), new Day(day), new Hour(hour), new Minute(minute), new Second(second));
        JulianDate julianDate = date.toJulianDayNumber();
        assertEquals(expectedJulianDate, julianDate.getValue());
    }

    @ParameterizedTest(name = "Julian Date {6} corresponds to Date UT {0}-{1}-{2} {3}:{4}:{5}")
    @CsvSource(
        {"2015, 5, 10, 6, 0, 0, 2457152.75",
        "2015, 5, 10, 18, 0, 0, 2457153.25",
        "1776, 7, 4, 0, 0, 0, 2369915.5",
        "2010, 5, 6, 12, 0, 0, 2455323.0",
        "2012, 4, 1, 20, 52, 48, 2456019.37"
        }
    )
    void givenAJulianDate_whenConvertToDate_thenCorrect(int year, int month, int day, int hour, int minute, int second, double expectedJulianDate) {
        JulianDate julianDate = new JulianDate(expectedJulianDate); 
        UT date = julianDate.toDate();
        assertEquals(year, date.getYear());
        assertEquals(month, date.getMonth());
        assertEquals(day, date.getDay());
    }

    @ParameterizedTest(name = "Date UT {0}-{1}-{2} corresponds to day of the week {3}")
    @CsvSource({
        "1776, 7, 4, Thursday",
        "2011, 9, 11, Sunday"}
    )
    void givenADate_whenGetDayOfTheWeek_thenCorrect(int year, int month, int day, String expectedDayOfWeek) {
        UT date = new UT(new Year(year), new Month(month), new Day(day));
        assertEquals(expectedDayOfWeek, date.getDayOfWeek());
    }

    @Test
    void givenDate_whenRequestHowManyDaysIntoTheYear_thenCorrect() {
        UT date = new UT(new Year(2009), new Month(10), new Day(30));
        assertEquals(303, date.getDayOfYear());
    }

    @Test
    void givenADateWithDays_whenRequestDate_thenCorrect() {
        UT date = new UT(new Year(1900), new Day(250));
        assertEquals(1900, date.getYear());
        assertEquals(9, date.getMonth());
        assertEquals(7, date.getDay());
    }

    @ParameterizedTest(name = "Longitude {0} corresponds to time zone offset {1}")
    @CsvSource({
        "0, 0",
        "15, 1",
        "30, 2",
        "-15, -1",
        "-30, -2"
    })
    void givenALongitude_whenRequestTimeZoneOffset_thenCorrect(int longitudeValue, int expectedOffset) {
        Degree longitude = new Degree(longitudeValue);
        int offset = LCT.getTimeZoneOffsetFromLongitude(longitude);
        assertEquals(expectedOffset, offset);
    }

    @ParameterizedTest(name = "Longitude {0} corresponds to time zone offset {1}")
    @CsvSource({
        "0, 0",
        "15, 1",
        "30, 2",
        "-15, -1",
        "-30, -2"
    })
    void givenALongitudeInDms_whenRequestTimeZoneOffset_thenCorrect(int longitudeValue, int expectedOffset) {
        Dms longitude = new Dms(new Degree(longitudeValue), new ArcMinute(0), new ArcSecond(0), false);
        int offset = LCT.getTimeZoneOffsetFromLongitude(longitude);
        assertEquals(expectedOffset, offset);
    }

}