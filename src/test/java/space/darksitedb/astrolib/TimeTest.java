package space.darksitedb.astrolib;

import static org.junit.jupiter.api.Assertions.assertEquals;
import space.darksitedb.astrolib.units.*;

import org.junit.jupiter.api.Test;

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

}
